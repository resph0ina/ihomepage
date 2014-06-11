package b;

import java.sql.ResultSet;
import java.sql.SQLException;

import datastruct.Data;

import manage.DataManager;


/** 评论功能.
 * @author Ljnanest
 */
public class CommentCB {
    /** 获取评论年内宄1�7.
     * 传入id，获取评论内宄1�7
     * @param id String
     * @return rs
     */
    public final ResultSet getData(final String id) {
        DataManager a = new DataManager();
        ResultSet rs = null;
        try {
            rs = a.getData(id);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rs;
    }

    /** 添加评论.
     * @param x Data
     */
    public final void addComment(final Data x) {
        DataManager a = new DataManager();
        try {
            if (a.addData(x)) {
                System.out.println("no");
            } else {
                System.out.println("yes");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
