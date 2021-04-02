package ru.zmeytee.networkingsample.data.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.zmeytee.networkingsample.R
import ru.zmeytee.networkingsample.data.models.User
import ru.zmeytee.networkingsample.databinding.ItemUserBinding
import ru.zmeytee.networkingsample.utils.inflate

class UserDelegate(
    private val onClick: (id: Long) -> Unit
) : AbsListItemAdapterDelegate<User, User, UserDelegate.Holder>() {

    override fun isForViewType(item: User, items: MutableList<User>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(
            ItemUserBinding.bind(parent.inflate(R.layout.item_user)),
            onClick
        )
    }

    override fun onBindViewHolder(
        item: User,
        holder: Holder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class Holder(
        private val binding: ItemUserBinding,
        private val onClick: (id: Long) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentUserId: Long? = null

        init {
            binding.root.setOnClickListener {
                currentUserId?.let { onClick(it) }
            }
        }

        fun bind(item: User) {
            currentUserId = item.id

            with(binding) {
                userItemName.text = item.name
                userItemUserName.text = item.userName
                userItemAvatar.load("https://www.meme-arsenal.com/memes/ad998282fd526298aeb217a8e2ee02b0.jpg") {
                    placeholder(R.drawable.ic_person)
                    transformations(CircleCropTransformation())
                }
            }
        }
    }
}