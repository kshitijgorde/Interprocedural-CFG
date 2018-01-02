import java.awt.Image;
import com.daysofwonder.tt.e;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.a;
import com.daysofwonder.applet.y;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.applet.ap;

// 
// Decompiled by Procyon v0.5.30
// 

public class A extends D
{
    private int[] p;
    
    public A(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.p = new int[this.G.width * this.G.height];
        (this.c = new b(this.G.width, this.G.height, y.b(this.i.a(this.j + ".wagons")), y.b(this.i.a(this.j + ".alpha")), y.b(this.i.a(this.j + ".shadow")), y.b(this.i.a(this.j + ".city")), y.b(this.i.a(this.j + ".citysel")), y.b(this.i.a(this.j + ".cityalpha")), y.b(this.i.a(this.j + ".station")), y.b(this.i.a(this.j + ".stationalpha")), y.b(this.i.a(this.j + ".stationshadow")), this.o, this.o, this.h, this.G.width, this.G.height, 0, 1.0, 0)).a(this.p, this.G.width);
        (this.d = new b(this.G.width, this.G.height, y.b(this.i.a(this.j + ".smallwagons")), y.b(this.i.a(this.j + ".smallalpha")), y.b(this.i.a(this.j + ".smallshadow")), y.b(this.i.a(this.j + ".smallcity")), y.b(this.i.a(this.j + ".smallcitysel")), y.b(this.i.a(this.j + ".smallcityalpha")), y.b(this.i.a(this.j + ".smallstation")), y.b(this.i.a(this.j + ".smallstationalpha")), y.b(this.i.a(this.j + ".smallstationshadow")), this.n, this.n, this.h, this.G.width, this.G.height, 1, this.m, 1)).a(this.p, this.G.width);
        this.e = this.d;
    }
    
    public void a(final a a) {
        if (this.K) {
            if (this.f == null) {
                this.f = this.N.b(this.G);
            }
            a.a(this.f, this.G.x, this.G.y, null);
            if (this.e != null) {
                this.e.a(a, this.G.width, this.G.height, this.G.x, this.G.y);
            }
        }
    }
    
    public void a(final e k, final boolean b, final boolean b2, final Image image) {
        this.k = k;
        final R r = b ? this.c : this.d;
        if (b2) {
            ((b)r).a(image);
        }
        else {
            ((b)r).a(k, image);
        }
        if (this.g()) {
            this.N.b("magnify").a(true);
        }
    }
    
    public void a(final e e, final Image image, final int n, final int n2, final int n3, final int n4) {
        ((b)this.c).a(e, image, n, n2, n3, n4);
    }
    
    public void a() {
        if (this.f != null) {
            this.f.c();
            this.f = null;
        }
        if (this.c != null) {
            ((b)this.c).a();
            this.c = null;
        }
        if (this.d != null) {
            ((b)this.d).a();
            this.d = null;
        }
        this.e = null;
        this.p = null;
    }
}
