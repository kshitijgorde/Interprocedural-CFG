// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

import java.util.List;

public abstract class Type
{
    public static final Type VOID;
    public static final Type FLOAT;
    public static final Type DOUBLE;
    public static final Type LONGDOUBLE;
    public static final Type UINT8;
    public static final Type SINT8;
    public static final Type UINT16;
    public static final Type SINT16;
    public static final Type UINT32;
    public static final Type SINT32;
    public static final Type UINT64;
    public static final Type SINT64;
    public static final Type POINTER;
    public static final Type UCHAR;
    public static final Type SCHAR;
    public static final Type USHORT;
    public static final Type SSHORT;
    public static final Type UINT;
    public static final Type SINT;
    public static final Type ULONG;
    public static final Type SLONG;
    public static final Type ULONG_LONG;
    public static final Type SLONG_LONG;
    
    public abstract int type();
    
    abstract long handle();
    
    public abstract int size();
    
    public abstract int alignment();
    
    public boolean equals(final Object obj) {
        return obj instanceof Type && ((Type)obj).handle() == this.handle();
    }
    
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int)(this.handle() ^ this.handle() >>> 32);
        return hash;
    }
    
    static final long[] nativeHandles(final Type[] types) {
        final long[] nativeTypes = new long[types.length];
        for (int i = 0; i < types.length; ++i) {
            nativeTypes[i] = types[i].handle();
        }
        return nativeTypes;
    }
    
    static final long[] nativeHandles(final List<Type> types) {
        final long[] nativeTypes = new long[types.size()];
        for (int i = 0; i < nativeTypes.length; ++i) {
            nativeTypes[i] = types.get(i).handle();
        }
        return nativeTypes;
    }
    
    private static final Type builtin(final NativeType nativeType) {
        return new Builtin(nativeType);
    }
    
    static {
        VOID = builtin(NativeType.VOID);
        FLOAT = builtin(NativeType.FLOAT);
        DOUBLE = builtin(NativeType.DOUBLE);
        LONGDOUBLE = builtin(NativeType.LONGDOUBLE);
        UINT8 = builtin(NativeType.UINT8);
        SINT8 = builtin(NativeType.SINT8);
        UINT16 = builtin(NativeType.UINT16);
        SINT16 = builtin(NativeType.SINT16);
        UINT32 = builtin(NativeType.UINT32);
        SINT32 = builtin(NativeType.SINT32);
        UINT64 = builtin(NativeType.UINT64);
        SINT64 = builtin(NativeType.SINT64);
        POINTER = builtin(NativeType.POINTER);
        UCHAR = Type.UINT8;
        SCHAR = Type.SINT8;
        USHORT = Type.UINT16;
        SSHORT = Type.SINT16;
        UINT = Type.UINT32;
        SINT = Type.SINT32;
        ULONG = builtin(NativeType.ULONG);
        SLONG = builtin(NativeType.SLONG);
        ULONG_LONG = Type.UINT64;
        SLONG_LONG = Type.SINT64;
    }
    
    static final class Builtin extends Type
    {
        private final NativeType nativeType;
        
        private Builtin(final NativeType nativeType) {
            this.nativeType = nativeType;
        }
        
        public final int type() {
            return BuiltinTypeInfo.find(this.nativeType).type;
        }
        
        public final long handle() {
            return BuiltinTypeInfo.find(this.nativeType).handle;
        }
        
        public final int size() {
            return BuiltinTypeInfo.find(this.nativeType).size;
        }
        
        public final int alignment() {
            return BuiltinTypeInfo.find(this.nativeType).alignment;
        }
    }
    
    private static final class BuiltinTypeInfo
    {
        public static final BuiltinTypeInfo[] typeMap;
        final int type;
        final int size;
        final int alignment;
        final long handle;
        
        static final BuiltinTypeInfo find(final NativeType t) {
            return BuiltinTypeInfo.typeMap[t.ordinal()];
        }
        
        private BuiltinTypeInfo(final long handle) {
            if (handle == 0L) {
                throw new NullPointerException("null ffi_type handle");
            }
            this.handle = handle;
            this.type = Foreign.getInstance().getTypeType(handle);
            this.size = Foreign.getInstance().getTypeSize(handle);
            this.alignment = Foreign.getInstance().getTypeAlign(handle);
        }
        
        static {
            final NativeType[] nativeTypes = NativeType.values();
            typeMap = new BuiltinTypeInfo[nativeTypes.length];
            for (int i = 0; i < BuiltinTypeInfo.typeMap.length; ++i) {
                final long h = Foreign.getInstance().lookupBuiltinType(nativeTypes[i].ffiType);
                if (h == 0L) {
                    throw new RuntimeException("invalid native type " + nativeTypes[i]);
                }
                BuiltinTypeInfo.typeMap[i] = new BuiltinTypeInfo(h);
            }
        }
    }
}
