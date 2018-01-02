import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.Toolkit;
import java.awt.List;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class t extends Frame implements ActionListener
{
    private final w a;
    private static String[] z;
    
    t(final w a, final String s) {
        super(s);
        this.a = a;
        this.setLayout(new BorderLayout());
        final esChat a2 = w.a(a);
        a2.getClass();
        final c c = new c(a2, t.z[4]);
        a.c = new List();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - 200, screenSize.height / 2 - 150, 400, 300);
        this.setResizable(false);
        a.c.setMultipleMode(true);
        w.a(a).a(t.z[2] + a.r + t.z[1], 1);
        this.addWindowListener(new r(this));
        this.add(t.z[3], a.c);
        this.add(t.z[0], c);
        c.a(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final boolean r = d.r;
        int n2;
        final int n = n2 = ((actionEvent.getSource() instanceof c) ? 1 : 0);
        if (!r) {
            if (n == 0) {
                this.dispose();
                return;
            }
            n2 = 0;
        }
        int n3 = n2;
        while (true) {
            Label_0118: {
                if (!r) {
                    break Label_0118;
                }
                final List c = this.a.c;
                final int n4 = n3;
                Label_0115: {
                    if (!r) {
                        if (!c.isIndexSelected(n4)) {
                            break Label_0115;
                        }
                        w.a(this.a).a(t.z[2] + this.a.r + t.z[5] + this.a.c.getItem(n3), 1);
                        final List c2 = this.a.c;
                    }
                    c.delItem(n4);
                    if (!r) {
                        break Label_0118;
                    }
                }
                ++n3;
            }
            if (n3 >= this.a.c.getItemCount()) {
                return;
            }
            continue;
        }
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(350, 250);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(350, 250);
    }
    
    static {
        final String[] z = new String[6];
        final int n = 0;
        final char[] charArray = "\u0015li\u0006m".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'F';
                    break;
                }
                case 1: {
                    c2 = '\u0003';
                    break;
                }
                case 2: {
                    c2 = '\u001c';
                    break;
                }
                case 3: {
                    c2 = 'r';
                    break;
                }
                default: {
                    c2 = '\u0005';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "f(~".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'F';
                    break;
                }
                case 1: {
                    c4 = '\u0003';
                    break;
                }
                case 2: {
                    c4 = '\u001c';
                    break;
                }
                case 3: {
                    c4 = 'r';
                    break;
                }
                default: {
                    c4 = '\u0005';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\u000bLX7%".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'F';
                    break;
                }
                case 1: {
                    c6 = '\u0003';
                    break;
                }
                case 2: {
                    c6 = '\u001c';
                    break;
                }
                case 3: {
                    c6 = 'r';
                    break;
                }
                default: {
                    c6 = '\u0005';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\u0005fr\u0006`4".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'F';
                    break;
                }
                case 1: {
                    c8 = '\u0003';
                    break;
                }
                case 2: {
                    c8 = '\u001c';
                    break;
                }
                case 3: {
                    c8 = 'r';
                    break;
                }
                default: {
                    c8 = '\u0005';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "\u0013m^\u0013k".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'F';
                    break;
                }
                case 1: {
                    c10 = '\u0003';
                    break;
                }
                case 2: {
                    c10 = '\u001c';
                    break;
                }
                case 3: {
                    c10 = 'r';
                    break;
                }
                default: {
                    c10 = '\u0005';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "f.~R".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'F';
                    break;
                }
                case 1: {
                    c12 = '\u0003';
                    break;
                }
                case 2: {
                    c12 = '\u001c';
                    break;
                }
                case 3: {
                    c12 = 'r';
                    break;
                }
                default: {
                    c12 = '\u0005';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        t.z = z;
    }
}
