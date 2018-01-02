// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.jna;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

public class Function extends Pointer
{
    public static final int MAX_NARGS = 256;
    public static final int C_CONVENTION = 0;
    public static final int ALT_CONVENTION = 1;
    private static final int MASK_CC = 3;
    public static final int THROW_LAST_ERROR = 4;
    static final Integer INTEGER_TRUE;
    static final Integer INTEGER_FALSE;
    private NativeLibrary library;
    private final String functionName;
    int callFlags;
    final Map options;
    static final String OPTION_INVOKING_METHOD = "invoking-method";
    static /* synthetic */ Class class$com$sun$jna$NativeMapped;
    static /* synthetic */ Class array$Lcom$sun$jna$Structure$ByReference;
    static /* synthetic */ Class array$Lcom$sun$jna$Structure;
    static /* synthetic */ Class class$java$lang$Void;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$com$sun$jna$WString;
    static /* synthetic */ Class class$com$sun$jna$Pointer;
    static /* synthetic */ Class class$com$sun$jna$Structure;
    static /* synthetic */ Class class$com$sun$jna$Structure$ByValue;
    static /* synthetic */ Class class$com$sun$jna$Callback;
    static /* synthetic */ Class array$Ljava$lang$String;
    static /* synthetic */ Class array$Lcom$sun$jna$WString;
    static /* synthetic */ Class array$Lcom$sun$jna$Pointer;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class array$Lcom$sun$jna$NativeMapped;
    static /* synthetic */ Class class$com$sun$jna$Structure$ByReference;
    
    public static Function getFunction(final String libraryName, final String functionName) {
        return NativeLibrary.getInstance(libraryName).getFunction(functionName);
    }
    
    public static Function getFunction(final String libraryName, final String functionName, final int callFlags) {
        return NativeLibrary.getInstance(libraryName).getFunction(functionName, callFlags);
    }
    
    public static Function getFunction(final Pointer p) {
        return getFunction(p, 0);
    }
    
    public static Function getFunction(final Pointer p, final int callFlags) {
        return new Function(p, callFlags);
    }
    
    Function(final NativeLibrary library, final String functionName, final int callFlags) {
        this.checkCallingConvention(callFlags & 0x3);
        if (functionName == null) {
            throw new NullPointerException("Function name must not be null");
        }
        this.library = library;
        this.functionName = functionName;
        this.callFlags = callFlags;
        this.options = library.options;
        try {
            this.peer = library.getSymbolAddress(functionName);
        }
        catch (UnsatisfiedLinkError e) {
            throw new UnsatisfiedLinkError("Error looking up function '" + functionName + "': " + e.getMessage());
        }
    }
    
    Function(final Pointer functionAddress, final int callFlags) {
        this.checkCallingConvention(callFlags & 0x3);
        if (functionAddress == null || functionAddress.peer == 0L) {
            throw new NullPointerException("Function address may not be null");
        }
        this.functionName = functionAddress.toString();
        this.callFlags = callFlags;
        this.peer = functionAddress.peer;
        this.options = Collections.EMPTY_MAP;
    }
    
    private void checkCallingConvention(final int convention) throws IllegalArgumentException {
        switch (convention) {
            case 0:
            case 1: {}
            default: {
                throw new IllegalArgumentException("Unrecognized calling convention: " + convention);
            }
        }
    }
    
    public String getName() {
        return this.functionName;
    }
    
    public int getCallingConvention() {
        return this.callFlags & 0x3;
    }
    
    public Object invoke(final Class returnType, final Object[] inArgs) {
        return this.invoke(returnType, inArgs, this.options);
    }
    
