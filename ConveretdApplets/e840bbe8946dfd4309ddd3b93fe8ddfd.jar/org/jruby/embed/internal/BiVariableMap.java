// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.jruby.runtime.scope.ManyVarsDynamicScope;
import org.jruby.RubyObject;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.embed.variable.VariableInterceptor;
import java.util.Iterator;
import java.util.HashMap;
import org.jruby.embed.LocalVariableBehavior;
import java.util.ArrayList;
import org.jruby.embed.variable.BiVariable;
import java.util.List;
import java.util.Map;

public class BiVariableMap<K, V> implements Map<K, V>
{
    private final LocalContextProvider provider;
    private final List<String> varNames;
    private final List<BiVariable> variables;
    private boolean lazy;
    
    public BiVariableMap(final LocalContextProvider provider, final boolean lazy) {
        this.varNames = new ArrayList<String>();
        this.variables = new ArrayList<BiVariable>();
        this.provider = provider;
        this.lazy = lazy;
    }
    
    public List<String> getNames() {
        return this.varNames;
    }
    
    public List<BiVariable> getVariables() {
        return this.variables;
    }
    
    public LocalVariableBehavior getLocalVariableBehavior() {
        return this.provider.getLocalVariableBehavior();
    }
    
    public Map getMap() {
        final Map m = new HashMap();
        for (final BiVariable v : this.variables) {
            m.put(v.getName(), v.getJavaObject());
        }
        return m;
    }
    
    public int size() {
        return this.varNames.size();
    }
    
    public boolean isEmpty() {
        return this.varNames.isEmpty();
    }
    
    private void checkKey(final Object key) {
        if (key == null) {
            throw new NullPointerException("key is null");
        }
        if (!(key instanceof String)) {
            throw new ClassCastException("key is NOT String");
        }
        if (((String)key).length() == 0) {
            throw new IllegalArgumentException("key is empty");
        }
    }
    
    public boolean containsKey(final Object key) {
        this.checkKey(key);
        return this.varNames.contains(key);
    }
    
    public boolean containsValue(final Object value) {
        for (final BiVariable v : this.variables) {
            if (value == v.getJavaObject()) {
                return true;
            }
        }
        return false;
    }
    
    public V get(final Object key) {
        return this.get(this.provider.getRuntime().getTopSelf(), key);
    }
    
    public V get(final Object receiver, final Object key) {
        this.checkKey(key);
        final RubyObject robj = this.getReceiverObject(receiver);
        if (this.lazy) {
            VariableInterceptor.tryLazyRetrieval(this.provider.getLocalVariableBehavior(), this, robj, key);
        }
        final BiVariable var = this.getVariable(robj, (String)key);
        if (var == null) {
            return null;
        }
        return (V)var.getJavaObject();
    }
    
    private RubyObject getReceiverObject(final Object receiver) {
        if (receiver == null || !(receiver instanceof IRubyObject)) {
            return (RubyObject)this.provider.getRuntime().getTopSelf();
        }
        if (receiver instanceof RubyObject) {
            return (RubyObject)receiver;
        }
        return (RubyObject)((IRubyObject)receiver).getRuntime().getTopSelf();
    }
    
    @Deprecated
    public BiVariable getVariable(final String key) {
        return this.getVariable((RubyObject)this.provider.getRuntime().getTopSelf(), key);
    }
    
    public BiVariable getVariable(final RubyObject receiver, final String key) {
        for (int i = 0; i < this.varNames.size(); ++i) {
            if (key.equals(this.varNames.get(i))) {
                BiVariable var = null;
                while (var == null) {
                    try {
                        var = this.variables.get(i);
                    }
                    catch (Exception e) {
                        var = null;
                    }
                }
                if (var.isReceiverIdentical(receiver)) {
                    return var;
                }
            }
        }
        return null;
    }
    
    @Deprecated
    public void setVariable(final BiVariable var) {
        this.setVariable((RubyObject)this.provider.getRuntime().getTopSelf(), var);
    }
    
    public void setVariable(final RubyObject receiver, final BiVariable var) {
        if (var == null) {
            return;
        }
        final String key = var.getName();
        final BiVariable old = this.getVariable(receiver, key);
        if (old != null) {
            old.setJavaObject(receiver.getRuntime(), var.getJavaObject());
        }
        else {
            this.update(key, var);
        }
    }
    
    public V put(final K key, final V value) {
        return this.put(this.provider.getRuntime().getTopSelf(), key, value);
    }
    
