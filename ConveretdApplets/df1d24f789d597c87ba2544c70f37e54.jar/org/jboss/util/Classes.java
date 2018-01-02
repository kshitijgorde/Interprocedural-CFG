// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.util.HashMap;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.reflect.Array;
import java.util.List;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.security.CodeSource;
import java.net.URL;
import java.util.Map;

public final class Classes
{
    public static final String PACKAGE_SEPARATOR = ".";
    public static final char PACKAGE_SEPARATOR_CHAR = '.';
    public static final String DEFAULT_PACKAGE_NAME = "<default>";
    private static final Map PRIMITIVE_NAME_TYPE_MAP;
    private static final Class[] PRIMITIVE_WRAPPER_MAP;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Short;
    
    public static void displayClassInfo(final Class clazz, final StringBuffer results) {
        final ClassLoader cl = clazz.getClassLoader();
        results.append("\n");
        results.append(clazz.getName());
        results.append("(");
        results.append(Integer.toHexString(clazz.hashCode()));
        results.append(").ClassLoader=");
        results.append(cl);
        for (ClassLoader parent = cl; parent != null; parent = parent.getParent()) {
            results.append("\n..");
            results.append(parent);
            final URL[] urls = getClassLoaderURLs(parent);
            for (int length = (urls != null) ? urls.length : 0, u = 0; u < length; ++u) {
                results.append("\n....");
                results.append(urls[u]);
            }
            if (parent != null) {}
        }
        final CodeSource clazzCS = clazz.getProtectionDomain().getCodeSource();
        if (clazzCS != null) {
            results.append("\n++++CodeSource: ");
            results.append(clazzCS);
        }
        else {
            results.append("\n++++Null CodeSource");
        }
        results.append("\nImplemented Interfaces:");
        final Class[] ifaces = clazz.getInterfaces();
        for (int i = 0; i < ifaces.length; ++i) {
            final Class iface = ifaces[i];
            results.append("\n++");
            results.append(iface);
            results.append("(");
            results.append(Integer.toHexString(iface.hashCode()));
            results.append(")");
            final ClassLoader loader = ifaces[i].getClassLoader();
            results.append("\n++++ClassLoader: ");
            results.append(loader);
            final ProtectionDomain pd = ifaces[i].getProtectionDomain();
            final CodeSource cs = pd.getCodeSource();
            if (cs != null) {
                results.append("\n++++CodeSource: ");
                results.append(cs);
            }
            else {
                results.append("\n++++Null CodeSource");
            }
        }
    }
    
    public static URL[] getClassLoaderURLs(final ClassLoader cl) {
        URL[] urls = new URL[0];
        try {
            final Class returnType = urls.getClass();
            final Class[] parameterTypes = new Class[0];
            final Class clClass = cl.getClass();
            final Method getURLs = clClass.getMethod("getURLs", (Class[])parameterTypes);
            if (returnType.isAssignableFrom(getURLs.getReturnType())) {
                final Object[] args = new Object[0];
                urls = (URL[])getURLs.invoke(cl, args);
            }
            if (urls == null || urls.length == 0) {
                final Method getCp = clClass.getMethod("getClasspath", (Class[])parameterTypes);
                if (returnType.isAssignableFrom(getCp.getReturnType())) {
                    final Object[] args2 = new Object[0];
                    urls = (URL[])getCp.invoke(cl, args2);
                }
            }
        }
        catch (Exception ex) {}
        return urls;
    }
    
    public static String getDescription(final Object object) {
        final StringBuffer buffer = new StringBuffer();
        describe(buffer, object);
        return buffer.toString();
    }
    
    public static void describe(final StringBuffer buffer, final Object object) {
        if (object == null) {
            buffer.append("**null**");
        }
        else {
            describe(buffer, object.getClass());
        }
    }
    
    public static void describe(final StringBuffer buffer, final Class clazz) {
        if (clazz == null) {
            buffer.append("**null**");
        }
        else {
            buffer.append("{class=").append(clazz.getName());
            final Class[] intfs = clazz.getInterfaces();
            if (intfs.length > 0) {
                buffer.append(" intfs=");
                for (int i = 0; i < intfs.length; ++i) {
                    buffer.append(intfs[i].getName());
                    if (i < intfs.length - 1) {
                        buffer.append(", ");
                    }
                }
            }
            buffer.append("}");
        }
    }
    
    public static String stripPackageName(final String classname) {
        final int idx = classname.lastIndexOf(".");
        if (idx != -1) {
            return classname.substring(idx + 1, classname.length());
        }
        return classname;
    }
    
