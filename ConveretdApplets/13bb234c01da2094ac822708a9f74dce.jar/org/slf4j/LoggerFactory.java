// 
// Decompiled by Procyon v0.5.30
// 

package org.slf4j;

import java.util.Arrays;
import org.slf4j.impl.StaticLoggerBinder;
import java.util.List;
import org.slf4j.helpers.Util;
import org.slf4j.helpers.SubstituteLoggerFactory;

public final class LoggerFactory
{
    static int a;
    static int b;
    static SubstituteLoggerFactory c;
    private static final String[] d;
    
    private static final void b() {
        c();
        e();
    }
    
    private static final void c() {
        try {
            f();
            LoggerFactory.a = 3;
            d();
        }
        catch (NoClassDefFoundError noClassDefFoundError) {
            LoggerFactory.a = 2;
            final String message = noClassDefFoundError.getMessage();
            if (message != null && message.indexOf("org/slf4j/impl/StaticLoggerBinder") != -1) {
                Util.a("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
                Util.a("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
            }
            throw noClassDefFoundError;
        }
        catch (Exception ex) {
            LoggerFactory.a = 2;
            Util.a("Failed to instantiate logger [" + f().c() + "]", ex);
        }
    }
    
    private static final void d() {
        final List a = LoggerFactory.c.a();
        if (a.size() == 0) {
            return;
        }
        Util.a("The following loggers will not work becasue they were created");
        Util.a("during the default configuration phase of the underlying logging system.");
        Util.a("See also http://www.slf4j.org/codes.html#substituteLogger");
        for (int i = 0; i < a.size(); ++i) {
            Util.a(a.get(i));
        }
    }
    
    private static final void e() {
        try {
            final String a = StaticLoggerBinder.a;
            boolean b = false;
            for (int i = 0; i < LoggerFactory.d.length; ++i) {
                if (LoggerFactory.d[i].equals(a)) {
                    b = true;
                }
            }
            if (!b) {
                Util.a("The requested version " + a + " by your slf4j binding is not compatible with " + Arrays.toString(LoggerFactory.d));
                Util.a("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
            }
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        catch (Throwable t) {
            Util.a("Unexpected problem occured during version sanity check", t);
        }
    }
    
    private static final StaticLoggerBinder f() {
        if (LoggerFactory.b == 1) {
            return StaticLoggerBinder.SINGLETON;
        }
        if (LoggerFactory.b == 2) {
            return StaticLoggerBinder.a();
        }
        try {
            final StaticLoggerBinder a = StaticLoggerBinder.a();
            LoggerFactory.b = 2;
            return a;
        }
        catch (NoSuchMethodError noSuchMethodError) {
            LoggerFactory.b = 1;
            return StaticLoggerBinder.SINGLETON;
        }
    }
    
    public static Logger a(final String s) {
        return a().a(s);
    }
    
    public static Logger a(final Class clazz) {
        return a(clazz.getName());
    }
    
    public static ILoggerFactory a() {
        if (LoggerFactory.a == 0) {
            LoggerFactory.a = 1;
            b();
        }
        switch (LoggerFactory.a) {
            case 3: {
                return f().b();
            }
            case 2: {
                throw new IllegalStateException("org.slf4j.LoggerFactory could not be successfully initialized. See also http://www.slf4j.org/codes.html#unsuccessfulInit");
            }
            case 1: {
                return LoggerFactory.c;
            }
            default: {
                throw new IllegalStateException("Unreachable code");
            }
        }
    }
    
    static {
        LoggerFactory.a = 0;
        LoggerFactory.b = 0;
        LoggerFactory.c = new SubstituteLoggerFactory();
        d = new String[] { "1.5.5", "1.5.6" };
    }
}
