package com.example.atlmovaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovaapp.R
import com.example.atlmovaapp.databinding.ItemPaymentCardBinding
import com.example.atlmovaapp.model.database.CardModel

class CardAdapter(
    private val onItemClick: (CardModel) -> Unit
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    val cards = arrayListOf<CardModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemPaymentCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = cards[position]
        holder.binding.card = item

        // Kart şəkili
        if (item.isDefault) {
            holder.binding.cardName.text = item.name
            when (item.name) {
                "PayPal" -> holder.binding.cardImage.setImageResource(R.drawable.paypalicon)
                "Google Pay" -> holder.binding.cardImage.setImageResource(R.drawable.googleicon)
                "Apple Pay" -> holder.binding.cardImage.setImageResource(R.drawable.appleicon)
            }
        } else {
            holder.binding.cardImage.setImageResource(R.drawable.movamastercard)
            val last4Digits = if (item.number.length >= 4) item.number.takeLast(4) else item.number
            holder.binding.cardName.text = "**** **** **** $last4Digits"
        }

        // Radio button statusu
        holder.binding.imageViewRadioFalse.setImageResource(
            if (item.selected) R.drawable.radiotrue else R.drawable.radiofalse
        )

        // Kart klik listener
        holder.binding.root.setOnClickListener {
            // Bütün kartları deaktiv et
            cards.forEach { it.selected = false }
            // Kliklənən kartı aktiv et
            item.selected = true
            notifyDataSetChanged()
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = cards.size

    fun updateList(newList: List<CardModel>) {
        cards.clear()
        cards.addAll(newList)
        notifyDataSetChanged()
    }

    class CardViewHolder(val binding: ItemPaymentCardBinding) :
        RecyclerView.ViewHolder(binding.root)
}
