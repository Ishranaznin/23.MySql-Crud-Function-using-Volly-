package com.example.erpsystem_layout;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter_Status extends RecyclerView.Adapter< RecyclerViewAdapter_Status.StatusViewHolder> {
    private Context mCTX;
    private List<Member_Status_Pojo> satusList;
    public RecyclerViewAdapter_Status(Context mCTX, List<Member_Status_Pojo> satusList) {
        this.mCTX = mCTX;
        this.satusList = satusList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter_Status.StatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(mCTX);
        View view= inflater.inflate(R.layout.rv_items_status,null);
        return new StatusViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter_Status.StatusViewHolder holder, int position) {
        Member_Status_Pojo proStatus=satusList.get(position);
        holder.textViewStatusname.setText(proStatus.getM_statusName());
         holder.textViewMemberID.setText(proStatus.getM_MemberID());



    }

    @Override
    public int getItemCount() {
        return satusList.size();
    }
    public class StatusViewHolder extends RecyclerView.ViewHolder {
        TextView textid,textViewStatusname ,textViewMemberID;

        public StatusViewHolder(@NonNull View itemView) {
            super(itemView);
            textid=itemView.findViewById(R.id.statusItemID);
            textViewStatusname=itemView.findViewById(R.id.statusItemName);
            textViewMemberID=itemView.findViewById(R.id.statusItem_MemberID);

        }

    }
}
