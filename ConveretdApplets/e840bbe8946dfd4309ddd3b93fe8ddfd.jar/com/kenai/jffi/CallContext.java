// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class CallContext implements CallInfo
{
    private final long contextAddress;
    private volatile boolean disposed;
    private final int parameterCount;
    private final int rawParameterSize;
    final Type returnType;
    final Type[] parameterTypes;
    final long[] parameterTypeHandles;
    final int flags;
    private final Foreign foreign;
    
    public CallContext(final Type returnType, final Type... paramTypes) {
        this(returnType, paramTypes, CallingConvention.DEFAULT, true);
    }
    
    public CallContext(final Type returnType, final Type[] paramTypes, final CallingConvention convention) {
        this(returnType, paramTypes, convention, true);
    }
    
    public CallContext(final Type returnType, final Type[] paramTypes, final CallingConvention convention, final boolean saveErrno) {
        this.disposed = false;
        this.foreign = Foreign.getInstance();
        final int flags = (saveErrno ? 0 : 2) | ((convention == CallingConvention.STDCALL) ? 1 : 0);
        final long h = this.foreign.newCallContext(returnType.handle(), Type.nativeHandles(paramTypes), flags);
        if (h == 0L) {
            throw new RuntimeException("Failed to create native function");
        }
        this.contextAddress = h;
        this.returnType = returnType;
        this.parameterTypes = paramTypes.clone();
        this.parameterCount = paramTypes.length;
        this.rawParameterSize = this.foreign.getFunctionRawParameterSize(h);
        this.parameterTypeHandles = Type.nativeHandles(paramTypes);
        this.flags = flags;
    }
    
    public final int getParameterCount() {
        return this.parameterCount;
    }
    
    public final int getRawParameterSize() {
        return this.rawParameterSize;
    }
    
    final long getAddress() {
        return this.contextAddress;
    }
    
    public final Type getReturnType() {
        return this.returnType;
    }
    
    public final Type getParameterType(final int index) {
        return this.parameterTypes[index];
    }
    
    public final synchronized void dispose() {
        if (this.disposed) {
            throw new RuntimeException("context already freed");
        }
        this.foreign.freeCallContext(this.contextAddress);
        this.disposed = true;
    }
    
    protected void finalize() throws Throwable {
        try {
            if (this.contextAddress != 0L && !this.disposed) {
                this.foreign.freeCallContext(this.contextAddress);
            }
        }
        catch (Throwable t) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Exception when freeing CallContext: %s", t.getLocalizedMessage());
        }
        finally {
            super.finalize();
        }
    }
}
