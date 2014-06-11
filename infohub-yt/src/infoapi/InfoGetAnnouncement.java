package infoapi;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import manage.DataManager;

/** InfoGetAnnouncement.
 * 获取信息
 * @author ZCH
 */
public class InfoGetAnnouncement {
    /** URL.
     * 清华网址
     */
    private String urlPrefix = "http://oars.tsinghua.edu.cn";
    /** list.
     * infoAnnouncement
     */
    private List<InfoAnnouncement> infoAnnoucements;

    /** magic number.
     * set magic number 3
     */
    private static final int MN3 = 3;

    /** 工作.
     *
     * @param infoURL String
     */
    public final void todo(final String infoURL) {
        //get the content of the info page
        String content = new String();
        try {
            URL url = new URL(infoURL);

            BufferedReader in =
                    new BufferedReader(
                        new InputStreamReader(url.openStream(), "GB18030"));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content += inputLine + "\r\n";
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //get the announcements from the info page
        infoAnnoucements = new ArrayList<InfoAnnouncement>();
        InfoAnnouncement infoAnnoucement;
        String tmpContent;
        String regex = "<a\\s+href\\s*=\\s*(\"\\S+?\"|\\S+\\s).*?>"
                + "(?:<.*?>)*(.*?)</a>.*?<TD>(.*?)</TD>";
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            infoAnnoucement = new InfoAnnouncement();
            if (matcher.group(1).charAt(0) == '\"') {
                tmpContent = "";
                for (int i = 1; matcher.group(1).charAt(i) != '\"'; i++) {
                    tmpContent += matcher.group(1).charAt(i);
                }
                if (tmpContent == null || tmpContent.equals("")) {
                    continue;
                }
            } else {
                tmpContent = matcher.group(1);
                if (tmpContent == null || tmpContent.equals("")) {
                    continue;
                }
            }

            if (!tmpContent.contains("http://")) {
                tmpContent = urlPrefix + tmpContent;
            }
            infoAnnoucement.setContent(tmpContent);

            if (matcher.group(2) == null || matcher.group(2).equals("")) {
                continue;
            }

            if (matcher.group(MN3) == null || matcher.group(MN3).equals("")) {
                continue;
            }

            infoAnnoucement.setTitle(matcher.group(2));

            infoAnnoucement.setTime(matcher.group(MN3));
            DataManager a = new DataManager();
            try {
                a.addContent(infoAnnoucement.getTitle(),
                        infoAnnoucement.getContent(),
                        infoAnnoucement.getTime(), "Info");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            infoAnnoucements.add(infoAnnoucement);
        }
    }
    /** 获取info公告.
     * @return info公告列表
     */
    public final List<InfoAnnouncement> getInfoAnnouncements() {
        // TODO Auto-generated method stub
        return infoAnnoucements;
    }
}
