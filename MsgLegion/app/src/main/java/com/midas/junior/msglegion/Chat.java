package com.midas.junior.msglegion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Chat extends AppCompatActivity {
    RelativeLayout Lout ;
    private RecyclerView rv;

    FirebaseDatabase datatbase;
    DatabaseReference ref;
    private int count;
    ImageView chit;
    ImageView shubhi;

    List<Message> listR;
    ArrayList<Message> listL;
    MessageAdapter adaptorR;
    private RelativeLayout signout1;
    String url="https://scontent.fbho2-1.fna.fbcdn.net/v/t1.0-9/36175961_989073857926206_4455089659684323328_n.jpg?_nc_cat=100&_nc_oc=AQlBHcEfDjbAwASzaaPybRHwjibHKI08LCvx2-hJgL2WWhBena5WBQRdswHFZinwxHo&_nc_ht=scontent.fbho2-1.fna&oh=7e8aa673857d5e624ee388b1e367d13c&oe=5D9FC431";
    String url2="https://scontent.fbho2-1.fna.fbcdn.net/v/t1.0-9/42748872_2164395857170934_6760073400560910336_n.jpg?_nc_cat=110&_nc_oc=AQn_gh1OXTtTbIsrOUYCnxCL6X_bXBBNG75A5BAIvzrelcWpt-Cn0eA1pAti9JphqVs&_nc_ht=scontent.fbho2-1.fna&oh=bf9c09b5a6bb9f3cb5f391fe444b7f24&oe=5D9B99B5";




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        rv=findViewById(R.id.chatui);
        signout1=findViewById(R.id.signout1);
        Lout = findViewById(R.id.touch);
        chit=findViewById(R.id.chit);
        shubhi=findViewById(R.id.shubhi);


        loadImageFromuri(url,url2);



        count=1;
        listR = new ArrayList<>();
        adaptorR =new MessageAdapter(listR);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv.setAdapter(adaptorR);


        signout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(Chat.this,MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
        Lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToCall();
            }
        });


    }

    private void loadImageFromuri(String url,String url2) {
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(chit, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Toast.makeText(Chat.this,"Couldn't load the Image",Toast.LENGTH_LONG).show();
                    }
                });
        Picasso.with(this).load(url2).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(shubhi, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                Toast.makeText(Chat.this,"Couldn't load the Image",Toast.LENGTH_LONG).show();
                    }
                });
    }


    private void ToCall() {

        try{datatbase = FirebaseDatabase.getInstance();}
        catch(Exception e){
            Toast.makeText(this,"Network Connection Failed", Toast.LENGTH_SHORT).show();
        }

        ref = datatbase.getReference().child("Chat");
        final DateFormat d=new SimpleDateFormat("HH:mm aa");
        final Date date=new Date();


        ref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (count < 16) {
                    for (int i = count; i ==count; i++) {
                        String chat = dataSnapshot.child(Integer.toString(count)).getValue().toString();

                        if (count % 2 == 0) {
                         Message msg=new Message(chat,true,d.format(date));

                            listR.add(msg);
                            adaptorR.notifyDataSetChanged();


                        }
                        if(count%2==1){

                            Message msg=new Message(chat,false,d.format(date));
                            listR.add(msg);
                            adaptorR.notifyDataSetChanged();

                        }
                    }
                    count = count + 1;
                }else {
                    Toast.makeText(getApplicationContext(),"The End",Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(),"You are Offline",Toast.LENGTH_LONG).show();
            }

        });





    }

}
