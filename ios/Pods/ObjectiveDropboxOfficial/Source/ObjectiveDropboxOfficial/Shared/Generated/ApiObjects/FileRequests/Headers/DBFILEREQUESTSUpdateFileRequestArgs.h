///
/// Copyright (c) 2016 Dropbox, Inc. All rights reserved.
///
/// Auto-generated by Stone, do not modify.
///

#import <Foundation/Foundation.h>

#import "DBSerializableProtocol.h"

@class DBFILEREQUESTSUpdateFileRequestArgs;
@class DBFILEREQUESTSUpdateFileRequestDeadline;

NS_ASSUME_NONNULL_BEGIN

#pragma mark - API Object

///
/// The `UpdateFileRequestArgs` struct.
///
/// Arguments for `update`.
///
/// This class implements the `DBSerializable` protocol (serialize and
/// deserialize instance methods), which is required for all Obj-C SDK API route
/// objects.
///
@interface DBFILEREQUESTSUpdateFileRequestArgs : NSObject <DBSerializable, NSCopying>

#pragma mark - Instance fields

/// The ID of the file request to update.
@property (nonatomic, readonly, copy) NSString *id_;

/// The new title of the file request. Must not be empty.
@property (nonatomic, readonly, copy, nullable) NSString *title;

/// The new path of the folder in the Dropbox where uploaded files will be sent.
/// For apps with the app folder permission, this will be relative to the app
/// folder.
@property (nonatomic, readonly, copy, nullable) NSString *destination;

/// The new deadline for the file request. Deadlines can only be set by
/// Professional and Business accounts.
@property (nonatomic, readonly) DBFILEREQUESTSUpdateFileRequestDeadline *deadline;

/// Whether to set this file request as open or closed.
@property (nonatomic, readonly, nullable) NSNumber *open;

#pragma mark - Constructors

///
/// Full constructor for the struct (exposes all instance variables).
///
/// @param id_ The ID of the file request to update.
/// @param title The new title of the file request. Must not be empty.
/// @param destination The new path of the folder in the Dropbox where uploaded
/// files will be sent. For apps with the app folder permission, this will be
/// relative to the app folder.
/// @param deadline The new deadline for the file request. Deadlines can only be
/// set by Professional and Business accounts.
/// @param open Whether to set this file request as open or closed.
///
/// @return An initialized instance.
///
- (instancetype)initWithId_:(NSString *)id_
                      title:(nullable NSString *)title
                destination:(nullable NSString *)destination
                   deadline:(nullable DBFILEREQUESTSUpdateFileRequestDeadline *)deadline
                       open:(nullable NSNumber *)open;

///
/// Convenience constructor (exposes only non-nullable instance variables with
/// no default value).
///
/// @param id_ The ID of the file request to update.
///
/// @return An initialized instance.
///
- (instancetype)initWithId_:(NSString *)id_;

- (instancetype)init NS_UNAVAILABLE;

@end

#pragma mark - Serializer Object

///
/// The serialization class for the `UpdateFileRequestArgs` struct.
///
@interface DBFILEREQUESTSUpdateFileRequestArgsSerializer : NSObject

///
/// Serializes `DBFILEREQUESTSUpdateFileRequestArgs` instances.
///
/// @param instance An instance of the `DBFILEREQUESTSUpdateFileRequestArgs` API
/// object.
///
/// @return A json-compatible dictionary representation of the
/// `DBFILEREQUESTSUpdateFileRequestArgs` API object.
///
+ (nullable NSDictionary<NSString *, id> *)serialize:(DBFILEREQUESTSUpdateFileRequestArgs *)instance;

///
/// Deserializes `DBFILEREQUESTSUpdateFileRequestArgs` instances.
///
/// @param dict A json-compatible dictionary representation of the
/// `DBFILEREQUESTSUpdateFileRequestArgs` API object.
///
/// @return An instantiation of the `DBFILEREQUESTSUpdateFileRequestArgs`
/// object.
///
+ (DBFILEREQUESTSUpdateFileRequestArgs *)deserialize:(NSDictionary<NSString *, id> *)dict;

@end

NS_ASSUME_NONNULL_END
