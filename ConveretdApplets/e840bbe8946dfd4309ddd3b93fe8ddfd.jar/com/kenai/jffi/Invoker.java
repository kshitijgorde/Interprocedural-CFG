// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

public abstract class Invoker
{
    private static final long ADDRESS_SIZE;
    private static final long ADDRESS_MASK;
    private final Foreign foreign;
    
    public static final Invoker getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    private Invoker() {
        this.foreign = Foreign.getInstance();
    }
    
    public final int invokeVrI(final Function function) {
        return this.foreign.invokeVrI(function.getContextAddress());
    }
    
    public final float invokeVrF(final Function function) {
        return this.foreign.invokeVrF(function.getContextAddress());
    }
    
    public final int invokeNoErrnoVrI(final Function function) {
        return this.foreign.invokeNoErrnoVrI(function.getContextAddress());
    }
    
    public final int invokeIrI(final Function function, final int arg1) {
        return this.foreign.invokeIrI(function.getContextAddress(), arg1);
    }
    
    public final int invokeNoErrnoIrI(final Function function, final int arg1) {
        return this.foreign.invokeNoErrnoIrI(function.getContextAddress(), arg1);
    }
    
    public final float invokeIrF(final Function function, final int arg1) {
        return this.foreign.invokeIrF(function.getContextAddress(), arg1);
    }
    
    public final int invokeIIrI(final Function function, final int arg1, final int arg2) {
        return this.foreign.invokeIIrI(function.getContextAddress(), arg1, arg2);
    }
    
    public final int invokeNoErrnoIIrI(final Function function, final int arg1, final int arg2) {
        return this.foreign.invokeNoErrnoIIrI(function.getContextAddress(), arg1, arg2);
    }
    
    public final float invokeIIrF(final Function function, final int arg1, final int arg2) {
        return this.foreign.invokeIIrF(function.getContextAddress(), arg1, arg2);
    }
    
    public final int invokeIIIrI(final Function function, final int arg1, final int arg2, final int arg3) {
        return this.foreign.invokeIIIrI(function.getContextAddress(), arg1, arg2, arg3);
    }
    
    public final int invokeNoErrnoIIIrI(final Function function, final int arg1, final int arg2, final int arg3) {
        return this.foreign.invokeNoErrnoIIIrI(function.getContextAddress(), arg1, arg2, arg3);
    }
    
    public final float invokeIIIrF(final Function function, final int arg1, final int arg2, final int arg3) {
        return this.foreign.invokeIIIrF(function.getContextAddress(), arg1, arg2, arg3);
    }
    
    public final long invokeVrL(final Function function) {
        return this.foreign.invokeVrL(function.getContextAddress());
    }
    
    public final long invokeLrL(final Function function, final long arg1) {
        return this.foreign.invokeLrL(function.getContextAddress(), arg1);
    }
    
    public final long invokeLLrL(final Function function, final long arg1, final long arg2) {
        return this.foreign.invokeLLrL(function.getContextAddress(), arg1, arg2);
    }
    
    public final long invokeLLLrL(final Function function, final long arg1, final long arg2, final long arg3) {
        return this.foreign.invokeLLLrL(function.getContextAddress(), arg1, arg2, arg3);
    }
    
    public final long invokeLLLLrL(final Function function, final long arg1, final long arg2, final long arg3, final long arg4, final long arg5) {
        return this.foreign.invokeLLLLrL(function.getContextAddress(), arg1, arg2, arg3, arg4);
    }
    
    public final long invokeLLLLLrL(final Function function, final long arg1, final long arg2, final long arg3, final long arg4, final long arg5) {
        return this.foreign.invokeLLLLLrL(function.getContextAddress(), arg1, arg2, arg3, arg4, arg5);
    }
    
    public final long invokeLLLLLLrL(final Function function, final long arg1, final long arg2, final long arg3, final long arg4, final long arg5, final long arg6) {
        return this.foreign.invokeLLLLLLrL(function.getContextAddress(), arg1, arg2, arg3, arg4, arg5, arg6);
    }
    
    public final long invokeVrN(final Function function) {
        return this.foreign.invokeVrN(function.getContextAddress());
    }
    
