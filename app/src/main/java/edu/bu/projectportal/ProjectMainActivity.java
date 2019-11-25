package edu.bu.projectportal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import 	android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by danazh on 4/24/18.
 */

public class ProjectMainActivity extends AppCompatActivity implements ProjectListAdapter.Listener {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_main);

        // get the last access time from the shared preferences. The file name is accesstime.xml
        SharedPreferences sharedPreferences = getSharedPreferences("accesstime", Context.MODE_PRIVATE);
        String lastAccessTime = sharedPreferences.getString("lastaccesstime", "");

        if (lastAccessTime != "") {
            TextView textView = findViewById(R.id.latid);
            textView.setText(lastAccessTime);
        }

        // get current time and write to the shared preferences file
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String curTime = (new Date()).toString();
        editor.putString("lastaccesstime","last access at " + curTime);
        editor.commit();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddProjectActivity.class);
                startActivity(intent);
            }
        });

        BroadcastReceiver br = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_BOOT_COMPLETED);
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        getApplication().registerReceiver(br, filter);

    }


    @Override
    public void onClick(int id, int position) {
        ProjectDetailFragment projectDetailFragment =
                (ProjectDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailfragment);
        if (projectDetailFragment != null) {
            projectDetailFragment.setProject(id);
        }

        else {
            Intent intent = new Intent(this, ProjectDetailActivity.class);
            intent.putExtra("Projectid", id);
            intent.putExtra("Projectposition", position);
            startActivity(intent);
        }

    }

}
