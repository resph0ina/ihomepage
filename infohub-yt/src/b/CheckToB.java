package b;

import java.sql.SQLException;

import manage.UserManager;


/** 验证登陆用户名和密码.
 * @author suning
 */
public class CheckToB {
    /** 获取用户标识砄1�7.
     * 传入用户名和密码，验证信息，返回用户标识砄1�7
     * @param username String
     * @param password String
     * @return ID
     * @throws SQLException if has error
     */
    public final String getID(final String username, final String password)
               throws SQLException {
        UserManager a = new UserManager();
        return a.checkLogin(username, password);
    }
}
