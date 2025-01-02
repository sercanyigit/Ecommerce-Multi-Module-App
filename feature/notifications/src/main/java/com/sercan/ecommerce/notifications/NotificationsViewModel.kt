package com.sercan.ecommerce.notifications

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*
import javax.inject.Inject

data class NotificationsUiState(
    val notifications: List<Notification> = emptyList()
)

@HiltViewModel
class NotificationsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationsUiState())
    val uiState: StateFlow<NotificationsUiState> = _uiState.asStateFlow()

    init {
        // Örnek bildirimler
        val sampleNotifications = listOf(
            Notification(
                id = 1,
                title = "Siparişiniz Yolda!",
                message = "123456 numaralı siparişiniz kargoya verildi. Tahmini teslimat süresi 2 gün.",
                type = NotificationType.ORDER,
                timestamp = Date(),
                isRead = false
            ),
            Notification(
                id = 2,
                title = "Özel İndirim Fırsatı",
                message = "Seçili ürünlerde %50'ye varan indirimler sizi bekliyor! Fırsatları kaçırmayın.",
                type = NotificationType.DISCOUNT,
                timestamp = Date(System.currentTimeMillis() - 3600000), // 1 saat önce
                isRead = true
            ),
            Notification(
                id = 3,
                title = "Yeni Ürünler Eklendi",
                message = "Sonbahar koleksiyonumuz mağazamızda yerini aldı. Yeni ürünleri keşfedin!",
                type = NotificationType.PRODUCT,
                timestamp = Date(System.currentTimeMillis() - 7200000), // 2 saat önce
                isRead = false
            ),
            Notification(
                id = 4,
                title = "Sistem Bakımı",
                message = "Yarın 03:00-05:00 saatleri arasında sistem bakımı yapılacaktır.",
                type = NotificationType.SYSTEM,
                timestamp = Date(System.currentTimeMillis() - 86400000), // 1 gün önce
                isRead = true
            )
        )
        _uiState.update { it.copy(notifications = sampleNotifications) }
    }

    fun markAsRead(notificationId: Int) {
        _uiState.update { state ->
            val updatedNotifications = state.notifications.map { notification ->
                if (notification.id == notificationId) {
                    notification.copy(isRead = true)
                } else {
                    notification
                }
            }
            state.copy(notifications = updatedNotifications)
        }
    }

    fun markAllAsRead() {
        _uiState.update { state ->
            val updatedNotifications = state.notifications.map { it.copy(isRead = true) }
            state.copy(notifications = updatedNotifications)
        }
    }

    fun deleteNotification(notificationId: Int) {
        _uiState.update { state ->
            val updatedNotifications = state.notifications.filterNot { it.id == notificationId }
            state.copy(notifications = updatedNotifications)
        }
    }

    fun clearAllNotifications() {
        _uiState.update { it.copy(notifications = emptyList()) }
    }
} 