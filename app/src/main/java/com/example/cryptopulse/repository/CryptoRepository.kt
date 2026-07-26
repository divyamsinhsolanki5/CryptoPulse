package com.example.cryptopulse.repository

import com.example.cryptopulse.data.ApiService
import com.example.cryptopulse.data.CryptoCoin
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getCoins(): Result<List<CryptoCoin>> {
        return try {
            val response = apiService.getCryptoCoins()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

