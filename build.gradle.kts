group = "de.rmrf"
version = "0.0.1"

allprojects {
    repositories {
        google()
        //mavenCentral()
        maven("https://repo1.maven.org/maven2/")
        maven("https://jitpack.io")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

plugins {
    kotlin("multiplatform") apply false
    kotlin("android") apply false
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.compose") apply false version "1.4.0"
}
