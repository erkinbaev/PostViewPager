package com.natlusrun.postviewpager.data.network;

import android.util.Log;
import android.widget.EditText;

import com.natlusrun.postviewpager.data.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class MockerService {

    private static final String BASE_URL = "https://android-3-mocker.herokuapp.com/";
    private static MockerService mockerService;
    protected Retrofit retrofit;

    public static MockerService getInstance() {
        if (mockerService == null) {
            mockerService = new MockerService();
        }
        return mockerService;
    }

    public MockerService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public MockerApi getPostApi() {
        return retrofit.create(MockerApi.class);

    }

    public interface MockerPostsCallback {
        void onSuccess(List<PostModel> list);

        void onFailure(Throwable t);
    }

    public interface MockerPostDeleteCallback {
        void onDeleteSuccess(PostModel postModelList);

        void onFailure(Throwable t);
    }

    public interface MockerPostPostCallback {
        void onPostSuccess(PostModel post);

        void onPostFailure(Throwable t);
    }

    public interface MockerApi {
        @GET("posts")
        Call<List<PostModel>> getPosts();

        @DELETE("posts/{id}")
        Call<PostModel> deletePostById(
                @Path("id") Integer itemId);

        @POST("posts")
        Call<PostModel> addPost
                (@Body PostModel post);
    }

}
