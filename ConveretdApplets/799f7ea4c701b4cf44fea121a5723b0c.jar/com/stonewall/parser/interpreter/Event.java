// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter;

import org.xmodel.log.Log;

public class Event
{
    public final Interpreter interpreter;
    public final Severity severity;
    public final String message;
    public final Throwable exception;
    
    public Event(final Interpreter intr, final Severity severity) {
        this(intr, severity, null, null);
    }
    
    public Event(final Interpreter intr, final Severity severity, final String msg) {
        this(intr, severity, msg, null);
    }
    
    public Event(final Interpreter intr, final Severity severity, final String msg, final Throwable e) {
        this.interpreter = intr;
        this.severity = severity;
        this.message = msg;
        this.exception = e;
    }
    
    public void log(final Log log) {
        switch (this.severity) {
            case info: {
                log.info(this.message);
                break;
            }
            case warning: {
                log.warn(this.message);
                break;
            }
            case error: {
                if (this.exception == null) {
                    log.error(this.message);
                    break;
                }
                log.error(this.message, this.exception);
                break;
            }
        }
    }
    
    public enum Severity
    {
        info("info", 0), 
        warning("warning", 1), 
        error("error", 2);
        
        private Severity(final String s, final int n) {
        }
    }
}
