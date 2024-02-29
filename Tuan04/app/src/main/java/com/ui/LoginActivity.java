package com.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tuan04.MainActivity;
import com.example.tuan04.R;
import com.google.gson.Gson;
import com.model.User;

public class LoginActivity extends AppCompatActivity
{
    Button btLogin, btRegister;
    EditText edUserNameC, edPasswordC;
    SharedPreferences.Editor editor;
    private final Gson gson = new Gson();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");
        //
        anhXaData();
        //
        sharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        //
        createEvent();
    }

    private void anhXaData()
    {
        btLogin = findViewById(R.id.btLogin);
        btRegister = findViewById(R.id.btRegister);
        edUserNameC = findViewById(R.id.edUserName);
        edPasswordC = findViewById(R.id.edPassword);
    }

    private void createEvent()
    {
        btLogin.setOnClickListener(v -> checkUserLogin());
        btRegister.setOnClickListener(funRegister());
    }

    @NonNull
    private View.OnClickListener funRegister()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        };
    }


    private void checkUserLogin()
    {
        String userPref = sharedPreferences.getString(Utils.KEY_USER, null);
        User user = gson.fromJson(userPref, User.class);
        //user = null có nghĩa là chưa có user đăng ký
        if (user == null)
        {
            return;
        }
        //Kiểm tra username và password có trùng với đối tượng user trong share preferences không
        boolean isValid = edUserNameC.getText().toString().trim().equals(user.getUserName()) && edPasswordC.getText().toString().trim().equals(user.getPassword());
        //Nếu kết quả trùng với user đã đăng ký thì start main activity
        if (isValid)
        {
            Intent i = new Intent(this, MainActivity.class);
            //Khởi tạo bundle để truyền user data qua cho MainActivity
            Bundle bundle = new Bundle();
            //Vì user là object nên dùng putSerializable
            bundle.putSerializable(Utils.KEY_USER_PROFILE, user);
            //Hoặc có thể dùng putString nêú chỉ dùng username
            //bundle.putString(Utils.KEY_USER_NAME, user.getUserName());

            //put bundle cho intent
            i.putExtras(bundle);
            startActivity(i);
            //sau khi start main activity thì finish login activity
            finish();
        }
    }
}