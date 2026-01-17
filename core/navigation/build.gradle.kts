plugins {
    id("convention.android.library")
    id("convention.android.library.compose")
    id("convention.android.hilt")
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.example.core.navigation"
}

dependencies {
    //Kotlin
    implementation(libs.androidx.core)
    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.kotlinx.serialization.json)

    //Navigation3

    api(libs.androidx.navigation3.runtime.android)
    api(libs.androidx.navigation3.ui.android)
}