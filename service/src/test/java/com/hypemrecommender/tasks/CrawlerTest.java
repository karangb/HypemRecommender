package com.hypemrecommender.tasks;

import com.google.common.collect.ImmutableSetMultimap;
import com.hypemrecommender.models.Track;
import com.hypemrecommender.models.User;
import com.hypemrecommender.models.UserRepository;
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
    UserRepository userRepository;
    @Mock User user1;
    @Mock User user2;
    @Mock User user3;

    @Mock Track track1;
    @Mock Track track2;
    @Mock Track track3;

    Collection<User> track1FavouritedBy;
    Collection<User> track2FavouritedBy;
    Collection<User> track3FavouritedBy;

    Collection<Track> user1Favourites;
    Collection<Track> user2Favourites;
    Collection<Track> user3Favourites;
    private ImmutableSetMultimap<String, String> parameters;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        populateTrackFavouritedByList();
        populateUserFavourites();

        when(user1.exists()).thenReturn(false);
        when(user2.exists()).thenReturn(true);
        when(user3.exists()).thenReturn(false);

        when(userRepository.createUser("karan")).thenReturn(user1);
        parameters = new ImmutableSetMultimap.Builder<String, String>()
                .put("initialUser", "karan")
                .put("userCount", "3")
                .build();
    }

    @Test
    public void testNonExistingUsersAreProvisioned() throws Exception {
        setFavouritesExpectations();

        Crawler crawlTask = new Crawler("crawl", userRepository, new LinkedList<User>());
        crawlTask.execute(parameters, null);

        verify(user1).provision();
        verify(user3).provision();

        verify(user2, never()).provision();
    }

    @Test
    public void testNonExistingTracksAreProvisioned() throws Exception {
        setFavouritesExpectations();
        allTracksExistExceptTrack3();

        Crawler crawlTask = new Crawler("crawl", userRepository, new LinkedList<User>());
        crawlTask.execute(parameters, null);

        verify(track3).provision();

        verify(track1, never()).provision();
        verify(track2, never()).provision();
    }

    private void setFavouritesExpectations() {
        when(track1.getFavouritedBy()).thenReturn(track1FavouritedBy);
        when(track2.getFavouritedBy()).thenReturn(track2FavouritedBy);
        when(track3.getFavouritedBy()).thenReturn(track3FavouritedBy);

        when(user1.getFavourites()).thenReturn(user1Favourites);
        when(user2.getFavourites()).thenReturn(user2Favourites);
        when(user3.getFavourites()).thenReturn(user3Favourites);
    }

    private void allTracksExistExceptTrack3() {
        when(track1.exists()).thenReturn(true);
        when(track2.exists()).thenReturn(true);
        when(track3.exists()).thenReturn(false);
    }

    private void populateUserFavourites() {
        user1Favourites = new ArrayList<>();
        user2Favourites = new ArrayList<>();
        user3Favourites = new ArrayList<>();

        user1Favourites.add(track1);
        user1Favourites.add(track2);

        user2Favourites.add(track2);

        user3Favourites.add(track2);
        user3Favourites.add(track3);
    }

    private void populateTrackFavouritedByList() {
        track1FavouritedBy = new ArrayList<>();
        track2FavouritedBy = new ArrayList<>();
        track3FavouritedBy = new ArrayList<>();
        track1FavouritedBy.add(user1);

        track2FavouritedBy.add(user1);
        track2FavouritedBy.add(user2);
        track2FavouritedBy.add(user3);

        track3FavouritedBy.add(user3);
    }

}
