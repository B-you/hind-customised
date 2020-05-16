# Jitsi Meet Mobile Verion - Development Environment Setup

Install Below Softwares

1. VS Code (https://code.visualstudio.com/t)
2. Android Studio
3. Xcode in Mac OS

Checkout Latest source

Navigate to base folder in terminal / cmd

run `yarn install`

It may take some time in installing all dependencies.

Once its completed,please navigate to ./node_module/lib-jitsi-meet/ folder in terminl / CMD

run `yarn install`

It may take some time in installing all dependencies.

Once the installation of all dependencies get completed, 

Change our meet server configuration in below file paths

# Android
/jitsi-meet-master/android/app/src/main/java/org/jitsi/meet/MainActivity.java

Please find this code snippet and make necessory changes 

.setServerURL(buildURL("https://vconference.streamed.co.in/"))

# iOS

/Volumes/Development/Rajesh/Rajkumar/jitsi-meet-master/ios/app/src/AppDelegate.m

builder.serverURL = [NSURL URLWithString:@"https://vconference.streamed.co.in/"];


Please start emulator or android / iOS from Android Studio / Xcode

# Run project in android platform

Navigate to project base path

run `react-native run-android`

# Run project in iOS platform

Navigate to project base path

run `react-native run-ios`

project will start build and run same in respective simulators.


