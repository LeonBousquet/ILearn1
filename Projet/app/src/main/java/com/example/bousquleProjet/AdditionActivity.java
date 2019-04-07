package com.example.bousquleProjet;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.bousquleProjet.DB.DBClient;

import java.util.Random;

public class AdditionActivity extends AppCompatActivity {

    public static final String NOMBRE1_KEY = "nombre1_key";
    public static final String NOMBRE2_KEY = "nombre2_key";

    int nombre1;
    int nombre2;
    int nbOpe;
    int score;
    private DBClient mDb;

    final int min = 2;
    final int max = 30;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        mDb = DBClient.getInstance(getApplicationContext());

        if(((MyApp) this.getApplication()).getNbOpe() != 0) {
            Log.d("rien faire","rien faire");
        }

        else {

            ((MyApp) this.getApplication()).setNbOpe(0);
            ((MyApp) this.getApplication()).setScore(10);
        }



        final int nb1 = new Random().nextInt((max - min) + 1) + min;
        final int nb2 = new Random().nextInt((max - min) + 1) + min;

        nombre1 = getIntent().getIntExtra(NOMBRE1_KEY,nb1);
        nombre2 = getIntent().getIntExtra(NOMBRE2_KEY,nb2);


        createCalcul();
    }


    private void createCalcul() {


        TextView calcul = (TextView) findViewById(R.id.calcul);
        calcul.setText(nombre1 + " + " + nombre2 + " = ");

    }

    public void valider(View view)
    {

        TextView correction = (TextView) findViewById(R.id.correction);


        EditText resultat = (EditText) findViewById(R.id.resultat);

        resultat.setFocusable(false);

        if(resultat.getText().toString().equals(""))
        {
            resultat.setText("0");
        }

        if(Integer.parseInt(resultat.getText().toString()) != (nombre1 + nombre2)) {
            resultat.setTextColor(Color.parseColor("#FF0000"));
            correction.setText("Réponse : " + (nombre1+nombre2));
            correction.setTextColor(Color.parseColor("#008000"));
            score = ((MyApp) this.getApplication()).getScore();
            score--;
            ((MyApp) this.getApplication()).setScore(score);
        }
        else {
            resultat.setTextColor(Color.parseColor("#008000"));
            correction.setText("Félicitations !!");
            correction.setTextColor(Color.parseColor("#008000"));
        }

        if(((MyApp) this.getApplication()).getNbOpe() == 9) {

            Button choixJeu = (Button) findViewById(R.id.valider);


            choixJeu.setText("RETOUR AUX JEUX");

            score = ((MyApp) this.getApplication()).getScore();

            saveScore(score);

            correction.setText("Ton score : " + score + " /10");

            choixJeu.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),JeuActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });

        }

        else {

            nbOpe = ((MyApp) this.getApplication()).getNbOpe();
            nbOpe++;
            ((MyApp) this.getApplication()).setNbOpe(nbOpe);

            Button suivant = (Button) findViewById(R.id.valider);

            suivant.setText("SUIVANT");

            final int nombre1 = new Random().nextInt((max - min) + 1) + min;
            final int nombre2 = new Random().nextInt((max - min) + 1) + min;

            suivant.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),AdditionActivity.class);
                    intent.putExtra(AdditionActivity.NOMBRE1_KEY,nombre1);
                    intent.putExtra(AdditionActivity.NOMBRE2_KEY,nombre2);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();
                    startActivity(intent);
                }
            });


        }


    }


    private void saveScore(final int score) {

        // Récupérer les informations contenues dans les vues
        final int userId;
        userId = ((MyApp) this.getApplication()).getId();


        class SaveScore extends AsyncTask<Void, Void, Boolean> {

            @Override
            protected Boolean doInBackground(Void... voids) {

                // adding to database
                mDb.getAppDatabase()
                        .mydao()
                        .addScore(score, userId);

                return true;
            }


        }

        SaveScore st = new SaveScore();
        st.execute();
    }

    @Override
    public void onBackPressed() {
        finish();
        ((MyApp) this.getApplication()).setNbOpe(0);
    }


}
