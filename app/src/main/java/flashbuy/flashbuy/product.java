package flashbuy.flashbuy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class product extends AppCompatActivity {

    private static final String TAG ="" ;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
   private DatabaseReference myRef = database.child("company_A");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        myRef.addValueEventListener(postListener);


    }
    ValueEventListener postListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Intent intent = getIntent();
            String product_id = intent.getExtras().getString("product_id");
            String key=product_id;
            TextView t2= (TextView) findViewById(R.id.textView2);
            t2.setText(product_id);
            if(dataSnapshot.hasChildren()){
                for(DataSnapshot child_id:dataSnapshot.getChildren()){
                    if(child_id.getKey().equals(key)){
                        for(DataSnapshot info_snapshot:child_id.getChildren()){
                            String info= String.valueOf(info_snapshot.getValue());
                           if(info_snapshot.getKey().equals("name")){
                               TextView t1= (TextView) findViewById(R.id.textView8);
                               t1.setText(info);
                               if(info.equals("M&M")){
                                   ImageView img= (ImageView) findViewById(R.id.imageView2);
                                   img.setImageResource(R.drawable.mm);
                               }
                           }else if (info_snapshot.getKey().equals("price")){
                               TextView t1= (TextView) findViewById(R.id.textView3);
                               t1.setText(info);
                           }else if(info_snapshot.getKey().equals("discount")){
                               TextView t1= (TextView) findViewById(R.id.textView9);
                               t1.setText(info);
                           }else if(info_snapshot.getKey().equals("ingredients")){
                               TextView t1= (TextView) findViewById(R.id.textView11);
                               t1.setText(info);
                           }
                           else if(info_snapshot.getKey().equals("allergy")){
                               TextView t1= (TextView) findViewById(R.id.textView13);
                               t1.setText(info);
                           }

                        }
                    }
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            // ...
        }
    };
    public void onCancelClick(View v){
        super.onBackPressed();
    }


}

