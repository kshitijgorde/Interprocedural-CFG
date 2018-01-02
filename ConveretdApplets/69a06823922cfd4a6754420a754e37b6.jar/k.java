import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.Color;
import java.applet.Applet;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class k extends Frame implements ActionListener
{
    private static final long serialVersionUID = 3339457634631866098L;
    protected Button a;
    protected Button b;
    private TextField c;
    private f d;
    private nb e;
    private static final String[] z;
    
    public k(final nb nb, final Applet applet) {
        this(k.z[1], nb, applet);
    }
    
    public k(final String s, final nb nb, final Applet applet) {
        final int c = d.c;
        super(s);
        this.setBackground(Color.white);
        this.c = new TextField("", 5);
        (this.a = new Button(k.z[9])).addActionListener(this);
        (this.b = new Button(k.z[4])).addActionListener(this);
        final Panel panel = new Panel(new mb(k.z[7]));
        panel.add(k.z[6], new Label(k.z[8]));
        panel.add(k.z[6], new Label(k.z[2]));
        panel.add(k.z[6], this.c);
        final Panel panel2 = new Panel(new lb(k.z[7]));
        panel2.add(k.z[5], this.b);
        panel2.add(k.z[3], this.a);
        panel.add(k.z[6], panel2);
        this.d = new f("", 300, 30, 2);
        panel.add(k.z[6], this.d);
        this.add(panel);
        panel.setVisible(true);
        this.validate();
        this.setResizable(false);
        this.pack();
        this.a(nb);
        this.addWindowListener(new g(this));
        if (c != 0) {
            int a = q.a;
            q.a = ++a;
        }
    }
    
    public void a(final nb e) {
        this.e = e;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.a) {
            this.setVisible(false);
            this.dispose();
        }
        if (actionEvent.getSource() == this.b) {
            this.a();
        }
    }
    
    private void a() {
        final int c = d.c;
        final String trim = this.c.getText().trim();
        if (trim.length() != 2) {
            this.d.a(k.z[0]);
            return;
        }
        final String string = trim + trim + trim + trim;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        final int[] array = new int[4];
        int i = string.length() - 1;
        while (i > -1) {
            if (n2 < 4) {
                n += (int)(string.charAt(i) * Math.pow(128.0, n3));
                if (++n3 >= (int)Math.floor(string.length() / 4)) {
                    n3 = 0;
                    array[n2] = n % 10000;
                    n = 0;
                    ++n2;
                }
            }
            --i;
            if (c != 0) {
                break;
            }
        }
        this.e.a(array);
        this.setVisible(false);
        this.dispose();
    }
    
    static {
        final String[] z2 = new String[10];
        final int n = 0;
        final char[] charArray = "F\u007fw4\tfe{z\u001d2q}fZkxgfZarwpZ\u007fba`Zpr2&Zq\u007fsf\u001bqcwf\t<".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u0012';
                    break;
                }
                case 1: {
                    c2 = '\u0017';
                    break;
                }
                case 2: {
                    c2 = '\u0012';
                    break;
                }
                case 3: {
                    c2 = '\u0014';
                    break;
                }
                default: {
                    c2 = 'z';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z2[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "ArwpZ@v|p\u0015\u007f7\\a\u0017pr`4=wywf\u001bfx`".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u0012';
                    break;
                }
                case 1: {
                    c4 = '\u0017';
                    break;
                }
                case 2: {
                    c4 = '\u0012';
                    break;
                }
                case 3: {
                    c4 = '\u0014';
                    break;
                }
                default: {
                    c4 = 'z';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z2[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "Wawf\u0003f\u007f{z\u001d2`{x\u00162uw4\nsef4\u0015t7f|\u001f2|wmV2~|w\u0016gs{z\u001d2dbu\u0019wd2u\u0014v7ba\u0014qcgu\u000e{x|:".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\u0012';
                    break;
                }
                case 1: {
                    c6 = '\u0017';
                    break;
                }
                case 2: {
                    c6 = '\u0012';
                    break;
                }
                case 3: {
                    c6 = '\u0014';
                    break;
                }
                default: {
                    c6 = 'z';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z2[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "Era`".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\u0012';
                    break;
                }
                case 1: {
                    c8 = '\u0017';
                    break;
                }
                case 2: {
                    c8 = '\u0012';
                    break;
                }
                case 3: {
                    c8 = '\u0014';
                    break;
                }
                default: {
                    c8 = 'z';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z2[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "Arf".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '\u0012';
                    break;
                }
                case 1: {
                    c10 = '\u0017';
                    break;
                }
                case 2: {
                    c10 = '\u0012';
                    break;
                }
                case 3: {
                    c10 = '\u0014';
                    break;
                }
                default: {
                    c10 = 'z';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z2[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "Wva`".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '\u0012';
                    break;
                }
                case 1: {
                    c12 = '\u0017';
                    break;
                }
                case 2: {
                    c12 = '\u0012';
                    break;
                }
                case 3: {
                    c12 = '\u0014';
                    break;
                }
                default: {
                    c12 = 'z';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z2[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "Qr|`\u001f`".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '\u0012';
                    break;
                }
                case 1: {
                    c14 = '\u0017';
                    break;
                }
                case 2: {
                    c14 = '\u0012';
                    break;
                }
                case 3: {
                    c14 = '\u0014';
                    break;
                }
                default: {
                    c14 = 'z';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z2[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "\\x``\u0012".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '\u0012';
                    break;
                }
                case 1: {
                    c16 = '\u0017';
                    break;
                }
                case 2: {
                    c16 = '\u0012';
                    break;
                }
                case 3: {
                    c16 = '\u0014';
                    break;
                }
                default: {
                    c16 = 'z';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z2[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "FnbqZs7yq\u00032ce{Zq\u007fsf\u001bqcwf\t2{}z\u001d2c}4\u001dwywf\u001bfr2uZarwpT".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '\u0012';
                    break;
                }
                case 1: {
                    c18 = '\u0017';
                    break;
                }
                case 2: {
                    c18 = '\u0012';
                    break;
                }
                case 3: {
                    c18 = '\u0014';
                    break;
                }
                default: {
                    c18 = 'z';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z2[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "Qv|w\u001f~".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '\u0012';
                    break;
                }
                case 1: {
                    c20 = '\u0017';
                    break;
                }
                case 2: {
                    c20 = '\u0012';
                    break;
                }
                case 3: {
                    c20 = '\u0014';
                    break;
                }
                default: {
                    c20 = 'z';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z2[n28] = new String(charArray10).intern();
        z = z2;
    }
}
