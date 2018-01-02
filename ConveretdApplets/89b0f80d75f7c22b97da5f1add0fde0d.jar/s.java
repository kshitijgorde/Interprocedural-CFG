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
        final c c = new c(a2, s.z[4]);
        a.c = new List();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - 200, screenSize.height / 2 - 150, 400, 300);
        this.setResizable(false);
        a.c.setMultipleMode(true);
        v.a(a).a(s.z[2] + a.r + s.z[3], 1);
        this.addWindowListener(new q(this));
        this.add(s.z[0], a.c);
        this.add(s.z[1], c);
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
                        v.a(this.a).a(s.z[2] + this.a.r + s.z[5] + this.a.c.getItem(n3), 1);
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
        final char[] charArray = "&0\\^\u0016\u0017".toCharArray();
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
                    c2 = 'U';
                    break;
                }
                case 2: {
                    c2 = '2';
                    break;
                }
                case 3: {
                    c2 = '*';
                    break;
                }
                default: {
                    c2 = 's';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "6:G^\u001b".toCharArray();
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
                    c4 = 'U';
                    break;
                }
                case 2: {
                    c4 = '2';
                    break;
                }
                case 3: {
                    c4 = '*';
                    break;
                }
                default: {
                    c4 = 's';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "(\u001avoS".toCharArray();
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
                    c6 = 'U';
                    break;
                }
                case 2: {
                    c6 = '2';
                    break;
                }
                case 3: {
                    c6 = '*';
                    break;
                }
                default: {
                    c6 = 's';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "E~P".toCharArray();
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
                    c8 = 'U';
                    break;
                }
                case 2: {
                    c8 = '2';
                    break;
                }
                case 3: {
                    c8 = '*';
                    break;
                }
                default: {
                    c8 = 's';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "0;pK\u001d".toCharArray();
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
                    c10 = 'U';
                    break;
                }
                case 2: {
                    c10 = '2';
                    break;
                }
                case 3: {
                    c10 = '*';
                    break;
                }
                default: {
                    c10 = 's';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "ExP\n".toCharArray();
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
                    c12 = 'U';
                    break;
                }
                case 2: {
                    c12 = '2';
                    break;
                }
                case 3: {
                    c12 = '*';
                    break;
                }
                default: {
                    c12 = 's';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        s.z = z;
    }
}
