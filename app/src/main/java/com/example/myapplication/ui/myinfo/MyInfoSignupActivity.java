package com.example.myapplication.ui.myinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.myapplication.R;

import static android.widget.Toast.LENGTH_SHORT;

public class MyInfoSignupActivity extends AppCompatActivity {
    Button btn_agree;
    CheckBox check_agree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info_signup);
        btn_agree = findViewById(R.id.btn_agree);
        check_agree = findViewById(R.id.check_agree);

        btn_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_agree.isChecked()) {
                   Intent intent = new Intent(getApplicationContext(),MyInfoActivity.class);
                   startActivity(intent);
                   Toast.makeText(MyInfoSignupActivity.this, "회원가입을 축하합니다.", LENGTH_SHORT).show();

                }
                else{
                  Toast.makeText(MyInfoSignupActivity.this,"약관 동의에 체크하여 주시기 바랍니다.", LENGTH_SHORT).show();

                }


            }

        });


    }
}