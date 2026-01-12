plugins {
    id("convention.android.library")
}

android {
    namespace = "com.example.core.datastore"
}

dependencies {
    // Kotlin
    implementation(libs.kotlinx.coroutines.core)
}