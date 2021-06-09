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

public class MemberCountryUpdateDelete extends AppCompatActivity {
    TextInputEditText country_id_ud,country_name_ud, member_id_country_ud;
    Button updatebtn, deletebtn;
    private static String TAG = MemberCountryUpdateDelete.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_country_update_delete);



        country_id_ud = (TextInputEditText) findViewById(R.id.ed_countryAutoID_UD);
        country_name_ud = (TextInputEditText) findViewById(R.id.ed_MembercountrysNameUD);
        member_id_country_ud = (TextInputEditText) findViewById(R.id.ed_memberAutoIDcountry_UD);

        updatebtn = (Button) findViewById(R.id.updatecountry);
        deletebtn = (Button) findViewById(R.id.deletecountry);
        Intent i = getIntent();


        //int country_id_auto = i.getExtras().getInt("key_id_country");
        String country_autoid = i.getStringExtra("key_id_country");
        String country_name = i.getStringExtra("key_name_country");
        String member_id_int_country = i.getStringExtra("key_member_country");


        country_id_ud .setText(country_autoid);
        country_name_ud.setText(country_name);
        member_id_country_ud.setText(member_id_int_country);


        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editcountry();

            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletecountry();

            }
        });

    }

    private void editcountry(){
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to update this country?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        update_country(); //edit method call
                        startActivity(new Intent(MemberCountryUpdateDelete.this, MainActivity.class));

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


    private void update_country()
    {

        String updateDataURL = "http://10.0.2.2/erp/updateMemberCountry.php";

        StringRequest updateReq = new StringRequest(Request.Method.POST, updateDataURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // player_name.setText("");
                        country_name_ud.setText("");
                        Toast.makeText(getApplicationContext(), "country UPDATE Successfully", Toast.LENGTH_SHORT).show();
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



                map.put("country_info_auto_id",country_id_ud.getText().toString());
                map.put("country_name",country_name_ud.getText().toString());
                map.put("member_auto_id",member_id_country_ud.getText().toString());
                return map;
            }
        };

        AppSingleton1.getInstance(this).addToRequestQueue(updateReq,TAG);
    }

    private void  deletecountry(){
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this country?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        delete_country();
                        startActivity(new Intent(MemberCountryUpdateDelete.this, MainActivity.class));
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

    public void delete_country() {
        String myurl = "http://10.0.2.2/erp/deleteMemberCountry.php";

        StringRequest StringRequest = new StringRequest(Request.Method.POST, myurl,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                country_id_ud.setText("");
                country_name_ud.setText("");
                member_id_country_ud.setText("");

                Toast.makeText(getApplicationContext(), "Country DELETED Successfully", Toast.LENGTH_SHORT).show();

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
                map.put("country_info_auto_id",country_id_ud.getText().toString());
                return map;
            }
        };
        AppSingleton1.getInstance(this).addToRequestQueue(StringRequest,TAG);
    }
}