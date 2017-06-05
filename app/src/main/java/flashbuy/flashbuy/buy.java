package flashbuy.flashbuy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by ny on 6/4/2017.
 */

public class buy extends Fragment {
    public buy(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_buy, container, false);
        Button button=(Button)v.findViewById(R.id.choose);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=new Intent(getActivity(),fingerprint.class);
                startActivity(i);
            }
        });
        return v;
    }
}
