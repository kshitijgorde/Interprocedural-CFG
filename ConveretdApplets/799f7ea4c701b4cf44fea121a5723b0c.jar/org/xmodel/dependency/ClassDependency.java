// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.dependency;

public class ClassDependency implements IDependency
{
    Class C;
    Class B;
    
    public ClassDependency(final Class c, final Class b) {
        this.C = c;
        this.B = b;
    }
    
    @Override
    public boolean evaluate(final Object o, final Object o2) {
        return o.getClass().equals(this.B) && o2.getClass().equals(this.C);
    }
}
