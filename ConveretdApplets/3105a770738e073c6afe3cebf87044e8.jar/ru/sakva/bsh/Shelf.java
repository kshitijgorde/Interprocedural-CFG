// 
// Decompiled by Procyon v0.5.30
// 

package ru.sakva.bsh;

import java.awt.image.DirectColorModel;
import java.awt.SystemColor;
import java.awt.event.ComponentEvent;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.image.ColorModel;
import java.util.Properties;
import java.awt.Image;
import java.awt.Color;
import java.util.Hashtable;
import java.awt.event.ComponentListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

public class Shelf extends Applet implements MouseListener, MouseMotionListener, ComponentListener
{
    protected static final int shelfH = 6;
    protected static Hashtable icons;
    public Color shelfColor;
    public Color plainColor;
    protected Book taken;
    protected int takenNum;
    int border;
    protected Book[] books;
    boolean vertical;
    Image im;
    int imU;
    int imV;
    int imW;
    int imH;
    int msU;
    int msV;
    int waitTime;
    boolean upward;
    boolean column;
    boolean turned;
    boolean boxes;
    int[] p;
    int[] q;
    Properties props;
    String encoding;
    static ColorModel dcm;
    static /* synthetic */ Class class$ru$sakva$bsh$Shelf;
    
    public void init() {
        try {
            this.encoding = System.getProperty("file.encoding");
        }
        catch (Exception ex) {}
        this.addComponentListener(this);
    }
    
    String getProp(final String s) {
        String s2;
        if ((s2 = this.getParameter(s)) == null && this.props != null) {
            s2 = this.props.getProperty(s);
        }
        return s2;
    }
    
    public void reserve(final int n) {
        this.books = new Book[n];
        for (int i = 0; i < n; this.books[i++] = new Book()) {}
    }
    
