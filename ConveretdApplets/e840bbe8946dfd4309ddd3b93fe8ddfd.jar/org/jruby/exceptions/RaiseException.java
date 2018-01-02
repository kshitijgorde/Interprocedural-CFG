// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.exceptions;

import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.jruby.runtime.backtrace.RubyStackTraceElement;
import org.jruby.runtime.RubyEvent;
import org.jruby.runtime.ThreadContext;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Member;
import org.jruby.NativeException;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyString;
import org.jruby.RubyClass;
import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyException;

public class RaiseException extends JumpException
{
    public static final boolean DEBUG = false;
    private static final long serialVersionUID = -7612079169559973951L;
    private RubyException exception;
    private String providedMessage;
    private boolean nativeException;
    private StackTraceElement[] cachedTrace;
    
    public RaiseException(final RubyException actException) {
        this(actException, false);
    }
    
    public RaiseException(final RubyException exception, final IRubyObject backtrace) {
        this.setException(exception, false);
        this.preRaise(exception.getRuntime().getCurrentContext(), backtrace);
    }
    
    public RaiseException(final Ruby runtime, final RubyClass excptnClass, String msg, final boolean nativeException) {
        super(msg);
        if (msg == null) {
            msg = "No message available";
        }
        this.providedMessage = "(" + excptnClass.getName() + ") " + msg;
        this.nativeException = nativeException;
        this.setException((RubyException)RuntimeHelpers.invoke(runtime.getCurrentContext(), excptnClass, "new", RubyString.newUnicodeString(excptnClass.getRuntime(), msg)), nativeException);
        this.preRaise(runtime.getCurrentContext());
    }
    
    public RaiseException(final Ruby runtime, final RubyClass excptnClass, String msg, final IRubyObject backtrace, final boolean nativeException) {
        super(msg);
        if (msg == null) {
            msg = "No message available";
        }
        this.providedMessage = "(" + excptnClass.getName() + ") " + msg;
        this.nativeException = nativeException;
        this.setException((RubyException)RuntimeHelpers.invoke(runtime.getCurrentContext(), excptnClass, "new", RubyString.newUnicodeString(excptnClass.getRuntime(), msg)), nativeException);
        this.preRaise(runtime.getCurrentContext(), backtrace);
    }
    
    public RaiseException(final RubyException exception, final boolean isNativeException) {
        this.setException(exception, this.nativeException = isNativeException);
        this.preRaise(exception.getRuntime().getCurrentContext());
    }
    
    public RaiseException(final Throwable cause, final NativeException nativeException) {
        super(buildMessage(cause), cause);
        this.providedMessage = buildMessage(cause);
        this.setException(nativeException, true);
        this.preRaise(nativeException.getRuntime().getCurrentContext());
    }
    
    public static RaiseException createNativeRaiseException(final Ruby runtime, final Throwable cause) {
        return createNativeRaiseException(runtime, cause, null);
    }
    
    public static RaiseException createNativeRaiseException(final Ruby runtime, final Throwable cause, final Member target) {
        final NativeException nativeException = new NativeException(runtime, runtime.getClass("NativeException"), cause);
        if (!runtime.getDebug().isTrue()) {
            nativeException.trimStackTrace(target);
        }
        return new RaiseException(cause, nativeException);
    }
    
    private static String buildMessage(final Throwable exception) {
        final StringBuilder sb = new StringBuilder();
        final StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        sb.append("Native Exception: '").append(exception.getClass()).append("'; ");
        sb.append("Message: ").append(exception.getMessage()).append("; ");
        sb.append("StackTrace: ").append(stackTrace.getBuffer().toString());
        return sb.toString();
    }
    
    public String getMessage() {
        if (this.providedMessage == null) {
            this.providedMessage = "(" + this.exception.getMetaClass().getBaseName() + ") " + this.exception.message(this.exception.getRuntime().getCurrentContext()).asJavaString();
        }
        return this.providedMessage;
    }
    
    public RubyException getException() {
        return this.exception;
    }
    
    private void preRaise(final ThreadContext context) {
        this.preRaise(context, null);
    }
    
    private void preRaise(final ThreadContext context, final IRubyObject backtrace) {
        context.runtime.incrementExceptionCount();
        this.doSetLastError(context);
        this.doCallEventHook(context);
        if (backtrace == null) {
            context.runtime.incrementBacktraceCount();
            this.exception.prepareBacktrace(context, this.nativeException);
        }
        else {
            this.exception.forceBacktrace(backtrace);
        }
    }
    
    private void doCallEventHook(final ThreadContext context) {
        if (context.runtime.hasEventHooks()) {
            context.runtime.callEventHooks(context, RubyEvent.RAISE, context.getFile(), context.getLine(), context.getFrameName(), context.getFrameKlazz());
        }
    }
    
    private void doSetLastError(final ThreadContext context) {
        if (!context.isWithinDefined()) {
            context.runtime.getGlobalVariables().set("$!", this.exception);
        }
    }
    
    public StackTraceElement[] getStackTrace() {
        if (this.cachedTrace == null) {
            if (this.exception instanceof NativeException) {
                this.setStackTrace(this.cachedTrace = ((NativeException)this.exception).getCause().getStackTrace());
            }
            else {
                this.setStackTrace(this.cachedTrace = this.javaTraceFromRubyTrace(this.exception.getBacktraceElements()));
            }
        }
        return this.cachedTrace;
    }
    
    protected void setException(final RubyException newException, final boolean nativeException) {
        this.exception = newException;
    }
    
    private StackTraceElement[] javaTraceFromRubyTrace(final RubyStackTraceElement[] trace) {
        final StackTraceElement[] newTrace = new StackTraceElement[trace.length];
        for (int i = 0; i < newTrace.length; ++i) {
            newTrace[i] = trace[i].getElement();
        }
        return newTrace;
    }
    
    public void printStackTrace() {
        this.printStackTrace(System.err);
    }
    
    public void printStackTrace(final PrintStream ps) {
        this.getStackTrace();
        super.printStackTrace(ps);
    }
    
    public void printStackTrace(final PrintWriter pw) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.printStackTrace(new PrintStream(baos));
        pw.print(baos.toString());
    }
}
