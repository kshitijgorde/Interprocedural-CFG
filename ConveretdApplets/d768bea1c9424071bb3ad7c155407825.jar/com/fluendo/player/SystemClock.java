// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

public class SystemClock implements ClockProvider
{
    public long getTime() {
        return System.currentTimeMillis();
    }
}
