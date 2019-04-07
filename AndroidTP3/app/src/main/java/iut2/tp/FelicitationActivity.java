package iut2.tp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FelicitationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_felicitation);
    }


    public void autreTable(View view) {

        Intent intentautreTable = new Intent(this,Exercice5Activity.class);


        intentautreTable.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        startActivity(intentautreTable);

    }

    public void autreExo(View view) {

        Intent intentautreExo = new Intent(this,MainActivity.class);

        intentautreExo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intentautreExo);

    }


}
