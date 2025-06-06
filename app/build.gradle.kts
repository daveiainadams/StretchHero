plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.dejvik.stretchhero"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dejvik.stretchhero"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0") // For viewModelScope
    implementation(libs.androidx.activity.compose)
    // implementation(platform(libs.androidx.compose.bom)) // Commented out
    implementation("androidx.compose.ui:ui:1.6.7") // Explicit version
    implementation("androidx.compose.ui:ui-graphics:1.6.7") // Explicit version
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.7") // Explicit version
    implementation("androidx.compose.material3:material3:1.2.1") // Explicit version
    testImplementation(libs.junit)
    implementation("androidx.activity:activity-compose:1.8.2")
    // implementation("androidx.compose.ui:ui:1.5.3") // Removed for BOM
    // implementation("androidx.compose.material3:material3:1.1.2") // Removed for BOM
    implementation("androidx.navigation:navigation-compose:2.7.5")
    implementation("androidx.compose.ui:ui-text-google-fonts:1.6.7")
    // Include Google Play services base to provide font provider resources
    implementation("com.google.android.gms:play-services-base:18.4.0")
    // Needed for Icons.Filled.Pause and other extended material icons
    implementation("androidx.compose.material:material-icons-extended:1.6.7")
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // androidTestImplementation(platform(libs.androidx.compose.bom)) // Commented out for test as well
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.7") // Explicit version
    debugImplementation(libs.androidx.ui.test.manifest)
}