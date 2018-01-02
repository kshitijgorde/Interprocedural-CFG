// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.jna;

import java.lang.reflect.InvocationTargetException;
import java.util.WeakHashMap;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Iterator;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.lang.reflect.Method;
import java.util.Map;
import java.lang.ref.WeakReference;

class CallbackReference extends WeakReference
{
    static final Map callbackMap;
    static final Map directCallbackMap;
    static final Map allocations;
    private static final Method PROXY_CALLBACK_METHOD;
    Pointer cbstruct;
    CallbackProxy proxy;
    Method method;
    static /* synthetic */ Class array$Ljava$lang$Object;
    static /* synthetic */ Class class$com$sun$jna$CallbackProxy;
    static /* synthetic */ Class class$com$sun$jna$AltCallingConvention;
    static /* synthetic */ Class class$com$sun$jna$Structure;
    static /* synthetic */ Class class$com$sun$jna$Structure$ByValue;
    static /* synthetic */ Class class$com$sun$jna$Pointer;
    static /* synthetic */ Class class$com$sun$jna$NativeMapped;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$com$sun$jna$WString;
    static /* synthetic */ Class array$Ljava$lang$String;
    static /* synthetic */ Class array$Lcom$sun$jna$WString;
    static /* synthetic */ Class class$com$sun$jna$Callback;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Void;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Double;
    
    public static Callback getCallback(final Class type, final Pointer p) {
        return getCallback(type, p, false);
    }
    
    private static Callback getCallback(final Class type, final Pointer p, final boolean direct) {
        if (p == null) {
            return null;
        }
        if (!type.isInterface()) {
            throw new IllegalArgumentException("Callback type must be an interface");
        }
        final Map map = direct ? CallbackReference.directCallbackMap : CallbackReference.callbackMap;
        synchronized (map) {
            for (final Callback cb : map.keySet()) {
                if (type.isAssignableFrom(cb.getClass())) {
                    final CallbackReference cbref = map.get(cb);
                    final Pointer cbp = (cbref != null) ? cbref.getTrampoline() : getNativeFunctionPointer(cb);
                    if (p.equals(cbp)) {
                        return cb;
                    }
                    continue;
                }
            }
            final int ctype = ((CallbackReference.class$com$sun$jna$AltCallingConvention == null) ? (CallbackReference.class$com$sun$jna$AltCallingConvention = class$("com.sun.jna.AltCallingConvention")) : CallbackReference.class$com$sun$jna$AltCallingConvention).isAssignableFrom(type) ? 1 : 0;
            final Map foptions = new HashMap();
            final Map options = Native.getLibraryOptions(type);
            if (options != null) {
                foptions.putAll(options);
            }
            foptions.put("invoking-method", getCallbackMethod(type));
            final NativeFunctionHandler h = new NativeFunctionHandler(p, ctype, foptions);
            final Callback cb2 = (Callback)Proxy.newProxyInstance(type.getClassLoader(), new Class[] { type }, h);
            map.put(cb2, null);
            return cb2;
        }
    }
    
