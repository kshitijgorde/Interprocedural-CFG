// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public final class bj
{
    private static int a;
    private static Boolean b;
    
    private bj() {
        if (bj.a > 0) {
            final int a = bj.a;
        }
        else {
            final String property;
            if ((property = System.getProperty("os.name")) != null) {
                final String lowerCase;
                if ((lowerCase = property.toLowerCase()).indexOf("windows") != -1) {
                    bj.a = 1;
                    return;
                }
                if (lowerCase.indexOf("mac") != -1) {
                    bj.a = 2;
                    return;
                }
                if (lowerCase.indexOf("linux") != -1) {
                    bj.a = 3;
                    return;
                }
            }
            bj.a = 20;
        }
    }
    
    public static boolean a() {
        return bj.a == 3;
    }
    
    public static boolean b() {
        return bj.a == 2;
    }
    
    public static boolean c() {
        return bj.a == 1;
    }
    
    public static boolean d() {
        final String property = System.getProperty("os.name");
        final String property2 = System.getProperty("os.version");
        final String lowerCase;
        return (lowerCase = property.toLowerCase()).indexOf("windows") != -1 && (lowerCase.indexOf("vista") != -1 || (lowerCase.indexOf("windows nt") != -1 && property2.indexOf("6.0") != -1));
    }
    
    public static void a(File exec) {
        if (!exec.exists()) {
            throw new FileNotFoundException(String.valueOf(exec));
        }
        final String[] array = { "chmod", "a+x", "" };
        if (b() || a()) {
            array[2] = exec.getAbsolutePath();
            try {
                exec = (File)Runtime.getRuntime().exec(array);
            }
            catch (IOException ex) {
                throw (IOException)ex.fillInStackTrace();
            }
            int i = 0;
            while (i < 5) {
                try {
                    ((Process)exec).waitFor();
                    return;
                }
                catch (InterruptedException ex2) {
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex3) {}
                    ++i;
                    continue;
                }
                break;
            }
            return;
        }
        if (c()) {
            return;
        }
    }
    
    public static boolean e() {
        if (bj.b != null) {
            return bj.b;
        }
        if (b()) {
            final String property;
            if ((property = System.getProperty("javaplugin.version")) != null && (property.indexOf("1.4.2") != -1 || property.indexOf("1.5") != -1)) {
                bj.b = Boolean.TRUE;
            }
            else {
                bj.b = Boolean.FALSE;
            }
        }
        else {
            bj.b = Boolean.TRUE;
        }
        return bj.b;
    }
    
    public static boolean f() {
        if (!c()) {
            return false;
        }
        try {
            final Process exec = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/C", "dir" }, null, new File(System.getProperty("user.home")));
            if (exec != null) {
                exec.destroy();
            }
        }
        catch (IOException ex) {
            System.err.println("PlatformUtils: " + ex);
            ex.printStackTrace();
            return true;
        }
        return false;
    }
    
    static {
        bj.a = -1;
        new bj();
        bj.b = null;
    }
}
