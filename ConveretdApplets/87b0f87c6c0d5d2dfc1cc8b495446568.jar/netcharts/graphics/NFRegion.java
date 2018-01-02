// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Toolkit;
import java.awt.FontMetrics;
import netcharts.util.NFColor;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import netcharts.util.NFParamImage;
import java.util.Vector;
import netcharts.util.NFParam;
import netcharts.util.NFUtil;
import java.awt.Polygon;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;

public class NFRegion
{
    public static final int NONE = 0;
    public static final int BOX = 1;
    public static final int SHADOW = 2;
    public static final int RAISED = 3;
    public static final int RECESS = 4;
    public static final int ETCHED = 5;
    public static final int TILE = 0;
    public static final int SIZE = 1;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int CENTER = 2;
    private Graphics a;
    private Image b;
    private int c;
    private int d;
    private int e;
    private int f;
    private Color g;
    private int h;
    private int i;
    private int j;
    private Color k;
    private Color l;
    private double m;
    private String n;
    private Font o;
    private int p;
    private int q;
    private static Polygon r;
    
    public NFRegion(final Graphics a, final int c, final int d, final int e, final int f, final Color g, final int h, final int j, final Color k) {
        this.a = null;
        this.b = null;
        this.c = 0;
        this.d = 0;
        this.e = 100;
        this.f = 100;
        this.g = Color.red;
        this.h = 2;
        this.i = 0;
        this.j = 4;
        this.k = Color.black;
        this.l = Color.black;
        this.m = 0.0;
        this.n = null;
        this.o = NFUtil.getFont("Helvetica", 0, 12);
        this.p = 0;
        this.q = 15;
        this.a = a;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.j = j;
        this.k = k;
    }
    
