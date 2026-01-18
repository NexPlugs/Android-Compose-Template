package convention.compose

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

/**
 * This function use to configuration Kotlin Android Gradle settings for the given [Project]
 */
internal fun Project.configureKotlinAndroid(
    commonExtensions: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtensions.apply {
        compileSdk = 36
        defaultConfig {
            minSdk = 24
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        lint {
            abortOnError = false
        }
    }
}

/**
 * This function use to configuration Kotlin Android Gradle settings for the given [Project]
 * Have to config to compile kotlin code
 */
internal fun Project.configureKotlinAndroid(
    extension: KotlinAndroidProjectExtension
) {
    extension.apply {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }
}