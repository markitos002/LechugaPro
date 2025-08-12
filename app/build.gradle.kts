plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

val roomVersion = "2.6.1"
val lifecycleVersion = "2.8.3" // O la más reciente
val navigationVersion = "2.7.7" // O la más reciente
val coroutinesVersion = "1.7.3" // O la más reciente
val materialVersion = "1.12.0" // O la más reciente

android {
    namespace = "eu.villacristina.lechugapro"
    compileSdk = 36

    defaultConfig {
        applicationId = "eu.villacristina.lechugapro"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
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

    implementation("androidx.core:core-ktx:1.13.1") // Suele estar ya
    implementation("androidx.appcompat:appcompat:1.7.0") // Suele estar ya
    implementation("com.google.android.material:material:$materialVersion") // Actualiza o añade Material
    implementation("androidx.constraintlayout:constraintlayout:2.1.4") // Suele estar ya para layouts

    // Lifecycle (ViewModel and LiveData)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion") // Para `lifecycleScope`

    // Room (Base de datos local)
    implementation("androidx.room:room-runtime:$roomVersion")
    // annotationProcessor("androidx.room:room-compiler:$roomVersion") // Para Java
    kapt("androidx.room:room-compiler:$roomVersion") // Para Kotlin con KAPT
    implementation("androidx.room:room-ktx:$roomVersion") // Extensiones KTX para Room (coroutines, etc.)

    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    // Para testing (ya suelen estar, pero verifica)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}