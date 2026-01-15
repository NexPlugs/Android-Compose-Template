plugins {
    id("convention.android.library")
    id("convention.android.hilt")
}

android {
    namespace = "com.example.core.datastore"
}

dependencies {
    // Modules
    implementation(project(":core:model"))

    // Kotlin
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.datastore.preferences)
}