// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal;

import java.io.InputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;

public class Library
{
    static int MAJOR_VERSION;
    static int MINOR_VERSION;
    static int REVISION;
    public static final int JAVA_VERSION;
    public static final int SWT_VERSION;
    static final String SEPARATOR;
    static final String DELIMITER;
    static final boolean IS_64;
    static final String SUFFIX_64 = "-64";
    static final String SWT_LIB_DIR;
    static /* synthetic */ Class class$0;
    
    static {
        Library.MAJOR_VERSION = 3;
        Library.MINOR_VERSION = 721;
        Library.REVISION = 0;
        IS_64 = (longConst() == (int)longConst());
        DELIMITER = System.getProperty("line.separator");
        SEPARATOR = System.getProperty("file.separator");
        SWT_LIB_DIR = ".swt" + Library.SEPARATOR + "lib" + Library.SEPARATOR + os() + Library.SEPARATOR + arch();
        JAVA_VERSION = parseVersion(System.getProperty("java.version"));
        SWT_VERSION = SWT_VERSION(Library.MAJOR_VERSION, Library.MINOR_VERSION);
    }
    
    static String arch() {
        final String property = System.getProperty("os.arch");
        if (property.equals("i386") || property.equals("i686")) {
            return "x86";
        }
        if (property.equals("IA64N")) {
            return "ia64_32";
        }
        if (property.equals("IA64W")) {
            return "ia64";
        }
        return property;
    }
    
    static String os() {
        final String property = System.getProperty("os.name");
        if (property.equals("Linux")) {
            return "linux";
        }
        if (property.equals("AIX")) {
            return "aix";
        }
        if (property.equals("Solaris") || property.equals("SunOS")) {
            return "solaris";
        }
        if (property.equals("HP-UX")) {
            return "hpux";
        }
        if (property.equals("Mac OS X")) {
            return "macosx";
        }
        if (property.startsWith("Win")) {
            return "win32";
        }
        return property;
    }
    
    static void chmod(final String s, final String s2) {
        if ("win32".equals("win32")) {
            return;
        }
        try {
            Runtime.getRuntime().exec(new String[] { "chmod", s, s2 }).waitFor();
        }
        catch (Throwable t) {}
    }
    
    static long longConst() {
        return 8589934591L;
    }
    
    static int parseVersion(final String s) {
        if (s == null) {
            return 0;
        }
        int int1 = 0;
        int int2 = 0;
        int int3 = 0;
        final int length = s.length();
        int i = 0;
        final int n = 0;
        while (i < length) {
            if (!Character.isDigit(s.charAt(i))) {
                break;
            }
            ++i;
        }
        try {
            if (n < length) {
                int1 = Integer.parseInt(s.substring(n, i));
            }
        }
        catch (NumberFormatException ex) {}
        int n2;
        for (n2 = ++i; i < length; ++i) {
            if (!Character.isDigit(s.charAt(i))) {
                break;
            }
        }
        try {
            if (n2 < length) {
                int2 = Integer.parseInt(s.substring(n2, i));
            }
        }
        catch (NumberFormatException ex2) {}
        int n3;
        for (n3 = ++i; i < length; ++i) {
            if (!Character.isDigit(s.charAt(i))) {
                break;
            }
        }
        try {
            if (n3 < length) {
                int3 = Integer.parseInt(s.substring(n3, i));
            }
        }
        catch (NumberFormatException ex3) {}
        return JAVA_VERSION(int1, int2, int3);
    }
    
    public static int JAVA_VERSION(final int n, final int n2, final int n3) {
        return (n << 16) + (n2 << 8) + n3;
    }
    
    public static int SWT_VERSION(final int n, final int n2) {
        return n * 1000 + n2;
    }
    
