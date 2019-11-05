package com.rameezsajid.sportsapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rameezsajid.sportsapplication.Model.AwayPlayersItem;
import com.rameezsajid.sportsapplication.R;

import java.util.ArrayList;

public class AwayPlayersAdapter extends RecyclerView.Adapter<AwayPlayersAdapter.AwayPlayersViewHolder> {

    private Context mContext;
    private ArrayList<AwayPlayersItem> mCommentaryList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListner(OnItemClickListener listner){
        mListener = listner;
    }

    public AwayPlayersAdapter(Context context, ArrayList<AwayPlayersItem> exampleList) {
        mContext = context;
        mCommentaryList = exampleList;
    }

    @Override
    public AwayPlayersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.players_item, parent, false);
        return new AwayPlayersViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AwayPlayersViewHolder holder, int position) {
        AwayPlayersItem currentItem = mCommentaryList.get(position);

        String firstName = currentItem.getFirstName();
        String lastName = currentItem.getLastName();

        holder.mTextViewFirstName.setText(firstName + " " + lastName);
    }



    @Override
    public int getItemCount() {
        return mCommentaryList.size();
    }

    public class AwayPlayersViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewFirstName;

        public AwayPlayersViewHolder(View itemView) {
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

