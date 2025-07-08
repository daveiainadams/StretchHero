package com.dejvik.stretchhero.utils

import android.content.Context
import android.content.res.Resources
import androidx.annotation.DrawableRes

/**
 * Extension function to safely get a drawable resource ID
 */
fun String.getDrawableResourceId(context: Context): Int {
    return context.resources.getIdentifier(
        this,
        "drawable",
        context.packageName
    ).takeIf { it != 0 } ?: 0
}

/**
 * Extension function to format duration in a human-readable format
 */
fun Int.formatDuration(): String {
    return when {
        this < 60 -> "${this}s"
        this < 3600 -> "${this / 60}m ${this % 60}s"
        else -> "${this / 3600}h ${(this % 3600) / 60}m"
    }
}

/**
 * Extension function to capitalize the first letter of a string
 */
fun String.capitalizeFirst(): String {
    return if (isNotEmpty()) {
        this[0].uppercase() + substring(1)
    } else {
        this
    }
}

/**
 * Extension function to check if a string is a valid resource name
 */
fun String.isValidResourceName(): Boolean {
    return matches(Regex("^[a-zA-Z_][a-zA-Z0-9_]*$"))
}

/**
 * Utility object for common app functions
 */
object AppUtils {
    /**
     * Get the app version name
     */
    fun getAppVersion(context: Context): String {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.versionName
        } catch (e: Exception) {
            "Unknown"
        }
    }
    
    /**
     * Get the app version code
     */
    fun getAppVersionCode(context: Context): Int {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.versionCode
        } catch (e: Exception) {
            0
        }
    }
    
    /**
     * Check if the app is running in debug mode
     */
    fun isDebugMode(): Boolean {
        return try {
            Class.forName("android.os.Build")
            android.os.Build.TYPE == "debug"
        } catch (e: Exception) {
            false
        }
    }
} 