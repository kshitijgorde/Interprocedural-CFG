// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.io.PrintStream;
import java.lang.reflect.Member;
import org.jruby.anno.JRubyMethod;
import org.jruby.javasupport.Java;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.Block;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "NativeException" }, parent = "RuntimeError")
public class NativeException extends RubyException
{
    private final Throwable cause;
    public static final String CLASS_NAME = "NativeException";
    private final Ruby runtime;
    
    public NativeException(final Ruby runtime, final RubyClass rubyClass, final Throwable cause) {
        super(runtime, rubyClass);
        this.runtime = runtime;
        this.cause = cause;
        this.message = runtime.newString(cause.getClass().getName() + ": " + this.searchStackMessage(cause));
    }
    
    public static RubyClass createClass(final Ruby runtime, final RubyClass baseClass) {
        final RubyClass exceptionClass = runtime.defineClass("NativeException", baseClass, ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        exceptionClass.defineAnnotatedMethods(NativeException.class);
        return exceptionClass;
    }
    
    @JRubyMethod
    public IRubyObject cause(final Block unusedBlock) {
        return Java.getInstance(this.getRuntime(), this.cause);
    }
    
    public IRubyObject backtrace() {
        final IRubyObject rubyTrace = super.backtrace();
        if (rubyTrace.isNil()) {
            return rubyTrace;
        }
        final RubyArray array = (RubyArray)rubyTrace.dup();
        final StackTraceElement[] stackTrace = this.cause.getStackTrace();
        for (int i = stackTrace.length - 1; i >= 0; --i) {
            final StackTraceElement element = stackTrace[i];
            final String className = element.getClassName();
            String line = null;
            if (element.getFileName() == null) {
                line = className + ":" + element.getLineNumber() + ":in `" + element.getMethodName() + "'";
            }
            else {
                final int index = className.lastIndexOf(".");
                String packageName = null;
                if (index == -1) {
                    packageName = "";
                }
                else {
                    packageName = className.substring(0, index) + "/";
                }
                line = packageName.replace(".", "/") + element.getFileName() + ":" + element.getLineNumber() + ":in `" + element.getMethodName() + "'";
            }
            final RubyString string = this.runtime.newString(line);
            array.unshift(string);
        }
        return array;
    }
    
    public void trimStackTrace(final Member target) {
        final Throwable t = new Throwable();
        final StackTraceElement[] origStackTrace = this.cause.getStackTrace();
        final StackTraceElement[] currentStackTrace = t.getStackTrace();
        int skip = 0;
        for (int i = 1; i <= origStackTrace.length && i <= currentStackTrace.length; ++i) {
            final StackTraceElement a = origStackTrace[origStackTrace.length - i];
            final StackTraceElement b = currentStackTrace[currentStackTrace.length - i];
            if (!a.equals(b)) {
                break;
            }
            ++skip;
        }
        if (target != null) {
            final String className = target.getDeclaringClass().getName();
            final String methodName = target.getName();
            for (int j = origStackTrace.length - skip - 1; j >= 0; --j) {
                final StackTraceElement frame = origStackTrace[j];
                if (frame.getClassName().equals(className) && frame.getMethodName().equals(methodName)) {
                    skip = origStackTrace.length - j - 1;
                    break;
                }
            }
        }
        if (skip > 0) {
            final StackTraceElement[] newStackTrace = new StackTraceElement[origStackTrace.length - skip];
            for (int k = 0; k < newStackTrace.length; ++k) {
                newStackTrace[k] = origStackTrace[k];
            }
            this.cause.setStackTrace(newStackTrace);
        }
    }
    
    public void printBacktrace(final PrintStream errorStream) {
        super.printBacktrace(errorStream);
        if (this.getRuntime().getDebug().isTrue()) {
            errorStream.println("Complete Java stackTrace");
            this.cause.printStackTrace(errorStream);
        }
    }
    
    public Throwable getCause() {
        return this.cause;
    }
    
    private String searchStackMessage(Throwable cause) {
        String message = null;
        do {
            message = cause.getMessage();
            cause = cause.getCause();
        } while (message == null && cause != null);
        return message;
    }
}
