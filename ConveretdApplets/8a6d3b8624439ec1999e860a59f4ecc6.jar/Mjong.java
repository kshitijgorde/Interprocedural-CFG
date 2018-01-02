import java.awt.image.RGBImageFilter;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Shape;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.net.URL;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.util.Stack;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mjong extends Applet implements Runnable
{
    static final int FACEW = 32;
    static final int FACEH = 42;
    private int Code;
    private boolean ConstantValue;
    private boolean repaint;
    private Thread contains;
    private boolean Z;
    private Image createImage;
    private Graphics brighter;
    private Image setCursor;
    private Image darker;
    private Image InnerClasses;
    private Image Point4D;
    private Image getX;
    private Button create;
    private Button isPopupTrigger;
    private Button getGraphics;
    private Image getFont;
    private Rectangle Serif;
    private long getSize;
    private long getFontMetrics;
    private int setFont;
    private int getHeight;
    private int black;
    private int width;
    private int height;
    private short[][][] setColor;
    private Stack stringWidth;
    private Point4D drawString;
    private Point4D white;
    private Point4D I;
    private Point4D drawImage;
    private Color copy;
    private Color v;
    private Color getY;
    private boolean[] x;
    private int y;
    private long z;
    private Rectangle clipRect;
    private char[] dispose;
    private char[] union;
    private Font toString;
    private FontMetrics append;
    private Image J;
    private Image fillRect;
    private Graphics getMaxDescent;
    private Rectangle game;
    private boolean getParameter;
    private Cursor nextToken;
    private boolean trim;
    private Cursor parseInt;
    private Cursor parseLong;
    
    private void valueOf(final int n, final int n2) {
        if (this.clipRect.contains(n, n2)) {
            if (!this.trim) {
                if (this.parseInt == null) {
                    this.parseInt = new Cursor(12);
                }
                this.setCursor(this.parseInt);
                this.trim = true;
            }
        }
        else if (this.trim) {
            if (this.parseLong == null) {
                this.parseLong = new Cursor(0);
            }
            this.setCursor(this.parseLong);
            this.trim = false;
        }
    }
    
    private void isPlain(final Point4D i, final Point4D drawImage) {
        final Point4D j = this.I;
        this.I = i;
        this.updateCell(j);
        this.updateCell(i);
        final Point4D drawImage2 = this.drawImage;
        this.drawImage = drawImage;
        this.updateCell(drawImage2);
        this.updateCell(drawImage);
    }
    
    private void getName(final String[] array) {
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        final Font font = graphics.getFont();
        final Font font2 = new Font("Serif", 1, font.getSize() * 3);
        final FontMetrics fontMetrics = graphics.getFontMetrics(font2);
        graphics.setFont(font2);
        final int n = 270;
        final int n2 = 200 - (fontMetrics.getHeight() + 10) * array.length / 2;
        graphics.setColor(Color.black);
        for (int i = 0; i < array.length; ++i) {
            graphics.drawString(array[i], n - fontMetrics.stringWidth(array[i]) / 2 + 1, n2 + 1 + (fontMetrics.getHeight() + 10) * i);
        }
        graphics.setColor(Color.white);
        for (int j = 0; j < array.length; ++j) {
            graphics.drawString(array[j], n - fontMetrics.stringWidth(array[j]) / 2, n2 + (fontMetrics.getHeight() + 10) * j);
        }
        graphics.setFont(font);
    }
    
    private boolean getMaxAscent() {
        if (this.black <= 0) {
            return false;
        }
        Point4D copy;
        if (this.I != null) {
            copy = this.I.copy();
        }
        else {
            copy = new Point4D(1, 1, 1, 1);
        }
        final Point4D point4D = (this.drawImage != null) ? this.drawImage.copy() : copy.copy();
        while (true) {
            final Point4D point4D2 = point4D;
            ++point4D2.v;
            if ((point4D.v - 1) / 4 != (copy.v - 1) / 4) {
                final Point4D point4D3 = copy;
                ++point4D3.v;
                if ((copy.v - 1) % 4 == 3) {
                    final Point4D point4D4 = copy;
                    ++point4D4.v;
                }
                if (copy.v > 143) {
                    copy.v = 1;
                }
                point4D.v = copy.v;
            }
            else {
                boolean b = false;
                int z = 6;
            Label_0230:
                do {
                    int x = 2;
                Block_8:
                    do {
                        int y = 1;
                        while (this.setColor[z][x][y] != copy.v) {
                            if (++y >= 18) {
                                continue Block_8;
                            }
                        }
                        copy.x = x;
                        copy.y = y;
                        copy.z = z;
                        b = true;
                        break Label_0230;
                    } while (++x < 33);
                } while (--z > 0);
                if (!b || this.c(copy)) {
                    continue;
                }
                boolean b2 = false;
                int z2 = 6;
            Label_0324:
                do {
                    int x2 = 2;
                Block_13:
                    do {
                        int y2 = 1;
                        while (this.setColor[z2][x2][y2] != point4D.v) {
                            if (++y2 >= 18) {
                                continue Block_13;
                            }
                        }
                        point4D.x = x2;
                        point4D.y = y2;
                        point4D.z = z2;
                        b2 = true;
                        break Label_0324;
                    } while (++x2 < 33);
                } while (--z2 > 0);
                if (b2 && !this.c(point4D)) {
                    break;
                }
                continue;
            }
        }
        this.isPlain(copy, point4D);
        return true;
    }
    
    private int getDescent(final int n, final int n2, final Point4D point4D) {
        if (n < -10 || n2 < 0 || n > 551 || n2 > 401) {
            return 0;
        }
        int z = 6;
        do {
            final int n3 = (n + 10 + 2 * z) / 16;
            final int n4 = (n2 + 5 * z) / 21;
            if (n3 > 1 && n3 < 33 && n4 > 0 && n4 < 17) {
                for (int i = n3 - 1; i < n3 + 1; ++i) {
                    for (int j = n4 - 1; j < n4 + 1; ++j) {
                        if (this.setColor[z][i][j] > 0) {
                            point4D.x = i;
                            point4D.y = j;
                            point4D.z = z;
                            return point4D.v = this.setColor[z][i][j];
                        }
                    }
                }
            }
        } while (--z > 0);
        return 0;
    }
    
    public String getState() {
        return Integer.toString(this.setFont) + ',' + Long.toString(this.getFontMetrics);
    }
    
    private Rectangle drawLine(final Rectangle rectangle, final int n, final int n2, final int n3) {
        final Rectangle s;
        final Rectangle rectangle2 = s = this.S(rectangle, n, n2, n3);
        s.x += 5;
        final Rectangle rectangle3 = rectangle2;
        rectangle3.y += 9;
        return rectangle2;
    }
    
    private void getCursor() {
        final String parameter = this.getParameter("game");
        if (parameter == null) {
            return;
        }
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
            this.setFont = Integer.parseInt(stringTokenizer.nextToken().trim());
            this.getFontMetrics = Long.parseLong(stringTokenizer.nextToken().trim());
            if (this.getFontMetrics < 0L) {
                this.getFontMetrics = 0L;
            }
        }
        catch (NoSuchElementException ex) {}
        catch (NumberFormatException ex2) {}
        catch (NullPointerException ex3) {}
    }
    
    public boolean isDoubleBuffered() {
        return true;
    }
    
    private void A(final Graphics graphics, int x, final int n, int n2) {
        if (graphics == null) {
            return;
        }
        Font font = graphics.getFont();
        if (!font.isPlain()) {
            font = new Font(font.getName(), 0, font.getSize());
            graphics.setFont(font);
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        final int maxAscent = fontMetrics.getMaxAscent();
        graphics.setColor(Color.black);
        final String s = "presented by";
        graphics.drawString(s, x + (n - x - fontMetrics.stringWidth(s)) / 2, n2);
        n2 += fontMetrics.getHeight();
        graphics.setColor(new Color(-16777088));
        final String s2 = "www.by-art.com";
        final int stringWidth = fontMetrics.stringWidth(s2);
        x += (n - x - stringWidth) / 2;
        graphics.drawString(s2, x, n2);
        final int n3 = n2 + fontMetrics.getDescent() - 1;
        graphics.drawLine(x + 1, n3, x + stringWidth, n3);
        this.clipRect.x = x;
        this.clipRect.width = stringWidth;
        this.clipRect.y = n2 - maxAscent;
        this.clipRect.height = maxAscent + fontMetrics.getMaxDescent();
    }
    
    private void B() {
        if (this.nextToken == null) {
            this.nextToken = new Cursor(3);
        }
        final Cursor cursor = this.getCursor();
        this.setCursor(this.nextToken);
        this.G();
        this.T();
        this.E(this.brighter);
        this.R();
        this.repaint();
        this.e();
        this.setCursor(cursor);
    }
    
    private void updateCell(final int n, final int n2, final int n3) {
        final int x;
        final int y;
        final int width;
        final int height;
        synchronized (this.brighter) {
            final Graphics create = this.brighter.create();
            final Rectangle union = this.L(null, n, n2, n3).union(this.drawLine(null, n, n2, n3));
            x = union.x;
            y = union.y;
            width = union.width;
            height = union.height;
            create.clipRect(x, y, width, height);
            create.drawImage(this.InnerClasses, 0, 0, this);
            int n4 = 0;
            do {
                if (n4 > 0) {
                    for (int i = n3 - 2; i <= n3 + 2; ++i) {
                        for (int j = n2 + 2; j >= n2 - 2; --j) {
                            this.b(create, n4, j, i);
                        }
                    }
                }
                for (int k = n2 + 2; k >= n2 - 2; --k) {
                    for (int l = n3 - 2; l <= n3 + 2; ++l) {
                        this.H(create, n4, k, l);
                    }
                }
                if (n2 >= 15 && n2 <= 17 && n3 == 5) {
                    this.H(create, 5, 15, 8);
                }
            } while (++n4 < 7);
            create.dispose();
        }
        // monitorexit(this.brighter)
        this.C(x, y, width, height);
    }
    
    private void updateCell(final Point4D point4D) {
        if (point4D != null) {
            this.updateCell(point4D.z, point4D.x, point4D.y);
        }
    }
    
    Image makeTheButton(final int n, final int n2, final String s, final Color color, final boolean b) {
        final Color darker = color.darker();
        final Color brighter = color.brighter();
        final Image image = this.createImage(n, n2);
        final Graphics graphics = image.getGraphics();
        final FontMetrics fontMetrics = graphics.getFontMetrics(graphics.getFont());
        graphics.setColor(color);
        graphics.fillRect(0, 0, n, n2);
        graphics.setColor(Color.black);
        final int n3 = b ? 2 : 0;
        graphics.drawString(s, (n - fontMetrics.stringWidth(s)) / 2 + n3, (n2 - fontMetrics.getHeight()) / 2 + fontMetrics.getMaxAscent() + n3);
        graphics.setColor(b ? darker : brighter);
        graphics.drawLine(0, n2 - 1, 0, 0);
        graphics.drawLine(0, 0, n - 1, 0);
        graphics.setColor(b ? brighter : darker);
        graphics.drawLine(0, n2 - 1, n - 1, n2 - 1);
        graphics.drawLine(n - 1, n2 - 1, n - 1, 1);
        return image;
    }
    
    private void C(final int n, final int n2, final int n3, final int n4) {
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        final Graphics create = graphics.create();
        create.clipRect(n, n2, n3, n4);
        create.drawImage(this.createImage, 0, 0, this);
        create.dispose();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            return;
        }
        this.valueOf(mouseEvent.getX(), mouseEvent.getY());
        super.processMouseEvent(mouseEvent);
    }
    
    public void start() {
        if (this.contains == null) {
            this.Z = false;
            (this.contains = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "The Mahjong solitaire v.1.01b, Author VSM. Copyright 2000 G.U.E.S.";
    }
    
    private void D() {
        if (this.getHeight > 0) {
            this.getName(new String[] { "No more moves.", "Try again." });
            return;
        }
        if (this.getSize < this.getFontMetrics || this.getFontMetrics == 0L) {
            this.getFontMetrics = this.getSize;
            this.getName(new String[] { "Congratulations!", "New best time!" });
            return;
        }
        this.getName(new String[] { "Well done.", "Play again." });
    }
    
    private void E(final Graphics graphics) {
        final int x = this.Serif.x;
        final int n = x + 96;
        final int n2 = 62;
        this.O(graphics, "games:", String.valueOf(this.setFont), x, n, n2);
        final long getFontMetrics = this.getFontMetrics;
        this.union[4] = this.Q((int)getFontMetrics % 10);
        this.union[3] = this.Q((int)getFontMetrics / 10 % 6);
        this.union[1] = this.Q((int)getFontMetrics / 60 % 10);
        this.union[0] = this.Q((int)getFontMetrics / 600 % 6);
        this.O(graphics, "best:", new String(this.union), x, n, n2 + 24 - 4);
        this.A(graphics, x, n, n2 + 72);
    }
    
    private void F(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final short[] array = { 0, 56, 68, 146, 170, 130, 68, 56, 0, 0, 12, 48, 12, 48, 12, 0, 12, 48, 12, 48, 12, 0, 12, 48, 12, 48, 12, 0, 32, 0, 63, 36, 36, 24, 0, 156, 96, 28, 0, 8, 8, 0, 20, 36, 56, 0, 60, 4, 0, 30, 36, 0, 32, 0, 24, 36, 36, 0, 24, 36, 36, 24, 0, 60, 4, 60, 4, 56, 0 };
        final int length = array.length;
        final int n5 = 8;
        final int[] array2 = new int[length * n5];
        for (int i = 0; i < length; ++i) {
            int n6 = array[i];
            for (int j = 0; j < n5; ++j) {
                array2[j + (length - i - 1) * n5] = (((n6 & 0x1) == 0x0) ? n4 : n3);
                n6 >>= 1;
            }
        }
        this.brighter.drawImage(this.createImage(new MemoryImageSource(n5, length, array2, 0, n5)), n, n2 - length, this);
    }
    
    private void G() {
        this.stringWidth.removeAllElements();
        this.getSize = 0L;
        this.z = System.currentTimeMillis();
        this.drawString = null;
        this.white = null;
        this.I = null;
        this.drawImage = null;
        int n = 0;
        do {
            int n2 = 0;
            do {
                int n3 = 0;
                do {
                    this.setColor[n][n2][n3] = 0;
                } while (++n3 < 18);
            } while (++n2 < 34);
        } while (++n < 7);
        this.y = 0;
        int n4 = 0;
        do {
            this.x[n4] = false;
        } while (++n4 < 145);
        final int n5 = 1;
        final int n6 = 1;
        int i = 4;
        do {
            this.setColor[n5][i][n6] = this.V();
            i += 2;
        } while (i < 28);
        final int n7 = 3;
        int j = 8;
        do {
            this.setColor[n5][j][n7] = this.V();
            j += 2;
        } while (j < 24);
        final int n8 = 5;
        int k = 6;
        do {
            this.setColor[n5][k][n8] = this.V();
            k += 2;
        } while (k < 26);
        final int n9 = 7;
        int l = 4;
        do {
            this.setColor[n5][l][n9] = this.V();
            l += 2;
        } while (l < 28);
        final int n10 = 8;
        this.setColor[n5][2][n10] = this.V();
        this.setColor[n5][28][n10] = this.V();
        this.setColor[n5][30][n10] = this.V();
        final int n11 = 9;
        int n12 = 4;
        do {
            this.setColor[n5][n12][n11] = this.V();
            n12 += 2;
        } while (n12 < 28);
        final int n13 = 11;
        int n14 = 6;
        do {
            this.setColor[n5][n14][n13] = this.V();
            n14 += 2;
        } while (n14 < 26);
        final int n15 = 13;
        int n16 = 8;
        do {
            this.setColor[n5][n16][n15] = this.V();
            n16 += 2;
        } while (n16 < 24);
        final int n17 = 15;
        int n18 = 4;
        do {
            this.setColor[n5][n18][n17] = this.V();
            n18 += 2;
        } while (n18 < 28);
        final int n19 = 2;
        int n20 = 3;
        do {
            int n21 = 10;
            do {
                this.setColor[n19][n21][n20] = this.V();
                n21 += 2;
            } while (n21 < 22);
            n20 += 2;
        } while (n20 < 15);
        final int n22 = 3;
        int n23 = 5;
        do {
            int n24 = 12;
            do {
                this.setColor[n22][n24][n23] = this.V();
                n24 += 2;
            } while (n24 < 20);
            n23 += 2;
        } while (n23 < 13);
        final int n25 = 4;
        int n26 = 7;
        do {
            int n27 = 14;
            do {
                this.setColor[n25][n27][n26] = this.V();
                n27 += 2;
            } while (n27 < 18);
            n26 += 2;
        } while (n26 < 11);
        this.setColor[5][15][8] = this.V();
        this.U();
        this.getHeight = 144;
    }
    
    private void H(final Graphics graphics, final int n, final int n2, final int n3) {
        if (n <= 0 || n2 < 0 || n3 < 0) {
            return;
        }
        if (n >= 7 || n2 > 34 || n3 >= 18) {
            return;
        }
        final short n4 = this.setColor[n][n2][n3];
        if (n4 == 0 && n > 0) {
            return;
        }
        if (n2 < 32 && n3 < 16 && this.setColor[n + 1][n2 + 2][n3] > 0 && this.setColor[n + 1][n2][n3] > 0 && this.setColor[n + 1][n2 + 2][n3 + 2] > 0 && this.setColor[n + 1][n2][n3 + 2] > 0) {
            return;
        }
        boolean b = false;
        for (int i = n2 - 2; i <= n2 + 1; ++i) {
            for (int j = n3 + 1; j >= n3 - 2; --j) {
                if (i >= 0 && i < 34 && j >= 0 && j < 18 && this.setColor[n + 1][i][j] > 0) {
                    b = true;
                    break;
                }
            }
        }
        if (n == 0 && !b) {
            return;
        }
        final Rectangle l = this.L(null, n, n2, n3);
        final Graphics graphics2 = this.Point4D.getGraphics();
        if (n != 0) {
            graphics2.drawImage(this.setCursor, 0, 0, this);
        }
        if (n > 0) {
            final Graphics create = graphics2.create(2, 1, 30, 40);
            final short n5 = (short)(n4 - 1);
            final short n6 = (short)(n5 / 4);
            short n7;
            int n8;
            if (n6 < 27) {
                n7 = (short)(n6 % 9 * 30);
                n8 = n6 / 9 * 40;
            }
            else if (n6 == 27) {
                n7 = (short)((n5 - n6 * 4) * 30);
                n8 = 120;
            }
            else if (n6 < 32) {
                n7 = (short)((4 + (n6 - 28)) * 30);
                n8 = 120;
            }
            else if (n6 == 32) {
                n7 = (short)((n5 - n6 * 4) * 30);
                n8 = 160;
            }
            else {
                n7 = (short)((4 + (n6 - 33)) * 30);
                n8 = 160;
            }
            create.drawImage(this.darker, -n7, -n8, this);
            if (this.drawString != null && this.drawString.equals(n2, n3, n)) {
                create.setColor(Color.black);
                create.setXORMode(Color.white);
                create.fillRect(0, 0, 30, 41);
                create.setPaintMode();
            }
            if ((this.I != null && this.I.equals(n2, n3, n)) || (this.drawImage != null && this.drawImage.equals(n2, n3, n))) {
                create.setColor(this.getY);
                create.drawRect(0, 0, 29, 39);
            }
            create.dispose();
        }
        graphics2.dispose();
        graphics.drawImage(this.Point4D, l.x, l.y, this);
    }
    
    private boolean K(final int n, final int n2) {
        if (this.Code == 0) {
            return true;
        }
        try {
            if (n2 < 352 && n2 > 317 && n < 7) {
                this.a();
                return true;
            }
            if (this.clipRect.contains(n, n2)) {
                this.a();
                return true;
            }
            if (this.create.contains(n, n2)) {
                this.create.pressed = true;
                this.create.redraw();
                return true;
            }
            if (this.isPopupTrigger.contains(n, n2)) {
                this.isPopupTrigger.pressed = true;
                this.isPopupTrigger.redraw();
                return true;
            }
            if (this.getGraphics.contains(n, n2)) {
                this.getGraphics.pressed = true;
                this.getGraphics.redraw();
                return true;
            }
            if (this.Code != 1) {
                return true;
            }
            final Point4D white = new Point4D();
            if (this.getDescent(n, n2, white) > 0) {
                if (this.c(white)) {
                    return true;
                }
                if (this.drawString == null) {
                    this.Y(white);
                    return true;
                }
                if (this.drawString.equals(white)) {
                    this.Y(null);
                    return true;
                }
                if ((this.drawString.v - 1) / 4 == (white.v - 1) / 4) {
                    this.white = white;
                    this.d(this.drawString, white);
                    this.isPlain(null, null);
                    this.Code = 2;
                    return true;
                }
                this.Y(white);
                return true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
    private Rectangle L(final Rectangle rectangle, final int n, final int n2, final int n3) {
        final Rectangle s;
        final Rectangle rectangle2 = s = this.S(rectangle, n, n2, n3);
        s.width += 3;
        final Rectangle rectangle3 = rectangle2;
        rectangle3.height += 4;
        final Rectangle rectangle4 = rectangle2;
        rectangle4.x -= 3;
        final Rectangle rectangle5 = rectangle2;
        --rectangle5.y;
        return rectangle2;
    }
    
    private int M() {
        int n = 0;
        if (!this.prepareImage(this.setCursor, -1, -1, this)) {
            ++n;
        }
        if (!this.prepareImage(this.darker, -1, -1, this)) {
            ++n;
        }
        if (!this.prepareImage(this.InnerClasses, -1, -1, this)) {
            ++n;
        }
        return n;
    }
    
    public void paint(final Graphics graphics) {
        if (this.brighter == null) {
            return;
        }
        synchronized (this.brighter) {
            if (!this.ConstantValue) {
                this.brighter.setColor(this.getY);
                this.brighter.drawString("Loading, please wait...", 10, 20);
            }
            else if (this.getParameter) {
                this.getParameter = false;
            }
            graphics.drawImage(this.createImage, 0, 0, this);
        }
        // monitorexit(this.brighter)
    }
    
    private void N() {
        this.Code = 2;
        this.Y(null);
        this.isPlain(null, null);
        if (!this.stringWidth.empty()) {
            final Point4D point4D = this.stringWidth.pop();
            try {
                this.setColor[point4D.z][point4D.x][point4D.y] = (short)point4D.v;
                this.updateCell(point4D);
            }
            catch (Exception ex) {}
        }
        if (!this.stringWidth.empty()) {
            final Point4D point4D2 = this.stringWidth.pop();
            try {
                this.setColor[point4D2.z][point4D2.x][point4D2.y] = (short)point4D2.v;
                this.updateCell(point4D2);
            }
            catch (Exception ex2) {}
        }
        this.U();
        this.R();
        this.Code = 1;
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        final int id = mouseEvent.getID();
        if (!mouseEvent.isPopupTrigger()) {
            if (id == 501) {
                if (this.K(mouseEvent.getX(), mouseEvent.getY())) {
                    return;
                }
            }
            else if (id == 502 && this.W(mouseEvent.getX(), mouseEvent.getY())) {
                return;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public Mjong() {
        this.Code = 0;
        this.ConstantValue = false;
        this.repaint = false;
        this.contains = null;
        this.Z = false;
        this.createImage = null;
        this.brighter = null;
        this.setCursor = null;
        this.darker = null;
        this.InnerClasses = null;
        this.Point4D = null;
        this.getX = null;
        this.create = null;
        this.isPopupTrigger = null;
        this.getGraphics = null;
        this.getFont = null;
        this.Serif = null;
        this.getSize = 0L;
        this.getFontMetrics = 0L;
        this.setFont = 0;
        this.getHeight = 0;
        this.black = 0;
        this.setColor = new short[7][34][18];
        this.stringWidth = null;
        this.drawString = null;
        this.white = null;
        this.I = null;
        this.drawImage = null;
        this.copy = null;
        this.v = null;
        this.getY = null;
        this.x = new boolean[145];
        this.y = 0;
        this.z = 0L;
        this.clipRect = new Rectangle(0, 0, 0, 0);
        this.dispose = new char[] { '0', '0', ':', '0', '0' };
        this.union = new char[] { '0', '0', ':', '0', '0' };
        this.toString = null;
        this.append = null;
        this.J = null;
        this.fillRect = null;
        this.getMaxDescent = null;
        this.game = null;
        this.getParameter = true;
        this.nextToken = null;
        this.trim = false;
        this.parseInt = null;
        this.parseLong = null;
    }
    
    public void destroy() {
        this.Z = true;
        this.contains = null;
    }
    
    private void O(final Graphics graphics, final String s, final String s2, int n, int n2, final int n3) {
        if (graphics == null) {
            return;
        }
        Font font = graphics.getFont();
        if (!font.isPlain()) {
            font = new Font(font.getName(), 0, font.getSize());
            graphics.setFont(font);
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        graphics.setColor(this.v);
        final int maxAscent = fontMetrics.getMaxAscent();
        graphics.fillRect(n, n3 - 2 - maxAscent, n2 - n + 1, maxAscent + fontMetrics.getMaxDescent() + 5);
        n += 8;
        n2 -= 8;
        graphics.setColor(Color.black);
        graphics.drawString(s, n, n3);
        graphics.drawString(s2, n2 - fontMetrics.stringWidth(s2), n3);
    }
    
    private int P(final int n, final int n2) {
        return (int)(Math.random() * (n2 - n + 1)) + n;
    }
    
    private char Q(final int n) {
        switch (n) {
            case 0: {
                return '0';
            }
            case 1: {
                return '1';
            }
            case 2: {
                return '2';
            }
            case 3: {
                return '3';
            }
            case 4: {
                return '4';
            }
            case 5: {
                return '5';
            }
            case 6: {
                return '6';
            }
            case 7: {
                return '7';
            }
            case 8: {
                return '8';
            }
            case 9: {
                return '9';
            }
            default: {
                return '0';
            }
        }
    }
    
    private void R() {
        final int x = this.Serif.x;
        final int n = x + 96;
        final int n2 = 62;
        final String value = String.valueOf(this.black);
        this.O(this.brighter, "free tiles:", value, x, n, n2 + 48 - 8);
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.O(graphics, "free tiles:", value, x, n, n2 + 48 - 8);
            graphics.dispose();
        }
    }
    
    private Rectangle S(final Rectangle rectangle, final int n, final int n2, final int n3) {
        final Rectangle rectangle2 = (rectangle != null) ? rectangle : new Rectangle();
        rectangle2.x = -10 + 16 * n2 - 2 * n;
        rectangle2.y = 21 * n3 - 5 * n;
        rectangle2.width = 32;
        rectangle2.height = 42;
        return rectangle2;
    }
    
    private void T() {
        synchronized (this.brighter) {
            final Graphics create = this.brighter.create(15, 15, 495, 386);
            create.drawImage(this.InnerClasses, -15, -15, this);
            create.dispose();
            int n = 0;
            do {
                if (n > 0 && n < 6) {
                    int n2 = 33;
                    do {
                        int n3 = 1;
                        do {
                            this.b(this.brighter, n, n2, n3);
                        } while (++n3 < 17);
                    } while (--n2 > 0);
                }
                int n4 = 33;
                do {
                    int n5 = 1;
                    do {
                        this.H(this.brighter, n, n4, n5);
                    } while (++n5 < 17);
                } while (--n4 > 0);
            } while (++n < 7);
        }
        // monitorexit(this.brighter)
    }
    
    private int U() {
        int black = 0;
        final Point4D point4D = new Point4D();
        final Point4D point4D2 = new Point4D();
        int z = 1;
        do {
            int x = 2;
        Label_0219_Outer:
            do {
                int y = 1;
            Label_0219:
                while (true) {
                    do {
                        point4D.x = x;
                        point4D.y = y;
                        point4D.z = z;
                        point4D.v = this.setColor[z][x][y];
                        if (point4D.v != 0 && !this.c(point4D)) {
                            int z2 = 1;
                            do {
                                int x2 = 2;
                                do {
                                    int y2 = 1;
                                    do {
                                        if (x2 != x || y2 != y || z2 != z) {
                                            point4D2.x = x2;
                                            point4D2.y = y2;
                                            point4D2.z = z2;
                                            point4D2.v = this.setColor[z2][x2][y2];
                                            if (point4D2.v != 0 && !this.c(point4D2) && (point4D2.v - 1) / 4 == (point4D.v - 1) / 4) {
                                                ++black;
                                                continue Label_0219;
                                            }
                                            continue Label_0219_Outer;
                                        }
                                    } while (++y2 < 17);
                                } while (++x2 < 32);
                            } while (++z2 < 7);
                        }
                    } while (++y < 17);
                    break;
                }
            } while (++x < 32);
        } while (++z < 7);
        return this.black = black;
    }
    
    private short V() {
        int p = this.P(1, 144);
        if (this.y >= 144) {
            System.out.println("no more stones to deal");
            return 0;
        }
        while (this.x[p]) {
            if (++p > 144) {
                p = 1;
            }
        }
        this.x[p] = true;
        ++this.y;
        return (short)p;
    }
    
    private boolean W(final int n, final int n2) {
        if (this.Code == 0) {
            return true;
        }
        try {
            if (this.create.pressed) {
                this.create.pressed = false;
                this.create.redraw();
                if (this.getSize > 10L) {
                    ++this.setFont;
                }
                this.B();
                this.Code = 1;
            }
            else if (this.isPopupTrigger.pressed) {
                this.isPopupTrigger.pressed = false;
                this.isPopupTrigger.redraw();
                if (this.Code == 1) {
                    this.N();
                }
            }
            else if (this.getGraphics.pressed) {
                this.getGraphics.pressed = false;
                this.getGraphics.redraw();
                if (this.Code == 1) {
                    this.Code = -1;
                    this.getMaxAscent();
                    this.Code = 1;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
    private int X() {
        if (this.drawString != null) {
            this.drawString.v = 0;
            this.setColor[this.drawString.z][this.drawString.x][this.drawString.y] = 0;
            this.updateCell(this.drawString);
            this.drawString = null;
        }
        if (this.white != null) {
            this.white.v = 0;
            this.setColor[this.white.z][this.white.x][this.white.y] = 0;
            this.updateCell(this.white);
            this.white = null;
        }
        this.getHeight -= 2;
        return 0;
    }
    
    private void Y(final Point4D drawString) {
        final Point4D drawString2 = this.drawString;
        this.drawString = drawString;
        this.updateCell(drawString2);
        this.updateCell(drawString);
    }
    
    private void a() {
        try {
            this.getAppletContext().showDocument(new URL("http://www.by-art.com"), "_top");
        }
        catch (Exception ex) {}
    }
    
    private void b(final Graphics graphics, final int n, final int n2, final int n3) {
        if (n < 0 || n2 < 0 || n3 < 0) {
            return;
        }
        if (n >= 7 || n2 > 34 || n3 >= 18) {
            return;
        }
        if (this.setColor[n][n2][n3] == 0) {
            return;
        }
        if (n3 < 16 && n2 < 32 && this.setColor[n][n2 + 2][n3] > 0 && this.setColor[n][n2][n3 + 2] > 0 && this.setColor[n][n2 + 2][n3 + 2] > 0) {
            return;
        }
        if (this.fillRect == null) {
            this.fillRect = this.createImage(32, 42);
        }
        if (this.getMaxDescent == null) {
            this.getMaxDescent = this.fillRect.getGraphics();
        }
        this.game = this.drawLine(this.game, n, n2, n3);
        this.getMaxDescent.drawImage(this.createImage, -this.game.x, -this.game.y, this);
        if (this.J == null) {
            this.J = this.createImage(new FilteredImageSource(this.fillRect.getSource(), new ShadowFilter()));
        }
        else {
            this.J.flush();
        }
        if (n == 1) {
            graphics.drawImage(this.J, this.game.x, this.game.y, this);
            return;
        }
        final boolean b = n2 + 2 < 34 && this.setColor[n - 1][n2 + 2][n3] > 0;
        final boolean b2 = n3 + 2 < 18 && this.setColor[n - 1][n2][n3 + 2] > 0;
        final boolean b3 = n2 + 2 < 34 && n3 + 2 < 18 && this.setColor[n - 1][n2 + 2][n3 + 2] > 0;
        if ((b && b2 && b3) || this.setColor[n - 1][n2 + 1][n3 + 1] > 0) {
            graphics.drawImage(this.J, this.game.x, this.game.y, this);
            return;
        }
        final Shape clip = graphics.getClip();
        final Rectangle intersection = this.S(null, n - 1, n2, n3).intersection(this.game);
        graphics.clipRect(intersection.x, intersection.y, b ? 32 : intersection.width, intersection.height);
        graphics.drawImage(this.J, this.game.x, this.game.y, this);
        if (b2 || b3) {
            final Rectangle intersection2 = this.S(intersection, n - 1, n2, n3 + 2).intersection(this.game);
            int n4;
            int width;
            if (b2 && b3) {
                n4 = this.game.x;
                width = 32;
            }
            else if (b2) {
                n4 = this.game.x;
                width = intersection2.width;
            }
            else {
                n4 = this.game.x + intersection2.width;
                width = 32 - intersection2.width;
            }
            graphics.setClip(clip);
            graphics.clipRect(n4, intersection2.y, width, intersection2.height);
            graphics.drawImage(this.J, this.game.x, this.game.y, this);
        }
        graphics.setClip(clip);
    }
    
    public void run() {
        while (!this.Z) {
            final int n = 100;
            Label_0312: {
                switch (this.Code) {
                    case 0: {
                        if (this.M() == 0) {
                            this.Code = -1;
                            this.getCursor();
                            this.ConstantValue = true;
                            this.brighter.drawImage(this.InnerClasses, 0, 0, this);
                            final Image image = this.createImage(106, this.height);
                            image.getGraphics().drawImage(this.InnerClasses, -511, 0, this);
                            this.brighter.drawImage(this.createImage(new FilteredImageSource(image.getSource(), new StainedGlassFilter(this.v, 70))), 511, 0, this);
                            this.create.redraw(this.brighter);
                            this.isPopupTrigger.redraw(this.brighter);
                            this.getGraphics.redraw(this.brighter);
                            this.brighter.drawImage(this.getFont, this.Serif.x, this.Serif.y, this);
                            this.F(this.brighter, 0, this.height - 5, -16777088, this.copy.getRGB());
                            this.repaint();
                            this.enableEvents(16L);
                            this.enableEvents(32L);
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if (this.X() != 0) {
                            break Label_0312;
                        }
                        this.U();
                        this.R();
                        if (this.black == 0) {
                            this.Code = -1;
                            this.D();
                            break;
                        }
                        this.Code = 1;
                        break Label_0312;
                    }
                    case 1: {
                        this.e();
                        break;
                    }
                }
            }
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void init() {
        System.gc();
        super.init();
        this.Code = 0;
        this.copy = new Color(215, 210, 180);
        this.v = new Color(238, 232, 202);
        this.getY = new Color(-2139655067);
        final Dimension size = this.getSize();
        this.width = size.width;
        this.height = size.height;
        this.createImage = this.createImage(this.width, this.height);
        this.brighter = this.createImage.getGraphics();
        int n = 0;
        do {
            int n2 = 0;
            do {
                int n3 = 0;
                do {
                    this.setColor[n][n2][n3] = 0;
                } while (++n3 < 18);
            } while (++n2 < 34);
        } while (++n < 7);
        this.stringWidth = new Stack();
        final URL codeBase = this.getCodeBase();
        this.setCursor = this.getImage(codeBase, "face.gif");
        this.darker = this.getImage(codeBase, "tiles.gif");
        this.InnerClasses = this.getImage(codeBase, "wall.jpg");
        this.Point4D = this.createImage(33, 44);
        this.getX = this.createImage(32, 42);
        this.create = new Button(this, 516, 328, 96, 24, "New game", this.copy);
        this.isPopupTrigger = new Button(this, 516, 289, 96, 24, "Undo", this.copy);
        this.getGraphics = new Button(this, 516, 260, 96, 24, "Hint", this.copy);
        this.getFont = this.makeTheButton(96, 24, "00:00", this.copy, false);
        this.Serif = new Rectangle(516, 15, 96, 24);
        this.brighter.setColor(this.copy);
        this.brighter.fillRect(0, 0, this.width, this.height);
        this.brighter.setColor(this.v);
        this.brighter.fillRect(511, 0, 106, this.height);
        this.create.redraw(this.brighter);
        this.isPopupTrigger.redraw(this.brighter);
        this.getGraphics.redraw(this.brighter);
        this.brighter.drawImage(this.getFont, this.Serif.x, this.Serif.y, this);
        this.F(this.brighter, 0, this.height - 5, -16777088, this.copy.getRGB());
    }
    
    private boolean c(final Point4D point4D) {
        if (point4D == null || point4D.v == 0) {
            return false;
        }
        final int x = point4D.x;
        final int y = point4D.y;
        final int z = point4D.z;
        final boolean b = this.setColor[z][x - 2][y] > 0 || this.setColor[z][x - 2][y - 1] > 0 || this.setColor[z][x - 2][y + 1] > 0;
        final boolean b2 = this.setColor[z][x + 2][y] > 0 || this.setColor[z][x + 2][y - 1] > 0 || this.setColor[z][x + 2][y + 1] > 0;
        if (b && b2) {
            return true;
        }
        for (int i = point4D.x - 1; i <= point4D.x + 1; ++i) {
            for (int j = point4D.y - 1; j <= point4D.y + 1; ++j) {
                if (this.setColor[point4D.z + 1][i][j] > 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void d(final Point4D point4D, final Point4D point4D2) {
        if (point4D != null) {
            this.stringWidth.push(point4D.copy());
        }
        else {
            this.stringWidth.push(new Point4D());
        }
        if (point4D2 != null) {
            this.stringWidth.push(point4D2.copy());
            return;
        }
        this.stringWidth.push(new Point4D());
    }
    
    private void e() {
        final long getSize = (System.currentTimeMillis() - this.z) / 1000L;
        if (this.getSize == getSize) {
            return;
        }
        this.getSize = getSize;
        this.dispose[4] = this.Q((int)getSize % 10);
        this.dispose[3] = this.Q((int)getSize / 10 % 6);
        this.dispose[1] = this.Q((int)getSize / 60 % 10);
        this.dispose[0] = this.Q((int)getSize / 600 % 6);
        final Graphics graphics = this.getFont.getGraphics();
        if (graphics == null) {
            return;
        }
        graphics.setColor(this.v);
        graphics.fillRect(1, 1, 94, 22);
        if (this.toString == null) {
            this.toString = graphics.getFont();
            this.toString = new Font(this.toString.getName(), 1, this.toString.getSize());
        }
        graphics.setFont(this.toString);
        if (this.append == null) {
            this.append = graphics.getFontMetrics(this.toString);
        }
        graphics.setColor(Color.black);
        graphics.drawChars(this.dispose, 0, this.dispose.length, (96 - this.append.charsWidth(this.dispose, 0, this.dispose.length)) / 2, (24 - this.append.getHeight()) / 2 + this.append.getMaxAscent());
        this.brighter.drawImage(this.getFont, this.Serif.x, this.Serif.y, this);
        final Graphics graphics2 = this.getGraphics();
        if (graphics2 != null) {
            graphics2.drawImage(this.getFont, this.Serif.x, this.Serif.y, this);
            graphics2.dispose();
        }
    }
    
    class Point4D
    {
        public int x;
        public int y;
        public int z;
        public int v;
        final Mjong this$0;
        
        public Point4D(final Mjong this$0) {
            (this.this$0 = this$0).getClass();
            this.x = 0;
            this.y = 0;
            this.z = 0;
            this.v = 0;
        }
        
        public Point4D(final Mjong this$0, final int x, final int y, final int z, final int v) {
            (this.this$0 = this$0).getClass();
            this.x = x;
            this.y = y;
            this.z = z;
            this.v = v;
        }
        
        public boolean equals(final int n, final int n2, final int n3) {
            return this.x == n && this.y == n2 && this.z == n3;
        }
        
        public boolean equals(final Point4D point4D) {
            return point4D != null && this.x == point4D.x && this.y == point4D.y && this.z == point4D.z;
        }
        
        public Point4D copy() {
            return this.this$0.new Point4D(this.x, this.y, this.z, this.v);
        }
    }
    
    class Button
    {
        public boolean pressed;
        private Image[] Code;
        private int InnerClasses;
        private int Mjong;
        private int Button;
        private int dispose;
        private Component Z;
        final Mjong this$0;
        
        public void redraw(final Graphics graphics) {
            if (this.Code[0] != null && this.Code[1] != null) {
                graphics.drawImage(this.pressed ? this.Code[1] : this.Code[0], this.InnerClasses, this.Mjong, this.Z);
            }
        }
        
        public Button(final Mjong this$0, final Component z, final int innerClasses, final int mjong, final int button, final int dispose, final String s, final Color color) {
            (this.this$0 = this$0).getClass();
            this.Code = null;
            this.Z = z;
            this.pressed = false;
            this.InnerClasses = innerClasses;
            this.Mjong = mjong;
            this.Button = button;
            this.dispose = dispose;
            (this.Code = new Image[2])[0] = this$0.makeTheButton(button, dispose, s, color, false);
            this.Code[1] = this$0.makeTheButton(button, dispose, s, color, true);
        }
        
        public void redraw() {
            final Graphics graphics = this.Z.getGraphics();
            if (graphics != null) {
                this.redraw(graphics);
                graphics.dispose();
            }
        }
        
        public boolean contains(final int n, final int n2) {
            return n >= this.InnerClasses && n < this.InnerClasses + this.Button && n2 >= this.Mjong && n2 < this.Mjong + this.dispose;
        }
    }
    
    class ShadowFilter extends RGBImageFilter
    {
        int width;
        int height;
        final Mjong this$0;
        
        public int filterRGB(final int n, final int n2, final int n3) {
            int n4 = (n < this.width / 2) ? n : (this.width - 1 - n);
            int n5 = (n2 < this.height / 2) ? n2 : (this.height - 1 - n2);
            if (n4 > 5) {
                n4 = 5;
            }
            if (n5 > 5) {
                n5 = 5;
            }
            if (n4 + n5 < 1) {
                return n3;
            }
            final int n6 = 204 - n4 * n5 * 2;
            return 0xFF000000 | (n3 >>> 16 & 0xFF) * n6 >>> 8 << 16 | (n3 >>> 8 & 0xFF) * n6 >>> 8 << 8 | (n3 & 0xFF) * n6 >>> 8;
        }
        
        ShadowFilter(final Mjong this$0) {
            (this.this$0 = this$0).getClass();
        }
        
        public void setDimensions(final int width, final int height) {
            super.setDimensions(width, height);
            this.width = width;
            this.height = height;
        }
    }
    
    class StainedGlassFilter extends RGBImageFilter
    {
        private int Code;
        private int InnerClasses;
        private int Mjong;
        private int StainedGlassFilter;
        final Mjong this$0;
        
        public int filterRGB(final int n, final int n2, final int n3) {
            return 0xFF000000 | ((n3 >> 16 & 0xFF) * this.StainedGlassFilter + this.Code) / 100 << 16 | ((n3 >> 8 & 0xFF) * this.StainedGlassFilter + this.InnerClasses) / 100 << 8 | ((n3 & 0xFF) * this.StainedGlassFilter + this.Mjong) / 100;
        }
        
        public StainedGlassFilter(final Mjong this$0, final Color color, int n) {
            (this.this$0 = this$0).getClass();
            if (n > 100) {
                n = 100;
            }
            else if (n < 0) {
                n = 0;
            }
            color.getRGB();
            this.Code = color.getRed() * n;
            this.InnerClasses = color.getGreen() * n;
            this.Mjong = color.getBlue() * n;
            this.StainedGlassFilter = 100 - n;
            super.canFilterIndexColorModel = true;
        }
    }
}
