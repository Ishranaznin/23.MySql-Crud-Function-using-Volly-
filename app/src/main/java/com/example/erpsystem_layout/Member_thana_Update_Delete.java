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

public class Member_thana_Update_Delete extends AppCompatActivity {


    EditText thana_id, thana_name, member_id_thana;
    Button updatethanabtn, deletethanabtn;
    private static String TAG = Member_thana_Update_Delete.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_thana__update__delete);


        thana_id=  findViewById(R.id.ed_thanaInfoId_UD);
        thana_name =  findViewById(R.id.ed_thanaName_UD);
        member_id_thana = findViewById(R.id.ed_memberAutoID_thana_UD);

        updatethanabtn = findViewById(R.id.updateThana);
        deletethanabtn = findViewById(R.id.deleteThana);
        Intent intentt2 = getIntent();


        String thana_autoid= intentt2.getExtras().getString("key_id_thana");

        String thana_name2 = intentt2.getStringExtra("key_name_thana");
        String member_id_thana2 = intentt2.getExtras().getString("key_member_thana");


        thana_id.setText(thana_autoid);
        thana_name.setText(thana_name2);
        member_id_thana.setText(member_id_thana2);

      updatethanabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editthana();

            }
        });

       deletethanabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletethana();

            }
        });

    }

    private void editthana(){
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to update this thana?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        update_thana(); //edit method call
                        startActivity(new Intent(Member_thana_Update_Delete.this, MainActivity.class));

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


    private void update_thana()
    {

        String updateDataURL = "http://10.0.2.2/erp/updateMemberThana.php";

        StringRequest updateReq = new StringRequest(Request.Method.POST, updateDataURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // player_name.setText("");
                        thana_name.setText("");
                        Toast.makeText(getApplicationContext(), "thana UPDATE Successfully", Toast.LENGTH_SHORT).show();
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



                map.put("tana_info_auto_id",thana_id.getText().toString());
                map.put("tana_name",thana_name.getText().toString());
                map.put("member_auto_id",member_id_thana.getText().toString());
                return map;
            }
        };

        AppSingleton1.getInstance(this).addToRequestQueue(updateReq,TAG);
    }

    private void   deletethana(){
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this thana?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        delete_thana();
                        startActivity(new Intent(Member_thana_Update_Delete.this, MainActivity.class));
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

    public void delete_thana() {
        String myurl = "http://10.0.2.2/erp/deleteMemberThana.php";

        StringRequest StringRequest = new StringRequest(Request.Method.POST, myurl,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               thana_id.setText("");
                thana_name.setText("");
               member_id_thana.setText("");

                Toast.makeText(getApplicationContext(), "Thana DELETED Successfully", Toast.LENGTH_SHORT).show();

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
                map.put("tana_info_auto_id",thana_id.getText().toString());
                return map;
            }
        };
        AppSingleton1.getInstance(this).addToRequestQueue(StringRequest,TAG);
    }
}