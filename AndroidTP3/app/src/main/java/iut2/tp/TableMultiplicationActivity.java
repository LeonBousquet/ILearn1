package iut2.tp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static iut2.tp.Exercice4Activity.HELLO_REQUEST;


public class TableMultiplicationActivity extends AppCompatActivity {

    public static final String TABLE_KEY = "table_key";
    public static final String MAUV_REP = "mauv_rep";
    ArrayList<Integer> arrayReponse = new ArrayList<Integer>();
    int nbErr = 0;
    ArrayList<Integer> repFausses = new ArrayList<Integer>();
    ArrayList<Integer> mauvRep = new ArrayList<Integer>();

    int table = 0;

    TextView q1;
    TextView q2;
    TextView q3;
    TextView q4;
    TextView q5;
    TextView q6;
    TextView q7;
    TextView q8;
    TextView q9;
    TextView q10;

    EditText rep1;
    EditText rep2;
    EditText rep3;
    EditText rep4;
    EditText rep5;
    EditText rep6;
    EditText rep7;
    EditText rep8;
    EditText rep9;
    EditText rep10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_multiplication);

        mauvRep = getIntent().getIntegerArrayListExtra("mauv_rep");


        table = getIntent().getIntExtra(TABLE_KEY,0);

        q1= (TextView) findViewById(R.id.ans1);
        q2= (TextView) findViewById(R.id.ans2);
        q3= (TextView) findViewById(R.id.ans3);
        q4= (TextView) findViewById(R.id.ans4);
        q5= (TextView) findViewById(R.id.ans5);
        q6= (TextView) findViewById(R.id.ans6);
        q7= (TextView) findViewById(R.id.ans7);
        q8= (TextView) findViewById(R.id.ans8);
        q9= (TextView) findViewById(R.id.ans9);
        q10= (TextView) findViewById(R.id.ans10);


        q1.setText("1  X  " + table + "  = ");
        q2.setText("2  X  " + table + "  = ");
        q3.setText("3  X  " + table + "  = ");
        q4.setText("4  X  " + table + "  = ");
        q5.setText("5  X  " + table + "  = ");
        q6.setText("6  X  " + table + "  = ");
        q7.setText("7  X  " + table + "  = ");
        q8.setText("8  X  " + table + "  = ");
        q9.setText("9  X  " + table + "  = ");
        q10.setText("10  X  " + table + "  = ");

        rep1 = findViewById(R.id.rep1);
        rep2 = findViewById(R.id.rep2);
        rep3 = findViewById(R.id.rep3);
        rep4 = findViewById(R.id.rep4);
        rep5 = findViewById(R.id.rep5);
        rep6 = findViewById(R.id.rep6);
        rep7 = findViewById(R.id.rep7);
        rep8 = findViewById(R.id.rep8);
        rep9 = findViewById(R.id.rep9);
        rep10 = findViewById(R.id.rep10);

        if(mauvRep != null) {
            for(int i=0;i<mauvRep.size();i++) {
                switch(mauvRep.get(i)) {
                    case 0: rep1.setBackgroundColor(Color.RED);
                        break;
                    case 1: rep2.setBackgroundColor(Color.RED);
                        break;
                    case 2: rep3.setBackgroundColor(Color.RED);
                        break;
                    case 3: rep4.setBackgroundColor(Color.RED);
                        break;
                    case 4: rep5.setBackgroundColor(Color.RED);
                        break;
                    case 5: rep6.setBackgroundColor(Color.RED);
                        break;
                    case 6: rep7.setBackgroundColor(Color.RED);
                        break;
                    case 7: rep8.setBackgroundColor(Color.RED);
                        break;
                    case 8: rep9.setBackgroundColor(Color.RED);
                        break;
                    case 9: rep10.setBackgroundColor(Color.RED);
                        break;
                }
            }
        }


    }

    public void click(View view) {


        if(!rep1.getText().toString().equals("")) {
            int r1 = Integer.parseInt(rep1.getText().toString());
            arrayReponse.add(r1);
        }
        else {
            arrayReponse.add(0);
        }



        if(!rep2.getText().toString().equals("")) {
            int r2 = Integer.parseInt(rep2.getText().toString());
            arrayReponse.add(r2);
        }
        else {
            arrayReponse.add(0);
        }



        if(!rep3.getText().toString().equals("")) {
            int r3 = Integer.parseInt(rep3.getText().toString());
            arrayReponse.add(r3);
        }
        else {
            arrayReponse.add(0);
        }



        if(!rep4.getText().toString().equals("")) {
            int r4 = Integer.parseInt(rep4.getText().toString());
            arrayReponse.add(r4);
        }
        else {
            arrayReponse.add(0);
        }



        if(!rep5.getText().toString().equals("")) {
            int r5 = Integer.parseInt(rep5.getText().toString());
            arrayReponse.add(r5);
        }
        else {
            arrayReponse.add(0);
        }



        if(!rep6.getText().toString().equals("")) {
            int r6 = Integer.parseInt(rep6.getText().toString());
            arrayReponse.add(r6);
        }
        else {
            arrayReponse.add(0);
        }



        if(!rep7.getText().toString().equals("")) {
            int r7 = Integer.parseInt(rep7.getText().toString());
            arrayReponse.add(r7);
        }
        else {
            arrayReponse.add(0);
        }



        if(!rep8.getText().toString().equals("")) {
            int r8 = Integer.parseInt(rep8.getText().toString());
            arrayReponse.add(r8);
        }
        else {
            arrayReponse.add(0);
        }



        if(!rep9.getText().toString().equals("")) {
            int r9 = Integer.parseInt(rep9.getText().toString());
            arrayReponse.add(r9);
        }
        else {
            arrayReponse.add(0);
        }



        if(!rep10.getText().toString().equals("")) {
            int r10 = Integer.parseInt(rep10.getText().toString());
            arrayReponse.add(r10);
        }
        else {
            arrayReponse.add(0);
        }


        for (int i = 0; i < 10; i++) {
            if (arrayReponse.get(i) != (table * i + 1)) {
                repFausses.add(i);
                nbErr++;
            }
        }


        if(nbErr > 0) {

            Intent intentErreurs = new Intent(this,ErreurActivity.class);

            intentErreurs.putExtra(ErreurActivity.ERREURS_KEY,nbErr);
            intentErreurs.putIntegerArrayListExtra(ErreurActivity.FALSE_ANS,(ArrayList<Integer>)repFausses);
            intentErreurs.putExtra(ErreurActivity.TABLE_NUM,table);

            intentErreurs.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intentErreurs);

        }

        else {
            Intent intentFelicitations = new Intent(this,FelicitationActivity.class);
            intentFelicitations.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intentFelicitations);
        }



    }



}
