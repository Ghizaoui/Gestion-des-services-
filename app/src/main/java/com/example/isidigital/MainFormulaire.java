package com.example.isidigital;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainFormulaire  extends AppCompatActivity {
   // private ArrayList<Champs> champsListes;
    String login="";
    String pass="";
    TextView textView;
   private EditText editText;
    Button btn;
    String url;
    String url2;
    String id;
    LinearLayout linearLayout;
    int i;
    private ArrayList<EditText> allEds;
    private ArrayList<TextView> allTx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutchamps);
        Intent res=getIntent();
         id= res.getStringExtra("id");
         url="http://digitalisi.tn:8080/engine-rest/process-definition/"+id+"/form-variables";
         url2="http://digitalisi.tn:8080/engine-rest/process-definition/"+id+"/submit-form";

        SharedPreferences sharedPreferences=getSharedPreferences("MyPref",MODE_PRIVATE);
        login=sharedPreferences.getString("login","");
        pass=sharedPreferences.getString("pass","");
        linearLayout=findViewById(R.id.linearlayout);




         allEds = new ArrayList<>();
        allTx = new ArrayList<>();



         i=0;
        getChamps();


    }

    private void getChamps()
    {

        RequestQueue queue = Volley.newRequestQueue(MainFormulaire.this);
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                //int i=0;
                Iterator<String> iterator=response.keys();
                while(iterator.hasNext())
                {
                     textView= new TextView(MainFormulaire.this);
                    textView.setText(iterator.next());
                    allTx.add(textView);
                    linearLayout.addView(textView);
                    editText=new EditText(MainFormulaire.this);
                    editText.setId(i);
                    allEds.add(editText);
                    linearLayout.addView(editText);
                    i++;



                }
                 btn=new Button(MainFormulaire.this);
                btn.setText("Submit");
                linearLayout.addView(btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        postData();
                       // Toast.makeText(MainFormulaire.this, allTx.get(0).getText()+" :: "+allEds.get(0).getText().toString(), Toast.LENGTH_LONG).show();

                    }
                });



            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainFormulaire.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //add params <key,value>
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                // add headers <key,value>
                String credentials = login + ":" + pass;
                String auth = "Basic "
                        + Base64.encodeToString(credentials.getBytes(),
                        Base64.NO_WRAP);
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonArrayRequest);

    }

    public void postData()
    {
        RequestQueue queue2 = Volley.newRequestQueue(MainFormulaire.this);
        JSONObject fromobject= new JSONObject();

        try {
            i=i-1;
            while(i>=0) {

                fromobject.put(allTx.get(i).getText().toString(), allEds.get(i).getText().toString());
                i--;

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonArrayRequest2 = new JsonObjectRequest(Request.Method.POST, url2, fromobject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Toast.makeText(MainFormulaire.this, "Donnes envoyes", Toast.LENGTH_SHORT).show();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainFormulaire.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //add params <key,value>
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                // add headers <key,value>
                String credentials = login + ":" + pass;
                String auth = "Basic "
                        + Base64.encodeToString(credentials.getBytes(),
                        Base64.NO_WRAP);
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue2.add(jsonArrayRequest2);
            }

            public  void onclick(View v)
            {
                postData();
            }


}