    void loadProps(final String s) {
        this.props = new Properties();
        try {
            final InputStream openStream = new URL(String.valueOf(this.getCodeBase()) + s).openStream();
            InputStreamReader inputStreamReader;
            try {
                inputStreamReader = new InputStreamReader(openStream, this.encoding);
            }
            catch (Exception ex2) {
                inputStreamReader = new InputStreamReader(openStream);
            }
            String line;
            while ((line = new BufferedReader(inputStreamReader).readLine()) != null) {
                final int index;
                if ((index = line.indexOf(61)) == -1) {
                    ((Hashtable<String, String>)this.props).put(line.toLowerCase(), "");
                }
                else {
                    ((Hashtable<String, String>)this.props).put(line.substring(index).toLowerCase(), line.substring(index + 1).trim());
                }
            }
            openStream.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private Image rotImg(final int n, final int n2, final int n3, final Book book, final int n4) {
        Image image = this.createImage(n, n2);
        final Graphics graphics = image.getGraphics();
        if (n4 == 0) {
            graphics.setColor(book.coverColor);
            graphics.fillRect(0, 0, n, n2);
            graphics.setColor(book.titleColor);
            book.drawTitle(graphics, 0, 0, n3);
        }
        else {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, n, n2);
            graphics.setColor(Color.white);
            book.drawTitle(graphics, 0, 0, n3);
            final Rot90 rot90 = new Rot90();
            rot90.angle = (book.turned() ? (-n4) : n4);
            if (book.upward()) {
                rot90.angle = -rot90.angle;
            }
            rot90.asIs = false;
            rot90.front = book.titleColor.getRGB();
            rot90.back = 0;
            image = this.createImage(new FilteredImageSource(image.getSource(), rot90));
        }
        return image;
    }
    
    public void getParams() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final String prop;
        if ((prop = this.getProp("encoding")) != null) {
            this.encoding = new String(prop);
        }
        final String parameter;
        if ((parameter = this.getParameter("input")) != null) {
            this.loadProps(parameter);
        }
        final String prop2;
        if ((prop2 = this.getProp("wait")) != null) {
            this.waitTime = Integer.parseInt(prop2);
        }
        final String prop3;
        if ((prop3 = this.getProp("font")) != null) {
            Book.prepareFonts(prop3);
        }
        final String prop4;
        if ((prop4 = this.getProp("border")) != null) {
            this.border = Integer.parseInt(prop4);
        }
        final String prop5;
        if ((prop5 = this.getProp("putbooks")) != null && prop5.startsWith("vert")) {
            this.vertical = true;
        }
        final String prop6;
        if ((prop6 = this.getProp("title.direction")) != null) {
            this.upward = prop6.equalsIgnoreCase("upward");
            this.column = prop6.equalsIgnoreCase("column");
        }
        final String prop7;
        if ((prop7 = this.getProp("book.turned")) != null) {
            this.turned = prop7.equalsIgnoreCase("yes");
        }
        final String prop8;
        if ((prop8 = this.getProp("wall.color")) != null) {
            this.shelfColor = new Color(Integer.parseInt(prop8, 16));
        }
        final String prop9;
        if ((prop9 = this.getProp("shelf.color")) != null) {
            this.plainColor = new Color(Integer.parseInt(prop9, 16));
        }
        final String prop10;
        if ((prop10 = this.getProp("cover.color")) != null) {
            Book.defaultCoverColor = new Color(Integer.parseInt(prop10, 16));
        }
        final String prop11;
        if ((prop11 = this.getProp("title.color")) != null) {
            Book.defaultTitleColor = new Color(Integer.parseInt(prop11, 16));
        }
        final String prop12;
        if ((prop12 = this.getProp("book.min")) != null) {
            Book.hmin = Integer.parseInt(prop12);
        }
        final String prop13;
        if ((prop13 = this.getProp("book.max")) != null) {
            Book.hmax = Integer.parseInt(prop13);
        }
        final String prop14;
        if ((prop14 = this.getProp("book.volume")) != null) {
            Book.volume = Integer.parseInt(prop14);
        }
        final String prop15;
        if ((prop15 = this.getProp("book.box")) != null) {
            this.boxes = prop15.equalsIgnoreCase("yes");
        }
        final String prop16;
        if ((prop16 = this.getProp("book.size")) != null) {
            Book.height = Integer.parseInt(prop16);
            Book.width = Book.height / 4;
            Book.thickness = Book.height / 8;
        }
        final String prop17;
        if ((prop17 = this.getProp("book.thickness")) != null) {
            Book.thickness = Integer.parseInt(prop17);
        }
        final String prop18;
        if ((prop18 = this.getProp("books.number")) != null) {
            final int int1 = Integer.parseInt(prop18);
            this.reserve(int1);
            for (int i = 0; i < int1; ++i) {
                final Book book = this.books[i];
                book.upward(this.upward);
                book.chinese(this.column);
                book.turn(this.turned);
                book.box(this.boxes);
                final String string = "$" + String.valueOf(i + 1);
                final String prop19;
                if ((prop19 = this.getProp(String.valueOf(string) + ".title")) != null) {
                    book.setTitle(prop19);
                }
                final String prop20;
                if ((prop20 = this.getProp(String.valueOf(string) + ".ref")) != null) {
                    book.setReference(prop20);
                }
                final String prop21;
                if ((prop21 = this.getProp(String.valueOf(string) + ".title.direction")) != null) {
                    book.upward(prop21.equalsIgnoreCase("upward"));
                    book.chinese(prop21.equalsIgnoreCase("column"));
                }
                final String prop22;
                if ((prop22 = this.getProp(String.valueOf(string) + ".volume")) != null) {
                    book.setVolume(Integer.parseInt(prop22, 16));
                }
                final String prop23;
                if ((prop23 = this.getProp(String.valueOf(string) + ".turned")) != null) {
                    book.turn(prop23.equalsIgnoreCase("yes"));
                }
                final String prop24;
                if ((prop24 = this.getProp(String.valueOf(string) + ".box")) != null) {
                    book.box(prop24.equalsIgnoreCase("yes"));
                }
                final String prop25;
                if ((prop25 = this.getProp(String.valueOf(string) + ".icon")) != null) {
                    try {
                        Image image = Shelf.icons.get(prop25);
                        if (image == null) {
                            final InputStream resourceAsStream = ((Shelf.class$ru$sakva$bsh$Shelf != null) ? Shelf.class$ru$sakva$bsh$Shelf : (Shelf.class$ru$sakva$bsh$Shelf = class$("ru.sakva.bsh.Shelf"))).getResourceAsStream("/" + prop25);
                            Image image2;
                            if (resourceAsStream != null) {
                                final int available = resourceAsStream.available();
                                final byte[] array = new byte[available];
                                for (int j = 0; j < available; array[j++] = (byte)resourceAsStream.read()) {}
                                resourceAsStream.close();
                                image2 = this.getToolkit().createImage(array, 0, available);
                            }
                            else {
                                image2 = this.getImage(new URL(this.getDocumentBase(), prop25));
                            }
                            if (image2 != null) {
                                if (!this.vertical || book.turned()) {
                                    final Rot90 rot90 = new Rot90();
                                    if (this.vertical) {
                                        rot90.angle = 180;
                                    }
                                    else {
                                        rot90.angle = (book.upward() ? 90 : -90);
                                    }
                                    image = this.createImage(new FilteredImageSource(image2.getSource(), rot90));
                                }
                                else {
                                    image = image2;
                                }
                                mediaTracker.addImage(image, i);
                                Shelf.icons.put(prop25, image);
                            }
                        }
                        book.setIcon(image);
                    }
                    catch (Exception ex) {}
                }
                final String prop26;
                if ((prop26 = this.getProp(String.valueOf(string) + ".title.color")) != null) {
                    book.titleColor = new Color(Integer.parseInt(prop26, 16));
                }
                final String prop27;
                if ((prop27 = this.getProp(String.valueOf(string) + ".cover.color")) != null) {
                    book.coverColor = new Color(Integer.parseInt(prop27, 16));
                }
            }
        }
        try {
            mediaTracker.waitForAll(this.waitTime);
        }
        catch (Exception ex2) {}
        if (this.books != null) {
            for (int k = 0; k < this.books.length; ++k) {
                final Book book2;
                if ((book2 = this.books[k]) != null) {
                    final boolean chinese = book2.chinese();
                    if (this.vertical || chinese) {
                        final int c = book2.c;
                        final int b = book2.b;
                        final boolean turned = book2.turned();
                        int n = c;
                        int n2 = b;
                        if (!this.vertical) {
                            n = b;
                            n2 = c;
                        }
                        final Image image3 = this.createImage(n, n2);
                        final Graphics graphics = image3.getGraphics();
                        graphics.setColor(book2.coverColor);
                        graphics.fillRect(book2.u, book2.v, n, n2);
                        final Image icon = book2.icon;
                        int n3 = 0;
                        if (icon != null) {
                            n3 = icon.getHeight(this) / icon.getWidth(this) * c;
                            if (this.vertical) {
                                graphics.drawImage(icon, 0, turned ? (b - n3) : 0, c, n3, this);
                            }
                            else {
                                graphics.drawImage(icon, turned ? (b - n3) : 0, 0, c, n3, this);
                            }
                        }
                        final int n4 = b - n3;
                        if (this.vertical) {
                            if (chinese) {
                                graphics.drawImage(this.rotImg(c, n4, n3, book2, 0), 0, turned ? 0 : n3, c, n4, this);
                            }
                            else {
                                graphics.drawImage(this.rotImg(n4, c, n3, book2, 90), 0, turned ? 0 : n3, c, n4, this);
                            }
                        }
                        else {
                            graphics.drawImage(this.rotImg(c, b, n3, book2, -90), turned ? 0 : n3, 0, n4, c, this);
                        }
                        book2.icon = image3;
                    }
                }
            }
        }
    }
    
