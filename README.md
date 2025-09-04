# Onboarding Animation Implementation

A modern Android application implementing an engaging onboarding animation sequence using Jetpack Compose, following MVVM architecture and Material Design 3 principles.

## 🏗️ Architecture

This project follows **MVVM (Model-View-ViewModel)** architecture with the following components:

- **Model**: Data classes representing API responses (`OnboardingResponse.kt`)
- **View**: Jetpack Compose UI components (`MainActivity.kt`)
- **ViewModel**: Business logic and state management (`OnboardingViewModel.kt`)
- **Repository**: Data layer abstraction (`OnboardingRepository.kt`)

## 🛠️ Tech Stack

- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM with Hilt DI
- **Networking**: Retrofit + OkHttp + Gson
- **Image Loading**: Coil
- **Dependency Injection**: Hilt
- **Asynchronous Programming**: Kotlin Coroutines + Flow
- **Animation**: Jetpack Compose Animation APIs

## 🎯 Features

### Core Functionality
- ✅ Welcome screen with animated title and subtitle
- ✅ Sequential card animations with tilt effects
- ✅ Smooth transitions between collapsed and expanded states
- ✅ Dynamic content loading from API
- ✅ Error handling with retry mechanism
- ✅ Final CTA screen with save button

### Animation Details
- **Card Stacking**: Multiple cards stacked with depth effect
- **Tilt Animation**: Cards tilt during transition
- **Scale Animation**: Smooth scaling with spring physics
- **Text Transitions**: Animated text changes between states
- **Gradient Backgrounds**: Dynamic gradient colors from API

## 🚀 Setup Instructions

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 8 or higher
- Android SDK API 24+ (Android 7.0)

### Installation Steps

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd devlop/onboarding_screen
   ```

2. **Open in Android Studio**:
   - Open Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned directory and select it

3. **Sync project**:
   - Android Studio will automatically sync Gradle
   - Wait for the build to complete

4. **Run the app**:
   - Connect an Android device or start an emulator
   - Click the "Run" button or press `Shift + F10`

### Dependencies
All dependencies are managed through Gradle and will be automatically downloaded during the first build.

## 📁 Project Structure

```
app/
├── src/main/java/com/example/onboardinganimation/
│   ├── OnboardingApplication.kt          # Hilt Application class
│   ├── MainActivity.kt                   # Main Activity with Compose UI
│   ├── OnboardingViewModel.kt            # ViewModel with business logic
│   ├── OnboardingRepository.kt           # Repository and API service
│   ├── OnboardingResponse.kt             # Data classes for API response
│   ├── NetworkModule.kt                  # Hilt DI module for networking
│   └── Theme.kt                          # Material Design theme
├── src/main/res/
│   └── AndroidManifest.xml              # App manifest
└── build.gradle.kts                     # Module dependencies
```

## 🔧 Customization

### API Endpoint
Change the base URL in `NetworkModule.kt`:
```kotlin
.baseUrl("https://myjar.app/")
```

## 🎯 SOLID Principles Implementation

- **Single Responsibility**: Each class has one clear purpose
- **Open/Closed**: Extensions possible without modification
- **Liskov Substitution**: Repository interface allows substitution
- **Interface Segregation**: Focused, minimal interfaces
- **Dependency Inversion**: High-level modules depend on abstractions

## 🔍 Code Quality Features

- Clean, readable code with meaningful variable names
- Proper error handling with user-friendly messages
- Efficient resource management
- Modern Kotlin coroutines usage
- Comprehensive documentation
- Type-safe API models with Gson serialization

## 📱 Supported Android Versions

- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **Compile SDK**: API 34

## 🐛 Troubleshooting

### Common Issues

1. **Build errors**: Ensure you're using the correct Android Studio version
2. **Network issues**: Check internet connection and API availability
3. **Animation stuttering**: Test on a physical device for better performance
4. **Image loading failures**: Verify internet connection and image URLs

### Debug Tips
- Enable network logging in `NetworkModule.kt`
- Use Android Studio's Layout Inspector for UI debugging
- Check Logcat for detailed error messages

## App Link
https://drive.google.com/file/d/1AxUO_gnaT-MA2m1kS04-aYUoYtcGWYr0/view?usp=sharing
