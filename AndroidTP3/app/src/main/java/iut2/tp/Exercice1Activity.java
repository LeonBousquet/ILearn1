package iut2.tp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

public class Exercice1Activity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On charge le XML pour créer l'arbre graphique
        setContentView(R.layout.activity_exercice1);


    }

    public void click(View view) {
        TextView prenom = (TextView) findViewById(R.id.reponse);
        TextView hello = (TextView) findViewById(R.id.affichage);

        if (!TextUtils.isEmpty(prenom.getText())) {
            hello.setText("Hello " + prenom.getText() + " !");
        }


    }
}
