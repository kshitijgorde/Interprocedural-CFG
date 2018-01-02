// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import java.nio.ByteOrder;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.util.SafePropertyAccessor;
import java.util.regex.Pattern;

public class Platform
{
    public static final CPU_TYPE CPU;
    public static final OS_TYPE OS;
    public static final String NAME;
    public static final String LIBPREFIX;
    public static final String LIBSUFFIX;
    public static final String LIBC;
    public static final int BIG_ENDIAN = 4321;
    public static final int LITTLE_ENDIAN = 1234;
    public static final int BYTE_ORDER;
    protected final int addressSize;
    protected final int longSize;
    private final long addressMask;
    protected final Pattern libPattern;
    private final int javaVersionMajor;
    
    private static final OS_TYPE determineOS() {
        final String osName = System.getProperty("os.name").split(" ")[0].toLowerCase();
        if (osName.startsWith("mac") || osName.startsWith("darwin") || osName.equalsIgnoreCase("darwin")) {
            final OS_TYPE os2 = Platform.OS;
            return OS_TYPE.DARWIN;
        }
        if (osName.startsWith("sunos") || osName.startsWith("solaris")) {
            final OS_TYPE os3 = Platform.OS;
            return OS_TYPE.SOLARIS;
        }
        final OS_TYPE os4 = Platform.OS;
        for (final OS_TYPE os : OS_TYPE.values()) {
            if (osName.startsWith(os.toString().toLowerCase())) {
                return os;
            }
        }
        final OS_TYPE os5 = Platform.OS;
        return OS_TYPE.UNKNOWN;
    }
    
    private static final Platform determinePlatform(final OS_TYPE os) {
        switch (os) {
            case DARWIN: {
                return new Darwin();
            }
            case LINUX: {
                return new Linux();
            }
            case AIX: {
                return new AIX();
            }
            case WINDOWS: {
                return new Windows();
            }
            case UNKNOWN: {
                return new Unsupported(os);
            }
            default: {
                return new Default(os);
            }
        }
    }
    
    private static final CPU_TYPE determineCPU() {
        final String archString = System.getProperty("os.arch").toLowerCase();
        if ("x86".equals(archString) || "i386".equals(archString) || "i86pc".equals(archString)) {
            final CPU_TYPE cpu = Platform.CPU;
            return CPU_TYPE.I386;
        }
        if ("x86_64".equals(archString) || "amd64".equals(archString)) {
            final CPU_TYPE cpu2 = Platform.CPU;
            return CPU_TYPE.X86_64;
        }
        if ("ppc".equals(archString) || "powerpc".equals(archString)) {
            final CPU_TYPE cpu3 = Platform.CPU;
            return CPU_TYPE.POWERPC;
        }
        if ("ppc64".equals(archString)) {
            final CPU_TYPE cpu4 = Platform.CPU;
            return CPU_TYPE.POWERPC64;
        }
        if ("sparc".equals(archString)) {
            final CPU_TYPE cpu5 = Platform.CPU;
            return CPU_TYPE.SPARC;
        }
        if ("sparcv9".equals(archString)) {
            final CPU_TYPE cpu6 = Platform.CPU;
            return CPU_TYPE.SPARCV9;
        }
        if ("s390x".equals(archString)) {
            final CPU_TYPE cpu7 = Platform.CPU;
            return CPU_TYPE.S390X;
        }
        if ("universal".equals(archString)) {
            final String bits = SafePropertyAccessor.getProperty("sun.arch.data.model");
            if ("32".equals(bits)) {
                System.setProperty("os.arch", "i386");
                final CPU_TYPE cpu8 = Platform.CPU;
                return CPU_TYPE.I386;
            }
            if ("64".equals(bits)) {
                System.setProperty("os.arch", "x86_64");
                final CPU_TYPE cpu9 = Platform.CPU;
                return CPU_TYPE.X86_64;
            }
        }
        final CPU_TYPE cpu10 = Platform.CPU;
        return CPU_TYPE.UNKNOWN;
    }
    