    private CallbackReference(final Callback callback, final int callingConvention, boolean direct) {
        super(callback);
        final TypeMapper mapper = Native.getTypeMapper(callback.getClass());
        final String arch = System.getProperty("os.arch").toLowerCase();
        final boolean ppc = "ppc".equals(arch) || "powerpc".equals(arch);
        if (direct) {
            final Method m = getCallbackMethod(callback);
            final Class[] ptypes = m.getParameterTypes();
            for (int i = 0; i < ptypes.length; ++i) {
                if (ppc && (ptypes[i] == Float.TYPE || ptypes[i] == Double.TYPE)) {
                    direct = false;
                    break;
                }
                if (mapper != null && mapper.getFromNativeConverter(ptypes[i]) != null) {
                    direct = false;
                    break;
                }
            }
            if (mapper != null && mapper.getToNativeConverter(m.getReturnType()) != null) {
                direct = false;
            }
        }
        if (direct) {
            this.method = getCallbackMethod(callback);
            final Class[] nativeParamTypes = this.method.getParameterTypes();
            final Class returnType = this.method.getReturnType();
            final long peer = Native.createNativeCallback(callback, this.method, nativeParamTypes, returnType, callingConvention, true);
            this.cbstruct = ((peer != 0L) ? new Pointer(peer) : null);
        }
        else {
            if (callback instanceof CallbackProxy) {
                this.proxy = (CallbackProxy)callback;
            }
            else {
                this.proxy = new DefaultCallbackProxy(getCallbackMethod(callback), mapper);
            }
            final Class[] nativeParamTypes = this.proxy.getParameterTypes();
            Class returnType = this.proxy.getReturnType();
            if (mapper != null) {
                for (int j = 0; j < nativeParamTypes.length; ++j) {
                    final FromNativeConverter rc = mapper.getFromNativeConverter(nativeParamTypes[j]);
                    if (rc != null) {
                        nativeParamTypes[j] = rc.nativeType();
                    }
                }
                final ToNativeConverter tn = mapper.getToNativeConverter(returnType);
                if (tn != null) {
                    returnType = tn.nativeType();
                }
            }
            for (int j = 0; j < nativeParamTypes.length; ++j) {
                nativeParamTypes[j] = this.getNativeType(nativeParamTypes[j]);
                if (!isAllowableNativeType(nativeParamTypes[j])) {
                    final String msg = "Callback argument " + nativeParamTypes[j] + " requires custom type conversion";
                    throw new IllegalArgumentException(msg);
                }
            }
            returnType = this.getNativeType(returnType);
            if (!isAllowableNativeType(returnType)) {
                final String msg2 = "Callback return type " + returnType + " requires custom type conversion";
                throw new IllegalArgumentException(msg2);
            }
            final long peer = Native.createNativeCallback(this.proxy, CallbackReference.PROXY_CALLBACK_METHOD, nativeParamTypes, returnType, callingConvention, false);
            this.cbstruct = ((peer != 0L) ? new Pointer(peer) : null);
        }
    }
    
    private Class getNativeType(final Class cls) {
        if (((CallbackReference.class$com$sun$jna$Structure == null) ? (CallbackReference.class$com$sun$jna$Structure = class$("com.sun.jna.Structure")) : CallbackReference.class$com$sun$jna$Structure).isAssignableFrom(cls)) {
            Structure.newInstance(cls);
            if (!((CallbackReference.class$com$sun$jna$Structure$ByValue == null) ? (CallbackReference.class$com$sun$jna$Structure$ByValue = class$("com.sun.jna.Structure$ByValue")) : CallbackReference.class$com$sun$jna$Structure$ByValue).isAssignableFrom(cls)) {
                return (CallbackReference.class$com$sun$jna$Pointer == null) ? (CallbackReference.class$com$sun$jna$Pointer = class$("com.sun.jna.Pointer")) : CallbackReference.class$com$sun$jna$Pointer;
            }
        }
        else {
            if (((CallbackReference.class$com$sun$jna$NativeMapped == null) ? (CallbackReference.class$com$sun$jna$NativeMapped = class$("com.sun.jna.NativeMapped")) : CallbackReference.class$com$sun$jna$NativeMapped).isAssignableFrom(cls)) {
                return NativeMappedConverter.getInstance(cls).nativeType();
            }
            if (cls == ((CallbackReference.class$java$lang$String == null) ? (CallbackReference.class$java$lang$String = class$("java.lang.String")) : CallbackReference.class$java$lang$String) || cls == ((CallbackReference.class$com$sun$jna$WString == null) ? (CallbackReference.class$com$sun$jna$WString = class$("com.sun.jna.WString")) : CallbackReference.class$com$sun$jna$WString) || cls == ((CallbackReference.array$Ljava$lang$String == null) ? (CallbackReference.array$Ljava$lang$String = class$("[Ljava.lang.String;")) : CallbackReference.array$Ljava$lang$String) || cls == ((CallbackReference.array$Lcom$sun$jna$WString == null) ? (CallbackReference.array$Lcom$sun$jna$WString = class$("[Lcom.sun.jna.WString;")) : CallbackReference.array$Lcom$sun$jna$WString) || ((CallbackReference.class$com$sun$jna$Callback == null) ? (CallbackReference.class$com$sun$jna$Callback = class$("com.sun.jna.Callback")) : CallbackReference.class$com$sun$jna$Callback).isAssignableFrom(cls)) {
                return (CallbackReference.class$com$sun$jna$Pointer == null) ? (CallbackReference.class$com$sun$jna$Pointer = class$("com.sun.jna.Pointer")) : CallbackReference.class$com$sun$jna$Pointer;
            }
        }
        return cls;
    }
    
