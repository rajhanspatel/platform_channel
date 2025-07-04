package com.example.platform_channel_practice

import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    private val CHANNEL = "channel"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)


        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).setMethodCallHandler { call, result ->
            if (call.method == "sendMessage")
            {
                val message = call.argument<String>("message")
                if (message == "hi") {
                    result.success("hello 1")
                } else {
                    result.error("INVALID_MESSAGE", "Expected 'hi', got '$message'", null)
                }
            } else {
                result.notImplemented()
            }
        }
    }
}
