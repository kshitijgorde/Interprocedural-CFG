// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.mapper.TypeMapper;
import com.kenai.jaffl.Library;
import java.util.HashMap;
import org.jruby.ext.posix.util.Platform;
import com.kenai.jaffl.LibraryOption;
import java.util.Map;

public class POSIXFactory
{
    static final String LIBC;
    static final Map<LibraryOption, Object> defaultOptions;
    
    public static POSIX getPOSIX(final POSIXHandler handler, final boolean useNativePOSIX) {
        return new LazyPOSIX(handler, useNativePOSIX);
    }
    
    static POSIX loadPOSIX(final POSIXHandler handler, final boolean useNativePOSIX) {
        POSIX posix = null;
        if (useNativePOSIX) {
            try {
                if (Platform.IS_MAC) {
                    posix = loadMacOSPOSIX(handler);
                }
                else if (Platform.IS_LINUX) {
                    posix = loadLinuxPOSIX(handler);
                }
                else if (Platform.IS_FREEBSD) {
                    posix = loadFreeBSDPOSIX(handler);
                }
                else if (Platform.IS_OPENBSD) {
                    posix = loadOpenBSDPOSIX(handler);
                }
                else if (Platform.IS_SOLARIS) {
                    posix = loadSolarisPOSIX(handler);
                }
                else if (Platform.IS_WINDOWS) {
                    posix = loadWindowsPOSIX(handler);
                }
                if (handler.isVerbose()) {
                    if (posix != null) {
                        System.err.println("Successfully loaded native POSIX impl.");
                    }
                    else {
                        System.err.println("Failed to load native POSIX impl; falling back on Java impl. Unsupported OS.");
                    }
                }
            }
            catch (Throwable t) {
                if (handler.isVerbose()) {
                    System.err.println("Failed to load native POSIX impl; falling back on Java impl. Stacktrace follows.");
                    t.printStackTrace();
                }
            }
        }
        if (posix == null) {
            posix = getJavaPOSIX(handler);
        }
        return posix;
    }
    
    public static POSIX getJavaPOSIX(final POSIXHandler handler) {
        return new JavaPOSIX(handler);
    }
    
    public static POSIX loadLinuxPOSIX(final POSIXHandler handler) {
        return new LinuxPOSIX(POSIXFactory.LIBC, new LinuxLibCProvider(), handler);
    }
    
    public static POSIX loadMacOSPOSIX(final POSIXHandler handler) {
        return new MacOSPOSIX(POSIXFactory.LIBC, new DefaultLibCProvider(), handler);
    }
    
    public static POSIX loadSolarisPOSIX(final POSIXHandler handler) {
        return new SolarisPOSIX(POSIXFactory.LIBC, new SolarisLibCProvider(), handler);
    }
    
    public static POSIX loadFreeBSDPOSIX(final POSIXHandler handler) {
        return new FreeBSDPOSIX(POSIXFactory.LIBC, new DefaultLibCProvider(), handler);
    }
    
    public static POSIX loadOpenBSDPOSIX(final POSIXHandler handler) {
        return new OpenBSDPOSIX(POSIXFactory.LIBC, new DefaultLibCProvider(), handler);
    }
    
    public static POSIX loadWindowsPOSIX(final POSIXHandler handler) {
        return new WindowsPOSIX(POSIXFactory.LIBC, new WindowsLibCProvider(), handler);
    }
    
    static {
        LIBC = (Platform.IS_LINUX ? "libc.so.6" : (Platform.IS_WINDOWS ? "msvcrt" : "c"));
        defaultOptions = new HashMap<LibraryOption, Object>() {
            {
                ((HashMap<LibraryOption, TypeMapper>)this).put(LibraryOption.TypeMapper, POSIXTypeMapper.INSTANCE);
                ((HashMap<LibraryOption, Boolean>)this).put(LibraryOption.LoadNow, Boolean.TRUE);
            }
        };
    }
    
    private static final class DefaultLibCProvider implements LibCProvider
    {
        public final LibC getLibC() {
            return SingletonHolder.libc;
        }
        
        private static final class SingletonHolder
        {
            public static LibC libc;
            
            static {
                SingletonHolder.libc = Library.loadLibrary(LibC.class, POSIXFactory.defaultOptions, "c");
            }
        }
    }
    
    private static final class LinuxLibCProvider implements LibCProvider
    {
        public final LibC getLibC() {
            return SingletonHolder.libc;
        }
        
        private static final class SingletonHolder
        {
            public static LibC libc;
            
            static {
                SingletonHolder.libc = Library.loadLibrary(LinuxLibC.class, POSIXFactory.defaultOptions, "libc.so.6");
            }
        }
    }
    
    private static final class SolarisLibCProvider implements LibCProvider
    {
        public final LibC getLibC() {
            return SingletonHolder.libc;
        }
        
        private static final class SingletonHolder
        {
            public static LibC libc;
            
            static {
                SingletonHolder.libc = Library.loadLibrary(LibC.class, POSIXFactory.defaultOptions, "socket", "nsl", "c");
            }
        }
    }
    
    private static final class WindowsLibCProvider implements LibCProvider
    {
        public final LibC getLibC() {
            return SingletonHolder.libc;
        }
        
        static final Map<LibraryOption, Object> getOptions() {
            final Map<LibraryOption, Object> options = new HashMap<LibraryOption, Object>(POSIXFactory.defaultOptions);
            options.put(LibraryOption.FunctionMapper, WindowsLibCFunctionMapper.INSTANCE);
            return options;
        }
        
        static final class SingletonHolder
        {
            public static LibC libc;
            
            static {
                SingletonHolder.libc = Library.loadLibrary(WindowsLibC.class, WindowsLibCProvider.getOptions(), "msvcrt", "kernel32");
            }
        }
    }
}
