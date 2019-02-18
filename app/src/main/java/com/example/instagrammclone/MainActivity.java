package com.example.instagrammclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtkickPower;

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
