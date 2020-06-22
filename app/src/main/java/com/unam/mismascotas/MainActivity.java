package com.unam.mismascotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listaMascotas;
    ArrayList<Mascota> mascotas;
    private ImageButton favoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar miActionBar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
        inicializarListaMascotas();
        inicializarAdaptador();

        favoritos = (ImageButton) findViewById(R.id.ibFavoritos);

        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, likes.get(0).toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (MainActivity.this, MascotasFav.class);
                intent.putExtra("mismascotas", (Serializable) mascotas);
                startActivity(intent);
            }
        });
    }

    public void inicializarAdaptador(){
        MascotaAdaptador ma = new MascotaAdaptador(mascotas, this);
        listaMascotas.setAdapter(ma);
    }

    public void inicializarListaMascotas(){
        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota(R.drawable.perro1, "Tobby"));
        mascotas.add(new Mascota(R.drawable.perro2, "Chimenea"));
        mascotas.add(new Mascota(R.drawable.perro3, "Mimzy"));
        mascotas.add(new Mascota(R.drawable.perro4, "Bobby"));
        mascotas.add(new Mascota(R.drawable.perro5, "Lucky"));
        mascotas.add(new Mascota(R.drawable.perro6, "Taco"));
        mascotas.add(new Mascota(R.drawable.perro7, "Violet"));
    }

}