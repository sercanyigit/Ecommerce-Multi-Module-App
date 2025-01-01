package com.sercan.ecommerce.common.navigation

enum class DeepLinks(val code: String, val screenName: String) {
    HOME("1", "Ana Sayfa"),
    FAVORITES("2", "Favoriler"),
    NOTIFICATIONS("3", "Bildirimler"),
    PROFILE("4", "Profil"),
    PRODUCT_DETAIL("5", "Ürün Detay");

    companion object {
        fun fromCode(code: String): DeepLinks? = values().find { it.code == code }
    }
} 