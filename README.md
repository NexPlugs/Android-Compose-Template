# Android Compose Template

A modern Android application template built with **Jetpack Compose**, following **Clean Architecture** principles. This template provides a solid foundation for building scalable Android applications with type-safe navigation, dependency injection, networking, local data persistence, and more.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Modules](#modules)
- [Getting Started](#getting-started)
- [Technology Stack](#technology-stack)
- [Build System](#build-system)
- [Usage Examples](#usage-examples)
- [Testing](#testing)
- [Contributing](#contributing)

## 🎯 Overview

This template is designed to help developers quickly start building Android applications with:
- **Modern Android Development**: Jetpack Compose, Kotlin, Coroutines
- **Clean Architecture**: Separation of concerns with clear module boundaries
- **Type-Safe Navigation**: Navigation3 with sealed classes
- **Dependency Injection**: Hilt for DI
- **Networking**: Retrofit with custom API response handling
- **Local Storage**: Room database and DataStore Preferences
- **Modular Design**: Feature-based and core module structure

## ✨ Features

### Core Features

- ✅ **Jetpack Compose UI** - Modern declarative UI framework
- ✅ **Navigation3** - Type-safe navigation with sealed classes
- ✅ **Hilt Dependency Injection** - Clean DI setup
- ✅ **Retrofit Networking** - Custom API response wrapper
- ✅ **Room Database** - Local SQLite database with caching
- ✅ **DataStore Preferences** - Encrypted key-value storage
- ✅ **Kotlin Coroutines** - Async operations and Flow support
- ✅ **Kotlinx Serialization** - JSON serialization/deserialization
- ✅ **Material Design 3** - Modern Material Design components
- ✅ **Modular Architecture** - Feature-based module structure

### Architecture Features

- ✅ **Clean Architecture** - Clear separation of layers
- ✅ **Repository Pattern** - Data abstraction layer
- ✅ **MVVM Pattern** - ViewModel for state management
- ✅ **Type-Safe State Management** - Custom StateFlow implementation
- ✅ **Error Handling** - Comprehensive error handling system
- ✅ **Offline Support** - Local data caching and persistence

## 🏗️ Architecture

The project follows **Clean Architecture** principles with clear separation of concerns:

```
┌─────────────────────────────────────────────────────────┐
│                      Presentation Layer                   │
│  (UI, ViewModels, Composables)                            │
└──────────────────────┬──────────────────────────────────┘
                       │
┌──────────────────────▼──────────────────────────────────┐
│                      Domain Layer                         │
│  (Use Cases, Business Logic, Models)                     │
└──────────────────────┬──────────────────────────────────┘
                       │
┌──────────────────────▼──────────────────────────────────┐
│                      Data Layer                          │
│  (Repositories, Data Sources, Network, Database)          │
└─────────────────────────────────────────────────────────┘
```

### Layer Responsibilities

1. **Presentation Layer** (`app`, `features`)
   - UI components (Composables)
   - ViewModels
   - Navigation
   - User interactions

2. **Domain Layer** (`core:model`, `core:common`)
   - Business logic
   - Domain models
   - Use cases (if needed)
   - Common utilities

3. **Data Layer** (`core:data`, `core:network`, `core:database`, `core:datastore`)
   - Repository implementations
   - Network services
   - Local database
   - Data sources

## 📁 Project Structure

```
AndroidComposeTemplate/
├── app/                          # Main application module
│   ├── src/main/
│   │   ├── java/com/example/androidcomposetemplate/
│   │   │   ├── AndroidApplication.kt      # Hilt application
│   │   │   ├── MainActivity.kt            # Main activity
│   │   │   ├── navigation/
│   │   │   │   └── AppNavHost.kt          # Navigation setup
│   │   │   └── ui/
│   │   │       └── ApplicationMain.kt     # Main composable
│   │   └── res/                            # Resources
│   └── build.gradle.kts
│
├── build-logic/                  # Gradle convention plugins
│   └── convention/
│       └── src/main/kotlin/
│           ├── AndroidApplicationConventionPlugin.kt
│           ├── AndroidLibraryConventionPlugin.kt
│           ├── AndroidHiltConventionPlugin.kt
│           └── compose/
│
├── core/                         # Core modules
│   ├── common/                   # Common utilities and extensions
│   ├── data/                     # Data layer (repositories)
│   ├── database/                 # Room database
│   ├── datastore/                # DataStore preferences
│   ├── designsystem/             # Design system and theme
│   ├── model/                    # Domain models
│   ├── navigation/               # Navigation setup
│   ├── network/                  # Networking layer
│   ├── test/                     # Testing utilities
│   ├── ui/                       # UI components
│   └── viewmodel/                # Base ViewModel
│
├── features/                     # Feature modules
│   └── auth/                     # Authentication feature
│
├── gradle/                       # Gradle configuration
│   ├── libs.versions.toml        # Version catalog
│   └── wrapper/
│
├── settings.gradle.kts            # Project settings
├── build.gradle.kts              # Root build file
└── README.md                     # This file
```

## 📦 Modules

### App Module (`app`)

The main application module that ties everything together.

**Key Components:**
- `AndroidApplication` - Hilt application class
- `MainActivity` - Main activity with Compose setup
- `AppNavHost` - Navigation host configuration
- `ApplicationMain` - Root composable

**Dependencies:**
- All feature modules
- Core modules (navigation, designsystem, common)

### Core Modules

#### `core:common`
Common utilities, extensions, and shared code.

**Features:**
- Extension functions
- Common utilities
- Event handling
- Exception handling

#### `core:model`
Domain models shared across the application.

**Features:**
- Data classes for domain models
- Enums for state management
- Shared model definitions

#### `core:network`
Networking layer with Retrofit and custom API response handling.

**Features:**
- Custom `ApiResponse` sealed interface
- Retrofit services
- Response mappers
- Error handling
- Retry mechanisms
- Global operators

**Key Components:**
- `ApiResponse<T>` - Type-safe response wrapper
- `NetworkModule` - Hilt module for network dependencies
- `AuthService` - Example service interface
- Response transformers and mappers

#### `core:data`
Data layer implementing repository pattern.

**Features:**
- Repository interfaces
- Repository implementations
- Data mappers
- Integration with network and local storage

**Key Components:**
- `AuthRepository` - Example repository interface
- `AuthRepositoryImpl` - Repository implementation
- Mappers for data transformation

#### `core:database`
Room database for local data persistence.

**Features:**
- Room database setup
- Entities and DAOs
- Repository with caching
- Entity-Domain model mappers

**Key Components:**
- `AppDatabase` - Room database
- `UserEntity` - Example entity
- `UserDao` - Data access object
- `UserRoomRepository` - Repository with LRU cache

#### `core:datastore`
DataStore Preferences for key-value storage with encryption.

**Features:**
- Encrypted token storage
- Onboarding state management
- CryptoManager for encryption
- Flow-based reactive streams

**Key Components:**
- `TokenDataSource` - Token storage interface
- `OnboardingDataSource` - Onboarding state interface
- `CryptoManager` - AES encryption manager
- DataStore implementations

#### `core:navigation`
Type-safe navigation using Navigation3.

**Features:**
- Navigator interface
- AppNavigator implementation
- ApplicationScreen sealed class
- CompositionLocal integration

**Key Components:**
- `Navigator` - Navigation interface
- `AppNavigator` - Navigation implementation
- `ApplicationScreen` - Screen definitions
- `LocalComposeNavigator` - Compose integration

#### `core:viewmodel`
Base ViewModel with state management.

**Features:**
- BaseViewModel class
- ViewModelStateFlow for scoped state
- ViewModelKey for instance identification

**Key Components:**
- `BaseViewModel` - Base class for ViewModels
- `ViewModelStateFlow<T>` - Custom StateFlow
- `ViewModelKey` - Key for ViewModel instances

#### `core:designsystem`
Design system and theming.

**Features:**
- Material Design 3 theme
- Color schemes
- Typography
- Component styles

#### `core:ui`
Reusable UI components.

**Features:**
- Common composables
- UI utilities
- Shared components

#### `core:test`
Testing utilities and helpers.

**Features:**
- Test utilities
- Mock helpers
- Testing extensions

### Feature Modules

#### `features:auth`
Authentication feature module.

**Features:**
- Auth screen
- Auth ViewModel
- Auth components

**Structure:**
- Feature-specific screens
- Feature-specific ViewModels
- Feature-specific components

### Build Logic (`build-logic`)

Gradle convention plugins for consistent build configuration.

**Plugins:**
- `convention.android.application` - Application module conventions
- `convention.android.library` - Library module conventions
- `convention.android.compose` - Compose setup
- `convention.android.hilt` - Hilt setup

## 🚀 Getting Started

### Prerequisites

- **Android Studio** Hedgehog (2023.1.1) or later
- **JDK** 17 or later
- **Android SDK** API 24+ (minimum), API 34+ (target)
- **Gradle** 8.10.1+

### Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd AndroidComposeTemplate
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory
   - Wait for Gradle sync to complete

3. **Configure Application**
   - Update `applicationId` in `app/build.gradle.kts` if needed
   - Update package names if needed
   - Configure signing if needed for release builds

4. **Run the Application**
   - Connect an Android device or start an emulator
   - Click "Run" or press `Shift+F10`
   - The app should build and launch

### Configuration

#### Update Base URL

Edit `core/network/src/main/java/com/example/core/network/di/NetworkModule.kt`:

```kotlin
fun provideRetrofit(...): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://your-api-url.com/") // Update this
        // ...
}
```

#### Update Database Name

Edit `core/database/src/main/java/com/goz247/database/di/DatabaseModule.kt`:

```kotlin
fun provideAppDatabase(...): AppDatabase {
    return Room
        .databaseBuilder(application, AppDatabase::class.java, "your_database_name")
        // ...
}
```

## 🛠️ Technology Stack

### Core Technologies

- **Kotlin** 2.2.21 - Programming language
- **Jetpack Compose** 1.9.5 - UI framework
- **Material Design 3** 1.4.0 - Design system
- **Kotlin Coroutines** 1.9.0 - Async programming
- **Kotlinx Serialization** 1.6.0 - JSON serialization

### Android Jetpack

- **Navigation3** 1.0.0 - Type-safe navigation
- **Room** 2.6.1 - Local database
- **DataStore** 1.1.0 - Preferences storage
- **Lifecycle** 2.10.0 - Lifecycle-aware components
- **ViewModel** - State management

### Dependency Injection

- **Hilt** 2.51.1 - Dependency injection framework
- **KSP** 2.2.21-2.0.4 - Kotlin Symbol Processing

### Networking

- **Retrofit** 2.9.0 - HTTP client
- **OkHttp** 4.11.0 - HTTP client library
- **Kotlinx Serialization Converter** 0.8.0 - JSON converter

### Build Tools

- **Android Gradle Plugin** 8.10.1
- **Gradle** 8.10.1+
- **KSP** - Code generation

## 📝 Usage Examples

### Creating a New Feature

1. **Create feature module** in `features/` directory
2. **Add to settings.gradle.kts**:
   ```kotlin
   include(":features:yourfeature")
   ```
3. **Create feature structure**:
   ```
   features/yourfeature/
   ├── build.gradle.kts
   └── src/main/java/com/example/yourfeature/
       ├── YourFeatureScreen.kt
       ├── YourFeatureViewModel.kt
       └── component/
   ```
4. **Add screen to ApplicationScreen**:
   ```kotlin
   @Serializable
   data object YourFeature: ApplicationScreen()
   ```
5. **Add to AppNavHost**:
   ```kotlin
   entry<ApplicationScreen.YourFeature> { YourFeatureScreen() }
   ```

### Adding a New Screen

1. **Add to ApplicationScreen**:
   ```kotlin
   sealed class ApplicationScreen: NavKey {
       @Serializable
       data class YourScreen(val param: String): ApplicationScreen()
   }
   ```
2. **Create screen composable**:
   ```kotlin
   @Composable
   fun YourScreen(param: String) {
       // Your screen content
   }
   ```
3. **Add to AppNavHost**:
   ```kotlin
   entry<ApplicationScreen.YourScreen> { 
       YourScreen(it.param) 
   }
   ```
4. **Navigate to screen**:
   ```kotlin
   navigator.navigateTo(ApplicationScreen.YourScreen(param = "value"))
   ```

### Creating a Repository

1. **Create interface** in `core:data/api/`:
   ```kotlin
   interface YourRepository {
       suspend fun getData(): Result<YourModel>
   }
   ```
2. **Create implementation** in `core:data/impl/repository/`:
   ```kotlin
   class YourRepositoryImpl @Inject constructor(
       private val yourService: YourService
   ): YourRepository {
       override suspend fun getData(): Result<YourModel> {
           // Implementation
       }
   }
   ```
3. **Bind in DataModule**:
   ```kotlin
   @Binds
   fun bindYourRepository(impl: YourRepositoryImpl): YourRepository
   ```

### Creating a Network Service

1. **Create service interface** in `core:network/service/`:
   ```kotlin
   interface YourService {
       @GET("/your/endpoint")
       suspend fun getData(): ApiResponse<YourResponse>
   }
   ```
2. **Add to NetworkModule**:
   ```kotlin
   @Provides
   @Singleton
   fun provideYourService(retrofit: Retrofit): YourService {
       return retrofit.create(YourService::class.java)
   }
   ```

## 📚 Additional Resources

### Documentation

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Navigation3](https://developer.android.com/jetpack/androidx/releases/navigation)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Room](https://developer.android.com/training/data-storage/room)
- [DataStore](https://developer.android.com/training/data-storage/datastore)
- [Retrofit](https://square.github.io/retrofit/)

### Architecture

- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Android Architecture Components](https://developer.android.com/topic/architecture)

## 🤝 Contributing

When contributing to this project:

1. Follow the existing code style
2. Write tests for new features
3. Update documentation
4. Follow Clean Architecture principles
5. Keep modules focused and cohesive

## 📄 License

This project is a template and can be used as a starting point for your Android applications.

## 🙏 Acknowledgments

Built with modern Android development best practices and following Google's recommended architecture patterns.

---

**Happy Coding! 🚀**
