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

public class ViewThana_RV extends AppCompatActivity {
    private static final String URL_Thana_details = "http://10.0.2.2/erp/getAllThana.php";
    List<Thana_Pojo>thanaList;


    RecyclerView recyclerViewThana;
    RecyclerViewAdapter_Thana viewAdapterThana;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_thana__rv);
        recyclerViewThana=findViewById(R.id.rv_thana_view);
       addThanadata();



        recyclerViewThana.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerViewThana, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intentt = new Intent(getApplicationContext(),Member_thana_Update_Delete.class);

                int tid=thanaList.get(position).getThana_id();
                String thanaID =String.valueOf(tid);
                String thana_name3=thanaList.get(position).getM_thanaName();

                String memid_thana=thanaList.get(position).getM_MemberIDThana();

                intentt.putExtra("key_id_thana",thanaID);
                intentt.putExtra("key_name_thana",thana_name3);
                intentt.putExtra("key_member_thana",memid_thana);
                startActivity(intentt);

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), thanaList.get(position).getThana_id() + " is clicked!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), thanaList.get(position).getM_MemberIDThana()+ " is clicked!", Toast.LENGTH_SHORT).show();

            }
        }));

    }


    public void   addThanadata(){
        thanaList=new ArrayList<>();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_Thana_details, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray array=new JSONArray(response);
                    for (int i=0; i<array.length(); i++) {
                        JSONObject studetailsall = array.getJSONObject(i);
                        thanaList.add(new Thana_Pojo(
                                studetailsall.getInt("tana_info_auto_id"),
                                studetailsall.getString("tana_name"),

                                studetailsall.getString("member_auto_id")


                        ));
                    }

                   viewAdapterThana=new RecyclerViewAdapter_Thana(getApplicationContext(),thanaList);

                    recyclerViewThana.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                   recyclerViewThana.setAdapter(viewAdapterThana);
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