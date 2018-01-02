// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.variable;

import java.util.Iterator;
import java.util.List;
import org.jruby.runtime.builtin.InstanceVariables;
import org.jruby.embed.internal.BiVariableMap;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyObject;

public class InstanceVariable extends AbstractVariable
{
    private static String pattern;
    
    public static BiVariable getInstance(final RubyObject receiver, final String name, final Object... javaObject) {
        if (name.matches(InstanceVariable.pattern)) {
            return new InstanceVariable(receiver, name, javaObject);
        }
        return null;
    }
    
    private InstanceVariable(final RubyObject receiver, final String name, final Object... javaObjects) {
        super(receiver, name, false);
        this.updateByJavaObject(receiver.getRuntime(), javaObjects);
    }
    
    public InstanceVariable(final IRubyObject receiver, final String name, final IRubyObject irubyObject) {
        super(receiver, name, true, irubyObject);
    }
    
    public static void retrieve(final RubyObject receiver, final BiVariableMap vars) {
        if (vars.isLazy()) {
            return;
        }
        updateInstanceVar(receiver, vars);
        updateInstanceVar((RubyObject)receiver.getRuntime().getTopSelf(), vars);
    }
    
    static void updateInstanceVar(final RubyObject receiver, final BiVariableMap vars) {
        final InstanceVariables ivars = receiver.getInstanceVariables();
        final List<String> keys = ivars.getInstanceVariableNameList();
        for (final String key : keys) {
            final IRubyObject value = ivars.fastGetInstanceVariable(key);
            BiVariable var = vars.getVariable(receiver, key);
            if (var != null) {
                var.setRubyObject(value);
            }
            else {
                var = new InstanceVariable((IRubyObject)receiver, key, value);
                vars.update(key, var);
            }
        }
    }
    
    public static void retrieveByKey(final RubyObject receiver, final BiVariableMap vars, final String key) {
        final InstanceVariables ivars = receiver.getInstanceVariables();
        if (!ivars.getInstanceVariableNameList().contains(key)) {
            return;
        }
        final IRubyObject value = ivars.fastGetInstanceVariable(key);
        BiVariable var = vars.getVariable(receiver, key);
        if (var != null) {
            var.setRubyObject(value);
        }
        else {
            var = new InstanceVariable((IRubyObject)receiver, key, value);
            vars.update(key, var);
        }
    }
    
    public BiVariable.Type getType() {
        return BiVariable.Type.InstanceVariable;
    }
    
    public static boolean isValidName(final Object name) {
        return AbstractVariable.isValidName(InstanceVariable.pattern, name);
    }
    
    public void inject() {
        ((RubyObject)this.receiver).fastSetInstanceVariable(this.name.intern(), this.getRubyObject());
    }
    
    public void remove() {
        ((RubyObject)this.receiver).removeInstanceVariable(this.name);
    }
    
    static {
        InstanceVariable.pattern = "@([a-zA-Z]|_)([a-zA-Z]|_|\\d)*";
    }
}
