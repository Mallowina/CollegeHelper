package com.example.collegehelper;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.collegehelper.ui.schedule.ScheduleFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        /**СКРЫТИЕ ЭЛЕМЕНТА В ШТОРКЕ*/
//        Menu menu = navigationView.getMenu();
//        menu.findItem(R.id.nav_schedule).setVisible(false);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_map, R.id.nav_event, R.id.nav_schedule)  //Сюда записать фрагмент, чтобы была красивая шторка, а не стрелка
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**ДЕЙСТВИЯ ПО КНОПКАМ ИЗ КЛАССА КАРТА*/
    public void ShowNewCorp (View view) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.View);

        linearLayout.removeAllViews();

        ImageView imageview = new ImageView(this);
        ImageView imageview2 = new ImageView(this);
        ImageView imageview3 = new ImageView(this);
        ImageView imageview4 = new ImageView(this);
        TextView textView = new TextView(this);
        TextView textView2 = new TextView(this);
        TextView textView3 = new TextView(this);
        TextView textView4 = new TextView(this);

        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView2.setGravity(Gravity.CENTER_HORIZONTAL);
        textView3.setGravity(Gravity.CENTER_HORIZONTAL);
        textView4.setGravity(Gravity.CENTER_HORIZONTAL);

        textView.setText("1 этаж");
        imageview.setImageResource(R.mipmap.floor1);       // Add image path from drawable folder.
        textView2.setText("2 этаж");
        imageview2.setImageResource(R.mipmap.floor2);       // Add image path from drawable folder.
        textView3.setText("3 этаж");
        imageview3.setImageResource(R.mipmap.floor3);       // Add image path from drawable folder.
        textView4.setText("4 этаж");
        imageview4.setImageResource(R.mipmap.floor4);       // Add image path from drawable folder.

        linearLayout.addView(textView);
        linearLayout.addView(imageview);
        linearLayout.addView(textView2);
        linearLayout.addView(imageview2);
        linearLayout.addView(textView3);
        linearLayout.addView(imageview3);
        linearLayout.addView(textView4);
        linearLayout.addView(imageview4);
    }

    public void ShowOldCorp (View view) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.View);

        linearLayout.removeAllViews();

        ImageView imageview = new ImageView(this);
        ImageView imageview2 = new ImageView(this);
        TextView textView = new TextView(this);
        TextView textView2 = new TextView(this);

        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView2.setGravity(Gravity.CENTER_HORIZONTAL);

        textView.setText("1 этаж");
        imageview.setImageResource(R.mipmap.floor1);       // Add image path from drawable folder.
        textView2.setText("2 этаж");
        imageview2.setImageResource(R.mipmap.floor2);       // Add image path from drawable folder.

        linearLayout.addView(textView);
        linearLayout.addView(imageview);
        linearLayout.addView(textView2);
        linearLayout.addView(imageview2);
    }

    public void ShowJanks (View view) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.View);

        linearLayout.removeAllViews();

        ImageView imageview = new ImageView(this);
        ImageView imageview2 = new ImageView(this);
        TextView textView = new TextView(this);
        TextView textView2 = new TextView(this);

        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView2.setGravity(Gravity.CENTER_HORIZONTAL);

        textView.setText("Женское общежитие");
        imageview.setImageResource(R.mipmap.floor3);       // Add image path from drawable folder.
        textView2.setText("Мужское общежитие");
        imageview2.setImageResource(R.mipmap.floor4);       // Add image path from drawable folder.

        linearLayout.addView(textView);
        linearLayout.addView(imageview);
        linearLayout.addView(textView2);
        linearLayout.addView(imageview2);
    }

    /**ДЕЙСТВИЕ КНОПКИ ОСНОВНОГО РАСПИСНАИЯ*/
    public void raspisanie (View view) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutt);
        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.layoutt1);
        linearLayout.setVisibility(View.VISIBLE);
        linearLayout1.setVisibility(View.INVISIBLE);
    }
    public  void ismen (View view){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutt);
        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.layoutt1);
        linearLayout1.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);
    }
    /**Кнопка перехода на изменение расписнаия*/
    public void teacher (View view){
        Intent intent = new Intent(this, ScheduleFragment.class);
        startActivity(intent);
    }
}