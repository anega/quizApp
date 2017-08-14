package com.example.android.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean formIsValid() {
        EditText nameContents = (EditText) findViewById(R.id.username);
        String nameStr = nameContents.getText().toString().trim();
        Boolean validName = !TextUtils.isEmpty(nameStr);

        EditText whaleNameContents = (EditText) findViewById(R.id.whale_name);
        String whaleNameStr = whaleNameContents.getText().toString().trim();
        Boolean validWhaleName = whaleNameStr.equals("Willy");

        return (validName && validWhaleName);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void submitQuiz(View view) {
        String message = "";
        if (formIsValid()) {
            message = "You've passed! Congratulations";
        } else {
            message = "Sorry! Think and google.";
        }

        showToast(message);
    }
}
