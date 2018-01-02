// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

final class dn implements cf
{
    private ec a;
    final /* synthetic */ bt b;
    
    public dn(final bt b, final ec a) {
        this.b = b;
        this.a = a;
    }
    
    public void a(final int n, final String s) {
        final boolean q = g.q;
        int n2 = n;
        final int n3 = 100;
        if (!q) {
            if (n == n3) {
                this.a.m = 2;
                if (!q) {
                    return;
                }
            }
            n2 = n;
        }
        if (n2 == n3) {
            System.out.println(a("\u001f+\u0007&m4%\u0014hs>7\u001f=s8!P-s)+\u0002hh5d\u0000)u3j"));
        }
    }
    
    public void a() {
        this.a.m = 4;
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
                            c2 = '[';
                            break;
                        }
                        case 1: {
                            c2 = 'D';
                            break;
                        }
                        case 2: {
                            c2 = 'p';
                            break;
                        }
                        case 3: {
                            c2 = 'H';
                            break;
                        }
                        default: {
                            c2 = '\u0001';
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
