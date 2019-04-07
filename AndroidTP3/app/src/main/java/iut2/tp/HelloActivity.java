package iut2.tp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HelloActivity extends AppCompatActivity {

    public static final String PRENOM_KEY = "prenom_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        String prenom = getIntent().getStringExtra(PRENOM_KEY);

        TextView message = (TextView) findViewById(R.id.helloM);

        message.setText("Hello" + " " + prenom);
    }

    public void click(View view) {

        setResult(RESULT_OK);
        super.finish();
    }

    public void click2(View view) {

        Intent intent =  new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}

