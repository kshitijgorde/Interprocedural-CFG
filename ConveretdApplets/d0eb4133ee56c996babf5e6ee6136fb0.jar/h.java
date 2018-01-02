import java.util.Vector;
import java.awt.TextArea;
import java.awt.Component;
import java.awt.Toolkit;
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
        int n2;
        int keyCode;
        final int n = keyCode = (n2 = keyEvent.getModifiers());
        int n5;
        int n4;
        final int n3 = n4 = (n5 = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        if (m == 0) {
            Label_0047: {
                if (n == n3) {
                    final int n7;
                    final int n6 = n7 = (keyCode = (n2 = (this.a.j ? 1 : 0)));
                    if (m != 0) {
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
        int n9 = 0;
        Label_0308: {
            Label_0281: {
                Label_0266: {
                    if (m == 0) {
                        if (n == n3) {
                            this.a.j = true;
                            this.a.setSize(eb.a(this.a).v.width, 30);
                            this.a.f.setLocation(0, 50);
                            this.a.e.setLocation(0, 0);
                            this.a.c.setLocation(-2, 9);
                            eb.a(this.a).Tb.validate();
                            eb.a(this.a).Tb.invalidate();
                            if (m == 0) {
                                break Label_0266;
                            }
                        }
                        n2 = (keyCode = keyEvent.getKeyCode());
                        n5 = (n4 = 18);
                    }
                    if (m != 0) {
                        break Label_0281;
                    }
                    if (keyCode == n4) {
                        this.a.j = true;
                        this.a.setSize(eb.a(this.a).v.width, 40);
                        this.a.c.setLocation(-2, 19);
                        this.a.e.setLocation(0, 50);
                        this.a.f.setLocation(0, 0);
                        eb.a(this.a).Tb.validate();
                        eb.a(this.a).Tb.invalidate();
                    }
                }
                final int n15;
                final boolean b5;
                final int n14;
                final boolean b4;
                final int n13;
                final boolean b3;
                final int n12;
                final boolean b2;
                final int n11;
                final boolean b;
                final int n10;
                n9 = (n2 = (n10 = ((b = ((n11 = ((b2 = ((n12 = ((b3 = ((n13 = ((b4 = ((n14 = ((b5 = ((n15 = keyEvent.getModifiers()) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)));
                if (m != 0) {
                    break Label_0308;
                }
                n5 = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
            }
            if (n2 == n5) {
                keyEvent.consume();
                return;
            }
            int n15;
            boolean b5;
            int n14;
            boolean b4;
            int n13;
            boolean b3;
            int n12;
            boolean b2;
            int n11;
            boolean b;
            n9 = ((b = ((n11 = ((b2 = ((n12 = ((b3 = ((n13 = ((b4 = ((n14 = ((b5 = ((n15 = (eb.a(this.a).P.equalsIgnoreCase(h.z[6]) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0);
        }
        final int n16;
        Label_0385: {
            if (m == 0) {
                if (n9 != 0) {
                    final int n15;
                    final boolean b5;
                    final int n14;
                    final boolean b4;
                    final int n13;
                    final boolean b3;
                    final int n12;
                    final boolean b2;
                    final int n11;
                    final boolean b;
                    n16 = ((b = ((n11 = ((b2 = ((n12 = ((b3 = ((n13 = ((b4 = ((n14 = ((b5 = ((n15 = keyEvent.getKeyCode()) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0);
                    if (m != 0) {
                        break Label_0385;
                    }
                    if (n16 == 525) {
                        eb.a(this.a).zb.show(eb.a(this.a).Tb.d, 100, 100);
                        keyEvent.consume();
                        return;
                    }
                }
                eb.a(this.a).P.equalsIgnoreCase(h.z[6]);
            }
        }
        final boolean b6;
        Label_0455: {
            if (m == 0) {
                if (n16 != 0) {
                    final int n15;
                    final boolean b5;
                    final int n14;
                    final boolean b4;
                    final int n13;
                    final boolean b3;
                    final int n12;
                    final boolean b2;
                    final int n11;
                    b6 = ((n11 = ((b2 = ((n12 = ((b3 = ((n13 = ((b4 = ((n14 = ((b5 = ((n15 = keyEvent.getKeyCode()) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0);
                    if (m != 0) {
                        break Label_0455;
                    }
                    if ((b6 ? 1 : 0) == 524) {
                        eb.a(this.a).zb.show(eb.a(this.a).Tb.d, 100, 100);
                        keyEvent.consume();
                        return;
                    }
                }
                this.a.d.startsWith("#");
            }
        }
        final boolean b7;
        final int n18;
        final boolean b8;
        final int n20;
        Label_0743: {
            final int n17;
            final int n19;
            Label_0698: {
                Label_0691: {
                    if (m == 0) {
                        if (!b6) {
                            int n15;
                            boolean b5;
                            int n14;
                            boolean b4;
                            int n13;
                            boolean b3;
                            int n12;
                            final boolean b2;
                            n17 = ((b2 = ((n12 = ((b3 = ((n13 = ((b4 = ((n14 = ((b5 = ((n15 = (this.a.d.equals(h.z[5]) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0);
                            if (m != 0) {
                                break Label_0691;
                            }
                            if (n17 == 0) {
                                b7 = ((n12 = ((b3 = ((n13 = ((b4 = ((n14 = ((b5 = ((n15 = (eb.a(this.a).V.equalsIgnoreCase(h.z[2]) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0);
                                if (m != 0) {
                                    break Label_0691;
                                }
                                if (b7) {
                                    n18 = ((b3 = ((n13 = ((b4 = ((n14 = ((b5 = ((n15 = (this.a.h ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0);
                                    if (m != 0) {
                                        break Label_0691;
                                    }
                                    if (n18 != 0) {
                                        final String text = this.a.c.getText();
                                        final int keyCode2 = keyEvent.getKeyCode();
                                        n19 = 191;
                                        if (m != 0) {
                                            break Label_0698;
                                        }
                                        if (keyCode2 != n19) {
                                            b8 = ((n13 = ((b4 = ((n14 = ((b5 = ((n15 = (text.startsWith("/") ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0)) != 0);
                                            if (m != 0) {
                                                break Label_0691;
                                            }
                                            if (!b8) {
                                                final x i = eb.a(this.a).i(this.a.d);
                                                if (m != 0 || i != null) {
                                                    n20 = ((b4 = ((n14 = ((b5 = ((n15 = (i.i ? 1 : 0)) != 0)) ? 1 : 0)) != 0)) ? 1 : 0);
                                                    if (m != 0) {
                                                        break Label_0691;
                                                    }
                                                    if (n20 != 0) {
                                                        eb.a(this.a).a(String.valueOf(eb.a(this.a).jb) + " " + this.a.d + h.z[7] + ' ' + h.z[9], 1);
                                                        this.a.h = false;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        keyEvent.getKeyCode();
                    }
                }
                if (m != 0) {
                    break Label_0743;
                }
            }
            if (n17 == n19) {
                this.a.c.setCaretPosition(this.a.c.getText().length());
            }
            eb.a(this.a).P.equalsIgnoreCase(h.z[6]);
        }
        final boolean b9;
        Label_0790: {
            if (m == 0) {
                if (b7) {
                    final int n15;
                    final boolean b5;
                    final int n14;
                    b9 = ((n14 = ((b5 = ((n15 = keyEvent.getKeyCode()) != 0)) ? 1 : 0)) != 0);
                    if (m != 0) {
                        break Label_0790;
                    }
                    if ((b9 ? 1 : 0) == 155) {
                        keyEvent.consume();
                        return;
                    }
                }
                eb.a(this.a).P.equalsIgnoreCase(h.z[6]);
            }
        }
        final boolean b10;
        final int n21;
        Label_0848: {
            if (m == 0) {
                if (n18 != 0) {
                    int n15;
                    final boolean b5;
                    b10 = (b5 = ((n15 = (keyEvent.isControlDown() ? 1 : 0)) != 0));
                    if (m != 0) {
                        break Label_0848;
                    }
                    if (b10) {
                        n21 = (n15 = keyEvent.getKeyCode());
                        if (m != 0) {
                            break Label_0848;
                        }
                        if (n21 == 86) {
                            keyEvent.consume();
                            return;
                        }
                    }
                }
                eb.a(this.a).P.equalsIgnoreCase(h.z[6]);
            }
        }
        final int keyCode3;
        Label_0895: {
            if (m == 0) {
                if (b8) {
                    keyCode3 = keyEvent.getKeyCode();
                    if (m != 0) {
                        break Label_0895;
                    }
                    if (keyCode3 == 155) {
                        keyEvent.consume();
                        return;
                    }
                }
                eb.a(this.a).P.equalsIgnoreCase(h.z[8]);
            }
        }
        if (m == 0) {
            if (n20 == 0) {
                return;
            }
            keyEvent.getKeyCode();
        }
        int n24;
        final int n23;
        final int n22 = n23 = (n24 = 38);
        final int n25;
        final int n26;
        Label_1069: {
            if (m == 0) {
                if ((b9 ? 1 : 0) == n22) {
                    final int index = this.a.c.getText().indexOf("\n");
                    n25 = (n24 = -1);
                    if (m != 0) {
                        break Label_1069;
                    }
                    if (index == n25) {
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
                keyEvent.getKeyCode();
                n26 = 40;
            }
        }
        if (m == 0) {
            if ((b10 ? 1 : 0) != n22) {
                return;
            }
            this.a.c.getText().indexOf("\n");
        }
        final eb a7;
        Label_1170: {
            if (m == 0) {
                if (n21 != n25) {
                    return;
                }
                a7 = this.a;
                if (m != 0) {
                    break Label_1170;
                }
                final int n15 = a7.g;
            }
            if (keyCode3 < n26) {
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
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        final int m = fb.m;
        int n3;
        final int n2;
        final int n = n2 = (n3 = keyEvent.getModifiers());
        if (m == 0) {
            if (n == Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()) {
                keyEvent.consume();
                return;
            }
            eb.a(this.a).P.equalsIgnoreCase(h.z[6]);
        }
        final int n4;
        Label_0124: {
            if (m == 0) {
                if (n != 0) {
                    n4 = (n3 = keyEvent.getKeyCode());
                    if (m != 0) {
                        break Label_0124;
                    }
                    if (n4 == 525) {
                        eb.a(this.a).zb.show(eb.a(this.a).Tb.d, 100, 100);
                        keyEvent.consume();
                        return;
                    }
                }
                eb.a(this.a).P.equalsIgnoreCase(h.z[6]);
            }
        }
        Label_0228: {
            final int keyCode;
            Label_0206: {
                if (m == 0) {
                    if (n4 != 0) {
                        keyCode = keyEvent.getKeyCode();
                        if (m != 0) {
                            break Label_0206;
                        }
                        if (keyCode == 524) {
                            eb.a(this.a).zb.show(eb.a(this.a).Tb.d, 100, 100);
                            keyEvent.consume();
                            return;
                        }
                    }
                    final String p = eb.a(this.a).P;
                    if (m != 0) {
                        break Label_0228;
                    }
                    p.equalsIgnoreCase(h.z[6]);
                }
            }
            if (keyCode != 0) {
                KeyEvent keyEvent2 = keyEvent;
                if (m == 0) {
                    if (keyEvent.getKeyCode() != 17) {
                        break Label_0228;
                    }
                    keyEvent2 = keyEvent;
                }
                keyEvent2.consume();
                return;
            }
        }
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
        int n8;
        int n7;
        int n6;
        final int n5 = n6 = (n7 = (n8 = (d.startsWith(s5) ? 1 : 0)));
        final int n10;
        Label_0496: {
            if (m == 0) {
                if (n5 == 0) {
                    final int n9 = n6 = (n7 = (n8 = (this.a.d.equals(h.z[5]) ? 1 : 0)));
                    if (m != 0) {
                        break Label_0496;
                    }
                    if (n9 == 0) {
                        n10 = (n7 = (n8 = s.length()));
                        if (m != 0) {
                            break Label_0496;
                        }
                        if (n10 == 0) {
                            final int n11 = n6 = (n7 = (n8 = (eb.a(this.a).V.equalsIgnoreCase(h.z[2]) ? 1 : 0)));
                            if (m != 0) {
                                break Label_0496;
                            }
                            if (n11 != 0) {
                                final x i = eb.a(this.a).i(this.a.d);
                                if (m != 0 || i != null) {
                                    final int n12 = n6 = (n7 = (n8 = (i.i ? 1 : 0)));
                                    if (m != 0) {
                                        break Label_0496;
                                    }
                                    if (n12 != 0) {
                                        eb.a(this.a).a(String.valueOf(eb.a(this.a).jb) + " " + this.a.d + h.z[7] + ' ' + h.z[1], 1);
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
        Label_0689: {
            if (m == 0) {
                Label_0683: {
                    if (n10 != n13) {
                        n15 = (n8 = keyEvent.getKeyCode());
                        n16 = 17;
                        if (m != 0) {
                            break Label_0689;
                        }
                        if (n15 != n16) {
                            keyCode2 = keyEvent.getKeyCode();
                            final int n17 = 86;
                            if (m != 0) {
                                break Label_0689;
                            }
                            if (keyCode2 != n17) {
                                final int keyCode3 = keyEvent.getKeyCode();
                                final int n18 = 86;
                                if (m != 0) {
                                    break Label_0689;
                                }
                                if (keyCode3 != n18) {
                                    final eb a = this.a;
                                    if (m == 0) {
                                        if (a.getSize().height != 40) {
                                            final int height = this.a.getSize().height;
                                            final int n19 = 30;
                                            if (m != 0) {
                                                break Label_0689;
                                            }
                                            if (height != n19) {
                                                break Label_0683;
                                            }
                                        }
                                        this.a.setSize(eb.a(this.a).v.width, 20);
                                        this.a.c.setLocation(-2, -1);
                                        this.a.e.setLocation(0, 50);
                                        this.a.f.setLocation(0, 50);
                                        eb.a(this.a).Tb.validate();
                                        eb.a(this.a).Tb.invalidate();
                                        final eb a2 = this.a;
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
        Label_1874: {
            int n23 = 0;
            Label_1861: {
                if (m == 0) {
                    Label_1834: {
                        if (n15 == n13) {
                            this.a.c.setCaretPosition(this.a.c.getText().length());
                            final String s8 = s;
                            if (m == 0 && s8.length() < 1) {
                                return;
                            }
                            String s9 = s8;
                            int index = 0;
                            int n22;
                            int n21;
                            final int n20 = n21 = (n22 = (n8 = (n23 = (this.a.d.equals(h.z[5]) ? 1 : 0))));
                            if (m == 0) {
                                if (n20 == 0) {
                                    eb a3 = null;
                                    while (true) {
                                        final String substring;
                                        Label_0932: {
                                            Label_0924: {
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
                                                        break Label_0932;
                                                    }
                                                    if (substring.length() <= 0) {
                                                        break Label_0924;
                                                    }
                                                    eb.a(this.a).a(String.valueOf(eb.a(this.a).jb) + " " + this.a.d + h.z[7] + s9, 1);
                                                    eb.a(this.a).a("\n" + eb.a(this.a).a(24, eb.a(this.a).n, s9, ""));
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
                                final int n24;
                                n21 = (n24 = (n22 = (n8 = (n23 = (s.startsWith("/") ? 1 : 0)))));
                            }
                            if (m == 0) {
                                if (n20 != 0) {
                                    final boolean startsWith = s.startsWith(h.z[3]);
                                    final eb a5;
                                    Label_1240: {
                                        Label_1189: {
                                            if (m == 0) {
                                                if (!startsWith) {
                                                    break Label_1189;
                                                }
                                                a5 = this.a;
                                                if (m != 0) {
                                                    break Label_1240;
                                                }
                                                a5.d.equals(h.z[5]);
                                            }
                                            if (!startsWith) {
                                                if (s.length() > 4) {
                                                    eb.a(this.a).a("\n" + eb.a(this.a).a(25, eb.a(this.a).n, s.substring(3), ""));
                                                    eb.a(this.a).a(String.valueOf(eb.a(this.a).jb) + " " + this.a.d + h.z[7] + '\u0001' + h.z[4] + s.substring(3) + '\u0001', 1);
                                                    this.a.c.setText(h.z[0]);
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
                                n22 = (n21 = (n8 = (n23 = (this.a.d.equals(h.z[5]) ? 1 : 0))));
                            }
                            if (m == 0) {
                                if (n21 == 0) {
                                    final int length = s.length();
                                    final TextArea c;
                                    Label_1767: {
                                        if (m == 0) {
                                            if (length > 0) {
                                                final eb a7 = this.a;
                                                Label_1450: {
                                                    Label_1392: {
                                                        if (m == 0) {
                                                            if (!a7.i) {
                                                                final eb a8 = this.a;
                                                                if (m != 0) {
                                                                    break Label_1392;
                                                                }
                                                                if (!a8.d.startsWith("#")) {
                                                                    eb.a(this.a).a(String.valueOf(eb.a(this.a).jb) + " " + this.a.d + h.z[7] + s + " ", 1);
                                                                    if (m == 0) {
                                                                        break Label_1450;
                                                                    }
                                                                }
                                                            }
                                                            final eb a9 = this.a;
                                                        }
                                                    }
                                                    eb.a(a7).a(String.valueOf(eb.a(this.a).jb) + " " + this.a.d + h.z[7] + s, 1);
                                                }
                                                this.a.i = true;
                                                this.a.b.insertElementAt(s, 0);
                                                eb.a(this.a).a("\n" + eb.a(this.a).a(24, eb.a(this.a).n, s, ""));
                                                this.a.h = true;
                                            }
                                            c = this.a.c;
                                            if (m != 0) {
                                                break Label_1767;
                                            }
                                            c.getText().charAt(0);
                                        }
                                        if (length == 3) {
                                            String substring2 = this.a.c.getText().substring(0, 2);
                                            final String substring3 = this.a.c.getText().substring(2, 3);
                                            if (m == 0) {
                                                Label_1713: {
                                                    if (substring2.substring(1, 2).equals("1")) {
                                                        final String s10 = substring3;
                                                        Label_1712: {
                                                            if (m == 0) {
                                                                if (!s10.equals("0")) {
                                                                    final String s11 = substring3;
                                                                    if (m != 0) {
                                                                        break Label_1712;
                                                                    }
                                                                    if (!s11.equals("1")) {
                                                                        final String s12 = substring3;
                                                                        if (m != 0) {
                                                                            break Label_1712;
                                                                        }
                                                                        if (!s12.equals("2")) {
                                                                            final String s13 = substring3;
                                                                            if (m != 0) {
                                                                                break Label_1712;
                                                                            }
                                                                            if (!s13.equals("3")) {
                                                                                final String s14 = substring3;
                                                                                if (m != 0) {
                                                                                    break Label_1712;
                                                                                }
                                                                                if (!s14.equals("4")) {
                                                                                    final String s15 = substring3;
                                                                                    if (m != 0) {
                                                                                        break Label_1712;
                                                                                    }
                                                                                    if (!s15.equals("5")) {
                                                                                        break Label_1713;
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
                                n22 = (n23 = (this.a.d.equals(h.z[5]) ? 1 : 0));
                            }
                            if (m != 0) {
                                break Label_1834;
                            }
                            if (n22 != 0) {
                                this.a.b.insertElementAt(s, 0);
                                this.a.c.setText("");
                                this.a.c.setCaretPosition(0);
                            }
                        }
                        n23 = (n8 = keyEvent.getKeyCode());
                    }
                    if (m != 0) {
                        break Label_1861;
                    }
                }
                if (keyCode2 != n16) {
                    return;
                }
                d2 = this.a.d;
                if (m != 0) {
                    break Label_1874;
                }
                n23 = (d2.startsWith("#") ? 1 : 0);
            }
            if (n23 == 0) {
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
                Label_1931: {
                    if (m == 0) {
                        break Label_1931;
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
            int n25 = 0;
            while (true) {
                Label_2262: {
                    if (m == 0) {
                        break Label_2262;
                    }
                    final boolean equals;
                    final boolean b = equals = d3.elementAt(n25).toString().equals(text);
                    final int n26;
                    Label_2159: {
                        if (m == 0) {
                            if (b) {
                                n26 = n25;
                                if (m != 0) {
                                    break Label_2159;
                                }
                                if (n26 < d3.size() - 1) {
                                    this.a.c.setText(String.valueOf(this.a.c.getText().substring(0, this.a.c.getText().length() - text.length())) + d3.elementAt(n25 + 1).toString());
                                    this.a.c.setCaretPosition(this.a.c.getText().length());
                                    keyEvent.consume();
                                    return;
                                }
                            }
                            d3.elementAt(n25).toString().toLowerCase().startsWith(text.toLowerCase());
                        }
                    }
                    if (n26 != 0) {
                        this.a.c.setText(String.valueOf(this.a.c.getText().substring(0, this.a.c.getText().length() - text.length())) + d3.elementAt(n25).toString());
                        this.a.c.setCaretPosition(this.a.c.getText().length());
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
        final int m = fb.m;
        final int keyCode = keyEvent.getKeyCode();
        final int n = 17;
        int n3 = 0;
        Label_0060: {
            if (m == 0) {
                Label_0040: {
                    if (keyCode == n) {
                        final int modifiers;
                        final int n2 = modifiers = (n3 = (this.a.j ? 1 : 0));
                        if (m != 0) {
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
                if (m != 0) {
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
        final String[] z = new String[10];
        final int n = 0;
        final char[] charArray = "\u001bod#".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '4';
                    break;
                }
                case 1: {
                    c2 = '\u0002';
                    break;
                }
                case 2: {
                    c2 = '\u0001';
                    break;
                }
                case 3: {
                    c2 = '\u0003';
                    break;
                }
                default: {
                    c2 = '\u001f';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "`[QF?\u0004\"".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '4';
                    break;
                }
                case 1: {
                    c4 = '\u0002';
                    break;
                }
                case 2: {
                    c4 = '\u0001';
                    break;
                }
                case 3: {
                    c4 = '\u0003';
                    break;
                }
                default: {
                    c4 = '\u001f';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "{l".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '4';
                    break;
                }
                case 1: {
                    c6 = '\u0002';
                    break;
                }
                case 2: {
                    c6 = '\u0001';
                    break;
                }
                case 3: {
                    c6 = '\u0003';
                    break;
                }
                default: {
                    c6 = '\u001f';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\u001bod".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '4';
                    break;
                }
                case 1: {
                    c8 = '\u0002';
                    break;
                }
                case 2: {
                    c8 = '\u0001';
                    break;
                }
                case 3: {
                    c8 = '\u0003';
                    break;
                }
                default: {
                    c8 = '\u001f';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "uAUJPz".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '4';
                    break;
                }
                case 1: {
                    c10 = '\u0002';
                    break;
                }
                case 2: {
                    c10 = '\u0001';
                    break;
                }
                case 3: {
                    c10 = '\u0003';
                    break;
                }
                default: {
                    c10 = '\u001f';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "gv`wjG".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '4';
                    break;
                }
                case 1: {
                    c12 = '\u0002';
                    break;
                }
                case 2: {
                    c12 = '\u0001';
                    break;
                }
                case 3: {
                    c12 = '\u0003';
                    break;
                }
                default: {
                    c12 = '\u001f';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "[dg".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '4';
                    break;
                }
                case 1: {
                    c14 = '\u0002';
                    break;
                }
                case 2: {
                    c14 = '\u0001';
                    break;
                }
                case 3: {
                    c14 = '\u0003';
                    break;
                }
                default: {
                    c14 = '\u001f';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "\u00148".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '4';
                    break;
                }
                case 1: {
                    c16 = '\u0002';
                    break;
                }
                case 2: {
                    c16 = '\u0001';
                    break;
                }
                case 3: {
                    c16 = '\u0003';
                    break;
                }
                default: {
                    c16 = '\u001f';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "[l".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '4';
                    break;
                }
                case 1: {
                    c18 = '\u0002';
                    break;
                }
                case 2: {
                    c18 = '\u0001';
                    break;
                }
                case 3: {
                    c18 = '\u0003';
                    break;
                }
                default: {
                    c18 = '\u001f';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "`[QF?\u0005\"".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '4';
                    break;
                }
                case 1: {
                    c20 = '\u0002';
                    break;
                }
                case 2: {
                    c20 = '\u0001';
                    break;
                }
                case 3: {
                    c20 = '\u0003';
                    break;
                }
                default: {
                    c20 = '\u001f';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        h.z = z;
    }
}
