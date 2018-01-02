// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.assigner;

import org.jruby.common.IRubyWarnings;
import org.jruby.RubyArray;
import org.jruby.ast.util.ArgsUtil;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.ast.Node;

public class Pre1ExpandedRest0Post0Assigner extends Assigner
{
    private final Node parameter1;
    
    public Pre1ExpandedRest0Post0Assigner(final Node parameter1) {
        this.parameter1 = parameter1;
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block block) {
        this.parameter1.assign(runtime, context, self, runtime.getNil(), block, false);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final Block block) {
        this.parameter1.assign(runtime, context, self, value1, block, false);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final IRubyObject value2, final Block block) {
        this.parameter1.assign(runtime, context, self, runtime.newArray(value1, value2), block, false);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final IRubyObject value2, final IRubyObject value3, final Block block) {
        this.parameter1.assign(runtime, context, self, runtime.newArray(value1, value2, value3), block, false);
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
            default: {
                this.assign(runtime, context, self, runtime.newArray(values), block);
                break;
            }
        }
    }
    
    public void assignArray(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value, final Block block) {
        this.assign(runtime, context, self, value, block);
    }
    
    public IRubyObject convertToArray(final Ruby runtime, final IRubyObject value) {
        return value;
    }
    
    public IRubyObject convertIfAlreadyArray(final Ruby runtime, IRubyObject value) {
        final int length = ArgsUtil.arrayLength(value);
        switch (length) {
            case 0: {
                value = runtime.getNil();
                break;
            }
            case 1: {
                value = ((RubyArray)value).eltInternal(0);
                break;
            }
            default: {
                runtime.getWarnings().warn(IRubyWarnings.ID.MULTIPLE_VALUES_FOR_BLOCK, "multiple values for a block parameter (" + length + " for 1)", new Object[0]);
                break;
            }
        }
        return value;
    }
}
