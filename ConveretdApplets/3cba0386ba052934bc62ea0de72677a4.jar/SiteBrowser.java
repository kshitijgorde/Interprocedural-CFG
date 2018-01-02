import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.IOException;
import java.net.Socket;
import java.awt.Font;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class SiteBrowser extends Applet implements MouseListener, MouseMotionListener, KeyListener, Runnable
{
    private final String VersionStr = "SiteBrowser 2.2";
    private final String SourceURL = "http://www.lordmark.de/SB";
    private int BorderSpace;
    private int LevelAdvance;
    private Color BGColor;
    private Image BGImage;
    private int BGOffsetX;
    private int BGOffsetY;
    private Color TextColor;
    private Color ActiveTextColor;
    private boolean Underlined;
    private String TreeFile;
    private String Target;
    private boolean AutoCollapse;
    private boolean ExpandOnMouseOver;
    private boolean MenuLayout;
    private int RAlign;
    private int HSpace;
    private int AlphaHigh;
    private int AlphaLow;
    private Color ColorHigh;
    private Color ColorLow;
    private int DropDownRate;
    private int DropUpRate;
    private int ScrollRate;
    private Color ScrollBGColor;
    private int ExpandDelay;
    private String TreeStructure;
    private String LinkPrefix;
    private int ScrollWidth;
    public int SliderType;
    private Item iScrollUp;
    private Item iScrollDown;
    private Item iSlider;
    private Item iScrollBG;
    private final int MAXDEPTH = 16;
    private Look_n_Feel[] LF;
    private Image BGImageHigh;
    private Image BGImageLow;
    private Item tree;
    private Image drawBuf;
    private int mouseX;
    private int mouseY;
    private int dragY;
    private Item iShape;
    private Item iText;
    private Item iOldText;
    private Item iTextPressed;
    private Item iEnsureVis;
    private Image ScrollBar;
    private boolean busy;
    private Cursor DefaultCursor;
    private Cursor HandCursor;
    private MediaTracker tracker;
    private YTrace[] trace;
    private boolean ShiftPressed;
    private Thread DelayedExpandThread;
    private boolean InitApplet;
    private boolean ScrollOn;
    private int OffsetY;
    private long OverEventTime;
    private int width;
    private int height;
    private int wHeight;
    private int sHeight;
    private int MaxY;
    private int SlideBy;
    private int SliderHeight;
    private Image[] SliderImage;
    
    public void init() {
        this.height = this.getSize().height;
        this.width = this.getSize().width;
        this.drawBuf = this.createImage(this.width, this.height);
        final Graphics graphics = this.drawBuf.getGraphics();
        graphics.setColor(this.BGColor);
        graphics.fillRect(0, 0, this.width, this.height);
        try {
            new Thread(this).start();
        }
        catch (SecurityException ex) {
            this.run();
        }
    }
    
    public void run() {
        if (!this.InitApplet) {
            final int n = this.ExpandDelay - (int)(System.currentTimeMillis() - this.OverEventTime);
            if (n > 0) {
                try {
                    Thread.sleep(n);
                }
                catch (InterruptedException ex) {
                    return;
                }
            }
            this.expandAnimated(this.iText, false, false);
            this.iOldText = this.iText;
            return;
        }
        this.InitApplet = false;
        this.tracker = new MediaTracker(this);
        this.LF = new Look_n_Feel[17];
        this.SliderImage = new Image[4];
        for (int i = 0; i < 4; ++i) {
            this.SliderImage[i] = null;
        }
        this.DefaultCursor = Cursor.getDefaultCursor();
        this.HandCursor = new Cursor(12);
        this.loadAppletParams();
        if (this.TreeFile == null) {
            return;
        }
        this.wHeight = this.height - 2 * this.BorderSpace;
        final Graphics graphics = this.drawBuf.getGraphics();
        graphics.setColor(this.BGColor);
        graphics.fillRect(0, 0, this.width, this.height);
        graphics.setColor(this.TextColor);
        graphics.setFont(this.LF[0].font);
        final int n2 = (this.width - graphics.getFontMetrics().stringWidth("Images couldn't load")) / 2;
        graphics.drawString("Loading...", n2, this.height / 3);
        this.repaint();
        this.loadTree();
        if (this.tree == null) {
            graphics.setColor(this.TextColor);
            graphics.setFont(this.LF[0].font);
            graphics.drawString("Loading...failed", n2, this.height / 3);
            graphics.drawString("Tree file not found", n2, this.height / 3 + graphics.getFontMetrics().getHeight());
            this.repaint();
            return;
        }
        try {
            this.tracker.waitForAll();
            if (this.tracker.isErrorAny()) {
                graphics.setColor(this.TextColor);
                graphics.setFont(this.LF[0].font);
                graphics.drawString("Loading...failed", n2, this.height / 3);
                graphics.drawString("Images couldn't load", n2, this.height / 3 + graphics.getFontMetrics().getHeight());
                this.repaint();
                return;
            }
        }
        catch (InterruptedException ex2) {}
        graphics.dispose();
        if (this.ScrollBar != null) {
            this.ScrollWidth = this.ScrollBar.getWidth(this);
        }
        this.tracker = new MediaTracker(this);
        this.tree.CalculateSize(this.LF, this);
        this.tree.align(this.LF, this.RAlign, this.MenuLayout, this.LevelAdvance);
        int n3 = 2;
        if (this.MenuLayout) {
            for (Item item = this.tree; item != null; item = item.next) {
                ++n3;
            }
        }
        this.trace = new YTrace[n3];
        for (int j = 0; j < n3; ++j) {
            this.trace[j] = new YTrace();
        }
        final Image prepareBackground = this.prepareBackground(0, this.BGColor);
        if (this.AlphaHigh == 0) {
            this.BGImageHigh = prepareBackground;
        }
        else {
            this.BGImageHigh = this.prepareBackground(this.AlphaHigh, this.ColorHigh);
        }
        if (this.AlphaLow == 0) {
            this.BGImageLow = prepareBackground;
        }
        else {
            this.BGImageLow = this.prepareBackground(this.AlphaLow, this.ColorLow);
        }
        this.BGImage = prepareBackground;
        this.prepareScroller();
        if (this.RAlign == 2) {
            this.tree.expandRecursive();
            this.placeItems();
            for (int k = 0; k < n3; ++k) {
                this.trace[k].align();
            }
        }
        this.reset();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
    }
    
    public void reset() {
        this.busy = true;
        this.tree.reset();
        this.placeItems();
        this.drawAll(this.busy = false);
    }
    
    public void collapseAll() {
        this.busy = true;
        for (Item item = this.tree; item != null; item = item.next) {
            if (item.expanded) {
                this.collapseAnimated(item, true);
            }
        }
        this.drawAll(this.busy = false);
    }
    
    private void prepareScroller() {
        if (this.ScrollWidth <= 0) {
            this.renderShapes(this.tree);
            return;
        }
        this.LF[0].texthi = this.ScrollWidth;
        if (this.LF[0].ShapeType < 0) {
            this.LF[0].ShapeType = 95;
        }
        if (this.LF[0].IconType < 0) {
            this.LF[0].IconType = 95;
        }
        this.iScrollUp.down = this.iScrollDown;
        this.iScrollDown.down = this.iScrollBG;
        this.iScrollUp.expanded = true;
        this.iScrollUp.level = 0;
        this.iScrollDown.level = 0;
        this.iScrollBG.level = 0;
        this.iScrollUp.CalculateSize(this.LF, this);
        this.iScrollBG.next = this.tree;
        this.renderShapes(this.iScrollUp);
        final Item iScrollUp = this.iScrollUp;
        final Item iScrollDown = this.iScrollDown;
        final int n = this.width - this.BorderSpace * 2 - this.ScrollWidth;
        iScrollDown.x = n;
        iScrollUp.x = n;
        this.iScrollUp.y = 0;
        this.iScrollDown.y = this.wHeight - this.iScrollDown.dy;
        this.iSlider.level = 0;
        this.iSlider.CalculateSize(this.LF, this);
        this.iScrollBG.next = null;
        this.iSlider.down = this.iScrollBG;
        this.LF[0].ShapeType = this.SliderType;
        (this.LF[0].ShapeImage = new Image[4])[0] = null;
        this.renderShapes(this.iSlider);
        this.SliderImage = this.iSlider.ShapeImage;
        this.SliderHeight = this.iSlider.dy;
        this.iSlider.ShapeImage = new Image[4];
        this.iSlider.dy = 0;
        this.iSlider.x = this.iScrollDown.x;
        this.trace[0].add(this.iScrollUp);
        this.trace[0].add(this.iSlider);
        this.trace[0].add(this.iScrollDown);
        final Graphics graphics = this.drawBuf.getGraphics();
        graphics.drawImage(this.BGImage, 0, 0, this);
        this.drawItemInstant(this.iScrollUp, graphics, null, false, false);
        this.drawItemInstant(this.iScrollDown, graphics, null, false, false);
        final int n2 = this.iScrollUp.x + this.BorderSpace;
        final int n3 = this.BorderSpace + this.iScrollUp.dy + this.LF[0].VSpace;
        this.sHeight = this.wHeight - this.iScrollUp.dy - this.iScrollDown.dy - 2 * this.LF[0].VSpace;
        graphics.clipRect(n2, n3, this.ScrollWidth, this.sHeight);
        graphics.drawImage(this.BGImageLow, 0, 0, this);
        for (int dy = this.iScrollBG.dy, i = n3 - dy + this.sHeight % dy / 2; i < n3 + this.sHeight; i += dy) {
            graphics.drawImage(this.iScrollBG.ShapeImage[0], n2, i, this);
        }
        graphics.dispose();
        this.ScrollBar = this.createImage(this.ScrollWidth, this.wHeight);
        final Graphics graphics2 = this.ScrollBar.getGraphics();
        graphics2.drawImage(this.drawBuf, -this.iScrollUp.x - this.BorderSpace, -this.BorderSpace, this);
        graphics2.dispose();
        this.ScrollWidth += this.LF[0].VSpace;
    }
    
    private void renderShapes(Item next) {
        while (next != null) {
            final int level = next.level;
            int n;
            if (level == 0 && this.ScrollBGColor != null) {
                n = (this.ScrollBGColor.getRGB() | 0xFF000000);
            }
            else {
                n = (this.BGColor.getRGB() & 0xFFFFFF);
            }
            if (next == this.iSlider) {
                n &= 0xFFFFFF;
            }
            Image[] shapeImage;
            if (next.down != null) {
                this.renderShapes(next.down);
                if (this.LF[level].ShapeType == this.LF[level + 1].ShapeType && this.LF[level].texthi == this.LF[level + 1].texthi) {
                    this.LF[level].ShapeImage = this.LF[level + 1].ShapeImage;
                }
                if (this.LF[level].ShapeType >= 0 && this.LF[level].ShapeImage[0] == null && next.ShapeImage[0] == null && next.dx1 > 0) {
                    final int texthi = this.LF[level].texthi;
                    final int[] array = new int[4 * texthi * texthi];
                    final int[] array2 = new int[texthi * texthi];
                    this.LF[level].ShapeImage[0] = this.drawShape(array, array2, texthi, this.LF[level].ShapeType, this.LF[level].ShapeColor, n, false);
                    this.LF[level].ShapeImage[1] = this.drawShape(array, array2, texthi, this.LF[level].ShapeType, this.LF[level].ActiveShapeColor, n, false);
                    this.LF[level].ShapeImage[2] = this.drawShape(array, array2, texthi, this.LF[level].ShapeType, this.LF[level].ShapeColor, n, true);
                    this.LF[level].ShapeImage[3] = this.drawShape(array, array2, texthi, this.LF[level].ShapeType, this.LF[level].ActiveShapeColor, n, true);
                }
                shapeImage = this.LF[level].ShapeImage;
            }
            else {
                if (this.LF[level].IconType == this.LF[level + 1].IconType && this.LF[level].texthi == this.LF[level + 1].texthi) {
                    this.LF[level].IconImage = this.LF[level + 1].IconImage;
                }
                if (this.LF[level].IconType >= 0 && this.LF[level].IconImage[0] == null && next.ShapeImage[0] == null && next.dx1 > 0) {
                    final int texthi2 = this.LF[level].texthi;
                    final int[] array3 = new int[4 * texthi2 * texthi2];
                    final int[] array4 = new int[texthi2 * texthi2];
                    this.LF[level].IconImage[0] = this.drawShape(array3, array4, texthi2, this.LF[level].IconType, this.LF[level].IconColor, n, false);
                    this.LF[level].IconImage[1] = this.drawShape(array3, array4, texthi2, this.LF[level].IconType, this.LF[level].ActiveIconColor, n, false);
                }
                shapeImage = this.LF[level].IconImage;
            }
            if (next.ShapeImage[0] == null && next.dx1 > 0) {
                next.ShapeImage = shapeImage;
            }
            next = next.next;
        }
    }
    
    private Image drawShape(final int[] array, final int[] array2, final int n, final int n2, final Color color, int n3, final boolean b) {
        for (int n4 = 4 * n * n, i = 0; i < n4; ++i) {
            array[i] = n3;
        }
        switch (n2) {
            case 112: {
                this.drawShape_v(array, 2 * n, n | 0x1, 2, color, !b, false);
                break;
            }
            case 80: {
                this.drawShape_v(array, 2 * n, 3 * n / 2 | 0x1, 2, color, !b, false);
                break;
            }
            case 118: {
                this.drawShape_v(array, 2 * n, 2 * n / 3 + 1, 1, color, false, b);
                break;
            }
            case 86: {
                this.drawShape_v(array, 2 * n, n, 1, color, false, b);
                break;
            }
            case 119: {
                this.drawShape_v(array, 2 * n, n | 0x1, 2, color, false, b);
                break;
            }
            case 87: {
                this.drawShape_v(array, 2 * n, 3 * n / 2 | 0x1, 2, color, false, b);
                break;
            }
            case 46: {
                this.drawShape_o(array, 2 * n, n / 3, color);
                break;
            }
            case 111: {
                this.drawShape_o(array, 2 * n, n / 2, color);
                break;
            }
            case 79: {
                this.drawShape_o(array, 2 * n, 2 * n / 3, color);
                break;
            }
            case 72: {
                this.drawShape_i(array, 2 * n, 3 * n / 2, color);
                break;
            }
            case 104: {
                this.drawShape_i(array, 2 * n, 4 * n / 3, color);
                break;
            }
            case 105: {
                this.drawShape_i(array, 2 * n, 2 * n / 3, color);
                break;
            }
            case 73: {
                this.drawShape_i(array, 2 * n, n, color);
                break;
            }
        }
        for (int j = 0, n5 = 0, n6 = 0; j < n; ++j, n5 += n * 4, n6 += n) {
            for (int k = 0, n7 = n5, n8 = n6; k < n; ++k, n7 += 2, ++n8) {
                n3 = (((array[n7] >> 24 & 0xFF) | (array[n7 + 1] >> 24 & 0xFF) | (array[n7 + 1 + 2 * n] >> 24 & 0xFF) | (array[n7 + 2 * n] >> 24 & 0xFF)) << 24 | ((array[n7] >> 16 & 0xFF) + (array[n7 + 1] >> 16 & 0xFF) + (array[n7 + 1 + 2 * n] >> 16 & 0xFF) + (array[n7 + 2 * n] >> 16 & 0xFF)) / 4 << 16 | ((array[n7] >> 8 & 0xFF) + (array[n7 + 1] >> 8 & 0xFF) + (array[n7 + 1 + 2 * n] >> 8 & 0xFF) + (array[n7 + 2 * n] >> 8 & 0xFF)) / 4 << 8 | ((array[n7] & 0xFF) + (array[n7 + 1] & 0xFF) + (array[n7 + 1 + 2 * n] & 0xFF) + (array[n7 + 2 * n] & 0xFF)) / 4);
                array2[n8] = n3;
            }
        }
        final Image image = this.createImage(new MemoryImageSource(n, n, array2, 0, n));
        try {
            this.tracker.addImage(image, 9999);
            this.tracker.waitForID(9999);
            this.tracker.removeImage(image);
        }
        catch (InterruptedException ex) {}
        return image;
    }
    
    private Image prepareBackground(final int n, final Color color) {
        Color color2 = this.BGColor;
        Image bgImage = this.BGImage;
        if (n == 255) {
            color2 = color;
            bgImage = null;
        }
        Image image;
        if (bgImage == null) {
            image = this.createImage(this.width, this.height);
            if (n > 0) {
                color2 = Blender.blendColors(color2, color, n);
            }
            final Graphics graphics = image.getGraphics();
            graphics.setColor(color2);
            graphics.fillRect(0, 0, this.width, this.height);
            graphics.dispose();
        }
        else {
            Image image2;
            if (n == 0) {
                image2 = bgImage;
            }
            else {
                image2 = this.createImage(new FilteredImageSource(bgImage.getSource(), new Blender(color, n)));
                this.tracker.addImage(image2, 9999);
                try {
                    this.tracker.waitForID(9999);
                }
                catch (InterruptedException ex) {}
                this.tracker.removeImage(image2);
            }
            final int width = image2.getWidth(this);
            final int height = image2.getHeight(this);
            if ((this.width != width || this.height != height) && width > 0 && height > 0 && this.BGOffsetX == 0 && this.BGOffsetY == 0) {
                image = this.createImage(this.width, this.height);
                final Graphics graphics2 = image.getGraphics();
                for (int i = -this.BGOffsetY; i < this.height; i += height) {
                    for (int j = -this.BGOffsetX; j < this.width; j += width) {
                        graphics2.drawImage(image2, j, i, this);
                    }
                }
                graphics2.dispose();
            }
            else {
                image = image2;
            }
        }
        return image;
    }
    
    public void paint(final Graphics graphics) {
        if (this.drawBuf != null) {
            graphics.drawImage(this.drawBuf, 0, 0, this);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.drawBuf != null) {
            graphics.drawImage(this.drawBuf, 0, 0, this);
        }
    }
    
    private void loadAppletParams() {
        if (this.getParameter("author") == null) {
            return;
        }
        final String parameter = this.getParameter("bgcolor");
        if (parameter != null) {
            this.BGColor = this.stringToColor(parameter);
        }
        final String parameter2 = this.getParameter("scrollbgcolor");
        if (parameter2 != null) {
            this.ScrollBGColor = this.stringToColor(parameter2);
        }
        final String parameter3 = this.getParameter("text");
        if (parameter3 != null) {
            this.TextColor = this.stringToColor(parameter3);
            this.ActiveTextColor = this.stringToColor(parameter3.substring(6));
            if (parameter3.length() >= 13 && parameter3.charAt(12) == '_') {
                this.Underlined = true;
            }
            else {
                this.Underlined = false;
            }
        }
        final String parameter4 = this.getParameter("tree");
        if (parameter4 != null) {
            this.TreeFile = parameter4;
        }
        this.TreeStructure = this.getParameter("items");
        final String parameter5 = this.getParameter("target");
        if (parameter5 != null) {
            this.Target = parameter5;
        }
        final String parameter6 = this.getParameter("border");
        if (parameter6 != null) {
            this.BorderSpace = Integer.decode(parameter6);
        }
        final String parameter7 = this.getParameter("advance");
        if (parameter7 != null) {
            this.LevelAdvance = Integer.decode(parameter7);
        }
        final String parameter8 = this.getParameter("bgoffsetx");
        if (parameter8 != null) {
            this.BGOffsetX = Integer.decode(parameter8);
        }
        final String parameter9 = this.getParameter("bgoffsety");
        if (parameter9 != null) {
            this.BGOffsetY = Integer.decode(parameter9);
        }
        final String parameter10 = this.getParameter("hspace");
        if (parameter10 != null) {
            this.HSpace = Integer.decode(parameter10);
        }
        final String parameter11 = this.getParameter("scroller");
        if (parameter11 != null) {
            this.SliderType = parameter11.charAt(0);
            final char lowerCase = Character.toLowerCase((char)this.SliderType);
            if (lowerCase == 'p' || lowerCase == 'v' || lowerCase == 'w' || lowerCase == 'o' || lowerCase == 'i' || lowerCase == 'h' || lowerCase == '.') {
                this.ScrollWidth = Integer.decode(parameter11.substring(1));
            }
        }
        final String parameter12 = this.getParameter("autocollapse");
        if (parameter12 != null) {
            this.AutoCollapse = parameter12.equalsIgnoreCase("yes");
        }
        final String parameter13 = this.getParameter("mouseover");
        if (parameter13 != null) {
            this.ExpandOnMouseOver = parameter13.equalsIgnoreCase("yes");
        }
        final String parameter14 = this.getParameter("menulayout");
        if (parameter14 != null) {
            this.MenuLayout = parameter14.equalsIgnoreCase("yes");
        }
        final String parameter15 = this.getParameter("ralign");
        if (parameter15 != null) {
            if (parameter15.equalsIgnoreCase("yes")) {
                this.RAlign = 1;
            }
            else if (parameter15.equalsIgnoreCase("full")) {
                this.RAlign = 2;
            }
            else {
                this.RAlign = 0;
            }
        }
        final String parameter16 = this.getParameter("highlight");
        if (parameter16 != null) {
            this.ColorLow = this.stringToColor(parameter16.substring(2));
            this.ColorHigh = this.stringToColor(parameter16.substring(10));
            this.AlphaLow = Integer.decode("0x" + parameter16.substring(0, 2));
            this.AlphaHigh = Integer.decode("0x" + parameter16.substring(8, 10));
        }
        final String parameter17 = this.getParameter("smoothup");
        if (parameter17 != null) {
            this.DropUpRate = Integer.decode(parameter17);
        }
        final String parameter18 = this.getParameter("smoothdown");
        if (parameter18 != null) {
            this.DropDownRate = Integer.decode(parameter18);
        }
        final String parameter19 = this.getParameter("smoothscroll");
        if (parameter19 != null) {
            this.ScrollRate = Integer.decode(parameter19);
        }
        final String parameter20 = this.getParameter("expanddelay");
        if (parameter20 != null) {
            this.ExpandDelay = Integer.decode(parameter20);
        }
        final String parameter21 = this.getParameter("linkprefix");
        if (parameter21 != null) {
            this.LinkPrefix = parameter21;
        }
        Font stringToFont = this.stringToFont("TimesRoman 0 14");
        Color stringToColor = new Color(255, 0, 0);
        Color stringToColor2 = new Color(255, 255, 0);
        int char1 = 112;
        Color stringToColor3 = new Color(175, 175, 0);
        Color stringToColor4 = new Color(255, 255, 0);
        int char2 = 111;
        int intValue = 0;
        for (int i = 0; i <= 16; ++i) {
            this.LF[i] = new Look_n_Feel();
            final String parameter22 = this.getParameter("font" + new Integer(i).toString());
            if (parameter22 != null) {
                final Font stringToFont2 = this.stringToFont(parameter22);
                if (stringToFont2 != null) {
                    stringToFont = stringToFont2;
                }
            }
            this.LF[i].font = stringToFont;
            final String parameter23 = this.getParameter("shape" + new Integer(i).toString());
            if (parameter23 != null) {
                char1 = parameter23.charAt(0);
                final char lowerCase2 = Character.toLowerCase((char)char1);
                if (lowerCase2 != 'p' && lowerCase2 != 'v' && lowerCase2 != 'w' && lowerCase2 != 'o' && lowerCase2 != 'i' && lowerCase2 != 'h' && lowerCase2 != '.') {
                    char1 = -1;
                }
                else {
                    stringToColor = this.stringToColor(parameter23.substring(1));
                    stringToColor2 = this.stringToColor(parameter23.substring(7));
                }
            }
            this.LF[i].ShapeColor = stringToColor;
            this.LF[i].ActiveShapeColor = stringToColor2;
            this.LF[i].ShapeType = char1;
            final String parameter24 = this.getParameter("icon" + new Integer(i).toString());
            if (parameter24 != null) {
                char2 = parameter24.charAt(0);
                final char lowerCase3 = Character.toLowerCase((char)char2);
                if (lowerCase3 != 'p' && lowerCase3 != 'v' && lowerCase3 != 'w' && lowerCase3 != 'o' && lowerCase3 != 'i' && lowerCase3 != 'h' && lowerCase3 != '.') {
                    char2 = -1;
                }
                else {
                    stringToColor3 = this.stringToColor(parameter24.substring(1));
                    stringToColor4 = this.stringToColor(parameter24.substring(7));
                }
            }
            this.LF[i].IconType = char2;
            this.LF[i].IconColor = stringToColor3;
            this.LF[i].ActiveIconColor = stringToColor4;
            final String parameter25 = this.getParameter("vspace" + new Integer(i).toString());
            if (parameter25 != null) {
                intValue = Integer.decode(parameter25);
            }
            this.LF[i].VSpace = intValue;
        }
        try {
            final String parameter26 = this.getParameter("bgimage");
            if (parameter26 != null) {
                this.BGImage = this.getImage(new URL(this.getDocumentBase(), parameter26));
                this.tracker.addImage(this.BGImage, 1);
            }
            this.loadScroller("scrollupimage", this.iScrollUp);
            this.loadScroller("scrolldownimage", this.iScrollDown);
            this.loadScroller("scrollbgimage", this.iScrollBG);
            this.loadScroller("sliderimage", this.iSlider);
        }
        catch (MalformedURLException ex) {}
    }
    
    private void loadScroller(final String s, final Item item) throws MalformedURLException {
        int index = 0;
        String s2 = this.getParameter(s);
        if (s2 == null) {
            return;
        }
        for (int n = 0; n < 4 && index >= 0; ++n) {
            index = s2.indexOf(124);
            String substring;
            if (index < 0) {
                substring = s2;
            }
            else {
                substring = s2.substring(0, index);
                s2 = s2.substring(index + 1);
            }
            final Image[] shapeImage = item.ShapeImage;
            final int n2 = n;
            final Image[] shapeImage2 = item.ShapeImage;
            final int n3 = n + 2;
            final Image image = this.getImage(new URL(this.getDocumentBase(), substring));
            shapeImage[n2] = (shapeImage2[n3] = image);
            this.ScrollBar = image;
            this.tracker.addImage(item.ShapeImage[n], 1);
        }
    }
    
    private Color stringToColor(final String s) {
        return new Color(Integer.decode("0x" + s.substring(0, 2)), Integer.decode("0x" + s.substring(2, 4)), Integer.decode("0x" + s.substring(4, 6)));
    }
    
    private Font stringToFont(String trim) {
        int intValue = 0;
        int intValue2 = 10;
        trim = trim.trim();
        int n = trim.indexOf(32);
        if (n < 0) {
            n = trim.length();
        }
        final String trim2 = trim.substring(0, n).trim();
        if (n != trim.length()) {
            int n2 = trim.indexOf(32, n + 1);
            if (n2 < 0) {
                n2 = trim.length();
            }
            intValue = Integer.decode(trim.substring(n + 1, n2).trim());
            if (n2 != trim.length()) {
                intValue2 = Integer.decode(trim.substring(n2 + 1).trim());
            }
        }
        return new Font(trim2, intValue, intValue2);
    }
    
    private void loadTree() {
        final Item[] array = new Item[16];
        OutputStream outputStream = null;
        final StringBuffer sb = new StringBuffer();
        Socket socket = null;
        final byte[] array2 = new byte[512];
        try {
            if (this.TreeStructure == null) {
                final URL url = new URL(this.getDocumentBase(), this.TreeFile);
                InputStream inputStream = null;
                Label_0203: {
                    try {
                        inputStream = url.openConnection().getInputStream();
                    }
                    catch (IOException ex2) {
                        try {
                            final URLConnection openConnection = url.openConnection();
                            openConnection.setUseCaches(false);
                            openConnection.setIfModifiedSince(0L);
                            inputStream = openConnection.getInputStream();
                        }
                        catch (IOException ex) {
                            if (url.getProtocol().equalsIgnoreCase("http")) {
                                int port = url.getPort();
                                if (port < 0) {
                                    port = 80;
                                }
                                socket = new Socket(url.getHost(), port);
                                outputStream = socket.getOutputStream();
                                outputStream.write(("GET " + url.toExternalForm() + "\n\n").getBytes());
                                inputStream = socket.getInputStream();
                                break Label_0203;
                            }
                            throw ex;
                        }
                    }
                }
                this.TreeStructure = "";
                int read;
                while ((read = inputStream.read(array2)) > 0) {
                    this.TreeStructure = String.valueOf(this.TreeStructure) + new String(array2, 0, read);
                }
                inputStream.close();
                if (outputStream != null) {
                    outputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            }
            else {
                final int length = this.TreeStructure.length();
                final char[] array3 = new char[length];
                this.TreeStructure.getChars(0, length, array3, 0);
                for (int i = 0; i < length - 1; ++i) {
                    if (array3[i] < ' ') {
                        array3[i] = '\u0001';
                    }
                    else if (array3[i] == '%' && array3[i + 1] == '%') {
                        array3[i] = '\n';
                        ++i;
                        array3[i] = '\u0001';
                        if (i < length - 1 && array3[i + 1] == ' ') {
                            array3[i + 1] = '\u0001';
                        }
                    }
                }
                this.TreeStructure = new String(array3);
            }
            StringReader stringReader;
            int j;
            for (stringReader = new StringReader(this.TreeStructure), j = stringReader.read(); j == 1; j = stringReader.read()) {}
            while (j >= 0) {
                int n = 0;
                while (j == 46) {
                    ++n;
                    j = stringReader.read();
                }
                if (j < 0) {
                    break;
                }
                if (n >= 16) {
                    continue;
                }
                final Item downLast = new Item();
                downLast.level = n + 1;
                if (array[n] == null) {
                    if (n == 0) {
                        this.tree = downLast;
                    }
                    else {
                        if (array[n - 1] == null) {
                            break;
                        }
                        array[n - 1].down = downLast;
                        array[n - 1].downLast = downLast;
                    }
                }
                else {
                    array[n].next = downLast;
                    downLast.prev = array[n];
                    if (n > 0) {
                        array[n - 1].downLast = downLast;
                    }
                }
                array[n] = downLast;
                if (n > 0) {
                    downLast.up = array[n - 1];
                }
                for (int k = n + 1; k < 16; ++k) {
                    array[k] = null;
                }
                if (j == 43) {
                    downLast.InitialExpanded = true;
                    j = stringReader.read();
                }
                int n3;
                int n2 = n3 = 0;
                final int n4;
                if ((n4 = j) == 38) {
                    j = stringReader.read();
                    if (j == 112 || j == 115) {
                        if (j == 115) {
                            downLast.ScrollType = true;
                        }
                        int n5 = stringReader.read();
                        if (downLast.ScrollType && n5 == 40) {
                            for (int n6 = stringReader.read(); n6 >= 48 && n6 <= 57; n6 = stringReader.read()) {
                                downLast.advance = downLast.advance * 10 + n6 - 48;
                            }
                            n5 = stringReader.read();
                        }
                        if (n5 == 95) {
                            downLast.NoIcon = true;
                        }
                        n3 = n5 - 48;
                        if (n3 < 0 || n3 > 4 || n3 == 3) {
                            n3 = 0;
                        }
                        n2 = stringReader.read() - 48;
                        if (n2 < 0 || n2 > 4 || n2 == 3) {
                            n2 = 0;
                        }
                        j = stringReader.read();
                    }
                }
                for (int l = 0; l < n3; ++l) {
                    if (j == 124) {
                        j = this.scanField(stringReader, sb, -1);
                        final String trim = sb.toString().trim();
                        try {
                            downLast.ShapeImage[l] = this.getImage(new URL(this.getDocumentBase(), trim));
                            this.tracker.addImage(downLast.ShapeImage[l], 2);
                        }
                        catch (MalformedURLException ex3) {
                            downLast.ShapeImage[l] = null;
                        }
                    }
                    else {
                        downLast.ShapeImage[l] = null;
                    }
                }
                for (int n7 = 0; n7 < n2; ++n7) {
                    if (j == 124) {
                        j = this.scanField(stringReader, sb, -1);
                        final String trim2 = sb.toString().trim();
                        try {
                            downLast.TextImage[n7] = this.getImage(new URL(this.getDocumentBase(), trim2));
                            this.tracker.addImage(downLast.TextImage[n7], 3);
                        }
                        catch (MalformedURLException ex4) {
                            downLast.TextImage[n7] = null;
                        }
                    }
                    else {
                        downLast.TextImage[n7] = null;
                    }
                }
                if (n4 == 38 && n2 == 0) {
                    j = stringReader.read();
                }
                if (n2 == 0) {
                    j = this.scanField(stringReader, sb, j);
                    String s = sb.toString();
                    if (s.trim().length() > 0) {
                        int index;
                        while ((index = s.indexOf("$$")) >= 0) {
                            downLast.text[downLast.nLines++] = s.substring(0, index);
                            s = s.substring(index + 2);
                        }
                        downLast.text[downLast.nLines++] = s;
                    }
                }
                String trim3;
                if (j == 124) {
                    j = this.scanField(stringReader, sb, -1);
                    trim3 = sb.toString().trim();
                }
                else {
                    trim3 = "";
                }
                if (trim3.length() > 0) {
                    try {
                        downLast.link = new URL(null, trim3);
                    }
                    catch (MalformedURLException ex5) {
                        try {
                            downLast.link = new URL(this.getDocumentBase(), String.valueOf(this.LinkPrefix) + trim3);
                        }
                        catch (MalformedURLException ex6) {}
                    }
                }
                downLast.target = this.Target;
                if (j == 124) {
                    j = this.scanField(stringReader, sb, -1);
                    final String trim4 = sb.toString().trim();
                    if (trim4.length() > 0) {
                        downLast.target = trim4;
                    }
                }
                while (j >= 0 && j != 10) {
                    j = stringReader.read();
                }
                for (j = stringReader.read(); j == 1; j = stringReader.read()) {}
            }
            stringReader.close();
        }
        catch (IOException ex7) {}
    }
    
    private int scanField(final StringReader stringReader, final StringBuffer sb, int n) throws IOException {
        sb.setLength(0);
        if (n < 0) {
            n = stringReader.read();
        }
        while (n >= 0 && n != 10 && n != 124) {
            if (n >= 32) {
                sb.append((char)n);
            }
            n = stringReader.read();
        }
        return n;
    }
    
    private void placeItems() {
        int n = 0;
        for (int i = 1; i < this.trace.length; ++i) {
            this.trace[i].clear();
        }
        if (this.MenuLayout) {
            n = this.HSpace / 2;
        }
        this.placeSubtree(n, this.MaxY = 0, this.tree, 1);
    }
    
    private int placeSubtree(int x, int n, Item next, int n2) {
        while (next != null) {
            next.x = x;
            next.y = n;
            this.trace[n2].add(next);
            int placeSubtree = n + next.dy;
            if (next.expanded && next.down != null) {
                placeSubtree = this.placeSubtree(x + next.advance, placeSubtree + this.LF[next.down.level].VSpace, next.down, n2);
            }
            if (this.MenuLayout && next.level == 1) {
                x += next.dx1 + next.dx2 + this.HSpace;
                ++n2;
            }
            else if (next.next != null) {
                n = placeSubtree + this.LF[next.level].VSpace;
            }
            else {
                n = placeSubtree;
            }
            next = next.next;
        }
        if (this.MaxY < n) {
            this.MaxY = n;
        }
        return n;
    }
    
    private void drawAll(final boolean b) {
        if (this.busy && !b) {
            return;
        }
        this.busy = true;
        Graphics graphics;
        if (!b) {
            if (this.OffsetY > this.MaxY) {
                this.OffsetY = this.MaxY;
            }
            if (this.MaxY <= this.wHeight || this.ScrollWidth <= 0) {
                if (this.OffsetY > 0) {
                    this.animateScrolling(0);
                }
                graphics = this.drawBuf.getGraphics();
                graphics.drawImage(this.BGImage, 0, 0, this);
                graphics.clipRect(this.BorderSpace, this.BorderSpace, this.width - 2 * this.BorderSpace, this.wHeight);
                this.ScrollOn = false;
            }
            else {
                if (this.iEnsureVis != null) {
                    this.SlideBy = this.iEnsureVis.y + this.iEnsureVis.dy - this.wHeight - this.OffsetY;
                    if (this.SlideBy < 0) {
                        this.SlideBy = 0;
                    }
                }
                if (this.OffsetY + this.wHeight + this.SlideBy > this.MaxY) {
                    this.SlideBy = this.MaxY - this.wHeight - this.OffsetY;
                }
                if (this.OffsetY + this.SlideBy < 0) {
                    this.SlideBy = -this.OffsetY;
                }
                this.animateScrolling(this.OffsetY + this.SlideBy);
                graphics = this.drawBuf.getGraphics();
                graphics.drawImage(this.BGImage, 0, 0, this);
                this.drawScroller(graphics);
                graphics.clipRect(this.BorderSpace, this.BorderSpace, this.width - 2 * this.BorderSpace - this.ScrollWidth, this.wHeight);
                this.ScrollOn = true;
            }
            this.iEnsureVis = null;
            this.SlideBy = 0;
        }
        else {
            graphics = this.drawBuf.getGraphics();
            if (this.ScrollOn) {
                graphics.clipRect(this.BorderSpace, this.BorderSpace, this.width - 2 * this.BorderSpace - this.ScrollWidth, this.wHeight);
            }
            else {
                graphics.clipRect(this.BorderSpace, this.BorderSpace, this.width - 2 * this.BorderSpace, this.wHeight);
            }
            graphics.drawImage(this.BGImage, 0, 0, this);
        }
        for (int i = 1; i < this.trace.length; ++i) {
            this.drawColumn(graphics, this.trace[i]);
        }
        graphics.dispose();
        if (b || this.dragY >= 0) {
            final Graphics graphics2 = this.getGraphics();
            graphics2.drawImage(this.drawBuf, 0, 0, this);
            graphics2.dispose();
            return;
        }
        this.repaint();
        this.busy = false;
        this.checkPoint();
    }
    
    private void drawColumn(final Graphics graphics, final YTrace yTrace) {
        final int itemCount = yTrace.getItemCount();
        final Item at = yTrace.at(0, this.OffsetY, false);
        if (at == null) {
            return;
        }
        for (int i = at.iTrace; i < itemCount; ++i) {
            final Item at2 = yTrace.at(i);
            final int n = at2.x + this.BorderSpace;
            final int n2 = this.BorderSpace + at2.y - this.OffsetY;
            if (n2 >= this.height) {
                break;
            }
            final Graphics create = graphics.create(n, n2, at2.dx1 + at2.dx2, at2.dy);
            this.drawItem(create, at2, n, n2, at2 == this.iShape, at2 == this.iText);
            create.dispose();
        }
    }
    
    private void drawItem(final Graphics graphics, final Item item, final int n, final int n2, final boolean b, final boolean b2) {
        int n3;
        if (b) {
            n3 = 1;
        }
        else {
            n3 = 0;
        }
        int n4;
        if (b2) {
            n4 = 1;
            graphics.drawImage(this.BGImageHigh, -n, -n2, this);
            graphics.setColor(this.ActiveTextColor);
        }
        else {
            n4 = 0;
            graphics.drawImage(this.BGImageLow, -n, -n2, this);
            graphics.setColor(this.TextColor);
        }
        if (item.expanded) {
            n3 += 2;
            n4 += 2;
        }
        if (item.ShapeImage[n3] != null) {
            graphics.drawImage(item.ShapeImage[n3], 0, 0, this);
        }
        if (item.TextImage[n4] == null) {
            if (item.nLines > 0) {
                if (graphics.getFont() != this.LF[item.level].font) {
                    graphics.setFont(this.LF[item.level].font);
                }
                for (int i = 0, baseline = item.baseline; i < item.nLines; ++i, baseline += this.LF[item.level].texthi) {
                    graphics.drawString(item.text[i], item.dx1, baseline);
                    if (this.Underlined && item.link != null) {
                        graphics.drawLine(item.dx1, baseline + 1, item.dx1 + item.textlen[i], baseline + 1);
                    }
                }
            }
        }
        else {
            graphics.drawImage(item.TextImage[n4], item.dx1, item.baseline, this);
        }
    }
    
    private void drawScroller(final Graphics graphics) {
        final int n = this.ScrollWidth - this.LF[0].VSpace;
        final int n2 = this.SliderHeight / 3;
        final int n3 = this.SliderHeight - 2 * n2;
        int n4 = (this.sHeight * this.wHeight / this.MaxY - 2 * n2) / n3;
        if (n4 < 0) {
            n4 = 0;
        }
        final int dy = 2 * n2 + n4 * n3;
        final int y = this.OffsetY * (this.sHeight - dy) / (this.MaxY - this.wHeight) + this.iScrollUp.dy + this.LF[0].VSpace;
        final int n5 = this.iScrollUp.x + this.BorderSpace;
        if (this.iSlider.dy != dy) {
            this.iSlider.ShapeImage[0] = this.createImage(n, dy);
            this.iSlider.ShapeImage[1] = this.createImage(n, dy);
            this.iSlider.dy = dy;
        }
        this.iSlider.y = y;
        int n6;
        if (this.iShape == this.iSlider) {
            n6 = 0;
        }
        else {
            n6 = 1;
        }
        for (int i = 0; i < 2; ++i, n6 = 1 - n6) {
            graphics.drawImage(this.ScrollBar, n5, this.BorderSpace, this);
            final int n7 = y + this.BorderSpace;
            final Graphics create = graphics.create(n5, n7, n, n2);
            create.drawImage(this.SliderImage[n6], 0, 0, this);
            create.dispose();
            int n8 = n7 + n2;
            for (int j = 0; j < n4; ++j, n8 += n3) {
                final Graphics create2 = graphics.create(n5, n8, n, n3);
                create2.drawImage(this.SliderImage[n6], 0, -n2, this);
                create2.dispose();
            }
            final Graphics create3 = graphics.create(n5, n8, n, n2);
            create3.drawImage(this.SliderImage[n6], 0, -n2 - n3, this);
            create3.dispose();
            final Graphics graphics2 = this.iSlider.ShapeImage[n6].getGraphics();
            graphics2.drawImage(this.drawBuf, -n5, -y - this.BorderSpace, this);
            graphics2.dispose();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.busy || this.iShape != this.iSlider) {
            return;
        }
        final int n = mouseEvent.getY() - this.mouseY;
        if (n == 0) {
            return;
        }
        this.OffsetY = this.dragY + (this.MaxY - this.wHeight) * n / (this.sHeight - this.iSlider.dy);
        if (this.OffsetY < 0) {
            this.OffsetY = 0;
        }
        else if (this.OffsetY + this.wHeight > this.MaxY) {
            this.OffsetY = this.MaxY - this.wHeight;
        }
        this.drawAll(false);
        this.busy = false;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.mouseX = -1;
        this.mouseY = -1;
        if (this.busy) {
            return;
        }
        if (this.DelayedExpandThread != null) {
            try {
                this.DelayedExpandThread.stop();
                this.DelayedExpandThread = null;
            }
            catch (SecurityException ex) {}
        }
        this.checkPoint();
        if (this.AutoCollapse && this.ExpandOnMouseOver) {
            this.collapseAll();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final Item iText = this.iText;
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
        if (this.busy) {
            return;
        }
        if (this.DelayedExpandThread != null) {
            try {
                this.DelayedExpandThread.stop();
                this.DelayedExpandThread = null;
            }
            catch (SecurityException ex) {}
        }
        this.checkPoint();
        if (this.iText != iText) {
            this.iOldText = null;
        }
        if (!this.ExpandOnMouseOver || this.iText == null || this.iText.expanded || this.iText.down == null || this.iText == this.iOldText) {
            return;
        }
        if (this.ExpandDelay == 0) {
            this.expandAnimated(this.iText, false, false);
            this.iOldText = this.iText;
            return;
        }
        this.OverEventTime = System.currentTimeMillis();
        try {
            (this.DelayedExpandThread = new Thread(this)).start();
        }
        catch (SecurityException ex2) {
            this.expandAnimated(this.iText, false, false);
            this.iOldText = this.iText;
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
        if (this.busy) {
            return;
        }
        if (this.DelayedExpandThread != null) {
            try {
                this.DelayedExpandThread.stop();
                this.DelayedExpandThread = null;
            }
            catch (SecurityException ex) {}
        }
        this.checkPoint();
        this.iTextPressed = this.iText;
        Item item;
        if (this.iText != null && this.ShiftPressed) {
            if (this.iText.down == null) {
                return;
            }
            item = this.iText;
        }
        else {
            if (this.iShape == null || this.iShape.down == null) {
                return;
            }
            item = this.iShape;
        }
        if (item == this.iScrollUp) {
            this.upPressed();
            return;
        }
        if (item == this.iScrollDown) {
            this.downPressed();
            return;
        }
        if (item == this.iSlider) {
            this.dragY = this.OffsetY;
            return;
        }
        if (item.expanded) {
            this.collapseAnimated(item, false);
            return;
        }
        this.expandAnimated(item, false, this.ShiftPressed && this.iShape != null);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
        this.dragY = -1;
        if (this.busy) {
            return;
        }
        if (this.DelayedExpandThread != null) {
            try {
                this.DelayedExpandThread.stop();
                this.DelayedExpandThread = null;
            }
            catch (SecurityException ex) {}
        }
        this.checkPoint();
        if (this.ShiftPressed || this.iText == null || this.iText.link == null || this.iTextPressed != this.iText) {
            return;
        }
        this.getAppletContext().showDocument(this.iText.link, this.iText.target);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 16) {
            this.ShiftPressed = true;
        }
        if (this.busy || !this.ScrollOn) {
            return;
        }
        if (keyEvent.getKeyCode() == 38) {
            this.upPressed();
        }
        if (keyEvent.getKeyCode() == 40) {
            this.downPressed();
        }
    }
    
    private void upPressed() {
        if (this.OffsetY <= 0) {
            return;
        }
        final Item at = this.trace[1].at(0, this.OffsetY - this.LF[1].texthi * 3 / 2, false);
        if (at == null) {
            this.SlideBy = -this.LF[1].texthi * 2;
        }
        else {
            this.SlideBy = at.y - this.OffsetY;
        }
        final Item item = null;
        this.iShape = item;
        this.iText = item;
        this.drawAll(false);
    }
    
    private void downPressed() {
        if (this.OffsetY >= this.MaxY + this.wHeight) {
            return;
        }
        if (this.trace.length == 2) {
            this.iEnsureVis = this.trace[1].at(0, this.OffsetY + this.wHeight + this.LF[1].texthi * 2, false);
        }
        this.SlideBy = this.LF[1].texthi * 2;
        final Item item = null;
        this.iShape = item;
        this.iText = item;
        this.drawAll(false);
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 16) {
            this.ShiftPressed = false;
        }
    }
    
    private void checkPoint() {
        Item item = null;
        if (this.busy) {
            return;
        }
        final int n = this.mouseX - this.BorderSpace;
        final int n2 = this.mouseY - this.BorderSpace;
        int n3 = this.width - this.BorderSpace;
        if (this.ScrollOn) {
            item = this.trace[0].at(n, n2, true);
            n3 -= this.ScrollWidth;
        }
        final int n4 = n2 + this.OffsetY;
        if (this.mouseX < n3) {
            for (int n5 = 1; n5 < this.trace.length && item == null; item = this.trace[n5].at(n, n4, true), ++n5) {}
        }
        Item iShape;
        Item iText;
        if (item == null) {
            iShape = null;
            iText = null;
        }
        else if (n >= item.x + item.dx1) {
            iText = item;
            iShape = null;
            if (item.link == null && item.down == null) {
                iText = null;
            }
            if (item.link == null || item.down == null) {
                iShape = iText;
            }
        }
        else {
            iShape = item;
            iText = null;
            if (item.down == null && item.link == null) {
                iShape = null;
            }
            if ((item.link == null || item.down == null) && item.level > 0) {
                iText = iShape;
            }
        }
        if (this.iShape == iShape && this.iText == iText) {
            return;
        }
        final Graphics graphics = this.getGraphics();
        final Graphics graphics2 = this.drawBuf.getGraphics();
        if (this.iShape != null) {
            this.drawItemInstant(this.iShape, graphics2, graphics, false, false);
        }
        else if (this.iText != null) {
            this.drawItemInstant(this.iText, graphics2, graphics, false, false);
        }
        this.iShape = iShape;
        this.iText = iText;
        if (this.iShape != null) {
            this.drawItemInstant(this.iShape, graphics2, graphics, true, this.iText != null);
        }
        else if (this.iText != null) {
            this.drawItemInstant(this.iText, graphics2, graphics, false, true);
        }
        graphics.dispose();
        graphics2.dispose();
        if (iText == null) {
            this.showStatus("");
            this.setCursor(this.DefaultCursor);
            return;
        }
        if (iText.link != null) {
            this.showStatus(iText.link.toExternalForm());
            this.setCursor(this.HandCursor);
            return;
        }
        this.showStatus("");
        this.setCursor(this.DefaultCursor);
    }
    
    private void drawItemInstant(final Item item, Graphics create, final Graphics graphics, final boolean b, final boolean b2) {
        final int n = item.x + this.BorderSpace;
        int n2 = item.y + this.BorderSpace;
        if (item.trace != this.trace[0]) {
            create = create.create();
            n2 -= this.OffsetY;
            if (this.ScrollOn) {
                create.clipRect(this.BorderSpace, this.BorderSpace, this.width - 2 * this.BorderSpace - this.ScrollWidth, this.wHeight);
            }
            else {
                create.clipRect(this.BorderSpace, this.BorderSpace, this.width - 2 * this.BorderSpace, this.wHeight);
            }
        }
        final Graphics create2 = create.create(n, n2, item.dx1 + item.dx2, item.dy);
        this.drawItem(create2, item, n, n2, b, b2);
        create2.dispose();
        if (graphics == null) {
            return;
        }
        final Graphics create3 = graphics.create(n, n2, item.dx1 + item.dx2, item.dy);
        create3.drawImage(this.drawBuf, -n, -n2, this);
        create3.dispose();
    }
    
    private void drawShape_v(final int[] array, final int n, int n2, final int n3, final Color color, final boolean b, final boolean b2) {
        final int red;
        final int n4 = red = color.getRed();
        final int green;
        final int n5 = green = color.getGreen();
        final int blue;
        final int n6 = blue = color.getBlue();
        final int n7 = n4 << 16 | n5 << 8 | n6 | 0xFF000000;
        final int n8 = (n4 + 255) / 2 << 16 | (n5 + 255) / 2 << 8 | (n6 + 255) / 2 | 0xFF000000;
        final int n9 = red / 2 << 16 | green / 2 << 8 | blue / 2 | 0xFF000000;
        int n10;
        int n11;
        if (b) {
            n10 = n;
            n11 = 1;
        }
        else {
            n10 = 1;
            n11 = n;
        }
        int n12;
        int n13;
        int n14;
        int n15;
        if (b2) {
            n12 = -n11;
            n13 = n9;
            n14 = n8;
            n15 = n7;
        }
        else {
            n12 = n11;
            n13 = n8;
            n14 = n7;
            n15 = n9;
        }
        int i = n2 / n3 + n3 / 2;
        final int n16 = (n - n2) / 2;
        int n17 = (n - i) / 2;
        if (b2) {
            n17 = n - n17 - 1;
        }
        int n18 = n16 * n10 + n17 * n11;
        int n19 = 0;
        for (int n20 = 1; i > 0; --i, --n20) {
            int n21 = n18;
            for (int j = n2; j > 1; --j) {
                array[n21] = n13;
                n21 += n10;
            }
            if (n20 >= 0) {
                array[n21 - n10] = (array[n21] = n15);
                array[n18] = n7;
            }
            else {
                array[n18] = n14;
                array[n21] = n15;
                if (n2 > 3) {
                    array[n18 + n10] = n14;
                    array[n21 - n10] = n15;
                }
            }
            n19 += n3;
            if ((n19 & 0x1) == 0x0) {
                n2 -= 2;
                n18 += n10;
            }
            n18 += n12;
            if (n20 == 0) {
                n13 = n7;
            }
        }
    }
    
    private void drawShape_o(final int[] array, final int n, int n2, final Color color) {
        if (n2 < 1) {
            n2 = 1;
        }
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        for (int i = -n2, n3 = (n / 2 - n2 + 1) * n + (n / 2 - n2 + 1); i <= n2; ++i, n3 += n) {
            for (int j = -n2, n4 = n3; j <= n2; ++j, ++n4) {
                if (j * j + i * i < n2 * n2) {
                    final double n5 = (n2 * n2 * 18.0 / 4.0 - (j + n2 / 2) * (j + n2 / 2) - (i + n2 / 2) * (i + n2 / 2)) * 4.0 / (n2 * n2 * 18);
                    int n7;
                    int n8;
                    int n9;
                    if (n5 > 0.95) {
                        final double n6 = (n5 - 0.95) / 0.050000000000000044 * 0.7;
                        n7 = (int)(red + (255 - red) * n6);
                        n8 = (int)(green + (255 - green) * n6);
                        n9 = (int)(blue + (255 - blue) * n6);
                    }
                    else {
                        final double n10 = n5 / 0.95;
                        n7 = (int)(red * n10 * n10);
                        n8 = (int)(green * n10 * n10);
                        n9 = (int)(blue * n10 * n10);
                    }
                    array[n4] = (n7 << 16 | n8 << 8 | n9 | 0xFF000000);
                }
            }
        }
    }
    
    private void drawShape_i(final int[] array, final int n, int n2, final Color color) {
        if ((n & 0x1) == (n2 & 0x1)) {
            --n2;
        }
        int n3 = (n - n2) / 2 + 1;
        final double n4 = n2 / 3.0;
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        for (int i = 0; i < n2; ++i, ++n3) {
            final double n5 = (n2 * n2 * 18.0 / 16.0 - (i - n4) * (i - n4)) * 16.0 / (n2 * n2 * 18);
            int n7;
            int n8;
            int n9;
            if (n5 > 0.95) {
                final double n6 = (n5 - 0.95) / 0.050000000000000044 * 0.7;
                n7 = (int)(red + (255 - red) * n6);
                n8 = (int)(green + (255 - green) * n6);
                n9 = (int)(blue + (255 - blue) * n6);
            }
            else {
                final double n10 = n5 / 0.95;
                n7 = (int)(red * n10 * n10);
                n8 = (int)(green * n10 * n10);
                n9 = (int)(blue * n10 * n10);
            }
            final int n11 = n7 << 16 | n8 << 8 | n9 | 0xFF000000;
            for (int j = 0, n12 = n3; j < n; ++j, n12 += n) {
                array[n12] = n11;
            }
        }
    }
    
    private void expandAnimated(final Item item, final boolean b, final boolean b2) {
        this.busy = true;
        if (this.AutoCollapse && !b) {
            for (Item up = item; up != null; up = up.up) {
                Item item2;
                if (up.up == null) {
                    item2 = this.tree;
                }
                else {
                    item2 = up.up.down;
                }
                while (item2 != null) {
                    if (item2 != up && item2.expanded && item2.down != null) {
                        this.collapseAnimated(item2, true);
                    }
                    item2 = item2.next;
                }
            }
            this.drawAll(this.busy = false);
            final Graphics graphics = this.getGraphics();
            graphics.drawImage(this.drawBuf, 0, 0, this);
            graphics.dispose();
            this.busy = true;
        }
        item.expanded = true;
        this.placeItems();
        this.animateDrop(item, false);
        if (b2) {
            for (Item item3 = item.down; item3 != null; item3 = item3.next) {
                if (item3.y - this.OffsetY >= this.wHeight) {
                    item3.expandRecursive();
                    this.placeItems();
                    this.iEnsureVis = null;
                    break;
                }
                if (item3.down != null && !item3.expanded) {
                    this.expandAnimated(item3, true, true);
                }
            }
        }
        if (!b) {
            this.drawAll(this.busy = false);
        }
    }
    
    private void collapseAnimated(final Item item, final boolean b) {
        this.busy = true;
        for (Item item2 = item.downLast; item2 != null; item2 = item2.prev) {
            final Item at = item2.trace.at(item2.trace.getItemCount() - 1);
            if (at.y + at.dy - this.OffsetY < 0) {
                item2.collapseRecursive();
                this.placeItems();
                break;
            }
            if (item2.downLast != null && item2.expanded) {
                if (item2.y - this.OffsetY >= this.wHeight) {
                    item2.downLast.collapseRecursive();
                    item2.expanded = false;
                    this.placeItems();
                }
                else {
                    this.collapseAnimated(item2, true);
                }
            }
        }
        this.animateDrop(item, true);
        item.expanded = false;
        this.placeItems();
        if (!b) {
            this.drawAll(this.busy = false);
        }
    }
    
    private void animateDrop(final Item item, final boolean b) {
        int x = 99999;
        int n = 0;
        final YTrace trace = item.trace;
        if (item.y - this.OffsetY >= this.wHeight) {
            return;
        }
        final int n2 = item.iTrace + 1;
        int n3;
        int n4;
        for (n3 = trace.getItemCount() - 1, n4 = n2; n4 < n3 && trace.at(n4 + 1).level > item.level; ++n4) {}
        for (int i = n2; i <= n3; ++i) {
            final Item at = trace.at(i);
            if (at.x < x) {
                x = at.x;
            }
            if (at.x + at.dx1 + at.dx2 > n) {
                n = at.x + at.dx1 + at.dx2;
            }
        }
        final int n5 = x + this.BorderSpace;
        int n6 = n + this.BorderSpace;
        int n7 = this.width - this.BorderSpace;
        if (this.ScrollOn) {
            n7 -= this.ScrollWidth;
        }
        if (n6 > n7) {
            n6 = n7;
        }
        final int n8 = item.y + item.dy;
        final Item at2 = trace.at(n3);
        int n9 = at2.y + at2.dy - n8;
        final Item at3 = trace.at(n4);
        final int n10 = at3.y + at3.dy - item.y - item.dy;
        final Graphics graphics = this.drawBuf.getGraphics();
        final Graphics create = graphics.create();
        final int n11 = n8 + (this.BorderSpace - this.OffsetY);
        if (n11 + n9 > this.BorderSpace + this.wHeight) {
            n9 = this.BorderSpace + this.wHeight - n11;
        }
        graphics.clipRect(n5, n11, n6 - n5, n9);
        final Graphics graphics2 = this.getGraphics();
        final Graphics create2 = graphics2.create();
        graphics2.clipRect(n5, n11, n6 - n5, n9);
        int n12;
        if (b) {
            n12 = this.DropUpRate;
        }
        else {
            n12 = this.DropDownRate;
            this.drawItemInstant(item, create, create2, item == this.iShape, item == this.iText);
            this.iEnsureVis = trace.at(n4);
        }
        int n13;
        if (n12 <= 0) {
            n13 = n10;
        }
        else {
            n13 = 0;
            if (n12 * 50 < n10) {
                n12 = n10 / 50 + 1;
            }
        }
        long currentTimeMillis = System.currentTimeMillis();
        int n14 = 0;
        while (true) {
            graphics.drawImage(this.BGImage, 0, 0, this);
            int n15;
            if (b) {
                n15 = -n13 - this.OffsetY;
            }
            else {
                n15 = n13 - n10 - this.OffsetY;
            }
            for (int j = n2; j <= n3; ++j) {
                final Item at4 = trace.at(j);
                final int n16 = at4.y + n15;
                if (n16 >= this.wHeight) {
                    break;
                }
                final Graphics create3 = graphics.create(at4.x + this.BorderSpace, n16 + this.BorderSpace, at4.dx1 + at4.dx2, at4.dy);
                this.drawItem(create3, at4, at4.x + this.BorderSpace, n16 + this.BorderSpace, at4 == this.iShape, at4 == this.iText);
                create3.dispose();
            }
            graphics2.drawImage(this.drawBuf, 0, 0, this);
            if (n13 >= n10) {
                break;
            }
            final long currentTimeMillis2 = System.currentTimeMillis();
            int n17 = n14 + (int)(currentTimeMillis2 - currentTimeMillis);
            currentTimeMillis = currentTimeMillis2;
            int n18;
            if (n17 * n12 < 100) {
                try {
                    Thread.sleep(100 / n12 - n17);
                }
                catch (InterruptedException ex) {}
                n18 = 1;
                final long currentTimeMillis3 = System.currentTimeMillis();
                n17 += (int)(currentTimeMillis3 - currentTimeMillis);
                currentTimeMillis = currentTimeMillis3;
            }
            else {
                n18 = (n17 * n12 + 50) / 100;
            }
            n13 += n18;
            if (n13 > n10) {
                n13 = n10;
            }
            n14 = n17 - n18 * 100 / n12;
        }
        create.dispose();
        create2.dispose();
        graphics.dispose();
        graphics2.dispose();
    }
    
    private void animateScrolling(final int offsetY) {
        boolean b = true;
        if (this.OffsetY != offsetY && this.ScrollRate > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            int n = 0;
            int n2 = 0;
            int n3 = offsetY - this.OffsetY;
            if (n3 < 0) {
                n3 = -n3;
                b = false;
            }
            int scrollRate;
            if (this.ScrollRate * 50 < n3) {
                scrollRate = n3 / 50 + 1;
            }
            else {
                scrollRate = this.ScrollRate;
            }
            while (true) {
                final long currentTimeMillis2 = System.currentTimeMillis();
                int n4 = n + (int)(currentTimeMillis2 - currentTimeMillis);
                currentTimeMillis = currentTimeMillis2;
                int n5;
                if (n4 * scrollRate < 100) {
                    try {
                        Thread.sleep(100 / scrollRate - n4);
                    }
                    catch (InterruptedException ex) {}
                    n5 = 1;
                    final long currentTimeMillis3 = System.currentTimeMillis();
                    n4 += (int)(currentTimeMillis3 - currentTimeMillis);
                    currentTimeMillis = currentTimeMillis3;
                }
                else {
                    n5 = (n4 * scrollRate + 50) / 100;
                }
                n2 += n5;
                if (n2 >= n3) {
                    break;
                }
                n = n4 - n5 * 100 / scrollRate;
                if (b) {
                    this.OffsetY += n5;
                }
                else {
                    this.OffsetY -= n5;
                }
                this.drawAll(true);
            }
        }
        this.OffsetY = offsetY;
    }
    
    public SiteBrowser() {
        this.BorderSpace = 5;
        this.LevelAdvance = 8;
        this.BGColor = new Color(192, 192, 192);
        this.TextColor = new Color(0, 0, 127);
        this.ActiveTextColor = new Color(127, 0, 0);
        this.Underlined = true;
        this.TreeFile = "tree.txt";
        this.Target = "_self";
        this.AutoCollapse = false;
        this.ExpandOnMouseOver = false;
        this.MenuLayout = false;
        this.HSpace = 5;
        this.ColorHigh = this.BGColor;
        this.ColorLow = this.BGColor;
        this.DropDownRate = 10;
        this.DropUpRate = 20;
        this.ScrollRate = 20;
        this.LinkPrefix = "";
        this.SliderType = -1;
        this.iScrollUp = new Item();
        this.iScrollDown = new Item();
        this.iSlider = new Item();
        this.iScrollBG = new Item();
        this.mouseX = -1;
        this.mouseY = -1;
        this.dragY = -1;
        this.busy = true;
        this.ShiftPressed = false;
        this.InitApplet = true;
        this.ScrollOn = false;
    }
}
