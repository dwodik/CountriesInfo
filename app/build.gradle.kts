plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.countriesinfo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.countriesinfo"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    implementation("com.squareup.retrofit2:converter-gson:2.11.0") // подключение gson конвертера
    implementation("com.squareup.retrofit2:retrofit:2.11.0") // подключение ретрофит

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1") // подключение корутинов - это легковесный поток, неполноценный
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")

    implementation("io.coil-kt.coil3:coil-compose:3.0.0-rc01") // подключение coil - библиотеки для работы с изображениями
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.0-rc01")
    implementation("io.coil-kt.coil3:coil-svg:3.0.0-rc01")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}