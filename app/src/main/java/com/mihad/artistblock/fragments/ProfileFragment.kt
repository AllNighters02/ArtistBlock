package com.mihad.artistblock.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.mihad.artistblock.*
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class ProfileFragment : Fragment() {

    lateinit var tvUsername : TextView
    lateinit var tvDescription: TextView
    lateinit var ivProfile: ImageView

    lateinit var postsRecyclerView: RecyclerView

    lateinit var adapter: ProfilePostAdapter

    var allPosts: MutableList<Post> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // This is where we're going to set up our views and click listeners
        postsRecyclerView = view.findViewById(R.id.PostRecyclerView)

        tvUsername = view.findViewById(R.id.tvUsername)
        tvDescription = view.findViewById(R.id.tvDescription)
        ivProfile = view.findViewById(R.id.ivProfile)

        adapter = ProfilePostAdapter(requireContext(), allPosts)
        postsRecyclerView.adapter = adapter

        postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val queryUser = ParseUser.getCurrentUser()

        if(queryUser != null) {
            tvUsername.text = queryUser.username
            tvDescription.text = queryUser.get("aboutMe").toString()

            if (queryUser.getParseFile("profilePic")?.url != null) {
                context?.let { Glide.with(it).load(queryUser.getParseFile("profilePic")?.url).into(ivProfile) }
            }
        }

        queryPosts()
    }

    fun queryPosts() {
        // Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)

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