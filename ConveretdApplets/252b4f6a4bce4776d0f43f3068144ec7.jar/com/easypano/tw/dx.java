// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

class dx implements cf
{
    private int a;
    private double b;
    private double c;
    private double d;
    private int e;
    final /* synthetic */ TWViewer f;
    
    public dx(final TWViewer f, final int a, final double b, final double c, final double d, final int e) {
        this.f = f;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public void a(final int n, final String s) {
        final int q = h.q;
        final int n2 = 100;
        final int a;
        if (q == 0) {
            Label_0070: {
                if (n == n2) {
                    dx dx = this;
                    if (q == 0) {
                        if (TWViewer.f(this.f) == null) {
                            break Label_0070;
                        }
                        dx = this;
                    }
                    a = dx.a;
                    final int n3 = -1;
                    if (q == 0) {
                        if (a != n3) {
                            TWViewer.f(this.f).a(this.a, this.b, this.c, this.d, this.e);
                        }
                    }
                }
            }
        }
        if (a == n2) {
            TWViewer.a(this.f, false);
            System.out.println(a("`|jk-Kry%3A`rp3Gv=`3V|o%(J3nf$Jv"));
        }
    }
    
    public void a() {
        TWViewer.a(this.f, false);
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
                            c2 = '$';
                            break;
                        }
                        case 1: {
                            c2 = '\u0013';
                            break;
                        }
                        case 2: {
                            c2 = '\u001d';
                            break;
                        }
                        case 3: {
                            c2 = '\u0005';
                            break;
                        }
                        default: {
                            c2 = 'A';
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
