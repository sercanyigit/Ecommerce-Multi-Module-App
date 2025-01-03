package com.sercan.ecommerce.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.sercan.ecommerce.common.datastore.PreferenceKeys

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _currentPage = MutableStateFlow(0)
    val currentPage = _currentPage.asStateFlow()

    fun updateCurrentPage(page: Int) {
        _currentPage.value = page
    }

    fun setOnboardingCompleted() {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[PreferenceKeys.ONBOARDING_COMPLETED] = true
            }
        }
    }
} 