package com.example.bousquleProjet;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bousquleProjet.DB.DBClient;
import com.example.bousquleProjet.DB.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD = 0;

    private List<User> itemList = new ArrayList<User>();
    private TaskAdapter adapter;
    private ListView listUsers;
    private DBClient mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDb = DBClient.getInstance(getApplicationContext());

        listUsers = findViewById(R.id.listUsers);

        adapter = new TaskAdapter(this, itemList);
        listUsers.setAdapter(adapter);

        listUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Récupération de l'utilisateur cliqué à l'aide de l'adapter
                User user = adapter.getItem(position);

                Toast.makeText(MainActivity.this, "Let's go " + " " + user.getPseudo() + "!!", Toast.LENGTH_SHORT).show();

                jeu(view,user.getId());
            }
        });

        getItem();

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipelayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh,R.color.refresh1,R.color.refresh2);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);

                        getItem();


                    }
                },3000);
            }
        });

    }


    private void getItem() {

        class GetItem extends AsyncTask<Void, Void, List<User>> {

            @Override
            protected List<User> doInBackground(Void... voids) {
                List<User> userList = mDb.getAppDatabase()
                        .mydao()
                        .getAll();
                return userList;
            }

            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);

                // Mettre à jour l'adapter avec la liste de users
                adapter.clear();
                adapter.addAll(users);

                // Now, notify the adapter of the change in source
                adapter.notifyDataSetChanged();
            }

        }

        GetItem gt = new GetItem();
        gt.execute();


        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK) {

            // Mise à jour des users
            getItem();
        }
    }



    public void newUser(View view) {

        Intent intent = new Intent(this,AddUserActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivityForResult(intent,REQUEST_CODE_ADD);

    }


    public void jeu(View view, int id) {

        ((MyApp) this.getApplication()).setId(id);

        Intent intent = new Intent(this,JeuActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);

    }


    public void jeuAnonyme(View view) {

        Intent intent = new Intent(this,JeuActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);

    }

}

