// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

public class NullArgumentException extends IllegalArgumentException
{
    protected final String name;
    protected final Object index;
    
    public NullArgumentException(final String name) {
        super(makeMessage(name));
        this.name = name;
        this.index = null;
    }
    
    public NullArgumentException(final String name, final long index) {
        super(makeMessage(name, new Long(index)));
        this.name = name;
        this.index = new Long(index);
    }
    
    public NullArgumentException(final String name, final Object index) {
        super(makeMessage(name, index));
        this.name = name;
        this.index = index;
    }
    
    public NullArgumentException() {
        this.name = null;
        this.index = null;
    }
    
    public final String getArgumentName() {
        return this.name;
    }
    
    public final Object getArgumentIndex() {
        return this.index;
    }
    
    private static String makeMessage(final String name) {
        return "'" + name + "' is null";
    }
    
    private static String makeMessage(final String name, final Object index) {
        return "'" + name + "[" + index + "]' is null";
    }
}
