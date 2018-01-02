// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import org.apache.xml.res.XMLMessages;
import javax.xml.transform.SourceLocator;

public class DTMException extends RuntimeException
{
    SourceLocator locator;
    Throwable containedException;
    
    public SourceLocator getLocator() {
        return this.locator;
    }
    
    public void setLocator(final SourceLocator location) {
        this.locator = location;
    }
    
    public Throwable getException() {
        return this.containedException;
    }
    
    public Throwable getCause() {
        return (this.containedException == this) ? null : this.containedException;
    }
    
    public synchronized Throwable initCause(final Throwable cause) {
        if (this.containedException == null && cause != null) {
            throw new IllegalStateException(XMLMessages.createXMLMessage("ER_CANNOT_OVERWRITE_CAUSE", null));
        }
        if (cause == this) {
            throw new IllegalArgumentException(XMLMessages.createXMLMessage("ER_SELF_CAUSATION_NOT_PERMITTED", null));
        }
        this.containedException = cause;
        return this;
    }
    
    public DTMException(final String message) {
        super(message);
        this.containedException = null;
        this.locator = null;
    }
    
    public DTMException(final Throwable e) {
        super(e.getMessage());
        this.containedException = e;
        this.locator = null;
    }
    
    public DTMException(final String message, final Throwable e) {
        super((message == null || message.length() == 0) ? e.getMessage() : message);
        this.containedException = e;
        this.locator = null;
    }
    
    public DTMException(final String message, final SourceLocator locator) {
        super(message);
        this.containedException = null;
        this.locator = locator;
    }
    
    public DTMException(final String message, final SourceLocator locator, final Throwable e) {
        super(message);
        this.containedException = e;
        this.locator = locator;
    }
    
    public String getMessageAndLocation() {
        final StringBuffer sbuffer = new StringBuffer();
        final String message = super.getMessage();
        if (null != message) {
            sbuffer.append(message);
        }
        if (null != this.locator) {
            final String systemID = this.locator.getSystemId();
            final int line = this.locator.getLineNumber();
            final int column = this.locator.getColumnNumber();
            if (null != systemID) {
                sbuffer.append("; SystemID: ");
                sbuffer.append(systemID);
            }
            if (0 != line) {
                sbuffer.append("; Line#: ");
                sbuffer.append(line);
            }
            if (0 != column) {
                sbuffer.append("; Column#: ");
                sbuffer.append(column);
            }
        }
        return sbuffer.toString();
    }
    
    public String getLocationAsString() {
        if (null != this.locator) {
            final StringBuffer sbuffer = new StringBuffer();
            final String systemID = this.locator.getSystemId();
            final int line = this.locator.getLineNumber();
            final int column = this.locator.getColumnNumber();
            if (null != systemID) {
                sbuffer.append("; SystemID: ");
                sbuffer.append(systemID);
            }
            if (0 != line) {
                sbuffer.append("; Line#: ");
                sbuffer.append(line);
            }
            if (0 != column) {
                sbuffer.append("; Column#: ");
                sbuffer.append(column);
            }
            return sbuffer.toString();
        }
        return null;
    }
    
    public void printStackTrace() {
        this.printStackTrace(new PrintWriter(System.err, true));
    }
    
    public void printStackTrace(final PrintStream s) {
        this.printStackTrace(new PrintWriter(s));
    }
    
    public void printStackTrace(PrintWriter s) {
        if (s == null) {
            s = new PrintWriter(System.err, true);
        }
        try {
            final String locInfo = this.getLocationAsString();
            if (null != locInfo) {
                s.println(locInfo);
            }
            super.printStackTrace(s);
        }
        catch (Throwable t) {}
        Throwable exception = this.getException();
        for (int i = 0; i < 10 && null != exception; ++i) {
            s.println("---------");
            try {
                if (exception instanceof DTMException) {
                    final String locInfo2 = ((DTMException)exception).getLocationAsString();
                    if (null != locInfo2) {
                        s.println(locInfo2);
                    }
                }
                exception.printStackTrace(s);
            }
            catch (Throwable e) {
                s.println("Could not print stack trace...");
            }
            try {
                final Method meth = exception.getClass().getMethod("getException", (Class<?>[])null);
                if (null != meth) {
                    final Throwable prev = exception;
                    exception = (Throwable)meth.invoke(exception, (Object[])null);
                    if (prev == exception) {
                        break;
                    }
                }
                else {
                    exception = null;
                }
            }
            catch (InvocationTargetException ite) {
                exception = null;
            }
            catch (IllegalAccessException iae) {
                exception = null;
            }
            catch (NoSuchMethodException nsme) {
                exception = null;
            }
        }
    }
}
