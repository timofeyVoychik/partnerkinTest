plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "ru.timofey.core_ui"
    compileSdk = 36

    defaultConfig {
        minSdk = 23

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
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }
    kotlin {
        jvmToolchain(17)
    }
}

dependencies {

    implementation(project(":domain"))

    api(libs.kotlinx.datetime)
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    api(libs.coil.compose)
    api(libs.coil.network.okhttp)

    // Compose BOM
    implementation(platform(libs.androidx.compose.bom))

    // Compose
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.tooling.preview)
    debugApi(libs.androidx.compose.ui.tooling)

    // Material3
    api(libs.androidx.compose.material3)

    // Lifecycle Compose
    api(libs.androidx.lifecycle.runtime.compose)
    api(libs.androidx.lifecycle.viewmodel.compose)

    api(libs.androidx.activity.compose)

    // Navigation
    api(libs.androidx.navigation.compose)

    // Hilt navigation
    api(libs.androidx.hilt.navigation.compose)


}