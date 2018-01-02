// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.util;

public class ReflectionHelper
{
    public static Object a(final String s, final Object[] array, final Class clazz) {
        final ClassLoader classLoader = clazz.getClassLoader();
        Object instance = null;
        try {
            final Class<?> loadClass = classLoader.loadClass(s);
            final Class[] array2 = new Class[array.length];
            for (int i = 0; i < array2.length; ++i) {
                if (array[i] == null) {
                    System.out.println("Null param in args: " + i);
                }
                array2[i] = array[i].getClass();
            }
            instance = loadClass.getConstructor((Class<?>[])array2).newInstance(array);
        }
        catch (Exception ex) {
            System.out.println("LOAD ERROR: " + ex);
            ex.printStackTrace(System.out);
        }
        return instance;
    }
}
