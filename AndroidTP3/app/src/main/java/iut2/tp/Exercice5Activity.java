package iut2.tp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import java.io.LineNumberReader;

import static iut2.tp.Exercice4Activity.HELLO_REQUEST;

public class Exercice5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On charge le XML pour créer l'arbre graphique
        setContentView(R.layout.activity_exercice5);


        NumberPicker picker = (NumberPicker) findViewById(R.id.np);
        picker.setMinValue(1);
        picker.setMaxValue(10);
    }

    public void click(View view) {

        NumberPicker table =  (NumberPicker) findViewById(R.id.np);

        int tableVal = table.getValue();

        Intent intent = new Intent(this,TableMultiplicationActivity.class);

        intent.putExtra(TableMultiplicationActivity.TABLE_KEY,tableVal);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);

    }
}
