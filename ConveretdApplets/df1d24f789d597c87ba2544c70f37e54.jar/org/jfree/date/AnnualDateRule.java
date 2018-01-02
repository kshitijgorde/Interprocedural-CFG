// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.date;

public abstract class AnnualDateRule implements Cloneable
{
    public abstract SerialDate getDate(final int p0);
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
