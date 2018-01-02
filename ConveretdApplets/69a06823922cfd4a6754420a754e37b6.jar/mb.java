import java.awt.Point;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.util.Vector;
import java.awt.LayoutManager;

// 
// Decompiled by Procyon v0.5.30
// 

public class mb implements LayoutManager
{
    private int a;
    private String b;
    private Vector c;
    private Vector d;
    public static boolean e;
    private static final String[] z;
    
    public mb() {
        this(2, mb.z[9]);
    }
    
    public mb(final String s) {
        this(2, s);
    }
    
    public mb(final int a, final String b) {
        this.a = a;
        this.b = b;
        this.c = new Vector();
        this.d = new Vector();
    }
    
    public void addLayoutComponent(String s, final Component component) {
        if (s == null) {
            s = new String(mb.z[0]);
        }
        this.c.addElement(component);
        this.d.addElement(s);
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        final boolean e = mb.e;
        int width = 0;
        int n = 0;
        int i = 0;
        while (i < container.getComponentCount()) {
            if (width < container.getComponent(i).getPreferredSize().width) {
                width = container.getComponent(i).getPreferredSize().width;
            }
            n += container.getComponent(i).getPreferredSize().height;
            ++i;
            if (e) {
                break;
            }
        }
        return new Dimension(container.getInsets().left + container.getInsets().right + width, container.getInsets().top + container.getInsets().bottom + this.a * (container.getComponentCount() - 1) + n);
    }
    
    public void layoutContainer(final Container container) {
        final boolean e = mb.e;
        final Insets insets = container.getInsets();
        final Dimension size = container.getSize();
        final int width = size.width;
        final int height = size.height;
        final Dimension preferredLayoutSize = this.preferredLayoutSize(container);
        if (preferredLayoutSize.width > size.width) {
            preferredLayoutSize.width = size.width;
        }
        if (preferredLayoutSize.height > size.height) {
            preferredLayoutSize.height = size.height;
        }
        final int n = preferredLayoutSize.width - insets.left - insets.right;
        final int n2 = preferredLayoutSize.height - insets.top - insets.bottom;
        final int componentCount = container.getComponentCount();
        final Point a = this.a(insets, width, height, n, n2);
        int i = 0;
        while (i < componentCount) {
            final Component component = container.getComponent(i);
            final Dimension preferredSize = component.getPreferredSize();
            final int min = Math.min(preferredSize.width, n);
            final int index = this.c.indexOf(component);
            String s = null;
            Label_0211: {
                if (index != -1) {
                    s = this.d.elementAt(index);
                    if (!e) {
                        break Label_0211;
                    }
                }
                s = mb.z[0];
            }
            Label_0370: {
                if (s.equals(mb.z[0])) {
                    component.setBounds(a.x, a.y, n, preferredSize.height);
                    if (!e) {
                        break Label_0370;
                    }
                }
                if (s.equals(mb.z[1])) {
                    component.setBounds(a.x, a.y, min, preferredSize.height);
                    if (!e) {
                        break Label_0370;
                    }
                }
                if (s.equals(mb.z[5])) {
                    component.setBounds(a.x + n - min, a.y, min, preferredSize.height);
                    if (!e) {
                        break Label_0370;
                    }
                }
                component.setBounds(a.x + (n - preferredSize.width) / 2, a.y, min, preferredSize.height);
            }
            final Point point = a;
            point.y += preferredSize.height + this.a;
            ++i;
            if (e) {
                break;
            }
        }
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return this.preferredLayoutSize(container);
    }
    
    public void removeLayoutComponent(final Component component) {
        final int index = this.c.indexOf(component);
        if (index != -1) {
            this.c.removeElementAt(index);
            this.d.removeElementAt(index);
        }
    }
    
