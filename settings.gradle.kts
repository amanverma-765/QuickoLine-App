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
include(":features:activity")
include(":core:ui")
include(":features:dashboard")
include(":core:utils")
include(":features:webview")
include(":data:local")
include(":data:remote")
include(":data:model")
include(":data:repository")
include(":domain")
