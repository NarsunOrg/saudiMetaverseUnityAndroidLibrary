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

rootProject.name = "saudiMetaverseUnityAndroidLibrary"
include(":app", ":saudiMetaverseUnity")
include(":SmileNativeShareModule")
project(":SmileNativeShareModule").projectDir = File(rootDir, "Smile_Native_Share")