    public NFRegion(final Graphics a, final int c, final int d, final int e, final int f) {
        this.a = null;
        this.b = null;
        this.c = 0;
        this.d = 0;
        this.e = 100;
        this.f = 100;
        this.g = Color.red;
        this.h = 2;
        this.i = 0;
        this.j = 4;
        this.k = Color.black;
        this.l = Color.black;
        this.m = 0.0;
        this.n = null;
        this.o = NFUtil.getFont("Helvetica", 0, 12);
        this.p = 0;
        this.q = 15;
        this.a = a;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    public NFRegion(final Graphics a) {
        this.a = null;
        this.b = null;
        this.c = 0;
        this.d = 0;
        this.e = 100;
        this.f = 100;
        this.g = Color.red;
        this.h = 2;
        this.i = 0;
        this.j = 4;
        this.k = Color.black;
        this.l = Color.black;
        this.m = 0.0;
        this.n = null;
        this.o = NFUtil.getFont("Helvetica", 0, 12);
        this.p = 0;
        this.q = 15;
        this.a = a;
    }
    
    public NFRegion() {
        this.a = null;
        this.b = null;
        this.c = 0;
        this.d = 0;
        this.e = 100;
        this.f = 100;
        this.g = Color.red;
        this.h = 2;
        this.i = 0;
        this.j = 4;
        this.k = Color.black;
        this.l = Color.black;
        this.m = 0.0;
        this.n = null;
        this.o = NFUtil.getFont("Helvetica", 0, 12);
        this.p = 0;
        this.q = 15;
    }
    
    public NFRegion(final NFRegion nfRegion) {
        this();
        if (nfRegion != null) {
            this.a = nfRegion.a;
            this.c = nfRegion.c;
            this.d = nfRegion.d;
            this.e = nfRegion.e;
            this.f = nfRegion.f;
            this.g = nfRegion.g;
            this.h = nfRegion.h;
            this.j = nfRegion.j;
            this.k = nfRegion.k;
        }
    }
    
    public void setLabelMargin(final int q) {
        this.q = q;
    }
    
    public void setJustify(final int p) {
        this.p = p;
    }
    
    public Color getLabelColor() {
        return this.l;
    }
    
    public void setLabelColor(final Color l) {
        this.l = l;
    }
    
    public String getLabel() {
        return this.n;
    }
    
    public void setLabel(final String n) {
        this.n = n;
    }
    
    public Font getLabelFont() {
        return this.o;
    }
    
    public void setLabelFont(final Font o) {
        this.o = o;
    }
    
    public static NFRegion loadParams(final NFParam nfParam, final Object o) {
        return loadParams(nfParam, o, 0);
    }
    
    public static NFRegion loadParams(final NFParam nfParam, final Object o, final int n) {
        final NFRegion nfRegion = new NFRegion();
        final Vector vector = (Vector)o;
        if (vector == null || vector.size() <= n) {
            return null;
        }
        loadParams(nfRegion, vector, n);
        nfRegion.setScale(nfParam.getChartScale());
        return nfRegion;
    }
    
    public static void loadParams(final NFRegion nfRegion, final Vector vector, final int n) {
        nfRegion.setColor(vector.elementAt(n + 0));
        nfRegion.setBorder(((Number)vector.elementAt(n + 1)).intValue(), ((Number)vector.elementAt(n + 2)).intValue(), vector.elementAt(n + 5));
        final NFParamImage nfParamImage = (NFParamImage)vector.elementAt(n + 3);
        nfRegion.setImage((nfParamImage == null) ? null : nfParamImage.im);
        nfRegion.setImageType(((Number)vector.elementAt(n + 4)).intValue());
    }
    
    public void setGraphics(final Graphics a) {
        this.a = a;
    }
    
    public void setImage(final Image b) {
        this.b = b;
    }
    
    public Image getImage() {
        return this.b;
    }
    
    public void setColor(final Color g) {
        this.g = g;
    }
    
    public Color getColor() {
        return this.g;
    }
    
    public void setPos(final int c, final int d) {
        this.c = c;
        this.d = d;
    }
    
    public void setSize(final int e, final int f) {
        this.e = e;
        this.f = f;
    }
    
    public void setBorder(final int h) {
        this.h = h;
    }
    
    public void setBorder(final int h, final int j, final Color k) {
        this.h = h;
        this.j = j;
        this.k = k;
    }
    
    public void setBorderColor(final Color k) {
        this.k = k;
    }
    
    public void setScale(final double m) {
        this.m = m;
    }
    
    public Color getBorderColor() {
        return this.k;
    }
    
    public void setBorderType(final int h) {
        this.h = h;
    }
    
    public void setBorderWidth(final int j) {
        this.j = j;
    }
    
    public void setImageType(final int i) {
        this.i = i;
    }
    
    public int getImageType() {
        return this.i;
    }
    
    public int getBorderType() {
        return this.h;
    }
    
    private static int a(int n, final double n2) {
        if (n <= 0 || n2 <= 0.0) {
            return n;
        }
        n *= (int)n2;
        if (n < 1) {
            n = 1;
        }
        return n;
    }
    
    public NFRegionBorder getBorder() {
        return getBorder(this.h, this.j, null, this.m, this.n, this.o);
    }
    
    public NFRegionBorder getBorder(final NFRegionBorder nfRegionBorder) {
        return getBorder(this.h, this.j, nfRegionBorder, this.m, this.n, this.o);
    }
    
    public static NFRegionBorder getBorder(final int n, final int n2) {
        return getBorder(n, n2, null, null, null);
    }
    
    public static NFRegionBorder getBorder(final int n, final int n2, final String s, final Font font) {
        return getBorder(n, n2, s, font, null);
    }
    
    public static NFRegionBorder getBorder(final int n, final int n2, final String s, final Font font, final NFRegionBorder nfRegionBorder) {
        return getBorder(n, n2, nfRegionBorder, 0.0, s, font);
    }
    
    public static NFRegionBorder getBorder(final int n, final int n2, final NFRegionBorder nfRegionBorder, final double n3) {
        return getBorder(n, n2, nfRegionBorder, n3, null, null);
    }
    
    public static NFRegionBorder getBorder(final int n, int a, NFRegionBorder nfRegionBorder, final double n2, final String s, final Font font) {
        if (nfRegionBorder == null) {
            nfRegionBorder = new NFRegionBorder();
        }
        nfRegionBorder.top = 0;
        nfRegionBorder.bottom = 0;
        nfRegionBorder.left = 0;
        nfRegionBorder.right = 0;
        a = a(a, n2);
        switch (n) {
            case 2: {
                final NFRegionBorder nfRegionBorder2 = nfRegionBorder;
                final NFRegionBorder nfRegionBorder3 = nfRegionBorder;
                final int n3 = a;
                nfRegionBorder3.bottom = n3;
                nfRegionBorder2.right = n3;
                if (s == null) {
                    break;
                }
                final Dimension a2 = a(null, s, font);
                if (a2.height > a) {
                    nfRegionBorder.top = a2.height;
                    final NFRegionBorder nfRegionBorder4 = nfRegionBorder;
                    final NFRegionBorder nfRegionBorder5 = nfRegionBorder;
                    final int height = a2.height;
                    nfRegionBorder5.bottom = height;
                    nfRegionBorder4.right = height;
                    break;
                }
                break;
            }
            case 1:
            case 3:
            case 4:
            case 5: {
                final NFRegionBorder nfRegionBorder6 = nfRegionBorder;
                final NFRegionBorder nfRegionBorder7 = nfRegionBorder;
                final int n4 = a;
                nfRegionBorder7.top = n4;
                nfRegionBorder6.left = n4;
                final NFRegionBorder nfRegionBorder8 = nfRegionBorder;
                final NFRegionBorder nfRegionBorder9 = nfRegionBorder;
                final int n5 = a;
                nfRegionBorder9.bottom = n5;
                nfRegionBorder8.right = n5;
                if (s == null) {
                    break;
                }
                final Dimension a3 = a(null, s, font);
                if (a3.height > a) {
                    nfRegionBorder.top = a3.height;
                    break;
                }
                break;
            }
        }
        return nfRegionBorder;
    }
    
    public void getHashID(final StringBuffer sb) {
        sb.append(this.g);
        sb.append(",");
        sb.append(this.h);
        sb.append(",");
        sb.append(this.i);
        sb.append(",");
        sb.append(this.k);
        sb.append(",");
        sb.append(this.j);
        sb.append(",");
        sb.append(this.b);
    }
    
    public void draw() {
        this.draw(this.a, this.c, this.d, this.e, this.f, this.g, this.h, this.j, this.k, this.b);
    }
    
    public void draw(final Graphics graphics) {
        this.draw(graphics, this.c, this.d, this.e, this.f, this.g, this.h, this.j, this.k, this.b);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2) {
        this.draw(graphics, n, n2, this.e, this.f, this.g, this.h, this.j, this.k, this.b);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        this.draw(graphics, n, n2, n3, n4, this.g, this.h, this.j, this.k, this.b);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final NFPatternFill nfPatternFill) {
        this.draw(graphics, n, n2, n3, n4, this.g, this.h, this.j, this.k, this.b, nfPatternFill);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color, final int n5, final int n6, final Color color2, final Image image) {
        draw(graphics, n, n2, n3, n4, color, n5, n6, color2, image, this.i, this.m, this.n, this.p, this.q, this.o, this.l);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color, final int n5, final int n6, final Color color2, final Image image, final NFPatternFill nfPatternFill) {
        draw(graphics, n, n2, n3, n4, color, n5, n6, color2, image, this.i, this.m, this.n, this.p, this.q, this.o, this.l, nfPatternFill);
    }
    
    public static synchronized void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color, final int n5, final int n6, final Color color2, final Image image, final int n7) {
        draw(graphics, n, n2, n3, n4, color, n5, n6, color2, image, n7, 0.0);
    }
    
