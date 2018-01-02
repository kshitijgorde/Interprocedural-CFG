// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging;

import org.apache.log4j.Level;

public class XLevel extends Level
{
    public static final int TRACE_INT = 4900;
    public static final String TRACE_STR = "TRACE";
    public static final XLevel TRACE;
    
    protected XLevel(final int level, final String strLevel, final int syslogEquiv) {
        super(level, strLevel, syslogEquiv);
    }
    
    public static Level toLevel(final String name, final Level defaultLevel) {
        if (name == null) {
            return defaultLevel;
        }
        final String upper = name.toUpperCase();
        if (upper.equals("TRACE")) {
            return XLevel.TRACE;
        }
        return Level.toLevel(name, defaultLevel);
    }
    
    public static Level toLevel(final String name) {
        return toLevel(name, XLevel.TRACE);
    }
    
    public static Level toLevel(final int i) {
        return toLevel(i, XLevel.TRACE);
    }
    
    public static Level toLevel(final int i, final Level defaultLevel) {
        Level p;
        if (i == 4900) {
            p = XLevel.TRACE;
        }
        else {
            p = Level.toLevel(i);
        }
        return p;
    }
    
    static {
        TRACE = new XLevel(4900, "TRACE", 7);
    }
}
