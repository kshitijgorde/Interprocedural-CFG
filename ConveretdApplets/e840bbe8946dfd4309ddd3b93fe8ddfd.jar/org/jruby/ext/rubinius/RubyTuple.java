// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.rubinius;

import org.jruby.util.ArraysUtil;
import org.jruby.RubyFixnum;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyClass;
import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyObject;

public class RubyTuple extends RubyObject
{
    private IRubyObject[] ary;
    
    public RubyTuple(final Ruby runtime, final RubyClass metaclass, final int size) {
        super(runtime, metaclass);
        RuntimeHelpers.fillNil(this.ary = new IRubyObject[size], runtime);
    }
    
    public static void createTupleClass(final Ruby runtime) {
        final RubyClass tupleClass = runtime.getOrCreateModule("Rubinius").defineClassUnder("Tuple", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        tupleClass.setReifiedClass(RubyTuple.class);
        tupleClass.defineAnnotatedMethods(RubyTuple.class);
    }
    
    @JRubyMethod(name = { "new" }, meta = true)
    public static IRubyObject rbNew(final ThreadContext context, final IRubyObject tupleCls, final IRubyObject cnt) {
        final int size = (int)cnt.convertToInteger().getLongValue();
        return new RubyTuple(context.runtime, (RubyClass)tupleCls, size);
    }
    
    @JRubyMethod(name = { "[]" })
    public IRubyObject op_aref(final ThreadContext context, final IRubyObject idx) {
        return this.ary[(int)((RubyFixnum)idx).getLongValue()];
    }
    
    @JRubyMethod(name = { "[]=" })
    public IRubyObject op_aset(final ThreadContext context, final IRubyObject idx, final IRubyObject val) {
        final int index = (int)((RubyFixnum)idx).getLongValue();
        if (index >= this.ary.length) {
            this.ary = ArraysUtil.copyOf(this.ary, this.ary.length * 3 / 2 + 1);
        }
        return this.ary[index] = val;
    }
}
