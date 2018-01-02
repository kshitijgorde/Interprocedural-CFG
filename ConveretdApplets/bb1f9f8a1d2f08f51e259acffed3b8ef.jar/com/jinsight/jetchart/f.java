// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.text.DecimalFormat;

class f
{
    Graph a;
    Slice b;
    boolean c;
    
    void a() {
        final boolean g = GraphSerie.G;
        int n = 0;
        while (true) {
            Label_0248: {
                if (!g) {
                    break Label_0248;
                }
                final AbstractSerie abstractSerie = this.a.bV.elementAt(n);
                Label_0245: {
                    if (abstractSerie instanceof PieSerie) {
                        this.b = ((PieSerie)abstractSerie).getSlice(this.a.bp, this.a.bq);
                        if (this.a.Z && this.b != null) {
                            this.a.bK.a(this.a(abstractSerie), this.a.bp, this.a.bq);
                            if (!g) {
                                return;
                            }
                        }
                        if (!g) {
                            break Label_0245;
                        }
                    }
                    if (abstractSerie.a(1, this.a.bp, this.a.bq)) {
                        int n2 = this.a.bp;
                        int n3 = this.a.bq;
                        if (!this.a.bc && this.a.ba) {
                            n2 = ((GraphSerie)abstractSerie).C;
                            n3 = ((GraphSerie)abstractSerie).D;
                        }
                        this.a.bK.a(this.a(abstractSerie), n2, n3);
                        if ((this.a.bc || !this.a.ba) && !g) {
                            return;
                        }
                    }
                }
                ++n;
            }
            if (n < this.a.bV.size()) {
                continue;
            }
            break;
        }
    }
    
    String[] a(final AbstractSerie abstractSerie) {
        final boolean g = GraphSerie.G;
        String[] array = null;
        String[] q = null;
        if (abstractSerie instanceof OHLCSerie) {
            q = ((OHLCSerie)abstractSerie).Q;
        }
        double[] a;
        if (abstractSerie instanceof PieSerie && this.b != null) {
            a = new double[] { this.b.getValue() };
            this.a.bn = this.a.g[this.b.getIndex()];
        }
        else {
            a = abstractSerie.a();
        }
        if (this.a.bo != null || (this.a.bt == 1 && !this.a.bn.trim().equals(""))) {
            array = new String[a.length + 1];
            array[0] = ((this.a.bo != null) ? this.a.bo : "") + ((this.a.bt == 1) ? this.a.bn.trim() : "");
        }
        else if (a != null) {
            array = new String[a.length];
        }
        if (abstractSerie instanceof GraphSerie || (abstractSerie instanceof PieSerie && a != null)) {
            DecimalFormat decimalFormat = null;
            int n = a.length - 1;
            while (true) {
                String s = null;
                Label_0307: {
                    if (n < 0) {
                        if (!g) {
                            break;
                        }
                    }
                    else if (this.a.bw != null) {
                        if (decimalFormat == null) {
                            decimalFormat = new DecimalFormat();
                            decimalFormat.applyPattern(this.a.bw);
                        }
                        s = decimalFormat.format(a[n]);
                        if (!g) {
                            break Label_0307;
                        }
                    }
                    s = Double.toString(a[n]);
                }
                Label_0416: {
                    if (abstractSerie instanceof OHLCSerie) {
                        if (((OHLCSerie)abstractSerie).J == 0 || (((OHLCSerie)abstractSerie).J != 1 && ((OHLCSerie)abstractSerie).J != 3) || n != 2) {
                            array[n + ((array.length != a.length) ? 1 : 0)] = q[n] + s;
                        }
                        if (!g) {
                            break Label_0416;
                        }
                    }
                    array[n + ((array.length != a.length) ? 1 : 0)] = s;
                }
                --n;
            }
            if (!g) {
                return array;
            }
        }
        return null;
    }
    
    f(final Graph a) {
        this.c = true;
        this.a = a;
    }
}
