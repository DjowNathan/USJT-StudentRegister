package br.ujst.jonathan.studentregister.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import br.ujst.jonathan.studentregister.R;
import br.ujst.jonathan.studentregister.model.User;
import br.ujst.jonathan.studentregister.model.UserViewModel;

public class RegisterActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private User userCorrente;
    private EditText editTextName;
    private EditText editTextNameHero;
    private EditText editTextCPF;
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Hawk.init(this).build();

        editTextName = findViewById(R.id.editTextName);
        editTextNameHero = findViewById(R.id.editTextNameHero);
        editTextCPF = findViewById(R.id.editTextCpf);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable final User user) {
                updateView(user);
            }
        });
    }

    private void updateView(User user){
        if(user != null && user.getId() > 0){
            userCorrente = user;
            editTextName.setText(user.getName());
            editTextNameHero.setText(user.getNameHero());
            editTextCPF.setText(user.getCpf());
            editTextEmail.setText(user.getEmail());
            editTextPassword.setText(user.getPassword());
        }
    }


    public void save(View view) {

        if(userCorrente == null){
            userCorrente = new User();
        }
        userCorrente.setName(editTextName.getText().toString());
        userCorrente.setNameHero(editTextNameHero.getText().toString());
        userCorrente.setCpf(editTextCPF.getText().toString());
        userCorrente.setEmail(editTextEmail.getText().toString());
        userCorrente.setPassword(editTextPassword.getText().toString());
        userViewModel.insert(userCorrente);
        Toast.makeText(this,"Usuário salvo com sucesso",
                Toast.LENGTH_SHORT).show();
        Hawk.put("has_register",true);
        finish();
    }
}