    public static synchronized void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color, final int n5, final int n6, final Color color2, final Image image, final int n7, final double n8) {
        draw(graphics, n, n2, n3, n4, color, n5, n6, color2, image, n7, n8, null, 0, 0, null, null);
    }
    
    public static synchronized void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color, final int n5, final int n6, final Color color2, final Image image, final int n7, final double n8, final String s, final int n9, final int n10, final Font font, final Color color3) {
        draw(graphics, n, n2, n3, n4, color, n5, n6, color2, image, n7, n8, s, n9, n10, font, color3, null);
    }
    
    public static synchronized void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color, final int n5, int a, final Color color2, final Image image, final int n6, final double n7, final String s, final int n8, final int n9, final Font font, final Color color3, final NFPatternFill nfPatternFill) {
        if (graphics == null) {
            return;
        }
        a = a(a, n7);
        final Color color4 = graphics.getColor();
        if (n5 == 2 && color2 != null) {
            graphics.setColor(color2);
            graphics.fillRect(n + a, n2 + a, n3 - a, n4 - a);
        }
        final NFRegionBorder border = getBorder(n5, a, s, font);
        if (color != null) {
            graphics.setColor(color);
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0 && (image == null || image.getWidth(null) <= 0)) {
                NF12GraphicsUtil.patternFillRect(graphics, n + border.left, n2 + border.top, n3 - border.left - border.right, n4 - border.top - border.bottom, color2, nfPatternFill);
            }
            else {
                graphics.fillRect(n + border.left, n2 + border.top, n3 - border.left - border.right, n4 - border.top - border.bottom);
            }
        }
        if (image != null && image.getWidth(null) > 0) {
            NFImage.drawBackgroundImage(graphics, image, n6, n + border.left, n2 + border.top, n3 - border.left - border.right, n4 - border.top - border.bottom);
        }
        a(graphics, n, n2, n3, n4, color, n5, a, color2, s, n8, n9, font, color3);
        graphics.setColor(color4);
    }
    
    private static void a(final Graphics graphics, final int n, int n2, final int n3, int n4, Color color, final int n5, final int n6, final Color color2, final String s, final int n7, final int n8, final Font font, final Color color3) {
        if (n5 == 0 || n5 == 2) {
            return;
        }
        int n9 = n;
        int n10 = 0;
        Dimension a = null;
        if (s != null) {
            a = a(null, s, font);
            int n11 = a.height / 2 - n6 / 2;
            if (n11 < 0) {
                n11 = 0;
                n10 = n2 + n6 / 2;
            }
            else {
                n10 = n11;
                n2 += n11;
                n4 -= n11;
            }
            n9 = 0;
            switch (n7) {
                case 0: {
                    n9 = n11 + n8;
                    break;
                }
                case 2: {
                    n9 = n3 / 2 - a.width / 2;
                    break;
                }
                case 1: {
                    n9 = n3 - n11 * 2 - n8 - a.width;
                    break;
                }
            }
        }
        if (n5 == 1 || n5 == 5) {
            if (color2 != null) {
                graphics.setColor(color2);
            }
            else {
                if (color == null) {
                    return;
                }
                graphics.setColor(color);
            }
            if (n5 == 1) {
                for (int i = 0; i < n6; ++i) {
                    graphics.drawRect(n + i, n2 + i, n3 - (i + i + 1), n4 - (i + i + 1));
                }
            }
            else {
                final Color color4 = graphics.getColor();
                final Color darker = NFColor.darker(color4);
                graphics.setColor(NFColor.brighter(color4));
                for (int j = 0; j < n6 / 2; ++j) {
                    graphics.drawRect(n + j + n6 / 2, n2 + j + n6 / 2, n3 - (j + j + 1) - n6 / 2, n4 - (j + j + 1) - n6 / 2);
                }
                graphics.setColor(darker);
                for (int k = 0; k < n6 / 2; ++k) {
                    graphics.drawRect(n + k, n2 + k, n3 - (k + k + 1) - n6 / 2, n4 - (k + k + 1) - n6 / 2);
                }
                graphics.setColor(color4);
            }
            if (s != null && font != null) {
                final FontMetrics fontMetrics = graphics.getFontMetrics(font);
                final int n12 = fontMetrics.stringWidth("W") / 2;
                if (color3 != null) {
                    graphics.setColor(color3);
                }
                graphics.clearRect(n9 - n12, n2 - n10, a.width + n12 * 2, a.height);
                final Font font2 = graphics.getFont();
                graphics.setFont(font);
                graphics.drawString(s, n9, n2 - n10 + fontMetrics.getMaxAscent());
                graphics.setFont(font2);
            }
            return;
        }
        if (color == null) {
            color = color2;
        }
        if (color == null) {
            return;
        }
        final Color color5 = graphics.getColor();
        final Color brighter = NFColor.brighter(color);
        final Color darker2 = NFColor.darker(color);
        Color color6;
        Color color7;
        if (n5 == 3) {
            color6 = brighter;
            color7 = darker2;
        }
        else {
            color6 = darker2;
            color7 = brighter;
        }
        if (NFRegion.r == null) {
            NFRegion.r = new Polygon();
            NFRegion.r.xpoints = new int[7];
            NFRegion.r.ypoints = new int[7];
            NFRegion.r.npoints = 7;
        }
        NFRegion.r.xpoints[0] = n;
        NFRegion.r.ypoints[0] = n2;
        NFRegion.r.xpoints[1] = n;
        NFRegion.r.ypoints[1] = n2 + n4;
        NFRegion.r.xpoints[2] = n + n6;
        NFRegion.r.ypoints[2] = n2 + n4 - n6;
        NFRegion.r.xpoints[3] = n + n6;
        NFRegion.r.ypoints[3] = n2 + n6;
        NFRegion.r.xpoints[4] = n + n3 - n6;
        NFRegion.r.ypoints[4] = n2 + n6;
        NFRegion.r.xpoints[5] = n + n3;
        NFRegion.r.ypoints[5] = n2;
        NFRegion.r.xpoints[6] = n;
        NFRegion.r.ypoints[6] = n2;
        graphics.setColor(color6);
        graphics.fillPolygon(NFRegion.r);
        NFRegion.r.xpoints[0] = n + n3;
        NFRegion.r.ypoints[0] = n2 + n4;
        NFRegion.r.xpoints[1] = n;
        NFRegion.r.ypoints[1] = n2 + n4;
        NFRegion.r.xpoints[2] = n + n6;
        NFRegion.r.ypoints[2] = n2 + n4 - n6;
        NFRegion.r.xpoints[3] = n + n3 - n6;
        NFRegion.r.ypoints[3] = n2 + n4 - n6;
        NFRegion.r.xpoints[4] = n + n3 - n6;
        NFRegion.r.ypoints[4] = n2 + n6;
        NFRegion.r.xpoints[5] = n + n3;
        NFRegion.r.ypoints[5] = n2;
        NFRegion.r.xpoints[6] = n + n3;
        NFRegion.r.ypoints[6] = n2 + n4;
        graphics.setColor(color7);
        graphics.fillPolygon(NFRegion.r);
        graphics.setColor(color5);
        if (s != null && font != null) {
            final FontMetrics fontMetrics2 = graphics.getFontMetrics(font);
            final int n13 = fontMetrics2.stringWidth("W") / 2;
            if (color3 != null) {
                graphics.setColor(color3);
            }
            graphics.clearRect(n9 - n13, n2 - n10, a.width + n13 * 2, a.height);
            final Font font3 = graphics.getFont();
            graphics.setFont(font);
            graphics.drawString(s, n9, n2 - n10 + fontMetrics2.getMaxAscent());
            graphics.setFont(font3);
        }
    }
    
    private static Dimension a(final Graphics graphics, final String s, final Font font) {
        if (s == null || font == null) {
            return new Dimension(0, 0);
        }
        FontMetrics fontMetrics;
        if (graphics == null) {
            fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
        }
        else {
            fontMetrics = graphics.getFontMetrics(font);
        }
        return new Dimension(fontMetrics.stringWidth(s), fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent());
    }
    
    static {
        NFRegion.r = null;
    }
}
