package com.ahmednts.eventtusassignment.followers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmednts.eventtusassignment.R;
import com.ahmednts.eventtusassignment.utils.CircleTransform;
import com.ahmednts.eventtusassignment.utils.Utils;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.models.User;

import java.util.List;

/**
 * Created by AhmedNTS on 6/2/2017.
 */
public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.PopularMovieViewHolder> {

    private List<User> itemList;
    private FollowerItemClickListener followerItemClickListener;

    FollowersAdapter(List<User> itemList, FollowerItemClickListener followerItemClickListener) {
        this.itemList = itemList;
        this.followerItemClickListener = followerItemClickListener;
    }

    @Override
    public PopularMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        android.view.View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follower, parent, false);
        return new PopularMovieViewHolder(layoutView, followerItemClickListener);
    }

    @Override
    public void onBindViewHolder(PopularMovieViewHolder holder, int position) {
        holder.onBindView(this, position);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public void replaceData(List<User> users) {
        setList(users);
        notifyDataSetChanged();
    }

    private void setList(List<User> users) {
        itemList = users;
    }

    static class PopularMovieViewHolder extends RecyclerView.ViewHolder {

        ImageView profileImage;
        TextView profileFullName, profileHandle, profileBio;

        private FollowerItemClickListener followerItemClickListener;

        PopularMovieViewHolder(android.view.View itemView, FollowerItemClickListener followerItemClickListener) {
            super(itemView);
            this.followerItemClickListener = followerItemClickListener;

            profileImage = (ImageView) itemView.findViewById(R.id.profileImage);
            profileFullName = (TextView) itemView.findViewById(R.id.profileFullName);
            profileHandle = (TextView) itemView.findViewById(R.id.profileHandle);
            profileBio = (TextView) itemView.findViewById(R.id.profileBio);
        }

        void onBindView(FollowersAdapter adapter, int position) {
            User follower = adapter.itemList.get(position);

            Picasso.with(itemView.getContext())
                    .load(follower.profileImageUrlHttps)
                    .transform(new CircleTransform())
                    .fit().centerCrop().into(profileImage);

            Utils.setText(profileFullName, follower.name, false);
            Utils.setText(profileHandle, follower.screenName, false);
            Utils.setText(profileBio, follower.description, true);

            itemView.setOnClickListener(v -> followerItemClickListener.onFollowerClick(follower));
        }
    }

    interface FollowerItemClickListener {
        void onFollowerClick(User follower);
    }
}