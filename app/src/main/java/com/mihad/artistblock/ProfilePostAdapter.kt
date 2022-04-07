package com.mihad.artistblock

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProfilePostAdapter (val context: Context, val posts:List<Post>)
: RecyclerView.Adapter<ProfilePostAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilePostAdapter.ViewHolder {
        //Specify layout file to use

        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfilePostAdapter.ViewHolder, position: Int) {
        val post = posts.get(position)
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivPicture2: ImageView

        init {
            ivPicture2 = itemView.findViewById(R.id.ivPicture2)
        }

        fun bind(post: Post){
            Glide.with(itemView.context).load(post.getImage()?.url).into(ivPicture2)
        }
    }

}