    private static final String determineLibC() {
        switch (Platform.OS) {
            case WINDOWS: {
                return "msvcrt.dll";
            }
            case LINUX: {
                return "libc.so.6";
            }
            case AIX: {
                if (Integer.getInteger("sun.arch.data.model") == 32) {
                    return "libc.a(shr.o)";
                }
                return "libc.a(shr_64.o)";
            }
            default: {
                return Platform.LIBPREFIX + "c." + Platform.LIBSUFFIX;
            }
        }
    }
    
    private static final String determineLibExt() {
        switch (Platform.OS) {
            case WINDOWS: {
                return "dll";
            }
            case AIX: {
                return "a";
            }
            case DARWIN: {
                return "dylib";
            }
            default: {
                return "so";
            }
        }
    }
    
    protected Platform(final OS_TYPE os) {
        int dataModel = Integer.getInteger("sun.arch.data.model");
        if (dataModel != 32 && dataModel != 64) {
            switch (Platform.CPU) {
                case I386:
                case POWERPC:
                case SPARC: {
                    dataModel = 32;
                    break;
                }
                case X86_64:
                case POWERPC64:
                case SPARCV9:
                case S390X: {
                    dataModel = 64;
                    break;
                }
                default: {
                    dataModel = 0;
                    break;
                }
            }
        }
        this.addressSize = dataModel;
        this.addressMask = ((this.addressSize == 32) ? 4294967295L : -1L);
        final OS_TYPE os2 = Platform.OS;
        this.longSize = ((os == OS_TYPE.WINDOWS) ? 32 : this.addressSize);
        String libpattern = null;
        switch (os) {
            case WINDOWS: {
                libpattern = ".*\\.dll$";
                break;
            }
            case DARWIN: {
                libpattern = "lib.*\\.(dylib|jnilib)$";
                break;
            }
            case AIX: {
                libpattern = "lib.*\\.a$";
                break;
            }
            default: {
                libpattern = "lib.*\\.so.*$";
                break;
            }
        }
        this.libPattern = Pattern.compile(libpattern);
        int version = 5;
        try {
            final String versionString = System.getProperty("java.version");
            if (versionString != null) {
                final String[] v = versionString.split("\\.");
                version = Integer.valueOf(v[1]);
            }
        }
        catch (Exception ex) {
            version = 0;
        }
        this.javaVersionMajor = version;
    }
    
    public static final Platform getPlatform() {
        return SingletonHolder.PLATFORM;
    }
    
    public final OS_TYPE getOS() {
        return Platform.OS;
    }
    
    public final CPU_TYPE getCPU() {
        return Platform.CPU;
    }
    
    public final int getJavaMajorVersion() {
        return this.javaVersionMajor;
    }
    
