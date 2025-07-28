package com.aakash.androiddeviceinfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class DeviceInfoX {

    private final Context context;

    public DeviceInfoX(Context context) {
        this.context = context.getApplicationContext();
    }

    @SuppressLint("HardwareIds")
    public DeviceInfo getInfo() {
        DeviceInfo info = new DeviceInfo();

        // Build and OS Info
        info.device = Build.DEVICE;
        info.model = Build.MODEL;
        info.brand = Build.BRAND;
        info.manufacturer = Build.MANUFACTURER;
        info.product = Build.PRODUCT;
        info.hardware = Build.HARDWARE;
        info.bootloader = Build.BOOTLOADER;
        info.fingerprint = Build.FINGERPRINT;
        info.androidVersion = Build.VERSION.RELEASE;
        info.apiLevel = Build.VERSION.SDK_INT;
        info.securityPatch = Build.VERSION.SECURITY_PATCH;
        info.buildId = Build.ID;
        info.buildTime = Build.TIME;
        info.buildTags = Build.TAGS;
        info.buildType = Build.TYPE;
        info.buildDisplay = Build.DISPLAY;

        // Locale and Time
        Locale locale = Locale.getDefault();
        info.language = locale.getLanguage();
        info.country = locale.getCountry();
        info.locale = locale.toString();
        info.timeZone = TimeZone.getDefault().getID();

        // Display
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) wm.getDefaultDisplay().getMetrics(metrics);

        info.screenWidthPx = metrics.widthPixels;
        info.screenHeightPx = metrics.heightPixels;
        info.densityDpi = metrics.densityDpi;
        info.density = metrics.density;

        // App Info
        try {
            PackageInfo pkg = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            info.packageName = pkg.packageName;
            info.versionName = pkg.versionName;
            info.versionCode = pkg.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        // Android ID
        info.androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

        // Telephony
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
            info.simCountryIso = tm.getSimCountryIso();
            info.networkOperatorName = tm.getNetworkOperatorName();
            info.phoneType = tm.getPhoneType();
        }

        // Battery
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        if (batteryStatus != null) {
            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            info.batteryPct = level * 100 / (float) scale;
            info.batteryStatus = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            info.batteryPlugged = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        }

        // Storage
        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        info.totalInternalStorage = statFs.getTotalBytes();
        info.availableInternalStorage = statFs.getAvailableBytes();

        // Network Type
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null) {
                info.networkType = networkInfo.getTypeName();
            }
        }

        // Sensors
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
            for (Sensor sensor : sensors) {
                info.sensorList.add(sensor.getName());
            }
        }

        // Extras
        info.isEmulator = isProbablyEmulator();
        info.isRooted = isDeviceRooted();

        // Log everything
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
        Log.d("DeviceInfoX", "Screen Width: " + info.screenWidthPx);
        Log.d("DeviceInfoX", "Screen Height: " + info.screenHeightPx);
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
        Log.d("DeviceInfoX", "Total Internal Storage: " + info.totalInternalStorage);
        Log.d("DeviceInfoX", "Available Internal Storage: " + info.availableInternalStorage);
        Log.d("DeviceInfoX", "Network Type: " + info.networkType);
        Log.d("DeviceInfoX", "Is Emulator: " + info.isEmulator);
        Log.d("DeviceInfoX", "Is Rooted: " + info.isRooted);
        for (String sensor : info.sensorList) {
            Log.d("DeviceInfoX", "Sensor: " + sensor);
        }
        Log.d("DeviceInfoX", "===== DEVICE INFO END =====");

        return info;
    }

    private boolean isProbablyEmulator() {
        return Build.FINGERPRINT.contains("generic")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

    private boolean isDeviceRooted() {
        String[] paths = {
                "/system/app/Superuser.apk",
                "/sbin/su", "/system/bin/su", "/system/xbin/su",
                "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
                "/system/bin/failsafe/su", "/data/local/su"
        };
        for (String path : paths) {
            if (new java.io.File(path).exists()) return true;
        }
        return false;
    }

    public static class DeviceInfo {
        public String device, model, brand, manufacturer, product, hardware, bootloader, fingerprint;
        public String androidVersion, securityPatch, buildId, buildTags, buildType, buildDisplay;
        public long buildTime;
        public int apiLevel;

        public String language, country, locale, timeZone;
        public int screenWidthPx, screenHeightPx, densityDpi;
        public float density;

        public String packageName, versionName, androidId;
        public int versionCode;

        public String simCountryIso, networkOperatorName;
        public int phoneType;

        public boolean isRooted, isEmulator;

        public float batteryPct;
        public int batteryStatus, batteryPlugged;

        public long totalInternalStorage, availableInternalStorage;

        public String networkType;

        public List<String> sensorList = new ArrayList<>();
    }
}
