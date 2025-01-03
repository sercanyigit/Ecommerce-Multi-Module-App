package com.sercan.ecommerce.common.navigation

/**
 * Kullanım:
 * 1. fromCode("home") -> ecommerce://app/home
 * 2. fromCode("cart") -> ecommerce://app/cart
 * 3. fromCode("favorites") -> ecommerce://app/favorites
 * 4. fromCode("notifications") -> ecommerce://app/notifications
 * 5. fromCode("profile") -> ecommerce://app/profile
 * 6. fromCode("product_detail/{productId}") -> ecommerce://app/product_detail/1
 */
object DeepLinks {
    private const val SCHEME = "ecommerce"
    private const val HOST = "app"

    /**
     * Verilen kod için deep link URI'sini oluşturur.
     * @param code Ekran kodu
     * @return Deep link URI
     */
    fun fromCode(code: String) = "$SCHEME://$HOST/$code"

    /**
     * 1. Ana Sayfa
     * Uygulamanın ana sayfasını açar.
     * Örnek: adb shell am start -W -a android.intent.action.VIEW -d "ecommerce://app/home"
     * Eğer bu şekilde açmazsa adb nin tam yolunu belirterek açabilirsin
     * Örnek: ~/Library/Android/sdk/platform-tools/adb shell am start -W -a android.intent.action.VIEW -d "ecommerce://app/home"
     *
     */
    object Home {
        const val CODE = "home"
        const val DEEP_LINK = "$SCHEME://$HOST/$CODE"
    }

    /**
     * 2. Sepetim
     * Kullanıcının sepetini açar.
     * Örnek: adb shell am start -W -a android.intent.action.VIEW -d "ecommerce://app/cart"
     */
    object Cart {
        const val CODE = "cart"
        const val DEEP_LINK = "$SCHEME://$HOST/$CODE"
    }

    /**
     * 3. Favoriler
     * Kullanıcının favori ürünlerini listeler.
     * Örnek: adb shell am start -W -a android.intent.action.VIEW -d "ecommerce://app/favorites"
     */
    object Favorites {
        const val CODE = "favorites"
        const val DEEP_LINK = "$SCHEME://$HOST/$CODE"
    }

    /**
     * 4. Bildirimler
     * Kullanıcının bildirimlerini gösterir.
     * Örnek: adb shell am start -W -a android.intent.action.VIEW -d "ecommerce://app/notifications"
     */
    object Notifications {
        const val CODE = "notifications"
        const val DEEP_LINK = "$SCHEME://$HOST/$CODE"
    }

    /**
     * 5. Profil
     * Kullanıcının profil sayfasını açar.
     * Örnek: adb shell am start -W -a android.intent.action.VIEW -d "ecommerce://app/profile"
     */
    object Profile {
        const val CODE = "profile"
        const val DEEP_LINK = "$SCHEME://$HOST/$CODE"
    }

    /**
     * 6. Ürün Detay
     * Belirli bir ürünün detay sayfasını açar.
     * @param productId Ürün ID'si
     * Örnek: adb shell am start -W -a android.intent.action.VIEW -d "ecommerce://app/product_detail/1"
     * Eğer bu şekilde açmazsa adb nin tam yolunu belirterek açabilirsin
     * Örnek: ~/Library/Android/sdk/platform-tools/adb shell am start -W -a android.intent.action.VIEW -d "ecommerce://app/product_detail/2"
     */
    object ProductDetail {
        const val CODE = "product_detail"
        const val ROUTE = "$CODE/{productId}"
        const val DEEP_LINK = "$SCHEME://$HOST/product_detail/{productId}"

        fun createRoute(productId: Int) = "$CODE/$productId"
        fun createDeepLink(productId: Int) = "$SCHEME://$HOST/$CODE/$productId"
    }
} 