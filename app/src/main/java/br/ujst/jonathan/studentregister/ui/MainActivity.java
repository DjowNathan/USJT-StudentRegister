package br.ujst.jonathan.studentregister.ui;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.ujst.jonathan.studentregister.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceFragment(R.id.frameLayoutMain,
                HomeFragment.newInstance("","")
                ,"HOMEFRAGMENT",
                "HOME");

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:
                        replaceFragment(R.id.frameLayoutMain,
                                HomeFragment.newInstance("","")
                                ,"HOMEFRAGMENT",
                                "HOME");
                        return true;

                    case R.id.contact:
                        replaceFragment(R.id.frameLayoutMain,
                                ContactFragment.newInstance("","")
                                ,"CONTATOFRAGMENT",
                                "CONTATO");
                        return true;

                    case R.id.perfil:
                        replaceFragment(R.id.frameLayoutMain,
                                PerfilFragment.newInstance("","")
                                ,"PERFILFRAGMENT",
                                "PERFIL");
                        return true;

                    case R.id.maps:
                        replaceFragment(R.id.frameLayoutMain,
                                MapsFragment.newInstance("","")
                                ,"MAPSFRAGMENT",
                                "MAPS");
                        return true;


                    case R.id.setting:
                        replaceFragment(R.id.frameLayoutMain,
                                SettingFragment.newInstance("","")
                                ,"CONFIGFRAGMENT",
                                "CONFIG");
                        return true;
                }


                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.edit:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
            case R.id.exit:
                finish();
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }

}