package b;

import java.sql.SQLException;

import rrapi.RrAccount;

import manage.UserManager;


/** 订阅籄1�7.
 * @author Ljnanest
 */

public class SubscribeToB {
    /** magic number.
     * set magic number 3
     */
    public static final int MN3 = 3;
    /** magic number.
     * set magic number 4
     */
    public static final int MN4 = 4;
    /** 用户的订阅情冄1�7.
     * 确定用户之前订阅了哪些内宄1�7,如果用户订阅了第x秄1�7,返回true,否则返回false.
     * @param x int
     * @param id String
     * @return boolean
     * @throws SQLException if has error
     */
    public final boolean isOrder(final int x, final String id)
            throws SQLException {
        UserManager a = new UserManager();
        String s = null;
        if (x == 1) { s = "INFO"; }
        if (x == 2) { s = "CST"; }
        if (x == MN3) { s = "LEARN"; }
        if (x == MN4) { s = "RENREN"; }
        return a.getInfo(s, id);
    }

    /** 设置订阅.
     * 将用户填写的订阅情况传�1�7�给数据库部刄1�7,棄1�7查填写的用户名和 密码是否有效.
     * 如果有效修改数据库中的数捄1�7,返回true,否则返回false.
     * @param x int
     * @param id String
     * @param username String
     * @param password String
     * @param choose String
     * @throws SQLException if has error
     */
    public final void setOrder(final int x, final String id,
            final String username, final String password, final String choose)
            throws SQLException {
        UserManager a = new UserManager();
        if (x == 1) { a.modifyInfo(id, "INFO", username, password, choose); }
        if (x == 2) { a.modifyInfo(id, "CST", username, password, choose); }
        if (x == MN3) { a.modifyInfo(id, "LEARN", username, password, choose); }
        if (x == MN4) {
            a.modifyInfo(id, "RENREN", username, password, choose);
        }
    }
    /** 设置订阅.
     * 获取人人账号
     * @param id String
     * @return 返回用户信息
     * @throws SQLException if has error
     */
    public final RrAccount getRrAccount(final String id) throws SQLException {
        UserManager a = new UserManager();
        return a.getAccount(id);
    }
}
