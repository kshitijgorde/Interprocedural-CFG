// 
// Decompiled by Procyon v0.5.30
// 

package com.aviva.framework.util;

import java.io.PrintWriter;
import java.io.PrintStream;

public class GenericException extends Exception
{
    private static final long serialVersionUID = 1L;
    private Throwable rootCause;
    private String errorCode;
    private Object[] parameterValues;
    
    protected GenericException(final String msg) {
        super(msg);
        this.rootCause = null;
        this.errorCode = null;
        this.parameterValues = null;
    }
    
    protected GenericException(final Throwable rootCause) {
        super(rootCause.getMessage());
        this.rootCause = null;
        this.errorCode = null;
        this.parameterValues = null;
        this.rootCause = rootCause;
    }
    
    public GenericException() {
        this.rootCause = null;
        this.errorCode = null;
        this.parameterValues = null;
    }
    
    public GenericException(final String msg, final Throwable rootCause) {
        this(msg, null, rootCause, null);
    }
    
    public GenericException(final String msg, final String errorCode) {
        this(msg, errorCode, null, null);
    }
    
    public GenericException(final String msg, final String errorCode, final Object[] parameterValues) {
        this(msg, errorCode, null, parameterValues);
    }
    
    public GenericException(final String msg, final String errorCode, final Throwable rootCause) {
        this(msg, errorCode, rootCause, null);
    }
    
    public GenericException(final String msg, final String errorCode, final Throwable rootCause, final Object[] paramValues) {
        super(msg);
        this.rootCause = null;
        this.errorCode = null;
        this.parameterValues = null;
        this.rootCause = rootCause;
        this.errorCode = errorCode;
        this.parameterValues = paramValues;
    }
    
    public void printStackTrace() {
        super.printStackTrace();
        if (this.rootCause != null) {
            this.rootCause.printStackTrace();
        }
    }
    
    public void printStackTrace(final PrintStream s) {
        super.printStackTrace(s);
        if (this.rootCause != null) {
            this.rootCause.printStackTrace(s);
        }
    }
    
    public void printStackTrace(final PrintWriter s) {
        super.printStackTrace(s);
        if (this.rootCause != null) {
            this.rootCause.printStackTrace(s);
        }
    }
    
    public String getErrorCode() {
        return this.errorCode;
    }
    
    public Throwable getRootCause() {
        return this.rootCause;
    }
    
    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }
    
    public void setRootCause(final Throwable rootCause) {
        this.rootCause = rootCause;
    }
    
    public Object[] getParameterValues() {
        return this.parameterValues;
    }
    
    public void setParameterValues(final Object[] parameterValues) {
        this.parameterValues = parameterValues;
    }
}