    public Object invoke(final Class returnType, final Object[] inArgs, final Map options) {
        Object[] args = new Object[0];
        if (inArgs != null) {
            if (inArgs.length > 256) {
                throw new UnsupportedOperationException("Maximum argument count is 256");
            }
            args = new Object[inArgs.length];
            System.arraycopy(inArgs, 0, args, 0, args.length);
        }
        final TypeMapper mapper = options.get("type-mapper");
        final Method invokingMethod = (Method)options.get("invoking-method");
        final boolean allowObjects = Boolean.TRUE.equals(options.get("allow-objects"));
        for (int i = 0; i < args.length; ++i) {
            args[i] = this.convertArgument(args, i, invokingMethod, mapper, allowObjects);
        }
        Class nativeType = returnType;
        FromNativeConverter resultConverter = null;
        if (((Function.class$com$sun$jna$NativeMapped == null) ? (Function.class$com$sun$jna$NativeMapped = class$("com.sun.jna.NativeMapped")) : Function.class$com$sun$jna$NativeMapped).isAssignableFrom(returnType)) {
            final NativeMappedConverter tc = (NativeMappedConverter)(resultConverter = NativeMappedConverter.getInstance(returnType));
            nativeType = tc.nativeType();
        }
        else if (mapper != null) {
            resultConverter = mapper.getFromNativeConverter(returnType);
            if (resultConverter != null) {
                nativeType = resultConverter.nativeType();
            }
        }
        Object result = this.invoke(args, nativeType, allowObjects);
        if (resultConverter != null) {
            FromNativeContext context;
            if (invokingMethod != null) {
                context = new MethodResultContext(returnType, this, inArgs, invokingMethod);
            }
            else {
                context = new FunctionResultContext(returnType, this, inArgs);
            }
            result = resultConverter.fromNative(result, context);
        }
        if (inArgs != null) {
            for (int j = 0; j < inArgs.length; ++j) {
                final Object inArg = inArgs[j];
                if (inArg != null) {
                    if (inArg instanceof Structure) {
                        if (!(inArg instanceof Structure.ByValue)) {
                            ((Structure)inArg).autoRead();
                        }
                    }
                    else if (args[j] instanceof PostCallRead) {
                        ((PostCallRead)args[j]).read();
                        if (args[j] instanceof PointerArray) {
                            final PointerArray array = (PointerArray)args[j];
                            if (((Function.array$Lcom$sun$jna$Structure$ByReference == null) ? (Function.array$Lcom$sun$jna$Structure$ByReference = class$("[Lcom.sun.jna.Structure$ByReference;")) : Function.array$Lcom$sun$jna$Structure$ByReference).isAssignableFrom(inArg.getClass())) {
                                final Class type = inArg.getClass().getComponentType();
                                final Structure[] ss = (Structure[])inArg;
                                for (int si = 0; si < ss.length; ++si) {
                                    final Pointer p = array.getPointer(Pointer.SIZE * si);
                                    ss[si] = Structure.updateStructureByReference(type, ss[si], p);
                                }
                            }
                        }
                    }
                    else if (((Function.array$Lcom$sun$jna$Structure == null) ? (Function.array$Lcom$sun$jna$Structure = class$("[Lcom.sun.jna.Structure;")) : Function.array$Lcom$sun$jna$Structure).isAssignableFrom(inArg.getClass())) {
                        Structure.autoRead((Structure[])inArg);
                    }
                }
            }
        }
        return result;
    }
    
