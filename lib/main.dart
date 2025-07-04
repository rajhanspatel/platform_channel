import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // Channel name — must match Android side
  static const platform = MethodChannel('channel');

  Future<void> sendMessageToNative() async {
    try {
      final String result = await platform.invokeMethod('sendMessage', {"message": "hi"});
      print("Response from Android: $result");
    } on PlatformException catch (e) {
      print("Failed to invoke method: '${e.message}'.");
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('Platform Channel Demo')),
        body: Center(
          child: ElevatedButton(
            onPressed: sendMessageToNative,
            child: Text('Send "hi" to Android'),
          ),
        ),
      ),
    );
  }
}
