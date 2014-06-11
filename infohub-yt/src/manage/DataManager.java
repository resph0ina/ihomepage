package manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import datastruct.Data;
/***
 * @author yyk
 */
public class DataManager {

    /** magic number.
     * set magic number 80001
     */
    public static final int MN3 = 3;
    /** magic number.
     * set magic number 9
     */
    public static final int MN9 = 9;
    /** sql.
     * sql
     */
    private final String sqlDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
    /** sql.
     * sql
     */
    private final String sqlUrl = "jdbc:odbc:driver={MicroSoft Access Driver"
     + " (*.mdb)};DBQ=d:\\suning\\infohub\\web\\database.mdb";
    /** connection.
     * connection
     */
    private Connection conn = null;
    /** statement.
     * statement
     */
    private Statement stmt = null;
    /** resultSet.
     * resultSet
     */
    private ResultSet rs = null;
    /*** 构造函数.
     * 构造函数
     */
    public DataManager() {
    }
    /***connect.
     * connect
     */
    private synchronized void connect2DB() {
        try {
            Class.forName(sqlDriver);
            this.conn = DriverManager.getConnection(sqlUrl);
            this.stmt = this.conn.createStatement();
            } catch (Exception e) {
                System.out.println(e.toString());
                }
        }
    /***根据类型得到同一类型的信息.
     * 根据类型得到同一类型的信息
     * @param type 信息的类型
     * @return 返回所有类型相同的信息的ResultSet
     * @throws SQLException 不处理
     */
    public final ResultSet getDataByType(final String type)
    throws SQLException {
        connect2DB();
        String sql = "select * from DATA where TYPE= '" + type
                + "' order by WHEN desc";
        try {
            rs = stmt.executeQuery(sql);
            } catch (SQLException e) {
            e.printStackTrace();
            }
        return rs;
        }
    /*** 查看是否有同名信息.
     * 查看是否有同名信息
     * @param title 信息的标题
     * @return 返回是否存在
     * @throws SQLException 不处理
     */
    public final boolean haveTitle(final String title) throws SQLException {
        connect2DB();
        String sql = "select * from DATA where TITLE='" + title + "'";
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            closeConnection();
            return true;
            }
        closeConnection();
        return false;
        }
    /***通过id获得信息的所有评论.
     * 通过id获得信息的所有评论
     * @param id 信息的评论
     * @return 返回所有评论的ResultSet
     * @throws SQLException 不处理
     */
    public final ResultSet getData(final String id) throws SQLException {
        connect2DB();
        String sql = "select * from DATA where ID='" + id + "'";
        rs = stmt.executeQuery(sql);
        return rs;
        }
    /***通过评论id得到对应评论.
     * 通过评论id得到对应评论
     * @param id 评论id
     * @return 返回对应信息的ResultSet
     * @throws SQLException 不处理
     */
    public final ResultSet getComData(final String id) throws SQLException {
        connect2DB();
        String sql = "select * from DATA where COMMENTID=" + id;
        rs = stmt.executeQuery(sql);
        return rs;
        }
    /***插入评论.
     * 插入评论
     * @param context 评论的内容
     * @param time 信息的时间
     * @param userName 用户名
     * @param id 信息的唯一标示
     * @param type 信息的类型
     * @param commentTime 评论的时间
     * @throws SQLException 不处理
     */
    public final void insertComment(final String context, final String time,
    final String userName, final String id, final String type,
    final String commentTime) throws SQLException {
        String newtime = "";
        int first = time.indexOf('.');
        int second, len;
        if (first == -1) {
            first = time.indexOf('-');
            second = time.indexOf('-', first + 1);
            len = time.length();
            newtime = time.substring(0, first + 1);
            if (second - first < MN3) {
               newtime = newtime + '0';
            }
            newtime = newtime + time.substring(first + 1, second + 1);
            if (len - second < MN9) {
                newtime = newtime + '0';
            }
            newtime = newtime + time.substring(second + 1, len);
        } else {
            second = time.indexOf('.', first + 1);
            len = time.length();
            newtime = time.substring(0, first + 1);
            if (second - first < MN3) {
                newtime = newtime + '0';
            }
            newtime = newtime + time.substring(first + 1, second + 1);
            if (len - second < MN3) {
                newtime = newtime + '0';
            }
            newtime = newtime + time.substring(second + 1, len);
        }
        connect2DB();
        String sql = "select * from DATA where ID='" + id + "'";
        rs = stmt.executeQuery(sql);
        String link = "";
        String title = "";
        if (rs.next()) {
            link = rs.getString("LINK");
            title = rs.getString("TITLE");
            }
        sql = "insert into DATA(ID,USERNAME,WHEN,COMMENTTIME,CONTEXT,LINK,"
        + "TITLE,TYPE) values('" + id + "','" + userName + "','" + newtime
        + "','" + commentTime + "','" + context + "','" + link + "','"
        + title + "','" + type + "')";
        stmt.executeUpdate(sql);
        closeConnection();
        }
    /***删除评论.
     * 删除评论
     * @param commentID 评论的唯一标示
     * @throws SQLException 不处理
     */
    public final void deleteComment(final String commentID)
    throws SQLException {
        connect2DB();
        String sql = "delete * from DATA where COMMENTID='" + commentID + "'";
        rs = stmt.executeQuery(sql);
        closeConnection();
        }
    /***添加信息.
     * 添加信息
     * @param d 信息的Data
     * @return 返回是否添加成功
     * @throws SQLException 不处理
     */
    public final boolean addData(final Data d) throws SQLException {
        connect2DB();
        String sql = "select * from DATA where ID='" + d.getId() + "'";
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            if (rs.getString("COMMENTTIME").compareTo(d.getTime()) == 0
            && rs.getString("TITLE").compareTo(d.getTitle()) == 0
            && rs.getString("LINK").compareTo(d.getLink()) == 0) {
                closeConnection();
                return true;
                }
            }
        sql = "insert into DATA(ID,COMMENTTIME,LINK,TITLE,TYPE,USERNAME)"
        + " values('" + d.getId() + "','" + d.getTime() + "','" + d.getLink()
        + "','" + d.getTitle() + "','" + d.getType() + "','" + d.getUserName()
        + "')";
        stmt.executeUpdate(sql);
        closeConnection();
        return false;
        }

    /***添加内容.
     * 返回是否添加成功
     * @param title String
     * @param link String
     * @param time String
     * @param type String
     * @return boolean
     * @throws SQLException if has error
     */
    public final boolean addContent(final String title,
    final String link, final String time,
    final String type) throws SQLException {
        connect2DB();
        String sql = "select * from DATA where link='" + link + "'";
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return false;
            } else {
                sql = "insert into DATA(WHEN,LINK,TITLE,TYPE) values('"
                + time + "','" + link + "','"
                + title + "','" + type + "')";
                stmt.executeUpdate(sql);
                closeConnection();
                return true;
                }
        }

    /** 断开数据库连接.
     * @throws SQLException if has error
     */
    private synchronized void closeConnection() throws SQLException {
        conn.close();
        }

}
