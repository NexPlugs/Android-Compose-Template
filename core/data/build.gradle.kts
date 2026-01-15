plugins {
    id("convention.android.library")
    id("convention.android.hilt")
}

android {
    namespace = "com.example.core.data"
}

dependencies {
    //Projects
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))

    // Kotlin
    implementation(libs.kotlinx.coroutines.core)
}
