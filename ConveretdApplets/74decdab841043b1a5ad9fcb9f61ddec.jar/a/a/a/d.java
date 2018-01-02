// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.FieldPosition;
import java.util.logging.LogRecord;
import java.util.Date;
import java.text.MessageFormat;
import java.util.logging.Formatter;

final class d extends Formatter
{
    private MessageFormat a;
    private Date b;
    private Object[] c;
    private String d;
    
    d() {
        this.a = new MessageFormat("{0,date} {0,time}");
        this.b = new Date();
        this.c = new Object[] { this.b };
        this.d = System.getProperty("line.separator");
    }
    
    public final synchronized String format(final LogRecord logRecord) {
        final StringBuffer sb = new StringBuffer();
        this.b.setTime(logRecord.getMillis());
        final StringBuffer sb2 = new StringBuffer();
        this.a.format(this.c, sb2, null);
        sb.append(sb2);
        sb.append(" ");
        final String formatMessage = this.formatMessage(logRecord);
        sb.append(logRecord.getLevel().getLocalizedName());
        sb.append(": ");
        sb.append(formatMessage);
        sb.append(this.d);
        if (logRecord.getThrown() != null) {
            try {
                final StringWriter stringWriter = new StringWriter();
                final PrintWriter printWriter = new PrintWriter(stringWriter);
                logRecord.getThrown().printStackTrace(printWriter);
                printWriter.close();
                sb.append(stringWriter.toString());
            }
            catch (Exception ex) {}
        }
        return sb.toString();
    }
}
