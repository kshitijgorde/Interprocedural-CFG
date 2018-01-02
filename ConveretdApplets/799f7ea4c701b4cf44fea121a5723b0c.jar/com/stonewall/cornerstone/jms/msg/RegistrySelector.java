// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg;

import org.xmodel.IModelObject;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

public abstract class RegistrySelector
{
    protected String methodName;
    protected Class messageClass;
    protected Constructor constructor;
    protected Method method;
    
    public RegistrySelector(final String methodName) {
        this.methodName = methodName;
    }
    
    public abstract boolean select(final JmsMessage p0, final IModelObject p1);
    
    public String getMethodName() {
        return this.methodName;
    }
    
    @Override
    public String toString() {
        return this.method.toString();
    }
}
