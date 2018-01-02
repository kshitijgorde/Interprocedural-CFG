// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.marshal.DataType;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.io.ObjectOutputStream;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Block;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Object" }, include = { "Kernel" })
public class RubyObject extends RubyBasicObject
{
    public static final ObjectAllocator OBJECT_ALLOCATOR;
    public static final ObjectAllocator REIFYING_OBJECT_ALLOCATOR;
    
    public RubyObject(final Ruby runtime, final RubyClass metaClass) {
        super(runtime, metaClass);
    }
    
    public RubyObject(final RubyClass metaClass) {
        super(metaClass);
    }
    
    protected RubyObject(final Ruby runtime, final RubyClass metaClass, final boolean useObjectSpace, final boolean canBeTainted) {
        super(runtime, metaClass, useObjectSpace, canBeTainted);
    }
    
    protected RubyObject(final Ruby runtime, final RubyClass metaClass, final boolean useObjectSpace) {
        super(runtime, metaClass, useObjectSpace);
    }
    
    public static RubyClass createObjectClass(final Ruby runtime, final RubyClass objectClass) {
        objectClass.index = 14;
        objectClass.setReifiedClass(RubyObject.class);
        objectClass.defineAnnotatedMethods(RubyObject.class);
        return objectClass;
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public IRubyObject initialize() {
        return this.getRuntime().getNil();
    }
    
    public void attachToObjectSpace() {
        this.getRuntime().getObjectSpace().add(this);
    }
    
    public int getNativeTypeIndex() {
        return 14;
    }
    
    public static void puts(final Object obj) {
        System.out.println(obj.toString());
    }
    
    public boolean equals(final Object other) {
        return other == this || (other instanceof IRubyObject && RuntimeHelpers.invokedynamic(this.getRuntime().getCurrentContext(), this, 1, (IRubyObject)other).isTrue());
    }
    
    public String toString() {
        final RubyString rubyString = RuntimeHelpers.invoke(this.getRuntime().getCurrentContext(), this, "to_s").convertToString();
        return rubyString.getUnicodeValue();
    }
    
    public final void callInit(final IRubyObject[] args, final Block block) {
        RuntimeHelpers.invoke(this.getRuntime().getCurrentContext(), this, "initialize", args, block);
    }
    
    public final void callInit(final Block block) {
        RuntimeHelpers.invoke(this.getRuntime().getCurrentContext(), this, "initialize", block);
    }
    
    public final void callInit(final IRubyObject arg0, final Block block) {
        RuntimeHelpers.invoke(this.getRuntime().getCurrentContext(), this, "initialize", arg0, block);
    }
    
    public final void callInit(final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        RuntimeHelpers.invoke(this.getRuntime().getCurrentContext(), this, "initialize", arg0, arg1, block);
    }
    
    public final void callInit(final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        RuntimeHelpers.invoke(this.getRuntime().getCurrentContext(), this, "initialize", arg0, arg1, arg2, block);
    }
    
    @Deprecated
    public final IRubyObject convertToType(final RubyClass target, final int convertMethodIndex) {
        throw new RuntimeException("Not supported; use the String versions");
    }
    
    @Deprecated
    public IRubyObject specificEval(final ThreadContext context, final RubyModule mod, final IRubyObject[] args, final Block block) {
        if (block.isGiven()) {
            if (args.length > 0) {
                throw this.getRuntime().newArgumentError(args.length, 0);
            }
            return this.yieldUnder(context, mod, block);
        }
        else {
            if (args.length == 0) {
                throw this.getRuntime().newArgumentError("block not supplied");
            }
            if (args.length > 3) {
                final String lastFuncName = context.getFrameName();
                throw this.getRuntime().newArgumentError("wrong number of arguments: " + lastFuncName + "(src) or " + lastFuncName + "{..}");
            }
            RubyString evalStr;
            if (args[0] instanceof RubyString) {
                evalStr = (RubyString)args[0];
            }
            else {
                evalStr = args[0].convertToString();
            }
            String file;
            int line;
            if (args.length > 1) {
                file = args[1].convertToString().asJavaString();
                if (args.length > 2) {
                    line = (int)(args[2].convertToInteger().getLongValue() - 1L);
                }
                else {
                    line = 0;
                }
            }
            else {
                file = "(eval)";
                line = 0;
            }
            return this.evalUnder(context, mod, evalStr, file, line);
        }
    }
    
    public IRubyObject op_eqq(final ThreadContext context, final IRubyObject other) {
        return context.getRuntime().newBoolean(equalInternal(context, this, other));
    }
    
    protected static boolean equalInternal(final ThreadContext context, final IRubyObject a, final IRubyObject b) {
        if (a == b) {
            return true;
        }
        if (a instanceof RubySymbol) {
            return false;
        }
        if (a instanceof RubyFixnum && b instanceof RubyFixnum) {
            return ((RubyFixnum)a).fastEqual((RubyFixnum)b);
        }
        if (a instanceof RubyFloat && b instanceof RubyFloat) {
            return ((RubyFloat)a).fastEqual((RubyFloat)b);
        }
        return RuntimeHelpers.invokedynamic(context, a, 1, b).isTrue();
    }
    
    protected static boolean eqlInternal(final ThreadContext context, final IRubyObject a, final IRubyObject b) {
        if (a == b) {
            return true;
        }
        if (a instanceof RubySymbol) {
            return false;
        }
        if (a instanceof RubyNumeric) {
            return a.getClass() == b.getClass() && equalInternal(context, a, b);
        }
        return RuntimeHelpers.invokedynamic(context, a, 2, b).isTrue();
    }
    
    public int hashCode() {
        final IRubyObject hashValue = RuntimeHelpers.invokedynamic(this.getRuntime().getCurrentContext(), this, 3);
        if (hashValue instanceof RubyFixnum) {
            return (int)RubyNumeric.fix2long(hashValue);
        }
        return this.nonFixnumHashCode(hashValue);
    }
    
    private int nonFixnumHashCode(IRubyObject hashValue) {
        final Ruby runtime = this.getRuntime();
        if (runtime.is1_9()) {
            final RubyInteger integer = hashValue.convertToInteger();
            if (integer instanceof RubyBignum) {
                return integer.getBigIntegerValue().intValue();
            }
            return (int)integer.getLongValue();
        }
        else {
            hashValue = hashValue.callMethod(runtime.getCurrentContext(), "%", RubyFixnum.newFixnum(runtime, 536870923L));
            if (hashValue instanceof RubyFixnum) {
                return (int)RubyNumeric.fix2long(hashValue);
            }
            return System.identityHashCode(hashValue);
        }
    }
    
    static RubyString inspect(final ThreadContext context, final IRubyObject object) {
        return RubyString.objAsString(context, object.callMethod(context, "inspect"));
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        final List<String> names = this.getInstanceVariableNameList();
        out.writeInt(names.size());
        for (final String name : names) {
            out.writeObject(name);
            out.writeObject(this.getInstanceVariables().getInstanceVariable(name));
        }
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        for (int ivarCount = in.readInt(), i = 0; i < ivarCount; ++i) {
            this.setInstanceVariable((String)in.readObject(), (IRubyObject)in.readObject());
        }
    }
    
    static {
        OBJECT_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyObject(runtime, klass);
            }
        };
        REIFYING_OBJECT_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                klass.reifyWithAncestors();
                return klass.allocate();
            }
        };
    }
    
    public static class Data extends RubyObject implements DataType
    {
        public Data(final Ruby runtime, final RubyClass metaClass, final Object data) {
            super(runtime, metaClass);
            this.dataWrapStruct(data);
        }
        
        public Data(final RubyClass metaClass, final Object data) {
            super(metaClass);
            this.dataWrapStruct(data);
        }
    }
}
