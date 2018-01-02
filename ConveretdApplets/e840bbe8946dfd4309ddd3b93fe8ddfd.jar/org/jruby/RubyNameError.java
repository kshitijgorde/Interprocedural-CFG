// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.util.Sprintf;
import org.jruby.util.ByteList;
import org.jruby.exceptions.JumpException;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.CallType;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "NameError" }, parent = "StandardError")
public class RubyNameError extends RubyException
{
    private IRubyObject name;
    private static ObjectAllocator NAMEERROR_ALLOCATOR;
    
    public static RubyClass createNameErrorClass(final Ruby runtime, final RubyClass standardErrorClass) {
        final RubyClass nameErrorClass = runtime.defineClass("NameError", standardErrorClass, RubyNameError.NAMEERROR_ALLOCATOR);
        nameErrorClass.defineAnnotatedMethods(RubyNameError.class);
        return nameErrorClass;
    }
    
    public static RubyClass createNameErrorMessageClass(final Ruby runtime, final RubyClass nameErrorClass) {
        final RubyClass messageClass = nameErrorClass.defineClassUnder("Message", runtime.getClass("Data"), RubyNameErrorMessage.NAMEERRORMESSAGE_ALLOCATOR);
        messageClass.defineAnnotatedMethods(RubyNameErrorMessage.class);
        return messageClass;
    }
    
    protected RubyNameError(final Ruby runtime, final RubyClass exceptionClass) {
        this(runtime, exceptionClass, exceptionClass.getName());
    }
    
    public RubyNameError(final Ruby runtime, final RubyClass exceptionClass, final String message) {
        this(runtime, exceptionClass, message, null);
    }
    
    public RubyNameError(final Ruby runtime, final RubyClass exceptionClass, final String message, final String name) {
        super(runtime, exceptionClass, message);
        this.name = ((name == null) ? runtime.getNil() : runtime.newString(name));
    }
    
    @JRubyMethod(name = { "exception" }, rest = true, meta = true)
    public static RubyException newRubyNameError(final IRubyObject recv, final IRubyObject[] args) {
        final RubyClass klass = (RubyClass)recv;
        final RubyException newError = (RubyException)klass.allocate();
        newError.callInit(args, Block.NULL_BLOCK);
        return newError;
    }
    
    @JRubyMethod(name = { "initialize" }, optional = 2)
    public IRubyObject initialize(IRubyObject[] args, final Block block) {
        if (args.length > 1) {
            this.name = args[args.length - 1];
            final int newLength = (args.length > 2) ? (args.length - 2) : (args.length - 1);
            final IRubyObject[] tmpArgs = new IRubyObject[newLength];
            System.arraycopy(args, 0, tmpArgs, 0, newLength);
            args = tmpArgs;
        }
        else {
            this.name = this.getRuntime().getNil();
        }
        super.initialize(args, block);
        return this;
    }
    
    @JRubyMethod(name = { "to_s" })
    public IRubyObject to_s() {
        if (this.message.isNil()) {
            return this.getRuntime().newString(this.message.getMetaClass().getName());
        }
        final RubyString str = this.message.convertToString();
        if (str != this.message) {
            this.message = str;
        }
        if (this.isTaint()) {
            this.message.setTaint(true);
        }
        return this.message;
    }
    
    @JRubyMethod(name = { "name" })
    public IRubyObject name() {
        return this.name;
    }
    
    public void copySpecialInstanceVariables(final IRubyObject clone) {
        super.copySpecialInstanceVariables(clone);
        final RubyNameError exception = (RubyNameError)clone;
        exception.name = this.name;
    }
    
    static {
        RubyNameError.NAMEERROR_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyNameError(runtime, klass);
            }
        };
    }
    
    @JRubyClass(name = { "NameError::Message" }, parent = "Data")
    public static final class RubyNameErrorMessage extends RubyObject
    {
        static ObjectAllocator NAMEERRORMESSAGE_ALLOCATOR;
        private final IRubyObject object;
        private final IRubyObject method;
        private final Visibility visibility;
        private final CallType callType;
        
        RubyNameErrorMessage(final Ruby runtime, final IRubyObject object, final IRubyObject method, final Visibility visibility, final CallType callType) {
            super(runtime, runtime.getNameErrorMessage(), false);
            this.object = object;
            this.method = method;
            this.visibility = visibility;
            this.callType = callType;
        }
        
        @JRubyMethod(name = { "_load" }, meta = true)
        public static IRubyObject load(final IRubyObject recv, final IRubyObject arg) {
            return arg;
        }
        
        @JRubyMethod(name = { "_dump" })
        public IRubyObject dump(final ThreadContext context, final IRubyObject arg) {
            return this.to_str(context);
        }
        
        @JRubyMethod(name = { "to_str" })
        public IRubyObject to_str(final ThreadContext context) {
            String format = null;
            if (this.visibility == Visibility.PRIVATE) {
                format = "private method `%s' called for %s";
            }
            else if (this.visibility == Visibility.PROTECTED) {
                format = "protected method `%s' called for %s";
            }
            else if (this.callType == CallType.VARIABLE) {
                format = "undefined local variable or method `%s' for %s";
            }
            else if (this.callType == CallType.SUPER) {
                format = "super: no superclass method `%s'";
            }
            if (format == null) {
                format = "undefined method `%s' for %s";
            }
            String description = null;
            if (this.object.isNil()) {
                description = "nil";
            }
            else if (this.object instanceof RubyBoolean && this.object.isTrue()) {
                description = "true";
            }
            else if (this.object instanceof RubyBoolean && !this.object.isTrue()) {
                description = "false";
            }
            else {
                try {
                    description = RubyObject.inspect(context, this.object).toString();
                }
                catch (JumpException ex) {}
                if (description == null || description.length() > 65) {
                    description = this.object.anyToString().toString();
                }
            }
            if (description.length() == 0 || (description.length() > 0 && description.charAt(0) != '#')) {
                description = description + ":" + this.object.getMetaClass().getRealClass().getName();
            }
            final Ruby runtime = this.getRuntime();
            final RubyArray arr = runtime.newArray(this.method, runtime.newString(description));
            final ByteList msgBytes = new ByteList(format.length() + description.length() + this.method.toString().length());
            Sprintf.sprintf(msgBytes, format, arr);
            return runtime.newString(msgBytes).infectBy(this.object);
        }
        
        static {
            RubyNameErrorMessage.NAMEERRORMESSAGE_ALLOCATOR = new ObjectAllocator() {
                public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                    final IRubyObject dummy = new RubyObject(runtime, runtime.getObject());
                    return new RubyNameErrorMessage(runtime, dummy, dummy, Visibility.PRIVATE, CallType.VARIABLE);
                }
            };
        }
    }
}
