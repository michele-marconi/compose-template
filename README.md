# Compose Template

This template repository provides a quick start for creating new Android apps using [Jetpack Compose](https://developer.android.com/jetpack/compose) as the UI framework and following the [MVVM architecture pattern](https://developer.android.com/topic/architecture).

It includes the following popular libraries:

- [Hilt](https://dagger.dev/hilt) - Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
- [Room](https://developer.android.com/training/data-storage/room) - Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
- [Retrofit](https://github.com/square/retrofit) - A type-safe HTTP client for Android and the JVM.
- [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
- [Coil](https://github.com/coil-kt/coil) - Image loading for Android backed by Kotlin Coroutines.
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore?hl=it) - Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers. DataStore uses Kotlin coroutines and Flow to store data asynchronously, consistently, and transactionally.

## How to use
To use this template, simply click on the **Use this template** button at the top (or fork the repository) and start building your app on top of it. 
Make sure to update the package name and other app-specific details before building and deploying your app.

## Annotation Processing
This project uses [Kotlin Symbol Processing (KSP)](https://kotlinlang.org/docs/ksp-overview.html) for annotation processing, which provides faster build times compared to [KAPT](https://kotlinlang.org/docs/kapt.html). ~~However, some dependencies (like Hilt) may not support KSP yet, which is why KAPT is still used~~.

## Contribution
Contributions to this project are welcome! If you encounter any problems or have suggestions for improvement, feel free to submit a pull request or open an issue.
