package com.natlusrun.postviewpager.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.natlusrun.postviewpager.R;
import com.natlusrun.postviewpager.data.model.PostModel;
import com.natlusrun.postviewpager.interfaces.OnPostsItemClick;

import java.util.ArrayList;
import java.util.List;

public class PostsListAdapter extends RecyclerView.Adapter<PostsListAdapter.PostViewHolder> {

    public List<PostModel> postsList = new ArrayList<>();
    public OnPostsItemClick onPostsItemClick;


    public void setPostsList(List<PostModel> postsList) {
        this.postsList = postsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.onPostsBind(postsList.get(position));
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public void setOnPostsItemClick(OnPostsItemClick onPostsItemClick) {
        this.onPostsItemClick = onPostsItemClick;
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView postsUser;
        TextView postsContent;
        TextView postsID;
        ImageView iconFav;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            postsUser = itemView.findViewById(R.id.posts_user_tv);
            postsContent = itemView.findViewById(R.id.posts_content_tv);
            cardView = itemView.findViewById(R.id.posts_cv);
            iconFav = itemView.findViewById(R.id.icon_fav);
            //postsID = itemView.findViewById(R.id.posts_id_tv);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onPostsItemClick.onPostsItemViewClick(getAdapterPosition());
                    return true;
                }
            });

        }

        @SuppressLint("SetTextI18n")
        public void onPostsBind(PostModel postModel) {
            postsUser.setText(postModel.getTitle());
            //postsID.setText(postModel.getId().toString());
            postsContent.setText(postModel.getContent());
        }
    }
}
