///
/// Copyright (c) 2016 Dropbox, Inc. All rights reserved.
///
/// Auto-generated by Stone, do not modify.
///

#import <Foundation/Foundation.h>

#import "DBSerializableProtocol.h"

@class DBTEAMLOGGroupRemoveExternalIdDetails;

NS_ASSUME_NONNULL_BEGIN

#pragma mark - API Object

///
/// The `GroupRemoveExternalIdDetails` struct.
///
/// Removed external ID for group.
///
/// This class implements the `DBSerializable` protocol (serialize and
/// deserialize instance methods), which is required for all Obj-C SDK API route
/// objects.
///
@interface DBTEAMLOGGroupRemoveExternalIdDetails : NSObject <DBSerializable, NSCopying>

#pragma mark - Instance fields

/// Old external id.
@property (nonatomic, readonly, copy) NSString *previousValue;

#pragma mark - Constructors

///
/// Full constructor for the struct (exposes all instance variables).
///
/// @param previousValue Old external id.
///
/// @return An initialized instance.
///
- (instancetype)initWithPreviousValue:(NSString *)previousValue;

- (instancetype)init NS_UNAVAILABLE;

@end

#pragma mark - Serializer Object

///
/// The serialization class for the `GroupRemoveExternalIdDetails` struct.
///
@interface DBTEAMLOGGroupRemoveExternalIdDetailsSerializer : NSObject

///
/// Serializes `DBTEAMLOGGroupRemoveExternalIdDetails` instances.
///
/// @param instance An instance of the `DBTEAMLOGGroupRemoveExternalIdDetails`
/// API object.
///
/// @return A json-compatible dictionary representation of the
/// `DBTEAMLOGGroupRemoveExternalIdDetails` API object.
///
+ (nullable NSDictionary<NSString *, id> *)serialize:(DBTEAMLOGGroupRemoveExternalIdDetails *)instance;

///
/// Deserializes `DBTEAMLOGGroupRemoveExternalIdDetails` instances.
///
/// @param dict A json-compatible dictionary representation of the
/// `DBTEAMLOGGroupRemoveExternalIdDetails` API object.
///
/// @return An instantiation of the `DBTEAMLOGGroupRemoveExternalIdDetails`
/// object.
///
+ (DBTEAMLOGGroupRemoveExternalIdDetails *)deserialize:(NSDictionary<NSString *, id> *)dict;

@end

NS_ASSUME_NONNULL_END
