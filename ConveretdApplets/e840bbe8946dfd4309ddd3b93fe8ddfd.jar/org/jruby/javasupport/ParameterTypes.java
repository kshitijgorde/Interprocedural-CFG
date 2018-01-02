// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

public interface ParameterTypes
{
    Class<?>[] getParameterTypes();
    
    Class<?>[] getExceptionTypes();
    
    boolean isVarArgs();
}
