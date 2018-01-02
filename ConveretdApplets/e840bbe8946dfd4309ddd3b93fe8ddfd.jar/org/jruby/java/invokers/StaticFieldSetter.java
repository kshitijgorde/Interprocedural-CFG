// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.invokers;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import java.lang.reflect.Field;
import org.jruby.RubyModule;

public class StaticFieldSetter extends FieldMethodOne
{
    public StaticFieldSetter(final String name, final RubyModule host, final Field field) {
        super(name, host, field);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg) {
        try {
            this.field.set(null, arg.toJava(this.field.getType()));
        }
        catch (IllegalAccessException iae) {
            throw context.getRuntime().newSecurityError(iae.getMessage());
        }
        catch (IllegalArgumentException iae2) {
            throw context.getRuntime().newTypeError(iae2.getMessage());
        }
        return arg;
    }
}
