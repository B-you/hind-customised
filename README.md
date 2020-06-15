# Hind - Secure, Simple and Scalable Video Conferences

Hind is an open-source (Apache) WebRTC JavaScript application that uses [Jitsi Videobridge](https://jitsi.org/videobridge) to provide high quality, [secure](https://jitsi.org/security) and scalable video conferences. Jitsi Meet in action can be seen at [here at the session #482 of the VoIP Users Conference](http://youtu.be/7vFUVClsNh0).

The Hind client runs in your browser, without installing anything else on your computer. You can try it out at https://meet.jit.si.

Hind allows very efficient collaboration. Users can stream their desktop or only some windows. It also supports shared document editing with Etherpad.

## Installation

On the client side, no installation is necessary. You just point your browser to the URL of your deployment. This section is about installing a Hind suite on your server and hosting your own conferencing service.

Installing Hind is a simple experience. For Debian-based system, following the [quick-install](https://github.com/jitsi/jitsi-meet/blob/master/doc/quick-install.md) document, which uses the package system. You can also see a demonstration of the process in [this tutorial video](https://jitsi.org/tutorial).

For other systems, or if you wish to install all components manually, see the [detailed manual installation instructions](https://github.com/jitsi/jitsi-meet/blob/master/doc/manual-install.md).

## Download

| Latest stable release | [![release](https://img.shields.io/badge/release-latest-green.svg)](https://github.com/jitsi/jitsi-meet/releases/latest) |
|---|---|

You can download Debian/Ubuntu binaries:
* [stable](https://download.jitsi.org/stable/) ([instructions](https://jitsi.org/downloads/ubuntu-debian-installations-instructions/))
* [testing](https://download.jitsi.org/testing/) ([instructions](https://jitsi.org/downloads/ubuntu-debian-installations-instructions-for-testing/))
* [nightly](https://download.jitsi.org/unstable/) ([instructions](https://jitsi.org/downloads/ubuntu-debian-installations-instructions-nightly/))

You can download source archives (produced by ```make source-package```):
* [source builds](https://download.jitsi.org/jitsi-meet/src/)

### Mobile apps

* [Android](https://play.google.com/store/apps/details?id=net.hind.android)

[<img src="resources/img/google-play-badge.png" height="50">](https://play.google.com/store/apps/details?id=net.hind.android)

* [iOS](https://apps.apple.com/us/app/hind/id1515297954)

[<img src="resources/img/appstore-badge.png" height="50">](https://apps.apple.com/us/app/hind/id1515297954)

## Development

For web development see [here](doc/development.md), and for mobile see [here](doc/mobile.md).

## Contributing

If you are looking to contribute to Hind, first of all, thank you! Please
see our [guidelines for contributing](CONTRIBUTING.md).

## Embedding in external applications

Hind provides a very flexible way of embedding in external applications by using the [Jitsi Meet API](doc/api.md).

## Security

The security section here was starting to feel a bit too succinct for the complexity of the topic, so we created a post that covers the topic much more broadly here: https://jitsi.org/security

The section on end-to-end encryption in that document is likely going to be one of the key points of interest: https://jitsi.org/security/#e2ee

## Security issues

For information on reporting security vulnerabilities in Jitsi Meet, see [SECURITY.md](./SECURITY.md).

## Acknowledgements

Hind is build using Jitsi Meet which was started out as a sample conferencing application using Jitsi Videobridge. It was originally developed by ESTOS' developer Philipp Hancke who then contributed it to the community where development continues with joint forces!
