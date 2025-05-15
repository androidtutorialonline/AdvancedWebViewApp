plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("io.gitlab.arturbosch.detekt") version "1.23.6"
    id("org.jlleitschuh.gradle.ktlint") version "11.6.0"
    id("com.google.gms.google-services")

    // Add the Crashlytics Gradle plugin
    id("com.google.firebase.crashlytics")
    id("jacoco") // Add JaCoCo plugin


    //id("dagger.hilt.android.plugin")
    //kotlin("android")
    kotlin("kapt")

}

apply {
    // from("../ktlint.gradle.kts")
    // from("../jacoco.gradle.kts")
}

android {
    namespace = "com.webapp.acpsnews"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.webapp.acpsnews"
        minSdk = 24
        targetSdk = 35
        versionCode = 102
        versionName = "1.02"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1" // Use the appropriate version
    }

    /*flavorDimensions += "environment"

    productFlavors {
        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            buildConfigField("String", "BASE_URL", "\"https://dev.example.com\"")
        }
        create("prod") {
            dimension = "environment"
            buildConfigField("String", "BASE_URL", "\"https://www.example.com\"")
        }
    }*/


    /*res/
    ├── mipmap-anydpi-v26
    │   ├── ic_launcher.xml (shared)
    ├── dev/
    │   └── mipmap-anydpi-v26
    │       └── ic_launcher.xml (dev version)
    ├── prod/
    │   └── mipmap-anydpi-v26
    │       └── ic_launcher.xml (prod version)


    app/
    ├── build.gradle.kts (flavors + entry point)
    ├── src/
    │   ├── dev/
    │   ├── prod/
    │   └── main/
    core/
    ├── utils/
    ├── network/
    ├── theme/
    navigation/
    ├── MainScreen.kt
    feature-webview/
    ├── WebViewScreen.kt
    ├── navigation/
    shared-test/
    ├── cucumber/ (BDD test setup)


    AdvancedWebViewApp-FullSource/
├── core/                   # Common utilities and base classes
├── navigation/             # Navigation and route handling
├── feature-webview/        # WebView screen with Compose and controls
├── app/                    # Main entry point and dependencies
├── cucumber/               # BDD test setup with Cucumber
├── build-logic/            # Custom Gradle plugins (optional)
├── .github/                # GitHub Actions workflows
├── setup.md                # This file
├── settings.gradle.kts
├── build.gradle.kts
└── ...




    */

    //sourceSets["dev"].assets.srcDir("src/dev/assets")
    //sourceSets["prod"].assets.srcDir("src/prod/assets")

}

/*tasks.test {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}*/

detekt {
    toolVersion = "1.23.1"
    config = files("$rootDir/detekt-config.yml")
    buildUponDefaultConfig = true
    parallel = true
}

ktlint {
    version.set("0.49.1")
    android.set(true)
    outputColorName.set("RED")
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
}

tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn("testDebugUnitTest")

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val fileFilter = listOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*")

    val debugTree = fileTree(project.layout.buildDirectory.dir("intermediates/javac/debug")) {
        exclude(fileFilter)
    }
    classDirectories.setFrom(debugTree)
    sourceDirectories.setFrom(files("src/main/java"))
    executionData.setFrom(files(project.layout.buildDirectory.dir("jacoco/test.exec")))
}

dependencies {



    implementation(project(":core"))
    implementation(project(":feature_webview"))
    implementation(project(":navigation"))

    


    testImplementation("io.cucumber:cucumber-java:7.11.2")
    testImplementation("io.cucumber:cucumber-junit:7.11.2")
    testImplementation("junit:junit:4.13.2")


    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")

    //navigation
    //files("src/main/res/navigation/nav_graph.xml")


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.activity.compose)
    implementation(libs.ui)
    implementation(libs.androidx.material)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.accompanist.swiperefresh)
}
