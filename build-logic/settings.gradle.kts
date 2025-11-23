dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml")) // Points to the root project's version catalog
        }
    }
}
rootProject.name = "build-logic"
// include(":convention") // Will be uncommented in Step 3
