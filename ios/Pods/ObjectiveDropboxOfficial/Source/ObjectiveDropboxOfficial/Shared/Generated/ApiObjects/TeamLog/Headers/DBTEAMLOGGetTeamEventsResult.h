///
/// Copyright (c) 2016 Dropbox, Inc. All rights reserved.
///
/// Auto-generated by Stone, do not modify.
///

#import <Foundation/Foundation.h>

#import "DBSerializableProtocol.h"

@class DBTEAMLOGGetTeamEventsResult;
@class DBTEAMLOGTeamEvent;

NS_ASSUME_NONNULL_BEGIN

#pragma mark - API Object

///
/// The `GetTeamEventsResult` struct.
///
/// This class implements the `DBSerializable` protocol (serialize and
/// deserialize instance methods), which is required for all Obj-C SDK API route
/// objects.
///
@interface DBTEAMLOGGetTeamEventsResult : NSObject <DBSerializable, NSCopying>

#pragma mark - Instance fields

/// List of events. Note that events are not guaranteed to be sorted by their
/// timestamp value.
@property (nonatomic, readonly) NSArray<DBTEAMLOGTeamEvent *> *events;

/// Pass the cursor into `getEventsContinue` to obtain additional events. The
/// value of cursor may change for each response from `getEventsContinue`,
/// regardless of the value of hasMore; older cursor strings may expire. Thus,
/// callers should ensure that they update their cursor based on the latest
/// value of cursor after each call, and poll regularly if they wish to poll for
/// new events. Callers should handle reset exceptions for expired cursors.
@property (nonatomic, readonly, copy) NSString *cursor;

/// Is true if there may be additional events that have not been returned yet.
/// An additional call to `getEventsContinue` can retrieve them. Note that
/// hasMore may be true, even if events is empty.
@property (nonatomic, readonly) NSNumber *hasMore;

#pragma mark - Constructors

///
/// Full constructor for the struct (exposes all instance variables).
///
/// @param events List of events. Note that events are not guaranteed to be
/// sorted by their timestamp value.
/// @param cursor Pass the cursor into `getEventsContinue` to obtain additional
/// events. The value of cursor may change for each response from
/// `getEventsContinue`, regardless of the value of hasMore; older cursor
/// strings may expire. Thus, callers should ensure that they update their
/// cursor based on the latest value of cursor after each call, and poll
/// regularly if they wish to poll for new events. Callers should handle reset
/// exceptions for expired cursors.
/// @param hasMore Is true if there may be additional events that have not been
/// returned yet. An additional call to `getEventsContinue` can retrieve them.
/// Note that hasMore may be true, even if events is empty.
///
/// @return An initialized instance.
///
- (instancetype)initWithEvents:(NSArray<DBTEAMLOGTeamEvent *> *)events
                        cursor:(NSString *)cursor
                       hasMore:(NSNumber *)hasMore;

- (instancetype)init NS_UNAVAILABLE;

@end

#pragma mark - Serializer Object

///
/// The serialization class for the `GetTeamEventsResult` struct.
///
@interface DBTEAMLOGGetTeamEventsResultSerializer : NSObject

///
/// Serializes `DBTEAMLOGGetTeamEventsResult` instances.
///
/// @param instance An instance of the `DBTEAMLOGGetTeamEventsResult` API
/// object.
///
/// @return A json-compatible dictionary representation of the
/// `DBTEAMLOGGetTeamEventsResult` API object.
///
+ (nullable NSDictionary<NSString *, id> *)serialize:(DBTEAMLOGGetTeamEventsResult *)instance;

///
/// Deserializes `DBTEAMLOGGetTeamEventsResult` instances.
///
/// @param dict A json-compatible dictionary representation of the
/// `DBTEAMLOGGetTeamEventsResult` API object.
///
/// @return An instantiation of the `DBTEAMLOGGetTeamEventsResult` object.
///
+ (DBTEAMLOGGetTeamEventsResult *)deserialize:(NSDictionary<NSString *, id> *)dict;

@end

NS_ASSUME_NONNULL_END
