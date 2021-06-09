package com.example.erpsystem_layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter_Position extends RecyclerView.Adapter<RecyclerViewAdapter_Position. PositionViewHolder>  {
    private Context mCTXposition;
    private List<Position_Pojo>positionList;
    public RecyclerViewAdapter_Position(Context mCTXposition, List<Position_Pojo> positionList) {
        this.mCTXposition = mCTXposition;
        this.positionList = positionList;
    }

    @NonNull
    @Override
    public PositionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCTXposition);
        View view= inflater.inflate(R.layout.rv_items_position,null);
        return new RecyclerViewAdapter_Position.PositionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PositionViewHolder holder, int position) {

        Position_Pojo pro_position = positionList.get(position);
        holder.TV_positionname.setText(pro_position.getM_positionName());
        holder.TV_positionMemberID.setText(pro_position.getM_MemberIDposition());
    }
    @Override
    public int getItemCount() {
        return positionList.size();
    }

    public class PositionViewHolder extends RecyclerView.ViewHolder{
        TextView TV_positionid,TV_positionname ,TV_positionMemberID;

        public PositionViewHolder(@NonNull View itemView) {
            super(itemView);
            TV_positionid=itemView.findViewById(R.id.positionitemID);
            TV_positionname=itemView.findViewById(R.id.positionItemName);
            TV_positionMemberID=itemView.findViewById(R.id.positionItemMemberID);

        }
    }
}
