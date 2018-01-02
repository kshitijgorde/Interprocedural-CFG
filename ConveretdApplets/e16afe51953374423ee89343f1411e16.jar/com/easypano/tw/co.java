// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

final class co implements ItemListener
{
    final /* synthetic */ cl a;
    
    co(final cl a) {
        this.a = a;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        System.out.println(a("\f*\u001e\u0013+\u0011?\u000f\u001b;\r?\u0015\u0019\u001d\u0001o"));
        cl.a(this.a).switchToScene(((dg)itemEvent.getSource()).b());
        System.out.println(a("\f*\u001e\u0013+\u0011?\u000f\u001b;\r?\u0015\u0019\u001d\u0001l"));
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
                            c2 = 'e';
                            break;
                        }
                        case 1: {
                            c2 = '^';
                            break;
                        }
                        case 2: {
                            c2 = '{';
                            break;
                        }
                        case 3: {
                            c2 = '~';
                            break;
                        }
                        default: {
                            c2 = 'x';
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
