package com.simplepathstudios.snowcam;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Map;

class FrigateConfig {
    public Map<String, FrigateCamera> cameras;

    public void populateNames(){
        for(String cameraName : cameras.keySet()){
            cameras.get(cameraName).setName(cameraName);
        }
    }

    public ArrayList<FrigateCamera> getCameras(){
        return new ArrayList<>(cameras.values());
    }
}

class FrigateCamera {
    public Map<Object, Object> ffmpeg;

    public String displayName;
    public String rawName;
    public String restreamUrl;

    public void setName(String name){
        this.rawName = name;
        this.displayName = name.replace('-',' ');
        this.restreamUrl = SnowcamSettings.FrigateRestreamUrl + "/live/" + rawName;
    }
    public String getQualityStreamUrl(){
        return (String)((ArrayList<LinkedTreeMap>)ffmpeg.get("inputs")).get(0).get("path");
    }

    public String getSpeedStreamUrl() {
        return (String)((ArrayList<LinkedTreeMap>)ffmpeg.get("inputs")).get(1).get("path");
    }
}