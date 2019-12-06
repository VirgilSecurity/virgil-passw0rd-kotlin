// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: purekitV3_client.proto

package com.virgilsecurity.purekit.protobuf.build;

public final class PurekitProtosV3Client {
  private PurekitProtosV3Client() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface GetUserRecordsOrBuilder extends
      // @@protoc_insertion_point(interface_extends:build.GetUserRecords)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>repeated string user_ids = 1;</code>
     */
    java.util.List<java.lang.String>
        getUserIdsList();
    /**
     * <code>repeated string user_ids = 1;</code>
     */
    int getUserIdsCount();
    /**
     * <code>repeated string user_ids = 1;</code>
     */
    java.lang.String getUserIds(int index);
    /**
     * <code>repeated string user_ids = 1;</code>
     */
    com.google.protobuf.ByteString
        getUserIdsBytes(int index);
  }
  /**
   * Protobuf type {@code build.GetUserRecords}
   */
  public  static final class GetUserRecords extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:build.GetUserRecords)
      GetUserRecordsOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use GetUserRecords.newBuilder() to construct.
    private GetUserRecords(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private GetUserRecords() {
      userIds_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new GetUserRecords();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private GetUserRecords(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();
              if (!((mutable_bitField0_ & 0x00000001) != 0)) {
                userIds_ = new com.google.protobuf.LazyStringArrayList();
                mutable_bitField0_ |= 0x00000001;
              }
              userIds_.add(s);
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000001) != 0)) {
          userIds_ = userIds_.getUnmodifiableView();
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.internal_static_build_GetUserRecords_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.internal_static_build_GetUserRecords_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords.class, com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords.Builder.class);
    }

    public static final int USER_IDS_FIELD_NUMBER = 1;
    private com.google.protobuf.LazyStringList userIds_;
    /**
     * <code>repeated string user_ids = 1;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getUserIdsList() {
      return userIds_;
    }
    /**
     * <code>repeated string user_ids = 1;</code>
     */
    public int getUserIdsCount() {
      return userIds_.size();
    }
    /**
     * <code>repeated string user_ids = 1;</code>
     */
    public java.lang.String getUserIds(int index) {
      return userIds_.get(index);
    }
    /**
     * <code>repeated string user_ids = 1;</code>
     */
    public com.google.protobuf.ByteString
        getUserIdsBytes(int index) {
      return userIds_.getByteString(index);
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      for (int i = 0; i < userIds_.size(); i++) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, userIds_.getRaw(i));
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      {
        int dataSize = 0;
        for (int i = 0; i < userIds_.size(); i++) {
          dataSize += computeStringSizeNoTag(userIds_.getRaw(i));
        }
        size += dataSize;
        size += 1 * getUserIdsList().size();
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords)) {
        return super.equals(obj);
      }
      com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords other = (com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords) obj;

      if (!getUserIdsList()
          .equals(other.getUserIdsList())) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (getUserIdsCount() > 0) {
        hash = (37 * hash) + USER_IDS_FIELD_NUMBER;
        hash = (53 * hash) + getUserIdsList().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code build.GetUserRecords}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:build.GetUserRecords)
        com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecordsOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.internal_static_build_GetUserRecords_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.internal_static_build_GetUserRecords_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords.class, com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords.Builder.class);
      }

      // Construct using com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        userIds_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.internal_static_build_GetUserRecords_descriptor;
      }

      @java.lang.Override
      public com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords getDefaultInstanceForType() {
        return com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords.getDefaultInstance();
      }

      @java.lang.Override
      public com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords build() {
        com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords buildPartial() {
        com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords result = new com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords(this);
        int from_bitField0_ = bitField0_;
        if (((bitField0_ & 0x00000001) != 0)) {
          userIds_ = userIds_.getUnmodifiableView();
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.userIds_ = userIds_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords) {
          return mergeFrom((com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords other) {
        if (other == com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords.getDefaultInstance()) return this;
        if (!other.userIds_.isEmpty()) {
          if (userIds_.isEmpty()) {
            userIds_ = other.userIds_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureUserIdsIsMutable();
            userIds_.addAll(other.userIds_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private com.google.protobuf.LazyStringList userIds_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      private void ensureUserIdsIsMutable() {
        if (!((bitField0_ & 0x00000001) != 0)) {
          userIds_ = new com.google.protobuf.LazyStringArrayList(userIds_);
          bitField0_ |= 0x00000001;
         }
      }
      /**
       * <code>repeated string user_ids = 1;</code>
       */
      public com.google.protobuf.ProtocolStringList
          getUserIdsList() {
        return userIds_.getUnmodifiableView();
      }
      /**
       * <code>repeated string user_ids = 1;</code>
       */
      public int getUserIdsCount() {
        return userIds_.size();
      }
      /**
       * <code>repeated string user_ids = 1;</code>
       */
      public java.lang.String getUserIds(int index) {
        return userIds_.get(index);
      }
      /**
       * <code>repeated string user_ids = 1;</code>
       */
      public com.google.protobuf.ByteString
          getUserIdsBytes(int index) {
        return userIds_.getByteString(index);
      }
      /**
       * <code>repeated string user_ids = 1;</code>
       */
      public Builder setUserIds(
          int index, java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureUserIdsIsMutable();
        userIds_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string user_ids = 1;</code>
       */
      public Builder addUserIds(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureUserIdsIsMutable();
        userIds_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string user_ids = 1;</code>
       */
      public Builder addAllUserIds(
          java.lang.Iterable<java.lang.String> values) {
        ensureUserIdsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, userIds_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string user_ids = 1;</code>
       */
      public Builder clearUserIds() {
        userIds_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string user_ids = 1;</code>
       */
      public Builder addUserIdsBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        ensureUserIdsIsMutable();
        userIds_.add(value);
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:build.GetUserRecords)
    }

    // @@protoc_insertion_point(class_scope:build.GetUserRecords)
    private static final com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords();
    }

    public static com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<GetUserRecords>
        PARSER = new com.google.protobuf.AbstractParser<GetUserRecords>() {
      @java.lang.Override
      public GetUserRecords parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new GetUserRecords(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<GetUserRecords> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<GetUserRecords> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client.GetUserRecords getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_build_GetUserRecords_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_build_GetUserRecords_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026purekitV3_client.proto\022\005build\"\"\n\016GetUs" +
      "erRecords\022\020\n\010user_ids\030\001 \003(\tBB\n)com.virgi" +
      "lsecurity.purekit.protobuf.buildB\025Pureki" +
      "tProtosV3Clientb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_build_GetUserRecords_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_build_GetUserRecords_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_build_GetUserRecords_descriptor,
        new java.lang.String[] { "UserIds", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