    Object invoke(final Object[] args, final Class returnType, final boolean allowObjects) {
        Object result = null;
        if (returnType == null || returnType == Void.TYPE || returnType == ((Function.class$java$lang$Void == null) ? (Function.class$java$lang$Void = class$("java.lang.Void")) : Function.class$java$lang$Void)) {
            this.invokeVoid(this.callFlags, args);
            result = null;
        }
        else if (returnType == Boolean.TYPE || returnType == ((Function.class$java$lang$Boolean == null) ? (Function.class$java$lang$Boolean = class$("java.lang.Boolean")) : Function.class$java$lang$Boolean)) {
            result = valueOf(this.invokeInt(this.callFlags, args) != 0);
        }
        else if (returnType == Byte.TYPE || returnType == ((Function.class$java$lang$Byte == null) ? (Function.class$java$lang$Byte = class$("java.lang.Byte")) : Function.class$java$lang$Byte)) {
            result = new Byte((byte)this.invokeInt(this.callFlags, args));
        }
        else if (returnType == Short.TYPE || returnType == ((Function.class$java$lang$Short == null) ? (Function.class$java$lang$Short = class$("java.lang.Short")) : Function.class$java$lang$Short)) {
            result = new Short((short)this.invokeInt(this.callFlags, args));
        }
        else if (returnType == Character.TYPE || returnType == ((Function.class$java$lang$Character == null) ? (Function.class$java$lang$Character = class$("java.lang.Character")) : Function.class$java$lang$Character)) {
            result = new Character((char)this.invokeInt(this.callFlags, args));
        }
        else if (returnType == Integer.TYPE || returnType == ((Function.class$java$lang$Integer == null) ? (Function.class$java$lang$Integer = class$("java.lang.Integer")) : Function.class$java$lang$Integer)) {
            result = new Integer(this.invokeInt(this.callFlags, args));
        }
        else if (returnType == Long.TYPE || returnType == ((Function.class$java$lang$Long == null) ? (Function.class$java$lang$Long = class$("java.lang.Long")) : Function.class$java$lang$Long)) {
            result = new Long(this.invokeLong(this.callFlags, args));
        }
        else if (returnType == Float.TYPE || returnType == ((Function.class$java$lang$Float == null) ? (Function.class$java$lang$Float = class$("java.lang.Float")) : Function.class$java$lang$Float)) {
            result = new Float(this.invokeFloat(this.callFlags, args));
        }
        else if (returnType == Double.TYPE || returnType == ((Function.class$java$lang$Double == null) ? (Function.class$java$lang$Double = class$("java.lang.Double")) : Function.class$java$lang$Double)) {
            result = new Double(this.invokeDouble(this.callFlags, args));
        }
        else if (returnType == ((Function.class$java$lang$String == null) ? (Function.class$java$lang$String = class$("java.lang.String")) : Function.class$java$lang$String)) {
            result = this.invokeString(this.callFlags, args, false);
        }
        else if (returnType == ((Function.class$com$sun$jna$WString == null) ? (Function.class$com$sun$jna$WString = class$("com.sun.jna.WString")) : Function.class$com$sun$jna$WString)) {
            final String s = this.invokeString(this.callFlags, args, true);
            if (s != null) {
                result = new WString(s);
            }
        }
        else if (((Function.class$com$sun$jna$Pointer == null) ? (Function.class$com$sun$jna$Pointer = class$("com.sun.jna.Pointer")) : Function.class$com$sun$jna$Pointer).isAssignableFrom(returnType)) {
            result = this.invokePointer(this.callFlags, args);
        }
        else if (((Function.class$com$sun$jna$Structure == null) ? (Function.class$com$sun$jna$Structure = class$("com.sun.jna.Structure")) : Function.class$com$sun$jna$Structure).isAssignableFrom(returnType)) {
            if (((Function.class$com$sun$jna$Structure$ByValue == null) ? (Function.class$com$sun$jna$Structure$ByValue = class$("com.sun.jna.Structure$ByValue")) : Function.class$com$sun$jna$Structure$ByValue).isAssignableFrom(returnType)) {
                final Structure s2 = this.invokeStructure(this.callFlags, args, Structure.newInstance(returnType));
                s2.autoRead();
                result = s2;
            }
            else {
                result = this.invokePointer(this.callFlags, args);
                if (result != null) {
                    final Structure s2 = Structure.newInstance(returnType);
                    s2.useMemory((Pointer)result);
                    s2.autoRead();
                    result = s2;
                }
            }
        }
        else if (((Function.class$com$sun$jna$Callback == null) ? (Function.class$com$sun$jna$Callback = class$("com.sun.jna.Callback")) : Function.class$com$sun$jna$Callback).isAssignableFrom(returnType)) {
            result = this.invokePointer(this.callFlags, args);
            if (result != null) {
                result = CallbackReference.getCallback(returnType, (Pointer)result);
            }
        }
        else if (returnType == ((Function.array$Ljava$lang$String == null) ? (Function.array$Ljava$lang$String = class$("[Ljava.lang.String;")) : Function.array$Ljava$lang$String)) {
            final Pointer p = this.invokePointer(this.callFlags, args);
            if (p != null) {
                result = p.getStringArray(0L);
            }
        }
        else if (returnType == ((Function.array$Lcom$sun$jna$WString == null) ? (Function.array$Lcom$sun$jna$WString = class$("[Lcom.sun.jna.WString;")) : Function.array$Lcom$sun$jna$WString)) {
            final Pointer p = this.invokePointer(this.callFlags, args);
            if (p != null) {
                final String[] arr = p.getStringArray(0L, true);
                final WString[] warr = new WString[arr.length];
                for (int i = 0; i < arr.length; ++i) {
                    warr[i] = new WString(arr[i]);
                }
                result = warr;
            }
        }
        else if (returnType == ((Function.array$Lcom$sun$jna$Pointer == null) ? (Function.array$Lcom$sun$jna$Pointer = class$("[Lcom.sun.jna.Pointer;")) : Function.array$Lcom$sun$jna$Pointer)) {
            final Pointer p = this.invokePointer(this.callFlags, args);
            if (p != null) {
                result = p.getPointerArray(0L);
            }
        }
        else {
            if (!allowObjects) {
                throw new IllegalArgumentException("Unsupported return type " + returnType + " in function " + this.getName());
            }
            result = this.invokeObject(this.callFlags, args);
            if (result != null && !returnType.isAssignableFrom(result.getClass())) {
                throw new ClassCastException("Return type " + returnType + " does not match result " + result.getClass());
            }
        }
        return result;
    }
    
