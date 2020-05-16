///
/// Copyright (c) 2016 Dropbox, Inc. All rights reserved.
///
/// Auto-generated by Stone, do not modify.
///

#import <Foundation/Foundation.h>

#import "DBSerializableProtocol.h"

@class DBTEAMLOGDeviceManagementDisabledDetails;

NS_ASSUME_NONNULL_BEGIN

#pragma mark - API Object

///
/// The `DeviceManagementDisabledDetails` struct.
///
/// Disabled device management.
///
/// This class implements the `DBSerializable` protocol (serialize and
/// deserialize instance methods), which is required for all Obj-C SDK API route
/// objects.
///
@interface DBTEAMLOGDeviceManagementDisabledDetails : NSObject <DBSerializable, NSCopying>

#pragma mark - Instance fields

#pragma mark - Constructors

///
/// Full constructor for the struct (exposes all instance variables).
///
/// @return An initialized instance.
///
- (instancetype)initDefault;

- (instancetype)init NS_UNAVAILABLE;

@end

#pragma mark - Serializer Object

///
/// The serialization class for the `DeviceManagementDisabledDetails` struct.
///
@interface DBTEAMLOGDeviceManagementDisabledDetailsSerializer : NSObject

///
/// Serializes `DBTEAMLOGDeviceManagementDisabledDetails` instances.
///
/// @param instance An instance of the
/// `DBTEAMLOGDeviceManagementDisabledDetails` API object.
///
/// @return A json-compatible dictionary representation of the
/// `DBTEAMLOGDeviceManagementDisabledDetails` API object.
///
+ (nullable NSDictionary<NSString *, id> *)serialize:(DBTEAMLOGDeviceManagementDisabledDetails *)instance;

///
/// Deserializes `DBTEAMLOGDeviceManagementDisabledDetails` instances.
///
/// @param dict A json-compatible dictionary representation of the
/// `DBTEAMLOGDeviceManagementDisabledDetails` API object.
///
/// @return An instantiation of the `DBTEAMLOGDeviceManagementDisabledDetails`
/// object.
///
+ (DBTEAMLOGDeviceManagementDisabledDetails *)deserialize:(NSDictionary<NSString *, id> *)dict;

@end

NS_ASSUME_NONNULL_END
