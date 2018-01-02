import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.util.Date;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.FontMetrics;
import java.awt.Panel;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ConsultTreeTrial extends Applet implements Runnable
{
    ConsultImageScroller Mypanel;
    boolean scrollBar;
    String[] SStyleArray;
    int[] StyleMap;
    String[] SColorArray;
    Color[] ColorMap;
    String temp;
    String unregistered;
    String slogan;
    ConsultCanvas consultCanvas;
    Thread thread;
    Image closeFolder;
    Image openFolder;
    Image cross;
    MediaTracker mt;
    Vector folderVector;
    Enumeration e;
    ConsultFolder folder;
    ConsultFolder rootFolder;
    Panel p;
    Vector dv;
    Vector iv;
    FontMetrics fm;
    boolean once;
    String linkFrame;
    String first;
    Color dotColor;
    Color backgroundColor;
    Color boxColor;
    Color plusColor;
    Color highliteColor;
    Color descriptionColor;
    Color descriptionBorder;
    Color borderColor;
    Color textColor;
    boolean scrollHor;
    boolean scrollVert;
    int z;
    int canvasWidth;
    int canvasHeight;
    boolean border;
    boolean borderRaised;
    String treeIndex;
    String treeLink;
    String folderColor;
    String collapsedFolder;
    String expandedFolder;
    String path;
    int fontStyle;
    long displayStatus;
    int nextDispl;
    String htmlPath;
    String htmlTarget;
    
    public void init() {
        super.init();
        this.setLayout(new BorderLayout(0, 0));
        this.checkSecurity();
        this.path = this.getCodeBase().toString();
        this.linkFrame = this.getParameter("linkFrame");
        if (this.linkFrame == null) {
            this.linkFrame = "_top";
        }
        this.folderColor = this.getParameter("folderColor");
        if (this.folderColor == null) {
            this.folderColor = "tan";
        }
        if (this.folderColor.startsWith("book")) {
            this.collapsedFolder = "bookClosedFolder.gif";
            this.expandedFolder = "bookOpenFolder.gif";
        }
        else if (this.folderColor.startsWith("blue")) {
            this.collapsedFolder = "blueClosedFolder.gif";
            this.expandedFolder = "blueOpenFolder.gif";
        }
        else {
            this.collapsedFolder = "tanClosedFolder.gif";
            this.expandedFolder = "tanOpenFolder.gif";
        }
        this.treeIndex = this.getParameter("treeIndex");
        if (this.treeIndex == null) {
            this.treeIndex = "treeIndex.txt";
        }
        this.treeLink = this.getParameter("treeLink");
        if (this.treeLink == null) {
            this.treeLink = "treeLink.txt";
        }
        this.dotColor = this.getColor("dotColor");
        if (this.dotColor == null) {
            this.dotColor = Color.black;
        }
        this.backgroundColor = this.getColor("backgroundColor");
        if (this.backgroundColor == null) {
            this.backgroundColor = Color.lightGray;
        }
        this.setBackground(this.backgroundColor);
        this.boxColor = this.getColor("boxColor");
        if (this.boxColor == null) {
            this.boxColor = Color.gray;
        }
        this.plusColor = this.getColor("plusColor");
        if (this.plusColor == null) {
            this.plusColor = Color.red;
        }
        this.highliteColor = this.getColor("highliteColor");
        if (this.highliteColor == null) {
            this.highliteColor = Color.yellow;
        }
        this.textColor = this.getColor("textColor");
        if (this.textColor == null) {
            this.textColor = Color.black;
        }
        this.descriptionColor = this.getColor("descriptionColor");
        if (this.descriptionColor == null) {
            this.descriptionColor = Color.orange;
        }
        this.descriptionBorder = this.getColor("descriptionBorder");
        if (this.descriptionBorder == null) {
            this.descriptionBorder = Color.black;
        }
        this.scrollHor = this.setBoolean("dragScrollHor");
        this.scrollVert = this.setBoolean("dragScrollVert");
        this.getCanvasSize("canvasSize");
        this.border = this.setBoolean("border");
        this.borderRaised = this.setBoolean("borderRaised");
        this.borderColor = this.getColor("borderColor");
        if (this.borderColor == null) {
            this.borderColor = Color.gray;
        }
        this.z = this.getStyle("fontStyle");
        if (this.z < 0) {
            this.fontStyle = 0;
        }
        else {
            this.fontStyle = this.StyleMap[this.z];
        }
        this.scrollBar = this.setBoolean("scrollBar");
        if (this.scrollBar) {
            this.scrollHor = false;
            this.scrollVert = false;
            this.border = false;
            this.add("Center", this.Mypanel = new ConsultImageScroller(this, this.folderVector));
            this.consultCanvas = this.Mypanel.consultCanvas;
        }
        else {
            this.add("Center", this.consultCanvas = new ConsultCanvas(this, this.folderVector));
        }
        this.getPicImage(this.collapsedFolder, this.expandedFolder, "cross.gif");
        final Graphics g = this.getGraphics();
        final Font f = new Font("Courier", this.fontStyle, 12);
        g.setFont(f);
        this.fm = g.getFontMetrics();
        this.getURL(this.dv, this.treeLink);
        this.getURL(this.iv, this.treeIndex);
        this.first = this.iv.elementAt(0);
        final StringTokenizer t = new StringTokenizer(this.first, ";");
        this.getHTML(this.first = t.nextToken());
        this.folder = new ConsultFolder(this, this.closeFolder, this.openFolder, this.first, true, null, true, this.htmlPath, this.fm, this.consultCanvas, this.htmlTarget);
        this.folderVector.addElement(this.folder);
        this.rootFolder = this.folder;
        this.showStatus(this.slogan);
    }
    
    public void start() {
        if (this.thread == null) {
            this.thread = new Thread(this);
        }
        this.thread.start();
        if (this.once) {
            return;
        }
        final Date date = new Date();
        this.consultCanvas.postEvent(new Event(this.consultCanvas, date.getTime(), 501, 47, 11, 0, 0));
        this.once = true;
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
        }
        this.thread = null;
    }
    
    public void run() {
        while (this.thread.isAlive()) {
            if (System.currentTimeMillis() > this.displayStatus) {
                this.showStatus(this.slogan);
                this.displayStatus = System.currentTimeMillis() + this.nextDispl;
            }
            if (this.consultCanvas.showCross && System.currentTimeMillis() > this.consultCanvas.crossTime + 500L) {
                final Graphics g = this.consultCanvas.getGraphics();
                g.clipRect(4, this.size().height - 31, 26, 26);
                g.drawImage(this.consultCanvas.offscreenImg, this.consultCanvas.x, this.consultCanvas.y, this.consultCanvas);
                this.consultCanvas.showCross = false;
            }
            this.pause(100);
        }
    }
    
    void pause(final int time) {
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException ex) {}
    }
    
    void getURL(final Vector v, final String fileName) {
        URL fileURL = null;
        DataInputStream dataInput = null;
        InputStream input = null;
        try {
            fileURL = new URL(this.getCodeBase() + fileName);
        }
        catch (MalformedURLException ex) {
            System.err.println("Error MalformedURLException");
        }
        try {
            input = fileURL.openStream();
            final BufferedInputStream bis = new BufferedInputStream(input);
            dataInput = new DataInputStream(bis);
            if (fileName.equals(this.treeIndex)) {
                String inputLine;
                while ((inputLine = dataInput.readLine()) != null) {
                    if (inputLine.indexOf(";") == -1) {
                        return;
                    }
                    v.addElement(inputLine);
                }
            }
            if (fileName.equals(this.treeLink)) {
                String inputLine;
                while ((inputLine = dataInput.readLine()) != null) {
                    if (inputLine.indexOf(";") == -1) {
                        return;
                    }
                    v.addElement(inputLine);
                }
            }
        }
        catch (IOException ex2) {
            System.err.println("Input File read failed");
            return;
        }
        try {
            dataInput.close();
        }
        catch (IOException ex3) {
            System.err.println("Error Closing file(s)");
        }
    }
    
    void getHTML(final String FolderName) {
        String vectorElement = null;
        String temp = "";
        final Enumeration e = this.dv.elements();
        while (e.hasMoreElements()) {
            vectorElement = e.nextElement();
            final StringTokenizer t = new StringTokenizer(vectorElement, ";");
            temp = t.nextToken();
            if (FolderName.equals(temp)) {
                this.htmlPath = t.nextToken();
                this.htmlTarget = null;
                while (t.hasMoreTokens()) {
                    this.htmlTarget = t.nextToken();
                }
            }
        }
    }
    
    void getPicImage(final String closeFolder, final String openFolder, final String cross) {
        this.mt = new MediaTracker(this);
        try {
            this.closeFolder = this.getImage(this.getCodeBase(), closeFolder);
            this.openFolder = this.getImage(this.getCodeBase(), openFolder);
            this.cross = this.getImage(this.getCodeBase(), cross);
        }
        catch (Exception e1) {
            System.out.println(e1);
        }
        this.mt.addImage(this.closeFolder, 0);
        this.mt.addImage(this.openFolder, 0);
        this.mt.addImage(this.cross, 0);
        try {
            this.mt.checkID(0, true);
            this.mt.waitForID(0);
        }
        catch (InterruptedException e2) {
            System.out.println(e2);
        }
    }
    
    Color getColor(final String parm) {
        int red = 0;
        int green = 0;
        int blue = 0;
        String sRed = "";
        String sGreen = "";
        String sBlue = "";
        this.temp = this.getParameter(parm);
        if (this.temp == null) {
            return null;
        }
        red = this.temp.indexOf(",");
        if (red <= 0) {
            for (int i = 0; i < this.SColorArray.length; ++i) {
                final int z = this.temp.compareTo(this.SColorArray[i]);
                if (z == 0) {
                    return this.ColorMap[i];
                }
            }
            return null;
        }
        sRed = this.temp.substring(0, red);
        green = this.temp.indexOf(",", red + 1);
        if (green < 0) {
            return null;
        }
        sGreen = this.temp.substring(red + 1, green);
        sBlue = this.temp.substring(green + 1, this.temp.length());
        try {
            red = Integer.valueOf(sRed);
        }
        catch (NumberFormatException ex) {
            return null;
        }
        try {
            green = Integer.valueOf(sGreen);
        }
        catch (NumberFormatException ex2) {
            return null;
        }
        try {
            blue = Integer.valueOf(sBlue);
        }
        catch (NumberFormatException ex3) {
            return null;
        }
        if (red > 255 || green > 255 || blue > 255) {
            return null;
        }
        return new Color(red, green, blue);
    }
    
    void checkSecurity() {
        final String security = this.getParameter("copyright");
        if (security == null) {
            this.showStatus(this.unregistered);
            System.out.println(this.unregistered);
            System.exit(1);
            return;
        }
        final int z = security.compareTo("ConsultTree v1.0 Copyright (c) 1998, consulting.com Inc.");
        if (z == 0) {
            return;
        }
        this.showStatus(this.unregistered);
        System.out.println(this.unregistered);
        System.exit(1);
    }
    
    boolean setBoolean(final String pass) {
        final String StringTemp = this.getParameter(pass);
        if (StringTemp == null) {
            return true;
        }
        this.z = StringTemp.compareTo("yes");
        return this.z == 0;
    }
    
    void getCanvasSize(final String parm) {
        int width = 0;
        String sWidth = "";
        String sHeight = "";
        this.temp = this.getParameter(parm);
        if (this.temp == null) {
            return;
        }
        width = this.temp.indexOf(",");
        if (width > 0) {
            sWidth = this.temp.substring(0, width);
            sHeight = this.temp.substring(width + 1, this.temp.length());
            try {
                this.canvasWidth = Integer.valueOf(sWidth);
            }
            catch (NumberFormatException ex) {
                return;
            }
            try {
                this.canvasHeight = Integer.valueOf(sHeight);
            }
            catch (NumberFormatException ex2) {}
        }
    }
    
    int getStyle(final String parm) {
        this.temp = this.getParameter(parm);
        if (this.temp == null) {
            return -1;
        }
        for (int i = 0; i < this.SStyleArray.length; ++i) {
            final int z = this.temp.compareTo(this.SStyleArray[i]);
            if (z == 0) {
                return i;
            }
        }
        return -1;
    }
    
    public ConsultTreeTrial() {
        this.scrollBar = false;
        this.SStyleArray = new String[] { "PLAIN", "ITALIC", "BOLD" };
        this.StyleMap = new int[] { 0, 2, 1 };
        this.SColorArray = new String[] { "black", "blue", "cyan", "darkGray", "gray", "green", "lightGray", "magenta", "orange", "pink", "red", "white", "yellow" };
        this.ColorMap = new Color[] { Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.white, Color.yellow };
        this.unregistered = "Unregistered: Contact support@consultcom.com";
        this.slogan = "FREE Java Applets at www.consultcom.com";
        this.folderVector = new Vector();
        this.e = this.folderVector.elements();
        this.dv = new Vector(50);
        this.iv = new Vector(50);
        this.once = false;
        this.linkFrame = "";
        this.first = "";
        this.canvasWidth = 500;
        this.canvasHeight = 700;
        this.border = true;
        this.borderRaised = true;
        this.nextDispl = 5000;
    }
}
