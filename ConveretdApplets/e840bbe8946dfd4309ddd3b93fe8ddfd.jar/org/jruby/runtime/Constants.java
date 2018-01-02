// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

public final class Constants
{
    public static final String PLATFORM = "java";
    public static final int MARSHAL_MAJOR = 4;
    public static final int MARSHAL_MINOR = 8;
    public static final String RUBY_MAJOR_VERSION = "1.8";
    public static final String RUBY_VERSION = "1.8.7";
    public static final int RUBY_PATCHLEVEL;
    public static final String RUBY1_9_MAJOR_VERSION = "1.9";
    public static final String RUBY1_9_VERSION = "1.9.2";
    public static final int RUBY1_9_PATCHLEVEL;
    public static final int RUBY1_9_REVISION;
    public static final String COMPILE_DATE = "2011-05-23";
    public static final String VERSION = "1.6.2";
    public static final String BUILD = "java1.5";
    public static final String TARGET = "java1.5";
    public static final String REVISION;
    public static final String ENGINE = "jruby";
    public static final String JODA_TIME_VERSION = "1.6.2";
    public static final String TZDATA_VERSION = "2010k";
    @Deprecated
    public static final String JRUBY_PROPERTIES = "/org/jruby/jruby.properties";
    
    static {
        RUBY_PATCHLEVEL = Integer.parseInt("330");
        RUBY1_9_PATCHLEVEL = Integer.parseInt("136");
        RUBY1_9_REVISION = Integer.parseInt("27758");
        REVISION = "e2ea975";
    }
}
