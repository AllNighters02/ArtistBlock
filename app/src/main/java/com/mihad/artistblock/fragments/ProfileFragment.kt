package com.mihad.artistblock.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mihad.artistblock.MainActivity
import com.mihad.artistblock.Post
import com.mihad.artistblock.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class ProfileFragment : HomeFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun queryPosts() {
        // Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)

        if(swipe.isRefreshing){
            swipe.isRefreshing = false

        }
        // Finds all Post Objects in our server
        query.include(Post.KEY_USER)
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser())
        query.addDescendingOrder("createdAt")
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e != null) {
                    // Something has went wrong
                    Log.e(MainActivity.TAG, "Error fetching posts")
                } else {
                    if (posts != null) {
                        for (post in posts) {
                            Log.i(
                                MainActivity.TAG,
                                "Post: " + post.getDescription() + " , username: " +
                                        post.getUser()?.username
                            )
                        }
                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }
}