    public final long invokeNrN(final Function function, final long arg1) {
        return this.foreign.invokeNrN(function.getContextAddress(), arg1);
    }
    
    public final long invokeNNrN(final Function function, final long arg1, final long arg2) {
        return this.foreign.invokeNNrN(function.getContextAddress(), arg1, arg2);
    }
    
    public final long invokeNNNrN(final Function function, final long arg1, final long arg2, final long arg3) {
        return this.foreign.invokeNNNrN(function.getContextAddress(), arg1, arg2, arg3);
    }
    
    public final long invokeNNNNrN(final Function function, final long arg1, final long arg2, final long arg3, final long arg4) {
        return this.foreign.invokeNNNNrN(function.getContextAddress(), arg1, arg2, arg3, arg4);
    }
    
    public final long invokeNNNNNrN(final Function function, final long arg1, final long arg2, final long arg3, final long arg4, final long arg5) {
        return this.foreign.invokeNNNNNrN(function.getContextAddress(), arg1, arg2, arg3, arg4, arg5);
    }
    
    public final long invokeNNNNNNrN(final Function function, final long arg1, final long arg2, final long arg3, final long arg4, final long arg5, final long arg6) {
        return this.foreign.invokeNNNNNNrN(function.getContextAddress(), arg1, arg2, arg3, arg4, arg5, arg6);
    }
    
    public abstract long invokeAddress(final Function p0, final HeapInvocationBuffer p1);
    
    public abstract long invokeAddress(final CallContext p0, final long p1, final HeapInvocationBuffer p2);
    
    public final int invokeInt(final Function function, final HeapInvocationBuffer buffer) {
        final ObjectBuffer objectBuffer = buffer.objectBuffer();
        return (objectBuffer != null) ? this.invokeArrayWithObjectsInt32(function, buffer, objectBuffer) : this.foreign.invokeArrayReturnInt(function.getContextAddress(), buffer.array());
    }
    
    public final int invokeInt(final CallContext ctx, final long function, final HeapInvocationBuffer buffer) {
        final ObjectBuffer objectBuffer = buffer.objectBuffer();
        final long fnHandle = this.newFunction(ctx, function);
        try {
            return (objectBuffer != null) ? this.invokeArrayWithObjectsInt32(fnHandle, buffer, objectBuffer) : this.foreign.invokeArrayReturnInt(fnHandle, buffer.array());
        }
        finally {
            this.foreign.freeFunction(fnHandle);
        }
    }
    
    public final long invokeLong(final Function function, final HeapInvocationBuffer buffer) {
        final ObjectBuffer objectBuffer = buffer.objectBuffer();
        return (objectBuffer != null) ? this.foreign.invokeArrayWithObjectsInt64(function.getContextAddress(), buffer.array(), objectBuffer.objectCount(), objectBuffer.info(), objectBuffer.objects()) : this.foreign.invokeArrayReturnLong(function.getContextAddress(), buffer.array());
    }
    
    public final long invokeLong(final CallContext ctx, final long function, final HeapInvocationBuffer buffer) {
        final ObjectBuffer objectBuffer = buffer.objectBuffer();
        final long fnHandle = this.newFunction(ctx, function);
        try {
            return (objectBuffer != null) ? this.invokeArrayWithObjectsInt64(fnHandle, buffer, objectBuffer) : this.foreign.invokeArrayReturnLong(fnHandle, buffer.array());
        }
        finally {
            this.foreign.freeFunction(fnHandle);
        }
    }
    
    public final float invokeFloat(final Function function, final HeapInvocationBuffer buffer) {
        final ObjectBuffer objectBuffer = buffer.objectBuffer();
        return (objectBuffer != null) ? this.foreign.invokeArrayWithObjectsFloat(function.getContextAddress(), buffer.array(), objectBuffer.objectCount(), objectBuffer.info(), objectBuffer.objects()) : this.foreign.invokeArrayReturnFloat(function.getContextAddress(), buffer.array());
    }
    
