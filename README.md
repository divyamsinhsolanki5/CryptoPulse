# CryptoPulse - Cryptocurrency Price Tracker

A native Android application built using Kotlin and Jetpack Compose that tracks cryptocurrency prices, details, and market metrics in real-time using the CoinGecko Public API.

---

## 🚀 Features & Required Functionality

- **Cryptocurrency List:** Displays live coins with current price and 24-hour price change percentage.
- **Search:** Instant search filtering by name or symbol.
- **Pull-to-Refresh:** Swipe down to update live prices.
- **UI States:**
    - Loading State (Shimmer/Progress Indicator)
    - Error State with Retry Button
    - Empty State (When search returns no results)
- **Detail Screen:** Displays Market Capitalization, Daily High, Daily Low, Price Change %, and Last Updated Time.
- **Image Loading & Caching:** Uses Coil for efficient image loading and memory caching.

---

## 🛠 Tech Stack & Architecture

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose with Material 3
- **Architecture:** MVVM (Model-View-ViewModel) with Clean Architecture & Repository pattern
- **Asynchronous Operations:** Kotlin Coroutines & StateFlow
- **Dependency Injection:** Dagger Hilt
- **Networking:** Retrofit 2 & Gson Converter
- **Image Loading:** Coil Compose

### Why these libraries were selected?
- **Dagger Hilt:** Standard dependency injection library for Android, ensuring loose coupling and testability.
- **Retrofit & Gson:** High-performance REST API handling and seamless JSON parsing.
- **Coil:** Lightweight, fast image loader optimized for Jetpack Compose.

---

## ⚙️ Setup & Installation Instructions

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/divyamsinhsolanki5/CryptoPulse.git](https://github.com/divyamsinhsolanki5/CryptoPulse.git)