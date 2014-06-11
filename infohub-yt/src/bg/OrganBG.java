package bg;

import java.sql.SQLException;

import manage.DataManager;
import rrapi.RrAccount;
import rrapi.RrFeed;
import rrapi.RrGetClient;
import rrapi.RrGetFeed;
import rrapi.RrSendShare;

import com.renren.api.AuthorizationException;
import com.renren.api.RennClient;
import com.renren.api.RennException;

/** 获取系内组织公告信息.
 * @author ZCH
 */
public final class OrganBG {
    /** 获取系内组织.
     * @throws RennException if has error
     */
    public static void getOrgan()  throws RennException {
        RrAccount rA = new RrAccount();
        rA.setUsername("thunull12@163.com");
        rA.setPassword("121314");
        RennClient client = null;
        try {
            client = RrGetClient.todo(rA);
        } catch (AuthorizationException e) {
            client = null;
        }
        if (client != null) {
            RrFeed []rF = RrGetFeed.getFeed(client, 1);
            for (int i = 0; i < rF.length; i++) {
                if (rF[i].getSource().equals("�ƾ�����")) {
                   System.out.println(rF[i].getId() + "@" + rF[i].getSource()
                          + "@" + rF[i].getType() + "@" + rF[i].getUid()
                          + "@" + rF[i].getContent() + "@" + rF[i].getTime());
                   DataManager a = new DataManager();
                    try {
                        a.addContent(rF[i].getSource(), rF[i].getContent()
                        , rF[i].getTime(), "Jiujing");
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            for (int i = 0; i < rF.length; i++) {
                if (rF[i].getSource().equals("�ƾ�����")) {
                    RrSendShare.addShare(client, rF[i], "");
                }
            }
        }
    }
    /** constructer.
     */
    private OrganBG() {

    }
}
