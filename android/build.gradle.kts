plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}

group = "de.rmrf"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    implementation(project(":common"))
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("ro.dragossusi:viewmodel:0.0.6")
}

android {
    compileSdkVersion(33)
    defaultConfig {
        applicationId = "de.rmrf.android"
        minSdkVersion(24)
        targetSdkVersion(33)
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    dexOptions {
        javaMaxHeapSize = "4g"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
