// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

public class Message
{
    public static final int EOS = 1;
    public static final int ERROR = 2;
    public static final int WARNING = 4;
    public static final int INFO = 8;
    public static final int TAG = 16;
    public static final int BUFFERING = 32;
    public static final int STATE_CHANGED = 64;
    public static final int STATE_DIRTY = 128;
    public static final int STEP_DONE = 256;
    public static final int CLOCK_PROVIDE = 512;
    public static final int CLOCK_LOST = 1024;
    public static final int NEW_CLOCK = 2048;
    public static final int STRUCTURE_CHANGE = 4096;
    public static final int STREAM_STATUS = 8192;
    public static final int APPLICATION = 16384;
    public static final int ELEMENT = 32768;
    public static final int SEGMENT_START = 65536;
    public static final int SEGMENT_DONE = 131072;
    public static final int DURATION = 262144;
    public static final int RESOURCE = 524288;
    public static final int BYTEPOSITION = 1048576;
    private com.fluendo.jst.Object src;
    private int type;
    private boolean boolVal;
    private int intVal;
    private long longVal;
    private String stringVal;
    private int old;
    private int next;
    private int pending;
    
    private Message(final com.fluendo.jst.Object src, final int type) {
        this.src = src;
        this.type = type;
    }
    
    public com.fluendo.jst.Object getSrc() {
        return this.src;
    }
    
    public int getType() {
        return this.type;
    }
    
    public String toString() {
        switch (this.type) {
            case 1: {
                return "[Message]: " + this.src + " type: EOS";
            }
            case 32: {
                return "[Message]: " + this.src + " type: BUFFERING, busy:" + this.boolVal + ", percent:" + this.intVal;
            }
            case 64: {
                return "[Message]: " + this.src + " type: STATE_CHANGED, old: " + Element.getStateName(this.old) + ", next: " + Element.getStateName(this.next) + ", pending: " + Element.getStateName(this.pending);
            }
            case 128: {
                return "[Message]: " + this.src + " type: STATE_DIRTY";
            }
            case 8192: {
                return "[Message]: " + this.src + " type: STREAM_STATUS, " + (this.boolVal ? "start" : "stop") + ", reason: " + Pad.getFlowName(this.intVal) + ", " + this.stringVal;
            }
            case 2: {
                return "[Message]: " + this.src + " type: ERROR, " + this.stringVal;
            }
            default: {
                return "[Message]: " + this.src + " type: " + this.type;
            }
        }
    }
    
    public static Message newEOS(final com.fluendo.jst.Object object) {
        return new Message(object, 1);
    }
    
    public static Message newError(final com.fluendo.jst.Object object, final String stringVal) {
        final Message message = new Message(object, 2);
        message.stringVal = stringVal;
        return message;
    }
    
    public static Message newWarning(final com.fluendo.jst.Object object, final String stringVal) {
        final Message message = new Message(object, 4);
        message.stringVal = stringVal;
        return message;
    }
    
    public String parseErrorString() {
        return this.stringVal;
    }
    
    public static Message newBuffering(final com.fluendo.jst.Object object, final boolean boolVal, final int intVal) {
        final Message message = new Message(object, 32);
        message.boolVal = boolVal;
        message.intVal = intVal;
        return message;
    }
    
    public boolean parseBufferingBusy() {
        return this.boolVal;
    }
    
    public int parseBufferingPercent() {
        return this.intVal;
    }
    
    public static Message newStateChanged(final com.fluendo.jst.Object object, final int old, final int next, final int pending) {
        final Message message = new Message(object, 64);
        message.old = old;
        message.next = next;
        message.pending = pending;
        return message;
    }
    
    public int parseStateChangedOld() {
        return this.old;
    }
    
    public int parseStateChangedNext() {
        return this.next;
    }
    
    public int parseStateChangedPending() {
        return this.pending;
    }
    
    public static Message newStateDirty(final com.fluendo.jst.Object object) {
        return new Message(object, 128);
    }
    
    public static Message newStreamStatus(final com.fluendo.jst.Object object, final boolean boolVal, final int intVal, final String stringVal) {
        final Message message = new Message(object, 8192);
        message.stringVal = stringVal;
        message.boolVal = boolVal;
        message.intVal = intVal;
        return message;
    }
    
    public String parseStreamStatusString() {
        return this.stringVal;
    }
    
    public boolean parseStreamStatusStart() {
        return this.boolVal;
    }
    
    public int parseStreamStatusReason() {
        return this.intVal;
    }
    
    public static Message newResource(final com.fluendo.jst.Object object, final String stringVal) {
        final Message message = new Message(object, 524288);
        message.stringVal = stringVal;
        return message;
    }
    
    public String parseResourceString() {
        return this.stringVal;
    }
    
    public static Message newDuration(final com.fluendo.jst.Object object, final int intVal, final long longVal) {
        final Message message = new Message(object, 262144);
        message.intVal = intVal;
        message.longVal = longVal;
        return message;
    }
    
    public int parseDurationFormat() {
        return this.intVal;
    }
    
    public long parseDurationValue() {
        return this.longVal;
    }
    
    public static Message newBytePosition(final com.fluendo.jst.Object object, final long longVal) {
        final Message message = new Message(object, 1048576);
        message.longVal = longVal;
        return message;
    }
    
    public long parseBytePosition() {
        return this.longVal;
    }
}
