package com.mihad.artistblock.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mihad.artistblock.Post
import com.mihad.artistblock.PostAdapter
import com.mihad.artistblock.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery

class HomeFragment : Fragment() {
    lateinit var postsRecyclerView: RecyclerView

    lateinit var adapter: PostAdapter

    var allPosts: MutableList<Post> = mutableListOf()

    private lateinit var swipe : SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // This is where we're going to set up our views and click listeners
        postsRecyclerView = view.findViewById(R.id.PostRecyclerView)

        adapter = PostAdapter(requireContext(), allPosts)
        postsRecyclerView.adapter = adapter

        postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        swipe = view.findViewById(R.id.swipe)
        swipe.setOnRefreshListener {
            clear()
            queryPosts()
            adapter.notifyDataSetChanged()

        }

        queryPosts()
    }

    // Query for all posts in our server
    open fun queryPosts() {
        // Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)

        if(swipe.isRefreshing){
            swipe.isRefreshing = false

        }

        // Finds all Post Objects in our server
        query.include(Post.KEY_USER)
        query.addDescendingOrder("createdAt")
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e != null) {
                    // Something has went wrong
                    Log.e(TAG, "Error fetching posts")
                }
                else
                {
                    if (posts != null) {
                        for (post in posts) {
                            Log.i(TAG, "Post: " + post.getDescription() + " , username: " + post.getUser()?.username)
                        }

                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }

    fun clear() {
        allPosts.clear()
    }

    companion object {
        const val TAG = "FeedFragment"
    }

}