    static boolean extract(final String s, final String s2, final StringBuffer sb) {
        FileOutputStream fileOutputStream = null;
        InputStream resourceAsStream = null;
        final File file = new File(s);
        boolean b = false;
        try {
            if (!file.exists()) {
                Class class$0;
                if ((class$0 = Library.class$0) == null) {
                    try {
                        class$0 = (Library.class$0 = Class.forName("org.eclipse.swt.internal.Library"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                resourceAsStream = class$0.getResourceAsStream("/" + s2);
                if (resourceAsStream != null) {
                    b = true;
                    final byte[] array = new byte[4096];
                    fileOutputStream = new FileOutputStream(s);
                    int read;
                    while ((read = resourceAsStream.read(array)) != -1) {
                        fileOutputStream.write(array, 0, read);
                    }
                    fileOutputStream.close();
                    resourceAsStream.close();
                    chmod("755", s);
                    if (load(s, sb)) {
                        return true;
                    }
                }
            }
        }
        catch (Throwable t) {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            }
            catch (IOException ex2) {}
            try {
                if (resourceAsStream != null) {
                    resourceAsStream.close();
                }
            }
            catch (IOException ex3) {}
            if (b && file.exists()) {
                file.delete();
            }
        }
        return false;
    }
    
    static boolean load(final String s, final StringBuffer sb) {
        try {
            if (s.indexOf(Library.SEPARATOR) != -1) {
                System.load(s);
            }
            else {
                System.loadLibrary(s);
            }
            return true;
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            if (sb.length() == 0) {
                sb.append(Library.DELIMITER);
            }
            sb.append('\t');
            sb.append(unsatisfiedLinkError.getMessage());
            sb.append(Library.DELIMITER);
            return false;
        }
    }
    
    public static void loadLibrary(final String s) {
        loadLibrary(s, true);
    }
    
    public static void loadLibrary(final String s, final boolean b) {
        String s2 = System.getProperty("sun.arch.data.model");
        if (s2 == null) {
            s2 = System.getProperty("com.ibm.vm.bitmode");
        }
        if (s2 != null) {
            if ("32".equals(s2) && Library.IS_64) {
                throw new UnsatisfiedLinkError("Cannot load 64-bit SWT libraries on 32-bit JVM");
            }
            if ("64".equals(s2) && !Library.IS_64) {
                throw new UnsatisfiedLinkError("Cannot load 32-bit SWT libraries on 64-bit JVM");
            }
        }
        String string;
        String string2;
        String mapLibraryName;
        String mapLibraryName2;
        if (b) {
            String s3 = System.getProperty("swt.version");
            if (s3 == null) {
                String s4 = new StringBuffer().append(Library.MAJOR_VERSION).toString();
                if (Library.MINOR_VERSION < 10) {
                    s4 = String.valueOf(s4) + "00";
                }
                else if (Library.MINOR_VERSION < 100) {
                    s4 = String.valueOf(s4) + "0";
                }
                s3 = String.valueOf(s4) + Library.MINOR_VERSION;
                if (Library.REVISION > 0) {
                    s3 = String.valueOf(s3) + "r" + Library.REVISION;
                }
            }
            string = String.valueOf(s) + "-" + "win32" + "-" + s3;
            string2 = String.valueOf(s) + "-" + "win32";
            mapLibraryName = mapLibraryName(string);
            mapLibraryName2 = mapLibraryName(string2);
        }
        else {
            mapLibraryName2 = s;
            mapLibraryName = s;
            string2 = s;
            string = s;
        }
        final StringBuffer sb = new StringBuffer();
        String s5 = System.getProperty("swt.library.path");
        if (s5 != null) {
            s5 = new File(s5).getAbsolutePath();
            if (load(String.valueOf(s5) + Library.SEPARATOR + mapLibraryName, sb)) {
                return;
            }
            if (b && load(String.valueOf(s5) + Library.SEPARATOR + mapLibraryName2, sb)) {
                return;
            }
        }
        if (load(string, sb)) {
            return;
        }
        if (b && load(string2, sb)) {
            return;
        }
        String mapLibraryName3 = mapLibraryName;
        String mapLibraryName4 = mapLibraryName2;
        if (s5 == null) {
            s5 = System.getProperty("user.home");
            final File file = new File(s5, Library.SWT_LIB_DIR);
            if ((file.exists() && file.isDirectory()) || file.mkdirs()) {
                s5 = file.getAbsolutePath();
            }
            else if (Library.IS_64) {
                mapLibraryName3 = mapLibraryName(String.valueOf(string) + "-64");
                mapLibraryName4 = mapLibraryName(String.valueOf(string2) + "-64");
            }
            if (load(String.valueOf(s5) + Library.SEPARATOR + mapLibraryName3, sb)) {
                return;
            }
            if (b && load(String.valueOf(s5) + Library.SEPARATOR + mapLibraryName4, sb)) {
                return;
            }
        }
        if (s5 != null) {
            if (extract(String.valueOf(s5) + Library.SEPARATOR + mapLibraryName3, mapLibraryName, sb)) {
                return;
            }
            if (b && extract(String.valueOf(s5) + Library.SEPARATOR + mapLibraryName4, mapLibraryName2, sb)) {
                return;
            }
        }
        throw new UnsatisfiedLinkError("Could not load SWT library. Reasons: " + sb.toString());
    }
    
    static String mapLibraryName(String s) {
        s = System.mapLibraryName(s);
        final String s2 = ".dylib";
        if (s.endsWith(s2)) {
            s = String.valueOf(s.substring(0, s.length() - s2.length())) + ".jnilib";
        }
        return s;
    }
}
