package com.hypemrecommender.engine;

import com.hypemrecommender.dal.TrackDal;
import com.hypemrecommender.dal.UserDal;
import com.hypemrecommender.representations.TrackRepresentation;
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
    private UserDal userDal;
    private TrackDal trackDal;
    private TrackRepresentation track2;
    private TrackRepresentation track1;
    private List<RecommendedItem> recommendedItems;

    @Before
    public void setUp()
    {
        userDal = mock(UserDal.class);
        trackDal = mock(TrackDal.class);
        mahout = mock(Recommender.class);

        track1 = new TrackRepresentation("1x991", "track1", "artist1");
        track2 = new TrackRepresentation("1x992", "track2", "artist2");

        RecommendedItem recommendedItem1 = new FakeRecommendedItem(55);
        RecommendedItem recommendedItem2 = new FakeRecommendedItem(66);

        recommendedItems = new ArrayList<>();
        recommendedItems.add(recommendedItem1);
        recommendedItems.add(recommendedItem2);
    }

    @Test
    public void testGetRecommendationForUser() throws TasteException {
        long userId = 123;
        when(userDal.getUserId("karan")).thenReturn(userId);
        when(mahout.recommend(userId, 100)).thenReturn(recommendedItems);
        when(trackDal.getTrack(55)).thenReturn(track1);
        when(trackDal.getTrack(66)).thenReturn(track2);

        HypemRecommender musicRecommender = new HypemRecommender(mahout, userDal, trackDal);
        List<TrackRepresentation> recommendedTracks = musicRecommender.getRecommendedTracks("karan");

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
