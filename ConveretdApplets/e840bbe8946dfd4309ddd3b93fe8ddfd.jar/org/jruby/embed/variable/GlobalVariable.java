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

public class GlobalVariable extends AbstractVariable
{
    private static String pattern;
    
    public static BiVariable getInstance(final RubyObject receiver, final String name, final Object... javaObject) {
        if (name.matches(GlobalVariable.pattern)) {
            final GlobalVariable gvar = new GlobalVariable(receiver, name, javaObject);
            gvar.tryEagerInjection(receiver.getRuntime(), null);
            return gvar;
        }
        return null;
    }
    
    protected GlobalVariable(final RubyObject receiver, final String name, final Object... javaObjects) {
        super(receiver, name, false);
        this.updateByJavaObject(receiver.getRuntime(), javaObjects);
    }
    
    GlobalVariable(final IRubyObject receiver, final String name, final IRubyObject irubyObject) {
        super(receiver, name, true, irubyObject);
    }
    
    public static void retrieve(final IRubyObject receiver, final BiVariableMap vars) {
        if (vars.isLazy()) {
            return;
        }
        final GlobalVariables gvars = receiver.getRuntime().getGlobalVariables();
        final Set<String> names = gvars.getNames();
        for (final String name : names) {
            if (isPredefined(name)) {
                continue;
            }
            final IRubyObject value = gvars.get(name);
            updateGlobalVar(vars, (RubyObject)receiver.getRuntime().getTopSelf(), name, value);
        }
    }
    
    private static void updateGlobalVar(final BiVariableMap vars, final RubyObject receiver, final String name, final IRubyObject value) {
        if (vars.containsKey(name)) {
            final BiVariable var = vars.getVariable(receiver, name);
            var.setRubyObject(value);
        }
        else {
            final BiVariable var = new GlobalVariable((IRubyObject)receiver, name, value);
            vars.update(name, var);
        }
    }
    
    public static void retrieveByKey(final Ruby runtime, final BiVariableMap vars, final String key) {
        final GlobalVariables gvars = runtime.getGlobalVariables();
        if (!gvars.getNames().contains(key)) {
            return;
        }
        final IRubyObject value = gvars.get(key);
        updateGlobalVar(vars, (RubyObject)runtime.getTopSelf(), key, value);
    }
    
    protected static boolean isPredefined(final String name) {
        final String[] arr$;
        final String[] patterns = arr$ = new String[] { "\\$([\\u0021-\\u0040]|\\u005c|[\\u005e-\\u0060]|\\u007e)", "\\$-(\\d|[A-z])", "\\$(DEBUG|F|FILENAME|KCODE|LOAD_PATH|SAFE|VERBOSE|CLASSPATH|LOADED_FEATURES|PROGRAM_NAME)", "\\$(configure_args|deferr|defout|expect_verbose|stderr|stdin|stdout)" };
        for (final String p : arr$) {
            if (name.matches(p)) {
                return true;
            }
        }
        return false;
    }
    
    public BiVariable.Type getType() {
        return BiVariable.Type.GlobalVariable;
    }
    
    public static boolean isValidName(final Object name) {
        return AbstractVariable.isValidName(GlobalVariable.pattern, name);
    }
    
    public void setJavaObject(final Ruby runtime, final Object javaObject) {
        this.updateByJavaObject(runtime, javaObject);
        this.tryEagerInjection(runtime, null);
    }
    
    public void inject() {
    }
    
    public void tryEagerInjection(final Ruby runtime, final IRubyObject receiver) {
        this.name = (this.name.startsWith("$") ? this.name : ("$" + this.name).intern());
        runtime.getGlobalVariables().set(this.name, this.irubyObject);
    }
    
    public void remove() {
        this.receiver.getRuntime().getGlobalVariables().clear(this.name);
    }
    
    public boolean isReceiverIdentical(final RubyObject recv) {
        return true;
    }
    
    static {
        GlobalVariable.pattern = "\\$(([a-zA-Z]|_|\\d)*|-[a-zA-Z]|[!-~&&[^#%()-\\{\\}\\[\\]\\|\\^]])";
    }
}
