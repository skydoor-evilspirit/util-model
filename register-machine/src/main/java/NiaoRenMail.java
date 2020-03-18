import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NiaoRenMail {

    public static void main(String[] args) throws Exception{
        NiaoRenMail.register();
        Thread.sleep(2000);
    }

    /**
     * 自动注册的方法
     * @throws IOException
     */
    public static void register() throws IOException {
        //拼接请求网址
        StringBuilder builder = new StringBuilder("http://app.niaoren001.com/App/Share/DoRegister?");
        int username = randomNum();
        int password = randomNum();
        builder.append("username=" + username + "&");
        builder.append("password=" + password + "&");
        builder.append("_=" + System.currentTimeMillis());
//        url已经拼接好了
        URL url = new URL(builder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置分享人cookies
        String shareCookies = "ShareUserID=MjcxMTQx";
        conn.setRequestProperty("Cookies", shareCookies);
          conn.setConnectTimeout(18000);
        conn.setReadTimeout(60000);
        conn.setRequestMethod("GET");
        conn.connect();
        //获取响应信息
        String headerName = null;
        StringBuilder realCookies = new StringBuilder(shareCookies+";");
        String cookies = "";
        if (conn.getResponseCode() == 200) {
            for (int i = 1; (headerName = conn.getHeaderFieldKey(i)) != null; i++){
                if ("Set-Cookie".equals(headerName)){
                    String cookiesFiled = conn.getHeaderField(i);
                    System.out.println("添加cookies      "+cookiesFiled);
                    cookiesFiled = cookiesFiled.substring(0, cookiesFiled.indexOf(";")+1);
                    realCookies.append(cookiesFiled);
                }
            }
            cookies = realCookies.substring(0, realCookies.lastIndexOf(";"));
            System.out.println("完整的cookies已经诞生："+cookies);
        }
        //请求附带注册账号的cookies发送到下载页面，获得赠送天数
        URL downURL = new URL("http://app.niaoren001.com/App/Share/DownLoad");
        HttpURLConnection urlConnection = (HttpURLConnection) downURL.openConnection();
        urlConnection.setRequestProperty("Cookies", cookies);
        urlConnection.setConnectTimeout(18000);
        urlConnection.setReadTimeout(60000);
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();
        if (urlConnection.getResponseCode()==200){
            System.out.println("注册成功~~~");
        }
    }

    //获得随机的9位数
    private static int randomNum() {
        double random = 0;
        while (random <= 0.09) {
            random = Math.random();
        }
        int result = (int) (random * 1000000000);
        return result;
    }


}
