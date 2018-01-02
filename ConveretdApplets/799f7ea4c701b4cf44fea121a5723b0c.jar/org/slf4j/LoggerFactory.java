// 
// Decompiled by Procyon v0.5.30
// 

package org.slf4j;

import java.util.Enumeration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.impl.StaticLoggerBinder;
import java.util.List;
import org.slf4j.helpers.Util;
import org.slf4j.helpers.SubstituteLoggerFactory;

public final class LoggerFactory
{
    static final String NO_STATICLOGGERBINDER_URL = "http://www.slf4j.org/codes.html#StaticLoggerBinder";
    static final String MULTIPLE_BINDINGS_URL = "http://www.slf4j.org/codes.html#multiple_bindings";
    static final String NULL_LF_URL = "http://www.slf4j.org/codes.html#null_LF";
    static final String VERSION_MISMATCH = "http://www.slf4j.org/codes.html#version_mismatch";
    static final String SUBSTITUTE_LOGGER_URL = "http://www.slf4j.org/codes.html#substituteLogger";
    static final String UNSUCCESSFUL_INIT_URL = "http://www.slf4j.org/codes.html#unsuccessfulInit";
    static final String UNSUCCESSFUL_INIT_MSG = "org.slf4j.LoggerFactory could not be successfully initialized. See also http://www.slf4j.org/codes.html#unsuccessfulInit";
    static final int UNINITIALIZED = 0;
    static final int ONGOING_INITILIZATION = 1;
    static final int FAILED_INITILIZATION = 2;
    static final int SUCCESSFUL_INITILIZATION = 3;
    static final int GET_SINGLETON_INEXISTENT = 1;
    static final int GET_SINGLETON_EXISTS = 2;
    static int INITIALIZATION_STATE;
    static int GET_SINGLETON_METHOD;
    static SubstituteLoggerFactory TEMP_FACTORY;
    private static final String[] API_COMPATIBILITY_LIST;
    private static String STATIC_LOGGER_BINDER_PATH;
    static /* synthetic */ Class class$org$slf4j$LoggerFactory;
    
    static void reset() {
        LoggerFactory.INITIALIZATION_STATE = 0;
        LoggerFactory.GET_SINGLETON_METHOD = 0;
        LoggerFactory.TEMP_FACTORY = new SubstituteLoggerFactory();
    }
    
    private static final void performInitialization() {
        bind();
        versionSanityCheck();
        singleImplementationSanityCheck();
    }
    
