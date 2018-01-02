// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.jna;

import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.io.InputStream;
import java.net.URL;
import java.io.IOException;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.net.URI;
import java.lang.ref.WeakReference;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.io.UnsupportedEncodingException;
import java.nio.Buffer;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Component;
import java.awt.Window;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.lang.reflect.Field;
import java.util.List;
import java.io.File;
import java.util.Map;

public final class Native
{
    private static final String VERSION = "3.2.7";
    private static String nativeLibraryPath;
    private static boolean unpacked;
    private static Map typeMappers;
    private static Map alignments;
    private static Map options;
    private static Map libraries;
    private static final Callback.UncaughtExceptionHandler DEFAULT_HANDLER;
    private static Callback.UncaughtExceptionHandler callbackExceptionHandler;
    public static final int POINTER_SIZE;
    public static final int LONG_SIZE;
    public static final int WCHAR_SIZE;
    public static final int SIZE_T_SIZE;
    private static final int TYPE_VOIDP = 0;
    private static final int TYPE_LONG = 1;
    private static final int TYPE_WCHAR_T = 2;
    private static final int TYPE_SIZE_T = 3;
    private static final Object finalizer;
    private static final ThreadLocal lastError;
    private static Map registeredClasses;
    private static Map registeredLibraries;
    private static Object unloader;
    private static final int CVT_UNSUPPORTED = -1;
    private static final int CVT_DEFAULT = 0;
    private static final int CVT_POINTER = 1;
    private static final int CVT_STRING = 2;
    private static final int CVT_STRUCTURE = 3;
    private static final int CVT_STRUCTURE_BYVAL = 4;
    private static final int CVT_BUFFER = 5;
    private static final int CVT_ARRAY_BYTE = 6;
    private static final int CVT_ARRAY_SHORT = 7;
    private static final int CVT_ARRAY_CHAR = 8;
    private static final int CVT_ARRAY_INT = 9;
    private static final int CVT_ARRAY_LONG = 10;
    private static final int CVT_ARRAY_FLOAT = 11;
    private static final int CVT_ARRAY_DOUBLE = 12;
    private static final int CVT_ARRAY_BOOLEAN = 13;
    private static final int CVT_BOOLEAN = 14;
    private static final int CVT_CALLBACK = 15;
    private static final int CVT_FLOAT = 16;
    private static final int CVT_NATIVE_MAPPED = 17;
    private static final int CVT_WSTRING = 18;
    private static final int CVT_INTEGER_TYPE = 19;
    private static final int CVT_POINTER_TYPE = 20;
    private static final int CVT_TYPE_MAPPER = 21;
    static /* synthetic */ Class class$com$sun$jna$Native;
    static /* synthetic */ Class class$java$lang$ClassLoader;
    static /* synthetic */ Class class$com$sun$jna$Library;
    static /* synthetic */ Class class$com$sun$jna$Callback;
    static /* synthetic */ Class class$com$sun$jna$TypeMapper;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$com$sun$jna$Structure;
    static /* synthetic */ Class class$com$sun$jna$Structure$ByReference;
    static /* synthetic */ Class class$com$sun$jna$NativeMapped;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$com$sun$jna$Structure$ByValue;
    static /* synthetic */ Class class$com$sun$jna$Pointer;
    static /* synthetic */ Class class$java$nio$Buffer;
    static /* synthetic */ Class class$com$sun$jna$WString;
    static /* synthetic */ Class class$java$lang$Void;
    static /* synthetic */ Class class$com$sun$jna$IntegerType;
    static /* synthetic */ Class class$com$sun$jna$PointerType;
    static /* synthetic */ Class class$com$sun$jna$LastErrorException;
    
