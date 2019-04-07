package iut2.tp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Exercice4Activity extends AppCompatActivity {

    public final static int HELLO_REQUEST = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On charge le XML pour créer l'arbre graphique
        setContentView(R.layout.activity_exercice4);
    }

    public void click(View view) {

        EditText prenomEdit =  (EditText) findViewById(R.id.nom);

        String prenom = prenomEdit.getText().toString();

        Intent intent = new Intent(this,HelloActivity.class);

        intent.putExtra(HelloActivity.PRENOM_KEY,prenom);

        startActivityForResult(intent,HELLO_REQUEST);

    }

    protected  void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == HELLO_REQUEST) {

            if(resultCode == RESULT_OK) {

                Toast.makeText(this, "Retour OK", Toast.LENGTH_SHORT).show();
            }

            else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this,"Retour cancel/back", Toast.LENGTH_SHORT).show();
            }
        }


    }


}
