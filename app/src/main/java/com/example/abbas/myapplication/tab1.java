package com.example.abbas.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.util.List;

import mklib.hosseini.com.vinci.Classes.AsyncBitmap;
import mklib.hosseini.com.vinci.Classes.Request;
import mklib.hosseini.com.vinci.Vinci;

/**
 * Created by abbas on 8/24/15.
 */
public class tab1 extends RecyclerView.Adapter<tab1.ViewHolder> implements Request{

    private List<File> mItems;
    protected Context context;

    public tab1(List<File> items){
        mItems= items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int i){
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_row_readitem_tabs1, viewGroup, false);
        context = viewGroup.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        final File uri = mItems.get(i);

        Log.e("file", String.valueOf(BitmapFactory.decodeFile(uri.getAbsolutePath()).getByteCount()));


//        viewHolder.Writer.post(new Runnable() {
//            @Override
//            public void run() {

                Vinci
                        .base(context)
                        .process()
                        .decodeFile(uri, new AsyncBitmap<Bitmap>() {
                            @Override
                            public void onFinish(Bitmap object) {
                                viewHolder.Writer.setImageBitmap(object);
                            }
                        });

//            }
//        });

    }

    @Override
    public int getItemCount(){
        return mItems.size();
    }

    @Override
    public void onSuccess(Bitmap bitmap) {

    }

    @Override
    public void onFailure(Throwable e) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView Writer;

        ViewHolder(View v) {
            super(v);
            Writer = (ImageView) v.findViewById(R.id.WriterName);
        }
    }
}
