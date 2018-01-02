import java.awt.Component;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class pb extends Thread
{
    private final esChat a;
    boolean b;
    boolean c;
    boolean d;
    Vector e;
    int f;
    int g;
    private static String z;
    
    pb(final esChat a) {
        this.a = a;
        this.b = true;
        this.c = false;
        this.d = true;
        this.e = new Vector(10, 10);
        this.f = 0;
        this.g = 100;
    }
    
    void a(String string) {
        final boolean r = d.r;
        int n2;
        final int n = n2 = string.length();
        int n4;
        final int n3 = n4 = 30;
        if (!r) {
            if (n > n3) {
                string = String.valueOf(string.substring(0, 30)) + pb.z;
            }
            this.e.addElement(string);
            final int size;
            n2 = (size = this.e.size());
            final int n5;
            n4 = (n5 = 5);
        }
        pb pb = null;
        Label_0096: {
            if (!r) {
                if (n > n3) {
                    this.e.removeElementAt(0);
                }
                pb = this;
                if (r) {
                    break Label_0096;
                }
                n2 = this.e.size();
                n4 = 1;
            }
            if (n2 != n4) {
                return;
            }
            this.c = true;
            pb = this;
        }
        pb.g = 100;
    }
    
    public void a(final Graphics graphics, final int n, final int n2) {
        final boolean r = d.r;
        final int size = this.e.size();
        if (!r) {
            if (size <= 0) {
                return;
            }
            graphics.drawImage(this.a.D, n, n2 + this.g, n + 10, n2 + 10 + this.g, 0, 0, 10, 10, null);
            graphics.drawImage(this.a.D, n + 10, n2 + this.g, n + 190, n2 + 10 + this.g, 10, 0, 20, 10, null);
            graphics.drawImage(this.a.D, n + 190, n2 + this.g, n + 200, n2 + 10 + this.g, 20, 0, 30, 10, null);
            graphics.drawImage(this.a.D, n, n2 + 10 + this.g, n + 10, n2 + 100 + this.g, 0, 10, 10, 20, null);
            graphics.drawImage(this.a.D, n + 10, n2 + 10 + this.g, n + 190, n2 + 100 + this.g, 10, 10, 20, 20, null);
            graphics.drawImage(this.a.D, n + 190, n2 + 10 + this.g, n + 200, n2 + 100 + this.g, 20, 10, 30, 20, null);
        }
        int n3 = size;
        while (true) {
            while (true) {
                Label_0345: {
                    if (!r) {
                        break Label_0345;
                    }
                    graphics.setColor(Color.gray);
                    final Object element = this.e.elementAt(n3);
                    graphics.drawString((String)element, n + 10, n2 + n3 * 18 + 18 + this.g);
                    ++n3;
                }
                if (n3 < this.e.size()) {
                    continue;
                }
                break;
            }
            final Object element = graphics;
            if (r) {
                continue;
            }
            break;
        }
        graphics.drawImage(this.a.z, n + 180, n2 + this.g + 6, n + 195, n2 + this.g + 21, 212, 92, 227, 107, null);
    }
    
    public void run() {
        final boolean r = d.r;
    Label_0004_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        Thread.sleep(100L);
                        this.e.size();
                        int i = 0;
                        while (i > 0) {
                            ++this.f;
                            int n;
                            int c;
                            i = (c = (n = this.f));
                            if (!r) {
                                if (!r) {
                                    if (i > 50) {
                                        this.e.removeElementAt(0);
                                        this.f = 0;
                                    }
                                    n = (c = (this.c ? 1 : 0));
                                }
                                pb pb = null;
                                Label_0104: {
                                    if (!r) {
                                        if (c != 0) {
                                            this.g -= 10;
                                        }
                                        pb = this;
                                        if (r) {
                                            break Label_0104;
                                        }
                                        n = this.g;
                                    }
                                    if (n <= 0) {
                                        this.g = 0;
                                        this.c = false;
                                    }
                                    pb = this;
                                }
                                final ib zb = pb.a.Zb;
                                if (!r) {
                                    if (zb == null) {
                                        return;
                                    }
                                    final ib zb2 = this.a.Zb;
                                }
                                Component component;
                                final eb eb = (eb)(component = zb.b);
                                if (!r) {
                                    if (eb == null) {
                                        return;
                                    }
                                    component = this.a.Zb.b.e();
                                }
                                final y y = (y)component;
                                boolean b2;
                                final boolean b = b2 = (y instanceof w);
                                if (!r) {
                                    if (b) {
                                        ((w)y).h.b.repaint();
                                    }
                                    final boolean b3;
                                    b2 = (b3 = (y instanceof y));
                                }
                                final y y2;
                                Label_0210: {
                                    if (!r) {
                                        if (b) {
                                            y.b.b.repaint();
                                        }
                                        y2 = y;
                                        if (r) {
                                            break Label_0210;
                                        }
                                        b2 = (y2 instanceof db);
                                    }
                                    if (!b2) {
                                        break;
                                    }
                                }
                                ((db)y2).d.b.repaint();
                                break;
                            }
                        }
                    }
                }
                catch (Exception ex) {
                    continue Label_0004_Outer;
                }
                continue;
            }
        }
    }
    
    static {
        final char[] charArray = "o\u001f".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'A';
                    break;
                }
                case 1: {
                    c2 = '1';
                    break;
                }
                case 2: {
                    c2 = '\b';
                    break;
                }
                case 3: {
                    c2 = 'z';
                    break;
                }
                default: {
                    c2 = '>';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        pb.z = new String(charArray).intern();
    }
}
