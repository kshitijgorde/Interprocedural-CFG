// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public class DefaultLoaderRepository
{
    public static Class loadClass(final String className) throws ClassNotFoundException {
        throw new ClassNotFoundException("Do not use, see MBeanServer.getClassLoaderRepository()");
    }
    
    public static Class loadClassWithout(final ClassLoader loader, final String className) throws ClassNotFoundException {
        throw new ClassNotFoundException("Do not use, see MBeanServer.getClassLoaderRepository()");
    }
}
