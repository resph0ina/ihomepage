package manage;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rrapi.RrAccount;


/***
 * @author suning
 */
public class UserManager {

    /** magic number.
     * set magic number 80001
     */
    public static final int MN80001 = 80001;
    /** 鏁版嵁搴撳弬鏁��
     */
    private String sqlDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
    /** 鏁版嵁搴撳弬鏁��
     */
    private String sqlUrl = "jdbc:odbc:driver="
            + "{MicroSoft Access Driver (*.mdb)};"
            + "DBQ=d:\\suning\\infohub\\web\\database.mdb";
    /** 鏁版嵁搴撳弬鏁��
     */
    private Connection conn = null;
    /** 鏁版嵁搴撳弬鏁��
     */
    private Statement stmt = null;
    /** 鏁版嵁搴撳弬鏁��
     */
    private ResultSet rs = null;

    /** 鏋勶拷1锟�藉嚱鏁��
     */
    public UserManager() {

    }

    /** 杩炴帴鏁版嵁搴��

     */
    private synchronized void connect2DB() {
        try {
               Class.forName(sqlDriver); //锟斤拷锟斤拷锟斤拷菘锟斤拷锟斤拷锟斤拷
               this.conn = DriverManager.getConnection(sqlUrl);
               this.stmt = this.conn.createStatement();
              } catch (Exception e) {
               System.out.println(e.toString());
              }
    }

