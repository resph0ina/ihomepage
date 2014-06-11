package rrapi;

import com.renren.api.service.CommentType;

/** 评论.
 * @author Ljnanest
 */
public class RrComment {
    /** 定义变量.
     * 定义评论内容
     */
    private String content;
    /** 定义变量.
     * 定义评论时间
     */
    private String time;
    /** 定义变量.
     * 定义用户名
     */
    private String user;
    /** 定义变量.
     * 定义用户id
     */
    private long uid;
    /** 定义变量.
     * 定义评论id
     */
    private long id;
    /** 定义变量.
     * 定义类型
     */
    private CommentType type;

    /** 设置.
     * @param c String
     * @param t String
     * @param i long
     * @param u String
     * @param ui long
     * @param tp CommentType
     */
    public RrComment(final String c, final String t, final long i,
            final String u, final long ui, final CommentType tp) {
        content = c; time = t; id = i; user = u; uid = ui; type = tp;
    }

    /** 获取类型.
     * @return type
     */
    public final CommentType getType() { return type; }

    /** 获取评论内容.
     * @return content
     */
    public final String getContent() {
        return content;
    }

    /** 获取评论时间.
     * @return time
     */
    public final String getTime() {
        return time;
    }

    /** 获取评论id.
     * @return id
     */
    public final long getId() {
        return id;
    }

    /** 获取用户.
     * @return user
     */
    public final String getUser() {
        return user;
    }

    /** 获取用户id.
     * @return uid
     */
    public final long getUid() {
        return uid;
    }
}
