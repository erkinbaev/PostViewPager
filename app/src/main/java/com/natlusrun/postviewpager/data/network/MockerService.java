package com.natlusrun.postviewpager.data.network;

import android.util.Log;

import com.natlusrun.postviewpager.data.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MockerService {

    private final String TAG = "MockerService";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://android-3-mocker.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    MockerApi service = retrofit.create(MockerApi.class);

    public void getPostsList(MockerPostsCallback postsCallback) {
        Call<List<PostModel>> listCall = service.getPosts();
        listCall.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (response.isSuccessful()) {
                    postsCallback.onSuccess(response.body());
                    Log.d(TAG, "onResponse: " + response.body().get(0).getTitle());
                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                postsCallback.onFailure(t);
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public interface MockerPostsCallback {
        void onSuccess(List<PostModel> list);

        void onFailure(Throwable t);
    }

    public interface MockerApi {
        @GET("posts")
        Call<List<PostModel>> getPosts();
    }
}
