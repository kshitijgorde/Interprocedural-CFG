import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class aq
{
    public static en a;
    public static Hashtable b;
    
    public static final en a() {
        return aq.a;
    }
    
    static {
        (aq.a = new en("LogProperties")).a("LOG", new eo("LOG", "WARN", et.o, "String", 2));
        aq.a.a("LOG_APPLICATION", new eo("LOG_APPLICATION", "unassigned", et.o, "String", 2));
        aq.a.a("APPENDER_SETUP", new eo("APPENDER_SETUP", "com.is_teledata.log.ConsoleAppender;false; ", et.o, "String", 2));
        aq.a.a("LOGLOG_LEVEL", new eo("LOGLOG_LEVEL", "OFF", et.o, "String", 2));
        aq.a.a("STACKTRACE_LEVEL", new eo("STACKTRACE_LEVEL", "DEBUG", et.o, "String", 2));
        aq.a.a("ENABLE_ASSERTIONS", new eo("ENABLE_ASSERTIONS", "false", et.p, "Boolean", 2));
        (aq.b = new Hashtable()).put("LOG", "Specifies the value for {@link Setup#levelSetup}");
        aq.b.put("LOG_APPLICATION", "The application string for {@link Setup#setApplication}");
        aq.b.put("APPENDER_SETUP", "The setup string for {@link Setup#appenderSetup}");
        aq.b.put("LOGLOG_LEVEL", "Defines the loglog {@link Level}");
        aq.b.put("STACKTRACE_LEVEL", "Defines the {@link Level} at which stacktraces are printed");
        aq.b.put("ENABLE_ASSERTIONS", "Defines whether assertions are enabled");
    }
}
