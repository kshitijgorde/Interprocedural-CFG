// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.scope;

import org.jruby.RubyArray;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.DynamicScope;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.builtin.IRubyObject;

public class ThreeVarDynamicScope extends TwoVarDynamicScope
{
    private static final int SIZE = 3;
    private static final String SIZE_ERROR = "ThreeVarDynamicScope only supports scopes with three variables";
    private static final String GROW_ERROR = "ThreeVarDynamicScope cannot be grown; use ManyVarsDynamicScope";
    protected IRubyObject variableValueTwo;
    
    public ThreeVarDynamicScope(final StaticScope staticScope, final DynamicScope parent) {
        super(staticScope, parent);
    }
    
    public ThreeVarDynamicScope(final StaticScope staticScope) {
        super(staticScope);
    }
    
    public void growIfNeeded() {
        this.growIfNeeded(3, "ThreeVarDynamicScope cannot be grown; use ManyVarsDynamicScope");
    }
    
    public DynamicScope cloneScope() {
        return new ThreeVarDynamicScope(this.staticScope, this.parent);
    }
    
    public IRubyObject[] getValues() {
        return new IRubyObject[] { this.variableValueZero, this.variableValueOne, this.variableValueTwo };
    }
    
    public IRubyObject getValue(final int offset, final int depth) {
        if (depth > 0) {
            return this.parent.getValue(offset, depth - 1);
        }
        assert offset < 3 : "ThreeVarDynamicScope only supports scopes with three variables";
        switch (offset) {
            case 0: {
                return this.variableValueZero;
            }
            case 1: {
                return this.variableValueOne;
            }
            case 2: {
                return this.variableValueTwo;
            }
            default: {
                throw new RuntimeException("ThreeVarDynamicScope only supports scopes with three variables");
            }
        }
    }
    
    public IRubyObject getValueOrNil(final int offset, final int depth, final IRubyObject nil) {
        if (depth > 0) {
            return this.parent.getValueOrNil(offset, depth - 1, nil);
        }
        return this.getValueDepthZeroOrNil(offset, nil);
    }
    
    public IRubyObject getValueDepthZeroOrNil(final int offset, final IRubyObject nil) {
        assert offset < 3 : "ThreeVarDynamicScope only supports scopes with three variables";
        switch (offset) {
            case 0: {
                if (this.variableValueZero == null) {
                    return this.variableValueZero = nil;
                }
                return this.variableValueZero;
            }
            case 1: {
                if (this.variableValueOne == null) {
                    return this.variableValueOne = nil;
                }
                return this.variableValueOne;
            }
            case 2: {
                if (this.variableValueTwo == null) {
                    return this.variableValueTwo = nil;
                }
                return this.variableValueTwo;
            }
            default: {
                throw new RuntimeException("ThreeVarDynamicScope only supports scopes with three variables");
            }
        }
    }
    
    public IRubyObject getValueTwoDepthZeroOrNil(final IRubyObject nil) {
        if (this.variableValueTwo == null) {
            return this.variableValueTwo = nil;
        }
        return this.variableValueTwo;
    }
    
    public IRubyObject setValue(final int offset, final IRubyObject value, final int depth) {
        if (depth > 0) {
            assert this.parent != null : "If depth > 0, then parent should not ever be null";
            return this.parent.setValue(offset, value, depth - 1);
        }
        else {
            assert offset < 3 : "ThreeVarDynamicScope only supports scopes with three variables";
            switch (offset) {
                case 0: {
                    return this.variableValueZero = value;
                }
                case 1: {
                    return this.variableValueOne = value;
                }
                case 2: {
                    return this.variableValueTwo = value;
                }
                default: {
                    throw new RuntimeException("ThreeVarDynamicScope only supports scopes with three variables");
                }
            }
        }
    }
    
    public IRubyObject setValueDepthZero(final IRubyObject value, final int offset) {
        assert offset < 3 : "ThreeVarDynamicScope only supports scopes with three variables";
        switch (offset) {
            case 0: {
                return this.variableValueZero = value;
            }
            case 1: {
                return this.variableValueOne = value;
            }
            case 2: {
                return this.variableValueTwo = value;
            }
            default: {
                throw new RuntimeException("ThreeVarDynamicScope only supports scopes with three variables");
            }
        }
    }
    
    public IRubyObject setValueTwoDepthZero(final IRubyObject value) {
        return this.variableValueTwo = value;
    }
    
    public void setArgValues(final IRubyObject[] values, final int size) {
        assert size <= 3 : "ThreeVarDynamicScope only supports scopes with three variables, not " + size;
        switch (size) {
            case 3: {
                this.variableValueTwo = values[2];
            }
            case 2: {
                this.variableValueOne = values[1];
            }
            case 1: {
                this.variableValueZero = values[0];
                break;
            }
        }
    }
    
    public void setEndArgValues(final IRubyObject[] values, final int index, final int size) {
        assert size <= 2 : "ThreeVarDynamicScope only supports scopes with three variables, not " + size;
        switch (size) {
            case 3: {
                this.variableValueZero = values[values.length - 3];
            }
            case 2: {
                this.variableValueOne = values[values.length - 2];
            }
            case 1: {
                this.variableValueTwo = values[values.length - 1];
                break;
            }
        }
    }
    
    public void setArgValues(final IRubyObject arg0) {
        this.variableValueZero = arg0;
    }
    
    public void setArgValues(final IRubyObject arg0, final IRubyObject arg1) {
        this.variableValueZero = arg0;
        this.variableValueOne = arg1;
    }
    
    public void setArgValues(final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        this.variableValueZero = arg0;
        this.variableValueOne = arg1;
        this.variableValueTwo = arg2;
    }
    
    public IRubyObject[] getArgValues() {
        if (!this.staticScope.isArgumentScope()) {
            return this.parent.getArgValues();
        }
        final int totalArgs = this.staticScope.getRequiredArgs() + this.staticScope.getOptionalArgs();
        assert totalArgs <= 3 : "ThreeVarDynamicScope only supports scopes with three variables";
        if (this.staticScope.getRestArg() < 0) {
            switch (totalArgs) {
                case 0: {
                    return IRubyObject.NULL_ARRAY;
                }
                case 1: {
                    return new IRubyObject[] { this.variableValueZero };
                }
                case 2: {
                    return new IRubyObject[] { this.variableValueZero, this.variableValueOne };
                }
                case 3: {
                    return new IRubyObject[] { this.variableValueZero, this.variableValueOne, this.variableValueTwo };
                }
                default: {
                    throw new RuntimeException("more args requested than available variables");
                }
            }
        }
        else {
            final IRubyObject restArg = this.getValue(this.staticScope.getRestArg(), 0);
            assert restArg != null;
            final RubyArray splattedArgs = RuntimeHelpers.splatValue(restArg);
            final IRubyObject[] argValues = new IRubyObject[totalArgs + splattedArgs.size()];
            System.arraycopy(splattedArgs.toJavaArray(), 0, argValues, totalArgs, splattedArgs.size());
            switch (totalArgs) {
                case 3: {
                    argValues[2] = this.variableValueTwo;
                }
                case 2: {
                    argValues[1] = this.variableValueOne;
                }
                case 1: {
                    argValues[0] = this.variableValueZero;
                    break;
                }
            }
            return argValues;
        }
    }
}
