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

class s extends Frame implements ActionListener
{
    private final v a;
    private static String[] z;
    
    s(final v a, final String s) {
        super(s);
        this.a = a;
        this.setLayout(new BorderLayout());
        final esChat a2 = v.a(a);
        a2.getClass();
        final c c = new c(a2, s.z[5]);
        a.c = new List();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - 200, screenSize.height / 2 - 150, 400, 300);
        this.setResizable(false);
        a.c.setMultipleMode(true);
        v.a(a).a(s.z[1] + a.r + s.z[2], 1);
        this.addWindowListener(new q(this));
        this.add(s.z[3], a.c);
        this.add(s.z[4], c);
        c.a(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final int m = fb.m;
        int n2;
        final int n = n2 = ((actionEvent.getSource() instanceof c) ? 1 : 0);
        if (m == 0) {
            if (n == 0) {
                this.dispose();
                return;
            }
            n2 = 0;
        }
        int n3 = n2;
        while (true) {
            Label_0118: {
                if (m == 0) {
                    break Label_0118;
                }
                final List c = this.a.c;
                final int n4 = n3;
                Label_0115: {
                    if (m == 0) {
                        if (!c.isIndexSelected(n4)) {
                            break Label_0115;
                        }
                        v.a(this.a).a(s.z[1] + this.a.r + s.z[0] + this.a.c.getItem(n3), 1);
                        final List c2 = this.a.c;
                    }
                    c.delItem(n4);
                    if (m == 0) {
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
        final char[] charArray = "E\u0013\u0017a".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'e';
                    break;
                }
                case 1: {
                    c2 = '>';
                    break;
                }
                case 2: {
                    c2 = 'u';
                    break;
                }
                case 3: {
                    c2 = 'A';
                    break;
                }
                default: {
                    c2 = '_';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "(q1\u0004\u007f".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'e';
                    break;
                }
                case 1: {
                    c4 = '>';
                    break;
                }
                case 2: {
                    c4 = 'u';
                    break;
                }
                case 3: {
                    c4 = 'A';
                    break;
                }
                default: {
                    c4 = '_';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "E\u0015\u0017".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'e';
                    break;
                }
                case 1: {
                    c6 = '>';
                    break;
                }
                case 2: {
                    c6 = 'u';
                    break;
                }
                case 3: {
                    c6 = 'A';
                    break;
                }
                default: {
                    c6 = '_';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "&[\u001b5:\u0017".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'e';
                    break;
                }
                case 1: {
                    c8 = '>';
                    break;
                }
                case 2: {
                    c8 = 'u';
                    break;
                }
                case 3: {
                    c8 = 'A';
                    break;
                }
                default: {
                    c8 = '_';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "6Q\u000057".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'e';
                    break;
                }
                case 1: {
                    c10 = '>';
                    break;
                }
                case 2: {
                    c10 = 'u';
                    break;
                }
                case 3: {
                    c10 = 'A';
                    break;
                }
                default: {
                    c10 = '_';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "0P7 1".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'e';
                    break;
                }
                case 1: {
                    c12 = '>';
                    break;
                }
                case 2: {
                    c12 = 'u';
                    break;
                }
                case 3: {
                    c12 = 'A';
                    break;
                }
                default: {
                    c12 = '_';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        s.z = z;
    }
}