    private static Method checkMethod(final Method m) {
        if (m.getParameterTypes().length > 256) {
            final String msg = "Method signature exceeds the maximum parameter count: " + m;
            throw new UnsupportedOperationException(msg);
        }
        return m;
    }
    
    static Class findCallbackClass(final Class type) {
        if (!((CallbackReference.class$com$sun$jna$Callback == null) ? (CallbackReference.class$com$sun$jna$Callback = class$("com.sun.jna.Callback")) : CallbackReference.class$com$sun$jna$Callback).isAssignableFrom(type)) {
            throw new IllegalArgumentException(type.getName() + " is not derived from com.sun.jna.Callback");
        }
        if (type.isInterface()) {
            return type;
        }
        final Class[] ifaces = type.getInterfaces();
        for (int i = 0; i < ifaces.length; ++i) {
            if (((CallbackReference.class$com$sun$jna$Callback == null) ? (CallbackReference.class$com$sun$jna$Callback = class$("com.sun.jna.Callback")) : CallbackReference.class$com$sun$jna$Callback).isAssignableFrom(ifaces[i])) {
                try {
                    getCallbackMethod(ifaces[i]);
                    return ifaces[i];
                }
                catch (IllegalArgumentException e) {
                    break;
                }
            }
        }
        if (((CallbackReference.class$com$sun$jna$Callback == null) ? (CallbackReference.class$com$sun$jna$Callback = class$("com.sun.jna.Callback")) : CallbackReference.class$com$sun$jna$Callback).isAssignableFrom(type.getSuperclass())) {
            return findCallbackClass(type.getSuperclass());
        }
        return type;
    }
    
    private static Method getCallbackMethod(final Callback callback) {
        return getCallbackMethod(findCallbackClass(callback.getClass()));
    }
    
    private static Method getCallbackMethod(final Class cls) {
        final Method[] pubMethods = cls.getDeclaredMethods();
        final Method[] classMethods = cls.getMethods();
        final Set pmethods = new HashSet(Arrays.asList(pubMethods));
        pmethods.retainAll(Arrays.asList(classMethods));
        final Iterator i = pmethods.iterator();
        while (i.hasNext()) {
            final Method m = i.next();
            if (Callback.FORBIDDEN_NAMES.contains(m.getName())) {
                i.remove();
            }
        }
        final Method[] methods = pmethods.toArray(new Method[pmethods.size()]);
        if (methods.length == 1) {
            return checkMethod(methods[0]);
        }
        for (int j = 0; j < methods.length; ++j) {
            final Method k = methods[j];
            if ("callback".equals(k.getName())) {
                return checkMethod(k);
            }
        }
        final String msg = "Callback must implement a single public method, or one public method named 'callback'";
        throw new IllegalArgumentException(msg);
    }
    
    public Pointer getTrampoline() {
        return this.cbstruct.getPointer(0L);
    }
    
    protected void finalize() {
        this.dispose();
    }
    
    protected synchronized void dispose() {
        if (this.cbstruct != null) {
            Native.freeNativeCallback(this.cbstruct.peer);
            this.cbstruct.peer = 0L;
            this.cbstruct = null;
        }
    }
    
    private Callback getCallback() {
        return this.get();
    }
    
    private static Pointer getNativeFunctionPointer(final Callback cb) {
        if (Proxy.isProxyClass(cb.getClass())) {
            final Object handler = Proxy.getInvocationHandler(cb);
            if (handler instanceof NativeFunctionHandler) {
                return ((NativeFunctionHandler)handler).getPointer();
            }
        }
        return null;
    }
    
    public static Pointer getFunctionPointer(final Callback cb) {
        return getFunctionPointer(cb, false);
    }
    
