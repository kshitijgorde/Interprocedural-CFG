// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

class ef extends Thread
{
    final /* synthetic */ TWViewer a;
    
    public ef(final TWViewer a) {
        super(a("0\u0017o\u0015$\u0014\nk\u0010\u0014"));
        this.a = a;
    }
    
    public void run() {
        try {
            TWViewer.h(this.a);
            TWViewer.i(this.a);
            this.a.validate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
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
                            c2 = '|';
                            break;
                        }
                        case 1: {
                            c2 = 'x';
                            break;
                        }
                        case 2: {
                            c2 = '\u000e';
                            break;
                        }
                        case 3: {
                            c2 = 'q';
                            break;
                        }
                        default: {
                            c2 = 'p';
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
