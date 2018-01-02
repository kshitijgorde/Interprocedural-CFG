import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class o extends Panel implements ActionListener
{
    protected Button a;
    protected Button b;
    protected boolean c;
    protected l d;
    private static final String[] z;
    
    public o(final l l) {
        this(l, null);
    }
    
    public o(final l d, final Font font) {
        this.c = true;
        this.d = d;
        this.c = true;
        this.d.a(this.c);
        this.a = new Button(o.z[4]);
        if (font != null) {
            this.a.setFont(font);
        }
        this.a.addActionListener(this);
        this.b = new Button(o.z[0]);
        if (font != null) {
            this.b.setFont(font);
        }
        this.b.addActionListener(this);
        this.setLayout(new lb(o.z[3]));
        final f f = new f(o.z[2], (font == null) ? 100 : (this.getFontMetrics(font).stringWidth(o.z[2]) + 10), (font == null) ? 30 : (this.getFontMetrics(font).getHeight() + 5), 1);
        if (font != null) {
            f.a(font);
        }
        this.add(o.z[1], f);
        this.add(o.z[5], this.a);
        this.add(o.z[5], this.b);
        this.validate();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.a) {
            this.c = !this.c;
            this.a.setLabel(this.c ? o.z[4] : o.z[6]);
            this.d.a(this.c);
        }
        if (actionEvent.getSource() == this.b) {
            this.d.setVisible(true);
        }
    }
    
    static {
        final String[] z2 = new String[7];
        final int n = 0;
        final char[] charArray = "#k\u0014)\u0002#`\u0014,G".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'p';
                    break;
                }
                case 1: {
                    c2 = '\u0003';
                    break;
                }
                case 2: {
                    c2 = '{';
                    break;
                }
                case 3: {
                    c2 = '^';
                    break;
                }
                default: {
                    c2 = '\"';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z2[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "5b\b*".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'p';
                    break;
                }
                case 1: {
                    c4 = '\u0003';
                    break;
                }
                case 2: {
                    c4 = '{';
                    break;
                }
                case 3: {
                    c4 = '^';
                    break;
                }
                default: {
                    c4 = '\"';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z2[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "#`\u0014,K\u001edA~".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'p';
                    break;
                }
                case 1: {
                    c6 = '\u0003';
                    break;
                }
                case 2: {
                    c6 = '{';
                    break;
                }
                case 3: {
                    c6 = '^';
                    break;
                }
                default: {
                    c6 = '\"';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z2[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = ">l\t*J".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'p';
                    break;
                }
                case 1: {
                    c8 = '\u0003';
                    break;
                }
                case 2: {
                    c8 = '{';
                    break;
                }
                case 3: {
                    c8 = '^';
                    break;
                }
                default: {
                    c8 = '\"';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z2[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "^#[\u001fA\u0004j\r;\u0002P-".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'p';
                    break;
                }
                case 1: {
                    c10 = '\u0003';
                    break;
                }
                case 2: {
                    c10 = '{';
                    break;
                }
                case 3: {
                    c10 = '^';
                    break;
                }
                default: {
                    c10 = '\"';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z2[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "3f\u0015*G\u0002".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'p';
                    break;
                }
                case 1: {
                    c12 = '\u0003';
                    break;
                }
                case 2: {
                    c12 = '{';
                    break;
                }
                case 3: {
                    c12 = '^';
                    break;
                }
                default: {
                    c12 = '\"';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z2[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = " b\u000e-G\u0014".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'p';
                    break;
                }
                case 1: {
                    c14 = '\u0003';
                    break;
                }
                case 2: {
                    c14 = '{';
                    break;
                }
                case 3: {
                    c14 = '^';
                    break;
                }
                default: {
                    c14 = '\"';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z2[n19] = new String(charArray7).intern();
        z = z2;
    }
}
