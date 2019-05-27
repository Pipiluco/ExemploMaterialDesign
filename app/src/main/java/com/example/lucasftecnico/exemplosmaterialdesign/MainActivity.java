package com.example.lucasftecnico.exemplosmaterialdesign;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lucasftecnico.exemplosmaterialdesign.fragments.CarFragment;
import com.example.lucasftecnico.exemplosmaterialdesign.models.Car;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar tbMain, tbMainBotton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbMain = (Toolbar) findViewById(R.id.tbMain);
        tbMain.setTitle("MainActivity");
        tbMain.setSubtitle("Subtítulo");
        tbMain.setLogo(R.drawable.ic_launcher_foreground);
        setSupportActionBar(tbMain);

        tbMainBotton = (Toolbar) findViewById(R.id.incTbMaimButton);
        tbMainBotton.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = null;

                switch (menuItem.getItemId()) {
                    case R.id.itFacebook:
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.facebook.com"));
                        break;
                    case R.id.itYoutube:
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.youtube.com"));
                        break;
                    case R.id.itGooglePlus:
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://plus.google.com"));
                        break;
                    case R.id.itLinkedin:
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.linkedin.com"));
                        break;
                    case R.id.itWhatsApp:
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.whatsapp.com"));
                        break;
                }
                startActivity(intent);

                return true;
            }
        });
        tbMainBotton.inflateMenu(R.menu.menu_main_button);

        tbMainBotton.findViewById(R.id.ivSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Settings clicado!", Toast.LENGTH_SHORT).show();
            }
        });

        // Fragment
        CarFragment fragment = (CarFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
        if (fragment == null) {
            fragment = new CarFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.rlFragmentContainer, fragment, "mainFrag");
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_second_activity) {
            startActivity(new Intent(this, SegundaActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public List<Car> getSetCarList(int qtd) {
        String[] models = new String[]{"Gallardo", "Vyron", "Corvette", "Pagani Zonda", "Porsche 911 Carrera", "BMW 720i", "DB77", "Mustang", "Camaro", "CT6"};
        String[] brands = new String[]{"Lamborghini", " bugatti", "Chevrolet", "Pagani", "Porsche", "BMW", "Aston Martin", "Ford", "Chevrolet", "Cadillac"};
        int[] photos = new int[]{R.drawable.gallardo, R.drawable.vyron, R.drawable.corvette, R.drawable.paganni_zonda, R.drawable.porsche_911, R.drawable.bmw_720, R.drawable.db77, R.drawable.mustang, R.drawable.camaro, R.drawable.ct6};
        List<Car> listAux = new ArrayList<>();

        for (int i = 0; i < qtd; i++) {
            Car c = new Car(models[i % models.length], brands[i % brands.length], photos[i % models.length]);
            listAux.add(c);
        }
        return (listAux);
    }
}
