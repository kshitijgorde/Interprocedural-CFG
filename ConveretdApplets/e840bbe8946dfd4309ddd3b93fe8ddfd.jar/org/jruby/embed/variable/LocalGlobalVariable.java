// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.variable;

import org.jruby.Ruby;
import java.util.Iterator;
import java.util.Set;
import org.jruby.internal.runtime.GlobalVariables;
import org.jruby.embed.internal.BiVariableMap;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyObject;

public class LocalGlobalVariable extends GlobalVariable
{
    private static String pattern;
    
    public static BiVariable getInstance(final RubyObject receiver, final String name, final Object... javaObject) {
        if (name.matches(LocalGlobalVariable.pattern)) {
            return new LocalGlobalVariable(receiver, name, javaObject);
        }
        return null;
    }
    
    private LocalGlobalVariable(final RubyObject receiver, final String name, final Object... javaObject) {
        super(receiver, name, javaObject);
    }
    
    LocalGlobalVariable(final IRubyObject receiver, final String name, final IRubyObject irubyObject) {
        super(receiver, name, irubyObject);
    }
    
    public static void retrieve(final RubyObject receiver, final BiVariableMap vars) {
        if (vars.isLazy()) {
            return;
        }
        final GlobalVariables gvars = receiver.getRuntime().getGlobalVariables();
        final Set<String> names = gvars.getNames();
        for (final String name : names) {
            if (GlobalVariable.isPredefined(name)) {
                continue;
            }
            final IRubyObject value = gvars.get(name);
            final String javaName = name.substring(1);
            updateLocalGlobal((RubyObject)receiver.getRuntime().getTopSelf(), vars, javaName, value);
        }
    }
    
    private static void updateLocalGlobal(final RubyObject receiver, final BiVariableMap vars, final String name, final IRubyObject value) {
        if (vars.containsKey(name)) {
            final BiVariable var = vars.getVariable(receiver, name);
            var.setRubyObject(value);
        }
        else {
            final BiVariable var = new LocalGlobalVariable((IRubyObject)receiver, name, value);
            vars.update(name, var);
        }
    }
    
    public static void retrieveByKey(final Ruby runtime, final BiVariableMap vars, final String key) {
        final GlobalVariables gvars = runtime.getGlobalVariables();
        final String rubyKey = ("$" + key).intern();
        if (!gvars.getNames().contains(rubyKey)) {
            return;
        }
        final IRubyObject value = gvars.get(rubyKey);
        updateLocalGlobal((RubyObject)runtime.getTopSelf(), vars, key, value);
    }
    
    public BiVariable.Type getType() {
        return BiVariable.Type.LocalGlobalVariable;
    }
    
    public static boolean isValidName(final Object name) {
        return AbstractVariable.isValidName(LocalGlobalVariable.pattern, name);
    }
    
    public void inject() {
        this.receiver.getRuntime().getGlobalVariables().set(("$" + this.name).intern(), this.irubyObject);
    }
    
    public void remove() {
        this.receiver.getRuntime().getGlobalVariables().clear(("$" + this.name).intern());
    }
    
    static {
        LocalGlobalVariable.pattern = "([a-zA-Z]|(_([a-zA-Z]|_|\\d)))([a-zA-Z]|_|\\d)*";
    }
}
