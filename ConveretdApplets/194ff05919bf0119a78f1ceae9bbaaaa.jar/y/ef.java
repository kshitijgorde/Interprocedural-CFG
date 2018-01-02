// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Image;

public abstract class ef extends ck
{
    public as a;
    private Image[] a;
    
    public ef() {
        this.a = new as();
        this.a = new Image[2];
    }
    
    public void a() {
        super.a();
        this.a[0] = super.a;
        this.a[1] = new ds(this.a[0]).a(1).a();
    }
    
    public final void b() {
        super.b();
        this.a.a();
        this.a[1].flush();
    }
}
