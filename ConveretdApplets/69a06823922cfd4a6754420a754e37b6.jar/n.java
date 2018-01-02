import java.awt.event.ActionEvent;
import java.awt.MenuShortcut;
import java.awt.CheckboxMenuItem;
import java.awt.MenuItem;
import java.awt.Menu;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.MenuBar;

// 
// Decompiled by Procyon v0.5.30
// 

public class n extends MenuBar implements ActionListener
{
    private static final long serialVersionUID = -2245754191497395604L;
    private Vector a;
    private static final String[] z;
    
    public n() {
        this.a = null;
        this.a = new Vector();
        System.setProperty(n.z[9], n.z[10]);
    }
    
    public n(final ActionListener actionListener) {
        this();
        this.a(actionListener);
    }
    
    public Menu a(final Menu menu, final String s) {
        final Menu menu2 = new Menu(s);
        if (menu == null) {
            this.add(menu2);
            if (d.c == 0) {
                return menu2;
            }
        }
        menu.add(menu2);
        return menu2;
    }
    
    public Menu a(final String s) {
        return this.a(null, s);
    }
    
    public void a(final Menu menu, final String s, final boolean b, final Integer n) {
        MenuItem menuItem = null;
        Label_0030: {
            if (b) {
                menuItem = new CheckboxMenuItem(s);
                if (d.c == 0) {
                    break Label_0030;
                }
            }
            menuItem = new MenuItem(s);
        }
        menuItem.addActionListener(this);
        if (n != null) {
            menuItem.setShortcut(new MenuShortcut(n));
        }
        menu.add(menuItem);
    }
    
    public void a(final Menu menu, final String s, final Integer n) {
        this.a(menu, s, false, n);
    }
    
    public void a(final ActionListener actionListener) {
        if (actionListener == null) {
            System.out.println(n.z[12]);
        }
        if (this.a == null) {
            System.out.println(n.z[11]);
        }
        this.a.addElement(actionListener);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final int c = d.c;
        Label_0048: {
            if (this.a != null) {
                int i = 0;
                while (i < this.a.size()) {
                    ((ActionListener)this.a.elementAt(i)).actionPerformed(actionEvent);
                    ++i;
                    if (c != 0) {
                        break Label_0048;
                    }
                }
                return;
            }
        }
        System.out.println(n.z[8]);
    }
    
    public static n a(final int[] array, final ActionListener actionListener) {
        final n n = new n(actionListener);
        final Menu a = n.a(n.z[2]);
        if (a(array, 0)) {
            n.a(a, n.z[5], new Integer(79));
            n.a(a, n.z[7], new Integer(83));
        }
        if (a(array, 1)) {
            n.a(a, n.z[6], new Integer(73));
            n.a(a, n.z[3], new Integer(69));
        }
        if (a(array, 2)) {
            n.a(a, n.z[4], new Integer(84));
            n.a(a, n.z[0], new Integer(89));
        }
        n.a(a, n.z[1], new Integer(88));
        return n;
    }
    
    public static n b(final ActionListener actionListener) {
        return a(new int[0], actionListener);
    }
    
    public static boolean a(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (!(source instanceof MenuItem)) {
            return false;
        }
        if (a((MenuItem)source, n.z[1])) {
            System.exit(0);
        }
        return false;
    }
    
    public static boolean a(final MenuItem menuItem, final String s) {
        return menuItem.getLabel().trim().equalsIgnoreCase(s.trim());
    }
    
    private static boolean a(final int[] array, final int n) {
        final int c = d.c;
        int i = 0;
        while (i < array.length) {
            if (array[i] == n) {
                return true;
            }
            ++i;
            if (c != 0) {
                break;
            }
        }
        return false;
    }
    
