package com.example.giphy_api.model


data class TrendingData(val data : List<list>)
data class list(val images:Images)
data class Images(val fixed_height: FixedHeight)
data class FixedHeight(val url: String)
