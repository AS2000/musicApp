plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = ConfigAndroid.applicationId
    compileSdk = ConfigAndroid.compileSdkVersion

    defaultConfig {
        applicationId = ConfigAndroid.applicationId
        minSdk = ConfigAndroid.minSdkVersion
        targetSdk = ConfigAndroid.targetSdkVersion
        versionCode = 1
        versionName = "1.0"

        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = ConfigAndroid.testInstrumentationRunner

        manifestPlaceholders["applicationLabel"] = ConfigAndroid.applicationName

        buildConfigField(
            "String",
            "URL_API",
            "\"https://music-app-mock.vianet.lt\"",
        )
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    // Modules
    implementation(project(Modules.common))
    implementation(project(Modules.Data.model))
    implementation(project(Modules.Data.repository))
    implementation(project(Modules.Data.storage))
    implementation(project(Modules.Data.network))
    implementation(project(Modules.Features.music))
    implementation(project(Modules.Features.playlist))

    // Androidx
    implementation(Dependencies.androidxCoreKtx)
    implementation(Dependencies.androidxAppCompat)

    // Splash screen
    implementation(Dependencies.androidxSplashscreen)

    // UI
    implementation(Dependencies.material)
    implementation(Dependencies.androidxConstraintLayout)

    // Fragment view binding helper
    implementation(Dependencies.fragmentViewBinding)

    // Lifecycle
    implementation(Dependencies.androidxLifecycleViewModel)
    implementation(Dependencies.androidxLifecycleLiveData)
    implementation(Dependencies.androidxLifecycleCommonJava)

    // Navigation
    implementation(Dependencies.androidxNavigationFragment)
    implementation(Dependencies.androidxNavigationUI)
    implementation(Dependencies.androidxNavigationTesting)

    // Hilt / Dagger
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)

    // Retrofit (Network)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitMoshi)

    // Logging Interceptor - logs HTTP request and response data.
    implementation(Dependencies.loggingInterceptor)

    // Glide
    implementation(Dependencies.glide)
    kapt(Dependencies.glideAnnotationProcessor)

    // Tests
    testImplementation(Dependencies.kotlinTest)
}

kapt {
    correctErrorTypes = true
}
