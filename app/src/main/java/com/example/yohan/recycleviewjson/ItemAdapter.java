package com.example.yohan.recycleviewjson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ExampleViewHolder> {

private Context context;
private ArrayList<items> itemList;
private OnItemCliked mListner;


    public interface OnItemCliked{
        void OnItemClicked(int index);
    }

    public void SetOnItemClickListener(OnItemCliked listner){
        mListner = listner;
    }
    public ItemAdapter(Context context, ArrayList<items> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View v = LayoutInflater.from(context).inflate(R.layout.item, viewGroup,false);
       return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {

        items  currentItem = itemList.get(i);
        String imageUrl = currentItem.getMimgeUrl();
        String creatorName = currentItem.getmCreator();
        int likes = currentItem.getmLikes();

        exampleViewHolder.mTextviewCreator.setText(creatorName);
        exampleViewHolder.mTextviewLikes.setText(String.valueOf(likes));
        //string url to convert url using picasso
        Picasso.get().load(imageUrl).fit().centerInside().into(exampleViewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ExampleViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mTextviewCreator;
        public TextView mTextviewLikes;

        public ExampleViewHolder(View itemView){
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_view);
            mTextviewCreator = itemView.findViewById(R.id.text_view_creator);
            mTextviewLikes = itemView.findViewById(R.id.textview_like);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListner != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListner.OnItemClicked(position);
                        }

                    }
                }
            });

        }

    }
}
