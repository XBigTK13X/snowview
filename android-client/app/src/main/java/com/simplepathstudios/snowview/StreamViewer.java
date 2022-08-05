package com.simplepathstudios.snowview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

class StreamViewer {
    private Uri _uri;
    public static int VLC_SUCCESS = 42;
    public StreamViewer(String uri){
        _uri = Uri.parse(uri);
    }
    public void openStream(Activity activity){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage("org.videolan.vlc"); // Use org.videolan.vlc for nightly builds
        intent.setDataAndType(_uri, "video/*");
        activity.startActivityForResult(intent, VLC_SUCCESS);
    }
}
