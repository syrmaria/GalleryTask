package com.syrovama.gallerytask;

import android.arch.paging.PositionalDataSource;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

class ImageDataSource extends PositionalDataSource<String> {
    //private static final String TAG = "ImageDataSource";
    //private static final int LIMIT = 1000;

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<String> callback) {
        List<String> result = getImagesPaths(params.requestedStartPosition, params.requestedLoadSize);
        callback.onResult(result, params.requestedStartPosition);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<String> callback) {
        List<String> result = getImagesPaths(params.startPosition, params.loadSize);
        callback.onResult(result);
    }


    private ArrayList<String> getImagesPaths(int from, int size) {
        Context context = MyApplication.getAppContext();
        Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null,
                MediaStore.MediaColumns.DATA + " ASC LIMIT " + size + " OFFSET " + from);
        ArrayList<String> imagePathList = new ArrayList<>();
        String currentPath;
        if (cursor != null) {
            int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            while (cursor.moveToNext()) {
                currentPath = cursor.getString(column_index_data);
                imagePathList.add(currentPath);
            }
            cursor.close();
        }
        return imagePathList;
    }
}
