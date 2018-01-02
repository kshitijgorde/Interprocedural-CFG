// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.parser;

import org.jruby.runtime.scope.DummyDynamicScope;
import org.jruby.runtime.Arity;
import org.jruby.ast.AssignableNode;
import org.jruby.ast.Node;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.RubyObject;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import org.jruby.runtime.DynamicScope;
import org.jruby.RubyModule;
import java.io.Serializable;

public abstract class StaticScope implements Serializable
{
    private static final long serialVersionUID = 4843861446986961013L;
    protected final StaticScope enclosingScope;
    private transient RubyModule cref;
    private StaticScope previousCRefScope;
    private String[] variableNames;
    private boolean[] variableCaptured;
    private int requiredArgs;
    private int optionalArgs;
    private int restArg;
    private boolean isBackrefLastlineScope;
    private DynamicScope dummyScope;
    
    protected StaticScope(final StaticScope enclosingScope, final String[] names) {
        this.cref = null;
        this.previousCRefScope = null;
        this.requiredArgs = 0;
        this.optionalArgs = 0;
        this.restArg = -1;
        this.isBackrefLastlineScope = false;
        assert names != null : "names is not null";
        assert namesAreInterned(names);
        this.enclosingScope = enclosingScope;
        this.variableNames = names;
        this.variableCaptured = new boolean[this.variableNames.length];
    }
    
    private static boolean namesAreInterned(final String[] names) {
        for (final String name : names) {
            if (name != name.intern()) {
                return false;
            }
        }
        return true;
    }
    
    public int addVariableThisScope(final String name) {
        final int slot = this.exists(name);
        if (slot >= 0) {
            return slot;
        }
        this.growVariableNames(name);
        return this.variableNames.length - 1;
    }
    
    public int addVariable(final String name) {
        final int slot = this.isDefined(name);
        if (slot >= 0) {
            return slot;
        }
        this.growVariableNames(name);
        return this.variableNames.length - 1;
    }
    
    public String[] getVariables() {
        return this.variableNames;
    }
    
    public int getNumberOfVariables() {
        return this.variableNames.length;
    }
    
    public void setVariables(final String[] names) {
        assert names != null : "names is not null";
        System.arraycopy(names, 0, this.variableNames = new String[names.length], 0, names.length);
        this.variableCaptured = new boolean[this.variableNames.length];
    }
    
    public IRubyObject getConstantWithConstMissing(final Ruby runtime, final String internedName, final RubyModule object) {
        final IRubyObject result = this.getConstantInner(runtime, internedName, object);
        return (result == null) ? this.cref.fastGetConstant(internedName) : result;
    }
    
    public IRubyObject getConstant(final Ruby runtime, final String internedName, final RubyModule object) {
        final IRubyObject result = this.getConstantInner(runtime, internedName, object);
        return (result == null) ? this.cref.getConstantNoConstMissing(internedName) : result;
    }
    
    private IRubyObject getConstantInner(final Ruby runtime, final String internedName, final RubyModule object) {
        final IRubyObject result = this.cref.fastFetchConstant(internedName);
        if (result == null) {
            return (this.previousCRefScope == null) ? null : this.previousCRefScope.getConstantInnerNoObject(runtime, internedName, object);
        }
        if (result == RubyObject.UNDEF) {
            return this.getUndefConstant(runtime, internedName, object);
        }
        return result;
    }
    
    private IRubyObject getConstantInnerNoObject(final Ruby runtime, final String internedName, final RubyModule object) {
        if (this.previousCRefScope == null) {
            return null;
        }
        return this.getConstantInner(runtime, internedName, object);
    }
    
    private IRubyObject getUndefConstant(final Ruby runtime, final String internedName, final RubyModule object) {
        if (this.cref.resolveUndefConstant(runtime, internedName) == null) {
            return null;
        }
        return this.getConstantInner(runtime, internedName, object);
    }
    
    public StaticScope getEnclosingScope() {
        return this.enclosingScope;
    }
    
    public int exists(final String name) {
        return this.findVariableName(name);
    }
    
    private int findVariableName(final String name) {
        for (int i = 0; i < this.variableNames.length; ++i) {
            if (name == this.variableNames[i]) {
                return i;
            }
        }
        return -1;
    }
    
