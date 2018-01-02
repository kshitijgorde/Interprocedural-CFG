// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine;

import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Platform
{
    private static final Platform INSTANCE;
    public static final Map<String, String> OS_NAMES;
    public static final Map<String, String> ARCH_NAMES;
    public static final String ARCH;
    public static final String OS;
    public static final String NAME;
    public static final int BIG_ENDIAN = 4321;
    public static final int LITTLE_ENDIAN = 1234;
    public static final int BYTE_ORDER;
    
    public static Platform getPlatform() {
        return Platform.INSTANCE;
    }
    
    public String getPackageName() {
        return String.format("%s.platform.%s.%s", Platform.class.getPackage().getName(), Platform.OS, Platform.ARCH);
    }
    
    public String getOSPackageName() {
        return String.format("%s.platform.%s", Platform.class.getPackage().getName(), Platform.OS);
    }
    
    private static final String initOperatingSystem() {
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
    
    private static final String initArchitecture() {
        final String arch = getProperty("os.arch", "unknown").toLowerCase();
        for (final String s : Platform.ARCH_NAMES.keySet()) {
            if (s.equalsIgnoreCase(arch)) {
                return Platform.ARCH_NAMES.get(s);
            }
        }
        return arch;
    }
    
    private static String getProperty(final String property, final String defValue) {
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
            public static final long serialVersionUID = 1L;
            
            {
                this.put("Mac OS X", "darwin");
            }
        };
        ARCH_NAMES = new HashMap<String, String>() {
            public static final long serialVersionUID = 1L;
            
            {
                this.put("x86", "i386");
            }
        };
        ARCH = initArchitecture();
        OS = initOperatingSystem();
        NAME = String.format("%s-%s", Platform.ARCH, Platform.OS);
        BYTE_ORDER = (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN) ? 4321 : 1234);
    }
}
