import java.net.MalformedURLException;
import java.awt.Toolkit;
import java.util.Random;
import java.awt.Event;
import java.awt.Font;
import java.util.Vector;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.LayoutManager;
import java.net.URL;
import java.awt.Rectangle;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CircleBanner extends Applet implements Runnable
{
    private static int PARAM_message;
    private static int PARAM_direction;
    private static int PARAM_url;
    private static int PARAM_fontName;
    private static int PARAM_fontSize;
    private static int PARAM_fontStyle;
    private static int PARAM_fontPadding;
    private static int PARAM_background;
    private static int PARAM_foreground;
    private static int PARAM_image;
    private static int PARAM_imageAttribute;
    private static int PARAM_charEffects;
    private static int PARAM_seed;
    private static int PARAM_pause;
    private static int PARAM_rotationfactor;
    private static int PARAM_radius;
    private static int PARAM_x;
    private static int PARAM_y;
    private static double RAD;
    private boolean random;
    private boolean fixed;
    private boolean clockwise;
    private boolean center;
    private boolean tile;
    private boolean scale;
    private boolean loaded;
    private double rotationfactor;
    private int pause;
    private int message_beg;
    private int increment;
    private int cH;
    private int fontSize;
    private int fontStyle;
    private int randCount;
    private int fontPadding;
    private int parm_radius;
    private int parm_x;
    private int parm_y;
    private int parm_seed;
    private int imageWidth;
    private int imageHeight;
    private int centered_XCorner;
    private int centered_YCorner;
    private int[] mX;
    private Color background;
    private Color foreground;
    private Color[] randColors;
    private Dimension appletSize;
    private FontMetrics fm;
    private Frame browserFrame;
    private Graphics bufferGraphics;
    private Image buffer;
    private Image image;
    private MediaTracker tracker;
    private Rectangle[] rectangles;
    private String message;
    private String fontName;
    private String imageName;
    private String[] m;
    private Thread animator;
    private URL url;
    public String[][] p;
    
    public void init() {
        this.setLayout(null);
        this.appletSize = this.size();
        try {
            this.browserFrame = (Frame)this.getParent();
        }
        catch (Exception ex) {
            this.browserFrame = null;
        }
        this.getParameters();
        this.buffer = this.createImage(this.appletSize.width, this.appletSize.height);
        this.setBufferGraphics();
        final Dimension dimension = new Dimension(this.fm.getMaxAdvance() + 2 * this.fm.getLeading() + this.fontPadding, this.fm.getMaxAscent() + this.fm.getMaxDescent() + 2 * this.fm.getLeading() + this.fontPadding);
        final int n = (dimension.width > dimension.height) ? dimension.width : dimension.height;
        final int n2 = (this.appletSize.width > this.appletSize.height) ? this.appletSize.height : this.appletSize.width;
        int parm_x;
        int parm_y = parm_x = n2 / 2;
        if (this.parm_x != -1000000) {
            parm_x = this.parm_x;
        }
        if (this.parm_y != -1000000) {
            parm_y = this.parm_y;
        }
        double n3 = parm_x - n2 / 4 / 2;
        if (this.parm_radius > -1) {
            n3 = this.parm_radius;
        }
        final Vector circle = CirclePoints.circle(parm_x, parm_y, n3, 1);
        double n4 = 360.0 / (Math.atan2(n, n3) * CircleBanner.RAD);
        final double n5 = 360.0 / (Math.atan2(n * this.rotationfactor, n3) * CircleBanner.RAD);
        double n6 = circle.size() / n5;
        if (n6 < 1.0) {
            n6 = 1.0;
        }
        if (this.rotationfactor > 1.0) {
            n4 = n5;
        }
        final double n7 = dimension.width + this.fm.getLeading();
        final double n8 = dimension.height;
        final Rectangle[] array = new Rectangle[circle.size()];
        int n9 = (int)Math.rint(circle.lastElement().x - n7 / 2.0);
        int n10 = (int)Math.rint(circle.lastElement().y - n8 / 2.0);
        int n11 = -1;
        int n13;
        for (double n12 = n6; (int)n12 < circle.size() && (n13 = (int)Math.rint(n12)) < circle.size(); n12 += n6) {
            final int n14 = (int)Math.rint(circle.elementAt(n13).x - n7 / 2.0);
            final int n15 = (int)Math.rint(circle.elementAt(n13).y - n8 / 2.0);
            if (n14 != n9 || n15 != n10) {
                array[++n11] = new Rectangle(n14, n15, (int)n7, (int)n8);
                n9 = n14;
                n10 = n15;
            }
        }
        System.arraycopy(array, 0, this.rectangles = new Rectangle[n11 + 1], 0, n11 + 1);
        this.increment = this.rectangles.length / (int)n4;
        if (this.message.length() > (int)n4) {
            this.message = this.message.substring(0, (int)n4);
        }
        this.message_beg = (this.clockwise ? 0 : (this.message.length() * this.increment - this.increment));
        this.m = new String[this.message.length()];
        this.mX = new int[this.m.length];
        final int maxAscent = this.fm.getMaxAscent();
        this.cH = (dimension.height - (maxAscent + this.fm.getMaxDescent() + this.fm.getLeading())) / 2 + maxAscent;
        final char[] charArray = this.message.toCharArray();
        for (int i = 0; i < this.message.length(); ++i) {
            this.m[i] = new String(charArray, i, 1);
            this.mX[i] = (dimension.width - this.fm.charWidth(this.message.charAt(i))) / 2;
        }
        if (!this.p[CircleBanner.PARAM_charEffects][1].equals("")) {
            this.buildColorArray();
        }
        if (!this.imageName.equals("")) {
            this.tracker = new MediaTracker(this);
            this.image = this.getImage(this.getDocumentBase(), this.imageName);
            this.tracker.addImage(this.image, 0);
            this.loaded = false;
        }
        else {
            this.image = null;
            this.loaded = false;
        }
        if (this.image != null && !this.loaded) {
            try {
                this.tracker.waitForID(0);
            }
            catch (InterruptedException ex2) {}
            if (this.tracker.isErrorID(0)) {
                this.showStatus("Error loading image " + this.imageName + ", quitting.");
                return;
            }
            this.loaded = true;
            while (true) {
                final int width = this.image.getWidth(this);
                this.imageWidth = width;
                if (width >= 0) {
                    break;
                }
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex3) {}
            }
            while (true) {
                final int height = this.image.getHeight(this);
                this.imageHeight = height;
                if (height >= 0) {
                    break;
                }
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex4) {}
            }
            if (this.center) {
                this.centered_XCorner = this.appletSize.width / 2 - this.imageWidth / 2;
                if (this.centered_XCorner < 0) {
                    this.centered_XCorner = 0;
                }
                this.centered_YCorner = this.appletSize.height / 2 - this.imageHeight / 2;
                if (this.centered_YCorner < 0) {
                    this.centered_YCorner = 0;
                }
            }
        }
    }
    
    private void setBufferGraphics() {
        this.bufferGraphics = this.buffer.getGraphics();
        this.fm = this.bufferGraphics.getFontMetrics(new Font(this.fontName, this.fontStyle, this.fontSize));
        this.bufferGraphics.setFont(this.fm.getFont());
        this.bufferGraphics.setColor(this.background);
        this.bufferGraphics.fillRect(0, 0, this.appletSize.width, this.appletSize.height);
    }
    
    public void destroy() {
    }
    
    public void update(final Graphics graphics) {
        if (this.image != null) {
            if (this.scale) {
                this.bufferGraphics.drawImage(this.image, 0, 0, this.appletSize.width, this.appletSize.height, 0, 0, 100, 100, this);
            }
            else if (this.tile) {
                for (int i = 0; i < this.appletSize.width; i += this.imageWidth) {
                    for (int j = 0; j < this.appletSize.height; j += this.imageHeight) {
                        this.bufferGraphics.drawImage(this.image, i, j, this);
                    }
                }
            }
            else if (this.center) {
                this.bufferGraphics.setColor(this.background);
                this.bufferGraphics.fillRect(0, 0, this.appletSize.width, this.appletSize.height);
                this.bufferGraphics.drawImage(this.image, this.centered_XCorner, this.centered_YCorner, this);
            }
            else {
                this.bufferGraphics.setColor(this.background);
                this.bufferGraphics.fillRect(0, 0, this.appletSize.width, this.appletSize.height);
                this.bufferGraphics.drawImage(this.image, 0, 0, this);
            }
        }
        else {
            this.bufferGraphics.setColor(this.background);
            this.bufferGraphics.fillRect(0, 0, this.appletSize.width, this.appletSize.height);
        }
        if (!this.random || !this.fixed) {
            this.bufferGraphics.setColor(this.foreground);
        }
        if (this.clockwise) {
            for (int k = 0, message_beg = this.message_beg; k < this.m.length; ++k, message_beg += this.increment) {
                final int n = (message_beg >= this.rectangles.length) ? (message_beg - this.rectangles.length) : message_beg;
                if (this.random) {
                    this.bufferGraphics.setColor(this.randColors[this.randCount]);
                    if (this.randCount < this.randColors.length - 1) {
                        ++this.randCount;
                    }
                    else {
                        this.randCount = 0;
                    }
                }
                if (this.fixed) {
                    this.bufferGraphics.setColor(this.randColors[k]);
                }
                this.bufferGraphics.drawString(this.m[k], this.rectangles[n].x + this.mX[k], this.rectangles[n].y + this.cH);
            }
            this.message_beg = ((this.message_beg + 1 >= this.rectangles.length) ? 0 : (this.message_beg + 1));
        }
        else {
            for (int l = this.message.length() - 1, message_beg2 = this.message_beg; l > -1; --l, message_beg2 -= this.increment) {
                final int n2 = (message_beg2 < 0) ? (message_beg2 + this.rectangles.length) : message_beg2;
                if (this.random) {
                    this.bufferGraphics.setColor(this.randColors[this.randCount]);
                    if (this.randCount < this.randColors.length - 1) {
                        ++this.randCount;
                    }
                    else {
                        this.randCount = 0;
                    }
                }
                if (this.fixed) {
                    this.bufferGraphics.setColor(this.randColors[l]);
                }
                this.bufferGraphics.drawString(this.m[l], this.rectangles[n2].x + this.mX[l], this.rectangles[n2].y + this.cH);
            }
            this.message_beg = ((this.message_beg - 1 < 0) ? (this.rectangles.length - 1) : (this.message_beg - 1));
        }
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.buffer, 0, 0, this);
        graphics.dispose();
    }
    
    public void start() {
        if (this.animator == null) {
            Thread.currentThread().setPriority(2);
            (this.animator = new Thread(this)).setPriority(1);
            this.animator.start();
        }
    }
    
    public void stop() {
        if (this.animator != null) {
            this.animator.stop();
            this.animator = null;
        }
    }
    
    public void run() {
        while (this.animator != null) {
            this.repaint();
            try {
                Thread.sleep(this.pause);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        try {
            if (this.url != null) {
                this.getAppletContext().showDocument(this.url);
            }
        }
        catch (Exception ex) {}
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.p[CircleBanner.PARAM_url][1] != null && !this.p[CircleBanner.PARAM_url][1].equals("")) {
            this.showStatus(this.p[CircleBanner.PARAM_url][1]);
            if (this.browserFrame != null) {
                this.browserFrame.setCursor(12);
            }
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    private void buildColorArray() {
        final Color[] array = { Color.black, Color.blue, Color.cyan, Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.white, Color.yellow };
        this.randCount = 0;
        final boolean b = false;
        this.fixed = b;
        this.random = b;
        boolean b2 = b;
        final String upperCase = this.p[CircleBanner.PARAM_charEffects][1].toUpperCase();
        if (upperCase.equals("RANDOMPASTELS")) {
            this.random = true;
            b2 = true;
        }
        else if (upperCase.equals("RANDOMCOLORS")) {
            this.random = true;
        }
        else if (upperCase.equals("FIXEDPASTELS")) {
            this.fixed = true;
            b2 = true;
        }
        else if (upperCase.equals("FIXEDCOLORS")) {
            this.fixed = true;
        }
        this.randColors = new Color[this.random ? 100 : this.m.length];
        final boolean b3;
        Random random;
        if (b3 = (this.parm_seed != -1000000)) {
            random = new Random(this.parm_seed);
        }
        else {
            random = null;
        }
        if (b2) {
            for (int i = 0; i < this.randColors.length; ++i) {
                this.randColors[i] = Color.getHSBColor((float)(b3 ? random.nextDouble() : Math.random()), 0.3f, 0.99f);
            }
            return;
        }
        for (int j = 0; j < this.randColors.length; ++j) {
            do {
                this.randColors[j] = array[(int)Math.ceil((b3 ? random.nextDouble() : Math.random()) * 11.0) - 1];
            } while (this.randColors[j] == this.background);
        }
    }
    
    private void getParameters() {
        for (int i = 0; i < this.p.length; ++i) {
            final String parameter = this.getParameter(this.p[i][0]);
            if (parameter != null) {
                this.p[i][1] = parameter;
            }
        }
        this.message = this.p[CircleBanner.PARAM_message][1];
        this.imageName = this.p[CircleBanner.PARAM_image][1];
        this.fontName = this.p[CircleBanner.PARAM_fontName][1];
        final String[] fontList = Toolkit.getDefaultToolkit().getFontList();
        for (int j = 0; j < fontList.length; ++j) {
            if (this.p[CircleBanner.PARAM_fontName][1].equalsIgnoreCase(fontList[j])) {
                this.fontName = fontList[j];
                break;
            }
        }
        if (this.p[CircleBanner.PARAM_fontStyle][1].equalsIgnoreCase("PLAIN")) {
            this.fontStyle = 0;
        }
        else if (this.p[CircleBanner.PARAM_fontStyle][1].equalsIgnoreCase("BOLD")) {
            this.fontStyle = 1;
        }
        else if (this.p[CircleBanner.PARAM_fontStyle][1].equalsIgnoreCase("ITALIC")) {
            this.fontStyle = 2;
        }
        else {
            this.fontStyle = 0;
        }
        this.fontSize = this.parse(this.p[CircleBanner.PARAM_fontSize][1], 12);
        this.pause = this.parse(this.p[CircleBanner.PARAM_pause][1], 100);
        this.rotationfactor = this.parse(this.p[CircleBanner.PARAM_rotationfactor][1], 100) * 0.01;
        this.parm_radius = this.parse(this.p[CircleBanner.PARAM_radius][1], -1);
        this.fontPadding = this.parse(this.p[CircleBanner.PARAM_fontPadding][1], 0);
        this.parm_x = this.parse(this.p[CircleBanner.PARAM_x][1], -1000000);
        this.parm_y = this.parse(this.p[CircleBanner.PARAM_y][1], -1000000);
        this.parm_seed = this.parse(this.p[CircleBanner.PARAM_seed][1], -1000000);
        this.clockwise = this.p[CircleBanner.PARAM_direction][1].equalsIgnoreCase("CLOCKWISE");
        this.scale = this.p[CircleBanner.PARAM_imageAttribute][1].equalsIgnoreCase("SCALE");
        this.tile = this.p[CircleBanner.PARAM_imageAttribute][1].equalsIgnoreCase("TILE");
        this.center = this.p[CircleBanner.PARAM_imageAttribute][1].equalsIgnoreCase("CENTER");
        this.background = this.getColor(this.p[CircleBanner.PARAM_background][1], Color.black);
        this.foreground = this.getColor(this.p[CircleBanner.PARAM_foreground][1], Color.white);
        try {
            this.url = new URL(this.p[CircleBanner.PARAM_url][1]);
        }
        catch (MalformedURLException ex) {
            this.url = null;
        }
    }
    
    private int parse(final String s, final int n) {
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    private Color getColor(final String s, final Color color) {
        try {
            return new Color(this.HexToInt(s.substring(0, 2)), this.HexToInt(s.substring(2, 4)), this.HexToInt(s.substring(4)));
        }
        catch (Exception ex) {
            return color;
        }
    }
    
    public int HexToInt(final String s) {
        int n;
        if (s.substring(0, 1).equalsIgnoreCase("a")) {
            n = 160;
        }
        else if (s.substring(0, 1).equalsIgnoreCase("b")) {
            n = 176;
        }
        else if (s.substring(0, 1).equalsIgnoreCase("c")) {
            n = 192;
        }
        else if (s.substring(0, 1).equalsIgnoreCase("d")) {
            n = 208;
        }
        else if (s.substring(0, 1).equalsIgnoreCase("e")) {
            n = 224;
        }
        else if (s.substring(0, 1).equalsIgnoreCase("f")) {
            n = 240;
        }
        else {
            n = Integer.valueOf(s.substring(0, 1)) * 16;
        }
        if (s.substring(1).equalsIgnoreCase("a")) {
            n += 10;
        }
        else if (s.substring(1).equalsIgnoreCase("b")) {
            n += 11;
        }
        else if (s.substring(1).equalsIgnoreCase("c")) {
            n += 12;
        }
        else if (s.substring(1).equalsIgnoreCase("d")) {
            n += 13;
        }
        else if (s.substring(1).equalsIgnoreCase("e")) {
            n += 14;
        }
        else if (s.substring(1).equalsIgnoreCase("f")) {
            n += 15;
        }
        else {
            n += Integer.valueOf(s.substring(1));
        }
        return n;
    }
    
    public CircleBanner() {
        this.p = new String[][] { { "message", "CircleBanner" }, { "direction", "counterclockwise" }, { "url", "" }, { "fontname", "Helvetica" }, { "fontsize", "12" }, { "fontstyle", "plain" }, { "fontpadding", "0" }, { "background", "000000" }, { "foreground", "FFFFFF" }, { "image", "" }, { "imageattribute", "" }, { "effects", "" }, { "seed", "" }, { "pause", "100" }, { "rotationfactor", "100" }, { "radius", "" }, { "x", "" }, { "y", "" } };
    }
    
    static {
        CircleBanner.PARAM_direction = 1;
        CircleBanner.PARAM_url = 2;
        CircleBanner.PARAM_fontName = 3;
        CircleBanner.PARAM_fontSize = 4;
        CircleBanner.PARAM_fontStyle = 5;
        CircleBanner.PARAM_fontPadding = 6;
        CircleBanner.PARAM_background = 7;
        CircleBanner.PARAM_foreground = 8;
        CircleBanner.PARAM_image = 9;
        CircleBanner.PARAM_imageAttribute = 10;
        CircleBanner.PARAM_charEffects = 11;
        CircleBanner.PARAM_seed = 12;
        CircleBanner.PARAM_pause = 13;
        CircleBanner.PARAM_rotationfactor = 14;
        CircleBanner.PARAM_radius = 15;
        CircleBanner.PARAM_x = 16;
        CircleBanner.PARAM_y = 17;
        CircleBanner.RAD = 57.29577951308232;
    }
}
