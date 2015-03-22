package mcs.gymapp.utility.connection;

import java.util.List;
import org.apache.http.Header;
import org.apache.http.cookie.Cookie;

public class Login {

    private String username,
            userid,
            password;
    private List<Cookie> cookies;
    private Header headers[];


    public Login(String user, String psw) {
        this.username = user;
        this.password = psw;
    }

    public Login(String user, String psw, String id, List<Cookie> c, Header[] h ) {
        this.username = user;
        this.password = psw;
        this.userid = id;
        this.cookies = c;
        this.headers = h;
    }


    public String getUser() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public List<Cookie> getCookies() {
        return this.cookies;
    }

    public void setCookies(List<Cookie> c) {
        this.cookies = c;
    }

    public Header[] getHeaders() {
        return this.headers;
    }

    public void setHeaders(Header[] h) {
        this.headers = h;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String s) {
        this.userid = s;
    }


}