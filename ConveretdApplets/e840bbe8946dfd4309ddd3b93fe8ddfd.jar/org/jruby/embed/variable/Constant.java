// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.variable;

import org.jruby.javasupport.JavaUtil;
import org.jruby.RubyModule;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jruby.embed.internal.BiVariableMap;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyObject;

public class Constant extends AbstractVariable
{
    private static String pattern;
    private boolean initialized;
    
    public static BiVariable getInstance(final RubyObject receiver, final String name, final Object... javaObject) {
        if (name.matches(Constant.pattern)) {
            return new Constant(receiver, name, javaObject);
        }
        return null;
    }
    
    private Constant(final RubyObject receiver, final String name, final Object... javaObjects) {
        super(receiver, name, false);
        this.initialized = false;
        this.updateByJavaObject(receiver.getRuntime(), javaObjects);
    }
    
    Constant(final IRubyObject receiver, final String name, final IRubyObject irubyObject) {
        super(receiver, name, true, irubyObject);
        this.initialized = false;
    }
    
    void markInitialized() {
        this.initialized = true;
    }
    
    public static void retrieve(final RubyObject receiver, final BiVariableMap vars) {
        if (vars.isLazy()) {
            return;
        }
        updateConstantsOfSuperClass(receiver, vars);
        updateConstants(receiver, vars);
        final RubyObject topSelf = (RubyObject)receiver.getRuntime().getTopSelf();
        updateConstants(topSelf, vars);
    }
    
    private static void updateConstantsOfSuperClass(final RubyObject receiver, final BiVariableMap vars) {
        final Map<String, IRubyObject> map = receiver.getRuntime().getTopSelf().getMetaClass().getSuperClass().getConstantMap();
        final List<BiVariable> variables = (List<BiVariable>)vars.getVariables();
        for (final BiVariable variable : variables) {
            if (variable.getType() == BiVariable.Type.Constant && map.containsKey(variable.getName())) {
                final IRubyObject value = map.get(variable.getName());
                variable.setRubyObject(value);
            }
        }
    }
    
    private static void updateConstants(final RubyObject receiver, final BiVariableMap vars) {
        final Collection<String> names = receiver.getMetaClass().getConstantNames();
        for (final String name : names) {
            final IRubyObject value = receiver.getMetaClass().getConstant(name);
            BiVariable var = null;
            final List<String> savedNames = (List<String>)vars.getNames();
            for (int i = 0; i < savedNames.size(); ++i) {
                if (name.equals(savedNames.get(i))) {
                    var = (BiVariable)vars.getVariables().get(i);
                    if (receiver == var.getReceiver()) {
                        var.setRubyObject(value);
                    }
                    else {
                        var = null;
                    }
                }
            }
            if (var == null) {
                var = new Constant((IRubyObject)receiver, name, value);
                ((Constant)var).markInitialized();
                vars.update(name, var);
            }
        }
    }
    
    public static void retrieveByKey(final RubyObject receiver, final BiVariableMap vars, final String key) {
        IRubyObject value = null;
        if (receiver.getMetaClass().getConstantNames().contains(key)) {
            value = receiver.getMetaClass().getConstant(key);
        }
        else if (receiver.getRuntime().getTopSelf().getMetaClass().getConstantNames().contains(key)) {
            value = receiver.getRuntime().getTopSelf().getMetaClass().getConstant(key);
        }
        else if (receiver.getRuntime().getTopSelf().getMetaClass().getSuperClass().getConstantNames().contains(key)) {
            value = receiver.getRuntime().getTopSelf().getMetaClass().getSuperClass().getConstant(key);
        }
        if (value == null) {
            return;
        }
        BiVariable var = vars.getVariable(receiver, key);
        if (var != null) {
            var.setRubyObject(value);
        }
        else {
            var = new Constant((IRubyObject)receiver, key, value);
            vars.update(key, var);
        }
    }
    
    public BiVariable.Type getType() {
        return BiVariable.Type.Constant;
    }
    
    public static boolean isValidName(final Object name) {
        return AbstractVariable.isValidName(Constant.pattern, name);
    }
    
    public void inject() {
        if (this.receiver == this.receiver.getRuntime().getTopSelf()) {
            RubyModule rubyModule = this.getRubyClass(this.receiver.getRuntime());
            if (rubyModule == null) {
                rubyModule = this.receiver.getRuntime().getCurrentContext().getRubyClass();
            }
            if (rubyModule == null) {
                return;
            }
            rubyModule.storeConstant(this.name, this.irubyObject);
            this.receiver.getRuntime().incrementConstantGeneration();
        }
        else {
            this.receiver.getMetaClass().storeConstant(this.name, this.irubyObject);
        }
        this.initialized = true;
    }
    
    public void remove() {
        final IRubyObject rubyName = JavaUtil.convertJavaToRuby(this.receiver.getRuntime(), this.name);
        if (this.receiver.getMetaClass().getConstantNames().contains(this.name)) {
            this.receiver.getMetaClass().remove_const(this.receiver.getRuntime().getCurrentContext(), rubyName);
        }
        else if (this.receiver.getRuntime().getTopSelf().getMetaClass().getConstantNames().contains(this.name)) {
            this.receiver.getRuntime().getTopSelf().getMetaClass().remove_const(this.receiver.getRuntime().getCurrentContext(), rubyName);
        }
        else if (this.receiver.getRuntime().getTopSelf().getMetaClass().getSuperClass().getConstantNames().contains(this.name)) {
            this.receiver.getRuntime().getTopSelf().getMetaClass().getSuperClass().remove_const(this.receiver.getRuntime().getCurrentContext(), rubyName);
        }
    }
    
    static {
        Constant.pattern = "[A-Z]([a-zA-Z]|_)([a-zA-Z]|_|\\d)*";
    }
}
