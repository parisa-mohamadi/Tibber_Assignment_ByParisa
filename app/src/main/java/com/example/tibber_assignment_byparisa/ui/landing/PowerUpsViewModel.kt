package com.example.tibber_assignment_byparisa.ui.landing

import androidx.lifecycle.ViewModel
import com.example.PowerUpsListQuery
import com.example.tibber_assignment_byparisa.repository.PowerUpRepositoryIterface
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel


@HiltViewModel
class PowerUpsViewModel @Inject constructor(
    private val repository: PowerUpRepositoryIterface,
):ViewModel() {
      fun getPowerUpsList(): List<PowerUpsListQuery.AssignmentDatum>? = repository.getPowerUpsList()


}

