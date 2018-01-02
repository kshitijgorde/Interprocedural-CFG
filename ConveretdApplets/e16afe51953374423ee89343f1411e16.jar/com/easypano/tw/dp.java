// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

final class dp implements cf
{
    private ed a;
    final /* synthetic */ bt b;
    
    public dp(final bt b, final ed a) {
        this.b = b;
        this.a = a;
    }
    
    public void a(final int n, final String s) {
        final boolean q = h.q;
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
            System.out.println(a("l\u0006m\u0014TG\b~ZJM\u001au\u000fJK\f:\u001fJZ\u0006hZQFIj\u001bL@G"));
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
                            c2 = '(';
                            break;
                        }
                        case 1: {
                            c2 = 'i';
                            break;
                        }
                        case 2: {
                            c2 = '\u001a';
                            break;
                        }
                        case 3: {
                            c2 = 'z';
                            break;
                        }
                        default: {
                            c2 = '8';
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
