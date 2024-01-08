plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "lt.vianet.musicapp.modules.data.model"
    compileSdk = ConfigAndroid.compileSdkVersion

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
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

    // Moshi (Json parser)
    implementation(Dependencies.moshi)
    kapt(Dependencies.moshiCodegen)
}
