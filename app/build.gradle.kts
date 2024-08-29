plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.myhazardgameapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myhazardgameapp"
        minSdk = 24
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
    sourceSets {
        getByName("main") {
            res {
                srcDirs(
                    "src\\main\\res\\layouts\\gameSelectionList",
                    "src\\main\\res\\layouts\\bottomSheets",
                    "src\\main\\res", "src\\main\\res", "src\\main\\res\\layouts\\other",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\gameSelected",
                    "src\\main\\res",
                    "src\\main\\res\\drawables",
                    "src\\main\\res",
                    "src\\main\\res\\drawables\\icons",
                    "src\\main\\res",
                    "src\\main\\res\\drawables\\borders",
                )
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}