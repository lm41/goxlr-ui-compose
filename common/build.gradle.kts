plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("plugin.serialization") version "1.8.10"
}

group = "de.rmrf"
version = "1.0-SNAPSHOT"

@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
kotlin {
    android()
    jvm("desktop") {
        jvmToolchain(11)
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                // define a BOM and its version
                implementation(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))

                // define any required OkHttp artifacts without version
                implementation("com.squareup.okhttp3:okhttp")
                implementation("com.github.ProjectMapK:jackson-module-kogera:2.14.2-alpha5")
                //implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
                implementation("com.flipkart.zjsonpatch:zjsonpatch:0.4.14")

                implementation("androidx.compose.ui:ui-tooling-preview:1.4.2")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
                implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

                implementation("io.insert-koin:koin-core:3.4.0")
                implementation("io.insert-koin:koin-compose:1.0.1")

                implementation("net.harawata:appdirs:1.2.1")
                implementation("io.github.xxfast:kstore:0.5.0")
                implementation("io.github.xxfast:kstore-file:0.5.0")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

                implementation(kotlin("reflect"))




                api(compose.runtime)
                api(compose.materialIconsExtended)
                api(compose.foundation)
                api(compose.material3)
                api(compose.ui)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.0")
                implementation("io.insert-koin:koin-android:3.4.0")
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation("io.insert-koin:koin-core:3.4.0")
                api(compose.preview)
            }
        }
        val desktopTest by getting
    }
}

android {
    compileSdkVersion(33)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(33)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
dependencies {
    implementation("androidx.room:room-ktx:2.5.1")
}
