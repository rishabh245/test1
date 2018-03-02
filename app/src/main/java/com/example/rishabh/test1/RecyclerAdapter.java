package com.example.rishabh.test1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by rishabh on 2/28/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {


    CountryList countries;
    Context context;
    ImageViewClickListener mListener;

    public RecyclerAdapter(CountryList countries , Context context,ImageViewClickListener mListener){
        this.countries = countries;
        this.context = context;
        this.mListener = mListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false);
        return new MyViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       holder.rankTextView.setText("Rank " + countries.getWorldpopulation().get(position).getRank());
       holder.nameTextView.setText("Name " + countries.getWorldpopulation().get(position).getCountry());
       holder.populationTextView.setText("Population " + countries.getWorldpopulation().get(position).getPopulation());
       Glide.with(context).load(countries.getWorldpopulation().get(position).getFlag()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return countries.getWorldpopulation().size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView rankTextView,nameTextView,populationTextView;
        ImageViewClickListener mListener;
        LinearLayout mainLayout;

        public MyViewHolder(View itemview , ImageViewClickListener mListener){
            super(itemview);
            this.mListener = mListener;
            imageView = itemview.findViewById(R.id.image_view);
            mainLayout = itemview.findViewById(R.id.main_layout);
            mainLayout.setOnClickListener(this);
            rankTextView = itemview.findViewById(R.id.rank_text_view);
            nameTextView = itemview.findViewById(R.id.name_text_view);
            populationTextView = itemview.findViewById(R.id.population_text_view);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mListener.onImageViewClicked(position);
        }
    }

    public interface ImageViewClickListener{
        void onImageViewClicked(int position);
    }

}
