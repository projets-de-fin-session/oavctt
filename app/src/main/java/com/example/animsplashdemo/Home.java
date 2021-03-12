package com.example.animsplashdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {
    private Toolbar mainToolbar;
    private FirebaseAuth mAuth;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);

        drawerLayout = findViewById(R.id.drwer);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,mainToolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        mAuth = FirebaseAuth.getInstance();
        mainToolbar = (Toolbar) findViewById(R.id.boll);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle("");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.fulname:
                        Intent myintent = new Intent(Intent.ACTION_SENDTO);
                        myintent.setType("text/plain");
                        String shareBody = "your body here";
                        String shareSub = "your sub here";
                        myintent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                        myintent.putExtra(Intent.EXTRA_TEXT,shareBody);
                        startActivity(Intent.createChooser(myintent,"Share using"));
                        break;

                    case R.id.home:
                        Intent intent = new Intent(Home.this, Home.class);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.service:
                        Intent service = new Intent(Home.this, Services.class);
                        startActivity(service);
                        finish();
                        break;

                    case R.id.feedback:
                        Intent instant = new Intent(Home.this, FeedBack.class);
                        startActivity(instant);
                        finish();
                        break;

                    case R.id.profile:
                        Intent intend = new Intent(Home.this, Profile.class);
                        startActivity(intend);
                        finish();
                        break;

                    case R.id.bureau:
                        Intent intenda = new Intent(Home.this, BureauPiprew.class);
                        startActivity(intenda);
                        finish();
                        break;

                    case R.id.logout:
                        logout();
                        break;

                    case R.id.exit:
                        Toast.makeText(Home.this,"exit",Toast.LENGTH_LONG).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                        builder.setMessage("Do you want to exit ?");
                        builder.setTitle(Html.fromHtml("<font color='#509324>Success</font>"));
                        builder.setCancelable(true);

                        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                finish();
                            }
                        });
                        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        break;

                    default:
                        break;

                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {



        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mAuth.signOut();
        sendToLogion();

    }


    private void sendToLogion(){
        Intent intent = new Intent(Home.this, Login.class);
        startActivity(intent);
        finish();

    }
}