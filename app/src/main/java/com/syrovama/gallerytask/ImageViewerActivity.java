package com.syrovama.gallerytask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

import static com.syrovama.gallerytask.GalleryActivity.EXTRA_POSITION;

public class ImageViewerActivity extends FragmentActivity {
    private List<String> imagePathList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viever);
        prepareData();
        setPager();
    }

    private void prepareData() {
        imagePathList = ImageRepository.IMAGE_REPOSITORY.getImagePagedList();
    }

    private void setPager() {
        int position = getIntent().getIntExtra(EXTRA_POSITION,0);
        ViewPager mPager = findViewById(R.id.view_pager);
        FragmentStatePagerAdapter mAdapter =
                new MyFragmentStatePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(position);
    }

    public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
        MyFragmentStatePagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            String imagePath = imagePathList.get(position);
            return ImageSlideFragment.newInstance(imagePath);
        }

        @Override
        public int getCount() {
            return imagePathList.size();
        }
    }
}
