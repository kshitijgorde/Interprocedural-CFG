// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets;

import java.util.Date;
import java.io.Serializable;

public class BNLogRecord implements Serializable
{
    static final long serialVersionUID = 3163582964010453065L;
    private double time;
    private double value;
    private int pointType;
    private int flags;
    private long dbSequenceNumber;
    private int timeZoneOffset;
    public static final int TYPE_LOG_STATUS = 0;
    public static final int TYPE_BOOLEAN = 1;
    public static final int TYPE_REAL = 2;
    public static final int TYPE_TIME_CHANGE = 9;
    public static final int TYPE_ENUM = 3;
    public static final int TYPE_UNS = 4;
    public static final int TYPE_INT = 5;
    public static final int TYPE_BITSTR = 6;
    public static final int TYPE_NULL = 7;
    public static final int TYPE_FAILURE = 8;
    public static final int TYPE_ANY = 10;
    public static final int TYPE_ALC_STATUS = -1;
    public static final int ALC_STATUS_TREND_SOURCE_CHANGED = 2;
    public static final int ALC_STATUS_HISTORIAN_ENABLED = 1;
    public static final int ALC_STATUS_HISTORIAN_DISABLED = 0;
    public static final int ALC_STATUS_MANUALSAVE_START = 3;
    public static final int ALC_STATUS_MANUALSAVE_END = 4;
    
    public BNLogRecord() {
    }
    
    public BNLogRecord(final long dateTime, final double value, final int flags, final int valueType, final long seqNumber, final int timeOffset) {
        this.time = dateTime + timeOffset;
        this.value = value;
        this.pointType = valueType;
        this.flags = flags;
        this.dbSequenceNumber = seqNumber;
        this.timeZoneOffset = timeOffset;
    }
    
    public double getXValue() {
        return this.time;
    }
    
    public double getYValue() {
        return this.value;
    }
    
    public void setXValue(final double time) {
        this.time = time;
    }
    
    public void setYValue(final double value) {
        this.value = value;
    }
    
    public long getSequenceNumber() {
        return this.dbSequenceNumber;
    }
    
    public boolean isHistorical() {
        return this.dbSequenceNumber > 0L;
    }
    
    public int getPointType() {
        return this.pointType;
    }
    
    public int getTimeZoneOffset() {
        return this.timeZoneOffset;
    }
    
    public long getBacnetDateTimeInMillisec() {
        return (long)this.getXValue();
    }
    
    public Date getBacnetDateTimeAsDate() {
        return new Date((long)this.getXValue());
    }
    
    public long getBacnetDateTimeInGMT() {
        return (long)(this.getXValue() - this.timeZoneOffset);
    }
    
    public boolean hasAnyFlag() {
        return this.flags != 0;
    }
    
    public boolean hasAlarm() {
        return (this.flags & 0x1) != 0x0;
    }
    
    public boolean hasFault() {
        return (this.flags & 0x2) != 0x0;
    }
    
    public boolean hasOverridden() {
        return (this.flags & 0x4) != 0x0;
    }
    
    public boolean hasOutOfService() {
        return (this.flags & 0x8) != 0x0;
    }
    
    public int getLogStatusFlags() throws NotALogStatusException {
        if (!this.isLogStatus()) {
            throw new NotALogStatusException("This BNLogRecord is not a LogStatus record");
        }
        return (int)this.getYValue();
    }
    
    public boolean isLogStatus_LogDisabled() throws NotALogStatusException {
        if (!this.isLogStatus()) {
            throw new NotALogStatusException("This BNLogRecord is not a LogStatus record");
        }
        return (this.getLogStatusFlags() & 0x1) != 0x0;
    }
    
    public boolean isLogStatus_BufferPurged() throws NotALogStatusException {
        if (!this.isLogStatus()) {
            throw new NotALogStatusException("This BNLogRecord is not a LogStatus record");
        }
        return (this.getLogStatusFlags() & 0x2) != 0x0;
    }
    
    public int getALCStatusType() throws NotAnALCStatusException {
        if (!this.isALCStatus()) {
            throw new NotAnALCStatusException("This BNLogRecord is not an ALCStatus record");
        }
        return (int)this.value;
    }
    
    public boolean isFailureCcn() {
        return this.getFailureClass() == 64;
    }
    
    public int getFailureClass() {
        return (int)((long)this.value & 0xFFFFFFFFFFFF0000L) >> 16;
    }
    
    public int getFailureCode() {
        return (int)((long)this.value & 0xFFFFL);
    }
    
    public boolean isLogStatus() {
        return this.pointType == 0;
    }
    
    public boolean isALCStatus() {
        return this.pointType == -1;
    }
    
    public boolean isBoolean() {
        return this.pointType == 1;
    }
    
    public boolean isReal() {
        return this.pointType == 2;
    }
    
    public boolean isTimeChange() {
        return this.pointType == 9;
    }
    
    public boolean isEnumeration() {
        return this.pointType == 3;
    }
    
    public boolean isUnsigned() {
        return this.pointType == 4;
    }
    
    public boolean isInteger() {
        return this.pointType == 5;
    }
    
    public boolean isBitString() {
        return this.pointType == 6;
    }
    
    public boolean isNullType() {
        return this.pointType == 7;
    }
    
    public boolean isFailure() {
        return this.pointType == 8;
    }
    
    public boolean isAnyType() {
        return this.pointType == 10;
    }
    
    public boolean isSpecial() {
        return 0 == this.pointType || 9 == this.pointType || 7 == this.pointType || 8 == this.pointType || -1 == this.pointType;
    }
    
    public double getDaysSince1900() {
        double result = this.getBacnetDateTimeInMillisec() / 8.64E7;
        result += 25569.0;
        return result;
    }
    
    public String toDebugString() {
        final StringBuffer result = new StringBuffer();
        result.append(this.getBacnetDateTimeInMillisec());
        result.append("\t");
        result.append(this.value);
        result.append("\t");
        result.append(this.pointType);
        result.append("\t");
        result.append(this.flags);
        result.append("\t");
        result.append(this.dbSequenceNumber);
        result.append("\t");
        result.append(this.timeZoneOffset);
        result.append("\n");
        return result.toString();
    }
    
    public String toString() {
        final StringBuffer result = new StringBuffer();
        result.append(this.getDaysSince1900());
        result.append("\t");
        result.append(this.value);
        result.append("\t");
        result.append(this.pointType);
        result.append("\t");
        result.append(this.flags);
        result.append("\n");
        return result.toString();
    }
    
    public int compareTo(final Object obj) {
        return this.compareTo((BNLogRecord)obj);
    }
    
    public int compareTo(final BNLogRecord r) {
        if (this.getXValue() < r.getXValue()) {
            return -1;
        }
        if (this.getXValue() > r.getXValue()) {
            return 1;
        }
        if (this.getSequenceNumber() < r.getSequenceNumber()) {
            return -1;
        }
        if (this.getSequenceNumber() > r.getSequenceNumber()) {
            return 1;
        }
        return 0;
    }
    
    public static class NotALogStatusException extends Exception
    {
        public NotALogStatusException(final String msg) {
            super(msg);
        }
    }
    
    public static class NotAnALCStatusException extends Exception
    {
        public NotAnALCStatusException(final String msg) {
            super(msg);
        }
    }
}
