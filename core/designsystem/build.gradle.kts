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
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.compose.foundation)

    implementation(libs.androidx.lifecycle.runtime.ktx)
}