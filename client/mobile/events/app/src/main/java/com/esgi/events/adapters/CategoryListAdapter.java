package com.esgi.events.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.esgi.events.R;
import com.esgi.events.activities.CategoryFormActivity;
import com.esgi.events.activities.EventDetailActivity;
import com.esgi.events.models.Category;
import com.esgi.events.models.Event;
import com.esgi.events.webservice.CategoryRestClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sylvainvincent on 21/01/16.
 */
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private List<Category> categoryArrayList;
    private Context context;
    public static final String PUT_EVENT_ID = "eventId";
    public static String token;
    private static final String TAG = "CategoryListAdapter";
    private CoordinatorLayout coordinator;


    public CategoryListAdapter(CoordinatorLayout coordinator, List<Category> categoryArrayList, Context context, String token){
        this.categoryArrayList = categoryArrayList;
        this.context = context;
        this.token = token;
        this.coordinator = coordinator;
    }

    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category,parent, false),coordinator);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(categoryArrayList.get(position),context);
    }

    public void updateData(ArrayList<Event> eventArrayList) {
        this.categoryArrayList.clear();
        this.categoryArrayList.addAll(categoryArrayList);
        notifyDataSetChanged();
    }

    public void addItem(int position, Category category) {
        this.categoryArrayList.add(position, category);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        this.categoryArrayList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private ImageView delete,modify;
        CoordinatorLayout coordinator;

        public ViewHolder(View itemView, CoordinatorLayout coordinator) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.row_category_title);
            delete = (ImageView) itemView.findViewById(R.id.delete_category_button);
            modify = (ImageView) itemView.findViewById(R.id.edit_category_button);
            this.coordinator = coordinator;

        }

        public void bind(final Category category, final Context context){
            title.setText(category.getName());
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    alertDialogBuilder
                            .setMessage("Êtes vous sûr de vouloir supprimer cette catégorie ?")
                            .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    CategoryRestClient categoryRestClient = new CategoryRestClient();
                                    Call<Category> call = categoryRestClient.deleteCategory(token, category.get_id());
                                    call.enqueue(new Callback<Category>() {
                                        @Override
                                        public void onResponse(Response<Category> response, Retrofit retrofit) {
                                            if (response.isSuccess()) {
                                                Intent intent = ((Activity) context).getIntent();
                                                ((Activity) context).finish();
                                                context.startActivity(intent);


                                            } else {
                                                if (response.code() == 403) {
                                                    Snackbar.make(coordinator,"Cette catégorie ne peut pas être supprimer car elle est lié à au moins un évenement",Snackbar.LENGTH_LONG).show();
                                                }
                                                Log.e(TAG, "onResponse: " + response.message());
                                                Log.e(TAG, "onResponse: " + response.code());
                                            }

                                        }

                                        @Override
                                        public void onFailure(Throwable t) {
                                            Log.e(TAG, "onFailure: " + t.getMessage());
                                        }
                                    });
                                }
                            })
                            .setNegativeButton("Non", null)
                            .create();

                    alertDialogBuilder.show();
                }
            });
            modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CategoryFormActivity.class);
                    intent.putExtra("categoryId", category.get_id());
                    intent.putExtra("categoryName", category.getName());
                    intent.putExtra("token", token);
                    Log.e(TAG, "onClick: " + token );
                    ((Activity) context).startActivityForResult(intent,1);
                }
            });
        }
    }

}
