/*
 * Copyright @ 2018-present 8x8, Inc.
 * Copyright @ 2017-2018 Atlassian Pty Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#import "AppDelegate.h"
#import "FIRUtilities.h"
#import "Types.h"
#import "ViewController.h"

@import Crashlytics;
@import Fabric;
@import Firebase;
@import JitsiMeet;


@implementation AppDelegate

-             (BOOL)application:(UIApplication *)application
  didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {

    // Initialize Crashlytics and Firebase if a valid GoogleService-Info.plist file was provided.
    if ([FIRUtilities appContainsRealServiceInfoPlist]) {
        NSLog(@"Enablign Crashlytics and Firebase");
        [FIRApp configure];
        [Fabric with:@[[Crashlytics class]]];
    }

    JitsiMeet *jitsiMeet = [JitsiMeet sharedInstance];

    jitsiMeet.conferenceActivityType = JitsiMeetConferenceActivityType;
    jitsiMeet.customUrlScheme = @"com.joga.world";
    jitsiMeet.universalLinkDomains = @[@"com.joga.world"];

    jitsiMeet.defaultConferenceOptions = [JitsiMeetConferenceOptions fromBuilder:^(JitsiMeetConferenceOptionsBuilder *builder) {
        builder.serverURL = [NSURL URLWithString:@"https://video.jogaworld.com/"];
        builder.welcomePageEnabled = YES;

        // Apple rejected our app because they claim requiring a
        // Dropbox account for recording is not acceptable.
#if DEBUG
        [builder setFeatureFlag:@"ios.recording.enabled" withBoolean:YES];
#endif
    }];

    [jitsiMeet application:application didFinishLaunchingWithOptions:launchOptions];

    return YES;
}

#pragma mark Linking delegate methods

-    (BOOL)application:(UIApplication *)application
  continueUserActivity:(NSUserActivity *)userActivity
    restorationHandler:(void (^)(NSArray<id<UIUserActivityRestoring>> *restorableObjects))restorationHandler {

  
    NSLog(@"...continueUserActivity...000000...%@...", userActivity.webpageURL);
    NSString * strUrl =userActivity.webpageURL.absoluteString;
    
    if([strUrl hasPrefix:@"https://"]){
      /*UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];

      // Get instance of initial Viewcontroller from storyboard
      UINavigationController *navController = [storyboard instantiateInitialViewController
      ];
      // Get instance of desired viewcontroller
          ViewController *viewController = [storyboard instantiateViewControllerWithIdentifier:@"ViewController"];
      [viewController setIsLoadFromUrl: url];
      // Push ViewController on to NavigationController
      [navController pushViewController:viewController  animated:NO];
             return YES;*/
      NSLog(@"...continueUserActivity...888888...%@...", strUrl);
      
      NSURL *url = CFBridgingRelease(CFURLCreateWithFileSystemPath(kCFAllocatorDefault, (CFStringRef)strUrl, kCFURLWindowsPathStyle, false));
      NSString *fileName = url.lastPathComponent;
      NSString *parentDirectory = url.URLByDeletingLastPathComponent.path;

      NSLog(@"....%@....continueUserActivity...2345555...%@...", fileName, parentDirectory);

       /*UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
       ViewController *viewController=[storyboard instantiateViewControllerWithIdentifier:@"ViewController"];
      [viewController setIsLoadFromUrl: fileName];
      [self.window.rootViewController.navigationController pushViewController:viewController animated:YES];*/
      
       UIStoryboard *mainStoryboard = [UIStoryboard storyboardWithName:@"Main" bundle: nil];
      ViewController *main = (ViewController *)[mainStoryboard instantiateViewControllerWithIdentifier:@"ViewController"];
      [main setLoadFromUrl: strUrl];
      [main setRoomName: fileName];
          self.window.rootViewController = main;
      return YES;
    }
  
    if ([FIRUtilities appContainsRealServiceInfoPlist]) {
        // 1. Attempt to handle Universal Links through Firebase in order to support
        //    its Dynamic Links (which we utilize for the purposes of deferred deep
        //    linking).
        BOOL handled
          = [[FIRDynamicLinks dynamicLinks]
                handleUniversalLink:userActivity.webpageURL
                         completion:^(FIRDynamicLink * _Nullable dynamicLink, NSError * _Nullable error) {
           NSURL *firebaseUrl = [FIRUtilities extractURL:dynamicLink];
           if (firebaseUrl != nil) {
             userActivity.webpageURL = firebaseUrl;
             [[JitsiMeet sharedInstance] application:application
                                continueUserActivity:userActivity
                                  restorationHandler:restorationHandler];
           }
        }];

        if (handled) {
          return handled;
        }
    }

    // 2. Default to plain old, non-Firebase-assisted Universal Links.
    return [[JitsiMeet sharedInstance] application:application
                              continueUserActivity:userActivity
                                restorationHandler:restorationHandler];
}

- (BOOL)application:(UIApplication *)app
            openURL:(NSURL *)url
            options:(NSDictionary<UIApplicationOpenURLOptionsKey,id> *)options {

    // This shows up during a reload in development, skip it.
    // https://github.com/firebase/firebase-ios-sdk/issues/233
    if ([[url absoluteString] containsString:@"google/link/?dismiss=1&is_weak_match=1"]) {
        return NO;
    }

    NSURL *openUrl = url;

    if ([FIRUtilities appContainsRealServiceInfoPlist]) {
        // Process Firebase Dynamic Links
        FIRDynamicLink *dynamicLink = [[FIRDynamicLinks dynamicLinks] dynamicLinkFromCustomSchemeURL:url];
        NSURL *firebaseUrl = [FIRUtilities extractURL:dynamicLink];
        if (firebaseUrl != nil) {
            openUrl = firebaseUrl;
        }
    }

    return [[JitsiMeet sharedInstance] application:app
                                           openURL:openUrl
                                           options:options];
}

@end
