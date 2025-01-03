package com.sercan.ecommerce.common.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferenceKeys {
    val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
    val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
} 