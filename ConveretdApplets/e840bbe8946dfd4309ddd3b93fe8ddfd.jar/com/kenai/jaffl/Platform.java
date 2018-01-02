// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl;

import java.util.Collection;
import java.util.Arrays;
import java.util.LinkedList;
import java.io.FilenameFilter;
import java.util.Iterator;
import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

public abstract class Platform
{
    private final OS os;
    private final CPU cpu;
    private final int longSize;
    private final int addressSize;
    private final long addressMask;
    private final int javaVersionMajor;
    protected final Pattern libPattern;
    
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
        return OS.UNKNOWN;
    }
    
    private static final Platform determinePlatform(final OS os) {
        switch (os) {
            case DARWIN: {
                return new Darwin();
            }
            case LINUX: {
                return new Linux();
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
    
    private static final CPU determineCPU() {
        final String archString = System.getProperty("os.arch").toLowerCase();
        if ("x86".equals(archString) || "i386".equals(archString) || "i86pc".equals(archString)) {
            return CPU.I386;
        }
        if ("x86_64".equals(archString) || "amd64".equals(archString)) {
            return CPU.X86_64;
        }
        if ("ppc".equals(archString) || "powerpc".equals(archString)) {
            return CPU.PPC;
        }
        try {
            return CPU.valueOf(archString.toUpperCase());
        }
        catch (IllegalArgumentException ex) {
            return CPU.UNKNOWN;
        }
    }
    
    private Platform(final OS os) {
        this.os = os;
        this.cpu = determineCPU();
        int dataModel = Integer.getInteger("sun.arch.data.model");
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
            default: {
                libpattern = "lib.*\\.so.*$";
                break;
            }
        }
        this.libPattern = Pattern.compile(libpattern);
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
    
    public final boolean isBSD() {
        if (this.os != OS.FREEBSD) {
            final OS os = this.os;
            final OS os2 = this.os;
            if (os != OS.OPENBSD && this.os != OS.NETBSD && this.os != OS.DARWIN) {
                return false;
            }
        }
        return true;
    }
    
    public final boolean isUnix() {
        return this.os != OS.WINDOWS;
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
        return this.cpu + "-" + this.os;
    }
    
    public String mapLibraryName(final String libName) {
        if (this.libPattern.matcher(libName).find()) {
            return libName;
        }
        return System.mapLibraryName(libName);
    }
    
    public String locateLibrary(final String libName, final List<String> libraryPath) {
        final String mappedName = this.mapLibraryName(libName);
        for (final String path : libraryPath) {
            final File libFile = new File(path, mappedName);
            if (libFile.exists()) {
                return libFile.getAbsolutePath();
            }
        }
        return mappedName;
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
    
    private static class Supported extends Platform
    {
        public Supported(final OS os) {
            super(os, null);
        }
    }
    
    private static class Unsupported extends Platform
    {
        public Unsupported(final OS os) {
            super(os, null);
        }
    }
    
    private static final class Default extends Supported
    {
        public Default(final OS os) {
            super(os);
        }
    }
    
    private static final class Darwin extends Supported
    {
        public Darwin() {
            super(OS.DARWIN);
        }
        
        public String mapLibraryName(final String libName) {
            if (this.libPattern.matcher(libName).find()) {
                return libName;
            }
            return "lib" + libName + ".dylib";
        }
        
        public String getName() {
            return "Darwin";
        }
    }
    
    private static final class Linux extends Supported
    {
        public Linux() {
            super(OS.LINUX);
        }
        
        public String locateLibrary(final String libName, final List<String> libraryPath) {
            final FilenameFilter filter = new FilenameFilter() {
                Pattern p = Pattern.compile("lib" + libName + "\\.so\\.[0-9]+$");
                String exact = "lib" + libName + ".so";
                
                public boolean accept(final File dir, final String name) {
                    return this.p.matcher(name).matches() || this.exact.equals(name);
                }
            };
            final List<File> matches = new LinkedList<File>();
            for (final String path : libraryPath) {
                final File[] files = new File(path).listFiles(filter);
                if (files != null && files.length > 0) {
                    matches.addAll(Arrays.asList(files));
                }
            }
            int version = 0;
            String bestMatch = null;
            for (final File file : matches) {
                final String path2 = file.getAbsolutePath();
                if (bestMatch == null && path2.endsWith(".so")) {
                    bestMatch = path2;
                    version = 0;
                }
                else {
                    final String num = path2.substring(path2.lastIndexOf(".so.") + 4);
                    try {
                        if (Integer.parseInt(num) < version) {
                            continue;
                        }
                        bestMatch = path2;
                    }
                    catch (NumberFormatException ex) {}
                }
            }
            return (bestMatch != null) ? bestMatch : this.mapLibraryName(libName);
        }
        
        public String mapLibraryName(final String libName) {
            return ("c".equals(libName) || "libc.so".equals(libName)) ? "libc.so.6" : super.mapLibraryName(libName);
        }
    }
    
    private static class Windows extends Supported
    {
        public Windows() {
            super(OS.WINDOWS);
        }
    }
}
