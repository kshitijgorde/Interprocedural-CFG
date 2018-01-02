// 
// Decompiled by Procyon v0.5.30
// 

package de.ts.b.a;

import java.awt.Point;

public class j extends a
{
    public static final int Y = 0;
    public static final int S = 1;
    public static final int R = 2;
    public static final int P = 3;
    public static final int O = 4;
    public static final int U = 5;
    public static final int W = 6;
    public static final int X = 7;
    public static final int aa = 8;
    public static final int V = 9;
    public static final int Z = 10;
    protected int Q;
    private int L;
    private int T;
    private int M;
    private int N;
    
    public j() {
        this.Q = 0;
        this.L = 0;
        this.T = 0;
        this.M = 0;
        this.N = 0;
        this.Q = 0;
    }
    
    public j(final int q) {
        this.Q = 0;
        this.L = 0;
        this.T = 0;
        this.M = 0;
        this.N = 0;
        this.Q = q;
    }
    
    public j(final int l, final int t) {
        this.Q = 0;
        this.L = 0;
        this.T = 0;
        this.M = 0;
        this.N = 0;
        this.Q = 10;
        this.L = l;
        this.T = t;
    }
    
    public void a(final int m, final int n) {
        this.M = m;
        this.N = n;
    }
    
    public Point a(final int n, final int n2, final int n3, final int n4) {
        int l = 0;
        switch (this.Q) {
            case 6:
            case 7:
            case 8: {
                l = 0;
                break;
            }
            case 0:
            case 1:
            case 5: {
                l = (n - n3) / 2;
                break;
            }
            case 2:
            case 3:
            case 4: {
                l = n - n3;
                break;
            }
            case 9: {
                l = 0;
                break;
            }
            case 10: {
                l = this.L;
                break;
            }
        }
        int t = 0;
        switch (this.Q) {
            case 1:
            case 2:
            case 8: {
                t = 0;
                break;
            }
            case 0:
            case 3:
            case 7: {
                t = (n2 - n4) / 2;
                break;
            }
            case 4:
            case 5:
            case 6: {
                t = n2 - n4;
                break;
            }
            case 9: {
                t = 0;
                break;
            }
            case 10: {
                t = this.T;
                break;
            }
        }
        return new Point(l, t);
    }
    
    public Point if(final int n, final int n2) {
        return this.a(n, n2, this.M, this.N);
    }
}
