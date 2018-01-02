// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.assigner;

import org.jruby.ast.util.ArgsUtil;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;

public abstract class Assigner
{
    public abstract void assign(final Ruby p0, final ThreadContext p1, final IRubyObject p2, final Block p3);
    
    public abstract void assign(final Ruby p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4);
    
    public abstract void assign(final Ruby p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5);
    
    public abstract void assign(final Ruby p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final IRubyObject p5, final Block p6);
    
    public abstract void assign(final Ruby p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4);
    
    public abstract void assignArray(final Ruby p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4);
    
    public IRubyObject convertToArray(final Ruby runtime, final IRubyObject value) {
        return ArgsUtil.convertToRubyArray(runtime, value, true);
    }
    
    public IRubyObject convertIfAlreadyArray(final Ruby runtime, final IRubyObject value) {
        return value;
    }
    
    protected IRubyObject[] shiftedArray(final IRubyObject[] originalValues, final int numberOfElementsToShift) {
        final int newLength = originalValues.length - numberOfElementsToShift;
        final IRubyObject[] newValues = new IRubyObject[newLength];
        System.arraycopy(originalValues, numberOfElementsToShift, newValues, 0, newLength);
        return newValues;
    }
}
