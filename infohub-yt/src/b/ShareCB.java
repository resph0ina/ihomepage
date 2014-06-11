package b;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.renren.api.AuthorizationException;
import com.renren.api.RennClient;
import com.renren.api.RennException;

import rrapi.RrAccount;
import rrapi.RrGetClient;
import rrapi.RrSendShare;
import manage.DataManager;
import manage.UserManager;

/** 分享功能.
 * @author Ljnanest
 */
public class ShareCB {
    /** 添加分享.
     * @param c String
     * @param na String
     * @param name String
     * @return boolean
     */
    public final boolean addShare(final String c, final String na,
            final String name) {
        UserManager ua = new UserManager();
        System.out.println("name=" + name + ",na=" + na);
        RrAccount ac = null;
        try {
            ac = ua.getAccountByName(name);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(ac.getUsername() + "#" + ac.getPassword());
        DataManager a = new DataManager();
        ResultSet rs = null;
        try {
            rs = a.getComData(na);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String s = null;
        try {
            if (rs.next()) {
                s = rs.getString("LINK");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (s == null) { return false; }
        RennClient client = null;
        try {
            client = RrGetClient.todo(ac);
        } catch (AuthorizationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (client == null) { return false; }
        try {
            System.out.println(c + "$" + s);
            RrSendShare.addUrl(client, c, s);
            return true;
        } catch (RennException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
