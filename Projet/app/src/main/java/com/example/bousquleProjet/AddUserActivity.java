package com.example.bousquleProjet;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bousquleProjet.DB.DBClient;
import com.example.bousquleProjet.DB.User;

public class AddUserActivity extends AppCompatActivity {

    // DATA
    private DBClient mDb;

    // VIEW
    private EditText pseudo;
    private NumberPicker age;
    private RadioGroup sexe;
    private ImageView save;
    private RadioButton garçon;
    private RadioButton fille;

    static final int CAM_REQUEST = 1;
    private int STORAGE_PERMISSION_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        // Récupération du DatabaseClient
        mDb = DBClient.getInstance(getApplicationContext());

        // Récupérer les vues
        pseudo = findViewById(R.id.pseudo_id);
        age = findViewById(R.id.age);
        sexe = findViewById(R.id.sexe);
        garçon = findViewById(R.id.garcon);
        garçon.setId(R.id.garcon);
        fille = findViewById(R.id.fille);
        fille.setId(R.id.fille);
        save = findViewById(R.id.save);

        age.setMinValue(6);
        age.setMaxValue(11);

        // Associer un événement au bouton save
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser();
            }
        });
    }

    public void takePic(View view) {

        if(ContextCompat.checkSelfPermission(AddUserActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission déjà donnée", Toast.LENGTH_SHORT).show();
        } else {
            requestStoragePermissions();
        }


    }

    public void choosePic(View view) {

        if(ContextCompat.checkSelfPermission(AddUserActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission déjà donnée", Toast.LENGTH_SHORT).show();
        } else {
            requestStoragePermissions();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==CAM_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            save.setImageBitmap(imageBitmap);
        }

    }

    private void requestStoragePermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this).setTitle("Demande de permission").setMessage("Cette permission est nécessaire pour accéder à vos photos").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(AddUserActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

                }
            })
                    .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == STORAGE_PERMISSION_CODE) {
            if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,"Permission accordée",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission non  accordée", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveUser() {

        // Récupérer les informations contenues dans les vues
        final String pseudoValue = pseudo.getText().toString().trim();
        final int ageValue = age.getValue();
        final int sexeValue = sexe.getCheckedRadioButtonId();

        // Vérifier les informations fournies par l'utilisateur
        if (pseudoValue.isEmpty()) {
            pseudo.setError("Obligatoire");
            pseudo.requestFocus();
            return;
        }


        class SaveUser extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {

                // creating a user
                User user = new User();
                user.setPseudo(pseudoValue);
                user.setAge(ageValue);


                if(sexeValue == R.id.garcon) {
                    user.setSexe("garçon");
                    user.setPhoto(R.drawable.homme);
                }
                else {
                    user.setSexe("fille");
                    user.setPhoto(R.drawable.femme);
                }

                // adding to database
                mDb.getAppDatabase()
                        .mydao()
                        .addUser(user);

                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);

                setResult(RESULT_OK);
                finish();
                Toast.makeText(getApplicationContext(), "Profil créé", Toast.LENGTH_LONG).show();
            }
        }

        SaveUser st = new SaveUser();
        st.execute();
    }

}