    public V put(final Object receiver, final K key, final V value) {
        this.checkKey(key);
        final RubyObject robj = this.getReceiverObject(receiver);
        final String name = ((String)key).intern();
        BiVariable v = this.getVariable(robj, name);
        Object oldValue = null;
        if (v != null) {
            oldValue = v.getJavaObject();
            v.setJavaObject(robj.getRuntime(), value);
        }
        else {
            v = VariableInterceptor.getVariableInstance(this.provider.getLocalVariableBehavior(), robj, name, value);
            if (v != null) {
                this.update(name, v);
            }
        }
        return (V)oldValue;
    }
    
    public String[] getLocalVarNames() {
        final List<String> localVarNames = new ArrayList<String>();
        for (final BiVariable v : this.variables) {
            if (v.getType() == BiVariable.Type.LocalVariable) {
                localVarNames.add(v.getName());
            }
        }
        if (localVarNames.size() > 0) {
            return localVarNames.toArray(new String[localVarNames.size()]);
        }
        return null;
    }
    
    public IRubyObject[] getLocalVarValues() {
        final List<IRubyObject> localVarValues = new ArrayList<IRubyObject>();
        for (final BiVariable v : this.variables) {
            if (v.getType() == BiVariable.Type.LocalVariable) {
                localVarValues.add(v.getRubyObject());
            }
        }
        if (localVarValues.size() > 0) {
            return localVarValues.toArray(new IRubyObject[localVarValues.size()]);
        }
        return null;
    }
    
    void inject(final ManyVarsDynamicScope scope, final int depth, final IRubyObject receiver) {
        VariableInterceptor.inject(this, this.provider.getRuntime(), scope, depth, receiver);
    }
    
    void retrieve(final IRubyObject receiver) {
        final RubyObject robj = this.getReceiverObject(receiver);
        VariableInterceptor.retrieve(this.provider.getLocalVariableBehavior(), this, robj);
    }
    
    void terminate() {
        VariableInterceptor.terminateGlobalVariables(this.provider.getLocalVariableBehavior(), this.variables, this.provider.getRuntime());
        VariableInterceptor.terminateLocalVariables(this.provider.getLocalVariableBehavior(), this.varNames, this.variables);
    }
    
    public V remove(final Object key) {
        return this.remove((Object)this.provider.getRuntime().getTopSelf(), key);
    }
    
    public V remove(final Object receiver, final Object key) {
        this.checkKey(key);
        final RubyObject robj = this.getReceiverObject(receiver);
        final String name = ((String)key).intern();
        for (int i = 0; i < this.varNames.size(); ++i) {
            if (name.equals(this.varNames.get(i))) {
                final BiVariable var = this.variables.get(i);
                if (var.getReceiver() == robj) {
                    this.varNames.remove(i);
                    final BiVariable v = this.variables.remove(i);
                    v.remove();
                    return (V)v.getJavaObject();
                }
            }
        }
        return null;
    }
    
    public void putAll(final Map<? extends K, ? extends V> t) {
        if (t == null) {
            throw new NullPointerException("map is null");
        }
        if (t.isEmpty()) {
            throw new IllegalArgumentException("map is empty");
        }
        final Set set = t.entrySet();
        for (final Entry entry : set) {
            if (!(entry.getKey() instanceof String)) {
                throw new ClassCastException("key is NOT String");
            }
            final K key = entry.getKey();
            final V value = entry.getValue();
            this.put(key, value);
        }
    }
    
    public void clear() {
        this.varNames.clear();
        for (final BiVariable v : this.variables) {
            if (v != null) {
                v.remove();
            }
        }
        this.variables.clear();
    }
    
    public Set keySet() {
        if (this.varNames.isEmpty()) {
            return null;
        }
        final Set s = new HashSet();
        for (final String name : this.varNames) {
            s.add(name);
        }
        return s;
    }
    
    public Collection values() {
        if (this.varNames.isEmpty()) {
            return null;
        }
        final List l = new ArrayList();
        for (final BiVariable v : this.variables) {
            l.add(v.getJavaObject());
        }
        return l;
    }
    
    public Set entrySet() {
        if (this.varNames.isEmpty()) {
            return null;
        }
        return this.getMap().entrySet();
    }
    
    public void update(final String name, final BiVariable value) {
        this.varNames.add(name);
        this.variables.add(value);
    }
    
    public boolean isLazy() {
        return this.lazy;
    }
}
