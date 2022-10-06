package com.example.tibber_assignment_byparisa.hilt

import com.example.tibber_assignment_byparisa.repository.PowerUpRepositoryIterface
import com.example.tibber_assignment_byparisa.repository.PowerUpsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class PowerUpModule {
    @Binds
    abstract fun getPowerUpSource(repo: PowerUpsRepository): PowerUpRepositoryIterface
}