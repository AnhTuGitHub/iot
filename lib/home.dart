import 'package:fionaretailtest/broadlink.dart';
import 'package:fionaretailtest/controller/scan_wifi.dart';
import 'package:fionaretailtest/sonoff.dart';
import 'package:flutter/material.dart';

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  ScanWifi _scanWifi = new ScanWifi();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _scanWifi.getListWifi();
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Fiona Retail"),
        centerTitle: true,
      ),
      body: SafeArea(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            StreamBuilder<String>(
              stream: _scanWifi.listSSIDController.stream,
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  return Text(snapshot.data.toString());
                }
                return CircularProgressIndicator();
              },
            ),
            Divider(),
            Text("Info wifi:"),
            RaisedButton(
              onPressed: () {
                _scanWifi.getInfoWifi();
              },
              child: Text("Get Info Wifi"),
            ),
            StreamBuilder<String>(
              stream: _scanWifi.wifiInfoController.stream,
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  return Text(snapshot.data.toString());
                }
                return Text("___");
              },
            ),
            Divider(),
            Divider(),
            RaisedButton(
              onPressed: () {
                Navigator.of(context)
                    .push(MaterialPageRoute(builder: (context) => BroadLink()));
              },
              child: Text("Broad Link Wifi"),
            ),
            Divider(),
            RaisedButton(
              onPressed: () {
                Navigator.of(context)
                    .push(MaterialPageRoute(builder: (context) => SonOff()));
              },
              child: Text("SonOff"),
            ),
          ],
        ),
      ),
    );
  }
}
