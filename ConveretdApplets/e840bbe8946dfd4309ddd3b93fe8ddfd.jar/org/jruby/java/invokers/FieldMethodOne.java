// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.invokers;

import org.jruby.Ruby;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import java.lang.reflect.Field;
import org.jruby.internal.runtime.methods.JavaMethod;

public abstract class FieldMethodOne extends JavaMethodOne
{
    Field field;
    
    FieldMethodOne(final String name, final RubyModule host, final Field field) {
        super(host, Visibility.PUBLIC);
        if (!Ruby.isSecurityRestricted()) {
            field.setAccessible(true);
        }
        this.field = field;
    }
}
