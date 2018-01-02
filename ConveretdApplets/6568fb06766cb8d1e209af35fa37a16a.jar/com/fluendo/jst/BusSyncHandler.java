// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

public interface BusSyncHandler
{
    public static final int DROP = 0;
    public static final int PASS = 1;
    
    int handleSyncMessage(final Message p0);
}
