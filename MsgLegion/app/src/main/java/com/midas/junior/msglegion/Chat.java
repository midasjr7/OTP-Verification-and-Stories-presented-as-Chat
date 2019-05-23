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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Chat extends AppCompatActivity {
    ListView listViewR;
    ListView listViewL;
    RelativeLayout Lout ;
    private RecyclerView rv;

    FirebaseDatabase datatbase;
    DatabaseReference ref;
    private int count;

    List<Message> listR;
    ArrayList<Message> listL;
    MessageAdapter adaptorR;
    private ImageView signout1;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        rv=findViewById(R.id.chatui);
        signout1=findViewById(R.id.signout);
        Lout = findViewById(R.id.touch);





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
