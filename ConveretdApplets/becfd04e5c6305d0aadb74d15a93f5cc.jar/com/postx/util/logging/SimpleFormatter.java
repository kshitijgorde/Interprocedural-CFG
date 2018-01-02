// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.util.logging;

import java.text.SimpleDateFormat;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.text.DateFormat;

public class SimpleFormatter extends Formatter
{
    public static final String Ident = "$Id: SimpleFormatter.java,v 1.2 2009/06/12 20:34:24 blm Exp $";
    private static final String eol;
    private static DateFormat dateFormatter;
    
    String format(final LogRecord logRecord) {
        final StringBuffer sb = new StringBuffer();
        final Throwable thrown = logRecord.getThrown();
        final Date date = new Date(logRecord.getMillis());
        final String format;
        synchronized (SimpleFormatter.dateFormatter) {
            format = SimpleFormatter.dateFormatter.format(date);
        }
        // monitorexit(SimpleFormatter.dateFormatter)
        appendLines(sb, format, logRecord.getMessage());
        if (thrown != null) {
            final StringWriter stringWriter = new StringWriter();
            thrown.printStackTrace(new PrintWriter(stringWriter));
            appendLines(sb, format, stringWriter.toString());
        }
        return sb.toString();
    }
    
    private static void appendLines(final StringBuffer sb, final String s, final String s2) {
        int n = 1;
        for (int length = s2.length(), i = 0; i < length; ++i) {
            if (n != 0) {
                sb.append(s);
                n = 0;
            }
            final char char1;
            if ((char1 = s2.charAt(i)) == '\r' || char1 == '\n') {
                sb.append("\n");
                n = 1;
                if (char1 == '\r' && i < length - 1 && s2.charAt(i + 1) == '\n') {
                    ++i;
                }
            }
            else if ((char1 != '\t' && char1 < ' ') || ('~' < char1 && char1 < '\u0100')) {
                sb.append("\\x");
                if (char1 < '\u0010') {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(char1));
            }
            else if ('\u0100' <= char1) {
                sb.append("\\u");
                if (char1 < '\u1000') {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(char1));
            }
            else {
                sb.append(char1);
            }
        }
        if (n == 0) {
            sb.append("\n");
        }
    }
    
    static {
        eol = System.getProperty("line.separator");
        SimpleFormatter.dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS: ");
    }
}
