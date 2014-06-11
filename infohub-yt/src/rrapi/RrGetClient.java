package rrapi;

import com.renren.api.AuthorizationException;
import com.renren.api.RennClient;

/***
 * @author YT
 */
public class RrGetClient {
    /***
     * @param account RrAccount
     * @return client
     * @throws AuthorizationException if has error
     */
    public static RennClient todo(final RrAccount account)
            throws AuthorizationException {
        String appKey = "72ef22c884454a40b9a3f75f81c32ace";
        String secKey = "9deb066b2c25495fbe25c1f5f7632a68";
        String callUrl = "http://www.renren.com/callback";
        RennClient client = new RennClient(appKey, secKey);
        try {
            client.authorizeWithResourceOwnerPassword(account.getUsername(),
                    account.getPassword());
            return client;
        } catch (AuthorizationException e) {
            // TODO Auto-generated catch block
            throw e;
            //e.printStackTrace();
        }
    }
}
