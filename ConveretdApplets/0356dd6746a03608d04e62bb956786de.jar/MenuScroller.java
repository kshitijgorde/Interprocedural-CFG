import java.util.StringTokenizer;
import java.awt.Container;
import java.awt.Polygon;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class MenuScroller extends Canvas implements Runnable
{
    String stringFont1;
    int stringStyle;
    int stringSize;
    int offset;
    int w;
    FontMetrics fontMetrics;
    boolean allowed;
    String status;
    int z;
    boolean clicked;
    Color color;
    int linkClick;
    long startTime;
    boolean link;
    boolean paused;
    Font[] fontArray3;
    boolean mouseexit;
    int mousex;
    int mousey;
    int lastlined;
    boolean once;
    String sColor;
    int tempInt;
    boolean checkError;
    String error;
    int lines;
    String[] lineArray;
    int[] sizeArray;
    Color[] colorArray;
    boolean[] separatorArray;
    String[] linkArray;
    String temp;
    String[] fontArray;
    int[] styleArray;
    int[] lineOffsetArray;
    int lineDown;
    Thread thread;
    Image offscreen;
    Image offscreen2;
    Graphics offscreeng;
    Graphics offscreeng2;
    int height;
    Font f;
    FontMetrics fm;
    int fs;
    int ypos;
    int lineSpace;
    int diff;
    int startLines;
    Color background;
    int totLines;
    int adjYpos;
    boolean run;
    String linkFrame;
    int lineOffsetDefault;
    Color linkColor;
    String[] SStyleArray;
    int[] StyleMap;
    String[] fontArray2;
    int lineSelect;
    int lastLink;
    int lined;
    long diffTime;
    Vector lineVector;
    Vector mapVector;
    int vectorLineNumber;
    int passLine;
    String path;
    int textStart;
    int textEnd;
    String menuName;
    MenuScrollTrial menuScroll;
    int whichPanel;
    MenuScroller MStemp;
    MenuScroller activeSubMenu;
    Color appletColor;
    String border;
    boolean borderRaised;
    Color borderColor;
    int oldActiveVectorPointer;
    int oldActiveArrayPointer;
    int l;
    int d;
    int u;
    int n;
    MediaTracker mediaTracker;
    Image tempImage;
    String SImage;
    boolean up;
    int s;
    int q;
    int scrollSpeed;
    int pauseValue;
    boolean paint;
    boolean makeIcalled;
    
    public MenuScroller(final MenuScrollTrial menuScroll, final String menuName, final int n, final int n2, final int whichPanel, final Color color) {
        this.allowed = true;
        this.status = "";
        this.clicked = false;
        this.link = false;
        this.paused = false;
        this.mouseexit = true;
        this.mousex = 0;
        this.mousey = 0;
        this.lastlined = 0;
        this.once = true;
        this.sColor = "";
        this.checkError = true;
        this.error = "";
        this.lineDown = 0;
        this.offscreen = null;
        this.offscreen2 = null;
        this.ypos = 0;
        this.startLines = 0;
        this.run = false;
        this.linkFrame = "";
        this.linkColor = Color.blue;
        this.SStyleArray = new String[] { "PLAIN", "ITALIC", "BOLD" };
        this.StyleMap = new int[] { 0, 2, 1 };
        this.fontArray2 = new String[] { "Dialog", "Helvetica", "TimesRoman", "Courier" };
        this.lineSelect = -1;
        this.lastLink = -1;
        this.lined = 0;
        this.lineVector = new Vector(10);
        this.mapVector = new Vector(10);
        this.vectorLineNumber = 0;
        this.textStart = -1;
        this.textEnd = -1;
        this.border = "up";
        this.borderRaised = true;
        this.oldActiveVectorPointer = -1;
        this.oldActiveArrayPointer = -1;
        this.up = true;
        this.scrollSpeed = 8;
        this.pauseValue = 15;
        this.paint = false;
        this.makeIcalled = false;
        this.menuScroll = menuScroll;
        this.menuName = menuName;
        this.whichPanel = whichPanel;
        this.appletColor = color;
        this.resize(n, n2);
        this.path = menuScroll.getCodeBase().toString();
        this.lines = this.getNumber("numberOflines");
        if (this.lines < 0) {
            return;
        }
        this.SImage = this.getParameter("image");
        if (this.SImage != null && this.SImage.indexOf(".gif") <= 0 && this.SImage.indexOf(".jpg") <= 0 && this.SImage.indexOf(".GIF") <= 0 && this.SImage.indexOf(".JPG") <= 0) {
            this.error = "error: " + menuName + "-image value=\"" + this.SImage + "\"";
            return;
        }
        if (this.SImage != null) {
            this.makeBackImage();
        }
        this.border = this.getParameter("border");
        if (this.border == null) {
            this.error = "error: " + menuName + "-border value=\"" + this.border + "\"";
            return;
        }
        this.l = this.border.compareTo("line");
        this.d = this.border.compareTo("down");
        this.u = this.border.compareTo("up");
        this.n = this.border.compareTo("no");
        if (this.l != 0 && this.d != 0 && this.u != 0 && this.n != 0) {
            this.error = "error: " + menuName + "-border value=\"" + this.border + "\"";
            return;
        }
        if (this.n != 0) {
            this.borderColor = this.getColor("borderColor");
            if (this.borderColor == null) {
                return;
            }
        }
        this.lineArray = new String[this.lines];
        this.sizeArray = new int[this.lines];
        this.colorArray = new Color[this.lines];
        this.fontArray = new String[this.lines];
        this.styleArray = new int[this.lines];
        this.linkArray = new String[this.lines];
        this.lineOffsetArray = new int[this.lines];
        this.fontArray3 = new Font[this.lines];
        this.separatorArray = new boolean[this.lines];
        this.linkFrame = this.getParameter("linkFrame");
        if (this.linkFrame == null || this.linkFrame.equals("")) {
            this.error = "error: " + menuName + "-linkFrame value=\"" + this.linkFrame + "\"";
            return;
        }
        this.linkClick = this.getNumber("linkClick");
        if (this.linkClick < 0) {
            this.linkClick = 1;
        }
        if (this.linkClick > 2) {
            this.linkClick = 2;
        }
        this.color = this.getColor("linkColor");
        if (this.color == null) {
            return;
        }
        this.linkColor = this.color;
        this.temp = this.getParameter("background");
        if (this.temp == null) {
            this.error = "error: " + menuName + "-background value=\"" + this.temp + "\"";
            return;
        }
        this.color = this.getColor("background");
        if (this.color == null) {
            return;
        }
        this.background = this.color;
        this.setBackground(color);
        this.lineSpace = this.getNumber("lineSpace");
        if (this.lineSpace < 0) {
            return;
        }
        this.scrollSpeed = this.getNumber("scrollSpeed");
        if (this.scrollSpeed < 0) {
            return;
        }
        if (this.scrollSpeed < 1 || this.scrollSpeed > 20) {
            this.error = "error: " + menuName + "-scrollSpeed value=\"" + this.scrollSpeed + "\"";
            return;
        }
        for (int i = 0; i < this.lines; ++i) {
            this.separatorArray[i] = false;
            this.lineArray[i] = this.getParameter("lineDefault");
            if (this.lineArray[i] == null) {
                this.lineArray[i] = "";
            }
            this.sizeArray[i] = this.getNumber("sizeDefault");
            if (this.sizeArray[i] < 0) {
                return;
            }
            this.color = this.getColor("colorDefault");
            if (this.color == null) {
                return;
            }
            this.colorArray[i] = this.color;
            this.tempInt = this.getFont("fontDefault");
            if (this.tempInt < 0) {
                return;
            }
            this.fontArray[i] = this.fontArray2[this.tempInt];
            this.tempInt = this.getStyle("styleDefault");
            if (this.tempInt < 0) {
                return;
            }
            this.styleArray[i] = this.StyleMap[this.tempInt];
            this.linkArray[i] = this.getParameter("linkDefault");
            if (this.linkArray[i] == null) {
                this.linkArray[i] = "";
            }
            else {
                this.linkArray[i] = this.linkArray[i].trim();
            }
            if (this.linkArray[i].compareTo("") != 0) {
                this.link = true;
            }
            if (whichPanel == 0 && menuName.equals(this.linkArray[i])) {
                this.error = "error: " + menuName + "-linkDefault value=\"" + this.linkArray[i] + "\"";
                this.checkError = true;
                return;
            }
            this.lineOffsetArray[i] = this.getNumber("lineOffsetDefault");
        }
        for (int j = 0; j < this.lines; ++j) {
            this.separatorArray[j] = this.setBoolean("separator" + Integer.toString(j + 1));
            this.tempInt = this.getNumber("lineOffset" + Integer.toString(j + 1));
            if (this.tempInt >= 0) {
                this.lineOffsetArray[j] = this.tempInt;
            }
            else if (this.tempInt < -1) {
                return;
            }
            final int[] lineOffsetArray = this.lineOffsetArray;
            final int n3 = j;
            lineOffsetArray[n3] += 3;
            this.tempInt = this.getNumber("size" + Integer.toString(j + 1));
            if (this.tempInt >= 0) {
                this.sizeArray[j] = this.tempInt;
            }
            else if (this.tempInt < -1) {
                return;
            }
            this.color = this.getColor("color" + Integer.toString(j + 1));
            if (this.color != null) {
                this.colorArray[j] = this.color;
            }
            this.tempInt = this.getFont("font" + Integer.toString(j + 1));
            if (this.tempInt >= 0) {
                this.fontArray[j] = this.fontArray2[this.tempInt];
            }
            else if (this.tempInt < -1) {
                return;
            }
            this.tempInt = this.getStyle("style" + Integer.toString(j + 1));
            if (this.tempInt >= 0) {
                this.styleArray[j] = this.StyleMap[this.tempInt];
            }
            else if (this.tempInt < -1) {
                return;
            }
            this.temp = this.getParameter("link" + Integer.toString(j + 1));
            if (this.temp != null) {
                this.linkArray[j] = this.temp;
                this.link = true;
            }
            if (whichPanel == 0 && menuName.equals(this.linkArray[j])) {
                this.error = "error: " + menuName + "-link" + (j + 1) + " value=\"" + this.linkArray[j] + "\"";
                this.checkError = true;
                return;
            }
            this.fontArray3[j] = new Font(this.fontArray[j], this.styleArray[j], this.sizeArray[j]);
            this.temp = this.getParameter("line" + Integer.toString(j + 1));
            if (this.temp != null) {
                this.lineArray[j] = this.temp;
            }
            this.insertLineIntoVector(this.lineArray[j], j);
        }
        this.startLines = 0;
        this.totLines = this.lineSpace * this.lineVector.size();
        this.height = this.size().height;
        this.ypos = this.size().height + this.scrollSpeed * 20;
        this.checkError = false;
    }
    
    void drawBoarder() {
        if (this.n != 0) {
            this.offscreeng.setColor(this.borderColor);
            if (this.d == 0) {
                for (int i = 0; i < 2; ++i) {
                    this.offscreeng.draw3DRect(i, i, this.size().width - i * 2 - 1, this.size().height - i * 2 - 1, false);
                }
            }
            else if (this.l == 0) {
                this.offscreeng.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
            }
            else if (this.u == 0) {
                for (int j = 0; j < 2; ++j) {
                    this.offscreeng.draw3DRect(j, j, this.size().width - j * 2 - 1, this.size().height - j * 2 - 1, true);
                }
            }
        }
    }
    
    void drawImage() {
        if (this.setBoolean("imageCenter")) {
            this.offscreeng.drawImage(this.tempImage, this.size().width / 2 - this.tempImage.getWidth(this) / 2, this.size().height / 2 - this.tempImage.getHeight(this) / 2, this);
            return;
        }
        int n = 0;
        int n2 = 0;
        while (true) {
            this.offscreeng.drawImage(this.tempImage, n, n2, this);
            n += this.tempImage.getWidth(this);
            if (n >= this.size().width) {
                n2 += this.tempImage.getHeight(this);
                n = 0;
                if (n2 >= this.size().height) {
                    break;
                }
                continue;
            }
        }
    }
    
    Color getColor(String string) {
        string = String.valueOf(this.menuName) + "-" + string;
        final Color color = this.menuScroll.getColor(string);
        if (color == null) {
            this.error = this.menuScroll.error;
            this.menuScroll.error = null;
        }
        return color;
    }
    
    int getFont(final String s) {
        this.temp = this.getParameter(s);
        if (this.temp == null) {
            this.error = "error: " + this.menuName + "-" + s + " value=\"" + this.temp + "\"";
            return -1;
        }
        for (int i = 0; i < this.fontArray2.length; ++i) {
            if (this.temp.compareTo(this.fontArray2[i]) == 0) {
                return i;
            }
        }
        this.error = "error: " + this.menuName + "-" + s + " value=\"" + this.temp + "\"";
        return -2;
    }
    
    int getNumber(final String s) {
        this.temp = this.getParameter(s);
        if (this.temp == null) {
            this.error = "error: " + this.menuName + "-" + s + " value=\"" + this.temp + "\"";
            return -1;
        }
        int intValue;
        try {
            intValue = Integer.valueOf(this.temp);
        }
        catch (NumberFormatException ex) {
            this.error = "error: " + this.menuName + "-" + s + " value=\"" + this.temp + "\"";
            return -2;
        }
        return intValue;
    }
    
    String getParameter(String string) {
        string = String.valueOf(this.menuName) + "-" + string;
        return this.menuScroll.getParameter(string);
    }
    
    int getStyle(final String s) {
        this.temp = this.getParameter(s);
        if (this.temp == null) {
            this.error = "error: " + this.menuName + "-" + s + " value=\"" + this.temp + "\"";
            return -1;
        }
        for (int i = 0; i < this.SStyleArray.length; ++i) {
            if (this.temp.compareTo(this.SStyleArray[i]) == 0) {
                return i;
            }
        }
        this.error = "error: " + this.menuName + "-" + s + " value=\"" + this.temp + "\"";
        return -2;
    }
    
    public boolean handleEvent(final Event event) {
        this.highlight(event.x, event.y);
        this.mousex = event.x;
        this.mousey = event.y;
        return super.handleEvent(event);
    }
    
    void highlight(final int n, final int n2) {
        if (!this.link || this.mouseexit || this.linkArray == null || !this.run) {
            return;
        }
        this.highlighted(n, n2);
    }
    
    synchronized void highlighted(final int n, final int n2) {
        this.adjYpos = this.startLines + this.ypos;
        this.diff = n2 - this.adjYpos;
        this.lineSelect = this.diff / this.lineSpace;
        if (this.lastLink == this.lineSelect) {
            return;
        }
        if (this.lastLink >= 0) {
            this.highliteLines(this.passLine = this.lastLink, 1);
        }
        this.adjYpos = this.startLines + this.ypos;
        if (n2 < this.adjYpos || n2 > this.adjYpos + this.totLines - 1) {
            if (this.paused) {
                this.repaint();
            }
            this.lastLink = -1;
            if (!this.menuScroll.status.equals(this.menuScroll.slogan)) {
                this.showStatus(this.menuScroll.status = "");
            }
            return;
        }
        final int intValue = this.mapVector.elementAt(this.lineSelect).elements().nextElement();
        if (this.linkArray[intValue].equals("")) {
            if (this.paused) {
                this.repaint();
            }
            this.lastLink = -1;
            if (!this.menuScroll.status.equals(this.menuScroll.slogan)) {
                this.showStatus(this.menuScroll.status = "");
            }
            return;
        }
        this.highliteLines(this.passLine = this.lineSelect, 0);
        if (this.paused) {
            this.repaint();
        }
        boolean b = false;
        for (int i = 0; i < this.menuScroll.menuScrollerArray.length; ++i) {
            if (this.linkArray[intValue].equals(this.menuScroll.menuScrollerNameArray[i])) {
                b = true;
            }
        }
        if (!b) {
            this.menuScroll.status = this.linkArray[intValue];
        }
        else {
            this.menuScroll.status = "";
        }
        this.showStatus(this.menuScroll.status);
        this.lastLink = this.lineSelect;
        this.lastlined = this.lined;
    }
    
    public void highliteLines(final int n, final int n2) {
        if (n < 0) {
            return;
        }
        final Enumeration<Integer> elements = this.mapVector.elementAt(n).elements();
        final int intValue = elements.nextElement();
        this.offscreeng.setFont(this.fontArray3[intValue]);
        if (n2 == 0) {
            this.offscreeng.setColor(this.linkColor);
        }
        if (n2 == 1) {
            this.offscreeng.setColor(this.colorArray[intValue]);
        }
        if (n2 == 2) {
            this.offscreeng.setColor(Color.red);
        }
        while (elements.hasMoreElements()) {
            final int intValue2 = elements.nextElement();
            this.lined = this.lineSpace * intValue2 + this.startLines + this.lineSpace;
            this.offscreeng.drawString((String)this.lineVector.elementAt(intValue2), this.lineOffsetArray[intValue], this.lined);
        }
    }
    
    void insertLineIntoVector(final String s, final int n) {
        final Vector vector = new Vector<String>(10);
        int vectorLineNumber = this.vectorLineNumber;
        this.w = this.size().width;
        this.fontMetrics = this.getFontMetrics(this.fontArray3[n]);
        final Vector<Integer> vector2 = new Vector<Integer>(10);
        this.offset = this.lineOffsetArray[n];
        this.wordWrap(s, vector, n);
        vector2.addElement(Integer.valueOf(Integer.toString(n)));
        for (int i = 0; i < vector.size(); ++i) {
            vector2.addElement(Integer.valueOf(Integer.toString(vectorLineNumber++)));
        }
        final Enumeration<String> elements = vector.elements();
        while (elements.hasMoreElements()) {
            this.lineVector.addElement(elements.nextElement());
            this.mapVector.addElement(vector2);
            ++this.vectorLineNumber;
        }
    }
    
    void jumpToUrl(String string) {
        try {
            if (!string.startsWith("http") && !string.startsWith("ftp") && !string.startsWith("mailto:")) {
                string = String.valueOf(this.path) + string;
            }
            this.menuScroll.getAppletContext().showDocument(new URL(string), this.linkFrame);
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
    
    void makeBackImage() {
        this.mediaTracker = new MediaTracker(this);
        this.tempImage = this.menuScroll.getImage(this.menuScroll.getCodeBase(), this.SImage);
        this.mediaTracker.addImage(this.tempImage, 0);
        try {
            this.mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
    }
    
    synchronized void makeImage() {
        if (this.makeIcalled) {
            return;
        }
        this.makeIcalled = true;
        if (this.checkError) {
            this.showStatus(this.error);
            return;
        }
        this.offscreen2 = this.createImage(this.size().width, this.height);
        (this.offscreeng2 = this.offscreen2.getGraphics()).setColor(this.appletColor);
        this.offscreeng2.fillRect(0, 0, this.size().width, this.height);
        this.offscreen = this.createImage(this.size().width, this.height);
        (this.offscreeng = this.offscreen.getGraphics()).setColor(this.background);
        this.offscreeng.fillRect(0, 0, this.size().width, this.height);
        this.drawBoarder();
        if (this.SImage != null) {
            this.drawImage();
        }
        this.lines = this.lineVector.size();
        for (int i = 0; i < this.lineVector.size(); ++i) {
            final Vector<Integer> vector = this.mapVector.elementAt(i);
            final int intValue = vector.elementAt(0);
            final int intValue2 = vector.elementAt(1);
            this.fm = this.offscreeng.getFontMetrics();
            this.offscreeng.setColor(this.colorArray[intValue]);
            this.fontArray3[intValue] = new Font(this.fontArray[intValue], this.styleArray[intValue], this.sizeArray[intValue]);
            this.offscreeng.setFont(this.fontArray3[intValue]);
            String parameter = this.lineVector.elementAt(i);
            if (parameter == null) {
                parameter = this.getParameter("lineDefault");
                if (parameter == null) {
                    parameter = "";
                }
            }
            this.offscreeng.drawString(parameter, this.lineOffsetArray[intValue], this.startLines + this.lineSpace * (i + 1));
            boolean b = false;
            for (int j = 0; j < this.menuScroll.menuScrollerArray.length; ++j) {
                if (this.linkArray[intValue].equals(this.menuScroll.menuScrollerNameArray[j])) {
                    b = true;
                }
            }
            final int n = this.size().width - 3;
            final int n2 = this.startLines + this.lineSpace * (i + 1) - this.sizeArray[intValue] / 3;
            if (this.whichPanel == 0 && !parameter.trim().equals("") && b && intValue2 == i) {
                this.offscreeng.setColor(this.menuScroll.arrowColor);
                final Polygon polygon = new Polygon();
                polygon.addPoint(n - 5, n2 - 5);
                polygon.addPoint(n - 5, n2 + 5);
                polygon.addPoint(n, n2);
                this.offscreeng.fillPolygon(polygon);
            }
            if (this.separatorArray[intValue] && intValue2 == i) {
                this.offscreeng.setColor(new Color(51, 51, 51));
                this.offscreeng.drawLine(15, n2 - 1, this.size().width - 15, n2 - 1);
                this.offscreeng.setColor(new Color(192, 192, 192));
                this.offscreeng.drawLine(15, n2, this.size().width - 15, n2);
            }
        }
        this.startTime = System.currentTimeMillis();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.menuScroll.slideShow && this.whichPanel != 0 && this.menuScroll.activeSubMenu != this) {
            return true;
        }
        this.run = false;
        this.adjYpos = this.startLines + this.ypos;
        this.diff = n2 - this.adjYpos;
        if (n2 < this.adjYpos || n2 > this.adjYpos + this.totLines - 1) {
            return true;
        }
        final int n3 = this.diff / this.lineSpace;
        this.lineDown = this.lineSpace * n3;
        this.lineDown += this.startLines;
        final Enumeration<Integer> elements = this.mapVector.elementAt(n3).elements();
        final int intValue = elements.nextElement();
        final int intValue2 = elements.nextElement();
        if (intValue >= this.linkArray.length || this.linkArray[intValue].equals("")) {
            this.lineDown = 0;
            return true;
        }
        if (event.clickCount == this.linkClick) {
            this.clicked = true;
            if (this.whichPanel != 0 || this.menuScroll.slideShow) {
                this.jumpToUrl(this.linkArray[intValue]);
                this.showStatus(this.menuScroll.status = this.linkArray[intValue]);
                return true;
            }
            boolean b = false;
            for (int i = 0; i < this.menuScroll.menuScrollerArray.length; ++i) {
                if (this.linkArray[intValue].equals(this.menuScroll.menuScrollerNameArray[i])) {
                    b = true;
                }
            }
            if (b) {
                this.s = intValue;
                this.q = intValue2;
                this.activeSubMenu = this.menuScroll.activeSubMenu;
                if (this.activeSubMenu != null) {
                    final int n4 = this.size().width - 3;
                    final int n5 = this.startLines + this.lineSpace * (this.oldActiveVectorPointer + 1) - this.sizeArray[this.oldActiveArrayPointer] / 3;
                    this.offscreeng.setColor(this.menuScroll.arrowColor);
                    final Polygon polygon = new Polygon();
                    polygon.addPoint(n4 - 5, n5 - 5);
                    polygon.addPoint(n4 - 5, n5 + 5);
                    polygon.addPoint(n4, n5);
                    this.offscreeng.fillPolygon(polygon);
                    this.activeSubMenu.up = false;
                }
                if (this.activeSubMenu == null) {
                    this.scrollup();
                }
            }
            else {
                this.jumpToUrl(this.linkArray[intValue]);
                this.showStatus(this.menuScroll.status = this.linkArray[intValue]);
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (!this.menuScroll.slideShow && this.whichPanel != 0 && this.menuScroll.activeSubMenu != this) {
            return true;
        }
        final int n3 = this.startLines + this.ypos;
        this.ypos = n2 - this.startLines - this.diff;
        if (this.ypos <= 0) {
            this.ypos = 0;
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.mouseexit = false;
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouseexit = true;
        if (!this.run) {
            return true;
        }
        if (this.lastLink >= 0) {
            this.highliteLines(this.passLine = this.lastLink, 1);
            this.lastLink = -1;
            this.repaint();
            this.showStatus(this.menuScroll.status = "");
        }
        this.lineSelect = -1;
        this.lastLink = -1;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.menuScroll.slideShow && this.whichPanel != 0 && this.menuScroll.activeSubMenu != this) {
            return true;
        }
        if (this.clicked) {
            this.highliteLines(this.passLine = this.lineSelect, 0);
            this.repaint();
            this.clicked = false;
        }
        this.lineDown = 0;
        return this.run = true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.offscreen == null) {
            this.makeImage();
        }
        if (this.checkError) {
            this.showStatus(this.error);
            return;
        }
        this.offscreeng2.setColor(this.appletColor);
        this.offscreeng2.fillRect(0, 0, this.size().width, this.height);
        this.offscreeng2.drawImage(this.offscreen, 0, this.ypos, this);
        graphics.drawImage(this.offscreen2, 0, 0, this);
    }
    
    void pause(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void run() {
        while (this.thread.isAlive()) {
            if (this.run && this.up) {
                if (this.ypos - this.scrollSpeed > this.scrollSpeed) {
                    this.ypos -= this.scrollSpeed;
                }
                else {
                    this.ypos = 0;
                }
            }
            if (this.run && !this.up) {
                if (this.ypos < this.size().height + this.scrollSpeed * 20) {
                    this.ypos += this.scrollSpeed;
                }
                else {
                    this.run = false;
                    this.paint = false;
                    this.menuScroll.menuScrollerArray[0].scrollup();
                }
            }
            if (this.whichPanel == 0 || this.paint) {
                this.repaint();
                this.highlight(this.mousex, this.mousey);
            }
            this.pause(this.pauseValue);
        }
    }
    
    void scrollup() {
        if (this.menuScroll.slideShow) {
            return;
        }
        this.menuScroll.layout.show(this.menuScroll.p2, this.linkArray[this.s]);
        this.MStemp = this.menuScroll.getMenu(this.linkArray[this.s]);
        if (this.menuScroll.activeSubMenu == this.MStemp) {
            this.menuScroll.activeSubMenu = null;
            return;
        }
        this.MStemp.paint = true;
        this.MStemp.up = true;
        this.MStemp.run = true;
        this.menuScroll.activeSubMenu = this.MStemp;
        this.showStatus(this.menuScroll.status = "");
        final int n = this.size().width - 3;
        final int n2 = this.startLines + this.lineSpace * (this.q + 1) - this.sizeArray[this.s] / 3;
        this.offscreeng.setColor(this.menuScroll.arrowHighliteColor);
        final Polygon polygon = new Polygon();
        polygon.addPoint(n - 5, n2 - 5);
        polygon.addPoint(n - 5, n2 + 5);
        polygon.addPoint(n, n2);
        this.offscreeng.fillPolygon(polygon);
        this.oldActiveVectorPointer = this.q;
        this.oldActiveArrayPointer = this.s;
    }
    
    boolean setBoolean(final String s) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return false;
        }
        this.z = parameter.compareTo("yes");
        return this.z == 0;
    }
    
    void showStatus(final String s) {
        if (this.checkError) {
            this.menuScroll.showStatus(this.error);
        }
        else {
            this.menuScroll.showStatus(this.menuScroll.status);
        }
    }
    
    public void start() {
        if (this.thread == null) {
            this.thread = new Thread(this);
        }
        this.thread.start();
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
        }
        this.thread = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    void wordWrap(final String s, final Vector vector, final int n) {
        int n2 = 2;
        if (this.whichPanel == 0) {
            boolean b = false;
            for (int i = 0; i < this.menuScroll.menuScrollerArray.length; ++i) {
                if (this.linkArray[n].equals(this.menuScroll.menuScrollerNameArray[i])) {
                    b = true;
                }
            }
            if (b) {
                n2 += 6;
            }
        }
        String string = "";
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (string.equals("")) {
                string = nextToken;
            }
            else if (this.fontMetrics.stringWidth(String.valueOf(string) + " " + nextToken) < this.w - this.offset - n2) {
                string = String.valueOf(string) + " " + nextToken;
            }
            else {
                vector.addElement(string);
                string = nextToken;
            }
        }
        vector.addElement(string);
    }
}
