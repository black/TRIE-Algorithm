package com.experiments.trieapp.KeyBox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.experiments.trieapp.R;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ContentViewHolder> {

    private OnRVItemClickListener onRVItemClickListener;
    private List<String> objectList;
    private Context context;
    public RVAdapter(List<String> objectList, Context context) {
        this.objectList = objectList;
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

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
        String title = objectList.get(position);
        if(objectList.get(position).equals("del") || objectList.get(position).equals("tts") || objectList.get(position).equals("space") || objectList.get(position).equals("done")){
            holder.titleView.setVisibility(View.GONE);
            holder.imageView.setImageResource(context.getResources().getIdentifier("key_"+objectList.get(position),"drawable",context.getPackageName()));
        }else{
            holder.titleView.setText(title);
            holder.titleView.setTextSize(position<10?14:32);
            holder.imageView.setVisibility(View.GONE);
        }
        //holder.indexView.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return objectList.size();
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
