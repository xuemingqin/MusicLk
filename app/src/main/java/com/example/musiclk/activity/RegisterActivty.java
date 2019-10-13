package com.example.musiclk.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musiclk.R;
import com.example.musiclk.util.MD5Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivty extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar progressBar;
    private TextInputEditText name, email, password;
    private FloatingActionButton floatingActionButton;
    private Button btn_register;
    private TextView webline;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        hideSoftKeyboard(this);
       progressBar = findViewById(R.id.progressbar);
        password = findViewById(R.id.password);
        name = findViewById(R.id.username);
        email = findViewById(R.id.email);
        btn_register = findViewById(R.id.btn_register);
        floatingActionButton = findViewById(R.id.btn_fioating);
        webline=findViewById(R.id.webline);

        name.setOnClickListener(this);
        password.setOnClickListener(this);
        email.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);
        webline.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String username = name.getText().toString().trim();
        String e_mail = email.getText().toString().trim();
        String mpassword = password.getText().toString().trim();
        switch (view.getId()) {
            case R.id.email:
                    if (!isEmail(e_mail)){
                    email.setError("请输入正确的邮箱");
                }
                return;
            case R.id.username:
                if (TextUtils.isEmpty(username)){
                    name.setError("昵称不能为空");
                }
                return;
            case R.id.password:
                MD5Utils.MD5(mpassword);
                if (!validatePassword(mpassword)) {
                    password.setError("密码长度需为8~16");
                }
                return;
            case R.id.btn_register:
                if (username.equals("abcde") && e_mail.equals("1208280008@qq.com")&& mpassword.equals("12345")){
                    Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText( this, "登入失败", Toast.LENGTH_SHORT ).show( );
                }
                break;

            case R.id.btn_fioating:
                break;
            case R.id.webline:
                Intent intent =new Intent(this, WebViewActivity.class);
                intent.putExtra("url","https://m.moretickets.com/topic/yinyuejie/");
                startActivity(intent);
                break;
        }
    }


    public boolean validatePassword(String password) {
        return password.length() > 5 && password.length() <= 18;
    }




    public  boolean isEmail(String strEmail) {
        String strPattern  ="^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();

    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            //隐藏键盘
            hideSoftKeyboard(this);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        View view = this.getCurrentFocus();
        if (view != null) {
            hideSoftKeyboard(this);
            return true;
        } else {
            return super.onTouchEvent(event);
        }
    }

    /**
     * 隐藏软键盘(只适用于Activity，不适用于Fragment)
     */
    public void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService( Activity.INPUT_METHOD_SERVICE );
            inputMethodManager.hideSoftInputFromWindow ( view.getWindowToken ( ), InputMethodManager.HIDE_NOT_ALWAYS );
        }
    }
}
