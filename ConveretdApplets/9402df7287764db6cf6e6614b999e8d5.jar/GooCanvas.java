import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class GooCanvas extends Canvas implements MouseListener, MouseMotionListener
{
    static QGoo applet;
    public static Image img;
    public static int iw;
    public static int ih;
    public static int dx;
    public static int dy;
    
    public GooCanvas(final QGoo applet) {
        GooCanvas.applet = applet;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    private int add(final int n, final int n2, final int n3) {
        return Math.max(0, Math.min(255, ((n & 0xFF0000) >> 16) + ((n2 & 0xFF0000) >> 16) * n3)) << 16 | Math.max(0, Math.min(255, ((n & 0xFF00) >> 8) + ((n2 & 0xFF00) >> 8) * n3)) << 8 | Math.max(0, Math.min(255, (n & 0xFF) + (n2 & 0xFF) * n3)) | 0xFF000000;
    }
    
    private int blend(final int n, final int n2, final float n3) {
        return Math.round(((n & 0xFF0000) >> 16) * n3 + ((n2 & 0xFF0000) >> 16) * (1.0f - n3)) << 16 | Math.round(((n & 0xFF00) >> 8) * n3 + ((n2 & 0xFF00) >> 8) * (1.0f - n3)) << 8 | Math.round((n & 0xFF) * n3 + (n2 & 0xFF) * (1.0f - n3)) | 0xFF000000;
    }
    
    Image calculateImage(final int n) {
        int n2 = 0;
        if (n > 0) {
            for (int i = 0; i < GooCanvas.ih; ++i) {
                for (int j = 0; j < GooCanvas.iw; ++j) {
                    final float n3 = GooCanvas.applet.transmapx[j + i * GooCanvas.iw];
                    final float n4 = GooCanvas.applet.transmapy[j + i * GooCanvas.iw];
                    if (i == GooCanvas.ih - 1 && (Math.rint(n3) == n3 || j == GooCanvas.iw - 1) && (Math.rint(n4) == n4 || i == GooCanvas.ih - 1)) {
                        GooCanvas.applet.modpix[n2++] = GooCanvas.applet.origpix[(int)GooCanvas.applet.transmapx[j + i * GooCanvas.iw] + (int)GooCanvas.applet.transmapy[j + i * GooCanvas.iw] * GooCanvas.iw];
                    }
                    else {
                        final float n5 = GooCanvas.applet.transmapx[j + i * GooCanvas.iw] - (float)Math.floor(GooCanvas.applet.transmapx[j + i * GooCanvas.iw]);
                        final float n6 = GooCanvas.applet.transmapy[j + i * GooCanvas.iw] - (float)Math.floor(GooCanvas.applet.transmapy[j + i * GooCanvas.iw]);
                        final int n7 = (int)GooCanvas.applet.transmapx[j + i * GooCanvas.iw];
                        final int n8 = (int)GooCanvas.applet.transmapy[j + i * GooCanvas.iw];
                        if (n7 >= GooCanvas.iw - 2 || n8 >= GooCanvas.ih - 2) {
                            GooCanvas.applet.modpix[n2++] = GooCanvas.applet.origpix[Math.round(GooCanvas.applet.transmapx[j + i * GooCanvas.iw]) + Math.round(GooCanvas.applet.transmapy[j + i * GooCanvas.iw]) * GooCanvas.iw];
                        }
                        else {
                            GooCanvas.applet.modpix[n2++] = this.blend(this.blend(GooCanvas.applet.origpix[n7 + 1 + GooCanvas.iw + n8 * GooCanvas.iw], GooCanvas.applet.origpix[n7 + GooCanvas.iw + n8 * GooCanvas.iw], n5), this.blend(GooCanvas.applet.origpix[n7 + 1 + n8 * GooCanvas.iw], GooCanvas.applet.origpix[n7 + n8 * GooCanvas.iw], n5), n6);
                        }
                    }
                }
            }
        }
        else {
            for (int k = 0; k < GooCanvas.ih; ++k) {
                for (int l = 0; l < GooCanvas.iw; ++l) {
                    GooCanvas.applet.modpix[n2++] = GooCanvas.applet.origpix[Math.round(GooCanvas.applet.transmapx[l + k * GooCanvas.iw]) + Math.round(GooCanvas.applet.transmapy[l + k * GooCanvas.iw]) * GooCanvas.iw];
                }
            }
        }
        return this.createImage(new MemoryImageSource(GooCanvas.iw, GooCanvas.ih, ColorModel.getRGBdefault(), GooCanvas.applet.modpix, 0, GooCanvas.iw));
    }
    
    private void doGoo(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = 0;
        switch (n5) {
            default: {
                n6 = 60;
                break;
            }
            case 1: {
                n6 = 30;
                break;
            }
            case 2: {
                n6 = 60;
                break;
            }
            case 3: {
                n6 = 30;
                break;
            }
            case 4: {
                n6 = 10;
                break;
            }
            case 5: {
                n6 = 10;
                break;
            }
            case 6: {
                n6 = 10;
                break;
            }
        }
        if (n5 > 4) {
            int n7;
            if (n5 > 5) {
                n7 = -1;
            }
            else {
                n7 = 1;
            }
            final int n8 = Math.round(GooCanvas.applet.transmapx[n3 + n4 * GooCanvas.iw]) - n6;
            final int n9 = Math.round(GooCanvas.applet.transmapy[n3 + n4 * GooCanvas.iw]) - n6;
            for (int i = 0; i < n6 * 2; ++i) {
                for (int j = 0; j < n6 * 2; ++j) {
                    float n10 = (float)Math.sqrt((j - n6) * (j - n6) + (i - n6) * (i - n6));
                    if (n10 > n6) {
                        n10 = n6;
                    }
                    final float n11 = n10 / n6;
                    if (n8 + j >= 0) {
                        if (n8 + j < GooCanvas.iw) {
                            if (n9 + i >= 0) {
                                if (n9 + i < GooCanvas.ih) {
                                    final int n12 = n8 + j + (n9 + i) * GooCanvas.iw;
                                    GooCanvas.applet.origpix[n12] = this.blend(GooCanvas.applet.origpix[n12], this.add(GooCanvas.applet.origpix[n12], 460551, n7), n11);
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            final float[] array = new float[n6 * n6 * 4];
            final float[] array2 = new float[n6 * n6 * 4];
            for (int k = 0; k < n6 * 2; ++k) {
                for (int l = 0; l < n6 * 2; ++l) {
                    final int n13 = l + k * n6 * 2;
                    final int n14 = Math.min(Math.max(0, l + n - n6), GooCanvas.iw - 1) + Math.min(Math.max(0, k + n2 - n6), GooCanvas.ih - 1) * GooCanvas.iw;
                    array[n13] = GooCanvas.applet.transmapx[n14];
                    array2[n13] = GooCanvas.applet.transmapy[n14];
                }
            }
            for (int n15 = 0; n15 < n6 * 2; ++n15) {
                for (int n16 = 0; n16 < n6 * 2; ++n16) {
                    final float n17 = (float)Math.sqrt((n16 - n6) * (n16 - n6) + (n15 - n6) * (n15 - n6));
                    if (n17 <= n6) {
                        final float n18 = n17 / n6;
                        final int n19 = n16 + n3 - n6;
                        if (n19 >= 0) {
                            if (n19 < GooCanvas.iw) {
                                final int n20 = n15 + n4 - n6;
                                if (n20 >= 0) {
                                    if (n20 < GooCanvas.ih) {
                                        final int n21 = n16 + n15 * n6 * 2;
                                        final int n22 = n16 + n3 - n6 + (n15 + n4 - n6) * GooCanvas.iw;
                                        if (n5 < 2) {
                                            final float n23 = n18 * n18;
                                            GooCanvas.applet.transmapx[n22] = array[n21] * (1.0f - n23) + GooCanvas.applet.transmapx[n22] * n23;
                                            GooCanvas.applet.transmapy[n22] = array2[n21] * (1.0f - n23) + GooCanvas.applet.transmapy[n22] * n23;
                                        }
                                        else if (n5 < 4) {
                                            final float n24 = n18 * n18;
                                            GooCanvas.applet.transmapx[n22] = (GooCanvas.applet.transmapx[n22] * 3.0f + n16 + n3 - n6) / 4.0f * (1.0f - n24) + GooCanvas.applet.transmapx[n22] * n24;
                                            GooCanvas.applet.transmapy[n22] = (GooCanvas.applet.transmapy[n22] * 3.0f + n15 + n4 - n6) / 4.0f * (1.0f - n24) + GooCanvas.applet.transmapy[n22] * n24;
                                        }
                                        else if (n5 < 5 && n22 >= GooCanvas.iw + 1) {
                                            if (n22 <= GooCanvas.iw * (GooCanvas.ih - 1) - 1) {
                                                GooCanvas.applet.transmapx[n22] = (GooCanvas.applet.transmapx[n22] + GooCanvas.applet.transmapx[n22 + 1] + GooCanvas.applet.transmapx[n22 - 1] + GooCanvas.applet.transmapx[n22 - GooCanvas.iw] + GooCanvas.applet.transmapx[n22 + GooCanvas.iw]) / 5.0f * (1.0f - n18) + GooCanvas.applet.transmapx[n22] * n18;
                                                GooCanvas.applet.transmapy[n22] = (GooCanvas.applet.transmapy[n22] + GooCanvas.applet.transmapy[n22 + 1] + GooCanvas.applet.transmapy[n22 - 1] + GooCanvas.applet.transmapy[n22 - GooCanvas.iw] + GooCanvas.applet.transmapy[n22 + GooCanvas.iw]) / 5.0f * (1.0f - n18) + GooCanvas.applet.transmapy[n22] * n18;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public Image getImage() {
        return GooCanvas.img;
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(GooCanvas.iw, GooCanvas.ih);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(GooCanvas.iw, GooCanvas.ih);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int dx = GooCanvas.dx;
        final int dy = GooCanvas.dy;
        final int n = (GooCanvas.dx + x) / 2;
        final int n2 = (GooCanvas.dy + y) / 2;
        final GooControls gcontrols = QGoo.gcontrols;
        this.doGoo(dx, dy, n, n2, GooControls.choice.getSelectedIndex());
        GooCanvas.dx = x;
        GooCanvas.dy = y;
        this.recalc(0);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        GooCanvas.applet.showStatus("Click and drag to start Goo-ing!");
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        GooCanvas.applet.showStatus("");
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public synchronized void mousePressed(final MouseEvent mouseEvent) {
        GooCanvas.dx = mouseEvent.getX();
        GooCanvas.dy = mouseEvent.getY();
        final GooControls gcontrols = QGoo.gcontrols;
        if (GooControls.choice.getSelectedIndex() > 1) {
            final int dx = GooCanvas.dx;
            final int dy = GooCanvas.dy;
            final int dx2 = GooCanvas.dx;
            final int dy2 = GooCanvas.dy;
            final GooControls gcontrols2 = QGoo.gcontrols;
            this.doGoo(dx, dy, dx2, dy2, GooControls.choice.getSelectedIndex());
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int dx = GooCanvas.dx;
        final int dy = GooCanvas.dy;
        final int n = (GooCanvas.dx + x) / 2;
        final int n2 = (GooCanvas.dy + y) / 2;
        final GooControls gcontrols = QGoo.gcontrols;
        this.doGoo(dx, dy, n, n2, GooControls.choice.getSelectedIndex());
        GooCanvas.dx = x;
        GooCanvas.dy = y;
        this.recalc(1);
    }
    
    public void paint(final Graphics graphics) {
        if (GooCanvas.img != null && graphics != null) {
            graphics.drawImage(GooCanvas.img, 0, 0, GooCanvas.iw, GooCanvas.ih, this);
        }
    }
    
    public void recalc(final int n) {
        GooCanvas.img = this.calculateImage(n);
        this.paint(this.getGraphics());
    }
    
    public void reset() {
        for (int i = 0; i < GooCanvas.ih; ++i) {
            for (int j = 0; j < GooCanvas.iw; ++j) {
                GooCanvas.applet.transmapx[i * GooCanvas.iw + j] = j;
                GooCanvas.applet.transmapy[i * GooCanvas.iw + j] = i;
            }
        }
        System.arraycopy(GooCanvas.applet.bakpix, 0, GooCanvas.applet.origpix, 0, GooCanvas.iw * GooCanvas.ih);
        this.recalc(0);
    }
    
    public void setImage(final Image img) {
        GooCanvas.img = img;
        this.paint(this.getGraphics());
    }
    
    public void setsize(final int iw, final int ih) {
        GooCanvas.iw = iw;
        GooCanvas.ih = ih;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
