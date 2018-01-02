// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging.util;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.log4j.Priority;
import org.apache.log4j.Category;
import java.io.PrintStream;

public class CategoryStream extends PrintStream
{
    public static final boolean TRACE;
    private Category category;
    private Priority priority;
    private boolean issuedWarning;
    private ThreadLocal recursiveCheck;
    static /* synthetic */ Class class$org$jboss$logging$util$CategoryStream;
    
    private static boolean getBoolean(final String name, final boolean defaultValue) {
        final String value = System.getProperty(name, null);
        if (value == null) {
            return defaultValue;
        }
        return new Boolean(value);
    }
    
    public CategoryStream(final Category category) {
        this(category, Priority.INFO, System.out);
    }
    
    public CategoryStream(final Category category, final Priority priority, final PrintStream ps) {
        super(ps);
        this.recursiveCheck = new ThreadLocal();
        this.category = category;
        this.priority = priority;
    }
    
    public void println(String msg) {
        if (msg == null) {
            msg = "null";
        }
        final byte[] bytes = msg.getBytes();
        this.write(bytes, 0, bytes.length);
    }
    
    public void println(Object msg) {
        if (msg == null) {
            msg = "null";
        }
        final byte[] bytes = msg.toString().getBytes();
        this.write(bytes, 0, bytes.length);
    }
    
    public void write(final byte b) {
        final byte[] bytes = { b };
        this.write(bytes, 0, 1);
    }
    
    public void write(final byte[] b, final int off, int len) {
        final Boolean recursed = this.recursiveCheck.get();
        if (recursed != null && recursed.equals(Boolean.TRUE)) {
            if (!this.issuedWarning) {
                final String msg = "ERROR: invalid console appender config detected, console stream is looping";
                try {
                    this.out.write(msg.getBytes());
                }
                catch (IOException ex) {}
                this.issuedWarning = true;
            }
            return;
        }
        while (len > 0 && (b[len - 1] == 10 || b[len - 1] == 13) && len > off) {
            --len;
        }
        if (len != 0) {
            final String msg = new String(b, off, len);
            this.recursiveCheck.set(Boolean.TRUE);
            if (CategoryStream.TRACE) {
                this.category.log(this.priority, (Object)msg, new Throwable());
            }
            else {
                this.category.log(this.priority, (Object)msg);
            }
            this.recursiveCheck.set(Boolean.FALSE);
        }
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError().initCause(x);
        }
    }
    
    static {
        TRACE = getBoolean(((CategoryStream.class$org$jboss$logging$util$CategoryStream == null) ? (CategoryStream.class$org$jboss$logging$util$CategoryStream = class$("org.jboss.logging.util.CategoryStream")) : CategoryStream.class$org$jboss$logging$util$CategoryStream).getName() + ".trace", false);
    }
}
