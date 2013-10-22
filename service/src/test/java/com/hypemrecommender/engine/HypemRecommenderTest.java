package com.hypemrecommender.engine;

import com.hypemrecommender.dal.TrackDao;
import com.hypemrecommender.dal.UserDao;
import com.hypemrecommender.representations.Track;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 09/09/2013
 * Time: 22:33
 */
public class HypemRecommenderTest {
    private Recommender mahout;
    private UserDao userDao;
    private TrackDao trackDao;
    private Track track2;
    private Track track1;
    private List<RecommendedItem> recommendedItems;

    @Before
    public void setUp()
    {
        userDao = mock(UserDao.class);
        trackDao = mock(TrackDao.class);
        mahout = mock(Recommender.class);

        track1 = new Track("1x991", "track1", "artist1");
        track2 = new Track("1x992", "track2", "artist2");

        RecommendedItem recommendedItem1 = new FakeRecommendedItem(55);
        RecommendedItem recommendedItem2 = new FakeRecommendedItem(66);

        recommendedItems = new ArrayList<>();
        recommendedItems.add(recommendedItem1);
        recommendedItems.add(recommendedItem2);
    }

    @Test
    public void testGetRecommendationForUser() throws TasteException {
        long userId = 123;
        when(userDao.getUserId("karan")).thenReturn(userId);
        when(mahout.recommend(userId, 100)).thenReturn(recommendedItems);
        when(trackDao.getTrack(55)).thenReturn(track1);
        when(trackDao.getTrack(66)).thenReturn(track2);

        HypemRecommender musicRecommender = new HypemRecommender(mahout, userDao, trackDao);
        List<Track> recommendedTracks = musicRecommender.getRecommendedTracks("karan");

        Assert.assertThat(recommendedTracks.get(0), equalTo(track1));
        Assert.assertThat(recommendedTracks.get(1), equalTo(track2));
    }

    private class FakeRecommendedItem implements RecommendedItem {
        private final int itemID;

        public FakeRecommendedItem(final int itemID) {
            this.itemID = itemID;
        }

        @Override
        public long getItemID() {
            return this.itemID;
        }

        @Override
        public float getValue() {
            return 0;
        }
    }
}
