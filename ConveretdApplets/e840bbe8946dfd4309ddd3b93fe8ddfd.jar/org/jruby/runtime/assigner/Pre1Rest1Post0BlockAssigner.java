// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.assigner;

import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyArray;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.ast.Node;

public class Pre1Rest1Post0BlockAssigner extends Assigner
{
    private final Node parameter1;
    private final Node rest;
    private final Node blockVar;
    
    public Pre1Rest1Post0BlockAssigner(final Node parameter1, final Node rest, final Node blockVar) {
        this.parameter1 = parameter1;
        this.rest = rest;
        this.blockVar = blockVar;
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block block) {
        this.parameter1.assign(runtime, context, self, runtime.getNil(), block, false);
        this.rest.assign(runtime, context, self, RubyArray.newEmptyArray(runtime), block, true);
        this.blockVar.assign(runtime, context, self, RuntimeHelpers.processBlockArgument(runtime, block), Block.NULL_BLOCK, false);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final Block block) {
        this.parameter1.assign(runtime, context, self, value1, block, false);
        this.rest.assign(runtime, context, self, RubyArray.newEmptyArray(runtime), block, true);
        this.blockVar.assign(runtime, context, self, RuntimeHelpers.processBlockArgument(runtime, block), Block.NULL_BLOCK, false);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final IRubyObject value2, final Block block) {
        this.parameter1.assign(runtime, context, self, value1, block, false);
        this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(value2), block, true);
        this.blockVar.assign(runtime, context, self, RuntimeHelpers.processBlockArgument(runtime, block), Block.NULL_BLOCK, false);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final IRubyObject value2, final IRubyObject value3, final Block block) {
        this.parameter1.assign(runtime, context, self, value1, block, false);
        this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(value2, value3), block, true);
        this.blockVar.assign(runtime, context, self, RuntimeHelpers.processBlockArgument(runtime, block), Block.NULL_BLOCK, false);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject[] values, final Block block) {
        final int length = (values == null) ? 0 : values.length;
        switch (length) {
            case 0: {
                this.assign(runtime, context, self, block);
                break;
            }
            case 1: {
                this.assign(runtime, context, self, values[0], block);
                break;
            }
            case 2: {
                this.assign(runtime, context, self, values[0], values[1], block);
                break;
            }
            case 3: {
                this.assign(runtime, context, self, values[0], values[1], values[2], block);
                break;
            }
            default: {
                this.parameter1.assign(runtime, context, self, values[0], block, false);
                this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(this.shiftedArray(values, 1)), block, true);
                this.blockVar.assign(runtime, context, self, RuntimeHelpers.processBlockArgument(runtime, block), Block.NULL_BLOCK, false);
                break;
            }
        }
    }
    
    public void assignArray(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject arg, final Block block) {
        final RubyArray values = (RubyArray)arg;
        final int length = values.getLength();
        switch (length) {
            case 0: {
                this.assign(runtime, context, self, block);
                break;
            }
            case 1: {
                this.assign(runtime, context, self, values.eltInternal(0), block);
                break;
            }
            case 2: {
                this.assign(runtime, context, self, values.eltInternal(0), values.eltInternal(1), block);
                break;
            }
            case 3: {
                this.assign(runtime, context, self, values.eltInternal(0), values.eltInternal(1), values.eltInternal(2), block);
                break;
            }
            default: {
                this.parameter1.assign(runtime, context, self, values.eltInternal(0), block, false);
                this.rest.assign(runtime, context, self, values.subseqLight(1L, length - 1), block, true);
                this.blockVar.assign(runtime, context, self, RuntimeHelpers.processBlockArgument(runtime, block), Block.NULL_BLOCK, false);
                break;
            }
        }
    }
}
