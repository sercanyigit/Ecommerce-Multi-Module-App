plugins {
    `kotlin-dsl`
}

group = "com.sercan.ecommerce.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<Copy> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "com.sercan.ecommerce.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
} 