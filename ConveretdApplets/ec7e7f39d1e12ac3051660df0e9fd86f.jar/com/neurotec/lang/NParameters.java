// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

import com.neurotec.jna.NLibrary;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.WString;
import com.neurotec.jna.ptr.NativeSize;
import com.sun.jna.Pointer;
import com.neurotec.jna.ptr.HNObject;
import com.sun.jna.Native;
import com.neurotec.jna.ptr.CharByReference;
import com.neurotec.jna.ptr.BooleanByReference;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.LongByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ShortByReference;
import com.sun.jna.ptr.ByReference;
import com.sun.jna.ptr.ByteByReference;

class NParameters
{
    private static final int TYPE_BYTE = 1;
    private static final int TYPE_SBYTE = 2;
    private static final int TYPE_USHORT = 3;
    private static final int TYPE_SHORT = 4;
    private static final int TYPE_UINT = 5;
    private static final int TYPE_INT = 6;
    private static final int TYPE_ULONG = 7;
    private static final int TYPE_LONG = 8;
    private static final int TYPE_BOOL = 10;
    private static final int TYPE_CHAR = 20;
    private static final int TYPE_FLOAT = 30;
    private static final int TYPE_DOUBLE = 31;
    private static final int TYPE_STRING = 100;
    private static final int PC_TYPE_ID = 1;
    static final NParametersLibrary LIBRARY;
    
    private static int makeId(final byte code, final byte index, final short id) {
        return code << 24 | index << 16 | id;
    }
    
    private static NativeClass getType(final int typeId) {
        switch (typeId) {
            case 1: {
                return new NativeClass(Byte.class, true);
            }
            case 2: {
                return new NativeClass(Byte.class, false);
            }
            case 3: {
                return new NativeClass(Short.class, true);
            }
            case 4: {
                return new NativeClass(Short.class, false);
            }
            case 5: {
                return new NativeClass(Integer.class, true);
            }
            case 6: {
                return new NativeClass(Integer.class, false);
            }
            case 7: {
                return new NativeClass(Long.class, true);
            }
            case 8: {
                return new NativeClass(Long.class, false);
            }
            case 30: {
                return new NativeClass(Float.class, false);
            }
            case 31: {
                return new NativeClass(Double.class, false);
            }
            case 10: {
                return new NativeClass(Boolean.class, false);
            }
            case 20: {
                return new NativeClass(Character.class, false);
            }
            case 100: {
                return new NativeClass(String.class, false);
            }
            default: {
                throw new UnsupportedOperationException();
            }
        }
    }
    
    private static int getTypeId(final NativeClass type) {
        if (type.getType().equals(Byte.class)) {
            return type.isUnsigned() ? 1 : 2;
        }
        if (type.getType().equals(Short.class)) {
            return type.isUnsigned() ? 3 : 4;
        }
        if (type.getType().equals(Integer.class)) {
            return type.isUnsigned() ? 5 : 6;
        }
        if (type.getType().equals(Long.class)) {
            return type.isUnsigned() ? 7 : 8;
        }
        if (type.getType().equals(Float.class)) {
            return 30;
        }
        if (type.getType().equals(Double.class)) {
            return 31;
        }
        if (type.getType().equals(Boolean.class)) {
            return 10;
        }
        if (type.getType().equals(Character.class)) {
            return 20;
        }
        if (type.getType().equals(String.class)) {
            return 100;
        }
        throw new UnsupportedOperationException("Specified parameter value type is not supported");
    }
    
