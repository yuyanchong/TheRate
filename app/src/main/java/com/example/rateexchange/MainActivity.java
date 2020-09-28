package com.example.rateexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public HashMap<String, Float> rate = new HashMap<>();
    public float newDollar,newEuroi,newWon;
    public float dollarRate,euroRate,wonRate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dollarRate = 1.5f;
        euroRate = 2.5f;
        wonRate = 3.5f;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void open(View v) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("dollar_rate_key",dollarRate);
        intent.putExtra("euro_rate_key",euroRate);
        intent.putExtra("won_rate_key",wonRate);
        startActivityForResult(intent, 1);
    }

    public void convert(View view) {
        EditText et = findViewById(R.id.editTextTextPersonName);
        TextView tv = findViewById(R.id.textView);
        Pattern pattern = Pattern.compile("[0-9]+\\.?[0-9]*");
        Matcher matcher = pattern.matcher(et.getText());
        if (matcher.matches()) {
            if(view.getTag().equals("dollar")){
                tv.setText(String.valueOf(Float.parseFloat(matcher.group()) * dollarRate));
            }
            if(view.getTag().equals("euro")){
                tv.setText(String.valueOf(Float.parseFloat(matcher.group()) * euroRate));
            }
            if(view.getTag().equals("won")){
                tv.setText(String.valueOf(Float.parseFloat(matcher.group()) * wonRate));
            }
        } else {
            Toast.makeText(this, "输入金额", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (requestCode == 1 && resultCode == 2) {

            Bundle bundle = data.getExtras();
            dollarRate = bundle.getFloat("key_dollar",0f);
            euroRate = bundle.getFloat("key_euro",0f);
            wonRate = bundle.getFloat("key_won",0f);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}