package com.rameezsajid.sportsapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rameezsajid.sportsapplication.Adapters.OfficialsAdapter;
import com.rameezsajid.sportsapplication.Model.OfficialsItem;
import com.rameezsajid.sportsapplication.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private OfficialsAdapter mExampleAdapter;
    private ArrayList<OfficialsItem> mExampleList;
    private RequestQueue mRequestQueue;

    private TextView mTextViewCompetiton, mTextViewPeriod, mTextViewAttendance, mTextViewSeason, mTextViewVenueName, mTextViewVenueLocation;
    private TextView mTextViewStatsHomeGoals, mTextViewStatsAwayGoals, mTextViewStatsHomePossession, mTextViewStatsAwayPossession;
    private TextView mTextViewShotsHome, mTextViewShotsAway, mTextViewYellowCardsHome, mTextViewYellowCardsAway;
    private TextView mTextViewCornersHome, mTextViewCornersAway, mTextViewFreekicksHome, mTextViewFreeKicksAway;
    private TextView mTextViewGoalKicksHome, mTextViewGoalKicksAway, mTextViewShotsOffTargetHome, mTextViewShotsOffTargetAway;
    private TextView mTextViewPenaltiesHome, mTextViewPenaltiesAway, mTextViewThrowInsHome, mTextViewThrowInsAway, mTextViewSubsHome, mTextViewSubsAway;
    private ImageView imageViewHome, imageViewAway;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        mRequestQueue = Volley.newRequestQueue(this);

        getSupportActionBar().setTitle("Match Stats");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTextViewShotsOffTargetHome = findViewById(R.id.text_view_stats_home_shots_off_target);
        mTextViewShotsOffTargetAway = findViewById(R.id.text_view_stats_away_shots_off_target);

        mTextViewPenaltiesHome = findViewById(R.id.text_view_stats_home_penalties);
        mTextViewPenaltiesAway = findViewById(R.id.text_view_stats_away_penalties);

        mTextViewThrowInsHome = findViewById(R.id.text_view_stats_home_throwins);
        mTextViewThrowInsAway = findViewById(R.id.text_view_stats_away_throwins);

        mTextViewSubsHome = findViewById(R.id.text_view_stats_home_subs);
        mTextViewSubsAway = findViewById(R.id.text_view_stats_away_subs);

        mTextViewCompetiton = findViewById(R.id.text_view_competition);
        mTextViewAttendance = findViewById(R.id.text_view_attendance);
        mTextViewPeriod = findViewById(R.id.text_view_period);
        mTextViewSeason = findViewById(R.id.text_view_season);
        mTextViewVenueLocation = findViewById(R.id.text_view_venue_location);
        mTextViewVenueName = findViewById(R.id.text_view_venue_name);

        mTextViewStatsHomeGoals = findViewById(R.id.text_view_stats_home_goals);
        mTextViewStatsAwayGoals = findViewById(R.id.text_view_stats_away_goals);

        mTextViewStatsHomePossession = findViewById(R.id.text_view_stats_home_possession);
        mTextViewStatsAwayPossession = findViewById(R.id.text_view_stats_away_possession);

        mTextViewFreekicksHome = findViewById(R.id.text_view_stats_home_freekicks);
        mTextViewFreeKicksAway = findViewById(R.id.text_view_stats_away_freekicks);

        mTextViewYellowCardsHome = findViewById(R.id.text_view_stats_home_yellow_cards);
        mTextViewYellowCardsAway = findViewById(R.id.text_view_stats_away_yellow_cards);

        mTextViewCornersHome = findViewById(R.id.text_view_stats_home_corners);
        mTextViewCornersAway = findViewById(R.id.text_view_stats_away_corners);

        mTextViewShotsHome = findViewById(R.id.text_view_stats_home_shots);
        mTextViewShotsAway = findViewById(R.id.text_view_stats_away_shots);

        mTextViewGoalKicksHome = findViewById(R.id.text_view_stats_home_GK);
        mTextViewGoalKicksAway = findViewById(R.id.text_view_stats_away_GK);

        imageViewHome = findViewById(R.id.image_view_team_home);
        imageViewAway = findViewById(R.id.image_view_team_away);

        mRecyclerView = findViewById(R.id.recycler_view_officials);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

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
                            JSONObject jsonObjectVenue = (JSONObject) jsonObject.get("venue");
                            JSONArray jsonArray = (JSONArray) jsonObject.get("officials");

                            JSONObject jsonObjectHomeTeam = (JSONObject) jsonObject.get("homeTeam");
                            JSONObject jsonObjectHomeStats = (JSONObject) jsonObjectHomeTeam.get("teamStats");
                            JSONObject jsonObjectAwayTeam = (JSONObject) jsonObject.get("awayTeam");
                            JSONObject jsonObjectAwayStats = (JSONObject) jsonObjectAwayTeam.get("teamStats");

                            String imageUrlHome = jsonObjectHomeTeam.getString("imageUrl");

                            Picasso.with(StatsActivity.this).load(imageUrlHome).fit().centerInside().into(imageViewHome);

                            String imageUrlAway = jsonObjectAwayTeam.getString("imageUrl");

                            Picasso.with(StatsActivity.this).load(imageUrlAway).fit().centerInside().into(imageViewAway);

                            String competition = jsonObject.getString("competition");
                            String period = jsonObject.getString("period");
                            String season = jsonObject.getString("season");
                            int attendance = jsonObject.getInt("attendance");

                            String venueName = jsonObjectVenue.getString("name");
                            String venueLocation = jsonObjectVenue.getString("location");

                            int goals = jsonObjectHomeStats.getInt("goals");
                            int goalsAway = jsonObjectAwayStats.getInt("goals");

                            double posHome = jsonObjectHomeStats.getDouble("possession");
                            double posAway = jsonObjectAwayStats.getDouble("possession");

                            int shotsHome = jsonObjectHomeStats.getInt("shotsOnTarget");
                            int shotsAway = jsonObjectAwayStats.getInt("shotsOnTarget");

                            int yellowHome = jsonObjectHomeStats.getInt("teamYellowCards");
                            int yellowAway = jsonObjectAwayStats.getInt("teamYellowCards");

                            int cornersHome = jsonObjectHomeStats.getInt("cornersWon");
                            int cornersAway = jsonObjectAwayStats.getInt("cornersWon");

                            int freekicksHome = jsonObjectHomeStats.optInt("freeKicksWon");
                            int freekicksAway = jsonObjectAwayStats.optInt("freeKicksWon");

                            int goalkicksHome = jsonObjectHomeStats.optInt("goalKicks");
                            int goalkicksAway = jsonObjectAwayStats.optInt("goalKicks");

                            int shotsOffHome = jsonObjectHomeStats.optInt("shotsOffTarget");
                            int shotsOffAway = jsonObjectAwayStats.optInt("shotsOffTarget");

                            int penHome = jsonObjectHomeStats.optInt("penaltiesWon");
                            int penAway = jsonObjectAwayStats.optInt("penaltiesWon");

                            int subsHome = jsonObjectHomeStats.optInt("substitutionsMade");
                            int subsAway = jsonObjectAwayStats.optInt("substitutionsMade");

                            int throwInsHome = jsonObjectHomeStats.optInt("throwIns");
                            int throwInsAway = jsonObjectAwayStats.optInt("throwIns");

                            mTextViewThrowInsHome.setText(String.valueOf(throwInsHome));
                            mTextViewThrowInsAway.setText(String.valueOf(throwInsAway));

                            mTextViewSubsHome.setText(String.valueOf(subsHome));
                            mTextViewSubsAway.setText(String.valueOf(subsAway));

                            mTextViewShotsOffTargetHome.setText(String.valueOf(shotsOffHome));
                            mTextViewShotsOffTargetAway.setText(String.valueOf(shotsOffAway));

                            mTextViewPenaltiesHome.setText(String.valueOf(penHome));
                            mTextViewPenaltiesAway.setText(String.valueOf(penAway));

                            mTextViewCompetiton.setText(competition);
                            mTextViewPeriod.setText(period);
                            mTextViewSeason.setText(season);
                            mTextViewAttendance.setText("Attendance " + String.valueOf(attendance));

                            mTextViewVenueName.setText(venueName);
                            mTextViewVenueLocation.setText(venueLocation);

                            mTextViewStatsHomeGoals.setText(String.valueOf(goals));
                            mTextViewStatsAwayGoals.setText(String.valueOf(goalsAway));

                            mTextViewStatsHomePossession.setText(String.valueOf(posHome));
                            mTextViewStatsAwayPossession.setText(String.valueOf(posAway));

                            mTextViewShotsHome.setText(String.valueOf(shotsHome));
                            mTextViewShotsAway.setText(String.valueOf(shotsAway));

                            mTextViewYellowCardsHome.setText(String.valueOf(yellowHome));
                            mTextViewYellowCardsAway.setText(String.valueOf(yellowAway));

                            mTextViewFreekicksHome.setText(String.valueOf(freekicksHome));
                            mTextViewFreeKicksAway.setText(String.valueOf(freekicksAway));

                            mTextViewCornersHome.setText(String.valueOf(cornersHome));
                            mTextViewCornersAway.setText(String.valueOf(cornersAway));

                            mTextViewGoalKicksHome.setText(String.valueOf(goalkicksHome));
                            mTextViewGoalKicksAway.setText(String.valueOf(goalkicksAway));


                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject officials = jsonArray.getJSONObject(i);

                                String name = officials.getString("name");
                                String type = officials.getString("type");

                                mExampleList.add(new OfficialsItem(name, type));
                            }


                            mExampleAdapter = new OfficialsAdapter(StatsActivity.this, mExampleList);
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

