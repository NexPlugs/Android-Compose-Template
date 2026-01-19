package convention

import com.android.build.gradle.LibraryExtension
import convention.compose.configureAndroidCompose
import convention.compose.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidFeatureConventionPlugin : Plugin<Project>{
    override fun apply(target: Project) {
       with(target) {
           pluginManager.apply {
               apply("com.android.library")
               apply("org.jetbrains.kotlin.android")
           }

           extensions.configure<LibraryExtension> {
               configureKotlinAndroid(this)
               configureAndroidCompose(this)
               defaultConfig.targetSdk = 35

           }

           extensions.getByType<KotlinAndroidProjectExtension>().apply {
                configureKotlinAndroid(this)
           }

           dependencies {
               add("implementation", project(":core:model"))
               add("implementation", project(":core:data"))
               add("implementation", project(":core:common"))
               add("implementation", project(":core:designsystem"))
               add("implementation", project(":core:navigation"))
               add("implementation", project(":core:viewmodel"))
           }
       }
    }
}