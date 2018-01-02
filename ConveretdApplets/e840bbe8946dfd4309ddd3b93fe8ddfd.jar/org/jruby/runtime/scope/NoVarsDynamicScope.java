// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.scope;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.DynamicScope;

public class NoVarsDynamicScope extends DynamicScope
{
    private static final int SIZE = 0;
    private static final String SIZE_ERROR = "NoVarsDynamicScope only supports scopes with one variable";
    private static final String GROW_ERROR = "NoVarsDynamicScope cannot be grown; use ManyVarsDynamicScope";
    
    public NoVarsDynamicScope(final StaticScope staticScope, final DynamicScope parent) {
        super(staticScope, parent);
    }
    
    public NoVarsDynamicScope(final StaticScope staticScope) {
        super(staticScope);
    }
    
    public void growIfNeeded() {
        this.growIfNeeded(0, "NoVarsDynamicScope cannot be grown; use ManyVarsDynamicScope");
    }
    
    protected void growIfNeeded(final int size, final String message) {
        if (this.staticScope.getNumberOfVariables() != size) {
            throw new RuntimeException(message);
        }
    }
    
    public DynamicScope cloneScope() {
        return new NoVarsDynamicScope(this.staticScope, this.parent);
    }
    
    public IRubyObject[] getValues() {
        return IRubyObject.NULL_ARRAY;
    }
    
    public IRubyObject getValue(final int offset, final int depth) {
        assert depth != 0 : "NoVarsDynamicScope only supports scopes with one variable";
        return this.parent.getValue(offset, depth - 1);
    }
    
    public IRubyObject getValueOrNil(final int offset, final int depth, final IRubyObject nil) {
        return this.parent.getValueOrNil(offset, depth - 1, nil);
    }
    
    public IRubyObject getValueDepthZeroOrNil(final int offset, final IRubyObject nil) {
        throw new RuntimeException(this.getClass().getSimpleName() + " does not support scopes with any variables");
    }
    
    public IRubyObject getValueZeroDepthZeroOrNil(final IRubyObject nil) {
        throw new RuntimeException(this.getClass().getSimpleName() + " does not support scopes with one or more variables");
    }
    
    public IRubyObject getValueOneDepthZeroOrNil(final IRubyObject nil) {
        throw new RuntimeException(this.getClass().getSimpleName() + " does not support scopes with two or more variables");
    }
    
    public IRubyObject getValueTwoDepthZeroOrNil(final IRubyObject nil) {
        throw new RuntimeException(this.getClass().getSimpleName() + " does not support scopes with three or more variables");
    }
    
    public IRubyObject getValueThreeDepthZeroOrNil(final IRubyObject nil) {
        throw new RuntimeException(this.getClass().getSimpleName() + " does not support scopes with four or more variables");
    }
    
    public IRubyObject setValue(final int offset, final IRubyObject value, final int depth) {
        return this.parent.setValue(offset, value, depth - 1);
    }
    
    public IRubyObject setValueDepthZero(final IRubyObject value, final int offset) {
        throw new RuntimeException(this.getClass().getSimpleName() + " does not support scopes with any variables");
    }
    
    public IRubyObject setValueZeroDepthZero(final IRubyObject value) {
        throw new RuntimeException(this.getClass().getSimpleName() + " does not support scopes with one or more variables");
    }
    
    public IRubyObject setValueOneDepthZero(final IRubyObject value) {
        throw new RuntimeException(this.getClass().getSimpleName() + " does not support scopes with two or more variables");
    }
    
    public IRubyObject setValueTwoDepthZero(final IRubyObject value) {
        throw new RuntimeException(this.getClass().getSimpleName() + " does not support scopes with three or more variables");
    }
    
    public IRubyObject setValueThreeDepthZero(final IRubyObject value) {
        throw new RuntimeException(this.getClass().getSimpleName() + " does not support scopes with four or more variables");
    }
    
    public void setArgValues(final IRubyObject[] values, final int size) {
        assert size <= 0 : this.getClass().getSimpleName() + " does not support scopes with " + size + " variables";
    }
    
    public void setArgValues(final IRubyObject arg0) {
        throw new RuntimeException(this.getClass().getSimpleName() + " does not support scopes with 1 variable");
    }
    
    public void setArgValues(final IRubyObject arg0, final IRubyObject arg1) {
        throw new RuntimeException(this.getClass().getSimpleName() + " does not support scopes with 2 variables");
    }
    
    public void setArgValues(final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        throw new RuntimeException(this.getClass().getSimpleName() + " does not support scopes with 3 variables");
    }
    
    public void setEndArgValues(final IRubyObject[] values, final int index, final int size) {
        assert false : this.getClass().getSimpleName() + " does not support any variables";
    }
    
    public IRubyObject[] getArgValues() {
        if (!this.staticScope.isArgumentScope()) {
            return this.parent.getArgValues();
        }
        final int totalArgs = this.staticScope.getRequiredArgs() + this.staticScope.getOptionalArgs();
        assert totalArgs == 0 : this.getClass().getSimpleName() + " only supports scopes with no variables";
        return IRubyObject.NULL_ARRAY;
    }
}