    public static String stripPackageName(final Class type) {
        return stripPackageName(type.getName());
    }
    
    public static String getPackageName(final String classname) {
        if (classname.length() == 0) {
            throw new EmptyStringException();
        }
        final int index = classname.lastIndexOf(".");
        if (index != -1) {
            return classname.substring(0, index);
        }
        return "";
    }
    
    public static String getPackageName(final Class type) {
        return getPackageName(type.getName());
    }
    
    public static void forceLoad(final Class type) {
        if (type == null) {
            throw new NullArgumentException("type");
        }
        if (type.isPrimitive()) {
            return;
        }
        final String packageName = getPackageName(type);
        if (packageName.startsWith("java.") || packageName.startsWith("javax.")) {
            return;
        }
        try {
            final Method[] methods = type.getDeclaredMethods();
            Method method = null;
            for (int i = 0; i < methods.length; ++i) {
                final int modifiers = methods[i].getModifiers();
                if (Modifier.isStatic(modifiers)) {
                    method = methods[i];
                    break;
                }
            }
            if (method != null) {
                method.invoke(null, (Object[])null);
            }
            else {
                type.newInstance();
            }
        }
        catch (Exception ignore) {
            ThrowableHandler.add(ignore);
        }
    }
    
    public static Class getPrimitiveTypeForName(final String name) {
        return Classes.PRIMITIVE_NAME_TYPE_MAP.get(name);
    }
    
    public static Class getPrimitiveWrapper(final Class type) {
        if (!type.isPrimitive()) {
            throw new IllegalArgumentException("type is not a primitive class");
        }
        for (int i = 0; i < Classes.PRIMITIVE_WRAPPER_MAP.length; i += 2) {
            if (type.equals(Classes.PRIMITIVE_WRAPPER_MAP[i])) {
                return Classes.PRIMITIVE_WRAPPER_MAP[i + 1];
            }
        }
        throw new UnreachableStatementException();
    }
    
    public static void getAllInterfaces(final List allIfaces, Class c) {
        while (c != null) {
            final Class[] ifaces = c.getInterfaces();
            for (int n = 0; n < ifaces.length; ++n) {
                allIfaces.add(ifaces[n]);
            }
            c = c.getSuperclass();
        }
    }
    
