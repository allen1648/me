package com.stan.me.photography;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        PhotoViewHolder holder = (PhotoViewHolder) viewHolder;
        holder.mCoverIV.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDeleteDialog(position);
                return false;
            }
        });
        ImageLoader.getInstance().loadImage(mActivity, mPhotoList.get(position), holder.mCoverIV);
        holder.mTitleTV.setText(mPhotoList.get(position).getTitle());
        holder.mContentTV.setText(mPhotoList.get(position).getContent());
        holder.mDateTV.setText(DateUtil.getDayTime(mPhotoList.get(position).getDate()));
    }

    private void showDeleteDialog(final int position) {
        AlertDialog dialog = new AlertDialog.Builder(mActivity).setTitle(R.string.delete_this_pic)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PhotoDao dao = new PhotoDao();
                        if (dao.removePhoto(mPhotoList.get(position).getUri().toString())) {
                            Toast.makeText(mActivity, R.string.delete_success, Toast.LENGTH_SHORT).show();
                            mPhotoList.remove(position);
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(mActivity, R.string.delete_fail, Toast.LENGTH_SHORT).show();
                        }
                    }
                }).create();
        dialog.show();
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
