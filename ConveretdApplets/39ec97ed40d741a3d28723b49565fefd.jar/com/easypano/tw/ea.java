// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

class ea extends Thread
{
    protected volatile boolean a;
    
    public ea() {
        super(a("Z<\u000b&no \u001a*ma;\u00156P~"));
        this.a = true;
    }
    
    public void stopWatch() {
        this.a = false;
        ds.stopThread(this, a("Z;\u0016.Mg$Y5Xz7\u0011bMf&\u001c#].=\nbH{=\r6P`3Wl\u0017 zWl\u0017 zW"), 100, 0);
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
                            c2 = '\u000e';
                            break;
                        }
                        case 1: {
                            c2 = 'T';
                            break;
                        }
                        case 2: {
                            c2 = 'y';
                            break;
                        }
                        case 3: {
                            c2 = 'B';
                            break;
                        }
                        default: {
                            c2 = '9';
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
