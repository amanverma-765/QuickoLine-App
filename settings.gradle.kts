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

rootProject.name = "QuickoLine"
include(":app")
include(":features:onboarding")
include(":core:ui")
include(":features:dashboard")
include(":core:utils")
include(":features:webview")
include(":core:domain")
include(":core:data")
include(":core:navigation")
