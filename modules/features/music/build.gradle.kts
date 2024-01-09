plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "lt.vianet.musicapp.modules.features.music"
    compileSdk = ConfigAndroid.compileSdkVersion

    defaultConfig {
        minSdk = ConfigAndroid.minSdkVersion
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Modules
    implementation(project(Modules.common))
    implementation(project(Modules.Data.model))
    implementation(project(Modules.Data.repository))
    implementation(project(Modules.Data.storage))
    implementation(project(Modules.Features.playlist))

    // Androidx
    implementation(Dependencies.androidxCoreKtx)
    implementation(Dependencies.androidxAppCompat)

    // UI
    implementation(Dependencies.material)
    implementation(Dependencies.androidxConstraintLayout)

    // Activity / Fragment Kotlin Extensions
    implementation(Dependencies.androidxFragmentKtx)
    implementation(Dependencies.androidxFragmentTesting)

    // Fragment view binding helper
    implementation(Dependencies.fragmentViewBinding)

    // Swipe Refresh
    implementation(Dependencies.androidxSwipeRefreshLayout)

    // Navigation
    implementation(Dependencies.androidxNavigationFragment)
    implementation(Dependencies.androidxNavigationUI)
    implementation(Dependencies.androidxNavigationTesting)

    // Hilt / Dagger
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)
}
