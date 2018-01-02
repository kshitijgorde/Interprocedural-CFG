// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.logging;

import java.util.Date;
import java.util.GregorianCalendar;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.text.DateFormat;
import java.io.PrintStream;

public class LoggingApi
{
    private String m_logFileName;
    private PrintStream m_logStream;
    private int typeIDLength;
    private boolean typeEnabled;
    private boolean threadInfoEnabled;
    private static final String TIME_PREFIX = "TIME : ";
    private static final String MESSAGE_PREFIX = "MSG : ";
    private static final String THREAD_PREFIX = "THD : ";
    private static final String CLASS_PREFIX = "CLASS : ";
    private static final String EXCEPTION_PREFIX = "EXCEPTION : ";
    DateFormat dateFormatter;
    private Hashtable enabledTypes;
    private static LoggingApi systemLogger;
    
    private synchronized void SetLogFile(final String logFileName) throws FileNotFoundException {
        this.m_logFileName = logFileName;
        this.m_logStream = new PrintStream(new FileOutputStream(logFileName), true);
    }
    
    public synchronized void ChangeLogFile(final String s) throws FileNotFoundException {
        if (!s.equalsIgnoreCase(this.m_logFileName)) {
            if (this.m_logStream != null) {
                this.m_logStream.close();
            }
            this.SetLogFile(s);
        }
    }
    
    public synchronized void ChangeLogPrintStream(final PrintStream logStream) {
        if (logStream != this.m_logStream) {
            this.m_logStream.close();
            this.m_logStream = logStream;
            this.m_logFileName = null;
        }
    }
    
    public static synchronized LoggingApi GetSystemLogger() {
        if (LoggingApi.systemLogger == null) {
            LoggingApi.systemLogger = new LoggingApi();
        }
        return LoggingApi.systemLogger;
    }
    
    public static synchronized LoggingApi GetSystemLogger(final String s) throws FileNotFoundException {
        if (LoggingApi.systemLogger == null) {
            LoggingApi.systemLogger = new LoggingApi(s);
        }
        else {
            LoggingApi.systemLogger.ChangeLogFile(s);
        }
        return LoggingApi.systemLogger;
    }
    
    public static synchronized LoggingApi GetSystemLogger(final PrintStream printStream) {
        if (LoggingApi.systemLogger == null) {
            LoggingApi.systemLogger = new LoggingApi(printStream);
        }
        else {
            LoggingApi.systemLogger.ChangeLogPrintStream(printStream);
        }
        return LoggingApi.systemLogger;
    }
    
    public LoggingApi() {
        this.m_logFileName = null;
        this.m_logStream = null;
        this.typeIDLength = 3;
        this.typeEnabled = false;
        this.threadInfoEnabled = true;
        this.dateFormatter = DateFormat.getDateTimeInstance();
        this.enabledTypes = new Hashtable();
        this.m_logStream = System.out;
    }
    
    public LoggingApi(final String s) throws FileNotFoundException {
        this.m_logFileName = null;
        this.m_logStream = null;
        this.typeIDLength = 3;
        this.typeEnabled = false;
        this.threadInfoEnabled = true;
        this.dateFormatter = DateFormat.getDateTimeInstance();
        this.enabledTypes = new Hashtable();
        this.SetLogFile(s);
    }
    
    public LoggingApi(final PrintStream logStream) {
        this.m_logFileName = null;
        this.m_logStream = null;
        this.typeIDLength = 3;
        this.typeEnabled = false;
        this.threadInfoEnabled = true;
        this.dateFormatter = DateFormat.getDateTimeInstance();
        this.enabledTypes = new Hashtable();
        this.m_logStream = logStream;
    }
    
    public synchronized void Configure(final LogMessageType logMessageType, final boolean b) {
        if (!b && this.enabledTypes.contains(logMessageType)) {
            this.enabledTypes.remove(logMessageType);
        }
        if (b && !this.enabledTypes.contains(logMessageType)) {
            this.enabledTypes.put(logMessageType, logMessageType);
        }
    }
    
    public synchronized boolean IsLoggingEnabledForType(final LogMessageType logMessageType) {
        return this.enabledTypes.contains(logMessageType);
    }
    
