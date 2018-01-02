import java.awt.event.ActionEvent;
import java.awt.MenuBar;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.ScrollPane;
import java.applet.AppletStub;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.applet.Applet;
import java.util.Hashtable;
import java.awt.event.ActionListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class m extends Frame implements ActionListener
{
    protected static Hashtable a;
    private static final String[] z;
    
    public m(final Hashtable hashtable, final Applet applet) {
        if (hashtable == null) {
            System.out.println(m.z[4]);
            System.exit(1);
        }
        final String[] a = a(hashtable);
        if (a.length != 3) {
            System.out.println(m.z[3]);
            System.out.println(m.z[1]);
            System.out.println(m.z[5]);
            System.out.println(m.z[0]);
            System.exit(1);
        }
        this.setTitle(a[2]);
        final int int1 = Integer.parseInt(a[0]);
        final int int2 = Integer.parseInt(a[1]);
        this.setLayout(new GridLayout(1, 0));
        this.setSize(int1 + 20, int2 + 10);
        this.setVisible(true);
        applet.setSize(int1, int2);
        applet.setStub(new tb(this, applet, a[2] + m.z[2], a[2]));
        final ScrollPane scrollPane = new ScrollPane(0);
        scrollPane.add(applet);
        this.add(scrollPane);
        this.addWindowListener(new i(this));
        applet.init();
        applet.start();
    }
    
    public m(final Hashtable hashtable, final Applet applet, final MenuBar menuBar) {
        this(hashtable, applet);
        this.setMenuBar(menuBar);
    }
    
    public static String[] a(final Hashtable a) {
        final int c = d.c;
        m.a = a;
        final String[] array = { a(m.z[7]), a(m.z[12]), a(m.z[11]) };
        int i = 0;
        while (i < array.length) {
            if (array[i] == null || array[i].length() == 0) {
                System.out.println(m.z[6]);
                System.out.println(m.z[9]);
                System.out.println(m.z[8]);
                System.out.println(m.z[10]);
                System.exit(1);
            }
            ++i;
            if (c != 0) {
                break;
            }
        }
        return array;
    }
    
    public static String a(final String s) {
        return m.a.get(s);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
    }
    
    static {
        final String[] z2 = new String[13];
        final int n = 0;
        final char[] charArray = "c\u001c503G\u0000&el\u0018Nr\u00116GN&,*N\u000br11\u0002\u001a: ~U\u0007<!1U@".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\"';
                    break;
                }
                case 1: {
                    c2 = 'n';
                    break;
                }
                case 2: {
                    c2 = 'R';
                    break;
                }
                case 3: {
                    c2 = 'E';
                    break;
                }
                default: {
                    c2 = '^';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z2[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "c\u001c503G\u0000&en\u0018Nr\u0004.R\u0002;&?V\u0007=+~d\u001c3(;\u00029;!*J@rmf\u0017^{".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\"';
                    break;
                }
                case 1: {
                    c4 = 'n';
                    break;
                }
                case 2: {
                    c4 = 'R';
                    break;
                }
                case 3: {
                    c4 = 'E';
                    break;
                }
                default: {
                    c4 = '^';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z2[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\u0002/\"52G\u001a".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\"';
                    break;
                }
                case 1: {
                    c6 = 'n';
                    break;
                }
                case 2: {
                    c6 = 'R';
                    break;
                }
                case 3: {
                    c6 = 'E';
                    break;
                }
                default: {
                    c6 = '^';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z2[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "k\u0003\"71R\u000b e+Q\u000f5 p\u0002N\u0006-,G\u000br$,E\u001b? 0V\u001dr(+Q\u001ar';\u0002\u001e *(K\n7!~V\u0001r16GN35.N\u000b&k".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\"';
                    break;
                }
                case 1: {
                    c8 = 'n';
                    break;
                }
                case 2: {
                    c8 = 'R';
                    break;
                }
                case 3: {
                    c8 = 'E';
                    break;
                }
                default: {
                    c8 = '^';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z2[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "f+\u0004e\u001bp<\u001d\u0017d\u0002N\u0001-1F\u0001 \u0004.R\u0002;&?V\u0007=+\u0018P\u000f? ~A\u001c7$*G\nr27V\u0006r++N\u0002r5?P\u000f? *G\u001c!e\u0016C\u001d:1?@\u00027k~\u0002>> ?Q\u000br&6G\r9e*J\u000br &K\u001d&$0A\u000br*8\u0002\u001e37?O\u000b& ,Q@&=*".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '\"';
                    break;
                }
                case 1: {
                    c10 = 'n';
                    break;
                }
                case 2: {
                    c10 = 'R';
                    break;
                }
                case 3: {
                    c10 = 'E';
                    break;
                }
                default: {
                    c10 = '^';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z2[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "c\u001c503G\u0000&eo\u0018Nr\u0004.R\u0002;&?V\u0007=+~d\u001c3(;\u0002&7,9J\u001a|ev\u001a^bl".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '\"';
                    break;
                }
                case 1: {
                    c12 = 'n';
                    break;
                }
                case 2: {
                    c12 = 'R';
                    break;
                }
                case 3: {
                    c12 = 'E';
                    break;
                }
                default: {
                    c12 = '^';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z2[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "k\u0003\"71R\u000b e+Q\u000f5 p\u0002N\u0006-,G\u000br$,E\u001b? 0V\u001dr(+Q\u001ar';\u0002\u001e *(K\n7!~V\u0001r16GN35.N\u000b&e7LN\"$,C\u000371;P\u001d|1&V@revg\u00163(.N\u000br5,M\u0018;!;F@{".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '\"';
                    break;
                }
                case 1: {
                    c14 = 'n';
                    break;
                }
                case 2: {
                    c14 = 'R';
                    break;
                }
                case 3: {
                    c14 = 'E';
                    break;
                }
                default: {
                    c14 = '^';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z2[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "U\u0007616".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '\"';
                    break;
                }
                case 1: {
                    c16 = 'n';
                    break;
                }
                case 2: {
                    c16 = 'R';
                    break;
                }
                case 3: {
                    c16 = 'E';
                    break;
                }
                default: {
                    c16 = '^';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z2[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "J\u000b;\"6VSdun".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '\"';
                    break;
                }
                case 1: {
                    c18 = 'n';
                    break;
                }
                case 2: {
                    c18 = 'R';
                    break;
                }
                case 3: {
                    c18 = 'E';
                    break;
                }
                default: {
                    c18 = '^';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z2[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "U\u0007616\u001fVbu".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '\"';
                    break;
                }
                case 1: {
                    c20 = 'n';
                    break;
                }
                case 2: {
                    c20 = 'R';
                    break;
                }
                case 3: {
                    c20 = 'E';
                    break;
                }
                default: {
                    c20 = '^';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z2[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "V\u0007&);\u001f,==.N\u0001&".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = '\"';
                    break;
                }
                case 1: {
                    c22 = 'n';
                    break;
                }
                case 2: {
                    c22 = 'R';
                    break;
                }
                case 3: {
                    c22 = 'E';
                    break;
                }
                default: {
                    c22 = '^';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z2[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "V\u0007&);".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = '\"';
                    break;
                }
                case 1: {
                    c24 = 'n';
                    break;
                }
                case 2: {
                    c24 = 'R';
                    break;
                }
                case 3: {
                    c24 = 'E';
                    break;
                }
                default: {
                    c24 = '^';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        z2[n34] = new String(charArray12).intern();
        final int n37 = 12;
        final char[] charArray13 = "J\u000b;\"6V".toCharArray();
        final int length9 = charArray13.length;
        for (int n38 = 0; length9 > n38; ++n38) {
            final int n39 = n38;
            final char c25 = charArray13[n39];
            char c26 = '\0';
            switch (n38 % 5) {
                case 0: {
                    c26 = '\"';
                    break;
                }
                case 1: {
                    c26 = 'n';
                    break;
                }
                case 2: {
                    c26 = 'R';
                    break;
                }
                case 3: {
                    c26 = 'E';
                    break;
                }
                default: {
                    c26 = '^';
                    break;
                }
            }
            charArray13[n39] = (char)(c25 ^ c26);
        }
        z2[n37] = new String(charArray13).intern();
        z = z2;
    }
}
