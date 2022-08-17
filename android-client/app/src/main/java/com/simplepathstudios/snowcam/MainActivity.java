package com.simplepathstudios.snowcam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CameraListAdapter adapter;
    private RecyclerView recyclerView;

    private static MainActivity __instance;

    public static MainActivity getInstance(){
        return __instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        __instance = this;
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.camera_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.getInstance());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CameraListAdapter();
        recyclerView.setAdapter(adapter);
        FrigateClient client = FrigateClient.getInstance();
        Activity activity = this;
        client.loadConfig().enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                FrigateConfig config = (FrigateConfig)response.body();
                config.populateNames();
                ArrayList<FrigateCamera> cameras = config.getCameras();

                adapter.setData(cameras);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                int x = 0;
            }
        });
    }
}