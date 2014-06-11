package infoapi;

/** Info公告接口.
 * @author ZCH
 */
public class InfoAnnouncement {
    /** 定义变量.
     * 公告标题
     */
    private String title;

    /** 定义变量.
     * 公告链接
     */
    private String content;

    /** 定义变量.
     * 公告发布时间
     */
    private String time;

    /** 定义变量.
     * 公告id
     */
    private long id;

    /** 设置函数.
     * 设置title
     * @param ntitle String
     */
    public final void setTitle(final String ntitle) {
        this.title = ntitle;
    }

    /** 获取函数.
     * 获取title
     * @return title
     */
    public final String getTitle() {
        return this.title;
    }

    /** 设置函数.
     * 设置content
     * @param ncontent String
     */
    public final void setContent(final String ncontent) {
        this.content = ncontent;
    }

    /** 获取函数.
     * 获取connent
     * @return connect
     */
    public final String getContent() {
        return this.content;
    }

    /** 设置函数.
     * 设置time
     * @param ntime String
     */
    public final void setTime(final String ntime) {
        this.time = ntime;
    }

    /** 获取函数.
     * 获取time
     * @return time
     */
    public final String getTime() {
        return this.time;
    }

    /** 设置函数.
     * 设置id
     * @param nid long
     */
    public final void setID(final long nid) {
        this.id = nid;
    }

    /** 获取函数.
     * 获取id
     * @return id
     */
    public final long getID() {
        return this.id;
    }
}
