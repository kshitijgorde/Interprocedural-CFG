// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.invokers;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import java.lang.reflect.Field;
import org.jruby.internal.runtime.methods.JavaMethod;

public abstract class FieldMethodZero extends JavaMethodZero
{
    Field field;
    
    FieldMethodZero(final String name, final RubyModule host, final Field field) {
        super(host, Visibility.PUBLIC);
        if (!Ruby.isSecurityRestricted()) {
            field.setAccessible(true);
        }
        this.field = field;
    }
    
    protected Object safeConvert(final IRubyObject value) {
        final Object newValue = value.toJava(Object.class);
        if (newValue == null) {
            if (this.field.getType().isPrimitive()) {
                throw value.getRuntime().newTypeError("wrong type for " + this.field.getType().getName() + ": null");
            }
        }
        else if (!this.field.getType().isInstance(newValue)) {
            throw value.getRuntime().newTypeError("wrong type for " + this.field.getType().getName() + ": " + newValue.getClass().getName());
        }
        return newValue;
    }
}
