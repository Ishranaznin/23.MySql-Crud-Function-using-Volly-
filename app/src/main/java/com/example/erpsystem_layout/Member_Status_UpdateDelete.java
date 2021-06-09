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
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Member_Status_UpdateDelete extends AppCompatActivity {
    EditText member_stats_id, member_status_name, member_id_ET;
    Button updatedata, deletedata;

    private static String TAG = Member_Status_UpdateDelete.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member__status__update_delete);

        member_stats_id = (EditText) findViewById(R.id.ed_statusAutoID_UD);
        member_status_name = (EditText) findViewById(R.id.ed_MemberStatusNameUD);
        member_id_ET = (EditText) findViewById(R.id.ed_memberAutoIDStatus_UD);

        updatedata = (Button) findViewById(R.id.update_button_STATUS);
        deletedata = (Button) findViewById(R.id.delete_button_STATUS);
        Intent intent = getIntent();


        String status_autoid = intent.getExtras().getString("key_id");
        String status_name = intent.getStringExtra("key_name");
       String member_id_int = intent.getExtras().getString("key_member");


        member_stats_id.setText(status_autoid);
        member_status_name.setText(status_name);
        member_id_ET.setText(member_id_int);




        updatedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editstatus();

            }
        });

        deletedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletestatus();

            }
        });

    }

    private void editstatus(){
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to update this Status?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        update_status(); //edit method call
                        startActivity(new Intent(Member_Status_UpdateDelete.this, MainActivity.class));

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


    private void update_status()
    {

        String updateDataURL = "http://10.0.2.2/erp/updateMemberStatus.php";

        StringRequest updateReq = new StringRequest(Request.Method.POST, updateDataURL,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // member_status_name.setText("");
                        Toast.makeText(getApplicationContext(), "Data UPDATE Successfully", Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject res = new JSONObject(response);
                            //Toast.makeText(InsertData.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


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

                /*member_stats_id=(TextInputEditText) findViewById(R.id.ed);
                member_status_name=(TextInputEditText)findViewById(R.id.payment_name2);
                member_id_ET=(TextInputEditText)findViewById(R.id.member_id2);

*/
                map.put("member_status_auto_id",member_stats_id.getText().toString());
                map.put("member_status_name",member_status_name.getText().toString());
                map.put("member_auto_id",member_id_ET.getText().toString());
                return map;
            }
        };

        AppSingleton1.getInstance(this).addToRequestQueue(updateReq,TAG);
    }


    private void  deletestatus(){
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this Status?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        delete_data();
                        startActivity(new Intent(Member_Status_UpdateDelete.this, MainActivity.class));
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

    public void delete_data() {
        String myurl = "http://10.0.2.2/erp/deleteMemberStatus.php";

        StringRequest StringRequest = new StringRequest(Request.Method.POST, myurl,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               member_stats_id.setText("");
              member_status_name.setText("");
               member_id_ET.setText("");

                Toast.makeText(getApplicationContext(), "Status DELETED Successfully", Toast.LENGTH_SHORT).show();

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
                map.put("member_status_auto_id",member_stats_id.getText().toString());
                return map;
            }
        };
        AppSingleton1.getInstance(this).addToRequestQueue(StringRequest,TAG);
    }
}