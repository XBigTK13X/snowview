package com.simplepathstudios.snowview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrigateClient client = FrigateClient.getInstance();
        Activity activity = this;
        client.loadConfig().enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                FrigateConfig config = (FrigateConfig)response.body();
                String cameraName = (String)config.cameras.keySet().toArray()[0];
                FrigateCamera camera = (FrigateCamera)config.cameras.get(cameraName);
                String cameraUrl = camera.getSpeedStreamUrl();
                StreamViewer viewer = new StreamViewer(cameraUrl);
                viewer.openStream(activity);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                int x = 0;
            }
        });
    }
}