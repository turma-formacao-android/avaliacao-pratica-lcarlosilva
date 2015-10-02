package com.example.administrador.myapplication.controller.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.model.entities.User;
import com.example.administrador.myapplication.model.persistence.contract.ClientContract;
import com.example.administrador.myapplication.model.persistence.contract.UserContract;
import com.example.administrador.myapplication.util.FormHelper;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private List<User> usersList;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usersList = User.getAll();
        user = usersList.get(0);
        criarBotaoLogin();
    }

    private void criarBotaoLogin() {
        Button botaoLogin = (Button) findViewById(R.id.btnlogin);
        botaoLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText editTextUserName = (EditText) findViewById(R.id.editTextUserName);
                EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
                if(FormHelper.requireValidate(LoginActivity.this, editTextPassword, editTextUserName)){
                    if(editTextUserName.getText().toString().equals(user.getNameUser()) && editTextPassword.getText().toString().equals(user.getPasswordUser())){
                        Intent formRegisterContacts = new Intent(LoginActivity.this, ClientListActivity.class);
                        startActivity(formRegisterContacts);
                    } else {
                        Toast message = Toast.makeText(getApplicationContext(), "Usuario ou Senha estao incorretos!",Toast.LENGTH_SHORT);
                        message.show();
                    }
                }
            }
        });
    }

    public static User bindLoginUser(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            User user = new User();
            user.setIdUser(cursor.getLong(cursor.getColumnIndex(UserContract.USER_ID)));
            user.setNameUser(cursor.getString(cursor.getColumnIndex(UserContract.USER_NAME)));
            user.setPasswordUser(cursor.getString(cursor.getColumnIndex(UserContract.USER_PASSWORD)));
            return user;
        }
        return null;
    }

    public static List<User> bindListUser(Cursor cursor) {
        List<User> user = new ArrayList<>();
        while (cursor.moveToNext()) {
            user.add(bindLoginUser(cursor));
        }
        return user;
    }
}
