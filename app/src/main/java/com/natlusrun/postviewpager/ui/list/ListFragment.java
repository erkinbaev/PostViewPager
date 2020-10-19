package com.natlusrun.postviewpager.ui.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.natlusrun.postviewpager.App;
import com.natlusrun.postviewpager.R;
import com.natlusrun.postviewpager.adapters.PostsListAdapter;
import com.natlusrun.postviewpager.data.model.PostModel;
import com.natlusrun.postviewpager.data.network.MockerService;
import com.natlusrun.postviewpager.interfaces.OnPostsItemClick;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {

    private final String TAG = "ListFragment";
    // private TextView postsUser, postsContent;
    private RecyclerView postsListRV;
    private PostsListAdapter postsListAdapter;
    List<PostModel> postModels;
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        postsListRV = view.findViewById(R.id.posts_rv);

        postsListAdapter = new PostsListAdapter();
        postsListRV.setAdapter(postsListAdapter);

       MockerService.getInstance().getPostApi().getPosts().enqueue(new Callback<List<PostModel>>() {
           @Override
           public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
               postModels = new ArrayList<>();
               postModels = response.body();
               postsListAdapter.setPostsList(postModels);
           }

           @Override
           public void onFailure(Call<List<PostModel>> call, Throwable t) {

           }
       });


        postsListAdapter.setOnPostsItemClick(new OnPostsItemClick() {
            @Override
            public void onPostsItemViewClick(int position) {
                MockerService.getInstance().getPostApi().deletePostById(postModels.get(position).getId()).enqueue(new Callback<PostModel>() {
                    @Override
                    public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                        Toast.makeText(getContext(), "deleted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<PostModel> call, Throwable t) {
                        Toast.makeText(getContext(), "Not deleted", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}