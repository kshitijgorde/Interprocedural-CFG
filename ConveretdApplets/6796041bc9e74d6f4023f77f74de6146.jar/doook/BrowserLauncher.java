// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.io.IOException;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

public class BrowserLauncher
{
    private static int g;
    private static Object b;
    private static boolean b;
    private static Class a;
    private static Class b;
    private static Class c;
    private static Constructor a;
    private static Constructor b;
    private static Constructor c;
    private static Method a;
    private static Method b;
    private static Method c;
    private static Method d;
    private static Method e;
    private static Method f;
    private static Method g;
    private static Object c;
    private static Integer a;
    private static Integer b;
    private static Integer c;
    private static Object d;
    private static String F;
    static Class d;
    static Class e;
    static Class f;
    static Class g;
    
    private static final boolean a() {
        switch (BrowserLauncher.g) {
            case 0: {
                try {
                    final Class<?> forName = Class.forName("com.apple.MacOS.AETarget");
                    final Class<?> forName2 = Class.forName("com.apple.MacOS.OSUtils");
                    final Class<?> forName3 = Class.forName("com.apple.MacOS.AppleEvent");
                    final Class<?> forName4 = Class.forName("com.apple.MacOS.ae");
                    BrowserLauncher.c = Class.forName("com.apple.MacOS.AEDesc");
                    BrowserLauncher.a = forName.getDeclaredConstructor(Integer.TYPE);
                    BrowserLauncher.b = forName3.getDeclaredConstructor(Integer.TYPE, Integer.TYPE, forName, Integer.TYPE, Integer.TYPE);
                    BrowserLauncher.c = BrowserLauncher.c.getDeclaredConstructor((BrowserLauncher.d == null) ? (BrowserLauncher.d = a("java.lang.String")) : BrowserLauncher.d);
                    BrowserLauncher.e = forName2.getDeclaredMethod("makeOSType", (BrowserLauncher.d == null) ? (BrowserLauncher.d = a("java.lang.String")) : BrowserLauncher.d);
                    BrowserLauncher.f = forName3.getDeclaredMethod("putParameter", Integer.TYPE, BrowserLauncher.c);
                    BrowserLauncher.g = forName3.getDeclaredMethod("sendNoReply", (Class[])new Class[0]);
                    BrowserLauncher.a = (Integer)forName4.getDeclaredField("keyDirectObject").get(null);
                    BrowserLauncher.b = (Integer)forName3.getDeclaredField("kAutoGenerateReturnID").get(null);
                    BrowserLauncher.c = (Integer)forName3.getDeclaredField("kAnyTransactionID").get(null);
                    break;
                }
                catch (ClassNotFoundException ex) {
                    BrowserLauncher.F = ex.getMessage();
                    return false;
                }
                catch (NoSuchMethodException ex2) {
                    BrowserLauncher.F = ex2.getMessage();
                    return false;
                }
                catch (NoSuchFieldException ex3) {
                    BrowserLauncher.F = ex3.getMessage();
                    return false;
                }
                catch (IllegalAccessException ex4) {
                    BrowserLauncher.F = ex4.getMessage();
                    return false;
                }
            }
            case 1: {
                try {
                    BrowserLauncher.a = Class.forName("com.apple.mrj.MRJFileUtils");
                    BrowserLauncher.b = Class.forName("com.apple.mrj.MRJOSType");
                    BrowserLauncher.c = BrowserLauncher.a.getDeclaredField("kSystemFolderType").get(null);
                    BrowserLauncher.a = BrowserLauncher.a.getDeclaredMethod("findFolder", BrowserLauncher.b);
                    BrowserLauncher.b = BrowserLauncher.a.getDeclaredMethod("getFileCreator", (BrowserLauncher.e == null) ? (BrowserLauncher.e = a("java.io.File")) : BrowserLauncher.e);
                    BrowserLauncher.c = BrowserLauncher.a.getDeclaredMethod("getFileType", (BrowserLauncher.e == null) ? (BrowserLauncher.e = a("java.io.File")) : BrowserLauncher.e);
                    break;
                }
                catch (ClassNotFoundException ex5) {
                    BrowserLauncher.F = ex5.getMessage();
                    return false;
                }
                catch (NoSuchFieldException ex6) {
                    BrowserLauncher.F = ex6.getMessage();
                    return false;
                }
                catch (NoSuchMethodException ex7) {
                    BrowserLauncher.F = ex7.getMessage();
                    return false;
                }
                catch (SecurityException ex8) {
                    BrowserLauncher.F = ex8.getMessage();
                    return false;
                }
                catch (IllegalAccessException ex9) {
                    BrowserLauncher.F = ex9.getMessage();
                    return false;
                }
            }
            case 3: {
                try {
                    BrowserLauncher.d = Class.forName("com.apple.mrj.jdirect.Linker").getConstructor((BrowserLauncher.f == null) ? (BrowserLauncher.f = a("java.lang.Class")) : BrowserLauncher.f).newInstance((BrowserLauncher.g == null) ? (BrowserLauncher.g = a("doook.BrowserLauncher")) : BrowserLauncher.g);
                    break;
                }
                catch (ClassNotFoundException ex10) {
                    BrowserLauncher.F = ex10.getMessage();
                    return false;
                }
                catch (NoSuchMethodException ex11) {
                    BrowserLauncher.F = ex11.getMessage();
                    return false;
                }
                catch (InvocationTargetException ex12) {
                    BrowserLauncher.F = ex12.getMessage();
                    return false;
                }
                catch (InstantiationException ex13) {
                    BrowserLauncher.F = ex13.getMessage();
                    return false;
                }
                catch (IllegalAccessException ex14) {
                    BrowserLauncher.F = ex14.getMessage();
                    return false;
                }
            }
            case 4: {
                try {
                    BrowserLauncher.a = Class.forName("com.apple.mrj.MRJFileUtils");
                    BrowserLauncher.d = BrowserLauncher.a.getDeclaredMethod("openURL", (BrowserLauncher.d == null) ? (BrowserLauncher.d = a("java.lang.String")) : BrowserLauncher.d);
                }
                catch (ClassNotFoundException ex15) {
                    BrowserLauncher.F = ex15.getMessage();
                    return false;
                }
                catch (NoSuchMethodException ex16) {
                    BrowserLauncher.F = ex16.getMessage();
                    return false;
                }
                break;
            }
        }
        return true;
    }
    
