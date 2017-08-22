package com.example.android.quiz;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI(findViewById(R.id.parent));
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setupUI(View view) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(MainActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public boolean validateForm() {
        EditText nameContents = (EditText) findViewById(R.id.et_username);
        String nameStr = nameContents.getText().toString().trim();
        Boolean validName = !TextUtils.isEmpty(nameStr);

        EditText whaleNameContents = (EditText) findViewById(R.id.et_whale_name);
        String whaleNameStr = whaleNameContents.getText().toString().trim();
        Boolean validWhaleName = whaleNameStr.equals("Willy");

        RadioButton whalePic = (RadioButton) findViewById(R.id.rb_whale_pic);
        Boolean validWhalePic = whalePic.isChecked();

        CheckBox whaleBlack = (CheckBox) findViewById(R.id.cb_color_black);
        Boolean validWhaleBlack = whaleBlack.isChecked();

        CheckBox whaleSky = (CheckBox) findViewById(R.id.cb_color_sky);
        Boolean validWhaleSky = whaleSky.isChecked();

        CheckBox whaleWhite = (CheckBox) findViewById(R.id.cb_color_white);
        Boolean validWhaleWhite = whaleWhite.isChecked();

        return (validName && validWhaleName && validWhalePic && validWhaleBlack && !validWhaleSky && validWhaleWhite);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void submitQuiz(View view) {
        String message = "";
        if (validateForm()) {
            message = getString(R.string.passed);
        } else {
            message = getString(R.string.failed);
        }

        showToast(message);
    }
}
