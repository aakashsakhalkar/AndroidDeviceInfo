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

      
}