    private Object convertArgument(final Object[] args, final int index, final Method invokingMethod, final TypeMapper mapper, final boolean allowObjects) {
        Object arg = args[index];
        if (arg != null) {
            final Class type = arg.getClass();
            ToNativeConverter converter = null;
            if (((Function.class$com$sun$jna$NativeMapped == null) ? (Function.class$com$sun$jna$NativeMapped = class$("com.sun.jna.NativeMapped")) : Function.class$com$sun$jna$NativeMapped).isAssignableFrom(type)) {
                converter = NativeMappedConverter.getInstance(type);
            }
            else if (mapper != null) {
                converter = mapper.getToNativeConverter(type);
            }
            if (converter != null) {
                ToNativeContext context;
                if (invokingMethod != null) {
                    context = new MethodParameterContext(this, args, index, invokingMethod);
                }
                else {
                    context = new FunctionParameterContext(this, args, index);
                }
                arg = converter.toNative(arg, context);
            }
        }
        if (arg == null || this.isPrimitiveArray(arg.getClass())) {
            return arg;
        }
        final Class argClass = arg.getClass();
        if (arg instanceof Structure) {
            final Structure struct = (Structure)arg;
            struct.autoWrite();
            if (struct instanceof Structure.ByValue) {
                Class ptype = struct.getClass();
                if (invokingMethod != null) {
                    final Class[] ptypes = invokingMethod.getParameterTypes();
                    if (isVarArgs(invokingMethod)) {
                        if (index < ptypes.length - 1) {
                            ptype = ptypes[index];
                        }
                        else {
                            final Class etype = ptypes[ptypes.length - 1].getComponentType();
                            if (etype != ((Function.class$java$lang$Object == null) ? (Function.class$java$lang$Object = class$("java.lang.Object")) : Function.class$java$lang$Object)) {
                                ptype = etype;
                            }
                        }
                    }
                    else {
                        ptype = ptypes[index];
                    }
                }
                if (((Function.class$com$sun$jna$Structure$ByValue == null) ? (Function.class$com$sun$jna$Structure$ByValue = class$("com.sun.jna.Structure$ByValue")) : Function.class$com$sun$jna$Structure$ByValue).isAssignableFrom(ptype)) {
                    return struct;
                }
            }
            return struct.getPointer();
        }
        if (arg instanceof Callback) {
            return CallbackReference.getFunctionPointer((Callback)arg);
        }
        if (arg instanceof String) {
            return new NativeString((String)arg, false).getPointer();
        }
        if (arg instanceof WString) {
            return new NativeString(arg.toString(), true).getPointer();
        }
        if (arg instanceof Boolean) {
            return Boolean.TRUE.equals(arg) ? Function.INTEGER_TRUE : Function.INTEGER_FALSE;
        }
        if (((Function.array$Ljava$lang$String == null) ? (Function.array$Ljava$lang$String = class$("[Ljava.lang.String;")) : Function.array$Ljava$lang$String) == argClass) {
            return new StringArray((String[])arg);
        }
        if (((Function.array$Lcom$sun$jna$WString == null) ? (Function.array$Lcom$sun$jna$WString = class$("[Lcom.sun.jna.WString;")) : Function.array$Lcom$sun$jna$WString) == argClass) {
            return new StringArray((WString[])arg);
        }
        if (((Function.array$Lcom$sun$jna$Pointer == null) ? (Function.array$Lcom$sun$jna$Pointer = class$("[Lcom.sun.jna.Pointer;")) : Function.array$Lcom$sun$jna$Pointer) == argClass) {
            return new PointerArray((Pointer[])arg);
        }
        if (((Function.array$Lcom$sun$jna$NativeMapped == null) ? (Function.array$Lcom$sun$jna$NativeMapped = class$("[Lcom.sun.jna.NativeMapped;")) : Function.array$Lcom$sun$jna$NativeMapped).isAssignableFrom(argClass)) {
            return new NativeMappedArray((NativeMapped[])arg);
        }
        if (((Function.array$Lcom$sun$jna$Structure == null) ? (Function.array$Lcom$sun$jna$Structure = class$("[Lcom.sun.jna.Structure;")) : Function.array$Lcom$sun$jna$Structure).isAssignableFrom(argClass)) {
            final Structure[] ss = (Structure[])arg;
            final Class type2 = argClass.getComponentType();
            final boolean byRef = ((Function.class$com$sun$jna$Structure$ByReference == null) ? (Function.class$com$sun$jna$Structure$ByReference = class$("com.sun.jna.Structure$ByReference")) : Function.class$com$sun$jna$Structure$ByReference).isAssignableFrom(type2);
            if (byRef) {
                final Pointer[] pointers = new Pointer[ss.length + 1];
                for (int i = 0; i < ss.length; ++i) {
                    pointers[i] = ((ss[i] != null) ? ss[i].getPointer() : null);
                }
                return new PointerArray(pointers);
            }
            if (ss.length == 0) {
                throw new IllegalArgumentException("Structure array must have non-zero length");
            }
            if (ss[0] == null) {
                Structure.newInstance(type2).toArray(ss);
                return ss[0].getPointer();
            }
            Structure.autoWrite(ss);
            return ss[0].getPointer();
        }
        else {
            if (argClass.isArray()) {
                throw new IllegalArgumentException("Unsupported array argument type: " + argClass.getComponentType());
            }
            if (allowObjects) {
                return arg;
            }
            if (!Native.isSupportedNativeType(arg.getClass())) {
                throw new IllegalArgumentException("Unsupported argument type " + arg.getClass().getName() + " at parameter " + index + " of function " + this.getName());
            }
            return arg;
        }
    }
    
