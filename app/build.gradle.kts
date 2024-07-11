plugins {
    id("charger.android.application")
    id("charger.android.application.compose")
    alias(libs.plugins.kotlinx.serialization)
}

val appVersion: String by project
val appVersionCode: String by project

android {
    namespace = "com.areyana.charger"
    defaultConfig {
        applicationId = "com.areyana.charger"
        versionCode = appVersionCode.toInt()
        versionName = appVersion
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.designSystem)
    implementation(projects.core.navigation)
    implementation(projects.core.mvi)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.lifecycle.runtimeCompose)

    implementation(libs.accompanist.systemuicontroller)

    implementation(libs.material3)
    
    implementation(libs.timber)

    implementation(libs.kotlinx.serialization.core)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.android)
}