package com.ahmednts.eventtusassignment.followers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmednts.eventtusassignment.R;
import com.ahmednts.eventtusassignment.utils.Utils;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.twitter.sdk.android.core.models.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AhmedNTS on 6/2/2017.
 */
public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.FollowerViewHolder> {

    private List<User> itemList;
    private FollowerItemClickListener followerItemClickListener;

    FollowersAdapter(List<User> itemList, FollowerItemClickListener followerItemClickListener) {
        this.itemList = itemList;
        this.followerItemClickListener = followerItemClickListener;
    }

    @Override
    public FollowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        android.view.View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follower, parent, false);
        return new FollowerViewHolder(layoutView, followerItemClickListener);
    }

    @Override
    public void onBindViewHolder(FollowerViewHolder holder, int position) {
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

    static class FollowerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.profileImage)
        ImageView profileImage;
        @BindView(R.id.profileFullName)
        TextView profileFullName;
        @BindView(R.id.profileHandle)
        TextView profileHandle;
        @BindView(R.id.profileBio)
        TextView profileBio;

        private FollowerItemClickListener followerItemClickListener;

        FollowerViewHolder(android.view.View itemView, FollowerItemClickListener followerItemClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            this.followerItemClickListener = followerItemClickListener;
        }

        void onBindView(FollowersAdapter adapter, int position) {
            User follower = adapter.itemList.get(position);

            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(5)
                    .oval(false)
                    .build();

            Picasso.with(itemView.getContext())
                    .load(follower.profileImageUrlHttps)
                    .placeholder(R.drawable.ic_profile_default)
                    .transform(transformation)
                    .fit().centerCrop().into(profileImage);

            profileFullName.setText(follower.name);
            profileHandle.setText(Utils.getUsernameScreenDisplay(follower.screenName));
            Utils.setText(profileBio, itemView.getContext().getString(R.string.profile_bio), follower.description, true);

            itemView.setOnClickListener(v -> followerItemClickListener.onFollowerClick(follower));
        }
    }

    interface FollowerItemClickListener {
        void onFollowerClick(User follower);
    }
}
