plugins {
    id("convention.android.library")
}

android {
    namespace = "com.example.core.common"
}

dependencies {
    // Kotlin
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)

    // AndroidX
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation)
    implementation(platform(libs.androidx.compose.bom))

    // Network
    implementation(libs.retrofit)

}