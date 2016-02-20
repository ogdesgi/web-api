package com.esgi.events.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.esgi.events.R;
import com.esgi.events.models.Category;
import com.esgi.events.webservice.CategoryRestClient;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sylvainvincent on 16/02/16.
 */
public class CategoryFormActivity extends AppCompatActivity{

    private final String TAG = getClass().getSimpleName();
    private EditText editCategory;
    private String token;
    private String categoryId;
    private String categoryName;
    private Button action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_category_form);
        categoryId = getIntent().getStringExtra("categoryId");
        categoryName = getIntent().getStringExtra("categoryName");
        token = getIntent().getStringExtra("token");
        editCategory = (EditText) findViewById(R.id.edit_category);
        action = (Button) findViewById(R.id.category_form_action);

        if(categoryName != null){
            if(!categoryName.equals("")){
                editCategory.setText(categoryName);
                action.setText("Modifier");
            }
        }


    }

    public void action(View view) {

        if(!editCategory.getText().toString().equals("")){
            CategoryRestClient categoryRestClient = new CategoryRestClient();

            if(action.getText().toString().equals("Modifier")){
                Call<Category> call = categoryRestClient.updateCategory(token, categoryId, new Category(editCategory.getText().toString()));
                call.enqueue(new Callback<Category>() {
                    @Override
                    public void onResponse(Response<Category> response, Retrofit retrofit) {
                        if(response.isSuccess()){
                            setResult(RESULT_OK);
                            finish();
                        }else{
                            Log.e(TAG, "no success: " + response.code() );
                            Log.e(TAG, "no success: " + response.message() );
                            Log.e(TAG, "no success: " + token);
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage() );
                        Log.e(TAG, "onFailure: " + token );
                    }
                });
            }else {
                Log.e(TAG, "test: " + token );
                Call<Category> call = categoryRestClient.postCategory(token, new Category(editCategory.getText().toString()));
                call.enqueue(new Callback<Category>() {
                    @Override
                    public void onResponse(Response<Category> response, Retrofit retrofit) {
                        if(response.isSuccess()){
                            setResult(RESULT_OK);
                            finish();
                        }else{
                            Log.e(TAG, "onFailure: " + response.code() );
                            Log.e(TAG, "onFailure: " + response.message() );
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage() );
                    }
                });
            }
        }

    }
}
