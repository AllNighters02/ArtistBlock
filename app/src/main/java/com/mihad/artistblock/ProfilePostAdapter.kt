package com.mihad.artistblock

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parse.ParseUser

class ProfilePostAdapter (val context: Context, val posts:ArrayList<Post>)
: RecyclerView.Adapter<ProfilePostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilePostAdapter.ViewHolder {
        //Specify layout file to use

        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfilePostAdapter.ViewHolder, position: Int) {

        val post = posts.get(position)
        holder.bind(post)

        holder.btnDelete.setOnClickListener {
            post.deleteInBackground()
            posts.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, posts.size)
            // holder.itemView.visibility = View.GONE
            notifyDataSetChanged()

        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivPicture2: ImageView
        val btnDelete: ImageButton

        init {
            ivPicture2 = itemView.findViewById(R.id.ivPicture2)
            btnDelete = itemView.findViewById(R.id.btnDelete)
        }

        fun bind(post: Post){
            Glide.with(itemView.context).load(post.getImage()?.url).into(ivPicture2)

            ivPicture2.setOnClickListener {
                val vis = btnDelete.isVisible
                if(vis) {
                    btnDelete.isVisible = false
                }
                else {
                    btnDelete.isVisible = true
                }
                post.saveInBackground()
            }


        }

        private fun update() {

        }
    }
}