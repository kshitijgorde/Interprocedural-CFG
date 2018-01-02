// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.variable;

import org.jruby.runtime.DynamicScope;
import org.jruby.runtime.ThreadContext;
import org.jruby.parser.StaticScope;
import org.jruby.parser.EvalStaticScope;
import org.jruby.runtime.scope.ManyVarsDynamicScope;
import org.jruby.embed.internal.BiVariableMap;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyObject;

public class PersistentLocalVariable extends AbstractVariable
{
    private static String pattern;
    
    public static BiVariable getInstance(final RubyObject receiver, final String name, final Object... javaObject) {
        if (name.matches(PersistentLocalVariable.pattern)) {
            return new PersistentLocalVariable(receiver, name, javaObject);
        }
        return null;
    }
    
    private PersistentLocalVariable(final RubyObject receiver, final String name, final Object... javaObjects) {
        super(receiver, name, false);
        this.updateByJavaObject(receiver.getRuntime(), javaObjects);
    }
    
    PersistentLocalVariable(final IRubyObject origin, final String name, final IRubyObject irubyObject) {
        super(origin, name, true, irubyObject);
    }
    
    public BiVariable.Type getType() {
        return BiVariable.Type.LocalVariable;
    }
    
    public static boolean isValidName(final Object name) {
        return AbstractVariable.isValidName(PersistentLocalVariable.pattern, name);
    }
    
    public static void retrieve(final RubyObject receiver, final BiVariableMap vars) {
        final ManyVarsDynamicScope scope = (ManyVarsDynamicScope)receiver.getRuntime().getCurrentContext().getCurrentScope();
        if (scope == null) {
            return;
        }
        final String[] names = scope.getAllNamesInScope();
        final IRubyObject[] values = scope.getValues();
        if (names == null || values == null || names.length == 0 || values.length == 0) {
            return;
        }
        for (int i = 0; i < names.length; ++i) {
            BiVariable var;
            if ((var = vars.getVariable((RubyObject)receiver.getRuntime().getTopSelf(), names[i])) != null && receiver.getRuntime().getTopSelf() == var.getReceiver()) {
                var.setRubyObject(values[i]);
            }
            else {
                var = new PersistentLocalVariable(receiver.getRuntime().getTopSelf(), names[i], values[i]);
                vars.update(names[i], var);
            }
        }
    }
    
    public void inject() {
    }
    
    public void remove() {
        final ThreadContext context = this.receiver.getRuntime().getCurrentContext();
        try {
            final DynamicScope currentScope = context.getCurrentScope();
            ManyVarsDynamicScope scope = (ManyVarsDynamicScope)context.getCurrentScope();
            scope = new ManyVarsDynamicScope(new EvalStaticScope(currentScope.getStaticScope()), currentScope);
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
    }
    
    static {
        PersistentLocalVariable.pattern = "([a-z]|_)([a-zA-Z]|_|\\d)*";
    }
}
