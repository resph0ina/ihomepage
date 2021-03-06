package b;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import rrapi.RrAccount;
import rrapi.RrComment;
import rrapi.RrFeed;
import rrapi.RrGetClient;
import rrapi.RrGetComment;
import rrapi.RrGetFeed;
import rrapi.RrSendComment;
import rrapi.RrSendShare;
import manage.UserManager;

import com.renren.api.AuthorizationException;
import com.renren.api.RennClient;
import com.renren.api.RennException;

/** 浜轰汉绫�.
 * @author Ljnanest
 */
public class RenrenB {
    /** 瀹氫箟鍙橀噺.
     * 鍙橀噺璁剧疆
     */
    private static RrFeed[] rF;
    /** 瀹氫箟鍙橀噺.
     * 鍙橀噺璁剧疆
     */
    private static RrComment[] rc;
    /** 瀹氫箟鍙橀噺.
     * 鍙橀噺璁剧疆
     */
    private static RennClient client;
    /** 瀹氫箟鍙橀噺.
     * 鍙橀噺璁剧疆
     */
    private static int num;

    /** 鑾峰彇浜轰汉鏂伴矞浜�.
     * 閫氳繃id鑾峰彇鏁版嵁搴撶殑浜轰汉璐﹀彿,瀵嗙爜.鐧诲綍浜轰汉鑾峰彇鏂伴矞浜嬪垪琛�.
     * @param id String
     * @return rF Feed List
     */
    public final RrFeed[] checkPass(final String id) {
        System.out.println("id=" + id);
        UserManager a = new UserManager();
        RrAccount ac = null;
        try {
            ac = a.getAccount(id);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("na=" + ac.getUsername()
                + " pa=" + ac.getPassword());
        client = null;
        try {
            client = RrGetClient.todo(ac);
        } catch (AuthorizationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (client == null) { return null; }
        rF = RrGetFeed.getFeed(client, 1);
        return rF;
    }

    /** 鑾峰彇璇勮琛ㄩ」.
     * 鑾峰彇绗瑇鏉¤瘎璁虹殑琛ㄩ」.鍖呮嫭璇勮鍐呭,璇勮鏃堕棿,鐢ㄦ埛鍚嶇О,鐢ㄦ埛id,璇勮id.
     * @param x int
     * @return rc 绗瑇鏉＄殑璇勮鐨勮〃椤�
     */
    public final RrComment[] getComment(final int x) {
        num = x;
        rc = null;
        try {
            rc = RrGetComment.getComment(client, 1, rF[x]);
        } catch (RennException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rc;
    }

    /** 娣诲姞鍒嗕韩.
     * @param x int
     * @param c String
     */
    public final void addShare(final int x, final String c) {
        try {
            RrSendShare.addShare(client, rF[x], c);
        } catch (RennException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /** 娣诲姞璁�.
     * @param x int
     * @param c String
     */
    public final void addComment(final int x, final String c) {
        System.out.println(c);
        try {
            String tmpc = new String(c.getBytes(), "GB18030");
            System.out.println(tmpc);
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (x == -1) {
            try {
                RrSendComment.addPureComment(client, rF[num], c);
            } catch (RennException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                RrSendComment.addComment(client, rc[x], rF[num], c);
            } catch (RennException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
