import 'package:flutter/material.dart';
import 'package:flutter_webview_plugin/flutter_webview_plugin.dart';
import 'package:webview_flutter/webview_flutter.dart';

class SonOff extends StatefulWidget {
  @override
  _SonOffState createState() => _SonOffState();
}

class _SonOffState extends State<SonOff> {
  @override
  Widget build(BuildContext context) {
    return WebviewScaffold(
      url: "http://192.168.43.88",
      appBar: AppBar(
        title: Text("-----------"),
      ),
      initialChild: Container(
        color: Colors.redAccent,
        child: Center(
          child: Text('Waiting.....'),
        ),
      ),
    );
  }
}
