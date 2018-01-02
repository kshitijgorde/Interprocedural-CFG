// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.assigner;

import org.jruby.RubyArray;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.ast.Node;

public class Pre2Rest0Post0Assigner extends Assigner
{
    private final Node parameter1;
    private final Node parameter2;
    
    public Pre2Rest0Post0Assigner(final Node parameter1, final Node parameter2) {
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block block) {
        this.parameter1.assign(runtime, context, self, runtime.getNil(), block, false);
        this.parameter2.assign(runtime, context, self, runtime.getNil(), block, false);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final Block block) {
        this.parameter1.assign(runtime, context, self, value1, block, false);
        this.parameter2.assign(runtime, context, self, runtime.getNil(), block, false);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final IRubyObject value2, final Block block) {
        this.parameter1.assign(runtime, context, self, value1, block, false);
        this.parameter2.assign(runtime, context, self, value2, block, false);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final IRubyObject value2, final IRubyObject value3, final Block block) {
        this.parameter1.assign(runtime, context, self, value1, block, false);
        this.parameter2.assign(runtime, context, self, value2, block, false);
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
                this.assign(runtime, context, self, values[0], values[1], block);
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
            default: {
                this.assign(runtime, context, self, values.eltInternal(0), values.eltInternal(1), block);
                break;
            }
        }
    }
}