package com.example.tibber_assignment_byparisa.repository

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.example.PowerUpsListQuery
import kotlinx.coroutines.*
import javax.inject.Inject



class PowerUpsRepository @Inject constructor(
    private var client: ApolloClient
): PowerUpRepositoryIterface{

    var response:List<PowerUpsListQuery.AssignmentDatum>?=null

    suspend fun fetch(): List<PowerUpsListQuery.AssignmentDatum>? {

        response = try {
            client.query(PowerUpsListQuery()).execute().data?.assignmentData
        } catch (e: ApolloException) {
            Log.d("taggg","repositroy in catch")
            null
        }
        Log.d("taggg","repositroy in try"+ response)
        return response

    }
    fun deffered(): Deferred<Result<List<PowerUpsListQuery.AssignmentDatum>?>> {
        return GlobalScope.async {
            kotlin.runCatching { fetch() }
        }
    }
    override fun getPowerUpsList() : List<PowerUpsListQuery.AssignmentDatum>?
    {
        runBlocking { val result=deffered().await()

            Log.d("taggg","repositroy result: "+ result)
            return@runBlocking
        }


        return response
    }
}

