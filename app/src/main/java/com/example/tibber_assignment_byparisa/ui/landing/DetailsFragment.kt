package com.example.tibber_assignment_byparisa.ui.landing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.tibber_assignment_byparisa.R
import com.example.tibber_assignment_byparisa.ui.MainActivity


class DetailsFragment : Fragment(R.layout.fragment_details) {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //get data
        val bundle = this.arguments

        if(bundle !=null){
            val title = bundle!!.getString("title")
            val description = bundle!!.getString("description")
            val longDescription = bundle!!.getString("longDescription")
            val connected = bundle!!.getBoolean("connected")
            val storeUrl = bundle!!.getString("storeUrl")
            val imageUrl = bundle!!.getString("imageUrl")

            //  set data

            (activity as MainActivity?)?.supportActionBar?.setTitle(title)

            val tvTitleDetail: TextView = view.findViewById<TextView>(R.id.tvTitleDetail)
            tvTitleDetail.text=title.toString()
            val tvDescriptionDetail: TextView = view.findViewById<TextView>(R.id.tvDescriptionDetail)
            tvDescriptionDetail.text=description.toString()
            val tvMore: TextView = view.findViewById<TextView>(R.id.tvMore)
            tvMore.text="More about " + title.toString()
            val tvLongDescription: TextView = view.findViewById<TextView>(R.id.tvLongDescription)
            tvLongDescription.text=longDescription.toString()
            val ivImageDetail: ImageView = view.findViewById<ImageView>(R.id.ivImageDetail)
            Glide.with(ivImageDetail)
                .load(imageUrl)
                .into( ivImageDetail)

            val connect_btn: Button = view.findViewById<Button>(R.id.connect_btn)
            val disconnect_btn: Button = view.findViewById<Button>(R.id.disconnect_btn)
            val buy_btn: Button = view.findViewById<Button>(R.id.buy_btn)

            if(connected){
                connect_btn.visibility = View.GONE;
                disconnect_btn.visibility = View.VISIBLE

            }
            if(!connected){
                connect_btn.visibility = View.VISIBLE;
                disconnect_btn.visibility = View.GONE

            }

            connect_btn.setOnClickListener {
                        connect_btn.visibility = View.GONE;
                        disconnect_btn.visibility = View.VISIBLE
            }
            disconnect_btn.setOnClickListener {
                connect_btn.visibility = View.VISIBLE
                disconnect_btn.visibility = View.GONE

            }
            buy_btn.setOnClickListener {
                val url = storeUrl
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
        }else{
            Log.d("taggg", "bundle is null!!!")
        }

        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        // TODO: Use the ViewModel

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            androidx.appcompat.R.id.home -> {

                activity?.onBackPressed()
                activity?.finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}

