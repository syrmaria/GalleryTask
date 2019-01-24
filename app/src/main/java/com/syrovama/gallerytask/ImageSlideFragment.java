package com.syrovama.gallerytask;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.Objects;

public class ImageSlideFragment extends Fragment {
    public static final String POSITION = "Position";
    String mImagePath;

    public static ImageSlideFragment newInstance(String path) {
        Bundle args = new Bundle();
        args.putString(POSITION, path);
        ImageSlideFragment fragment = new ImageSlideFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImagePath = Objects.requireNonNull(getArguments()).getString(POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_viewer, container, false);
        PhotoView photoView = view.findViewById(R.id.full_photo_view);
        Glide.with(this)
                .load(mImagePath)
                .into(photoView);
        return view;
    }
}
