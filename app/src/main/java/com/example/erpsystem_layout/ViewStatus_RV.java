package com.example.erpsystem_layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class ViewStatus_RV extends AppCompatActivity {
    private static final String URL_Status_details = "http://10.0.2.2/erp/getAllStatus.php";
    //a list to store all the productt
    List<Member_Status_Pojo>statusList;


    //the recyclerview
    RecyclerView recyclerViewstatus;
    RecyclerViewAdapter_Status viewAdapterstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_status_recyler_view);

        recyclerViewstatus=findViewById(R.id.rv_Status_view);
        Statusdata();


        recyclerViewstatus.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerViewstatus, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(getApplicationContext(), Member_Status_UpdateDelete.class);

                int sid=statusList.get(position).getM_id();
                String statusID =String.valueOf(sid);
                String status_name=statusList.get(position).getM_statusName();

                String memid_status=statusList.get(position).getM_MemberID();

                intent.putExtra("key_id",statusID);
                intent.putExtra("key_name",status_name);
                intent.putExtra("key_member",memid_status);
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), statusList.get(position).getM_id() + " is clicked!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), statusList.get(position).getM_MemberID()+ " is clicked!", Toast.LENGTH_SHORT).show();

            }
        }));

    }
    public void  Statusdata(){
        statusList=new ArrayList<>();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_Status_details, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray array=new JSONArray(response);
                    for (int i=0; i<array.length(); i++) {
                        JSONObject studetailsall = array.getJSONObject(i);
                      statusList.add(new Member_Status_Pojo(
                                studetailsall.getInt("member_status_auto_id"),
                                studetailsall.getString("member_status_name"),

                                studetailsall.getString("member_auto_id")


                        ));
                    }

                    viewAdapterstatus = new RecyclerViewAdapter_Status(getApplicationContext(), statusList);

                    recyclerViewstatus.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerViewstatus.setAdapter(viewAdapterstatus);
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