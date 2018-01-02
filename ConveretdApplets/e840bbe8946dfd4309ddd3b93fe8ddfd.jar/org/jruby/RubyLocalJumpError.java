// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "LocalJumpError" }, parent = "StandardError")
public class RubyLocalJumpError extends RubyException
{
    private static ObjectAllocator LOCALJUMPERROR_ALLOCATOR;
    private Reason reason;
    
    public static RubyClass createLocalJumpErrorClass(final Ruby runtime, final RubyClass standardErrorClass) {
        final RubyClass nameErrorClass = runtime.defineClass("LocalJumpError", standardErrorClass, RubyLocalJumpError.LOCALJUMPERROR_ALLOCATOR);
        nameErrorClass.defineAnnotatedMethods(RubyLocalJumpError.class);
        return nameErrorClass;
    }
    
    private RubyLocalJumpError(final Ruby runtime, final RubyClass exceptionClass) {
        super(runtime, exceptionClass);
    }
    
    public RubyLocalJumpError(final Ruby runtime, final RubyClass exceptionClass, final String message, final Reason reason, final IRubyObject exitValue) {
        super(runtime, exceptionClass, message);
        this.reason = reason;
        this.fastSetInternalVariable("reason", runtime.newSymbol(reason.toString()));
        this.fastSetInternalVariable("exit_value", exitValue);
    }
    
    @JRubyMethod(name = { "reason" })
    public IRubyObject reason() {
        return (IRubyObject)this.fastGetInternalVariable("reason");
    }
    
    public Reason getReason() {
        return this.reason;
    }
    
    @JRubyMethod(name = { "exit_value" })
    public IRubyObject exit_value() {
        return (IRubyObject)this.fastGetInternalVariable("exit_value");
    }
    
    static {
        RubyLocalJumpError.LOCALJUMPERROR_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyLocalJumpError(runtime, klass, (RubyLocalJumpError$1)null);
            }
        };
    }
    
    public enum Reason
    {
        REDO, 
        BREAK, 
        NEXT, 
        RETURN, 
        RETRY, 
        NOREASON;
        
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
