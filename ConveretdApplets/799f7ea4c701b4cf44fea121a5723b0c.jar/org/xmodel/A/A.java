// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.A;

import org.xmodel.IModelObject;

public class A implements B
{
    public IModelObject W;
    public String X;
    public Object V;
    
    @Override
    public void B() {
        this.W.revertUpdate(this);
    }
    
    @Override
    public void C() {
        this.W.restoreUpdate(this);
    }
    
    @Override
    public void A() {
        this.W = null;
        this.X = null;
        this.V = null;
    }
}
