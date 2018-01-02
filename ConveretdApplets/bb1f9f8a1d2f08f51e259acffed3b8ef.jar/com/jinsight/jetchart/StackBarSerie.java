// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.Graphics;
import java.util.Observable;

public class StackBarSerie extends BarSerie implements g
{
    static int W;
    static Graph X;
    int Y;
    
    public static void setStackBarWidth(final int w) {
        StackBarSerie.W = w;
        if (StackBarSerie.X != null) {
            StackBarSerie.X.cg = true;
        }
    }
    
    public void update(final Observable observable, final Object o) {
        final boolean g = GraphSerie.G;
        final p p2 = (p)observable;
        super.e = (Graphics)o;
        super.I = p2.a(this);
        super.K = StackBarSerie.X.bI.j();
        super.L = StackBarSerie.X.bI.k();
        super.y = (StackBarSerie.X.bc ? (StackBarSerie.X.bf ? StackBarSerie.X.p : StackBarSerie.X.o) : 0) * this.e();
        if (super.m == null) {
            super.m = new Polygon[super.I.length];
        }
        super.T = 0;
        while (true) {
            while (true) {
                Label_0296: {
                    if (!g) {
                        break Label_0296;
                    }
                    Label_0286: {
                        Label_0181: {
                            Label_0176: {
                                if (!StackBarSerie.X.bf && super.T < super.E) {
                                    break Label_0176;
                                }
                                if (!StackBarSerie.X.bf || super.I.length - 1 - super.T >= super.E) {
                                    break Label_0181;
                                }
                            }
                            if (!g) {
                                break Label_0286;
                            }
                        }
                        super.J = super.I[super.T];
                        this.b();
                        Rectangle rectangle;
                        if (!StackBarSerie.X.bf) {
                            rectangle = super.S[super.T];
                        }
                        else {
                            rectangle = super.S[super.I.length - super.T - 1];
                        }
                        rectangle.setBounds(super.M[0], super.N[0], super.M[1] - super.M[0], super.N[2] - super.N[0]);
                    }
                    ++super.T;
                }
                if (super.T < super.I.length) {
                    continue;
                }
                break;
            }
            if (!g) {
                return;
            }
            continue;
        }
    }
    
    int g() {
        return StackBarSerie.W;
    }
    
    void j() {
    }
    
    void f() {
    }
    
    void b() {
        final Color color = super.e.getColor();
        final int n = super.J.x + super.y;
        final int n2 = super.J.x + super.J.width + super.y;
        final int n3 = n;
        super.M[0] = n;
        super.M[1] = n2;
        super.M[2] = n2;
        super.M[3] = n3;
        final int n4 = super.J.y - super.y;
        final int n5 = super.J.y + super.J.height - super.y;
        super.N[0] = n4;
        super.N[1] = n4;
        super.N[2] = n5;
        super.N[3] = n5;
        final Color h = this.h();
        super.e.setColor(h);
        super.e.fillPolygon(super.M, super.N, 4);
        super.e.setColor(Color.black);
        super.e.drawPolygon(super.M, super.N, 4);
        Label_0263: {
            if (StackBarSerie.X.bc) {
                if (!StackBarSerie.X.bf) {
                    this.a(super.M, super.N, h);
                    if (!GraphSerie.G) {
                        break Label_0263;
                    }
                }
                this.b(super.M, super.N, h);
            }
        }
        if (StackBarSerie.X.cg && !StackBarSerie.X.D && !StackBarSerie.X.C) {
            Polygon polygon;
            if (!StackBarSerie.X.bc) {
                polygon = new Polygon(super.M, super.N, 4);
            }
            else {
                int[] array;
                int[] array2;
                if (!StackBarSerie.X.bf) {
                    if (this.Y == 0) {
                        array = new int[] { super.M[0], super.Q[1], super.Q[2], super.O[2], super.O[3], super.M[3] };
                        array2 = new int[] { super.N[0], super.R[1], super.R[2], super.P[2], super.P[3], super.N[3] };
                    }
                    else {
                        array = new int[] { super.M[0], super.M[1], super.Q[2], super.O[2], super.O[3], super.M[3] };
                        array2 = new int[] { super.N[0], super.N[1], super.R[2], super.P[2], super.P[3], super.N[3] };
                    }
                }
                else if (this.Y == 0) {
                    array = new int[] { super.M[0], super.Q[1], super.Q[2], super.O[2], super.O[3], super.M[3] };
                    array2 = new int[] { super.N[0], super.R[1], super.R[2], super.P[2], super.P[3], super.N[3] };
                }
                else {
                    array = new int[] { super.M[0], super.Q[1], super.Q[2], super.M[1], super.M[2], super.M[3] };
                    array2 = new int[] { super.N[0], super.R[1], super.R[2], super.N[1], super.N[2], super.N[3] };
                }
                polygon = new Polygon(array, array2, 6);
            }
            super.m[super.T] = polygon;
        }
        super.e.setColor(color);
        if (super.V == null) {
            super.V = new k();
        }
        super.V.a(StackBarSerie.X, super.e);
    }
    
