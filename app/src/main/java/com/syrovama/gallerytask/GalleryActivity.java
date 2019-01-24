package com.syrovama.gallerytask;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class GalleryActivity extends AppCompatActivity {
    public static final String TAG = "GalleryActivity";
    public static final int READ_STORAGE_PERMISSION_REQUEST_CODE = 3;
    public static final String EXTRA_POSITION = "Position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        if (checkPermissionReadExternalStorage()) {
            initRecycler();
        } else {
            requestPermissionReadExternalStorage();
        }
    }

    private void initRecycler() {
        RecyclerView recyclerView = findViewById(R.id.gallery_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        GalleryPagedAdapter adapter = new GalleryPagedAdapter();
        adapter.setOnItemClickListener(new GalleryPagedAdapter.ImageClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.d(TAG, "Position "+ position);
                openPhoto(position);
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.submitList(ImageRepository.IMAGE_REPOSITORY.getImagePagedList());
    }

    private void openPhoto(int position) {
        Intent intent = new Intent(this, ImageViewerActivity.class);
        intent.putExtra(EXTRA_POSITION, position);
        startActivity(intent);

    }

    public boolean checkPermissionReadExternalStorage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public void requestPermissionReadExternalStorage() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                READ_STORAGE_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
            initRecycler();
        } else {
            Toast.makeText(this, "Grant permission to continue", Toast.LENGTH_LONG).show();
        }
    }
}
