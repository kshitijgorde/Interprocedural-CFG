// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Graphics;
import java.awt.Color;

final class br extends bp
{
    final /* synthetic */ TWViewer f;
    private final /* synthetic */ Color g;
    
    br(final TWViewer f, final Color g) {
        this.f = f;
        this.g = g;
    }
    
    public void c(final Graphics graphics) {
        graphics.setColor(this.g);
        graphics.drawString(b("\u000e\r/\u0012>7\u000b,\u0012k\u0012\r8\u0012>%\bj2$1\u0016j6'%\u001d/\u0014kuJxS"), 20, 50);
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
                            c2 = 'D';
                            break;
                        }
                        case 1: {
                            c2 = 'd';
                            break;
                        }
                        case 2: {
                            c2 = 'J';
                            break;
                        }
                        case 3: {
                            c2 = 'f';
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
