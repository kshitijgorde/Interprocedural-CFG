// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.threading;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MethodRunner implements Runnable
{
    Object mClass;
    Object[] mArgs;
    Method mMethod;
    Object returnValue;
    
    public MethodRunner(final Object mClass, final Method mMethod, final Object[] mArgs) {
        this.returnValue = null;
        this.mClass = mClass;
        this.mMethod = mMethod;
        this.mArgs = mArgs;
    }
    
    public void run() {
        try {
            if (this.mMethod != null) {
                this.returnValue = this.mMethod.invoke(this.mClass, this.mArgs);
            }
        }
        catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
        catch (IllegalArgumentException ex2) {
            ex2.printStackTrace();
        }
        catch (IllegalAccessException ex3) {
            ex3.printStackTrace();
        }
    }
    
    Object getReturnValue() {
        return this.returnValue;
    }
}
