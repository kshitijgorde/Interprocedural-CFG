// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.marshal.UnmarshalStream;
import java.util.List;
import org.jruby.runtime.builtin.Variable;
import org.jruby.runtime.component.VariableEntry;
import org.jruby.runtime.marshal.MarshalStream;
import java.io.IOException;
import java.io.PrintStream;
import org.jruby.exceptions.JumpException;
import org.jruby.java.proxies.ConcreteJavaProxy;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.backtrace.TraceType;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.backtrace.RubyStackTraceElement;
import org.jruby.runtime.ObjectMarshal;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.backtrace.BacktraceData;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Exception" })
public class RubyException extends RubyObject
{
    private BacktraceData backtraceData;
    private IRubyObject backtrace;
    public IRubyObject message;
    public static final int TRACE_HEAD = 8;
    public static final int TRACE_TAIL = 4;
    public static final int TRACE_MAX = 18;
    public static ObjectAllocator EXCEPTION_ALLOCATOR;
    private static final ObjectMarshal EXCEPTION_MARSHAL;
    
    protected RubyException(final Ruby runtime, final RubyClass rubyClass) {
        this(runtime, rubyClass, null);
    }
    
    public RubyException(final Ruby runtime, final RubyClass rubyClass, final String message) {
        super(runtime, rubyClass);
        this.message = ((message == null) ? runtime.getNil() : runtime.newString(message));
    }
    
    public static RubyClass createExceptionClass(final Ruby runtime) {
        final RubyClass exceptionClass = runtime.defineClass("Exception", runtime.getObject(), RubyException.EXCEPTION_ALLOCATOR);
        runtime.setException(exceptionClass);
        exceptionClass.index = 30;
        exceptionClass.setReifiedClass(RubyException.class);
        exceptionClass.setMarshal(RubyException.EXCEPTION_MARSHAL);
        exceptionClass.defineAnnotatedMethods(RubyException.class);
        return exceptionClass;
    }
    
    public static RubyException newException(final Ruby runtime, final RubyClass excptnClass, final String msg) {
        return new RubyException(runtime, excptnClass, msg);
    }
    
    public void setBacktraceData(final BacktraceData backtraceData) {
        this.backtraceData = backtraceData;
    }
    
    public BacktraceData getBacktraceData() {
        return this.backtraceData;
    }
    
    public RubyStackTraceElement[] getBacktraceElements() {
        if (this.backtraceData == null) {
            return RubyStackTraceElement.EMPTY_ARRAY;
        }
        return this.backtraceData.getBacktrace(this.getRuntime());
    }
    
    public void prepareBacktrace(final ThreadContext context, final boolean nativeException) {
        if (this.backtraceData == null) {
            this.backtraceData = context.runtime.getInstanceConfig().getTraceType().getBacktrace(context, nativeException);
        }
    }
    
    public void forceBacktrace(final IRubyObject backtrace) {
        this.backtraceData = BacktraceData.EMPTY;
        this.set_backtrace(backtrace);
    }
    
    public IRubyObject getBacktrace() {
        if (this.backtrace == null) {
            this.initBacktrace();
        }
        return this.backtrace;
    }
    
    public void initBacktrace() {
        final Ruby runtime = this.getRuntime();
        if (this.backtraceData == null) {
            this.backtrace = runtime.getNil();
        }
        else {
            this.backtrace = TraceType.generateMRIBacktrace(runtime, this.backtraceData.getBacktrace(runtime));
        }
    }
    
    @JRubyMethod(optional = 2, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final IRubyObject[] args, final Block block) {
        if (args.length == 1) {
            this.message = args[0];
        }
        return this;
    }
    
    @JRubyMethod
    public IRubyObject backtrace() {
        final IRubyObject bt = this.getBacktrace();
        return bt;
    }
    
    @JRubyMethod(required = 1)
    public IRubyObject set_backtrace(final IRubyObject obj) {
        if (obj.isNil()) {
            this.backtrace = null;
        }
        else {
            if (!this.isArrayOfStrings(obj)) {
                throw this.getRuntime().newTypeError("backtrace must be Array of String");
            }
            this.backtrace = obj;
        }
        return this.backtrace();
    }
    
    @JRubyMethod(name = { "exception" }, optional = 1, rest = true, meta = true)
    public static IRubyObject exception(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        return ((RubyClass)recv).newInstance(context, args, block);
    }
    
    @JRubyMethod(optional = 1)
    public RubyException exception(final IRubyObject[] args) {
        switch (args.length) {
            case 0: {
                return this;
            }
            case 1: {
                if (args[0] == this) {
                    return this;
                }
                final RubyException ret = (RubyException)this.rbClone();
                ret.initialize(args, Block.NULL_BLOCK);
                return ret;
            }
            default: {
                throw this.getRuntime().newArgumentError("Wrong argument count");
            }
        }
    }
    
