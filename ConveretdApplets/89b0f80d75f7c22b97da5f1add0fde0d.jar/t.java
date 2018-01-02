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

class t extends Frame implements ActionListener
{
    private final esChat a;
    private static String[] z;
    
    t(final esChat a, final String s, final String s2) {
        super(s2);
        this.a = a;
        this.setBackground(a.g);
        this.setResizable(false);
        final Panel panel = new Panel();
        panel.add(new Label(s));
        this.add(panel, t.z[0]);
        final Panel panel2 = new Panel();
        a.getClass();
        final c c = new c(a, a.a(1, "", "", ""));
        c.a(this);
        panel2.add(c);
        this.add(panel2, t.z[1]);
        this.addWindowListener(new r(this));
        this.pack();
        this.show();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final boolean b = actionEvent.getSource() instanceof c;
        if (fb.m == 0) {
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
        final char[] charArray = "\u0012\u0005aJ\u000f#".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'Q';
                    break;
                }
                case 1: {
                    c2 = '`';
                    break;
                }
                case 2: {
                    c2 = '\u000f';
                    break;
                }
                case 3: {
                    c2 = '>';
                    break;
                }
                default: {
                    c2 = 'j';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u0002\u000fzJ\u0002".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'Q';
                    break;
                }
                case 1: {
                    c4 = '`';
                    break;
                }
                case 2: {
                    c4 = '\u000f';
                    break;
                }
                case 3: {
                    c4 = '>';
                    break;
                }
                default: {
                    c4 = 'j';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        t.z = z;
    }
}
