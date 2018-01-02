// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.util.logging;

public class Level
{
    public static final String Ident = "$Id: Level.java,v 1.2 2009/06/12 20:34:24 blm Exp $";
    public static final Level OFF;
    public static final Level SEVERE;
    public static final Level WARNING;
    public static final Level INFO;
    public static final Level CONFIG;
    public static final Level FINE;
    public static final Level FINER;
    public static final Level FINEST;
    public static final Level ALL;
    private static Level[] levels;
    private String name;
    private int level;
    
    public static Level parse(final String s) throws IllegalArgumentException {
        for (int length = Level.levels.length, i = 0; i < length; ++i) {
            final Level level = Level.levels[i];
            if (s.equals(level.getName()) || s.equals(String.valueOf(level.intValue()))) {
                return level;
            }
        }
        throw new IllegalArgumentException("Unknown level `" + s + "'");
    }
    
    public int intValue() {
        return this.level;
    }
    
    protected Level(final String name, final int level) {
        this.name = name;
        this.level = level;
    }
    
    static {
        OFF = new Level("OFF", Integer.MAX_VALUE);
        SEVERE = new Level("SEVERE", 1000);
        WARNING = new Level("WARNING", 900);
        INFO = new Level("INFO", 800);
        CONFIG = new Level("CONFIG", 700);
        FINE = new Level("FINE", 500);
        FINER = new Level("FINER", 400);
        FINEST = new Level("FINEST", 300);
        ALL = new Level("ALL", Integer.MIN_VALUE);
        Level.levels = new Level[] { Level.OFF, Level.SEVERE, Level.WARNING, Level.INFO, Level.CONFIG, Level.FINE, Level.FINER, Level.FINEST, Level.ALL };
    }
    
    public String getName() {
        return this.name;
    }
}
