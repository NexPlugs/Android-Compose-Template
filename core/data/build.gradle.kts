plugins {
    id("convention.android.library")
    id("convention.android.hilt")
}

android {
    namespace = "com.example.core.data"
}

dependencies {
    // Kotlin
    project(":core:network")

    implementation(libs.kotlinx.coroutines.core)

}