    public void createOffScreen() {
        final Dimension size = this.getSize();
        this.imW = size.width - 2 * this.border;
        this.imH = size.height - 2 * this.border;
        final boolean b = false;
        this.imV = (b ? 1 : 0);
        this.imU = (b ? 1 : 0);
        this.im = this.createImage(this.imW, this.imH);
    }
    
    public void refresh() {
        if (this.im == null) {
            this.createOffScreen();
        }
        if (this.im != null) {
            this.draw(this.im.getGraphics());
        }
        this.repaint();
    }
    
    public void drawShelf(final Graphics graphics, final int n) {
        final int width = Book.width;
        this.p[0] = 0;
        this.p[1] = this.imW - width - 3;
        this.p[2] = (this.p[3] = this.imW - 1);
        this.p[4] = width + 2;
        this.p[5] = 0;
        this.q[0] = (this.q[1] = n + 6);
        this.q[2] = this.q[1] - width - 2;
        this.q[3] = this.q[2] - 6;
        this.q[4] = this.q[3];
        this.q[5] = n;
        graphics.setColor(this.plainColor);
        graphics.fillPolygon(this.p, this.q, 6);
        graphics.setColor(Color.black);
        graphics.drawPolygon(this.p, this.q, 6);
        graphics.drawLine(this.p[0], n, this.p[1], n);
        graphics.drawLine(this.p[1], n, this.p[2], this.q[3]);
        graphics.drawLine(this.p[1], this.q[1], this.p[1], n);
        graphics.drawLine(this.p[4], this.q[4], this.p[0], n);
    }
    
