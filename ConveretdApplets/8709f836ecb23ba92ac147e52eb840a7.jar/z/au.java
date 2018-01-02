// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.io.File;

public class au
{
    private static /* synthetic */ boolean a;
    
    public static String a(final String s) {
        return s.substring(s.lastIndexOf(File.separator) + 1);
    }
    
    public static String b(final String s) {
        return s.substring(0, s.lastIndexOf(File.separator));
    }
    
    public static String a(final int n) {
        if (!au.a && n < 0) {
            throw new AssertionError();
        }
        final int n2 = 1048576;
        if (n < 1024) {
            return String.format("%dB", n);
        }
        if (n < n2) {
            return String.format("%dKB", n / 1024);
        }
        return String.format("%.1fMB", n / n2);
    }
    
    public static boolean c(final String s) {
        return s.toLowerCase().endsWith(".tif") || s.toLowerCase().endsWith(".tiff");
    }
    
    public static boolean d(final String s) {
        return s.toLowerCase().endsWith(".jpg") || s.toLowerCase().endsWith(".jpeg");
    }
    
    public static boolean e(final String s) {
        return s.toLowerCase().endsWith(".bmp");
    }
    
    public static boolean a() {
        final String lowerCase = System.getProperty("os.name").toLowerCase();
        return System.getProperty("os.name").indexOf("Vista") != -1 || lowerCase.indexOf("windows 7") != -1;
    }
    
    public static boolean b() {
        return System.getProperty("os.name").indexOf("Mac") != -1;
    }
    
    public static String f(final String s) {
        final int index;
        if ((index = s.indexOf(File.separator)) == -1) {
            return null;
        }
        return s.substring(0, index + 1);
    }
    
    public static String a(final String s, final int n) {
        if (!au.a && n < 0) {
            throw new AssertionError();
        }
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            final int index;
            if ((index = s.indexOf(File.separator, n2)) == -1) {
                return null;
            }
            n2 = index + 1;
        }
        String separator = "";
        if (n == 0) {
            separator = File.separator;
        }
        final int index2;
        if ((index2 = s.indexOf(File.separator, n2)) == -1) {
            return s.substring(n2) + separator;
        }
        return s.substring(n2, index2) + separator;
    }
    
    public static String c() {
        if (a() || b()) {
            String s = null;
            if (G.a().equals("FR")) {
                s = "Documents";
            }
            else if (G.a().equals("SP")) {
                return "Documentos";
            }
            if (s != null && new File(System.getProperty("user.home") + File.separatorChar + s).exists()) {
                return s;
            }
            return "Documents";
        }
        else {
            String s2 = null;
            if (G.a().equals("FR")) {
                s2 = "Mes documents";
            }
            else if (G.a().equals("SP")) {
                return "Mis documentos";
            }
            if (s2 != null && new File(System.getProperty("user.home") + File.separatorChar + s2).exists()) {
                return s2;
            }
            return "My Documents";
        }
    }
    
    public static String d() {
        if (a() || b()) {
            String s = null;
            if (G.a().equals("FR")) {
                s = "Images";
            }
            else if (G.a().equals("SP")) {
                return "Fotos";
            }
            if (s != null && new File(System.getProperty("user.home") + File.separatorChar + s).exists()) {
                return s;
            }
            return "Pictures";
        }
        else {
            String s2 = null;
            if (G.a().equals("FR")) {
                s2 = "Mes images";
            }
            else if (G.a().equals("SP")) {
                return "Mis fotos";
            }
            if (s2 != null && new File(System.getProperty("user.home") + File.separatorChar + c() + File.separatorChar + s2).exists()) {
                return s2;
            }
            return "My Pictures";
        }
    }
    
    public static String e() {
        String s = null;
        if (G.a().equals("FR")) {
            s = "Bureau";
        }
        else if (G.a().equals("SP")) {
            return "Escritorio";
        }
        if (s != null && new File(System.getProperty("user.home") + File.separatorChar + s).exists()) {
            return s;
        }
        return "Desktop";
    }
    
    public static String f() {
        if (b()) {
            return System.getProperty("user.home");
        }
        if (G.a().equals("EN")) {
            return "Computer";
        }
        if (G.a().equals("FR")) {
            return "Ordinateur";
        }
        if (G.a().equals("SP")) {
            return "Computadora";
        }
        throw new RuntimeException("Language unsupported");
    }
    
    public static int g(String substring) {
        if (substring == null) {
            return 0;
        }
        if (substring.endsWith(File.separator)) {
            substring = substring.substring(0, substring.length() - 1);
        }
        int n = 0;
        for (int index = 0; (index = substring.indexOf(File.separator, index)) != -1; ++index, ++n) {}
        return n;
    }
    
    public static boolean h(final String s) {
        return s.toLowerCase().endsWith(".pdf");
    }
    
    public static String g() {
        return "/Volumes";
    }
    
    static {
        au.a = !au.class.desiredAssertionStatus();
    }
}
