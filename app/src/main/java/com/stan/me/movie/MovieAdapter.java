package com.stan.me.movie;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stan.core.http.bean.Top250Info;
import com.stan.me.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter {

    private Activity mActivity;
    private List<Top250Info.MovieInfo> mMovieInfos;

    public MovieAdapter(Activity activity, List<Top250Info.MovieInfo> movieInfos) {
        mActivity = activity;
        mMovieInfos = movieInfos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = mActivity.getLayoutInflater().inflate(R.layout.movie_item, viewGroup, false);
        RecyclerView.ViewHolder viewHolder = new MovieViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MovieViewHolder holder = (MovieViewHolder) viewHolder;
        Glide.with(mActivity).load(mMovieInfos.get(position).getImages().getSmall()).into(holder.mCoverIV);
        holder.mTitleTV.setText(mMovieInfos.get(position).getTitle());
        holder.mYearTV.setText(mMovieInfos.get(position).getYear());
        holder.mDirectorTV.setText(mMovieInfos.get(position).getDirectors().get(0).getName());
    }

    @Override
    public int getItemCount() {
        return mMovieInfos.size();
    }

    public void setData(List<Top250Info.MovieInfo> movieInfos) {
        mMovieInfos = movieInfos;
    }


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView mCoverIV;
        private TextView mTitleTV;
        private TextView mDirectorTV;
        private TextView mYearTV;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mCoverIV = itemView.findViewById(R.id.iv_cover);
            mTitleTV = itemView.findViewById(R.id.tv_title);
            mDirectorTV = itemView.findViewById(R.id.tv_director);
            mYearTV = itemView.findViewById(R.id.tv_year);
        }
    }
}
