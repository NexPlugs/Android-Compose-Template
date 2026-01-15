pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Android ComposeTemplate"

include(":app")
include(":common")
include(":core:common")
include(":core:datastore")
include(":core:designsystem")
include(":core:model")
include(":core:network")
include(":core:ui")
include(":features:auth")
include(":preview")
include(":core:viewmodel")
include(":core:test")
include(":core:navigation")
include(":core:di")
include(":core:data")
include(":core:database")
