// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.util.Observable;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.util.Observer;

public abstract class AbstractSerie implements Observer
{
    double[] a;
    double[][] b;
    Font c;
    Graph d;
    Graphics e;
    int f;
    int g;
    int h;
    int i;
    boolean j;
    boolean k;
    Color l;
    Shape[] m;
    public static boolean n;
    
    public void setId(final int g) {
        this.g = g;
    }
    
    public int getId() {
        return this.g;
    }
    
    public int getElementIndex() {
        return this.f;
    }
    
    public void setFont(final Font c) {
        this.c = c;
        if (this.d != null) {
            this.d.cg = true;
        }
    }
    
    public void setValues(final double[] a) {
        this.a = a;
        if (this.d != null) {
            this.d.F = true;
        }
        this.m = null;
        if (this.d != null) {
            this.d.cg = true;
        }
    }
    
    public double[] getValues() {
        return this.a;
    }
    
    public void setMultipleValues(final double[][] b) {
        this.b = b;
        if (this.d != null) {
            this.d.cg = true;
            this.m = null;
        }
    }
    
    public double[][] getMultipleValues() {
        return this.b;
    }
    
    public void setHighlightEnabled(final boolean k) {
        this.k = k;
    }
    
    public void setHighlightColor(final Color l) {
        this.l = l;
    }
    
    void a(final Graph d) {
        this.d = d;
    }
    
    abstract boolean a(final int p0, final int p1, final int p2);
    
    abstract double[] a();
    
    abstract void b();
    
    public abstract void update(final Observable p0, final Object p1);
    
    public AbstractSerie() {
        this.c = new Font(a("kJ)\"2]Y.7"), 0, 10);
        this.l = Color.cyan;
    }
    
    private static String a(final String s) {
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
                            c2 = '8';
                            break;
                        }
                        case 1: {
                            c2 = '+';
                            break;
                        }
                        case 2: {
                            c2 = 'G';
                            break;
                        }
                        case 3: {
                            c2 = 'Q';
                            break;
                        }
                        default: {
                            c2 = 'a';
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
