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

public class MemberPositionInfo_Insert extends AppCompatActivity {
    private String insertPositionDataURL = "http://10.0.2.2/erp/addMemberPosition.php";
    private static String TAG =MemberPositionInfo_Insert.class.getSimpleName();
    EditText positionID, positionName, memberIDposition;
    Button savebtnposition;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_position_info_insert);

        positionID=findViewById(R.id.ed_positionAutoID);
        positionName=findViewById(R.id.ed_positionName);
        memberIDposition=findViewById(R.id.ed_memberAutoID_position);
        savebtnposition=findViewById(R.id.positionSave);
        pDialog=new ProgressDialog(this);
        pDialog.setMessage("Please wait");
        pDialog.setCancelable(false);
        savebtnposition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmPositionadddata();
            }
        });

    }



    private void confirmPositionadddata(){
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilde  = new androidx.appcompat.app.AlertDialog.Builder (this);
        alertDialogBuilde.setMessage("Are you sure you want to Add Data?");

        alertDialogBuilde.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        insertPositionData();
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






    public void insertPositionData() {
        showpDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, insertPositionDataURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //progressBar.setVisibility(View.GONE);
                positionName.setText("");
                memberIDposition.setText("");

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

                parameters.put("member_status_name", positionName.getText().toString());
                parameters.put("member_auto_id", memberIDposition.getText().toString());


                hidepDialog();


                return parameters;
            }
        };

        AppSingleton1.getInstance(this).addToRequestQueue(stringRequest,TAG);
    }
}