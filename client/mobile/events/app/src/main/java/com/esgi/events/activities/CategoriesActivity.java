package com.esgi.events.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.esgi.events.R;
import com.esgi.events.adapters.CategoryListAdapter;
import com.esgi.events.models.Categories;
import com.esgi.events.webservice.CategoryRestClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sylvainvincent on 16/02/16.
 */
public class CategoriesActivity extends AppCompatActivity {

    private static final String TAG = "CategoriesActivity";
    @Bind(R.id.category_form_fab)
    FloatingActionButton categoryFormFab;
    @Bind(R.id.coordinator_category)
    CoordinatorLayout coordinatorCategory;

    private String token;
    private Categories categoryList;
    private RecyclerView categoryRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        categoryRecyclerView = (RecyclerView) findViewById(R.id.category_recycler_view);
        token = getIntent().getStringExtra("token");
        Log.e(TAG, "token :  " + token);

        CategoryRestClient categoryRestClient = new CategoryRestClient();
        Call<Categories> call = categoryRestClient.getCategories();
        call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Response<Categories> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    categoryList = response.body();
                    categoryRecyclerView.setLayoutManager(new LinearLayoutManager(CategoriesActivity.this));
                    categoryRecyclerView.setAdapter(new CategoryListAdapter(coordinatorCategory, categoryList.getCategoryList(), CategoriesActivity.this, token));
                } else {

                    Log.e(TAG, "onResponse: non success ");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "onResponse: failure " + t);
                t.printStackTrace();
            }
        });

    }

    public void actionAddCategory(View view) {
        Intent intent = new Intent(this, CategoryFormActivity.class);
        intent.putExtra("token", token);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }
}
