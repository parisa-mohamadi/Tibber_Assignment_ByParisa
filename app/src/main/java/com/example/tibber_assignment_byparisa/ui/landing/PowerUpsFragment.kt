package com.example.tibber_assignment_byparisa.ui.landing


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.PowerUpsListQuery
import com.example.tibber_assignment_byparisa.R
import com.example.tibber_assignment_byparisa.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_power_ups.*


@AndroidEntryPoint
class PowerUpsFragment : Fragment(R.layout.fragment_power_ups) {

    companion object {
        fun newInstance() = PowerUpsFragment()
    }

    private lateinit var powerUpsAdapter: PowerUpsAdapter
    private lateinit var activePowerUpsAdapter: ActivePowerUpsAdapter
    private val powerUpsViewModel by viewModels<PowerUpsViewModel>()

    var activePowerUpsList: MutableList<PowerUpsListQuery.AssignmentDatum> = mutableListOf()
    var availablePowerUpsList: MutableList<PowerUpsListQuery.AssignmentDatum> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity?)?.supportActionBar?.setTitle("Power-ups")

        activePowerUpsAdapter = ActivePowerUpsAdapter()
        rvActivePowerUps.layoutManager = LinearLayoutManager(requireContext())
        rvActivePowerUps.adapter = activePowerUpsAdapter

        powerUpsAdapter = PowerUpsAdapter()
        rvPowerUps.layoutManager = LinearLayoutManager(requireContext())
        rvPowerUps.adapter = powerUpsAdapter

        rvPowerUps.setNestedScrollingEnabled(false);
        rvActivePowerUps.setNestedScrollingEnabled(false);


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val powerups = powerUpsViewModel.getPowerUpsList()

        Log.d("taggg", "Powerups are:" + powerups)


        if (powerups != null) {
            for (item in powerups) {
                if (item.connected == true) {
                    activePowerUpsList.add(item)
                    activePowerUpsAdapter.setPowerUps(activePowerUpsList)
                } else {
                    availablePowerUpsList.add(item)
                    powerUpsAdapter.setPowerUps(availablePowerUpsList)
                }
            }

            Log.d("taggg", "activePowerUpsList:" + activePowerUpsList.size)
        }

        powerUpsAdapter.onItemClick = {

            val bundle = Bundle()
            bundle.putString("title", it.title)
            bundle.putString("description", it.description)
            bundle.putString("longDescription", it.longDescription)
            bundle.putBoolean("connected", it.connected)
            bundle.putString("storeUrl", it.storeUrl)
            bundle.putString("imageUrl", it.imageUrl)

            val detailsFragment = DetailsFragment()

            Log.d("taggg", "bundle is:" + bundle.toString())

            detailsFragment.arguments = bundle

            fragmentManager?.beginTransaction()?.addToBackStack(null)
                ?.replace(R.id.nav_host_fragment, detailsFragment)
                ?.commit()
            clearList()
        }

        activePowerUpsAdapter.onItemClick = {

            val bundle = Bundle()
            bundle.putString("title", it.title)
            bundle.putString("description", it.description)
            bundle.putString("longDescription", it.longDescription)
            bundle.putBoolean("connected", it.connected)
            bundle.putString("storeUrl", it.storeUrl)
            bundle.putString("imageUrl", it.imageUrl)

            val detailsFragment = DetailsFragment()

            Log.d("taggg", "bundle is:" + bundle.toString())

            detailsFragment.arguments = bundle

            fragmentManager?.beginTransaction()?.addToBackStack(null)
                ?.replace(R.id.nav_host_fragment, detailsFragment)
                ?.commit()
            clearList()
//            val navHostFragment = getActivity()?.getSupportFragmentManager()?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//            val navController = navHostFragment.navController
//
//            navController.navigate(R.id.detailsFragment)


        }
    }

    fun clearList() {
        activePowerUpsList.clear()
        availablePowerUpsList.clear()
    }
}