    static {
        final String[] z2 = new String[13];
        final int n = 0;
        final char[] charArray = "(\u0017Syy/\u0013]h".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '{';
                    break;
                }
                case 1: {
                    c2 = 'v';
                    break;
                }
                case 2: {
                    c2 = '%';
                    break;
                }
                case 3: {
                    c2 = '\u001c';
                    break;
                }
                default: {
                    c2 = 'Y';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z2[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = ">\u000eLh".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '{';
                    break;
                }
                case 1: {
                    c4 = 'v';
                    break;
                }
                case 2: {
                    c4 = '%';
                    break;
                }
                case 3: {
                    c4 = '\u001c';
                    break;
                }
                default: {
                    c4 = 'Y';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z2[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "=\u001fIy".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '{';
                    break;
                }
                case 1: {
                    c6 = 'v';
                    break;
                }
                case 2: {
                    c6 = '%';
                    break;
                }
                case 3: {
                    c6 = '\u001c';
                    break;
                }
                default: {
                    c6 = 'Y';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z2[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = ">\u000eUs+\u000fVfO\u000f".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '{';
                    break;
                }
                case 1: {
                    c8 = 'v';
                    break;
                }
                case 2: {
                    c8 = '%';
                    break;
                }
                case 3: {
                    c8 = '\u001c';
                    break;
                }
                default: {
                    c8 = 'Y';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z2[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "4\u0006@ry/\u0013]h".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '{';
                    break;
                }
                case 1: {
                    c10 = 'v';
                    break;
                }
                case 2: {
                    c10 = '%';
                    break;
                }
                case 3: {
                    c10 = '\u001c';
                    break;
                }
                default: {
                    c10 = 'Y';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z2[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "4\u0006@r".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '{';
                    break;
                }
                case 1: {
                    c12 = 'v';
                    break;
                }
                case 2: {
                    c12 = '%';
                    break;
                }
                case 3: {
                    c12 = '\u001c';
                    break;
                }
                default: {
                    c12 = 'Y';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z2[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "2\u001bUs+\u000fVfO\u000f".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '{';
                    break;
                }
                case 1: {
                    c14 = 'v';
                    break;
                }
                case 2: {
                    c14 = '%';
                    break;
                }
                case 3: {
                    c14 = '\u001c';
                    break;
                }
                default: {
                    c14 = 'Y';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z2[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "(\u0017Sy".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '{';
                    break;
                }
                case 1: {
                    c16 = 'v';
                    break;
                }
                case 2: {
                    c16 = '%';
                    break;
                }
                case 3: {
                    c16 = '\u001c';
                    break;
                }
                default: {
                    c16 = 'Y';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z2[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "?3s<\u001c)$jNy6\u0013Ki\u001a\t\u0013Dh6\tL\u0005R6[7Fh0\u0014\u0018iu*\u000f\u0013Ky+[\u0002J<7\u0014\u0002Lz U".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '{';
                    break;
                }
                case 1: {
                    c18 = 'v';
                    break;
                }
                case 2: {
                    c18 = '%';
                    break;
                }
                case 3: {
                    c18 = '\u001c';
                    break;
                }
                default: {
                    c18 = 'Y';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z2[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "\u001a\u0006Up<U\u001aDzw\u000e\u0005@O:\t\u0013@r\u0014\u001e\u0018P^8\t".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '{';
                    break;
                }
                case 1: {
                    c20 = 'v';
                    break;
                }
                case 2: {
                    c20 = '%';
                    break;
                }
                case 3: {
                    c20 = '\u001c';
                    break;
                }
                default: {
                    c20 = 'Y';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z2[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "\u000f\u0004Py".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = '{';
                    break;
                }
                case 1: {
                    c22 = 'v';
                    break;
                }
                case 2: {
                    c22 = '%';
                    break;
                }
                case 3: {
                    c22 = '\u001c';
                    break;
                }
                default: {
                    c22 = 'Y';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z2[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "5#iPy-\u0013Fh6\t".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = '{';
                    break;
                }
                case 1: {
                    c24 = 'v';
                    break;
                }
                case 2: {
                    c24 = '%';
                    break;
                }
                case 3: {
                    c24 = '\u001c';
                    break;
                }
                default: {
                    c24 = 'Y';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        z2[n34] = new String(charArray12).intern();
        final int n37 = 12;
        final char[] charArray13 = "5#iPy:5qU\u00165ViU\n/3kY\u000b".toCharArray();
        final int length9 = charArray13.length;
        for (int n38 = 0; length9 > n38; ++n38) {
            final int n39 = n38;
            final char c25 = charArray13[n39];
            char c26 = '\0';
            switch (n38 % 5) {
                case 0: {
                    c26 = '{';
                    break;
                }
                case 1: {
                    c26 = 'v';
                    break;
                }
                case 2: {
                    c26 = '%';
                    break;
                }
                case 3: {
                    c26 = '\u001c';
                    break;
                }
                default: {
                    c26 = 'Y';
                    break;
                }
            }
            charArray13[n39] = (char)(c25 ^ c26);
        }
        z2[n37] = new String(charArray13).intern();
        z = z2;
    }
}
