// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal;

public class Callback
{
    Object object;
    String method;
    String signature;
    int argCount;
    int address;
    int errorResult;
    boolean isStatic;
    boolean isArrayBased;
    static final String PTR_SIGNATURE;
    static final String SIGNATURE_0;
    static final String SIGNATURE_1;
    static final String SIGNATURE_2;
    static final String SIGNATURE_3;
    static final String SIGNATURE_4;
    static final String SIGNATURE_N;
    
    static {
        PTR_SIGNATURE = ((C.PTR_SIZEOF == 4) ? "I" : "J");
        SIGNATURE_0 = getSignature(0);
        SIGNATURE_1 = getSignature(1);
        SIGNATURE_2 = getSignature(2);
        SIGNATURE_3 = getSignature(3);
        SIGNATURE_4 = getSignature(4);
        SIGNATURE_N = "([" + Callback.PTR_SIGNATURE + ")" + Callback.PTR_SIGNATURE;
    }
    
    public Callback(final Object o, final String s, final int n) {
        this(o, s, n, false);
    }
    
    public Callback(final Object o, final String s, final int n, final boolean b) {
        this(o, s, n, b, 0);
    }
    
    public Callback(final Object object, final String method, final int argCount, final boolean isArrayBased, final int errorResult) {
        this.object = object;
        this.method = method;
        this.argCount = argCount;
        this.isStatic = (object instanceof Class);
        this.isArrayBased = isArrayBased;
        this.errorResult = errorResult;
        if (isArrayBased) {
            this.signature = Callback.SIGNATURE_N;
        }
        else {
            switch (argCount) {
                case 0: {
                    this.signature = Callback.SIGNATURE_0;
                    break;
                }
                case 1: {
                    this.signature = Callback.SIGNATURE_1;
                    break;
                }
                case 2: {
                    this.signature = Callback.SIGNATURE_2;
                    break;
                }
                case 3: {
                    this.signature = Callback.SIGNATURE_3;
                    break;
                }
                case 4: {
                    this.signature = Callback.SIGNATURE_4;
                    break;
                }
                default: {
                    this.signature = getSignature(argCount);
                    break;
                }
            }
        }
        this.address = bind(this, object, method, this.signature, argCount, this.isStatic, isArrayBased, errorResult);
    }
    
    static synchronized native int bind(final Callback p0, final Object p1, final String p2, final String p3, final int p4, final boolean p5, final boolean p6, final int p7);
    
    public void dispose() {
        if (this.object == null) {
            return;
        }
        unbind(this);
        final String object = null;
        this.signature = object;
        this.method = object;
        this.object = object;
        this.address = 0;
    }
    
    public int getAddress() {
        return this.address;
    }
    
    public static native String getPlatform();
    
    public static native int getEntryCount();
    
    static String getSignature(final int n) {
        String string = "(";
        for (int i = 0; i < n; ++i) {
            string = String.valueOf(string) + Callback.PTR_SIGNATURE;
        }
        return String.valueOf(string) + ")" + Callback.PTR_SIGNATURE;
    }
    
    public static final synchronized native void setEnabled(final boolean p0);
    
    public static final synchronized native boolean getEnabled();
    
    static final void ignoreCallbacks(final boolean b) {
        setEnabled(!b);
    }
    
    public static final synchronized native void reset();
    
    static final synchronized native void unbind(final Callback p0);
}
