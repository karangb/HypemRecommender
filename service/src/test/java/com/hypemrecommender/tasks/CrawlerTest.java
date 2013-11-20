package com.hypemrecommender.tasks;

import com.google.common.collect.ImmutableSetMultimap;
import com.hypemrecommender.models.Track;
import com.hypemrecommender.models.User;
import com.hypemrecommender.models.UserFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 18/11/2013
 * Time: 20:20
 */
public class CrawlerTest {

    @Mock
    UserFactory userFactory;
    @Mock User user1;
    @Mock User user2;
    @Mock User user3;

    @Mock Track track1;
    @Mock Track track2;
    @Mock Track track3;

    Collection<User> track1Users;
    Collection<User> track2Users;
    Collection<User> track3Users;

    Collection<Track> user1Favourites;
    Collection<Track> user2Favourites;
    Collection<Track> user3Favourites;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        track1Users = new ArrayList<>();
        track2Users = new ArrayList<>();
        track3Users = new ArrayList<>();

        user1Favourites = new ArrayList<>();
        user2Favourites = new ArrayList<>();
        user3Favourites = new ArrayList<>();

        track1Users.add(user1);

        track2Users.add(user1);
        track2Users.add(user2);
        track2Users.add(user3);

        track3Users.add(user3);

        user1Favourites.add(track1);
        user1Favourites.add(track2);

        user2Favourites.add(track2);

        user3Favourites.add(track2);
        user3Favourites.add(track3);

        when(user1.exists()).thenReturn(false);
        when(user2.exists()).thenReturn(true);
        when(user3.exists()).thenReturn(false);

        when(userFactory.getUser("karan")).thenReturn(user1);
    }

    @Test
    public void testExecute() throws Exception {
        ImmutableSetMultimap<String, String> parameters = new ImmutableSetMultimap.Builder<String, String>()
                .put("initialUser", "karan")
                .put("userCount", "3")
                .build();
        when(track1.getFavouritedBy()).thenReturn(track1Users);
        when(track2.getFavouritedBy()).thenReturn(track2Users);
        when(track3.getFavouritedBy()).thenReturn(track3Users);

        when(user1.getFavourites()).thenReturn(user1Favourites);
        when(user2.getFavourites()).thenReturn(user2Favourites);
        when(user3.getFavourites()).thenReturn(user3Favourites);

        Crawler crawlTask = new Crawler("crawl", userFactory, new LinkedList<User>());
        crawlTask.execute(parameters, null);

        verify(user1).provision();
        verify(user3).provision();

        verify(user2, never()).provision();
    }
}
