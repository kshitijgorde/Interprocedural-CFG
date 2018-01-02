// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import java.io.PrintWriter;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.io.IOException;

public class HttpException extends IOException
{
    private final Throwable cause;
    static /* synthetic */ Class class$0;
    
    public HttpException() {
        this.cause = null;
    }
    
    public HttpException(final String message) {
        super(message);
        this.cause = null;
    }
    
    public HttpException(final String message, final Throwable cause) {
        super(message);
        this.cause = cause;
        try {
            final Class[] array = { null };
            final int n = 0;
            Class class$0;
            if ((class$0 = HttpException.class$0) == null) {
                try {
                    class$0 = (HttpException.class$0 = Class.forName("[Ljava.lang.Throwable;").getComponentType());
                }
                catch (ClassNotFoundException ex) {
                    throw new NoClassDefFoundError(ex.getMessage());
                }
            }
            array[n] = class$0;
            final Class[] paramsClasses = array;
            Class class$2;
            if ((class$2 = HttpException.class$0) == null) {
                try {
                    class$2 = (HttpException.class$0 = Class.forName("[Ljava.lang.Throwable;").getComponentType());
                }
                catch (ClassNotFoundException ex2) {
                    throw new NoClassDefFoundError(ex2.getMessage());
                }
            }
            final Method initCause = class$2.getMethod("initCause", (Class[])paramsClasses);
            initCause.invoke(this, cause);
        }
        catch (Exception ex3) {}
    }
    
    public Throwable getCause() {
        return this.cause;
    }
    
    public void printStackTrace() {
        this.printStackTrace(System.err);
    }
    
    public void printStackTrace(final PrintStream s) {
        try {
            final Class[] paramsClasses = new Class[0];
            this.getClass().getMethod("getStackTrace", (Class<?>[])paramsClasses);
            super.printStackTrace(s);
        }
        catch (Exception ex) {
            super.printStackTrace(s);
            if (this.cause != null) {
                s.print("Caused by: ");
                this.cause.printStackTrace(s);
            }
        }
    }
    
    public void printStackTrace(final PrintWriter s) {
        try {
            final Class[] paramsClasses = new Class[0];
            this.getClass().getMethod("getStackTrace", (Class<?>[])paramsClasses);
            super.printStackTrace(s);
        }
        catch (Exception ex) {
            super.printStackTrace(s);
            if (this.cause != null) {
                s.print("Caused by: ");
                this.cause.printStackTrace(s);
            }
        }
    }
}
