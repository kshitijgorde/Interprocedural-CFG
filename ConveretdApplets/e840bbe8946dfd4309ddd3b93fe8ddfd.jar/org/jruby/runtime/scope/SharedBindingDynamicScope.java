// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.scope;

import org.jruby.RubyArray;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.parser.StaticScope;
import org.jruby.compiler.ir.IRMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.DynamicScope;

public class SharedBindingDynamicScope extends DynamicScope
{
    private IRubyObject[] variableValues;
    private IRMethod irMethod;
    
    public SharedBindingDynamicScope(final StaticScope staticScope, final IRMethod irMethod) {
        super(staticScope);
        this.irMethod = irMethod;
        this.allocate();
    }
    
    private void allocate() {
        if (this.variableValues == null) {
            final int size = this.irMethod.getBindingSlotsCount();
            this.variableValues = new IRubyObject[size];
        }
    }
    
    public DynamicScope cloneScope() {
        return new SharedBindingDynamicScope(this.staticScope, this.irMethod);
    }
    
    public IRubyObject[] getValues() {
        return this.variableValues;
    }
    
    public IRubyObject getValue(final int offset, final int depth) {
        if (depth > 0) {
            return this.parent.getValue(offset, depth - 1);
        }
        this.assertGetValue(offset, depth);
        return this.variableValues[offset];
    }
    
    public IRubyObject getValueOrNil(final int offset, final int depth, final IRubyObject nil) {
        if (depth > 0) {
            return this.parent.getValueOrNil(offset, depth - 1, nil);
        }
        return this.getValueDepthZeroOrNil(offset, nil);
    }
    
    public IRubyObject getValueDepthZeroOrNil(final int offset, final IRubyObject nil) {
        this.assertGetValueDepthZeroOrNil(offset);
        final IRubyObject value = this.variableValues[offset];
        if (value == null) {
            return this.setValueDepthZero(value, offset);
        }
        return value;
    }
    
    public IRubyObject getValueZeroDepthZeroOrNil(final IRubyObject nil) {
        this.assertGetValueZeroDepthZeroOrNil();
        final IRubyObject value = this.variableValues[0];
        if (value == null) {
            return this.setValueZeroDepthZero(value);
        }
        return value;
    }
    
    public IRubyObject getValueOneDepthZeroOrNil(final IRubyObject nil) {
        this.assertGetValueOneDepthZeroOrNil();
        final IRubyObject value = this.variableValues[1];
        if (value == null) {
            return this.setValueOneDepthZero(value);
        }
        return value;
    }
    
    public IRubyObject getValueTwoDepthZeroOrNil(final IRubyObject nil) {
        this.assertGetValueTwoDepthZeroOrNil();
        final IRubyObject value = this.variableValues[2];
        if (value == null) {
            return this.setValueTwoDepthZero(value);
        }
        return value;
    }
    
    public IRubyObject getValueThreeDepthZeroOrNil(final IRubyObject nil) {
        this.assertGetValueThreeDepthZeroOrNil();
        final IRubyObject value = this.variableValues[3];
        if (value == null) {
            return this.setValueThreeDepthZero(value);
        }
        return value;
    }
    
    public IRubyObject setValue(final int offset, final IRubyObject value, final int depth) {
        if (depth > 0) {
            this.assertParent();
            return this.parent.setValue(offset, value, depth - 1);
        }
        this.assertSetValue(offset, value);
        return this.setValueDepthZero(value, offset);
    }
    
    public IRubyObject setValueDepthZero(final IRubyObject value, final int offset) {
        this.assertSetValueDepthZero(offset, value);
        return this.variableValues[offset] = value;
    }
    
    public IRubyObject setValueZeroDepthZero(final IRubyObject value) {
        this.assertSetValueZeroDepthZero(value);
        return this.variableValues[0] = value;
    }
    
    public IRubyObject setValueOneDepthZero(final IRubyObject value) {
        this.assertSetValueOneDepthZero(value);
        return this.variableValues[1] = value;
    }
    
    public IRubyObject setValueTwoDepthZero(final IRubyObject value) {
        this.assertSetValueTwoDepthZero(value);
        return this.variableValues[2] = value;
    }
    
    public IRubyObject setValueThreeDepthZero(final IRubyObject value) {
        this.assertSetValueThreeDepthZero(value);
        return this.variableValues[3] = value;
    }
    
    public void setArgValues(final IRubyObject[] values, final int size) {
        System.arraycopy(values, 0, this.variableValues, 0, size);
    }
    
    public void setArgValues(final IRubyObject arg0) {
        this.variableValues[0] = arg0;
    }
    
