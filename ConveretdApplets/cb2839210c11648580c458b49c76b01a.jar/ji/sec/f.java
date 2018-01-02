// 
// Decompiled by Procyon v0.5.30
// 

package ji.sec;

import java.awt.Toolkit;
import java.awt.PrintJob;
import java.awt.Frame;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import ji.util.d;
import ji.io.ac;
import java.util.Vector;
import java.io.FileOutputStream;
import ji.io.q;
import java.io.FileInputStream;
import java.io.File;
import ji.io.h;
import ji.util.i;
import java.util.Properties;
import netscape.security.PrivilegeManager;
import java.util.Hashtable;

public class f
{
    static boolean a;
    private static Hashtable b;
    static /* synthetic */ Class c;
    
    private static final void a() {
        f.a = (g.a() && !g.b());
    }
    
    public static void a(final String s) {
    }
    
    public static String a(final String s, final String s2) {
        a(s2);
        a();
        String s3 = null;
        try {
            boolean b = false;
            try {
                s3 = System.getProperty(s);
            }
            catch (Exception ex) {
                b = true;
            }
            if (s3 == null && (f.a || b) && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex2) {
            g.r = true;
        }
        if (s3 == null) {
            s3 = System.getProperty(s);
        }
        return s3;
    }
    
    public static final Properties b(final String s) {
        a(s);
        a();
        try {
            if (f.a && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
                PrivilegeManager.enablePrivilege("UniversalPropertyWrite");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        return System.getProperties();
    }
    
    public static void a(final String s, final Object o, final String s2, String b) {
        a(s2);
        a();
        try {
            if (f.a && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex2) {
            g.r = true;
        }
        try {
            if (d(s)) {
                if (i.c(83)) {
                    h.d(s2, String.valueOf(String.valueOf(new StringBuffer("Library ").append(s).append(" already loaded"))));
                }
                if (c(s, s2) && i.c(83)) {
                    h.d(s2, String.valueOf(String.valueOf(new StringBuffer("Library ").append(s).append(" already loaded for instance ").append(s2))));
                }
            }
            if (b != null) {
                final File file = new File(s);
                final byte[] array = new byte[Math.min(102400, (int)file.length())];
                final FileInputStream fileInputStream = new FileInputStream(file);
                b = q.a(o, s2).b(".lib");
                final FileOutputStream fileOutputStream = new FileOutputStream(b);
                int i = fileInputStream.read(array);
                int n = 0;
                while (i > 0) {
                    n += i;
                    fileOutputStream.write(array, 0, i);
                    i = fileInputStream.read(array);
                }
                fileInputStream.close();
                fileOutputStream.close();
                final File file2 = new File(b);
                System.load(b);
                b(b, s2);
            }
            else {
                System.load(s);
                b(s, s2);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.load(s);
            b(s, s2);
        }
    }
    
    private static void b(String canonicalPath, final String s) {
        if (canonicalPath == null) {
            return;
        }
        if (i.c(83)) {
            h.d(s, String.valueOf(String.valueOf(new StringBuffer("Registering client ").append(s).append(" with ").append(canonicalPath))));
        }
        try {
            canonicalPath = new File(canonicalPath).getCanonicalPath();
            if (i.c(83)) {
                h.d(s, "Got platform specific path ".concat(String.valueOf(String.valueOf(canonicalPath))));
            }
        }
        catch (Exception ex) {
            if (i.c(83)) {
                h.d(s, "Failed to get platform specific path");
            }
        }
        synchronized (f.b) {
            final Vector<String> value = f.b.get(canonicalPath);
            if (value != null) {
                if (value instanceof Vector) {
                    final Vector<String> vector = value;
                    if (!vector.contains(s)) {
                        vector.addElement(s);
                    }
                }
            }
            else {
                final Vector<String> vector2 = new Vector<String>();
                vector2.addElement(s);
                f.b.put(canonicalPath, vector2);
            }
        }
        // monitorexit(f.b)
    }
    
    private static int c(String canonicalPath) {
        synchronized (f.b) {
            try {
                canonicalPath = new File(canonicalPath).getCanonicalPath();
            }
            catch (Exception ex) {}
            final Vector value = f.b.get(canonicalPath);
            if (value instanceof Vector) {
                // monitorexit(f.b)
                return value.size();
            }
            final boolean b = false;
            // monitorexit(f.b)
            return b ? 1 : 0;
        }
    }
    
    private static boolean d(String canonicalPath) {
        synchronized (f.b) {
            try {
                canonicalPath = new File(canonicalPath).getCanonicalPath();
            }
            catch (Exception ex) {}
            // monitorexit(f.b)
            return f.b.containsKey(canonicalPath);
        }
    }
    
    private static boolean c(String canonicalPath, final String s) {
        synchronized (f.b) {
            try {
                canonicalPath = new File(canonicalPath).getCanonicalPath();
            }
            catch (Exception ex) {}
            final Vector value = f.b.get(canonicalPath);
            if (value != null && value instanceof Vector && value.contains(s)) {
                // monitorexit(f.b)
                return true;
            }
            // monitorexit(f.b)
            return false;
        }
    }
    
    private static void d(String canonicalPath, final String s) {
        synchronized (f.b) {
            try {
                canonicalPath = new File(canonicalPath).getCanonicalPath();
            }
            catch (Exception ex) {}
            if (c(canonicalPath, s)) {
                final Vector value = f.b.get(canonicalPath);
                if (value != null && value instanceof Vector) {
                    value.removeElement(s);
                }
            }
        }
        // monitorexit(f.b)
    }
    
    public static synchronized void a(final Object o, final String s) {
        if (s == null) {
            if (i.c(83)) {
                h.d(s, "parentId is null, returning");
            }
            return;
        }
        try {
            final Object o2 = null;
            if (o != null) {
                if (i.c(83)) {
                    h.d(s, "Base obj passed null");
                }
                o.getClass().getClassLoader();
            }
            if (o2 == null) {
                try {
                    final ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
                    if (i.c(83)) {
                        h.d(s, "Result of get system cl: ".concat(String.valueOf(String.valueOf(systemClassLoader))));
                    }
                }
                catch (Throwable t) {
                    if (i.c(83)) {
                        h.d(s, "Can't get system class loader: ".concat(String.valueOf(String.valueOf(t.getMessage()))));
                    }
                }
            }
            if (o2 != null) {
                final Field declaredField = ((f.c == null) ? (f.c = class$("java.lang.ClassLoader")) : f.c).getDeclaredField("nativeLibraries");
                if (declaredField != null) {
                    declaredField.setAccessible(true);
                    final Object value = declaredField.get(o2);
                    if (value instanceof Vector) {
                        final Vector<Object> vector = (Vector<Object>)value;
                        if (vector.size() > 0) {
                            for (int i = 0; i < vector.size(); ++i) {
                                boolean b = false;
                                final Object value2 = vector.get(i);
                                if (value2 != null) {
                                    try {
                                        final Field declaredField2 = value2.getClass().getDeclaredField("name");
                                        if (declaredField2 != null) {
                                            declaredField2.setAccessible(true);
                                            final Object value3 = declaredField2.get(value2);
                                            if (value3 instanceof String) {
                                                final String s2 = (String)value3;
                                                if (i.c(83)) {
                                                    h.d(s, "Found loaded library ".concat(String.valueOf(String.valueOf(s2))));
                                                }
                                                if (s2 != null) {
                                                    if (d(s2)) {
                                                        if (i.c(83)) {
                                                            h.d(s, String.valueOf(String.valueOf(new StringBuffer("Unregistering ").append(s).append(" with ").append(s2))));
                                                        }
                                                        d(s2, s);
                                                        final int c = c(s2);
                                                        if (c == 0) {
                                                            final Method declaredMethod = value2.getClass().getDeclaredMethod("finalize", (Class<?>[])new Class[0]);
                                                            if (declaredMethod != null) {
                                                                declaredMethod.setAccessible(true);
                                                                try {
                                                                    declaredMethod.invoke(value2, new Object[0]);
                                                                    b = true;
                                                                    try {
                                                                        if (!ac.c(s2, s) && i.c(83)) {
                                                                            h.d(s, "Failed to delete library file ".concat(String.valueOf(String.valueOf(s2))));
                                                                        }
                                                                    }
                                                                    catch (Exception ex) {
                                                                        if (i.c(83)) {
                                                                            h.d(s, String.valueOf(String.valueOf(new StringBuffer("Failed to delete library file ").append(s2).append(": ").append(ex.getMessage()))));
                                                                        }
                                                                    }
                                                                }
                                                                catch (Exception ex2) {
                                                                    if (i.c(83)) {
                                                                        h.d(s, "Exception whilst unloading ".concat(String.valueOf(String.valueOf(ex2.getMessage()))));
                                                                    }
                                                                }
                                                                catch (Error error) {
                                                                    if (i.c(83)) {
                                                                        h.d(s, "Error whilst unloading ".concat(String.valueOf(String.valueOf(error.getMessage()))));
                                                                    }
                                                                }
                                                                if (!b && i.c(83)) {
                                                                    h.d(s, "Failed to unload ".concat(String.valueOf(String.valueOf(s2))));
                                                                }
                                                            }
                                                        }
                                                        else if (i.c(83)) {
                                                            h.d(s, "Library still has clients: ".concat(String.valueOf(String.valueOf(c))));
                                                        }
                                                    }
                                                    else if (i.c(83)) {
                                                        h.d(s, "Library not loaded");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    catch (NoSuchFieldException ex3) {
                                        if (i.c(83)) {
                                            h.d(s, "Lib object has no field \"name\"");
                                        }
                                    }
                                }
                                else if (i.c(83)) {
                                    h.d(s, "Lib object null");
                                }
                            }
                        }
                        else if (i.c(83)) {
                            h.d(s, "No libs loaded");
                        }
                    }
                    else if (i.c(83)) {
                        h.d(s, ("Libs list wrong type: ".concat(String.valueOf(String.valueOf(value))) == null) ? "null" : ((Vector<Object>)value).getClass().getName());
                    }
                }
                else if (i.c(83)) {
                    h.d(s, "Unable to obtain classloader native libs list.");
                }
            }
            else if (i.c(83)) {
                h.d(s, "Unable to obtain classloader.");
            }
        }
        catch (Throwable t2) {
            if (i.c(83)) {
                h.d(s, "Unable to unload native libraries.");
            }
            d.a(t2);
        }
    }
    
    public static PrintJob a(final Frame frame, final String s, final Properties properties, final String s2) {
        a(s2);
        a();
        try {
            if (f.a && !g.r) {
                PrivilegeManager.enablePrivilege("UniversalPrintJobAccess");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        return Toolkit.getDefaultToolkit().getPrintJob(frame, s, properties);
    }
    
    static Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        f.a = false;
        f.b = new Hashtable();
    }
}
