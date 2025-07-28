# AndroidDeviceInfo

📱 A simple Android library to collect detailed device, build, screen, locale, battery, storage, and network information — in one place.

---

## 📦 Installation

Add [JitPack](https://jitpack.io) to your root `settings.gradle`:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://www.jitpack.io' }
    }
}
```

Then, add the dependency to your app-level `build.gradle`:

```groovy
implementation 'com.github.aakashsakhalkar:AndroidDeviceInfo:v1.0.0'
```

---

## ✅ Example Usage

```java
DeviceInfoX infoX = new DeviceInfoX(this);
DeviceInfoX.DeviceInfo info = infoX.getInfo();

Log.d("DeviceInfoX", "===== DEVICE INFO START =====");
Log.d("DeviceInfoX", "Device: " + info.device);
Log.d("DeviceInfoX", "Model: " + info.model);
Log.d("DeviceInfoX", "Brand: " + info.brand);
Log.d("DeviceInfoX", "Manufacturer: " + info.manufacturer);
Log.d("DeviceInfoX", "Product: " + info.product);
Log.d("DeviceInfoX", "Hardware: " + info.hardware);
Log.d("DeviceInfoX", "Bootloader: " + info.bootloader);
Log.d("DeviceInfoX", "Fingerprint: " + info.fingerprint);
Log.d("DeviceInfoX", "Android Version: " + info.androidVersion);
Log.d("DeviceInfoX", "API Level: " + info.apiLevel);
Log.d("DeviceInfoX", "Security Patch: " + info.securityPatch);
Log.d("DeviceInfoX", "Build ID: " + info.buildId);
Log.d("DeviceInfoX", "Build Tags: " + info.buildTags);
Log.d("DeviceInfoX", "Build Type: " + info.buildType);
Log.d("DeviceInfoX", "Build Display: " + info.buildDisplay);
Log.d("DeviceInfoX", "Build Time: " + info.buildTime);
Log.d("DeviceInfoX", "Language: " + info.language);
Log.d("DeviceInfoX", "Country: " + info.country);
Log.d("DeviceInfoX", "Locale: " + info.locale);
Log.d("DeviceInfoX", "Time Zone: " + info.timeZone);
Log.d("DeviceInfoX", "Screen Width (px): " + info.screenWidthPx);
Log.d("DeviceInfoX", "Screen Height (px): " + info.screenHeightPx);
Log.d("DeviceInfoX", "Density DPI: " + info.densityDpi);
Log.d("DeviceInfoX", "Density: " + info.density);
Log.d("DeviceInfoX", "Package Name: " + info.packageName);
Log.d("DeviceInfoX", "Version Name: " + info.versionName);
Log.d("DeviceInfoX", "Version Code: " + info.versionCode);
Log.d("DeviceInfoX", "Android ID: " + info.androidId);
Log.d("DeviceInfoX", "SIM Country ISO: " + info.simCountryIso);
Log.d("DeviceInfoX", "Network Operator Name: " + info.networkOperatorName);
Log.d("DeviceInfoX", "Phone Type: " + info.phoneType);
Log.d("DeviceInfoX", "Battery %: " + info.batteryPct);
Log.d("DeviceInfoX", "Battery Status: " + info.batteryStatus);
Log.d("DeviceInfoX", "Battery Plugged: " + info.batteryPlugged);
Log.d("DeviceInfoX", "Total Storage: " + info.totalInternalStorage);
Log.d("DeviceInfoX", "Available Storage: " + info.availableInternalStorage);
Log.d("DeviceInfoX", "Network Type: " + info.networkType);
Log.d("DeviceInfoX", "Is Rooted: " + info.isRooted);
Log.d("DeviceInfoX", "Is Emulator: " + info.isEmulator);

for (String sensorName : info.sensorList) {
    Log.d("DeviceInfoX", "Sensor: " + sensorName);
}
Log.d("DeviceInfoX", "===== DEVICE INFO END =====");
```

---

## 🔐 Permissions Required

To access certain data, you must declare the following in your `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" /> <!-- Optional: if using phone-type info on older Android versions -->
```

> ⚠️ IMEI and some telephony details are restricted on Android 10+ unless your app is a system app or default dialer.

---

## 💬 Contributions & Feedback

Feel free to open issues or submit pull requests if you want to extend the info fields or improve the structure.

---

## 📘 License

[MIT](LICENSE)
