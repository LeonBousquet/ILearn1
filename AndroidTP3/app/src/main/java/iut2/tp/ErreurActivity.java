package iut2.tp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ErreurActivity extends AppCompatActivity {

    public static final String ERREURS_KEY = "erreurs_key";
    public static final String FALSE_ANS = "false_ans";
    public static final String TABLE_NUM = "table_num";

    int table = 0;

    TextView nb;
    ArrayList<Integer> wrongAns = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erreur);

        nb= (TextView) findViewById(R.id.nb);

        table = getIntent().getIntExtra(TABLE_NUM,0);

        int nbErreurs = getIntent().getIntExtra(ERREURS_KEY,0);

        wrongAns = getIntent().getIntegerArrayListExtra("FALSE_ANS");

        nb.setText("Nombre d'erreurs :" + " " + nbErreurs);
    }

    public void autreTable(View view) {

        Intent intentautreTable = new Intent(this,Exercice5Activity.class);

        intentautreTable.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intentautreTable);

    }

    public void corriger(View view) {

        Intent intentCorriger = new Intent(this,TableMultiplicationActivity.class);

        intentCorriger.putExtra(TableMultiplicationActivity.TABLE_KEY,table);
        intentCorriger.putIntegerArrayListExtra(TableMultiplicationActivity.MAUV_REP,(ArrayList<Integer>)wrongAns);

        intentCorriger.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intentCorriger);

    }


}
