// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.lang.reflect.Method;

final class Foreign
{
    public static final int VERSION_MAJOR;
    public static final int VERSION_MINOR;
    public static final int VERSION_MICRO;
    public static final int TYPE_VOID = 0;
    public static final int TYPE_FLOAT = 2;
    public static final int TYPE_DOUBLE = 3;
    public static final int TYPE_LONGDOUBLE = 4;
    public static final int TYPE_UINT8 = 5;
    public static final int TYPE_SINT8 = 6;
    public static final int TYPE_UINT16 = 7;
    public static final int TYPE_SINT16 = 8;
    public static final int TYPE_UINT32 = 9;
    public static final int TYPE_SINT32 = 10;
    public static final int TYPE_UINT64 = 11;
    public static final int TYPE_SINT64 = 12;
    public static final int TYPE_STRUCT = 13;
    public static final int TYPE_POINTER = 14;
    public static final int TYPE_UCHAR = 101;
    public static final int TYPE_SCHAR = 102;
    public static final int TYPE_USHORT = 103;
    public static final int TYPE_SSHORT = 104;
    public static final int TYPE_UINT = 105;
    public static final int TYPE_SINT = 106;
    public static final int TYPE_ULONG = 107;
    public static final int TYPE_SLONG = 108;
    public static final int RTLD_LAZY = 1;
    public static final int RTLD_NOW = 2;
    public static final int RTLD_LOCAL = 4;
    public static final int RTLD_GLOBAL = 8;
    public static final int PROT_READ = 1;
    public static final int PROT_WRITE = 2;
    public static final int PROT_EXEC = 4;
    public static final int PROT_NONE = 0;
    public static final int MAP_SHARED = 1;
    public static final int MAP_PRIVATE = 2;
    public static final int MAP_FIXED = 16;
    public static final int MAP_NORESERVE = 64;
    public static final int MAP_ANON = 256;
    public static final int MAP_ALIGN = 512;
    public static final int MAP_TEXT = 1024;
    public static final int PAGE_NOACCESS = 1;
    public static final int PAGE_READONLY = 2;
    public static final int PAGE_READWRITE = 4;
    public static final int PAGE_WRITECOPY = 8;
    public static final int PAGE_EXECUTE = 16;
    public static final int PAGE_EXECUTE_READ = 32;
    public static final int PAGE_EXECUTE_READWRITE = 64;
    public static final int PAGE_EXECUTE_WRITECOPY = 128;
    public static final int MEM_COMMIT = 4096;
    public static final int MEM_RESERVE = 8192;
    public static final int MEM_DECOMMIT = 16384;
    public static final int MEM_RELEASE = 32768;
    public static final int MEM_FREE = 65536;
    public static final int MEM_PRIVATE = 131072;
    public static final int MEM_MAPPED = 262144;
    public static final int MEM_RESET = 524288;
    public static final int MEM_TOP_DOWN = 1048576;
    public static final int MEM_PHYSICAL = 4194304;
    public static final int MEM_4MB_PAGES = Integer.MIN_VALUE;
    public static final int JNI_OK = 0;
    public static final int JNI_ERR = -1;
    public static final int JNI_EDETACHED = -2;
    public static final int JNI_EVERSION = -3;
    public static final int JNI_ENOMEM = -4;
    public static final int JNI_EEXIST = -5;
    public static final int JNI_EINVAL = -6;
    public static final int F_DEFAULT = 0;
    public static final int F_STDCALL = 1;
    public static final int F_NOERRNO = 2;
    
    public static final Foreign getInstance() {
        return InstanceHolder.INSTANCE.getForeign();
    }
    