    @JRubyMethod(name = { "to_s" })
    public IRubyObject to_s(final ThreadContext context) {
        if (this.message.isNil()) {
            return context.getRuntime().newString(this.getMetaClass().getRealClass().getName());
        }
        this.message.setTaint(this.isTaint());
        return this.message;
    }
    
    @JRubyMethod(name = { "to_str" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject to_str(final ThreadContext context) {
        return this.callMethod(context, "to_s");
    }
    
    @JRubyMethod(name = { "message" })
    public IRubyObject message(final ThreadContext context) {
        return this.callMethod(context, "to_s");
    }
    
    @JRubyMethod(name = { "inspect" })
    public IRubyObject inspect(final ThreadContext context) {
        final RubyModule rubyClass = this.getMetaClass();
        final RubyString exception = RubyString.objAsString(context, this);
        if (exception.getByteList().getRealSize() == 0) {
            return this.getRuntime().newString(rubyClass.getName());
        }
        final StringBuilder sb = new StringBuilder("#<");
        sb.append(rubyClass.getName()).append(": ").append(exception.getByteList()).append(">");
        return this.getRuntime().newString(sb.toString());
    }
    
    @JRubyMethod(name = { "==" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_equal(final ThreadContext context, final IRubyObject other) {
        final boolean equal = context.getRuntime().getException().isInstance(other) && this.callMethod(context, "message").equals(other.callMethod(context, "message")) && this.callMethod(context, "backtrace").equals(other.callMethod(context, "backtrace"));
        return context.getRuntime().newBoolean(equal);
    }
    
    @JRubyMethod(name = { "===" }, meta = true)
    public static IRubyObject op_eqq(final ThreadContext context, final IRubyObject recv, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (recv == runtime.getException() && other instanceof ConcreteJavaProxy) {
            final Object object = ((ConcreteJavaProxy)other).getObject();
            if (object instanceof Throwable && !(object instanceof JumpException.FlowControlException)) {
                return context.getRuntime().getTrue();
            }
        }
        return ((RubyClass)recv).op_eqq(context, other);
    }
    
    public void copySpecialInstanceVariables(final IRubyObject clone) {
        final RubyException exception = (RubyException)clone;
        exception.backtraceData = this.backtraceData;
        exception.backtrace = this.backtrace;
        exception.message = this.message;
    }
    
    public void printBacktrace(final PrintStream errorStream) {
        final IRubyObject backtrace = this.callMethod(this.getRuntime().getCurrentContext(), "backtrace");
        if (!backtrace.isNil() && backtrace instanceof RubyArray) {
            final IRubyObject[] elements = backtrace.convertToArray().toJavaArray();
            for (int i = 1; i < elements.length; ++i) {
                final IRubyObject stackTraceLine = elements[i];
                if (stackTraceLine instanceof RubyString) {
                    this.printStackTraceLine(errorStream, stackTraceLine);
                }
            }
        }
    }
    
    private void printStackTraceLine(final PrintStream errorStream, final IRubyObject stackTraceLine) {
        errorStream.print("\tfrom " + stackTraceLine + '\n');
    }
    
    private boolean isArrayOfStrings(final IRubyObject backtrace) {
        if (!(backtrace instanceof RubyArray)) {
            return false;
        }
        final IRubyObject[] elements = ((RubyArray)backtrace).toJavaArray();
        for (int i = 0; i < elements.length; ++i) {
            if (!(elements[i] instanceof RubyString)) {
                return false;
            }
        }
        return true;
    }
    
    public static IRubyObject newException(final ThreadContext context, final RubyClass exceptionClass, final IRubyObject message) {
        return exceptionClass.callMethod(context, "new", message.convertToString());
    }
    
    static {
        RubyException.EXCEPTION_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                final RubyException instance = new RubyException(runtime, klass);
                instance.setMetaClass(klass);
                return instance;
            }
        };
        EXCEPTION_MARSHAL = new ObjectMarshal() {
            public void marshalTo(final Ruby runtime, final Object obj, final RubyClass type, final MarshalStream marshalStream) throws IOException {
                final RubyException exc = (RubyException)obj;
                marshalStream.registerLinkTarget(exc);
                final List<Variable<Object>> attrs = exc.getVariableList();
                attrs.add(new VariableEntry<Object>("mesg", (exc.message == null) ? runtime.getNil() : exc.message));
                attrs.add((Variable<IRubyObject>)new VariableEntry<IRubyObject>("bt", exc.getBacktrace()));
                marshalStream.dumpVariables(attrs);
            }
            
            public Object unmarshalFrom(final Ruby runtime, final RubyClass type, final UnmarshalStream unmarshalStream) throws IOException {
                final RubyException exc = (RubyException)type.allocate();
                unmarshalStream.registerLinkTarget(exc);
                unmarshalStream.defaultVariablesUnmarshal(exc);
                exc.message = (IRubyObject)exc.removeInternalVariable("mesg");
                exc.set_backtrace((IRubyObject)exc.removeInternalVariable("bt"));
                return exc;
            }
        };
    }
}
