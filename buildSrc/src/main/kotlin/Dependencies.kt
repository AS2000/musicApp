object Dependencies {

    // Androidx
    private const val coreKtxVersion = "1.12.0"
    const val androidxCoreKtx = "androidx.core:core-ktx:$coreKtxVersion"

    private const val appCompatVersion = "1.6.1"
    const val androidxAppCompat = "androidx.appcompat:appcompat:$appCompatVersion"

    // Gradle
    private const val androidBuildToolsVersion = "8.2.1"
    const val androidBuildToolsGradlePlugin =
        "com.android.tools.build:gradle:$androidBuildToolsVersion"
    private const val kotlinGradleVersion = "1.9.22"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinGradleVersion"

    // Splash screen
    private const val splashscreenVersion = "1.0.0"
    const val androidxSplashscreen = "androidx.core:core-splashscreen:$splashscreenVersion"

    // UI
    private const val materialVersion = "1.7.0"
    const val material = "com.google.android.material:material:$materialVersion"
    private const val androidxConstraintLayoutVersion = "2.1.4"
    const val androidxConstraintLayout =
        "androidx.constraintlayout:constraintlayout:$androidxConstraintLayoutVersion"

    // Activity / Fragment Kotlin Extensions
    private const val androidxActivityVersion = "1.6.1"
    const val androidxActivityKtx = "androidx.activity:activity-ktx:$androidxActivityVersion"

    // is not needed to import activity-ktx separately as it is the part of fragment-ktx
    private const val androidxFragmentVersion = "1.6.0"
    const val androidxFragmentKtx = "androidx.fragment:fragment-ktx:$androidxFragmentVersion"

    /** fragment-ktx includes: activity-ktx, core-ktx, collection-ktx, fragment, lifecycle-livedata-core-ktx,
     lifecycle-viewmodel-ktx, savedstate-ktx, kotlin-stdlib */
    const val androidxFragmentTesting =
        "androidx.fragment:fragment-testing:$androidxFragmentVersion"

    // Lifecycle
    private const val androidxLifecycleVersion = "2.5.1"
    const val androidxLifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:$androidxLifecycleVersion"
    const val androidxLifecycleLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:$androidxLifecycleVersion"
    const val androidxLifecycleCommonJava =
        "androidx.lifecycle:lifecycle-common-java8:$androidxLifecycleVersion"
    const val androidxLifecycleExtensions =
        "androidx.lifecycle:lifecycle-com.extensions:$androidxLifecycleVersion"

    // Swipe Refresh
    private const val androidxSwipeRefreshlayoutVersion = "1.1.0"
    const val androidxSwipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:$androidxSwipeRefreshlayoutVersion"

    // Kotlin Coroutines
    private const val coroutinesVersion = "1.6.4"
    const val kotlinxCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"

    // Navigation
    private const val androidxNavigationVersion = "2.5.3"
    const val androidxNavigationFragment =
        "androidx.navigation:navigation-fragment-ktx:$androidxNavigationVersion"
    const val androidxNavigationUI =
        "androidx.navigation:navigation-ui-ktx:$androidxNavigationVersion"
    const val androidxNavigationSafeArgsGradle =
        "androidx.navigation:navigation-safe-args-gradle-plugin:$androidxNavigationVersion"
    const val androidxNavigationTesting =
        "androidx.navigation:navigation-testing:$androidxNavigationVersion"

    // Room (DataBase)
    private const val androidxRoomVersion = "2.6.1"
    const val androidxRoomRuntime = "androidx.room:room-runtime:$androidxRoomVersion"
    const val androidxRoomCompiler = "androidx.room:room-compiler:$androidxRoomVersion"
    const val androidxRoomKtx = "androidx.room:room-ktx:$androidxRoomVersion"
    const val androidxRoomTesting = "androidx.room:room-testing:$androidxRoomVersion"

    // Retrofit (Network)
    private const val retrofitVersion = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"

    // Moshi (Json parser)
    private const val moshiVersion = "1.15.0"
    const val moshi = "com.squareup.moshi:moshi:$moshiVersion"
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"

    // Logging Interceptor - logs HTTP request and response data.
    private const val loggingInterceptorVersion = "4.10.0"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:$loggingInterceptorVersion"

    // Android in-app HTTP inspector
    private const val chuckerVersion = "3.5.2"
    const val chucker = "com.github.chuckerteam.chucker:library:$chuckerVersion"
    const val chuckerNoOp = "com.github.chuckerteam.chucker:library-no-op:$chuckerVersion"

    // Fragment view binding helper
    private const val fragmentViewBindingVersion = "1.0.2"
    const val fragmentViewBinding =
        "com.github.Zhuinden:fragmentviewbindingdelegate-kt:$fragmentViewBindingVersion"

    // Hilt / Dagger2
    private const val hiltVersion = "2.50"
    const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:$hiltVersion"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"

    // Glide
    private const val glideVersion = "4.16.0"
    const val glide = "com.github.bumptech.glide:glide:$glideVersion"
    const val glideAnnotationProcessor = "com.github.bumptech.glide:ksp:$glideVersion"

    // Tests
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test:1.9.22"

    // Test Mock
    const val mockito = "org.mockito:mockito-core:5.8.0"
}
