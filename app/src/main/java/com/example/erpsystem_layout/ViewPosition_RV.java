package com.example.erpsystem_layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

public class ViewPosition_RV extends AppCompatActivity {
    private static final String URL_Position_details = "http://10.0.2.2/erp/getAllPosition.php";
    List<Position_Pojo> positionList;


    RecyclerView recyclerViewPosition;
    RecyclerViewAdapter_Position viewAdapterPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_position_rv);
        recyclerViewPosition=findViewById(R.id.rv_Position_view);
        addPositiondata();


       recyclerViewPosition.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerViewPosition, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                //  Toast.makeText(getApplicationContext(), studentdetailsList.get(position).getSdid() + " is clicked!", Toast.LENGTH_SHORT).show();
                //  Toast.makeText(getApplicationContext(), studentdetailsList.get(position).getSid() + " is clicked!", Toast.LENGTH_SHORT).show();

                Intent intentp = new Intent(getApplicationContext(), MemberPositionUpdateDelete.class);

                int pid=positionList.get(position).getPosition_id();
                String positionID =String.valueOf(pid);
               String mmid=positionList.get(position).getM_MemberIDposition();
                String pname=positionList.get(position).getM_positionName();



                intentp.putExtra("key_position_id",positionID);
                intentp.putExtra("key_position_name",pname);
                intentp.putExtra("key_member_id",mmid);

                startActivity(intentp);

            }

            @Override
            public void onLongClick(View view, int position) {
              /*  Toast.makeText(getApplicationContext(), accountList.get(position).getAcountID() + " is clicked!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), accountList.get(position).getMem_id() + " is clicked!", Toast.LENGTH_SHORT).show();
*/
            }
        }));



    }


    public void  addPositiondata(){
        positionList=new ArrayList<>();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_Position_details, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray array=new JSONArray(response);
                    for (int i=0; i<array.length(); i++) {
                        JSONObject studetailsall = array.getJSONObject(i);
                        positionList.add(new Position_Pojo(
                                studetailsall.getInt("member_position_auto_id"),
                                studetailsall.getString("member_position_name"),

                                studetailsall.getString("member_auto_id")


                        ));
                    }

                    viewAdapterPosition=new RecyclerViewAdapter_Position(getApplicationContext(),positionList);

                    recyclerViewPosition.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerViewPosition.setAdapter(viewAdapterPosition);
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