package com.example.hw8q2.data

import android.content.Context
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DiaryEntryManager(private val context: Context) {
    
    private val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    
    /**
     * Save a diary entry for a specific date
     */
    fun saveEntry(date: Date, content: String): Boolean {
        val fileName = getFileNameForDate(date)
        return try {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { outputStream ->
                outputStream.write(content.toByteArray())
            }
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }
    
    /**
     * Read a diary entry for a specific date
     */
    fun getEntry(date: Date): String {
        val fileName = getFileNameForDate(date)
        return try {
            context.openFileInput(fileName).bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            // Return empty string if no entry exists
            ""
        }
    }
    
    /**
     * Check if an entry exists for a specific date
     */
    fun entryExists(date: Date): Boolean {
        val fileName = getFileNameForDate(date)
        val file = File(context.filesDir, fileName)
        return file.exists()
    }
    
    /**
     * Delete a diary entry for a specific date
     */
    fun deleteEntry(date: Date): Boolean {
        val fileName = getFileNameForDate(date)
        val file = File(context.filesDir, fileName)
        return if (file.exists()) {
            file.delete()
        } else {
            false
        }
    }
    
    /**
     * Get all available diary entry dates
     */
    fun getAllEntryDates(): List<Date> {
        val entries = mutableListOf<Date>()
        val fileNamePrefix = "diary_entry_"
        
        context.filesDir.listFiles()?.forEach { file ->
            if (file.name.startsWith(fileNamePrefix)) {
                try {
                    val dateString = file.name.substring(fileNamePrefix.length)
                    val date = formatter.parse(dateString)
                    if (date != null) {
                        entries.add(date)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        
        return entries.sortedDescending()
    }
    
    /**
     * Generate a file name based on the date
     */
    private fun getFileNameForDate(date: Date): String {
        val dateString = formatter.format(date)
        return "diary_entry_$dateString"
    }
} 