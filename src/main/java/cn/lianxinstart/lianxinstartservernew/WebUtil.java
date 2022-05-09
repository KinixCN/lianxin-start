package cn.lianxinstart.lianxinstartservernew;

import java.net.HttpURLConnection;
import java.net.URL;

public class WebUtil {

    public static String getRedirectUrl(String path) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(path)
                .openConnection();
        conn.setInstanceFollowRedirects(false);
        conn.setConnectTimeout(5000);
        return conn.getHeaderField("Location");
    }
}
