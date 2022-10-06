package com.example.tibber_assignment_byparisa.ui.landing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.PowerUpsListQuery
import com.example.tibber_assignment_byparisa.R
import kotlinx.android.synthetic.main.item_power_ups.view.*

class ActivePowerUpsAdapter :RecyclerView.Adapter<ActivePowerUpsAdapter.PowerUpsViewHolder>() {

    private var powerUps: List<PowerUpsListQuery.AssignmentDatum>? = null
    var onItemClick: ((PowerUpsListQuery.AssignmentDatum) -> Unit)? = null

    fun setPowerUps(powerUps: List<PowerUpsListQuery.AssignmentDatum>) {
        this.powerUps = powerUps
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PowerUpsViewHolder {
        return PowerUpsViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        if (powerUps == null) return 0
        else return powerUps?.size!!
    }

    override fun onBindViewHolder(holder: PowerUpsViewHolder, position: Int) {
        val powerUp = powerUps?.get(position)
        if (powerUp != null) {
            holder.bind(powerUp)
            holder.itemView.setOnClickListener {
                onItemClick?.invoke(powerUp)
            }
        }
    }


    class PowerUpsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(powerUp: PowerUpsListQuery.AssignmentDatum) {
            itemView.apply {
                    Glide.with(ivImage)
                        .load(powerUp.imageUrl)
                        .into(ivImage)
                    tvTitle.text = powerUp.title
                    tvDescription.text = powerUp.description
            }
        }

        companion object {
            fun from(parent: ViewGroup): PowerUpsViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val itemView = inflater.inflate(R.layout.item_power_ups, parent, false)
                return PowerUpsViewHolder(itemView)
            }
        }
    }

}