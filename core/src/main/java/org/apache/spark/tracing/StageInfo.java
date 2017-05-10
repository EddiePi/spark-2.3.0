/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.spark.tracing;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2017-2-16")
public class StageInfo implements org.apache.thrift.TBase<StageInfo, StageInfo._Fields>, java.io.Serializable, Cloneable, Comparable<StageInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("StageInfo");

  private static final org.apache.thrift.protocol.TField STAGE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("stageId", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField JOB_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("jobId", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField APP_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("appId", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField STATUS_FIELD_DESC = new org.apache.thrift.protocol.TField("status", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField TASK_NUM_FIELD_DESC = new org.apache.thrift.protocol.TField("taskNum", org.apache.thrift.protocol.TType.I32, (short)6);
  private static final org.apache.thrift.protocol.TField START_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("startTime", org.apache.thrift.protocol.TType.I64, (short)7);
  private static final org.apache.thrift.protocol.TField FINISH_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("finishTime", org.apache.thrift.protocol.TType.I64, (short)8);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new StageInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new StageInfoTupleSchemeFactory());
  }

  public int stageId; // required
  public String type; // required
  public int jobId; // required
  public String appId; // required
  public String status; // required
  public int taskNum; // required
  public long startTime; // required
  public long finishTime; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    STAGE_ID((short)1, "stageId"),
    TYPE((short)2, "type"),
    JOB_ID((short)3, "jobId"),
    APP_ID((short)4, "appId"),
    STATUS((short)5, "status"),
    TASK_NUM((short)6, "taskNum"),
    START_TIME((short)7, "startTime"),
    FINISH_TIME((short)8, "finishTime");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // STAGE_ID
          return STAGE_ID;
        case 2: // TYPE
          return TYPE;
        case 3: // JOB_ID
          return JOB_ID;
        case 4: // APP_ID
          return APP_ID;
        case 5: // STATUS
          return STATUS;
        case 6: // TASK_NUM
          return TASK_NUM;
        case 7: // START_TIME
          return START_TIME;
        case 8: // FINISH_TIME
          return FINISH_TIME;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __STAGEID_ISSET_ID = 0;
  private static final int __JOBID_ISSET_ID = 1;
  private static final int __TASKNUM_ISSET_ID = 2;
  private static final int __STARTTIME_ISSET_ID = 3;
  private static final int __FINISHTIME_ISSET_ID = 4;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.STAGE_ID, new org.apache.thrift.meta_data.FieldMetaData("stageId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.JOB_ID, new org.apache.thrift.meta_data.FieldMetaData("jobId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.APP_ID, new org.apache.thrift.meta_data.FieldMetaData("appId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.STATUS, new org.apache.thrift.meta_data.FieldMetaData("status", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TASK_NUM, new org.apache.thrift.meta_data.FieldMetaData("taskNum", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.START_TIME, new org.apache.thrift.meta_data.FieldMetaData("startTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.FINISH_TIME, new org.apache.thrift.meta_data.FieldMetaData("finishTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(StageInfo.class, metaDataMap);
  }

  public StageInfo() {
  }

  public StageInfo(
    int stageId,
    String type,
    int jobId,
    String appId,
    String status,
    int taskNum,
    long startTime,
    long finishTime)
  {
    this();
    this.stageId = stageId;
    setStageIdIsSet(true);
    this.type = type;
    this.jobId = jobId;
    setJobIdIsSet(true);
    this.appId = appId;
    this.status = status;
    this.taskNum = taskNum;
    setTaskNumIsSet(true);
    this.startTime = startTime;
    setStartTimeIsSet(true);
    this.finishTime = finishTime;
    setFinishTimeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public StageInfo(StageInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    this.stageId = other.stageId;
    if (other.isSetType()) {
      this.type = other.type;
    }
    this.jobId = other.jobId;
    if (other.isSetAppId()) {
      this.appId = other.appId;
    }
    if (other.isSetStatus()) {
      this.status = other.status;
    }
    this.taskNum = other.taskNum;
    this.startTime = other.startTime;
    this.finishTime = other.finishTime;
  }

  public StageInfo deepCopy() {
    return new StageInfo(this);
  }

  @Override
  public void clear() {
    setStageIdIsSet(false);
    this.stageId = 0;
    this.type = null;
    setJobIdIsSet(false);
    this.jobId = 0;
    this.appId = null;
    this.status = null;
    setTaskNumIsSet(false);
    this.taskNum = 0;
    setStartTimeIsSet(false);
    this.startTime = 0;
    setFinishTimeIsSet(false);
    this.finishTime = 0;
  }

  public int getStageId() {
    return this.stageId;
  }

  public StageInfo setStageId(int stageId) {
    this.stageId = stageId;
    setStageIdIsSet(true);
    return this;
  }

  public void unsetStageId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __STAGEID_ISSET_ID);
  }

  /** Returns true if field stageId is set (has been assigned a value) and false otherwise */
  public boolean isSetStageId() {
    return EncodingUtils.testBit(__isset_bitfield, __STAGEID_ISSET_ID);
  }

  public void setStageIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __STAGEID_ISSET_ID, value);
  }

  public String getType() {
    return this.type;
  }

  public StageInfo setType(String type) {
    this.type = type;
    return this;
  }

  public void unsetType() {
    this.type = null;
  }

  /** Returns true if field type is set (has been assigned a value) and false otherwise */
  public boolean isSetType() {
    return this.type != null;
  }

  public void setTypeIsSet(boolean value) {
    if (!value) {
      this.type = null;
    }
  }

  public int getJobId() {
    return this.jobId;
  }

  public StageInfo setJobId(int jobId) {
    this.jobId = jobId;
    setJobIdIsSet(true);
    return this;
  }

  public void unsetJobId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __JOBID_ISSET_ID);
  }

  /** Returns true if field jobId is set (has been assigned a value) and false otherwise */
  public boolean isSetJobId() {
    return EncodingUtils.testBit(__isset_bitfield, __JOBID_ISSET_ID);
  }

  public void setJobIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __JOBID_ISSET_ID, value);
  }

  public String getAppId() {
    return this.appId;
  }

  public StageInfo setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public void unsetAppId() {
    this.appId = null;
  }

  /** Returns true if field appId is set (has been assigned a value) and false otherwise */
  public boolean isSetAppId() {
    return this.appId != null;
  }

  public void setAppIdIsSet(boolean value) {
    if (!value) {
      this.appId = null;
    }
  }

  public String getStatus() {
    return this.status;
  }

  public StageInfo setStatus(String status) {
    this.status = status;
    return this;
  }

  public void unsetStatus() {
    this.status = null;
  }

  /** Returns true if field status is set (has been assigned a value) and false otherwise */
  public boolean isSetStatus() {
    return this.status != null;
  }

  public void setStatusIsSet(boolean value) {
    if (!value) {
      this.status = null;
    }
  }

  public int getTaskNum() {
    return this.taskNum;
  }

  public StageInfo setTaskNum(int taskNum) {
    this.taskNum = taskNum;
    setTaskNumIsSet(true);
    return this;
  }

  public void unsetTaskNum() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TASKNUM_ISSET_ID);
  }

  /** Returns true if field taskNum is set (has been assigned a value) and false otherwise */
  public boolean isSetTaskNum() {
    return EncodingUtils.testBit(__isset_bitfield, __TASKNUM_ISSET_ID);
  }

  public void setTaskNumIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TASKNUM_ISSET_ID, value);
  }

  public long getStartTime() {
    return this.startTime;
  }

  public StageInfo setStartTime(long startTime) {
    this.startTime = startTime;
    setStartTimeIsSet(true);
    return this;
  }

  public void unsetStartTime() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __STARTTIME_ISSET_ID);
  }

  /** Returns true if field startTime is set (has been assigned a value) and false otherwise */
  public boolean isSetStartTime() {
    return EncodingUtils.testBit(__isset_bitfield, __STARTTIME_ISSET_ID);
  }

  public void setStartTimeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __STARTTIME_ISSET_ID, value);
  }

  public long getFinishTime() {
    return this.finishTime;
  }

  public StageInfo setFinishTime(long finishTime) {
    this.finishTime = finishTime;
    setFinishTimeIsSet(true);
    return this;
  }

  public void unsetFinishTime() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __FINISHTIME_ISSET_ID);
  }

  /** Returns true if field finishTime is set (has been assigned a value) and false otherwise */
  public boolean isSetFinishTime() {
    return EncodingUtils.testBit(__isset_bitfield, __FINISHTIME_ISSET_ID);
  }

  public void setFinishTimeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __FINISHTIME_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case STAGE_ID:
      if (value == null) {
        unsetStageId();
      } else {
        setStageId((Integer)value);
      }
      break;

    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((String)value);
      }
      break;

    case JOB_ID:
      if (value == null) {
        unsetJobId();
      } else {
        setJobId((Integer)value);
      }
      break;

    case APP_ID:
      if (value == null) {
        unsetAppId();
      } else {
        setAppId((String)value);
      }
      break;

    case STATUS:
      if (value == null) {
        unsetStatus();
      } else {
        setStatus((String)value);
      }
      break;

    case TASK_NUM:
      if (value == null) {
        unsetTaskNum();
      } else {
        setTaskNum((Integer)value);
      }
      break;

    case START_TIME:
      if (value == null) {
        unsetStartTime();
      } else {
        setStartTime((Long)value);
      }
      break;

    case FINISH_TIME:
      if (value == null) {
        unsetFinishTime();
      } else {
        setFinishTime((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case STAGE_ID:
      return Integer.valueOf(getStageId());

    case TYPE:
      return getType();

    case JOB_ID:
      return Integer.valueOf(getJobId());

    case APP_ID:
      return getAppId();

    case STATUS:
      return getStatus();

    case TASK_NUM:
      return Integer.valueOf(getTaskNum());

    case START_TIME:
      return Long.valueOf(getStartTime());

    case FINISH_TIME:
      return Long.valueOf(getFinishTime());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case STAGE_ID:
      return isSetStageId();
    case TYPE:
      return isSetType();
    case JOB_ID:
      return isSetJobId();
    case APP_ID:
      return isSetAppId();
    case STATUS:
      return isSetStatus();
    case TASK_NUM:
      return isSetTaskNum();
    case START_TIME:
      return isSetStartTime();
    case FINISH_TIME:
      return isSetFinishTime();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof StageInfo)
      return this.equals((StageInfo)that);
    return false;
  }

  public boolean equals(StageInfo that) {
    if (that == null)
      return false;

    boolean this_present_stageId = true;
    boolean that_present_stageId = true;
    if (this_present_stageId || that_present_stageId) {
      if (!(this_present_stageId && that_present_stageId))
        return false;
      if (this.stageId != that.stageId)
        return false;
    }

    boolean this_present_type = true && this.isSetType();
    boolean that_present_type = true && that.isSetType();
    if (this_present_type || that_present_type) {
      if (!(this_present_type && that_present_type))
        return false;
      if (!this.type.equals(that.type))
        return false;
    }

    boolean this_present_jobId = true;
    boolean that_present_jobId = true;
    if (this_present_jobId || that_present_jobId) {
      if (!(this_present_jobId && that_present_jobId))
        return false;
      if (this.jobId != that.jobId)
        return false;
    }

    boolean this_present_appId = true && this.isSetAppId();
    boolean that_present_appId = true && that.isSetAppId();
    if (this_present_appId || that_present_appId) {
      if (!(this_present_appId && that_present_appId))
        return false;
      if (!this.appId.equals(that.appId))
        return false;
    }

    boolean this_present_status = true && this.isSetStatus();
    boolean that_present_status = true && that.isSetStatus();
    if (this_present_status || that_present_status) {
      if (!(this_present_status && that_present_status))
        return false;
      if (!this.status.equals(that.status))
        return false;
    }

    boolean this_present_taskNum = true;
    boolean that_present_taskNum = true;
    if (this_present_taskNum || that_present_taskNum) {
      if (!(this_present_taskNum && that_present_taskNum))
        return false;
      if (this.taskNum != that.taskNum)
        return false;
    }

    boolean this_present_startTime = true;
    boolean that_present_startTime = true;
    if (this_present_startTime || that_present_startTime) {
      if (!(this_present_startTime && that_present_startTime))
        return false;
      if (this.startTime != that.startTime)
        return false;
    }

    boolean this_present_finishTime = true;
    boolean that_present_finishTime = true;
    if (this_present_finishTime || that_present_finishTime) {
      if (!(this_present_finishTime && that_present_finishTime))
        return false;
      if (this.finishTime != that.finishTime)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_stageId = true;
    list.add(present_stageId);
    if (present_stageId)
      list.add(stageId);

    boolean present_type = true && (isSetType());
    list.add(present_type);
    if (present_type)
      list.add(type);

    boolean present_jobId = true;
    list.add(present_jobId);
    if (present_jobId)
      list.add(jobId);

    boolean present_appId = true && (isSetAppId());
    list.add(present_appId);
    if (present_appId)
      list.add(appId);

    boolean present_status = true && (isSetStatus());
    list.add(present_status);
    if (present_status)
      list.add(status);

    boolean present_taskNum = true;
    list.add(present_taskNum);
    if (present_taskNum)
      list.add(taskNum);

    boolean present_startTime = true;
    list.add(present_startTime);
    if (present_startTime)
      list.add(startTime);

    boolean present_finishTime = true;
    list.add(present_finishTime);
    if (present_finishTime)
      list.add(finishTime);

    return list.hashCode();
  }

  @Override
  public int compareTo(StageInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetStageId()).compareTo(other.isSetStageId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStageId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.stageId, other.stageId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetType()).compareTo(other.isSetType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.type, other.type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetJobId()).compareTo(other.isSetJobId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetJobId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.jobId, other.jobId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAppId()).compareTo(other.isSetAppId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAppId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.appId, other.appId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStatus()).compareTo(other.isSetStatus());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStatus()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.status, other.status);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTaskNum()).compareTo(other.isSetTaskNum());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTaskNum()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.taskNum, other.taskNum);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStartTime()).compareTo(other.isSetStartTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStartTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.startTime, other.startTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFinishTime()).compareTo(other.isSetFinishTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFinishTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.finishTime, other.finishTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("StageInfo(");
    boolean first = true;

    sb.append("stageId:");
    sb.append(this.stageId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("type:");
    if (this.type == null) {
      sb.append("null");
    } else {
      sb.append(this.type);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("jobId:");
    sb.append(this.jobId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("appId:");
    if (this.appId == null) {
      sb.append("null");
    } else {
      sb.append(this.appId);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("status:");
    if (this.status == null) {
      sb.append("null");
    } else {
      sb.append(this.status);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("taskNum:");
    sb.append(this.taskNum);
    first = false;
    if (!first) sb.append(", ");
    sb.append("startTime:");
    sb.append(this.startTime);
    first = false;
    if (!first) sb.append(", ");
    sb.append("finishTime:");
    sb.append(this.finishTime);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class StageInfoStandardSchemeFactory implements SchemeFactory {
    public StageInfoStandardScheme getScheme() {
      return new StageInfoStandardScheme();
    }
  }

  private static class StageInfoStandardScheme extends StandardScheme<StageInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, StageInfo struct) throws TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // STAGE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.stageId = iprot.readI32();
              struct.setStageIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.type = iprot.readString();
              struct.setTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // JOB_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.jobId = iprot.readI32();
              struct.setJobIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // APP_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.appId = iprot.readString();
              struct.setAppIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // STATUS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.status = iprot.readString();
              struct.setStatusIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // TASK_NUM
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.taskNum = iprot.readI32();
              struct.setTaskNumIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // START_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.startTime = iprot.readI64();
              struct.setStartTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // FINISH_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.finishTime = iprot.readI64();
              struct.setFinishTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, StageInfo struct) throws TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(STAGE_ID_FIELD_DESC);
      oprot.writeI32(struct.stageId);
      oprot.writeFieldEnd();
      if (struct.type != null) {
        oprot.writeFieldBegin(TYPE_FIELD_DESC);
        oprot.writeString(struct.type);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(JOB_ID_FIELD_DESC);
      oprot.writeI32(struct.jobId);
      oprot.writeFieldEnd();
      if (struct.appId != null) {
        oprot.writeFieldBegin(APP_ID_FIELD_DESC);
        oprot.writeString(struct.appId);
        oprot.writeFieldEnd();
      }
      if (struct.status != null) {
        oprot.writeFieldBegin(STATUS_FIELD_DESC);
        oprot.writeString(struct.status);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TASK_NUM_FIELD_DESC);
      oprot.writeI32(struct.taskNum);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(START_TIME_FIELD_DESC);
      oprot.writeI64(struct.startTime);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(FINISH_TIME_FIELD_DESC);
      oprot.writeI64(struct.finishTime);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class StageInfoTupleSchemeFactory implements SchemeFactory {
    public StageInfoTupleScheme getScheme() {
      return new StageInfoTupleScheme();
    }
  }

  private static class StageInfoTupleScheme extends TupleScheme<StageInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, StageInfo struct) throws TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetStageId()) {
        optionals.set(0);
      }
      if (struct.isSetType()) {
        optionals.set(1);
      }
      if (struct.isSetJobId()) {
        optionals.set(2);
      }
      if (struct.isSetAppId()) {
        optionals.set(3);
      }
      if (struct.isSetStatus()) {
        optionals.set(4);
      }
      if (struct.isSetTaskNum()) {
        optionals.set(5);
      }
      if (struct.isSetStartTime()) {
        optionals.set(6);
      }
      if (struct.isSetFinishTime()) {
        optionals.set(7);
      }
      oprot.writeBitSet(optionals, 8);
      if (struct.isSetStageId()) {
        oprot.writeI32(struct.stageId);
      }
      if (struct.isSetType()) {
        oprot.writeString(struct.type);
      }
      if (struct.isSetJobId()) {
        oprot.writeI32(struct.jobId);
      }
      if (struct.isSetAppId()) {
        oprot.writeString(struct.appId);
      }
      if (struct.isSetStatus()) {
        oprot.writeString(struct.status);
      }
      if (struct.isSetTaskNum()) {
        oprot.writeI32(struct.taskNum);
      }
      if (struct.isSetStartTime()) {
        oprot.writeI64(struct.startTime);
      }
      if (struct.isSetFinishTime()) {
        oprot.writeI64(struct.finishTime);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, StageInfo struct) throws TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(8);
      if (incoming.get(0)) {
        struct.stageId = iprot.readI32();
        struct.setStageIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.type = iprot.readString();
        struct.setTypeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.jobId = iprot.readI32();
        struct.setJobIdIsSet(true);
      }
      if (incoming.get(3)) {
        struct.appId = iprot.readString();
        struct.setAppIdIsSet(true);
      }
      if (incoming.get(4)) {
        struct.status = iprot.readString();
        struct.setStatusIsSet(true);
      }
      if (incoming.get(5)) {
        struct.taskNum = iprot.readI32();
        struct.setTaskNumIsSet(true);
      }
      if (incoming.get(6)) {
        struct.startTime = iprot.readI64();
        struct.setStartTimeIsSet(true);
      }
      if (incoming.get(7)) {
        struct.finishTime = iprot.readI64();
        struct.setFinishTimeIsSet(true);
      }
    }
  }

}

