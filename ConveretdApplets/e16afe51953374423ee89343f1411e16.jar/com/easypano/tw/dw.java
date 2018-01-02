// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

class dw implements cf
{
    private int a;
    private double b;
    private double c;
    private double d;
    private int e;
    final /* synthetic */ TWViewer f;
    
    public dw(final TWViewer f, final int a, final double b, final double c, final double d, final int e) {
        this.f = f;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public void a(final int n, final String s) {
        final boolean q = h.q;
        final int n2 = 100;
        final int a;
        if (!q) {
            Label_0070: {
                if (n == n2) {
                    dw dw = this;
                    if (!q) {
                        if (TWViewer.f(this.f) == null) {
                            break Label_0070;
                        }
                        dw = this;
                    }
                    a = dw.a;
                    final int n3 = -1;
                    if (!q) {
                        if (a != n3) {
                            TWViewer.f(this.f).a(this.a, this.b, this.c, this.d, this.e);
                        }
                    }
                }
            }
        }
        if (a == n2) {
            TWViewer.a(this.f, false);
            System.out.println(a("4\u0013\u0011\u0016f\u001f\u001d\u0002Xx\u0015\u000f\t\rx\u0013\u0019F\u001dx\u0002\u0013\u0014Xc\u001e\\\u0015\u001bo\u001e\u0019"));
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
                            c2 = 'p';
                            break;
                        }
                        case 1: {
                            c2 = '|';
                            break;
                        }
                        case 2: {
                            c2 = 'f';
                            break;
                        }
                        case 3: {
                            c2 = 'x';
                            break;
                        }
                        default: {
                            c2 = '\n';
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
