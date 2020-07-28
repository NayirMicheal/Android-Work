package com.example.nayir.newtweets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class MainActivity extends AppCompatActivity {
    TextView textView;
    ProgressBar pBar;
    Button searchBtn;
    EditText searchEditText;
    String topic = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.json);
        pBar = (ProgressBar) findViewById(R.id.Bar);
        searchBtn = (Button) findViewById(R.id.search);
        searchEditText = (EditText) findViewById(R.id.sea_ed);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topic = searchEditText.getText().toString();
                if (!(topic.length() == 0)) {
                    pBar.setVisibility(View.VISIBLE);
                    AsyncHelper helper = new AsyncHelper(getApplicationContext(), topic);
                    helper.getData(new OnNetworkResponse() {
                        @Override
                        public void onSucess(String result) {
                            String appended = "";
//                                JSONObject obj = new JSONObject(result);
//                                JSONArray statuses;
//                                statuses = obj.getJSONArray(getString(R.string.statusarray));
//                                for (int i = 0; i < statuses.length(); i++) {
//                                    String text = statuses.getJSONObject(i).getString(getString(R.string.textkey));
//                                    appended += text + "\n\n";

                            Gson gson = new GsonBuilder().create();
                            StatusResponse status = gson.fromJson(result, StatusResponse.class);
                            for (TwitterResponse tweetres : status.tweetresponse) {
                                appended += tweetres.getTweet() + "\n\n";
                            }
                            pBar.setVisibility(View.INVISIBLE);
                            textView.setText(appended);
                        }

                        @Override
                        public void OnFailure(String fail) {
                            textView.setText("");
                            Toast.makeText(getApplicationContext(), getString(R.string.failure), Toast.LENGTH_LONG).show();
                        }
                    });

                } else {
                    textView.setText("");
                    Toast.makeText(getApplicationContext(), R.string.Enter_valid_value, Toast.LENGTH_LONG).show();
                }


            }
        });
    }


}
