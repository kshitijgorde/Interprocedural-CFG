// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.A;

import org.xmodel.IModelObject;

public class D implements B
{
    public IModelObject E;
    public IModelObject F;
    public int D;
    
    @Override
    public void B() {
        if (this.E != null) {
            this.E.revertUpdate(this);
        }
    }
    
    @Override
    public void C() {
        if (this.E != null) {
            this.E.restoreUpdate(this);
        }
    }
    
    @Override
    public void A() {
        this.E = null;
        this.F = null;
        this.D = 0;
    }
}
