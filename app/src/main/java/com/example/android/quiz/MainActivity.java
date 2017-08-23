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

    // Declare all variables
    EditText nameContents;
    String nameStr;
    Boolean isValidName;
    EditText whaleNameContents;
    String whaleNameStr;
    Boolean isValidWhaleName;
    RadioButton whalePic;
    Boolean isValidWhalePic;
    CheckBox whaleBlack;
    Boolean isValidWhaleBlack;
    CheckBox whaleSky;
    Boolean isValidWhaleSky;
    CheckBox whaleWhite;
    Boolean isValidWhaleWhite;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI(findViewById(R.id.parent));

        // Get views, will need for validation
        nameContents = (EditText) findViewById(R.id.et_username);
        whaleNameContents = (EditText) findViewById(R.id.et_whale_name);
        whalePic = (RadioButton) findViewById(R.id.rb_whale_pic);
        whaleBlack = (CheckBox) findViewById(R.id.cb_color_black);
        whaleSky = (CheckBox) findViewById(R.id.cb_color_sky);
        whaleWhite = (CheckBox) findViewById(R.id.cb_color_white);
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

    /**
     * Validate form(check if all the answers are right)
     * @return true if all answers are right
     */
    public boolean validateForm() {
        nameStr = nameContents.getText().toString().trim();
        isValidName = !TextUtils.isEmpty(nameStr);
        whaleNameStr = whaleNameContents.getText().toString().trim();
        isValidWhaleName = whaleNameStr.equals("Willy");
        isValidWhalePic = whalePic.isChecked();
        isValidWhaleBlack = whaleBlack.isChecked();
        isValidWhaleSky = whaleSky.isChecked();
        isValidWhaleWhite = whaleWhite.isChecked();

        return (isValidName && isValidWhaleName && isValidWhalePic && isValidWhaleBlack && !isValidWhaleSky && isValidWhaleWhite);
    }

    /**
     * Show toast with proper message
     * @param message string, will be shown to user
     */
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void submitQuiz(View view) {
        if (validateForm()) {
            message = getString(R.string.user_res_passed);
        } else {
            message = getString(R.string.user_res_failed);
        }

        showToast(message);
    }
}
