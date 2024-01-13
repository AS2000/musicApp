plugins {
    id("com.android.library")
    kotlin("android")
    id("com.google.devtools.ksp")
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
    ksp(Dependencies.hiltCompiler)

    // Room (DataBase)
    implementation(Dependencies.androidxRoomRuntime)
    ksp(Dependencies.androidxRoomCompiler)

    implementation(Dependencies.androidxRoomKtx)
    implementation(Dependencies.androidxRoomTesting)

    // Retrofit (Network)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitMoshi)

    // Moshi (Json parser)
    implementation(Dependencies.moshi)
    ksp(Dependencies.moshiCodegen)
}
