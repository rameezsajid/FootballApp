package com.rameezsajid.sportsapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rameezsajid.sportsapplication.Model.CommentaryItem;
import com.rameezsajid.sportsapplication.R;

import java.util.ArrayList;

public class CommentaryAdapter extends RecyclerView.Adapter<CommentaryAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<CommentaryItem> mCommentaryList;

    public CommentaryAdapter(Context context, ArrayList<CommentaryItem> exampleList) {
        mContext = context;
        mCommentaryList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.commentary_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        CommentaryItem currentItem = mCommentaryList.get(position);

        String comment = currentItem.getComment();
        String time = currentItem.getTime();

        holder.mTextViewComment.setText(comment);

        if (time.isEmpty()){
            holder.mTextViewTime.setVisibility(View.INVISIBLE);
        }else {
            holder.mTextViewTime.setText(time);
        }

    }

    @Override
    public int getItemCount() {
        return mCommentaryList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewComment;
        public TextView mTextViewTime;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextViewComment = itemView.findViewById(R.id.text_view_comment);
            mTextViewTime = itemView.findViewById(R.id.text_view_time);
        }
    }
}

