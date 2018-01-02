// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Graphics;
import java.awt.Color;

final class bs extends bq
{
    final /* synthetic */ TWViewer f;
    private final /* synthetic */ Color g;
    
    bs(final TWViewer f, final Color g) {
        this.f = f;
        this.g = g;
    }
    
    public void c(final Graphics graphics) {
        graphics.setColor(this.g);
        graphics.drawString(b("\u0014\u000151;0\u000e)h\u001d8\u00122=*=@\u0012'>#@\u0016$*(\u00054hz\u007fRs"), 20, 50);
    }
    
    private static String b(final String s) {
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
                            c2 = 'Q';
                            break;
                        }
                        case 1: {
                            c2 = '`';
                            break;
                        }
                        case 2: {
                            c2 = 'F';
                            break;
                        }
                        case 3: {
                            c2 = 'H';
                            break;
                        }
                        default: {
                            c2 = 'K';
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
