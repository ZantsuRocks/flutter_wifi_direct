package rocks.zantsu.flutter_wifi_direct;

import android.app.Activity;
import android.net.wifi.p2p.WifiP2pManager;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * FlutterWifiDirectPlugin
 */
public class FlutterWifiDirectPlugin implements FlutterPlugin, MethodCallHandler/*, EventChannel.StreamHandler, RequestPermissionsResultListener*/ {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private EventChannel eventChannel;

  private Activity moActivity;
  private WifiP2pManager wifiDirectManager;


  // initialize members of this class with Activity
  private void initWithActivity(Activity activity) {
    moActivity = activity;
  }

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_wifi_direct");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    switch (call.method) {
      case "getPlatformVersion": //#PLACEHOLDER
        result.success("Android " + android.os.Build.VERSION.RELEASE);
        break;
      case "initService":
        result.notImplemented();
        break;
      case "getDevices":
        String ex = call.argument("example");
        String tmp = getDevices(ex);
        result.success(tmp);
        break;
      case "onReceive":
        result.notImplemented();
        break;
      default:
        result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  String getDevices(String input) {
    return input + " Ola, Oi.";
  }
}
