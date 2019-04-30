package com.midas.junior.msglegion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class sendpic extends AppCompatActivity {
    private ImageButton bulbonof;
    private ImageButton signout;
    private ImageView bulb;
    private int on=R.drawable.noun_buttonon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendpic);
        bulbonof=findViewById(R.id.bulbonoff);
        signout=findViewById(R.id.signout);
        bulb=findViewById(R.id.bulb);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(sendpic.this,MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        bulbonof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(on==R.drawable.noun_buttonon){
//                    bulbonof.setBackgroundResource(R.drawable.noun_buttonon);
//                    int o=R.drawable.noun_buttonon;
//                    on=1;
//                while (o==R.drawable.noun_buttonon){
//                    bulb.setBackgroundResource(R.drawable.bulbglow);
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    bulb.setBackgroundResource(R.drawable.bulb);
//                }
//                }else {
//                    bulbonof.setBackgroundResource(R.drawable.noun_buttonoff);
//                    bulb.setBackgroundResource(R.drawable.bulb);
//                    on=0;
//                }

                if(on==R.drawable.noun_buttonon){
                bulbonof.setBackgroundResource(R.drawable.noun_buttonon);
                bulb.setBackgroundResource(R.drawable.bulbl);
                on=R.drawable.noun_buttonoff;}else {
                    bulbonof.setBackgroundResource(R.drawable.noun_buttonoff);
                    bulb.setBackgroundResource(R.drawable.bulb0);
                    on=R.drawable.noun_buttonon;
                }

            }
        });
    }

}
