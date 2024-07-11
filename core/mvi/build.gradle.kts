plugins {
    id("charger.android.library")
    id("charger.android.library.compose")
    kotlin("kapt")
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
}
android {
    namespace = "com.areyana"
}
