import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class k extends Canvas
{
    CHAT a;
    int a;
    Image a;
    String a;
    boolean b;
    boolean a;
    int b;
    
    public k(final CHAT a) {
        this.a = 40;
        this.a = " ";
        this.b = 0;
        this.a = a;
        this.a = this.createImage(new MemoryImageSource(5, 5, a.a(5, 5, 2.0, a.b[36], a.b[38]), 0, 5));
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void a() {
        this.a.u = true;
    }
    
    public void a(final Graphics graphics) {
        final boolean a = d.a;
        final boolean equals = this.a.equals(" ");
        if (!a && equals) {}
        final int enabled = equals ? 1 : 0;
        this.a.a.setEnabled(enabled != 0);
        int value;
        final int n = value = enabled;
        if (!a) {
            if (n == 0) {
                this.a.a.setState(false);
            }
            value = this.a.a[1].getValue();
        }
        int value2 = value;
        int n2 = 0;
        final int visibleAmount = this.a.a[1].getSize().height / 15;
        final int size = this.a.a[0].size();
        final int n3 = visibleAmount;
        int n4;
        if (!a && n3 < size) {
            n4 = visibleAmount;
            this.a.a[1].setMaximum(size);
            this.a.a[1].setVisibleAmount(visibleAmount);
            this.a.a[1].setEnabled(true);
            if (a) {
                goto Label_0175;
            }
        }
        else {
            n4 = n3;
            this.a.a[1].setEnabled(false);
        }
        final int n5 = size - n4;
        final int n6 = value2;
        if (!a && n5 < n6) {
            final int n7 = n5;
            if (!a && n7 < 0) {
                value2 = 0;
                if (a) {
                    goto Label_0227;
                }
            }
            else {
                value2 = n7;
            }
            this.a.a[1].setValue(value2);
            goto Label_0243;
        }
        int n8 = value2;
        int c = this.a.c;
        final int k = this.a.k;
        final int n9 = c;
        if (a || n9 > 0) {
            c = n9;
            --c;
        }
        final Color color = new Color(-986896);
        boolean b = true;
        final boolean h = this.a.h;
        final int n10 = this.a.a[1].getSize().width - 5;
        int n11 = 0;
        while (true) {
            Label_0743: {
                if (!a) {
                    break Label_0743;
                }
                n2 += 15;
                final String s = this.a.a[0].elementAt(n8);
                final Font font = this.a.a[2].elementAt(n8);
                graphics.setFont(font);
                final boolean booleanValue = this.a.a[4].elementAt(n8);
                Color color2 = this.a.a[1].elementAt(n8);
                int equals2;
                int n13;
                final int n12 = n13 = (equals2 = (s.equals(this.a) ? 1 : 0));
                Label_0708: {
                    Graphics graphics2 = null;
                    Label_0700: {
                        Label_0669: {
                            Label_0656: {
                                if (!a) {
                                    if (n12 != 0) {
                                        this.a.b.setEnabled(true);
                                        this.a.a.setEnabled(true);
                                        b = false;
                                        Label_0573: {
                                            if (!a) {
                                                if (!booleanValue) {
                                                    this.a.b.setLabel(this.a.c[11]);
                                                    this.a.b.invalidate();
                                                    this.a.a[19].validate();
                                                    if (!a) {
                                                        break Label_0573;
                                                    }
                                                }
                                                this.a.b.setLabel(this.a.c[10]);
                                                this.a.b.invalidate();
                                            }
                                            this.a.a[19].validate();
                                        }
                                        final Color color3 = color2;
                                        Label_0594: {
                                            if (!a) {
                                                if (!color3.equals(Color.lightGray)) {
                                                    break Label_0594;
                                                }
                                                final Color white = Color.white;
                                            }
                                            color2 = color3;
                                        }
                                        graphics.setColor(Color.lightGray);
                                        graphics.fillRect(3, n2 - 12, n10 - 2, 15);
                                        if (!a) {
                                            break Label_0656;
                                        }
                                    }
                                    equals2 = (n13 = n11);
                                }
                                if (a) {
                                    break Label_0669;
                                }
                                if (n13 == c) {
                                    graphics.setColor(color);
                                    graphics.fillRect(3, n2 - 12, n10 - 2, 15);
                                }
                            }
                            graphics2 = graphics;
                            if (a) {
                                break Label_0700;
                            }
                            graphics.setColor(color2);
                            equals2 = (h ? 1 : 0);
                        }
                        if (equals2 != 0) {
                            graphics.drawString(s, n10 - graphics.getFontMetrics(font).stringWidth(s), n2);
                            if (!a) {
                                break Label_0708;
                            }
                        }
                        graphics2 = graphics;
                    }
                    graphics2.drawString(s, 4, n2);
                }
                if (!booleanValue) {
                    graphics.setColor(Color.black);
                    graphics.drawLine(3, n2 - 4, n10 - 2, n2 - 4);
                }
                ++n11;
                ++n8;
            }
            if (n11 >= n4) {
                if (b) {
                    this.a.b.setEnabled(false);
                    this.a.a.setEnabled(false);
                }
                this.a.a.a();
                if (CHAT.A) {
                    d.a = !a;
                }
                return;
            }
            continue;
        }
    }
    
    public void paint(final Graphics graphics) {
        final Image b = this.a.b;
        int height;
        if (b != null && !this.b && (height = b.getHeight(this.a)) > 0) {
            if (height > 200) {
                height = 200;
            }
            this.setSize(145, this.b = height + 30);
            final boolean b2 = true;
            this.a = b2;
            this.b = b2;
            this.a.a[3].validate();
        }
        else if (!this.a) {
            this.setSize(145, 25);
            this.a = true;
            this.b = 25;
        }
        if (this.b) {
            graphics.drawImage(this.a.b, (145 - this.a.b.getWidth(this)) / 2, (this.b + 15 - this.a.b.getHeight(this)) / 2, this);
        }
        for (int i = 7; i < 135; i += 10) {
            graphics.drawImage(this.a, i, 5, this);
        }
        for (int j = 12; j < 130; j += 10) {
            graphics.drawImage(this.a, j, 13, this);
        }
    }
}
