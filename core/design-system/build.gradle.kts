plugins {
    id("charger.android.library")
    id("charger.android.library.compose")
}

android {
    namespace = "com.areyana.charger.core.designsystem"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.compose.ui.tooling.preview)
}