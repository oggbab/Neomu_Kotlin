package com.not.app.mypage;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class JoinMyFragment extends MyMainFragment {


    public JoinMyFragment() {
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {

        String myUserId = getUid();
        Query recentPostsQuery = databaseReference.child("posts")
                .limitToFirst(1);


        return recentPostsQuery;

    }
}