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
    /** 数据库参敄1�7�1�7
     */
    private String sqlDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
    /** 数据库参敄1�7�1�7
     */
    private String sqlUrl = "jdbc:odbc:driver="
            + "{MicroSoft Access Driver (*.mdb)};"
            + "DBQ=d:\\suning\\infohub\\web\\database.mdb";
    /** 数据库参敄1�7�1�7
     */
    private Connection conn = null;
    /** 数据库参敄1�7�1�7
     */
    private Statement stmt = null;
    /** 数据库参敄1�7�1�7
     */
    private ResultSet rs = null;

    /** 构�1ￄ1�7�函敄1�7�1�7
     */
    public UserManager() {

    }

    /** 连接数据庄1�7�1�7

     */
    private synchronized void connect2DB() {
        try {
               Class.forName(sqlDriver); //������ݿ������
               this.conn = DriverManager.getConnection(sqlUrl);
               this.stmt = this.conn.createStatement();
              } catch (Exception e) {
               System.out.println(e.toString());
              }
    }

    /** 棄1�7�1�7�用户名.
     *  棄1�7�1�7�用户名是否存在,不存在返回true,存在返回false
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

    /** 注册.
     * 注册,并返回用户id
     * @param userName String
     * @param passWord String
     * @param eMail String
     * @return 返回id
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


    /*** 棄1�7�1�7�登录情冄1�7�1�7
     *
     * @param userName String
     * @param passWord String
     * @return 登陆成功返回用户id,登陆失败返回错误信息
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
            return "�������";
        }
        closeConnection();
        return "�û���������";
    }
    /***
     * @author YT
     * @param id String
     * @return 用户信息
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

    /** 获取用户信息.
     * 通过名称获取用户信息 获取用户信息
     * @param name String
     * @return 用户信息
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

    /** 棄1�7�1�7�订阅信恄1�7�1�7
     * 棄1�7�1�7�用户的某一信息是否订阅,返回true或false
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

    /** 修改信息.
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

    /** 断开连接.
     * @throws SQLException if has error
     */
    private synchronized void closeConnection() throws SQLException {
        conn.close();
    }


}
