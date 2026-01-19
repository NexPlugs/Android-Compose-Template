plugins {
    id("convention.android.library")
    id("convention.android.library.compose")
}


android {
    namespace = "com.example.core.designsystem"
}

dependencies {
    // Modules
    implementation(project(":core:common"))

    // AndroidX
    api(libs.androidx.compose.material3)
    api(libs.androidx.ui)
    api(libs.androidx.compose.foundation)

    api(libs.androidx.lifecycle.runtime.ktx)
}