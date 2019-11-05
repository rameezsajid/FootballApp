package com.rameezsajid.sportsapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rameezsajid.sportsapplication.Model.OfficialsItem;
import com.rameezsajid.sportsapplication.R;

import java.util.ArrayList;

public class OfficialsAdapter extends RecyclerView.Adapter<OfficialsAdapter.OfficialsViewHolder> {

    private Context mContext;
    private ArrayList<OfficialsItem> mOfficialsList;

    public OfficialsAdapter(Context context, ArrayList<OfficialsItem> officialList) {
        mContext = context;
        mOfficialsList = officialList;
    }

    @Override
    public OfficialsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.officials_item, parent, false);
        return new OfficialsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OfficialsViewHolder holder, int position) {
        OfficialsItem currentItem = mOfficialsList.get(position);

        String name = currentItem.getName();
        String type = currentItem.getType();

        holder.mTextViewOfficialsName.setText(name);
        holder.mTextViewOfficialsType.setText(type);

    }

    @Override
    public int getItemCount() {
        return mOfficialsList.size();
    }

    public class OfficialsViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewOfficialsName;
        public TextView mTextViewOfficialsType;

        public OfficialsViewHolder(View itemView) {
            super(itemView);
            mTextViewOfficialsName = itemView.findViewById(R.id.text_view_officials_name);
            mTextViewOfficialsType = itemView.findViewById(R.id.text_view_officials_type);
        }
    }
}