    private static NativeObject getNativeObject(final Class type, final Object obj) {
        if (type.equals(Byte.class)) {
            return new NativeObject((obj == null) ? new ByteByReference() : new ByteByReference((byte)obj), 1);
        }
        if (type.equals(Short.class)) {
            return new NativeObject((obj == null) ? new ShortByReference() : new ShortByReference((short)obj), 2);
        }
        if (type.equals(Integer.class)) {
            return new NativeObject((obj == null) ? new IntByReference() : new IntByReference((int)obj), 4);
        }
        if (type.equals(Long.class)) {
            return new NativeObject((obj == null) ? new LongByReference() : new LongByReference((long)obj), 8);
        }
        if (type.equals(Float.class)) {
            return new NativeObject((obj == null) ? new FloatByReference() : new FloatByReference((float)obj), 4);
        }
        if (type.equals(Double.class)) {
            return new NativeObject((obj == null) ? new DoubleByReference() : new DoubleByReference((double)obj), 8);
        }
        if (type.equals(Boolean.class)) {
            return new NativeObject((obj == null) ? new BooleanByReference() : new BooleanByReference((boolean)obj), 4);
        }
        if (type.equals(Character.class)) {
            return new NativeObject((obj == null) ? new CharByReference() : new CharByReference((char)obj), Native.WCHAR_SIZE);
        }
        throw new UnsupportedOperationException();
    }
    
    private static Object getJavaObject(final Class type, final ByReference obj) {
        if (type.equals(Byte.class)) {
            return ((ByteByReference)obj).getValue();
        }
        if (type.equals(Short.class)) {
            return ((ShortByReference)obj).getValue();
        }
        if (type.equals(Integer.class)) {
            return ((IntByReference)obj).getValue();
        }
        if (type.equals(Long.class)) {
            return ((LongByReference)obj).getValue();
        }
        if (type.equals(Float.class)) {
            return ((FloatByReference)obj).getValue();
        }
        if (type.equals(Double.class)) {
            return ((DoubleByReference)obj).getValue();
        }
        if (type.equals(Boolean.class)) {
            return ((BooleanByReference)obj).getValue();
        }
        if (type.equals(Character.class)) {
            return ((CharByReference)obj).getValue();
        }
        throw new UnsupportedOperationException();
    }
    
    private static NativeClass getType(final NNativeType nativeType, final short partId, final short parameterId) {
        return getType((int)getValueRaw(nativeType, HNObject.NULL, partId, (byte)1, (byte)0, parameterId, Integer.class));
    }
    
    private static Object getValueRaw(final NNativeType nativeType, final HNObject hObject, final short partId, final byte parameterCode, final byte parameterIndex, final short parameterId, final Class parameterClass) {
        NativeClass parameterType;
        if (parameterCode != 0) {
            parameterType = new NativeClass(parameterClass, false);
        }
        else {
            parameterType = getType(nativeType, partId, parameterId);
            if (parameterClass != null) {
                parameterType = new NativeClass(parameterClass, parameterType.isUnsigned());
            }
        }
        final int id = makeId(parameterCode, parameterIndex, parameterId);
        final int typeId = getTypeId(parameterType);
        if (parameterType.equals(String.class)) {
            final int len = NResult.check(NParameters.LIBRARY.NTypeGetParameterEx(nativeType.getHandle(), hObject, partId, id, typeId, Pointer.NULL, null)) / Native.WCHAR_SIZE - 1;
            if (NResult.isFailed(len)) {
                return null;
            }
            final Pointer pValue = NCore.allocString(len);
            try {
                if (NResult.isFailed(NParameters.LIBRARY.NTypeGetParameterEx(nativeType.getHandle(), hObject, partId, id, typeId, pValue, new NativeSize((long)((len + 1) * Native.WCHAR_SIZE))))) {
                    return null;
                }
                return pValue.getString(0L, true);
            }
            finally {
                NCore.free(pValue);
            }
        }
        final NativeObject nObject = getNativeObject(parameterType.getType(), null);
        NResult.check(NParameters.LIBRARY.NTypeGetParameterEx(nativeType.getHandle(), hObject, partId, id, typeId, nObject.getNativeObject(), new NativeSize((long)nObject.getSize())));
        return getJavaObject(parameterType.getType(), nObject.getNativeObject());
    }
    
