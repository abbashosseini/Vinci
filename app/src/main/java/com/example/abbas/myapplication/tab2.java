package com.example.abbas.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import mklib.hosseini.com.vinci.Classes.Storage;
import mklib.hosseini.com.vinci.Vinci;

/**
 * Created by abbas on 8/24/15.
 */
public class tab2 extends RecyclerView.Adapter<tab2.ViewHolder>{

    private List<String> mItems;
    protected Context context;

    public tab2(List<String> items){
        mItems = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int i){
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_row_tabs2, viewGroup, false);
        context = viewGroup.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        final String uri = mItems.get(i);

        Vinci
            .base(context)
            .process()
            .load(uri)
            .view(viewHolder.Writer);

        viewHolder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Vinci.base(context).process().load(uri).file().remove();
            }
        });

        viewHolder.Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Storage store = Vinci.base(context).process().load(uri).file();

                Log.e("Created", Boolean.toString(store.isCreated()));
                Log.e("FileObject", store.FileObject().getName());
                Log.e("FullPath", store.getfullPath().getPath());
                Log.e("LocalPath", Storage.LocalPath().getPath());
                Log.e("Get Bitmap File", String.valueOf(store.getBitmap().getByteCount()));
            }
        });



    }

    @Override
    public int getItemCount(){
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView Writer;
        private final Button remove;
        private final Button Save;

        ViewHolder(View v) {
            super(v);
            Writer = (ImageView) v.findViewById(R.id.Writer);
            remove = (Button) v.findViewById(R.id.remove);
            Save = (Button) v.findViewById(R.id.Save);
        }
    }
}