    private static Pointer getFunctionPointer(final Callback cb, final boolean direct) {
        Pointer fp = null;
        if (cb == null) {
            return null;
        }
        if ((fp = getNativeFunctionPointer(cb)) != null) {
            return fp;
        }
        final int callingConvention = (cb instanceof AltCallingConvention) ? 1 : 0;
        final Map map = direct ? CallbackReference.directCallbackMap : CallbackReference.callbackMap;
        synchronized (map) {
            CallbackReference cbref = map.get(cb);
            if (cbref == null) {
                cbref = new CallbackReference(cb, callingConvention, direct);
                map.put(cb, cbref);
            }
            return cbref.getTrampoline();
        }
    }
    
    private static boolean isAllowableNativeType(final Class cls) {
        return cls == Void.TYPE || (cls == ((CallbackReference.class$java$lang$Void == null) ? (CallbackReference.class$java$lang$Void = class$("java.lang.Void")) : CallbackReference.class$java$lang$Void) || cls == Boolean.TYPE) || (cls == ((CallbackReference.class$java$lang$Boolean == null) ? (CallbackReference.class$java$lang$Boolean = class$("java.lang.Boolean")) : CallbackReference.class$java$lang$Boolean) || cls == Byte.TYPE) || (cls == ((CallbackReference.class$java$lang$Byte == null) ? (CallbackReference.class$java$lang$Byte = class$("java.lang.Byte")) : CallbackReference.class$java$lang$Byte) || cls == Short.TYPE) || (cls == ((CallbackReference.class$java$lang$Short == null) ? (CallbackReference.class$java$lang$Short = class$("java.lang.Short")) : CallbackReference.class$java$lang$Short) || cls == Character.TYPE) || (cls == ((CallbackReference.class$java$lang$Character == null) ? (CallbackReference.class$java$lang$Character = class$("java.lang.Character")) : CallbackReference.class$java$lang$Character) || cls == Integer.TYPE) || (cls == ((CallbackReference.class$java$lang$Integer == null) ? (CallbackReference.class$java$lang$Integer = class$("java.lang.Integer")) : CallbackReference.class$java$lang$Integer) || cls == Long.TYPE) || (cls == ((CallbackReference.class$java$lang$Long == null) ? (CallbackReference.class$java$lang$Long = class$("java.lang.Long")) : CallbackReference.class$java$lang$Long) || cls == Float.TYPE) || (cls == ((CallbackReference.class$java$lang$Float == null) ? (CallbackReference.class$java$lang$Float = class$("java.lang.Float")) : CallbackReference.class$java$lang$Float) || cls == Double.TYPE) || cls == ((CallbackReference.class$java$lang$Double == null) ? (CallbackReference.class$java$lang$Double = class$("java.lang.Double")) : CallbackReference.class$java$lang$Double) || (((CallbackReference.class$com$sun$jna$Structure$ByValue == null) ? (CallbackReference.class$com$sun$jna$Structure$ByValue = class$("com.sun.jna.Structure$ByValue")) : CallbackReference.class$com$sun$jna$Structure$ByValue).isAssignableFrom(cls) && ((CallbackReference.class$com$sun$jna$Structure == null) ? (CallbackReference.class$com$sun$jna$Structure = class$("com.sun.jna.Structure")) : CallbackReference.class$com$sun$jna$Structure).isAssignableFrom(cls)) || ((CallbackReference.class$com$sun$jna$Pointer == null) ? (CallbackReference.class$com$sun$jna$Pointer = class$("com.sun.jna.Pointer")) : CallbackReference.class$com$sun$jna$Pointer).isAssignableFrom(cls);
    }
    
