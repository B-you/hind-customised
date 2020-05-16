///
/// Copyright (c) 2016 Dropbox, Inc. All rights reserved.
///
/// Auto-generated by Stone, do not modify.
///

#import <Foundation/Foundation.h>

#import "DBSerializableProtocol.h"

@class DBFILEPROPERTIESUpdateTemplateResult;

NS_ASSUME_NONNULL_BEGIN

#pragma mark - API Object

///
/// The `UpdateTemplateResult` struct.
///
/// This class implements the `DBSerializable` protocol (serialize and
/// deserialize instance methods), which is required for all Obj-C SDK API route
/// objects.
///
@interface DBFILEPROPERTIESUpdateTemplateResult : NSObject <DBSerializable, NSCopying>

#pragma mark - Instance fields

/// An identifier for template added by route  See `templatesAddForUser` or
/// `templatesAddForTeam`.
@property (nonatomic, readonly, copy) NSString *templateId;

#pragma mark - Constructors

///
/// Full constructor for the struct (exposes all instance variables).
///
/// @param templateId An identifier for template added by route  See
/// `templatesAddForUser` or `templatesAddForTeam`.
///
/// @return An initialized instance.
///
- (instancetype)initWithTemplateId:(NSString *)templateId;

- (instancetype)init NS_UNAVAILABLE;

@end

#pragma mark - Serializer Object

///
/// The serialization class for the `UpdateTemplateResult` struct.
///
@interface DBFILEPROPERTIESUpdateTemplateResultSerializer : NSObject

///
/// Serializes `DBFILEPROPERTIESUpdateTemplateResult` instances.
///
/// @param instance An instance of the `DBFILEPROPERTIESUpdateTemplateResult`
/// API object.
///
/// @return A json-compatible dictionary representation of the
/// `DBFILEPROPERTIESUpdateTemplateResult` API object.
///
+ (nullable NSDictionary<NSString *, id> *)serialize:(DBFILEPROPERTIESUpdateTemplateResult *)instance;

///
/// Deserializes `DBFILEPROPERTIESUpdateTemplateResult` instances.
///
/// @param dict A json-compatible dictionary representation of the
/// `DBFILEPROPERTIESUpdateTemplateResult` API object.
///
/// @return An instantiation of the `DBFILEPROPERTIESUpdateTemplateResult`
/// object.
///
+ (DBFILEPROPERTIESUpdateTemplateResult *)deserialize:(NSDictionary<NSString *, id> *)dict;

@end

NS_ASSUME_NONNULL_END