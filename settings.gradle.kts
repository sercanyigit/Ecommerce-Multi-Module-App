pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "ecommerceapp"
include(":app")
include(":core:common")
include(":core:database")
include(":core:model")
include(":core:ui")
include(":feature:home")
include(":feature:favorites")
include(":feature:profile")
include(":feature:notifications")
include(":feature:productdetail")
include(":feature:addtocard")
include(":feature:onboarding")
