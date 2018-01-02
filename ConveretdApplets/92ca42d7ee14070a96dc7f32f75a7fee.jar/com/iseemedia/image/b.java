// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.image;

import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.Image;

public final class b
{
    public Image a;
    public int[] b;
    public int c;
    public int d;
    private a e;
    
    public b(final int c, final int d) {
        this.c = c;
        this.d = d;
        this.b = null;
        this.a = null;
        this.e = null;
        try {
            this.b = new int[this.c * this.d];
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.b = null;
        }
        if (this.b != null) {
            this.e = new a(this.c, this.d, this.b);
        }
    }
    
    public final void a() {
        if (this.a == null) {
            try {
                if (this.e != null) {
                    this.a = Toolkit.getDefaultToolkit().createImage(this.e);
                }
                return;
            }
            catch (OutOfMemoryError outOfMemoryError) {
                this.a = null;
                return;
            }
        }
        if (this.e != null) {
            this.e.a();
        }
    }
    
    public final void b() {
        if (this.a != null) {
            this.a.flush();
        }
        this.a = null;
        this.b = null;
        this.e = null;
    }
}
