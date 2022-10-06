package com.example.tibber_assignment_byparisa.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient

@Module
@InstallIn(SingletonComponent::class)
object ApolloClient {
    private val GRAPHQL_API_URL = "https://app.tibber.com/v4/gql"
    @Singleton
    @Provides
    fun provideApolloClient():  ApolloClient{
        val logging = HttpLoggingClient.providesHttpLoggingClient()
        var okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(logging)

        return ApolloClient.builder()
            .serverUrl(GRAPHQL_API_URL)
            .okHttpClient(okHttpClient.build())
            .build()
    }


}
