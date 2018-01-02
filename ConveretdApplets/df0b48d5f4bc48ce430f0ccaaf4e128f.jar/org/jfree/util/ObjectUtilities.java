// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Collection;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public final class ObjectUtilities
{
    public static final String THREAD_CONTEXT = "ThreadContext";
    public static final String CLASS_CONTEXT = "ClassContext";
    private static String classLoaderSource;
    private static ClassLoader classLoader;
    static /* synthetic */ Class class$org$jfree$util$ObjectUtilities;
    
    static {
        ObjectUtilities.classLoaderSource = "ThreadContext";
    }
    
    static /* synthetic */ Class class$(final String class$) {
        try {
            return Class.forName(class$);
        }
        catch (ClassNotFoundException forName) {
            throw new NoClassDefFoundError(forName.getMessage());
        }
    }
    
    public static Object clone(final Object object) throws CloneNotSupportedException {
        if (object == null) {
            throw new IllegalArgumentException("Null 'object' argument.");
        }
        if (object instanceof PublicCloneable) {
            final PublicCloneable pc = (PublicCloneable)object;
            return pc.clone();
        }
        try {
            final Method method = object.getClass().getMethod("clone", (Class<?>[])null);
            if (Modifier.isPublic(method.getModifiers())) {
                return method.invoke(object, (Object[])null);
            }
        }
        catch (NoSuchMethodException ex) {
            Log.warn("Object without clone() method is impossible.");
        }
        catch (IllegalAccessException ex2) {
            Log.warn("Object.clone(): unable to call method.");
        }
        catch (InvocationTargetException ex3) {
            Log.warn("Object without clone() method is impossible.");
        }
        throw new CloneNotSupportedException("Failed to clone.");
    }
    
    private static String convertName(final String name, Class c) {
        if (name.startsWith("/")) {
            return name.substring(1);
        }
        while (c.isArray()) {
            c = c.getComponentType();
        }
        final String baseName = c.getName();
        final int index = baseName.lastIndexOf(46);
        if (index == -1) {
            return name;
        }
        final String pkgName = baseName.substring(0, index);
        return String.valueOf(pkgName.replace('.', '/')) + "/" + name;
    }
    
    public static Collection deepClone(final Collection collection) throws CloneNotSupportedException {
        if (collection == null) {
            throw new IllegalArgumentException("Null 'collection' argument.");
        }
        final Collection result = (Collection)clone(collection);
        result.clear();
        for (final Object item : collection) {
            if (item != null) {
                result.add(clone(item));
            }
            else {
                result.add(null);
            }
        }
        return result;
    }
    
    public static boolean equal(final Object o1, final Object o2) {
        return o1 == o2 || (o1 != null && o1.equals(o2));
    }
    
    public static ClassLoader getClassLoader() {
        return ObjectUtilities.classLoader;
    }
    
    public static synchronized ClassLoader getClassLoader(final Class c) {
        if (ObjectUtilities.classLoader != null) {
            return ObjectUtilities.classLoader;
        }
        if ("ThreadContext".equals(ObjectUtilities.classLoaderSource)) {
            final ClassLoader threadLoader = Thread.currentThread().getContextClassLoader();
            if (threadLoader != null) {
                return threadLoader;
            }
        }
        final ClassLoader applicationCL = c.getClassLoader();
        if (applicationCL == null) {
            return ClassLoader.getSystemClassLoader();
        }
        return applicationCL;
    }
    
    public static String getClassLoaderSource() {
        return ObjectUtilities.classLoaderSource;
    }
    
    public static URL getResource(final String name, final Class c) {
        final ClassLoader cl = getClassLoader(c);
        if (cl == null) {
            return null;
        }
        return cl.getResource(name);
    }
    
    public static InputStream getResourceAsStream(final String name, final Class context) {
        final URL url = getResource(name, context);
        if (url == null) {
            return null;
        }
        try {
            return url.openStream();
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public static URL getResourceRelative(final String name, final Class c) {
        final ClassLoader cl = getClassLoader(c);
        final String cname = convertName(name, c);
        if (cl == null) {
            return null;
        }
        return cl.getResource(cname);
    }
    
    public static InputStream getResourceRelativeAsStream(final String name, final Class context) {
        final URL url = getResourceRelative(name, context);
        if (url == null) {
            return null;
        }
        try {
            return url.openStream();
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public static int hashCode(final Object object) {
        int result = 0;
        if (object != null) {
            result = object.hashCode();
        }
        return result;
    }
    
    public static boolean isJDK14() {
        final ClassLoader loader = getClassLoader((ObjectUtilities.class$org$jfree$util$ObjectUtilities != null) ? ObjectUtilities.class$org$jfree$util$ObjectUtilities : (ObjectUtilities.class$org$jfree$util$ObjectUtilities = class$("org.jfree.util.ObjectUtilities")));
        if (loader != null) {
            try {
                loader.loadClass("java.util.RandomAccess");
                return true;
            }
            catch (ClassNotFoundException ex) {
                return false;
            }
            catch (Exception ex2) {}
        }
        try {
            final String version = System.getProperty("java.vm.specification.version");
            if (version == null) {
                return false;
            }
            final String[] versions = parseVersions(version);
            final String[] target = { "1", "4" };
            return ArrayUtilities.compareVersionArrays(versions, target) >= 0;
        }
        catch (Exception ex3) {
            return false;
        }
    }
    
    public static Object loadAndInstantiate(final String className, final Class source) {
        try {
            final ClassLoader loader = getClassLoader(source);
            final Class c = loader.loadClass(className);
            return c.newInstance();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static Object loadAndInstantiate(final String className, final Class source, final Class type) {
        try {
            final ClassLoader loader = getClassLoader(source);
            final Class c = loader.loadClass(className);
            if (type.isAssignableFrom(c)) {
                return c.newInstance();
            }
        }
        catch (Exception ex) {
            return null;
        }
        return null;
    }
    
    private static String[] parseVersions(final String version) {
        if (version == null) {
            return new String[0];
        }
        final ArrayList versions = new ArrayList();
        final StringTokenizer strtok = new StringTokenizer(version, ".");
        while (strtok.hasMoreTokens()) {
            versions.add(strtok.nextToken());
        }
        return versions.toArray(new String[versions.size()]);
    }
    
    public static synchronized void setClassLoader(final ClassLoader classLoader) {
        ObjectUtilities.classLoader = classLoader;
    }
    
    public static void setClassLoaderSource(final String classLoaderSource) {
        ObjectUtilities.classLoaderSource = classLoaderSource;
    }
}
