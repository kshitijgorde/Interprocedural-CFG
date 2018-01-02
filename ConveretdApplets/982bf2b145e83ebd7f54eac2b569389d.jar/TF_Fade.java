import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TF_Fade extends Applet implements MouseListener, Runnable
{
    private Thread null;
    private Image[] do;
    private String[] long;
    private String[] goto;
    private String[] a;
    private String[] char;
    private boolean[] else;
    private Color[] case;
    private Font[] byte;
    private int[] try;
    private int[] new;
    private int[] int;
    private String for;
    private String m;
    private String k;
    private int j;
    private String i;
    private Color c;
    private Color l;
    private MediaTracker d;
    private Image g;
    private int if;
    private int void;
    private int f;
    private int h;
    private int e;
    private boolean b;
    
    public TF_Fade() {
        this.null = null;
        this.do = null;
        this.long = null;
        this.goto = null;
        this.a = null;
        this.char = null;
        this.i = "|";
        this.c = new Color(16777215);
        this.l = new Color(0);
        this.b = true;
    }
    
    public void init() {
        System.out.println(this.getAppletInfo());
        this.if = this.getSize().width;
        this.void = this.getSize().height;
        this.f = 24;
        this.e = 100;
        this.j = 2000;
        this.h = 30;
        this.d = new MediaTracker(this);
        this.a();
        this.setBackground(this.c);
        this.addMouseListener(this);
        System.gc();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.showStatus("");
        this.a(this).setCursor(0);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.for != null && !this.for.equals("")) {
            if (this.k != null) {
                this.showStatus(this.k);
            }
            this.a(this).setCursor(12);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.for != null && !this.for.equals("")) {
            try {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.for), this.m);
            }
            catch (MalformedURLException ex) {}
        }
    }
    
    private Frame a(final Component component) {
        if (component != null && !(component instanceof Frame)) {
            return (Frame)component.getParent();
        }
        return null;
    }
    
    private String[] a(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    private Color a(String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == ' ') {
                sb.append("");
            }
            else {
                sb.append(char1);
            }
        }
        s = sb.toString();
        if (s != null && s.length() >= 5) {
            try {
                final int index = s.indexOf(",");
                final int int1 = Integer.parseInt(s.substring(0, index));
                s = s.substring(index + 1);
                final int index2 = s.indexOf(",");
                final int int2 = Integer.parseInt(s.substring(0, index2));
                final int int3 = Integer.parseInt(s.substring(index2 + 1));
                return new Color((int1 > 255) ? 255 : ((int1 < 0) ? 0 : int1), (int2 > 255) ? 255 : ((int2 < 0) ? 0 : int2), (int3 > 255) ? 255 : ((int3 < 0) ? 0 : int3));
            }
            catch (Exception ex) {
                return new Color(16777215);
            }
        }
        return new Color(16777215);
    }
    
    private int a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n >> 16 & 0xFF;
        final int n6 = n >> 8 & 0xFF;
        final int n7 = n & 0xFF;
        return 0xFF000000 | n5 + ((n2 >> 16 & 0xFF) - n5) * n4 / n3 << 16 | n6 + ((n2 >> 8 & 0xFF) - n6) * n4 / n3 << 8 | n7 + ((n2 & 0xFF) - n7) * n4 / n3;
    }
    
    private Image[] a(final Image image, final Image image2, final int n) {
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        if (width != image2.getWidth(this) || height != image2.getHeight(this)) {
            return null;
        }
        final Image[] array = new Image[n];
        final int n2 = width * height;
        final int[] array2 = new int[n2];
        final int[] array3 = new int[n2];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array2, 0, width);
        final PixelGrabber pixelGrabber2 = new PixelGrabber(image2, 0, 0, width, height, array3, 0, width);
        try {
            pixelGrabber.grabPixels();
            pixelGrabber2.grabPixels();
        }
        catch (InterruptedException ex) {
            return null;
        }
        for (int i = 1; i <= n; ++i) {
            final int[] array4 = new int[n2];
            for (int j = 0; j < array4.length; ++j) {
                if (array3[j] != array2[j]) {
                    array4[j] = this.a(array2[j], array3[j], n, i);
                }
                else {
                    array4[j] = array2[j];
                }
            }
            final Image[] array5 = array;
            final int n3 = i - 1;
            final int n4 = width;
            final int n5 = height;
            this.getColorModel();
            array5[n3] = this.createImage(new MemoryImageSource(n4, n5, ColorModel.getRGBdefault(), array4, 0, width));
        }
        return array;
    }
    
    public void start() {
        if (this.null == null) {
            (this.null = new Thread(this)).setPriority(1);
            this.null.start();
        }
    }
    
    public void stop() {
        if (this.null != null) {
            this.null.stop();
            this.null = null;
        }
        System.gc();
    }
    
    public void run() {
        this.repaint();
        this.do = new Image[this.e];
        this.long = new String[this.e];
        this.goto = new String[this.e];
        this.a = new String[this.e];
        this.char = new String[this.e];
        this.byte = new Font[this.e];
        this.try = new int[this.e];
        this.new = new int[this.e];
        this.else = new boolean[this.e];
        this.case = new Color[this.e];
        this.int = new int[this.e];
        for (int i = 0; i < this.e; ++i) {
            this.long[i] = this.getParameter("link".concat(String.valueOf(String.valueOf(i))));
            this.goto[i] = this.getParameter("target".concat(String.valueOf(String.valueOf(i))));
            this.a[i] = this.getParameter("status".concat(String.valueOf(String.valueOf(i))));
            final String parameter = this.getParameter("text".concat(String.valueOf(String.valueOf(i))));
            if (parameter != null) {
                final String[] a = this.a(parameter, this.i);
                this.char[i] = a[0];
                this.byte[i] = new Font(a[1], Integer.parseInt(a[2]), Integer.parseInt(a[3]));
                this.case[i] = this.a(a[4]);
                final FontMetrics fontMetrics = this.getFontMetrics(this.byte[i]);
                final int stringWidth = fontMetrics.stringWidth(this.char[i]);
                final int n = fontMetrics.getDescent() + fontMetrics.getAscent() / 2;
                if (a[5].equalsIgnoreCase("C")) {
                    this.try[i] = (this.if - stringWidth) / 2;
                }
                else if (a[5].equalsIgnoreCase("L")) {
                    this.try[i] = 2;
                }
                else if (a[5].equalsIgnoreCase("R")) {
                    this.try[i] = this.if - stringWidth - 2;
                }
                else {
                    this.try[i] = Integer.parseInt(a[5]);
                }
                if (a[6].equalsIgnoreCase("M")) {
                    this.new[i] = (this.void + n + 3) / 2;
                }
                else if (a[6].equalsIgnoreCase("T")) {
                    this.new[i] = (2 + fontMetrics.getDescent()) * 3;
                }
                else if (a[6].equalsIgnoreCase("B")) {
                    this.new[i] = this.void - fontMetrics.getDescent() * 2 - 2;
                }
                else {
                    this.new[i] = Integer.parseInt(a[6]);
                }
                this.else[i] = (a[7].equalsIgnoreCase("true") || a[7].equalsIgnoreCase("1"));
            }
            try {
                this.int[i] = 1000 * Integer.parseInt(this.getParameter("delay".concat(String.valueOf(String.valueOf(i)))));
            }
            catch (Exception ex) {
                this.int[i] = 1000;
            }
            this.do[i] = this.getImage(this.getDocumentBase(), this.getParameter("image".concat(String.valueOf(String.valueOf(i)))));
            this.d.addImage(this.do[i], i);
            try {
                this.d.waitForID(i);
            }
            catch (Exception ex2) {
                this.do[i] = null;
            }
        }
        int n2 = this.e - 1;
        int n3 = 0;
        this.g = this.createImage(this.if, this.void);
        final Graphics graphics = this.g.getGraphics();
        final Image image = this.createImage(this.if, this.void);
        final Graphics graphics2 = image.getGraphics();
        this.b = false;
        while (this.null != null) {
            this.showStatus("Programmed by Tarek Fouda 'tarek@fouda.de'");
            try {
                this.for = this.long[n2];
                this.m = this.goto[n2];
                this.k = this.a[n2];
                this.j = this.int[n2];
                if (this.do[n3] != null) {
                    graphics2.drawImage(this.do[n3], 0, 0, null);
                }
                else {
                    graphics2.setColor(this.c);
                    graphics2.fillRect(0, 0, this.if, this.void);
                }
                if (this.char[n3] != null && this.case[n3] != null) {
                    graphics2.setFont(this.byte[n3]);
                    if (this.else[n3]) {
                        graphics2.setColor(this.c.darker().darker());
                        graphics2.drawString(this.char[n3], this.try[n3] + 1, this.new[n3] + 1);
                    }
                    graphics2.setColor(this.case[n3]);
                    graphics2.drawString(this.char[n3], this.try[n3], this.new[n3]);
                }
                final Image[] a2 = this.a(this.g, image, this.f);
                for (int j = 0; j < a2.length; ++j) {
                    graphics.drawImage(a2[j], 0, 0, null);
                    this.repaint();
                    Thread.sleep(this.h);
                }
                if (++n2 >= this.do.length) {
                    n2 = 0;
                }
                if (++n3 >= this.do.length) {
                    n3 = 0;
                }
                Thread.sleep(this.j);
                continue;
            }
            catch (InterruptedException ex3) {
                this.stop();
                continue;
            }
            finally {
                System.gc();
            }
            break;
        }
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.b) {
            graphics.setColor(this.c);
            graphics.fillRect(0, 0, this.if, this.void);
            graphics.setColor(this.l);
            graphics.drawString("Loading ...", this.if / 2 - 20, this.void / 2 + 3);
        }
        else {
            graphics.drawImage(this.g, 0, 0, this);
        }
    }
    
    public synchronized void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void a() {
        try {
            this.c = this.a(this.getParameter("bg_color"));
        }
        catch (Exception ex) {
            this.c = new Color(16777215);
        }
        try {
            this.l = this.a(this.getParameter("fg_color"));
        }
        catch (Exception ex2) {
            this.l = new Color(0);
        }
        try {
            this.e = Integer.parseInt(this.getParameter("max"));
        }
        catch (Exception ex3) {
            this.e = 50;
        }
        try {
            this.f = Integer.parseInt(this.getParameter("frames"));
        }
        catch (Exception ex4) {
            this.f = 24;
        }
        try {
            this.h = Integer.parseInt(this.getParameter("speed"));
        }
        catch (Exception ex5) {
            this.h = 30;
        }
    }
    
    public String getAppletInfo() {
        return "--------------------------------\r\n\r\nClass Name : TF_Fade\r\nOwner : Tarek Fouda\r\nAuthor : Tarek Fouda\r\nContact : tarek@fouda.de\r\nHomepage : http://www.fouda.de\r\nLast Modify : 07.06.2002\r\n\r\n--------------------------------\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n";
    }
}
