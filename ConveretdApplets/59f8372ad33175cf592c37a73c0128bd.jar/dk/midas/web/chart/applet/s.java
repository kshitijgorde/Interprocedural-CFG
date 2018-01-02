// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

public class s
{
    static int if;
    static final int do = 4;
    Analyse[] a;
    int for;
    
    s() {
        this.a = new Analyse[4];
        this.for = 0;
    }
    
    boolean a(final Analyse analyse) {
        if (this.for < 4) {
            this.a[this.for] = analyse;
            ++this.for;
            return true;
        }
        return false;
    }
    
    boolean if(final int n) {
        for (int i = n; i < this.for - 1; ++i) {
            this.a[i] = this.a[i + 1];
        }
        this.a[this.for - 1] = null;
        --this.for;
        return true;
    }
    
    Analyse if(final String s) {
        final int a = this.a(s);
        if (a != -1) {
            final Analyse analyse = this.a[a];
            this.if(a);
            return analyse;
        }
        return null;
    }
    
    int a(final String s) {
        for (int i = 0; i < this.for; ++i) {
            if (this.a[i] != null && this.a[i].g().compareTo(s) == 0) {
                return i;
            }
        }
        return -1;
    }
    
    int if() {
        return this.for;
    }
    
    Analyse a(final int n) {
        return this.a[n];
    }
    
    void a() {
        for (int i = 0; i < 4; ++i) {
            if (this.a[i] != null) {
                this.a[i].a(true);
                this.a[i].m();
                this.a[i].repaint();
            }
        }
    }
    
    void a(final DataSource dataSource) {
        for (int i = 0; i < 4; ++i) {
            if (this.a[i] != null) {
                this.a[i].if(dataSource);
            }
        }
    }
}