    public void setArgValues(final IRubyObject arg0, final IRubyObject arg1) {
        this.variableValues[0] = arg0;
        this.variableValues[1] = arg1;
    }
    
    public void setArgValues(final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        this.variableValues[0] = arg0;
        this.variableValues[1] = arg1;
        this.variableValues[2] = arg2;
    }
    
    public void setEndArgValues(final IRubyObject[] values, final int index, final int size) {
        System.arraycopy(values, values.length - size, this.variableValues, index, size);
    }
    
    public IRubyObject[] getArgValues() {
        if (!this.staticScope.isArgumentScope()) {
            return this.parent.getArgValues();
        }
        final int totalArgs = this.staticScope.getRequiredArgs() + this.staticScope.getOptionalArgs();
        if (this.staticScope.getRestArg() < 0) {
            final IRubyObject[] argValues = new IRubyObject[totalArgs];
            System.arraycopy(this.variableValues, 0, argValues, 0, totalArgs);
            return argValues;
        }
        final IRubyObject restArg = this.getValue(this.staticScope.getRestArg(), 0);
        assert restArg != null;
        final RubyArray splattedArgs = RuntimeHelpers.splatValue(restArg);
        final IRubyObject[] argValues2 = new IRubyObject[totalArgs + splattedArgs.size()];
        System.arraycopy(this.variableValues, 0, argValues2, 0, totalArgs);
        System.arraycopy(splattedArgs.toJavaArray(), 0, argValues2, totalArgs, splattedArgs.size());
        return argValues2;
    }
    
    public void growIfNeeded() {
        final int dynamicSize = (this.variableValues == null) ? 0 : this.variableValues.length;
        if (this.staticScope.getNumberOfVariables() > dynamicSize) {
            final IRubyObject[] values = new IRubyObject[this.staticScope.getNumberOfVariables()];
            if (dynamicSize > 0) {
                System.arraycopy(this.variableValues, 0, values, 0, dynamicSize);
            }
            this.variableValues = values;
        }
    }
    
    private void assertGetValue(final int offset, final int depth) {
        final IRubyObject[] values = this.variableValues;
        assert values != null && offset < values.length : "No variables or index to big for getValue off: " + offset + ", Dep: " + depth + ", O: " + this;
    }
    
    private void assertGetValueDepthZeroOrNil(final int offset) {
        final IRubyObject[] values = this.variableValues;
        assert values != null && offset < values.length : "No variables or index too big for getValue off: " + offset + ", Dep: " + 0 + ", O: " + this;
    }
    
    private void assertGetValueZeroDepthZeroOrNil() {
        final IRubyObject[] values = this.variableValues;
        assert values != null && 0 < values.length : "No variables or index to big for getValue off: 0, Dep: 0, O: " + this;
    }
    
    private void assertGetValueOneDepthZeroOrNil() {
        final IRubyObject[] values = this.variableValues;
        assert values != null && 1 < values.length : "No variables or index to big for getValue off: 1, Dep: 0, O: " + this;
    }
    
    private void assertGetValueTwoDepthZeroOrNil() {
        final IRubyObject[] values = this.variableValues;
        assert values != null && 3 < values.length : "No variables or index to big for getValue off: 3, Dep: 0, O: " + this;
    }
    
    private void assertGetValueThreeDepthZeroOrNil() {
        final IRubyObject[] values = this.variableValues;
        assert values != null && 2 < values.length : "No variables or index to big for getValue off: 2, Dep: 0, O: " + this;
    }
    
    private void assertParent() {
        assert this.parent != null : "If depth > 0, then parent should not ever be null";
    }
    
    private void assertSetValue(final int offset, final IRubyObject value) {
        assert offset < this.variableValues.length : "Setting " + offset + " to " + value + ", O: " + this;
    }
    
    private void assertSetValueDepthZero(final int offset, final IRubyObject value) {
        assert offset < this.variableValues.length : "Setting " + offset + " to " + value + ", O: " + this;
    }
    
    private void assertSetValueZeroDepthZero(final IRubyObject value) {
        assert 0 < this.variableValues.length : "Setting 0 to " + value + ", O: " + this;
    }
    
    private void assertSetValueOneDepthZero(final IRubyObject value) {
        assert 1 < this.variableValues.length : "Setting 1 to " + value + ", O: " + this;
    }
    
    private void assertSetValueThreeDepthZero(final IRubyObject value) {
        assert 3 < this.variableValues.length : "Setting 3 to " + value + ", O: " + this;
    }
    
    private void assertSetValueTwoDepthZero(final IRubyObject value) {
        assert 2 < this.variableValues.length : "Setting 2 to " + value + ", O: " + this;
    }
}
