plugins {
    id("convention.android.library")
    id("convention.android.hilt")
}

android {
    namespace = "com.example.core.network"
}

dependencies {
    // Kotlin
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)

    // Network
    implementation(libs.retrofit)

    // Inject
    implementation(libs.javax.inject)


}
