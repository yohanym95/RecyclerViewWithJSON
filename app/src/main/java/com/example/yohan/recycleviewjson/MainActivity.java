package com.example.yohan.recycleviewjson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemAdapter.OnItemCliked {

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_CREATOR = "creatorName";
    public static final String EXTRA_LIKES= "likeCount";

    private RecyclerView mRecyclerView;
    private ItemAdapter mItemAdapter;
    private ArrayList<items> mItemList;
    private RequestQueue mRequestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mItemList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        parseJson();

    }

    private void parseJson() {
        String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("hits");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject hit = jsonArray.getJSONObject(i);

                        String creatorNmae = hit.getString("user");
                        String imageURL = hit.getString("webformatURL");
                        int likes = hit.getInt("likes");
                        mItemList.add(new items(imageURL,creatorNmae,likes));


                    }

                    mItemAdapter = new ItemAdapter(MainActivity.this,mItemList);
                    mRecyclerView.setAdapter(mItemAdapter);
                    mItemAdapter.SetOnItemClickListener(MainActivity.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });
        mRequestQueue.add(jsonObjectRequest);
    }


    @Override
    public void OnItemClicked(int index) {
        Intent i = new Intent(this,DetailsActivity.class);
        items item = mItemList.get(index);

        i.putExtra(EXTRA_URL,item.getMimgeUrl());
        i.putExtra(EXTRA_CREATOR,item.getmCreator());
        i.putExtra(EXTRA_LIKES, item.getmLikes());

        startActivity(i);

    }
}
