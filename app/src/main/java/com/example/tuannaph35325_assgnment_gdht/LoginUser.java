package com.example.tuannaph35325_assgnment_gdht;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class LoginUser extends AppCompatActivity {
    CheckBox chkRemember;
    EditText edtUserlogin;
    EditText edtPassLogin;
    ArrayList<AccountUser> listAccountLogin = new ArrayList<>();
    public static String KEY_ACCOUNT = "account.txt";

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("data", MODE_PRIVATE);

        edtUserlogin = findViewById(R.id.edt_username);
        edtPassLogin = findViewById(R.id.edt_password);

        String userNameOld = pref.getString(KEY_USER_NAME, "");
        String passOld = pref.getString(KEY_PASSWORD, "");

        edtUserlogin.setText(userNameOld);
        edtPassLogin.setText(passOld);


        chkRemember = findViewById(R.id.chk_remember);

        chkRemember.setChecked(pref.getBoolean("remember", false));



        String userName = getIntent().getStringExtra("user_name");
        String userpass = getIntent().getStringExtra("pass_word");
        if (userName != null && !userName.equals("")) {
            edtUserlogin.setText(userName);
            edtPassLogin.setText(userpass);
        }


        Button btnDk = findViewById(R.id.btn_dangky);
        btnDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginUser.this, RegisterUser.class);
                startActivity(intent);
                ;
            }
        });

        Button btnLgin = findViewById(R.id.btn_login);
        btnLgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chkUserName = edtUserlogin.getText().toString();
                String chkPassWord = edtPassLogin.getText().toString();
                if (chkUserName.length() == 0 || chkUserName.trim().equals(" ")) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhận tên của bạn !", Toast.LENGTH_SHORT).show();
                } else if (chkPassWord.length() == 0 || chkPassWord.trim().equals(" ")) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhận password của bạn !", Toast.LENGTH_SHORT).show();
                } else {

                    //readAccount();

                    String userName  = edtUserlogin.getText().toString();
                    String password = edtPassLogin.getText().toString();

                    if (chkRemember.isChecked()) {

                        SharedPreferences.Editor editor = pref.edit();

                        editor.putString(KEY_USER_NAME, userName);
                        editor.putString(KEY_PASSWORD, password);

                        editor.apply();
                    }
                }
            }
        });
        if (chkRemember.isChecked()) {
            chkRemember.setChecked(true);

        }

        chkRemember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginUser.this, "Remember", Toast.LENGTH_SHORT).show();
                String chkUserName = edtUserlogin.getText().toString();
                String chkPassWord = edtPassLogin.getText().toString();
                AccountUser user = new AccountUser(chkUserName, chkPassWord);
                listAccountLogin.add(user);
                writeAccountUser();

                pref.edit().putBoolean("remember", chkRemember.isChecked()).apply();
            }
        });
    }


    final String KEY_USER_NAME = "username";
    final String KEY_PASSWORD = "password";

    public void readAccount() {
        try {
            FileInputStream fis = openFileInput(KEY_ACCOUNT);
            ObjectInputStream ois = new ObjectInputStream(fis);

            listAccountLogin = (ArrayList<AccountUser>) ois.readObject();

            for (int i = 0; i <= listAccountLogin.size() - 1; i++) {
                if (listAccountLogin.get(i).getUserName().equals(edtUserlogin.getText().toString()) && listAccountLogin.get(i).getPassWord().equals(edtPassLogin.getText().toString())) {

                    String userName  = edtUserlogin.getText().toString();
                    String password = edtPassLogin.getText().toString();

                    if (chkRemember.isChecked()) {

                        SharedPreferences.Editor editor = pref.edit();

                        editor.putString(KEY_USER_NAME, userName);
                        editor.putString(KEY_PASSWORD, password);

                        editor.apply();
                    }

                    Intent intent = new Intent(LoginUser.this, SelectActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(this, "tài khoản hoặc mật khẩu không chính xác !!!", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeAccountUser() {
        try {
            FileOutputStream fos = openFileOutput(KEY_ACCOUNT, MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listAccountLogin);
            fos.close();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}