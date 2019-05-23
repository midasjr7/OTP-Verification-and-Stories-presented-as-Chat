package com.midas.junior.msglegion;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.CustomView> {

    class CustomView extends RecyclerView.ViewHolder{
        TextView txt;
        TextView time;
        public CustomView(View itemView){
            super(itemView);
            txt=itemView.findViewById(R.id.textMessage);
            time=itemView.findViewById(R.id.time);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(listA.get(position).isBubble()){
            return R.layout.me_bubble;
        }
        return R.layout.bot_bubble;
    }

    List<Message> listA;
    public  MessageAdapter(List ls){
        this.listA=ls;
    }

    @Override
    public CustomView onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CustomView(LayoutInflater.from(viewGroup.getContext()).inflate(i,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(MessageAdapter.CustomView viewHolder, int i) {
      viewHolder.txt.setText(listA.get(i).msg);
      viewHolder.time.setText(listA.get(i).time);
    }

    @Override
    public int getItemCount() {
        return listA.size();
    }
}
