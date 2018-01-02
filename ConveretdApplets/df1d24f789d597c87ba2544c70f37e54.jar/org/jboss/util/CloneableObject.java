// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

public class CloneableObject implements java.lang.Cloneable
{
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
    
    public interface Cloneable extends java.lang.Cloneable
    {
        Object clone();
    }
}
