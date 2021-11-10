package rocks.zantsu.flutter_wifi_direct;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.flutter.plugin.common.PluginRegistry.ViewDestroyListener;
import io.flutter.plugin.common.PluginRegistry.RequestPermissionsResultListener;

/**
 * FlutterWifiDirectPlugin
 */
public class FlutterWifiDirectPlugin implements FlutterPlugin, ActivityAware, MethodCallHandler, EventChannel.StreamHandler/*, RequestPermissionsResultListener*/ {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private EventChannel eventChannel;

  private Activity moActivity;
  private Context moContext;
  private WifiP2pManager wifiDirectManager;


  // initialize members of this class with Activity
  private void initWithActivity(Activity activity) {
    moActivity = activity;
  }

  // initialize members of this class with Context
  private void initWithContext(Context context) {
    moContext = context;
    wifiDirectManager = (WifiP2pManager) moContext.getApplicationContext().getSystemService(Context.WIFI_P2P_SERVICE);
  }

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_wifi_direct");
    eventChannel = new EventChannel(flutterPluginBinding.getBinaryMessenger(), "plugins.zantsu.rocks/wifi_direct");
    channel.setMethodCallHandler(this);
    eventChannel.setStreamHandler(this);

    //INICIAR CONTEXTO OU ACTIVITY!
    initWithContext(flutterPluginBinding.getApplicationContext());
//    initWithActivity(binding.getApplicationActivity());
  }

  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
    // init with activity
    initWithActivity(binding.getActivity());
//    binding.addRequestPermissionsResultListener(this);
  }
  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
    // init with activity
    initWithActivity(binding.getActivity());
//    binding.addRequestPermissionsResultListener(this);
  }
  @Override
  public void onDetachedFromActivityForConfigChanges() {
    // set activity to null
    moActivity = null;
  }
  @Override
  public void onDetachedFromActivity() {
    // set activity to null
    moActivity = null;
  }

  @Override
  public void onCancel(Object o) {
//    if (receiver != null) {
//      moContext.unregisterReceiver(receiver);
//      receiver = null;
//    }
  }
  @Override
  public void onListen(Object o, EventChannel.EventSink eventSink) {
//    receiver = createReceiver(eventSink);
//    moContext.registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
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
        getDevices(call, result);
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
    eventChannel.setStreamHandler(null);

    //Limpezas necessarias
//    channel = null;
//    eventChannel = null;
//    moActivity = null;
//    moContext = null;
//    wifiDirectManager = null;
  }

  void getDevices(final MethodCall call, final Result result) {
    new Thread(){
      public void run(){
        final String ex = call.argument("example");
        String tmp = ex + " Ola, Oi.";
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.post(
          new Runnable(){
            @Override
            public void run() {
              result.success(tmp);
            }
          }
        );
      }
    }.start();
  }
}
