package rrapi;

/** 用户.
 * @author Ljnanest
 */
public class RrAccount {
    /** 定义变量.
     * 定义用户名
     */
    private String username;
    /** 定义变量.
     * 定义密码
     */
    private String password;

    /** 设置用户名.
     * @param u String
     */
    public final void setUsername(final String u) {
        username = u;
    }

    /** 设置密码.
     * @param p String
     */
    public final void setPassword(final String p) {
        password = p;
    }

    /** 获取用户名.
     * @return username
     */
    public final String getUsername() {
        return username;
    }

    /** 获取密码.
     * @return password
     */
    public final String getPassword() {
        return password;
    }
}
