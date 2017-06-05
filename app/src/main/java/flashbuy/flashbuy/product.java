package flashbuy.flashbuy;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class product extends Fragment {
    int buy_count=0;
    String product_name="";
    String product_price="";
    int price=0;
    public product(){
        buy_count=0;
        product_name="";
        product_price="";
        price=0;
    }

    private static final String TAG ="" ;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
   private DatabaseReference myRef = database.child("company_A");
    private DatabaseReference buy=database.child("company_A/user_tommy");


    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_product);
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_product, container, false);
        myRef.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                Intent intent = getIntent();
//                String product_id = intent.getExtras().getString("product_id");
                Bundle bundle = getArguments();
                String product_id="";
                product_id= bundle.getString("product_id", product_id);
                String key = product_id;
                TextView t2 = (TextView) v.findViewById(R.id.textView2);
                t2.setText(product_id);
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot child_id : dataSnapshot.getChildren()) {
                        if (child_id.getKey().equals(key)) {
                            for (DataSnapshot info_snapshot : child_id.getChildren()) {
                                String info = String.valueOf(info_snapshot.getValue());
                                if (info_snapshot.getKey().equals("name")) {
                                    TextView t1 = (TextView) v.findViewById(R.id.textView8);
                                    t1.setText(info);
                                    product_name=info;
                                    if (info.equals("M&M")) {
                                        ImageView img = (ImageView) v.findViewById(R.id.imageView2);
                                        img.setImageResource(R.drawable.mm);
                                    }else if(info.equals("tea")){
                                        ImageView img = (ImageView) v.findViewById(R.id.imageView2);
                                        img.setImageResource(R.drawable.tea);
                                    }
                                } else if (info_snapshot.getKey().equals("price")) {
                                    TextView t1 = (TextView) v.findViewById(R.id.textView3);
                                    t1.setText(info);
                                } else if (info_snapshot.getKey().equals("discount")) {
                                    TextView t1 = (TextView) v.findViewById(R.id.textView9);
                                    t1.setText(info);
                                } else if (info_snapshot.getKey().equals("ingredients")) {
                                    TextView t1 = (TextView) v.findViewById(R.id.textView11);
                                    t1.setText(info);
                                } else if (info_snapshot.getKey().equals("allergy")) {
                                    TextView t1 = (TextView) v.findViewById(R.id.textView13);
                                    t1.setText(info);
                                }else if(info_snapshot.getKey().equals("real_price")){
                                    product_price=info;
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
        });
        Button button = (Button) v.findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                buy_count+=1;
                price = Integer.valueOf(product_price);
                buy.child(product_name).child("real_price").setValue(price);
                buy.child(product_name).child("number").setValue(buy_count);
                Toast t1=Toast.makeText(getActivity(),buy_count+" Item has been added to cart",Toast.LENGTH_SHORT);
                t1.show();
            }
        });
        Button button2 = (Button) v.findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                getFragmentManager().popBackStack();
            }
        });
        return v;
    }

}

