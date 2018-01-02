// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix.util;

import java.util.HashMap;
import java.util.Map;

public class Platform
{
    public static final String OS_NAME;
    public static final String OS_NAME_LC;
    private static final String WINDOWS = "windows";
    private static final String WINDOWS_9X = "windows 9";
    private static final String WINDOWS_NT = "nt";
    private static final String WINDOWS_20X = "windows 2";
    private static final String WINDOWS_XP = "windows xp";
    private static final String WINDOWS_SERVER = "server";
    private static final String WINDOWS_VISTA = "vista";
    private static final String WINDOWS_7 = "windows 7";
    private static final String MAC_OS = "mac os";
    private static final String DARWIN = "darwin";
    private static final String FREEBSD = "freebsd";
    private static final String OPENBSD = "openbsd";
    private static final String LINUX = "linux";
    private static final String SOLARIS = "sunos";
    public static final boolean IS_WINDOWS;
    public static final boolean IS_WINDOWS_9X;
    public static final boolean IS_WINDOWS_NT;
    public static final boolean IS_WINDOWS_20X;
    public static final boolean IS_WINDOWS_XP;
    public static final boolean IS_WINDOWS_VISTA;
    public static final boolean IS_WINDOWS_SERVER;
    public static final boolean IS_WINDOWS_7;
    public static final boolean IS_MAC;
    public static final boolean IS_FREEBSD;
    public static final boolean IS_OPENBSD;
    public static final boolean IS_LINUX;
    public static final boolean IS_SOLARIS;
    public static final boolean IS_BSD;
    public static final boolean IS_32_BIT;
    public static final boolean IS_64_BIT;
    public static final String ARCH;
    public static final Map<String, String> OS_NAMES;
    
    public static final String envCommand() {
        if (Platform.IS_WINDOWS) {
            if (Platform.IS_WINDOWS_9X) {
                return "command.com /c set";
            }
            if (Platform.IS_WINDOWS_NT || Platform.IS_WINDOWS_20X || Platform.IS_WINDOWS_XP || Platform.IS_WINDOWS_SERVER || Platform.IS_WINDOWS_VISTA || Platform.IS_WINDOWS_7) {
                return "cmd.exe /c set";
            }
        }
        return "env";
    }
    
    public static String getOSName() {
        final String theOSName = Platform.OS_NAMES.get(Platform.OS_NAME);
        return (theOSName == null) ? Platform.OS_NAME : theOSName;
    }
    
    public static String getProperty(final String property, final String defValue) {
        try {
            return System.getProperty(property, defValue);
        }
        catch (SecurityException se) {
            return defValue;
        }
    }
    
    static {
        OS_NAME = System.getProperty("os.name");
        OS_NAME_LC = Platform.OS_NAME.toLowerCase();
        IS_WINDOWS = (Platform.OS_NAME_LC.indexOf("windows") != -1);
        IS_WINDOWS_9X = (Platform.OS_NAME_LC.indexOf("windows 9") > -1);
        IS_WINDOWS_NT = (Platform.IS_WINDOWS && Platform.OS_NAME_LC.indexOf("nt") > -1);
        IS_WINDOWS_20X = (Platform.OS_NAME_LC.indexOf("windows 2") > -1);
        IS_WINDOWS_XP = (Platform.OS_NAME_LC.indexOf("windows xp") > -1);
        IS_WINDOWS_VISTA = (Platform.IS_WINDOWS && Platform.OS_NAME_LC.indexOf("vista") > -1);
        IS_WINDOWS_SERVER = (Platform.IS_WINDOWS && Platform.OS_NAME_LC.indexOf("server") > -1);
        IS_WINDOWS_7 = (Platform.IS_WINDOWS && Platform.OS_NAME_LC.indexOf("windows 7") > -1);
        IS_MAC = (Platform.OS_NAME_LC.startsWith("mac os") || Platform.OS_NAME_LC.startsWith("darwin"));
        IS_FREEBSD = Platform.OS_NAME_LC.startsWith("freebsd");
        IS_OPENBSD = Platform.OS_NAME_LC.startsWith("openbsd");
        IS_LINUX = Platform.OS_NAME_LC.startsWith("linux");
        IS_SOLARIS = Platform.OS_NAME_LC.startsWith("sunos");
        IS_BSD = (Platform.IS_MAC || Platform.IS_FREEBSD || Platform.IS_OPENBSD);
        IS_32_BIT = "32".equals(getProperty("sun.arch.data.model", "32"));
        IS_64_BIT = "64".equals(getProperty("sun.arch.data.model", "64"));
        ARCH = System.getProperty("os.arch");
        (OS_NAMES = new HashMap<String, String>()).put("Mac OS X", "darwin");
        Platform.OS_NAMES.put("Darwin", "darwin");
        Platform.OS_NAMES.put("Linux", "linux");
    }
}
