package b;

import java.sql.ResultSet;
import java.sql.SQLException;

import manage.DataManager;

/** .
 * @author Ljnanest
 */
public class JiuJingB {
    /** 获取酒井表项.
     * 从数据库中获取酒井表项�1�7�1�7
     * @return 敬酒表项
     * @throws SQLException if has error
     */
    public final ResultSet getJiuJingData() throws SQLException {
        DataManager a = new DataManager();
        return a.getDataByType("JiuJing");
    }
}
