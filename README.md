# saudiMetaverseUnityAndroidLibrary

## Prerequisites:
- **Android Studio Koala | 2024.1.1 Patch 2**
- **Kotlin** 1.9.0

Make sure the Android device is connected and USB Debugging is enabled.

## Steps to Integrate Saudi Metaverse Unity Library in Project for Android App using kotlin:


1. Add the following dependencies to your app level `build.gradle.kts` file `/app/build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.NarsunOrg:saudiMetaverseUnityAndroidLibrary:latest-version")//i.e v1.1.1
}
```
2. In `/app/build.gradle`, ensure your `minSdkVersion` is at least 22:

```kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.demo.unitykotlin"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.demo.unitykotlin"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        ndk {
            abiFilters.add("armeabi-v7a")
            abiFilters.add("arm64-v8a")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    aaptOptions {
        noCompress("unity3d", "ress", "resource", "obb")
        ignoreAssetsPattern = "!.svn:!.git:!.ds_store:!*.scc:.*:!CVS:!thumbs.db:!picasa.ini:!*~"
    }

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
    implementation("com.github.NarsunOrg:saudiMetaverseUnityAndroidLibrary:v1.1.1")
}

```


3. Clean, clear cache and Rebuild of project:
   ```
    ./gradlew clean
    ./gradlew build
    ```
4. Modify your `MainActivity` class. This is usually located at `<Android project>//app/src/main/kotlin/com/<your org>/MainActivity.kt`:

```kotlin

package com.demo.unitykotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demo.unitykotlin.ui.theme.UnityKotlinDemoTheme
import com.unity3d.player.IUnityMessageListener
import com.unity3d.player.UnityPlayer
import com.unity3d.player.UnityPlayerActivity


// IUnityMessageListener interface used to share messages between UnityPlayerActivity to MainActivity
class MainActivity : ComponentActivity(), IUnityMessageListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UnityPlayerActivity.setiUnityMessageListener(this)
        enableEdgeToEdge()
        setContent {
            UnityKotlinDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = { callUnityPlayerActivity() }
                        ) {
                            Text(text = "Start UnityPlayer")
                        }
                    }
                }
            }
        }

    }
    //star Unity Player
    private fun callUnityPlayerActivity(){
        val intent = Intent(this, UnityPlayerActivity::class.java)
        startActivity(intent);
    }


    // Message received from unity
    override fun onUnityMessageReceived(route: String?) {
        Log.e("abc:", "MainActivity onUnityMessageReceived: $route")
        sendMessageToUnity(route)
    }
}
// send message to Unity
fun sendMessageToUnity(route: String?) {
    Log.e("abc:", "MainActivity sendMessageToUnity: ", )
    if(route == AndroidRouts.AUTH_TOKEN){
        UnityPlayer.UnitySendMessage("AndroidNativeBridge", "ReceiveMessageFromAndroid", "Paste auth token here...")
    }
}
class AndroidRouts {
    companion object {
        const val AUTH_TOKEN = "AUTH_TOKEN"
        const val HOME_PAGE = "HOME_PAGE"
        const val UNITY_INITIALIZED = "UNITY_INITIALIZED"
    }
}



@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnityKotlinDemoTheme {
        Greeting("Android")
    }
}



```


5. Connect your Android device and run .


