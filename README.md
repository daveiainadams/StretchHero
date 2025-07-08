# StretchHero - Android Stretching App

A modern Android app built with Jetpack Compose that helps users perform guided stretching routines with voice instructions and visual guidance.

## Features

- **Guided Stretching Routines**: Pre-built routines for different purposes (morning wake-up, office relief, post-workout, etc.)
- **Voice Instructions**: Text-to-speech guidance for each stretch
- **Visual Timer**: Animated countdown timer for each stretch
- **Progress Tracking**: Visual progress indicator showing current step and total progress
- **Multiple Difficulty Levels**: Routines categorized by difficulty (Beginner, Intermediate, Advanced)
- **Modern UI**: Built with Material Design 3 and Jetpack Compose

## Recent Improvements

### Performance Optimizations
- **State Management**: Migrated from multiple `MutableState` to single `StateFlow` for better performance and consistency
- **Dependency Management**: Properly configured Compose BOM for version consistency
- **Memory Management**: Improved TextToSpeech lifecycle management with proper cleanup
- **Build Optimization**: Enabled ProGuard for release builds with optimized rules

### Code Quality Improvements
- **Data Models**: Enhanced with validation, computed properties, and better structure
- **Error Handling**: Comprehensive error handling with user-friendly error states
- **Loading States**: Added proper loading indicators and state management
- **Utility Functions**: Created reusable extension functions and utilities
- **Type Safety**: Better null safety and validation throughout the codebase

### UI/UX Enhancements
- **Better Typography**: Improved text styles with proper font weights and spacing
- **Enhanced Cards**: More informative routine cards with difficulty levels and descriptions
- **Error Recovery**: Retry mechanisms and clear error messages
- **Accessibility**: Better content descriptions and navigation

### Architecture Improvements
- **ViewModel Pattern**: Proper separation of concerns with improved state management
- **Resource Management**: Better handling of drawable resources with fallbacks
- **Navigation**: Cleaner navigation patterns with proper back stack management
- **Lifecycle Awareness**: Proper cleanup of resources and timers

## Technical Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM with ViewModel
- **Navigation**: Navigation Compose
- **State Management**: StateFlow and Compose State
- **Text-to-Speech**: Android TTS API
- **Build System**: Gradle with Kotlin DSL

## Project Structure

```
app/src/main/java/com/dejvik/stretchhero/
├── data/
│   ├── models.kt          # Data classes for Routine and StretchStep
│   └── RoutineData.kt     # Data source with predefined routines
├── navigation/
│   ├── Screen.kt          # Navigation routes
│   └── StretchHeroNavHost.kt # Navigation setup
├── ui/
│   ├── screens/
│   │   ├── HomeScreen.kt      # Welcome screen
│   │   ├── StretchLibraryScreen.kt # Routine selection
│   │   ├── StretchRoutineScreen.kt # Active routine execution
│   │   └── RoutineViewModel.kt     # Business logic
│   └── theme/
│       ├── Color.kt       # Color definitions
│       ├── Theme.kt       # Material theme setup
│       └── Type.kt        # Typography definitions
└── utils/
    ├── TextToSpeechHelper.kt # TTS functionality
    └── Extensions.kt      # Utility functions
```

## Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 24+ (API level 24)
- Kotlin 1.9.0+

### Installation
1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Build and run the app

### Building for Release
```bash
./gradlew assembleRelease
```

## Key Improvements Made

### 1. State Management
- **Before**: Multiple `MutableState` objects scattered throughout ViewModel
- **After**: Single `StateFlow` with comprehensive `RoutineUiState` data class
- **Benefit**: Better performance, consistency, and easier testing

### 2. Error Handling
- **Before**: Basic error handling with limited user feedback
- **After**: Comprehensive error states with retry mechanisms and user-friendly messages
- **Benefit**: Better user experience and easier debugging

### 3. Resource Management
- **Before**: Direct resource ID lookups without fallbacks
- **After**: Utility functions with proper fallbacks and validation
- **Benefit**: More robust app that handles missing resources gracefully

### 4. Performance
- **Before**: Inefficient timer implementation and memory leaks
- **After**: Optimized timer with proper lifecycle management and memory cleanup
- **Benefit**: Better battery life and app stability

### 5. Code Organization
- **Before**: Duplicate code and inconsistent patterns
- **After**: Reusable utilities, consistent patterns, and better separation of concerns
- **Benefit**: Easier maintenance and future development

## Future Enhancements

- [ ] User progress tracking and statistics
- [ ] Custom routine creation
- [ ] Integration with fitness trackers
- [ ] Offline mode support
- [ ] Dark/light theme toggle
- [ ] Accessibility improvements
- [ ] Unit and UI tests

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 