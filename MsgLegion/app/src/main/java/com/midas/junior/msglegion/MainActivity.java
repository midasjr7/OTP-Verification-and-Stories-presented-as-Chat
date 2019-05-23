package com.midas.junior.msglegion;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private EditText phoneno;
    private ImageButton send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=findViewById(R.id.spinner);
        phoneno=findViewById(R.id.phoneNumber);
        send=findViewById(R.id.send);
        spinner.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,Countrycodes.countryNames));
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=Countrycodes.countryCodes[spinner.getSelectedItemPosition()];
                String number=phoneno.getText().toString().trim();
                if(number.isEmpty()&& number.length()<10){
                    phoneno.setError("Valid number is Required");
                    phoneno.requestFocus();
                    return;
                }
                String phoneNumber="+"+code+number;
                Intent i=new Intent(MainActivity.this,Verification.class);
                i.putExtra("phonenumber",phoneNumber);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent i=new Intent(MainActivity.this,Chat.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }
}
