package com.example.myapplication.ui.information;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;


public class InformationActivity extends AppCompatActivity {
    //test
    //
    //
    //
    //
    //58585
    //484654
    //54864546
    TextView textview;


    //send2
    //send3
    //send4
    //send4
    //send5

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        textview = findViewById(R.id.tv_information);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InformationActivity.this, "각 지점의 물품 재고를 보여줍니다. ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
