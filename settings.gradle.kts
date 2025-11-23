pluginManagement {
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
