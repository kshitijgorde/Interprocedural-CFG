import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class bb extends Panel implements MouseListener
{
    private final esChat a;
    public String b;
    TextArea c;
    private static String[] z;
    
    bb(final esChat a, final String b, final String s, final TextArea c) {
        this.a = a;
        this.setBackground(a.h);
        this.c = c;
        this.b = b;
        this.addMouseListener(this);
        this.repaint();
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(400, 20);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(400, 20);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int m = fb.m;
        String s = "";
        int n7;
        int x;
        int n6;
        int n5;
        int n4;
        int n3;
        int n2;
        final int n = n2 = (n3 = (n4 = (n5 = (n6 = (x = (n7 = mouseEvent.getX()))))));
        int n15;
        int n14;
        int n13;
        int n12;
        int n11;
        int n10;
        int n9;
        final int n8 = n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = 210))))));
        Label_0235: {
            if (m == 0) {
                if (n > n8) {
                    s = bb.z[0];
                    if (m == 0) {
                        break Label_0235;
                    }
                }
                final int n16;
                n2 = (n16 = (n3 = (n4 = (n5 = (n6 = (x = (n7 = mouseEvent.getX())))))));
                final int n17;
                n9 = (n17 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = 184)))))));
            }
            if (m == 0) {
                if (n > n8) {
                    s = bb.z[5];
                    if (m == 0) {
                        break Label_0235;
                    }
                }
                n3 = (n2 = (n4 = (n5 = (n6 = (x = (n7 = mouseEvent.getX()))))));
                n10 = (n9 = (n11 = (n12 = (n13 = (n14 = (n15 = 165))))));
            }
            if (m == 0) {
                if (n2 > n9) {
                    s = bb.z[9];
                    if (m == 0) {
                        break Label_0235;
                    }
                }
                n4 = (n3 = (n5 = (n6 = (x = (n7 = mouseEvent.getX())))));
                n11 = (n10 = (n12 = (n13 = (n14 = (n15 = 140)))));
            }
            if (m == 0) {
                if (n3 > n10) {
                    s = bb.z[2];
                    if (m == 0) {
                        break Label_0235;
                    }
                }
                n5 = (n4 = (n6 = (x = (n7 = mouseEvent.getX()))));
                n12 = (n11 = (n13 = (n14 = (n15 = 120))));
            }
            if (m == 0) {
                if (n4 > n11) {
                    s = bb.z[1];
                    if (m == 0) {
                        break Label_0235;
                    }
                }
                n6 = (n5 = (x = (n7 = mouseEvent.getX())));
                n13 = (n12 = (n14 = (n15 = 95)));
            }
            if (m == 0) {
                if (n5 > n12) {
                    s = bb.z[7];
                    if (m == 0) {
                        break Label_0235;
                    }
                }
                x = (n6 = (n7 = mouseEvent.getX()));
                n14 = (n13 = (n15 = 72));
            }
            if (m == 0) {
                if (n6 > n13) {
                    s = bb.z[4];
                    if (m == 0) {
                        break Label_0235;
                    }
                }
                n7 = (x = mouseEvent.getX());
                n15 = (n14 = 50);
            }
            int x2 = 0;
            Label_0226: {
                if (m == 0) {
                    if (x > n14) {
                        s = bb.z[8];
                        if (m == 0) {
                            break Label_0235;
                        }
                    }
                    x2 = (n7 = mouseEvent.getX());
                    if (m != 0) {
                        break Label_0226;
                    }
                    n15 = 30;
                }
                if (n7 > n15) {
                    s = bb.z[6];
                    if (m == 0) {
                        break Label_0235;
                    }
                }
                x2 = mouseEvent.getX();
            }
            if (x2 > 0) {
                s = bb.z[3];
            }
        }
        this.c.append(s);
        this.c.setCaretPosition(this.c.getText().length());
        this.c.requestFocus();
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        if (this.a.y != null) {
            graphics.drawImage(this.a.y, 3, 3, 233, 40, 0, 0, 230, 37, this);
        }
    }
    
    static {
        final String[] z = new String[10];
        final int n = 0;
        final char[] charArray = "zd".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '@';
                    break;
                }
                case 1: {
                    c2 = 'N';
                    break;
                }
                case 2: {
                    c2 = '\u001b';
                    break;
                }
                case 3: {
                    c2 = 'R';
                    break;
                }
                default: {
                    c2 = 'w';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "xg".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '@';
                    break;
                }
                case 1: {
                    c4 = 'N';
                    break;
                }
                case 2: {
                    c4 = '\u001b';
                    break;
                }
                case 3: {
                    c4 = 'R';
                    break;
                }
                default: {
                    c4 = 'w';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "zi".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '@';
                    break;
                }
                case 1: {
                    c6 = 'N';
                    break;
                }
                case 2: {
                    c6 = '\u001b';
                    break;
                }
                case 3: {
                    c6 = 'R';
                    break;
                }
                default: {
                    c6 = 'w';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "zg".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '@';
                    break;
                }
                case 1: {
                    c8 = 'N';
                    break;
                }
                case 2: {
                    c8 = '\u001b';
                    break;
                }
                case 3: {
                    c8 = 'R';
                    break;
                }
                default: {
                    c8 = 'w';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "z>".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '@';
                    break;
                }
                case 1: {
                    c10 = 'N';
                    break;
                }
                case 2: {
                    c10 = '\u001b';
                    break;
                }
                case 3: {
                    c10 = 'R';
                    break;
                }
                default: {
                    c10 = 'w';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "zq".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '@';
                    break;
                }
                case 1: {
                    c12 = 'N';
                    break;
                }
                case 2: {
                    c12 = '\u001b';
                    break;
                }
                case 3: {
                    c12 = 'R';
                    break;
                }
                default: {
                    c12 = 'w';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "zf".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '@';
                    break;
                }
                case 1: {
                    c14 = 'N';
                    break;
                }
                case 2: {
                    c14 = '\u001b';
                    break;
                }
                case 3: {
                    c14 = 'R';
                    break;
                }
                default: {
                    c14 = 'w';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "{g".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '@';
                    break;
                }
                case 1: {
                    c16 = 'N';
                    break;
                }
                case 2: {
                    c16 = '\u001b';
                    break;
                }
                case 3: {
                    c16 = 'R';
                    break;
                }
                default: {
                    c16 = 'w';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "z\n".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '@';
                    break;
                }
                case 1: {
                    c18 = 'N';
                    break;
                }
                case 2: {
                    c18 = '\u001b';
                    break;
                }
                case 3: {
                    c18 = 'R';
                    break;
                }
                default: {
                    c18 = 'w';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "za".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '@';
                    break;
                }
                case 1: {
                    c20 = 'N';
                    break;
                }
                case 2: {
                    c20 = '\u001b';
                    break;
                }
                case 3: {
                    c20 = 'R';
                    break;
                }
                default: {
                    c20 = 'w';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        bb.z = z;
    }
}
