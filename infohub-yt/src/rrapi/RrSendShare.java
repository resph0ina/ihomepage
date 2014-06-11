package rrapi;

import com.renren.api.RennClient;
import com.renren.api.RennException;
import com.renren.api.service.UGCType;
/** 人人分享功能.
 * @author Ljnanest
 */
public class RrSendShare {

    /** 添加URL.
     * @param client RenClient
     * @param c String
     * @param url Sting
     * @throws RennException if has error
     */
    public static final void addUrl(final RennClient client,
            final String c, final String url) throws RennException {
        client.getShareService().putShareUrl(c, url);
    }

    /** 添加分享.
     * @param client RennClient
     * @param rf RrFeed
     * @param c String
     * @throws RennException if has error
     */
    public static final void addShare(final RennClient client,
            final RrFeed rf, final String c) throws RennException {
        if (rf.getType() == "UPDATE_STATUS") {
            client.getStatusService().shareStatus(c, rf.getId(), rf.getUid());
        } else {
            if (rf.getType() == "SHARE_VIDEO"
                    || rf.getType() == "SHARE_PHOTO"
                    || rf.getType() == "SHARE_ALBUM"
                    || rf.getType() == "SHARE_LINK"
                    || rf.getType() == "SHARE_BLOG") {
                client.getShareService().putShareUgc(rf.getUid(),
                        c, rf.getId(), UGCType.TYPE_SHARE);
            } else {
                if (rf.getType() == "PUBLISH_BLOG") {
                    client.getShareService().putShareUgc(rf.getUid(),
                            c, rf.getId(), UGCType.TYPE_BLOG);
                } else {
                    if (rf.getType() == "PUBLISH_ONE_PHOTO") {
                        client.getShareService().putShareUgc(rf.getUid(),
                                c, rf.getId(), UGCType.TYPE_PHOTO);
                    } else {
                        if (rf.getType() == "PUBLISH_MORE_PHOTO") {
                            client.getShareService().putShareUgc(rf.getUid(),
                                    c, rf.getId(), UGCType.TYPE_ALBUM);
                        }
                    }
                }
            }
        }
    }
}
