package com.experiments.trieapp.KeyBox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.experiments.trieapp.R;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ContentViewHolder> {

    private OnRVItemClickListener onRVItemClickListener;
    private String[] keyVal;
    private Context context;
    public RVAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RVAdapter.ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view  = inflater.inflate(R.layout.rv_item,parent,false);
        int height = parent.getMeasuredHeight() / 5;
        view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
        return new RVAdapter.ContentViewHolder(view, onRVItemClickListener);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
        String title = keyVal[position];
        if(title.equals("del") || title.equals("tts") || title.equals("space") || title.equals("done") || title.equals("send")){
            holder.view.setBackground(context.getResources().getDrawable(R.drawable.roundcorner_img));
            holder.titleView.setVisibility(View.GONE);
            holder.imageView.setVisibility(View.VISIBLE);
            holder.imageView.setImageResource(context.getResources().getIdentifier("key_"+title,"drawable",context.getPackageName()));
        }else{
            holder.titleView.setText(title);
            holder.titleView.setTextSize(position<10?18:32);
            holder.titleView.setAllCaps(position>9?true:false);
            holder.imageView.setVisibility(View.GONE);
            holder.titleView.setVisibility(View.VISIBLE);
            holder.view.setBackground(position<10?context.getResources().getDrawable(R.drawable.roundcorner_sgs):context.getResources().getDrawable(R.drawable.roundcorner_btn));
        }
        holder.indexView.setText(String.valueOf(position));
    }

    public void setData(String[] keyVal){
        this.keyVal = keyVal;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return keyVal.length;
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder{
        TextView titleView;
        TextView indexView;
        ImageView imageView;
        View view;
        public ContentViewHolder(@NonNull View itemView,final OnRVItemClickListener listener) {
            super(itemView);
            view = itemView;
            titleView = itemView.findViewById(R.id.rv_tile);
            indexView = itemView.findViewById(R.id.rv_index);
            imageView = itemView.findViewById(R.id.rv_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        listener.onClickListener(getAdapterPosition());
                    }
                }
            });
        }
    }

    public void setOnRVItemClickListener(OnRVItemClickListener rvItemClickListener){
        onRVItemClickListener = rvItemClickListener;
    }
}
