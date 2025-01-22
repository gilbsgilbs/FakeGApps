@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.tasks.PackageAndroidArtifact

plugins {
    id("com.android.application")
}

android {
    namespace = "inc.whew.android.fakegapps"
    compileSdk = 35

    defaultConfig {
        applicationId = "inc.whew.android.fakegapps"
        minSdk = 15
        targetSdk = 35
        versionCode = 12
        versionName = "6.5"
    }

    signingConfigs {
        create("release") {
            if (project.hasProperty("RELEASE_KEY_ALIAS")) {
                storeFile = file(project.properties["RELEASE_STORE_FILE"].toString())
                storePassword = project.properties["RELEASE_STORE_PASSWORD"].toString()
                keyAlias = project.properties["RELEASE_KEY_ALIAS"].toString()
                keyPassword = project.properties["RELEASE_KEY_PASSWORD"].toString()
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            if (project.hasProperty("RELEASE_KEY_ALIAS")) {
                signingConfig = signingConfigs["release"]
            }
            vcsInfo {
                include = false
            }
        }
        debug {
            vcsInfo {
                include = false
            }
        }
    }

    packaging {
        dex {
            useLegacyPackaging = false
        }
    }

    // https://gitlab.com/IzzyOnDroid/repo/-/issues/491
    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }

    // https://stackoverflow.com/a/77745844
    tasks.withType<PackageAndroidArtifact> {
        doFirst { appMetadata.asFile.orNull?.writeText("") }
    }
}

dependencies {
    compileOnly("de.robv.android.xposed:api:82")
    compileOnly("de.robv.android.xposed:api:82:sources")
}
