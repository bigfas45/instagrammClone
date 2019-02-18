package com.example.instagrammclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtkickPower;
    private TextView txtGetData;
    private Button btnGetAllData;
    private String allkickBoxer;
    private Button btnTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnsave);
        btnSave.setOnClickListener(MainActivity.this);

        edtName = findViewById(R.id.edtName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtPunchPower = findViewById(R.id.edtKickPower);
        edtKickSpeed = findViewById(R.id.edtkickSpeed);
        edtkickPower = findViewById(R.id.edtKickPower);

        txtGetData = findViewById(R.id.txtGetData);

        btnGetAllData = findViewById(R.id.btnGetAllData);
        btnTransition = findViewById(R.id.btnNextActivity);



        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
               parseQuery.getInBackground("uVlIhoSZtc", new GetCallback<ParseObject>() {
                   @Override
                   public void done(ParseObject object, ParseException e) {
                       if (object != null && e == null){
                           txtGetData.setText(object.get("name") + " - " + " Punch Power : " + object.get("punchPower") + "");
                       }
                   }
               });
            }
        });

        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allkickBoxer = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");

//                queryAll.whereGreaterThan("punchPower", 2000);
                queryAll.whereGreaterThanOrEqualTo("punchPower", 1000);


                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null){
                            if (objects.size() > 0){
                                for (ParseObject KickBoxer : objects) {

                                    allkickBoxer = allkickBoxer + KickBoxer.get("name") + "\n";
                                }

                                FancyToast.makeText(MainActivity.this, allkickBoxer, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            }else{
                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }
                    }
                });
            }
        });

        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,
                        SignUpLoginActivity.class);
                startActivity(intent);

            }
        });


    }


    @Override
    public void onClick(View v) {

       try {
           final ParseObject KickBoxer = new ParseObject("KickBoxer");
           KickBoxer.put("name", edtName.getText().toString());
           KickBoxer.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
           KickBoxer.put("punchPower", Integer.parseInt(edtkickPower.getText().toString()));
           KickBoxer.put("KickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
           KickBoxer.put("KickPower", Integer.parseInt(edtkickPower.getText().toString()));
           KickBoxer.saveInBackground(new SaveCallback() {
               @Override
               public void done(ParseException e) {
                   if (e == null) {
                       FancyToast.makeText(MainActivity.this, KickBoxer.get("name") + "Is Saved To SERVER !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                   } else {
                       FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                   }
               }
           });
       }catch (Exception e){
           FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
       }
    }
}
