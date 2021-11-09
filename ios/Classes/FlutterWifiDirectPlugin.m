#import "FlutterWifiDirectPlugin.h"
#if __has_include(<flutter_wifi_direct/flutter_wifi_direct-Swift.h>)
#import <flutter_wifi_direct/flutter_wifi_direct-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_wifi_direct-Swift.h"
#endif

@implementation FlutterWifiDirectPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterWifiDirectPlugin registerWithRegistrar:registrar];
}
@end