    private static boolean deleteNativeLibrary() {
        final String path = Native.nativeLibraryPath;
        if (path == null || !Native.unpacked) {
            return true;
        }
        final File flib = new File(path);
        if (flib.delete()) {
            Native.nativeLibraryPath = null;
            Native.unpacked = false;
            return true;
        }
        try {
            final ClassLoader cl = ((Native.class$com$sun$jna$Native == null) ? (Native.class$com$sun$jna$Native = class$("com.sun.jna.Native")) : Native.class$com$sun$jna$Native).getClassLoader();
            Field f = ((Native.class$java$lang$ClassLoader == null) ? (Native.class$java$lang$ClassLoader = class$("java.lang.ClassLoader")) : Native.class$java$lang$ClassLoader).getDeclaredField("nativeLibraries");
            f.setAccessible(true);
            final List libs = (List)f.get(cl);
            for (final Object lib : libs) {
                f = lib.getClass().getDeclaredField("name");
                f.setAccessible(true);
                final String name = (String)f.get(lib);
                if (name.equals(path) || name.indexOf(path) != -1 || name.equals(flib.getCanonicalPath())) {
                    final Method m = lib.getClass().getDeclaredMethod("finalize", (Class<?>[])new Class[0]);
                    m.setAccessible(true);
                    m.invoke(lib, new Object[0]);
                    Native.nativeLibraryPath = null;
                    if (!Native.unpacked || !flib.exists()) {
                        return true;
                    }
                    if (flib.delete()) {
                        Native.unpacked = false;
                        return true;
                    }
                    return false;
                }
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    private static native void initIDs();
    
    public static synchronized native void setProtected(final boolean p0);
    
    public static synchronized native boolean isProtected();
    
    public static synchronized native void setPreserveLastError(final boolean p0);
    
    public static synchronized native boolean getPreserveLastError();
    
    public static long getWindowID(final Window w) throws HeadlessException {
        return getComponentID(w);
    }
    
    public static long getComponentID(final Component c) throws HeadlessException {
        if (GraphicsEnvironment.isHeadless()) {
            throw new HeadlessException("No native windows when headless");
        }
        if (c.isLightweight()) {
            throw new IllegalArgumentException("Component must be heavyweight");
        }
        if (!c.isDisplayable()) {
            throw new IllegalStateException("Component must be displayable");
        }
        if (Platform.isX11() && System.getProperty("java.version").startsWith("1.4") && !c.isVisible()) {
            throw new IllegalStateException("Component must be visible");
        }
        return getWindowHandle0(c);
    }
    
    public static Pointer getWindowPointer(final Window w) throws HeadlessException {
        return getComponentPointer(w);
    }
    
    public static Pointer getComponentPointer(final Component c) throws HeadlessException {
        return new Pointer(getComponentID(c));
    }
    
    private static native long getWindowHandle0(final Component p0);
    
    public static native Pointer getDirectBufferPointer(final Buffer p0);
    
    public static String toString(final byte[] buf) {
        return toString(buf, System.getProperty("jna.encoding"));
    }
    
    public static String toString(final byte[] buf, final String encoding) {
        String s = null;
        if (encoding != null) {
            try {
                s = new String(buf, encoding);
            }
            catch (UnsupportedEncodingException ex) {}
        }
        if (s == null) {
            s = new String(buf);
        }
        final int term = s.indexOf(0);
        if (term != -1) {
            s = s.substring(0, term);
        }
        return s;
    }
    
    public static String toString(final char[] buf) {
        String s = new String(buf);
        final int term = s.indexOf(0);
        if (term != -1) {
            s = s.substring(0, term);
        }
        return s;
    }
    
    public static Object loadLibrary(final Class interfaceClass) {
        return loadLibrary(null, interfaceClass);
    }
    
    public static Object loadLibrary(final Class interfaceClass, final Map options) {
        return loadLibrary(null, interfaceClass, options);
    }
    
    public static Object loadLibrary(final String name, final Class interfaceClass) {
        return loadLibrary(name, interfaceClass, Collections.EMPTY_MAP);
    }
    
    public static Object loadLibrary(final String name, final Class interfaceClass, final Map options) {
        final Library.Handler handler = new Library.Handler(name, interfaceClass, options);
        final ClassLoader loader = interfaceClass.getClassLoader();
        final Library proxy = (Library)Proxy.newProxyInstance(loader, new Class[] { interfaceClass }, handler);
        cacheOptions(interfaceClass, options, proxy);
        return proxy;
    }
    
    private static void loadLibraryInstance(final Class cls) {
        if (cls != null && !Native.libraries.containsKey(cls)) {
            try {
                final Field[] fields = cls.getFields();
                for (int i = 0; i < fields.length; ++i) {
                    final Field field = fields[i];
                    if (field.getType() == cls && Modifier.isStatic(field.getModifiers())) {
                        Native.libraries.put(cls, new WeakReference<Object>(field.get(null)));
                        break;
                    }
                }
            }
            catch (Exception e) {
                throw new IllegalArgumentException("Could not access instance of " + cls + " (" + e + ")");
            }
        }
    }
    
    static Class findEnclosingLibraryClass(Class cls) {
        if (cls == null) {
            return null;
        }
        synchronized (Native.libraries) {
            if (Native.options.containsKey(cls)) {
                return cls;
            }
        }
        if (((Native.class$com$sun$jna$Library == null) ? (Native.class$com$sun$jna$Library = class$("com.sun.jna.Library")) : Native.class$com$sun$jna$Library).isAssignableFrom(cls)) {
            return cls;
        }
        if (((Native.class$com$sun$jna$Callback == null) ? (Native.class$com$sun$jna$Callback = class$("com.sun.jna.Callback")) : Native.class$com$sun$jna$Callback).isAssignableFrom(cls)) {
            cls = CallbackReference.findCallbackClass(cls);
        }
        final Class declaring = cls.getDeclaringClass();
        final Class fromDeclaring = findEnclosingLibraryClass(declaring);
        if (fromDeclaring != null) {
            return fromDeclaring;
        }
        return findEnclosingLibraryClass(cls.getSuperclass());
    }
    
    public static Map getLibraryOptions(final Class type) {
        synchronized (Native.libraries) {
            Class interfaceClass = findEnclosingLibraryClass(type);
            if (interfaceClass != null) {
                loadLibraryInstance(interfaceClass);
            }
            else {
                interfaceClass = type;
            }
            if (!Native.options.containsKey(interfaceClass)) {
                try {
                    final Field field = interfaceClass.getField("OPTIONS");
                    field.setAccessible(true);
                    Native.options.put(interfaceClass, field.get(null));
                }
                catch (NoSuchFieldException e2) {}
                catch (Exception e) {
                    throw new IllegalArgumentException("OPTIONS must be a public field of type java.util.Map (" + e + "): " + interfaceClass);
                }
            }
            return Native.options.get(interfaceClass);
        }
    }
    
    public static TypeMapper getTypeMapper(final Class cls) {
        synchronized (Native.libraries) {
            Class interfaceClass = findEnclosingLibraryClass(cls);
            if (interfaceClass != null) {
                loadLibraryInstance(interfaceClass);
            }
            else {
                interfaceClass = cls;
            }
            if (!Native.typeMappers.containsKey(interfaceClass)) {
                try {
                    final Field field = interfaceClass.getField("TYPE_MAPPER");
                    field.setAccessible(true);
                    Native.typeMappers.put(interfaceClass, field.get(null));
                }
                catch (NoSuchFieldException e2) {
                    final Map options = getLibraryOptions(cls);
                    if (options != null && options.containsKey("type-mapper")) {
                        Native.typeMappers.put(interfaceClass, options.get("type-mapper"));
                    }
                }
                catch (Exception e) {
                    throw new IllegalArgumentException("TYPE_MAPPER must be a public field of type " + ((Native.class$com$sun$jna$TypeMapper == null) ? (Native.class$com$sun$jna$TypeMapper = class$("com.sun.jna.TypeMapper")) : Native.class$com$sun$jna$TypeMapper).getName() + " (" + e + "): " + interfaceClass);
                }
            }
            return Native.typeMappers.get(interfaceClass);
        }
    }
    
    public static int getStructureAlignment(final Class cls) {
        synchronized (Native.libraries) {
            Class interfaceClass = findEnclosingLibraryClass(cls);
            if (interfaceClass != null) {
                loadLibraryInstance(interfaceClass);
            }
            else {
                interfaceClass = cls;
            }
            if (!Native.alignments.containsKey(interfaceClass)) {
                try {
                    final Field field = interfaceClass.getField("STRUCTURE_ALIGNMENT");
                    field.setAccessible(true);
                    Native.alignments.put(interfaceClass, field.get(null));
                }
                catch (NoSuchFieldException e2) {
                    final Map options = getLibraryOptions(interfaceClass);
                    if (options != null && options.containsKey("structure-alignment")) {
                        Native.alignments.put(interfaceClass, options.get("structure-alignment"));
                    }
                }
                catch (Exception e) {
                    throw new IllegalArgumentException("STRUCTURE_ALIGNMENT must be a public field of type int (" + e + "): " + interfaceClass);
                }
            }
            final Integer value = Native.alignments.get(interfaceClass);
            return (value != null) ? value : 0;
        }
    }
    
    static byte[] getBytes(final String s) {
        try {
            return getBytes(s, System.getProperty("jna.encoding"));
        }
        catch (UnsupportedEncodingException e) {
            return s.getBytes();
        }
    }
    
    static byte[] getBytes(final String s, final String encoding) throws UnsupportedEncodingException {
        if (encoding != null) {
            return s.getBytes(encoding);
        }
        return s.getBytes();
    }
    
    public static byte[] toByteArray(final String s) {
        final byte[] bytes = getBytes(s);
        final byte[] buf = new byte[bytes.length + 1];
        System.arraycopy(bytes, 0, buf, 0, bytes.length);
        return buf;
    }
    
    public static byte[] toByteArray(final String s, final String encoding) throws UnsupportedEncodingException {
        final byte[] bytes = getBytes(s, encoding);
        final byte[] buf = new byte[bytes.length + 1];
        System.arraycopy(bytes, 0, buf, 0, bytes.length);
        return buf;
    }
    
    public static char[] toCharArray(final String s) {
        final char[] chars = s.toCharArray();
        final char[] buf = new char[chars.length + 1];
        System.arraycopy(chars, 0, buf, 0, chars.length);
        return buf;
    }
    
    static String getNativeLibraryResourcePath(final int osType, String arch, final String name) {
        arch = arch.toLowerCase();
        String osPrefix = null;
        switch (osType) {
            case 2: {
                if ("i386".equals(arch)) {
                    arch = "x86";
                }
                osPrefix = "win32-" + arch;
                break;
            }
            case 0: {
                osPrefix = "darwin";
                break;
            }
            case 1: {
                if ("x86".equals(arch)) {
                    arch = "i386";
                }
                else if ("x86_64".equals(arch)) {
                    arch = "amd64";
                }
                osPrefix = "linux-" + arch;
                break;
            }
            case 3: {
                osPrefix = "sunos-" + arch;
                break;
            }
            default: {
                osPrefix = name.toLowerCase();
                if ("x86".equals(arch)) {
                    arch = "i386";
                }
                if ("x86_64".equals(arch)) {
                    arch = "amd64";
                }
                if ("powerpc".equals(arch)) {
                    arch = "ppc";
                }
                final int space = osPrefix.indexOf(" ");
                if (space != -1) {
                    osPrefix = osPrefix.substring(0, space);
                }
                osPrefix = osPrefix + "-" + arch;
                break;
            }
        }
        return "/com/sun/jna/" + osPrefix;
    }
    
    private static void loadNativeLibrary() {
        final String libName = "jnidispatch";
        final String bootPath = System.getProperty("jna.boot.library.path");
        if (bootPath != null) {
            final String[] dirs = bootPath.split(File.pathSeparator);
            int i = 0;
            while (i < dirs.length) {
                String path = new File(new File(dirs[i]), System.mapLibraryName(libName)).getAbsolutePath();
                try {
                    System.load(path);
                    Native.nativeLibraryPath = path;
                    return;
                }
                catch (UnsatisfiedLinkError ex) {
                    if (Platform.isMac()) {
                        String orig;
                        String ext;
                        if (path.endsWith("dylib")) {
                            orig = "dylib";
                            ext = "jnilib";
                        }
                        else {
                            orig = "jnilib";
                            ext = "dylib";
                        }
                        try {
                            path = path.substring(0, path.lastIndexOf(orig)) + ext;
                            System.load(path);
                            Native.nativeLibraryPath = path;
                            return;
                        }
                        catch (UnsatisfiedLinkError unsatisfiedLinkError) {}
                    }
                    ++i;
                    continue;
                }
                break;
            }
        }
        try {
            System.loadLibrary(libName);
            Native.nativeLibraryPath = libName;
        }
        catch (UnsatisfiedLinkError e) {
            loadNativeLibraryFromJar();
        }
    }
    
    private static void loadNativeLibraryFromJar() {
        final String libname = System.mapLibraryName("jnidispatch");
        final String arch = System.getProperty("os.arch");
        final String name = System.getProperty("os.name");
        String resourceName = getNativeLibraryResourcePath(Platform.getOSType(), arch, name) + "/" + libname;
        URL url = ((Native.class$com$sun$jna$Native == null) ? (Native.class$com$sun$jna$Native = class$("com.sun.jna.Native")) : Native.class$com$sun$jna$Native).getResource(resourceName);
        if (url == null && Platform.isMac() && resourceName.endsWith(".dylib")) {
            resourceName = resourceName.substring(0, resourceName.lastIndexOf(".dylib")) + ".jnilib";
            url = ((Native.class$com$sun$jna$Native == null) ? (Native.class$com$sun$jna$Native = class$("com.sun.jna.Native")) : Native.class$com$sun$jna$Native).getResource(resourceName);
        }
        if (url == null) {
            throw new UnsatisfiedLinkError("jnidispatch (" + resourceName + ") not found in resource path");
        }
        File lib = null;
        if (url.getProtocol().toLowerCase().equals("file")) {
            try {
                lib = new File(new URI(url.toString()));
            }
            catch (URISyntaxException e2) {
                lib = new File(url.getPath());
            }
            if (!lib.exists()) {
                throw new Error("File URL " + url + " could not be properly decoded");
            }
        }
        else {
            final InputStream is = ((Native.class$com$sun$jna$Native == null) ? (Native.class$com$sun$jna$Native = class$("com.sun.jna.Native")) : Native.class$com$sun$jna$Native).getResourceAsStream(resourceName);
            if (is == null) {
                throw new Error("Can't obtain jnidispatch InputStream");
            }
            FileOutputStream fos = null;
            try {
                lib = File.createTempFile("jna", Platform.isWindows() ? ".dll" : null);
                lib.deleteOnExit();
                final ClassLoader cl = ((Native.class$com$sun$jna$Native == null) ? (Native.class$com$sun$jna$Native = class$("com.sun.jna.Native")) : Native.class$com$sun$jna$Native).getClassLoader();
                if (Platform.deleteNativeLibraryAfterVMExit() && (cl == null || cl.equals(ClassLoader.getSystemClassLoader()))) {
                    Runtime.getRuntime().addShutdownHook(new DeleteNativeLibrary(lib));
                }
                fos = new FileOutputStream(lib);
                final byte[] buf = new byte[1024];
                int count;
                while ((count = is.read(buf, 0, buf.length)) > 0) {
                    fos.write(buf, 0, count);
                }
            }
            catch (IOException e) {
                throw new Error("Failed to create temporary file for jnidispatch library: " + e);
            }
            finally {
                try {
                    is.close();
                }
                catch (IOException ex) {}
                if (fos != null) {
                    try {
                        fos.close();
                    }
                    catch (IOException ex2) {}
                }
            }
            Native.unpacked = true;
        }
        System.load(lib.getAbsolutePath());
        Native.nativeLibraryPath = lib.getAbsolutePath();
    }
    
    private static native int sizeof(final int p0);
    
    private static native String getNativeVersion();
    
    private static native String getAPIChecksum();
    
    public static int getLastError() {
        return Native.lastError.get();
    }
    
    public static native void setLastError(final int p0);
    
    static void updateLastError(final int e) {
        Native.lastError.set(new Integer(e));
    }
    
    public static Library synchronizedLibrary(final Library library) {
        final Class cls = library.getClass();
        if (!Proxy.isProxyClass(cls)) {
            throw new IllegalArgumentException("Library must be a proxy class");
        }
        final InvocationHandler ih = Proxy.getInvocationHandler(library);
        if (!(ih instanceof Library.Handler)) {
            throw new IllegalArgumentException("Unrecognized proxy handler: " + ih);
        }
        final Library.Handler handler = (Library.Handler)ih;
        final InvocationHandler newHandler = new InvocationHandler() {
            public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
                synchronized (handler.getNativeLibrary()) {
                    return handler.invoke(library, method, args);
                }
            }
        };
        return (Library)Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), newHandler);
    }
    
