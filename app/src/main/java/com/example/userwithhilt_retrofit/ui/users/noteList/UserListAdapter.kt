package com.example.userwithhilt_retrofit.ui.users.noteList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.userwithhilt_retrofit.databinding.UserListItemBinding
import com.example.userwithhilt_retrofit.domain.model.UserItem
import com.example.userwithhilt_retrofit.ui.users.noteList.UserListAdapter.CustomViewHolder

class UserListAdapter(
    private val requestManager: RequestManager,
    private val clickListener:(UserItem)->Unit
) : ListAdapter<UserItem, CustomViewHolder>(UserItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CustomViewHolder {
        val binding =
            UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding, requestManager)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindTo(getItem(position), clickListener)
    }

    class CustomViewHolder(
        val binding: UserListItemBinding,
        val requestManager: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(user: UserItem, clickListener: (UserItem) -> Unit) {
            binding.apply {
                requestManager
                    .load(user.avatarUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(userImage)
                userName.text = user.name
                userUrl.text = user.url

                root.setOnClickListener {
                    clickListener(user)
                }
            }
        }
    }

    class UserItemDiffCallback : DiffUtil.ItemCallback<UserItem>() {
        override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean = oldItem == newItem

    }
}
