// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.parser.BlockStaticScope;
import org.jruby.Ruby;
import org.jruby.parser.EvalStaticScope;
import org.jruby.runtime.scope.DummyDynamicScope;
import org.jruby.runtime.scope.ManyVarsDynamicScope;
import org.jruby.runtime.scope.FourVarDynamicScope;
import org.jruby.runtime.scope.ThreeVarDynamicScope;
import org.jruby.runtime.scope.TwoVarDynamicScope;
import org.jruby.runtime.scope.OneVarDynamicScope;
import org.jruby.runtime.scope.NoVarsDynamicScope;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.parser.StaticScope;

public abstract class DynamicScope
{
    protected final StaticScope staticScope;
    protected final DynamicScope parent;
    private IRubyObject backref;
    private IRubyObject lastline;
    protected DynamicScope evalScope;
    
    protected DynamicScope(final StaticScope staticScope, final DynamicScope parent) {
        this.staticScope = staticScope;
        this.parent = parent;
    }
    
    protected DynamicScope(final StaticScope staticScope) {
        this(staticScope, null);
    }
    
    public static DynamicScope newDynamicScope(final StaticScope staticScope, final DynamicScope parent) {
        switch (staticScope.getNumberOfVariables()) {
            case 0: {
                return new NoVarsDynamicScope(staticScope, parent);
            }
            case 1: {
                return new OneVarDynamicScope(staticScope, parent);
            }
            case 2: {
                return new TwoVarDynamicScope(staticScope, parent);
            }
            case 3: {
                return new ThreeVarDynamicScope(staticScope, parent);
            }
            case 4: {
                return new FourVarDynamicScope(staticScope, parent);
            }
            default: {
                return new ManyVarsDynamicScope(staticScope, parent);
            }
        }
    }
    
    public static DynamicScope newDummyScope(final StaticScope staticScope, final DynamicScope parent) {
        return new DummyDynamicScope(staticScope, parent);
    }
    
    public DynamicScope getNthParentScope(final int n) {
        DynamicScope scope = this;
        for (int i = 0; i < n && scope != null; scope = scope.getNextCapturedScope(), ++i) {}
        return scope;
    }
    
    public static DynamicScope newDynamicScope(final StaticScope staticScope) {
        return newDynamicScope(staticScope, null);
    }
    
    public final DynamicScope getEvalScope() {
        if (this.evalScope == null) {
            final DynamicScope parent = this.getNextCapturedScope();
            if (parent != null && parent.getEvalScope() == this) {
                this.evalScope = this;
            }
            else {
                this.evalScope = new ManyVarsDynamicScope(new EvalStaticScope(this.getStaticScope()), this);
            }
        }
        return this.evalScope;
    }
    
    public DynamicScope getFlipScope() {
        if (this.staticScope.getLocalScope() == this.staticScope) {
            return this;
        }
        return this.parent.getFlipScope();
    }
    
    public final DynamicScope getNextCapturedScope() {
        return this.parent;
    }
    
    public final StaticScope getStaticScope() {
        return this.staticScope;
    }
    
    public final String[] getAllNamesInScope() {
        return this.staticScope.getAllNamesInScope();
    }
    
    public IRubyObject getBackRef(final Ruby runtime) {
        if (!this.staticScope.isBackrefLastlineScope()) {
            return this.parent.getBackRef(runtime);
        }
        return (this.backref == null) ? runtime.getNil() : this.backref;
    }
    
    public IRubyObject setBackRef(final IRubyObject backref) {
        if (!this.staticScope.isBackrefLastlineScope()) {
            return this.parent.setBackRef(backref);
        }
        return this.backref = backref;
    }
    
    public IRubyObject getLastLine(final Ruby runtime) {
        if (!this.staticScope.isBackrefLastlineScope()) {
            return this.parent.getLastLine(runtime);
        }
        return (this.lastline == null) ? runtime.getNil() : this.lastline;
    }
    
    public IRubyObject setLastLine(final IRubyObject lastline) {
        if (!this.staticScope.isBackrefLastlineScope()) {
            return this.parent.setLastLine(lastline);
        }
        return this.lastline = lastline;
    }
    
    public abstract void growIfNeeded();
    
    public abstract DynamicScope cloneScope();
    
    public abstract IRubyObject[] getValues();
    
    public abstract IRubyObject getValue(final int p0, final int p1);
    
    public abstract IRubyObject getValueOrNil(final int p0, final int p1, final IRubyObject p2);
    
