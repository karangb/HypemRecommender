package com.hypemrecommender.tasks;

import com.google.common.collect.ImmutableMultimap;
import com.hypemrecommender.models.Track;
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
    private final Queue<User> users;

    public Crawler(final String name, final UserRepository userRepository, final Queue<User> users) {
        super(name);
        this.userRepository = userRepository;
        this.users = users;
    }

    @Override
    public void execute(final ImmutableMultimap<String, String> parameters, final PrintWriter output) throws Exception {
        String initialUsername = parameters.get("initialUser").iterator().next();
        User initialUser = userRepository.createUser(initialUsername);

        Set<User> seen = new HashSet<>();
        users.offer(initialUser);

        while(!users.isEmpty())
        {
            User user = users.poll();
            if(seen.contains(user))
                continue;

            if(!user.exists())
                user.provision();

            Collection<Track> favourites = user.getFavourites();
            for(Track favourite : favourites)
            {
                if(!favourite.exists())
                    favourite.provision();
                users.addAll(favourite.getFavouritedBy());
            }
            seen.add(user);
        }
    }
}
