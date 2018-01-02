// 
// Decompiled by Procyon v0.5.30
// 

package com.eaio.nativecall;

public class VoidCall extends NativeCall
{
    public native void executeCall();
    
    public void executeCall(final Object param) {
        this.executeCall(new Object[] { param });
    }
    
    public void executeCall(final Object[] params) {
        if (params == null || params.length == 0) {
            this.executeCall();
            return;
        }
        this.check(params);
        this.executeCall0(params);
    }
    
    private final native void executeCall0(final Object[] p0);
    
    public VoidCall(final String function) throws SecurityException, IllegalArgumentException, NullPointerException {
        super(function);
    }
    
    public VoidCall(final String module, final String function) throws SecurityException, IllegalArgumentException, NullPointerException {
        super(module, function);
    }
}
