package com.rameezsajid.sportsapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rameezsajid.sportsapplication.Model.HomePlayersItem;
import com.rameezsajid.sportsapplication.R;

import java.util.ArrayList;

public class HomePlayersAdapter extends RecyclerView.Adapter<HomePlayersAdapter.PlayersViewHolder> {

    private Context mContext;
    private ArrayList<HomePlayersItem> mCommentaryList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListner(OnItemClickListener listner){
        mListener = listner;
    }

    public HomePlayersAdapter(Context context, ArrayList<HomePlayersItem> exampleList) {
        mContext = context;
        mCommentaryList = exampleList;
    }

    @Override
    public PlayersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.players_item, parent, false);
        return new PlayersViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlayersViewHolder holder, int position) {
        HomePlayersItem currentItem = mCommentaryList.get(position);

        String firstName = currentItem.getFirstName();
        String lastName = currentItem.getLastName();

        holder.mTextViewFirstName.setText(firstName + " " + lastName);
    }



    @Override
    public int getItemCount() {
        return mCommentaryList.size();
    }

    public class PlayersViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewFirstName;

        public PlayersViewHolder(View itemView) {
            super(itemView);
            mTextViewFirstName = itemView.findViewById(R.id.text_view_players_full_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

}
