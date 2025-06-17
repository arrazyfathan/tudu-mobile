# Tudu - Cross-Platform Journal Management App

Tudu is a modern, cross-platform journal management application built with Kotlin Multiplatform and Jetpack Compose. It allows users to organize their tasks and "capture beauty in their life" across Android, iOS, and Desktop platforms from a single codebase.

## Main Features

- **Cross-Platform Support**: Run on Android, iOS, and Desktop from a single codebase
- **User Authentication**: Secure login and registration system
- **Task Management**: Create, organize, and track your journal (coming soon)
- **Modern UI**: Built with Jetpack Compose for a consistent and beautiful user interface
- **Offline Support**: Access your journal even without an internet connection (coming soon)

## Tech Stack

- **Kotlin Multiplatform**: For sharing code across platforms
- **Jetpack Compose**: For building the UI on all platforms
- **Koin**: For dependency injection
- **Ktor**: For networking and API communication
- **Kotlinx Serialization**: For JSON parsing
- **DataStore**: For preferences and data persistence
- **Coroutines**: For asynchronous programming
- **Firebase**: For analytics and crash reporting
- **Coil**: For image loading
- **Napier**: For logging

## Installation Guide

### Prerequisites

- Android Studio Arctic Fox or newer
- Xcode 13 or newer (for iOS development)
- JDK 11 or newer

### Android

1. Clone the repository:
   ```
   git clone https://github.com/arrazyfathan/Tudu.git
   ```

2. Open the project in Android Studio

3. Run the app on an Android device or emulator:
   ```
   ./gradlew :composeApp:installDebug
   ```

### iOS

1. Clone the repository:
   ```
   git clone https://github.com/arrazyfathan/Tudu.git
   ```

2. Open the `iosApp/iosApp.xcodeproj` file in Xcode

3. Run the app on an iOS device or simulator

### Desktop

1. Clone the repository:
   ```
   git clone https://github.com/arrazyfathan/Tudu.git
   ```

2. Run the desktop application:
   ```
   ./gradlew :composeApp:run
   ```

## Usage Instructions

1. **First Launch**: On first launch, you'll be greeted with an onboarding screen
2. **Authentication**: Create an account or log in with existing credentials
3. **Journal Management**: Once logged in, you can manage your journal (feature coming soon)

## Project Structure

- `/composeApp`: Contains the shared code for all platforms
  - `/commonMain`: Code shared across all platforms
  - `/androidMain`: Android-specific code
  - `/iosMain`: iOS-specific code
  - `/desktopMain`: Desktop-specific code
- `/iosApp`: iOS application entry point and Swift-specific code

## Contributing Guidelines

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature-name`
3. Commit your changes: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature/your-feature-name`
5. Open a pull request

Please make sure to update tests as appropriate and adhere to the existing coding style. Run ktlint before submitting:
```
./gradlew ktlintCheck
```

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact/Author Info

**Ar Razy Fathan Rabbani**

- GitHub: [arrazyfathan](https://github.com/arrazyfathan)
- Email: [razywrk@gmail.com](mailto:razywrk@gmail.com)

---

*Note: This project is currently in development. Some features mentioned may not be fully implemented yet.*