package flashbuy.flashbuy;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by ny on 5/8/2017.
 */

public class scan extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
//    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
//    private DatabaseReference myRef = database.child("company_A/item1");
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        stContentView(R.layout.scan);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler((ZXingScannerView.ResultHandler) this);
        mScannerView.startCamera();
    }
    @Override
    public void handleResult(Result result) {
//        //Do anything with result here :D
//        Log.w("handleResult", result.getText());
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Scan result");
//        builder.setMessage(result.getText());
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
        String product_id=result.getText();
        //myRef.setValue(product_id);

        Intent i= new Intent(this,product.class);
        i.putExtra("product_id", product_id);
        startActivity(i);
        //Resume scanning
        //mScannerView.resumeCameraPreview(this);
    }
    @Override
    public void  onBackPressed() {
        super.onBackPressed();
    }
}
