// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

class ed extends Thread
{
    protected volatile boolean a;
    
    public ed() {
        super(a("r[hP\u0012GGy\\\u0011I\\v@,V"));
        this.a = true;
    }
    
    public void stopWatch() {
        this.a = false;
        dt.stopThread(this, a("r\\uX1OC:C$RPr\u00141NA\u007fU!\u0006Zi\u00144SZn@,HT4\u001ak\b\u001d4\u001ak\b\u001d4"), 100, 0);
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
                            c2 = '&';
                            break;
                        }
                        case 1: {
                            c2 = '3';
                            break;
                        }
                        case 2: {
                            c2 = '\u001a';
                            break;
                        }
                        case 3: {
                            c2 = '4';
                            break;
                        }
                        default: {
                            c2 = 'E';
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