    public void draw(final Graphics graphics) {
        final int[] array = new int[32];
        int n = 0;
        graphics.setColor(this.shelfColor);
        graphics.fillRect(0, 0, this.imW, this.imH);
        graphics.setColor(Color.black);
        if (this.books != null) {
            if (this.vertical) {
                int n2 = 2;
                final int n3 = this.imH - Book.height - 12;
                final int n4 = 6 + Book.height + 6;
                array[n++] = 0;
                for (int i = 0; i < this.books.length; ++i) {
                    final Book book;
                    if ((book = this.books[i]) != null) {
                        if (n2 + book.a + book.c + 2 > this.imW) {
                            array[n++] = i;
                            for (int j = 0; j < i; ++j) {
                                if (this.books[j] != null) {
                                    final Book book2 = this.books[j];
                                    book2.v -= n4;
                                }
                            }
                            n2 = 2;
                        }
                        book.u = n2 + 2;
                        book.v = n3 - 2;
                        n2 += book.c + 2;
                        if (book.selected()) {
                            final Book book3 = book;
                            book3.u -= 4;
                            final Book book4 = book;
                            book4.v += 4;
                        }
                    }
                }
                int n5 = this.imH - 12;
                int length = this.books.length;
                int n6 = n;
                while (--n6 >= 0) {
                    this.drawShelf(graphics, n5);
                    for (int k = array[n6]; k < length; ++k) {
                        if (this.books[k] != null) {
                            this.books[k].drawVertical(graphics);
                        }
                    }
                    length = array[n6];
                    n5 -= n4;
                }
                return;
            }
            int n7 = 2;
            int width = Book.width;
            int n8 = 0;
            int l;
            for (l = 0; l < this.books.length; ++l) {
                final Book book5;
                if ((book5 = this.books[l]) != null) {
                    width += book5.c + 1;
                    if (width > this.imH - 12) {
                        array[n++] = l;
                        final int n9 = this.imH - 12 - (width - book5.c - 1);
                        for (int n10 = n8; n10 < l; ++n10) {
                            if (this.books[n10] != null) {
                                final Book book6 = this.books[n10];
                                book6.v += n9;
                            }
                        }
                        n8 = l;
                        width = Book.width + book5.c + 1;
                        n7 += Book.height + 6;
                    }
                    book5.u = n7 + 2;
                    book5.v = width - book5.c - 3;
                    if (book5.selected()) {
                        final Book book7 = book5;
                        book7.u -= 4;
                        final Book book8 = book5;
                        book8.v += 4;
                    }
                }
            }
            array[n++] = l;
            final int n11 = this.imH - 12 - width;
            for (int n12 = n8; n12 < l; ++n12) {
                if (this.books[n12] != null) {
                    final Book book9 = this.books[n12];
                    book9.v += n11;
                }
            }
            this.drawShelf(graphics, this.imH - 12);
            int n13 = 0;
            for (int n14 = 0; n14 < n; n13 = array[n14++]) {
                int n15 = array[n14];
                while (--n15 >= n13) {
                    if (this.books[n15] != null) {
                        this.books[n15].drawHorisontal(graphics);
                    }
                }
            }
        }
    }
    
