package com.example.erpsystem_layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewDistrict_RV extends AppCompatActivity {
    private static final String URL_District_details = "http://10.0.2.2/erp/getAllDistrict.php";
    List<District_Pojo>districtList;
    RecyclerView recyclerViewdistrict;
    RecyclerViewAdapter_District viewAdapter_District;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_district__rv);
        recyclerViewdistrict=findViewById(R.id.rv_District_view);
        addDistrictdata();



        recyclerViewdistrict.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(),  recyclerViewdistrict, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intentd = new Intent(getApplicationContext(), MemberDistrictUpdateDelete.class);

                int did=districtList.get(position).getDistrict_id();
                String districtID =String.valueOf(did);
                String district_name2=districtList.get(position).getM_districtName();

                String memid_district=districtList.get(position).getM_MemberIDdistrict();

                intentd.putExtra("distric_id",districtID);
                intentd.putExtra("distric_name",district_name2);
                intentd.putExtra("distric_member",memid_district);
                startActivity(intentd);

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), districtList.get(position).getDistrict_id() + " is clicked!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), districtList.get(position).getM_MemberIDdistrict()+ " is clicked!", Toast.LENGTH_SHORT).show();

            }
        }));
    }


    public void  addDistrictdata(){
        districtList=new ArrayList<>();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_District_details, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray array=new JSONArray(response);
                    for (int i=0; i<array.length(); i++) {
                        JSONObject studetailsall = array.getJSONObject(i);
                        districtList.add(new District_Pojo(
                                studetailsall.getInt("district_auto_id"),
                                studetailsall.getString("district_name"),

                                studetailsall.getString("member_auto_id")


                        ));
                    }

                    viewAdapter_District=new RecyclerViewAdapter_District(getApplicationContext(),districtList);

                    recyclerViewdistrict.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerViewdistrict.setAdapter(viewAdapter_District);
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }

}