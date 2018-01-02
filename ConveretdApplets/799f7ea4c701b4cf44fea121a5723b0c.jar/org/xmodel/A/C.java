// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.A;

import org.xmodel.IModelObject;

public class C implements B
{
    public IModelObject B;
    public IModelObject C;
    public int A;
    
    @Override
    public void B() {
        if (this.B != null) {
            this.B.revertUpdate(this);
        }
    }
    
    @Override
    public void C() {
        if (this.B != null) {
            this.B.restoreUpdate(this);
        }
    }
    
    @Override
    public void A() {
        this.B = null;
        this.C = null;
        this.A = 0;
    }
}