    public final float invokeFloat(final CallContext ctx, final long function, final HeapInvocationBuffer buffer) {
        final ObjectBuffer objectBuffer = buffer.objectBuffer();
        final long fnHandle = this.newFunction(ctx, function);
        try {
            return (objectBuffer != null) ? this.foreign.invokeArrayWithObjectsFloat(fnHandle, buffer.array(), objectBuffer.objectCount(), objectBuffer.info(), objectBuffer.objects()) : this.foreign.invokeArrayReturnFloat(fnHandle, buffer.array());
        }
        finally {
            this.foreign.freeFunction(fnHandle);
        }
    }
    
    public final double invokeDouble(final Function function, final HeapInvocationBuffer buffer) {
        final ObjectBuffer objectBuffer = buffer.objectBuffer();
        return (objectBuffer != null) ? this.foreign.invokeArrayWithObjectsDouble(function.getContextAddress(), buffer.array(), objectBuffer.objectCount(), objectBuffer.info(), objectBuffer.objects()) : this.foreign.invokeArrayReturnDouble(function.getContextAddress(), buffer.array());
    }
    
    public final double invokeDouble(final CallContext ctx, final long function, final HeapInvocationBuffer buffer) {
        final ObjectBuffer objectBuffer = buffer.objectBuffer();
        final long fnHandle = this.newFunction(ctx, function);
        try {
            return (objectBuffer != null) ? this.foreign.invokeArrayWithObjectsDouble(fnHandle, buffer.array(), objectBuffer.objectCount(), objectBuffer.info(), objectBuffer.objects()) : this.foreign.invokeArrayReturnDouble(fnHandle, buffer.array());
        }
        finally {
            this.foreign.freeFunction(fnHandle);
        }
    }
    
    public final byte[] invokeStruct(final Function function, final HeapInvocationBuffer buffer) {
        final byte[] returnBuffer = new byte[function.getReturnType().size()];
        this.invokeStruct(function, buffer, returnBuffer, 0);
        return returnBuffer;
    }
    
    public final byte[] invokeStruct(final CallContext ctx, final long function, final HeapInvocationBuffer buffer) {
        final byte[] returnBuffer = new byte[ctx.getReturnType().size()];
        this.invokeStruct(ctx, function, buffer, returnBuffer, 0);
        return returnBuffer;
    }
    
    public final void invokeStruct(final Function function, final HeapInvocationBuffer buffer, final byte[] returnBuffer, final int offset) {
        final ObjectBuffer objectBuffer = buffer.objectBuffer();
        if (objectBuffer != null) {
            this.foreign.invokeArrayWithObjectsReturnStruct(function.getContextAddress(), buffer.array(), objectBuffer.objectCount(), objectBuffer.info(), objectBuffer.objects(), returnBuffer, offset);
        }
        else {
            this.foreign.invokeArrayReturnStruct(function.getContextAddress(), buffer.array(), returnBuffer, offset);
        }
    }
    
    public final void invokeStruct(final CallContext ctx, final long function, final HeapInvocationBuffer buffer, final byte[] returnBuffer, final int offset) {
        final ObjectBuffer objectBuffer = buffer.objectBuffer();
        final long fnHandle = this.newFunction(ctx, function);
        try {
            if (objectBuffer != null) {
                this.foreign.invokeArrayWithObjectsReturnStruct(fnHandle, buffer.array(), objectBuffer.objectCount(), objectBuffer.info(), objectBuffer.objects(), returnBuffer, offset);
            }
            else {
                this.foreign.invokeArrayReturnStruct(fnHandle, buffer.array(), returnBuffer, offset);
            }
        }
        finally {
            this.foreign.freeFunction(fnHandle);
        }
    }
    
    public final Object invokeObject(final Function function, final HeapInvocationBuffer buffer) {
        final ObjectBuffer objectBuffer = buffer.objectBuffer();
        return this.foreign.invokeArrayWithObjectsReturnObject(function.getContextAddress(), buffer.array(), objectBuffer.objectCount(), objectBuffer.info(), objectBuffer.objects());
    }
    
    public final void invoke(final Function function, final long returnBuffer, final long[] parameters) {
        this.foreign.invokePointerParameterArray(function.getContextAddress(), returnBuffer, parameters);
    }
    
    public final void invoke(final CallContext ctx, final long function, final long returnBuffer, final long[] parameters) {
        final long fnHandle = this.newFunction(ctx, function);
        try {
            this.foreign.invokePointerParameterArray(fnHandle, returnBuffer, parameters);
        }
        finally {
            this.foreign.freeFunction(fnHandle);
        }
    }
    
