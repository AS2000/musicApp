pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}
rootProject.name = "MusicApp"
include(":app")
include(":modules")
include(":modules:common")
include(":modules:features")
include(":modules:data")
include(":modules:data:model")
include(":modules:data:network")
include(":modules:data:repository")
include(":modules:data:storage")
include(":modules:features:music")
include(":modules:features:playlist")
