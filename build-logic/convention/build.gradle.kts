plugins {
    `kotlin-dsl`
}


dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        create("androidHilt") {
            id = "convention.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        create("androidFeature") {
            id = "convention.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        create("androidApplicationCompose") {
            id = "convention.android.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
    }
}