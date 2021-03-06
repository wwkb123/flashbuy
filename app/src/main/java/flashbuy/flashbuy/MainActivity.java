package flashbuy.flashbuy;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import com.google.zxing.Result;
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;


//
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Fragment fragment=new home();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void onClick(View v){
//       Intent i= new Intent(MainActivity.this,scan.class);
//        startActivity(i);
        Fragment fragment=new scan();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mScannerView.stopCamera();
//    }

    @Override
    public void handleResult(Result result) {
        //Do anything with result here :D
//
//        Log.w("handleResult", result.getText());
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Scan result");
//        builder.setMessage(result.getText() );
//        AlertDialog alertDialog = builder.create();
////        myRef.setValue("ggg");
//        alertDialog.show();


        //Resume scanning
        //mScannerView.resumeCameraPreview(this);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment=null;
        if(id==R.id.home){
            fragment=new home();
        } else if (id == R.id.cart) {
            fragment=new cart();
        } else if (id == R.id.history) {
//        Intent i= new Intent(this,history.class);
//            startActivity(i);
            fragment=new history();

        } else if (id == R.id.settings) {
            fragment=new settings();
        } else if (id == R.id.help) {
            fragment=new help();
        } else if (id == R.id.logout){
            fragment=new help();
        }
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
