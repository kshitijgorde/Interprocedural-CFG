import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.Toolkit;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class f extends Dialog implements ActionListener
{
    private final esChat a;
    private static String[] z;
    
    f(final esChat a) {
        super(new Frame(), f.z[0]);
        this.a = a;
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - 200, screenSize.height / 2 - 150, 400, 300);
        this.setBackground(a.h);
        this.addWindowListener(new q(this));
        this.pack();
        this.setSize(300, 230);
        this.show();
        this.toFront();
    }
    
    static esChat a(final f f) {
        return f.a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
    }
    
    public void paint(final Graphics graphics) {
        final int n = 10;
        final int n2 = 30;
        graphics.setColor(Color.lightGray);
        graphics.fill3DRect(n, n2, 280, 140, true);
        graphics.fill3DRect(3 + n, 3 + n2, 274, 100, true);
        graphics.setColor(Color.blue.darker());
        graphics.fillRect(4 + n, 4 + n2, 270, 30);
        graphics.setColor(Color.white);
        graphics.setFont(new Font("", 1, 28));
        graphics.drawString(this.a.Bb, 20, 28 + n2);
        graphics.setColor(Color.black);
        graphics.setFont(new Font("", 0, 13));
        graphics.drawString(f.z[3] + this.a.Ab, 25, 80);
        graphics.drawString(f.z[2], 25, 100);
        graphics.drawString(f.z[1] + this.a.Ab, 25, 120);
        graphics.setColor(Color.blue);
        graphics.drawString(f.z[4], 45, 160);
        graphics.setColor(Color.lightGray);
        graphics.setFont(new Font("", 2, 12));
        graphics.drawString(f.z[6], 35, 190);
        graphics.drawString(f.z[5], 30, 203);
    }
    
    static {
        final String[] z = new String[7];
        final int n = 0;
        final char[] charArray = "|\u0013sy\u000fm@Xp\u0005r\t^u\u000f7N\u001e".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u0019';
                    break;
                }
                case 1: {
                    c2 = '`';
                    break;
                }
                case 2: {
                    c2 = '0';
                    break;
                }
                case 3: {
                    c2 = '\u0011';
                    break;
                }
                default: {
                    c2 = 'n';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "U\tCp\u0000j@Cp\u0006p\u0002Y1N#@".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u0019';
                    break;
                }
                case 1: {
                    c4 = '`';
                    break;
                }
                case 2: {
                    c4 = '0';
                    break;
                }
                case 3: {
                    c4 = '\u0011';
                    break;
                }
                default: {
                    c4 = 'n';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "U\tCp\u0000j@D\u00ed\u001c\u00e5@\u00101N9Z\u0010U\u000f~\tDx\u0003".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\u0019';
                    break;
                }
                case 1: {
                    c6 = '`';
                    break;
                }
                case 2: {
                    c6 = '0';
                    break;
                }
                case 3: {
                    c6 = '\u0011';
                    break;
                }
                default: {
                    c6 = 'n';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "U\tCp\u0000j@Xp\u0005u\u0001BxN#@".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\u0019';
                    break;
                }
                case 1: {
                    c8 = '`';
                    break;
                }
                case 2: {
                    c8 = '0';
                    break;
                }
                case 3: {
                    c8 = '\u0011';
                    break;
                }
                default: {
                    c8 = 'n';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "M\u0015]1\u0006x\u000b\\p\u001cp@ug\u001c|\u000eC~\bm@Cx\u001cr\u0005Dx\u0000|@Qx\u001am\tB?".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '\u0019';
                    break;
                }
                case 1: {
                    c10 = '`';
                    break;
                }
                case 2: {
                    c10 = '0';
                    break;
                }
                case 3: {
                    c10 = '\u0011';
                    break;
                }
                default: {
                    c10 = 'n';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "U\tCp\u0000j@Cp\u001ap\u0013\u0010z\u0001w\u0014QzN#@\u0010|\u001dw Ug\u001c|\u000eC~\bmNS~\u0003".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '\u0019';
                    break;
                }
                case 1: {
                    c12 = '`';
                    break;
                }
                case 2: {
                    c12 = '0';
                    break;
                }
                case 3: {
                    c12 = '\u0011';
                    break;
                }
                default: {
                    c12 = 'n';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "VNrp\u001dx\u0012\u0010T\u0018k\u0005^1\u001ax\u0012Qw\u0007w\u0004Q\u007fNi\u0012_v\u001cx\r\\p\u0000t\tCe\u0007kN".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '\u0019';
                    break;
                }
                case 1: {
                    c14 = '`';
                    break;
                }
                case 2: {
                    c14 = '0';
                    break;
                }
                case 3: {
                    c14 = '\u0011';
                    break;
                }
                default: {
                    c14 = 'n';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        f.z = z;
    }
}
