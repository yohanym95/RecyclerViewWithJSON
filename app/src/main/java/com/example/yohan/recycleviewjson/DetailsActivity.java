package com.example.yohan.recycleviewjson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView mName;
    TextView mLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(MainActivity.EXTRA_URL);
        String creatorName = intent.getStringExtra(MainActivity.EXTRA_CREATOR);
        int likes = intent.getIntExtra(MainActivity.EXTRA_LIKES, 0);

        imageView = findViewById(R.id.ImageView1);
        mName = findViewById(R.id.tvCreator);
        mLike = findViewById(R.id.tvLikes);


        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        mName.setText(creatorName);
        mLike.setText("Likes: "+likes);

    }
}
