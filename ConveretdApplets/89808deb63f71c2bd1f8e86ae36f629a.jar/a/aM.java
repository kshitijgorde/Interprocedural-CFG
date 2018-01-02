// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;
import java.awt.Color;

public class aM
{
    public aY q;
    public Color q;
    public Color w;
    public boolean q;
    public boolean w;
    
    public aM() {
    }
    
    public aM(final bg bg, final bg bg2, final aY q) {
        this.q = Color.black;
        this.w = Color.white;
        this.q = true;
        this.w = false;
        this.q = q;
    }
    
    public int q() {
        if (this.q instanceof l) {
            return ((l)this.q).y - 1;
        }
        return 0;
    }
    
    public int w() {
        if (this.q instanceof l) {
            return ((l)this.q).u;
        }
        return 0;
    }
    
    public Image q() {
        if (this.q instanceof ar) {
            return ((ar)this.q).q;
        }
        return null;
    }
}
