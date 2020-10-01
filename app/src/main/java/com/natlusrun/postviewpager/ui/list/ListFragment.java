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

import com.natlusrun.postviewpager.App;
import com.natlusrun.postviewpager.R;
import com.natlusrun.postviewpager.adapters.PostsListAdapter;
import com.natlusrun.postviewpager.data.model.PostModel;
import com.natlusrun.postviewpager.data.network.MockerService;

import java.util.List;

public class ListFragment extends Fragment {

    private final String TAG = "ListFragment";
    // private TextView postsUser, postsContent;
    private RecyclerView postsListRV;
    private PostsListAdapter postsListAdapter;

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

        App.mockerService.getPostsList(new MockerService.MockerPostsCallback() {
            @Override
            public void onSuccess(List<PostModel> list) {
                Log.d(TAG, "onSuccess: " + (list.size()));
                postsListAdapter.setPostsList(list);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}