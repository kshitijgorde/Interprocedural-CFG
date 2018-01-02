import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class u extends Frame implements ActionListener
{
    private final esChat a;
    private static String[] z;
    
    u(final esChat a, final String s, final String s2) {
        super(s2);
        this.a = a;
        this.setBackground(a.h);
        this.setResizable(false);
        final Panel panel = new Panel();
        panel.add(new Label(s));
        this.add(panel, u.z[0]);
        final Panel panel2 = new Panel();
        a.getClass();
        final c c = new c(a, a.a(1, "", "", ""));
        c.a(this);
        panel2.add(c);
        this.add(panel2, u.z[1]);
        this.addWindowListener(new s(this));
        this.pack();
        this.show();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final boolean b = actionEvent.getSource() instanceof c;
        if (!d.r) {
            if (!b) {
                return;
            }
            actionEvent.getActionCommand().equals(this.a.a(1, "", "", ""));
        }
        if (b) {
            this.dispose();
        }
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "s~9\u001byB".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '0';
                    break;
                }
                case 1: {
                    c2 = '\u001b';
                    break;
                }
                case 2: {
                    c2 = 'W';
                    break;
                }
                case 3: {
                    c2 = 'o';
                    break;
                }
                default: {
                    c2 = '\u001c';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "ct\"\u001bt".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '0';
                    break;
                }
                case 1: {
                    c4 = '\u001b';
                    break;
                }
                case 2: {
                    c4 = 'W';
                    break;
                }
                case 3: {
                    c4 = 'o';
                    break;
                }
                default: {
                    c4 = '\u001c';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        u.z = z;
    }
}
