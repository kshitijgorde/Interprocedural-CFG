// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.platform;

import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Platform
{
    private static final Platform INSTANCE;
    private static final String DARWIN = "darwin";
    private static final String WINDOWS = "windows";
    private static final String LINUX = "linux";
    private static final String FREEBSD = "freebsd";
    private static final String OPENBSD = "openbsd";
    private static final String SOLARIS = "solaris";
    private static final String OPENVMS = "openvms";
    private static final String GCJ = "GNU libgcj";
    public static final Map<String, String> OS_NAMES;
    public static final Map<String, String> ARCH_NAMES;
    public static final String ARCH;
    public static final String OS;
    public static final String JVM;
    public static final boolean IS_WINDOWS;
    public static final boolean IS_MAC;
    public static final boolean IS_FREEBSD;
    public static final boolean IS_OPENBSD;
    public static final boolean IS_LINUX;
    public static final boolean IS_SOLARIS;
    public static final boolean IS_BSD;
    public static final boolean IS_OPENVMS;
    public static final String NAME;
    public static final int BIG_ENDIAN = 4321;
    public static final int LITTLE_ENDIAN = 1234;
    public static final int BYTE_ORDER;
    public static final boolean IS_GCJ;
    
    public static Platform getPlatform() {
        return Platform.INSTANCE;
    }
    
    public static void main(final String[] args) {
        System.out.println(System.getProperties());
    }
    
    public String getPackageName() {
        return String.format("%s.%s.%s", Platform.class.getPackage().getName(), Platform.OS, Platform.ARCH);
    }
    
    public String getOSPackageName() {
        return String.format("%s.%s", Platform.class.getPackage().getName(), Platform.OS);
    }
    
    private static String initOperatingSystem() {
        final String osname = getProperty("os.name", "unknown").toLowerCase();
        for (final String s : Platform.OS_NAMES.keySet()) {
            if (s.equalsIgnoreCase(osname)) {
                return Platform.OS_NAMES.get(s);
            }
        }
        if (osname.startsWith("windows")) {
            return "windows";
        }
        return osname;
    }
    
    private static String initArchitecture() {
        final String arch = getProperty("os.arch", "unknown").toLowerCase();
        for (final String s : Platform.ARCH_NAMES.keySet()) {
            if (s.equalsIgnoreCase(arch)) {
                return Platform.ARCH_NAMES.get(s);
            }
        }
        return arch;
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
        INSTANCE = new Platform();
        OS_NAMES = new HashMap<String, String>() {
            {
                this.put("Mac OS X", "darwin");
            }
        };
        ARCH_NAMES = new HashMap<String, String>() {
            {
                this.put("x86", "i386");
            }
        };
        ARCH = initArchitecture();
        OS = initOperatingSystem();
        JVM = getProperty("java.vm.name", "unknown");
        IS_WINDOWS = Platform.OS.equals("windows");
        IS_MAC = Platform.OS.equals("darwin");
        IS_FREEBSD = Platform.OS.equals("freebsd");
        IS_OPENBSD = Platform.OS.equals("openbsd");
        IS_LINUX = Platform.OS.equals("linux");
        IS_SOLARIS = Platform.OS.equals("solaris");
        IS_BSD = (Platform.IS_MAC || Platform.IS_FREEBSD || Platform.IS_OPENBSD);
        IS_OPENVMS = Platform.OS.equals("openvms");
        NAME = String.format("%s-%s", Platform.ARCH, Platform.OS);
        BYTE_ORDER = (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN) ? 4321 : 1234);
        IS_GCJ = Platform.JVM.equals("GNU libgcj");
    }
}