    private static final Object b() {
        if (BrowserLauncher.b != null) {
            return BrowserLauncher.b;
        }
        switch (BrowserLauncher.g) {
            case 0: {
                try {
                    final Object instance = BrowserLauncher.a.newInstance((Integer)BrowserLauncher.e.invoke(null, "MACS"));
                    final Integer n = (Integer)BrowserLauncher.e.invoke(null, "GURL");
                    return BrowserLauncher.b.newInstance(n, n, instance, BrowserLauncher.b, BrowserLauncher.c);
                }
                catch (IllegalAccessException ex) {
                    BrowserLauncher.b = null;
                    BrowserLauncher.F = ex.getMessage();
                    return BrowserLauncher.b;
                }
                catch (InstantiationException ex2) {
                    BrowserLauncher.b = null;
                    BrowserLauncher.F = ex2.getMessage();
                    return BrowserLauncher.b;
                }
                catch (InvocationTargetException ex3) {
                    BrowserLauncher.b = null;
                    BrowserLauncher.F = ex3.getMessage();
                    return BrowserLauncher.b;
                }
            }
            case 1: {
                File file;
                try {
                    file = (File)BrowserLauncher.a.invoke(null, BrowserLauncher.c);
                }
                catch (IllegalArgumentException ex4) {
                    BrowserLauncher.b = null;
                    BrowserLauncher.F = ex4.getMessage();
                    return BrowserLauncher.b;
                }
                catch (IllegalAccessException ex5) {
                    BrowserLauncher.b = null;
                    BrowserLauncher.F = ex5.getMessage();
                    return BrowserLauncher.b;
                }
                catch (InvocationTargetException ex6) {
                    BrowserLauncher.b = null;
                    BrowserLauncher.F = ex6.getTargetException().getClass() + ": " + ex6.getTargetException().getMessage();
                    return BrowserLauncher.b;
                }
                final String[] list = file.list();
                for (int i = 0; i < list.length; ++i) {
                    try {
                        final File file2 = new File(file, list[i]);
                        if (file2.isFile() && "FNDR".equals(BrowserLauncher.c.invoke(null, file2).toString()) && "MACS".equals(BrowserLauncher.b.invoke(null, file2).toString())) {
                            return BrowserLauncher.b = file2.toString();
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        BrowserLauncher.b = BrowserLauncher.b;
                        BrowserLauncher.F = ex7.getMessage();
                        return null;
                    }
                    catch (IllegalAccessException ex8) {
                        BrowserLauncher.b = null;
                        BrowserLauncher.F = ex8.getMessage();
                        return BrowserLauncher.b;
                    }
                    catch (InvocationTargetException ex9) {
                        BrowserLauncher.b = null;
                        BrowserLauncher.F = ex9.getTargetException().getClass() + ": " + ex9.getTargetException().getMessage();
                        return BrowserLauncher.b;
                    }
                }
                BrowserLauncher.b = null;
                break;
            }
            case 3:
            case 4: {
                BrowserLauncher.b = "";
                break;
            }
            case 5: {
                BrowserLauncher.b = "cmd.exe";
                break;
            }
            case 6: {
                BrowserLauncher.b = "command.com";
                break;
            }
            default: {
                BrowserLauncher.b = "netscape";
                break;
            }
        }
        return BrowserLauncher.b;
    }
    
    public static void a(String s) {
        if (!BrowserLauncher.b) {
            throw new IOException("Exception in finding browser: " + BrowserLauncher.F);
        }
        final Object b = b();
        if (b == null) {
            throw new IOException("Unable to locate browser: " + BrowserLauncher.F);
        }
        switch (BrowserLauncher.g) {
            case 1: {
                try {
                    try {
                        BrowserLauncher.f.invoke(b, BrowserLauncher.a, BrowserLauncher.c.newInstance(s));
                        BrowserLauncher.g.invoke(b, new Object[0]);
                    }
                    catch (InvocationTargetException ex) {
                        throw new IOException("InvocationTargetException while creating AEDesc: " + ex.getMessage());
                    }
                    catch (IllegalAccessException ex2) {
                        throw new IOException("IllegalAccessException while building AppleEvent: " + ex2.getMessage());
                    }
                    catch (InstantiationException ex3) {
                        throw new IOException("InstantiationException while creating AEDesc: " + ex3.getMessage());
                    }
                    return;
                }
                finally {}
            }
            case 2: {
                Runtime.getRuntime().exec(new String[] { (String)b, s });
                return;
            }
            case 4: {
                final int[] array = { 0 };
                final int icStart = ICStart(array, 0);
                if (icStart != 0) {
                    throw new IOException("Unable to create an Internet Config instance: " + icStart);
                }
                final int[] array2 = { 0 };
                final byte[] bytes = s.getBytes();
                final int icLaunchURL = ICLaunchURL(array[0], new byte[] { 0 }, bytes, bytes.length, array2, new int[] { bytes.length });
                if (icLaunchURL == 0) {
                    ICStop(array);
                    return;
                }
                throw new IOException("Unable to launch URL: " + icLaunchURL);
            }
            case 5: {
                try {
                    BrowserLauncher.d.invoke(null, s);
                    return;
                }
                catch (InvocationTargetException ex4) {
                    throw new IOException("InvocationTargetException while calling openURL: " + ex4.getMessage());
                }
                catch (IllegalAccessException ex5) {
                    throw new IOException("IllegalAccessException while calling openURL: " + ex5.getMessage());
                }
            }
            case 6:
            case 7: {
                if (s.endsWith(".html")) {
                    s = s.substring(0, s.indexOf(".html")) + ".ht%6Dl";
                }
                else if (s.endsWith(".htm")) {
                    s = s.substring(0, s.indexOf(".htm")) + ".ht%6D";
                }
                try {
                    final Process exec = Runtime.getRuntime().exec(new String[] { "rundll32", "url.dll,FileProtocolHandler", s });
                    exec.waitFor();
                    exec.exitValue();
                }
                catch (Exception ex6) {
                    ex6.printStackTrace();
                }
                return;
            }
            case 0: {
                final Process exec2 = Runtime.getRuntime().exec(new String[] { (String)b, "-remote", "'openURL(" + s + ")'" });
                try {
                    if (exec2.waitFor() != 0) {
                        Runtime.getRuntime().exec(new String[] { (String)b, s });
                    }
                    return;
                }
                catch (InterruptedException ex7) {
                    throw new IOException("InterruptedException while launching browser: " + ex7.getMessage());
                }
                break;
            }
        }
        Runtime.getRuntime().exec(new String[] { (String)b, s });
    }
    
    private static final native int ICStart(final int[] p0, final int p1);
    
    private static final native int ICStop(final int[] p0);
    
    private static final native int ICLaunchURL(final int p0, final byte[] p1, final byte[] p2, final int p3, final int[] p4, final int[] p5);
    
    static Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        BrowserLauncher.b = true;
        final String property = System.getProperty("os.name");
        if (property.startsWith("Mac OS")) {
            final String property2 = System.getProperty("mrj.version");
            final String substring = property2.substring(0, 3);
            try {
                final double doubleValue = Double.valueOf(substring);
                if (doubleValue == 2.0) {
                    BrowserLauncher.g = 0;
                }
                else if (doubleValue >= 2.1 && doubleValue < 3.0) {
                    BrowserLauncher.g = 1;
                }
                else if (doubleValue == 3.0) {
                    BrowserLauncher.g = 3;
                }
                else if (doubleValue >= 3.1) {
                    BrowserLauncher.g = 4;
                }
                else {
                    BrowserLauncher.b = false;
                    BrowserLauncher.F = "Unsupported MRJ version: " + doubleValue;
                }
            }
            catch (NumberFormatException ex) {
                BrowserLauncher.b = false;
                BrowserLauncher.F = "Invalid MRJ version: " + property2;
            }
        }
        else if (property.startsWith("Windows")) {
            if (property.indexOf("9") != -1) {
                BrowserLauncher.g = 6;
            }
            else {
                BrowserLauncher.g = 5;
            }
        }
        else {
            BrowserLauncher.g = -1;
        }
        if (BrowserLauncher.b) {
            BrowserLauncher.b = a();
        }
    }
}
