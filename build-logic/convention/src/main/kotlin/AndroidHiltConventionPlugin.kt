package convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.api.artifacts.VersionCatalogsExtension

class AndroidHiltConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
                apply("com.google.devtools.ksp")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies.apply {
                add("implementation", libs.findLibrary("hilt.android").get())
                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("ksp", libs.findLibrary("hilt.compiler").get())
            }
        }
        
    }
}