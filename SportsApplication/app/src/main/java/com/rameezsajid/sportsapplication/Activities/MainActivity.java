package com.rameezsajid.sportsapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rameezsajid.sportsapplication.Adapters.CommentaryAdapter;
import com.rameezsajid.sportsapplication.Model.CommentaryItem;
import com.rameezsajid.sportsapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CommentaryAdapter mExampleAdapter;
    private ArrayList<CommentaryItem> mExampleList;
    private RequestQueue mRequestQueue;

    private TextView mTextViewScoreHome, mTextViewScoreAway, mTextViewHomeTeam, mTextViewAwayTeam, mTextViewComp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Commentary");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTextViewScoreHome = findViewById(R.id.text_view_home_score);
        mTextViewScoreAway = findViewById(R.id.text_view_away_score);

        mTextViewHomeTeam = findViewById(R.id.text_view_home_team);
        mTextViewAwayTeam = findViewById(R.id.text_view_away_team);

        mTextViewComp = findViewById(R.id.text_view_competition);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(this, StatsActivity.class));
                return true;
            case R.id.subitem1:
                startActivity(new Intent(this, HomePlayersActivity.class));
                return true;
            case R.id.subitem2:
                startActivity(new Intent(this, AwayPlayersActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void parseJSON() {

        //Empty URL String
        //No API included for this demo
        String url = "";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject jsonObject = response.getJSONObject("data");
                            JSONArray jsonArray = (JSONArray) jsonObject.get("commentaryEntries");

                            String homeTeam = jsonObject.getString("homeTeamName");
                            String awayTeam = jsonObject.getString("awayTeamName");
                            int homeScore = jsonObject.getInt("homeScore");
                            int awayScore = jsonObject.getInt("awayScore");
                            String comp = jsonObject.getString("competition");

                            mTextViewScoreHome.setText(String.valueOf(homeScore));
                            mTextViewScoreAway.setText(String.valueOf(awayScore));

                            mTextViewHomeTeam.setText(homeTeam);
                            mTextViewAwayTeam.setText(awayTeam);

                            mTextViewComp.setText(comp);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject commentary = jsonArray.getJSONObject(i);

                                String comment = commentary.getString("comment");
                                String time = commentary.optString("time");
                                mExampleList.add(new CommentaryItem(comment, time));

                            }

                            mExampleAdapter = new CommentaryAdapter(MainActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }
}