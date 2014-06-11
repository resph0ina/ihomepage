package b;

import java.sql.SQLException;

import manage.UserManager;

/** 鎴戜篃涓嶇煡閬撴槸鍟�.
 * @author Ljnanest
 */
public class RegCheckB {
    /** 鑾峰彇id.
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
