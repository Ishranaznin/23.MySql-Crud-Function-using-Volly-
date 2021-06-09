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

public class MemberPositionUpdateDelete extends AppCompatActivity {
EditText pid,pname2,pmemberid2;
Button update,delete;
    private static String TAG = MemberPositionUpdateDelete.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_position_update_delete);
        pid=findViewById(R.id.ed_positionAutoID_UD);
        pname2=findViewById(R.id.ed_positionName_UD);
        pmemberid2=findViewById(R.id.ed_memberAutoID_position_UD);
        update=findViewById(R.id.updatePosition);
        delete=findViewById(R.id.deletePosition);

        Intent intentp2 = getIntent();


        String  position_autoid = intentp2.getExtras().getString("key_position_id");

        String position_name = intentp2.getStringExtra("key_position_name");
        String member_id_position = intentp2.getExtras().getString("key_member_id");


        pid.setText(position_autoid);
        pname2.setText(position_name);
        pmemberid2.setText(member_id_position);



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editposition();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteposition();

            }
        });

    }

    private void editposition(){
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to update this position?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        update_position(); //edit method call
                        startActivity(new Intent(MemberPositionUpdateDelete.this, MainActivity.class));

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


    private void update_position()
    {

        String updateDataURL = "http://10.0.2.2/erp/updatememberPosition.php";

        StringRequest updateReq = new StringRequest(Request.Method.POST, updateDataURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        pname2.setText("");
                        Toast.makeText(getApplicationContext(), "position UPDATE Successfully", Toast.LENGTH_SHORT).show();

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



                map.put("member_position_auto_id",pid.getText().toString());
                map.put("member_position_name",pname2.getText().toString());
                map.put("member_auto_id",pmemberid2.getText().toString());
                return map;
            }
        };

        AppSingleton1.getInstance(this).addToRequestQueue(updateReq,TAG);
    }


    private void deleteposition(){
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this position?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        delete_position();
                        startActivity(new Intent(MemberPositionUpdateDelete.this, MainActivity.class));
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

    public void delete_position() {
        String myurl = "http://10.0.2.2/erp/deleteMemberPosition.php";

        StringRequest StringRequest = new StringRequest(Request.Method.POST, myurl,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                pid.setText("");
                pname2.setText("");
                pmemberid2.setText("");

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
                map.put("member_position_auto_id",pid.getText().toString());
                return map;
            }
        };
        AppSingleton1.getInstance(this).addToRequestQueue(StringRequest,TAG);
    }
}