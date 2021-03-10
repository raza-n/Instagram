package com.example.instagram.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.instagram.Post;
import com.example.instagram.R;
import com.example.instagram.ui.login.LoginActivity;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostFragment {
    private ImageView ivProfilePicFrag;
    private TextView tvUsername3;
    protected Button btnEdit;
    protected TextView tvBioPro;
    protected Button btnLogout;
    protected Context context;
    protected TextView tvPostsNum;
    protected TextView tvFollowers;
    protected TextView tvFollowing;
    protected TextView post;
    protected TextView followers;
    protected TextView following;
    protected ImageView grid;
    protected ImageView tag;
    protected ImageView ivGrid;
    protected ImageView ivList;

/*
    public ProfileFragment() {
        // Required empty public constructor
    }

 */
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
        ivProfilePicFrag = view.findViewById(R.id.ivProfilePicFrag);
        tvUsername3 = view.findViewById(R.id.tvUsername3);
        btnEdit = view.findViewById(R.id.btnEdit);
        tvBioPro = view.findViewById(R.id.tvBioPro);
        tvPostsNum=view.findViewById(R.id.tvPostsNum);
        tvFollowers=view.findViewById(R.id.tvFollowers);
        tvFollowing=view.findViewById(R.id.tvFollowing);
        post=view.findViewById(R.id.post);
        followers=view.findViewById(R.id.followers);
        following=view.findViewById(R.id.following);
        grid=view.findViewById(R.id.grid);
        tag=view.findViewById(R.id.tag);
        ivList = view.findViewById(R.id.ivList);
        ivGrid = view.findViewById(R.id.ivGrid);
        tvUsername3.setText(ParseUser.getCurrentUser().getUsername());

        btnLogout = view.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                goLoginActivity();
            }
        });

        ParseFile profilePic = ParseUser.getCurrentUser().getParseFile("ProfilePic");
        if (profilePic != null) {
            Glide.with(context).load(profilePic.getUrl()).circleCrop().into(ivProfilePicFrag);
        }
        queryPost();
    }

    private void goLoginActivity() {
       Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    @Override
    protected void queryPost() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issues with getting posts", e);
                }
                for (Post post: posts){
                    Log.i(TAG, "Post: " + post.getDescription() + " username: " + post.getUser().getUsername());
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();

            }
        });
    }
}

























