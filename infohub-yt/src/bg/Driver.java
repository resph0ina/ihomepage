package bg;

import com.renren.api.RennException;

/** 定时刷新信息.
 * @author ZCH
 */
public final class Driver {
    /** magic number.
     * set magic number 180000
     */
    private static final int MN = 180000;

    /** 调用dirive()函数.
     * @param args String[]
     */
    public static void main(final String[] args) {
        drive();
    }

    /** Timer.
     */
    private static void drive() {
        long interval = MN;
        while (true) {
            InfoBG.getInfo();
            try {
                OrganBG.getOrgan();
            } catch (RennException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("==============================");
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /** constructer.
     */
    private Driver() {

    }
}
