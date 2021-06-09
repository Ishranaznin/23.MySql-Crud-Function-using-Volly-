package com.example.erpsystem_layout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MemberDistrictUpdateDelete extends AppCompatActivity {
EditText did_UD, dname_ud, mem_id_d_UD;
Button updtBtn,dltBTN;
    private static String TAG = MemberDistrictUpdateDelete.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_district_update_delete);

        did_UD = (EditText) findViewById(R.id.ed_districtAutoId_UD);
        dname_ud = (EditText) findViewById(R.id.ed_districtName_UD);
        mem_id_d_UD= (EditText) findViewById(R.id.ed_memberAutoID_District_UD);

        updtBtn = (Button) findViewById(R.id.updateDistrict);
        dltBTN = (Button) findViewById(R.id.deleteDistrict);
        Intent intentd2 = getIntent();


        String districtautoid  = intentd2.getExtras().getString("distric_id");

        String district_name = intentd2.getStringExtra("distric_name");
        String member_id_district = intentd2.getExtras().getString("distric_member");


        did_UD.setText(districtautoid);
        dname_ud.setText(district_name);
        mem_id_d_UD.setText(member_id_district);



        updtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editdistrict();

            }
        });

        dltBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletedistrict();

            }
        });

    }

    private void editdistrict(){
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to update this district?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        updatedistrict(); //edit method call
                        startActivity(new Intent(MemberDistrictUpdateDelete.this,MainActivity.class));

                    }
                });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
        androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    private void updatedistrict()
    {

        String updateDataURL = "http://10.0.2.2/erp/updateMemberDistrict.php";

        StringRequest updateReq = new StringRequest(Request.Method.POST, updateDataURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // player_name.setText("");
                        dname_ud.setText("");
                        Toast.makeText(getApplicationContext(), "district UPDATE Successfully", Toast.LENGTH_SHORT).show();
                        //pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            //Toast.makeText(InsertData.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //startActivity( new Intent(InsertData.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();




                map.put("district_auto_id",did_UD.getText().toString());
                map.put("district_name",dname_ud.getText().toString());
                map.put("member_auto_id",mem_id_d_UD.getText().toString());
                return map;
            }
        };

        AppSingleton1.getInstance(this).addToRequestQueue(updateReq,TAG);
    }


    private void deletedistrict(){
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this district?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        delete_district();
                        startActivity(new Intent(MemberDistrictUpdateDelete.this, MainActivity.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void delete_district() {
        String myurl = "http://10.0.2.2/erp/deleteMemberDistrict.php";

        StringRequest StringRequest = new StringRequest(Request.Method.POST, myurl,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                did_UD.setText("");
               dname_ud.setText("");
               mem_id_d_UD.setText("");

                Toast.makeText(getApplicationContext(), "Data DELETED Successfully", Toast.LENGTH_SHORT).show();

            }

        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("district_auto_id",did_UD.getText().toString());
                return map;
            }
        };
        AppSingleton1.getInstance(this).addToRequestQueue(StringRequest,TAG);
    }
}