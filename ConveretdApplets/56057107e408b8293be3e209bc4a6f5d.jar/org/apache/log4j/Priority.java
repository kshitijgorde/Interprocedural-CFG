// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j;

public class Priority
{
    transient int level;
    transient String levelStr;
    transient int syslogEquivalent;
    public static final int OFF_INT = Integer.MAX_VALUE;
    public static final int FATAL_INT = 50000;
    public static final int ERROR_INT = 40000;
    public static final int WARN_INT = 30000;
    public static final int INFO_INT = 20000;
    public static final int DEBUG_INT = 10000;
    public static final int ALL_INT = Integer.MIN_VALUE;
    public static final Priority FATAL;
    public static final Priority ERROR;
    public static final Priority WARN;
    public static final Priority INFO;
    public static final Priority DEBUG;
    
    protected Priority() {
        this.level = 10000;
        this.levelStr = "DEBUG";
        this.syslogEquivalent = 7;
    }
    
    protected Priority(final int level, final String levelStr, final int syslogEquivalent) {
        this.level = level;
        this.levelStr = levelStr;
        this.syslogEquivalent = syslogEquivalent;
    }
    
    public boolean equals(final Object o) {
        return o instanceof Priority && this.level == ((Priority)o).level;
    }
    
    public final int getSyslogEquivalent() {
        return this.syslogEquivalent;
    }
    
    public boolean isGreaterOrEqual(final Priority priority) {
        return this.level >= priority.level;
    }
    
    public static Priority[] getAllPossiblePriorities() {
        return new Priority[] { Priority.FATAL, Priority.ERROR, Level.WARN, Priority.INFO, Priority.DEBUG };
    }
    
    public final String toString() {
        return this.levelStr;
    }
    
    public final int toInt() {
        return this.level;
    }
    
    public static Priority toPriority(final String s) {
        return Level.toLevel(s);
    }
    
    public static Priority toPriority(final int n) {
        return toPriority(n, Priority.DEBUG);
    }
    
    public static Priority toPriority(final int n, final Priority priority) {
        return Level.toLevel(n, (Level)priority);
    }
    
    public static Priority toPriority(final String s, final Priority priority) {
        return Level.toLevel(s, (Level)priority);
    }
    
    static {
        FATAL = new Level(50000, "FATAL", 0);
        ERROR = new Level(40000, "ERROR", 3);
        WARN = new Level(30000, "WARN", 4);
        INFO = new Level(20000, "INFO", 6);
        DEBUG = new Level(10000, "DEBUG", 7);
    }
}