    public abstract IRubyObject getValueDepthZeroOrNil(final int p0, final IRubyObject p1);
    
    public abstract IRubyObject getValueZeroDepthZeroOrNil(final IRubyObject p0);
    
    public abstract IRubyObject getValueOneDepthZeroOrNil(final IRubyObject p0);
    
    public abstract IRubyObject getValueTwoDepthZeroOrNil(final IRubyObject p0);
    
    public abstract IRubyObject getValueThreeDepthZeroOrNil(final IRubyObject p0);
    
    public abstract IRubyObject setValue(final int p0, final IRubyObject p1, final int p2);
    
    public IRubyObject setValue(final IRubyObject value, final int offset, final int depth) {
        return this.setValue(offset, value, depth);
    }
    
    public abstract IRubyObject setValueDepthZero(final IRubyObject p0, final int p1);
    
    public abstract IRubyObject setValueZeroDepthZero(final IRubyObject p0);
    
    public abstract IRubyObject setValueOneDepthZero(final IRubyObject p0);
    
    public abstract IRubyObject setValueTwoDepthZero(final IRubyObject p0);
    
    public abstract IRubyObject setValueThreeDepthZero(final IRubyObject p0);
    
    public abstract void setArgValues(final IRubyObject[] p0, final int p1);
    
    public void setArgValues() {
        this.setArgValues(IRubyObject.NULL_ARRAY, 0);
    }
    
    public void setArgValues(final IRubyObject arg0) {
        this.setArgValues(new IRubyObject[] { arg0 }, 1);
    }
    
    public void setArgValues(final IRubyObject arg0, final IRubyObject arg1) {
        this.setArgValues(new IRubyObject[] { arg0, arg1 }, 2);
    }
    
    public void setArgValues(final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        this.setArgValues(new IRubyObject[] { arg0, arg1, arg2 }, 3);
    }
    
    public void setArgValues(final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        this.setArgValues(new IRubyObject[] { arg0, arg1, arg2, arg3 }, 4);
    }
    
    public void setArgValues(final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4) {
        this.setArgValues(new IRubyObject[] { arg0, arg1, arg2, arg3, arg4 }, 5);
    }
    
    public void setArgValues(final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5) {
        this.setArgValues(new IRubyObject[] { arg0, arg1, arg2, arg3, arg4, arg5 }, 6);
    }
    
    public void setArgValues(final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6) {
        this.setArgValues(new IRubyObject[] { arg0, arg1, arg2, arg3, arg4, arg5, arg6 }, 7);
    }
    
    public void setArgValues(final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7) {
        this.setArgValues(new IRubyObject[] { arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7 }, 8);
    }
    
    public void setArgValues(final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final IRubyObject arg8) {
        this.setArgValues(new IRubyObject[] { arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8 }, 9);
    }
    
    public void setArgValues(final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final IRubyObject arg8, final IRubyObject arg9) {
        this.setArgValues(new IRubyObject[] { arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9 }, 10);
    }
    
    public abstract void setEndArgValues(final IRubyObject[] p0, final int p1, final int p2);
    
    public abstract IRubyObject[] getArgValues();
    
    public String toString() {
        return this.toString(new StringBuffer(), "");
    }
    
    public String toString(final StringBuffer buf, final String indent) {
        buf.append(indent).append("Static Type[" + this.hashCode() + "]: " + ((this.staticScope instanceof BlockStaticScope) ? "block" : "local") + " [");
        final int size = this.staticScope.getNumberOfVariables();
        final IRubyObject[] variableValues = this.getValues();
        if (size != 0) {
            final String[] names = this.staticScope.getVariables();
            for (int i = 0; i < size - 1; ++i) {
                buf.append(names[i]).append("=");
                if (variableValues[i] == null) {
                    buf.append("null");
                }
                else {
                    buf.append(variableValues[i]);
                }
                buf.append(",");
            }
            buf.append(names[size - 1]).append("=");
            assert variableValues.length == names.length : "V: " + variableValues.length + " != N: " + names.length + " for " + (Object)buf;
            if (variableValues[size - 1] == null) {
                buf.append("null");
            }
            else {
                buf.append(variableValues[size - 1]);
            }
        }
        buf.append("]");
        if (this.parent != null) {
            buf.append("\n");
            this.parent.toString(buf, indent + "  ");
        }
        return buf.toString();
    }
}
