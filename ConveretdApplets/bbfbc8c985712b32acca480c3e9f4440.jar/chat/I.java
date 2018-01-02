// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Font;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.image.ImageObserver;

public class I implements ImageObserver
{
    public boolean a;
    public boolean b;
    int a;
    int b;
    public int c;
    String a;
    String b;
    public boolean c;
    public boolean d;
    Object a;
    public Color a;
    public K a;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x40) != 0x0) {
            return false;
        }
        this.a.c(this);
        return (n & 0x20) == 0x0;
    }
    
    public final void a(int n) {
        if (n < 15) {
            n = 15;
        }
        this.b = n;
        this.a = n;
        if (this.a != null) {
            this.a.repaint();
            this.a.f();
        }
    }
    
    public final void b(final int a) {
        this.a = a;
        if (this.a != null) {
            this.a.repaint();
        }
    }
    
    public final void c(final int b) {
        this.b = b;
        if (this.a != null) {
            this.a.f();
        }
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
        if (!(this.a instanceof Image)) {
            if (this.a != null) {
                graphics.setColor(b ? Color.white : this.a.getForeground());
                K.a(graphics, this.a.toString(), n + 7, n2 - 12, n3);
            }
            return;
        }
        final Image image;
        final int height = (image = (Image)this.a).getHeight(this.a);
        final int width = image.getWidth(this.a);
        if (height <= 0 || height <= 0) {
            graphics.drawImage(image, -1, -1, 1, 1, this.a);
            return;
        }
        graphics.drawImage(image, n + (n2 - width) / 2, (n3 - height) / 2, this.a);
    }
    
    public boolean a(final Event event, final a a) {
        return false;
    }
    
    final void a(final Graphics graphics, final y y, Object o, int n, final int n2, final int n3, final int n4, final boolean b) {
        if (o instanceof Boolean) {
            final boolean booleanValue = (boolean)o;
            final int n5 = n;
            if (booleanValue) {
                final int n6 = n2 + n4 / 2 + 3;
                final int n7 = n5 + n3 / 2 - 1;
                if (y.a) {
                    if (this.a == null) {
                        graphics.setColor(Color.red);
                    }
                    else {
                        graphics.setColor(this.a);
                    }
                }
                else {
                    graphics.setColor(K.a());
                }
                if (this.d) {
                    graphics.drawLine(n7, n6 - 6, n7 + 6, n6);
                    graphics.drawLine(n7, n6 - 7, n7 + 6, n6 - 1);
                }
                else {
                    graphics.drawLine(n7, n6, n7 - 4, n6 - 4);
                    graphics.drawLine(n7, n6 - 1, n7 - 3, n6 - 4);
                }
                graphics.drawLine(n7, n6, n7 + 6, n6 - 6);
                graphics.drawLine(n7, n6 - 1, n7 + 6, n6 - 7);
            }
            return;
        }
        if (o instanceof Image) {
            final Image image = (Image)o;
            final int n8 = n;
            final Image image2 = image;
            final int height = image2.getHeight(this);
            final int width = image2.getWidth(this);
            if (height == 0 || width == 0) {
                graphics.drawImage(image2, -1, -1, 1, 1, this);
                return;
            }
            graphics.drawImage(image2, n8 + (n3 - width) / 2, n2 + (n4 - height) / 2 - 1, this);
        }
        else {
            if (o instanceof Color) {
                final Color color = (Color)o;
                final int n9 = n;
                final Color color2 = color;
                final int n10 = n9 + (n3 - 18) / 2;
                final int n11 = n2 + (n4 - 18) / 2 - 1;
                graphics.setColor(new Color(15658734));
                graphics.drawRect(n10, n11, 17, 17);
                graphics.drawRect(n10 + 1, n11 + 1, 15, 15);
                graphics.setColor(color2);
                graphics.fillRect(n10 + 2, n11 + 2, 13, 13);
                final int n12 = n10 + 17 - 1;
                final int n13 = n11 + 17 - 1;
                graphics.setColor(Color.gray);
                graphics.drawLine(n10 + 3, n11, n12 - 2, n11);
                graphics.drawLine(n12 - 1, n11 + 1, n12, n11 + 2);
                graphics.drawLine(n12, n11 + 3, n12, n13 - 2);
                graphics.drawLine(n12 - 1, n13 - 1, n12 - 2, n13);
                graphics.drawLine(n12 - 3, n13, n10 + 2, n13);
                graphics.drawLine(n10 + 1, n13 - 1, n10, n13 - 2);
                graphics.drawLine(n10, n13 - 3, n10, n11 + 2);
                graphics.drawLine(n10 + 1, n11 + 1, n10 + 2, n11);
                graphics.setColor(color2);
                graphics.drawLine(n10 + 1, n13 - 2, n10 + 1, n11 + 2);
                graphics.drawLine(n10 + 2, n11 + 1, n12 - 2, n11 + 1);
                graphics.drawLine(n10 + 2, n13 - 3, n10 + 2, n11 + 2);
                graphics.drawLine(n10 + 3, n11 + 2, n12 - 2, n11 + 2);
                graphics.drawLine(n10 + 2, n13 - 1, n12 - 2, n13 - 1);
                graphics.drawLine(n12 - 1, n13 - 2, n12 - 1, n11 + 2);
                graphics.drawLine(n10 + 2, n13 - 2, n12 - 2, n13 - 2);
                graphics.drawLine(n12 - 2, n13 - 3, n12 - 2, n11 + 3);
                return;
            }
            if (o != null) {
                final int n14 = n + 5;
                final int n15 = n2 + n4 / 2;
                if (this.a.a() && y.d > 0) {
                    graphics.setFont(new Font(graphics.getFont().getFamily(), 0, 12));
                    graphics.setColor(Color.red);
                    graphics.fillOval(2, n2 + 1, 15, 15);
                    graphics.setColor(Color.blue);
                    graphics.drawOval(2, n2 + 1, 14, 14);
                    graphics.setColor(Color.white);
                    graphics.drawString(String.valueOf(y.d), (y.d <= 9) ? 6 : 2, n2 + 14);
                }
                if (y.b > 0 && y.b < 5 && cs.a[y.b] != null && cs.a[y.b].getHeight(this) != 0) {
                    graphics.drawImage(cs.a[y.b], n14 - 11, n2 + (n4 - 2) - cs.a[y.b].getHeight(this), this);
                }
                if (y.a != 0) {
                    try {
                        final Image image3;
                        if (!y.d && (image3 = (Image)y.a.a("sImage")) != null) {
                            final int height2 = image3.getHeight(this);
                            final int width2 = image3.getWidth(this);
                            if (height2 == 0 || width2 == 0) {
                                graphics.drawImage(image3, -1, -1, 1, 1, this);
                            }
                            else {
                                graphics.drawImage(image3, n, n15 - height2 / 2, this);
                            }
                            o = "";
                        }
                        final Image image4;
                        if ((image4 = (Image)y.a.a("stars")) != null) {
                            graphics.drawImage(image4, n14 - 2, n2 + (n4 - image4.getHeight(this)) / 2, this);
                        }
                        n += 14;
                    }
                    catch (Exception ex) {}
                }
                final String string = o.toString();
                final int n16 = n;
                final String s = string;
                graphics.setFont(this.a.getFont());
                Font font;
                if (y.c && !y.e) {
                    font = new Font(graphics.getFont().getFamily(), 1, graphics.getFont().getSize());
                }
                else if (y.c && y.e) {
                    font = new Font(graphics.getFont().getFamily(), 3, graphics.getFont().getSize());
                }
                else if (y.e) {
                    font = new Font(graphics.getFont().getFamily(), 2, graphics.getFont().getSize());
                }
                else {
                    font = new Font(graphics.getFont().getFamily(), 0, graphics.getFont().getSize());
                }
                graphics.setFont(font);
                if (b) {
                    graphics.setColor(y.a ? y.b : Color.lightGray);
                }
                else {
                    graphics.setColor(y.a ? y.a : Color.gray);
                }
                K.a(graphics, s, n16 + 5, n2, n3 - 8, n4, this.c, y.b, y.a);
                if (this.a.b()) {
                    try {
                        final Image image5;
                        if ((image5 = (Image)y.a.a("flag")) != null) {
                            final int height3 = image5.getHeight(this);
                            final int width3 = image5.getWidth(this);
                            if (height3 == 0 || width3 == 0) {
                                graphics.drawImage(image5, -1, -1, 1, 1, this);
                                return;
                            }
                            graphics.drawImage(image5, n14 + n3 - 15 - 7, n2 + (n4 - 9) / 2, this);
                        }
                    }
                    catch (Exception ex2) {}
                }
            }
        }
    }
    
    public I(final String s) {
        this(s, s);
    }
    
    public I(final Object a, final String a2) {
        this.a = false;
        this.b = false;
        this.a = 50;
        this.b = 50;
        this.a = a2;
        this.a = a;
    }
}
