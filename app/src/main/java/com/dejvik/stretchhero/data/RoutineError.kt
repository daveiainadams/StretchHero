package com.dejvik.stretchhero.data

/**
 * Sealed class representing different types of errors that can occur in the routine system.
 * This provides type-safe error handling with specific error types and user-friendly messages.
 */
sealed class RoutineError {
    object NetworkError : RoutineError()
    object RoutineNotFound : RoutineError()
    object InvalidRoutineData : RoutineError()
    object TimerError : RoutineError()
    object TextToSpeechError : RoutineError()
    data class UnknownError(val message: String) : RoutineError()
    
    /**
     * Converts the error to a user-friendly message that can be displayed in the UI.
     * @return Human-readable error message
     */
    fun toUserMessage(): String = when (this) {
        is NetworkError -> "Network connection error. Please check your internet connection and try again."
        is RoutineNotFound -> "The requested routine could not be found. Please select a different routine."
        is InvalidRoutineData -> "The routine data is invalid or corrupted. Please try restarting the app."
        is TimerError -> "Timer error occurred. Please restart the routine."
        is TextToSpeechError -> "Voice guidance is unavailable. You can continue without voice instructions."
        is UnknownError -> "An unexpected error occurred: $message"
    }
    
    /**
     * Returns whether this error should trigger a retry mechanism.
     * @return true if the error is potentially recoverable
     */
    fun isRetryable(): Boolean = when (this) {
        is NetworkError, is TimerError, is TextToSpeechError -> true
        is RoutineNotFound, is InvalidRoutineData, is UnknownError -> false
    }
    
    /**
     * Returns the severity level of the error for logging purposes.
     * @return Error severity level
     */
    fun getSeverity(): ErrorSeverity = when (this) {
        is NetworkError, is TextToSpeechError -> ErrorSeverity.WARNING
        is RoutineNotFound, is InvalidRoutineData -> ErrorSeverity.ERROR
        is TimerError, is UnknownError -> ErrorSeverity.CRITICAL
    }
}

/**
 * Enum representing the severity level of errors.
 */
enum class ErrorSeverity {
    WARNING,
    ERROR,
    CRITICAL
}

/**
 * Extension function to safely execute operations that might throw exceptions
 * and convert them to appropriate RoutineError types.
 */
inline fun <T> safeExecute(operation: () -> T): Result<T> {
    return try {
        Result.success(operation())
    } catch (e: Exception) {
        val error = when (e) {
            is java.net.UnknownHostException, 
            is java.net.SocketTimeoutException,
            is java.io.IOException -> RoutineError.NetworkError
            is IllegalArgumentException,
            is IllegalStateException -> RoutineError.InvalidRoutineData
            else -> RoutineError.UnknownError(e.message ?: "Unknown error occurred")
        }
        Result.failure(RuntimeException(error.toUserMessage()))
    }
}