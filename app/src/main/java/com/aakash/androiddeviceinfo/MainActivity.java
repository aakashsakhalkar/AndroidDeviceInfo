package com.aakash.androiddeviceinfo;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
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
    }
}
