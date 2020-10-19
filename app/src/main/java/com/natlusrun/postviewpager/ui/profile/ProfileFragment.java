package com.natlusrun.postviewpager.ui.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.natlusrun.postviewpager.App;
import com.natlusrun.postviewpager.R;
import com.natlusrun.postviewpager.data.model.PostModel;
import com.natlusrun.postviewpager.data.network.MockerService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    private Button btnPost;
    private EditText etTitle, etContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostModel model = new PostModel();
                model.setContent(etContent.getText().toString().trim());
                model.setTitle(etTitle.getText().toString().trim());
                MockerService.getInstance().getPostApi().addPost(model).enqueue(new Callback<PostModel>() {
                    @Override
                    public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                        Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<PostModel> call, Throwable t) {
                        Toast.makeText(getContext(), "failer", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void initViews(View view) {
        btnPost = view.findViewById(R.id.post_btn);
        etTitle = view.findViewById(R.id.title_et);
        etContent = view.findViewById(R.id.content_et);
    }
}