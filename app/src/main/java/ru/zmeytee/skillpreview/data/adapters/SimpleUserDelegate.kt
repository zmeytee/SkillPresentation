package ru.zmeytee.skillpreview.data.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.zmeytee.skillpreview.R
import ru.zmeytee.skillpreview.data.enums.ItemAction
import ru.zmeytee.skillpreview.data.models.User
import ru.zmeytee.skillpreview.databinding.ItemSimpleUserBinding
import ru.zmeytee.skillpreview.utils.inflate

class SimpleUserDelegate(
    private val onClick: (id: Long, action: ItemAction) -> Unit
) : AbsListItemAdapterDelegate<User.SimpleUser, User, SimpleUserDelegate.Holder>() {

    override fun isForViewType(item: User, items: MutableList<User>, position: Int): Boolean {
        return item is User.SimpleUser
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(
            ItemSimpleUserBinding.bind(parent.inflate(R.layout.item_simple_user)),
            onClick
        )
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
        private val onClick: (id: Long, action: ItemAction) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentUserId: Long? = null

        init {
            binding.userItemNavAction.setOnClickListener {
                currentUserId?.let { userId -> onClick(userId, ItemAction.INFO) }
            }

            binding.userItemCallUser.setOnClickListener {
                currentUserId?.let { userId -> onClick(userId, ItemAction.CALL) }
            }

            binding.userItemMailToUser.setOnClickListener {
                currentUserId?.let { userId -> onClick(userId, ItemAction.MAIL) }
            }

            binding.userItemDeleteUser.setOnClickListener {
                currentUserId?.let { userId -> onClick(userId, ItemAction.DELETE) }
            }
        }

        fun bind(item: User.SimpleUser) {
            currentUserId = item.id

            with(binding) {
                userItemName.text = item.name
                userItemUserName.text = item.userName
            }
        }
    }
}