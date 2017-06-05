package flashbuy.flashbuy;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by ny on 5/8/2017.
 */

public class scan extends android.support.v4.app.Fragment implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    public scan(){

    }

public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mScannerView = new ZXingScannerView(getActivity());
        mScannerView.setResultHandler((ZXingScannerView.ResultHandler) this);
        mScannerView.startCamera();
    return mScannerView;
    }
    @Override
    public void handleResult(Result result) {

        String product_id=result.getText();
        Bundle bundle = new Bundle();
        bundle.putString("product_id", product_id);
        android.support.v4.app.Fragment fragment=new product();
        fragment.setArguments(bundle);
        FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onResume(){
        super.onResume();
       mScannerView.resumeCameraPreview(this);
    }



//    @Override
//    public void onPause() {
//        super.onPause();
//        mScannerView.stopCamera();
//    }
}
