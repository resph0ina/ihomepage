package rrapi;

import com.renren.api.RennClient;
import com.renren.api.RennException;
import com.renren.api.service.CommentType;
/***
 * @author YT
 */
public class RrSendComment {
    /**新加评论.
     * @param client RennClinet
     * @param rf RrFeed
     * @param c String
     * @throws RennException if has error
     */
    public static void addPureComment(final RennClient client,
            final RrFeed rf, final String c) throws RennException {
        String commentType = rf.getType();
        if (commentType == "SHARE_ALBUM"
                || commentType == "SHARE_VIDEO"
                || commentType == "SHARE_PHOTO"
                || commentType == "SHARE_LINK"
                || commentType == "SHARE_BLOG") {
            client.getCommentService().putComment(c,
                    (long) 0, CommentType.SHARE, rf.getUid(), rf.getId());
        }
        if (commentType == "UPDATE_STATUS") {
            client.getCommentService().putComment(c,
                    (long) 0, CommentType.STATUS, rf.getUid(), rf.getId());
        }
        if (commentType == "PUBLISH_BLOG") {
            client.getCommentService().putComment(c,
                    (long) 0, CommentType.BLOG, rf.getUid(), rf.getId());
        }
        if (commentType == "PUBLISH_ONE_PHOTO") {
            client.getCommentService().putComment(c,
                    (long) 0, CommentType.PHOTO, rf.getUid(), rf.getId());
        }
        if (commentType == "PUBLISH_MORE_PHOTO") {
            client.getCommentService().putComment(c,
                    (long) 0, CommentType.ALBUM, rf.getUid(), rf.getId());
        }
    }

    /** 添加恢复.
     * @param client RennClient
     * @param rc RrComment
     * @param rf RrFeed
     * @param c String
     * @throws RennException if has error
     */
    public static void addComment(final RennClient client, final RrComment rc,
            final RrFeed rf, final String c) throws RennException {
        client.getCommentService().putComment(c,
                rc.getUid(), rc.getType(), rf.getUid(), rf.getId());
    }
}