    private static Pointer getNativeString(final Object value, final boolean wide) {
        if (value != null) {
            final NativeString ns = new NativeString(value.toString(), wide);
            CallbackReference.allocations.put(value, ns);
            return ns.getPointer();
        }
        return null;
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
        callbackMap = new WeakHashMap();
        directCallbackMap = new WeakHashMap();
        allocations = new WeakHashMap();
        try {
            PROXY_CALLBACK_METHOD = ((CallbackReference.class$com$sun$jna$CallbackProxy == null) ? (CallbackReference.class$com$sun$jna$CallbackProxy = class$("com.sun.jna.CallbackProxy")) : CallbackReference.class$com$sun$jna$CallbackProxy).getMethod("callback", (CallbackReference.array$Ljava$lang$Object == null) ? (CallbackReference.array$Ljava$lang$Object = class$("[Ljava.lang.Object;")) : CallbackReference.array$Ljava$lang$Object);
        }
        catch (Exception e) {
            throw new Error("Error looking up CallbackProxy.callback() method");
        }
    }
    
    private class DefaultCallbackProxy implements CallbackProxy
    {
        private Method callbackMethod;
        private ToNativeConverter toNative;
        private FromNativeConverter[] fromNative;
        
        public DefaultCallbackProxy(final Method callbackMethod, final TypeMapper mapper) {
            this.callbackMethod = callbackMethod;
            final Class[] argTypes = callbackMethod.getParameterTypes();
            final Class returnType = callbackMethod.getReturnType();
            this.fromNative = new FromNativeConverter[argTypes.length];
            if (((CallbackReference.class$com$sun$jna$NativeMapped == null) ? (CallbackReference.class$com$sun$jna$NativeMapped = CallbackReference.class$("com.sun.jna.NativeMapped")) : CallbackReference.class$com$sun$jna$NativeMapped).isAssignableFrom(returnType)) {
                this.toNative = NativeMappedConverter.getInstance(returnType);
            }
            else if (mapper != null) {
                this.toNative = mapper.getToNativeConverter(returnType);
            }
            for (int i = 0; i < this.fromNative.length; ++i) {
                if (((CallbackReference.class$com$sun$jna$NativeMapped == null) ? (CallbackReference.class$com$sun$jna$NativeMapped = CallbackReference.class$("com.sun.jna.NativeMapped")) : CallbackReference.class$com$sun$jna$NativeMapped).isAssignableFrom(argTypes[i])) {
                    this.fromNative[i] = new NativeMappedConverter(argTypes[i]);
                }
                else if (mapper != null) {
                    this.fromNative[i] = mapper.getFromNativeConverter(argTypes[i]);
                }
            }
            if (!callbackMethod.isAccessible()) {
                try {
                    callbackMethod.setAccessible(true);
                }
                catch (SecurityException e) {
                    throw new IllegalArgumentException("Callback method is inaccessible, make sure the interface is public: " + callbackMethod);
                }
            }
        }
        
        private Object invokeCallback(final Object[] args) {
            final Class[] paramTypes = this.callbackMethod.getParameterTypes();
            final Object[] callbackArgs = new Object[args.length];
            for (int i = 0; i < args.length; ++i) {
                final Class type = paramTypes[i];
                final Object arg = args[i];
                if (this.fromNative[i] != null) {
                    final FromNativeContext context = new CallbackParameterContext(type, this.callbackMethod, args, i);
                    callbackArgs[i] = this.fromNative[i].fromNative(arg, context);
                }
                else {
                    callbackArgs[i] = this.convertArgument(arg, type);
                }
            }
            Object result = null;
            final Callback cb = CallbackReference.this.getCallback();
            if (cb != null) {
                try {
                    result = this.convertResult(this.callbackMethod.invoke(cb, callbackArgs));
                }
                catch (IllegalArgumentException e) {
                    Native.getCallbackExceptionHandler().uncaughtException(cb, e);
                }
                catch (IllegalAccessException e2) {
                    Native.getCallbackExceptionHandler().uncaughtException(cb, e2);
                }
                catch (InvocationTargetException e3) {
                    Native.getCallbackExceptionHandler().uncaughtException(cb, e3.getTargetException());
                }
            }
            for (int j = 0; j < callbackArgs.length; ++j) {
                if (callbackArgs[j] instanceof Structure && !(callbackArgs[j] instanceof Structure.ByValue)) {
                    ((Structure)callbackArgs[j]).autoWrite();
                }
            }
            return result;
        }
        
        public Object callback(final Object[] args) {
            try {
                return this.invokeCallback(args);
            }
            catch (Throwable t) {
                Native.getCallbackExceptionHandler().uncaughtException(CallbackReference.this.getCallback(), t);
                return null;
            }
        }
        
