// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.builtin;

public interface Variable<BaseObjectType>
{
    String getName();
    
    BaseObjectType getValue();
    
    boolean isInstanceVariable();
    
    boolean isClassVariable();
    
    boolean isConstant();
    
    boolean isRubyVariable();
}
