package pe.com.jv.qrbarra;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button myBtnVerProducto, myBtnSalir;

    private Button myBtnCaptureCode;
    private  ZBarScannerView myScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBtnCaptureCode = (Button)findViewById(R.id.btnCaptureCode);
        myBtnCaptureCode.setOnClickListener(this);

        myBtnVerProducto = (Button) findViewById(R.id.btnProducto);
        myBtnSalir = (Button)findViewById(R.id.btnSalir1);

        myBtnVerProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });
        myBtnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCaptureCode:
                Intent intent = new Intent(this, MySampleEscannerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
