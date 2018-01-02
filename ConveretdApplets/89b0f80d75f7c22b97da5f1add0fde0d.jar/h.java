import java.util.Vector;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class h extends KeyAdapter
{
    private final eb a;
    private static String[] z;
    
    h(final eb a) {
        this.a = a;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final int m = fb.m;
        int n5;
        int keyCode;
        int n4;
        int n3;
        int n2;
        final int n = n2 = (n3 = (n4 = (keyCode = (n5 = (this.a.d.startsWith("#") ? 1 : 0)))));
        final int n9;
        int n14 = 0;
        int n11 = 0;
        final int n10;
        Label_0253: {
            Label_0251: {
                if (m == 0) {
                    if (n == 0) {
                        final int n6 = n2 = (n3 = (n4 = (keyCode = (n5 = (this.a.d.equals(h.z[1]) ? 1 : 0)))));
                        if (m != 0) {
                            break Label_0251;
                        }
                        if (n6 == 0) {
                            final int n7 = n2 = (n3 = (n4 = (keyCode = (n5 = (eb.a(this.a).S.equalsIgnoreCase(h.z[3]) ? 1 : 0)))));
                            if (m != 0) {
                                break Label_0251;
                            }
                            if (n7 != 0) {
                                final int n8 = n2 = (n3 = (n4 = (keyCode = (n5 = (this.a.h ? 1 : 0)))));
                                if (m != 0) {
                                    break Label_0251;
                                }
                                if (n8 != 0) {
                                    final String text = this.a.c.getText();
                                    n9 = (n3 = (n4 = (keyCode = (n5 = keyEvent.getKeyCode()))));
                                    final int n13;
                                    final int n12;
                                    n10 = (n11 = (n12 = (n13 = (n14 = 191))));
                                    if (m != 0) {
                                        break Label_0253;
                                    }
                                    if (n9 != n10) {
                                        final int n15 = n2 = (n3 = (n4 = (keyCode = (n5 = (text.startsWith("/") ? 1 : 0)))));
                                        if (m != 0) {
                                            break Label_0251;
                                        }
                                        if (n15 == 0) {
                                            final x i = eb.a(this.a).i(this.a.d);
                                            if (m != 0 || i != null) {
                                                final int n16 = n2 = (n3 = (n4 = (keyCode = (n5 = (i.i ? 1 : 0)))));
                                                if (m != 0) {
                                                    break Label_0251;
                                                }
                                                if (n16 != 0) {
                                                    eb.a(this.a).a(String.valueOf(eb.a(this.a).gb) + " " + this.a.d + h.z[0] + ' ' + h.z[2], 1);
                                                    this.a.h = false;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    n3 = (n2 = (n4 = (keyCode = (n5 = keyEvent.getKeyCode()))));
                }
            }
            int n13;
            int n12;
            final int n17;
            n11 = (n17 = (n12 = (n13 = (n14 = 10))));
        }
        if (m == 0) {
            if (n9 == n10) {
                this.a.c.setCaretPosition(this.a.c.getText().length());
            }
            n3 = (keyCode = (n5 = keyEvent.getKeyCode()));
            final int n13;
            n11 = (n13 = (n14 = 38));
        }
        final int n18;
        final int n19;
        Label_0450: {
            if (m == 0) {
                if (n3 == n11) {
                    n18 = (keyCode = (n5 = this.a.c.getText().indexOf("\n")));
                    final int n13;
                    n19 = (n13 = (n14 = -1));
                    if (m != 0) {
                        break Label_0450;
                    }
                    if (n18 == n19) {
                        final eb a = this.a;
                        if (m == 0) {
                            if (a.g + 1 >= this.a.b.size()) {
                                final eb a2 = this.a;
                                if (m == 0) {
                                    if (a2.g < this.a.b.size()) {
                                        final eb a3 = this.a;
                                        ++a3.g;
                                    }
                                    final eb a4 = this.a;
                                }
                                a2.c.setText("");
                                return;
                            }
                            final eb a5 = this.a;
                            ++a5.g;
                            final eb a6 = this.a;
                        }
                        a.c.setText(this.a.b.elementAt(this.a.g));
                        return;
                    }
                }
                n5 = keyEvent.getKeyCode();
                n14 = 40;
            }
        }
        final int n20;
        final int n21;
        Label_0588: {
            if (m == 0) {
                if (n18 == n19) {
                    n20 = (n5 = this.a.c.getText().indexOf("\n"));
                    n21 = (n14 = -1);
                    if (m != 0) {
                        break Label_0588;
                    }
                    if (n20 == n21) {
                        final eb a7 = this.a;
                        if (m == 0) {
                            if (a7.g < 1) {
                                final eb a8 = this.a;
                                if (m == 0) {
                                    if (a8.g == 0) {
                                        this.a.g = -1;
                                    }
                                    final eb a9 = this.a;
                                }
                                a8.c.setText("");
                                return;
                            }
                            final eb a10 = this.a;
                            --a10.g;
                            final eb a11 = this.a;
                        }
                        a7.c.setText(this.a.b.elementAt(this.a.g));
                        if (m == 0) {
                            return;
                        }
                    }
                }
                n5 = (keyCode = keyEvent.getKeyCode());
                final int n13;
                n14 = (n13 = 17);
            }
        }
        if (m == 0) {
            if (n20 == n21) {
                this.a.setSize(eb.a(this.a).u.width, 30);
                this.a.f.setLocation(0, 50);
                this.a.e.setLocation(0, 0);
                this.a.c.setLocation(-2, 9);
                eb.a(this.a).Ob.validate();
                eb.a(this.a).Ob.invalidate();
                if (m == 0) {
                    return;
                }
            }
            n5 = keyEvent.getKeyCode();
            n14 = 18;
        }
        if (n5 == n14) {
            this.a.setSize(eb.a(this.a).u.width, 40);
            this.a.c.setLocation(-2, 19);
            this.a.e.setLocation(0, 50);
            this.a.f.setLocation(0, 0);
            eb.a(this.a).Ob.validate();
            eb.a(this.a).Ob.invalidate();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        final int m = fb.m;
        String s = this.a.c.getText();
        String s2 = "";
        String d;
        final String s3 = d = s;
        String s5;
        final String s4 = s5 = "\u0003";
        if (m == 0) {
            if (s3.startsWith(s4)) {
                s2 = "\u0003";
            }
            s = s.trim();
            final String s6;
            d = (s6 = s2);
            final String s7;
            s5 = (s7 = "");
        }
        if (m == 0) {
            if (s3 != s4) {
                s = String.valueOf(s2) + s;
            }
            d = this.a.d;
            s5 = "#";
        }
        int n4;
        int n3;
        int n2;
        final int n = n2 = (n3 = (n4 = (d.startsWith(s5) ? 1 : 0)));
        final int n6;
        Label_0272: {
            if (m == 0) {
                if (n == 0) {
                    final int n5 = n2 = (n3 = (n4 = (this.a.d.equals(h.z[1]) ? 1 : 0)));
                    if (m != 0) {
                        break Label_0272;
                    }
                    if (n5 == 0) {
                        n6 = (n3 = (n4 = s.length()));
                        if (m != 0) {
                            break Label_0272;
                        }
                        if (n6 == 0) {
                            final int n7 = n2 = (n3 = (n4 = (eb.a(this.a).S.equalsIgnoreCase(h.z[3]) ? 1 : 0)));
                            if (m != 0) {
                                break Label_0272;
                            }
                            if (n7 != 0) {
                                final x i = eb.a(this.a).i(this.a.d);
                                if (m != 0 || i != null) {
                                    final int n8 = n2 = (n3 = (n4 = (i.i ? 1 : 0)));
                                    if (m != 0) {
                                        break Label_0272;
                                    }
                                    if (n8 != 0) {
                                        eb.a(this.a).a(String.valueOf(eb.a(this.a).gb) + " " + this.a.d + h.z[0] + ' ' + h.z[4], 1);
                                    }
                                }
                            }
                        }
                    }
                }
                keyEvent.getKeyCode();
            }
        }
        final int n10;
        final int n9 = n10 = 18;
        final int n11;
        final int n12;
        final int height;
        Label_0429: {
            if (m == 0) {
                Label_0423: {
                    if (n6 != n9) {
                        n11 = (n4 = keyEvent.getKeyCode());
                        n12 = 17;
                        if (m != 0) {
                            break Label_0429;
                        }
                        if (n11 != n12) {
                            final eb a = this.a;
                            if (m == 0) {
                                if (a.getSize().height != 40) {
                                    height = this.a.getSize().height;
                                    final int n13 = 30;
                                    if (m != 0) {
                                        break Label_0429;
                                    }
                                    if (height != n13) {
                                        break Label_0423;
                                    }
                                }
                                this.a.setSize(eb.a(this.a).u.width, 20);
                                this.a.c.setLocation(-2, -1);
                                this.a.e.setLocation(0, 50);
                                this.a.f.setLocation(0, 50);
                                eb.a(this.a).Ob.validate();
                                final eb a2 = this.a;
                            }
                            eb.a(a).Ob.invalidate();
                        }
                    }
                }
                keyEvent.getKeyCode();
            }
        }
        final String d2;
        Label_1612: {
            int n17 = 0;
            Label_1599: {
                if (m == 0) {
                    Label_1572: {
                        if (n11 == n9) {
                            this.a.c.setCaretPosition(this.a.c.getText().length());
                            final String s8 = s;
                            if (m == 0 && s8.length() < 1) {
                                return;
                            }
                            String s9 = s8;
                            int index = 0;
                            int n16;
                            int n15;
                            final int n14 = n15 = (n16 = (n4 = (n17 = (this.a.d.equals(h.z[1]) ? 1 : 0))));
                            if (m == 0) {
                                if (n14 == 0) {
                                    eb a3 = null;
                                    while (true) {
                                        final String substring;
                                        Label_0671: {
                                            Label_0663: {
                                                if (s.indexOf("\n") <= -1) {
                                                    a3 = this.a;
                                                    if (m == 0) {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    index = s.indexOf("\n");
                                                    s9 = (substring = s.substring(0, index));
                                                    if (m != 0) {
                                                        break Label_0671;
                                                    }
                                                    if (substring.length() <= 0) {
                                                        break Label_0663;
                                                    }
                                                    eb.a(this.a).a(String.valueOf(eb.a(this.a).gb) + " " + this.a.d + h.z[0] + s9, 1);
                                                    eb.a(this.a).a("\n" + eb.a(this.a).a(24, eb.a(this.a).m, s9, ""));
                                                    final eb a4 = this.a;
                                                }
                                                a3.b.insertElementAt(s9, 0);
                                            }
                                            s.substring(index + 1);
                                        }
                                        s = substring;
                                    }
                                    a3.h = true;
                                }
                                final int n18;
                                n15 = (n18 = (n16 = (n4 = (n17 = (s.startsWith("/") ? 1 : 0)))));
                            }
                            if (m == 0) {
                                if (n14 != 0) {
                                    final boolean startsWith = s.startsWith(h.z[7]);
                                    final eb a5;
                                    Label_0980: {
                                        Label_0929: {
                                            if (m == 0) {
                                                if (!startsWith) {
                                                    break Label_0929;
                                                }
                                                a5 = this.a;
                                                if (m != 0) {
                                                    break Label_0980;
                                                }
                                                a5.d.equals(h.z[1]);
                                            }
                                            if (!startsWith) {
                                                if (s.length() > 4) {
                                                    eb.a(this.a).a("\n" + eb.a(this.a).a(25, eb.a(this.a).m, s.substring(3), ""));
                                                    eb.a(this.a).a(String.valueOf(eb.a(this.a).gb) + " " + this.a.d + h.z[0] + '\u0001' + h.z[5] + s.substring(3) + '\u0001', 1);
                                                    this.a.c.setText(h.z[6]);
                                                    this.a.c.setCaretPosition(5);
                                                    this.a.h = true;
                                                }
                                                return;
                                            }
                                        }
                                        this.a.c.setText("");
                                        this.a.c.setCaretPosition(0);
                                        eb.a(this.a).a(s, false);
                                        this.a.b.insertElementAt(s, 0);
                                        final eb a6 = this.a;
                                    }
                                    a5.h = true;
                                    return;
                                }
                                n16 = (n15 = (n4 = (n17 = (this.a.d.equals(h.z[1]) ? 1 : 0))));
                            }
                            if (m == 0) {
                                if (n15 == 0) {
                                    final int length = s.length();
                                    final TextArea c;
                                    Label_1505: {
                                        if (m == 0) {
                                            if (length > 0) {
                                                final eb a7 = this.a;
                                                Label_1188: {
                                                    Label_1131: {
                                                        if (m == 0) {
                                                            if (!a7.i) {
                                                                final eb a8 = this.a;
                                                                if (m != 0) {
                                                                    break Label_1131;
                                                                }
                                                                if (!a8.d.startsWith("#")) {
                                                                    eb.a(this.a).a(String.valueOf(eb.a(this.a).gb) + " " + this.a.d + h.z[0] + s + " ", 1);
                                                                    if (m == 0) {
                                                                        break Label_1188;
                                                                    }
                                                                }
                                                            }
                                                            final eb a9 = this.a;
                                                        }
                                                    }
                                                    eb.a(a7).a(String.valueOf(eb.a(this.a).gb) + " " + this.a.d + h.z[0] + s, 1);
                                                }
                                                this.a.i = true;
                                                this.a.b.insertElementAt(s, 0);
                                                eb.a(this.a).a("\n" + eb.a(this.a).a(24, eb.a(this.a).m, s, ""));
                                                this.a.h = true;
                                            }
                                            c = this.a.c;
                                            if (m != 0) {
                                                break Label_1505;
                                            }
                                            c.getText().charAt(0);
                                        }
                                        if (length == 3) {
                                            String substring2 = this.a.c.getText().substring(0, 2);
                                            final String substring3 = this.a.c.getText().substring(2, 3);
                                            if (m == 0) {
                                                Label_1451: {
                                                    if (substring2.substring(1, 2).equals("1")) {
                                                        final String s10 = substring3;
                                                        Label_1450: {
                                                            if (m == 0) {
                                                                if (!s10.equals("0")) {
                                                                    final String s11 = substring3;
                                                                    if (m != 0) {
                                                                        break Label_1450;
                                                                    }
                                                                    if (!s11.equals("1")) {
                                                                        final String s12 = substring3;
                                                                        if (m != 0) {
                                                                            break Label_1450;
                                                                        }
                                                                        if (!s12.equals("2")) {
                                                                            final String s13 = substring3;
                                                                            if (m != 0) {
                                                                                break Label_1450;
                                                                            }
                                                                            if (!s13.equals("3")) {
                                                                                final String s14 = substring3;
                                                                                if (m != 0) {
                                                                                    break Label_1450;
                                                                                }
                                                                                if (!s14.equals("4")) {
                                                                                    final String s15 = substring3;
                                                                                    if (m != 0) {
                                                                                        break Label_1450;
                                                                                    }
                                                                                    if (!s15.equals("5")) {
                                                                                        break Label_1451;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                new StringBuffer(String.valueOf(substring2)).append(substring3).toString();
                                                            }
                                                        }
                                                        substring2 = s10;
                                                    }
                                                }
                                                this.a.c.setText(substring2);
                                                this.a.c.setCaretPosition(this.a.c.getText().length());
                                            }
                                            return;
                                        }
                                        this.a.c.setText("");
                                        final TextArea c2 = this.a.c;
                                    }
                                    c.setCaretPosition(0);
                                    return;
                                }
                                n16 = (n17 = (this.a.d.equals(h.z[1]) ? 1 : 0));
                            }
                            if (m != 0) {
                                break Label_1572;
                            }
                            if (n16 != 0) {
                                this.a.b.insertElementAt(s, 0);
                                this.a.c.setText("");
                                this.a.c.setCaretPosition(0);
                            }
                        }
                        n17 = (n4 = keyEvent.getKeyCode());
                    }
                    if (m != 0) {
                        break Label_1599;
                    }
                }
                if (height != n12) {
                    return;
                }
                d2 = this.a.d;
                if (m != 0) {
                    break Label_1612;
                }
                n17 = (d2.startsWith("#") ? 1 : 0);
            }
            if (n17 == 0) {
                return;
            }
            this.a.c.getText();
        }
        final String s16 = d2;
        String text = s16.substring(0, s16.length() - 1);
        this.a.c.setText(text);
        int index2 = 0;
        v h;
        while (true) {
            while (true) {
                Label_1669: {
                    if (m == 0) {
                        break Label_1669;
                    }
                    index2 = text.indexOf(" ");
                    text = text.substring(index2 + 1);
                }
                if (text.indexOf(" ") >= 0) {
                    continue;
                }
                break;
            }
            h = eb.a(this.a).h(this.a.d);
            if (m != 0) {
                continue;
            }
            break;
        }
        final v v = h;
        if (m != 0 || v != null) {
            final Vector d3 = v.b.b.b.d();
            int n19 = 0;
            while (true) {
                Label_1992: {
                    if (m == 0) {
                        break Label_1992;
                    }
                    final boolean equals;
                    final boolean b = equals = d3.elementAt(n19).toString().equals(text);
                    final int n20;
                    Label_1893: {
                        if (m == 0) {
                            if (b) {
                                n20 = n19;
                                if (m != 0) {
                                    break Label_1893;
                                }
                                if (n20 < d3.size() - 1) {
                                    this.a.c.setText(String.valueOf(this.a.c.getText().substring(0, this.a.c.getText().length() - text.length())) + d3.elementAt(n19 + 1).toString());
                                    this.a.c.setCaretPosition(this.a.c.getText().length());
                                    return;
                                }
                            }
                            d3.elementAt(n19).toString().toLowerCase().startsWith(text.toLowerCase());
                        }
                    }
                    if (n20 != 0) {
                        this.a.c.setText(String.valueOf(this.a.c.getText().substring(0, this.a.c.getText().length() - text.length())) + d3.elementAt(n19).toString());
                        this.a.c.setCaretPosition(this.a.c.getText().length());
                        return;
                    }
                    ++n19;
                }
                if (n19 < d3.size()) {
                    continue;
                }
                break;
            }
        }
    }
    
    static {
        final String[] z = new String[8];
        final int n = 0;
        final char[] charArray = "Dp".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'd';
                    break;
                }
                case 1: {
                    c2 = 'J';
                    break;
                }
                case 2: {
                    c2 = 'Q';
                    break;
                }
                case 3: {
                    c2 = '*';
                    break;
                }
                default: {
                    c2 = '7';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "7>0^B\u0017".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'd';
                    break;
                }
                case 1: {
                    c4 = 'J';
                    break;
                }
                case 2: {
                    c4 = 'Q';
                    break;
                }
                case 3: {
                    c4 = '*';
                    break;
                }
                default: {
                    c4 = '7';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "0\u0013\u0001o\u0017Uj".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'd';
                    break;
                }
                case 1: {
                    c6 = 'J';
                    break;
                }
                case 2: {
                    c6 = 'Q';
                    break;
                }
                case 3: {
                    c6 = '*';
                    break;
                }
                default: {
                    c6 = '7';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "+$".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'd';
                    break;
                }
                case 1: {
                    c8 = 'J';
                    break;
                }
                case 2: {
                    c8 = 'Q';
                    break;
                }
                case 3: {
                    c8 = '*';
                    break;
                }
                default: {
                    c8 = '7';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "0\u0013\u0001o\u0017Tj".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'd';
                    break;
                }
                case 1: {
                    c10 = 'J';
                    break;
                }
                case 2: {
                    c10 = 'Q';
                    break;
                }
                case 3: {
                    c10 = '*';
                    break;
                }
                default: {
                    c10 = '7';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "%\t\u0005cx*".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'd';
                    break;
                }
                case 1: {
                    c12 = 'J';
                    break;
                }
                case 2: {
                    c12 = 'Q';
                    break;
                }
                case 3: {
                    c12 = '*';
                    break;
                }
                default: {
                    c12 = '7';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "K'4\n".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'd';
                    break;
                }
                case 1: {
                    c14 = 'J';
                    break;
                }
                case 2: {
                    c14 = 'Q';
                    break;
                }
                case 3: {
                    c14 = '*';
                    break;
                }
                default: {
                    c14 = '7';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "K'4".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 'd';
                    break;
                }
                case 1: {
                    c16 = 'J';
                    break;
                }
                case 2: {
                    c16 = 'Q';
                    break;
                }
                case 3: {
                    c16 = '*';
                    break;
                }
                default: {
                    c16 = '7';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        h.z = z;
    }
}