    private static final void bind() {
        try {
            getSingleton();
            LoggerFactory.INITIALIZATION_STATE = 3;
            emitSubstituteLoggerWarning();
        }
        catch (NoClassDefFoundError ncde) {
            LoggerFactory.INITIALIZATION_STATE = 2;
            final String msg = ncde.getMessage();
            if (msg != null && msg.indexOf("org/slf4j/impl/StaticLoggerBinder") != -1) {
                Util.reportFailure("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
                Util.reportFailure("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
            }
            throw ncde;
        }
        catch (Exception e) {
            LoggerFactory.INITIALIZATION_STATE = 2;
            Util.reportFailure("Failed to instantiate logger [" + getSingleton().getLoggerFactoryClassStr() + "]", e);
        }
    }
    
    private static final void emitSubstituteLoggerWarning() {
        final List loggerNameList = LoggerFactory.TEMP_FACTORY.getLoggerNameList();
        if (loggerNameList.size() == 0) {
            return;
        }
        Util.reportFailure("The following loggers will not work becasue they were created");
        Util.reportFailure("during the default configuration phase of the underlying logging system.");
        Util.reportFailure("See also http://www.slf4j.org/codes.html#substituteLogger");
        for (int i = 0; i < loggerNameList.size(); ++i) {
            final String loggerName = loggerNameList.get(i);
            Util.reportFailure(loggerName);
        }
    }
    
    private static final void versionSanityCheck() {
        try {
            final String requested = StaticLoggerBinder.REQUESTED_API_VERSION;
            boolean match = false;
            for (int i = 0; i < LoggerFactory.API_COMPATIBILITY_LIST.length; ++i) {
                if (requested.startsWith(LoggerFactory.API_COMPATIBILITY_LIST[i])) {
                    match = true;
                }
            }
            if (!match) {
                Util.reportFailure("The requested version " + requested + " by your slf4j binding is not compatible with " + Arrays.asList(LoggerFactory.API_COMPATIBILITY_LIST).toString());
                Util.reportFailure("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
            }
        }
        catch (NoSuchFieldError nsfe) {}
        catch (Throwable e) {
            Util.reportFailure("Unexpected problem occured during version sanity check", e);
        }
    }
    
    private static void singleImplementationSanityCheck() {
        try {
            final ClassLoader loggerFactoryClassLoader = ((LoggerFactory.class$org$slf4j$LoggerFactory == null) ? (LoggerFactory.class$org$slf4j$LoggerFactory = class$("org.slf4j.LoggerFactory")) : LoggerFactory.class$org$slf4j$LoggerFactory).getClassLoader();
            if (loggerFactoryClassLoader == null) {
                return;
            }
            final Enumeration paths = loggerFactoryClassLoader.getResources(LoggerFactory.STATIC_LOGGER_BINDER_PATH);
            final List implementationList = new ArrayList();
            while (paths.hasMoreElements()) {
                final URL path = paths.nextElement();
                implementationList.add(path);
            }
            if (implementationList.size() > 1) {
                Util.reportFailure("Class path contains multiple SLF4J bindings.");
                for (int i = 0; i < implementationList.size(); ++i) {
                    Util.reportFailure("Found binding in [" + implementationList.get(i) + "]");
                }
                Util.reportFailure("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
            }
        }
        catch (IOException ioe) {
            Util.reportFailure("Error getting resources from path", ioe);
        }
    }
    
    private static final StaticLoggerBinder getSingleton() {
        if (LoggerFactory.GET_SINGLETON_METHOD == 1) {
            return StaticLoggerBinder.SINGLETON;
        }
        if (LoggerFactory.GET_SINGLETON_METHOD == 2) {
            return StaticLoggerBinder.getSingleton();
        }
        try {
            final StaticLoggerBinder singleton = StaticLoggerBinder.getSingleton();
            LoggerFactory.GET_SINGLETON_METHOD = 2;
            return singleton;
        }
        catch (NoSuchMethodError nsme) {
            LoggerFactory.GET_SINGLETON_METHOD = 1;
            return StaticLoggerBinder.SINGLETON;
        }
    }
    
    public static Logger getLogger(final String name) {
        final ILoggerFactory iLoggerFactory = getILoggerFactory();
        return iLoggerFactory.getLogger(name);
    }
    
    public static Logger getLogger(final Class clazz) {
        return getLogger(clazz.getName());
    }
    
    public static ILoggerFactory getILoggerFactory() {
        if (LoggerFactory.INITIALIZATION_STATE == 0) {
            LoggerFactory.INITIALIZATION_STATE = 1;
            performInitialization();
        }
        switch (LoggerFactory.INITIALIZATION_STATE) {
            case 3: {
                return getSingleton().getLoggerFactory();
            }
            case 2: {
                throw new IllegalStateException("org.slf4j.LoggerFactory could not be successfully initialized. See also http://www.slf4j.org/codes.html#unsuccessfulInit");
            }
            case 1: {
                return LoggerFactory.TEMP_FACTORY;
            }
            default: {
                throw new IllegalStateException("Unreachable code");
            }
        }
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        LoggerFactory.INITIALIZATION_STATE = 0;
        LoggerFactory.GET_SINGLETON_METHOD = 0;
        LoggerFactory.TEMP_FACTORY = new SubstituteLoggerFactory();
        API_COMPATIBILITY_LIST = new String[] { "1.5.5", "1.5.6", "1.5.7", "1.5.8", "1.5.9", "1.5.10", "1.5.11" };
        LoggerFactory.STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";
    }
}
