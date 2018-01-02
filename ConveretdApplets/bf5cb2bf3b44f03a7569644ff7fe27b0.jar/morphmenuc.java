import java.awt.Rectangle;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class morphmenuc
{
    int[] a;
    int b;
    URL c;
    int[] d;
    int e;
    String f;
    int g;
    int h;
    Rectangle i;
    int[] j;
    String k;
    
    public morphmenuc(final String f, final int n, final int n2, final int h) {
        this.f = f;
        this.a = new int[n * n2];
        this.j = new int[n * n2];
        this.d = new int[n * n2];
        this.i = new Rectangle(n, n2);
        this.h = h;
        this.b = -1;
        this.g = -1;
        this.e = -1;
    }
}
