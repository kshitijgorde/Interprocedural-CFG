import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class b extends Canvas implements MouseListener
{
    CHAT a;
    int[] a;
    Color[] a;
    int b;
    boolean a;
    int a;
    int g;
    int f;
    int e;
    int d;
    int c;
    
    public b(final CHAT a) {
        this.a = new int[] { 0, 16711680, 255, 32768, 16753920, 8388736, 16761035 };
        this.a = new Color[7];
        this.b = 0;
        this.a = 11;
        this.g = 166;
        this.f = 172;
        this.e = 193;
        this.d = 25;
        this.c = 0;
        this.a = a;
        final boolean h = a.h;
        this.a = h;
        if (h) {
            this.a = 192;
            this.g = 6;
            this.f = 32;
            this.e = 14;
            this.d = 47;
        }
        this.setSize(206, 22);
        this.addMouseListener(this);
        System.arraycopy(this.a, 0, a.b, 1, 7);
        for (int i = 0; i < 7; ++i) {
            this.a[i] = new Color(this.a[i]);
        }
        a.c = 1;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(5, 2, 201, 16);
        graphics.setColor(this.a[this.b]);
        graphics.fillOval(this.a, 5, 10, 10);
        graphics.setColor(Color.lightGray);
        graphics.fillRect(this.g, 3, 19, 14);
        graphics.fillRect(this.g + 20, 3, 19, 14);
        graphics.setColor(Color.black);
        graphics.setFont(this.a.a[1]);
        graphics.drawString("B", this.f, 15);
        graphics.setFont(this.a.a[3]);
        graphics.drawString("I", this.e, 15);
        for (int i = 0; i < 7; ++i) {
            int n;
            if (this.a) {
                n = 6 - i;
            }
            else {
                n = i;
            }
            graphics.setColor(this.a[n]);
            graphics.fillRect(i * 20 + this.d, 5, 20, 10);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final boolean a = d.a;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        int n4;
        int n3;
        int n2;
        final int n = n2 = (n3 = (n4 = y));
        int n8;
        int n7;
        int n6;
        final int n5 = n6 = (n7 = (n8 = 4));
        if (!a) {
            if (n <= n5) {
                return;
            }
            final int n9;
            n2 = (n9 = (n3 = (n4 = y)));
            final int n10;
            n6 = (n10 = (n7 = (n8 = 16)));
        }
        if (!a) {
            if (n >= n5) {
                return;
            }
            n3 = (n2 = (n4 = x));
            n7 = (n6 = (n8 = 5));
        }
        if (!a) {
            if (n2 <= n6) {
                return;
            }
            n4 = (n3 = x);
            n8 = (n7 = 204);
        }
        if (!a) {
            if (n3 >= n7) {
                return;
            }
            n4 = x - 5;
            n8 = 20;
        }
        int b = n4 / n8;
        int a2;
        int n13;
        int n12;
        final int n11 = n12 = (n13 = (a2 = (this.a ? 1 : 0)));
        Label_0243: {
            if (!a) {
                if (n11 != 0) {
                    final int n14 = b;
                    final int n15 = 8;
                    int n16 = 0;
                    Label_0119: {
                        if (!a) {
                            if (n14 > n15) {
                                return;
                            }
                            final int n17;
                            n16 = (n17 = b);
                            if (a) {
                                break Label_0119;
                            }
                        }
                        if (n14 == n15) {
                            this.c ^= 0x1;
                            if (!a) {
                                break Label_0243;
                            }
                        }
                        n16 = b;
                    }
                    if (n16 == 0) {
                        this.c ^= 0x2;
                        if (!a) {
                            break Label_0243;
                        }
                    }
                    this.b = 8 - b;
                    this.repaint(192, 5, 10, 10);
                    if (!a) {
                        break Label_0243;
                    }
                }
                n13 = (n12 = (a2 = b));
            }
            if (!a) {
                if (n12 == 0) {
                    return;
                }
                a2 = (n13 = --b);
            }
            final int n18 = 7;
            if (!a) {
                if (n13 == n18) {
                    this.c ^= 0x1;
                    if (!a) {
                        break Label_0243;
                    }
                }
                a2 = b;
            }
            if (a2 == n18) {
                this.c ^= 0x2;
                if (!a) {
                    break Label_0243;
                }
            }
            this.b = b;
            this.repaint(11, 5, 10, 10);
        }
        final int n19 = this.b + 1;
        this.a.c = (byte)n19;
        this.a.a[1].setForeground(this.a.a[n19]);
        this.a.a[1].setFont(this.a.a[this.c]);
        this.a.e = String.valueOf(this.c) + this.a.a[n19];
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.a.h();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
}
