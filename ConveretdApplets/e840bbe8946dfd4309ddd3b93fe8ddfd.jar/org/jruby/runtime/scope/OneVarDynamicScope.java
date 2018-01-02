// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.scope;

import org.jruby.RubyArray;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.DynamicScope;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.builtin.IRubyObject;

public class OneVarDynamicScope extends NoVarsDynamicScope
{
    private static final int SIZE = 1;
    private static final String SIZE_ERROR = "OneVarDynamicScope only supports scopes with one variable";
    private static final String GROW_ERROR = "OneVarDynamicScope cannot be grown; use ManyVarsDynamicScope";
    protected IRubyObject variableValueZero;
    
    public OneVarDynamicScope(final StaticScope staticScope, final DynamicScope parent) {
        super(staticScope, parent);
    }
    
    public OneVarDynamicScope(final StaticScope staticScope) {
        super(staticScope);
    }
    
    public void growIfNeeded() {
        this.growIfNeeded(1, "OneVarDynamicScope cannot be grown; use ManyVarsDynamicScope");
    }
    
    public DynamicScope cloneScope() {
        return new OneVarDynamicScope(this.staticScope, this.parent);
    }
    
    public IRubyObject[] getValues() {
        return new IRubyObject[] { this.variableValueZero };
    }
    
    public IRubyObject getValue(final int offset, final int depth) {
        if (depth > 0) {
            return this.parent.getValue(offset, depth - 1);
        }
        assert offset < 1 : "OneVarDynamicScope only supports scopes with one variable";
        return this.variableValueZero;
    }
    
    public IRubyObject getValueOrNil(final int offset, final int depth, final IRubyObject nil) {
        if (depth > 0) {
            return this.parent.getValueOrNil(offset, depth - 1, nil);
        }
        return this.getValueDepthZeroOrNil(offset, nil);
    }
    
    public IRubyObject getValueDepthZeroOrNil(final int offset, final IRubyObject nil) {
        final IRubyObject value = this.variableValueZero;
        if (value == null) {
            return this.variableValueZero = nil;
        }
        return value;
    }
    
    public IRubyObject getValueZeroDepthZeroOrNil(final IRubyObject nil) {
        final IRubyObject value = this.variableValueZero;
        if (value == null) {
            return this.variableValueZero = nil;
        }
        return value;
    }
    
    public IRubyObject setValue(final int offset, final IRubyObject value, final int depth) {
        if (depth > 0) {
            return this.parent.setValue(offset, value, depth - 1);
        }
        return this.variableValueZero = value;
    }
    
    public IRubyObject setValueDepthZero(final IRubyObject value, final int offset) {
        return this.variableValueZero = value;
    }
    
    public IRubyObject setValueZeroDepthZero(final IRubyObject value) {
        return this.variableValueZero = value;
    }
    
    public void setArgValues(final IRubyObject[] values, final int size) {
        if (size == 1) {
            this.variableValueZero = values[0];
        }
    }
    
    public void setArgValues(final IRubyObject arg0) {
        this.variableValueZero = arg0;
    }
    
    public void setEndArgValues(final IRubyObject[] values, final int index, final int size) {
        this.variableValueZero = values[0];
    }
    
    public IRubyObject[] getArgValues() {
        if (!this.staticScope.isArgumentScope()) {
            return this.parent.getArgValues();
        }
        final int totalArgs = this.staticScope.getRequiredArgs() + this.staticScope.getOptionalArgs();
        if (this.staticScope.getRestArg() >= 0) {
            final IRubyObject restArg = this.getValue(this.staticScope.getRestArg(), 0);
            final RubyArray splattedArgs = RuntimeHelpers.splatValue(restArg);
            final IRubyObject[] argValues = new IRubyObject[totalArgs + splattedArgs.size()];
            System.arraycopy(splattedArgs.toJavaArray(), 0, argValues, totalArgs, splattedArgs.size());
            if (totalArgs == 1) {
                argValues[0] = this.variableValueZero;
            }
            return argValues;
        }
        if (totalArgs == 1) {
            return new IRubyObject[] { this.variableValueZero };
        }
        return IRubyObject.NULL_ARRAY;
    }
}
