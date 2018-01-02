// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.A;

import org.xmodel.IModelObject;

public class H implements B
{
    public IModelObject S;
    public IModelObject U;
    public int T;
    public int R;
    
    @Override
    public void B() {
        if (this.S != null) {
            this.S.revertUpdate(this);
        }
    }
    
    @Override
    public void C() {
        if (this.S != null) {
            this.S.restoreUpdate(this);
        }
    }
    
    @Override
    public void A() {
        this.S = null;
        this.U = null;
        this.T = 0;
        this.R = 0;
    }
}
