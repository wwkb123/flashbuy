package flashbuy.flashbuy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by ny on 6/4/2017.
 */

public class cart extends Fragment {
    public cart(){
        info = "";
        number="";
        num=0.00;
        total=0.00;
    }
    private static final String TAG ="" ;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference myRef = database.child("company_A/user_tommy");
    String info = "";
    String number="";
    double num=0.00;
    double total=0.00;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_history);
        final View v = inflater.inflate(R.layout.activity_cart, container, false);
        final TextView t1= (TextView) v.findViewById(R.id.textView16);
        info = "";
        number="";
        num=0.00;
        total=0.00;
        myRef.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                Intent intent = getIntent();
//                String product_id = intent.getExtras().getString("product_id");
                if (dataSnapshot.hasChildren()) {

                    for (DataSnapshot child_id : dataSnapshot.getChildren()) {
                        info += child_id.getKey()+"   ";
                        for(DataSnapshot child_child:child_id.getChildren()){
                            if(child_child.getKey().equals("number")){
                                info +=String.valueOf(child_child.getValue())+"     ";
                                number= String.valueOf(child_child.getValue());
                                num=Double.valueOf(number);
                            }else if(child_child.getKey().equals("real_price")){
                                String price= String.valueOf(child_child.getValue());
                                double pri=Double.valueOf(price);
                                info +="$"+num*pri+"\n";
                                total+=num*pri;
                            }

                        }
                    }
                    TextView t1 = (TextView) v.findViewById(R.id.textView16);
                    t1.setText(info);
                    TextView t2= (TextView)v.findViewById(R.id.textView19);
                    info=t2.getText()+"       $"+total*0.045;
                    t2.setText(info);
                    TextView t3= (TextView)v.findViewById(R.id.textView27);
                    info=t3.getText()+"     $"+(total*0.045+total);
                    t3.setText(info);
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
        Button button=(Button)v.findViewById(R.id.button_add);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Fragment fragment=new scan();
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.mainFrame,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        Button button2=(Button)v.findViewById(R.id.button_buy);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Fragment fragment=new buy();
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.mainFrame,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return v;
    }
}
