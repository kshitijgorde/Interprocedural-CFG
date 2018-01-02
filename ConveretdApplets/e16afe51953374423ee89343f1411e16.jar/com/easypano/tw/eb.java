// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

class eb extends Thread
{
    protected volatile boolean a;
    
    public eb() {
        super(a("..`ht\u001b2qdw\u0015)~xJ\n"));
        this.a = true;
    }
    
    public void stopWatch() {
        this.a = false;
        dt.stopThread(this, a(".)}`W\u001362{B\u000e%z,W\u00124wmGZ/a,R\u000f/fxJ\u0014!<\"\rTh<\"\rTh<"), 100, 0);
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
                            c2 = 'z';
                            break;
                        }
                        case 1: {
                            c2 = 'F';
                            break;
                        }
                        case 2: {
                            c2 = '\u0012';
                            break;
                        }
                        case 3: {
                            c2 = '\f';
                            break;
                        }
                        default: {
                            c2 = '#';
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
