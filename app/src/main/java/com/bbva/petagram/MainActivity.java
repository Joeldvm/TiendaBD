package com.bbva.petagram;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class MainActivity extends AppCompatActivity {

    //ArrayList<ListaPets> lista = new ArrayList<ListaPets>();
    private RecyclerView lista_pets;
    ImageButton likeBut;

    Toolbar miActionBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        this.setActionBar(miActionBar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ponViewPager();

        likeBut = (ImageButton) findViewById(R.id.ibot);

        likeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Toy en la clase irtoppet",Toast.LENGTH_SHORT).show();
                //irTop5pets();
                Intent llamado;
                llamado = new Intent(MainActivity.this,top5Pets.class);
                //llamado.putExtra("PerroT","Perro");
                startActivity(llamado);
            }
        });


        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menucontacto:
                Intent intent = new Intent(this, ActivityContacto.class);
                startActivity(intent);
                break;

            case R.id.menuacerca:
                Intent intent1 = new Intent(this, ActivityAcercade.class);
                startActivity(intent1);
                break;

        }
        return super.onOptionsItemSelected(item);
    }



    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new fragment_recview());
        fragments.add(new Fragment_perfil());

        return fragments;
    }

    private void  ponViewPager(){
        viewPager.setAdapter(new pageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_tienda_background);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_fotos_background);
    }

    public void irTop5pets(){
        //Toast.makeText(this,"Toy en la clase irtoppet",Toast.LENGTH_SHORT).show();
        Intent intent;
        intent = new Intent(MainActivity.this,top5Pets.class);
        startActivity(intent);


    }


}