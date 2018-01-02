// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.image.ImageConsumer;

final class bW extends bO
{
    float a;
    float b;
    float c;
    private float[] a;
    private boolean a;
    private int c;
    private int d;
    private int e;
    
    protected bW(final int n, final int n2, final float n3, final float n4, final float n5) {
        super(n2);
        this.a = 0.0f;
        this.b = 0.0f;
        this.c = 0.0f;
        this.a = new float[3];
        this.a = true;
        this.a(n, n3, n4, n5);
    }
    
    public final void a(final int e, final float a, final float b, final float c) {
        this.e = e;
        this.c = -1;
        this.d = 0;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    protected final boolean a() {
        return false;
    }
    
    public final synchronized void a() {
        this.a = true;
        this.notifyAll();
    }
    
    public final synchronized void addConsumer(final ImageConsumer imageConsumer) {
        this.a = true;
        super.addConsumer(imageConsumer);
    }
    
    public final void a(final int n, final int n2, final float[] array) {
        switch (this.e) {
            case 0: {
                final float n3 = n / super.a;
                final float n4 = n2 / super.b;
                array[0] = this.a;
                array[1] = this.b - n3;
                array[2] = this.c - n4;
            }
            case 1: {
                final float n5 = n2 / super.b;
                array[0] = n * (1.0f / super.a);
                array[1] = this.b;
                array[2] = 1.0f - n5;
            }
            case 2: {
                final float n6 = n2 / super.b;
                array[0] = n * (1.0f / super.a);
                array[1] = 1.0f - n6;
                array[2] = this.c;
            }
            case 3: {
                array[0] = n2 * (1.0f / super.b);
                array[1] = this.b;
                array[2] = this.c;
            }
            case 4: {
                final float n7 = n2 / super.b;
                array[0] = this.a;
                array[1] = this.b - n7;
                array[2] = this.c;
            }
            case 5: {
                final float n8 = n2 / super.b;
                array[0] = this.a;
                array[1] = this.b;
                array[2] = this.c - n8;
                break;
            }
        }
    }
    
    protected final void a(final int c, final int[] array) {
        if (c == 0) {
            synchronized (this) {
                try {
                    while (!this.a) {
                        this.wait();
                    }
                }
                catch (InterruptedException ex) {}
                this.a = false;
            }
        }
        for (int i = 0; i < array.length; ++i) {
            final int n = i;
            final Throwable t2;
            final Throwable t = t2;
            final int n2 = i;
            final bW bw = (bW)t;
            int n3;
            if (((bW)t).e >= 3 && c == bw.c) {
                n3 = bw.d;
            }
            else {
                bw.a(n2, c, bw.a);
                bw.c = c;
                bw.d = y.a(bw.a[0], bw.a[1], bw.a[2]);
                n3 = bw.d;
            }
            array[n] = n3;
        }
    }
}
