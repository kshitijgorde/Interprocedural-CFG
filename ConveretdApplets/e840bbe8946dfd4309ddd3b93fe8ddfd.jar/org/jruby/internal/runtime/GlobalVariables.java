// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime;

import java.util.Set;
import org.jruby.RubyProc;
import org.jruby.common.IRubyWarnings;
import org.jruby.exceptions.RaiseException;
import org.jruby.runtime.IAccessor;
import java.util.concurrent.ConcurrentHashMap;
import org.jruby.runtime.builtin.IRubyObject;
import java.util.Map;
import org.jruby.Ruby;

public class GlobalVariables
{
    private Ruby runtime;
    private Map<String, GlobalVariable> globalVariables;
    private IRubyObject defaultSeparator;
    
    public GlobalVariables(final Ruby runtime) {
        this.globalVariables = new ConcurrentHashMap<String, GlobalVariable>();
        this.runtime = runtime;
    }
    
    public void define(final String name, final IAccessor accessor) {
        assert name != null;
        assert accessor != null;
        assert name.startsWith("$");
        this.globalVariables.put(name, new GlobalVariable(accessor));
    }
    
    public void defineReadonly(final String name, final IAccessor accessor) {
        assert name != null;
        assert accessor != null;
        assert name.startsWith("$");
        this.globalVariables.put(name, new GlobalVariable(new ReadonlyAccessor(name, accessor)));
    }
    
    public boolean isDefined(final String name) {
        assert name != null;
        assert name.startsWith("$");
        final GlobalVariable variable = this.globalVariables.get(name);
        return variable != null && !(variable.getAccessor() instanceof UndefinedAccessor);
    }
    
    public void alias(final String name, final String oldName) {
        assert name != null;
        assert oldName != null;
        assert name.startsWith("$");
        assert oldName.startsWith("$");
        if (this.runtime.getSafeLevel() >= 4) {
            throw this.runtime.newSecurityError("Insecure: can't alias global variable");
        }
        final GlobalVariable oldVariable = this.createIfNotDefined(oldName);
        final GlobalVariable variable = this.globalVariables.get(name);
        if (variable != null && oldVariable != variable && variable.isTracing()) {
            throw new RaiseException(this.runtime, this.runtime.getRuntimeError(), "can't alias in tracer", false);
        }
        this.globalVariables.put(name, oldVariable);
    }
    
    public IRubyObject get(final String name) {
        assert name != null;
        assert name.startsWith("$");
        final GlobalVariable variable = this.globalVariables.get(name);
        if (variable != null) {
            return variable.getAccessor().getValue();
        }
        if (this.runtime.isVerbose()) {
            this.runtime.getWarnings().warning(IRubyWarnings.ID.GLOBAL_NOT_INITIALIZED, "global variable `" + name + "' not initialized", name);
        }
        return this.runtime.getNil();
    }
    
    public IRubyObject set(final String name, final IRubyObject value) {
        assert name != null;
        assert name.startsWith("$");
        if (this.runtime.getSafeLevel() >= 4) {
            throw this.runtime.newSecurityError("Insecure: can't change global variable value");
        }
        final GlobalVariable variable = this.createIfNotDefined(name);
        final IRubyObject result = variable.getAccessor().setValue(value);
        variable.trace(value);
        return result;
    }
    
    public IRubyObject clear(final String name) {
        return this.set(name, this.runtime.getNil());
    }
    
    public void setTraceVar(final String name, final RubyProc proc) {
        assert name != null;
        assert name.startsWith("$");
        final GlobalVariable variable = this.createIfNotDefined(name);
        variable.addTrace(proc);
    }
    
    public boolean untraceVar(final String name, final IRubyObject command) {
        assert name != null;
        assert name.startsWith("$");
        if (this.isDefined(name)) {
            final GlobalVariable variable = this.globalVariables.get(name);
            return variable.removeTrace(command);
        }
        return false;
    }
    
    public void untraceVar(final String name) {
        assert name != null;
        assert name.startsWith("$");
        if (this.isDefined(name)) {
            final GlobalVariable variable = this.globalVariables.get(name);
            variable.removeTraces();
        }
    }
    
    public Set<String> getNames() {
        return this.globalVariables.keySet();
    }
    
    private GlobalVariable createIfNotDefined(final String name) {
        GlobalVariable variable = this.globalVariables.get(name);
        if (variable == null) {
            variable = GlobalVariable.newUndefined(this.runtime, name);
            this.globalVariables.put(name, variable);
        }
        return variable;
    }
    
    public IRubyObject getDefaultSeparator() {
        return this.defaultSeparator;
    }
    
    public void setDefaultSeparator(final IRubyObject defaultSeparator) {
        this.defaultSeparator = defaultSeparator;
    }
}
