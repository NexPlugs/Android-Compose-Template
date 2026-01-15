plugins {
    id("convention.android.library")
    id("convention.android.hilt")
}


android {
    namespace = "com.example.core.database"
}

dependencies {
    // Projects
    implementation(project(":core:model"))

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}