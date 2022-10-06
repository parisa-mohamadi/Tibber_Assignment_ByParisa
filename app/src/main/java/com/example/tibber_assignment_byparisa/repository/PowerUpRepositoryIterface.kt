package com.example.tibber_assignment_byparisa.repository


import com.example.PowerUpsListQuery


interface PowerUpRepositoryIterface {

    fun getPowerUpsList(): List<PowerUpsListQuery.AssignmentDatum>?
}