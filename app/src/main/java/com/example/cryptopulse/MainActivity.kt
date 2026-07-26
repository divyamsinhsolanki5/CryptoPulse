package com.example.cryptopulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptopulse.data.CryptoCoin
import com.example.cryptopulse.presentation.CryptoDetailScreen
import com.example.cryptopulse.presentation.CryptoListScreen
import com.example.cryptopulse.presentation.CryptoViewModel
import com.example.cryptopulse.ui.theme.CryptoPulseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoPulseTheme {
                CryptoAppNav()
            }
        }
    }
}

@Composable
fun CryptoAppNav(
    viewModel: CryptoViewModel = hiltViewModel()
) {
    var selectedCoin by remember { mutableStateOf<CryptoCoin?>(null) }

    // System Back Button Gesture handling
    if (selectedCoin != null) {
        BackHandler {
            selectedCoin = null // System Back press par detail screen thi list screen par lai jase
        }
    }

    if (selectedCoin == null) {
        CryptoListScreen(
            viewModel = viewModel,
            onCoinClick = { coin ->
                selectedCoin = coin
            }
        )
    } else {
        CryptoDetailScreen(
            coin = selectedCoin!!,
            onBackClick = {
                selectedCoin = null // Top AppBar na back arrow par click karva thi list screen par lai jase
            }
        )
    }
}