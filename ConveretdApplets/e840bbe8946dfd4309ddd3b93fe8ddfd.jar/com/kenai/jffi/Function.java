// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Function implements CallInfo
{
    private final long contextAddress;
    private final long functionAddress;
    private final int parameterCount;
    private final int rawParameterSize;
    private final Type returnType;
    private final Type[] paramTypes;
    private volatile boolean disposed;
    private final Foreign foreign;
    
    public Function(final long address, final Type returnType, final Type... paramTypes) {
        this(address, returnType, paramTypes, CallingConvention.DEFAULT, true);
    }
    
    public Function(final long address, final Type returnType, final Type[] paramTypes, final CallingConvention convention) {
        this(address, returnType, paramTypes, convention, true);
    }
    
    public Function(final long address, final Type returnType, final Type[] paramTypes, final CallingConvention convention, final boolean saveErrno) {
        this.disposed = false;
        this.foreign = Foreign.getInstance();
        this.functionAddress = address;
        final int flags = (saveErrno ? 0 : 2) | ((convention == CallingConvention.STDCALL) ? 1 : 0);
        final long h = this.foreign.newFunction(address, returnType.handle(), Type.nativeHandles(paramTypes), flags);
        if (h == 0L) {
            throw new RuntimeException("Failed to create native function");
        }
        this.contextAddress = h;
        this.returnType = returnType;
        this.paramTypes = paramTypes.clone();
        this.parameterCount = paramTypes.length;
        this.rawParameterSize = this.foreign.getFunctionRawParameterSize(h);
    }
    
    public final int getParameterCount() {
        return this.parameterCount;
    }
    
    public final int getRawParameterSize() {
        return this.rawParameterSize;
    }
    
    final long getContextAddress() {
        return this.contextAddress;
    }
    
    public final long getFunctionAddress() {
        return this.functionAddress;
    }
    
    public final Type getReturnType() {
        return this.returnType;
    }
    
    public final Type getParameterType(final int index) {
        return this.paramTypes[index];
    }
    
    public final synchronized void dispose() {
        if (this.disposed) {
            throw new RuntimeException("function already freed");
        }
        this.foreign.freeFunction(this.contextAddress);
        this.disposed = true;
    }
    
    protected void finalize() throws Throwable {
        try {
            if (this.contextAddress != 0L && !this.disposed) {
                this.foreign.freeFunction(this.contextAddress);
            }
        }
        catch (Throwable t) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Exception when freeing function context: %s", t.getLocalizedMessage());
        }
        finally {
            super.finalize();
        }
    }
}
