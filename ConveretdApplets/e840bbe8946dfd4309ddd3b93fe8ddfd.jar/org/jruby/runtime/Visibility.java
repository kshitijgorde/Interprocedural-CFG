// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

public enum Visibility
{
    PUBLIC, 
    PROTECTED, 
    PRIVATE, 
    MODULE_FUNCTION;
    
    public boolean isPrivate() {
        return this == Visibility.PRIVATE;
    }
    
    public boolean isProtected() {
        return this == Visibility.PROTECTED;
    }
}
