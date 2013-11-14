import com.hypemrecommender.HypemCrawler;
import com.hypemrecommender.representations.HypemUserRepresentation;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 09/11/2013
 * Time: 21:18
 */
public class HypemCrawlerTest {


    /*
    This test uses the real server, which is not ideal. Update to use a recorder such
    as Betamax, or build a custom fake server
     */
    @Test
    public void testFetchUser()
    {
        Client client = Client.create();
        WebResource userResource = client.resource("http://gijwi.com:3001/user");

        HypemCrawler crawler = new HypemCrawler(userResource);
        HypemUserRepresentation user = crawler.fetchUser("karan");

        assertThat(user.name, equalTo("karan"));
    }
}
