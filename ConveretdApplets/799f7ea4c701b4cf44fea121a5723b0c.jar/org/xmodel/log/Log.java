// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.log;

import java.util.HashMap;
import java.util.Map;

public class Log
{
    public static final int exception = 128;
    public static final int verbose = 64;
    public static final int debug = 32;
    public static final int info = 16;
    public static final int warn = 8;
    public static final int error = 4;
    public static final int severe = 2;
    public static final int fatal = 1;
    public static final int problems = 143;
    public static final int all = 255;
    private static Map<String, Log> B;
    private String A;
    private int D;
    private ILogSink C;
    
    static {
        Log.B = new HashMap<String, Log>();
    }
    
    public static final String getLevelName(final int n) {
        switch (n) {
            case 128: {
                return "Exception";
            }
            case 64: {
                return "Verbose";
            }
            case 32: {
                return "Debug";
            }
            case 16: {
                return "Info";
            }
            case 8: {
                return "Warn";
            }
            case 4: {
                return "Error";
            }
            case 2: {
                return "Severe";
            }
            case 1: {
                return "Fatal";
            }
            default: {
                return null;
            }
        }
    }
    
    public static final int getLevelIndex(final String s) {
        if (s.equalsIgnoreCase("verbose")) {
            return 64;
        }
        if (s.equalsIgnoreCase("debug")) {
            return 32;
        }
        if (s.equalsIgnoreCase("info")) {
            return 16;
        }
        if (s.equalsIgnoreCase("warn")) {
            return 8;
        }
        if (s.equalsIgnoreCase("error")) {
            return 4;
        }
        if (s.equalsIgnoreCase("severe")) {
            return 2;
        }
        if (s.equalsIgnoreCase("fatal")) {
            return 1;
        }
        if (s.equalsIgnoreCase("exception")) {
            return 128;
        }
        throw new IllegalArgumentException(String.format("Invalid log level name, %s.", s));
    }
    
    public static Log getLog(final String s) {
        Log log = Log.B.get(s);
        if (log == null) {
            log = new Log(s);
        }
        Log.B.put(s, log);
        return log;
    }
    
    public static Log getLog(final Class<?> clazz) {
        return getLog(clazz.getName());
    }
    
    public Log(final String a) {
        this.A = a;
        this.D = -1;
    }
    
    public String getName() {
        return this.A;
    }
    
    public void setLevel(final int d) {
        if (this.D < 0) {
            this.A();
        }
        this.D = d;
    }
    
    public void setSink(final ILogSink c) {
        if (this.D < 0) {
            this.A();
        }
        this.C = c;
    }
    
    public boolean isLevelEnabled(final int n) {
        if (this.D < 0) {
            this.A();
        }
        return (this.D & n) != 0x0;
    }
    
    public void verbose(final Object o) {
        this.log(64, o);
    }
    
    public void verbose(final Object o, final Throwable t) {
        this.log(64, o, t);
    }
    
    public void verbosef(final String s, final Object... array) {
        this.logf(64, s, array);
    }
    
    public void debug(final Object o) {
        this.log(32, o);
    }
    
    public void debug(final Object o, final Throwable t) {
        this.log(32, o, t);
    }
    
    public void debugf(final String s, final Object... array) {
        this.logf(32, s, array);
    }
    
    public void info(final Object o) {
        this.log(16, o);
    }
    
    public void info(final Object o, final Throwable t) {
        this.log(16, o, t);
    }
    
    public void infof(final String s, final Object... array) {
        this.logf(16, s, array);
    }
    
    public void warn(final Object o) {
        this.log(8, o);
    }
    
    public void warn(final Object o, final Throwable t) {
        this.log(8, o, t);
    }
    
    public void warnf(final String s, final Object... array) {
        this.logf(8, s, array);
    }
    
    public void error(final Object o) {
        this.log(4, o);
    }
    
    public void error(final Object o, final Throwable t) {
        this.log(4, o, t);
    }
    
    public void errorf(final String s, final Object... array) {
        this.logf(4, s, array);
    }
    
    public void severe(final Object o) {
        this.log(2, o);
    }
    
    public void severe(final Object o, final Throwable t) {
        this.log(2, o, t);
    }
    
    public void severef(final String s, final Object... array) {
        this.logf(2, s, array);
    }
    
    public void fatal(final Object o) {
        this.log(1, o);
    }
    
    public void fatal(final Object o, final Throwable t) {
        this.log(1, o, t);
    }
    
    public void fatalf(final String s, final Object... array) {
        this.logf(1, s, array);
    }
    
    public void exception(final Throwable t) {
        if (this.D < 0) {
            this.A();
        }
        if ((this.D & 0x80) == 0x0) {
            return;
        }
        this.C.log(this, 128, t);
    }
    
    public void exceptionf(final Throwable t, final String s, final Object... array) {
        if (this.D < 0) {
            this.A();
        }
        if ((this.D & 0x80) == 0x0) {
            return;
        }
        this.C.log(this, 128, String.format(s, array), t);
    }
    
    public void log(final int n, final Object o) {
        if (this.D < 0) {
            this.A();
        }
        if ((this.D & n) == 0x0) {
            return;
        }
        this.C.log(this, n, o);
    }
    
    public void log(final int n, final Object o, final Throwable t) {
        if (this.D < 0) {
            this.A();
        }
        if ((this.D & n) == 0x0) {
            return;
        }
        this.C.log(this, n, o, t);
    }
    
    public void logf(final int n, final String s, final Object... array) {
        if (this.D < 0) {
            this.A();
        }
        if ((this.D & n) == 0x0) {
            return;
        }
        this.C.log(this, n, String.format(s, array));
    }
    
    private void A() {
        this.D = 255;
        try {
            this.C = new MultiSink(new ILogSink[] { new FormatSink(new ConsoleSink()), new FormatSink(new SyslogSink()) });
        }
        catch (Exception ex) {
            this.C = new FormatSink(new ConsoleSink());
        }
    }
}
