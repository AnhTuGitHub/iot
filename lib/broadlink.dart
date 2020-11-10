import 'package:flutter/material.dart';
import 'package:fionaretailtest/controller/connect_socket_broadlink.dart';

class BroadLink extends StatefulWidget {
  @override
  _BroadLinkState createState() => _BroadLinkState();
}

class _BroadLinkState extends State<BroadLink> {
  ConnetSoketBroadLink _connetSoketBroadLink = new ConnetSoketBroadLink();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Test BroadLink"),
        centerTitle: true,
      ),
      body: SafeArea(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            RaisedButton(
              onPressed: () {
                _connetSoketBroadLink.connectBroadLink();
              },
              child: Text("Connect BroadLink"),
            ),
            // Text("data"),
          ],
        ),
      ),
    );
  }
}
