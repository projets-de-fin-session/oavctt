package com.example.animsplashdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class TvShowAdapter  extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>{

    private List<TvShow> tvShows;
    private TvShowListener tvShowListener;

    public TvShowAdapter(List<TvShow> tvShows, TvShowListener tvShowListener) {
        this.tvShows = tvShows;
        this.tvShowListener = tvShowListener;
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TvShowViewHolder(

                LayoutInflater.from(parent.getContext()).inflate(

                        R.layout.item_container_tv_show, parent, false
                )

        );
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {

        holder.bindTvShow(tvShows.get(position));

    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    public List<TvShow> getSelectedTvShow(){

        List<TvShow> selectedTvshows = new ArrayList<>();
        for (TvShow tvShow : tvShows){

            if (tvShow.isSelected){

                selectedTvshows.add(tvShow);
            }
        }
        return selectedTvshows;
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout    layoutTvShow;
        View                viewBackground;
        RoundedImageView    imageTvShow;
        TextView            textName, textCreateBy, textStory;
        RatingBar           ratingBar;
        ImageView           imageSelected;


        public TvShowViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutTvShow = itemView.findViewById(R.id.layoutTvshow);
            viewBackground = itemView.findViewById(R.id.viewbackground);
            imageTvShow = itemView.findViewById(R.id.imageTvshow);
            textName = itemView.findViewById(R.id.textName);
            textCreateBy = itemView.findViewById(R.id.textcreateBy);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingbar);
            imageSelected = itemView.findViewById(R.id.imageSelected);
        }

        void bindTvShow(final TvShow tvShow){
            imageTvShow.setImageResource(tvShow.imanges);
            textName.setText(tvShow.name);
            textCreateBy.setText(tvShow.createdBy);
            textStory.setText(tvShow.story);
            ratingBar.setRating(tvShow.rating);

            if (tvShow.isSelected){
                viewBackground.setBackgroundResource(R.drawable.tv_show_selected_background);
                imageSelected.setVisibility(View.VISIBLE);

            }else {

                viewBackground.setBackgroundResource(R.drawable.tv_show_background);
                imageSelected.setVisibility(View.GONE);
            }

            layoutTvShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tvShow.isSelected){

                        viewBackground.setBackgroundResource(R.drawable.tv_show_background);
                        imageSelected.setVisibility(View.GONE);
                        tvShow.isSelected = false;

                        if (getSelectedTvShow().size() == 0){

                            tvShowListener.onTvShowAction(false);
                        }else {

                            viewBackground.setBackgroundResource(R.drawable.tv_show_selected_background);
                            imageSelected.setVisibility(View.VISIBLE);
                            tvShow.isSelected = true;
                            tvShowListener.onTvShowAction(true);

                        }
                    }
                }
            });

        }
    }
}
