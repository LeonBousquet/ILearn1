package iut2.tp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

public class Exercice2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On charge le XML pour créer l'arbre graphique
        setContentView(R.layout.activity_exercice2);
    }

    public void click(View view) {
        TextView rep = (TextView) findViewById(R.id.message);
        RadioGroup g = (RadioGroup) findViewById(R.id.group);

        if(R.id.exercice2_bonne_reponse == g.getCheckedRadioButtonId()) {
            rep.setText("Bravo vous avez la bonne réponse !");
        }


    }
}
