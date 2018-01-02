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
    private com.fluendo.jst.Object src;
    private int type;
    private boolean bool;
    private int value;
    private String string;
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
                return "[Message]: " + this.src + " type: BUFFERING, busy:" + this.bool + ", percent:" + this.value;
            }
            case 64: {
                return "[Message]: " + this.src + " type: STATE_CHANGED, old:" + this.old + ", next:" + this.next + ", pending:" + this.pending;
            }
            case 128: {
                return "[Message]: " + this.src + " type: STATE_DIRTY";
            }
            case 8192: {
                return "[Message]: " + this.src + " type: STREAM_STATUS, " + (this.bool ? "start" : "stop") + ", reason: " + Pad.getFlowName(this.value) + ", " + this.string;
            }
            case 2: {
                return "[Message]: " + this.src + " type: ERROR, " + this.string;
            }
            default: {
                return "[Message]: " + this.src + " type: " + this.type;
            }
        }
    }
    
    public static Message newEOS(final com.fluendo.jst.Object src) {
        return new Message(src, 1);
    }
    
    public static Message newError(final com.fluendo.jst.Object src, final String str) {
        final Message msg = new Message(src, 2);
        msg.string = str;
        return msg;
    }
    
    public static Message newWarning(final com.fluendo.jst.Object src, final String str) {
        final Message msg = new Message(src, 4);
        msg.string = str;
        return msg;
    }
    
    public String parseErrorString() {
        return this.string;
    }
    
    public static Message newBuffering(final com.fluendo.jst.Object src, final boolean busy, final int percent) {
        final Message msg = new Message(src, 32);
        msg.bool = busy;
        msg.value = percent;
        return msg;
    }
    
    public boolean parseBufferingBusy() {
        return this.bool;
    }
    
    public int parseBufferingPercent() {
        return this.value;
    }
    
    public static Message newStateChanged(final com.fluendo.jst.Object src, final int old, final int next, final int pending) {
        final Message msg = new Message(src, 64);
        msg.old = old;
        msg.next = next;
        msg.pending = pending;
        return msg;
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
    
    public static Message newStateDirty(final com.fluendo.jst.Object src) {
        return new Message(src, 128);
    }
    
    public static Message newStreamStatus(final com.fluendo.jst.Object src, final boolean start, final int reason, final String aString) {
        final Message msg = new Message(src, 8192);
        msg.string = aString;
        msg.bool = start;
        msg.value = reason;
        return msg;
    }
    
    public String parseStreamStatusString() {
        return this.string;
    }
    
    public boolean parseStreamStatusStart() {
        return this.bool;
    }
    
    public int parseStreamStatusReason() {
        return this.value;
    }
    
    public static Message newResource(final com.fluendo.jst.Object src, final String aString) {
        final Message msg = new Message(src, 524288);
        msg.string = aString;
        return msg;
    }
    
    public String parseResourceString() {
        return this.string;
    }
}
