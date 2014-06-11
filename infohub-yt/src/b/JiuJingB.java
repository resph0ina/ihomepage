package b;

import java.sql.ResultSet;
import java.sql.SQLException;

import manage.DataManager;

/** .
 * @author Ljnanest
 */
public class JiuJingB {
    /** 鑾峰彇閰掍簳琛ㄩ」.
     * 浠庢暟鎹簱涓幏鍙栭厭浜曡〃椤广��
     * @return 鏁厭琛ㄩ」
     * @throws SQLException if has error
     */
    public final ResultSet getJiuJingData() throws SQLException {
        DataManager a = new DataManager();
        return a.getDataByType("JiuJing");
    }
}
