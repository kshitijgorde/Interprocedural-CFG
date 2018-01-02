import java.awt.Container;
import javax.swing.JComponent;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.image.DataBufferInt;
import java.awt.ImageCapabilities;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.VolatileImage;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class F extends JPanel
{
    protected U a;
    private BufferedImage b;
    private VolatileImage c;
    private boolean d;
    private Graphics e;
    private int f;
    private int g;
    private int[] h;
    private int[] i;
    private int j;
    private boolean k;
    private long l;
    private String m;
    private int n;
    private Font o;
    private int p;
    
    public F(final U a, final int f, final int g) {
        super(false);
        this.d = false;
        this.k = false;
        this.o = new Font("Verdana", 1, 10);
        this.p = Color.white.darker().getRGB();
        this.a = a;
        this.f = f;
        this.g = g;
        this.j = -1;
    }
    
    public final void a(final int p) {
        this.p = p;
    }
    
    public final void b(int n) {
        if (j != this.j) {
            final boolean b = c(j) != c(this.j);
            final boolean b2 = d(j) != d(this.j);
            this.j = j;
            if (b || b2) {
                n = (int)this;
                final int d = d(this.j);
                if (!c(((F)n).j)) {
                    ((F)n).b = new BufferedImage(((F)n).f * d, ((F)n).g * d, 1);
                }
                else {
                    ((F)n).b = new BufferedImage(((F)n).f, ((F)n).g, 1);
                    (((F)n).e = ((F)n).b.createGraphics()).setFont(((F)n).o);
                    final Graphics2D graphics2D;
                    (graphics2D = (Graphics2D)((F)n).e).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                    try {
                        ((F)n).c = ((Component)n).createVolatileImage(((F)n).f, ((F)n).g, new ImageCapabilities(true));
                    }
                    catch (Exception ex) {
                        ((F)n).j = 3;
                        ((F)n).b = new BufferedImage(((F)n).f * d, ((F)n).g * d, 1);
                    }
                }
                (((F)n).e = ((F)n).b.createGraphics()).setFont(((F)n).o);
                final Graphics2D graphics2D2;
                (graphics2D2 = (Graphics2D)((F)n).e).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
                graphics2D2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                final int[] data = ((DataBufferInt)((F)n).b.getRaster().getDataBuffer()).getData();
                if (((F)n).j == 0 || ((F)n).j == 1 || ((F)n).j == 2) {
                    ((F)n).h = data;
                    ((F)n).a.c.d = data;
                }
                else {
                    ((F)n).i = data;
                }
                for (int i = 0; i < data.length; ++i) {
                    data[i] = ((F)n).p;
                }
                ((Component)n).setSize(((F)n).f * d, ((F)n).g * d);
                ((Component)n).setBounds(((JComponent)n).getX(), ((JComponent)n).getY(), ((F)n).f * d, ((F)n).g * d);
                ((Container)n).invalidate();
                ((Component)n).repaint();
            }
        }
    }
    
    public void a() {
        this.b(0);
    }
    
    public void b(final boolean b) {
        if (!b) {
            if (this.j != 0) {
                if (this.j == 3) {
                    final int[] h = this.h;
                    final int[] i = this.i;
                    final boolean[] e = this.a.c.e;
                    final int[] array = i;
                    final int[] array2 = h;
                    int n = 0;
                    int n2 = 512;
                    for (int j = 0; j < 240; ++j) {
                        if (e[j]) {
                            for (int n3 = j + 1 << 8, k = j << 8; k < n3; ++k) {
                                final int n4 = array2[k];
                                array[n++] = n4;
                                array[n++] = n4;
                                array[n2++] = n4;
                                array[n2++] = n4;
                            }
                        }
                        else {
                            n += 512;
                            n2 += 512;
                        }
                        n += 512;
                        n2 += 512;
                    }
                }
                else if (this.j == 4) {
                    final int[] h2 = this.h;
                    final int[] l = this.i;
                    final boolean[] e2 = this.a.c.e;
                    final int[] array3 = l;
                    final int[] array4 = h2;
                    int n5 = 0;
                    int n6 = 512;
                    for (int n7 = 0; n7 < 240; ++n7) {
                        if (e2[n7]) {
                            for (int n8 = n7 + 1 << 8, n9 = n7 << 8; n9 < n8; ++n9) {
                                final int n10 = array4[n9];
                                array3[n5] = n10;
                                array3[++n5] = n10;
                                final int n11 = n10 - (n10 >> 2 & 0x3F3F3F);
                                array3[n6] = n11;
                                array3[++n6] = n11;
                                ++n5;
                                ++n6;
                            }
                        }
                        else {
                            n5 += 512;
                            n6 += 512;
                        }
                        n5 += 512;
                        n6 += 512;
                    }
                }
                else if (this.j == 5) {
                    final int[] h3 = this.h;
                    final int[] m = this.i;
                    final boolean[] e3 = this.a.c.e;
                    final int[] array5 = m;
                    final int[] array6 = h3;
                    int n12 = 0;
                    int n13 = 512;
                    int n14 = 0;
                    for (int n15 = 0; n15 < 240; ++n15) {
                        if (e3[n15]) {
                            for (int n16 = n15 + 1 << 8, n17 = n15 << 8; n17 < n16; ++n17) {
                                final int n18 = array6[n17];
                                array5[n12] = n18;
                                array5[n13] = (array5[++n12] = n18);
                                array5[++n13] = n18;
                                final int n19 = n18 - 0;
                                final int n20 = n18 + 0;
                                array5[n12 + (0x200 & n14) - 1] = (array5[n12 + (0x200 & n14)] = n19);
                                array5[n12 + 512 & 512 - n14] = n20;
                                n14 = 512 - n14;
                                ++n12;
                                ++n13;
                            }
                        }
                        else {
                            n12 += 512;
                            n13 += 512;
                        }
                        n12 += 512;
                        n13 += 512;
                    }
                }
            }
            this.a.c.f = false;
            this.paint(this.getGraphics());
        }
    }
    
    public final int[] b() {
        return this.h;
    }
    
    public void update(final Graphics graphics) {
    }
    
    public final boolean c() {
        return this.j != 0;
    }
    
    public void paint(final Graphics graphics) {
        if (this.j != 0) {
            this.a(0, 14);
            if (this.j == 1) {
                if (graphics != null && this.b != null && this.c != null) {
                    this.c.getGraphics().drawImage(this.b, 0, 0, null);
                    graphics.drawImage(this.c, 0, 0, this.f << 1, this.g << 1, null);
                }
            }
            else if (this.j == 2) {
                if (graphics != null && this.b != null && this.c != null) {
                    this.c.getGraphics().drawImage(this.b, 0, 0, null);
                    graphics.drawImage(this.c, 0, 0, this.f * 3, this.g * 3, null);
                }
            }
            else if (graphics != null && this.b != null) {
                graphics.drawImage(this.b, 0, 0, this.f << 1, this.g << 1, null);
            }
            return;
        }
        if (this.b != null && graphics != null) {
            this.a(0, 14);
            graphics.drawImage(this.b, 0, 0, null);
        }
    }
    
    public final void c(final boolean k) {
        this.k = k;
    }
    
    private void a(final int n, final int n2) {
        if (this.k) {
            if (--this.n <= 0) {
                final long l;
                final long n3;
                if ((n3 = ((l = System.nanoTime() / 1000L) - this.l) / 45L) == 0L) {
                    this.m = "FPS: -";
                }
                else {
                    this.m = "FPS: " + 1000000L / n3;
                }
                this.n = 45;
                this.l = l;
            }
            this.e.setColor(Color.black);
            this.e.fillRect(0, 14 - this.e.getFontMetrics().getAscent(), this.e.getFontMetrics().stringWidth(this.m) + 3, this.e.getFontMetrics().getHeight());
            this.e.setColor(Color.cyan);
            this.e.drawString(this.m, 0, 14);
        }
    }
    
    public final boolean d() {
        return c(this.j);
    }
    
    private static boolean c(final int n) {
        return n == 1 || n == 2;
    }
    
    private static int d(final int n) {
        if (n == -1) {
            return -1;
        }
        if (n == 0) {
            return 1;
        }
        if (n == 2) {
            return 3;
        }
        return 2;
    }
    
    public final void e() {
        this.a = null;
        this.b = null;
    }
}
