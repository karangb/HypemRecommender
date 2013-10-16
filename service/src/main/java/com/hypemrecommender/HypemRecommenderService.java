package com.hypemrecommender;

import com.hypemrecommender.dal.MongoTrackDao;
import com.hypemrecommender.dal.MongoUserDao;
import com.hypemrecommender.dal.TrackDao;
import com.hypemrecommender.dal.UserDao;
import com.hypemrecommender.engine.HypemRecommender;
import com.hypemrecommender.resources.RecommendationResource;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import org.apache.mahout.cf.taste.impl.model.mongodb.MongoDBDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

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

        MongoClient client = new MongoClient(configuration.getHost());
        DB db = client.getDB(configuration.getDb());
        DBCollection mongo_data_model_map = db.getCollection("mongo_data_model_map");
        UserDao userDao = new MongoUserDao(mongo_data_model_map, db.getCollection("users"));
        TrackDao trackDao = new MongoTrackDao(mongo_data_model_map, db.getCollection("tracks"));
        environment.addResource(new RecommendationResource(new HypemRecommender(recommender, userDao, trackDao)));
    }
}
