// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

public abstract class Platform
{
    private final OS os;
    private final CPU cpu;
    private final int addressSize;
    private final long addressMask;
    private final int longSize;
    private final int javaVersionMajor;
    
    private static final OS determineOS() {
        final String osName = System.getProperty("os.name").split(" ")[0].toLowerCase();
        if (osName.startsWith("mac") || osName.startsWith("darwin")) {
            return OS.DARWIN;
        }
        if (osName.startsWith("linux")) {
            return OS.LINUX;
        }
        if (osName.startsWith("sunos") || osName.startsWith("solaris")) {
            return OS.SOLARIS;
        }
        if (osName.startsWith("aix")) {
            return OS.AIX;
        }
        if (osName.startsWith("openbsd")) {
            return OS.OPENBSD;
        }
        if (osName.startsWith("freebsd")) {
            return OS.FREEBSD;
        }
        if (osName.startsWith("windows")) {
            return OS.WINDOWS;
        }
        throw new ExceptionInInitializerError("Unsupported operating system");
    }
    
    private static final Platform determinePlatform(final OS os) {
        switch (os) {
            case DARWIN: {
                return new Darwin();
            }
            case WINDOWS: {
                return new Windows();
            }
            case UNKNOWN: {
                throw new ExceptionInInitializerError("Unsupported operating system");
            }
            default: {
                return new Default(os);
            }
        }
    }
    
    private static final CPU determineCPU() {
        final String archString = System.getProperty("os.arch", "unknown").toLowerCase();
        if ("x86".equals(archString) || "i386".equals(archString) || "i86pc".equals(archString)) {
            return CPU.I386;
        }
        if ("x86_64".equals(archString) || "amd64".equals(archString)) {
            return CPU.X86_64;
        }
        if ("ppc".equals(archString) || "powerpc".equals(archString)) {
            return CPU.PPC;
        }
        if ("powerpc64".equals(archString)) {
            return CPU.PPC64;
        }
        try {
            return CPU.valueOf(archString.toUpperCase());
        }
        catch (IllegalArgumentException ex) {
            throw new ExceptionInInitializerError("Unsupported CPU architecture: " + archString);
        }
    }
    
    private Platform(final OS os) {
        this.os = os;
        this.cpu = determineCPU();
        int dataModel = Integer.getInteger("sun.arch.data.model", 0);
        if (dataModel != 32 && dataModel != 64) {
            switch (this.cpu) {
                case I386:
                case PPC:
                case SPARC: {
                    dataModel = 32;
                    break;
                }
                case X86_64:
                case PPC64:
                case SPARCV9:
                case S390X: {
                    dataModel = 64;
                    break;
                }
                default: {
                    throw new ExceptionInInitializerError("Cannot determine cpu address size");
                }
            }
        }
        this.addressSize = dataModel;
        this.addressMask = ((this.addressSize == 32) ? 4294967295L : -1L);
        this.longSize = ((os == OS.WINDOWS) ? 32 : this.addressSize);
        int version = 5;
        try {
            final String versionString = System.getProperty("java.version");
            if (versionString != null) {
                final String[] v = versionString.split("\\.");
                version = Integer.valueOf(v[1]);
            }
        }
        catch (Exception ex) {
            throw new ExceptionInInitializerError("Could not determine java version");
        }
        this.javaVersionMajor = version;
    }
    
    public static final Platform getPlatform() {
        return SingletonHolder.PLATFORM;
    }
    
    public final OS getOS() {
        return this.os;
    }
    
    public final CPU getCPU() {
        return this.cpu;
    }
    
    public final int getJavaMajorVersion() {
        return this.javaVersionMajor;
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
        final String osName = System.getProperty("os.name").split(" ")[0];
        return this.getCPU().name().toLowerCase() + "-" + osName;
    }
    
    public String mapLibraryName(final String libName) {
        if (libName.matches(this.getLibraryNamePattern())) {
            return libName;
        }
        return System.mapLibraryName(libName);
    }
    
    public String getLibraryNamePattern() {
        return "lib.*\\.so.*$";
    }
    
    public boolean isSupported() {
        final int version = Foreign.getInstance().getVersion();
        if ((version & 0xFFFF00) == (Foreign.VERSION_MAJOR << 16 | Foreign.VERSION_MINOR << 8)) {
            return true;
        }
        throw new UnsatisfiedLinkError("Incorrect native library version");
    }
    
    public enum OS
    {
        DARWIN, 
        FREEBSD, 
        NETBSD, 
        OPENBSD, 
        LINUX, 
        SOLARIS, 
        WINDOWS, 
        AIX, 
        ZLINUX, 
        UNKNOWN;
        
        public String toString() {
            return this.name().toLowerCase();
        }
    }
    
    public enum CPU
    {
        I386, 
        X86_64, 
        PPC, 
        PPC64, 
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
        static final Platform PLATFORM;
        
        static {
            PLATFORM = determinePlatform(determineOS());
        }
    }
    
    private static final class Default extends Platform
    {
        public Default(final OS os) {
            super(os, null);
        }
    }
    
    private static final class Darwin extends Platform
    {
        public Darwin() {
            super(OS.DARWIN, null);
        }
        
        public String mapLibraryName(final String libName) {
            if (libName.matches(this.getLibraryNamePattern())) {
                return libName;
            }
            return "lib" + libName + ".dylib";
        }
        
        public String getLibraryNamePattern() {
            return "lib.*\\.(dylib|jnilib)$";
        }
        
        public String getName() {
            return "Darwin";
        }
    }
    
    private static class Windows extends Platform
    {
        public Windows() {
            super(OS.WINDOWS, null);
        }
        
        public String getLibraryNamePattern() {
            return ".*\\.dll$";
        }
    }
}