    public static boolean isPrimitiveWrapper(final Class type) {
        for (int i = 0; i < Classes.PRIMITIVE_WRAPPER_MAP.length; i += 2) {
            if (type.equals(Classes.PRIMITIVE_WRAPPER_MAP[i + 1])) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isPrimitive(final Class type) {
        return type.isPrimitive() || isPrimitiveWrapper(type);
    }
    
    public static boolean isPrimitive(final String type) {
        return Classes.PRIMITIVE_NAME_TYPE_MAP.containsKey(type);
    }
    
    public static Class getPrimitive(final Class wrapper) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       org/jboss/util/Classes.class$java$lang$Integer:Ljava/lang/Class;
        //     3: ifnonnull       18
        //     6: ldc             "java.lang.Integer"
        //     8: invokestatic    org/jboss/util/Classes.class$:(Ljava/lang/String;)Ljava/lang/Class;
        //    11: dup            
        //    12: putstatic       org/jboss/util/Classes.class$java$lang$Integer:Ljava/lang/Class;
        //    15: goto            21
        //    18: getstatic       org/jboss/util/Classes.class$java$lang$Integer:Ljava/lang/Class;
        //    21: aload_0         /* wrapper */
        //    22: if_acmpne       32
        //    25: getstatic       java/lang/Integer.TYPE:Ljava/lang/Class;
        //    28: astore_1        /* primitive */
        //    29: goto            283
        //    32: getstatic       org/jboss/util/Classes.class$java$lang$Long:Ljava/lang/Class;
        //    35: ifnonnull       50
        //    38: ldc             "java.lang.Long"
        //    40: invokestatic    org/jboss/util/Classes.class$:(Ljava/lang/String;)Ljava/lang/Class;
        //    43: dup            
        //    44: putstatic       org/jboss/util/Classes.class$java$lang$Long:Ljava/lang/Class;
        //    47: goto            53
        //    50: getstatic       org/jboss/util/Classes.class$java$lang$Long:Ljava/lang/Class;
        //    53: aload_0         /* wrapper */
        //    54: if_acmpne       64
        //    57: getstatic       java/lang/Long.TYPE:Ljava/lang/Class;
        //    60: astore_1        /* primitive */
        //    61: goto            283
        //    64: getstatic       org/jboss/util/Classes.class$java$lang$Double:Ljava/lang/Class;
        //    67: ifnonnull       82
        //    70: ldc             "java.lang.Double"
        //    72: invokestatic    org/jboss/util/Classes.class$:(Ljava/lang/String;)Ljava/lang/Class;
        //    75: dup            
        //    76: putstatic       org/jboss/util/Classes.class$java$lang$Double:Ljava/lang/Class;
        //    79: goto            85
        //    82: getstatic       org/jboss/util/Classes.class$java$lang$Double:Ljava/lang/Class;
        //    85: aload_0         /* wrapper */
        //    86: if_acmpne       96
        //    89: getstatic       java/lang/Double.TYPE:Ljava/lang/Class;
        //    92: astore_1        /* primitive */
        //    93: goto            283
        //    96: getstatic       org/jboss/util/Classes.class$java$lang$Boolean:Ljava/lang/Class;
        //    99: ifnonnull       114
        //   102: ldc             "java.lang.Boolean"
        //   104: invokestatic    org/jboss/util/Classes.class$:(Ljava/lang/String;)Ljava/lang/Class;
        //   107: dup            
        //   108: putstatic       org/jboss/util/Classes.class$java$lang$Boolean:Ljava/lang/Class;
        //   111: goto            117
        //   114: getstatic       org/jboss/util/Classes.class$java$lang$Boolean:Ljava/lang/Class;
        //   117: aload_0         /* wrapper */
        //   118: if_acmpne       128
        //   121: getstatic       java/lang/Boolean.TYPE:Ljava/lang/Class;
        //   124: astore_1        /* primitive */
        //   125: goto            283
        //   128: getstatic       org/jboss/util/Classes.class$java$lang$Short:Ljava/lang/Class;
        //   131: ifnonnull       146
        //   134: ldc             "java.lang.Short"
        //   136: invokestatic    org/jboss/util/Classes.class$:(Ljava/lang/String;)Ljava/lang/Class;
        //   139: dup            
        //   140: putstatic       org/jboss/util/Classes.class$java$lang$Short:Ljava/lang/Class;
        //   143: goto            149
        //   146: getstatic       org/jboss/util/Classes.class$java$lang$Short:Ljava/lang/Class;
        //   149: aload_0         /* wrapper */
        //   150: if_acmpne       160
        //   153: getstatic       java/lang/Short.TYPE:Ljava/lang/Class;
        //   156: astore_1        /* primitive */
        //   157: goto            283
        //   160: getstatic       org/jboss/util/Classes.class$java$lang$Float:Ljava/lang/Class;
        //   163: ifnonnull       178
        //   166: ldc             "java.lang.Float"
        //   168: invokestatic    org/jboss/util/Classes.class$:(Ljava/lang/String;)Ljava/lang/Class;
        //   171: dup            
        //   172: putstatic       org/jboss/util/Classes.class$java$lang$Float:Ljava/lang/Class;
        //   175: goto            181
        //   178: getstatic       org/jboss/util/Classes.class$java$lang$Float:Ljava/lang/Class;
        //   181: aload_0         /* wrapper */
        //   182: if_acmpne       192
        //   185: getstatic       java/lang/Float.TYPE:Ljava/lang/Class;
        //   188: astore_1        /* primitive */
        //   189: goto            283
        //   192: getstatic       org/jboss/util/Classes.class$java$lang$Byte:Ljava/lang/Class;
        //   195: ifnonnull       210
        //   198: ldc             "java.lang.Byte"
        //   200: invokestatic    org/jboss/util/Classes.class$:(Ljava/lang/String;)Ljava/lang/Class;
        //   203: dup            
        //   204: putstatic       org/jboss/util/Classes.class$java$lang$Byte:Ljava/lang/Class;
        //   207: goto            213
        //   210: getstatic       org/jboss/util/Classes.class$java$lang$Byte:Ljava/lang/Class;
        //   213: aload_0         /* wrapper */
        //   214: if_acmpne       224
        //   217: getstatic       java/lang/Byte.TYPE:Ljava/lang/Class;
        //   220: astore_1        /* primitive */
        //   221: goto            283
        //   224: getstatic       org/jboss/util/Classes.class$java$lang$Character:Ljava/lang/Class;
        //   227: ifnonnull       242
        //   230: ldc             "java.lang.Character"
        //   232: invokestatic    org/jboss/util/Classes.class$:(Ljava/lang/String;)Ljava/lang/Class;
        //   235: dup            
        //   236: putstatic       org/jboss/util/Classes.class$java$lang$Character:Ljava/lang/Class;
        //   239: goto            245
        //   242: getstatic       org/jboss/util/Classes.class$java$lang$Character:Ljava/lang/Class;
        //   245: aload_0         /* wrapper */
        //   246: if_acmpne       256
        //   249: getstatic       java/lang/Character.TYPE:Ljava/lang/Class;
        //   252: astore_1        /* primitive */
        //   253: goto            283
        //   256: new             Ljava/lang/IllegalArgumentException;
        //   259: dup            
        //   260: new             Ljava/lang/StringBuffer;
        //   263: dup            
        //   264: invokespecial   java/lang/StringBuffer.<init>:()V
        //   267: ldc             "The class is not a primitive wrapper type: "
        //   269: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   272: aload_0         /* wrapper */
        //   273: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/Object;)Ljava/lang/StringBuffer;
        //   276: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   279: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   282: athrow         
        //   283: aload_1        
        //   284: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name       Signature
        //  -----  ------  ----  ---------  -----------------
        //  29     3       1     primitive  Ljava/lang/Class;
        //  61     3       1     primitive  Ljava/lang/Class;
        //  93     3       1     primitive  Ljava/lang/Class;
        //  125    3       1     primitive  Ljava/lang/Class;
        //  157    3       1     primitive  Ljava/lang/Class;
        //  189    3       1     primitive  Ljava/lang/Class;
        //  221    3       1     primitive  Ljava/lang/Class;
        //  253    3       1     primitive  Ljava/lang/Class;
        //  0      285     0     wrapper    Ljava/lang/Class;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static Object instantiate(final Class expected, final String property, final String defaultClassName) {
        final String className = getProperty(property, defaultClassName);
        Class clazz = null;
        try {
            clazz = loadClass(className);
        }
        catch (ClassNotFoundException e) {
            throw new NestedRuntimeException("Cannot load class " + className, e);
        }
        Object result = null;
        try {
            result = clazz.newInstance();
        }
        catch (InstantiationException e2) {
            throw new NestedRuntimeException("Error instantiating " + className, e2);
        }
        catch (IllegalAccessException e3) {
            throw new NestedRuntimeException("Error instantiating " + className, e3);
        }
        if (!expected.isAssignableFrom(clazz)) {
            throw new NestedRuntimeException("Class " + className + " from classloader " + clazz.getClassLoader() + " is not of the expected class " + expected + " loaded from " + expected.getClassLoader());
        }
        return result;
    }
    
    public static Class loadClass(final String className) throws ClassNotFoundException {
        return loadClass(className, Thread.currentThread().getContextClassLoader());
    }
    
    public static Class loadClass(final String className, final ClassLoader classLoader) throws ClassNotFoundException {
        if (className.length() == 1) {
            final char type = className.charAt(0);
            if (type == 'B') {
                return Byte.TYPE;
            }
            if (type == 'C') {
                return Character.TYPE;
            }
            if (type == 'D') {
                return Double.TYPE;
            }
            if (type == 'F') {
                return Float.TYPE;
            }
            if (type == 'I') {
                return Integer.TYPE;
            }
            if (type == 'J') {
                return Long.TYPE;
            }
            if (type == 'S') {
                return Short.TYPE;
            }
            if (type == 'Z') {
                return Boolean.TYPE;
            }
            if (type == 'V') {
                return Void.TYPE;
            }
            throw new ClassNotFoundException(className);
        }
        else {
            if (isPrimitive(className)) {
                return Classes.PRIMITIVE_NAME_TYPE_MAP.get(className);
            }
            if (className.charAt(0) == 'L' && className.charAt(className.length() - 1) == ';') {
                return classLoader.loadClass(className.substring(1, className.length() - 1));
            }
            try {
                return classLoader.loadClass(className);
            }
            catch (ClassNotFoundException e) {
                if (className.charAt(0) != '[') {
                    throw e;
                }
                int arrayDimension;
                for (arrayDimension = 0; className.charAt(arrayDimension) == '['; ++arrayDimension) {}
                final Class componentType = loadClass(className.substring(arrayDimension), classLoader);
                return Array.newInstance(componentType, new int[arrayDimension]).getClass();
            }
        }
    }
    
    public static final Class[] convertToJavaClasses(final Iterator it, final ClassLoader cl) throws ClassNotFoundException {
        final ArrayList classes = new ArrayList();
        while (it.hasNext()) {
            classes.add(convertToJavaClass(it.next(), cl));
        }
        return classes.toArray(new Class[classes.size()]);
    }
    
    public static final Method getAttributeGetter(final Class cls, final String attr) throws NoSuchMethodException {
        final StringBuffer buf = new StringBuffer(attr.length() + 3);
        buf.append("get");
        if (Character.isLowerCase(attr.charAt(0))) {
            buf.append(Character.toUpperCase(attr.charAt(0))).append(attr.substring(1));
        }
        else {
            buf.append(attr);
        }
        try {
            return cls.getMethod(buf.toString(), (Class[])null);
        }
        catch (NoSuchMethodException e) {
            buf.replace(0, 3, "is");
            return cls.getMethod(buf.toString(), (Class[])null);
        }
    }
    
    public static final Method getAttributeSetter(final Class cls, final String attr, final Class type) throws NoSuchMethodException {
        final StringBuffer buf = new StringBuffer(attr.length() + 3);
        buf.append("set");
        if (Character.isLowerCase(attr.charAt(0))) {
            buf.append(Character.toUpperCase(attr.charAt(0))).append(attr.substring(1));
        }
        else {
            buf.append(attr);
        }
        return cls.getMethod(buf.toString(), type);
    }
    
    private static final Class convertToJavaClass(String name, final ClassLoader cl) throws ClassNotFoundException {
        int arraySize;
        for (arraySize = 0; name.endsWith("[]"); name = name.substring(0, name.length() - 2), ++arraySize) {}
        Class c = Classes.PRIMITIVE_NAME_TYPE_MAP.get(name);
        if (c == null) {
            try {
                c = cl.loadClass(name);
            }
            catch (ClassNotFoundException cnfe) {
                throw new ClassNotFoundException("Parameter class not found: " + name);
            }
        }
        if (arraySize > 0) {
            final int[] dims = new int[arraySize];
            for (int i = 0; i < arraySize; ++i) {
                dims[i] = 1;
            }
            c = Array.newInstance(c, dims).getClass();
        }
        return c;
    }
    
    private static String getProperty(final String name, final String defaultValue) {
        return AccessController.doPrivileged((PrivilegedAction<String>)new PrivilegedAction() {
            public Object run() {
                return System.getProperty(name, defaultValue);
            }
        });
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
        (PRIMITIVE_NAME_TYPE_MAP = new HashMap()).put("boolean", Boolean.TYPE);
        Classes.PRIMITIVE_NAME_TYPE_MAP.put("byte", Byte.TYPE);
        Classes.PRIMITIVE_NAME_TYPE_MAP.put("char", Character.TYPE);
        Classes.PRIMITIVE_NAME_TYPE_MAP.put("short", Short.TYPE);
        Classes.PRIMITIVE_NAME_TYPE_MAP.put("int", Integer.TYPE);
        Classes.PRIMITIVE_NAME_TYPE_MAP.put("long", Long.TYPE);
        Classes.PRIMITIVE_NAME_TYPE_MAP.put("float", Float.TYPE);
        Classes.PRIMITIVE_NAME_TYPE_MAP.put("double", Double.TYPE);
        PRIMITIVE_WRAPPER_MAP = new Class[] { Boolean.TYPE, (Classes.class$java$lang$Boolean == null) ? (Classes.class$java$lang$Boolean = class$("java.lang.Boolean")) : Classes.class$java$lang$Boolean, Byte.TYPE, (Classes.class$java$lang$Byte == null) ? (Classes.class$java$lang$Byte = class$("java.lang.Byte")) : Classes.class$java$lang$Byte, Character.TYPE, (Classes.class$java$lang$Character == null) ? (Classes.class$java$lang$Character = class$("java.lang.Character")) : Classes.class$java$lang$Character, Double.TYPE, (Classes.class$java$lang$Double == null) ? (Classes.class$java$lang$Double = class$("java.lang.Double")) : Classes.class$java$lang$Double, Float.TYPE, (Classes.class$java$lang$Float == null) ? (Classes.class$java$lang$Float = class$("java.lang.Float")) : Classes.class$java$lang$Float, Integer.TYPE, (Classes.class$java$lang$Integer == null) ? (Classes.class$java$lang$Integer = class$("java.lang.Integer")) : Classes.class$java$lang$Integer, Long.TYPE, (Classes.class$java$lang$Long == null) ? (Classes.class$java$lang$Long = class$("java.lang.Long")) : Classes.class$java$lang$Long, Short.TYPE, (Classes.class$java$lang$Short == null) ? (Classes.class$java$lang$Short = class$("java.lang.Short")) : Classes.class$java$lang$Short };
    }
}
