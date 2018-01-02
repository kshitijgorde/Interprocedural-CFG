// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.assigner;

import org.jruby.ast.util.ArgsUtil;
import org.jruby.RubyArray;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.ast.Node;

public class Pre0Rest1Post0Assigner extends Assigner
{
    private final Node rest;
    
    public Pre0Rest1Post0Assigner(final Node rest) {
        this.rest = rest;
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block block) {
        this.rest.assign(runtime, context, self, RubyArray.newEmptyArray(runtime), block, true);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final Block block) {
        this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(value1), block, true);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final IRubyObject value2, final Block block) {
        this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(value1, value2), block, true);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final IRubyObject value2, final IRubyObject value3, final Block block) {
        this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(value1, value2, value3), block, true);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject[] values, final Block block) {
        this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(values), block, true);
    }
    
    public void assignArray(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject arg, final Block block) {
        this.rest.assign(runtime, context, self, arg, block, true);
    }
    
    public IRubyObject convertToArray(final Ruby runtime, final IRubyObject value) {
        return ArgsUtil.convertToRubyArray(runtime, value, false);
    }
}