    /** 妫��嬬敤鎴峰悕.
     *  妫��ョ敤鎴峰悕鏄惁瀛樺湪,涓嶅瓨鍦ㄨ繑鍥瀟rue,瀛樺湪杩斿洖false
     * @param userName String
     * @return boolean
     * @throws SQLException if has error
     */
    public final boolean checkUsername(final String userName)
            throws SQLException {
        connect2DB();
        String sql = "select * from USER where USERNAME='" + userName + "'";
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            closeConnection();
            return false;
        }
        closeConnection();
        return true;
    }

    /** 娉ㄥ唽.
     * 娉ㄥ唽,骞惰繑鍥炵敤鎴穒d
     * @param userName String
     * @param passWord String
     * @param eMail String
     * @return 杩斿洖id
     * @throws SQLException if has error
     */
    public final synchronized String register(final String userName,
            final String passWord,  final String eMail) throws SQLException {
        connect2DB();
        int id = MN80001;
        while (true) {
            String sql = "select * from USER where UID='" + id + "'";
            rs = stmt.executeQuery(sql);
            if (!rs.next()) { break; }
            id++;
        }
        String sql = "insert into "
                + "USER(UID,USERNAME,PASSWORD,EMAIL,INFO,CST,LEARN,RENREN)"
                + " values('"
                + id + "','" + userName + "','" + passWord + "','"
                + eMail + "','yes','yes','no','no')";
        stmt.executeUpdate(sql);
        closeConnection();
        return "" + id;
    }


    /*** 妫��ョ櫥褰曟儏鍐��
     *
     * @param userName String
     * @param passWord String
     * @return 鐧婚檰鎴愬姛杩斿洖鐢ㄦ埛id,鐧婚檰澶辫触杩斿洖閿欒淇℃伅
     * @throws SQLException if has error
     */
    public final String checkLogin(final String userName,
            final String passWord) throws SQLException {
        connect2DB();
        String sql = "select * from USER where USERNAME='" + userName + "'";
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            if (rs.getString("PASSWORD").compareTo(passWord) == 0) {
                String id;
                id = rs.getString("UID");
                closeConnection();
                return id;
            }
            closeConnection();
            return "密码错误";
        }
        closeConnection();
        return "用户名不存在";
    }
    /***
     * @author YT
     * @param id String
     * @return 鐢ㄦ埛淇℃伅
     * @throws SQLException if has error
     */
    public final RrAccount getAccount(final String id) throws SQLException {
        connect2DB();
        String sql = "select * from USER where UID='" + id + "'";
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            RrAccount res = new RrAccount();
            res.setUsername(rs.getString("RENRENNA"));
            res.setPassword(rs.getString("RENRENPA"));
            return res;
        }
        return null;
    }

    /** 鑾峰彇鐢ㄦ埛淇℃伅.
     * 閫氳繃鍚嶇О鑾峰彇鐢ㄦ埛淇℃伅 鑾峰彇鐢ㄦ埛淇℃伅
     * @param name String
     * @return 鐢ㄦ埛淇℃伅
     * @throws SQLException if has error
     */
    public final RrAccount getAccountByName(final String name)
            throws SQLException {
        connect2DB();
        String sql = "select * from USER where USERNAME='" + name + "'";
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            RrAccount res = new RrAccount();
            res.setUsername(rs.getString("RENRENNA"));
            res.setPassword(rs.getString("RENRENPA"));
            return res;
        }
        return null;
    }

    /** 妫��嬭闃呬俊鎭��
     * 妫��ョ敤鎴风殑鏌愪竴淇℃伅鏄惁璁㈤槄,杩斿洖true鎴杅alse
     * @param type String
     * @param id String
     * @return boolean
     * @throws SQLException if has error
     */
    public final boolean getInfo(final String type, final String id)
            throws SQLException {
        connect2DB();
        String sql = "select * from USER where UID='" + id + "'";
        rs = stmt.executeQuery(sql);
        rs.next();
        boolean f = false;
        if (type == "INFO") {
            if (rs.getString("INFO").compareTo("yes") == 0) {
                f = true;
            } else {
                f = false;
            }
        }
        if (type == "CST") {
            if (rs.getString("CST").compareTo("yes") == 0) {
                f = true;
            } else {
                f = false;
            }
        }
        if (type == "LEARN") {
            if (rs.getString("LEARN").compareTo("yes") == 0) {
                f = true;
            } else {
                f = false;
            }
        }
        if (type == "RENREN") {
            if (rs.getString("RENREN").compareTo("yes") == 0) {
                f = true;
            } else {
                f = false;
            }
        }
        closeConnection();
        return f;
    }

    /** 淇敼淇℃伅.
     * @param id String
     * @param type String
     * @param username String
     * @param password String
     * @param choose String
     * @throws SQLException if has error
     */
    public final synchronized void modifyInfo(final String id,
            final String type, final String username, final String password,
            final String choose) throws SQLException {
        connect2DB();
        String sql = null;
        if (type == "INFO") {
            sql = "update USER set INFO='" + choose + "' where UID='"
                    + id + "'";
            stmt.executeUpdate(sql);
        }
        if (type == "CST") {
            sql = "update USER set CST='" + choose + "' where UID='"
                    + id + "'";
            stmt.executeUpdate(sql);
        }
        if (type == "LEARN") {
            sql = "update USER set LEARN='" + choose + "' where UID='"
                    + id + "'";
            stmt.executeUpdate(sql);
            sql = "update USER set LEARNNA='" + username + "' where UID='"
                    + id + "'";
            stmt.executeUpdate(sql);
            sql = "update USER set LEARNPA='" + password + "' where UID='"
                    + id + "'";
            stmt.executeUpdate(sql);
        }
        if (type == "RENREN") {
            sql = "update USER set RENREN='" + choose + "' where UID='"
                    + id + "'";
            stmt.executeUpdate(sql);
            sql = "update USER set RENRENNA='" + username + "' where UID='"
                    + id + "'";
            stmt.executeUpdate(sql);
            sql = "update USER set RENRENPA='" + password + "' where UID='"
                    + id + "'";
            stmt.executeUpdate(sql);
        }
        closeConnection();
    }

    /** 鏂紑杩炴帴.
     * @throws SQLException if has error
     */
    private synchronized void closeConnection() throws SQLException {
        conn.close();
    }


}
