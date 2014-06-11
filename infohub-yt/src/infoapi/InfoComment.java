package infoapi;
/** info评价表项.
 * @author Ljnanest
 */
class InfoComment {
    /** 定义变量.
     * 论内容
     */
    private String content;
     /** 定义变量.
     * 评论时间
     */
    private String time;
     /** 定义变量.
     * 用户名称
     */
    private String user;
     /** 定义变量.
     * 用户id
     */
    private long uid;
     /** 定义变量.
     * 评论id
     */
    private long id;

    /** 设置函数.
     * 设置content
     * @param ncontent String
     */
    void setContent(final String ncontent) {
        this.content = ncontent;
    }

    /** 获取函数.
     * 获取content
     * @return content
     */
    String getContent() {
        return this.content;
    }

    /** 设置函数.
     * 设置time
     * @param ntime String
     */
    void setTime(final String ntime) {
        this.time = ntime;
    }

    /** 获取函数.
     * 获取time
     * @return time
     */
    String getTime() {
        return this.time;
    }

    /** 设置函数.
     * 设置user
     * @param nuser String
     */
    void setUser(final String nuser) {
        this.user = nuser;
    }

    /** 获取函数.
     * 获取user
     * @return user
     */
    String getUser() {
        return this.user;
    }

    /** 设置函数.
     * 设置uid
     * @param nuid long
     */
    void setUid(final long nuid) {
        this.uid = nuid;
    }

    /** 获取函数.
     * 获取uid
     * @return uid
     */
    long getUid() {
        return this.uid;
    }

    /** 设置函数.
     * 设置id
     * @param nid long
     */
    void setID(final long nid) {
        this.id = nid;
    }

    /** 获取函数.
     * 获取id
     * @return id
     */
    long getID() {
        return this.id;
    }

}
