// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.assigner;

import org.jruby.ast.util.ArgsUtil;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.ast.Node;

public class Pre0Rest0Post0BlockAssigner extends Assigner
{
    private final Node blockVar;
    
    public Pre0Rest0Post0BlockAssigner(final Node blockVar) {
        this.blockVar = blockVar;
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block block) {
        this.blockVar.assign(runtime, context, self, RuntimeHelpers.processBlockArgument(runtime, block), Block.NULL_BLOCK, false);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final Block block) {
        this.blockVar.assign(runtime, context, self, RuntimeHelpers.processBlockArgument(runtime, block), Block.NULL_BLOCK, false);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final IRubyObject value2, final Block block) {
        this.blockVar.assign(runtime, context, self, RuntimeHelpers.processBlockArgument(runtime, block), Block.NULL_BLOCK, false);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final IRubyObject value2, final IRubyObject value3, final Block block) {
        this.blockVar.assign(runtime, context, self, RuntimeHelpers.processBlockArgument(runtime, block), Block.NULL_BLOCK, false);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject[] values, final Block block) {
        this.blockVar.assign(runtime, context, self, RuntimeHelpers.processBlockArgument(runtime, block), Block.NULL_BLOCK, false);
    }
    
    public void assignArray(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject values, final Block block) {
        this.blockVar.assign(runtime, context, self, RuntimeHelpers.processBlockArgument(runtime, block), Block.NULL_BLOCK, false);
    }
    
    public IRubyObject convertToArray(final Ruby runtime, final IRubyObject value) {
        return ArgsUtil.convertToRubyArray(runtime, value, false);
    }
}