    private boolean isPrimitiveArray(final Class argClass) {
        return argClass.isArray() && argClass.getComponentType().isPrimitive();
    }
    
    private native int invokeInt(final int p0, final Object[] p1);
    
    private native long invokeLong(final int p0, final Object[] p1);
    
    public void invoke(final Object[] args) {
        this.invoke((Function.class$java$lang$Void == null) ? (Function.class$java$lang$Void = class$("java.lang.Void")) : Function.class$java$lang$Void, args);
    }
    
    private native void invokeVoid(final int p0, final Object[] p1);
    
    private native float invokeFloat(final int p0, final Object[] p1);
    
    private native double invokeDouble(final int p0, final Object[] p1);
    
    private String invokeString(final int callFlags, final Object[] args, final boolean wide) {
        final Pointer ptr = this.invokePointer(callFlags, args);
        String s = null;
        if (ptr != null) {
            if (wide) {
                s = ptr.getString(0L, wide);
            }
            else {
                s = ptr.getString(0L);
            }
        }
        return s;
    }
    
    private native Pointer invokePointer(final int p0, final Object[] p1);
    
    private native Structure invokeStructure(final int p0, final Object[] p1, final Structure p2);
    
    private native Object invokeObject(final int p0, final Object[] p1);
    
    public String toString() {
        if (this.library != null) {
            return "native function " + this.functionName + "(" + this.library.getName() + ")@0x" + Long.toHexString(this.peer);
        }
        return "native function@0x" + Long.toHexString(this.peer);
    }
    
    public Object invokeObject(final Object[] args) {
        return this.invoke((Function.class$java$lang$Object == null) ? (Function.class$java$lang$Object = class$("java.lang.Object")) : Function.class$java$lang$Object, args);
    }
    
    public Pointer invokePointer(final Object[] args) {
        return (Pointer)this.invoke((Function.class$com$sun$jna$Pointer == null) ? (Function.class$com$sun$jna$Pointer = class$("com.sun.jna.Pointer")) : Function.class$com$sun$jna$Pointer, args);
    }
    
