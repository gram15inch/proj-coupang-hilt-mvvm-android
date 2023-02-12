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
    id("kotlin-kapt")
    id("de.mannodermaus.android-junit5")
}

android {
    namespace = "com.gram15inch.domain"
    compileSdk = Versions.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = Versions.MIN_SDK_VERSION
        targetSdk = Versions.TARGET_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    testImplementation(UnitTest.JUNIT)

    // dagger
    implementation(Google.HILT_ANDROID)
    kapt (Google.HILT_ANDROID_COMPILER)

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

    // retrofit for networking
    implementation (Libraries.RETROFIT)

}