package com.example.cryptopulse.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cryptopulse.data.CryptoCoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoDetailScreen(
    coin: CryptoCoin,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(coin.name) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = coin.image,
                contentDescription = coin.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = coin.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = coin.symbol.uppercase(),
                fontSize = 16.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "$${coin.currentPrice ?: 0.0}",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            val priceChange = coin.priceChangePercentage24h ?: 0.0
            val isPositive = priceChange >= 0
            Text(
                text = "${if (isPositive) "+" else ""}${String.format("%.2f", priceChange)}%",
                color = if (isPositive) Color(0xFF4CAF50) else Color(0xFFE53935),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    DetailRow(label = "Market Cap", value = "$${coin.marketCap ?: "N/A"}")
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    DetailRow(label = "24h High", value = "$${coin.high24h ?: "N/A"}")
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    DetailRow(label = "24h Low", value = "$${coin.low24h ?: "N/A"}")
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    DetailRow(label = "Last Updated", value = coin.lastUpdated ?: "N/A")
                }
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color.Gray, fontSize = 14.sp)
        Text(text = value, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
    }
}




