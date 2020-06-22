package com.unam.mismascotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MascotasFav extends AppCompatActivity{

    private ImageButton ibFlecha;
    private RecyclerView listaMascotas;
    ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
    //ArrayList<Mascota> ordenado = new ArrayList<Mascota>();

    ArrayList<Integer> fotos = new ArrayList<>();
    ArrayList<String> nombres = new ArrayList<>();
    ArrayList<Integer> likes = new ArrayList<>();

    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_fav);

        Toolbar miActionBar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.miActionBar_fav);
        setSupportActionBar(miActionBar);

        Intent intent = getIntent();

        mascotas=(ArrayList<Mascota>) intent.getSerializableExtra("mismascotas");

        int menor=100;
        int iteracion=0;
        int pos=0;

        while(mascotas.size()>5){
            if(mascotas.get(iteracion).getLikes()<menor){
                menor=mascotas.get(iteracion).getLikes();
                pos=iteracion;
            }
            if(iteracion==mascotas.size()-1){
                iteracion=0;
                mascotas.remove(pos);
                menor=100;
            }
            iteracion++;
        }

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotasFav);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);

        //Toast.makeText(MascotasFav.this, mascotas.get(0).getNombre(), Toast.LENGTH_SHORT).show();

        inicializarAdaptador();

        ibFlecha = (ImageButton) findViewById(R.id.ibFlecha);
        ibFlecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MascotasFav.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void inicializarAdaptador(){
        MascotaAdaptador ma = new MascotaAdaptador(mascotas, this);
        listaMascotas.setAdapter(ma);
    }

}