package ru.zmeytee.skillpreview.data.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.zmeytee.skillpreview.R
import ru.zmeytee.skillpreview.data.models.User
import ru.zmeytee.skillpreview.databinding.ItemSimpleUserBinding
import ru.zmeytee.skillpreview.utils.inflate

class SimpleUserDelegate(
    private val onClick: (id: Long) -> Unit
): AbsListItemAdapterDelegate<User.SimpleUser, User, SimpleUserDelegate.Holder>() {

    override fun isForViewType(item: User, items: MutableList<User>, position: Int): Boolean {
        return item is User.SimpleUser
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(ItemSimpleUserBinding.bind(parent.inflate(R.layout.item_simple_user)), onClick)
    }

    override fun onBindViewHolder(
        item: User.SimpleUser,
        holder: Holder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class Holder(
        private val binding: ItemSimpleUserBinding,
        private val onClick: (id: Long) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {

        private var currentUserId: Long? = null

        init {
            binding.root.setOnClickListener { currentUserId?.let(onClick) }
        }

        fun bind(item: User.SimpleUser) {
            currentUserId = item.id

            with(binding) {
                userItemNameTextView.text = item.name
                userItemUserNameTextView.text = item.userName
            }
        }
    }
}