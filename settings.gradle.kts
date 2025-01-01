pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ecommerceapp"
include(":app")
include(":core:common")
include(":core:database")
include(":core:network")
include(":core:model")
include(":core:ui")
include(":feature:home")
include(":feature:favorites")
include(":feature:profile")
include(":feature:notifications")
include(":feature:productdetail")