        private Object convertArgument(Object value, final Class dstType) {
            if (value instanceof Pointer) {
                if (dstType == ((CallbackReference.class$java$lang$String == null) ? (CallbackReference.class$java$lang$String = CallbackReference.class$("java.lang.String")) : CallbackReference.class$java$lang$String)) {
                    value = ((Pointer)value).getString(0L);
                }
                else if (dstType == ((CallbackReference.class$com$sun$jna$WString == null) ? (CallbackReference.class$com$sun$jna$WString = CallbackReference.class$("com.sun.jna.WString")) : CallbackReference.class$com$sun$jna$WString)) {
                    value = new WString(((Pointer)value).getString(0L, true));
                }
                else if (dstType == ((CallbackReference.array$Ljava$lang$String == null) ? (CallbackReference.array$Ljava$lang$String = CallbackReference.class$("[Ljava.lang.String;")) : CallbackReference.array$Ljava$lang$String) || dstType == ((CallbackReference.array$Lcom$sun$jna$WString == null) ? (CallbackReference.array$Lcom$sun$jna$WString = CallbackReference.class$("[Lcom.sun.jna.WString;")) : CallbackReference.array$Lcom$sun$jna$WString)) {
                    value = ((Pointer)value).getStringArray(0L, dstType == ((CallbackReference.array$Lcom$sun$jna$WString == null) ? (CallbackReference.array$Lcom$sun$jna$WString = CallbackReference.class$("[Lcom.sun.jna.WString;")) : CallbackReference.array$Lcom$sun$jna$WString));
                }
                else if (((CallbackReference.class$com$sun$jna$Callback == null) ? (CallbackReference.class$com$sun$jna$Callback = CallbackReference.class$("com.sun.jna.Callback")) : CallbackReference.class$com$sun$jna$Callback).isAssignableFrom(dstType)) {
                    value = CallbackReference.getCallback(dstType, (Pointer)value);
                }
                else if (((CallbackReference.class$com$sun$jna$Structure == null) ? (CallbackReference.class$com$sun$jna$Structure = CallbackReference.class$("com.sun.jna.Structure")) : CallbackReference.class$com$sun$jna$Structure).isAssignableFrom(dstType)) {
                    final Structure s = Structure.newInstance(dstType);
                    if (((CallbackReference.class$com$sun$jna$Structure$ByValue == null) ? (CallbackReference.class$com$sun$jna$Structure$ByValue = CallbackReference.class$("com.sun.jna.Structure$ByValue")) : CallbackReference.class$com$sun$jna$Structure$ByValue).isAssignableFrom(dstType)) {
                        final byte[] buf = new byte[s.size()];
                        ((Pointer)value).read(0L, buf, 0, buf.length);
                        s.getPointer().write(0L, buf, 0, buf.length);
                    }
                    else {
                        s.useMemory((Pointer)value);
                    }
                    s.read();
                    value = s;
                }
            }
            else if ((Boolean.TYPE == dstType || ((CallbackReference.class$java$lang$Boolean == null) ? (CallbackReference.class$java$lang$Boolean = CallbackReference.class$("java.lang.Boolean")) : CallbackReference.class$java$lang$Boolean) == dstType) && value instanceof Number) {
                value = Function.valueOf(((Number)value).intValue() != 0);
            }
            return value;
        }
        
