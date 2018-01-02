// 
// Decompiled by Procyon v0.5.30
// 

package com.eaio.nativecall;

import sun.misc.ServiceConfigurationError;
import java.io.IOException;
import com.eaio.util.lang.NativeLoader;

public abstract class NativeCall
{
    private static boolean initialized;
    private int lastErrorCode;
    private int functionHandle;
    private int moduleHandle;
    private String function;
    private String module;
    
    private static final native void initIDs();
    
    public static synchronized void init() throws IOException, SecurityException, UnsatisfiedLinkError, ServiceConfigurationError, UnsupportedOperationException {
        if (!NativeCall.initialized) {
            Verifiers.init();
            if (Verifiers.getInstance() == null) {
                throw new UnsupportedOperationException();
            }
            new NativeLoader("NativeCall").load();
            initIDs();
            NativeCall.initialized = true;
        }
    }
    
    private final native boolean initHandles();
    
    public final int getLastErrorCode() {
        return this.lastErrorCode;
    }
    
    public final native String getLastError();
    
    public synchronized native void destroy();
    
    protected void check(final Object[] params) throws ClassCastException {
        if (params == null) {
            return;
        }
        for (int i = 0; i < params.length; ++i) {
            this.checkParam(params[i]);
            if (params[i] instanceof String) {
                params[i] = Verifiers.getInstance().handleString((String)params[i], this.module, this.function);
            }
        }
    }
    
    protected void checkParam(final Object o) throws ClassCastException {
        if (o == null || o instanceof Boolean || o instanceof Integer || o instanceof byte[] || o instanceof char[] || o instanceof String) {
            return;
        }
        if (o instanceof Holder) {
            this.checkParam(((Holder)o).get());
            return;
        }
        throw new ClassCastException(o.getClass().getName());
    }
    
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NativeCall)) {
            return false;
        }
        if (!this.getClass().getName().equals(obj.getClass().getName())) {
            return false;
        }
        final NativeCall c = (NativeCall)obj;
        boolean b = false;
        if (this.module.equals(c.module) && this.function.equals(c.function)) {
            b = true;
        }
        return b;
    }
    
    public int hashCode() {
        int out = this.function.hashCode();
        out ^= this.module.hashCode();
        return out;
    }
    
    protected void finalize() throws Throwable {
        try {
            this.destroy();
        }
        finally {
            super.finalize();
        }
        super.finalize();
    }
    
    public final String toString() {
        return this.toStringBuffer(null).toString();
    }
    
    public StringBuffer toStringBuffer(StringBuffer in) {
        if (in == null) {
            in = new StringBuffer(64);
        }
        else {
            in.ensureCapacity(in.length() + 64);
        }
        in.append("{ ");
        int idx = this.getClass().getName().lastIndexOf(".");
        if (idx > -1) {
            in.append(this.getClass().getName().substring(++idx));
        }
        else {
            in.append(this.getClass().getName());
        }
        in.append(": module = ");
        in.append(this.module);
        in.append(", function = ");
        in.append(this.function);
        in.append(" }");
        return in;
    }
    
    public NativeCall(final String function) throws IllegalArgumentException, NullPointerException {
        this(Verifiers.getInstance().getDefaultModule(), function);
    }
    
    public NativeCall(final String module, final String function) throws IllegalArgumentException, NullPointerException {
        final Verifier v = Verifiers.getInstance();
        this.function = v.verifyFunctionName(function);
        this.module = v.verifyModuleName(module);
        if (this.initHandles()) {
            return;
        }
        if (this.lastErrorCode != 0) {
            throw new IllegalArgumentException(this.getLastError());
        }
        throw new IllegalArgumentException();
    }
    
    static {
        NativeCall.initialized = false;
    }
}
