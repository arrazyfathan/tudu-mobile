import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.util.Properties
import com.android.build.gradle.internal.api.BaseVariantOutputImpl

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

val versionPropsFile = file("$rootDir/version.properties")
val version = Properties()
version.load(FileInputStream(versionPropsFile))

val versionMajorProperty = version["VERSION_MAJOR"].toString().toInt()
val versionMinorProperty = version["VERSION_MINOR"].toString().toInt()
val versionPatchProperty = version["VERSION_PATCH"].toString().toInt()
val versionBuildProperty = version["BUILD_VERSION"].toString().toInt() + 1
val versionCodeProperty = version["VERSION_CODE"].toString().toInt()


version["VERSION_MAJOR"] = versionMajorProperty.toString()
version["VERSION_MINOR"] = versionMinorProperty.toString()
version["VERSION_PATCH"] = versionPatchProperty.toString()
version["BUILD_VERSION"] = versionBuildProperty.toString()
version["VERSION_CODE"] = versionCodeProperty.toString()

val versionNameProperty =
    "${versionMajorProperty}.${versionMinorProperty}.${versionPatchProperty}"

version.store(versionPropsFile.writer(), null)

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
            freeCompilerArgs += listOf<String>("-Xbinary=bundleId=com.arrazyfathan.Tudu")
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
            implementation(libs.haze)

            implementation(libs.paging.compose.common)

            implementation(libs.rich.text.editor)
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
            isMinifyEnabled = true
            isDebuggable = false
        }

        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }

    flavorDimensions += "version"
    productFlavors {
        create("development") {
            dimension = "version"
            versionNameSuffix = "-dev"
            applicationIdSuffix = ".dev"

            val appName = "Tudu-dev-$versionNameProperty"
            manifestPlaceholders["appName"] = "Tudu - Development"

            val apkName = "${appName}_$versionNameProperty$versionNameSuffix($versionCodeProperty).apk"

            buildOutputs.all {
                val variantOutputImpl = this as BaseVariantOutputImpl
                variantOutputImpl.outputFileName =  apkName
            }
        }

        create("production") {
            dimension = "version"
            versionNameSuffix = "-prod"

            val appName = "Tudu-$versionNameProperty"
            manifestPlaceholders["appName"] = "Tudu"

            val apkName = "${appName}_$versionNameProperty$versionNameSuffix($versionCodeProperty).apk"

            buildOutputs.all {
                val variantOutputImpl = this as BaseVariantOutputImpl
                variantOutputImpl.outputFileName =  apkName
            }
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
    listOf(
        "kspAndroid", "kspIosArm64", "kspIosX64", "kspIosSimulatorArm64", "kspDesktop"
    ).forEach { target ->
        add(configurationName = target, dependencyNotation = libs.androidx.room.compiler)
    }
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
        buildConfigField(FieldSpec.Type.STRING, "APP_VERSION_NAME", versionNameProperty)
    }

    defaultConfigs("development") {
        buildConfigField(FieldSpec.Type.STRING, "APP_NAME", "Tudu")
        buildConfigField(FieldSpec.Type.STRING, "DEFAULT_BASE_URL", "http://localhost:3000")
        buildConfigField(FieldSpec.Type.STRING, "APP_VERSION_NAME", "$versionNameProperty-dev [$versionBuildProperty]")
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
            buildConfigField(FieldSpec.Type.STRING, "BASE_URL_ANDROID", "http://10.0.2.2:3000")
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
