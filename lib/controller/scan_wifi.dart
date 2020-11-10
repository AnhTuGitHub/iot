import 'dart:async';

import 'package:flutter/services.dart';

class ScanWifi {
  static const platformScanWifi =
      const MethodChannel('com.fiona.fionaretailtest/infowifi');
  static const platformEventWifi =
      const EventChannel('com.fiona.fionaretailtest/scanwifi-even');
  StreamController<String> listSSIDController =
      new StreamController<String>.broadcast();
  StreamController<String> wifiInfoController =
  new StreamController<String>.broadcast();
  getListWifi() async {
    ScanWifi.platformEventWifi.receiveBroadcastStream().listen((event) {
      print(event.toString());
      listSSIDController.sink.add(event.toString());
    });
  }

  getInfoWifi() async {
    print("|get wifi info------->");
    String result = await platformScanWifi.invokeMethod('GetInfoWifi');
    wifiInfoController.sink.add(result);
  }
}
