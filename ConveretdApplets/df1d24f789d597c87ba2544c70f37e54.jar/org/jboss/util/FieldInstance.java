// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.lang.reflect.Field;

public class FieldInstance
{
    protected final Field field;
    protected final Object instance;
    
    public FieldInstance(final Object instance, final String fieldName) throws NoSuchFieldException {
        if (instance == null) {
            throw new NullArgumentException("instance");
        }
        if (fieldName == null) {
            throw new NullArgumentException("fieldName");
        }
        this.field = instance.getClass().getField(fieldName);
        if (!this.field.getDeclaringClass().isAssignableFrom(instance.getClass())) {
            throw new IllegalArgumentException("field does not belong to instance class");
        }
        this.instance = instance;
    }
    
    public final Field getField() {
        return this.field;
    }
    
    public final Object getInstance() {
        return this.instance;
    }
    
    public final Object get() throws IllegalAccessException {
        return this.field.get(this.instance);
    }
    
    public final void set(final Object value) throws IllegalAccessException {
        this.field.set(this.instance, value);
    }
}
