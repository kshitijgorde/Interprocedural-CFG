import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.net.URL;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class headline_newsticker extends Applet implements Runnable
{
    Style[] styles;
    Style defaultstyle;
    Item[] items;
    int rg;
    int mousestate;
    int dir;
    int itemcount;
    int coordY;
    int delay;
    int pause;
    int appW;
    int appH;
    Image buffer;
    Graphics offScreen;
    static Class FontC;
    static String[] fontlar;
    static int ortam;
    boolean son;
    Thread scroll;
    int cur;
    String infostr;
    Frame frm;
    Container container;
    
    public boolean test(final String s, final String s2) {
        boolean b = false;
        if (s.indexOf(s2) >= 0) {
            b = true;
        }
        return b;
    }
    
    public headline_newsticker() {
        headline_newsticker.fontlar = null;
        this.mousestate = 0;
        this.styles = new Style[64];
        this.items = new Item[64];
        headline_newsticker.ortam = -1;
        this.dir = 1;
        this.appW = 450;
        this.appH = 15;
        this.son = false;
        this.rg = 0;
        this.infostr = "Copyright (c) 2002 SecretPlus All Rights Reserved.";
        this.cur = 0;
    }
    
    public void init() {
        this.defaultstyle = new Style(null, this);
        final String parameter = this.getParameter("width");
        final String parameter2 = this.getParameter("height");
        if (parameter != null) {
            this.appW = Integer.parseInt(parameter);
        }
        if (parameter2 != null) {
            this.appH = Integer.parseInt(parameter2);
        }
        try {
            this.buffer = this.createImage(this.appW, this.appH);
            this.offScreen = this.buffer.getGraphics();
        }
        catch (Exception ex) {
            this.offScreen = null;
        }
        for (int i = 0; i < 64; ++i) {
            final String parameter3 = this.getParameter("style" + (i + 1));
            if (parameter3 == null) {
                break;
            }
            this.styles[i] = new Style(parameter3, this);
        }
        final String parameter4 = this.getParameter("copyright");
        if (parameter4 != null && this.test(parameter4.toLowerCase(), this.infostr)) {
            ++this.rg;
        }
        final String parameter5 = this.getParameter("regcode");
        if (parameter5 != null && this.test(parameter5.toUpperCase(), String.valueOf(this.spp(19496)) + this.spp(49996) + this.spp(37752) + this.spp(56549))) {
            this.rg += 2;
        }
        final URL documentBase = this.getDocumentBase();
        if (this.test(documentBase.getHost().toLowerCase(), "secretplus") || this.test(documentBase.getProtocol().toLowerCase(), "file")) {
            this.rg = 3;
        }
        if (this.rg > 0) {
            int j;
            for (j = 0; j < 64; ++j) {
                final String parameter6 = this.getParameter("item" + (j + 1) + "_label");
                if (parameter6 == null) {
                    break;
                }
                this.items[j] = new Item(j, parameter6);
            }
            this.itemcount = j;
        }
        else {
            this.items[0] = new Item(0, this.infostr);
            this.itemcount = 1;
        }
        final String parameter7 = this.getParameter("direction");
        if (parameter7 != null && this.test(parameter7.toLowerCase(), "up")) {
            this.dir = -1;
            this.cur = this.itemcount - 1;
        }
        this.pause = this.pint("pause", 10, 4);
        this.delay = this.pint("delay", 10, 2);
        this.container = this.getParent();
        while (!(this.container instanceof Frame)) {
            this.container = this.container.getParent();
        }
        this.frm = (Frame)this.container;
    }
    
    public int pint(String parameter, final int n, final int n2) {
        parameter = this.getParameter(parameter);
        if (parameter == null) {
            return n2;
        }
        int int1;
        try {
            int1 = Integer.parseInt(parameter, n);
        }
        catch (Exception ex) {
            int1 = n2;
        }
        return int1;
    }
    
    public String spp(final int n) {
        String s = Integer.toString(n, 16);
        for (int n2 = 4 - s.length(), i = 0; i < n2; ++i) {
            s = "0" + s;
        }
        return s.toUpperCase();
    }
    
    public static Color getColour(final String s) {
        if (s != null) {
            return new Color(Integer.parseInt(s, 16));
        }
        return null;
    }
    
    public void start() {
        this.coordY = 0;
        (this.scroll = new Thread(this)).start();
    }
    
    public void stop() {
        this.scroll.stop();
    }
    
    public void destroy() {
        this.scroll = null;
        this.son = true;
    }
    
    public void run() {
        Thread.currentThread().setPriority(5);
        while (!this.son) {
            final Style style = (this.mousestate == 0) ? this.items[this.cur].stNormal : this.items[this.cur].stOver;
            if (this.mousestate == 1) {
                this.coordY = (this.appH + style.size) / 2 - 1;
            }
            if (this.coordY == (this.appH + style.size) / 2 - 1) {
                for (int i = 0; i < 16; ++i) {
                    this.repaint();
                    this.stay(this.pause * 64);
                }
            }
            this.stay(5);
            this.coordY += this.dir;
            boolean b = false;
            if (this.dir == 1 && this.coordY >= this.appH + style.size * 2) {
                this.coordY = 0;
                b = true;
            }
            else if (this.coordY <= 0 && this.dir == -1) {
                this.coordY = this.appH + style.size * 2;
                b = true;
            }
            if (b && this.itemcount > 1) {
                ++this.cur;
            }
            if (this.cur >= this.itemcount) {
                this.cur = 0;
            }
            else if (this.cur < 0) {
                this.cur = this.itemcount - 1;
            }
            this.repaint();
            this.stay(this.delay);
        }
    }
    
    public synchronized void update(final Graphics graphics) {
        if (this.offScreen != null) {
            this.paintApplet(this.offScreen);
            graphics.drawImage(this.buffer, 0, 0, this);
            return;
        }
        this.paintApplet(graphics);
    }
    
    public void paintApplet(final Graphics graphics) {
        if (this.cur < this.itemcount) {
            final Style style = (this.mousestate == 0) ? this.items[this.cur].stNormal : this.items[this.cur].stOver;
            final int n = (this.mousestate == 0) ? this.items[this.cur].leftNormal : this.items[this.cur].leftOver;
            graphics.setColor(style.bgcolor);
            graphics.fillRect(0, 0, this.appW, this.appH);
            if (style.bgimage != null) {
                graphics.drawImage(style.bgimage, style.imgx, style.imgy, this);
            }
            graphics.setFont(style.font);
            graphics.setColor(style.color);
            graphics.drawString(this.items[this.cur].label, n + 1, this.coordY);
            final int border = style.border;
            if (border > 0) {
                graphics.setColor(style.bordercolor);
                graphics.fillRect(0, 0, this.appW, border);
                graphics.fillRect(0, this.appH - border, this.appW, border);
                graphics.fillRect(0, 0, border, this.appH);
                graphics.fillRect(this.appW - border, 0, border, this.appH);
            }
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        URL url = null;
        if (this.items[this.cur].url == null) {
            return true;
        }
        this.repaint();
        final String lowerCase = this.items[this.cur].url.toLowerCase();
        try {
            if (this.test(lowerCase, "http:") || this.test(lowerCase, "https:") || this.test(lowerCase, "mailto:")) {
                url = new URL(lowerCase);
            }
            else if (!this.test(lowerCase, "javascript:")) {
                url = new URL(this.getDocumentBase(), lowerCase);
            }
        }
        catch (Exception ex) {
            return true;
        }
        if (url != null) {
            this.getAppletContext().showDocument(url, this.items[this.cur].stNormal.target);
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.items[this.cur].url != null) {
            this.frm.setCursor(12);
        }
        this.mousestate = 1;
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.frm.setCursor(0);
        this.mousestate = 0;
        this.repaint();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.rg < 2) {
            this.showStatus("--- Unregistered version of Headline Newsticker Java Applet,(www.secretplus.com) ---");
        }
        else {
            String s = this.items[this.cur].sbmsg;
            if (s == null) {
                s = this.items[this.cur].url;
            }
            if (s != null) {
                this.showStatus(s);
            }
            else {
                this.showStatus("");
            }
        }
        return true;
    }
    
    public void stay(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (Exception ex) {
            this.son = true;
        }
    }
    
    public static String getFontName(String lowerCase) {
        if (headline_newsticker.ortam == -1) {
            final String property = System.getProperty("java.version");
            if (property.indexOf("1.1.4") >= 0) {
                try {
                    headline_newsticker.FontC = Class.forName("com.ms.awt.FontX");
                    headline_newsticker.fontlar = (String[])headline_newsticker.FontC.getMethod("getFontList", (Class[])null).invoke(null, (Object[])null);
                    headline_newsticker.ortam = 1;
                }
                catch (Exception ex2) {
                    headline_newsticker.ortam = 0;
                }
            }
            else if (Integer.parseInt(property.substring(2, 3)) >= 2) {
                try {
                    final Class<?> forName = Class.forName("java.awt.GraphicsEnvironment");
                    headline_newsticker.fontlar = (String[])forName.getMethod("getAvailableFontFamilyNames", (Class[])null).invoke(forName.getMethod("getLocalGraphicsEnvironment", (Class<?>[])null).invoke(null, (Object[])null), (Object[])null);
                    headline_newsticker.ortam = 2;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    headline_newsticker.ortam = 0;
                }
            }
            else {
                headline_newsticker.ortam = 0;
            }
        }
        if (headline_newsticker.ortam > 0) {
            for (int i = 0; i < headline_newsticker.fontlar.length; ++i) {
                lowerCase = lowerCase.toLowerCase();
                if (lowerCase.indexOf(headline_newsticker.fontlar[i].toLowerCase()) >= 0) {
                    lowerCase = headline_newsticker.fontlar[i];
                    return lowerCase;
                }
            }
            return "Tahoma";
        }
        return "sansserif";
    }
    
    public Font makeFont(final String s, final int n, final int n2) {
        final String fontName = getFontName(s);
        Font font = null;
        if (headline_newsticker.ortam == 1) {
            try {
                font = (Font)headline_newsticker.FontC.getMethod("getFont", fontName.getClass(), Integer.TYPE, Integer.TYPE, Integer.TYPE).invoke(null, fontName, new Integer(n), new Integer(n2), (Integer)headline_newsticker.FontC.getField("EMBEDDED").get(null));
            }
            catch (Exception ex) {
                ex.printStackTrace();
                font = new Font("Tahoma", n, n2);
            }
        }
        else if (headline_newsticker.ortam == 2) {
            font = new Font(fontName, n, n2);
        }
        else if (headline_newsticker.ortam == 0) {
            font = new Font(s, n, n2);
        }
        return font;
    }
    
    class Item
    {
        Style stNormal;
        Style stOver;
        String url;
        String label;
        int leftNormal;
        int leftOver;
        int widthNormal;
        int widthOver;
        String sbmsg;
        
        public Item(final int n, final String label) {
            this.url = null;
            this.stNormal = headline_newsticker.this.defaultstyle;
            this.label = label;
            final String parameter = headline_newsticker.this.getParameter("item" + (n + 1) + "_url");
            if (parameter != null) {
                this.url = parameter;
            }
            final String parameter2 = headline_newsticker.this.getParameter("item" + (n + 1) + "_status_bar_msg");
            if (parameter2 != null) {
                this.sbmsg = parameter2;
            }
            final String parameter3 = headline_newsticker.this.getParameter("item" + (n + 1) + "_style");
            if (parameter3 != null) {
                final int n2 = Integer.parseInt(parameter3.substring(5)) - 1;
                if (headline_newsticker.this.styles[n2] != null) {
                    this.stNormal = headline_newsticker.this.styles[n2];
                }
            }
            final String parameter4 = headline_newsticker.this.getParameter("item" + (n + 1) + "_over_style");
            if (parameter4 != null) {
                final int n3 = Integer.parseInt(parameter4.substring(5)) - 1;
                if (headline_newsticker.this.styles[n3] != null) {
                    this.stOver = headline_newsticker.this.styles[n3];
                }
                else {
                    this.stOver = this.stNormal;
                }
            }
            else {
                this.stOver = this.stNormal;
            }
            this.widthNormal = this.stNormal.metrics.stringWidth(this.label);
            if (this.stNormal.align == 0) {
                this.leftNormal = this.stNormal.leftmargin;
            }
            else if (this.stNormal.align == 1) {
                this.leftNormal = (headline_newsticker.this.appW - this.widthNormal) / 2;
            }
            else if (this.stNormal.align == 2) {
                this.leftNormal = headline_newsticker.this.appW - this.stNormal.rightmargin - this.widthNormal;
            }
            this.widthOver = this.stOver.metrics.stringWidth(this.label);
            if (this.stOver.align == 0) {
                this.leftOver = this.stOver.leftmargin;
                return;
            }
            if (this.stOver.align == 1) {
                this.leftOver = (headline_newsticker.this.appW - this.widthOver) / 2;
                return;
            }
            if (this.stOver.align == 2) {
                this.leftOver = headline_newsticker.this.appW - this.stOver.rightmargin - this.widthOver;
            }
        }
    }
    
    class Style
    {
        FontMetrics metrics;
        Color color;
        Color bgcolor;
        int align;
        Font font;
        int size;
        String target;
        int border;
        Color bordercolor;
        String face;
        int style;
        int leftmargin;
        int rightmargin;
        Image bgimage;
        int imgx;
        int imgy;
        
        public Style(String lowerCase, final Applet applet) {
            this.size = 12;
            this.style = 0;
            this.face = "Tahoma";
            this.color = Color.black;
            this.bgcolor = Color.white;
            this.border = 0;
            this.bordercolor = Color.white;
            this.align = 0;
            this.target = "_blank";
            this.leftmargin = 0;
            this.rightmargin = 0;
            this.bgimage = null;
            this.imgx = 0;
            this.imgy = 0;
            int n = 0;
            if (lowerCase != null) {
                lowerCase = lowerCase.toLowerCase();
                for (int i = lowerCase.indexOf(";", 0); i > 0; i = lowerCase.indexOf(";", i + 1)) {
                    final int index;
                    if ((index = lowerCase.indexOf(":", n)) > 0) {
                        final String substring = lowerCase.substring(n, index);
                        final String substring2 = lowerCase.substring(index + 1, i);
                        if (headline_newsticker.this.test(substring, "parentstyle")) {
                            final int n2 = Integer.parseInt(substring2.substring(5)) - 1;
                            if (headline_newsticker.this.styles[n2] != null) {
                                final Style style = headline_newsticker.this.styles[n2];
                                this.size = style.size;
                                this.style = style.style;
                                this.face = style.face;
                                this.color = style.color;
                                this.bgcolor = style.bgcolor;
                                this.border = style.border;
                                this.bordercolor = style.bordercolor;
                                this.align = style.align;
                                this.target = style.target;
                                this.leftmargin = style.leftmargin;
                                this.rightmargin = style.rightmargin;
                                this.bgimage = style.bgimage;
                                this.imgx = style.imgx;
                                this.imgy = style.imgy;
                            }
                        }
                        else if (headline_newsticker.this.test(substring, "fontcolor")) {
                            this.color = headline_newsticker.getColour(substring2);
                        }
                        else if (headline_newsticker.this.test(substring, "bgcolor")) {
                            this.bgcolor = headline_newsticker.getColour(substring2);
                        }
                        else if (headline_newsticker.this.test(substring, "bordercolor")) {
                            this.bordercolor = headline_newsticker.getColour(substring2);
                        }
                        else if (headline_newsticker.this.test(substring, "fontsize")) {
                            this.size = Integer.parseInt(substring2);
                        }
                        else if (headline_newsticker.this.test(substring, "border")) {
                            this.border = Integer.parseInt(substring2);
                        }
                        else if (headline_newsticker.this.test(substring, "leftmargin")) {
                            this.leftmargin = Integer.parseInt(substring2);
                        }
                        else if (headline_newsticker.this.test(substring, "rightmargin")) {
                            this.rightmargin = Integer.parseInt(substring2);
                        }
                        else if (headline_newsticker.this.test(substring, "fontface")) {
                            this.face = substring2;
                        }
                        else if (headline_newsticker.this.test(substring, "align")) {
                            if (headline_newsticker.this.test(substring2, "center")) {
                                this.align = 1;
                            }
                            else if (headline_newsticker.this.test(substring2, "right")) {
                                this.align = 2;
                            }
                            else {
                                this.align = 0;
                            }
                        }
                        else if (headline_newsticker.this.test(substring, "fontstyle")) {
                            this.style = Integer.parseInt(substring2);
                        }
                        else if (headline_newsticker.this.test(substring, "target")) {
                            this.target = substring2;
                        }
                        else if (headline_newsticker.this.test(substring, "imgxpos")) {
                            this.imgx = Integer.parseInt(substring2);
                        }
                        else if (headline_newsticker.this.test(substring, "imgypos")) {
                            this.imgy = Integer.parseInt(substring2);
                        }
                        else if (headline_newsticker.this.test(substring, "bgimage") && substring2 != null) {
                            if (headline_newsticker.this.test(substring2, "noimage")) {
                                this.bgimage = null;
                            }
                            else if (headline_newsticker.this.test(substring2, ".gif") || headline_newsticker.this.test(substring2, ".jpg")) {
                                final MediaTracker mediaTracker = new MediaTracker(applet);
                                mediaTracker.addImage(this.bgimage = headline_newsticker.this.getImage(headline_newsticker.this.getCodeBase(), substring2), 0);
                                try {
                                    mediaTracker.waitForAll();
                                }
                                catch (InterruptedException ex) {
                                    this.bgimage = null;
                                }
                            }
                        }
                    }
                    n = i + 1;
                }
            }
            this.font = headline_newsticker.this.makeFont(this.face, this.style, this.size);
            this.metrics = headline_newsticker.this.getFontMetrics(this.font);
        }
    }
}
