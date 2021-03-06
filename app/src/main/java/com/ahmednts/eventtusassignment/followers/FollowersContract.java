package com.ahmednts.eventtusassignment.followers;

import com.twitter.sdk.android.core.models.User;

import java.util.List;

/**
 * Created by AhmedNTS on 6/1/2017.
 */

/**
 * This specifies the contract between the view and the presenter.
 */
interface FollowersContract {

    interface View {
        void setTitle(String username);

        void showFollowersList(List<User> users);

        void openFollowerDetailsUI(User follower);

        void showIndicator();

        void hideIndicator();

        void showNoResultMessage();

        void showApiLimitMessage();

        void showToastMessage(String msg);

        void showNoNetworkMessage();
    }

    interface Presenter {

        void loadFollowersList(boolean reload);

        void openFollowerDetails(User follower);

        void setActiveUser(String userName);

        void stop();
    }
}
