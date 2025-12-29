plugins {
    `kotlin-dsl`
}

group = "com.example.buildlogic.convention"


java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "convention.android.hilt"
            implementationClass = "convention.AndroidHiltConventionPlugin"
        }
        register("androidFeature") {
            id = "convention.android.feature"
            implementationClass = "convention.AndroidFeatureConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "convention.android.compose"
            implementationClass = "convention.AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "convention.android.library"
            implementationClass = "convention.AndroidLibraryConventionPlugin"
        }
    }
}