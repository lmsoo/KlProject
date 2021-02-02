package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.ui.myinfo.MyInfoActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ResultActivity extends AppCompatActivity {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    private TextView tv_result;
    private ImageView image_profile;
    private TextView tv_email;
    Button btn_logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName");
        String photoUrl = intent.getStringExtra("photoUrl");
        String email = intent.getStringExtra("email");
        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                finish();
                Toast.makeText(ResultActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MyInfoActivity.class);
                startActivity(intent);


            }
        });


        tv_result = findViewById(R.id.tv_result);
        tv_result.setText(nickName);

        image_profile = findViewById(R.id.image_profile);
        Glide.with(this).load(photoUrl).into(image_profile);

        tv_email = findViewById(R.id.tv_email);
        tv_email.setText(email);



    }
}