// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class dw implements ActionListener
{
    final /* synthetic */ TWViewer a;
    
    dw(final TWViewer a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.a(a("\u007f\u001f\u001a1hb\u0003W7id\u001fEp2g\u0018\bqwy\n\u000b*n\u007f\t\u000bq~\u007f\u0002S\u007fBr\u0003\u001e1v9"));
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
                            c2 = '\u0010';
                            break;
                        }
                        case 1: {
                            c2 = 'o';
                            break;
                        }
                        case 2: {
                            c2 = '\u007f';
                            break;
                        }
                        case 3: {
                            c2 = '_';
                            break;
                        }
                        default: {
                            c2 = '\u001d';
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
