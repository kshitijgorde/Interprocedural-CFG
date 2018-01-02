// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.e;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.TextField;
import java.awt.Dialog;
import java.awt.event.ActionListener;

final class l implements ActionListener
{
    private final j a;
    private final String[] b;
    private final Dialog c;
    private final TextField[] d;
    private final int[] e;
    private static String z;
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final boolean a = g.a;
        int n = 0;
        while (true) {
            Label_0091: {
                if (!a) {
                    break Label_0091;
                }
                final TextField textField = this.d[this.e[n]];
                final String text = textField.getText();
                if (text == null || text.trim().length() == 0) {
                    j.b(this.c, String.valueOf(this.b[this.e[n]]) + l.z);
                    textField.requestFocus();
                    return;
                }
                ++n;
            }
            if (n >= this.e.length) {
                j.a(this.a, 0);
                this.c.dispose();
                return;
            }
            continue;
        }
    }
    
    l(final Dialog c, final TextField[] d, final String[] b, final int[] e, final j a) {
        this.c = c;
        this.d = d;
        this.b = b;
        this.e = e;
        this.a = a;
    }
    
    static {
        l.z = z(z("v!~T\u0001?0~\u0018\u00013 rJ\u0015?99"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'f';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'V';
                    break;
                }
                case 1: {
                    c2 = 'C';
                    break;
                }
                case 2: {
                    c2 = '\u0017';
                    break;
                }
                case 3: {
                    c2 = '8';
                    break;
                }
                default: {
                    c2 = 'f';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
