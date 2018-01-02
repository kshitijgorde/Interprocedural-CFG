// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl;

public final class LastError
{
    public static final int getLastError() {
        return FFIProvider.getProvider().getLastError();
    }
    
    public static final void setLastError(final int error) {
        FFIProvider.getProvider().setLastError(error);
    }
}
