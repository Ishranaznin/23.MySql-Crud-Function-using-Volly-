package com.example.erpsystem_layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter_District extends RecyclerView.Adapter<RecyclerViewAdapter_District.DistrictViewHolder> {

    public RecyclerViewAdapter_District(Context mCTXDistrict, List<District_Pojo> districtList) {
        this.mCTXDistrict = mCTXDistrict;
        this.districtList = districtList;
    }

    private Context mCTXDistrict;
    private List<District_Pojo> districtList;

    @NonNull
    @Override
    public RecyclerViewAdapter_District.DistrictViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCTXDistrict);
        View view= inflater.inflate(R.layout.rv_items_district,null);
        return new RecyclerViewAdapter_District.DistrictViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter_District.DistrictViewHolder holder, int position) {

        District_Pojo pro_District=districtList.get(position);
        holder.TV_Districtname.setText( pro_District.getM_districtName());
        holder.TV_DistrictMemberID.setText( pro_District.getM_MemberIDdistrict());
    }

    @Override
    public int getItemCount() {
        return  districtList.size();
    }


    public class DistrictViewHolder extends RecyclerView.ViewHolder{
        TextView TV_Districtid,TV_Districtname ,TV_DistrictMemberID;

        public DistrictViewHolder(@NonNull View itemView) {
            super(itemView);
            TV_Districtid=itemView.findViewById(R.id.districtitemID);
            TV_Districtname=itemView.findViewById(R.id.districtItemName);
            TV_DistrictMemberID=itemView.findViewById(R.id.districtItemMemberID);

        }
    }

}

