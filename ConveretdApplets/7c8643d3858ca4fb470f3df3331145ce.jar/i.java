import java.util.Vector;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class i extends KeyAdapter
{
    private final fb a;
    private static String[] z;
    
    i(final fb a) {
        this.a = a;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final boolean r = d.r;
        int n2;
        int keyCode;
        final int n = keyCode = (n2 = keyEvent.getModifiers());
        int n5;
        int n4;
        final int n3 = n4 = (n5 = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        if (!r) {
            Label_0047: {
                if (n == n3) {
                    final int n7;
                    final int n6 = n7 = (keyCode = (n2 = (this.a.j ? 1 : 0)));
                    if (r) {
                        break Label_0047;
                    }
                    if (n6 != 0) {
                        keyEvent.consume();
                        return;
                    }
                }
                int n7;
                keyCode = (n7 = (n2 = keyEvent.getKeyCode()));
            }
            final int n8;
            n4 = (n8 = (n5 = 17));
        }
        boolean b = false;
        Label_0305: {
            Label_0279: {
                Label_0264: {
                    if (!r) {
                        if (n == n3) {
                            this.a.j = true;
                            this.a.setSize(fb.a(this.a).w.width, 30);
                            this.a.f.setLocation(0, 50);
                            this.a.e.setLocation(0, 0);
                            this.a.c.setLocation(0, 9);
                            fb.a(this.a).Zb.validate();
                            fb.a(this.a).Zb.invalidate();
                            if (!r) {
                                break Label_0264;
                            }
                        }
                        n2 = (keyCode = keyEvent.getKeyCode());
                        n5 = (n4 = 18);
                    }
                    if (r) {
                        break Label_0279;
                    }
                    if (keyCode == n4) {
                        this.a.j = true;
                        this.a.setSize(fb.a(this.a).w.width, 40);
                        this.a.c.setLocation(0, 19);
                        this.a.e.setLocation(0, 50);
                        this.a.f.setLocation(0, 0);
                        fb.a(this.a).Zb.validate();
                        fb.a(this.a).Zb.invalidate();
                    }
                }
                final int n13;
                final boolean b6;
                final int n12;
                final boolean b5;
                final int n11;
                final boolean b4;
                final int n10;
                final boolean b3;
                final int n9;
                final boolean b2;
                b = ((n2 = ((b2 = ((n9 = ((b3 = ((n10 = ((b4 = ((n11 = ((b5 = ((n12 = ((b6 = ((n13 = keyEvent.getModifiers()) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0);
                if (r) {
                    break Label_0305;
                }
                n5 = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
            }
            if (n2 == n5) {
                keyEvent.consume();
                return;
            }
            int n13;
            boolean b6;
            int n12;
            boolean b5;
            int n11;
            boolean b4;
            int n10;
            boolean b3;
            int n9;
            b = ((n9 = ((b3 = ((n10 = ((b4 = ((n11 = ((b5 = ((n12 = ((b6 = ((n13 = (fb.a(this.a).T.equalsIgnoreCase(i.z[3]) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0);
        }
        final boolean b7;
        Label_0381: {
            if (!r) {
                if (b) {
                    final int n13;
                    final boolean b6;
                    final int n12;
                    final boolean b5;
                    final int n11;
                    final boolean b4;
                    final int n10;
                    final boolean b3;
                    final int n9;
                    b7 = ((n9 = ((b3 = ((n10 = ((b4 = ((n11 = ((b5 = ((n12 = ((b6 = ((n13 = keyEvent.getKeyCode()) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0);
                    if (r) {
                        break Label_0381;
                    }
                    if ((b7 ? 1 : 0) == 525) {
                        fb.a(this.a).Fb.show(fb.a(this.a).Zb.e, 100, 100);
                        keyEvent.consume();
                        return;
                    }
                }
                fb.a(this.a).T.equalsIgnoreCase(i.z[3]);
            }
        }
        final int n14;
        Label_0451: {
            if (!r) {
                if (b7) {
                    final int n13;
                    final boolean b6;
                    final int n12;
                    final boolean b5;
                    final int n11;
                    final boolean b4;
                    final int n10;
                    final boolean b3;
                    n14 = ((b3 = ((n10 = ((b4 = ((n11 = ((b5 = ((n12 = ((b6 = ((n13 = keyEvent.getKeyCode()) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0);
                    if (r) {
                        break Label_0451;
                    }
                    if (n14 == 524) {
                        fb.a(this.a).Fb.show(fb.a(this.a).Zb.e, 100, 100);
                        keyEvent.consume();
                        return;
                    }
                }
                this.a.d.startsWith("#");
            }
        }
        final boolean b8;
        final int n15;
        final boolean b9;
        final int n16;
        final boolean b11;
        final int n17;
        Label_0699: {
            if (!r) {
                final esChat a;
                Label_0688: {
                    if (n14 == 0) {
                        int n13;
                        boolean b6;
                        int n12;
                        boolean b5;
                        int n11;
                        boolean b4;
                        final int n10;
                        b8 = ((n10 = ((b4 = ((n11 = ((b5 = ((n12 = ((b6 = ((n13 = (this.a.d.equals(i.z[4]) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0);
                        if (r) {
                            break Label_0699;
                        }
                        if (!b8) {
                            n15 = ((b4 = ((n11 = ((b5 = ((n12 = ((b6 = ((n13 = (fb.a(this.a).Z.equalsIgnoreCase(i.z[2]) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0);
                            if (r) {
                                break Label_0699;
                            }
                            if (n15 != 0) {
                                b9 = ((n11 = ((b5 = ((n12 = ((b6 = ((n13 = (this.a.h ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0);
                                if (r) {
                                    break Label_0699;
                                }
                                if (b9) {
                                    final String b10 = this.a.c.b();
                                    n16 = ((b5 = ((n12 = ((b6 = ((n13 = keyEvent.getKeyCode()) != 0)) ? 1 : 0)) != 0)) ? 1 : 0);
                                    if (r) {
                                        break Label_0699;
                                    }
                                    if (n16 != 191) {
                                        b11 = ((n12 = ((b6 = ((n13 = (b10.startsWith("/") ? 1 : 0)) != 0)) ? 1 : 0)) != 0);
                                        if (r) {
                                            break Label_0699;
                                        }
                                        if (!b11) {
                                            a = fb.a(this.a);
                                            if (r) {
                                                break Label_0688;
                                            }
                                            final y i = a.i(this.a.d);
                                            if (i != null) {
                                                n17 = ((b6 = ((n13 = (i.i ? 1 : 0)) != 0)) ? 1 : 0);
                                                if (r) {
                                                    break Label_0699;
                                                }
                                                if (n17 != 0) {
                                                    fb.a(this.a).a(String.valueOf(fb.a(this.a).ob) + " " + this.a.d + i.z[1] + ' ' + i.z[0], 1);
                                                    this.a.h = false;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    fb.a(this.a);
                }
                a.T.equalsIgnoreCase(i.z[3]);
            }
        }
        final int n18;
        Label_0745: {
            if (!r) {
                if (b8) {
                    final int n13;
                    n18 = (n13 = keyEvent.getKeyCode());
                    if (r) {
                        break Label_0745;
                    }
                    if (n18 == 155) {
                        keyEvent.consume();
                        return;
                    }
                }
                fb.a(this.a).T.equalsIgnoreCase(i.z[3]);
            }
        }
        final int controlDown;
        Label_0802: {
            if (!r) {
                if (n15 != 0) {
                    controlDown = (keyEvent.isControlDown() ? 1 : 0);
                    if (r) {
                        break Label_0802;
                    }
                    if (controlDown != 0) {
                        final int keyCode2 = keyEvent.getKeyCode();
                        if (r) {
                            break Label_0802;
                        }
                        if (keyCode2 == 86) {
                            keyEvent.consume();
                            return;
                        }
                    }
                }
                fb.a(this.a).T.equalsIgnoreCase(i.z[3]);
            }
        }
        Label_0848: {
            if (!r) {
                if (b9) {
                    final int keyCode3 = keyEvent.getKeyCode();
                    if (r) {
                        break Label_0848;
                    }
                    if (keyCode3 == 155) {
                        keyEvent.consume();
                        return;
                    }
                }
                fb.a(this.a).T.equalsIgnoreCase(i.z[5]);
            }
        }
        if (!r) {
            if (n16 == 0) {
                return;
            }
            keyEvent.getKeyCode();
        }
        int n21;
        final int n20;
        final int n19 = n20 = (n21 = 38);
        final int n22;
        final int n23;
        Label_1022: {
            if (!r) {
                if ((b11 ? 1 : 0) == n19) {
                    final int index = this.a.c.b().indexOf("\n");
                    n22 = (n21 = -1);
                    if (r) {
                        break Label_1022;
                    }
                    if (index == n22) {
                        final fb a2 = this.a;
                        if (!r) {
                            if (a2.g + 1 >= this.a.b.size()) {
                                final fb a3 = this.a;
                                if (!r) {
                                    if (a3.g < this.a.b.size()) {
                                        final fb a4 = this.a;
                                        ++a4.g;
                                    }
                                    final fb a5 = this.a;
                                }
                                a3.c.a("");
                                return;
                            }
                            final fb a6 = this.a;
                            ++a6.g;
                            final fb a7 = this.a;
                        }
                        a2.c.a(this.a.b.elementAt(this.a.g));
                        return;
                    }
                }
                keyEvent.getKeyCode();
                n23 = 40;
            }
        }
        if (!r) {
            if (n17 != n19) {
                return;
            }
            this.a.c.b().indexOf("\n");
        }
        final fb a8;
        Label_1123: {
            if (!r) {
                if (n18 != n22) {
                    return;
                }
                a8 = this.a;
                if (r) {
                    break Label_1123;
                }
                final int n13 = a8.g;
            }
            if (controlDown < n23) {
                final fb a9 = this.a;
                if (!r) {
                    if (a9.g == 0) {
                        this.a.g = -1;
                    }
                    final fb a10 = this.a;
                }
                a9.c.a("");
                return;
            }
            final fb a11 = this.a;
            --a11.g;
            final fb a12 = this.a;
        }
        a8.c.a(this.a.b.elementAt(this.a.g));
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        final boolean r = d.r;
        int n3;
        final int n2;
        final int n = n2 = (n3 = keyEvent.getModifiers());
        if (!r) {
            if (n == Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()) {
                keyEvent.consume();
                return;
            }
            fb.a(this.a).T.equalsIgnoreCase(i.z[3]);
        }
        final int n4;
        Label_0122: {
            if (!r) {
                if (n != 0) {
                    n4 = (n3 = keyEvent.getKeyCode());
                    if (r) {
                        break Label_0122;
                    }
                    if (n4 == 525) {
                        fb.a(this.a).Fb.show(fb.a(this.a).Zb.e, 100, 100);
                        keyEvent.consume();
                        return;
                    }
                }
                fb.a(this.a).T.equalsIgnoreCase(i.z[3]);
            }
        }
        Label_0225: {
            final int keyCode;
            Label_0203: {
                if (!r) {
                    if (n4 != 0) {
                        keyCode = keyEvent.getKeyCode();
                        if (r) {
                            break Label_0203;
                        }
                        if (keyCode == 524) {
                            fb.a(this.a).Fb.show(fb.a(this.a).Zb.e, 100, 100);
                            keyEvent.consume();
                            return;
                        }
                    }
                    final String t = fb.a(this.a).T;
                    if (r) {
                        break Label_0225;
                    }
                    t.equalsIgnoreCase(i.z[3]);
                }
            }
            if (keyCode != 0) {
                KeyEvent keyEvent2 = keyEvent;
                if (!r) {
                    if (keyEvent.getKeyCode() != 17) {
                        break Label_0225;
                    }
                    keyEvent2 = keyEvent;
                }
                keyEvent2.consume();
                return;
            }
        }
        String s = this.a.c.b();
        String s2 = "";
        String d;
        final String s3 = d = s;
        String s5;
        final String s4 = s5 = "\u0003";
        if (!r) {
            if (s3.startsWith(s4)) {
                s2 = "\u0003";
            }
            s = s.trim();
            final String s6;
            d = (s6 = s2);
            final String s7;
            s5 = (s7 = "");
        }
        if (!r) {
            if (s3 != s4) {
                s = String.valueOf(s2) + s;
            }
            d = this.a.d;
            s5 = "#";
        }
        int n8;
        int n7;
        int n6;
        final int n5 = n6 = (n7 = (n8 = (d.startsWith(s5) ? 1 : 0)));
        final int n10;
        Label_0493: {
            if (!r) {
                if (n5 == 0) {
                    final int n9 = n6 = (n7 = (n8 = (this.a.d.equals(i.z[4]) ? 1 : 0)));
                    if (r) {
                        break Label_0493;
                    }
                    if (n9 == 0) {
                        n10 = (n7 = (n8 = s.length()));
                        if (r) {
                            break Label_0493;
                        }
                        if (n10 == 0) {
                            final int n11 = n6 = (n7 = (n8 = (fb.a(this.a).Z.equalsIgnoreCase(i.z[2]) ? 1 : 0)));
                            if (r) {
                                break Label_0493;
                            }
                            if (n11 != 0) {
                                final y i = fb.a(this.a).i(this.a.d);
                                if (r || i != null) {
                                    final int n12 = n6 = (n7 = (n8 = (i.i ? 1 : 0)));
                                    if (r) {
                                        break Label_0493;
                                    }
                                    if (n12 != 0) {
                                        fb.a(this.a).a(String.valueOf(fb.a(this.a).ob) + " " + this.a.d + i.z[1] + ' ' + i.z[10], 1);
                                    }
                                }
                            }
                        }
                    }
                }
                keyEvent.getKeyCode();
            }
        }
        final int n14;
        final int n13 = n14 = 18;
        final int n15;
        final int n16;
        final int keyCode2;
        Label_0685: {
            if (!r) {
                Label_0679: {
                    if (n10 != n13) {
                        n15 = (n8 = keyEvent.getKeyCode());
                        n16 = 17;
                        if (r) {
                            break Label_0685;
                        }
                        if (n15 != n16) {
                            keyCode2 = keyEvent.getKeyCode();
                            final int n17 = 86;
                            if (r) {
                                break Label_0685;
                            }
                            if (keyCode2 != n17) {
                                final int keyCode3 = keyEvent.getKeyCode();
                                final int n18 = 86;
                                if (r) {
                                    break Label_0685;
                                }
                                if (keyCode3 != n18) {
                                    final fb a = this.a;
                                    if (!r) {
                                        if (a.getSize().height != 40) {
                                            final int height = this.a.getSize().height;
                                            final int n19 = 30;
                                            if (r) {
                                                break Label_0685;
                                            }
                                            if (height != n19) {
                                                break Label_0679;
                                            }
                                        }
                                        this.a.setSize(fb.a(this.a).w.width, 20);
                                        this.a.c.setLocation(0, 0);
                                        this.a.e.setLocation(0, 50);
                                        this.a.f.setLocation(0, 50);
                                        fb.a(this.a).Zb.validate();
                                        fb.a(this.a).Zb.invalidate();
                                        final fb a2 = this.a;
                                    }
                                    a.j = false;
                                }
                            }
                        }
                    }
                }
                keyEvent.getKeyCode();
            }
        }
        final String d2;
        Label_1907: {
            int n23 = 0;
            Label_1894: {
                if (!r) {
                    Label_1867: {
                        if (n15 == n13) {
                            final String s8 = s;
                            if (!r && s8.length() < 1) {
                                return;
                            }
                            String s9 = s8;
                            int index = 0;
                            int n22;
                            int n21;
                            final int n20 = n21 = (n22 = (n8 = (n23 = (this.a.d.equals(i.z[4]) ? 1 : 0))));
                            if (!r) {
                                if (n20 == 0) {
                                    fb a3 = null;
                                    while (true) {
                                        final String substring;
                                        Label_0904: {
                                            Label_0896: {
                                                if (s.indexOf("\n") <= -1) {
                                                    a3 = this.a;
                                                    if (!r) {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    index = s.indexOf("\n");
                                                    s9 = (substring = s.substring(0, index));
                                                    if (r) {
                                                        break Label_0904;
                                                    }
                                                    if (substring.length() <= 0) {
                                                        break Label_0896;
                                                    }
                                                    fb.a(this.a).a(String.valueOf(fb.a(this.a).ob) + " " + this.a.d + i.z[1] + s9, 1);
                                                    fb.a(this.a).a("\n" + fb.a(this.a).a(24, fb.a(this.a).n, s9, ""));
                                                    final fb a4 = this.a;
                                                }
                                                a3.b.insertElementAt(s9, 0);
                                            }
                                            s.substring(index + 1);
                                        }
                                        s = substring;
                                    }
                                    a3.h = true;
                                }
                                final int n24;
                                n21 = (n24 = (n22 = (n8 = (n23 = (s.startsWith("/") ? 1 : 0)))));
                            }
                            if (!r) {
                                if (n20 != 0) {
                                    final boolean startsWith = s.startsWith(i.z[6]);
                                    final fb a5;
                                    Label_1192: {
                                        Label_1152: {
                                            if (!r) {
                                                if (!startsWith) {
                                                    break Label_1152;
                                                }
                                                a5 = this.a;
                                                if (r) {
                                                    break Label_1192;
                                                }
                                                a5.d.equals(i.z[4]);
                                            }
                                            if (!startsWith) {
                                                if (s.length() > 4) {
                                                    fb.a(this.a).a("\n" + fb.a(this.a).a(25, fb.a(this.a).n, s.substring(3), ""));
                                                    fb.a(this.a).a(String.valueOf(fb.a(this.a).ob) + " " + this.a.d + i.z[1] + '\u0001' + i.z[9] + s.substring(3) + '\u0001', 1);
                                                    this.a.c.a(i.z[8]);
                                                    this.a.h = true;
                                                }
                                                return;
                                            }
                                        }
                                        this.a.c.a("");
                                        fb.a(this.a).a(s, false);
                                        this.a.b.insertElementAt(s, 0);
                                        final fb a6 = this.a;
                                    }
                                    a5.h = true;
                                    return;
                                }
                                n22 = (n21 = (n8 = (n23 = (this.a.d.equals(i.z[4]) ? 1 : 0))));
                            }
                            if (!r) {
                                if (n21 == 0) {
                                    final int length = s.length();
                                    final d c;
                                    Label_1810: {
                                        if (!r) {
                                            if (length > 0) {
                                                final int j = this.a.i ? 1 : 0;
                                                Label_1598: {
                                                    Label_1528: {
                                                        Label_1415: {
                                                            if (!r) {
                                                                if (j == 0) {
                                                                    final boolean startsWith2 = this.a.d.startsWith("#");
                                                                    if (r) {
                                                                        break Label_1415;
                                                                    }
                                                                    if (!startsWith2) {
                                                                        fb.a(this.a).a(String.valueOf(fb.a(this.a).ob) + " " + this.a.d + i.z[1] + s + " ", 1);
                                                                        if (!r) {
                                                                            break Label_1528;
                                                                        }
                                                                    }
                                                                }
                                                                fb.a(this.a).a(String.valueOf(fb.a(this.a).ob) + " " + this.a.d + i.z[1] + s, 1);
                                                                if (r) {
                                                                    break Label_1598;
                                                                }
                                                                s.indexOf(i.z[7]);
                                                            }
                                                        }
                                                        if (j > -1) {
                                                            final w h = fb.a(this.a).h(this.a.d);
                                                            if (r) {
                                                                break Label_1598;
                                                            }
                                                            if (h != null) {
                                                                final w w = h;
                                                                if (!r) {
                                                                    if (!w.b.c(fb.a(this.a).n)) {
                                                                        break Label_1528;
                                                                    }
                                                                    h.h.a();
                                                                }
                                                                w.a("\n" + fb.a(this.a).a(61, fb.a(this.a).n, "", ""));
                                                            }
                                                        }
                                                    }
                                                    this.a.i = true;
                                                    this.a.b.insertElementAt(s, 0);
                                                    fb.a(this.a).a("\n" + fb.a(this.a).a(24, fb.a(this.a).n, s, ""));
                                                }
                                                this.a.h = true;
                                            }
                                            c = this.a.c;
                                            if (r) {
                                                break Label_1810;
                                            }
                                            c.b().charAt(0);
                                        }
                                        if (length == 3) {
                                            String substring2 = this.a.c.b().substring(0, 2);
                                            final String substring3 = this.a.c.b().substring(2, 3);
                                            if (!r) {
                                                Label_1791: {
                                                    if (substring2.substring(1, 2).equals("1")) {
                                                        final String s10 = substring3;
                                                        Label_1790: {
                                                            if (!r) {
                                                                if (!s10.equals("0")) {
                                                                    final String s11 = substring3;
                                                                    if (r) {
                                                                        break Label_1790;
                                                                    }
                                                                    if (!s11.equals("1")) {
                                                                        final String s12 = substring3;
                                                                        if (r) {
                                                                            break Label_1790;
                                                                        }
                                                                        if (!s12.equals("2")) {
                                                                            final String s13 = substring3;
                                                                            if (r) {
                                                                                break Label_1790;
                                                                            }
                                                                            if (!s13.equals("3")) {
                                                                                final String s14 = substring3;
                                                                                if (r) {
                                                                                    break Label_1790;
                                                                                }
                                                                                if (!s14.equals("4")) {
                                                                                    final String s15 = substring3;
                                                                                    if (r) {
                                                                                        break Label_1790;
                                                                                    }
                                                                                    if (!s15.equals("5")) {
                                                                                        break Label_1791;
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
                                                this.a.c.a(substring2);
                                            }
                                            return;
                                        }
                                        final d c2 = this.a.c;
                                    }
                                    c.a("");
                                    return;
                                }
                                n22 = (n23 = (this.a.d.equals(i.z[4]) ? 1 : 0));
                            }
                            if (r) {
                                break Label_1867;
                            }
                            if (n22 != 0) {
                                this.a.b.insertElementAt(s, 0);
                                this.a.c.a("");
                            }
                        }
                        n23 = (n8 = keyEvent.getKeyCode());
                    }
                    if (r) {
                        break Label_1894;
                    }
                }
                if (keyCode2 != n16) {
                    return;
                }
                d2 = this.a.d;
                if (r) {
                    break Label_1907;
                }
                n23 = (d2.startsWith("#") ? 1 : 0);
            }
            if (n23 == 0) {
                return;
            }
            this.a.c.b();
        }
        final String s16 = d2;
        String s17 = s16.substring(0, s16.length() - 1);
        this.a.c.a(s17);
        int index2 = 0;
        w h2;
        while (true) {
            while (true) {
                Label_1964: {
                    if (!r) {
                        break Label_1964;
                    }
                    index2 = s17.indexOf(" ");
                    s17 = s17.substring(index2 + 1);
                }
                if (s17.indexOf(" ") >= 0) {
                    continue;
                }
                break;
            }
            h2 = fb.a(this.a).h(this.a.d);
            if (r) {
                continue;
            }
            break;
        }
        final w w2 = h2;
        if (r || w2 != null) {
            final Vector d3 = w2.b.b.b.d();
            int n25 = 0;
            while (true) {
                Label_2249: {
                    if (!r) {
                        break Label_2249;
                    }
                    final boolean equals;
                    final boolean b = equals = d3.elementAt(n25).toString().equals(s17);
                    final int n26;
                    Label_2169: {
                        if (!r) {
                            if (b) {
                                n26 = n25;
                                if (r) {
                                    break Label_2169;
                                }
                                if (n26 < d3.size() - 1) {
                                    this.a.c.a(String.valueOf(this.a.c.b().substring(0, this.a.c.b().length() - s17.length())) + d3.elementAt(n25 + 1).toString());
                                    keyEvent.consume();
                                    return;
                                }
                            }
                            d3.elementAt(n25).toString().toLowerCase().startsWith(s17.toLowerCase());
                        }
                    }
                    if (n26 != 0) {
                        this.a.c.a(String.valueOf(this.a.c.b().substring(0, this.a.c.b().length() - s17.length())) + d3.elementAt(n25).toString());
                        keyEvent.consume();
                        return;
                    }
                    ++n25;
                }
                if (n25 < d3.size()) {
                    continue;
                }
                break;
            }
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final boolean r = d.r;
        final int keyCode = keyEvent.getKeyCode();
        final int n = 17;
        int n3 = 0;
        Label_0060: {
            if (!r) {
                Label_0040: {
                    if (keyCode == n) {
                        final int modifiers;
                        final int n2 = modifiers = (n3 = (this.a.j ? 1 : 0));
                        if (r) {
                            break Label_0040;
                        }
                        if (n2 != 0) {
                            keyEvent.consume();
                            return;
                        }
                    }
                    int modifiers;
                    n3 = (modifiers = keyEvent.getModifiers());
                }
                if (r) {
                    break Label_0060;
                }
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
            }
            if (keyCode != n) {
                return;
            }
            n3 = (this.a.j ? 1 : 0);
        }
        if (n3 != 0) {
            keyEvent.consume();
        }
    }
    
    static {
        final String[] z = new String[11];
        final int n = 0;
        final char[] charArray = "\u0004W@WQa.".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'P';
                    break;
                }
                case 1: {
                    c2 = '\u000e';
                    break;
                }
                case 2: {
                    c2 = '\u0010';
                    break;
                }
                case 3: {
                    c2 = '\u0012';
                    break;
                }
                default: {
                    c2 = 'q';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "p4".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'P';
                    break;
                }
                case 1: {
                    c4 = '\u000e';
                    break;
                }
                case 2: {
                    c4 = '\u0010';
                    break;
                }
                case 3: {
                    c4 = '\u0012';
                    break;
                }
                default: {
                    c4 = 'q';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\u001f`".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'P';
                    break;
                }
                case 1: {
                    c6 = '\u000e';
                    break;
                }
                case 2: {
                    c6 = '\u0010';
                    break;
                }
                case 3: {
                    c6 = '\u0012';
                    break;
                }
                default: {
                    c6 = 'q';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "?hv".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'P';
                    break;
                }
                case 1: {
                    c8 = '\u000e';
                    break;
                }
                case 2: {
                    c8 = '\u0010';
                    break;
                }
                case 3: {
                    c8 = '\u0012';
                    break;
                }
                default: {
                    c8 = 'q';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "\u0003zqf\u0004#".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'P';
                    break;
                }
                case 1: {
                    c10 = '\u000e';
                    break;
                }
                case 2: {
                    c10 = '\u0010';
                    break;
                }
                case 3: {
                    c10 = '\u0012';
                    break;
                }
                default: {
                    c10 = 'q';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "?`".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'P';
                    break;
                }
                case 1: {
                    c12 = '\u000e';
                    break;
                }
                case 2: {
                    c12 = '\u0010';
                    break;
                }
                case 3: {
                    c12 = '\u0012';
                    break;
                }
                default: {
                    c12 = 'q';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "\u007fcu".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'P';
                    break;
                }
                case 1: {
                    c14 = '\u000e';
                    break;
                }
                case 2: {
                    c14 = '\u0010';
                    break;
                }
                case 3: {
                    c14 = '\u0012';
                    break;
                }
                default: {
                    c14 = 'q';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "qzu\u007f\u0018*bu".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 'P';
                    break;
                }
                case 1: {
                    c16 = '\u000e';
                    break;
                }
                case 2: {
                    c16 = '\u0010';
                    break;
                }
                case 3: {
                    c16 = '\u0012';
                    break;
                }
                default: {
                    c16 = 'q';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "\u007fcu2".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = 'P';
                    break;
                }
                case 1: {
                    c18 = '\u000e';
                    break;
                }
                case 2: {
                    c18 = '\u0010';
                    break;
                }
                case 3: {
                    c18 = '\u0012';
                    break;
                }
                default: {
                    c18 = 'q';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "\u0011MD[>\u001e".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = 'P';
                    break;
                }
                case 1: {
                    c20 = '\u000e';
                    break;
                }
                case 2: {
                    c20 = '\u0010';
                    break;
                }
                case 3: {
                    c20 = '\u0012';
                    break;
                }
                default: {
                    c20 = 'q';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "\u0004W@WQ`.".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = 'P';
                    break;
                }
                case 1: {
                    c22 = '\u000e';
                    break;
                }
                case 2: {
                    c22 = '\u0010';
                    break;
                }
                case 3: {
                    c22 = '\u0012';
                    break;
                }
                default: {
                    c22 = 'q';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z[n31] = new String(charArray11).intern();
        i.z = z;
    }
}
