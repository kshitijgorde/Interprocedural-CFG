// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.image.ImageConsumer;

class w extends az
{
    protected float a;
    protected float b;
    protected float c;
    protected float[] a;
    protected boolean U;
    protected int T;
    protected int U;
    protected int type;
    
    protected w(final int n, final int n2, final int n3, final float n4, final float n5, final float n6) {
        super(n2, n3);
        this.a = 0.0f;
        this.b = 0.0f;
        this.c = 0.0f;
        this.a = new float[3];
        this.U = true;
        this.a(n, n4, n5, n6);
    }
    
    public void a(final int type, final float n, final float n2, final float n3) {
        this.type = type;
        this.T = -1;
        this.U = 0;
        this.a(n);
        this.b(n2);
        this.c(n3);
    }
    
    public final void a(final float a) {
        this.a = a;
    }
    
    public final void b(final float b) {
        this.b = b;
    }
    
    public final void c(final float c) {
        this.c = c;
    }
    
    public final float a() {
        return this.a;
    }
    
    public final float b() {
        return this.b;
    }
    
    public final float c() {
        return this.c;
    }
    
    protected boolean i() {
        return false;
    }
    
    public synchronized void n() {
        this.U = true;
        this.notifyAll();
    }
    
    public synchronized void addConsumer(final ImageConsumer imageConsumer) {
        this.U = true;
        super.addConsumer(imageConsumer);
    }
    
    private int a(final int n, final int t) {
        if (this.type >= 3 && t == this.T) {
            return this.U;
        }
        this.a(n, t, this.a);
        this.T = t;
        return this.U = Color.HSBtoRGB(this.a[0], this.a[1], this.a[2]);
    }
    
    public void a(final int n, final int n2, final float[] array) {
        switch (this.type) {
            case 0: {
                final float n3 = n / super.width;
                final float n4 = n2 / super.height;
                array[0] = this.a;
                array[1] = this.b - n3;
                array[2] = this.c - n4;
                break;
            }
            case 1: {
                final float n5 = n2 / super.height;
                array[0] = n * (1.0f / super.width);
                array[1] = this.b;
                array[2] = 1.0f - n5;
                break;
            }
            case 2: {
                final float n6 = n2 / super.height;
                array[0] = n * (1.0f / super.width);
                array[1] = 1.0f - n6;
                array[2] = this.c;
                break;
            }
            case 3: {
                array[0] = n2 * (1.0f / super.height);
                array[1] = this.b;
                array[2] = this.c;
                break;
            }
            case 4: {
                final float n7 = n2 / super.height;
                array[0] = this.a;
                array[1] = this.b - n7;
                array[2] = this.c;
                break;
            }
            case 5: {
                final float n8 = n2 / super.height;
                array[0] = this.a;
                array[1] = this.b;
                array[2] = this.c - n8;
                break;
            }
        }
    }
    
    protected void a(final int n, final int[] array) {
        if (n == 0) {
            synchronized (this) {
                try {
                    while (!this.U) {
                        this.wait();
                    }
                }
                catch (InterruptedException ex) {}
                this.U = false;
            }
        }
        if (super.aborted) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.a(i, n);
        }
    }
}
