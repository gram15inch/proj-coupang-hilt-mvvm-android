import com.gram15inch.buildsrc.Versions
import com.gram15inch.buildsrc.Libraries
import com.gram15inch.buildsrc.UnitTest
import com.gram15inch.buildsrc.AndroidX
import com.gram15inch.buildsrc.Kotlin
import com.gram15inch.buildsrc.Google
import com.gram15inch.buildsrc.AndroidTest

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("de.mannodermaus.android-junit5")
    id("kotlin-kapt")
}

android {
    namespace = "com.gram15inch.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 31
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["dagger.hilt.disableModulesHaveInstallInCheck"]="true"
            }
        }
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(project(mapOf("path" to ":domain")))

    testImplementation(UnitTest.JUNIT)

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
    kapt(Libraries.MOSHI_CODEGEN)


    // https://github.com/square/okhttp
    implementation (Libraries.OKHTTP)
    // https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor
    implementation (Libraries.OKHTTP_LOGGING_INTERCEPTOR)

    //coroutine
    testImplementation (Kotlin.COROUTINES_TEST)

    // Timber : Log util
    implementation(Libraries.TIMBER)

}