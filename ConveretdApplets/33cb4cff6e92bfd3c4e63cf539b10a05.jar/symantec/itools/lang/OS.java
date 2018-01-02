// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.lang;

public final class OS
{
    private static boolean isWindows95;
    private static boolean isWindowsNT;
    private static boolean isMacintosh;
    private static boolean isSolaris;
    private static boolean isCaseSensitive;
    
    public static boolean isWindows() {
        return isWindows95() || isWindowsNT();
    }
    
    public static boolean isWindows95() {
        return OS.isWindows95;
    }
    
    public static boolean isWindowsNT() {
        return OS.isWindowsNT;
    }
    
    public static boolean isMacintosh() {
        return OS.isMacintosh;
    }
    
    public static boolean isSolaris() {
        return OS.isSolaris;
    }
    
    public static boolean isCaseSensitive() {
        return OS.isCaseSensitive;
    }
    
    static {
        final String s = System.getProperty("os.name");
        if (s.equals("Windows NT")) {
            OS.isWindowsNT = true;
        }
        else if (s.equals("Windows 95")) {
            OS.isWindows95 = true;
        }
        else if (s.equals("Macintosh") || s.equals("macos") || s.equals("Mac OS") || s.equals("MacOS")) {
            OS.isMacintosh = true;
        }
        else if (s.equals("SunOS") || s.equals("Solaris")) {
            OS.isSolaris = true;
            OS.isCaseSensitive = true;
        }
    }
}
