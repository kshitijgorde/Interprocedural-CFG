// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.invokers;

import org.jruby.javasupport.JavaUtil;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import java.lang.reflect.Field;
import org.jruby.RubyModule;

public class StaticFieldGetter extends FieldMethodZero
{
    public StaticFieldGetter(final String name, final RubyModule host, final Field field) {
        super(name, host, field);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
        try {
            return JavaUtil.convertJavaToUsableRubyObject(context.getRuntime(), this.field.get(null));
        }
        catch (IllegalAccessException iae) {
            throw context.getRuntime().newTypeError("illegal access getting variable: " + iae.getMessage());
        }
    }
}
