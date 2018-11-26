package com.stan.me.photography;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stan.core.database.PhotoDao;
import com.stan.core.utils.DateUtil;
import com.stan.core.utils.ImageLoader;
import com.stan.me.R;

import java.util.LinkedList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter {
    private List<PhotoDao.PhotoRecord> mPhotoList = new LinkedList<>();
    private Activity mActivity;

    public PhotoAdapter(Activity context) {
        mActivity = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = mActivity.getLayoutInflater().inflate(R.layout.photo_item, viewGroup, false);
        RecyclerView.ViewHolder viewHolder = new PhotoViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        PhotoViewHolder holder = (PhotoViewHolder) viewHolder;
        ImageLoader.getInstance().loadImage(mActivity, mPhotoList.get(position), holder.mCoverIV);
        holder.mTitleTV.setText(mPhotoList.get(position).getTitle());
        holder.mContentTV.setText(mPhotoList.get(position).getContent());
        holder.mDateTV.setText(DateUtil.getDayTime(mPhotoList.get(position).getDate()));
    }

    @Override
    public int getItemCount() {
        return mPhotoList.size();
    }

    public void addData(List<PhotoDao.PhotoRecord> list) {
        if (list != null) {
            mPhotoList.addAll(list);
        }
    }

    public void setData(List<PhotoDao.PhotoRecord> list) {
        if (list != null) {
            mPhotoList.clear();
            mPhotoList.addAll(list);
        }
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView mCoverIV;
        private TextView mTitleTV;
        private TextView mContentTV;
        private TextView mDateTV;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            mCoverIV = itemView.findViewById(R.id.iv_cover);
            mTitleTV = itemView.findViewById(R.id.tv_title);
            mContentTV = itemView.findViewById(R.id.tv_content);
            mDateTV = itemView.findViewById(R.id.tv_date);
        }
    }
}