    private static void setValueRaw(final NNativeType nativeType, final HNObject hObject, final short partId, final byte parameterCode, final byte parameterIndex, final short parameterId, final Class parameterClass, final Object value) {
        NativeClass parameterType;
        if (parameterCode != 0) {
            parameterType = new NativeClass(parameterClass, false);
        }
        else {
            parameterType = getType(nativeType, partId, parameterId);
            if (parameterClass != null) {
                parameterType = new NativeClass(parameterClass, parameterType.isUnsigned());
            }
        }
        if (parameterClass != null) {
            parameterType = new NativeClass(parameterClass, parameterType.isUnsigned());
        }
        else if (value != null) {
            parameterType = new NativeClass(value.getClass(), parameterType.isUnsigned());
        }
        if (!parameterType.getType().equals(String.class) && value == null) {
            throw new NullPointerException("value");
        }
        if (value != null && !parameterType.getType().equals(value.getClass())) {
            throw new NullPointerException("value class is invalid");
        }
        final int id = makeId(parameterCode, parameterIndex, parameterId);
        final int typeId = getTypeId(parameterType);
        if (parameterType.equals(String.class)) {
            NResult.check(NParameters.LIBRARY.NTypeSetParameterEx(nativeType.getHandle(), hObject, partId, id, typeId, new WString((String)value), new NativeSize((long)((((String)value).length() + 1) * Native.WCHAR_SIZE))));
        }
        else {
            final NativeObject nObject = getNativeObject(parameterType.getType(), value);
            NResult.check(NParameters.LIBRARY.NTypeSetParameterEx(nativeType.getHandle(), hObject, partId, id, typeId, nObject.getNativeObject(), new NativeSize((long)nObject.getSize())));
        }
    }
    
    public static Object getValue(final NNativeType nativeType, final NObject obj, final short partId, final int parameterId) {
        return getValue(nativeType, obj, partId, parameterId, null);
    }
    
    public static Object getValue(final NNativeType nativeType, final NObject obj, final short partId, final int parameterId, final Class parameterClass) {
        if (partId < 0) {
            throw new IllegalArgumentException("parameterId value is out of range");
        }
        if (parameterId < 0 || parameterId > 65535) {
            throw new IllegalArgumentException("parameterId value is out of range");
        }
        return getValueRaw(nativeType, (obj == null) ? HNObject.NULL : obj.getHandle(), partId, (byte)0, (byte)0, (short)parameterId, parameterClass);
    }
    
    public static void setValue(final NNativeType nativeType, final NObject obj, final short partId, final int parameterId, final Object value) {
        setValue(nativeType, obj, partId, parameterId, null, value);
    }
    
    public static void setValue(final NNativeType nativeType, final NObject obj, final short partId, final int parameterId, final Class parameterClass, final Object value) {
        if (partId < 0) {
            throw new IllegalArgumentException("parameterId value is out of range");
        }
        if (parameterId < 0 || parameterId > 65535) {
            throw new IllegalArgumentException("parameterId value is out of range");
        }
        setValueRaw(nativeType, (obj == null) ? HNObject.NULL : obj.getHandle(), partId, (byte)0, (byte)0, (short)parameterId, parameterClass, value);
    }
    
    static {
        LIBRARY = (NParametersLibrary)Native.loadLibrary("NCore", NParametersLibrary.class, W32APIOptions.UNICODE_OPTIONS);
    }
    
    interface NParametersLibrary extends NLibrary
    {
        int NTypeGetParameterEx(final NNativeType.NNativeTypeLibrary.HNType p0, final HNObject p1, final short p2, final int p3, final int p4, final ByReference p5, final NativeSize p6);
        
        int NTypeGetParameterEx(final NNativeType.NNativeTypeLibrary.HNType p0, final HNObject p1, final short p2, final int p3, final int p4, final Pointer p5, final NativeSize p6);
        
        int NTypeSetParameterEx(final NNativeType.NNativeTypeLibrary.HNType p0, final HNObject p1, final short p2, final int p3, final int p4, final ByReference p5, final NativeSize p6);
        
        int NTypeSetParameterEx(final NNativeType.NNativeTypeLibrary.HNType p0, final HNObject p1, final short p2, final int p3, final int p4, final WString p5, final NativeSize p6);
    }
}
