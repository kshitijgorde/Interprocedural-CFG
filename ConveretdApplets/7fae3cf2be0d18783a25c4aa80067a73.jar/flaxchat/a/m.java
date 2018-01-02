// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.TextField;
import java.awt.Dialog;
import java.awt.event.ActionListener;

final class m implements ActionListener
{
    private final k a;
    private final String[] b;
    private final Dialog c;
    private final TextField[] d;
    private final int[] e;
    private static String z;
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final boolean a = h.a;
        int n = 0;
        while (true) {
            Label_0091: {
                if (!a) {
                    break Label_0091;
                }
                final TextField textField = this.d[this.e[n]];
                final String text = textField.getText();
                if (text == null || text.trim().length() == 0) {
                    k.b(this.c, String.valueOf(this.b[this.e[n]]) + m.z);
                    textField.requestFocus();
                    return;
                }
                ++n;
            }
            if (n >= this.e.length) {
                k.a(this.a, 0);
                this.c.dispose();
                return;
            }
            continue;
        }
    }
    
    m(final Dialog c, final TextField[] d, final String[] b, final int[] e, final k a) {
        this.c = c;
        this.d = d;
        this.b = b;
        this.e = e;
        this.a = a;
    }
    
    static {
        m.z = z(z("TS>\u0005p\u001dB>Ip\u0011R2\u001bd\u001dKy"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u0017';
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
                    c2 = 't';
                    break;
                }
                case 1: {
                    c2 = '1';
                    break;
                }
                case 2: {
                    c2 = 'W';
                    break;
                }
                case 3: {
                    c2 = 'i';
                    break;
                }
                default: {
                    c2 = '\u0017';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
