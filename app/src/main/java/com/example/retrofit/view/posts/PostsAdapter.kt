package com.example.retrofit.view.posts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.model.Post


class PostsAdapter(val context: Context) :
    RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    var onCLickListener:OnCLickListener?=null

    var posts: List<Post> = ArrayList<Post>()
        set(value){
            field=value
            notifyDataSetChanged()
        }

    constructor(context: Context,posts: List<Post>):this(context){
        this.posts=posts
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.post_layout,
                    parent,
                    false
                )
        return ViewHolder(v, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTV.text= posts[position].title
        holder.bodyTV.text= posts[position].body
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class ViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
        var titleTV:TextView
        var bodyTV:TextView

        init {
            titleTV=itemView.findViewById(R.id.post_title)
            bodyTV=itemView.findViewById(R.id.post_body)
            itemView.setOnClickListener {
                if(onCLickListener != null){
                    val position = adapterPosition
                    if (position !=RecyclerView.NO_POSITION){
                        onCLickListener!!.onCLick(position);
                    }

                }
            }
        }
    }
    interface OnCLickListener{
        fun onCLick(pos:Int)
    }
}