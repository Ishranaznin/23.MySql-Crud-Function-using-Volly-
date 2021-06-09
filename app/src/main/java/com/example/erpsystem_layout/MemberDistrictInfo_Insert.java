package com.example.erpsystem_layout;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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

import java.util.HashMap;
import java.util.Map;

public class MemberDistrictInfo_Insert extends AppCompatActivity {
    private String insertDistrictDataURL = "http://10.0.2.2/erp/addMemberDistrict.php";
    private static String TAG =MemberDistrictInfo_Insert.class.getSimpleName();
    EditText districtID, districtName, memberIDdistrict;
    Button savebtndistrict;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_district_info_insert);

        districtID=findViewById(R.id.ed_districtAutoId);
        districtName=findViewById(R.id.ed_districtName);
        memberIDdistrict=findViewById(R.id.ed_memberAutoID_District);
        savebtndistrict=findViewById(R.id.Savedistrictinfo);
        pDialog=new ProgressDialog(this);
        pDialog.setMessage("Please wait");
        pDialog.setCancelable(false);
       savebtndistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDistrictadddata();
            }
        });

    }



    private void  confirmDistrictadddata(){
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilde  = new androidx.appcompat.app.AlertDialog.Builder (this);
        alertDialogBuilde.setMessage("Are you sure you want to Add Data?");

        alertDialogBuilde.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        insertDistrictData();
                        //  startActivity(new Intent(Insert_new.this,ViewproductList_2.class));
                    }
                });

        alertDialogBuilde.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilde.create();
        alertDialog.show();
    }



//--------------------------

    private void showpDialog() {
        if (!pDialog.isShowing()) pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing()) pDialog.dismiss();
    }






    public void insertDistrictData() {
        showpDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, insertDistrictDataURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //progressBar.setVisibility(View.GONE);
                districtName.setText("");
               memberIDdistrict.setText("");

                Toast.makeText(getApplicationContext(), "Data Inserted Successfully", Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parameters = new HashMap<String, String>();

                parameters.put("district_name", districtName.getText().toString());
                parameters.put("member_auto_id",memberIDdistrict.getText().toString());



                hidepDialog();

                return parameters;
            }
        };

        AppSingleton1.getInstance(this).addToRequestQueue(stringRequest,TAG);
    }
}