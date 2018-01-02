import java.awt.Component;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.MediaTracker;
import java.io.InputStream;
import java.util.Hashtable;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.AppletContext;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.net.URL;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class VisualScrollFx extends Applet implements Runnable, MouseListener
{
    String[] text;
    String[] framename;
    String[] shadowdirection;
    String[] enter;
    String[] exit;
    String readfile;
    String delim;
    String param;
    String bg;
    URL[] url;
    URL file;
    FontMetrics fm;
    Font[] font;
    Color[] color;
    Color[] shadowcolor;
    Color bgcolor;
    Color[] entercolor;
    Color temp1;
    Color tempenter;
    Color tempshadow;
    int[] wait;
    int[] sleepfadeenter;
    int[] sleepfadeexit;
    int[] speed;
    int[] jump;
    int[] sleeptypeenter;
    int[] sleeptypeexit;
    int[] r;
    int[] g;
    int[] b;
    int[] shadrmax;
    int[] shadgmax;
    int[] shadbmax;
    int[] shadr;
    int[] shadg;
    int[] shadb;
    int[] maxr;
    int[] maxg;
    int[] maxb;
    int minr;
    int ming;
    int minb;
    int rgbmax;
    int n;
    int yy;
    int xx;
    int max;
    int arrayNum;
    final int PLAIN = 0;
    final int BOLD = 1;
    final int ITALIC = 2;
    AppletContext ac;
    Image workspace;
    Graphics offscreen;
    Thread runner;
    Hashtable colorProp;
    InputStream inFile;
    boolean in;
    boolean start;
    boolean allowed;
    boolean[] shadow;
    boolean[] typeenter;
    boolean[] typeexit;
    boolean[] fade;
    boolean isBgImage;
    boolean getStuffLater;
    boolean doBgImageLater;
    boolean[] highlight;
    MediaTracker mt;
    Image bgImage;
    int imageWidth;
    int imageHeight;
    Image applet;
    Graphics background;
    int widthHow;
    int heightHow;
    Dimension d;
    String memory;
    String temp;
    int length;
    
    private void makeImage() {
        for (int i = 0; i < this.d.height; i += this.imageHeight) {
            for (int j = 0; j < this.d.width; j += this.imageWidth) {
                this.background.drawImage(this.bgImage, j, i, this);
            }
        }
    }
    
    private Color decodeColor(String substring, final String s) {
        final char[] charArray = substring.toCharArray();
        if (Character.isLetter(charArray[0])) {
            return this.colorProp.get(substring.toLowerCase());
        }
        if (Character.isDigit(charArray[0])) {
            final StringTokenizer stringTokenizer = new StringTokenizer(substring, s);
            return new Color(this.toInt(stringTokenizer.nextToken()), this.toInt(stringTokenizer.nextToken()), this.toInt(stringTokenizer.nextToken()));
        }
        if (charArray[0] == '#') {
            substring = substring.substring(1, substring.length());
            return new Color(Integer.parseInt(substring, 16));
        }
        return null;
    }
    
    private Font decodeFont(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final String nextToken = stringTokenizer.nextToken();
        final String nextToken2 = stringTokenizer.nextToken();
        final int int1 = this.toInt(stringTokenizer.nextToken());
        int n;
        if (nextToken2.equalsIgnoreCase("plain")) {
            n = 0;
        }
        else if (nextToken2.equalsIgnoreCase("bold")) {
            n = 1;
        }
        else if (nextToken2.equalsIgnoreCase("italic")) {
            n = 2;
        }
        else if (nextToken2.equalsIgnoreCase("bold+italic") || nextToken2.equalsIgnoreCase("italic+bold")) {
            n = 3;
        }
        else {
            n = 0;
        }
        return new Font(nextToken, n, int1);
    }
    
    private URL decodeURL(final String s) {
        if (this.url.equals("none")) {
            return null;
        }
        try {
            return new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    private String decodeFrame(final String s) {
        return s.substring(s.indexOf(44) + 1, s.length());
    }
    
    private String decodeURLCut(final String s) {
        return s.substring(0, s.indexOf(44));
    }
    
    private int toInt(final String s) {
        return Integer.parseInt(s);
    }
    
    private int stringWidth(final Font font, final String s) {
        return this.getFontMetrics(font).stringWidth(s);
    }
    
    private int stringHeight(final Font font) {
        return this.getFontMetrics(font).getHeight();
    }
    
    private void sleep(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    private void getParameters() {
        this.max = this.toInt(this.getParameter("msg#"));
        if (this.max > 5) {
            this.max = 5;
        }
        for (int i = 0; i < this.max; ++i) {
            final String parameter = this.getParameter("enter" + i);
            if (parameter != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
                final String nextToken = stringTokenizer.nextToken();
                if (this.SCIC(nextToken, "type")) {
                    this.enter[i] = "type";
                    this.typeenter[i] = true;
                    this.sleeptypeenter[i] = this.toInt(stringTokenizer.nextToken());
                }
                else if (this.SCIC(nextToken, "fade")) {
                    this.enter[i] = "fade";
                    this.fade[i] = true;
                    this.sleepfadeenter[i] = this.toInt(stringTokenizer.nextToken());
                    this.getStuffLater = true;
                }
                else if (this.SCIC(nextToken, "left")) {
                    this.enter[i] = "left";
                }
                else if (this.SCIC(nextToken, "right")) {
                    this.enter[i] = "right";
                }
                else if (this.SCIC(nextToken, "down")) {
                    this.enter[i] = "down";
                }
                else if (this.SCIC(nextToken, "up")) {
                    this.enter[i] = "up";
                }
            }
            else {
                this.enter[i] = "left";
            }
            final String parameter2 = this.getParameter("text" + i);
            this.text[i] = ((parameter2 != null) ? parameter2 : "No Text Specified!");
            final String parameter3 = this.getParameter("color" + i);
            this.color[i] = ((parameter3 != null) ? this.decodeColor(parameter3, ",") : this.decodeColor("black", null));
            final String parameter4 = this.getParameter("font" + i);
            this.font[i] = ((parameter4 != null) ? this.decodeFont(parameter4, ",") : this.decodeFont("Arial,plain,30", ","));
            final String parameter5 = this.getParameter("highlight" + i);
            if (parameter5 != null) {
                this.highlight[i] = true;
            }
            this.entercolor[i] = ((parameter5 != null) ? this.decodeColor(parameter5, ",") : this.color[i]);
            final String parameter6 = this.getParameter("shadow" + i);
            if (parameter6 != null) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter6, ",");
                this.shadow[i] = true;
                this.shadowdirection[i] = stringTokenizer2.nextToken();
                this.shadowcolor[i] = this.decodeColor(stringTokenizer2.nextToken(), ",");
                this.jump[i] = this.toInt(stringTokenizer2.nextToken());
            }
            else {
                this.shadow[i] = false;
            }
            final String parameter7 = this.getParameter("url" + i);
            if (parameter7 != null) {
                this.framename[i] = this.decodeFrame(parameter7);
                this.url[i] = this.decodeURL(this.decodeURLCut(parameter7));
            }
            final String parameter8 = this.getParameter("wait" + i);
            this.wait[i] = ((parameter8 != null) ? this.toInt(parameter8) : 0);
            final String parameter9 = this.getParameter("speed" + i);
            this.speed[i] = ((parameter9 != null) ? this.toInt(parameter9) : 7);
            final String parameter10 = this.getParameter("exit" + i);
            if (parameter10 != null) {
                final StringTokenizer stringTokenizer3 = new StringTokenizer(parameter10, ",");
                final String nextToken2 = stringTokenizer3.nextToken();
                if (this.SCIC(nextToken2, "type")) {
                    this.exit[i] = "type";
                    this.typeexit[i] = true;
                    this.sleeptypeexit[i] = this.toInt(stringTokenizer3.nextToken());
                }
                else if (this.SCIC(nextToken2, "fade")) {
                    this.exit[i] = "fade";
                    this.fade[i] = true;
                    this.sleepfadeexit[i] = this.toInt(stringTokenizer3.nextToken());
                    this.getStuffLater = true;
                }
                else if (this.SCIC(nextToken2, "left")) {
                    this.exit[i] = "left";
                }
                else if (this.SCIC(nextToken2, "right")) {
                    this.exit[i] = "right";
                }
                else if (this.SCIC(nextToken2, "down")) {
                    this.exit[i] = "down";
                }
                else if (this.SCIC(nextToken2, "up")) {
                    this.exit[i] = "up";
                }
            }
            else {
                this.exit[i] = "left";
            }
            final String parameter11 = this.getParameter("bgcolor");
            if (parameter11 != null) {
                if (parameter11.startsWith("img:")) {
                    this.doBgImageLater = true;
                    this.isBgImage = true;
                }
                else {
                    this.bgcolor = this.decodeColor(parameter11, ",");
                }
            }
            if (parameter11 == null) {
                this.bgcolor = this.decodeColor("white", ",");
            }
            if (this.getStuffLater) {
                if (!this.isBgImage) {
                    this.r[i] = this.color[i].getRed();
                    this.g[i] = this.color[i].getGreen();
                    this.b[i] = this.color[i].getBlue();
                    this.maxr[i] = this.r[i];
                    this.maxg[i] = this.g[i];
                    this.maxb[i] = this.b[i];
                    this.minr = this.bgcolor.getRed();
                    this.ming = this.bgcolor.getGreen();
                    this.minb = this.bgcolor.getBlue();
                    this.r[i] = this.minr;
                    this.g[i] = this.ming;
                    this.b[i] = this.minb;
                    if (this.shadow[i]) {
                        this.shadr[i] = this.shadowcolor[i].getRed();
                        this.shadg[i] = this.shadowcolor[i].getGreen();
                        this.shadb[i] = this.shadowcolor[i].getBlue();
                        this.shadrmax[i] = this.shadr[i];
                        this.shadgmax[i] = this.shadg[i];
                        this.shadbmax[i] = this.shadb[i];
                        this.shadr[i] = this.minr;
                        this.shadg[i] = this.ming;
                        this.shadb[i] = this.minb;
                    }
                }
                else {
                    while (true) {
                        this.showStatus("fading won't work on textured backgrounds!");
                    }
                }
            }
        }
        this.readfile = this.getParameter("file");
        this.bg = this.getParameter("bgcolor");
        if (this.bg != null) {
            if (this.bg.startsWith("img:")) {
                this.doBgImageLater = true;
            }
            else {
                this.bgcolor = this.decodeColor(this.bg, ",");
            }
        }
        if (this.bg == null) {
            this.bgcolor = this.decodeColor("white", ",");
        }
        if (this.doBgImageLater) {
            this.bg = this.bg.substring(4, this.bg.length());
            this.isBgImage = true;
            this.bgImage = this.getImage(this.getDocumentBase(), this.bg);
            this.mt.addImage(this.bgImage, 0);
            try {
                this.mt.waitForAll();
            }
            catch (InterruptedException ex) {}
            this.imageWidth = this.bgImage.getWidth(this);
            this.imageHeight = this.bgImage.getHeight(this);
        }
    }
    
    private void putColors() {
        this.colorProp.put("white", Color.white);
        this.colorProp.put("lightgray", Color.lightGray);
        this.colorProp.put("gray", Color.gray);
        this.colorProp.put("darkgray", Color.darkGray);
        this.colorProp.put("black", Color.black);
        this.colorProp.put("red", Color.red);
        this.colorProp.put("pink", Color.pink);
        this.colorProp.put("orange", Color.orange);
        this.colorProp.put("yellow", Color.yellow);
        this.colorProp.put("green", Color.green);
        this.colorProp.put("magenta", Color.magenta);
        this.colorProp.put("cyan", Color.cyan);
        this.colorProp.put("blue", Color.blue);
    }
    
    private void initGraphics() {
        this.workspace = this.createImage(this.d.width, this.d.height);
        this.offscreen = this.workspace.getGraphics();
        this.applet = this.createImage(this.d.width, this.d.height);
        this.background = this.applet.getGraphics();
    }
    
    private void mattMadeVsfx(final String[] array) {
        for (int i = 0; i < this.max; ++i) {
            final String lowerCase = array[i].toLowerCase();
            if (lowerCase.startsWith("vsfx made by") || lowerCase.startsWith("visualscrollfx made by")) {
                array[i] = "VSFX Made By Matt!";
            }
        }
    }
    
    public void init() {
        this.ac = this.getAppletContext();
        this.d = this.getSize();
        this.initGraphics();
        this.putColors();
        this.getParameters();
        this.mattMadeVsfx(this.text);
        if (this.isBgImage) {
            this.makeImage();
        }
        this.addMouseListener(this);
        this.setBackground(this.bgcolor);
    }
    
    private void blank(final Graphics graphics) {
        graphics.setColor(this.bgcolor);
        graphics.fillRect(0, 0, this.d.width, this.d.height);
    }
    
    private boolean SCIC(final String s, final String s2) {
        return s.equalsIgnoreCase(s2);
    }
    
    private void shadow(final Graphics graphics, final String s, final String s2, final int n, final int n2, final int n3, final Color color, final boolean b) {
        if (b) {
            graphics.setColor(color);
            if (this.SCIC(s2, "bottomright")) {
                graphics.drawString(s, n + n3, n2 + n3);
            }
            if (this.SCIC(s2, "bottomleft")) {
                graphics.drawString(s, n - n3, n2 + n3);
            }
            if (this.SCIC(s2, "bottom")) {
                graphics.drawString(s, n, n2 + n3);
            }
            if (this.SCIC(s2, "topright")) {
                graphics.drawString(s, n + n3, n2 - n3);
            }
            if (this.SCIC(s2, "topleft")) {
                graphics.drawString(s, n - n3, n2 - n3);
            }
            if (this.SCIC(s2, "top")) {
                graphics.drawString(s, n, n2 - n3);
            }
            if (this.SCIC(s2, "left")) {
                graphics.drawString(s, n - n3, n2);
            }
            if (this.SCIC(s2, "right")) {
                graphics.drawString(s, n + n3, n2);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        this.blank(this.offscreen);
        if (this.isBgImage) {
            this.offscreen.drawImage(this.applet, 0, 0, this);
        }
        this.shadow(this.offscreen, this.text[this.arrayNum], this.shadowdirection[this.arrayNum], this.xx, this.yy, this.jump[this.arrayNum], this.shadowcolor[this.arrayNum], this.shadow[this.arrayNum]);
        this.offscreen.setColor(this.in ? this.entercolor[this.arrayNum] : this.color[this.arrayNum]);
        this.offscreen.setFont(this.font[this.arrayNum]);
        this.offscreen.drawString(this.text[this.arrayNum], this.xx, this.yy);
        graphics.drawImage(this.workspace, 0, 0, this);
    }
    
    private void enter(String lowerCase, String memory, final Font font) {
        lowerCase = lowerCase.toLowerCase();
        this.fm = this.getFontMetrics(font);
        final int ascent = this.fm.getAscent();
        final int descent = this.fm.getDescent();
        final int width = this.d.width;
        final int height = this.d.height;
        if (this.SCIC(lowerCase, "fade")) {
            this.xx = (width - this.stringWidth(font, memory)) / 2;
            this.yy = ascent + (height - (ascent + descent)) / 2;
            this.rgbmax = 0;
            this.temp1 = this.color[this.arrayNum];
            this.tempshadow = this.shadowcolor[this.arrayNum];
            this.tempenter = this.entercolor[this.arrayNum];
            while (this.rgbmax != 255) {
                if (this.shadow[this.arrayNum]) {
                    if (this.r[this.arrayNum] == this.maxr[this.arrayNum] && this.g[this.arrayNum] == this.maxg[this.arrayNum] && this.b[this.arrayNum] == this.maxb[this.arrayNum] && this.shadr[this.arrayNum] == this.shadrmax[this.arrayNum] && this.shadg[this.arrayNum] == this.shadgmax[this.arrayNum] && this.shadb[this.arrayNum] == this.shadbmax[this.arrayNum]) {
                        break;
                    }
                }
                else if (this.r[this.arrayNum] == this.maxr[this.arrayNum] && this.g[this.arrayNum] == this.maxg[this.arrayNum] && this.b[this.arrayNum] == this.maxb[this.arrayNum]) {
                    break;
                }
                this.r_getcloserup();
                this.g_getcloserup();
                this.b_getcloserup();
                this.r_shadow_getcloserup();
                this.g_shadow_getcloserup();
                this.b_shadow_getcloserup();
                this.color[this.arrayNum] = new Color(this.r[this.arrayNum], this.g[this.arrayNum], this.b[this.arrayNum]);
                if (!this.highlight[this.arrayNum]) {
                    this.entercolor[this.arrayNum] = new Color(this.r[this.arrayNum], this.g[this.arrayNum], this.b[this.arrayNum]);
                }
                this.shadowcolor[this.arrayNum] = new Color(this.shadr[this.arrayNum], this.shadg[this.arrayNum], this.shadb[this.arrayNum]);
                this.sleep(this.sleepfadeenter[this.arrayNum]);
                this.repaint(0L);
                ++this.rgbmax;
            }
            this.color[this.arrayNum] = this.temp1;
            this.entercolor[this.arrayNum] = this.tempenter;
            this.shadowcolor[this.arrayNum] = this.tempshadow;
            this.r[this.arrayNum] = this.maxr[this.arrayNum];
            this.g[this.arrayNum] = this.maxg[this.arrayNum];
            this.b[this.arrayNum] = this.maxb[this.arrayNum];
            this.shadr[this.arrayNum] = this.shadrmax[this.arrayNum];
            this.shadg[this.arrayNum] = this.shadgmax[this.arrayNum];
            this.shadb[this.arrayNum] = this.shadbmax[this.arrayNum];
        }
        if (this.SCIC(lowerCase, "type")) {
            this.xx = (width - this.stringWidth(font, memory)) / 2;
            this.yy = ascent + (height - (ascent + descent)) / 2;
            final String s = memory;
            final int length = s.length();
            this.n = 0;
            this.memory = memory;
            while (this.n < length + 1) {
                this.text[this.arrayNum] = s.substring(0, this.n);
                this.sleep(this.sleeptypeenter[this.arrayNum]);
                this.repaint(0L);
                ++this.n;
            }
            this.n = 0;
            memory = this.memory;
            return;
        }
        if (this.SCIC(lowerCase, "left")) {
            this.xx = this.getSize().width + this.jump[this.arrayNum];
            this.yy = ascent + (height - (ascent + descent)) / 2;
            while (this.xx != (width - this.stringWidth(font, memory)) / 2) {
                --this.xx;
                this.sleep(this.speed[this.arrayNum]);
                this.repaint(0L);
            }
            return;
        }
        if (this.SCIC(lowerCase, "right")) {
            this.xx = -this.stringWidth(font, memory) + -this.jump[this.arrayNum];
            this.yy = ascent + (height - (ascent + descent)) / 2;
            while (this.xx != (width - this.stringWidth(font, memory)) / 2) {
                ++this.xx;
                this.sleep(this.speed[this.arrayNum]);
                this.repaint(0L);
            }
            return;
        }
        if (this.SCIC(lowerCase, "up")) {
            this.yy = height + this.stringHeight(font) + this.jump[this.arrayNum];
            this.xx = (width - this.stringWidth(font, memory)) / 2;
            while (this.yy != ascent + (height - (ascent + descent)) / 2) {
                --this.yy;
                this.sleep(this.speed[this.arrayNum]);
                this.repaint(0L);
            }
            return;
        }
        if (this.SCIC(lowerCase, "down")) {
            this.yy = -this.stringHeight(font) + -this.jump[this.arrayNum];
            this.xx = (width - this.stringWidth(font, memory)) / 2;
            while (this.yy != ascent + (height - (ascent + descent)) / 2) {
                ++this.yy;
                this.sleep(this.speed[this.arrayNum]);
                this.repaint(0L);
            }
        }
    }
    
    private void r_getcloserup() {
        if (this.r[this.arrayNum] > this.maxr[this.arrayNum]) {
            final int[] r = this.r;
            final int arrayNum = this.arrayNum;
            --r[arrayNum];
        }
        if (this.r[this.arrayNum] < this.maxr[this.arrayNum]) {
            final int[] r2 = this.r;
            final int arrayNum2 = this.arrayNum;
            ++r2[arrayNum2];
        }
    }
    
    private void g_getcloserup() {
        if (this.g[this.arrayNum] > this.maxg[this.arrayNum]) {
            final int[] g = this.g;
            final int arrayNum = this.arrayNum;
            --g[arrayNum];
        }
        if (this.g[this.arrayNum] < this.maxg[this.arrayNum]) {
            final int[] g2 = this.g;
            final int arrayNum2 = this.arrayNum;
            ++g2[arrayNum2];
        }
    }
    
    private void b_getcloserup() {
        if (this.b[this.arrayNum] > this.maxb[this.arrayNum]) {
            final int[] b = this.b;
            final int arrayNum = this.arrayNum;
            --b[arrayNum];
        }
        if (this.b[this.arrayNum] < this.maxb[this.arrayNum]) {
            final int[] b2 = this.b;
            final int arrayNum2 = this.arrayNum;
            ++b2[arrayNum2];
        }
    }
    
    private void r_shadow_getcloserup() {
        if (this.shadr[this.arrayNum] > this.shadrmax[this.arrayNum]) {
            final int[] shadr = this.shadr;
            final int arrayNum = this.arrayNum;
            --shadr[arrayNum];
        }
        if (this.shadr[this.arrayNum] < this.shadrmax[this.arrayNum]) {
            final int[] shadr2 = this.shadr;
            final int arrayNum2 = this.arrayNum;
            ++shadr2[arrayNum2];
        }
    }
    
    private void g_shadow_getcloserup() {
        if (this.shadg[this.arrayNum] > this.shadgmax[this.arrayNum]) {
            final int[] shadg = this.shadg;
            final int arrayNum = this.arrayNum;
            --shadg[arrayNum];
        }
        if (this.shadg[this.arrayNum] < this.shadgmax[this.arrayNum]) {
            final int[] shadg2 = this.shadg;
            final int arrayNum2 = this.arrayNum;
            ++shadg2[arrayNum2];
        }
    }
    
    private void b_shadow_getcloserup() {
        if (this.shadb[this.arrayNum] > this.shadbmax[this.arrayNum]) {
            final int[] shadb = this.shadb;
            final int arrayNum = this.arrayNum;
            --shadb[arrayNum];
        }
        if (this.shadb[this.arrayNum] < this.shadbmax[this.arrayNum]) {
            final int[] shadb2 = this.shadb;
            final int arrayNum2 = this.arrayNum;
            ++shadb2[arrayNum2];
        }
    }
    
    private void exit(String lowerCase, final String memory, final Font font) {
        lowerCase = lowerCase.toLowerCase();
        this.fm = this.getFontMetrics(font);
        if (this.SCIC(lowerCase, "fade")) {
            this.temp1 = this.color[this.arrayNum];
            this.tempshadow = this.shadowcolor[this.arrayNum];
            this.tempenter = this.entercolor[this.arrayNum];
            this.rgbmax = 255;
            this.r[this.arrayNum] = this.maxr[this.arrayNum];
            this.g[this.arrayNum] = this.maxg[this.arrayNum];
            this.b[this.arrayNum] = this.maxb[this.arrayNum];
            this.shadr[this.arrayNum] = this.shadrmax[this.arrayNum];
            this.shadg[this.arrayNum] = this.shadgmax[this.arrayNum];
            this.shadb[this.arrayNum] = this.shadbmax[this.arrayNum];
            while (this.rgbmax != 0) {
                if (this.shadow[this.arrayNum]) {
                    if (this.r[this.arrayNum] == this.minr && this.g[this.arrayNum] == this.ming && this.b[this.arrayNum] == this.minb && this.shadr[this.arrayNum] == this.minr && this.shadg[this.arrayNum] == this.ming && this.shadb[this.arrayNum] == this.minb) {
                        break;
                    }
                }
                else if (this.r[this.arrayNum] == this.minr && this.g[this.arrayNum] == this.ming && this.b[this.arrayNum] == this.minb) {
                    break;
                }
                this.r_getcloserdown();
                this.g_getcloserdown();
                this.b_getcloserdown();
                this.r_shadow_getcloserdown();
                this.g_shadow_getcloserdown();
                this.b_shadow_getcloserdown();
                this.color[this.arrayNum] = new Color(this.r[this.arrayNum], this.g[this.arrayNum], this.b[this.arrayNum]);
                if (!this.highlight[this.arrayNum]) {
                    this.entercolor[this.arrayNum] = new Color(this.r[this.arrayNum], this.g[this.arrayNum], this.b[this.arrayNum]);
                }
                this.shadowcolor[this.arrayNum] = new Color(this.shadr[this.arrayNum], this.shadg[this.arrayNum], this.shadb[this.arrayNum]);
                this.sleep(this.sleepfadeexit[this.arrayNum]);
                this.repaint(0L);
                --this.rgbmax;
            }
        }
        if (this.SCIC(lowerCase, "type")) {
            this.memory = memory;
            this.n = this.memory.length();
            while (this.n != -1) {
                this.text[this.arrayNum] = this.memory.substring(0, this.n);
                this.sleep(this.sleeptypeexit[this.arrayNum]);
                this.repaint(0L);
                --this.n;
            }
        }
        if (this.SCIC(lowerCase, "left")) {
            while (this.xx != -this.stringWidth(font, memory) + -this.jump[this.arrayNum]) {
                --this.xx;
                this.sleep(this.speed[this.arrayNum]);
                this.repaint(0L);
            }
            return;
        }
        if (this.SCIC(lowerCase, "right")) {
            while (this.xx != this.getSize().width + this.jump[this.arrayNum]) {
                ++this.xx;
                this.sleep(this.speed[this.arrayNum]);
                this.repaint(0L);
            }
            return;
        }
        if (this.SCIC(lowerCase, "up")) {
            while (this.yy != -this.stringHeight(font) + -this.jump[this.arrayNum]) {
                --this.yy;
                this.sleep(this.speed[this.arrayNum]);
                this.repaint(0L);
            }
            return;
        }
        if (this.SCIC(lowerCase, "down")) {
            while (this.yy != this.getSize().height + this.stringHeight(font) + this.jump[this.arrayNum]) {
                ++this.yy;
                this.sleep(this.speed[this.arrayNum]);
                this.repaint(0L);
            }
        }
    }
    
    private void r_getcloserdown() {
        if (this.r[this.arrayNum] > this.minr) {
            final int[] r = this.r;
            final int arrayNum = this.arrayNum;
            --r[arrayNum];
        }
        if (this.r[this.arrayNum] < this.minr) {
            final int[] r2 = this.r;
            final int arrayNum2 = this.arrayNum;
            ++r2[arrayNum2];
        }
    }
    
    private void g_getcloserdown() {
        if (this.g[this.arrayNum] > this.ming) {
            final int[] g = this.g;
            final int arrayNum = this.arrayNum;
            --g[arrayNum];
        }
        if (this.g[this.arrayNum] < this.ming) {
            final int[] g2 = this.g;
            final int arrayNum2 = this.arrayNum;
            ++g2[arrayNum2];
        }
    }
    
    private void b_getcloserdown() {
        if (this.b[this.arrayNum] > this.minb) {
            final int[] b = this.b;
            final int arrayNum = this.arrayNum;
            --b[arrayNum];
        }
        if (this.b[this.arrayNum] < this.minb) {
            final int[] b2 = this.b;
            final int arrayNum2 = this.arrayNum;
            ++b2[arrayNum2];
        }
    }
    
    private void r_shadow_getcloserdown() {
        if (this.shadr[this.arrayNum] > this.minr) {
            final int[] shadr = this.shadr;
            final int arrayNum = this.arrayNum;
            --shadr[arrayNum];
        }
        if (this.shadr[this.arrayNum] < this.minr) {
            final int[] shadr2 = this.shadr;
            final int arrayNum2 = this.arrayNum;
            ++shadr2[arrayNum2];
        }
    }
    
    private void g_shadow_getcloserdown() {
        if (this.shadg[this.arrayNum] > this.ming) {
            final int[] shadg = this.shadg;
            final int arrayNum = this.arrayNum;
            --shadg[arrayNum];
        }
        if (this.shadg[this.arrayNum] < this.ming) {
            final int[] shadg2 = this.shadg;
            final int arrayNum2 = this.arrayNum;
            ++shadg2[arrayNum2];
        }
    }
    
    private void b_shadow_getcloserdown() {
        if (this.shadb[this.arrayNum] > this.minb) {
            final int[] shadb = this.shadb;
            final int arrayNum = this.arrayNum;
            --shadb[arrayNum];
        }
        if (this.shadb[this.arrayNum] < this.minb) {
            final int[] shadb2 = this.shadb;
            final int arrayNum2 = this.arrayNum;
            ++shadb2[arrayNum2];
        }
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.runner.stop();
        this.runner = null;
    }
    
    public void run() {
        while (true) {
            if (this.arrayNum == this.max) {
                this.arrayNum = 0;
            }
            this.enter(this.enter[this.arrayNum], this.text[this.arrayNum], this.font[this.arrayNum]);
            this.allowed = true;
            this.sleep(this.wait[this.arrayNum]);
            this.allowed = false;
            this.exit(this.exit[this.arrayNum], this.text[this.arrayNum], this.font[this.arrayNum]);
            this.sleep(300);
            if (this.typeexit[this.arrayNum]) {
                this.text[this.arrayNum] = this.memory;
            }
            if (this.fade[this.arrayNum]) {
                this.color[this.arrayNum] = this.temp1;
                this.entercolor[this.arrayNum] = this.tempenter;
                this.shadowcolor[this.arrayNum] = this.tempshadow;
                this.r[this.arrayNum] = this.minr;
                this.g[this.arrayNum] = this.ming;
                this.b[this.arrayNum] = this.minb;
                this.shadr[this.arrayNum] = this.minr;
                this.shadg[this.arrayNum] = this.ming;
                this.shadb[this.arrayNum] = this.minb;
            }
            ++this.arrayNum;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.allowed) {
            this.in = true;
            this.repaint();
        }
        else {
            this.in = true;
        }
        if (this.url[this.arrayNum] != null) {
            this.showStatus(this.url[this.arrayNum].toString());
            return;
        }
        this.showStatus("Copyright 2002 VSFX By Matt Schaller, matt@swfla.rr.com");
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.url[this.arrayNum] != null && this.framename[this.arrayNum] != null) {
            this.ac.showDocument(this.url[this.arrayNum], this.framename[this.arrayNum]);
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.allowed) {
            this.in = false;
            this.repaint();
        }
        else {
            this.in = false;
        }
        this.showStatus("Copyright 2002 VSFX By Matt Schaller, matt@swfla.rr.com");
    }
    
    public VisualScrollFx() {
        this.text = new String[10];
        this.framename = new String[10];
        this.shadowdirection = new String[10];
        this.enter = new String[10];
        this.exit = new String[10];
        this.url = new URL[10];
        this.font = new Font[10];
        this.color = new Color[10];
        this.shadowcolor = new Color[10];
        this.entercolor = new Color[10];
        this.wait = new int[10];
        this.sleepfadeenter = new int[10];
        this.sleepfadeexit = new int[10];
        this.speed = new int[10];
        this.jump = new int[10];
        this.sleeptypeenter = new int[10];
        this.sleeptypeexit = new int[10];
        this.r = new int[10];
        this.g = new int[10];
        this.b = new int[10];
        this.shadrmax = new int[10];
        this.shadgmax = new int[10];
        this.shadbmax = new int[10];
        this.shadr = new int[10];
        this.shadg = new int[10];
        this.shadb = new int[10];
        this.maxr = new int[10];
        this.maxg = new int[10];
        this.maxb = new int[10];
        this.yy = -50;
        this.xx = -50;
        this.colorProp = new Hashtable();
        this.start = true;
        this.allowed = false;
        this.shadow = new boolean[10];
        this.typeenter = new boolean[10];
        this.typeexit = new boolean[10];
        this.fade = new boolean[10];
        this.isBgImage = false;
        this.getStuffLater = false;
        this.doBgImageLater = false;
        this.highlight = new boolean[10];
        this.mt = new MediaTracker(this);
        this.memory = "";
    }
}
