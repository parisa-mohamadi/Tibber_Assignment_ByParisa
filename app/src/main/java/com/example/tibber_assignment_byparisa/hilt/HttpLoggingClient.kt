package com.example.tibber_assignment_byparisa.hilt

import okhttp3.logging.HttpLoggingInterceptor

object HttpLoggingClient {

    fun providesHttpLoggingClient() : HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging

    }
}