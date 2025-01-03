package com.sercan.ecommerce.profile

import androidx.lifecycle.ViewModel
import com.sercan.ecommerce.ui.theme.ThemeManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val themeManager: ThemeManager
) : ViewModel() {

    val isDarkMode: Flow<Boolean> = themeManager.isDarkMode

    suspend fun setDarkMode(isDarkMode: Boolean) {
        themeManager.setDarkMode(isDarkMode)
    }
}