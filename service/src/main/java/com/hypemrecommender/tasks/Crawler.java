package com.hypemrecommender.tasks;

import com.google.common.collect.ImmutableMultimap;
import com.hypemrecommender.CloudTrack;
import com.hypemrecommender.MusicCloudApi;
import com.hypemrecommender.models.User;
import com.hypemrecommender.models.UserRepository;
import com.yammer.dropwizard.tasks.Task;

import java.io.PrintWriter;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 18/11/2013
 * Time: 20:18
 */
public class Crawler extends Task {

    private final UserRepository userRepository;
    private final MusicCloudApi api;
    private final Queue<String> users;

    public Crawler(final String name, final UserRepository userRepository, final MusicCloudApi api, final Queue<String> users) {
        super(name);
        this.userRepository = userRepository;
        this.api = api;
        this.users = users;
    }

    @Override
    public void execute(final ImmutableMultimap<String, String> parameters, final PrintWriter output) throws Exception {
        String initialUsername = parameters.get("initialUser").iterator().next();
        Set<String> seen = new HashSet<>();
        users.offer(initialUsername);

        while(!users.isEmpty())
        {
            String username = users.poll();
            if(seen.contains(username) || userRepository.exists(username))
                continue;

            Collection<CloudTrack> favourites = api.fetchFavourites(username);

            User user = userRepository.createUser(username);
            user.addFavourites(favourites);

            for(CloudTrack favourite : favourites)
            {
                Collection<String> favouritedBy = favourite.getFavouritedBy();
                users.addAll(favouritedBy);
            }
            seen.add(username);
        }
    }
}
