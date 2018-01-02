// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.D;

import java.awt.Image;
import jmaster.jumploader.model.api.C.C;

public class B implements C
{
    private int D;
    private Image E;
    
    public Image A() {
        return this.E;
    }
    
    public void A(final Image e) {
        this.E = e;
    }
    
    public int E() {
        return this.D;
    }
    
    public void A(final int d) {
        this.D = d;
    }
    
    public boolean C() {
        return this.D == 2;
    }
    
    public boolean D() {
        return this.D == 0;
    }
    
    public boolean B() {
        return this.D == 1;
    }
}
