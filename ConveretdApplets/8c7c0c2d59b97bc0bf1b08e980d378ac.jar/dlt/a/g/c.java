// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.g;

import java.util.Vector;
import dlt.a.b.a;

public class c
{
    private d[] a;
    
    public c(final d[] a) {
        this.a = a;
    }
    
    public d[] if() {
        return this.a;
    }
    
    public void a(final d[] a) {
        this.a = a;
    }
    
    public void a(final a a) {
        final b[] a2 = this.a();
        for (int i = 0; i < a2.length; ++i) {
            a2[i].a(a);
        }
    }
    
    private b[] a() {
        final Vector vector = new Vector<b>();
        for (int i = 0; i < this.a.length; ++i) {
            final b[] a = this.a[i].a();
            for (int j = 0; j < 3; ++j) {
                if (!vector.contains(a[j])) {
                    vector.addElement(a[j]);
                }
            }
        }
        return vector.toArray(new b[vector.size()]);
    }
    
    public void do() {
        final dlt.a.b.c a = this.a[0].a()[0].a();
        double for1 = a.for();
        double a2 = a.a();
        double int1 = a.int();
        double for2 = a.for();
        double a3 = a.a();
        double int2 = a.int();
        for (int i = 0; i < this.a.length; ++i) {
            final b[] a4 = this.a[i].a();
            for (int j = 0; j < 3; ++j) {
                final dlt.a.b.c a5 = a4[j].a();
                final double for3 = a5.for();
                final double a6 = a5.a();
                final double int3 = a5.int();
                if (for3 < for2) {
                    for2 = for3;
                }
                else if (for3 > for1) {
                    for1 = for3;
                }
                if (a6 < a3) {
                    a3 = a6;
                }
                else if (a6 > a2) {
                    a2 = a6;
                }
                if (int3 < int2) {
                    int2 = int3;
                }
                else if (int3 > int1) {
                    int1 = int3;
                }
            }
        }
        this.a(dlt.a.b.a.a(new dlt.a.b.b(-((for1 - for2) / 2.0 + for2), -((a2 - a3) / 2.0 + a3), -((int1 - int2) / 2.0 + int2))));
    }
}
