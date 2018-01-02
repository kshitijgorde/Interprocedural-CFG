// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.log;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class FormatSink implements ILogSink
{
    private ILogSink E;
    private Calendar F;
    private SimpleDateFormat D;
    
    public FormatSink(final ILogSink e) {
        this.E = e;
        this.F = Calendar.getInstance();
        (this.D = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).setCalendar(this.F);
    }
    
    @Override
    public void log(final Log log, final int n, final Object o) {
        final StringBuilder sb = new StringBuilder();
        sb.append(Log.getLevelName(n));
        sb.append(' ');
        sb.append(log.getName());
        sb.append(' ');
        sb.append(this.D.format(new Date()));
        sb.append(' ');
        sb.append(o);
        this.E.log(log, n, sb.toString());
    }
    
    @Override
    public void log(final Log log, final int n, final Throwable t) {
        this.log(log, n, "", t);
    }
    
    @Override
    public void log(final Log log, final int n, final Object o, final Throwable t) {
        final String string = o.toString();
        final String levelName = Log.getLevelName(n);
        final String format = this.D.format(new Date());
        final StringBuilder sb = new StringBuilder();
        if (string.length() > 0) {
            sb.append(levelName);
            sb.append(' ');
            sb.append(log.getName());
            sb.append(' ');
            sb.append(format);
            sb.append(' ');
            sb.append(string);
            this.E.log(log, n, sb.toString());
        }
        sb.setLength(0);
        sb.append(levelName);
        sb.append(' ');
        sb.append(log.getName());
        sb.append(' ');
        sb.append(format);
        sb.append(' ');
        sb.append(t.getClass().getSimpleName());
        sb.append(": ");
        sb.append(t.getMessage());
        this.E.log(log, n, sb.toString());
        StackTraceElement[] stackTrace;
        for (int length = (stackTrace = t.getStackTrace()).length, i = 0; i < length; ++i) {
            final StackTraceElement stackTraceElement = stackTrace[i];
            sb.setLength(0);
            sb.append(levelName);
            sb.append(' ');
            sb.append(log.getName());
            sb.append(' ');
            sb.append(format);
            sb.append(' ');
            sb.append(stackTraceElement.toString());
            this.E.log(log, n, sb.toString());
        }
    }
}
