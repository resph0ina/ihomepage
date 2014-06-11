package rrapi;
import com.renren.api.RennClient;
import com.renren.api.RennException;
import com.renren.api.service.Feed;
import com.renren.api.service.FeedType;

/***
 * @author YT
 */
public class RrGetFeed {
    /** 获取新鲜事.
     * @param client RennClinet
     * @param page int
     * @return renren feed
     */
    public static RrFeed[] getFeed(final RennClient client,
            final int page) {
        FeedType []feedtype = {FeedType.ALL};
        try {
            Feed []feed = client.getFeedService()
                    .listFeed(feedtype, null, null, page);
            RrFeed[] rrf = new RrFeed[feed.length];
            System.out.println("total=" + feed.length);
            for (int i = 0; i < feed.length; i++) {
                if (feed[i] != null) {
                    System.out.println(i + "@" + feed[i].getMessage());
                    if (feed[i].getResource().getContent() == null) {
                        rrf[i] = new RrFeed(feed[i].getMessage()
                                + feed[i].getResource().getTitle(),
                                feed[i].getTime(),
                                feed[i].getResource().getId(),
                                feed[i].getSourceUser().getName(),
                                feed[i].getType().name(),
                                feed[i].getSourceUser().getId());
                    } else {
                        rrf[i] = new RrFeed(feed[i].getMessage()
                                + feed[i].getResource().getContent(),
                                feed[i].getTime(),
                                feed[i].getResource().getId(),
                                feed[i].getSourceUser().getName(),
                                feed[i].getType().name(),
                                feed[i].getSourceUser().getId());
                    }
                }
            }
            return rrf;
        } catch (RennException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
