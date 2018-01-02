// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

public interface PreBufferNotify
{
    public static final int STATE_START = 0;
    public static final int STATE_BUFFER = 1;
    public static final int STATE_PLAYBACK = 2;
    public static final int STATE_OVERFLOW = 3;
    
    void preBufferNotify(final int p0);
}
