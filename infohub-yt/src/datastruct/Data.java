package datastruct;
/** 基本类.
 * 存储各种参数.
 * @author Ljnanest
 */
public class Data {
    /** 定义变量.
     * 用户名
     */
    private String userName = new String();

    /** 定义变量.
     * 信息时间
     */
    private String time = new String();

    /** 定义变量.
     * 评论时间
     */
    private String commentTime = new String();

    /** 定义变量.
     * 信息的唯一标示
     */
    private String id = new String();

    /** 定义变量.
     *  信息标题
     */
    private String title = new String();

    /** 定义变量.
     * 信息链接
     */
    private String link = new String();

    /** 定义变量.
     * 信息内容
     */
    private String context = new String();

    /** 定义变量.
     * 评论的唯一标示
     */
    private String commentID = new String();

    /** 定义变量.
     * 信息的类型
     */
    private String type = new String();

    /** 获取函数.
     * 获取userName
     * @return userName
     */
    public final String getUserName() {
        return userName;
    }

    /** 设置函数.
     * 设置userName
     * @param nuserName String
     */
    public final void setUserName(final String nuserName) {
        this.userName = nuserName;
    }

    /** 获取函数.
     * 获取time
     * @return time
     */
    public final String getTime() {
        return time;
    }

    /** 设置函数.
     * 设置time
     * @param ntime String
     */
    public final void setTime(final String ntime) {
        this.time = ntime;
    }

    /** 获取函数.
     * 获取commentTime
     * @return commentTime
     */
    public final String getCommentTime() {
        return commentTime;
    }

    /** 设置函数.
     * 设置commentTime
     * @param ncommentTime String
     */
    public final void setCommentTime(final String ncommentTime) {
        this.commentTime = ncommentTime;
    }

    /** 获取函数.
     * 获取id
     * @return id
     */
    public final String getId() {
        return id;
    }

    /** 设置函数.
     * 设置id
     * @param nid String
     */
    public final void setId(final String nid) {
        this.id = nid;
    }

    /** 获取函数.
     * 获取title
     * @return title
     */
    public final String getTitle() {
        return title;
    }

    /** 设置函数.
     * 设置title
     * @param ntitle String
     */
    public final void setTitle(final String ntitle) {
        this.title = ntitle;
    }

    /** 获取函数.
     * 获取link
     * @return link
     */
    public final String getLink() {
        return link;
    }

    /** 设置函数.
     * 设置link
     * @param nlink String
     */
    public final void setLink(final String nlink) {
        this.link = nlink;
    }

    /** 获取函数.
     * 获取context
     * @return context
     */
    public final String getContext() {
        return context;
    }

    /** 设置函数.
     * 设置context
     * @param ncontext String
     */
    public final void setContext(final String ncontext) {
        this.context = ncontext;
    }

    /** 获取函数.
     * 获取commentID
     * @return commentID
     */
    public final String getCommentID() {
        return commentID;
    }

    /** 设置函数.
     * 设置commentID
     * @param ncommentID String
     */
    public final void setCommentID(final String ncommentID) {
        this.commentID = ncommentID;
    }

    /** 获取函数.
     * 获取type
     * @return type
     */
    public final String getType() {
        return type;
    }

    /** 设置函数.
     * 设置type
     * @param ntype String
     */
    public final void setType(final String ntype) {
        this.type = ntype;
    }
}
