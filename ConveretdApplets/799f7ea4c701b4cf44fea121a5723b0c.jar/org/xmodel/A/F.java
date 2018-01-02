// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.A;

import org.xmodel.xpath.variable.IVariableScope;

public class F implements B
{
    public IVariableScope L;
    public String N;
    public Object M;
    public Object K;
    
    @Override
    public void B() {
        this.L.revert(this);
    }
    
    @Override
    public void C() {
        this.L.restore(this);
    }
    
    @Override
    public void A() {
        this.L = null;
        this.N = null;
        this.M = null;
        this.K = null;
    }
}
