package com.example.erpsystem_layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter_Thana  extends RecyclerView.Adapter< RecyclerViewAdapter_Thana.ThanaViewHolder>{
    private Context mCTXthana;
    private List<Thana_Pojo>thanaList;
    public RecyclerViewAdapter_Thana(Context mCTXthana, List<Thana_Pojo>thanaList) {
        this.mCTXthana = mCTXthana;
        this.thanaList = thanaList;
    }




    @NonNull
    @Override
    public ThanaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCTXthana);
        View view= inflater.inflate(R.layout.rv_items_thana,null);
        return new RecyclerViewAdapter_Thana.ThanaViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ThanaViewHolder holder, int position) {
       Thana_Pojo pro=thanaList.get(position);
        holder.TV_thananame.setText(pro.getM_thanaName());
        holder.TV_ThanaMemberID.setText(pro.getM_MemberIDThana());
    }

    @Override
    public int getItemCount() {
        return thanaList.size();
    }

    public class ThanaViewHolder extends RecyclerView.ViewHolder{
        TextView TV_thanaid,TV_thananame ,TV_ThanaMemberID;

        public ThanaViewHolder(@NonNull View itemView) {
            super(itemView);
            TV_thanaid=itemView.findViewById(R.id.thanaitemID);
            TV_thananame=itemView.findViewById(R.id.ThanaItemName);
            TV_ThanaMemberID=itemView.findViewById(R.id.ThanaItemMemberID);

        }
    }

}
