package com.example.contactproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import com.example.contactproject.databinding.CardlayoutBinding

class ContactListAdapter(private val onDeleteClickListener: (Int) -> Unit) :
    ListAdapter<Contact, ContactListAdapter.ContactViewHolder>(ContactComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = DataBindingUtil.inflate<CardlayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.cardlayout,
            parent,
            false
        )
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    inner class ContactViewHolder(private val binding: CardlayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: Contact) {
            binding.nameText.text = contact.contactName
            binding.phoneText.text = contact.contactPhone
            binding.deleteButton.setOnClickListener {
                onDeleteClickListener(contact.contactId)
            }
        }
    }

    class ContactComparator : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.contactId == newItem.contactId
        }
    }
}