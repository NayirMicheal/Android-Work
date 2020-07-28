package com.example.nayir.newtweets;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by nayir on 4/19/2017.
 */
public class AsyncHelper {
    private final String CONSUMER_KEY;
    private final String CONSUMER_SECRET;
    private final String TOKEN_URL;
    public String bearerToken;
    public String tokenType;
    public String myLog;
    Context con;
    String topic;
    String returnEntry;
    StringBuffer response;
    TwitterAuthorization Oauth;
    OnNetworkResponse onresponse;

    AsyncHelper(Context context, String top) {
        con = context;
        topic = top;
        CONSUMER_KEY = con.getString(R.string.consumer_key);
        CONSUMER_SECRET = con.getString(R.string.consumer_secret);
        TOKEN_URL = con.getString(R.string.token_url);
    }

    public void sendPostRequestToGetBearerToken() {
        URL loc;
        HttpsURLConnection conn = null;
        InputStreamReader is;
        BufferedReader in;
        try {
            loc = new URL(TOKEN_URL);
        } catch (MalformedURLException ex) {
            return;
        }
        try {
            String urlApiKey = URLEncoder.encode(CONSUMER_KEY, con.getString(R.string.utf8));
            String urlApiSecret = URLEncoder.encode(CONSUMER_SECRET, con.getString(R.string.utf8));
            String combined = urlApiKey + con.getString(R.string.semicolon) + urlApiSecret;
            byte[] data = combined.getBytes();
            String base64 = Base64.encodeToString(data, Base64.NO_WRAP);
            conn = (HttpsURLConnection) loc.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod(con.getString(R.string.postmethod));
            conn.setRequestProperty(con.getString(R.string.hostkey), con.getString(R.string.host_value));
            conn.setRequestProperty(con.getString(R.string.useragent_key), con.getString(R.string.user_agent_value));
            conn.setRequestProperty(con.getString(R.string.auth_key), con.getString(R.string.basics_key) + base64);
            conn.setRequestProperty(con.getString(R.string.contnet_type_key), con.getString(R.string.content_type_value));
            conn.setRequestProperty(con.getString(R.string.content_len_key), con.getString(R.string.content_len_value));
            conn.setUseCaches(false);
            String urlParameters = con.getString(R.string.grant_type);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            is = new InputStreamReader(conn.getInputStream(), con.getString(R.string.utf8));
            in = new BufferedReader(is);
            readResponse(in);
            setJSONresults();
        } catch (IOException ex) {
        } finally {
            conn.disconnect();
        }
    }

    public void readResponse(BufferedReader in) {
        String tmp = "";
        StringBuffer response = new StringBuffer();
        do {
            try {
                tmp = in.readLine();
            } catch (IOException ex) {
            }
            if (tmp != null) {
                response.append(tmp);
            }
        }
        while (tmp != null);
        returnEntry = response.toString();
    }

    public void setJSONresults() {
        try {
            JSONObject obj1 = new JSONObject(returnEntry);
            tokenType = obj1.getString(con.getString(R.string.token_type));
            myLog += tokenType;
            bearerToken = obj1.getString(con.getString(R.string.token_access));
            myLog += bearerToken;
        } catch (JSONException ex) {
        }
    }

    private String fetchTimelineTweet() throws IOException, ParseException {
        HttpsURLConnection connection = null;
        BufferedReader bufferedReader;
        response = new StringBuffer();
        String testUrl = con.getString(R.string.testurl) + topic;
        topic = "";
        try {
            URL url = new URL(testUrl);
            connection = (HttpsURLConnection) url.openConnection();
            JSONObject jsonObjectDocument = new JSONObject(returnEntry);
            String token = jsonObjectDocument.getString(con.getString(R.string.token_type)) + " "
                    + jsonObjectDocument.getString(con.getString(R.string.token_access));
            connection.setRequestProperty(con.getString(R.string.auth_key), token);
            connection.setRequestMethod(con.getString(R.string.get));
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty(con.getString(R.string.hostkey), con.getString(R.string.host_value));
            connection.setRequestProperty(con.getString(R.string.accept_encode), con.getString(R.string.accept_encode_value));
            connection.setRequestProperty(con.getString(R.string.contnet_type_key), con.getString(R.string.content_type_json));
            connection.connect();
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        } catch (MalformedURLException e) {
            throw new IOException(con.getString(R.string.exception), e);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return new String();
    }

    public void getData(OnNetworkResponse response) {
        onresponse = response;
        Oauth = new TwitterAuthorization();
        Oauth.execute();
    }

    public class TwitterAuthorization extends AsyncTask<String, Void, Void> {
        boolean finished;

        @Override
        protected Void doInBackground(String... params) {
            finished = false;
            if (bearerToken == null) {
                sendPostRequestToGetBearerToken();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            finished = true;
            TwitterRetrieve retrieveJson = new TwitterRetrieve();
            retrieveJson.execute();
        }
    }

    public class TwitterRetrieve extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String retured = null;
            try {
                retured = fetchTimelineTweet();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return retured;
        }

        @Override
        protected void onPostExecute(String result) {
            if (!(result.length() == 0)) {
                onresponse.onSucess(result);
            } else {
                onresponse.OnFailure(con.getString(R.string.failure));
            }

        }
    }
}
