package com.example.lucasftecnico.exemplosmaterialdesign;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SegundaActivity extends AppCompatActivity {
    private Toolbar tbMain, tbMainBotton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        tbMain = (Toolbar) findViewById(R.id.tbMain);
        tbMain.setTitle("SegundaActivity");
        setSupportActionBar(tbMain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            tbMain.setBackgroundResource(R.drawable.toolbar_rounded_cornes);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_segundo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }
}
