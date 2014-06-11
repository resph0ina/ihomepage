package bg;

import java.sql.SQLException;
import java.util.List;

import manage.DataManager;
import infoapi.InfoAnnouncement;
import infoapi.InfoGetAnnouncement;

/** 获取info信息.
 * @author ZCH

 */
public final class InfoBG {
    /** 获取info信息.
     */
    public static void getInfo() {
        InfoGetAnnouncement infoAnnouncement =
                new InfoGetAnnouncement();
        infoAnnouncement.todo("http://oars.tsinghua.edu.cn/zzh/"
                + "30630.nsf/1fa2?ReadForm&TemplateType=2&"
                + "TargetUNID=FA65745AE14925D2C825669E002C5"
                + "ECF&AutoFramed");
        List<InfoAnnouncement> infoAnnoucements =
                infoAnnouncement.getInfoAnnouncements();
        for (InfoAnnouncement ia: infoAnnoucements) {
            System.out.println(ia.getContent()
                    + "\r\n" + ia.getTitle()
                    + "\r\n" + ia.getTime());
            DataManager a = new DataManager();
            try {
                a.addContent(ia.getTitle(),
                        ia.getContent(),
                        ia.getTime(), "Info");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    /** constructer.
     */
    private InfoBG() {

    }
}
