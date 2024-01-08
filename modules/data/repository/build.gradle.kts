plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "lt.vianet.musicapp.modules.data.repository"
    compileSdk = ConfigAndroid.compileSdkVersion
}

dependencies {
    // Modules
    implementation(project(Modules.common))
    implementation(project(Modules.Data.model))
    implementation(project(Modules.Data.network))
    implementation(project(Modules.Data.storage))

    // Hilt / Dagger
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)

    // Room (DataBase)
    implementation(Dependencies.androidxRoomRuntime)
    kapt(Dependencies.androidxRoomCompiler)
    implementation(Dependencies.androidxRoomKtx)
    implementation(Dependencies.androidxRoomTesting)

    // Retrofit (Network)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitMoshi)
}
