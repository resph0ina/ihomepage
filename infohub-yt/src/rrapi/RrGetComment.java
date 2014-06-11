package rrapi;

import com.renren.api.RennClient;
import com.renren.api.RennException;
import com.renren.api.service.Comment;
import com.renren.api.service.CommentType;

/***
 * @author YT
 */
public class RrGetComment {
    /** 获取评论信息.
     * @param client RennClient
     * @param page int
     * @param rf RrFeed
     * @return rrComment
     * @throws RennException if has error
     */
    public static RrComment[] getComment(final RennClient client,
            final int page, final RrFeed rf) throws RennException {
        long l = rf.getId();
        long m = rf.getUid();
        String commentType = rf.getType();
        if (commentType == "SHARE_ALBUM"
                || commentType == "SHARE_VIDEO"
                || commentType == "SHARE_PHOTO"
                || commentType == "SHARE_LINK"
                || commentType == "SHARE_BLOG") {
            Comment []comment = client.getCommentService().listComment(true,
                    null, page, CommentType.SHARE, (long) m, (long) l);
            RrComment []rrC = new RrComment[comment.length];
            for (int i = 0; i < comment.length; i++) {
                rrC[i] = new RrComment(comment[i].getContent(),
                        comment[i].getTime(), comment[i].getId(),
        client.getUserService().getUser(comment[i].getAuthorId()).getName(),
                        comment[i].getAuthorId(), CommentType.SHARE);
            }
            return rrC;
        }
        if (commentType == "UPDATE_STATUS") {
            Comment []comment = client.getCommentService().listComment(true,
                    null, page, CommentType.STATUS, (long) m, (long) l);
            RrComment []rrC = new RrComment[comment.length];
            for (int i = 0; i < comment.length; i++) {
                rrC[i] = new RrComment(comment[i].getContent(),
                        comment[i].getTime(), comment[i].getId(),
        client.getUserService().getUser(comment[i].getAuthorId()).getName(),
                        comment[i].getAuthorId(), CommentType.STATUS);
            }
            return rrC;
        }
        if (commentType == "PUBLISH_BLOG") {
            Comment []comment = client.getCommentService().listComment(true,
                    null, page, CommentType.BLOG, (long) m, (long) l);
            RrComment []rrC = new RrComment[comment.length];
            for (int i = 0; i < comment.length; i++) {
                rrC[i] = new RrComment(comment[i].getContent(),
                        comment[i].getTime(), comment[i].getId(),
        client.getUserService().getUser(comment[i].getAuthorId()).getName(),
                        comment[i].getAuthorId(), CommentType.BLOG);
            }
            return rrC;
        }
        if (commentType == "PUBLISH_ONE_PHOTO") {
            Comment []comment = client.getCommentService().listComment(true,
                    null, page, CommentType.PHOTO, (long) m, (long) l);
            RrComment []rrC = new RrComment[comment.length];
            for (int i = 0; i < comment.length; i++) {
                rrC[i] = new RrComment(comment[i].getContent(),
                        comment[i].getTime(), comment[i].getId(),
        client.getUserService().getUser(comment[i].getAuthorId()).getName(),
        comment[i].getAuthorId(), CommentType.PHOTO);
            }
            return rrC;
        }
        if (commentType == "PUBLISH_MORE_PHOTO") {
            Comment []comment = client.getCommentService().listComment(true,
                    null, page, CommentType.ALBUM, (long) m, (long) l);
            RrComment []rrC = new RrComment[comment.length];
            for (int i = 0; i < comment.length; i++) {
                rrC[i] = new RrComment(comment[i].getContent(),
                        comment[i].getTime(), comment[i].getId(),
        client.getUserService().getUser(comment[i].getAuthorId()).getName(),
                        comment[i].getAuthorId(), CommentType.ALBUM);
            }
            return rrC;
        }
        return null;
    }
}