    public String invokeString(final Object[] args, final boolean wide) {
        final Object o = this.invoke(wide ? ((Function.class$com$sun$jna$WString == null) ? (Function.class$com$sun$jna$WString = class$("com.sun.jna.WString")) : Function.class$com$sun$jna$WString) : ((Function.class$java$lang$String == null) ? (Function.class$java$lang$String = class$("java.lang.String")) : Function.class$java$lang$String), args);
        return (o != null) ? o.toString() : null;
    }
    
    public int invokeInt(final Object[] args) {
        return (int)this.invoke((Function.class$java$lang$Integer == null) ? (Function.class$java$lang$Integer = class$("java.lang.Integer")) : Function.class$java$lang$Integer, args);
    }
    
    public long invokeLong(final Object[] args) {
        return (long)this.invoke((Function.class$java$lang$Long == null) ? (Function.class$java$lang$Long = class$("java.lang.Long")) : Function.class$java$lang$Long, args);
    }
    
    public float invokeFloat(final Object[] args) {
        return (float)this.invoke((Function.class$java$lang$Float == null) ? (Function.class$java$lang$Float = class$("java.lang.Float")) : Function.class$java$lang$Float, args);
    }
    
    public double invokeDouble(final Object[] args) {
        return (double)this.invoke((Function.class$java$lang$Double == null) ? (Function.class$java$lang$Double = class$("java.lang.Double")) : Function.class$java$lang$Double, args);
    }
    
    public void invokeVoid(final Object[] args) {
        this.invoke((Function.class$java$lang$Void == null) ? (Function.class$java$lang$Void = class$("java.lang.Void")) : Function.class$java$lang$Void, args);
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() == this.getClass()) {
            final Function other = (Function)o;
            return other.callFlags == this.callFlags && other.options.equals(this.options) && other.peer == this.peer;
        }
        return false;
    }
    
    static Object[] concatenateVarArgs(Object[] inArgs) {
        if (inArgs != null && inArgs.length > 0) {
            final Object lastArg = inArgs[inArgs.length - 1];
            final Class argType = (lastArg != null) ? lastArg.getClass() : null;
            if (argType != null && argType.isArray()) {
                final Object[] varArgs = (Object[])lastArg;
                final Object[] fullArgs = new Object[inArgs.length + varArgs.length];
                System.arraycopy(inArgs, 0, fullArgs, 0, inArgs.length - 1);
                System.arraycopy(varArgs, 0, fullArgs, inArgs.length - 1, varArgs.length);
                fullArgs[fullArgs.length - 1] = null;
                inArgs = fullArgs;
            }
        }
        return inArgs;
    }
    
    static boolean isVarArgs(final Method m) {
        try {
            final Method v = m.getClass().getMethod("isVarArgs", (Class<?>[])new Class[0]);
            return Boolean.TRUE.equals(v.invoke(m, new Object[0]));
        }
        catch (SecurityException e) {}
        catch (NoSuchMethodException e2) {}
        catch (IllegalArgumentException e3) {}
        catch (IllegalAccessException e4) {}
        catch (InvocationTargetException ex) {}
        return false;
    }
    
    static Boolean valueOf(final boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError().initCause(x);
        }
    }
    
    static {
        INTEGER_TRUE = new Integer(-1);
        INTEGER_FALSE = new Integer(0);
    }
    
    private static class NativeMappedArray extends Memory implements PostCallRead
    {
        private final NativeMapped[] original;
        
        public NativeMappedArray(final NativeMapped[] arg) {
            super(Native.getNativeSize(arg.getClass(), arg));
            this.original = arg;
            final Class nativeType = arg.getClass().getComponentType();
            this.setValue(0L, this.original, this.original.getClass());
        }
        
        public void read() {
            this.getValue(0L, this.original.getClass(), this.original);
        }
    }
    
    private static class PointerArray extends Memory implements PostCallRead
    {
        private final Pointer[] original;
        
        public PointerArray(final Pointer[] arg) {
            super(Pointer.SIZE * (arg.length + 1));
            this.original = arg;
            for (int i = 0; i < arg.length; ++i) {
                this.setPointer(i * Pointer.SIZE, arg[i]);
            }
            this.setPointer(Pointer.SIZE * arg.length, null);
        }
        
        public void read() {
            this.read(0L, this.original, 0, this.original.length);
        }
    }
    
    public interface PostCallRead
    {
        void read();
    }
}
