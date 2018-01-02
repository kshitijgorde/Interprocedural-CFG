// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.util.Collection;
import org.jruby.runtime.builtin.Variable;
import java.util.List;
import org.jruby.internal.runtime.methods.DynamicMethod;
import java.util.Map;
import org.jruby.runtime.builtin.IRubyObject;

public final class IncludedModuleWrapper extends RubyClass
{
    private final RubyModule delegate;
    
    public IncludedModuleWrapper(final Ruby runtime, final RubyClass superClass, final RubyModule delegate) {
        super(runtime, superClass, false);
        this.delegate = delegate;
        this.metaClass = delegate.metaClass;
        delegate.addIncludingHierarchy(this);
    }
    
    @Deprecated
    public IncludedModuleWrapper newIncludeClass(final RubyClass superClass) {
        final IncludedModuleWrapper includedModule = new IncludedModuleWrapper(this.getRuntime(), superClass, this.getNonIncludedClass());
        if (this.getSuperClass() != null) {
            includedModule.includeModule(this.getSuperClass());
        }
        return includedModule;
    }
    
    public boolean isModule() {
        return false;
    }
    
    public boolean isClass() {
        return false;
    }
    
    public boolean isIncluded() {
        return true;
    }
    
    public boolean isImmediate() {
        return true;
    }
    
    public void setMetaClass(final RubyClass newRubyClass) {
        throw new UnsupportedOperationException("An included class is only a wrapper for a module");
    }
    
    public Map<String, DynamicMethod> getMethods() {
        return this.delegate.getMethods();
    }
    
    public Map<String, DynamicMethod> getMethodsForWrite() {
        return this.delegate.getMethodsForWrite();
    }
    
    public void addMethod(final String name, final DynamicMethod method) {
        throw new UnsupportedOperationException("An included class is only a wrapper for a module");
    }
    
    public void setMethods(final Map newMethods) {
        throw new UnsupportedOperationException("An included class is only a wrapper for a module");
    }
    
    public String getName() {
        return this.delegate.getName();
    }
    
    public RubyModule getNonIncludedClass() {
        return this.delegate;
    }
    
    public RubyClass getRealClass() {
        if (this.superClass == null) {
            return null;
        }
        return this.superClass.getRealClass();
    }
    
    protected boolean isSame(final RubyModule module) {
        return this.delegate.isSame(module);
    }
    
    public IRubyObject id() {
        return this.delegate.id();
    }
    
    protected synchronized Map<String, IRubyObject> getClassVariables() {
        return this.delegate.getClassVariables();
    }
    
    protected Map<String, IRubyObject> getClassVariablesForRead() {
        return this.delegate.getClassVariablesForRead();
    }
    
    protected boolean variableTableContains(final String name) {
        return this.delegate.variableTableContains(name);
    }
    
    protected boolean variableTableFastContains(final String internedName) {
        return this.delegate.variableTableFastContains(internedName);
    }
    
    protected Object variableTableFetch(final String name) {
        return this.delegate.variableTableFetch(name);
    }
    
    protected Object variableTableFastFetch(final String internedName) {
        return this.delegate.variableTableFastFetch(internedName);
    }
    
    protected Object variableTableStore(final String name, final Object value) {
        return this.delegate.variableTableStore(name, value);
    }
    
    protected Object variableTableFastStore(final String internedName, final Object value) {
        return this.delegate.variableTableFastStore(internedName, value);
    }
    
    protected Object variableTableRemove(final String name) {
        return this.delegate.variableTableRemove(name);
    }
    
    protected void variableTableSync(final List<Variable<Object>> vars) {
        this.delegate.variableTableSync(vars);
    }
    
    protected boolean constantTableContains(final String name) {
        return this.delegate.constantTableContains(name);
    }
    
    protected boolean constantTableFastContains(final String internedName) {
        return this.delegate.constantTableFastContains(internedName);
    }
    
    protected IRubyObject constantTableFetch(final String name) {
        return this.delegate.constantTableFetch(name);
    }
    
    protected IRubyObject constantTableFastFetch(final String internedName) {
        return this.delegate.constantTableFastFetch(internedName);
    }
    
    protected IRubyObject constantTableStore(final String name, final IRubyObject value) {
        return this.delegate.constantTableStore(name, value);
    }
    
    protected IRubyObject constantTableFastStore(final String internedName, final IRubyObject value) {
        return this.delegate.constantTableFastStore(internedName, value);
    }
    
    protected IRubyObject constantTableRemove(final String name) {
        return this.delegate.constantTableRemove(name);
    }
    
    @Deprecated
    public List<String> getStoredConstantNameList() {
        return this.delegate.getStoredConstantNameList();
    }
    
    public Collection<String> getConstantNames() {
        return this.delegate.getConstantNames();
    }
}
