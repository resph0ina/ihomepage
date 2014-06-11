package b;

import java.sql.ResultSet;
import java.sql.SQLException;

import manage.DataManager;

/**
 * @author Ljnanest
 */
public class InfoB {
    /** 获取info表项.
     *  从数据库中获取infoa表项〄1�7
     * @return 表项〄1�7
     * @throws SQLException if has error!
     */
    public final ResultSet getInfoData() throws SQLException {
        DataManager a = new DataManager();
        return a.getDataByType("Info");
    }
}
