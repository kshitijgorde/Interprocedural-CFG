// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.FieldPosition;
import java.util.logging.LogRecord;
import java.security.PrivilegedAction;
import java.security.AccessController;
import sun.security.action.GetPropertyAction;
import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Formatter;

public class LogFormatter extends Formatter
{
    Date dat;
    private static final String format = "{0,date,short} {0,time}";
    private MessageFormat formatter;
    private Object[] args;
    private String lineSeparator;
    
    public LogFormatter() {
        this.dat = new Date();
        this.args = new Object[1];
        this.lineSeparator = AccessController.doPrivileged((PrivilegedAction<String>)new GetPropertyAction("line.separator"));
    }
    
    public synchronized String format(final LogRecord logRecord) {
        final StringBuffer sb = new StringBuffer();
        this.dat.setTime(logRecord.getMillis());
        final StringBuffer sb2 = new StringBuffer();
        if (this.formatter == null) {
            this.formatter = new MessageFormat("{0,date,short} {0,time}");
        }
        this.args[0] = this.dat;
        this.formatter.format(this.args, sb2, null);
        sb.append(sb2);
        sb.append(" ");
        final String localizedName = logRecord.getLevel().getLocalizedName();
        sb.append(localizedName);
        if (localizedName.length() <= 4) {
            sb.append("  ");
        }
        else {
            sb.append(" ");
        }
        if (logRecord.getSourceClassName() != null) {
            sb.append(logRecord.getSourceClassName());
        }
        else {
            sb.append(logRecord.getLoggerName());
        }
        final String formatMessage = this.formatMessage(logRecord);
        if (logRecord.getSourceMethodName() != null && (formatMessage.equals("ENTRY") || formatMessage.equals("RETURN"))) {
            sb.append(".");
            sb.append(logRecord.getSourceMethodName());
        }
        sb.append(" - ");
        sb.append(formatMessage);
        sb.append(this.lineSeparator);
        if (logRecord.getThrown() != null) {
            try {
                final StringWriter stringWriter = new StringWriter();
                final PrintWriter printWriter = new PrintWriter(stringWriter);
                logRecord.getThrown().printStackTrace(printWriter);
                printWriter.close();
                sb.append(stringWriter.toString());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return sb.toString();
    }
}
