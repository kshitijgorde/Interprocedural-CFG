import java.awt.Container;
import java.awt.Component;
import netscape.javascript.JSObject;
import java.awt.MediaTracker;
import sun.audio.AudioStream;
import java.applet.AppletContext;
import java.net.URL;
import java.util.StringTokenizer;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioDataStream;
import sun.audio.ContinuousAudioDataStream;
import sun.audio.AudioData;
import java.applet.AudioClip;
import java.awt.FontMetrics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Event;
import java.util.Vector;
import java.awt.Point;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BtnPlayer extends Applet implements Runnable
{
    int[] A;
    Image[] B;
    int[] C;
    Object D;
    Object[] E;
    Color[] F;
    String[] G;
    String[] H;
    int I;
    boolean J;
    boolean K;
    int L;
    boolean M;
    boolean N;
    boolean O;
    boolean P;
    String Q;
    String R;
    String S;
    Frame T;
    Image U;
    Image V;
    boolean W;
    int X;
    int Y;
    int Z;
    int AA;
    Point AB;
    int AC;
    int AD;
    int AE;
    Color AF;
    int AG;
    int AH;
    Point AI;
    String AJ;
    int AK;
    String AL;
    Vector AM;
    Vector AN;
    Vector AO;
    Vector AP;
    Vector AQ;
    Vector AR;
    final int AS = 51;
    final int AT = 19;
    final int AU = 15;
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.I >= 0) {
            Rectangle rectangle = null;
            final int cc = this.CC(n, n2);
            final int[] c = this.C;
            if (this.AD != -1 && this.AD != cc) {
                final int n3 = this.AD * 51;
                final int n4 = n3 + 51 - 1;
                final int n5 = c[n4];
                if (c[n4] == 1 && c[n3 + 5] == 0) {
                    c[n4] = 0;
                    this.BH(this.AD);
                    rectangle = this.BN(this.AD, rectangle, 0);
                }
                if ((this.N || this.O) && (c[n3 + 6] & 0x2) == 0x0) {
                    this.CD(0);
                }
                this.AD = -1;
            }
            if (cc < 0) {
                this.AD = cc;
                this.showStatus("");
            }
            else if (cc != this.AD) {
                final int n6 = cc * 51;
                final int n7 = n6 + 51 - 1;
                final int n8 = c[n7];
                final String s = this.G[cc * 19 + 12];
                this.showStatus((s != null) ? s : "");
                if (c[n7] == 0 && c[n6 + 5] == 0) {
                    c[n7] = 1;
                    this.BH(cc);
                    this.BJ(cc, 1);
                    rectangle = this.BN(cc, rectangle, 1);
                }
                if ((this.N || this.O) && (c[n6 + 6] & 0x2) == 0x0) {
                    this.CD(12);
                }
                this.AD = cc;
            }
            if (rectangle != null) {
                this.BQ(rectangle);
            }
        }
        return true;
    }
    
    public void BV(final Graphics graphics, final int n, final int n2, final int n3, final Image image, final Vector vector) {
        try {
            final boolean b = n == this.AA;
            final int n4 = n * 6 + ((n3 == 0) ? 0 : 3) + n2;
            final Image[] b2 = this.B;
            if (n3 == 1 || b || b2[n4] == null) {
                final int[] c = this.C;
                final int n5 = n * 51;
                final int n6 = c[n5 + 2];
                final int n7 = c[n5 + 3];
                final Color color = this.F[n * 15 + n2];
                final int n8 = c[n5 + 14 + n2];
                final boolean b3 = (n8 & 0x100) != 0x0;
                final int n9 = n8 & 0xFF;
                final int n10 = c[n5 + 11 + n2];
                final int red = color.getRed();
                final int green = color.getGreen();
                final int blue = color.getBlue();
                final int min = Math.min(red + n9, 255);
                final int min2 = Math.min(green + n9, 255);
                final int min3 = Math.min(blue + n9, 255);
                final int max = Math.max(red - n9, 0);
                final int max2 = Math.max(green - n9, 0);
                final int max3 = Math.max(blue - n9, 0);
                final Color color2 = new Color(min, min2, min3);
                final Color color3 = new Color(max, max2, max3);
                final int n11 = c[n5 + 8 + n2];
                final int n12 = (n11 > 1) ? n10 : 0;
                final int n13 = (n11 == 3 || n11 == 5) ? 2 : 0;
                final int n14 = n6 * n7;
                int[] array = null;
                if (n3 == 0) {
                    if (n11 == 0 || vector != null) {
                        return;
                    }
                    if (!b) {
                        if (n14 <= 0) {
                            return;
                        }
                        array = new int[n14];
                    }
                    if (n11 == 1 || n11 > 3) {
                        if (b) {
                            graphics.setColor(color);
                            graphics.fillRect(0, 0, n6, n7);
                        }
                        else {
                            final int n15 = color.getRGB() | 0xFF000000;
                            for (int i = 0; i < n6; ++i) {
                                array[i] = n15;
                            }
                            for (int n16 = 0, j = 0; j < n7 - 1; ++j, n16 += n6) {
                                System.arraycopy(array, n16, array, n16 + n6, n6);
                            }
                        }
                    }
                    if (n10 > 0 && n11 > 1) {
                        for (int k = 0; k < n10; ++k) {
                            final int n17 = n10 - k;
                            final Color color4 = b3 ? color2 : new Color(red + (min - red) * n17 / n10, green + (min2 - green) * n17 / n10, blue + (min3 - blue) * n17 / n10);
                            final Color color5 = b3 ? color3 : new Color(red + (max - red) * n17 / n10, green + (max2 - green) * n17 / n10, blue + (max3 - blue) * n17 / n10);
                            final Color color6 = (n11 == 3 || n11 == 5) ? color5 : color4;
                            this.BT(b, graphics, array, n6, k, k, n6 - 1 - k, color6);
                            this.BU(b, graphics, array, n6, k, k, n7 - 1 - k, color6);
                            final Color color7 = (n11 == 3 || n11 == 5) ? color4 : color5;
                            this.BU(b, graphics, array, n6, n6 - 1 - k, k, n7 - 1 - k, color7);
                            this.BT(b, graphics, array, n6, n7 - 1 - k, k, n6 - 1 - k, color7);
                        }
                    }
                }
                else {
                    if (n3 == 1) {
                        final Image be = this.BE(this.AN, this.A[n * 3 + n2]);
                        if (be != null) {
                            this.checkImage(be, this);
                            final int width = be.getWidth(this);
                            final int height = be.getHeight(this);
                            if (width != -1 && height != -1) {
                                if (vector != null && image == be) {
                                    vector.addElement(new Rectangle(c[n5], c[n5 + 1], c[n5 + 2], c[n5 + 3]));
                                    return;
                                }
                                final int n18 = (n7 - height) / 2 + n13;
                                final int n19 = c[n5 + 35 + n2];
                                switch (((n19 & 0x10) != 0x0) ? 4 : (n19 & 0x3)) {
                                    case 0: {
                                        graphics.drawImage(be, n12 + n13, n18, this);
                                    }
                                    default: {
                                        graphics.drawImage(be, (n6 - width) / 2 + n13, n18, this);
                                    }
                                    case 2: {
                                        graphics.drawImage(be, n6 - width - n12 + n13, n18, this);
                                    }
                                    case 3: {
                                        graphics.drawImage(be, n12, n12, n6 - 2 * n12, n7 - 2 * n12, this);
                                    }
                                    case 4: {
                                        graphics.drawImage(be, c[n5 + 41 + n2] - width / 2, c[n5 + 44 + n2] - height / 2, this);
                                    }
                                }
                            }
                        }
                        return;
                    }
                    if (n3 == 2) {
                        final String s = this.G[n * 19 + 3 + n2];
                        if (s == null || vector != null) {
                            return;
                        }
                        Image image2 = null;
                        final Color color8 = this.F[n * 15 + 3 + n2];
                        Graphics graphics2;
                        if (b) {
                            graphics2 = graphics;
                            graphics2.setColor(color8);
                        }
                        else {
                            if (n14 <= 0) {
                                return;
                            }
                            array = new int[n14];
                            image2 = this.createImage(n6, n7);
                            graphics2 = image2.getGraphics();
                        }
                        final int n20 = c[n5 + 23 + n2];
                        final Font font = new Font(this.H[c[n5 + 17 + n2]], n20 & 0x3, c[n5 + 20 + n2]);
                        graphics2.setFont(font);
                        final FontMetrics fontMetrics = graphics2.getFontMetrics(font);
                        int height2 = fontMetrics.getHeight();
                        int ascent = fontMetrics.getAscent();
                        if (this.O) {
                            height2 = (height2 * 17 + 10) / 20;
                            ascent = (ascent * 17 + 10) / 20;
                        }
                        int index = -2;
                        int n21 = 1;
                        while ((index = s.indexOf("\\n", index + 2)) != -1) {
                            ++n21;
                        }
                        final int n22 = height2 * n21;
                        final int n23 = (c[n5 + 26 + n2] == 3) ? c[n5 + 32 + n2] : ((n7 - n22) / 2 + ascent);
                        final int n24 = n20 & 0x18;
                        final int n25 = (n24 == 8) ? 1 : -1;
                        final int[] array2 = { -1, 1, 0 };
                        final Color[] array3 = { color2, color3, color8 };
                        for (int l = (n24 == 0) ? 2 : 0; l < 3; ++l) {
                            final Color color9 = array3[l];
                            final int n26 = array2[l] * n25;
                            if (!b) {
                                graphics2.setColor(Color.black);
                                graphics2.fillRect(0, 0, n6, n7);
                            }
                            graphics2.setColor(b ? color9 : Color.white);
                            int index2 = 0;
                            for (int n27 = n23, n28 = 0; n28 < n21; ++n28, n27 += height2) {
                                final int n29 = index2;
                                index2 = s.indexOf("\\n", n29);
                                this.BW((index2 == -1) ? s.substring(n29) : s.substring(n29, index2), graphics2, n27 + n13, n26, c, n5, n2, fontMetrics, n6, n13, n12, n20);
                                index2 += 2;
                            }
                            if (!b) {
                                final int[] array4 = new int[n14];
                                final PixelGrabber pixelGrabber = new PixelGrabber(image2, 0, 0, n6, n7, array4, 0, n6);
                                try {
                                    pixelGrabber.grabPixels();
                                }
                                catch (Exception ex) {}
                                final int n30 = color9.getRGB() | 0xFF000000;
                                for (int n31 = 0; n31 < n14; ++n31) {
                                    if ((array4[n31] & 0xFF) > 127) {
                                        array[n31] = n30;
                                    }
                                }
                            }
                        }
                        if (!b) {
                            graphics2.dispose();
                            image2.flush();
                        }
                    }
                }
                if (b) {
                    return;
                }
                b2[n4] = this.createImage(new MemoryImageSource(n6, n7, array, 0, n6));
            }
            if (graphics != null) {
                graphics.drawImage(b2[n4], 0, 0, this);
            }
        }
        catch (Exception ex2) {}
    }
    
    public void setRect(final String s, final int n, final int n2, final int n3, final int n4) {
        for (int i = 0; i < this.AC; ++i) {
            if (s.equals(this.G[i * 19 + 11])) {
                final Rectangle bp = this.BP(i);
                final int[] c = this.C;
                final int n5 = i * 51;
                c[n5] = n;
                c[n5 + 1] = n2;
                c[n5 + 2] = n3;
                c[n5 + 3] = n4;
                this.CE(i);
                this.BQ(bp.union(this.BP(i)));
                return;
            }
        }
    }
    
    public void performAction(final String s) {
        this.BQ(this.performAction(s, null));
    }
    
    void CE(final int n) {
        final int n2 = n * 6;
        int n3 = 0;
        do {
            if (this.B[n2 + n3] != null) {
                this.B[n2 + n3].flush();
                this.B[n2 + n3] = null;
            }
        } while (++n3 < 6);
    }
    
    void BO(final int n, final Rectangle rectangle) {
        final int n2 = n * 51;
        final int[] c = this.C;
        c[n2] = rectangle.x;
        c[n2 + 1] = rectangle.y;
        c[n2 + 2] = rectangle.width;
        c[n2 + 3] = rectangle.height;
    }
    
    public Rectangle BP(final int n) {
        final int n2 = n * 51;
        int n3 = 0;
        final int[] c = this.C;
        int n4 = 0;
        do {
            final int n5 = c[n2 + 38 + n4];
            if (n5 > n3) {
                n3 = n5;
            }
        } while (++n4 < 3);
        return new Rectangle(c[n2] - n3, c[n2 + 1] - n3, c[n2 + 2] + n3 * 2, c[n2 + 3] + n3 * 2);
    }
    
    public void BS(final int n) {
        this.BQ(this.BP(n));
    }
    
    public void setState(final String s, final int n) {
        this.setAttrib(s, 50, n, null, true);
    }
    
    Rectangle setAttrib(final String s, final int n, final int n2, final Rectangle rectangle, final boolean b) {
        Rectangle rectangle2 = rectangle;
        final int index = s.indexOf(42);
        for (int i = 0; i < this.AC; ++i) {
            final String s2 = this.G[i * 19 + 11];
            if ((index >= 0 && s2.regionMatches(0, s, 0, index)) || s2.equals(s)) {
                rectangle2 = this.BK(rectangle2, this.BP(i));
                this.C[i * 51 + n] = n2;
                if (b) {
                    this.CE(i);
                }
                this.BH(i);
                if (n == 50) {
                    final int[] c = this.C;
                    final int n3 = i * 51;
                    if (c[n3 + 7] == 0) {
                        c[n3 + 5] = ((n2 == 2) ? 1 : 0);
                    }
                    else if (c[n3 + 7] == 1) {
                        if ((n2 == 2 && this.Z >= 0 && this.Z != i) || (n2 == 0 && i == this.Z)) {
                            final int n4 = this.Z * 51;
                            c[n4 + 51 - 1] = 0;
                            this.BH(this.Z);
                            c[n4 + 5] = 0;
                            rectangle2 = rectangle2.union(this.BP(this.Z));
                            this.Z = -1;
                        }
                        if (n2 == 2) {
                            c[n3 + 5] = 1;
                            this.Z = i;
                        }
                    }
                }
            }
        }
        if (rectangle == null) {
            this.BQ(rectangle2);
        }
        return rectangle2;
    }
    
    public int getAttrib(final String s, final int n) {
        for (int i = 0; i < this.AC; ++i) {
            if (s.equals(this.G[i * 19 + 11])) {
                return this.C[i * 51 + n];
            }
        }
        return -1;
    }
    
    public int getState(final String s) {
        return this.getAttrib(s, 50);
    }
    
    String BA() {
        return this.AZ(this.AX());
    }
    
    Rectangle BN(final int n, final Rectangle rectangle, final int n2) {
        return this.performAction(this.G[n * 19 + ((n2 >= 3) ? (13 + n2) : (6 + n2))], this.BK(rectangle, this.BP(n)));
    }
    
    int AW(final String s) {
        final String parameter = this.getParameter(s);
        if (parameter != null && parameter.length() > 0) {
            return Integer.parseInt(parameter, 16);
        }
        return Integer.MIN_VALUE;
    }
    
    String AZ(String replace) {
        replace = replace.trim().replace('|', ',').replace('`', '\"');
        if (replace.equals(".")) {
            return null;
        }
        return replace;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (!this.W) {
            final boolean b = (n & 0x10) != 0x0;
            final boolean b2 = (n & 0x20) != 0x0;
            if (b && !b2) {
                final int index = this.AN.indexOf(image);
                if (index >= 0) {
                    final Image be = this.BE(this.AO, index);
                    if (be == null) {
                        this.AO.setElementAt(this.U, index);
                    }
                    else if (be == this.U) {
                        this.AO.setElementAt(image, index);
                    }
                }
            }
            if (b || b2) {
                final Vector<Rectangle> vector = new Vector<Rectangle>();
                this.BZ(image, vector, null);
                for (int i = 0; i < vector.size(); ++i) {
                    this.BQ(vector.elementAt(i));
                }
            }
        }
        return (n & 0xA0) == 0x0;
    }
    
    void BJ(final int n, final int n2) {
        final int n3 = n * 9 + n2;
        Object d = this.E[n3];
        final int n4 = this.C[n * 51 + 35 + n2] >> 2;
        if (d != null) {
            if (this.P) {
                this.D = this.BI(this.D);
            }
            if (d instanceof AudioClip) {
                final AudioClip audioClip = (AudioClip)d;
                if ((n4 & 0x2) != 0x0) {
                    audioClip.loop();
                }
                else {
                    audioClip.play();
                }
            }
            else if (d instanceof AudioData) {
                final AudioData audioData = (AudioData)d;
                this.E[n3 + 6] = this.BI(this.E[n3 + 6]);
                AudioDataStream audioDataStream;
                if ((n4 & 0x2) != 0x0) {
                    audioDataStream = new ContinuousAudioDataStream(audioData);
                }
                else {
                    audioDataStream = new AudioDataStream(audioData);
                }
                AudioPlayer.player.start(audioDataStream);
                d = audioDataStream;
            }
            if (this.P) {
                this.D = d;
            }
            this.E[n3 + 6] = d;
            if (n4 != 0) {
                this.E[n3 + 3] = d;
            }
        }
    }
    
    Image BE(final Vector vector, final int n) {
        return (n < 0 || n >= vector.size()) ? null : vector.elementAt(n);
    }
    
    void BW(final String s, final Graphics graphics, final int n, final int n2, final int[] array, final int n3, final int n4, final FontMetrics fontMetrics, final int n5, final int n6, final int n7, final int n8) {
        final int stringWidth = fontMetrics.stringWidth(s);
        int n9 = 0;
        switch (array[n3 + 26 + n4]) {
            case 0: {
                n9 = n6 + n7;
                break;
            }
            default: {
                n9 = (n5 - stringWidth) / 2 + n6;
                break;
            }
            case 2: {
                n9 = n5 - stringWidth - n7 + n6;
                break;
            }
            case 3: {
                n9 = array[n3 + 29 + n4];
                break;
            }
        }
        this.BX(graphics, s, n9 + n2, n + n2, stringWidth, n8, array[n3 + 20 + n4]);
    }
    
    public void start() {
        this.BR();
        this.CD(0);
    }
    
    public void BR() {
        this.BQ(new Rectangle(0, 0, this.X, this.Y));
    }
    
    public void BZ(final Image image, final Vector vector, final Rectangle rectangle) {
        final int x = this.X;
        final int y = this.Y;
        final Graphics graphics = this.U.getGraphics();
        graphics.setColor(this.AF);
        if (rectangle == null) {
            graphics.fillRect(0, 0, x, y);
        }
        else {
            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        final Image be = this.BE(this.AN, this.AH);
        if (be != null) {
            final int width = be.getWidth(this);
            final int height = be.getHeight(this);
            if (width != -1 && height != -1) {
                if (vector != null && image != null && be == image) {
                    vector.addElement(new Rectangle(0, 0, x, y));
                }
                else {
                    switch (this.AG) {
                        case 0: {
                            graphics.drawImage(be, (x - width) / 2, (y - height) / 2, this);
                            break;
                        }
                        case 1: {
                            graphics.drawImage(be, 0, 0, x, y, this);
                            break;
                        }
                        case 2: {
                            for (int i = this.AI.y; i < y; i += height) {
                                for (int j = this.AI.x; j < x; j += width) {
                                    graphics.drawImage(be, j, i, width, height, this);
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (this.I >= 0) {
            final int[] c = this.C;
            for (int k = this.AC - 1; k >= 0; --k) {
                final int n = k * 51;
                if (c[n + 4] != 0 && (rectangle == null || rectangle.intersects(this.BP(k)))) {
                    final int n2 = c[n + 51 - 1];
                    final int n3 = c[n + 38 + n2];
                    final int n4 = c[n + 2];
                    final int n5 = c[n + 3];
                    final Graphics graphics2 = this.U.getGraphics();
                    graphics2.translate(c[n], c[n + 1]);
                    graphics2.clipRect(-n3, -n3, n4 + n3 * 2, n5 + n3 * 2);
                    int n6 = 0;
                    do {
                        this.BV(graphics2, k, n2, n6, image, vector);
                    } while (++n6 < 3);
                    if (n3 > 0) {
                        graphics2.setColor(this.F[k * 15 + 6 + n2]);
                        for (int l = 1; l <= n3; ++l) {
                            graphics2.drawRect(-l, -l, n4 + l * 2 - 1, n5 + l * 2 - 1);
                        }
                    }
                    graphics2.dispose();
                }
            }
        }
        if (!this.J) {
            final String s = "UNREGISTERED";
            final int stringWidth = this.getFontMetrics(graphics.getFont()).stringWidth(s);
            graphics.setColor(Color.black);
            graphics.fillRect((x - stringWidth) / 2 - 10, y / 2 - 15, stringWidth + 20, 30);
            graphics.setColor(Color.white);
            graphics.drawRect((x - stringWidth) / 2 - 11, y / 2 - 16, stringWidth + 21, 31);
            graphics.drawString(s, (x - stringWidth) / 2, y / 2 + 4);
        }
        graphics.dispose();
    }
    
    int CC(final int n, final int n2) {
        final int[] c = this.C;
        for (int i = 0; i < this.AC; ++i) {
            final int n3 = i * 51;
            if (c[n3 + 4] != 0 && (this.AB == null || (i != this.AE && (c[n3 + 6] & 0x1) == 0x0))) {
                final Rectangle bp = this.BP(i);
                final int n4 = n - bp.x;
                final int n5 = n2 - bp.y;
                if (n4 >= 0 && n4 < bp.width && n5 >= 0 && n5 < bp.height) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    Rectangle BK(final Rectangle rectangle, final Rectangle rectangle2) {
        if (rectangle == null) {
            return rectangle2;
        }
        return rectangle.union(rectangle2);
    }
    
    void AY(final String[] array) {
        final String ax = this.AX();
        if (ax.startsWith("^")) {
            final String az = this.AZ(ax.substring(1, ax.length()));
            int n = 0;
            do {
                array[n] = az;
            } while (++n < 3);
            return;
        }
        array[0] = this.AZ(ax);
        int n2 = 1;
        do {
            array[n2] = this.BA();
        } while (++n2 < 3);
    }
    
    public void CD(final int cursor) {
        if (this.T != null) {
            this.T.setCursor(cursor);
        }
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouseMove(event, Integer.MIN_VALUE, Integer.MIN_VALUE);
        return true;
    }
    
    int BC() {
        return this.BB(this.AX());
    }
    
    void BM(final StringTokenizer stringTokenizer, final int[] array) {
        String s = stringTokenizer.nextToken().trim();
        if (s.endsWith("+")) {
            array[0] = 1;
        }
        else if (s.endsWith("-")) {
            array[0] = -1;
        }
        if (array[0] != 0) {
            s = s.substring(0, s.length() - 1);
        }
        array[1] = this.BB(s);
    }
    
    public void paint(final Graphics graphics) {
        this.BY(graphics);
        this.L |= 0x1;
    }
    
    public String getJS() {
        if (this.AR.size() <= 0) {
            return null;
        }
        final String s = this.AR.elementAt(0);
        this.AR.removeElementAt(0);
        return s;
    }
    
    public BtnPlayer() {
        this.D = null;
        this.H = new String[] { "Helvetica", "TimesRoman", "Courier", "Dialog" };
        this.I = -1;
        this.J = true;
        this.K = true;
        this.L = 0;
        this.Z = -1;
        this.AA = -1;
        this.AB = null;
        this.AD = -1;
        this.AE = -1;
        this.AM = new Vector();
        this.AN = new Vector();
        this.AO = new Vector();
        this.AP = new Vector();
        this.AQ = new Vector();
        this.AR = new Vector();
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.I >= 0) {
            this.mouseDrag(event, n, n2);
            boolean b = false;
            final int cc = this.CC(n, n2);
            if (this.AE >= 0) {
                final int[] c = this.C;
                final int n3 = this.AE * 51;
                final int n4 = c[n3 + 4];
                c[n3 + 4] = 1;
                final int cc2 = this.CC(n, n2);
                c[n3 + 4] = n4;
                Rectangle rectangle = this.BN(this.AE, null, 3);
                if (cc == this.AE || this.AB != null) {
                    if (this.AB != null && this.AD >= 0) {
                        c[n3 + 4] = 0;
                        rectangle = this.BP(this.AD);
                        c[this.AD * 51 + 51 - 1] = 1;
                        this.BH(this.AD);
                        this.BJ(this.AE, 0);
                        b = true;
                    }
                    else {
                        switch (c[n3 + 7]) {
                            default: {
                                c[n3 + 51 - 1] = 1;
                                this.AD = this.AE;
                                this.BH(this.AE);
                                if (this.AB == null) {
                                    this.BJ(this.AE, 0);
                                    break;
                                }
                                break;
                            }
                            case 0: {
                                c[n3 + 5] = 1 - c[n3 + 5];
                                c[n3 + 51 - 1] = ((c[n3 + 5] == 1) ? 2 : 1);
                                rectangle = this.BN(this.AE, rectangle, 4 + ((c[n3 + 5] != 1) ? 1 : 0));
                                this.BH(this.AE);
                                if (c[n3 + 5] == 0) {
                                    this.BJ(cc, 0);
                                    this.AD = this.AE;
                                    break;
                                }
                                break;
                            }
                            case 1: {
                                if (this.Z != this.AE) {
                                    if (this.Z >= 0) {
                                        final int n5 = this.Z * 51;
                                        c[n5 + 51 - 1] = 0;
                                        this.BH(this.Z);
                                        c[n5 + 5] = 0;
                                        rectangle = this.BN(this.Z, this.BN(this.Z, rectangle, 5), 0);
                                    }
                                    this.Z = this.AE;
                                    c[n3 + 5] = 1;
                                    rectangle = this.BN(this.Z, rectangle, 4);
                                    break;
                                }
                                break;
                            }
                        }
                    }
                    final Rectangle bn = this.BN(this.AE, rectangle, c[n3 + 51 - 1]);
                    if (bn != null) {
                        this.BQ(bn);
                    }
                }
                if (b || cc2 == this.AE) {
                    final int n6 = this.AE * 19;
                    final String s = this.G[n6 + 9];
                    final String s2 = this.G[n6 + 10];
                    if (s != null) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
                        final StringTokenizer stringTokenizer2 = (s2 != null) ? new StringTokenizer(s2, ",") : null;
                        while (stringTokenizer.hasMoreTokens()) {
                            String s3 = stringTokenizer.nextToken().trim();
                            if (s3.toLowerCase().startsWith("javascript:")) {
                                this.CB(s3.substring(s3.indexOf(58) + 1).trim());
                            }
                            else {
                                final int index;
                                if (this.M && !this.N && (index = s3.indexOf(35)) >= 0) {
                                    s3 = s3.substring(0, index + 1) + "#" + s3.substring(index + 1);
                                }
                                URL url = null;
                                try {
                                    url = new URL(this.getDocumentBase(), s3);
                                }
                                catch (Exception ex) {}
                                if (url == null) {
                                    continue;
                                }
                                final AppletContext appletContext = this.getAppletContext();
                                if (stringTokenizer2 != null && stringTokenizer2.hasMoreTokens()) {
                                    appletContext.showDocument(url, stringTokenizer2.nextToken().trim());
                                }
                                else {
                                    if (s3.indexOf(".htm") != -1) {
                                        this.CD(3);
                                    }
                                    appletContext.showDocument(url);
                                }
                            }
                        }
                    }
                }
            }
            this.AB = null;
            this.AE = -1;
        }
        return true;
    }
    
    void BL(final long n) {
        if (n > 0L) {
            try {
                Thread.sleep(n);
            }
            catch (Exception ex) {}
        }
    }
    
    public void CB(String string) {
        if (!string.endsWith(";")) {
            string += ";";
        }
        if ((this.M && !this.N) || !this.CA(string)) {
            this.AR.addElement(string);
        }
    }
    
    void BY(final Graphics graphics) {
        this.BZ(null, null, graphics.getClipRect());
        graphics.drawImage(this.U, 0, 0, this);
        graphics.dispose();
    }
    
    public void setVisible(final String s, final int n) {
        this.setAttrib(s, 4, n, null, true);
    }
    
    Object BI(final Object o) {
        if (o != null) {
            if (o instanceof AudioClip) {
                ((AudioClip)o).stop();
            }
            else if (o instanceof InputStream) {
                AudioPlayer.player.stop((InputStream)o);
            }
        }
        return null;
    }
    
    public int getVisible(final String s) {
        return this.getAttrib(s, 4);
    }
    
    Object BG(final String s) {
        if (s == null) {
            return null;
        }
        for (int i = this.AP.size() - 1; i >= 0; --i) {
            if (((String)this.AP.elementAt(i)).equals(s)) {
                return this.AQ.elementAt(i);
            }
        }
        Object o = null;
        InputStream resourceAsStream = null;
        try {
            if (this.O) {
                resourceAsStream = this.getClass().getResourceAsStream(s);
            }
            if (resourceAsStream != null) {
                o = new AudioStream(resourceAsStream).getData();
            }
            else {
                String string = s;
                if (this.R != null && !this.K) {
                    string = this.R + "/" + s;
                }
                final AudioClip audioClip = (AudioClip)(o = this.getAudioClip(this.getDocumentBase(), string));
                if (this.O) {
                    audioClip.play();
                    audioClip.stop();
                }
            }
        }
        catch (Exception ex) {}
        if (o != null) {
            this.AP.addElement(s);
            this.AQ.addElement(o);
        }
        return o;
    }
    
    String AX() {
        String trim = "";
        if (this.AK < this.AJ.length()) {
            int n = this.AJ.indexOf(44, this.AK);
            if (n < 0) {
                n = this.AJ.length();
            }
            trim = this.AJ.substring(this.AK, n).trim();
            this.AK = n + 1;
        }
        return trim;
    }
    
    Color BD(final String s) {
        int int1 = 0;
        try {
            int1 = Integer.parseInt(s.trim(), 16);
        }
        catch (Exception ex) {}
        return new Color(int1);
    }
    
    void BU(final boolean b, final Graphics graphics, final int[] array, final int n, final int n2, final int n3, final int n4, final Color color) {
        if (b) {
            graphics.setColor(color);
            graphics.drawLine(n2, n3, n2, n4);
            return;
        }
        final int n5 = color.getRGB() | 0xFF000000;
        int i = n3 * n + n2;
        do {
            array[i] = n5;
            i += n;
        } while (i != (n4 + 1) * n + n2);
    }
    
    int BF(final String s, final int n, final int n2, final boolean b, final boolean b2, final MediaTracker mediaTracker) {
        if (s == null) {
            return -1;
        }
        for (int i = this.AM.size() - 1; i >= 0; --i) {
            if (((String)this.AM.elementAt(i)).equals(s)) {
                return i;
            }
        }
        int size = -1;
        try {
            Image image = null;
            if (this.O) {
                try {
                    final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
                    if (resourceAsStream != null) {
                        final byte[] array = new byte[resourceAsStream.available()];
                        resourceAsStream.read(array);
                        image = this.getToolkit().createImage(array);
                    }
                }
                catch (Exception ex) {}
            }
            if (image == null) {
                String string = s;
                if (this.Q != null && !this.K) {
                    string = this.Q + "/" + s;
                }
                image = this.getImage(this.getDocumentBase(), string);
            }
            image.getWidth(this);
            final Graphics graphics = this.V.getGraphics();
            graphics.drawImage(image, 0, 0, this);
            graphics.dispose();
            size = this.AN.size();
            mediaTracker.addImage(image, size);
            this.AM.addElement(s);
            this.AN.addElement(image);
            this.AO.addElement(null);
        }
        catch (Exception ex2) {}
        return size;
    }
    
    void BX(final Graphics graphics, final String s, final int n, final int n2, final int n3, final int n4, final int n5) {
        graphics.drawString(s, n, n2);
        if ((n4 & 0x4) != 0x0) {
            final int n6 = n2 + n5 / 6;
            graphics.drawLine(n, n6, n + n3, n6);
        }
    }
    
    public void BQ(final Rectangle rectangle) {
        final Graphics graphics = this.getGraphics();
        if (graphics != null && rectangle != null) {
            graphics.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            this.BY(graphics);
        }
    }
    
    int AV(final String s, int n) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            final char c = charArray[i];
            n ^= (c << 24 | c << 16 | c << 8 | c);
            final char c2 = (char)(c & '\u001f');
            n = (int)((n & -1L) << c2 | n >>> ' ' - c2);
        }
        return n;
    }
    
    int BB(String substring) {
        if (substring.startsWith("+")) {
            substring = substring.substring(1);
        }
        int int1 = 0;
        try {
            int1 = Integer.parseInt(substring);
        }
        catch (Exception ex) {}
        return int1;
    }
    
    public boolean CA(final String s) {
        boolean b = false;
        try {
            JSObject.getWindow((Applet)this).eval(s);
            b = true;
        }
        catch (Exception ex) {}
        return b;
    }
    
    public void run() {
        final int[] c = this.C;
        final MediaTracker mediaTracker = new MediaTracker(this);
        this.AH = ((this.AG >= 0) ? this.BF(this.AL, this.X, this.Y, true, this.AG == 3, mediaTracker) : -1);
        for (int i = 0; i < this.AC; ++i) {
            final int n = i * 51;
            int n2 = 0;
            do {
                final int n3 = c[n + 8 + n2];
                final int n4 = c[n + 11 + n2];
                final int n5 = (n3 > 1) ? n4 : false;
                this.A[i * 3 + n2] = this.BF(this.G[i * 19 + 13 + n2], c[n + 2] - 2 * n5, c[n + 3] - 2 * n5, n2 != 0 || c[n + 4] == 0, (c[n + 35 + n2] & 0x3) == 0x3, mediaTracker);
            } while (++n2 < 3);
        }
        this.I = 1;
        this.BR();
        for (int j = 0; j < this.AC; ++j) {
            int n6 = 0;
            do {
                this.BV(null, j, n6, 0, null, null);
                this.BV(null, j, n6, 2, null, null);
            } while (++n6 < 3);
        }
        this.performAction(this.S);
        for (int k = 0; k < this.AC; ++k) {
            int n7 = 0;
            do {
                this.E[k * 9 + n7] = this.BG(this.G[k * 19 + n7]);
            } while (++n7 < 3);
        }
        this.BR();
        try {
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {}
        this.BR();
        while (this.N) {
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex2) {}
            if (this.L == 1) {
                this.BR();
                this.L = 0;
            }
        }
    }
    
    public void init() {
        this.K = this.getCodeBase().getProtocol().equals("file");
        final String property = System.getProperty("java.version");
        final boolean equals = property.equals("1.0.2");
        final boolean b = !property.startsWith("1.0");
        final boolean startsWith = System.getProperty("os.version").startsWith("3.1");
        final boolean b2 = System.getProperty("os.name").toLowerCase().indexOf("windows") != -1 && !startsWith;
        final String lowerCase = System.getProperty("java.vendor").toLowerCase();
        this.M = (lowerCase.indexOf("microsoft") != -1);
        final boolean b3 = lowerCase.indexOf("netscape") != -1;
        this.N = (this.M && b);
        this.O = (b3 && b && b2);
        this.X = this.size().width;
        this.Y = this.size().height;
        this.U = this.createImage(this.X, this.Y);
        this.V = this.createImage(this.X, this.Y);
        for (Container container = this.getParent(); container != null; container = container.getParent()) {
            if (container instanceof Frame) {
                this.T = (Frame)container;
            }
        }
        final String parameter = this.getParameter("p");
        this.AJ = parameter;
        this.AK = 0;
        this.AC = this.BC();
        this.BC();
        this.BC();
        this.setBackground(this.AF = this.BD(this.AX()));
        this.show();
        this.BR();
        this.AG = this.BC();
        this.AL = this.BA();
        this.AI = new Point(this.BC(), this.BC());
        this.P = (this.BC() == 1);
        this.Q = this.BA();
        this.R = this.BA();
        this.S = this.BA();
        int n;
        int av = n = this.AV(parameter, 1114221141);
        this.C = new int[this.AC * 51];
        this.A = new int[this.AC * 3];
        this.B = new Image[this.AC * 6];
        this.E = new Object[this.AC * 9];
        this.G = new String[this.AC * 19];
        this.F = new Color[this.AC * 15];
        final int[] c = this.C;
        final String[] array = new String[3];
        for (int i = 0; i < this.AC; ++i) {
            final String parameter2 = this.getParameter("" + i);
            this.AJ = parameter2;
            this.AK = 0;
            final int n2 = i * 51;
            int n3 = 0;
            do {
                c[n2 + n3] = this.BC();
            } while (++n3 < 8);
            int n4 = 0;
            do {
                this.AY(array);
                int j = 0;
                do {
                    c[n2 + n3] = this.BB(array[j]);
                    ++j;
                    ++n3;
                } while (j < 3);
            } while (++n4 < 13);
            c[n2 + 51 - 1] = ((c[n2 + 5] == 1) ? 2 : 0);
            if (this.Z < 0 && c[n2 + 7] == 1 && c[n2 + 5] != 0) {
                this.Z = i;
            }
            final String substring = this.AJ.substring(0, this.AK);
            this.AY(array);
            int n5 = 0;
            do {
                this.G[i * 19 + 13 + n5] = array[n5];
                this.A[i * 3 + n5] = -1;
            } while (++n5 < 3);
            int n6 = i * 19;
            int n7 = 0;
            do {
                this.AY(array);
                int k = 0;
                do {
                    this.G[n6] = array[k];
                    ++k;
                    ++n6;
                } while (k < 3);
            } while (++n7 < 3);
            int l = 0;
            do {
                this.G[n6] = this.BA();
                ++l;
                ++n6;
            } while (l < 4);
            final int ak = this.AK;
            int n8 = i * 15;
            int n9 = 0;
            do {
                this.AY(array);
                int n10 = 0;
                do {
                    this.F[n8] = this.BD(array[n10]);
                    ++n10;
                    ++n8;
                } while (n10 < 3);
            } while (++n9 < 3);
            final String string = substring + this.AJ.substring(ak, this.AK - 1);
            int n11 = i * 19 + 16;
            this.AY(array);
            int n12 = 0;
            do {
                this.G[n11] = array[n12];
                ++n12;
                ++n11;
            } while (n12 < 3);
            av = this.AV(parameter2, av);
            n = this.AV(string, n);
        }
        final int n13 = av & Integer.MAX_VALUE;
        final int n14 = n & Integer.MAX_VALUE;
        final int aw = this.AW("r");
        final int aw2 = this.AW("c");
        if (n13 != aw && n14 != aw2 && !this.K) {
            this.J = false;
        }
        this.I = 0;
        if ((b2 && equals) || b) {
            new Thread(this).start();
            return;
        }
        this.run();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.I >= 0) {
            this.mouseMove(event, n, n2);
            Rectangle bn = null;
            final int cc = this.CC(n, n2);
            final int[] c = this.C;
            if (cc != -1 && (this.AE == -1 || this.AE == cc)) {
                final int n3 = cc * 51;
                if (c[n3 + 51 - 1] != 2) {
                    c[n3 + 51 - 1] = 2;
                    this.BH(cc);
                    this.BJ(cc, 2);
                    this.AB = (((c[n3 + 6] & 0x1) != 0x0) ? new Point(n - c[n3], n2 - c[n3 + 1]) : null);
                    bn = this.BN(cc, bn, 2);
                }
                this.AE = cc;
            }
            if (bn != null) {
                this.BQ(bn);
            }
        }
        this.AD = this.CC(n, n2);
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.I >= 0 && this.AE != -1) {
            Rectangle bn = null;
            int n3 = this.AE * 51;
            final int[] c = this.C;
            if (this.AB != null) {
                final int n4 = c[n3 + 38 + c[n3 + 51 - 1]];
                final Rectangle rectangle = new Rectangle(c[n3] - n4, c[n3 + 1] - n4, c[n3 + 2] + n4 + n4, c[n3 + 3] + n4 + n4);
                c[n3] = n - this.AB.x;
                c[n3 + 1] = n2 - this.AB.y;
                Rectangle rectangle2 = new Rectangle(c[n3] - n4, c[n3 + 1] - n4, c[n3 + 2] + n4 + n4, c[n3 + 3] + n4 + n4).union(rectangle);
                final int cc = this.CC(n, n2);
                if (this.AD >= 0 && this.AD != cc && cc != this.AE) {
                    n3 = this.AD * 51;
                    if (c[n3 + 51 - 1] == 2 && c[n3 + 5] == 0) {
                        c[n3 + 51 - 1] = 0;
                        this.BH(this.AD);
                        rectangle2 = rectangle2.union(this.BP(this.AD));
                    }
                }
                if (cc != this.AD && cc != -1) {
                    n3 = cc * 51;
                    if (c[n3 + 51 - 1] != 2) {
                        c[n3 + 51 - 1] = 2;
                        this.BH(cc);
                        rectangle2 = rectangle2.union(this.BP(cc));
                        this.BJ(cc, 2);
                    }
                }
                this.AD = cc;
                this.BQ(rectangle2);
            }
            else {
                final int cc2 = this.CC(n, n2);
                this.mouseDown(event, n, n2);
                if (cc2 != this.AE && c[n3 + 5] == 0 && c[n3 + 51 - 1] == 2 && this.AE != this.Z) {
                    c[n3 + 51 - 1] = 0;
                    this.BH(this.AE);
                    bn = this.BN(this.AE, bn, 0);
                }
            }
            if (bn != null) {
                this.BQ(bn);
            }
            if ((this.N || this.O) && (c[n3 + 6] & 0x2) == 0x0) {
                this.CD((this.AD < 0) ? 0 : 12);
            }
        }
        return true;
    }
    
    void BH(final int n) {
        final int n2 = n * 9 + 3;
        int n3 = 0;
        do {
            this.E[n2 + n3] = this.BI(this.E[n2 + n3]);
        } while (++n3 < 3);
        final int[] c = this.C;
        final int n4 = n * 51;
        if ((c[n4 + 6] & 0x4) != 0x0) {
            final int n5 = this.A[n * 3 + c[n4 + 51 - 1]];
            final Image be = this.BE(this.AN, n5);
            final Image be2 = this.BE(this.AO, n5);
            if (be != null && be2 == be && (this.N || (this.checkImage(be, this) & 0x20) != 0x0)) {
                this.W = true;
                be.flush();
                be.getWidth(this);
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(be, 0);
                try {
                    mediaTracker.waitForAll();
                }
                catch (Exception ex) {}
                this.checkImage(be, this);
                this.W = false;
            }
        }
    }
    
    void BT(final boolean b, final Graphics graphics, final int[] array, final int n, final int n2, final int n3, final int n4, final Color color) {
        if (b) {
            graphics.setColor(color);
            graphics.drawLine(n3, n2, n4, n2);
            return;
        }
        final int n5 = color.getRGB() | 0xFF000000;
        final int n6 = n2 * n;
        int i = n6 + n3;
        do {
            array[i++] = n5;
        } while (i != n6 + n4 + 1);
    }
    
    Rectangle performAction(final String s, Rectangle rectangle) {
        if (s != null) {
            final int[] c = this.C;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
            for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; ++i) {
                final String trim = stringTokenizer.nextToken().trim();
                int index = trim.indexOf(32);
                if (index >= 0) {
                    String[] array;
                    String substring;
                    int n;
                    for (array = new String[] { "show", "hide", "up", "over", "down", "wait", "call", "move", "size", "xform" }, substring = trim.substring(0, index), n = array.length - 1; n >= 0 && !substring.equalsIgnoreCase(array[n]); --n) {}
                    if (n >= 0) {
                        while (trim.charAt(index) == ' ' && index < trim.length()) {
                            ++index;
                        }
                        if (index < trim.length()) {
                            final String substring2 = trim.substring(index);
                            switch (n) {
                                case 5: {
                                    this.BQ(rectangle);
                                    this.BL(this.BB(substring2));
                                    break;
                                }
                                case 6: {
                                    this.CB(substring2);
                                    break;
                                }
                                case 7:
                                case 8:
                                case 9: {
                                    final int index2 = substring2.indexOf(":");
                                    if (index2 >= 0) {
                                        final String trim2 = substring2.substring(0, index2).trim();
                                        final StringTokenizer stringTokenizer2 = new StringTokenizer(substring2.substring(index2 + 1).trim(), ", ");
                                        final int[] array2 = new int[2];
                                        final int[] array3 = new int[2];
                                        final int[] array4 = new int[2];
                                        final int[] array5 = new int[2];
                                        if (n == 7 || n == 9) {
                                            this.BM(stringTokenizer2, array2);
                                            this.BM(stringTokenizer2, array3);
                                        }
                                        if (n == 8 || n == 9) {
                                            this.BM(stringTokenizer2, array4);
                                            this.BM(stringTokenizer2, array5);
                                        }
                                        int bb = 0;
                                        if (stringTokenizer2.hasMoreTokens()) {
                                            bb = this.BB(stringTokenizer2.nextToken().trim());
                                        }
                                        int bb2 = 0;
                                        if (stringTokenizer2.hasMoreTokens()) {
                                            bb2 = this.BB(stringTokenizer2.nextToken().trim());
                                        }
                                        for (int j = this.AC - 1; j >= 0; --j) {
                                            if (this.G[j * 19 + 11].equals(trim2)) {
                                                rectangle = this.BK(rectangle, this.BP(j));
                                                final int n2 = j * 51;
                                                final Rectangle rectangle2 = new Rectangle(c[n2], c[n2 + 1], c[n2 + 2], c[n2 + 3]);
                                                final Rectangle union = rectangle2.union(rectangle2);
                                                if (n == 7 || n == 9) {
                                                    union.x = ((array2[0] == 0) ? array2[1] : (rectangle2.x + array2[0] * array2[1]));
                                                    union.y = ((array3[0] == 0) ? array3[1] : (rectangle2.y + array3[0] * array3[1]));
                                                }
                                                if (n == 8 || n == 9) {
                                                    union.width = ((array4[0] == 0) ? array4[1] : (rectangle2.width + array4[0] * array4[1]));
                                                    union.height = ((array5[0] == 0) ? array5[1] : (rectangle2.height + array5[0] * array5[1]));
                                                    this.CE(this.AA = j);
                                                }
                                                if (bb == 0) {
                                                    this.BO(j, union);
                                                    rectangle = this.BK(rectangle, this.BP(j));
                                                }
                                                else {
                                                    this.L |= 0x2;
                                                    this.BQ(rectangle);
                                                    for (int k = 1; k <= bb; ++k) {
                                                        final long currentTimeMillis = System.currentTimeMillis();
                                                        final int n3 = bb - k;
                                                        final Rectangle bp = this.BP(j);
                                                        this.BO(j, new Rectangle((n3 * rectangle2.x + k * union.x) / bb, (n3 * rectangle2.y + k * union.y) / bb, (n3 * rectangle2.width + k * union.width) / bb, (n3 * rectangle2.height + k * union.height) / bb));
                                                        this.BQ(bp.union(this.BP(j)));
                                                        if (k < bb) {
                                                            this.BL(bb2 - (System.currentTimeMillis() - currentTimeMillis));
                                                        }
                                                    }
                                                    this.L &= 0xFFFFFFFD;
                                                }
                                                this.AA = -1;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                    break;
                                }
                                default: {
                                    if (n < 2) {
                                        rectangle = this.setAttrib(substring2, 4, 1 - n, rectangle, false);
                                        break;
                                    }
                                    rectangle = this.setAttrib(substring2, 50, n - 2, rectangle, false);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return rectangle;
    }
}
