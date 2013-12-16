package com.hypemrecommender;

import com.hypemrecommender.blogapi.MusicCloudApi;
import com.hypemrecommender.dal.*;
import com.hypemrecommender.engine.HypemRecommender;
import com.hypemrecommender.models.UserImplRepository;
import com.hypemrecommender.models.UserRepository;
import com.hypemrecommender.recommendation.OryxClient;
import com.hypemrecommender.recommendation.RecommendationClient;
import com.hypemrecommender.resources.RecommendationResource;
import com.hypemrecommender.tasks.Crawler;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.config.FilterBuilder;
import org.apache.mahout.cf.taste.impl.model.mongodb.MongoDBDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 04/09/2013
 * Time: 22:51
 */
public class HypemRecommenderService extends Service<HypemRecommenderConfiguration> {

    public static void main(String[] args) throws Exception {
        new HypemRecommenderService().run(args);
    }

    @Override
    public void initialize(Bootstrap<HypemRecommenderConfiguration> bootstrap) {
        bootstrap.setName("hypem-recommender");
    }

    @Override
    public void run(HypemRecommenderConfiguration configuration, Environment environment) throws Exception {
        DataModel dataModel = new MongoDBDataModel(configuration.getHost(), 27017, configuration.getDb(), "ratings", false, false, null);
        ItemSimilarity itemSimilarity = new LogLikelihoodSimilarity(dataModel);
        Recommender recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);

        allowOrigins(environment, configuration.getAllowedOrigins());

        MongoClient client = new MongoClient(configuration.getHost());
        DB db = client.getDB(configuration.getDb());

        DBCollection mongo_data_model_map = db.getCollection("mongo_data_model_map");
        DBCollection userCollection = db.getCollection("users");
        DBCollection trackCollection = db.getCollection("tracks");

        UserDal userDal = new MahoutUserDao(mongo_data_model_map, userCollection);
        TrackDal trackDal = new MahoutTrackDal(mongo_data_model_map, trackCollection);

        SoundcloudUserRepository userDaoRepository = new SoundcloudUserRepository(userCollection, trackCollection);
        final RecommendationClient oryxClient = new OryxClient(configuration.getOryxUrl());
        UserRepository userRepository = new UserImplRepository(userDaoRepository, new SoundcloudTrackRepository(trackCollection), oryxClient);
        final MusicCloudApi soundcloudClient = new SoundcloudClient(configuration.getSoundcloudKey());

        environment.addResource(new RecommendationResource(userRepository, new HypemRecommender(recommender, userDal, trackDal)));
        environment.addTask(new Crawler("soundcloudCrawler", userRepository, soundcloudClient, new TimedQueue<String>(20000)));
    }

    private void allowOrigins(final Environment environment, List<String> allowedOrigins) {
        FilterBuilder filterConfig = environment.addFilter(CrossOriginFilter.class, "/*");
        filterConfig.setInitParam(CrossOriginFilter.PREFLIGHT_MAX_AGE_PARAM, String.valueOf(60*60*24)); // 1 day - jetty-servlet CrossOriginFilter will convert to Int.

        StringBuffer commaSeparatedAllowedOrigins = commaSeparated(allowedOrigins);
        filterConfig.setInitParam(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, commaSeparatedAllowedOrigins.toString()); // comma separated list of allowed origin domains
    }

    private StringBuffer commaSeparated(final List<String> allowedOrigins) {
        StringBuffer commaSeparatedAllowedOrigins = new StringBuffer();
        for(int i=0; i<allowedOrigins.size(); i++)
        {
            commaSeparatedAllowedOrigins.append(allowedOrigins.get(i));
            if(i != allowedOrigins.size()-1)
            {
                commaSeparatedAllowedOrigins.append(",");
            }
        }
        return commaSeparatedAllowedOrigins;
    }
}