    private Point a(final Insets insets, final int n, final int n2, final int n3, final int n4) {
        final boolean e = mb.e;
        if (this.b.equals(mb.z[6])) {
            final int n5 = insets.left + (n - insets.left - insets.right - n3) / 2;
            final int n6 = insets.top;
            if (!e) {
                return new Point(n5, n6);
            }
        }
        if (this.b.equals(mb.z[8])) {
            final int n5 = insets.left + (n - insets.left - insets.right - n3) / 2;
            final int n6 = n2 - insets.bottom - n4;
            if (!e) {
                return new Point(n5, n6);
            }
        }
        if (this.b.equals(mb.z[5])) {
            final int n5 = n - insets.right - n3;
            final int n6 = insets.top + (n2 - insets.top - insets.bottom - n4) / 2;
            if (!e) {
                return new Point(n5, n6);
            }
        }
        if (this.b.equals(mb.z[1])) {
            final int n5 = insets.left;
            final int n6 = insets.top + (n2 - insets.top - insets.bottom - n4) / 2;
            if (!e) {
                return new Point(n5, n6);
            }
        }
        if (this.b.equals(mb.z[2])) {
            final int n5 = n - insets.right - n3;
            final int n6 = insets.top;
            if (!e) {
                return new Point(n5, n6);
            }
        }
        if (this.b.equals(mb.z[4])) {
            final int n5 = insets.left;
            final int n6 = insets.top;
            if (!e) {
                return new Point(n5, n6);
            }
        }
        if (this.b.equals(mb.z[7])) {
            final int n5 = n - insets.right - n3;
            final int n6 = n2 - insets.bottom - n4;
            if (!e) {
                return new Point(n5, n6);
            }
        }
        if (this.b.equals(mb.z[3])) {
            final int n5 = insets.left;
            final int n6 = n2 - insets.bottom - n4;
            if (!e) {
                return new Point(n5, n6);
            }
        }
        final int n5 = insets.left + (n - insets.left - insets.right - n3) / 2;
        final int n6 = insets.top + (n2 - insets.top - insets.bottom - n4) / 2;
        return new Point(n5, n6);
    }
    
    static {
        final String[] z2 = new String[10];
        final int n = 0;
        final char[] charArray = "1Qd]".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'w';
                    break;
                }
                case 1: {
                    c2 = '8';
                    break;
                }
                case 2: {
                    c2 = '\b';
                    break;
                }
                case 3: {
                    c2 = '1';
                    break;
                }
                default: {
                    c2 = '\u0011';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z2[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = ";]nE".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'w';
                    break;
                }
                case 1: {
                    c4 = '8';
                    break;
                }
                case 2: {
                    c4 = '\b';
                    break;
                }
                case 3: {
                    c4 = '1';
                    break;
                }
                default: {
                    c4 = '\u0011';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z2[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "9WzEy\u0012Y{E".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'w';
                    break;
                }
                case 1: {
                    c6 = '8';
                    break;
                }
                case 2: {
                    c6 = '\b';
                    break;
                }
                case 3: {
                    c6 = '1';
                    break;
                }
                default: {
                    c6 = '\u0011';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z2[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "$W}Ey\u0000]{E".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'w';
                    break;
                }
                case 1: {
                    c8 = '8';
                    break;
                }
                case 2: {
                    c8 = '\b';
                    break;
                }
                case 3: {
                    c8 = '1';
                    break;
                }
                default: {
                    c8 = '\u0011';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z2[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "9WzEy\u0000]{E".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'w';
                    break;
                }
                case 1: {
                    c10 = '8';
                    break;
                }
                case 2: {
                    c10 = '\b';
                    break;
                }
                case 3: {
                    c10 = '1';
                    break;
                }
                default: {
                    c10 = '\u0011';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z2[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "%QoYe".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'w';
                    break;
                }
                case 1: {
                    c12 = '8';
                    break;
                }
                case 2: {
                    c12 = '\b';
                    break;
                }
                case 3: {
                    c12 = '1';
                    break;
                }
                default: {
                    c12 = '\u0011';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z2[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "9WzEy".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'w';
                    break;
                }
                case 1: {
                    c14 = '8';
                    break;
                }
                case 2: {
                    c14 = '\b';
                    break;
                }
                case 3: {
                    c14 = '1';
                    break;
                }
                default: {
                    c14 = '\u0011';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z2[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "$W}Ey\u0012Y{E".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 'w';
                    break;
                }
                case 1: {
                    c16 = '8';
                    break;
                }
                case 2: {
                    c16 = '\b';
                    break;
                }
                case 3: {
                    c16 = '1';
                    break;
                }
                default: {
                    c16 = '\u0011';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z2[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "$W}Ey".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = 'w';
                    break;
                }
                case 1: {
                    c18 = '8';
                    break;
                }
                case 2: {
                    c18 = '\b';
                    break;
                }
                case 3: {
                    c18 = '1';
                    break;
                }
                default: {
                    c18 = '\u0011';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z2[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "4]fEt\u0005".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = 'w';
                    break;
                }
                case 1: {
                    c20 = '8';
                    break;
                }
                case 2: {
                    c20 = '\b';
                    break;
                }
                case 3: {
                    c20 = '1';
                    break;
                }
                default: {
                    c20 = '\u0011';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z2[n28] = new String(charArray10).intern();
        z = z2;
    }
}
