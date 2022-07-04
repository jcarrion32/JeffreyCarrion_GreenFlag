package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CreateAccount extends AppCompatActivity {

    ImageButton ibtnMoveToMain;
    TextInputEditText etEmail, etPassword, etPassword2;
    Button btnNext;
    String inputEmail, inputPassword, inputPassword2;
    boolean validEmail, validPassword, validPassword2;
    String TAG = "ActivityTest-Main";
    TextView tvError;
    LinearLayout llError, llEmail, llPassword, llPassword2;
    ImageView ivTxt1, ivTxt2, ivTxt3;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ibtnMoveToMain = findViewById(R.id.ibtn_back_to_main);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etPassword2 = findViewById(R.id.et_password2);
        btnNext = findViewById(R.id.btn_next);
        tvError = findViewById(R.id.tv_error);
        llError = findViewById(R.id.ll_error);
        llEmail = findViewById(R.id.ll_email);
        llPassword = findViewById(R.id.ll_password);
        llPassword2 = findViewById(R.id.ll_password2);
        ivTxt1 = findViewById(R.id.iv_txt1);
        ivTxt2 = findViewById(R.id.iv_txt2);
        ivTxt3 = findViewById(R.id.iv_txt3);


        ibtnMoveToMain.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });


        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                inputEmail = etEmail.getText().toString();
                validEmail = isValidEmail(inputEmail);

                if(!validEmail){
                    tvError.setText(R.string.email_error);
                    llError.setVisibility(View.VISIBLE);
                    llEmail.setBackgroundResource(R.drawable.text_red_border);
                    ivTxt1.setBackgroundResource(R.mipmap.im_txt_error);

                } else  {
                    tvError.setText("");
                    llError.setVisibility(View.GONE);
                    llEmail.setBackgroundResource(R.drawable.green_borders);
                    ivTxt1.setBackgroundResource(R.mipmap.im_txt_good);
                }
                enableNextBtn(validEmail, validPassword, validPassword2);
            }
        });


        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                inputPassword = etPassword.getText().toString();
                validPassword = isValidPassword(inputPassword);

                if(!validPassword && !validEmail){
                    tvError.setText(R.string.email_error);
                    llError.setVisibility(View.VISIBLE);
                    llPassword.setBackgroundResource(R.drawable.text_red_border);
                    ivTxt2.setBackgroundResource(R.mipmap.im_txt_error);

                } else if (!validPassword) {
                    tvError.setText(R.string.error_password1);
                    llError.setVisibility(View.VISIBLE);
                    llPassword.setBackgroundResource(R.drawable.text_red_border);
                    ivTxt2.setBackgroundResource(R.mipmap.im_txt_error);
                }else {
                    tvError.setText("");
                    llError.setVisibility(View.GONE);
                    llPassword.setBackgroundResource(R.drawable.green_borders);
                    ivTxt2.setBackgroundResource(R.mipmap.im_txt_good);
                }
                enableNextBtn(validEmail, validPassword, validPassword2);
            }
        });

        etPassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                inputPassword2 = etPassword2.getText().toString();
                validPassword2 = isValidPassword2(inputPassword, inputPassword2);

                if(!validPassword && !validEmail && !validPassword2){
                    tvError.setText(R.string.email_error);
                    llError.setVisibility(View.VISIBLE);
                    llPassword2.setBackgroundResource(R.drawable.text_red_border);
                    ivTxt3.setBackgroundResource(R.mipmap.im_txt_error);

                } else if (!validPassword && validEmail && !validPassword2) {
                    tvError.setText(R.string.error_password1);
                    llError.setVisibility(View.VISIBLE);
                    llPassword2.setBackgroundResource(R.drawable.text_red_border);
                    ivTxt3.setBackgroundResource(R.mipmap.im_txt_error);

                }else if (validPassword && validEmail && !validPassword2){
                    tvError.setText(R.string.error_password2);
                    llError.setVisibility(View.VISIBLE);
                    llPassword2.setBackgroundResource(R.drawable.text_red_border);
                    ivTxt3.setBackgroundResource(R.mipmap.im_txt_error);

                } else {
                    tvError.setText("");
                    llError.setVisibility(View.GONE);
                    llPassword2.setBackgroundResource(R.drawable.green_borders);
                    ivTxt3.setBackgroundResource(R.mipmap.im_txt_good);
                }
                enableNextBtn(validEmail, validPassword, validPassword2);
            }
        });

        btnNext.setOnClickListener(view -> {
            CustomerModel customerModel = new CustomerModel(-1, etEmail.getText().toString());
            DatabaseHelper databaseHelper = new DatabaseHelper(CreateAccount.this);

            boolean success = databaseHelper.addOne(customerModel);

            if (success){
                Toast myToast = Toast.makeText(this, "Your account has been saved!", Toast.LENGTH_SHORT);
                myToast.show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);}

            else{
                llError.setVisibility(View.VISIBLE);
                tvError.setText(R.string.error_account_exist);
                llEmail.setBackgroundResource(R.drawable.text_red_border);
                ivTxt1.setBackgroundResource(R.mipmap.im_txt_error);
            }




        });
    }

    public boolean isValidEmail(String email) {
        String  regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public boolean isValidPassword(String password) {
        String length = "^.{8,}";
        String upperCase = "^.*[A-Z].*$";
        String lowerCase = "^.*[a-z].*$";
        String numbers = "^.*[0-9].*$";

        if (password.matches(length) && password.matches(upperCase) && password.matches(lowerCase) && password.matches(numbers)){
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidPassword2(String password, String password2) {
        Log.d(TAG, etPassword2.getText().toString());
        Log.d(TAG, inputPassword2);
        if (password.equals(password2)) {
            return true;
        } else {
            return false;
        }
    }

    public void enableNextBtn(boolean validEmail, boolean validPassword, boolean validPassword2){
        if (validEmail && validPassword && validPassword2){
            btnNext.setEnabled(true);
        } else {
            btnNext.setEnabled(false);
        }
    }
}