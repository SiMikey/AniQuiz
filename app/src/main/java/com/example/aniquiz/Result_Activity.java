package com.example.aniquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aniquiz.databinding.ActivityMainBinding;
import com.example.aniquiz.databinding.ActivityResultBinding;

public class Result_Activity extends AppCompatActivity {

    ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);

        binding.txtAnswer.setText(
                "Your score is: "
                        +MainActivity.result
                        +"/" +MainActivity.totalquestion);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Result_Activity.this
                        , MainActivity.class);
                startActivity(intent);
            }
        });
    }
}