package com.syrovama.gallerytask;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class GalleryPagedAdapter extends PagedListAdapter<String, GalleryPagedAdapter.ImageHolder> {
    private static final String TAG = "GalleryPagedAdapter";

    public interface ImageClickListener {
        void onItemClick(int position, View v);
    }

    private ImageClickListener mClickListener;

    public void setOnItemClickListener(ImageClickListener clickListener) {
        mClickListener = clickListener;
    }

    private static DiffUtil.ItemCallback<String> CALLBACK = new DiffUtil.ItemCallback<String>() {
        @Override
        public boolean areItemsTheSame(String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }
    };

    GalleryPagedAdapter() {
        super(CALLBACK);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Nullable
    @Override
    protected String getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ImageHolder(inflater.inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.recycler_item;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        holder.bind(getItem(position));
    }


    public class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView mThumbnail;

        ImageHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mThumbnail = itemView.findViewById(R.id.thumb_image_view);
        }

        void bind(String path) {
            Glide.with(itemView.getContext())
                    .load(path)
                    .into(mThumbnail);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Log.d(TAG, "Start position for ViewPager "+ position);
            mClickListener.onItemClick(position, view);
        }
    }
}
