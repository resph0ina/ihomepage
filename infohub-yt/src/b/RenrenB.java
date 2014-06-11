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

/** 人人籄1�7.
 * @author Ljnanest
 */
public class RenrenB {
    /** 定义变量.
     * 变量设置
     */
    private static RrFeed[] rF;
    /** 定义变量.
     * 变量设置
     */
    private static RrComment[] rc;
    /** 定义变量.
     * 变量设置
     */
    private static RennClient client;
    /** 定义变量.
     * 变量设置
     */
    private static int num;

    /** 获取人人新鲜亄1�7.
     * 通过id获取数据库的人人账号,密码.登录人人获取新鲜事列衄1�7.
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

    /** 获取评论表项.
     * 获取第x条评论的表项.包括评论内容,评论时间,用户名称,用户id,评论id.
     * @param x int
     * @return rc 第x条的评论的表顄1�7
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

    /** 添加分享.
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

    /** 添加讄1�7.
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
