package com.mihad.artistblock

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostAdapter(val context: Context, val posts:List<Post>)
    : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        //Specify layout file to use

        val view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        val post = posts.get(position)
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvUserName: TextView
        val ivPicture: ImageView
        val tvDescription: TextView
        val ivProfilePic: ImageView
        val tvLikes: TextView
        val tvCreatedAt: TextView
        val ivLike: ImageButton
        var isLiked: Boolean


        init {
            tvUserName = itemView.findViewById(R.id.tvUserName)
            ivPicture = itemView.findViewById(R.id.ivPicture)
            tvDescription = itemView.findViewById(R.id.tvDescription)
            ivProfilePic = itemView.findViewById(R.id.ivProfilePic)
            tvLikes = itemView.findViewById(R.id.tvLikes)
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt)
            ivLike = itemView.findViewById(R.id.ivLike)
            isLiked = false
        }

        fun bind(post: Post){
            tvDescription.text = post.getDescription()
            tvUserName.text = post.getUser()?.username
            tvLikes.text = post.getNumber("likes").toString()
            tvCreatedAt.text = TimeFormatter.getTimeDifference((post.createdAt).toString())

            Glide.with(itemView.context).load(post.getImage()?.url).into(ivPicture)
            if (post.getUser()?.getParseFile("profilePic")!=null) {
                Glide.with(itemView.context).load(post.getUser()?.getParseFile("profilePic")?.url)
                    .into(ivProfilePic)
            }

            ivLike.setOnClickListener {
                ivLike.setColorFilter(Color.BLUE)
                if (!isLiked) {
                    post.put("likes", (post.getNumber("likes")!!.toInt()+1))
                    post.saveInBackground()
                    tvLikes.text = post.getNumber("likes").toString()
                }
                isLiked = true
            }

        }
    }
}