// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.IndexColorModel;
import java.awt.Image;

class c
{
    private Image g;
    private IndexColorModel h;
    protected int[] a;
    private int i;
    private int j;
    int b;
    int c;
    protected byte[] d;
    protected byte[] e;
    protected byte[] f;
    
    public c() {
        this.g = null;
        this.h = null;
        this.a = null;
        this.b = 2;
        this.c = 2;
        this.d = new byte[] { 0, 0, 32, 96, 32, 64, -96, 64, -32, -32, -64, -64, 32, -64, -96, -32 };
        this.e = new byte[] { 0, 0, -64, -32, 32, 96, 32, -64, 32, 96, -64, -64, -128, 64, -96, -32 };
        this.f = new byte[] { 0, 0, 32, 96, -32, -32, 32, -32, 32, 96, 32, -128, 32, -96, -96, -32 };
    }
    
    public final boolean a(final int i, final int j) {
        this.i = i;
        this.j = j;
        this.a = new int[this.i * this.j];
        return true;
    }
    
    public final void a(final Graphics graphics, final Component component) {
        this.h = new IndexColorModel(8, 16, this.d, this.e, this.f);
        graphics.drawImage(this.g = component.createImage(new MemoryImageSource(this.i, this.j, this.h, this.a, 0, this.i)), 0, 0, this.i * this.b, this.j * this.c, null);
    }
}
