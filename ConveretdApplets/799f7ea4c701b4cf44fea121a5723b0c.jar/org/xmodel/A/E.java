// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.A;

import org.xmodel.IModelObject;

public class E implements B
{
    public IModelObject H;
    public String I;
    public Object J;
    public Object G;
    
    @Override
    public void B() {
        this.H.revertUpdate(this);
    }
    
    @Override
    public void C() {
        this.H.restoreUpdate(this);
    }
    
    @Override
    public void A() {
        this.H = null;
        this.I = null;
        this.J = null;
        this.G = null;
    }
}
