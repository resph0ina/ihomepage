package rrapi;

/** 新鲜事.
 * @author Ljnanest
 */
public class RrFeed {
    /** 备注.
     *
     */
    private String content;
    /** 备注.
    *
    */
    private String time;
    /** 备注.
    *
    */
    private long id;
    /** 备注.
    *
    */
    private String source;
    /** 备注.
    *
    */
    private String type;
    /** 备注.
    *
    */
    private long uid;

    /** 构造函数.
     * @param c String
     * @param t String
     * @param i long
     * @param s String
     * @param tp String
     * @param u long
     */
    public RrFeed(final String c, final String t,
            final long i, final String s, final String tp, final long u) {
        content = c; time = t; id = i; source = s; type = tp; uid = u;
    }

    /**获取新鲜事内容.
     * @return content
     */
    public final String getContent() { return this.content; }

    /** 获取新鲜事时间.
     * @return time
     */
    public final String getTime() { return this.time; }

    /** 获取新鲜事id.
     * @return id
     */
    public final long getId() { return this.id; }

    /** 获取新鲜事来源，发布之前.
     * @return source
     */
    public final String getSource() { return this.source; }

    /** 获取新鲜事类型.
     * @return id
     */
    public final String getType() { return this.type; }

    /** 获取新鲜事用户id.
     * @return uid
     */
    public final long getUid() { return this.uid; }
}