    public final boolean isBSD() {
        final OS_TYPE os = Platform.OS;
        final OS_TYPE os2 = Platform.OS;
        if (os != OS_TYPE.FREEBSD) {
            final OS_TYPE os3 = Platform.OS;
            final OS_TYPE os4 = Platform.OS;
            if (os3 != OS_TYPE.OPENBSD) {
                final OS_TYPE os5 = Platform.OS;
                final OS_TYPE os6 = Platform.OS;
                if (os5 != OS_TYPE.NETBSD) {
                    final OS_TYPE os7 = Platform.OS;
                    final OS_TYPE os8 = Platform.OS;
                    if (os7 != OS_TYPE.DARWIN) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public final boolean isUnix() {
        final OS_TYPE os = Platform.OS;
        final OS_TYPE os2 = Platform.OS;
        return os != OS_TYPE.WINDOWS;
    }
    
    public final boolean isSupported() {
        final OS_TYPE os = Platform.OS;
        final OS_TYPE os2 = Platform.OS;
        if (os != OS_TYPE.UNKNOWN) {
            final CPU_TYPE cpu = Platform.CPU;
            final CPU_TYPE cpu2 = Platform.CPU;
            if (cpu != CPU_TYPE.UNKNOWN && (this.addressSize == 32 || this.addressSize == 64) && this.javaVersionMajor >= 5) {
                return true;
            }
        }
        return false;
    }
    
    public static void createPlatformModule(final Ruby runtime, final RubyModule ffi) {
        final RubyModule module = ffi.defineModuleUnder("Platform");
        final Platform platform = getPlatform();
        final OS_TYPE os = platform.getOS();
        module.defineConstant("ADDRESS_SIZE", runtime.newFixnum(platform.addressSize));
        module.defineConstant("LONG_SIZE", runtime.newFixnum(platform.longSize));
        module.defineConstant("OS", runtime.newString(Platform.OS.toString()));
        module.defineConstant("ARCH", runtime.newString(platform.getCPU().toString()));
        module.defineConstant("NAME", runtime.newString(platform.getName()));
        final RubyModule rubyModule = module;
        final String name = "IS_WINDOWS";
        final OS_TYPE os_TYPE = os;
        final OS_TYPE os2 = Platform.OS;
        rubyModule.defineConstant(name, runtime.newBoolean(os_TYPE == OS_TYPE.WINDOWS));
        module.defineConstant("IS_BSD", runtime.newBoolean(platform.isBSD()));
        final RubyModule rubyModule2 = module;
        final String name2 = "IS_FREEBSD";
        final OS_TYPE os_TYPE2 = os;
        final OS_TYPE os3 = Platform.OS;
        rubyModule2.defineConstant(name2, runtime.newBoolean(os_TYPE2 == OS_TYPE.FREEBSD));
        final RubyModule rubyModule3 = module;
        final String name3 = "IS_OPENBSD";
        final OS_TYPE os_TYPE3 = os;
        final OS_TYPE os4 = Platform.OS;
        rubyModule3.defineConstant(name3, runtime.newBoolean(os_TYPE3 == OS_TYPE.OPENBSD));
        final RubyModule rubyModule4 = module;
        final String name4 = "IS_SOLARIS";
        final OS_TYPE os_TYPE4 = os;
        final OS_TYPE os5 = Platform.OS;
        rubyModule4.defineConstant(name4, runtime.newBoolean(os_TYPE4 == OS_TYPE.SOLARIS));
        final RubyModule rubyModule5 = module;
        final String name5 = "IS_LINUX";
        final OS_TYPE os_TYPE5 = os;
        final OS_TYPE os6 = Platform.OS;
        rubyModule5.defineConstant(name5, runtime.newBoolean(os_TYPE5 == OS_TYPE.LINUX));
        final RubyModule rubyModule6 = module;
        final String name6 = "IS_MAC";
        final OS_TYPE os_TYPE6 = os;
        final OS_TYPE os7 = Platform.OS;
        rubyModule6.defineConstant(name6, runtime.newBoolean(os_TYPE6 == OS_TYPE.DARWIN));
        module.defineConstant("LIBC", runtime.newString(Platform.LIBC));
        module.defineConstant("LIBPREFIX", runtime.newString(Platform.LIBPREFIX));
        module.defineConstant("LIBSUFFIX", runtime.newString(Platform.LIBSUFFIX));
        module.defineConstant("BYTE_ORDER", runtime.newFixnum(Platform.BYTE_ORDER));
        module.defineConstant("BIG_ENDIAN", runtime.newFixnum(4321));
        module.defineConstant("LITTLE_ENDIAN", runtime.newFixnum(1234));
        module.defineAnnotatedMethods(Platform.class);
    }
    
    @JRubyMethod(name = { "windows?" }, module = true)
    public static IRubyObject windows_p(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        final OS_TYPE os = Platform.OS;
        final OS_TYPE os2 = Platform.OS;
        return runtime.newBoolean(os == OS_TYPE.WINDOWS);
    }
    
    @JRubyMethod(name = { "mac?" }, module = true)
    public static IRubyObject mac_p(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        final OS_TYPE os = Platform.OS;
        final OS_TYPE os2 = Platform.OS;
        return runtime.newBoolean(os == OS_TYPE.DARWIN);
    }
    
    @JRubyMethod(name = { "unix?" }, module = true)
    public static IRubyObject unix_p(final ThreadContext context, final IRubyObject recv) {
        return context.getRuntime().newBoolean(getPlatform().isUnix());
    }
    
    @JRubyMethod(name = { "bsd?" }, module = true)
    public static IRubyObject bsd_p(final ThreadContext context, final IRubyObject recv) {
        return context.getRuntime().newBoolean(getPlatform().isBSD());
    }
    
    @JRubyMethod(name = { "linux?" }, module = true)
    public static IRubyObject linux_p(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        final OS_TYPE os = Platform.OS;
        final OS_TYPE os2 = Platform.OS;
        return runtime.newBoolean(os == OS_TYPE.LINUX);
    }
    
    @JRubyMethod(name = { "solaris?" }, module = true)
    public static IRubyObject solaris_p(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        final OS_TYPE os = Platform.OS;
        final OS_TYPE os2 = Platform.OS;
        return runtime.newBoolean(os == OS_TYPE.SOLARIS);
    }
    
    public static String getProperty(final String property, final String defValue) {
        try {
            return System.getProperty(property, defValue);
        }
        catch (SecurityException se) {
            return defValue;
        }
    }
    
    public final int longSize() {
        return this.longSize;
    }
    
    public final int addressSize() {
        return this.addressSize;
    }
    
    public final long addressMask() {
        return this.addressMask;
    }
    
    public String getName() {
        return Platform.CPU + "-" + Platform.OS;
    }
    
    public String mapLibraryName(final String libName) {
        if (this.libPattern.matcher(libName).find()) {
            return libName;
        }
        return System.mapLibraryName(libName);
    }
    
    static {
        CPU = determineCPU();
        OS = determineOS();
        NAME = Platform.CPU + "-" + Platform.OS;
        final OS_TYPE os = Platform.OS;
        final OS_TYPE os2 = Platform.OS;
        LIBPREFIX = ((os == OS_TYPE.WINDOWS) ? "" : "lib");
        LIBSUFFIX = determineLibExt();
        LIBC = determineLibC();
        BYTE_ORDER = (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN) ? 4321 : 1234);
    }
    
    public enum OS_TYPE
    {
        DARWIN, 
        FREEBSD, 
        NETBSD, 
        OPENBSD, 
        LINUX, 
        SOLARIS, 
        AIX, 
        WINDOWS, 
        UNKNOWN;
        
        public String toString() {
            return this.name().toLowerCase();
        }
    }
    
    public enum CPU_TYPE
    {
        I386, 
        X86_64, 
        POWERPC, 
        POWERPC64, 
        SPARC, 
        SPARCV9, 
        S390X, 
        UNKNOWN;
        
        public String toString() {
            return this.name().toLowerCase();
        }
    }
    
    private static final class SingletonHolder
    {
        private static final Platform PLATFORM;
        
        static {
            PLATFORM = determinePlatform(determineOS());
        }
    }
    
    private static class Supported extends Platform
    {
        public Supported(final OS_TYPE os) {
            super(os);
        }
    }
    
    private static class Unsupported extends Platform
    {
        public Unsupported(final OS_TYPE os) {
            super(os);
        }
    }
    
    private static final class Default extends Platform
    {
        public Default(final OS_TYPE os) {
            super(os);
        }
    }
    
    private static final class Darwin extends Supported
    {
        public Darwin() {
            final OS_TYPE os = Darwin.OS;
            super(OS_TYPE.DARWIN);
        }
        
        public String mapLibraryName(final String libName) {
            if (this.libPattern.matcher(libName).find()) {
                return libName;
            }
            return "lib" + libName + ".dylib";
        }
    }
    
    private static final class Linux extends Supported
    {
        public Linux() {
            final OS_TYPE os = Linux.OS;
            super(OS_TYPE.LINUX);
        }
        
        public String mapLibraryName(final String libName) {
            return ("c".equals(libName) || "libc.so".equals(libName)) ? "libc.so.6" : super.mapLibraryName(libName);
        }
    }
    
    private static final class AIX extends Supported
    {
        public AIX() {
            final OS_TYPE os = AIX.OS;
            super(OS_TYPE.AIX);
        }
        
        public String mapLibraryName(final String libName) {
            return ("c".equals(libName) || "libc.so".equals(libName)) ? AIX.LIBC : super.mapLibraryName(libName);
        }
    }
    
    private static class Windows extends Supported
    {
        public Windows() {
            final OS_TYPE os = Windows.OS;
            super(OS_TYPE.WINDOWS);
        }
    }
}
