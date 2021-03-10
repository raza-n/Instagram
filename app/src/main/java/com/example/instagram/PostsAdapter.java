package com.example.instagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.Viewholder>{
    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvUsername2;
        private TextView tvDescription;
        private ImageView ivProfilePic;
        private TextView tvTimeStamp;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvUsername=itemView.findViewById(R.id.tvUsername);
            ivImage=itemView.findViewById(R.id.ivImage);
            tvUsername2=itemView.findViewById(R.id.tvUsername2);
            tvDescription=itemView.findViewById(R.id.tvDescription);
            ivProfilePic=itemView.findViewById(R.id.ivProfilePicFrag);
            tvTimeStamp=itemView.findViewById(R.id.tvTimeStamp);
            //ivProfilePic=Glide.with(context).load(url).apply(RequestOptions.circleCropTransform()).into(ivProfilePic);

        }

        public void bind(Post post) {
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            tvUsername2.setText(post.getUser().getUsername());
            tvTimeStamp.setText(post.getRelativeTime());
            ParseFile image =post.getImage();
            //ParseFile profilePic = post.getProfilePic();
            if (image != null) {
                Glide.with(context).load(post.getImage().getUrl()).into(ivImage);
            }/*
            if (profilePic != null) {
                Glide.with(context).load(post.getProfilePic().getUrl()).into(ivProfilePic);
                Glide.with(context).load(post.getProfilePic()).apply(RequestOptions.circleCropTransform()).into(ivProfilePic);
            }*/
            ParseFile profilePic =  post.getUser().getParseFile("ProfilePic");
            if (profilePic != null) {
                Glide.with(context).load(profilePic.getUrl()).circleCrop().into(ivProfilePic);
                //Glide.with(context).load(profilePic.getUrl()).apply(RequestOptions.circleCropTransform()).into(ivProfilePic);
            }

        }
    }






















}