    public synchronized void Write(final LogMessageType logMessageType, final Object o, final Object o2) {
        this.Write(logMessageType, o, o2, null, null, null, null);
    }
    
    public synchronized void Write(final LogMessageType logMessageType, final Object o, final Object o2, final Object o3) {
        this.Write(logMessageType, o, o2, o3, null, null, null);
    }
    
    public synchronized void Write(final LogMessageType logMessageType, final Object o, final Object o2, final Object o3, final Object o4) {
        this.Write(logMessageType, o, o2, o3, o4, null, null);
    }
    
    public synchronized void Write(final LogMessageType logMessageType, final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
        this.Write(logMessageType, o, o2, o3, o4, o5, null);
    }
    
    public synchronized void Write(final LogMessageType logMessageType, final Object o, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6) {
        if (!this.IsLoggingEnabledForType(logMessageType)) {
            return;
        }
        final StringBuffer sb = new StringBuffer("");
        this.GenerateMessagePrefix(logMessageType, sb, o);
        sb.append("MSG : ");
        if (o2 != null) {
            sb.append(o2.toString());
        }
        if (o3 != null) {
            sb.append(o3.toString());
        }
        if (o4 != null) {
            sb.append(o4.toString());
        }
        if (o5 != null) {
            sb.append(o5.toString());
        }
        if (o6 != null) {
            sb.append(o6.toString());
        }
        this.m_logStream.println(sb);
    }
    
    public synchronized void Write(final LogMessageType logMessageType, final Object o, final Exception ex) {
        if (!this.IsLoggingEnabledForType(logMessageType)) {
            return;
        }
        final StringBuffer sb = new StringBuffer("");
        this.GenerateMessagePrefix(logMessageType, sb, o);
        sb.append("EXCEPTION : ");
        this.m_logStream.println(sb);
        ex.printStackTrace(this.m_logStream);
    }
    
    private void GenerateMessagePrefix(final LogMessageType logMessageType, final StringBuffer sb, final Object o) {
        this.WriteLogMessageType(logMessageType, sb);
        this.WriteThreadInfo(sb);
        this.WriteDateTimeInfo(sb);
        if (o != null) {
            sb.append("CLASS : ");
            sb.append(o.getClass().getName());
            sb.append(" ");
        }
    }
    
    public synchronized void SetTypeIDLength(final int typeIDLength) {
        this.typeIDLength = typeIDLength;
    }
    
    public synchronized void EnableTypeID(final boolean typeEnabled) {
        this.typeEnabled = typeEnabled;
    }
    
    public synchronized void EnableThreadInfo(final boolean threadInfoEnabled) {
        this.threadInfoEnabled = threadInfoEnabled;
    }
    
    private synchronized void WriteLogMessageType(final LogMessageType logMessageType, final StringBuffer sb) {
        if (!this.typeEnabled) {
            return;
        }
        final StringBuffer sb2 = new StringBuffer();
        if (logMessageType.toString().length() >= this.typeIDLength) {
            sb2.append(logMessageType.toString().substring(0, this.typeIDLength).toUpperCase());
        }
        else {
            final int length = logMessageType.toString().length();
            sb2.append(logMessageType.toString().toUpperCase());
            for (int i = this.typeIDLength - length; i > 0; ++i) {
                sb2.append(" ");
            }
        }
        sb.append("[");
        sb.append((Object)sb2);
        sb.append("] ");
    }
    
    private void WriteThreadInfo(final StringBuffer sb) {
        if (!this.threadInfoEnabled) {
            return;
        }
        final Thread currentThread = Thread.currentThread();
        sb.append("THD : ");
        sb.append(currentThread.toString());
        sb.append(" ");
    }
    
    public synchronized void SetDateFormatter(final DateFormat dateFormatter) {
        this.dateFormatter = dateFormatter;
    }
    
    private synchronized void WriteDateTimeInfo(final StringBuffer sb) {
        final Date time = new GregorianCalendar().getTime();
        sb.append("TIME : ");
        sb.append(this.dateFormatter.format(time));
        sb.append(" ");
    }
    
    static {
        LoggingApi.systemLogger = null;
    }
}