        private Object convertResult(Object value) {
            if (this.toNative != null) {
                value = this.toNative.toNative(value, new CallbackResultContext(this.callbackMethod));
            }
            if (value == null) {
                return null;
            }
            final Class cls = value.getClass();
            if (((CallbackReference.class$com$sun$jna$Structure == null) ? (CallbackReference.class$com$sun$jna$Structure = CallbackReference.class$("com.sun.jna.Structure")) : CallbackReference.class$com$sun$jna$Structure).isAssignableFrom(cls)) {
                if (((CallbackReference.class$com$sun$jna$Structure$ByValue == null) ? (CallbackReference.class$com$sun$jna$Structure$ByValue = CallbackReference.class$("com.sun.jna.Structure$ByValue")) : CallbackReference.class$com$sun$jna$Structure$ByValue).isAssignableFrom(cls)) {
                    return value;
                }
                return ((Structure)value).getPointer();
            }
            else {
                if (cls == Boolean.TYPE || cls == ((CallbackReference.class$java$lang$Boolean == null) ? (CallbackReference.class$java$lang$Boolean = CallbackReference.class$("java.lang.Boolean")) : CallbackReference.class$java$lang$Boolean)) {
                    return Boolean.TRUE.equals(value) ? Function.INTEGER_TRUE : Function.INTEGER_FALSE;
                }
                if (cls == ((CallbackReference.class$java$lang$String == null) ? (CallbackReference.class$java$lang$String = CallbackReference.class$("java.lang.String")) : CallbackReference.class$java$lang$String) || cls == ((CallbackReference.class$com$sun$jna$WString == null) ? (CallbackReference.class$com$sun$jna$WString = CallbackReference.class$("com.sun.jna.WString")) : CallbackReference.class$com$sun$jna$WString)) {
                    return getNativeString(value, cls == ((CallbackReference.class$com$sun$jna$WString == null) ? (CallbackReference.class$com$sun$jna$WString = CallbackReference.class$("com.sun.jna.WString")) : CallbackReference.class$com$sun$jna$WString));
                }
                if (cls == ((CallbackReference.array$Ljava$lang$String == null) ? (CallbackReference.array$Ljava$lang$String = CallbackReference.class$("[Ljava.lang.String;")) : CallbackReference.array$Ljava$lang$String) || cls == ((CallbackReference.class$com$sun$jna$WString == null) ? (CallbackReference.class$com$sun$jna$WString = CallbackReference.class$("com.sun.jna.WString")) : CallbackReference.class$com$sun$jna$WString)) {
                    final StringArray sa = (cls == ((CallbackReference.array$Ljava$lang$String == null) ? (CallbackReference.array$Ljava$lang$String = CallbackReference.class$("[Ljava.lang.String;")) : CallbackReference.array$Ljava$lang$String)) ? new StringArray((String[])value) : new StringArray((WString[])value);
                    CallbackReference.allocations.put(value, sa);
                    return sa;
                }
                if (((CallbackReference.class$com$sun$jna$Callback == null) ? (CallbackReference.class$com$sun$jna$Callback = CallbackReference.class$("com.sun.jna.Callback")) : CallbackReference.class$com$sun$jna$Callback).isAssignableFrom(cls)) {
                    return CallbackReference.getFunctionPointer((Callback)value);
                }
                return value;
            }
        }
        
        public Class[] getParameterTypes() {
            return this.callbackMethod.getParameterTypes();
        }
        
        public Class getReturnType() {
            return this.callbackMethod.getReturnType();
        }
    }
    
    private static class NativeFunctionHandler implements InvocationHandler
    {
        private Function function;
        private Map options;
        
        public NativeFunctionHandler(final Pointer address, final int callingConvention, final Map options) {
            this.function = new Function(address, callingConvention);
            this.options = options;
        }
        
        public Object invoke(final Object proxy, final Method method, Object[] args) throws Throwable {
            if (Library.Handler.OBJECT_TOSTRING.equals(method)) {
                String str = "Proxy interface to " + this.function;
                final Method m = this.options.get("invoking-method");
                final Class cls = CallbackReference.findCallbackClass(m.getDeclaringClass());
                str = str + " (" + cls.getName() + ")";
                return str;
            }
            if (Library.Handler.OBJECT_HASHCODE.equals(method)) {
                return new Integer(this.hashCode());
            }
            if (!Library.Handler.OBJECT_EQUALS.equals(method)) {
                if (Function.isVarArgs(method)) {
                    args = Function.concatenateVarArgs(args);
                }
                return this.function.invoke(method.getReturnType(), args, this.options);
            }
            final Object o = args[0];
            if (o != null && Proxy.isProxyClass(o.getClass())) {
                return Function.valueOf(Proxy.getInvocationHandler(o) == this);
            }
            return Boolean.FALSE;
        }
        
        public Pointer getPointer() {
            return this.function;
        }
    }
}
