package mcs.gymapp.utility.connection;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

import com.loopj.android.http.*;

import mcs.gymapp.Main;
import mcs.gymapp.dialog.LoginDialog;
import mcs.gymapp.fragment.FragNews;


public class Connect extends JsonHttpResponseHandler {

    public static final String SERVER_URL = "http://nightlife.macosoft.altervista.org/";
    private static final Exception InvalidLogin = null;

    private URL url = null;
    private URLConnection conn = null;
    private Header cookie = null;
    private HttpPost httppost = null;
    private HttpResponse response = null;
    private DefaultHttpClient httpclient= null;
    private Context _context;
    private Fragment frag;

    private Login login;
    private AsyncHttpClient client = new AsyncHttpClient();

    /**
    static public boolean isConnectingToInternet(Context c) {
        ConnectivityManager connectivity = (ConnectivityManager) c
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }*/

/*
        JSONObject stato = null;
        String auth = null;
        String userid = null;
        try {
            stato = new JSONObject(testo);
            userid = stato.getString("userid");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        login.setUserid(userid);

        if (userid.equals("-1")) {   //<--controllare se il componente ha funzionato
            // login non valido
            throw new InvalidLogin();
        } else {
            List<Cookie> c = httpclient.getCookieStore().getCookies();
            login.setCookies(c);

            headers = null;
            headers = response.getAllHeaders();
            login.setHeaders(headers);
            httpclient.getConnectionManager().shutdown();
        }
    }

**************************************************/

    public Connect(Fragment f) {
        frag = f;

        AsyncHttpClient client = new AsyncHttpClient();
   //     PersistentCookieStore myCookieStore = new PersistentCookieStore(f.getActivity().getBaseContext());
    //    client.setCookieStore(myCookieStore);

        client.post(SERVER_URL +
                    "nightlife/index.php/component/news&format=json",
                    this);
    }

    public Connect(String user, String pswd,Fragment f) {
        login = new Login(user, pswd);
        frag = f;
        PersistentCookieStore myCookieStore = new PersistentCookieStore(f.getActivity());
        AsyncHttpClient client = new AsyncHttpClient();
        client.setCookieStore(myCookieStore);

        RequestParams params = new RequestParams();
        params.put("username", user);
        params.put("password", pswd);
        client.post(SERVER_URL +
                "nightlife/index.php/component/mchatta?task=login.auth&format=json",
                params, this);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            // If the response is JSONObject instead of expected JSONArray
        //login.setUserid(userid);

        if (frag instanceof LoginDialog){
            ((LoginDialog) frag).loginStatus(false);
        }else if(frag instanceof FragNews){
            ((FragNews) frag).connectData();
        }

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        if (frag instanceof LoginDialog){
            ((LoginDialog) frag).loginStatus(false);
        }else if(frag instanceof FragNews){
            ((FragNews) frag).connectError();
        }
    }


}