    private static final int getVersionField(final String name) {
        try {
            final Class c = Class.forName(Foreign.class.getPackage().getName() + ".Version");
            return (int)c.getField(name).get(c);
        }
        catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
    
    final native int getVersion();
    
    private final native void init();
    
    final native long dlopen(final String p0, final int p1);
    
    final native void dlclose(final long p0);
    
    final native long dlsym(final long p0, final String p1);
    
    final native String dlerror();
    
    final native long allocateMemory(final long p0, final boolean p1);
    
    final native void freeMemory(final long p0);
    
    final native long pageSize();
    
    final native long mmap(final long p0, final long p1, final int p2, final int p3, final int p4, final long p5);
    
    final native int munmap(final long p0, final long p1);
    
    final native int mprotect(final long p0, final long p1, final int p2);
    
    final native long VirtualAlloc(final long p0, final int p1, final int p2, final int p3);
    
    final native boolean VirtualFree(final long p0, final int p1, final int p2);
    
    final native boolean VirtualProtect(final long p0, final int p1, final int p2);
    
    final native long newFunction(final long p0, final long p1, final long[] p2, final int p3);
    
    final native void freeFunction(final long p0);
    
    final native long getFunctionAddress(final long p0);
    
    final native int getFunctionRawParameterSize(final long p0);
    
    final native long newCallContext(final long p0, final long[] p1, final int p2);
    
    final native void freeCallContext(final long p0);
    
    final native int getCallContextRawParameterSize(final long p0);
    
    final native boolean isRawParameterPackingEnabled();
    
    final native int getLastError();
    
    final native void setLastError(final int p0);
    
    final native long newClosureMagazine(final long p0, final Method p1);
    
    final native void freeClosureMagazine(final long p0);
    
    final native long closureMagazineGet(final long p0, final Object p1);
    
    final native long lookupBuiltinType(final int p0);
    
    final native int getTypeSize(final long p0);
    
    final native int getTypeAlign(final long p0);
    
    final native int getTypeType(final long p0);
    
    final native long newStruct(final long[] p0, final boolean p1);
    
    final native long newArray(final long p0, final int p1);
    
    final native void freeAggregate(final long p0);
    
    final native int invokeVrI(final long p0);
    
    final native float invokeVrF(final long p0);
    
    final native int invokeNoErrnoVrI(final long p0);
    
    final native int invokeIrI(final long p0, final int p1);
    
    final native int invokeNoErrnoIrI(final long p0, final int p1);
    
    final native float invokeIrF(final long p0, final int p1);
    
    final native int invokeIIrI(final long p0, final int p1, final int p2);
    
    final native float invokeIIrF(final long p0, final int p1, final int p2);
    
    final native int invokeNoErrnoIIrI(final long p0, final int p1, final int p2);
    
    final native int invokeIIIrI(final long p0, final int p1, final int p2, final int p3);
    
    final native int invokeIIIIrI(final long p0, final int p1, final int p2, final int p3, final int p4);
    
    final native int invokeIIIIIrI(final long p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    final native int invokeIIIIIIrI(final long p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
    
    final native float invokeIIIrF(final long p0, final int p1, final int p2, final int p3);
    
    final native int invokeNoErrnoIIIrI(final long p0, final int p1, final int p2, final int p3);
    
    final native long invokeVrL(final long p0);
    
    final native double invokeVrD(final long p0);
    
    final native long invokeLrL(final long p0, final long p1);
    
    final native double invokeLrD(final long p0, final long p1);
    
    final native long invokeLLrL(final long p0, final long p1, final long p2);
    
    final native double invokeLLrD(final long p0, final long p1, final long p2);
    
    final native long invokeLLLrL(final long p0, final long p1, final long p2, final long p3);
    
    final native long invokeLLLLrL(final long p0, final long p1, final long p2, final long p3, final long p4);
    
    final native long invokeLLLLLrL(final long p0, final long p1, final long p2, final long p3, final long p4, final long p5);
    
    final native long invokeLLLLLLrL(final long p0, final long p1, final long p2, final long p3, final long p4, final long p5, final long p6);
    
    final native double invokeLLLrD(final long p0, final long p1, final long p2, final long p3);
    
    final native long invokeVrN(final long p0);
    
    final native long invokeNrN(final long p0, final long p1);
    
    final native long invokeNNrN(final long p0, final long p1, final long p2);
    
    final native long invokeNNNrN(final long p0, final long p1, final long p2, final long p3);
    
    final native long invokeNNNNrN(final long p0, final long p1, final long p2, final long p3, final long p4);
    
    final native long invokeNNNNNrN(final long p0, final long p1, final long p2, final long p3, final long p4, final long p5);
    
    final native long invokeNNNNNNrN(final long p0, final long p1, final long p2, final long p3, final long p4, final long p5, final long p6);
    
    final native int invokeArrayReturnInt(final long p0, final byte[] p1);
    
    final native long invokeArrayReturnLong(final long p0, final byte[] p1);
    
    final native float invokeArrayReturnFloat(final long p0, final byte[] p1);
    
    final native double invokeArrayReturnDouble(final long p0, final byte[] p1);
    
    final native void invokeArrayReturnStruct(final long p0, final byte[] p1, final byte[] p2, final int p3);
    
    final native Object invokeArrayWithObjectsReturnObject(final long p0, final byte[] p1, final int p2, final int[] p3, final Object[] p4);
    
    final native int invokeArrayWithObjectsInt32(final long p0, final byte[] p1, final int p2, final int[] p3, final Object[] p4);
    
    final native long invokeArrayWithObjectsInt64(final long p0, final byte[] p1, final int p2, final int[] p3, final Object[] p4);
    
    final native float invokeArrayWithObjectsFloat(final long p0, final byte[] p1, final int p2, final int[] p3, final Object[] p4);
    
    final native double invokeArrayWithObjectsDouble(final long p0, final byte[] p1, final int p2, final int[] p3, final Object[] p4);
    
    final native void invokeArrayWithObjectsReturnStruct(final long p0, final byte[] p1, final int p2, final int[] p3, final Object[] p4, final byte[] p5, final int p6);
    
    final native int invokeArrayO1Int32(final long p0, final byte[] p1, final Object p2, final int p3, final int p4, final int p5);
    
    final native int invokeArrayO2Int32(final long p0, final byte[] p1, final Object p2, final int p3, final int p4, final int p5, final Object p6, final int p7, final int p8, final int p9);
    
    final native long invokeArrayO1Int64(final long p0, final byte[] p1, final Object p2, final int p3, final int p4, final int p5);
    
    final native long invokeArrayO2Int64(final long p0, final byte[] p1, final Object p2, final int p3, final int p4, final int p5, final Object p6, final int p7, final int p8, final int p9);
    
    final native void invokePointerParameterArray(final long p0, final long p1, final long[] p2);
    
    final native byte getByte(final long p0);
    
    final native short getShort(final long p0);
    
    final native int getInt(final long p0);
    
    final native long getLong(final long p0);
    
    final native float getFloat(final long p0);
    
    final native double getDouble(final long p0);
    
    final native long getAddress(final long p0);
    
    final native void putByte(final long p0, final byte p1);
    
    final native void putShort(final long p0, final short p1);
    
    final native void putInt(final long p0, final int p1);
    
    final native void putLong(final long p0, final long p1);
    
    final native void putFloat(final long p0, final float p1);
    
    final native void putDouble(final long p0, final double p1);
    
    final native void putAddress(final long p0, final long p1);
    
    final native void setMemory(final long p0, final long p1, final byte p2);
    
    final native void copyMemory(final long p0, final long p1, final long p2);
    
    final native void putByteArray(final long p0, final byte[] p1, final int p2, final int p3);
    
    final native void getByteArray(final long p0, final byte[] p1, final int p2, final int p3);
    
    final native void putCharArray(final long p0, final char[] p1, final int p2, final int p3);
    
    final native void getCharArray(final long p0, final char[] p1, final int p2, final int p3);
    
    final native void putShortArray(final long p0, final short[] p1, final int p2, final int p3);
    
    final native void getShortArray(final long p0, final short[] p1, final int p2, final int p3);
    
    final native void putIntArray(final long p0, final int[] p1, final int p2, final int p3);
    
    final native void getIntArray(final long p0, final int[] p1, final int p2, final int p3);
    
    final native void putLongArray(final long p0, final long[] p1, final int p2, final int p3);
    
    final native void getLongArray(final long p0, final long[] p1, final int p2, final int p3);
    
    final native void putFloatArray(final long p0, final float[] p1, final int p2, final int p3);
    
    final native void getFloatArray(final long p0, final float[] p1, final int p2, final int p3);
    
    final native void putDoubleArray(final long p0, final double[] p1, final int p2, final int p3);
    
    final native void getDoubleArray(final long p0, final double[] p1, final int p2, final int p3);
    
    final native long memchr(final long p0, final int p1, final long p2);
    
    final native void memmove(final long p0, final long p1, final long p2);
    
    final native void memcpy(final long p0, final long p1, final long p2);
    
    final native long strlen(final long p0);
    
    final native byte[] getZeroTerminatedByteArray(final long p0);
    
    final native byte[] getZeroTerminatedByteArray(final long p0, final int p1);
    
    final native void putZeroTerminatedByteArray(final long p0, final byte[] p1, final int p2, final int p3);
    
    final native ByteBuffer newDirectByteBuffer(final long p0, final int p1);
    
    final native long getDirectBufferAddress(final Buffer p0);
    
    final native long newNativeMethod(final String p0, final String p1, final long p2);
    
    final native void freeNativeMethod(final long p0);
    
    final native long compileNativeMethods(final long[] p0);
    
    final native void freeCompiledMethods(final long p0);
    
    final native boolean registerNativeMethods(final Class p0, final long p1);
    
    final native void unregisterNativeMethods(final Class p0);
    
    final native long getSaveErrnoFunction();
    
    final native int getJNIVersion();
    
    final native long getJavaVM();
    
    final native void fatalError(final String p0);
    
    final native Class defineClass(final String p0, final Object p1, final byte[] p2, final int p3, final int p4);
    
    final native Class defineClass(final String p0, final Object p1, final ByteBuffer p2);
    
    final native Object allocObject(final Class p0);
    
    final native int registerNatives(final Class p0, final long p1, final int p2);
    
    final native int unregisterNatives(final Class p0);
    
    static {
        VERSION_MAJOR = getVersionField("MAJOR");
        VERSION_MINOR = getVersionField("MINOR");
        VERSION_MICRO = getVersionField("MICRO");
    }
    
    private abstract static class InstanceHolder
    {
        static final InstanceHolder INSTANCE;
        
        private static final InstanceHolder getInstanceHolder() {
            try {
                Init.load();
                final Foreign foreign = new Foreign(null);
                if ((foreign.getVersion() & 0xFFFF00) != (Foreign.VERSION_MAJOR << 16 | Foreign.VERSION_MINOR << 8)) {
                    return new InValidInstanceHolder(new UnsatisfiedLinkError("Incorrect native library version"));
                }
                foreign.init();
                return new ValidInstanceHolder(foreign);
            }
            catch (UnsatisfiedLinkError ex) {
                return new InValidInstanceHolder(ex);
            }
        }
        
        abstract Foreign getForeign();
        
        static {
            INSTANCE = getInstanceHolder();
        }
    }
    
    private static final class ValidInstanceHolder extends InstanceHolder
    {
        final Foreign foreign;
        
        public ValidInstanceHolder(final Foreign foreign) {
            this.foreign = foreign;
        }
        
        final Foreign getForeign() {
            return this.foreign;
        }
    }
    
    private static final class InValidInstanceHolder extends InstanceHolder
    {
        private final Error cause;
        
        public InValidInstanceHolder(final Error cause) {
            this.cause = cause;
        }
        
        final Foreign getForeign() {
            throw new RuntimeException(this.cause);
        }
    }
}
