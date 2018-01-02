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
        Buffer buffer;
        try {
            buffer = Buffer.pool.pop();
        }
        catch (EmptyStackException ex) {
            buffer = new Buffer();
            ++Buffer.live;
        }
        buffer.time_offset = -1L;
        buffer.timestamp = -1L;
        buffer.timestampEnd = -1L;
        buffer.flags = 0;
        return buffer;
    }
    
    public boolean isFlagSet(final int n) {
        return (this.flags & n) == n;
    }
    
    public void setFlag(final int n, final boolean b) {
        if (b) {
            this.flags |= n;
        }
        else {
            this.flags &= ~n;
        }
    }
    
    public void free() {
        this.object = null;
        this.caps = null;
        Buffer.pool.push(this);
    }
    
    public void ensureSize(final int n) {
        if (this.data == null) {
            this.data = new byte[n];
        }
        else if (this.data.length < n) {
            this.data = new byte[n];
        }
    }
    
    public void copyData(final byte[] array, final int n, final int length) {
        this.ensureSize(length);
        System.arraycopy(array, n, this.data, 0, length);
        this.offset = 0;
        this.length = length;
    }
    
    static {
        Buffer.pool = new Stack();
    }
}
