package com.example.pillativecareapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pillativecareapp.R
import com.example.pillativecareapp.data.User
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_chat_search.view.*

class DoctorAdapter (private val list: ArrayList<User>, var context: Context) :
    RecyclerView.Adapter<DoctorAdapter.ViewHolder>() {

    private var filteredItems: List<User> = list
    private var isFiltering: Boolean = false
    private lateinit var mlistener: OnItemClickListener

    fun onItemClickListener(listener: OnItemClickListener) {
        mlistener = listener
    }

    class ViewHolder(itemView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
        val name = itemView.name
        val image = itemView.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_search, parent, false)
        return ViewHolder(itemView, mlistener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = filteredItems[position]
        var name = current.firstName + current.lastName
        holder.name.text = name

//        Glide.with(context).load(current.image).into(holder.image)
//        Picasso.with(context).load(current.image).into(holder.image)

        val storageImage = FirebaseStorage.getInstance().reference
        val storageRef = storageImage.child(current.image.toString())
        storageRef.downloadUrl.addOnSuccessListener { uri ->
            val imageUrl = uri.toString()
            Picasso.with(context).load(imageUrl).into(holder.image)
        }
    }

    override fun getItemCount(): Int {
        return filteredItems.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    fun filter(query: String) {
        filteredItems = if (query.isEmpty()) {
            isFiltering = false
            list
        } else {
            isFiltering = true
            list.filter { item ->
                item.name.contains(query)
            }
        }
        notifyDataSetChanged()
    }

    fun isFiltering(): Boolean {
        return isFiltering
    }
}
