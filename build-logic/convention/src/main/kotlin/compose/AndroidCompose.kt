    package convention.compose

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.assign
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

/**
 * Configures Android Compose settings for the given [Project] using the provided
 * [CommonExtension].
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    pluginManager.apply("org.jetbrains.kotlin.android")

    commonExtension.apply {
        buildFeatures {
            compose = true
        }
    }

    extensions.configure<ComposeCompilerGradlePluginExtension> {
        reportsDestination = layout.buildDirectory.dir("compose_compiler")
    }

}
