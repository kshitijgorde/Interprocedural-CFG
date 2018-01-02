// 
// Decompiled by Procyon v0.5.30
// 

package com.eaio.nativecall;

public class IntCall extends NativeCall
{
    public boolean executeBooleanCall() {
        final int n = 1;
        int n2 = 0;
        if (this.executeCall() == 0) {
            n2 = 1;
        }
        return n - n2 != 0;
    }
    
    public boolean executeBooleanCall(final Object param) {
        final int n = 1;
        int n2 = 0;
        if (this.executeCall(param) == 0) {
            n2 = 1;
        }
        return n - n2 != 0;
    }
    
    public boolean executeBooleanCall(final Object[] params) {
        final int n = 1;
        int n2 = 0;
        if (this.executeCall(params) == 0) {
            n2 = 1;
        }
        return n - n2 != 0;
    }
    
    public native int executeCall();
    
    public int executeCall(final Object param) {
        return this.executeCall(new Object[] { param });
    }
    
    public int executeCall(final Object[] params) {
        if (params == null || params.length == 0) {
            return this.executeCall();
        }
        this.check(params);
        return this.executeCall0(params);
    }
    
    private final native int executeCall0(final Object[] p0);
    
    public IntCall(final String function) throws SecurityException, IllegalArgumentException, NullPointerException {
        super(function);
    }
    
    public IntCall(final String module, final String function) throws SecurityException, IllegalArgumentException, NullPointerException {
        super(module, function);
    }
}
