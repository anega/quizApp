package com.example.android.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
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

        RadioButton whalePic = (RadioButton) findViewById(R.id.whale_pic);
        Boolean validWhalePic = whalePic.isChecked();

        CheckBox whaleBlack = (CheckBox) findViewById(R.id.color_black);
        Boolean validWhaleBlack = whaleBlack.isChecked();

        CheckBox whaleSky = (CheckBox) findViewById(R.id.color_sky);
        Boolean validWhaleSky = whaleSky.isChecked();

        CheckBox whaleWhite = (CheckBox) findViewById(R.id.color_white);
        Boolean validWhaleWhite = whaleWhite.isChecked();

        return (validName && validWhaleName && validWhalePic && validWhaleBlack && !validWhaleSky && validWhaleWhite);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void submitQuiz(View view) {
        String message = "";
        if (formIsValid()) {
            message = getString(R.string.passed);
        } else {
            message = getString(R.string.failed);
        }

        showToast(message);
    }
}
