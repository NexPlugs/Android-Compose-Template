plugins {
    id("convention.android.library")
}

android {
    namespace = "com.example.core.navigation"
}

dependencies {
    //Kotlin
    implementation(libs.androidx.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.core)

    //Navigation3
    api(libs.androidx.navigation.ui)
    api(libs.androidx.navigation.compose)
}