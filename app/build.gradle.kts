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
        versionCode = 1
        versionName = "1.0"
    }

    signingConfigs {

        create("release") {
            storePassword = rootProject.extra.get("StorePassword") as String
            keyPassword = rootProject.extra.get("KeyPassword") as String
            storeFile = file(rootProject.extra.get("KeyStoreFile") as String)
            keyAlias = "key0"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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