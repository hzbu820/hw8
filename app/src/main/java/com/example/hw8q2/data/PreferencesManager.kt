package com.example.hw8q2.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "diary_preferences")

class PreferencesManager(private val context: Context) {
    
    private val dataStore = context.dataStore
    
    companion object {
        val THEME_KEY = stringPreferencesKey("theme")
        val FONT_SIZE_KEY = floatPreferencesKey("font_size")
        val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
    }
    
    // Theme preference
    val themeFlow: Flow<String> = dataStore.data.map { preferences ->
        preferences[THEME_KEY] ?: "default"
    }
    
    suspend fun setTheme(theme: String) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme
        }
    }
    
    // Font size preference
    val fontSizeFlow: Flow<Float> = dataStore.data.map { preferences ->
        preferences[FONT_SIZE_KEY] ?: 16f
    }
    
    suspend fun setFontSize(size: Float) {
        dataStore.edit { preferences ->
            preferences[FONT_SIZE_KEY] = size
        }
    }
    
    // Dark mode preference
    val darkModeFlow: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[DARK_MODE_KEY] ?: false
    }
    
    suspend fun setDarkMode(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = enabled
        }
    }
} 