package com.example.erpsystem_layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter_Country extends RecyclerView.Adapter<RecyclerViewAdapter_Country.CountryViewHolder> {

    private Context mCTXcountry;
    private List<Country_Pojo> countryList;
    public RecyclerViewAdapter_Country(Context mCTXcountry, List<Country_Pojo> countryList) {
        this.mCTXcountry = mCTXcountry;
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCTXcountry);
        View view= inflater.inflate(R.layout.rv_items_country,null);
        return new RecyclerViewAdapter_Country.CountryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {

        Country_Pojo pro_country=countryList.get(position);
        holder.TV_countryname.setText( pro_country.getM_countryName());
        holder.TV_countryMemberID.setText( pro_country.getM_MemberIDcountry());
    }

    @Override
    public int getItemCount() {
        return  countryList.size();
    }


    public class CountryViewHolder extends RecyclerView.ViewHolder{
        TextView TV_countryid,TV_countryname ,TV_countryMemberID;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            TV_countryid=itemView.findViewById(R.id.countryitemID);
            TV_countryname=itemView.findViewById(R.id.countryItemName);
            TV_countryMemberID=itemView.findViewById(R.id.countryItemMemberID);
           itemView.setTag(itemView);
// itemView.setOnClickListener(this);
        }
    }

}
