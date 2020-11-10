package com.fiona.fionaretailtest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.ScanResult
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.util.Log
import io.flutter.plugin.common.EventChannel
import java.math.BigInteger
import java.net.Inet4Address
import java.net.InetAddress
import java.util.*

class WifiIOT() {
    lateinit var wifiScanReceiver:BroadcastReceiver
    fun scanWifi(context: Context, events: EventChannel.EventSink) {
        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        println(wifiManager.isWifiEnabled)
        if (wifiManager.isWifiEnabled) {
            wifiScanReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
                    if (success) {
                        val results = wifiManager.scanResults
                        Log.d("SSID", "|1==>" + results.size)
                        val listSSID = mutableListOf<String>()
                        for (item in results) {
//                            Log.d("SSID", "|1==>" + item.SSID + " | " + item.BSSID)
                            listSSID.add(item.SSID)
                        }
                        events.success(listSSID)
                    } else {
                        val results = wifiManager.scanResults
                        Log.d("SSID", "|2==>" + results.size)
                        events.success("Not available")
                    }
                }
            }
            context.registerReceiver(wifiScanReceiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        }
    }
    fun getInfo(context: Context): String? {
        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if (wifiManager.isWifiEnabled) {
            val infoWifi: WifiInfo = wifiManager.connectionInfo
            val ipAddress = infoWifi.ipAddress
            val  bytes = BigInteger.valueOf(ipAddress.toLong()).toByteArray()
            val address = InetAddress.getByAddress(bytes)
            Log.i("INFO", infoWifi.ssid + " | " + address.hostAddress )
            return (infoWifi.ssid + " | " + address.hostAddress)
        }
        return null
    }
}