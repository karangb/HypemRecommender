package com.hypemrecommender.tasks;

import com.google.common.collect.ImmutableSetMultimap;
import com.hypemrecommender.blogapi.CloudTrack;
import com.hypemrecommender.blogapi.MusicCloudApi;
import com.hypemrecommender.models.User;
import com.hypemrecommender.models.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 18/11/2013
 * Time: 20:20
 */
public class CrawlerTest {

    @Mock UserRepository userRepository;
    @Mock MusicCloudApi api;
    @Mock User user1;
    @Mock User user2;

    @Mock CloudTrack cloudTrack1;
    @Mock CloudTrack cloudTrack2;

    Map<CloudTrack, Collection<String>> likes;
    private ImmutableSetMultimap<String, String> parameters;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        likes = new HashMap<>();

        when(userRepository.createUser("myNonCrawledUser1")).thenReturn(user1);
        when(userRepository.createUser("myNonCrawledUser2")).thenReturn(user2);

        parameters = new ImmutableSetMultimap.Builder<String, String>()
                .put("initialUser", "myNonCrawledUser1")
                .build();
    }

    @Test
    public void testNonExistingUsersAreProvisioned() throws Exception {
        existingUsers("myCrawledUser");
        nonExistingUsers("myNonCrawledUser1", "myNonCrawledUser2");

        LikeCollection user1LikeCollection = user("myNonCrawledUser1").likes(cloudTrack1);
        LikeCollection user2LikeCollection = user("myNonCrawledUser2").likes(cloudTrack1).likes(cloudTrack2);

        Crawler crawlTask = new Crawler("crawl", userRepository, api, new LinkedList<String>());
        crawlTask.execute(parameters, null);

        verify(userRepository).createUser("myNonCrawledUser1");
        verify(userRepository).createUser("myNonCrawledUser2");
        verify(user1).addFavourites(user1LikeCollection.getFavourites());
        verify(user2).addFavourites(user2LikeCollection.getFavourites());

        verify(userRepository, never()).createUser("myCrawledUser");
    }

    private LikeCollection user(String username)
    {
         return new LikeCollection(username);
    }

    private class LikeCollection {
        private final String username;
        private final ArrayList<CloudTrack> favourites;

        LikeCollection(String username)
        {
            this.username = username;
            favourites = new ArrayList<>();
            when(api.fetchFavourites(username)).thenReturn(favourites);
        }

        public LikeCollection likes(CloudTrack cloudTrack)
        {
            favourites.add(cloudTrack);
            if(!likes.containsKey(cloudTrack))
            {
                likes.put(cloudTrack, new ArrayList<String>());
                when(cloudTrack.getFavouritedBy()).thenReturn(likes.get(cloudTrack));
            }
            likes.get(cloudTrack).add(username);

            return this;
        }

        public ArrayList<CloudTrack> getFavourites() {
            return favourites;
        }
    }

    private void nonExistingUsers(String... users) {
        for(String user : users)
        {
            when(userRepository.exists(user)).thenReturn(false);
        }
    }

    private void existingUsers(final String user) {
        when(userRepository.exists(user)).thenReturn(true);
    }

}
