import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class aq
{
    public String a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public boolean j;
    public static final int[] k;
    
    public boolean a(final Hashtable hashtable) {
        if (this.a != null && this.a.length() >= 3 && hashtable != null && hashtable.size() > 0) {
            this.j = hashtable.contains(this.a.toLowerCase());
        }
        else {
            this.j = false;
        }
        return this.j;
    }
    
    public aq(final Vector vector, final int h, final int i, final String s) {
        this.a = "";
        this.h = h;
        this.i = i;
        for (int j = 0; j < vector.size(); ++j) {
            final ad ad = vector.elementAt(j);
            if (ad != null) {
                ++this.c;
                if (ad.f()) {
                    ++this.d;
                }
                else if (ad.d()) {
                    ++this.f;
                }
                else if (ad.a()) {
                    ++this.g;
                }
                else if (ad.c()) {
                    ++this.e;
                }
                this.a += ad.g;
                if (ad.g == 'Q' || ad.g == 'q') {
                    this.a += 'U';
                }
            }
        }
        switch (h) {
            case 0: {
                this.b = this.a();
            }
            case 1: {
                this.b = this.b();
            }
            default: {
                this.b = 0;
            }
        }
    }
    
    public String a(final String s) {
        String string = new String("");
        for (int n = 0; n < s.length() && this.a.length() > n && s.charAt(n) == this.a.charAt(n); ++n) {
            string += s.charAt(n);
        }
        return string;
    }
    
    static {
        k = new int[] { 0, 0, 0, 50, 100, 200, 400, 600, 1000 };
    }
    
    public int a() {
        if (this.a.length() < 3) {
            return 0;
        }
        int n = aq.k[Math.min(this.a.length(), aq.k.length - 1)] + (this.i + 1);
        if (this.a.length() > 8) {
            n += (this.a.length() - 8) * 500;
        }
        return n * (1 + this.f * 2 + this.g * 4) * 10;
    }
    
    public int b() {
        if (this.a.length() < 3) {
            return 0;
        }
        int n = 0;
        for (int i = 0; i < this.a.length(); ++i) {
            n += w.c[this.a.charAt(i) - 'A'];
        }
        return (n + (this.i + 1)) * (this.a.length() + this.f * 2 + this.g * 4) * 10;
    }
}
