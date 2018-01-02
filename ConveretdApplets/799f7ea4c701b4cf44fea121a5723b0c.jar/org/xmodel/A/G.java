// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.A;

import org.xmodel.IModelObject;

public class G implements B
{
    public IModelObject Q;
    public IModelObject P;
    public IModelObject O;
    
    @Override
    public void B() {
        this.Q.revertUpdate(this);
    }
    
    @Override
    public void C() {
        this.Q.restoreUpdate(this);
    }
    
    @Override
    public void A() {
        this.Q = null;
        this.P = null;
        this.O = null;
    }
}
