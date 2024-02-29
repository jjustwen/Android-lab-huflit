package com.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tuan04.R;
import com.google.gson.Gson;
import com.model.User;

public class RegisterActivity extends AppCompatActivity
{
    private EditText edUserNameC;
    private EditText edPasswordC;
    private EditText edConfirmPasswordC;
    private EditText edEmailC;
    private EditText edPhoneNumberC;
    private RadioGroup rbSex;
    private Button btnRegister;
    private ImageButton imbBack;
    private SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");
        //Khai báo Share Preferences
        sharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        //Lấy dữ liệu
        anhXaData();
        createRegisterEvent();
    }

    void anhXaData()
    {
        edUserNameC = findViewById(R.id.edUserNameRe);
        edPasswordC = findViewById(R.id.edPasswordRe);
        edConfirmPasswordC = findViewById(R.id.edt_confirm_password);
        edEmailC = findViewById(R.id.edEmail);
        edPhoneNumberC = findViewById(R.id.edPhone);
        rbSex = findViewById(R.id.rgSex);
        btnRegister = findViewById(R.id.btRegister);
        imbBack = findViewById(R.id.imbBack);
    }

    void createRegisterEvent()
    {
        btnRegister.setOnClickListener(v -> registerEvent());
        imbBack.setOnClickListener(v -> finish());
    }

    void registerEvent()
    {
        String userName = edUserNameC.getText().toString().trim();
        String password = edPasswordC.getText().toString().trim();
        String confirmPassword = edConfirmPasswordC.getText().toString().trim();
        String email = edEmailC.getText().toString().trim();
        String phone = edPhoneNumberC.getText().toString().trim();
        //nếu sex = 1 là nam, sex = 0 là nữ
        int sex = 1;
        boolean isValid = checkUserName(userName) && checkPassword(password, confirmPassword);
        if (isValid)
        {
            //Nếu dữ liệu hợp lệ, tạo đối tượng User để lưu vào Share Preferences
            User newUser = new User();
            newUser.setUserName(userName);
            newUser.setPassword(password);
            newUser.setEmail(email);
            newUser.setPhoneNumber(phone);
            //Lấy redio button id đang được checked
            int sexSelected = rbSex.getCheckedRadioButtonId();
            if (sexSelected == R.id.rbFemale)
            {
                sex = 0; // nữ
            }
            newUser.setSex(sex);
            //Vì user là object nên covert qua String với format là json để lưu vào Share Preferences
            String strUser = gson.toJson(newUser);
            editor.putString(Utils.KEY_USER, strUser);
            editor.commit();
            //dùng Toast để show thông báo đăng ký thành công
            Toast.makeText(RegisterActivity.this, "Đăng ký tài khoản thành công", Toast.LENGTH_LONG).show();
            //finish RegisterActivity
            finish();
        }
    }

    //Kiểm tra dữ liệu
    boolean checkUserName(String username)
    {
        if (username.isEmpty())
        {
            edUserNameC.setError("Vui lòng nhập");
            return false;
        }
        if (username.length() <= 5)
        {
            edUserNameC.setError("tên đăng nhập phải có ít nhất 6 ký tự");
            return false;
        }
        return true;
    }

    boolean checkPassword(String password, String confirmPassword)
    {
        if (password.isEmpty())
        {
            edPasswordC.setError("Vui lòng nhập");
            return false;
        }
        if (password.length() <= 5)
        {
            edPasswordC.setError("Mật khẩu phải lớn hơn 5 ký tự");
            return false;
        }
        if (!password.equals(confirmPassword))
        {
            edConfirmPasswordC.setError("Mật khẩu không khớp");
            return false;
        }
        return true;
    }
}