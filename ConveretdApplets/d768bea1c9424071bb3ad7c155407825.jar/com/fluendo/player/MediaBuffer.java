// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import java.util.EmptyStackException;
import java.util.Stack;

public class MediaBuffer
{
    private static Stack pool;
    private static int live;
    public Object object;
    public byte[] data;
    public int offset;
    public int length;
    public long time_offset;
    public long timestamp;
    
    public static MediaBuffer create() {
        MediaBuffer mediaBuffer;
        try {
            mediaBuffer = MediaBuffer.pool.pop();
        }
        catch (EmptyStackException ex) {
            mediaBuffer = new MediaBuffer();
            ++MediaBuffer.live;
        }
        mediaBuffer.time_offset = -1L;
        mediaBuffer.timestamp = -1L;
        return mediaBuffer;
    }
    
    public void free() {
        this.object = null;
        MediaBuffer.pool.push(this);
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
        MediaBuffer.pool = new Stack();
    }
}
