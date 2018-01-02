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

public class Pre3Rest1Post0Assigner extends Assigner
{
    private Node parameter1;
    private Node parameter2;
    private Node parameter3;
    private Node rest;
    
    public Pre3Rest1Post0Assigner(final Node parameter1, final Node parameter2, final Node parameter3, final Node rest) {
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
        this.parameter3 = parameter3;
        this.rest = rest;
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block block) {
        final IRubyObject nil = runtime.getNil();
        this.parameter1.assign(runtime, context, self, nil, block, false);
        this.parameter2.assign(runtime, context, self, nil, block, false);
        this.parameter3.assign(runtime, context, self, nil, block, false);
        this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(IRubyObject.NULL_ARRAY), block, true);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final Block block) {
        this.parameter1.assign(runtime, context, self, value1, block, false);
        this.parameter2.assign(runtime, context, self, runtime.getNil(), block, false);
        this.parameter3.assign(runtime, context, self, runtime.getNil(), block, false);
        this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(IRubyObject.NULL_ARRAY), block, true);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final IRubyObject value2, final Block block) {
        this.parameter1.assign(runtime, context, self, value1, block, false);
        this.parameter2.assign(runtime, context, self, value2, block, false);
        this.parameter3.assign(runtime, context, self, runtime.getNil(), block, false);
        this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(IRubyObject.NULL_ARRAY), block, true);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final IRubyObject value2, final IRubyObject value3, final Block block) {
        this.parameter1.assign(runtime, context, self, value1, block, false);
        this.parameter2.assign(runtime, context, self, value2, block, false);
        this.parameter3.assign(runtime, context, self, value3, block, false);
        this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(IRubyObject.NULL_ARRAY), block, true);
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
                this.parameter2.assign(runtime, context, self, values[1], block, false);
                this.parameter3.assign(runtime, context, self, values[2], block, false);
                this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(this.shiftedArray(values, 3)), block, true);
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
                this.parameter2.assign(runtime, context, self, values.eltInternal(1), block, false);
                this.parameter3.assign(runtime, context, self, values.eltInternal(2), block, false);
                this.rest.assign(runtime, context, self, values.subseqLight(3L, length - 3), block, true);
                break;
            }
        }
    }
}
