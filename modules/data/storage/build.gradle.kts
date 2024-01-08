plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "lt.vianet.musicapp.modules.data.storage"
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
    implementation(project(Modules.Data.model))

    // Androidx
    implementation(Dependencies.androidxCoreKtx)
    implementation(Dependencies.androidxAppCompat)

    // UI
    implementation(Dependencies.material)
    implementation(Dependencies.androidxConstraintLayout)

    // Navigation
    implementation(Dependencies.androidxNavigationFragment)
    implementation(Dependencies.androidxNavigationUI)
    implementation(Dependencies.androidxNavigationTesting)

    // Fragment Kotlin Extensions
    implementation(Dependencies.androidxFragmentKtx)
    implementation(Dependencies.androidxFragmentTesting)

    // Lifecycle
    implementation(Dependencies.androidxLifecycleViewModel)
    implementation(Dependencies.androidxLifecycleLiveData)
    implementation(Dependencies.androidxLifecycleCommonJava)

    // Kotlin Coroutines
    implementation(Dependencies.kotlinxCoroutines)

    // Swipe Refresh
    implementation(Dependencies.androidxSwipeRefreshLayout)

    // Kotlin Coroutines
    implementation(Dependencies.kotlinxCoroutines)

    // Retrofit (Network)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitMoshi)

    // Hilt / Dagger
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)

    // Glide
    implementation(Dependencies.glide)
    kapt(Dependencies.glideAnnotationProcessor)

    // Tests
    testImplementation(Dependencies.kotlinTest)
}
