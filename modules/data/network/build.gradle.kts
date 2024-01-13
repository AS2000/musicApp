plugins {
    id("com.android.library")
    kotlin("android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "lt.vianet.musicapp.modules.data.network"
    compileSdk = ConfigAndroid.compileSdkVersion
}

dependencies {
    // Modules
    implementation(project(Modules.common))
    implementation(project(Modules.Data.model))
    implementation(project(Modules.Data.storage))

    // Hilt / Dagger
    implementation(Dependencies.hilt)
    ksp(Dependencies.hiltCompiler)

    // Androidx
    implementation(Dependencies.androidxCoreKtx)

    // Kotlin Coroutines
    implementation(Dependencies.kotlinxCoroutines)

    // Retrofit (Network)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitMoshi)

    // Moshi (Json parser)
    implementation(Dependencies.moshi)
    ksp(Dependencies.moshiCodegen)

    // Logging Interceptor - logs HTTP request and response data.
    implementation(Dependencies.loggingInterceptor)
}
