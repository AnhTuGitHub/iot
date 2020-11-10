package com.fiona.fionaretailtest

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.EventChannel.EventSink
import io.flutter.plugin.common.MethodChannel


class MainActivity : FlutterActivity() {
    final val CHANNEL = "com.fiona.fionaretailtest/infowifi"
    final val EVENCHANNEL = "com.fiona.fionaretailtest/scanwifi-even"
    val wifiIOT = WifiIOT()
    override fun onStop() {
        super.onStop()
        unregisterReceiver(wifiIOT.wifiScanReceiver);
        Log.d("Stop App", "unregisterReceiver")
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        EventChannel(flutterEngine.dartExecutor.binaryMessenger, EVENCHANNEL).setStreamHandler(
                object : EventChannel.StreamHandler {
                    override fun onListen(arguments: Any?, events: EventSink?) {
                        checkPermission()
                        if (events != null) {
                            wifiIOT.scanWifi(context, events)
                        }
                    }
                    override fun onCancel(arguments: Any?) {
                        TODO("Not yet implemented")
                    }
                }
        )
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "GetInfoWifi") {
                checkPermission()
                val info = wifiIOT.getInfo(context)
                if (info != null) result.success(info)
            }
        }
    }

    fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // 23
            val permission1 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            // Check for permissions
            if (permission1 != PackageManager.PERMISSION_GRANTED) {
                Log.d("Permission", "Requesting Permissions")

                // Request permissions
                ActivityCompat.requestPermissions(this, arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.ACCESS_NETWORK_STATE
                ), 111333)
                return
            }
            Log.d("LOG_TAG", "Permissions Already Granted")
        }
    }
}