    public int isDefined(final String name) {
        return this.isDefined(name, 0);
    }
    
    public AssignableNode assign(final ISourcePosition position, final String name, final Node value) {
        return this.assign(position, name, value, this, 0);
    }
    
    public abstract String[] getAllNamesInScope();
    
    protected abstract int isDefined(final String p0, final int p1);
    
    protected abstract AssignableNode assign(final ISourcePosition p0, final String p1, final Node p2, final StaticScope p3, final int p4);
    
    protected abstract Node declare(final ISourcePosition p0, final String p1, final int p2);
    
    public Node declare(final ISourcePosition position, final String name) {
        return this.declare(position, name, 0);
    }
    
    public void capture(final int index) {
        this.variableCaptured[index] = true;
    }
    
    public boolean isCaptured(final int index) {
        return this.variableCaptured[index];
    }
    
    public abstract StaticScope getLocalScope();
    
    public RubyModule getModule() {
        return this.cref;
    }
    
    public StaticScope getPreviousCRefScope() {
        return this.previousCRefScope;
    }
    
    public void setPreviousCRefScope(final StaticScope crefScope) {
        this.previousCRefScope = crefScope;
    }
    
    public void setModule(final RubyModule module) {
        this.cref = module;
        if (this.previousCRefScope == null) {
            for (StaticScope scope = this.getEnclosingScope(); scope != null; scope = scope.getEnclosingScope()) {
                if (scope.cref != null) {
                    this.previousCRefScope = scope;
                    return;
                }
            }
        }
    }
    
    public RubyModule determineModule() {
        if (this.cref == null) {
            this.cref = this.getEnclosingScope().determineModule();
            assert this.cref != null : "CRef is always created before determine happens";
            this.previousCRefScope = this.getEnclosingScope().previousCRefScope;
        }
        return this.cref;
    }
    
    public int getOptionalArgs() {
        return this.optionalArgs;
    }
    
    public int getRequiredArgs() {
        return this.requiredArgs;
    }
    
    public void setRequiredArgs(final int requiredArgs) {
        this.requiredArgs = requiredArgs;
    }
    
    public int getRestArg() {
        return this.restArg;
    }
    
    public void setRestArg(final int restArg) {
        this.restArg = restArg;
    }
    
    public abstract boolean isArgumentScope();
    
    public abstract void makeArgumentScope();
    
    public boolean isBackrefLastlineScope() {
        return this.isBackrefLastlineScope;
    }
    
    public void setBackrefLastlineScope(final boolean isBackrefLastlineScope) {
        this.isBackrefLastlineScope = isBackrefLastlineScope;
    }
    
    public Arity getArity() {
        if (this.optionalArgs > 0) {
            if (this.restArg >= 0) {
                return Arity.optional();
            }
            return Arity.required(this.requiredArgs);
        }
        else {
            if (this.restArg >= 0) {
                return Arity.optional();
            }
            return Arity.fixed(this.requiredArgs);
        }
    }
    
    public void setArities(final int required, final int optional, final int rest) {
        this.requiredArgs = required;
        this.optionalArgs = optional;
        this.restArg = rest;
    }
    
    public DynamicScope getDummyScope() {
        return (this.dummyScope == null) ? (this.dummyScope = new DummyDynamicScope(this)) : this.dummyScope;
    }
    
    private void growVariableNames(final String name) {
        final String[] newVariableNames = new String[this.variableNames.length + 1];
        System.arraycopy(this.variableNames, 0, newVariableNames, 0, this.variableNames.length);
        (this.variableNames = newVariableNames)[this.variableNames.length - 1] = name;
        final boolean[] newVariableCaptured = new boolean[this.variableCaptured.length + 1];
        System.arraycopy(this.variableCaptured, 0, newVariableCaptured, 0, this.variableCaptured.length);
        this.variableCaptured = newVariableCaptured;
    }
    
    public String toString() {
        final StringBuilder buf = new StringBuilder("[");
        for (int i = 0; i < this.variableNames.length - 1; ++i) {
            buf.append(this.variableNames[i]).append(", ");
        }
        if (this.variableNames.length > 0) {
            buf.append(this.variableNames[this.variableNames.length - 1]);
        }
        buf.append("]");
        return buf.toString();
    }
}
