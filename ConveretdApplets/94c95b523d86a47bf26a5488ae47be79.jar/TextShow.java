import java.awt.Component;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.awt.Rectangle;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.io.DataInputStream;
import java.io.InputStream;
import java.awt.Image;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.StringTokenizer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TextShow extends Applet implements Runnable
{
    int[] fnSize;
    int[] fnStyle;
    int[] linkNum;
    int[] linkX;
    int[] linkY;
    int[] linkH;
    int[] linkW;
    int[] linkC;
    int[] linkFont;
    int[] lineH;
    int fnNum;
    int fnCnum;
    int bgNum;
    int nlP;
    int imageNum;
    int appletW;
    int appletH;
    int drawX;
    int drawY;
    int startX;
    int napTime;
    int napTime1;
    int i;
    int linkInc;
    int linkTemp;
    int linkNum1;
    int linkColor;
    float napTime2;
    String[] fnType;
    String[] target;
    String[] linkStr;
    String str1;
    String Temp;
    String Temp1;
    String check1;
    String fileName;
    StringTokenizer fnT;
    char readChar;
    char fnChar;
    boolean nl;
    boolean ok;
    boolean first;
    boolean start;
    boolean showLink;
    boolean linkB;
    boolean readText;
    boolean linkAll;
    boolean outLink;
    boolean linkRect;
    boolean wait;
    Font[] fn;
    FontMetrics fnM;
    Color[] useColor;
    URL[] link;
    URL fileUrl;
    Graphics offsGr;
    Graphics g;
    Image offsIm;
    Image[] image;
    InputStream textFile;
    DataInputStream in;
    Thread thread;
    
    public void init() {
        this.start = true;
        this.appletW = this.size().width;
        this.appletH = this.size().height;
        this.useColor[0] = new Color(0, 0, 0);
        this.getfont();
        this.getbg();
        this.getlink();
        this.getlinkcolor();
        this.getfile();
        this.fnM = this.getFontMetrics(this.fn[this.fnNum]);
        final int n = 300;
        this.napTime1 = n;
        this.napTime = n;
    }
    
    public void start() {
        this.openfile();
        this.readtext();
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (this.thread != null) {
            try {
                Thread.sleep(this.napTime);
                while (this.wait) {
                    Thread.sleep(this.napTime);
                }
            }
            catch (Exception ex) {}
            this.readtext();
        }
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.start) {
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, this.appletW, this.appletH);
            graphics.setColor(Color.blue);
            graphics.drawString("Loading applet & images", 10, 20);
            this.getimage();
            return;
        }
        this.readText = true;
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        if (this.readText) {
            graphics.drawImage(this.offsIm, 0, 0, this);
            this.readText = false;
            return;
        }
        if (!this.linkAll) {
            graphics.setColor(this.useColor[this.fnCnum]);
            graphics.setFont(this.fn[this.linkFont[this.i]]);
            graphics.drawString(this.linkStr[this.i], this.linkX[this.i], this.linkY[this.i]);
            graphics.fillRect(this.linkX[this.i], this.linkY[this.i] + this.lineH[this.i], this.linkW[this.i], (this.lineH[this.i] < 3) ? 2 : this.lineH[this.linkInc]);
        }
        if (this.linkAll) {
            if (this.linkRect) {
                graphics.setColor(this.useColor[this.bgNum]);
                graphics.drawRect(0, 0, this.appletW - 1, this.appletH - 1);
                return;
            }
            graphics.setColor(this.useColor[this.linkColor]);
            graphics.drawRect(0, 0, this.appletW - 1, this.appletH - 1);
        }
    }
    
    public void openfile() {
        try {
            this.fileUrl = new URL(this.getDocumentBase(), this.fileName);
            this.textFile = this.fileUrl.openStream();
            this.in = new DataInputStream(this.textFile);
        }
        catch (Exception ex) {}
    }
    
    public void readtext() {
        if (!this.start) {
            this.readText = true;
            this.outLink = true;
            this.offsIm = this.createImage(this.appletW, this.appletH);
            this.offsGr = this.offsIm.getGraphics();
            this.i = 1;
            while (this.i < 21) {
                this.linkX[this.i] = -1;
                ++this.i;
            }
            this.str1 = "";
            this.linkInc = 0;
            this.first = true;
            this.linkAll = false;
            this.linkB = false;
            try {
                Label_0208: {
                    break Label_0208;
                    char readChar;
                    do {
                        if (this.readChar == '\\') {
                            this.check();
                        }
                        if (this.ok) {
                            if (this.readChar == '\r' || this.readChar == '\n') {
                                this.readChar = ' ';
                            }
                            else {
                                this.str1 = String.valueOf(this.str1) + String.valueOf(this.readChar);
                            }
                            if (this.readChar == ' ') {
                                this.offsImg();
                            }
                        }
                        this.ok = true;
                        readChar = (char)this.in.read();
                        this.readChar = readChar;
                    } while (readChar != '~');
                }
            }
            catch (Exception ex) {}
            this.napTime = this.napTime1;
            if (this.str1.equals("EOF")) {
                this.napTime = 0;
                this.drawX = this.startX;
                this.drawY = this.fnM.getHeight();
                this.openfile();
                return;
            }
            this.offsImg();
            this.repaint();
            this.drawX = this.startX;
            this.drawY = this.fnM.getHeight();
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.linkAll || this.linkB) {
            this.getAppletContext().showDocument(this.link[this.linkNum1], this.target[this.linkNum1]);
        }
        else if (!this.readText) {
            this.i = 1;
            while (this.i < 21 && this.linkX[this.i] != -1) {
                if (this.isinside(n, n2)) {
                    if (this.linkNum[this.i] == -1) {
                        this.wait = false;
                        break;
                    }
                    this.getAppletContext().showDocument(this.link[this.linkNum[this.i]], this.target[this.linkNum[this.i]]);
                    break;
                }
                else {
                    ++this.i;
                }
            }
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (!this.linkAll) {
            if (!this.readText && this.outLink) {
                this.i = 1;
                while (this.i < 21) {
                    if (this.linkX[this.i] == -1) {
                        break;
                    }
                    if (this.isinside(n, n2)) {
                        this.outLink = false;
                        this.fnCnum = this.linkColor;
                        this.repaint();
                        break;
                    }
                    ++this.i;
                }
            }
            else if (!this.readText && !this.isinside(n, n2)) {
                this.fnCnum = this.linkC[this.i];
                this.outLink = true;
                this.repaint();
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus("TextShow 1.1 Free for non-commercial use, By Menashe Cohen (menashec@balu.com)");
        if (this.linkAll) {
            this.linkRect = false;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.linkAll) {
            this.linkRect = true;
            this.repaint();
        }
        else if (!this.outLink) {
            this.readText = true;
            this.outLink = true;
            this.repaint();
        }
        return true;
    }
    
    public boolean isinside(final int n, final int n2) {
        return new Rectangle(this.linkX[this.i], this.linkY[this.i] - this.linkH[this.i] + 5, this.linkW[this.i], this.linkH[this.i] + 4).inside(n, n2);
    }
    
    public boolean offsImg() {
        if (this.drawX + this.fnM.stringWidth(this.str1) > this.appletW - 7) {
            this.drawX = this.startX;
            this.drawY = this.drawY + this.fnM.getHeight() + this.nlP;
        }
        if (this.nl) {
            this.nl = false;
            this.drawY += this.nlP;
            this.drawX = this.startX;
            this.nlP = 0;
        }
        this.offsGr.setFont(this.fn[this.fnNum]);
        this.offsGr.setColor(this.useColor[this.fnCnum]);
        this.offsGr.drawString(this.str1, this.drawX, this.drawY);
        if (this.showLink) {
            this.linkX[this.linkInc] = this.drawX;
            this.linkY[this.linkInc] = this.drawY;
            this.linkC[this.linkInc] = this.fnCnum;
            this.linkFont[this.linkInc] = this.fnNum;
            this.linkH[this.linkInc] = this.fnM.getHeight();
            this.linkW[this.linkInc] = this.fnM.stringWidth(this.str1.substring(0, this.str1.length() - 1));
            this.linkStr[this.linkInc] = this.str1;
            this.lineH[this.linkInc] = this.fnM.getDescent() - 2;
            this.offsGr.fillRect(this.drawX, this.drawY + this.lineH[this.linkInc], this.fnM.stringWidth(this.str1.substring(0, this.str1.length() - 1)), (this.lineH[this.linkInc] < 3) ? 2 : this.lineH[this.linkInc]);
            this.showLink = false;
        }
        this.drawX += this.fnM.stringWidth(this.str1);
        this.str1 = "";
        return true;
    }
    
    public boolean getfile() {
        this.Temp = this.getParameter("file");
        if (this.Temp != null) {
            this.fileName = this.Temp;
        }
        else {
            this.fileName = "textshow.txt";
        }
        return true;
    }
    
    public boolean getlinkcolor() {
        this.Temp = this.getParameter("linkcolor");
        if (this.Temp == null) {
            this.linkColor = 0;
        }
        else {
            try {
                this.linkColor = Integer.parseInt(this.Temp);
            }
            catch (Exception ex) {
                this.linkColor = 0;
            }
        }
        return true;
    }
    
    public boolean getfont() {
        this.i = 0;
        while (this.i < 21) {
            this.Temp = this.getParameter("font" + this.i);
            if (this.Temp != null) {
                this.getfont1();
            }
            else {
                this.fn[this.i] = new Font("Tahoma", 0, 12);
                this.fnM = this.getFontMetrics(this.fn[this.i]);
                this.drawY = this.fnM.getHeight();
            }
            ++this.i;
        }
        return true;
    }
    
    public boolean getfont1() {
        this.fnType[this.i] = "";
        this.fnT = new StringTokenizer(this.Temp, ",");
        this.fnType[this.i] = this.fnT.nextToken();
        if (this.fnType[this.i] == null) {
            this.fnType[this.i] = "Tahoma";
        }
        if (this.fnT.hasMoreTokens()) {
            try {
                this.fnSize[this.i] = Integer.parseInt(this.fnT.nextToken());
            }
            catch (Exception ex) {
                this.fnSize[this.i] = 12;
            }
        }
        if (this.fnT.hasMoreTokens()) {
            this.Temp1 = this.fnT.nextToken();
            if (this.Temp1.equalsIgnoreCase("n")) {
                this.fnStyle[this.i] = 0;
            }
            else if (this.Temp1.equalsIgnoreCase("b")) {
                this.fnStyle[this.i] = 1;
            }
            else if (this.Temp1.equalsIgnoreCase("i")) {
                this.fnStyle[this.i] = 2;
            }
            else if (this.Temp1.equalsIgnoreCase("bi")) {
                this.fnStyle[this.i] = 3;
            }
            else {
                this.fnStyle[this.i] = 0;
            }
        }
        this.fn[this.i] = new Font(this.fnType[this.i], this.fnStyle[this.i], this.fnSize[this.i]);
        return true;
    }
    
    public boolean getbg() {
        this.i = 0;
        this.Temp = this.getParameter("colors");
        if (this.Temp == null) {
            this.i = 1;
            while (this.i < 21) {
                this.useColor[this.i] = new Color(255, 255, 255);
                ++this.i;
            }
        }
        else {
            this.fnT = new StringTokenizer(this.Temp, ",");
            while (this.fnT.hasMoreTokens() && this.i < 21) {
                ++this.i;
                this.Temp1 = this.fnT.nextToken();
                if (this.Temp1.length() == 7 && this.Temp1.charAt(0) == '#') {
                    try {
                        this.useColor[this.i] = new Color(Integer.parseInt(this.Temp1.substring(1, 3), 16), Integer.parseInt(this.Temp1.substring(3, 5), 16), Integer.parseInt(this.Temp1.substring(5, 7), 16));
                        this.bgNum = 1;
                    }
                    catch (Exception ex) {
                        this.useColor[this.i] = new Color(255, 255, 255);
                    }
                }
                else {
                    this.useColor[this.i] = new Color(255, 255, 255);
                }
            }
        }
        return true;
    }
    
    public boolean getimage() {
        this.i = 0;
        this.Temp = this.getParameter("images");
        if (this.Temp != null) {
            this.fnT = new StringTokenizer(this.Temp, ",");
            while (this.fnT.hasMoreTokens() && this.i < 10) {
                ++this.i;
                this.Temp1 = this.fnT.nextToken();
                try {
                    this.image[this.i] = this.getImage(new URL(this.getDocumentBase(), this.Temp1));
                }
                catch (MalformedURLException ex) {}
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(this.image[this.i], this.i);
                try {
                    mediaTracker.waitForID(this.i);
                }
                catch (InterruptedException ex2) {}
            }
        }
        this.start = false;
        return true;
    }
    
    public boolean getlink() {
        this.i = 1;
        while (this.i < 21) {
            this.Temp = this.getParameter("link" + this.i);
            if (this.Temp != null) {
                this.getlink1();
            }
            else {
                try {
                    this.link[this.i] = new URL(null);
                }
                catch (MalformedURLException ex) {}
                this.target[this.i] = null;
            }
            ++this.i;
        }
        return true;
    }
    
    public boolean getlink1() {
        this.fnT = new StringTokenizer(this.Temp, ",");
        this.Temp1 = this.fnT.nextToken();
        try {
            if (this.Temp1.substring(0, 3).equalsIgnoreCase("http")) {
                this.link[this.i] = new URL(this.fnT.nextToken());
            }
            else {
                this.link[this.i] = new URL(this.getDocumentBase(), this.Temp1);
            }
        }
        catch (MalformedURLException ex) {
            this.link[this.i] = null;
        }
        if (this.fnT.hasMoreTokens()) {
            this.target[this.i] = this.fnT.nextToken();
        }
        else {
            this.target[this.i] = "_self";
        }
        return true;
    }
    
    public boolean check() {
        this.check1 = "";
        this.i = 0;
        try {
            while ((this.readChar = (char)this.in.read()) != '\\') {
                this.check1 = String.valueOf(this.check1) + String.valueOf(this.readChar);
                ++this.i;
                if (this.i <= 4) {
                    continue;
                }
                break;
            }
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }
        Label_1147: {
            if (this.check1.length() == 4) {
                this.Temp = this.check1.substring(0, 2);
                if (this.check1.substring(0, 1).equalsIgnoreCase("n")) {
                    if (this.str1 != "") {
                        this.offsImg();
                    }
                    try {
                        this.nlP = Integer.parseInt(this.check1.substring(1, 4));
                    }
                    catch (Exception ex2) {
                        this.nlP = 0;
                    }
                    this.nl = true;
                    this.ok = false;
                }
                else if (this.Temp.equalsIgnoreCase("fn")) {
                    if (this.str1 != "") {
                        this.offsImg();
                    }
                    try {
                        this.fnNum = Integer.parseInt(this.check1.substring(2, 4));
                    }
                    catch (Exception ex3) {
                        this.fnNum = 0;
                    }
                    this.fnM = this.getFontMetrics(this.fn[this.fnNum]);
                    if (this.first) {
                        this.drawY = this.fnM.getHeight();
                        this.first = false;
                    }
                    this.ok = false;
                }
                else if (this.Temp.equalsIgnoreCase("fc")) {
                    if (this.str1 != "") {
                        this.offsImg();
                    }
                    try {
                        this.fnCnum = Integer.parseInt(this.check1.substring(2, 4));
                    }
                    catch (Exception ex4) {
                        this.fnCnum = 0;
                    }
                    this.ok = false;
                }
                else if (this.Temp.equalsIgnoreCase("bg")) {
                    if (this.str1 != "") {
                        this.offsImg();
                    }
                    try {
                        this.bgNum = Integer.parseInt(this.check1.substring(2, 4));
                    }
                    catch (Exception ex5) {
                        this.bgNum = 0;
                    }
                    this.offsGr.setColor(this.useColor[this.bgNum]);
                    this.offsGr.fillRect(0, 0, this.appletW, this.appletH);
                    this.ok = false;
                }
                else if (this.Temp.equalsIgnoreCase("lk")) {
                    if (this.str1 != "") {
                        this.offsImg();
                    }
                    try {
                        this.linkTemp = Integer.parseInt(this.check1.substring(2, 4));
                        ++this.linkInc;
                        this.linkNum[this.linkInc] = this.linkTemp;
                        this.showLink = true;
                    }
                    catch (Exception ex6) {
                        this.linkTemp = 0;
                    }
                    this.ok = false;
                }
                else if (this.check1.substring(0, 4).equalsIgnoreCase("wait")) {
                    if (this.str1 != "") {
                        this.offsImg();
                    }
                    ++this.linkInc;
                    this.linkNum[this.linkInc] = -1;
                    this.napTime1 = 1000;
                    this.wait = true;
                    this.showLink = true;
                    this.ok = false;
                }
                else {
                    if (!this.Temp.equalsIgnoreCase("la")) {
                        if (!this.Temp.equalsIgnoreCase("lb")) {
                            if (this.Temp.equalsIgnoreCase("pu")) {
                                try {
                                    this.napTime1 = Integer.parseInt(this.check1.substring(2, 4));
                                    this.napTime1 *= 1000;
                                }
                                catch (Exception ex7) {
                                    try {
                                        this.napTime2 = new Float(this.check1.substring(2, 4)) * 1000.0f;
                                        this.napTime1 = (int)this.napTime2;
                                    }
                                    catch (Exception ex8) {
                                        this.napTime1 = 5000;
                                    }
                                }
                                this.ok = false;
                                break Label_1147;
                            }
                            if (this.check1.substring(0, 1).equalsIgnoreCase("i")) {
                                this.offsImg();
                                try {
                                    this.imageNum = Integer.parseInt(this.check1.substring(1, 2));
                                }
                                catch (Exception ex9) {
                                    this.imageNum = 0;
                                }
                                if (this.check1.substring(2, 4).equalsIgnoreCase("tl")) {
                                    this.offsGr.drawImage(this.image[this.imageNum], 0, 0, this);
                                }
                                else if (this.check1.substring(2, 4).equalsIgnoreCase("bl")) {
                                    this.offsGr.drawImage(this.image[this.imageNum], 0, this.appletH - this.image[this.imageNum].getHeight(this), this);
                                }
                                else if (this.check1.substring(2, 4).equalsIgnoreCase("tr")) {
                                    this.offsGr.drawImage(this.image[this.imageNum], this.appletW - this.image[this.imageNum].getWidth(this), 0, this);
                                }
                                else if (this.check1.substring(2, 4).equalsIgnoreCase("br")) {
                                    this.offsGr.drawImage(this.image[this.imageNum], this.appletW - this.image[this.imageNum].getWidth(this), this.appletH - this.image[this.imageNum].getHeight(this), this);
                                }
                                this.ok = false;
                                break Label_1147;
                            }
                            if (this.check1.substring(0, 1).equalsIgnoreCase("x")) {
                                if (this.str1 != "") {
                                    this.offsImg();
                                }
                                try {
                                    this.startX = Integer.parseInt(this.check1.substring(1, 4));
                                }
                                catch (Exception ex10) {
                                    this.startX = 5;
                                }
                                this.drawX = this.startX;
                                this.ok = false;
                            }
                            break Label_1147;
                        }
                    }
                    try {
                        this.linkNum1 = Integer.parseInt(this.check1.substring(2, 4));
                        if (this.Temp.equalsIgnoreCase("la")) {
                            this.linkAll = true;
                        }
                        else {
                            this.linkB = true;
                        }
                    }
                    catch (Exception ex11) {
                        this.linkNum1 = 0;
                    }
                    this.ok = false;
                }
            }
        }
        if (this.ok) {
            this.str1 = String.valueOf(this.str1) + "\\" + this.check1;
        }
        return true;
    }
    
    public TextShow() {
        this.fnSize = new int[21];
        this.fnStyle = new int[21];
        this.linkNum = new int[21];
        this.linkX = new int[21];
        this.linkY = new int[21];
        this.linkH = new int[21];
        this.linkW = new int[21];
        this.linkC = new int[21];
        this.linkFont = new int[21];
        this.lineH = new int[21];
        this.bgNum = 1;
        this.drawX = 5;
        this.startX = 5;
        this.fnType = new String[21];
        this.target = new String[21];
        this.linkStr = new String[21];
        this.str1 = "";
        this.nl = false;
        this.ok = true;
        this.first = true;
        this.showLink = false;
        this.linkB = false;
        this.readText = true;
        this.linkAll = false;
        this.outLink = true;
        this.wait = false;
        this.fn = new Font[21];
        this.useColor = new Color[21];
        this.link = new URL[21];
        this.image = new Image[10];
    }
}
