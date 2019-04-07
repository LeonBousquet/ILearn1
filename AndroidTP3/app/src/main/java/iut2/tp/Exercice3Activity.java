package iut2.tp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.text.TextUtils;
import android.widget.*;

import org.w3c.dom.Text;

import java.util.Random;

public class Exercice3Activity extends AppCompatActivity {

    int nb_def = 0;
    int nb_vic = 0;
    int nb_eg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On charge le XML pour créer l'arbre graphique
        setContentView(R.layout.activity_exercice3);
    }

    public void click(View view) {
        int image_id;
        ImageView repOrdi = (ImageView) findViewById(R.id.choixOrdi);
        TextView result = (TextView) findViewById(R.id.resultat);
        TextView victoire = (TextView) findViewById(R.id.victoires);
        TextView defaite = (TextView) findViewById(R.id.defaites);
        TextView egalité = (TextView) findViewById(R.id.egalités);



        int CAILLOUX = 0;
       int CISEAUX = 1;
       int PAPIER = 2;
        Random rand = new Random();
        int mainOrdinateur = rand.nextInt(3);

        if (mainOrdinateur == 1) {
            repOrdi.setImageResource(R.drawable.ciseaux);
        }
        else if(mainOrdinateur == 2) {
            repOrdi.setImageResource(R.drawable.papier);
        }
        else {
            repOrdi.setImageResource(R.drawable.caillou);
        }

        ImageView rep = (ImageView) findViewById(view.getId());

        switch(view.getId()){
            case R.id.caillou :
                if (mainOrdinateur == 1) {
                    result.setText("Victoire !");
                    nb_vic = nb_vic +1;
                    victoire.setText("Nombre de victoires: " + nb_vic);
                }
                else if(mainOrdinateur == 0) {
                    result.setText("Egalité !");
                    nb_eg = nb_eg +1;
                    egalité.setText("Nombre d'égalités: " +nb_eg);
                }
                else {
                    result.setText("Défaite !");
                    nb_def = nb_def +1;
                    defaite.setText("Nombre de défaites: " +nb_def);
                }
                rep.setBackgroundColor(5);
                break;
            case R.id.papier :
                if (mainOrdinateur == 0) {
                    result.setText("Victoire !");
                    nb_vic = nb_vic +1;
                    victoire.setText("Nombre de victoires: " +nb_vic);
                }
                else if(mainOrdinateur == 2) {
                    result.setText("Egalité !");
                    nb_eg = nb_eg +1;
                    egalité.setText("Nombre d'égalités: " +nb_eg);
                }
                else {
                    result.setText("Défaite !");
                    nb_def = nb_def +1;
                    defaite.setText("Nombre de défaites: " +nb_def);
                }
                rep.setBackgroundColor(5);
                break;
            case R.id.ciseaux :
                if (mainOrdinateur == 2) {
                    result.setText("Victoire !");
                    nb_vic = nb_vic +1;
                    victoire.setText("Nombre de victoires: " +nb_vic);
                }
                else if(mainOrdinateur == 1) {
                    result.setText("Egalité !");
                    nb_eg = nb_eg +1;
                    egalité.setText("Nombre d'égalités: " +nb_eg);
                }
                else {
                    result.setText("Défaite !");
                    nb_def = nb_def +1;
                    defaite.setText("Nombre de défaites: " +nb_def);
                }
                rep.setBackgroundColor(5);
                break;
        }


    }

}
