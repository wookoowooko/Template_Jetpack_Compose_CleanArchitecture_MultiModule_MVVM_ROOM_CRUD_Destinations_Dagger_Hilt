pluginManagement {
    repositories {
        google()
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

rootProject.name = "Template_Jetpack_Compose_CleanArchitecture_MultiModule_MVVM_ROOM_CRUD_Destinations_Dagger_Hilt"
include(":app")
include(":data")
include(":domain")