    void a(final int[] array, final int[] array2, final Color color) {
        final int n = array2[0];
        final int n2 = array2[2];
        final int n3 = array[0];
        final int n4 = array[1];
        final int n5 = array[1] - array[0];
        final int n6 = array2[3] - array2[0];
        final int o = StackBarSerie.X.o;
        super.O[0] = n4;
        super.O[1] = n4 + o;
        super.O[2] = n4 + o;
        super.O[3] = n4;
        super.P[0] = n;
        super.P[1] = n - o;
        super.P[2] = n2 - o;
        super.P[3] = n2;
        super.Q[0] = n3;
        super.Q[1] = n3 + o;
        super.Q[2] = n4 + o;
        super.Q[3] = n4;
        super.R[0] = n;
        super.R[1] = n - o;
        super.R[2] = n - o;
        super.R[3] = n;
        super.e.setColor(color.darker());
        super.e.fillPolygon(super.O, super.P, 4);
        super.e.setColor(Color.black);
        super.e.drawPolygon(super.O, super.P, 4);
        super.e.setColor(color.darker());
        super.e.fillPolygon(super.Q, super.R, 4);
        super.e.setColor(Color.black);
        super.e.drawPolygon(super.Q, super.R, 4);
        super.e.setColor(color);
    }
    
    void b(final int[] array, final int[] array2, final Color color) {
        final int n = array2[0];
        final int n2 = array2[2];
        final int n3 = array[0];
        final int n4 = array[1];
        final int n5 = array[1] - array[0];
        final int n6 = array2[3] - array2[0];
        final int p3 = StackBarSerie.X.p;
        super.O[0] = n4;
        super.O[1] = n4 + p3;
        super.O[2] = n4 + p3;
        super.O[3] = n4;
        super.P[0] = n;
        super.P[1] = n - p3;
        super.P[2] = n2 - p3;
        super.P[3] = n2;
        super.Q[0] = n3;
        super.Q[1] = n3 + p3;
        super.Q[2] = n4 + p3;
        super.Q[3] = n4;
        super.R[0] = n;
        super.R[1] = n - p3;
        super.R[2] = n - p3;
        super.R[3] = n;
        super.e.setColor(color.darker());
        super.e.fillPolygon(super.O, super.P, 4);
        super.e.setColor(Color.black);
        super.e.drawPolygon(super.O, super.P, 4);
        super.e.setColor(color.darker());
        super.e.fillPolygon(super.Q, super.R, 4);
        super.e.setColor(Color.black);
        super.e.drawPolygon(super.Q, super.R, 4);
        super.e.setColor(color);
    }
    
    public StackBarSerie(final double[] array, final String s) {
        final boolean g = GraphSerie.G;
        super(array, s);
        StackBarSerie.X = StackBarSerie.X;
        int n = 0;
        while (true) {
            while (true) {
                Label_0048: {
                    if (!g) {
                        break Label_0048;
                    }
                    if (array[n] < 0.0) {
                        throw new GraphException(b(",\u0015sD\u0012=\u0000`t\u001c\r\bwTY\u0012\u0014aSY\u001c\u000e|S\u0018\u0016\u000f2H\u0017\u0013\u00182W\u0016\f\bfN\u000f\u001aAdF\u0015\n\u0004a\t"));
                    }
                    ++n;
                }
                if (n < array.length) {
                    continue;
                }
                break;
            }
            if (!g) {
                return;
            }
            continue;
        }
    }
    
    static {
        StackBarSerie.W = 10;
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = '\u007f';
                            break;
                        }
                        case 1: {
                            c2 = 'a';
                            break;
                        }
                        case 2: {
                            c2 = '\u0012';
                            break;
                        }
                        case 3: {
                            c2 = '\'';
                            break;
                        }
                        default: {
                            c2 = 'y';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
