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

public class ViewCountry_RV extends AppCompatActivity {
    private static final String URL_Countr_details = "http://10.0.2.2/erp/getAllCountry.php";
    List<Country_Pojo> countryList;


    RecyclerView recyclerViewCountry;
    RecyclerViewAdapter_Country viewAdapterCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_country__rv);
        recyclerViewCountry=findViewById(R.id.rv_Country_view);
        addCountrydata();

        recyclerViewCountry.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerViewCountry, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent country = new Intent(getApplicationContext(), MemberCountryUpdateDelete.class);

                int cid=countryList.get(position).getCountry_id();
                String countryID3 =String.valueOf(cid);
                String country_name3=countryList.get(position).getM_countryName();

                String memid_country2=countryList.get(position).getM_MemberIDcountry();

               country.putExtra("key_id_country",countryID3);
               country.putExtra("key_name_country",country_name3);
                country.putExtra("key_member_country",memid_country2);
                startActivity(country);

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), countryList.get(position).getCountry_id() + " is clicked!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), countryList.get(position).getM_MemberIDcountry()+ " is clicked!", Toast.LENGTH_SHORT).show();

            }
        }));

    }


    public void  addCountrydata(){
       countryList=new ArrayList<>();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_Countr_details, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray array=new JSONArray(response);
                    for (int i=0; i<array.length(); i++) {
                        JSONObject studetailsall = array.getJSONObject(i);
                        countryList.add(new Country_Pojo(
                                studetailsall.getInt("country_info_auto_id"),
                                studetailsall.getString("country_name"),

                                studetailsall.getString("member_auto_id")


                        ));
                    }

                   viewAdapterCountry=new RecyclerViewAdapter_Country(getApplicationContext(),countryList);

                    recyclerViewCountry.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerViewCountry.setAdapter(viewAdapterCountry);
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