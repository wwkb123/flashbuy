package flashbuy.flashbuy;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by ny on 5/8/2017.
 */

public class scan extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
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
        //Do anything with result here :D
        Log.w("handleResult", result.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan result");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //Resume scanning
        //mScannerView.resumeCameraPreview(this);
    }
    @Override
    public void  onBackPressed() {
        super.onBackPressed();
    }
}
