plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
    id("org.jlleitschuh.gradle.ktlint") version "11.6.0"
    id("jacoco") // Add JaCoCo plugin
}

apply {
    // from("../ktlint.gradle.kts")
    // from("../jacoco.gradle.kts")
    //from("../util.gradle")
}


android {
    namespace = "com.webapp.acpsnews"
    compileSdk = 35
    //setProperty("archivesBaseName", getArtifactName(defaultConfig))
    /*setProperty(
        "archivesBaseName",
        //"ACPSNEWS_${SimpleDateFormat("yyyyMMdd-HHmm").format(Date())}_v${defaultConfig.versionName}(${defaultConfig.versionCode})"
        "ACPSNEWS_${SimpleDateFormat("dd_MMM_yyyy_HH_mm_a").format(Date())}"
    )*/

     defaultConfig {
        applicationId = "com.webapp.acpsnews"
        minSdk = 24
        targetSdk = 35
        versionCode = 103
        versionName = "1.03"


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            /*storeFile = file(System.getenv("MYAPP_RELEASE_STORE_FILE") ?: project.property("MYAPP_RELEASE_STORE_FILE") as String)
            storePassword = System.getenv("MYAPP_RELEASE_STORE_PASSWORD") ?: project.property("MYAPP_RELEASE_STORE_PASSWORD") as String
            keyAlias = System.getenv("MYAPP_RELEASE_KEY_ALIAS") ?: project.property("MYAPP_RELEASE_KEY_ALIAS") as String
            keyPassword = System.getenv("MYAPP_RELEASE_KEY_PASSWORD") ?: project.property("MYAPP_RELEASE_KEY_PASSWORD") as String*/
            storeFile = file(project.property("MYAPP_RELEASE_STORE_FILE") as String)
            storePassword = project.property("MYAPP_RELEASE_STORE_PASSWORD") as String
            keyAlias = project.property("MYAPP_RELEASE_KEY_ALIAS") as String
            keyPassword = project.property("MYAPP_RELEASE_KEY_PASSWORD") as String
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
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
        // kotlinCompilerExtensionVersion '1.5.2'
    }
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
