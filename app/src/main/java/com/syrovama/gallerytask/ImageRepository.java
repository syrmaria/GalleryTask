package com.syrovama.gallerytask;

import android.arch.paging.PagedList;

import java.util.concurrent.Executors;

public enum ImageRepository {
    IMAGE_REPOSITORY;
    private PagedList<String> imagePathList;

    ImageRepository() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(15)
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(18)
                .setPrefetchDistance(3)
                .build();

        imagePathList = new PagedList.Builder<>(new ImageDataSource(), config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .setNotifyExecutor(new MainExecutor())
                .build();
    }

    public PagedList<String> getImagePagedList() {
        return imagePathList;
    }

}
