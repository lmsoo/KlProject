package com.example.myapplication.ui.myinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.zxing.ResultPoint;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import org.w3c.dom.Text;

import java.util.List;

public class MyInfoBarcodeActivity extends AppCompatActivity {
    CaptureManager captureManager;
    DecoratedBarcodeView barcodeScannerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info_barcode);

        barcodeScannerView = (DecoratedBarcodeView) findViewById(R.id.dbvBarcode);
        captureManager = new CaptureManager(this,barcodeScannerView);
        captureManager.initializeFromIntent(this.getIntent(),savedInstanceState);
        captureManager.decode();
        barcodeScannerView.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                readBarcode(result.toString());
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }

    private void readBarcode(String barcode) {
        Toast.makeText(getApplicationContext(),barcode, Toast.LENGTH_SHORT).show();
    }
}