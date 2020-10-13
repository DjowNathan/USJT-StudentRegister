package br.ujst.jonathan.studentregister.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import br.ujst.jonathan.studentregister.R;
import br.ujst.jonathan.studentregister.model.User;
import br.ujst.jonathan.studentregister.model.UserViewModel;

public class LoginActivity extends AppCompatActivity {

    private TextView textViewNewRegister;
    private Button buttonLogin;
    private UserViewModel userViewModel;
    private User userCorrente;
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Hawk.init(this).build();

        editTextEmail = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);

        textViewNewRegister = findViewById(R.id.textViewNewRegister);
        buttonLogin = findViewById(R.id.buttonLogin);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable final User user) {
                updateUser(user);
            }
        });
    }

    private void updateUser(User user){
        userCorrente = user;
    }

    @Override
    public void onResume(){
        super.onResume();
        if(Hawk.contains("has_register")){
            if(Hawk.get("has_register")){
                enableLogin();
            }else{
                disableLogin();
            }

        }else{
            disableLogin();
        }
    }

    private void enableLogin(){
        textViewNewRegister.setVisibility(View.GONE);
        buttonLogin.setEnabled(true);
        buttonLogin.setBackgroundColor(getColor(R.color.colorPrimary));
    }

    private void disableLogin(){
        textViewNewRegister.setVisibility(View.VISIBLE);
        buttonLogin.setEnabled(false);
        buttonLogin.setBackgroundColor(getColor(R.color.colorGray));
    }

    public void newRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view) {

        if(userCorrente != null){
            if(userCorrente.getEmail().equalsIgnoreCase(editTextEmail.getText().toString())
                    && userCorrente.getPassword().equals(editTextPassword.getText().toString())){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Acesso invalido!",Toast.LENGTH_SHORT).show();
            }
        }


    }
}