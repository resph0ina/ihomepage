package b;

import java.sql.SQLException;

import manage.UserManager;

/** 我也不知道是啄1�7.
 * @author Ljnanest
 */
public class RegCheckB {
    /** 获取id.
     * @param username String
     * @param password String
     * @param email Sting
     * @return id
     * @throws SQLException if has error
     */
    public final String getID(final String username, final String password,
            final String email) throws SQLException {
        UserManager a = new UserManager();
        Boolean ok = a.checkUsername(username);
        if (ok) {
            String id = a.register(username, password, email);
            return id;
        } else {
            return null;
        }
    }
}
