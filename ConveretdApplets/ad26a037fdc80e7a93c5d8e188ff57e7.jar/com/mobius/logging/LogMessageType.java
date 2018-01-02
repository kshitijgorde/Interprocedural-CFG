// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.logging;

import java.util.Hashtable;

public final class LogMessageType
{
    private static Hashtable types;
    private String typeName;
    public static final LogMessageType debug;
    public static final LogMessageType performance;
    
    private LogMessageType(final String typeName) {
        this.typeName = null;
        this.typeName = typeName;
    }
    
    public static synchronized LogMessageType getType(final String s) {
        return LogMessageType.types.get(s);
    }
    
    public String toString() {
        return this.typeName;
    }
    
    public static synchronized LogMessageType defineType(final String s) {
        final LogMessageType type = getType(s);
        if (type != null) {
            return type;
        }
        final LogMessageType logMessageType = new LogMessageType(s);
        LogMessageType.types.put(s, logMessageType);
        return logMessageType;
    }
    
    public static synchronized void reset() {
        LogMessageType.types.clear();
        LogMessageType.types.put(LogMessageType.debug.toString(), LogMessageType.debug);
        LogMessageType.types.put(LogMessageType.performance.toString(), LogMessageType.performance);
    }
    
    static {
        LogMessageType.types = new Hashtable();
        debug = defineType("debug");
        performance = defineType("performance");
    }
}
