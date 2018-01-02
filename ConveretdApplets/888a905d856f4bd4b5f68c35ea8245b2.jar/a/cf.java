// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;
import java.awt.Color;

public class cf
{
    public bJ q;
    public Color q;
    public Color w;
    public boolean q;
    public boolean w;
    
    public cf() {
    }
    
    public cf(final cc cc, final cc cc2, final bJ q) {
        this.q = Color.black;
        this.w = Color.white;
        this.q = true;
        this.w = false;
        this.q = q;
    }
    
    public int q() {
        if (this.q instanceof p) {
            return ((p)this.q).y - 1;
        }
        return 0;
    }
    
    public int w() {
        if (this.q instanceof p) {
            return ((p)this.q).u;
        }
        return 0;
    }
    
    public Image q() {
        if (this.q instanceof aO) {
            return ((aO)this.q).q;
        }
        return null;
    }
}
