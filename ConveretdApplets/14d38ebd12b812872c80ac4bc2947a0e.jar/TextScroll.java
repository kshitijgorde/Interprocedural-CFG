import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TextScroll extends Applet implements Runnable
{
    public static final String VERSION = "2.8.3";
    public static final int MAX_SPEED = 100;
    public static final int MIN_SPEED = 1;
    public static final int DEFAULT_SPEED = 70;
    public static final int BG_LOAD_COLOR = 14737663;
    public static final int FG_LOAD_COLOR = 2105488;
    public static final int BG_ERROR_COLOR = 16769248;
    public static final int FG_ERROR_COLOR = 9445408;
    public static final int T_READY = 1;
    public static final int T_LOADING = 2;
    public static final int T_FORMATTING = 4;
    public static final int T_NO_DATA_SPECIFIED = 8;
    public static final int T_LOAD_ERROR = 16;
    public static final String STATUS_MSG = "TextScroll -- Version 2.8.3";
    public static final String DEFAULT_FONT_FACE = "SansSerif";
    public static final int DEFAULT_FONT_SIZE = 12;
    public static final int DEFAULT_LEFT_WIDTH = 0;
    private Image buffImage;
    private Graphics buffGraphics;
    private int buffHeight;
    private int buffWidth;
    private Color leftBackgroundColor;
    private Color leftForegroundColor;
    private Color rightBackgroundColor;
    private Color rightForegroundColor;
    private Color bgLoadColor;
    private Color fgLoadColor;
    private Font initialFont;
    private Font leftFont;
    private Font rightFont;
    private int leftFontSize;
    private int rightFontSize;
    private int fontHeight;
    private String fileName;
    private URL dataFileURL;
    private int width;
    private int height;
    private int leftWidth;
    private boolean leftCenter;
    private boolean rightCenter;
    private int speed;
    private URL targetURL;
    private boolean shouldWrapText;
    private String targetFrame;
    private int inset;
    private boolean running;
    private boolean showLeftText;
    private String leftText;
    private int lineSpacing;
    private String[] text;
    private int frame;
    private int currLine;
    private int iteration;
    private int refreshValue;
    private Thread scroller;
    private int offset;
    private boolean mouseInside;
    private int status;
    private DataLoader loader;
    private boolean reloaded;
    private DirectiveManager directiveManager;
    
    public void init() {
        this.status = 2;
        this.text = null;
        this.directiveManager = new DirectiveManager(this);
        System.err.println("TextScroll  v2.8.3\nCopyright (C) 1998   Kevin Swan, 013639s@dragon.acadiau.ca");
        this.running = false;
        this.width = this.size().width;
        this.height = this.size().height;
        this.buffWidth = this.width;
        this.buffHeight = this.height + 200;
        final String parameter = this.getParameter("leftwidth");
        if (parameter == null) {
            this.leftWidth = 0;
        }
        else {
            try {
                this.leftWidth = Integer.parseInt(parameter);
            }
            catch (NumberFormatException ex) {
                this.leftWidth = 0;
            }
        }
        final String parameter2 = this.getParameter("leftforeground");
        if (parameter2 == null) {
            this.leftForegroundColor = Color.black;
        }
        else {
            this.leftForegroundColor = getColorFromString(parameter2);
        }
        if (this.leftForegroundColor == null) {
            this.leftForegroundColor = Color.black;
        }
        final String parameter3 = this.getParameter("leftbackground");
        if (parameter3 == null) {
            this.leftBackgroundColor = Color.white;
        }
        else {
            this.leftBackgroundColor = getColorFromString(parameter3);
        }
        if (this.leftBackgroundColor == null) {
            this.leftBackgroundColor = Color.white;
        }
        final String parameter4 = this.getParameter("rightforeground");
        if (parameter4 == null) {
            this.rightForegroundColor = Color.black;
        }
        else {
            this.rightForegroundColor = getColorFromString(parameter4);
        }
        if (this.rightForegroundColor == null) {
            this.rightForegroundColor = Color.black;
        }
        final String parameter5 = this.getParameter("rightbackground");
        if (parameter5 == null) {
            this.rightBackgroundColor = Color.white;
        }
        else {
            this.rightBackgroundColor = getColorFromString(parameter5);
        }
        if (this.rightBackgroundColor == null) {
            this.rightBackgroundColor = Color.white;
        }
        final String parameter6 = this.getParameter("foreground");
        if (parameter6 == null) {
            this.rightForegroundColor = Color.black;
        }
        else {
            this.rightForegroundColor = getColorFromString(parameter6);
        }
        if (this.rightForegroundColor == null) {
            this.rightForegroundColor = Color.black;
        }
        final String parameter7 = this.getParameter("background");
        if (parameter7 == null) {
            this.rightBackgroundColor = Color.white;
        }
        else {
            this.rightBackgroundColor = getColorFromString(parameter7);
        }
        if (this.rightBackgroundColor == null) {
            this.rightBackgroundColor = Color.white;
        }
        final String parameter8 = this.getParameter("fgloadcolor");
        if (parameter8 == null) {
            this.fgLoadColor = new Color(2105488);
        }
        else {
            this.fgLoadColor = getColorFromString(parameter8);
        }
        if (this.fgLoadColor == null) {
            this.fgLoadColor = new Color(2105488);
        }
        final String parameter9 = this.getParameter("bgloadcolor");
        if (parameter9 == null) {
            this.bgLoadColor = new Color(14737663);
        }
        else {
            this.bgLoadColor = getColorFromString(parameter9);
        }
        if (this.bgLoadColor == null) {
            this.bgLoadColor = new Color(14737663);
        }
        final String parameter10 = this.getParameter("leftfontsize");
        if (parameter10 == null) {
            this.leftFontSize = 12;
        }
        else {
            try {
                this.leftFontSize = Integer.parseInt(parameter10);
            }
            catch (NumberFormatException ex2) {
                this.leftFontSize = 12;
            }
        }
        final String parameter11 = this.getParameter("rightfontsize");
        if (parameter11 == null) {
            this.rightFontSize = 12;
        }
        else {
            try {
                this.rightFontSize = Integer.parseInt(parameter11);
            }
            catch (NumberFormatException ex3) {
                this.rightFontSize = 12;
            }
        }
        final String parameter12 = this.getParameter("fontsize");
        if (parameter12 == null) {
            this.rightFontSize = 12;
        }
        else {
            try {
                this.rightFontSize = Integer.parseInt(parameter12);
            }
            catch (NumberFormatException ex4) {
                this.rightFontSize = 12;
            }
        }
        final String parameter13 = this.getParameter("leftfontface");
        String s;
        if (parameter13 == null) {
            s = "SansSerif";
        }
        else {
            s = parameter13;
        }
        this.leftFont = new Font(s, 0, this.leftFontSize);
        final String parameter14 = this.getParameter("rightfontface");
        String s2;
        if (parameter14 == null) {
            s2 = "SansSerif";
        }
        else {
            s2 = parameter14;
        }
        this.rightFont = new Font(s2, 0, this.rightFontSize);
        this.initialFont = new Font(s2, 0, this.rightFontSize);
        final FontMetrics fontMetrics = this.getToolkit().getFontMetrics(this.rightFont);
        this.fontHeight = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent() + fontMetrics.getLeading();
        this.frame = this.lineSpacing + this.fontHeight;
        final String parameter15 = this.getParameter("fontface");
        String s3;
        if (parameter15 == null) {
            s3 = "SansSerif";
        }
        else {
            s3 = parameter15;
        }
        this.rightFont = new Font(s3, 0, this.rightFontSize);
        this.initialFont = new Font(s3, 0, this.rightFontSize);
        final FontMetrics fontMetrics2 = this.getToolkit().getFontMetrics(this.rightFont);
        this.fontHeight = fontMetrics2.getMaxAscent() + fontMetrics2.getMaxDescent() + fontMetrics2.getLeading();
        this.frame = this.lineSpacing + this.fontHeight;
        final String parameter16 = this.getParameter("data");
        this.dataFileURL = null;
        if (parameter16 == null) {
            this.status = 8;
        }
        else {
            try {
                if (!parameter16.startsWith("http://")) {
                    this.dataFileURL = new URL(this.getDocumentBase(), parameter16);
                }
                else {
                    this.dataFileURL = new URL(parameter16);
                }
            }
            catch (MalformedURLException ex5) {
                this.status = 16;
            }
        }
        final String parameter17 = this.getParameter("wraptext");
        if (parameter17 != null) {
            this.shouldWrapText = new Boolean(parameter17);
        }
        final String parameter18 = this.getParameter("offset");
        if (parameter18 == null) {
            this.offset = 1;
        }
        else {
            try {
                this.offset = Integer.parseInt(parameter18);
            }
            catch (NumberFormatException ex6) {
                this.offset = 1;
            }
        }
        final String parameter19 = this.getParameter("speed");
        if (parameter19 == null) {
            this.speed = 70;
        }
        else {
            try {
                this.speed = Integer.parseInt(parameter19);
            }
            catch (NumberFormatException ex7) {
                this.speed = 70;
            }
        }
        if (this.speed > 100 || this.speed < 1) {
            this.speed = 70;
        }
        final String parameter20 = this.getParameter("refresh");
        if (parameter20 == null) {
            this.refreshValue = -1;
        }
        else {
            try {
                this.refreshValue = Integer.parseInt(parameter20);
            }
            catch (NumberFormatException ex8) {
                this.refreshValue = -1;
            }
        }
        this.resize(this.width, this.height);
        this.buffImage = this.createImage(this.buffWidth, this.buffHeight);
        (this.buffGraphics = this.buffImage.getGraphics()).setColor(this.leftBackgroundColor);
        this.buffGraphics.fillRect(0, 0, this.leftWidth, this.buffHeight);
        this.buffGraphics.setColor(this.rightBackgroundColor);
        this.buffGraphics.fillRect(this.leftWidth, 0, this.buffWidth, this.buffHeight);
        this.loadData(this.dataFileURL);
    }
    
    private Graphics getBuffGraphics() {
        if (this.buffGraphics == null) {
            this.buffImage = this.createImage(this.buffWidth, this.buffHeight);
            this.buffGraphics = this.buffImage.getGraphics();
        }
        return this.buffGraphics;
    }
    
    public static Color getColorFromString(final String s) {
        if (s == null) {
            return null;
        }
        int int1;
        int int2;
        int int3;
        try {
            int1 = Integer.parseInt(s.substring(0, s.indexOf(",")).trim());
            int2 = Integer.parseInt(s.substring(s.indexOf(",") + 1, s.lastIndexOf(",")).trim());
            int3 = Integer.parseInt(s.substring(s.lastIndexOf(",") + 1).trim());
        }
        catch (NumberFormatException ex) {
            return null;
        }
        try {
            return new Color(int1, int2, int3);
        }
        catch (IllegalArgumentException ex2) {
            return null;
        }
    }
    
    public void run() {
        while (!this.isReady()) {
            if (this.getStatus() > 2) {
                this.repaint();
                return;
            }
            try {
                Thread.sleep(200L);
            }
            catch (InterruptedException ex) {
                return;
            }
        }
        while (this.running) {
            this.getBuffGraphics().copyArea(0, this.offset, this.buffWidth, this.height + this.fontHeight + this.lineSpacing, 0, -this.offset);
            this.frame -= this.offset;
            if (this.frame < 0) {
                final String s = this.text[this.currLine];
                ++this.currLine;
                if (this.currLine == this.text.length) {
                    this.currLine = 0;
                    ++this.iteration;
                    if (this.refreshValue > 0 && this.iteration >= this.refreshValue) {
                        if (this.reloaded) {
                            if (this.isReady()) {
                                final String[] data = this.loader.getData();
                                System.arraycopy(data, 0, this.text = new String[data.length], 0, data.length);
                                this.iteration = 0;
                                this.reloaded = false;
                            }
                        }
                        else {
                            this.refreshData();
                        }
                    }
                }
                if (s.startsWith("^^")) {
                    if (this.directiveManager.performDirective(s)) {
                        continue;
                    }
                    System.err.println("Illegal directive call:\n\t" + s);
                }
                final FontMetrics fontMetrics = this.getToolkit().getFontMetrics(this.leftFont);
                if (this.showLeftText) {
                    this.getBuffGraphics().setColor(this.leftForegroundColor);
                    this.getBuffGraphics().setFont(this.leftFont);
                    if (!this.leftCenter) {
                        this.getBuffGraphics().drawString(this.leftText, 0, this.height + this.lineSpacing + fontMetrics.getMaxAscent());
                    }
                    else {
                        final int n = this.leftWidth / 2 - fontMetrics.stringWidth(this.leftText) / 2;
                        this.getBuffGraphics().drawString(this.leftText, (n < 0) ? 0 : n, this.height + this.lineSpacing + fontMetrics.getMaxAscent());
                    }
                    this.getBuffGraphics().setColor(this.rightForegroundColor);
                    this.showLeftText = false;
                }
                this.getBuffGraphics().setColor(this.rightForegroundColor);
                this.getBuffGraphics().setFont(this.rightFont);
                final FontMetrics fontMetrics2 = this.getToolkit().getFontMetrics(this.rightFont);
                this.frame = this.lineSpacing + this.fontHeight;
                if (!this.rightCenter) {
                    this.getBuffGraphics().drawString(s, this.leftWidth + this.inset, this.height + this.lineSpacing + fontMetrics2.getMaxAscent());
                }
                else {
                    final int n2 = (this.width - this.leftWidth) / 2 - fontMetrics2.stringWidth(s) / 2;
                    this.getBuffGraphics().drawString(s, ((n2 < this.leftWidth) ? this.leftWidth : n2) + this.leftWidth, this.height + this.lineSpacing + fontMetrics2.getMaxAscent());
                }
            }
            try {
                Thread.sleep(101 - this.speed);
            }
            catch (InterruptedException ex2) {
                return;
            }
            this.repaint();
        }
    }
    
    private void refreshData() {
        this.loader.refresh();
        this.reloaded = true;
    }
    
    private void loadData(final URL url) {
        if (url == null) {
            return;
        }
        this.loader = new DataLoader(url, this.initialFont, this.width - this.leftWidth, this.shouldWrapText);
    }
    
    public void paint(final Graphics graphics) {
        if (this.status > 1) {
            this.displayStatus(graphics);
            return;
        }
        graphics.drawImage(this.buffImage, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        if (this.status > 1) {
            this.displayStatus(graphics);
            return;
        }
        graphics.drawImage(this.buffImage, 0, 0, this);
    }
    
    private void displayStatus(final Graphics graphics) {
        if (this.status == 1) {
            return;
        }
        graphics.setFont(new Font("SansSerif", 0, 12));
        switch (this.getStatus()) {
            case 2: {
                graphics.setColor(this.bgLoadColor);
                graphics.fillRect(0, 0, this.width, this.height);
                graphics.setColor(this.fgLoadColor);
                graphics.drawString("Loading data ...", 5, 15);
            }
            case 4: {
                graphics.setColor(this.bgLoadColor);
                graphics.fillRect(0, 0, this.width, this.height);
                graphics.setColor(this.fgLoadColor);
                graphics.drawString("Formatting data ...", 5, 15);
            }
            case 8: {
                graphics.setColor(new Color(16769248));
                graphics.fillRect(0, 0, this.width, this.height);
                graphics.setColor(new Color(9445408));
                graphics.drawString("No \"data\" parameter specified.", 5, 15);
            }
            case 16: {
                graphics.setColor(new Color(16769248));
                graphics.fillRect(0, 0, this.width, this.height);
                graphics.setColor(new Color(9445408));
                graphics.drawString("Couldn't read specified text file.", 5, 15);
            }
            default: {}
        }
    }
    
    public void start() {
        this.running = true;
        (this.scroller = new Thread(this)).start();
        if (this.loader != null) {
            this.loader.start();
            return;
        }
        System.err.println("ERROR: DataLoader reference was lost.");
    }
    
    public void stop() {
        this.running = false;
        if (this.scroller != null) {
            this.scroller.stop();
        }
        if (this.loader != null) {
            this.loader.stop();
        }
    }
    
    private void toggle() {
        if (this.running) {
            this.stop();
            return;
        }
        this.start();
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.mouseInside = true;
        if (this.getURL() == null) {
            this.showStatus("TextScroll -- Version 2.8.3");
        }
        else {
            this.showStatus(this.getURL().toString());
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouseInside = false;
        this.showStatus("");
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.getURL() == null) {
            this.toggle();
        }
        else {
            try {
                if (this.getAppletContext() != null) {
                    if (this.targetFrame == null) {
                        this.getAppletContext().showDocument(this.getURL());
                    }
                    else {
                        this.getAppletContext().showDocument(this.getURL(), this.targetFrame);
                    }
                }
            }
            catch (Exception ex) {
                this.toggle();
            }
        }
        return true;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public boolean isReady() {
        if (this.text == null) {
            if (this.loader.dataReady()) {
                final String[] data = this.loader.getData();
                System.arraycopy(data, 0, this.text = new String[data.length], 0, data.length);
                this.status = 1;
            }
            else if (this.loader.errorOccurred()) {
                this.status = 16;
            }
            else {
                this.status = 2;
            }
        }
        return (this.status & 0x1) == 0x1;
    }
    
    public void setLeftCenter(final String s) {
        if (s.equalsIgnoreCase("true")) {
            this.leftCenter = true;
            return;
        }
        if (s.equalsIgnoreCase("false")) {
            this.leftCenter = false;
            return;
        }
        System.err.println("setLeftCenter directive ignored - invalid argument: " + s);
    }
    
    public void setRightCenter(final String s) {
        if (s.equalsIgnoreCase("true")) {
            this.rightCenter = true;
            return;
        }
        if (s.equalsIgnoreCase("false")) {
            this.rightCenter = false;
            return;
        }
        System.err.println("setRightCenter directive ignored - invalid argument: " + s);
    }
    
    public void setCenter(final String s) {
        if (s.equalsIgnoreCase("true")) {
            this.rightCenter = true;
            return;
        }
        if (s.equalsIgnoreCase("false")) {
            this.rightCenter = false;
            return;
        }
        System.err.println("setCenter directive ignored - invalid argument: " + s);
    }
    
    public void setLeftBold(final String s) {
        final int style = this.leftFont.getStyle();
        int n;
        if (s.equalsIgnoreCase("true")) {
            n = (style | 0x1);
        }
        else {
            if (!s.equalsIgnoreCase("false")) {
                System.err.println("setLeftBold directive ignored - invalid argument: " + s);
                return;
            }
            n = (style & 0xFFFFFFFE);
        }
        this.leftFont = new Font(this.leftFont.getName(), n, this.leftFontSize);
    }
    
    public void setRightBold(final String s) {
        final int style = this.rightFont.getStyle();
        int n;
        if (s.equalsIgnoreCase("true")) {
            n = (style | 0x1);
        }
        else {
            if (!s.equalsIgnoreCase("false")) {
                System.err.println("Error: Invalid int arg for setBold or setRightBold: " + s);
                return;
            }
            n = (style & 0xFFFFFFFE);
        }
        this.rightFont = new Font(this.rightFont.getName(), n, this.rightFontSize);
        final FontMetrics fontMetrics = this.getToolkit().getFontMetrics(this.rightFont);
        this.fontHeight = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent() + fontMetrics.getLeading();
        this.getBuffGraphics().setFont(this.rightFont);
    }
    
    public void setBold(final String rightBold) {
        this.setRightBold(rightBold);
    }
    
    public void setLeftItalic(final String s) {
        final int style = this.leftFont.getStyle();
        int n;
        if (s.equalsIgnoreCase("true")) {
            n = (style | 0x2);
        }
        else {
            if (!s.equalsIgnoreCase("false")) {
                System.err.println("Error: Invalid int arg for setLeftItalic: " + s);
                return;
            }
            n = (style & 0xFFFFFFFD);
        }
        this.leftFont = new Font(this.leftFont.getName(), n, this.leftFontSize);
    }
    
    public void setRightItalic(final String s) {
        final int style = this.rightFont.getStyle();
        int n;
        if (s.equalsIgnoreCase("true")) {
            n = (style | 0x2);
        }
        else {
            if (!s.equalsIgnoreCase("false")) {
                System.err.println("Error: Invalid int arg for setItalic or setRightItalic: " + s);
                return;
            }
            n = (style & 0xFFFFFFFD);
        }
        this.rightFont = new Font(this.rightFont.getName(), n, this.rightFontSize);
        final FontMetrics fontMetrics = this.getToolkit().getFontMetrics(this.rightFont);
        this.fontHeight = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent() + fontMetrics.getLeading();
        this.getBuffGraphics().setFont(this.rightFont);
    }
    
    public void setItalic(final String rightItalic) {
        this.setRightItalic(rightItalic);
    }
    
    public void setInset(final String s) {
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            System.err.println("setInset directive ignored - invalid argument: " + s + " (Expected a number)");
            return;
        }
        this.inset = int1;
    }
    
    public void setURL(final String s) {
        if (s == null) {
            this.setURL((URL)null);
            this.setTargetFrame(null);
        }
        if (s.indexOf(44) >= 0 && s.indexOf(44) < s.indexOf("http://")) {
            final String substring = s.substring(0, s.indexOf(44));
            this.setURL(s.substring(s.indexOf(44) + 1, s.length()));
            this.setTargetFrame(substring);
            return;
        }
        this.setTargetFrame(null);
        if (s.equalsIgnoreCase("null")) {
            this.setURL((URL)null);
            this.setTargetFrame(null);
            return;
        }
        try {
            this.setURL(new URL(s));
        }
        catch (MalformedURLException ex) {
            System.err.println("setURL directive ignored - invalid argument: " + s + "\n(Unsetting clickable link)");
            this.setURL((URL)null);
        }
    }
    
    public void setTargetFrame(String trim) {
        if (trim != null) {
            trim = trim.trim();
            if (trim.equalsIgnoreCase("null")) {
                trim = null;
            }
        }
        this.targetFrame = trim;
    }
    
    public String getTargetFrame() {
        return this.targetFrame;
    }
    
    public void setURL(final URL targetURL) {
        this.targetURL = targetURL;
        if (this.mouseInside) {
            if (this.targetURL == null) {
                this.showStatus("TextScroll -- Version 2.8.3");
                return;
            }
            this.showStatus(this.targetURL.toString());
        }
    }
    
    public void setLeftText(final String leftText) {
        this.leftText = leftText;
        this.showLeftText = true;
    }
    
    public URL getURL() {
        return this.targetURL;
    }
    
    public String getAppletInfo() {
        return "TextScroll  Version 2.8.3  Copyright (C) 1998 by Kevin Swan, 013639s@dragon.acadiau.ca";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "fontface", "Serif, SansSerif, Monospaced", "The font to use" }, { "fontsize", "integer", "The size of font to use" }, { "speed", String.valueOf(1) + " - " + 100, "Scroll speed" }, { "data", "String", "Name of text file to display" }, { "foreground", "rrr,ggg,bbb", "RGB value to use for foreground color" }, { "background", "rrr,ggg,bbb", "RGB value to use for background color" } };
    }
    
    public void setLeftForegroundColor(final String s) {
        final Color colorFromString = getColorFromString(s);
        if (colorFromString != null) {
            this.leftForegroundColor = colorFromString;
        }
        this.getBuffGraphics().setColor(this.leftForegroundColor);
    }
    
    public void setLeftBackgroundColor(final String s) {
        final Color colorFromString = getColorFromString(s);
        if (colorFromString != null) {
            this.leftBackgroundColor = colorFromString;
        }
        this.getBuffGraphics().setColor(this.leftBackgroundColor);
        this.getBuffGraphics().fillRect(0, 0, this.leftWidth, this.buffHeight);
        this.getBuffGraphics().setColor(this.rightForegroundColor);
    }
    
    public void setRightForegroundColor(final String s) {
        final Color colorFromString = getColorFromString(s);
        if (colorFromString != null) {
            this.rightForegroundColor = colorFromString;
        }
        this.getBuffGraphics().setColor(this.rightForegroundColor);
    }
    
    public void setForegroundColor(final String rightForegroundColor) {
        this.setRightForegroundColor(rightForegroundColor);
    }
    
    public void setRightBackgroundColor(final String s) {
        final Color colorFromString = getColorFromString(s);
        if (colorFromString != null) {
            this.rightBackgroundColor = colorFromString;
        }
        this.getBuffGraphics().setColor(this.rightBackgroundColor);
        this.getBuffGraphics().fillRect(this.leftWidth, 0, this.buffWidth, this.buffHeight);
        this.getBuffGraphics().setColor(this.rightForegroundColor);
    }
    
    public void setBackgroundColor(final String rightBackgroundColor) {
        this.setRightBackgroundColor(rightBackgroundColor);
    }
    
    public void pause(final String s) {
        Integer value;
        try {
            value = Integer.valueOf(s);
        }
        catch (NumberFormatException ex) {
            System.err.println("Error: Invalid integer specified for pause (): \"" + s + "\"");
            return;
        }
        try {
            Thread.sleep(value);
        }
        catch (InterruptedException ex2) {}
    }
    
    public void pause() {
        this.toggle();
    }
    
    public void setSpeed(final String s) {
        Integer value;
        try {
            value = Integer.valueOf(s);
        }
        catch (NumberFormatException ex) {
            System.err.println("Error: Invalid integer specified for setSpeed (): \"" + s + "\"");
            return;
        }
        if (value <= 100 && value >= 1) {
            this.speed = value;
        }
    }
    
    public void setLeftFontFace(final String s) {
        this.leftFont = new Font(s, this.leftFont.getStyle(), this.leftFontSize);
    }
    
    public void setRightFontFace(final String s) {
        this.rightFont = new Font(s, this.rightFont.getStyle(), this.rightFontSize);
        final FontMetrics fontMetrics = this.getToolkit().getFontMetrics(this.rightFont);
        this.fontHeight = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent() + fontMetrics.getLeading();
        this.getBuffGraphics().setFont(this.rightFont);
    }
    
    public void setFontFace(final String rightFontFace) {
        this.setRightFontFace(rightFontFace);
    }
    
    public void setLeftFontSize(final String s) {
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            System.err.println("Error: Invalid integer specified for setLeftFontSize (): \"" + s + "\"");
            return;
        }
        this.leftFontSize = int1;
        this.leftFont = new Font(this.leftFont.getName(), this.leftFont.getStyle(), this.leftFontSize);
    }
    
    public void setRightFontSize(final String s) {
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            System.err.println("Error: Invalid arg for setFontSize or setRightFontSize (): \"" + s + "\"");
            return;
        }
        this.rightFontSize = int1;
        this.rightFont = new Font(this.rightFont.getName(), this.rightFont.getStyle(), this.rightFontSize);
        final FontMetrics fontMetrics = this.getToolkit().getFontMetrics(this.rightFont);
        this.fontHeight = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent() + fontMetrics.getLeading();
        this.getBuffGraphics().setFont(this.rightFont);
    }
    
    public void setFontSize(final String rightFontSize) {
        this.setRightFontSize(rightFontSize);
    }
    
    public TextScroll() {
        this.leftWidth = 0;
        this.leftCenter = false;
        this.rightCenter = false;
        this.shouldWrapText = true;
        this.inset = 3;
        this.showLeftText = false;
        this.leftText = "";
        this.lineSpacing = 5;
        this.frame = 16;
        this.refreshValue = 1;
        this.offset = 1;
        this.mouseInside = false;
        this.reloaded = true;
    }
}
