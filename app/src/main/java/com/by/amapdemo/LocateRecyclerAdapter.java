package com.by.amapdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 木子饼干 on 2016/11/9.
 */

public class LocateRecyclerAdapter extends RecyclerView.Adapter<LocateRecyclerAdapter.LocateViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<LocationInfo> mList;
    private OnLocationItemClick mLocationItemClick;
    private RecyclerView mRecyclerView;


    public LocateRecyclerAdapter(Context context, List<LocationInfo> list) {
        mContext = context;
        mList = list;
    }

    public void setLocationItemClick(OnLocationItemClick locationItemClick) {
        mLocationItemClick = locationItemClick;
    }

    @Override
    public LocateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.locate_info_item,parent,false);
        view.setOnClickListener(this);
        return new LocateViewHolder(view);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;

    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView = null;
    }

    @Override
    public void onBindViewHolder(LocateViewHolder holder, int position) {
        holder.mTextView.setText(mList.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View view) {
        int position = mRecyclerView.getChildAdapterPosition(view);
        mLocationItemClick.OnLocationClick(mRecyclerView,view,position,mList.get(position));
    }

    public static class LocateViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.locate_info_adress)
        TextView mTextView;
        public LocateViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnLocationItemClick{
        void OnLocationClick(RecyclerView parent, View view, int position, LocationInfo locationInfo);
    }
}
