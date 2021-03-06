package com.example.animsplashdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BureauPiprew extends AppCompatActivity implements TvShowListener{
    private Button butonadd;
    private ImageView bac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bureau_piprew);

        butonadd = (Button) findViewById(R.id.buttonadtowatchlist);
        RecyclerView tvShowRecyclerView = findViewById(R.id.tvshowrecyclerview);
        bac = (ImageView) findViewById(R.id.back);


        List<TvShow> tvShows = new ArrayList<>();

        TvShow the100 = new TvShow();
        the100.imanges = R.drawable.splash;
        the100.name = "Ouanaminthe";
        the100.rating = 5f;
        the100.createdBy = "Rue St-Pierre prolongée, bureau de l'ancienne Douane";
        the100.story = "Office Assurance Véhicules Contre-Tiers (OAVCT) - Haiti";
        tvShows.add(the100);

        TvShow lostInspaces = new TvShow();
        lostInspaces.imanges = R.drawable.vc;
        lostInspaces.name = "Ouest";
        lostInspaces.rating = 4.5f;
        lostInspaces.createdBy = "Tabarre, Boulevard du 15 Octobre";
        lostInspaces.story = "Office Assurance Véhicules Contre-Tiers (OAVCT) - Haiti ";
        tvShows.add(lostInspaces);


        TvShow vikings = new TvShow();
        vikings.imanges = R.drawable.splash;
        vikings.name = "Artibonite";
        vikings.rating = 4.5f;
        vikings.createdBy = "Rue Jean jacques Demangles,Les Gonaives,Haiti";
        vikings.story = "Office Assurance Véhicules Contre-Tiers (OAVCT) - Haiti ";
        tvShows.add(vikings);

        TvShow a = new TvShow();
        a.imanges = R.drawable.splash;
       a.name = "Ouest";
       a.rating = 4.5f;
       a.createdBy = "Ave Charles Summer";
       a.story = "Office Assurance Véhicules Contre-Tiers (OAVCT) - Haiti ";
        tvShows.add(a);

        TvShow b = new TvShow();
        b.imanges = R.drawable.splash;
        b.name = "Jacmel";
        b.rating = 4.5f;
        b.createdBy = "41, rue Normande , Jacmel";
        b.story = "Office Assurance Véhicules Contre-Tiers (OAVCT) - Haiti ";
        tvShows.add(b);

        TvShow c = new TvShow();
        c.imanges = R.drawable.splash;
        c.name = "Centre";
        c.rating = 4.5f;
        c.createdBy = "Complexe administratif de Hinche, rue des Lattes , Hinche";
        c.story = "Office Assurance Véhicules Contre-Tiers (OAVCT) - Haiti ";
        tvShows.add(c);

        TvShow m = new TvShow();
        m.imanges = R.drawable.jacmel;
        m.name = "Artibonite";
        m.rating = 4.5f;
        m.createdBy = "Saint-Marc, Haiti";
        m.story = "Office Assurance Véhicules Contre-Tiers (OAVCT) - Haiti ";
        tvShows.add(m);

        TvShow é = new TvShow();
        é.imanges = R.drawable.loc;
        é.name = "Nord-est";
        é.rating = 4.5f;
        é.createdBy = "212, rue du Quai prolongée , Fort Liberté (Nord-Est)";
        é.story = "Office Assurance Véhicules Contre-Tiers (OAVCT) - Haiti ";
        tvShows.add(é);

        TvShow k = new TvShow();
        k.imanges = R.drawable.oavct_tabarre_2;
        k.name = "Sud";
        k.rating = 4.5f;
        k.createdBy = "7, Rue Du Quai, Complexe Administrati";
        k.story = "Office Assurance Véhicules Contre-Tiers (OAVCT) - Haiti ";
        tvShows.add(k);

        TvShow r = new TvShow();
        r.imanges = R.drawable.miragouanne;
       r.name = "Nord-ouest";
       r.rating = 4.5f;
       r.createdBy = "150 Bis, Rue Capois-la-Mort";
       r.story = "Office Assurance Véhicules Contre-Tiers (OAVCT) - Haiti ";
        tvShows.add(r);

        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Intent vhome = new Intent(BureauPiprew.this, Home.class);
                startActivity(vhome);
                finish();
            }
        });

        final TvShowAdapter tvShowAdapter = new TvShowAdapter(tvShows, this);
        tvShowRecyclerView.setAdapter(tvShowAdapter);

        butonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<TvShow> selectedTvShow = tvShowAdapter.getSelectedTvShow();

                StringBuilder tvShowName  = new StringBuilder();

                for (int i = 0; i < selectedTvShow.size(); i++){

                    if (i == 0){

                        tvShowName.append(selectedTvShow.get(i).name);
                    }else {

                        tvShowName.append("\n").append(selectedTvShow.get(i).name);
                    }
                }

                Toast.makeText(BureauPiprew.this, tvShowName.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onTvShowAction(Boolean isSelected) {
        if (isSelected){

           butonadd.setVisibility(View.VISIBLE);

        }else {

            butonadd.setVisibility(View.GONE);
        }

    }
}