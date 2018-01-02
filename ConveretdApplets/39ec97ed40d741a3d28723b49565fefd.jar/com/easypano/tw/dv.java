// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

class dv implements cf
{
    private int a;
    private double b;
    private double c;
    private double d;
    private int e;
    final /* synthetic */ TWViewer f;
    
    public dv(final TWViewer f, final int a, final double b, final double c, final double d, final int e) {
        this.f = f;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public void a(final int n, final String s) {
        final boolean q = g.q;
        final int n2 = 100;
        final int a;
        if (!q) {
            Label_0070: {
                if (n == n2) {
                    dv dv = this;
                    if (!q) {
                        if (TWViewer.f(this.f) == null) {
                            break Label_0070;
                        }
                        dv = this;
                    }
                    a = dv.a;
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
            System.out.println(a("6Yt\u001aB\u001dWgT\\\u0017El\u0001\\\u0011S#\u0011\\\u0000YqTG\u001c\u0016p\u0017K\u001cS"));
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
                            c2 = 'r';
                            break;
                        }
                        case 1: {
                            c2 = '6';
                            break;
                        }
                        case 2: {
                            c2 = '\u0003';
                            break;
                        }
                        case 3: {
                            c2 = 't';
                            break;
                        }
                        default: {
                            c2 = '.';
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
