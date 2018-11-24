package pe.com.jv.qrbarra;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class MySampleEscannerActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {

    private final static String TAG = "ScannerLog";
    private ZBarScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result result) {
        final String code = result.getContents();
        final String format = result.getBarcodeFormat().getName();
        final String fullMessage = "Contents = " +code + ", Format = "+ format;
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone sonido = RingtoneManager.getRingtone(getApplicationContext(),notification);
            sonido.play();
        }catch (Exception e){
            Log.e(TAG, e.getLocalizedMessage());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Resultado del Scan");
        builder.setMessage(fullMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(MySampleEscannerActivity.this, ProductActivity.class).putExtra("codigoX",code));
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        //myCodex(code);

        //Intent intent = new Intent();
        //intent.putExtra("xxx", code);

        //startActivity(new Intent(context, ProductActivity.class).putExtra("codigo",code));
        //mScannerView.resumeCameraPreview(this);

    }

    /*public String myCodex(String xxx) {
        return xxx;
    }*/
}
