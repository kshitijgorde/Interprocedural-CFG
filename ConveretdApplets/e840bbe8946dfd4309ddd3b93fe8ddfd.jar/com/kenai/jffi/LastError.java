// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

public final class LastError
{
    private final Foreign foreign;
    
    private LastError() {
        this.foreign = Foreign.getInstance();
    }
    
    public static final LastError getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    @Deprecated
    public final int getError() {
        return this.foreign.getLastError();
    }
    
    public final int get() {
        return this.foreign.getLastError();
    }
    
    public final void set(final int value) {
        this.foreign.setLastError(value);
    }
    
    private static final class SingletonHolder
    {
        static final LastError INSTANCE;
        
        static {
            INSTANCE = new LastError(null);
        }
    }
}
