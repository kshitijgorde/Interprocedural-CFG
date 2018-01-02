// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

class ec extends Thread
{
    protected volatile boolean a;
    
    public ec() {
        super(a("Y\u000e\u001bvpl\u0012\nzsb\t\u0005fN}"));
        this.a = true;
    }
    
    public void stopWatch() {
        this.a = false;
        ds.stopThread(this, a("Y\t\u0006~Sd\u0016IeFy\u0005\u00012Se\u0014\fsC-\u000f\u001a2Vx\u000f\u001dfNc\u0001G<\t#HG<\t#HG"), 100, 0);
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
                            c2 = '\r';
                            break;
                        }
                        case 1: {
                            c2 = 'f';
                            break;
                        }
                        case 2: {
                            c2 = 'i';
                            break;
                        }
                        case 3: {
                            c2 = '\u0012';
                            break;
                        }
                        default: {
                            c2 = '\'';
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
