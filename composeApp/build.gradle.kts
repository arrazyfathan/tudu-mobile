import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.google.gms.googleService)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.buildKonfig)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

val versionPropertiesInputStream = FileInputStream("$rootDir/version.properties")
val versionProperties = Properties().apply {
    load(versionPropertiesInputStream)
}

val versionCodeProperty = versionProperties.getProperty("VERSION_CODE").toInt()
val versionMajorProperty = versionProperties.getProperty("VERSION_MAJOR").toInt()
val versionMinorProperty = versionProperties.getProperty("VERSION_MINOR").toInt()
val versionPatchProperty = versionProperties.getProperty("VERSION_PATCH").toInt()

val versionNameProperty = "$versionMajorProperty.$versionMinorProperty.$versionPatchProperty"

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.core.splashscreen)

            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)

            implementation(project.dependencies.platform(libs.firebase.bom))
            implementation(libs.firebase.analytics)
            implementation(libs.firebase.crashlytics)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.coroutines.core)

            implementation(libs.jetbrains.compose.navigation)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            api(libs.koin.core)

            implementation(libs.bundles.ktor)
            implementation(libs.bundles.coil)

            implementation(libs.datastore)
            implementation(libs.datastore.preferences)

            implementation(libs.feather)

            implementation(libs.napier)

            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)

            implementation(libs.composables.core)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)

            implementation(libs.ktor.client.cio)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.koin.test)
        }
    }
}

android {
    namespace = "com.arrazyfathan.tudu"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.arrazyfathan.tudu"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = versionCodeProperty
        versionName = versionNameProperty
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    debugImplementation(compose.uiTooling)
    ksp(libs.androidx.room.compiler)
}

compose.desktop {
    application {
        mainClass = "com.arrazyfathan.tudu.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Tudu"
            packageVersion = versionNameProperty
            description = "Compose Multiplatform App"
            copyright = "© 2024 Ar Razy Fathan Rabbani. All rights reserved."
            vendor = "Ar Razy Fathan Rabbani"
            licenseFile.set(project.file("LICENSE.txt"))

            macOS {
                dockName = "Tudu"
                entitlementsFile.set(project.file("default.entitlements"))
                iconFile.set(project.file("resources/mac_icon.icns"))
            }

            windows {
                iconFile.set(project.file("resources/windows_icon.ico"))
            }

            linux {
                iconFile.set(project.file("resources/linux_icon.png"))
            }
        }

        buildTypes.release {
            proguard {
                obfuscate.set(true)
                configurationFiles.from("proguard-rules.pro")
            }
        }
    }
}

buildkonfig {
    packageName = "com.arrazyfathan.tudu"

    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "APP_NAME", "Tudu")
        buildConfigField(FieldSpec.Type.STRING, "DEFAULT_BASE_URL", "http://localhost:3000")
    }

    defaultConfigs("development") {
        buildConfigField(FieldSpec.Type.STRING, "APP_NAME", "Tudu")
        buildConfigField(FieldSpec.Type.STRING, "DEFAULT_BASE_URL", "http://localhost:3000")
    }

    defaultConfigs("production") {
        buildConfigField(FieldSpec.Type.STRING, "APP_NAME", "Tudu")
        buildConfigField(FieldSpec.Type.STRING, "DEFAULT_BASE_URL", "http://localhost:3000")
    }

    targetConfigs {
        create("android") {
            buildConfigField(FieldSpec.Type.STRING, "BASE_URL_ANDROID", "http://10.0.2.2:3000")
        }
    }

    targetConfigs("development") {
        create("android") {
            buildConfigField(FieldSpec.Type.STRING, "BASE_URL_ANDROID", "http://192.168.100.162:3000")
        }
    }

    targetConfigs("production") {
        create("android") {
            buildConfigField(FieldSpec.Type.STRING, "BASE_URL_ANDROID", "http://10.0.2.2:3000")
        }
    }
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)

    filter {
        exclude("**/build/**")
        exclude("**/generated/**")
    }
}