    public static String getWebStartLibraryPath(final String libName) {
        if (System.getProperty("javawebstart.version") == null) {
            return null;
        }
        try {
            final ClassLoader cl = ((Native.class$com$sun$jna$Native == null) ? (Native.class$com$sun$jna$Native = class$("com.sun.jna.Native")) : Native.class$com$sun$jna$Native).getClassLoader();
            final Method m = AccessController.doPrivileged((PrivilegedAction<Method>)new PrivilegedAction() {
                public Object run() {
                    try {
                        final Method m = ((Native.class$java$lang$ClassLoader == null) ? (Native.class$java$lang$ClassLoader = Native.class$("java.lang.ClassLoader")) : Native.class$java$lang$ClassLoader).getDeclaredMethod("findLibrary", (Native.class$java$lang$String == null) ? (Native.class$java$lang$String = Native.class$("java.lang.String")) : Native.class$java$lang$String);
                        m.setAccessible(true);
                        return m;
                    }
                    catch (Exception e) {
                        return null;
                    }
                }
            });
            final String libpath = (String)m.invoke(cl, libName);
            if (libpath != null) {
                return new File(libpath).getParent();
            }
            return null;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static int getNativeSize(final Class type, Object value) {
        if (type.isArray()) {
            final int len = Array.getLength(value);
            if (len > 0) {
                final Object o = Array.get(value, 0);
                return len * getNativeSize(type.getComponentType(), o);
            }
            throw new IllegalArgumentException("Arrays of length zero not allowed: " + type);
        }
        else {
            if (((Native.class$com$sun$jna$Structure == null) ? (Native.class$com$sun$jna$Structure = class$("com.sun.jna.Structure")) : Native.class$com$sun$jna$Structure).isAssignableFrom(type) && !((Native.class$com$sun$jna$Structure$ByReference == null) ? (Native.class$com$sun$jna$Structure$ByReference = class$("com.sun.jna.Structure$ByReference")) : Native.class$com$sun$jna$Structure$ByReference).isAssignableFrom(type)) {
                if (value == null) {
                    value = Structure.newInstance(type);
                }
                return ((Structure)value).size();
            }
            try {
                return getNativeSize(type);
            }
            catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("The type \"" + type.getName() + "\" is not supported: " + e.getMessage());
            }
        }
    }
    
    public static int getNativeSize(Class cls) {
        if (((Native.class$com$sun$jna$NativeMapped == null) ? (Native.class$com$sun$jna$NativeMapped = class$("com.sun.jna.NativeMapped")) : Native.class$com$sun$jna$NativeMapped).isAssignableFrom(cls)) {
            cls = NativeMappedConverter.getInstance(cls).nativeType();
        }
        if (cls == Boolean.TYPE || cls == ((Native.class$java$lang$Boolean == null) ? (Native.class$java$lang$Boolean = class$("java.lang.Boolean")) : Native.class$java$lang$Boolean)) {
            return 4;
        }
        if (cls == Byte.TYPE || cls == ((Native.class$java$lang$Byte == null) ? (Native.class$java$lang$Byte = class$("java.lang.Byte")) : Native.class$java$lang$Byte)) {
            return 1;
        }
        if (cls == Short.TYPE || cls == ((Native.class$java$lang$Short == null) ? (Native.class$java$lang$Short = class$("java.lang.Short")) : Native.class$java$lang$Short)) {
            return 2;
        }
        if (cls == Character.TYPE || cls == ((Native.class$java$lang$Character == null) ? (Native.class$java$lang$Character = class$("java.lang.Character")) : Native.class$java$lang$Character)) {
            return Native.WCHAR_SIZE;
        }
        if (cls == Integer.TYPE || cls == ((Native.class$java$lang$Integer == null) ? (Native.class$java$lang$Integer = class$("java.lang.Integer")) : Native.class$java$lang$Integer)) {
            return 4;
        }
        if (cls == Long.TYPE || cls == ((Native.class$java$lang$Long == null) ? (Native.class$java$lang$Long = class$("java.lang.Long")) : Native.class$java$lang$Long)) {
            return 8;
        }
        if (cls == Float.TYPE || cls == ((Native.class$java$lang$Float == null) ? (Native.class$java$lang$Float = class$("java.lang.Float")) : Native.class$java$lang$Float)) {
            return 4;
        }
        if (cls == Double.TYPE || cls == ((Native.class$java$lang$Double == null) ? (Native.class$java$lang$Double = class$("java.lang.Double")) : Native.class$java$lang$Double)) {
            return 8;
        }
        if (((Native.class$com$sun$jna$Structure == null) ? (Native.class$com$sun$jna$Structure = class$("com.sun.jna.Structure")) : Native.class$com$sun$jna$Structure).isAssignableFrom(cls)) {
            if (((Native.class$com$sun$jna$Structure$ByValue == null) ? (Native.class$com$sun$jna$Structure$ByValue = class$("com.sun.jna.Structure$ByValue")) : Native.class$com$sun$jna$Structure$ByValue).isAssignableFrom(cls)) {
                return Structure.newInstance(cls).size();
            }
            return Native.POINTER_SIZE;
        }
        else {
            if (((Native.class$com$sun$jna$Pointer == null) ? (Native.class$com$sun$jna$Pointer = class$("com.sun.jna.Pointer")) : Native.class$com$sun$jna$Pointer).isAssignableFrom(cls) || ((Native.class$java$nio$Buffer == null) ? (Native.class$java$nio$Buffer = class$("java.nio.Buffer")) : Native.class$java$nio$Buffer).isAssignableFrom(cls) || ((Native.class$com$sun$jna$Callback == null) ? (Native.class$com$sun$jna$Callback = class$("com.sun.jna.Callback")) : Native.class$com$sun$jna$Callback).isAssignableFrom(cls) || ((Native.class$java$lang$String == null) ? (Native.class$java$lang$String = class$("java.lang.String")) : Native.class$java$lang$String) == cls || ((Native.class$com$sun$jna$WString == null) ? (Native.class$com$sun$jna$WString = class$("com.sun.jna.WString")) : Native.class$com$sun$jna$WString) == cls) {
                return Native.POINTER_SIZE;
            }
            throw new IllegalArgumentException("Native size for type \"" + cls.getName() + "\" is unknown");
        }
    }
    
    public static boolean isSupportedNativeType(final Class cls) {
        if (((Native.class$com$sun$jna$Structure == null) ? (Native.class$com$sun$jna$Structure = class$("com.sun.jna.Structure")) : Native.class$com$sun$jna$Structure).isAssignableFrom(cls)) {
            return true;
        }
        try {
            return getNativeSize(cls) != 0;
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    public static void setCallbackExceptionHandler(final Callback.UncaughtExceptionHandler eh) {
        Native.callbackExceptionHandler = ((eh == null) ? Native.DEFAULT_HANDLER : eh);
    }
    
    public static Callback.UncaughtExceptionHandler getCallbackExceptionHandler() {
        return Native.callbackExceptionHandler;
    }
    
    public static void register(final String libName) {
        register(getNativeClass(getCallingClass()), NativeLibrary.getInstance(libName));
    }
    
    public static void register(final NativeLibrary lib) {
        register(getNativeClass(getCallingClass()), lib);
    }
    
    static Class getNativeClass(final Class cls) {
        final Method[] methods = cls.getDeclaredMethods();
        for (int i = 0; i < methods.length; ++i) {
            if ((methods[i].getModifiers() & 0x100) != 0x0) {
                return cls;
            }
        }
        final int idx = cls.getName().lastIndexOf("$");
        if (idx != -1) {
            final String name = cls.getName().substring(0, idx);
            try {
                return getNativeClass(Class.forName(name, true, cls.getClassLoader()));
            }
            catch (ClassNotFoundException ex) {}
        }
        throw new IllegalArgumentException("Can't determine class with native methods from the current context (" + cls + ")");
    }
    
    static Class getCallingClass() {
        final Class[] context = new SecurityManager() {
            public Class[] getClassContext() {
                return super.getClassContext();
            }
        }.getClassContext();
        if (context.length < 4) {
            throw new IllegalStateException("This method must be called from the static initializer of a class");
        }
        return context[3];
    }
    
    public static void unregister() {
        unregister(getNativeClass(getCallingClass()));
    }
    
    public static void unregister(final Class cls) {
        synchronized (Native.registeredClasses) {
            if (Native.registeredClasses.containsKey(cls)) {
                unregister(cls, Native.registeredClasses.get(cls));
                Native.registeredClasses.remove(cls);
                Native.registeredLibraries.remove(cls);
            }
        }
    }
    
    private static native void unregister(final Class p0, final long[] p1);
    
    private static String getSignature(final Class cls) {
        if (cls.isArray()) {
            return "[" + getSignature(cls.getComponentType());
        }
        if (cls.isPrimitive()) {
            if (cls == Void.TYPE) {
                return "V";
            }
            if (cls == Boolean.TYPE) {
                return "Z";
            }
            if (cls == Byte.TYPE) {
                return "B";
            }
            if (cls == Short.TYPE) {
                return "S";
            }
            if (cls == Character.TYPE) {
                return "C";
            }
            if (cls == Integer.TYPE) {
                return "I";
            }
            if (cls == Long.TYPE) {
                return "J";
            }
            if (cls == Float.TYPE) {
                return "F";
            }
            if (cls == Double.TYPE) {
                return "D";
            }
        }
        return "L" + replace(".", "/", cls.getName()) + ";";
    }
    
    static String replace(final String s1, final String s2, String str) {
        final StringBuffer buf = new StringBuffer();
        while (true) {
            final int idx = str.indexOf(s1);
            if (idx == -1) {
                break;
            }
            buf.append(str.substring(0, idx));
            buf.append(s2);
            str = str.substring(idx + s1.length());
        }
        buf.append(str);
        return buf.toString();
    }
    
    private static int getConversion(Class type, final TypeMapper mapper) {
        if (type == ((Native.class$java$lang$Boolean == null) ? (Native.class$java$lang$Boolean = class$("java.lang.Boolean")) : Native.class$java$lang$Boolean)) {
            type = Boolean.TYPE;
        }
        else if (type == ((Native.class$java$lang$Byte == null) ? (Native.class$java$lang$Byte = class$("java.lang.Byte")) : Native.class$java$lang$Byte)) {
            type = Byte.TYPE;
        }
        else if (type == ((Native.class$java$lang$Short == null) ? (Native.class$java$lang$Short = class$("java.lang.Short")) : Native.class$java$lang$Short)) {
            type = Short.TYPE;
        }
        else if (type == ((Native.class$java$lang$Character == null) ? (Native.class$java$lang$Character = class$("java.lang.Character")) : Native.class$java$lang$Character)) {
            type = Character.TYPE;
        }
        else if (type == ((Native.class$java$lang$Integer == null) ? (Native.class$java$lang$Integer = class$("java.lang.Integer")) : Native.class$java$lang$Integer)) {
            type = Integer.TYPE;
        }
        else if (type == ((Native.class$java$lang$Long == null) ? (Native.class$java$lang$Long = class$("java.lang.Long")) : Native.class$java$lang$Long)) {
            type = Long.TYPE;
        }
        else if (type == ((Native.class$java$lang$Float == null) ? (Native.class$java$lang$Float = class$("java.lang.Float")) : Native.class$java$lang$Float)) {
            type = Float.TYPE;
        }
        else if (type == ((Native.class$java$lang$Double == null) ? (Native.class$java$lang$Double = class$("java.lang.Double")) : Native.class$java$lang$Double)) {
            type = Double.TYPE;
        }
        else if (type == ((Native.class$java$lang$Void == null) ? (Native.class$java$lang$Void = class$("java.lang.Void")) : Native.class$java$lang$Void)) {
            type = Void.TYPE;
        }
        if (mapper != null && (mapper.getFromNativeConverter((Class)type) != null || mapper.getToNativeConverter((Class)type) != null)) {
            return 21;
        }
        if (((Native.class$com$sun$jna$Pointer == null) ? (Native.class$com$sun$jna$Pointer = class$("com.sun.jna.Pointer")) : Native.class$com$sun$jna$Pointer).isAssignableFrom((Class)type)) {
            return 1;
        }
        if (((Native.class$java$lang$String == null) ? (Native.class$java$lang$String = class$("java.lang.String")) : Native.class$java$lang$String) == type) {
            return 2;
        }
        if (((Native.class$com$sun$jna$WString == null) ? (Native.class$com$sun$jna$WString = class$("com.sun.jna.WString")) : Native.class$com$sun$jna$WString).isAssignableFrom((Class)type)) {
            return 18;
        }
        if (((Native.class$java$nio$Buffer == null) ? (Native.class$java$nio$Buffer = class$("java.nio.Buffer")) : Native.class$java$nio$Buffer).isAssignableFrom((Class)type)) {
            return 5;
        }
        if (((Native.class$com$sun$jna$Structure == null) ? (Native.class$com$sun$jna$Structure = class$("com.sun.jna.Structure")) : Native.class$com$sun$jna$Structure).isAssignableFrom((Class)type)) {
            if (((Native.class$com$sun$jna$Structure$ByValue == null) ? (Native.class$com$sun$jna$Structure$ByValue = class$("com.sun.jna.Structure$ByValue")) : Native.class$com$sun$jna$Structure$ByValue).isAssignableFrom((Class)type)) {
                return 4;
            }
            return 3;
        }
        else {
            if (((Class)type).isArray()) {
                switch (((Class)type).getName().charAt(1)) {
                    case 'Z': {
                        return 13;
                    }
                    case 'B': {
                        return 6;
                    }
                    case 'S': {
                        return 7;
                    }
                    case 'C': {
                        return 8;
                    }
                    case 'I': {
                        return 9;
                    }
                    case 'J': {
                        return 10;
                    }
                    case 'F': {
                        return 11;
                    }
                    case 'D': {
                        return 12;
                    }
                }
            }
            if (((Class)type).isPrimitive()) {
                return (type == Boolean.TYPE) ? 14 : 0;
            }
            if (((Native.class$com$sun$jna$Callback == null) ? (Native.class$com$sun$jna$Callback = class$("com.sun.jna.Callback")) : Native.class$com$sun$jna$Callback).isAssignableFrom((Class)type)) {
                return 15;
            }
            if (((Native.class$com$sun$jna$IntegerType == null) ? (Native.class$com$sun$jna$IntegerType = class$("com.sun.jna.IntegerType")) : Native.class$com$sun$jna$IntegerType).isAssignableFrom((Class)type)) {
                return 19;
            }
            if (((Native.class$com$sun$jna$PointerType == null) ? (Native.class$com$sun$jna$PointerType = class$("com.sun.jna.PointerType")) : Native.class$com$sun$jna$PointerType).isAssignableFrom((Class)type)) {
                return 20;
            }
            if (((Native.class$com$sun$jna$NativeMapped == null) ? (Native.class$com$sun$jna$NativeMapped = class$("com.sun.jna.NativeMapped")) : Native.class$com$sun$jna$NativeMapped).isAssignableFrom((Class)type)) {
                return 17;
            }
            return -1;
        }
    }
    
    public static void register(final Class cls, final NativeLibrary lib) {
        final Method[] methods = cls.getDeclaredMethods();
        final List mlist = new ArrayList();
        final TypeMapper mapper = lib.getOptions().get("type-mapper");
        for (int i = 0; i < methods.length; ++i) {
            if ((methods[i].getModifiers() & 0x100) != 0x0) {
                mlist.add(methods[i]);
            }
        }
        final long[] handles = new long[mlist.size()];
        for (int j = 0; j < handles.length; ++j) {
            final Method method = mlist.get(j);
            String sig = "(";
            final Class rclass = method.getReturnType();
            final Class[] ptypes = method.getParameterTypes();
            final long[] atypes = new long[ptypes.length];
            final long[] closure_atypes = new long[ptypes.length];
            final int[] cvt = new int[ptypes.length];
            final ToNativeConverter[] toNative = new ToNativeConverter[ptypes.length];
            FromNativeConverter fromNative = null;
            final int rcvt = getConversion(rclass, mapper);
            boolean throwLastError = false;
            long closure_rtype = 0L;
            long rtype = 0L;
            switch (rcvt) {
                case -1: {
                    throw new IllegalArgumentException(rclass + " is not a supported return type (in method " + method.getName() + " in " + cls + ")");
                }
                case 21: {
                    fromNative = mapper.getFromNativeConverter(rclass);
                    closure_rtype = Structure.FFIType.get(rclass).peer;
                    rtype = Structure.FFIType.get(fromNative.nativeType()).peer;
                    break;
                }
                case 17:
                case 19:
                case 20: {
                    closure_rtype = Structure.FFIType.get((Native.class$com$sun$jna$Pointer == null) ? (Native.class$com$sun$jna$Pointer = class$("com.sun.jna.Pointer")) : Native.class$com$sun$jna$Pointer).peer;
                    rtype = Structure.FFIType.get(NativeMappedConverter.getInstance(rclass).nativeType()).peer;
                    break;
                }
                case 3: {
                    rtype = (closure_rtype = Structure.FFIType.get((Native.class$com$sun$jna$Pointer == null) ? (Native.class$com$sun$jna$Pointer = class$("com.sun.jna.Pointer")) : Native.class$com$sun$jna$Pointer).peer);
                    break;
                }
                case 4: {
                    closure_rtype = Structure.FFIType.get((Native.class$com$sun$jna$Pointer == null) ? (Native.class$com$sun$jna$Pointer = class$("com.sun.jna.Pointer")) : Native.class$com$sun$jna$Pointer).peer;
                    rtype = Structure.FFIType.get(rclass).peer;
                    break;
                }
                default: {
                    rtype = (closure_rtype = Structure.FFIType.get(rclass).peer);
                    break;
                }
            }
            for (int t = 0; t < ptypes.length; ++t) {
                Class type = ptypes[t];
                sig += getSignature(type);
                cvt[t] = getConversion(type, mapper);
                if (cvt[t] == -1) {
                    throw new IllegalArgumentException(type + " is not a supported argument type (in method " + method.getName() + " in " + cls + ")");
                }
                if (cvt[t] == 17 || cvt[t] == 19) {
                    type = NativeMappedConverter.getInstance(type).nativeType();
                }
                else if (cvt[t] == 21) {
                    toNative[t] = mapper.getToNativeConverter(type);
                }
                switch (cvt[t]) {
                    case 4:
                    case 17:
                    case 19:
                    case 20: {
                        atypes[t] = Structure.FFIType.get(type).peer;
                        closure_atypes[t] = Structure.FFIType.get((Native.class$com$sun$jna$Pointer == null) ? (Native.class$com$sun$jna$Pointer = class$("com.sun.jna.Pointer")) : Native.class$com$sun$jna$Pointer).peer;
                        break;
                    }
                    case 21: {
                        if (type.isPrimitive()) {
                            closure_atypes[t] = Structure.FFIType.get(type).peer;
                        }
                        else {
                            closure_atypes[t] = Structure.FFIType.get((Native.class$com$sun$jna$Pointer == null) ? (Native.class$com$sun$jna$Pointer = class$("com.sun.jna.Pointer")) : Native.class$com$sun$jna$Pointer).peer;
                        }
                        atypes[t] = Structure.FFIType.get(toNative[t].nativeType()).peer;
                        break;
                    }
                    case 0: {
                        closure_atypes[t] = (atypes[t] = Structure.FFIType.get(type).peer);
                        break;
                    }
                    default: {
                        closure_atypes[t] = (atypes[t] = Structure.FFIType.get((Native.class$com$sun$jna$Pointer == null) ? (Native.class$com$sun$jna$Pointer = class$("com.sun.jna.Pointer")) : Native.class$com$sun$jna$Pointer).peer);
                        break;
                    }
                }
            }
            sig += ")";
            sig += getSignature(rclass);
            final Class[] etypes = method.getExceptionTypes();
            for (int e = 0; e < etypes.length; ++e) {
                if (((Native.class$com$sun$jna$LastErrorException == null) ? (Native.class$com$sun$jna$LastErrorException = class$("com.sun.jna.LastErrorException")) : Native.class$com$sun$jna$LastErrorException).isAssignableFrom(etypes[e])) {
                    throwLastError = true;
                    break;
                }
            }
            String name = method.getName();
            final FunctionMapper fmapper = lib.getOptions().get("function-mapper");
            if (fmapper != null) {
                name = fmapper.getFunctionName(lib, method);
            }
            final Function f = lib.getFunction(name, method);
            try {
                handles[j] = registerMethod(cls, method.getName(), sig, cvt, closure_atypes, atypes, rcvt, closure_rtype, rtype, rclass, f.peer, f.getCallingConvention(), throwLastError, toNative, fromNative);
            }
            catch (NoSuchMethodError e2) {
                throw new UnsatisfiedLinkError("No method " + method.getName() + " with signature " + sig + " in " + cls);
            }
        }
        synchronized (Native.registeredClasses) {
            Native.registeredClasses.put(cls, handles);
            Native.registeredLibraries.put(cls, lib);
        }
        cacheOptions(cls, lib.getOptions(), null);
    }
    
    private static void cacheOptions(final Class cls, final Map libOptions, final Object proxy) {
        synchronized (Native.libraries) {
            if (!libOptions.isEmpty()) {
                Native.options.put(cls, libOptions);
            }
            if (libOptions.containsKey("type-mapper")) {
                Native.typeMappers.put(cls, libOptions.get("type-mapper"));
            }
            if (libOptions.containsKey("structure-alignment")) {
                Native.alignments.put(cls, libOptions.get("structure-alignment"));
            }
            if (proxy != null) {
                Native.libraries.put(cls, new WeakReference<Object>(proxy));
            }
            if (!cls.isInterface() && ((Native.class$com$sun$jna$Library == null) ? (Native.class$com$sun$jna$Library = class$("com.sun.jna.Library")) : Native.class$com$sun$jna$Library).isAssignableFrom(cls)) {
                final Class[] ifaces = cls.getInterfaces();
                for (int i = 0; i < ifaces.length; ++i) {
                    if (((Native.class$com$sun$jna$Library == null) ? (Native.class$com$sun$jna$Library = class$("com.sun.jna.Library")) : Native.class$com$sun$jna$Library).isAssignableFrom(ifaces[i])) {
                        cacheOptions(ifaces[i], libOptions, proxy);
                        break;
                    }
                }
            }
        }
    }
    
    private static native long registerMethod(final Class p0, final String p1, final String p2, final int[] p3, final long[] p4, final long[] p5, final int p6, final long p7, final long p8, final Class p9, final long p10, final int p11, final boolean p12, final ToNativeConverter[] p13, final FromNativeConverter p14);
    
    private static NativeMapped fromNative(final Class cls, final Object value) {
        return (NativeMapped)NativeMappedConverter.getInstance(cls).fromNative(value, new FromNativeContext(cls));
    }
    
    private static Class nativeType(final Class cls) {
        return NativeMappedConverter.getInstance(cls).nativeType();
    }
    
    private static Object toNative(final ToNativeConverter cvt, final Object o) {
        return cvt.toNative(o, new ToNativeContext());
    }
    
    private static Object fromNative(final FromNativeConverter cvt, final Object o, final Class cls) {
        return cvt.fromNative(o, new FromNativeContext(cls));
    }
    
    public static native long ffi_prep_cif(final int p0, final int p1, final long p2, final long p3);
    
    public static native void ffi_call(final long p0, final long p1, final long p2, final long p3);
    
    public static native long ffi_prep_closure(final long p0, final ffi_callback p1);
    
    public static native void ffi_free_closure(final long p0);
    
    static native int initialize_ffi_type(final long p0);
    
    public static void main(final String[] args) {
        final String DEFAULT_TITLE = "Java Native Access (JNA)";
        final String DEFAULT_VERSION = "3.2.7";
        final String DEFAULT_BUILD = "3.2.7 (package information missing)";
        final Package pkg = ((Native.class$com$sun$jna$Native == null) ? (Native.class$com$sun$jna$Native = class$("com.sun.jna.Native")) : Native.class$com$sun$jna$Native).getPackage();
        String title = pkg.getSpecificationTitle();
        if (title == null) {
            title = "Java Native Access (JNA)";
        }
        String version = pkg.getSpecificationVersion();
        if (version == null) {
            version = "3.2.7";
        }
        title = title + " API Version " + version;
        System.out.println(title);
        version = pkg.getImplementationVersion();
        if (version == null) {
            version = "3.2.7 (package information missing)";
        }
        System.out.println("Version: " + version);
        System.out.println(" Native: " + getNativeVersion() + " (" + getAPIChecksum() + ")");
        System.exit(0);
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
        Native.nativeLibraryPath = null;
        Native.typeMappers = new WeakHashMap();
        Native.alignments = new WeakHashMap();
        Native.options = new WeakHashMap();
        Native.libraries = new WeakHashMap();
        DEFAULT_HANDLER = new Callback.UncaughtExceptionHandler() {
            public void uncaughtException(final Callback c, final Throwable e) {
                System.err.println("JNA: Callback " + c + " threw the following exception:");
                e.printStackTrace();
            }
        };
        Native.callbackExceptionHandler = Native.DEFAULT_HANDLER;
        loadNativeLibrary();
        POINTER_SIZE = sizeof(0);
        LONG_SIZE = sizeof(1);
        WCHAR_SIZE = sizeof(2);
        SIZE_T_SIZE = sizeof(3);
        initIDs();
        if (Boolean.getBoolean("jna.protected")) {
            setProtected(true);
        }
        finalizer = new Object() {
            protected void finalize() {
                deleteNativeLibrary();
            }
        };
        lastError = new ThreadLocal() {
            protected synchronized Object initialValue() {
                return new Integer(0);
            }
        };
        Native.registeredClasses = new HashMap();
        Native.registeredLibraries = new HashMap();
        Native.unloader = new Object() {
            protected void finalize() {
                synchronized (Native.registeredClasses) {
                    final Iterator i = Native.registeredClasses.entrySet().iterator();
                    while (i.hasNext()) {
                        final Map.Entry e = i.next();
                        unregister(e.getKey(), e.getValue());
                        i.remove();
                    }
                }
            }
        };
    }
    
    public static class DeleteNativeLibrary extends Thread
    {
        private final File file;
        
        public DeleteNativeLibrary(final File file) {
            this.file = file;
        }
        
        public void run() {
            if (!deleteNativeLibrary()) {
                try {
                    Runtime.getRuntime().exec(new String[] { System.getProperty("java.home") + "/bin/java", "-cp", System.getProperty("java.class.path"), this.getClass().getName(), this.file.getAbsolutePath() });
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        public static void main(final String[] args) {
            if (args.length == 1) {
                final File file = new File(args[0]);
                if (file.exists()) {
                    final long start = System.currentTimeMillis();
                    while (!file.delete() && file.exists()) {
                        try {
                            Thread.sleep(10L);
                        }
                        catch (InterruptedException ex) {}
                        if (System.currentTimeMillis() - start > 5000L) {
                            System.err.println("Could not remove temp file: " + file.getAbsolutePath());
                            break;
                        }
                    }
                }
            }
            System.exit(0);
        }
    }
    
    public interface ffi_callback
    {
        void invoke(final long p0, final long p1, final long p2);
    }
}
