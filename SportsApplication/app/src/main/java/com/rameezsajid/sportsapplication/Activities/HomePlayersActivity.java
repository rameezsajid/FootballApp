package com.rameezsajid.sportsapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rameezsajid.sportsapplication.Adapters.HomePlayersAdapter;
import com.rameezsajid.sportsapplication.Model.HomePlayersItem;
import com.rameezsajid.sportsapplication.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomePlayersActivity extends AppCompatActivity implements HomePlayersAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private HomePlayersAdapter mExampleAdapter;
    private ArrayList<HomePlayersItem> mExampleList;
    private RequestQueue mRequestQueue;
    private TextView textViewDetailsFullName, textViewDetailsGoalsScored, textViewDetailsAssists, textViewDetailsGoalsConceded;
    private TextView textViewSaves, textViewDetailsMinsPlayed, textViewDetailsShotsOnTarget, textViewDetailsThrowIns, textViewDetailsGoalKicks;
    private TextView textViewDetailsPosition, textViewDetailsShirtNumber;
    private ImageView imageView;

    private RelativeLayout relativeLayout;

    private TextView textViewTeamName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_players);

        getSupportActionBar().setTitle("Home Team Stats");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        relativeLayout = findViewById(R.id.relative_layout_1);

        mRecyclerView = findViewById(R.id.recycler_view_players_home);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();

        textViewTeamName = findViewById(R.id.text_view_team_name);

        textViewDetailsFullName = findViewById(R.id.text_view_details_full_name);

        textViewDetailsGoalsConceded = findViewById(R.id.text_view_details_goals_conceded);

        textViewDetailsGoalsScored = findViewById(R.id.text_view_details_goals_scored);
        textViewDetailsAssists = findViewById(R.id.text_view_details_goals_assist);

        textViewDetailsMinsPlayed = findViewById(R.id.text_view_details_mins_played);
        textViewDetailsShotsOnTarget = findViewById(R.id.text_view_details_shots_on_goal);

        textViewDetailsGoalKicks = findViewById(R.id.text_view_details_goal_kicks);
        textViewDetailsThrowIns = findViewById(R.id.text_view_details_throw_ins);
        textViewSaves = findViewById(R.id.text_view_details_saves);

        textViewDetailsPosition = findViewById(R.id.text_view_details_position);
        textViewDetailsShirtNumber = findViewById(R.id.text_view_details_shirt_number);

        imageView = findViewById(R.id.image_view_team);
        textViewDetailsPosition = findViewById(R.id.text_view_details_position);
        textViewDetailsShirtNumber = findViewById(R.id.text_view_details_shirt_number);

        relativeLayout.setVisibility(View.GONE);
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
                            JSONObject jsonObjectHomeTeam = (JSONObject) jsonObject.get("homeTeam");
                            JSONArray jsonArray = (JSONArray) jsonObjectHomeTeam.get("players");

                            String imageUrl = jsonObjectHomeTeam.getString("imageUrl");

                            Picasso.with(HomePlayersActivity.this).load(imageUrl).fit().centerInside().into(imageView);

                            String name = jsonObjectHomeTeam.getString("name");

                            textViewTeamName.setText(name);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject players = jsonArray.getJSONObject(i);
                                JSONObject playerStats = players.getJSONObject("playerStats");

                                String firstName = players.getString("firstName");
                                String lastName = players.getString("lastName");
                                int shirt = players.getInt("shirtNumber");
                                String position = players.getString("position");
                                Boolean captain = players.getBoolean("captain");

                                int goalsConceded = playerStats.optInt("goalsConceded");
                                int goalsScored = playerStats.optInt("goals");
                                int assists = playerStats.optInt("intentionalGoalAssists");

                                int shotsOnGoal = playerStats.optInt("shotsOnTarget");
                                int mins = playerStats.optInt("minutesPlayed");

                                int saves = playerStats.optInt("saves");
                                int throwIns = playerStats.optInt("throwIns");
                                int goalKicks = playerStats.optInt("goalKicks");

                                mExampleList.add(new HomePlayersItem(firstName, lastName, shirt, position, captain, goalsConceded, goalsScored, assists, shotsOnGoal, mins, saves, throwIns, goalKicks));
                            }

                            mExampleAdapter = new HomePlayersAdapter(HomePlayersActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);
                            mExampleAdapter.setOnItemClickListner(HomePlayersActivity.this);

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

    @Override
    public void onItemClick(int position) {
        HomePlayersItem clickedItem = mExampleList.get(position);

        String firstName = clickedItem.getFirstName();
        String lastName = clickedItem.getLastName();
        Boolean captain = clickedItem.getCaptain();
        int goalsConceded = clickedItem.getGoalsConceded();
        int goalsScored = clickedItem.getGoalsScored();
        int assists = clickedItem.getAssists();
        int mins = clickedItem.getMinsPlayed();
        int shotsOnGoal = clickedItem.getShotsOnGoal();
        int goalKicks = clickedItem.getGoalKicks();
        int throwIns = clickedItem.getThrowIns();
        int saves = clickedItem.getSaves();

        int shirtNumber = clickedItem.getShirt();
        String pos = clickedItem.getPosition();

        textViewDetailsGoalsConceded.setText("Goals Conceded " + String.valueOf(goalsConceded));
        textViewDetailsGoalsScored.setText("Goals Scored " + String.valueOf(goalsScored));
        textViewDetailsAssists.setText("Goal Assists " + String.valueOf(assists));

        textViewSaves.setText("Saves " + String.valueOf(saves));
        textViewDetailsThrowIns.setText("Throw Ins " + String.valueOf(throwIns));
        textViewDetailsGoalKicks.setText("Goal Kicks " + String.valueOf(goalKicks));

        textViewDetailsShotsOnTarget.setText("Shots On Target " + String.valueOf(shotsOnGoal));
        textViewDetailsMinsPlayed.setText("Minutes Played " + String.valueOf(mins));

        textViewDetailsShirtNumber.setText(String.valueOf(shirtNumber));
        textViewDetailsPosition.setText(pos);

        if (captain.toString().equals("true")){
            textViewDetailsFullName.setText(firstName + " " + lastName + " (c)");
        }else {
            textViewDetailsFullName.setText(firstName + " " + lastName);
        }

        relativeLayout.setVisibility(View.VISIBLE);

    }
}
