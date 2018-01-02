// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

import java.util.EmptyStackException;
import java.util.Stack;

public class Buffer
{
    private static Stack pool;
    private static int live;
    public static final int FLAG_DISCONT = 1;
    public static final int FLAG_DELTA_UNIT = 2;
    public int flags;
    public Object object;
    public byte[] data;
    public int offset;
    public int length;
    public Caps caps;
    public long time_offset;
    public long timestamp;
    public long timestampEnd;
    
    public static Buffer create() {
        Buffer result;
        try {
            result = Buffer.pool.pop();
        }
        catch (EmptyStackException e) {
            result = new Buffer();
            ++Buffer.live;
        }
        result.time_offset = -1L;
        result.timestamp = -1L;
        result.timestampEnd = -1L;
        result.flags = 0;
        return result;
    }
    
    public boolean isFlagSet(final int flag) {
        return (this.flags & flag) == flag;
    }
    
    public void setFlag(final int flag, final boolean val) {
        if (val) {
            this.flags |= flag;
        }
        else {
            this.flags &= ~flag;
        }
    }
    
    public void free() {
        this.object = null;
        this.caps = null;
        Buffer.pool.push(this);
    }
    
    public void ensureSize(final int length) {
        if (this.data == null) {
            this.data = new byte[length];
        }
        else if (this.data.length < length) {
            this.data = new byte[length];
        }
    }
    
    public void copyData(final byte[] data, final int offset, final int length) {
        this.ensureSize(length);
        System.arraycopy(data, offset, this.data, 0, length);
        this.offset = 0;
        this.length = length;
    }
    
    static {
        Buffer.pool = new Stack();
    }
}
