import com.gram15inch.buildsrc.Versions
import com.gram15inch.buildsrc.AndroidX
import com.gram15inch.buildsrc.Kotlin
import com.gram15inch.buildsrc.Google
import com.gram15inch.buildsrc.Libraries
import com.gram15inch.buildsrc.UnitTest
import com.gram15inch.buildsrc.AndroidTest


plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("de.mannodermaus.android-junit5")
    id("kotlin-kapt")
}

android {
    namespace = "com.gram15inch.mycoupangeats"
    compileSdk = Versions.COMPILE_SDK_VERSION
    defaultConfig {
        applicationId = "com.gram15inch.mycoupangeats"
        minSdk = Versions.MIN_SDK_VERSION
        targetSdk = Versions.TARGET_SDK_VERSION
        versionCode = Versions.VERSION_CODE
        versionName = Versions.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["dagger.hilt.disableModulesHaveInstallInCheck"]="true"
            }
        }
        testInstrumentationRunnerArguments["runnerBuilder"] = "de.mannodermaus.junit5.AndroidJUnit5Builder"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(mapOf("path" to ":domain")))


    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.APP_COMPAT)
    implementation(Google.MATERIAL)
    implementation(AndroidX.CONSTRAINT_LAYOUT)
    testImplementation(UnitTest.JUNIT)
    androidTestImplementation(AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(AndroidTest.ESPRESSO_CORE)


    // add
    implementation (AndroidX.LIFECYCLE_LIVEDATA_KTX)
    implementation (AndroidX.LIFECYCLE_VIEWMODEL_KTX)

    implementation (AndroidX.LEGACY_SUPPORT_V4)
    implementation (AndroidX.ANNOTATION)

    
    // Java language implementation
    implementation (AndroidX.NAVIGATION_FRAGMENT)
    implementation (AndroidX.NAVIGATION_UI)
    // Kotlin
    implementation (AndroidX.NAVIGATION_FRAGMENT_KTX)
    implementation (AndroidX.NAVIGATION_UI_KTX  )
    // Feature module Support
    implementation (AndroidX.NAVIGATION_DYNAMIC_FEATURES_FRAGMENT)
    // Testing Navigation
    androidTestImplementation (AndroidX.NAVIGATION_TESTING)
    // Jetpack Compose Integration
    implementation (AndroidX.NAVIGATION_COMPOSE)


    // dagger
    implementation(Google.HILT_ANDROID)
    kapt (Google.HILT_COMPILER)

    //hilt test
    // For Robolectric tests.
    testImplementation (Google.HILT_ANDROID_TESTING)
    // ...with Kotlin.
    kaptTest (Google.HILT_ANDROID_COMPILER)
    // For instrumented tests.
    androidTestImplementation (Google.HILT_ANDROID_TESTING)
    // ...with Kotlin.
    kaptAndroidTest (Google.HILT_ANDROID_COMPILER)
    testImplementation(Libraries.MOCKK)

    // junit5
    //testImplementation (UnitTest.JUNIT_JUPITER_API)
    testImplementation (UnitTest.JUNIT_JUPITER_API)
    testRuntimeOnly (UnitTest.JUNIT_JUPITER_ENGINE)
    testImplementation (UnitTest.JUNIT_JUPITER_PARAMS)

    androidTestImplementation (AndroidX.TEST_RUNNER)
    androidTestImplementation (UnitTest.JUNIT_JUPITER_API)

    androidTestImplementation (UnitTest.JUNIT5_TEST_CORE)
    androidTestRuntimeOnly (UnitTest.JUNIT5_TEST_RUNNER)

    // Timber : Log util
    implementation(Libraries.TIMBER)

    implementation(Libraries.AMARJAIN07_STICKYSCROLLVIEW)


    // ****** network ******

    // retrofit for networking
    implementation (Libraries.RETROFIT)
    implementation (Libraries.RETROFIT_KOTLIN_COROUTINES_ADAPTER)
    implementation (Libraries.RETROFIT_CONVERTER_MOSHI)
    androidTestImplementation (Libraries.RETROFIT_CONVERTER_MOSHI)

    // moshi for parsing the JSON format

    implementation (Libraries.MOSHI)
    implementation (Libraries.MOSHI_KOTLIN)
    implementation (Libraries.MOSHI_ADAPTERS)
    kapt (Libraries.MOSHI_CODEGEN)


    // https://github.com/square/okhttp
    implementation (Libraries.OKHTTP)
    // https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor
    implementation (Libraries.OKHTTP_LOGGING_INTERCEPTOR)

    //coroutine
    testImplementation (Kotlin.COROUTINES_TEST)


    // 사진 불러오기
    implementation (Libraries.GLIDE)


}