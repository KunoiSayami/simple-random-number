plugins {
    id("com.android.application")
}


//apply( rootProject.file("keys.properties.gradle.kts"))

android {
    namespace = "com.github.kunoisayami.randomnumbers"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.github.kunoisayami.randomnumbers"
        minSdk = 29
        targetSdk = 34
        versionCode = 5
        versionName = "1.2.0"
        resourceConfigurations.addAll(listOf("en"))
        /*ndk {
            abiFilters.addAll(listOf("armeabi", "armeabi-v7a"))
        }*/
    }

    signingConfigs {

        create("release") {
            keyAlias = "key0"
            storePassword = rootProject.extra.get("StorePassword") as String
            keyPassword = rootProject.extra.get("KeyPassword") as String
            storeFile = file(rootProject.extra.get("KeyStoreFile") as String)
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            //isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // https://stackoverflow.com/a/66195950
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}