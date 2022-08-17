package com.simplepathstudios.snowcam;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class CameraListAdapter extends RecyclerView.Adapter<CameraListAdapter.ViewHolder> {

    private ArrayList<FrigateCamera> data;

    public CameraListAdapter(){
        this.data = new ArrayList<FrigateCamera>();
    }

    public void setData(ArrayList<FrigateCamera> cameras){
        data = cameras;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.camera_list_item, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.camera = this.data.get(position);
        holder.name.setText(holder.camera.displayName);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {
        public FrigateCamera camera;
        public TextView name;

        public ViewHolder(LinearLayout layout) {
            super(layout);
            this.name = layout.findViewById(R.id.camera_name);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            StreamViewer viewer = new StreamViewer(this.camera.restreamUrl);
            viewer.openStream();
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            ViewHolder self = this;
            MenuItem viewRestreamAction = menu.add("Restream (default)");
            viewRestreamAction.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    StreamViewer viewer = new StreamViewer(self.camera.restreamUrl);
                    viewer.openStream();
                    return false;
                }
            });
            MenuItem copyRestreamAction = menu.add("Copy restream URL");
            copyRestreamAction.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    ClipboardManager clipboard = (ClipboardManager) MainActivity.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("label", self.camera.restreamUrl);
                    clipboard.setPrimaryClip(clip);
                    return false;
                }
            });
            MenuItem viewFastStreamAction = menu.add("Direct stream - fast");
            viewFastStreamAction.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    StreamViewer viewer = new StreamViewer(self.camera.getSpeedStreamUrl());
                    viewer.openStream();
                    return false;
                }
            });
            MenuItem viewDetailedStreamAction = menu.add("Direct stream - pretty");
            viewDetailedStreamAction.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    StreamViewer viewer = new StreamViewer(self.camera.getQualityStreamUrl());
                    viewer.openStream();
                    return false;
                }
            });

        }
    }
}
