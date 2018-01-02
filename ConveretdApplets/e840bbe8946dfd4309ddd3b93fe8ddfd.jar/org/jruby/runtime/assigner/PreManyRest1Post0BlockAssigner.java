// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.assigner;

import org.jruby.RubyArray;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.ast.Node;
import org.jruby.ast.ListNode;

public class PreManyRest1Post0BlockAssigner extends Assigner
{
    private final int preLength;
    private final ListNode pre;
    private final Node rest;
    private final Node blockVar;
    
    public PreManyRest1Post0BlockAssigner(final ListNode pre, final int preLength, final Node rest, final Node blockVar) {
        this.pre = pre;
        this.preLength = preLength;
        this.rest = rest;
        this.blockVar = blockVar;
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block block) {
        this.assignNilTo(runtime, context, self, block, 0);
        this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(IRubyObject.NULL_ARRAY), block, true);
        this.blockVar.assign(runtime, context, self, RuntimeHelpers.processBlockArgument(runtime, block), Block.NULL_BLOCK, false);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final Block block) {
        this.pre.get(0).assign(runtime, context, self, value1, block, false);
        this.assignNilTo(runtime, context, self, block, 1);
        this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(IRubyObject.NULL_ARRAY), block, true);
        this.blockVar.assign(runtime, context, self, RuntimeHelpers.processBlockArgument(runtime, block), Block.NULL_BLOCK, false);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final IRubyObject value2, final Block block) {
        this.pre.get(0).assign(runtime, context, self, value1, block, false);
        this.pre.get(1).assign(runtime, context, self, value1, block, false);
        this.assignNilTo(runtime, context, self, block, 2);
        this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(IRubyObject.NULL_ARRAY), block, true);
        this.blockVar.assign(runtime, context, self, RuntimeHelpers.processBlockArgument(runtime, block), Block.NULL_BLOCK, false);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value1, final IRubyObject value2, final IRubyObject value3, final Block block) {
        this.pre.get(0).assign(runtime, context, self, value1, block, false);
        this.pre.get(1).assign(runtime, context, self, value1, block, false);
        this.pre.get(2).assign(runtime, context, self, value1, block, false);
        this.assignNilTo(runtime, context, self, block, 3);
        this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(IRubyObject.NULL_ARRAY), block, true);
        this.blockVar.assign(runtime, context, self, RuntimeHelpers.processBlockArgument(runtime, block), Block.NULL_BLOCK, false);
    }
    
    public void assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject[] values, final Block block) {
        final int valueLength = (values == null) ? 0 : values.length;
        switch (valueLength) {
            case 0: {
                this.assign(runtime, context, self, block);
            }
            case 1: {
                this.assign(runtime, context, self, values[0], block);
            }
            case 2: {
                this.assign(runtime, context, self, values[0], values[1], block);
            }
            case 3: {
                this.assign(runtime, context, self, values[0], values[1], values[2], block);
            }
            default: {
                for (int i = 0; i < this.preLength && i < valueLength; ++i) {
                    this.pre.get(i).assign(runtime, context, self, values[i], block, false);
                }
                if (valueLength < this.preLength) {
                    this.assignNilTo(runtime, context, self, block, valueLength);
                }
                else if (valueLength == this.preLength) {
                    this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(IRubyObject.NULL_ARRAY), block, true);
                }
                else {
                    this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(this.shiftedArray(values, valueLength - this.preLength)), block, true);
                }
                this.blockVar.assign(runtime, context, self, RuntimeHelpers.processBlockArgument(runtime, block), Block.NULL_BLOCK, false);
            }
        }
    }
    
    public void assignArray(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject arg, final Block block) {
        final RubyArray values = (RubyArray)arg;
        final int valueLength = values.getLength();
        switch (valueLength) {
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
        }
        for (int i = 0; i < this.preLength && i < valueLength; ++i) {
            this.pre.get(i).assign(runtime, context, self, values.eltInternal(i), block, false);
        }
        if (valueLength < this.preLength) {
            this.assignNilTo(runtime, context, self, block, valueLength);
        }
        else if (valueLength == this.preLength) {
            this.rest.assign(runtime, context, self, runtime.newArrayNoCopyLight(IRubyObject.NULL_ARRAY), block, true);
        }
        else {
            this.rest.assign(runtime, context, self, values.subseqLight(this.preLength, valueLength - this.preLength), block, true);
        }
        this.blockVar.assign(runtime, context, self, RuntimeHelpers.processBlockArgument(runtime, block), Block.NULL_BLOCK, false);
    }
    
    private void assignNilTo(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block block, final int start) {
        final IRubyObject nil = runtime.getNil();
        for (int i = start; i < this.preLength; ++i) {
            this.pre.get(i).assign(runtime, context, self, nil, block, false);
        }
    }
}
