import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Polygon;
import java.awt.Dimension;
import java.awt.Event;
import java.applet.AudioClip;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Point;
import java.awt.Color;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class uasbase
{
    private int $279;
    private int $267;
    private int $303;
    private int $227;
    private int $232;
    private Image $216;
    private int $231;
    private int $230;
    private Color[] $117;
    private Color[] $130;
    private Image $219;
    private int[] $106;
    public static final int MINTIMEUNIT = 20;
    protected int m_nBkType;
    protected Color m_clrBk;
    protected int m_nBkImg;
    protected int m_nBkMode;
    protected Point m_ptBkImg;
    protected int m_nBkAnim;
    protected int m_nBkDirection;
    protected int m_nBkSpeed;
    protected int m_nBdStyle;
    protected int m_nBdType;
    protected int m_nBdWidth;
    protected Color m_clrBd;
    protected int m_nClrChg;
    public int AppletEndTime;
    
    private boolean $353() {
        try {
            Integer.valueOf("");
            return true;
        }
        catch (NumberFormatException ex) {}
        catch (Exception ex2) {}
        return false;
    }
    
    public boolean InitSprite(final Component component, final Rectangle rectangle, final int n, final int n2, final MediaTracker mediaTracker, final Image[] array, final boolean[] array2, final AudioClip[] array3, final boolean b, final boolean b2, final String s) {
        return false;
    }
    
    public void stopAudio() {
    }
    
    public void DeleteSprite() {
    }
    
    public boolean Hittest(final int n, final int n2) {
        return true;
    }
    
    public Object Process(final Component component, final Object o, final Rectangle rectangle, final Event event, final int n) {
        return null;
    }
    
    public int mouseEvent(final Event event, final String[] array) {
        return 0;
    }
    
    public void SetExtra(final Object[] array) {
    }
    
    public static Object ProcessImg(final String s, final Component component, final Object o, final Rectangle rectangle, final Object o2, final Dimension dimension, final int n) {
        int min = 0;
        final int n2 = dimension.width >>> 1;
        final int n3 = dimension.height >>> 1;
        final Object[] array = { new Integer(0), new String("ua2color"), new Integer(1), new Color(0), new Color(16777215), new Polygon() };
        parser(s, array, "|");
        final String s2 = (String)array[1];
        if (dimension.width <= 0 || dimension.height <= 0) {
            return null;
        }
        int[] array2;
        if (n == 17 || n == 15) {
            array2 = new int[dimension.width * dimension.height];
        }
        else {
            if ((n & 0x3) != 0x3) {
                return null;
            }
            array2 = (int[])o2;
        }
        if (s2 == null) {
            return null;
        }
        final Object[] array3 = new Object[3];
        for (int i = 0; i < 3; ++i) {
            array3[i] = array[i + 2];
        }
        final int intValue = (int)array[0];
        switch (intValue) {
            case 1: {
                final int[] array4 = (int[])ProcessImg2(component, null, null, null, new Dimension(dimension.height, 0), 17, array3);
                for (int j = 0; j < dimension.height; ++j) {
                    for (int k = 0; k < dimension.width; ++k) {
                        array2[j * dimension.width + k] = array4[j];
                    }
                }
                break;
            }
            case 2: {
                final int[] array5 = (int[])ProcessImg2(component, null, null, null, new Dimension(dimension.width, 0), 17, array3);
                for (int l = 0; l < dimension.height; ++l) {
                    System.arraycopy(array5, 0, array2, l * dimension.width, dimension.width);
                }
                break;
            }
            case 3:
            case 4: {
                final int[] array6 = (int[])ProcessImg2(component, null, null, null, new Dimension(dimension.width * 2, 0), 17, array3);
                for (int n4 = 0; n4 < dimension.height; ++n4) {
                    final int n5 = (int)(n4 * dimension.width / dimension.height + 0.5);
                    System.arraycopy(array6, (intValue == 3) ? n5 : (dimension.width - n5), array2, n4 * dimension.width, dimension.width);
                }
                break;
            }
            case 5:
            case 7:
            case 8: {
                int n6;
                if (intValue == 5) {
                    n6 = (min = Math.min(n3, n2));
                }
                else if (intValue == 7) {
                    n6 = n2 << 1;
                }
                else {
                    n6 = n2;
                }
                final int[] array7 = (int[])ProcessImg2(component, null, null, null, new Dimension(n6, 0), 17, array3);
                final int n7 = dimension.width & 0x1;
                int n8 = n6 - 1;
                int n9 = n2;
                int n11;
                for (int n10 = n11 = 0, n12 = dimension.width - 1; n11 < n3; ++n11, n10 += dimension.width, n12 += dimension.width) {
                    if (intValue == 5) {
                        min = ((min <= 0) ? 0 : (min - 1));
                        n9 = Math.min(n11, n2);
                    }
                    else if (intValue == 7) {
                        n8 = n6 - 1 - n2 * n11 / (n3 - 1);
                        min = n8 - n2 + 1;
                    }
                    else {
                        min = (n6 - 1) * (n3 - n11) / n3;
                        n9 = Math.min(n2 * n11 / (n3 - 1), n2);
                    }
                    int n13;
                    for (n13 = 0; n13 < n9; ++n13) {
                        array2[n12 - n13] = (array2[n10 + n13] = array7[n8 - n13]);
                    }
                    while (n13 < n2) {
                        array2[n12 - n13] = (array2[n10 + n13] = array7[min]);
                        ++n13;
                    }
                    if (n7 != 0) {
                        array2[n10 + n13] = array7[min];
                    }
                    System.arraycopy(array2, n11 * dimension.width, array2, (dimension.height - n11 - 1) * dimension.width, dimension.width);
                }
                if ((dimension.height & 0x1) != 0x0) {
                    System.arraycopy(array2, (n11 - 1) * dimension.width, array2, n11 * dimension.width, dimension.width);
                    break;
                }
                break;
            }
            case 6: {
                final int max = Math.max(n2, n3);
                final int[] array8 = (int[])ProcessImg2(component, null, null, null, new Dimension(max, 0), 17, array3);
                final Image image = component.createImage(dimension.width, dimension.height);
                final Graphics graphics = image.getGraphics();
                graphics.setColor(new Color(array8[max - 1]));
                graphics.fillRect(0, 0, dimension.width, dimension.height);
                for (int n14 = 1; n14 < max; ++n14) {
                    final int n15 = (n2 - 1) * n14 / (max - 1);
                    final int n16 = (n3 - 1) * n14 / (max - 1);
                    graphics.setColor(new Color(array8[max - n14 - 1]));
                    graphics.fillOval(n15, n16, dimension.width - (n15 << 1), dimension.height - (n16 << 1));
                }
                final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, dimension.width, dimension.height, array2, 0, dimension.width);
                try {
                    pixelGrabber.grabPixels();
                }
                catch (InterruptedException ex) {}
                break;
            }
        }
        if (n == 15) {
            final int[] array9 = (int[])o2;
            final int[] array10 = (int[])o;
            final int min2 = Math.min(dimension.height, rectangle.height - rectangle.y);
            final int min3 = Math.min(dimension.width, rectangle.width - rectangle.x);
            for (int n17 = 0; n17 < min2; ++n17) {
                for (int n18 = n17 * dimension.width, n19 = (n17 + rectangle.y) * rectangle.width + rectangle.x, n20 = 0; n20 < min3; ++n20, ++n18, ++n19) {
                    if ((array9[n18] & 0xFF000000) != 0x0) {
                        array10[n19] = array2[n18];
                    }
                }
            }
        }
        if ((n & 0x10) != 0x0) {
            return component.createImage(new MemoryImageSource(dimension.width, dimension.height, array2, 0, dimension.width));
        }
        return array2;
    }
    
    public static Object ProcessImg2(final Component component, final Object o, final Rectangle rectangle, final Object o2, final Dimension dimension, final int n, final Object[] array) {
        float[] rgBtoHSB = null;
        float[] rgBtoHSB2 = null;
        int[] array2;
        if ((n & 0x2) != 0x0) {
            array2 = (int[])o2;
        }
        else {
            if ((n & 0x10) == 0x0) {
                return null;
            }
            array2 = new int[dimension.width];
        }
        int intValue = (int)array[0];
        final int rgb = ((Color)array[1]).getRGB();
        final int rgb2 = ((Color)array[2]).getRGB();
        if (intValue < 1 || intValue > 3) {
            intValue = 1;
        }
        final int n2 = rgb >>> 16 & 0xFF;
        final int n3 = rgb >>> 8 & 0xFF;
        final int n4 = rgb & 0xFF;
        final int n5 = rgb2 >>> 16 & 0xFF;
        final int n6 = rgb2 >>> 8 & 0xFF;
        final int n7 = rgb2 & 0xFF;
        if (intValue != 1) {
            rgBtoHSB = Color.RGBtoHSB(n2, n3, n4, null);
            rgBtoHSB2 = Color.RGBtoHSB(n5, n6, n7, null);
            if (intValue == 2 && rgBtoHSB[0] <= rgBtoHSB2[0]) {
                final float[] array3 = rgBtoHSB;
                final int n8 = 0;
                ++array3[n8];
            }
            else if (intValue == 3 && rgBtoHSB[0] >= rgBtoHSB2[0]) {
                final float[] array4 = rgBtoHSB2;
                final int n9 = 0;
                ++array4[n9];
            }
        }
        for (int i = 0; i < dimension.width; ++i) {
            final int n10 = dimension.width - 1;
            final int n11 = n10 - i;
            if (intValue != 1) {
                final float n12 = (rgBtoHSB[0] * n11 + rgBtoHSB2[0] * i) / n10;
                array2[i] = Color.HSBtoRGB((n12 > 1.0f) ? (n12 - 1.0f) : n12, (rgBtoHSB[1] * n11 + rgBtoHSB2[1] * i) / n10, (rgBtoHSB[2] * n11 + rgBtoHSB2[2] * i) / n10);
            }
            else {
                array2[i] = (0xFF000000 | (int)((n2 * n11 + n5 * i) / n10 + 0.5) << 16 | (int)((n3 * n11 + n6 * i) / n10 + 0.5) << 8 | (int)((n4 * n11 + n7 * i) / n10 + 0.5));
            }
        }
        return array2;
    }
    
    public int getBkTimeUnit(int n, final int n2, final int n3) {
        if (this.m_nBkType != 3 || this.m_nBkMode != 3 || this.m_nBkAnim != 1) {
            return -1;
        }
        n -= n2;
        if (this.$232 == 0) {
            this.$232 = 20;
        }
        return (n / this.$232 + 1) * this.$232 - n;
    }
    
    public int getTimeUnit(final int n) {
        return -1;
    }
    
    public void InitBack(final int n, final Component component, final Rectangle rectangle, final MediaTracker mediaTracker, final Image[] array, final boolean[] array2) {
        if (n <= 0) {
            return;
        }
        final Object[] array3 = { new Integer(0), new Color(0), new Integer(0), new Integer(0), new Point(0, 0), new Integer(0), new Integer(0), new Integer(1), new String(), new String() };
        parser(((Applet)component).getParameter("background" + String.valueOf(n)), array3, ",");
        this.m_nBkType = (int)array3[0];
        this.m_clrBk = (Color)array3[1];
        this.m_nBkImg = (int)array3[2];
        this.m_nBkMode = (int)array3[3];
        this.m_ptBkImg = (Point)array3[4];
        this.m_nBkAnim = (int)array3[5];
        this.m_nBkDirection = (int)array3[6];
        this.m_nBkSpeed = (int)array3[7];
        if (this.m_nBkDirection < 1 || this.m_nBkDirection > 8) {
            this.m_nBkDirection = 1;
        }
        final boolean b = false;
        this.$230 = (b ? 1 : 0);
        this.$231 = (b ? 1 : 0);
        if (this.m_nBkType > 1 && this.m_nBkImg != 0) {
            final int n2 = this.m_nBkImg - 1;
            if (!WaitMediaLoad(n2, mediaTracker, array, array2)) {
                return;
            }
            final Image image = array[n2];
            this.$231 = image.getWidth(component);
            this.$230 = image.getHeight(component);
            if (this.m_nBkType == 2 || (this.m_nBkType == 3 && (this.m_nBkMode == 1 || this.m_nBkMode == 4))) {
                this.$216 = image;
                return;
            }
            if (this.m_nBkType == 3 && this.m_nBkMode == 2) {
                if (this.$231 == rectangle.width && this.$230 == rectangle.height) {
                    this.$216 = image;
                    return;
                }
                this.$216 = component.createImage(new MemoryImageSource(rectangle.width, rectangle.height, resizeImage(image, rectangle.width, rectangle.height, component), 0, rectangle.width));
            }
            else if (this.m_nBkType == 3 && this.m_nBkMode == 3) {
                int i = this.m_ptBkImg.x;
                int j = this.m_ptBkImg.y;
                final int n3 = this.$231 * (rectangle.width / this.$231 + 2);
                final int n4 = this.$230 * (rectangle.height / this.$230 + 2);
                this.$216 = component.createImage(n3, n4);
                final Graphics graphics = this.$216.getGraphics();
                graphics.setColor(this.m_clrBk);
                graphics.fillRect(0, 0, n3, n4);
                if (this.m_nBkAnim == 0) {
                    while (i > 0) {
                        i -= this.$231;
                    }
                    while (j > 0) {
                        j -= this.$230;
                    }
                }
                else {
                    this.$303 = 1;
                    this.$232 = 20;
                    if (this.m_nBkSpeed > 6) {
                        for (int k = 7; k < this.m_nBkSpeed; ++k) {
                            this.$303 *= 2;
                        }
                    }
                    else {
                        this.$232 *= (7 - this.m_nBkSpeed) * 2;
                    }
                    if (this.$232 < 20) {
                        this.$232 = 20;
                    }
                    i = 0;
                    j = 0;
                }
                for (int l = 0; l <= (n3 - i) / this.$231; ++l) {
                    for (int n5 = 0; n5 <= (n4 - j) / this.$230; ++n5) {
                        this.WaitDrawImage(graphics, image, i + l * this.$231, j + n5 * this.$230, 0L, 0L, component);
                    }
                }
            }
        }
    }
    
    public void DrawBackground(final Component component, final Image image, final int n, final Rectangle rectangle, final Rectangle rectangle2) {
        if (this.m_nBkType <= 0) {
            return;
        }
        final Graphics graphics = image.getGraphics();
        graphics.setColor(this.m_clrBk);
        graphics.fillRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
        if (this.$216 != null) {
            if (this.m_nBkType == 2) {
                graphics.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                graphics.drawImage(this.$216, rectangle.x, rectangle.y, null);
            }
            else if (this.m_nBkType == 3) {
                final int $231 = this.$231;
                final int $232 = this.$230;
                int x = this.m_ptBkImg.x;
                int y = this.m_ptBkImg.y;
                switch (this.m_nBkMode) {
                    case 1: {
                        x += (rectangle.width - $231) / 2;
                        y += (rectangle.height - $232) / 2;
                    }
                    case 4: {
                        if ($231 != -1) {
                            graphics.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                            graphics.drawImage(this.$216, rectangle.x + x, rectangle.y + y, null);
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if (this.$216 != null) {
                            graphics.drawImage(this.$216, rectangle.x, rectangle.y, null);
                            break;
                        }
                        break;
                    }
                    case 3: {
                        this.TileAnim(component, graphics, n, rectangle, rectangle2);
                        break;
                    }
                }
            }
        }
        graphics.dispose();
    }
    
    public void InitBord(final int n, final Component component, final Rectangle rectangle, final MediaTracker mediaTracker, final Image[] array, final boolean[] array2) {
        if (n <= 0) {
            return;
        }
        final Object[] array3 = { new Integer(0), new Integer(0), new Integer(1), new Color(12566463), new Integer(50), new String(), new String() };
        parser(((Applet)component).getParameter("border" + String.valueOf(n)), array3, ",");
        this.m_nBdStyle = (int)array3[0];
        this.m_nBdType = (int)array3[1];
        this.m_nBdWidth = (int)array3[2];
        this.m_clrBd = (Color)array3[3];
        this.m_nClrChg = (int)array3[4];
        final int width = rectangle.width;
        final int height = rectangle.height;
        if (this.m_nBdStyle == 1 || this.m_nBdStyle == 2) {
            final int min = Math.min(width >> 1, height >> 1);
            if (this.m_nBdWidth > min) {
                this.m_nBdWidth = min;
            }
            this.$117 = new Color[this.m_nBdWidth];
            this.$130 = new Color[this.m_nBdWidth];
            if (this.m_nBdWidth == 1) {
                this.$130[0] = (this.$117[0] = this.m_clrBd);
            }
        }
        else {
            this.m_nBdWidth = 0;
        }
        switch (this.m_nBdStyle) {
            case 1: {
                if (this.m_nBdType == 0) {
                    for (int i = this.m_nBdWidth - 1; i >= 1; --i) {
                        if (i == this.m_nBdWidth - 1) {
                            this.$117[i] = this.m_clrBd;
                            this.$130[i] = this.$117[i];
                        }
                        this.$117[i - 1] = new Color(this.ColorGradient(this.$117[i].getRGB(), this.m_nClrChg, false));
                        this.$130[i - 1] = this.$117[i - 1];
                    }
                    return;
                }
                for (int j = 0; j < this.m_nBdWidth - 1; ++j) {
                    if (j == 0) {
                        this.$117[j] = this.m_clrBd;
                        this.$130[j] = this.$117[j];
                    }
                    this.$117[j + 1] = new Color(this.ColorGradient(this.$117[j].getRGB(), this.m_nClrChg, false));
                    this.$130[j + 1] = this.$117[j + 1];
                }
            }
            case 2: {
                for (int k = this.m_nBdWidth - 1; k >= 1; --k) {
                    if (k == this.m_nBdWidth - 1) {
                        this.$117[k] = this.m_clrBd;
                        this.$130[k] = this.$117[k];
                    }
                    if (this.m_nBdType != 0) {
                        this.$117[k - 1] = new Color(this.ColorGradient(this.$117[k].getRGB(), this.m_nClrChg, false));
                        this.$130[k - 1] = new Color(this.ColorGradient(this.$130[k].getRGB(), this.m_nClrChg, true));
                    }
                    else {
                        this.$117[k - 1] = new Color(this.ColorGradient(this.$117[k].getRGB(), this.m_nClrChg, true));
                        this.$130[k - 1] = new Color(this.ColorGradient(this.$130[k].getRGB(), this.m_nClrChg, false));
                    }
                }
            }
            case 3: {
                final int n2 = this.m_nBdType - 1;
                if (!WaitMediaLoad(n2, mediaTracker, array, array2)) {
                    return;
                }
                final Image $219 = array[n2];
                final int width2 = $219.getWidth(component);
                final int height2 = $219.getHeight(component);
                if (width2 == rectangle.width && height2 == rectangle.height) {
                    this.$219 = $219;
                    return;
                }
                (this.$106 = resizeImage($219, rectangle.width, rectangle.height, component))[0] = 0;
                this.$219 = component.createImage(new MemoryImageSource(rectangle.width, rectangle.height, this.$106, 0, rectangle.width));
            }
            default: {}
        }
    }
    
    public void DrawBorder(final Component component, final Image image, final Rectangle rectangle, final Rectangle rectangle2) {
        if (this.m_nBdStyle <= 0) {
            return;
        }
        final int x = rectangle.x;
        final int y = rectangle.y;
        final int width = rectangle.width;
        final int height = rectangle.height;
        final Graphics graphics = image.getGraphics();
        if (this.m_nBdStyle == 1 || this.m_nBdStyle == 2) {
            final int n = width + (x - 1);
            final int n2 = height + (y - 1);
            for (int i = this.m_nBdWidth - 1; i >= 0; --i) {
                graphics.setColor(this.$117[i]);
                graphics.drawLine(x + i, y, x + i, n2);
                graphics.drawLine(x, y + i, n, y + i);
                graphics.setColor(this.$130[i]);
                graphics.drawLine(n - i, y, n - i, n2);
                graphics.drawLine(x, n2 - i, n, n2 - i);
            }
        }
        else if (this.m_nBdStyle == 3 && this.$219 != null) {
            graphics.drawImage(this.$219, rectangle.x, rectangle.y, null);
        }
        graphics.dispose();
    }
    
    public int getBorderWidth() {
        return this.m_nBdWidth;
    }
    
    public boolean hittestBDBK(final Rectangle rectangle, int n, int n2) {
        if (this.m_nBkType > 0) {
            return true;
        }
        if (this.m_nBdStyle == 0) {
            return false;
        }
        n -= rectangle.x;
        n2 -= rectangle.y;
        if (n < 0 || n2 < 0 || n >= rectangle.width || n2 >= rectangle.height) {
            return false;
        }
        if (this.m_nBdStyle == 1 || this.m_nBdStyle == 2) {
            return n < this.m_nBdWidth || n >= rectangle.width - this.m_nBdWidth || n2 < this.m_nBdWidth || n2 >= rectangle.height - this.m_nBdWidth;
        }
        if (this.m_nBdStyle != 3) {
            return true;
        }
        if (this.$219 == null) {
            return false;
        }
        if (this.$106 == null) {
            return true;
        }
        final int width = this.$219.getWidth(null);
        final int height = this.$219.getHeight(null);
        return n < width && n2 < height && (this.$106[n2 * width + n] & 0xFF000000) != 0x0;
    }
    
    public int ColorGradient(final int n, final int n2, final boolean b) {
        final int n3 = n >> 16 & 0xFF;
        final int n4 = n >> 8 & 0xFF;
        final int n5 = n & 0xFF;
        int n6;
        int n7;
        int n8;
        if (b) {
            n6 = Math.min(255, n3 + n2);
            n7 = Math.min(255, n4 + n2);
            n8 = Math.min(255, n5 + n2);
        }
        else {
            n6 = Math.max(0, n3 - n2);
            n7 = Math.max(0, n4 - n2);
            n8 = Math.max(0, n5 - n2);
        }
        return (n & 0xFF000000) | n6 << 16 | n7 << 8 | n8;
    }
    
    public void TileAnim(final Component component, final Graphics graphics, final int n, final Rectangle rectangle, final Rectangle rectangle2) {
        if (this.$231 <= 0 || this.$230 <= 0 || this.$216 == null) {
            return;
        }
        graphics.clipRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
        if (this.m_nBkAnim == 0) {
            graphics.drawImage(this.$216, rectangle.x, rectangle.y, null);
            return;
        }
        this.$267 = n / this.$232;
        if (this.$267 != this.$279) {
            this.$227 += this.$303;
            this.$279 = this.$267;
        }
        int n2 = 0;
        int n3 = 0;
        switch (this.m_nBkDirection) {
            case 1: {
                if (this.$231 < this.$230) {
                    if (this.$227 >= this.$231) {
                        this.$227 -= this.$231;
                    }
                    n2 = this.$227;
                    n3 = this.$227 * this.$230 / this.$231;
                    break;
                }
                if (this.$227 >= this.$230) {
                    this.$227 -= this.$230;
                }
                n3 = this.$227;
                n2 = this.$227 * this.$231 / this.$230;
                break;
            }
            case 2: {
                n2 = 0;
                if (this.$227 >= this.$230) {
                    this.$227 -= this.$230;
                }
                n3 = this.$227;
                break;
            }
            case 3: {
                if (this.$231 < this.$230) {
                    if (this.$227 >= this.$231) {
                        this.$227 -= this.$231;
                    }
                    n2 = this.$231 - this.$227;
                    n3 = this.$227 * this.$230 / this.$231;
                    break;
                }
                if (this.$227 >= this.$230) {
                    this.$227 -= this.$230;
                }
                n3 = this.$227;
                n2 = this.$231 - this.$227 * this.$231 / this.$230;
                break;
            }
            case 4: {
                n3 = 0;
                if (this.$227 >= this.$231) {
                    this.$227 -= this.$231;
                }
                n2 = this.$227;
                break;
            }
            case 5: {
                n3 = 0;
                if (this.$227 >= this.$231) {
                    this.$227 -= this.$231;
                }
                n2 = this.$231 - this.$227;
                break;
            }
            case 6: {
                if (this.$231 < this.$230) {
                    if (this.$227 >= this.$231) {
                        this.$227 -= this.$231;
                    }
                    n2 = this.$227;
                    n3 = this.$230 - this.$227 * this.$230 / this.$231;
                    break;
                }
                if (this.$227 >= this.$230) {
                    this.$227 -= this.$230;
                }
                n3 = this.$230 - this.$227;
                n2 = this.$227 * this.$231 / this.$230;
                break;
            }
            case 7: {
                n2 = 0;
                if (this.$227 >= this.$230) {
                    this.$227 -= this.$230;
                }
                n3 = this.$230 - this.$227;
                break;
            }
            case 8: {
                if (this.$231 < this.$230) {
                    if (this.$227 >= this.$231) {
                        this.$227 -= this.$231;
                    }
                    n2 = this.$231 - this.$227;
                    n3 = this.$230 - this.$227 * this.$230 / this.$231;
                    break;
                }
                if (this.$227 >= this.$230) {
                    this.$227 -= this.$230;
                }
                n3 = this.$230 - this.$227;
                n2 = this.$231 - this.$227 * this.$231 / this.$230;
                break;
            }
            default: {
                return;
            }
        }
        graphics.drawImage(this.$216, rectangle.x - n2, rectangle.y - n3, null);
    }
    
    public void WaitDrawImage(final Graphics graphics, final Image image, final float n, final float n2, final long n3, final long n4, final Component component) {
        int n5 = 0;
        while (n5++ < 1000) {
            if (n3 <= 0L || n4 <= 0L) {
                if (graphics.drawImage(image, (int)n, (int)n2, component)) {
                    return;
                }
                continue;
            }
            else {
                if (graphics.drawImage(image, (int)n, (int)n2, (int)n3, (int)n4, component)) {
                    return;
                }
                continue;
            }
        }
    }
    
    public static boolean parser(final String s, final Object[] array, final String s2) {
        final String string = s + s2;
        final int length = string.length();
        final int[] array2 = { 0 };
        for (int i = 0; i < array.length; ++i) {
            if (array[i] instanceof Integer) {
                array[i] = new Integer(nextInt(string, s2, array2, (int)array[i], 10));
            }
            else if (array[i] == null || array[i] instanceof String) {
                final String nextToken;
                if ((nextToken = nextToken(string, s2, array2)) != null) {
                    array[i] = nextToken;
                }
            }
            else if (array[i] instanceof Color) {
                array[i] = new Color(nextInt(string, s2, array2, ((Color)array[i]).getRGB(), 16));
            }
            else if (array[i] instanceof Point) {
                final Point point = (Point)array[i];
                point.x = nextInt(string, s2, array2, point.x, 10);
                point.y = nextInt(string, s2, array2, point.y, 10);
            }
            else if (array[i] instanceof Polygon) {
                final int nextInt = nextInt(string, s2, array2, 0, 10);
                if (nextInt > 0) {
                    final Polygon polygon = new Polygon();
                    for (int j = 0; j < nextInt; ++j) {
                        polygon.addPoint(nextInt(string, s2, array2, 0, 10), nextInt(string, s2, array2, 0, 10));
                    }
                    array[i] = polygon;
                }
            }
            if (array2[0] >= length) {
                return false;
            }
        }
        return true;
    }
    
    public static int nextInt(final String s, final String s2, final int[] array, int int1, final int n) {
        final String nextToken = nextToken(s, s2, array);
        if (nextToken != null) {
            try {
                int1 = Integer.parseInt(nextToken, n);
            }
            catch (Exception ex) {}
        }
        return int1;
    }
    
    public static String nextToken(final String s, final String s2, final int[] array) {
        final int index = s.indexOf(s2, array[0]);
        if (index != -1) {
            final int n = array[0];
            array[0] = index + s2.length();
            if (index != n) {
                try {
                    return s.substring(n, index);
                }
                catch (Exception ex) {}
            }
        }
        return null;
    }
    
    public static String decodeDelim(String replaceStr) {
        if (replaceStr == null) {
            return null;
        }
        for (int i = replaceStr.indexOf(127); i != -1; i = replaceStr.indexOf(127, i)) {
            char char1;
            try {
                char1 = replaceStr.charAt(i + 1);
            }
            catch (Exception ex) {
                break;
            }
            char c = '\0';
            switch (char1) {
                case 48: {
                    c = '|';
                    break;
                }
                case 49: {
                    c = '&';
                    break;
                }
                case 50: {
                    c = '$';
                    break;
                }
                case 51: {
                    c = ',';
                    break;
                }
                case 52: {
                    c = '\"';
                    break;
                }
                default: {
                    c = ' ';
                    break;
                }
            }
            replaceStr = replaceStr(replaceStr, i, c);
        }
        return replaceStr;
    }
    
    public static String replaceStr(final String s, final int n, final char c) {
        final int length = s.length();
        String s2;
        try {
            s2 = s.substring(0, n) + String.valueOf(c);
        }
        catch (Exception ex) {
            return s;
        }
        if (n + 2 < length) {
            try {
                s2 += s.substring(n + 2);
            }
            catch (Exception ex2) {}
        }
        s2.length();
        return s2;
    }
    
    public static boolean IsPixelGrabberWork(final Component component) {
        final Image image = component.createImage(2, 2);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, 2, 2);
        graphics.dispose();
        final int[] getImageMemory = GetImageMemory(image, null, false, 0);
        return getImageMemory[0] != 0 || getImageMemory[1] != 0 || getImageMemory[2] != 0 || getImageMemory[3] != 0;
    }
    
    public static int[] GetImageMemory(final Image image, final Rectangle rectangle, final boolean b, int n) {
        Rectangle rectangle2;
        if (rectangle == null) {
            rectangle2 = new Rectangle(0, 0, image.getWidth(null), image.getHeight(null));
        }
        else {
            rectangle2 = rectangle;
        }
        final int n2 = rectangle2.width * rectangle2.height;
        final int[] array = new int[n2];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height, array, 0, rectangle2.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (Exception ex) {}
        if (b) {
            n |= 0xFF000000;
            for (int i = 0; i < n2; ++i) {
                if (array[i] == n) {
                    final int[] array2 = array;
                    final int n3 = i;
                    array2[n3] &= 0xFFFFFF;
                }
            }
        }
        return array;
    }
    
    protected Vector GetMultilinesText(final Graphics graphics, final Font font, final String s, final int[] array, final int n) {
        final Font font2 = graphics.getFont();
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n2 = 0;
        int n3 = 0;
        final int[] array2 = { 0 };
        final String string = s + "\\n";
        final int length = string.length();
        final Vector<String> vector = new Vector<String>();
        array[0] = 0;
        while (array2[0] < length) {
            String s2 = nextToken(string, "\\n", array2);
            if (s2 != null) {
                int i;
                for (i = fontMetrics.stringWidth(s2); i > n; i = fontMetrics.stringWidth(s2), ++n3) {
                    final int wrapText = this.wrapText(s2, fontMetrics, n);
                    if (wrapText == s2.length()) {
                        break;
                    }
                    final String substring = s2.substring(0, wrapText);
                    vector.addElement((substring == null) ? "" : substring);
                    final int stringWidth = fontMetrics.stringWidth(substring);
                    if (stringWidth > n2) {
                        n2 = stringWidth;
                        array[0] = n3;
                    }
                    s2 = s2.substring(wrapText);
                }
                if (i > n2) {
                    n2 = i;
                    array[0] = n3;
                }
            }
            vector.addElement((s2 == null) ? "" : s2);
            ++n3;
        }
        graphics.setFont(font2);
        return vector;
    }
    
    protected int wrapText(final String s, final FontMetrics fontMetrics, final int n) {
        int n2 = 0;
        int n3 = -1;
        for (int i = 0; i <= n; i = fontMetrics.stringWidth(s.substring(0, n3 + 1))) {
            n2 = n3 + 1;
            n3 = s.indexOf(" ", n2);
            if (n3 == -1) {
                break;
            }
        }
        if (n2 != 0) {
            n3 = n2;
        }
        else if (n3 == -1) {
            n3 = s.length();
        }
        else {
            ++n3;
        }
        return n3;
    }
    
    protected boolean DrawLineText(final Graphics graphics, final Vector vector, final Font font, final int n, final Rectangle rectangle, final int n2, final int n3) {
        if (vector == null) {
            return false;
        }
        Font font2;
        if (font == null) {
            font2 = new Font("Helvetica", 0, 16);
        }
        else {
            font2 = font;
        }
        final Font font3 = graphics.getFont();
        graphics.setFont(font2);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int italicExt = this.getItalicExt(font);
        final int height = fontMetrics.getHeight();
        final int ascent = fontMetrics.getAscent();
        final int size = vector.size();
        if (rectangle == null) {
            return false;
        }
        for (int n4 = rectangle.y + ascent + n3, i = 0; i < size; ++i, n4 += height) {
            String s;
            try {
                s = vector.elementAt(i);
            }
            catch (Exception ex) {
                break;
            }
            if (s != null) {
                int n5 = rectangle.x + n2;
                switch (n) {
                    case 2: {
                        n5 += (rectangle.width - fontMetrics.stringWidth(s)) / 2;
                        break;
                    }
                    case 3: {
                        n5 += rectangle.width - fontMetrics.stringWidth(s) - italicExt;
                        break;
                    }
                }
                graphics.drawString(s, n5, n4);
            }
        }
        graphics.setFont(font3);
        return true;
    }
    
    protected Color brighter(final Color color) {
        return new Color(Math.min(color.getRed() + 200, 255), Math.min(color.getGreen() + 200, 255), Math.min(color.getBlue() + 200, 255));
    }
    
    protected Color darker(final Color color) {
        return new Color(Math.max(color.getRed() - 200, 0), Math.max(color.getGreen() - 200, 0), Math.max(color.getBlue() - 200, 0));
    }
    
    protected int getTypeOffset(final int n, final int n2) {
        int n3 = n / 60 + 1;
        if (n2 == 3) {
            n3 *= 2;
        }
        return n3;
    }
    
    protected String checkFont(final String s, final int n) {
        if (s.equals("Dialog") && (n == 2 || n == 3)) {
            return "Helvetica";
        }
        return s;
    }
    
    public static Image resizeImage(final Image image, int n, int n2, final boolean b, final Component component) {
        if (image == null) {
            return component.createImage(n, n2);
        }
        final int width = image.getWidth(component);
        final int height = image.getHeight(component);
        if (n <= 0) {
            n = 1;
        }
        if (n2 <= 0) {
            n2 = 1;
        }
        Image image2;
        if (b) {
            final int[] array = new int[width * height];
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex) {}
            final int[] array2 = new int[n * n2];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n2; ++j) {
                    array2[i + j * n] = array[i * width / n + j * height / n2 * width];
                }
            }
            image2 = component.createImage(new MemoryImageSource(n, n2, array2, 0, n));
        }
        else {
            image2 = component.createImage(n, n2);
            final Graphics graphics = image2.getGraphics();
            final long currentTimeMillis = System.currentTimeMillis();
            boolean drawImage;
            long currentTimeMillis2;
            do {
                drawImage = graphics.drawImage(image, 0, 0, n, n2, component);
                currentTimeMillis2 = System.currentTimeMillis();
            } while (!drawImage && currentTimeMillis2 - currentTimeMillis < 6000L);
            graphics.dispose();
        }
        return image2;
    }
    
    public static int[] resizeImage(final Image image, int n, int n2, final Component component) {
        if (n <= 0) {
            n = 1;
        }
        if (n2 <= 0) {
            n2 = 1;
        }
        final int[] array = new int[n * n2];
        if (image != null) {
            final int width = image.getWidth(component);
            final int height = image.getHeight(component);
            final int[] array2 = new int[width * height];
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array2, 0, width);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex) {}
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n2; ++j) {
                    array[i + j * n] = array2[i * width / n + j * height / n2 * width];
                }
            }
        }
        return array;
    }
    
    public static void ArrayCopyArea(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        for (int i = 0; i < n6; ++i) {
            final int n9 = n2 * (n4 + i) + n3;
            final int n10 = n2 * (n4 + i + n8) + (n3 + n7);
            if (n < n9 + n5) {
                break;
            }
            if (n < n10 + n5) {
                return;
            }
            System.arraycopy(array, n9, array, n10, n5);
        }
    }
    
    protected int getItalicExt(final Font font) {
        int n = 0;
        if (font.isItalic()) {
            n = font.getSize() / 4 + 4;
        }
        return n;
    }
    
    public static boolean WaitMediaLoad(final int n, final MediaTracker mediaTracker, final Image[] array, final boolean[] array2) {
        if (array == null || n < 0 || n >= array.length || n >= array2.length) {
            return false;
        }
        if (!array2[n]) {
            try {
                mediaTracker.waitForID(n);
                array2[n] = !mediaTracker.isErrorID(n);
            }
            catch (Exception ex) {}
        }
        return array2[n] && array[n] != null;
    }
    
    public uasbase() {
        this.$279 = -1;
        this.$303 = 1;
    }
}
