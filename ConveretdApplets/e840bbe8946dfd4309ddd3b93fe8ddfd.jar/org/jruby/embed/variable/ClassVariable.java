// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.variable;

import org.jruby.RubyModule;
import org.jruby.RubyClass;
import java.util.Iterator;
import java.util.List;
import org.jruby.embed.internal.BiVariableMap;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyObject;

public class ClassVariable extends AbstractVariable
{
    private static String pattern;
    
    public static BiVariable getInstance(final RubyObject receiver, final String name, final Object... javaObject) {
        if (name.matches(ClassVariable.pattern)) {
            return new ClassVariable(receiver, name, javaObject);
        }
        return null;
    }
    
    private ClassVariable(final RubyObject receiver, final String name, final Object... javaObjects) {
        super(receiver, name, false);
        this.updateByJavaObject(receiver.getRuntime(), javaObjects);
    }
    
    ClassVariable(final IRubyObject receiver, final String name, final IRubyObject irubyObject) {
        super(receiver, name, true, irubyObject);
    }
    
    public static void retrieve(final RubyObject receiver, final BiVariableMap vars) {
        if (vars.isLazy()) {
            return;
        }
        updateClassVar(receiver, vars);
        final RubyObject topSelf = (RubyObject)receiver.getRuntime().getTopSelf();
        updateClassVar(topSelf, vars);
    }
    
    private static void updateClassVar(final RubyObject receiver, final BiVariableMap vars) {
        final List<String> keys = receiver.getMetaClass().getClassVariableNameList();
        for (final String key : keys) {
            final IRubyObject value = receiver.getMetaClass().getClassVar(key);
            BiVariable var = vars.getVariable(receiver, key);
            if (var != null) {
                var.setRubyObject(value);
            }
            else {
                var = new ClassVariable((IRubyObject)receiver, key, value);
                vars.update(key, var);
            }
        }
    }
    
    public static void retrieveByKey(final RubyObject receiver, final BiVariableMap vars, final String key) {
        IRubyObject value = null;
        if (receiver == receiver.getRuntime().getTopSelf() && receiver.getMetaClass().getClassVariableNameList().contains(key)) {
            value = receiver.getMetaClass().getClassVar(key);
        }
        else {
            final RubyClass klazz = receiver.getMetaClass();
            if (klazz.fastHasClassVariable(key.intern())) {
                value = klazz.fastGetClassVar(key.intern());
            }
        }
        if (value == null) {
            return;
        }
        BiVariable var = vars.getVariable(receiver, key);
        if (var != null) {
            var.setRubyObject(value);
        }
        else {
            var = new ClassVariable((IRubyObject)receiver, key, value);
            vars.update(key, var);
        }
    }
    
    public BiVariable.Type getType() {
        return BiVariable.Type.ClassVariable;
    }
    
    public static boolean isValidName(final Object name) {
        return AbstractVariable.isValidName(ClassVariable.pattern, name);
    }
    
    public void inject() {
        final RubyModule rubyClass = this.getRubyClass(this.receiver.getRuntime());
        rubyClass.setClassVar(this.name, this.irubyObject);
    }
    
    public void remove() {
        final RubyModule rubyClass = this.getRubyClass(this.receiver.getRuntime());
        rubyClass.removeClassVariable(this.name);
    }
    
    static {
        ClassVariable.pattern = "@@([a-zA-Z]|_)([a-zA-Z]|_|\\d)*";
    }
}