    void drawBorder(final Graphics graphics) {
        final int n = this.imW + 2 * this.border;
        final int n2 = this.imH + 2 * this.border;
        final int n3 = n2 - this.border;
        final int n4 = n - this.border;
        graphics.setColor(this.shelfColor);
        graphics.fill3DRect(0, 0, n, this.border, true);
        graphics.fill3DRect(0, n3, n, this.border, true);
        graphics.fill3DRect(0, this.border, this.border, n3 - this.border, true);
        graphics.fill3DRect(n4, this.border, this.border, n3 - this.border, true);
        graphics.clipRect(this.border + 1, this.border + 1, n - 2 * this.border - 1, n2 - 2 * this.border - 1);
    }
    
    public void update(final Graphics graphics) {
        if (this.im != null) {
            final Dimension size = this.getSize();
            graphics.clearRect(this.imU + size.width + this.border, this.border, size.width - this.imU - 2 * this.border, size.height - 2 * this.border);
            graphics.drawImage(this.im, this.imU + this.border, this.imV + this.border, this);
            if (this.taken != null) {
                if (this.vertical) {
                    this.taken.drawVertical(graphics);
                }
                else {
                    this.taken.drawHorisontal(graphics);
                }
            }
        }
        if (this.border != 0) {
            this.drawBorder(graphics);
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public int getBookIndex(final int n, final int n2) {
        int i = -1;
        if (this.books != null) {
            for (i = 0; i < this.books.length; ++i) {
                final Book book;
                if ((book = this.books[i]) != null) {
                    final int n3 = book.u + this.border;
                    final int n4 = book.v + this.border;
                    final int n5 = n3 + (this.vertical ? book.c : book.b);
                    final int n6 = n4 + (this.vertical ? book.b : book.c);
                    if (n3 < n && n < n5 && n4 < n2 && n2 < n6) {
                        break;
                    }
                }
            }
        }
        return i;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return (n & 0x20) != 0x20 && (n & 0xC0) == 0x0;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.msU = mouseEvent.getX();
        this.msV = mouseEvent.getY();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int n = x - this.msU;
        this.msU = x;
        if (n != 0) {
            final int imU = this.imU + n;
            if (16 - this.getSize().width < imU && imU <= 0) {
                this.imU = imU;
                this.repaint();
            }
        }
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        if (this.im == null) {
            this.createOffScreen();
            this.refresh();
        }
    }
    
    public Shelf() {
        this.shelfColor = SystemColor.window;
        this.plainColor = SystemColor.control;
        this.vertical = false;
        this.waitTime = 1000;
        this.upward = true;
        this.column = true;
        this.turned = false;
        this.boxes = false;
        this.p = new int[7];
        this.q = new int[7];
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Shelf.icons = new Hashtable();
        Shelf.dcm = new DirectColorModel(24, 16711680, 65280, 255);
    }
}
