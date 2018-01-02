// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.variable;

import org.jruby.embed.internal.BiVariableMap;
import org.jruby.RubyObject;

public class TransientLocalVariable extends AbstractVariable
{
    private static String pattern;
    
    public static BiVariable getInstance(final RubyObject receiver, final String name, final Object... javaObject) {
        if (name.matches(TransientLocalVariable.pattern)) {
            return new TransientLocalVariable(receiver, name, javaObject);
        }
        return null;
    }
    
    private TransientLocalVariable(final RubyObject receiver, final String name, final Object... javaObjects) {
        super(receiver, name, false);
        this.updateByJavaObject(receiver.getRuntime(), javaObjects);
    }
    
    public BiVariable.Type getType() {
        return BiVariable.Type.LocalVariable;
    }
    
    public static boolean isValidName(final Object name) {
        return AbstractVariable.isValidName(TransientLocalVariable.pattern, name);
    }
    
    public static void retrieve(final RubyObject receiver, final BiVariableMap vars) {
    }
    
    public void inject() {
    }
    
    public void remove() {
    }
    
    static {
        TransientLocalVariable.pattern = "([a-z]|_)([a-zA-Z]|_|\\d)*";
    }
}
