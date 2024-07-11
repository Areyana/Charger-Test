plugins {
    id("charger.android.library")
}

dependencies {
    api(libs.androidx.navigation.compose)
}
android {
    namespace = "com.areyana.charger.core.navigation"
}