    private final int invokeArrayWithObjectsInt32(final Function function, final HeapInvocationBuffer buffer, final ObjectBuffer objectBuffer) {
        return this.invokeArrayWithObjectsInt32(function.getContextAddress(), buffer, objectBuffer);
    }
    
    private final int invokeArrayWithObjectsInt32(final CallContext ctx, final long function, final HeapInvocationBuffer buffer, final ObjectBuffer objectBuffer) {
        final long fnHandle = this.newFunction(ctx, function);
        try {
            return this.invokeArrayWithObjectsInt32(fnHandle, buffer, objectBuffer);
        }
        finally {
            this.foreign.freeFunction(fnHandle);
        }
    }
    
    private final int invokeArrayWithObjectsInt32(final long fnHandle, final HeapInvocationBuffer buffer, final ObjectBuffer objectBuffer) {
        final Object[] objects = objectBuffer.objects();
        final int[] info = objectBuffer.info();
        final int objectCount = objectBuffer.objectCount();
        switch (objectCount) {
            case 1: {
                return this.foreign.invokeArrayO1Int32(fnHandle, buffer.array(), objects[0], info[0], info[1], info[2]);
            }
            case 2: {
                return this.foreign.invokeArrayO2Int32(fnHandle, buffer.array(), objects[0], info[0], info[1], info[2], objects[1], info[3], info[4], info[5]);
            }
            default: {
                return this.foreign.invokeArrayWithObjectsInt32(fnHandle, buffer.array(), objectCount, info, objects);
            }
        }
    }
    
    private final long invokeArrayWithObjectsInt64(final long fnHandle, final HeapInvocationBuffer buffer, final ObjectBuffer objectBuffer) {
        final Object[] objects = objectBuffer.objects();
        final int[] info = objectBuffer.info();
        final int objectCount = objectBuffer.objectCount();
        switch (objectCount) {
            case 1: {
                return this.foreign.invokeArrayO1Int64(fnHandle, buffer.array(), objects[0], info[0], info[1], info[2]);
            }
            case 2: {
                return this.foreign.invokeArrayO2Int64(fnHandle, buffer.array(), objects[0], info[0], info[1], info[2], objects[1], info[3], info[4], info[5]);
            }
            default: {
                return this.foreign.invokeArrayWithObjectsInt64(fnHandle, buffer.array(), objectCount, info, objects);
            }
        }
    }
    
    private long newFunction(final CallContext ctx, final long function) {
        return this.foreign.newFunction(function, ctx.getReturnType().handle(), ctx.parameterTypeHandles, ctx.flags);
    }
    
    static {
        ADDRESS_SIZE = Platform.getPlatform().addressSize();
        ADDRESS_MASK = Platform.getPlatform().addressMask();
    }
    
    private static final class SingletonHolder
    {
        private static final Invoker INSTANCE;
        
        static {
            INSTANCE = ((Invoker.ADDRESS_SIZE == 64L) ? LP64.INSTANCE : ILP32.INSTANCE);
        }
    }
    
    private static final class ILP32 extends Invoker
    {
        private static final Invoker INSTANCE;
        
        private ILP32() {
            super(null);
        }
        
        public final long invokeAddress(final Function function, final HeapInvocationBuffer buffer) {
            return this.invokeInt(function, buffer) & Invoker.ADDRESS_MASK;
        }
        
        public final long invokeAddress(final CallContext ctx, final long function, final HeapInvocationBuffer buffer) {
            return this.invokeInt(ctx, function, buffer) & Invoker.ADDRESS_MASK;
        }
        
        static {
            INSTANCE = new ILP32();
        }
    }
    
    private static final class LP64 extends Invoker
    {
        private static final Invoker INSTANCE;
        
        private LP64() {
            super(null);
        }
        
        public long invokeAddress(final Function function, final HeapInvocationBuffer buffer) {
            return this.invokeLong(function, buffer);
        }
        
        public final long invokeAddress(final CallContext ctx, final long function, final HeapInvocationBuffer buffer) {
            return this.invokeLong(ctx, function, buffer);
        }
        
        static {
            INSTANCE = new LP64();
        }
    }
}
