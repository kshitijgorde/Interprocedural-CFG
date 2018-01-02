// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Image;

public class eo
{
    public Image[] a;
    
    public eo(final String[] array) {
        this.a = new Image[array.length];
        for (int i = 0; i < array.length; ++i) {
            this.a[i] = j.a(array[i]);
        }
    }
    
    public final void a() {
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i].flush();
        }
    }
}
