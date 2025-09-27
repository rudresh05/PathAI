import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.rudresh05.pathai"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.rudresh05.pathai"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    val localProperties = Properties()
    val localPropertiesFile = rootProject.file("local.properties")
    if(localPropertiesFile.exists()) {
        localProperties.load(FileInputStream(localPropertiesFile))
    }
    defaultConfig{
        buildConfigField("String", "YOUTUBE_API_KEY", localProperties.getProperty("YOUTUBE_API_KEY"))
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
    }
    android.buildFeatures.buildConfig = true
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
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //lotty animations
    implementation(libs.lottie)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Retrofit for API calls
    implementation(libs.retrofit.v290)
    implementation(libs.converter.gson.v290)

    // Glide for image loading
    implementation(libs.glide)

    // YouTube Player Library
    implementation(libs.core)
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.2")

    //image slider
    implementation("com.github.denzcoskun:ImageSlideshow:0.1.2")

    //fancy toast builder
    implementation("io.github.shashank02051997:FancyToast:2.0.2")

    //custom toast builder
    implementation("com.github.emreesen27:Android-Custom-Toast-Message:1.0.5")


}