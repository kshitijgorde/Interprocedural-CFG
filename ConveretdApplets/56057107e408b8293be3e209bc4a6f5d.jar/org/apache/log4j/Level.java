// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j;

import java.io.ObjectStreamException;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Level extends Priority implements Serializable
{
    public static final int TRACE_INT = 5000;
    public static final Level OFF;
    public static final Level FATAL;
    public static final Level ERROR;
    public static final Level WARN;
    public static final Level INFO;
    public static final Level DEBUG;
    public static final Level TRACE;
    public static final Level ALL;
    static final long serialVersionUID = 3491141966387921974L;
    static /* synthetic */ Class class$org$apache$log4j$Level;
    
    protected Level(final int n, final String s, final int n2) {
        super(n, s, n2);
    }
    
    public static Level toLevel(final String s) {
        return toLevel(s, Level.DEBUG);
    }
    
    public static Level toLevel(final int n) {
        return toLevel(n, Level.DEBUG);
    }
    
    public static Level toLevel(final int n, final Level level) {
        switch (n) {
            case Integer.MIN_VALUE: {
                return Level.ALL;
            }
            case 10000: {
                return Level.DEBUG;
            }
            case 20000: {
                return Level.INFO;
            }
            case 30000: {
                return Level.WARN;
            }
            case 40000: {
                return Level.ERROR;
            }
            case 50000: {
                return Level.FATAL;
            }
            case Integer.MAX_VALUE: {
                return Level.OFF;
            }
            case 5000: {
                return Level.TRACE;
            }
            default: {
                return level;
            }
        }
    }
    
    public static Level toLevel(final String s, final Level level) {
        if (s == null) {
            return level;
        }
        final String upperCase = s.toUpperCase();
        if (upperCase.equals("ALL")) {
            return Level.ALL;
        }
        if (upperCase.equals("DEBUG")) {
            return Level.DEBUG;
        }
        if (upperCase.equals("INFO")) {
            return Level.INFO;
        }
        if (upperCase.equals("WARN")) {
            return Level.WARN;
        }
        if (upperCase.equals("ERROR")) {
            return Level.ERROR;
        }
        if (upperCase.equals("FATAL")) {
            return Level.FATAL;
        }
        if (upperCase.equals("OFF")) {
            return Level.OFF;
        }
        if (upperCase.equals("TRACE")) {
            return Level.TRACE;
        }
        return level;
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        super.level = objectInputStream.readInt();
        super.syslogEquivalent = objectInputStream.readInt();
        super.levelStr = objectInputStream.readUTF();
        if (super.levelStr == null) {
            super.levelStr = "";
        }
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(super.level);
        objectOutputStream.writeInt(super.syslogEquivalent);
        objectOutputStream.writeUTF(super.levelStr);
    }
    
    private Object readResolve() throws ObjectStreamException {
        if (this.getClass() == ((Level.class$org$apache$log4j$Level == null) ? (Level.class$org$apache$log4j$Level = class$("org.apache.log4j.Level")) : Level.class$org$apache$log4j$Level)) {
            return toLevel(super.level);
        }
        return this;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        OFF = new Level(Integer.MAX_VALUE, "OFF", 0);
        FATAL = new Level(50000, "FATAL", 0);
        ERROR = new Level(40000, "ERROR", 3);
        WARN = new Level(30000, "WARN", 4);
        INFO = new Level(20000, "INFO", 6);
        DEBUG = new Level(10000, "DEBUG", 7);
        TRACE = new Level(5000, "TRACE", 7);
        ALL = new Level(Integer.MIN_VALUE, "ALL", 7);
    }
}
