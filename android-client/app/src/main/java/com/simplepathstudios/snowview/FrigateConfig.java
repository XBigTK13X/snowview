package com.simplepathstudios.snowview;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class FrigateConfig {
    public Map<String, FrigateCamera> cameras;
}

class FrigateCamera {
    public Map<Object, Object> ffmpeg;

    public String getQualityStreamUrl(){
        return (String)((ArrayList<LinkedTreeMap>)ffmpeg.get("inputs")).get(0).get("path");
    }

    public String getSpeedStreamUrl() {
        return (String)((ArrayList<LinkedTreeMap>)ffmpeg.get("inputs")).get(1).get("path");
    }
}