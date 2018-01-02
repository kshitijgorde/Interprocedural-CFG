import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;
import java.awt.Color;
import java.awt.MediaTracker;
import java.io.InputStream;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class FindIt2Frame extends Applet implements MouseListener, MouseMotionListener
{
    Pscreen ps1;
    Pscreen ps2;
    TimeLine tm;
    InfoCanvas info;
    Level lvl;
    InputStream input;
    MediaTracker mt;
    Color bgcolor;
    Color bgColor;
    Color string1Color;
    Color string2Color;
    int difficulty;
    int picW;
    int picH;
    int intRead;
    int loadCount;
    int totalFiles;
    int numberOfLevels;
    int numberOfPictures;
    int[] xcoV;
    int[] ycoV;
    int[] wcoV;
    int[] hcoV;
    int loadPercent;
    int level;
    int psWidth;
    int psHeight;
    int goXco;
    int goYco;
    int startXco;
    int startYco;
    int raXco;
    int raYco;
    int endXco;
    int endYco;
    int frameWidth;
    int frameHeight;
    int score;
    int itemFound;
    int psLoaded;
    String raString1;
    String raString2;
    String raString;
    String pauseString1;
    String pauseString2;
    String pauseString;
    String title;
    String[] rightPic;
    String[] wrongPic;
    char chRead;
    boolean useRandom;
    public boolean runThreads;
    boolean goFlag;
    boolean splashFlag;
    boolean startFlag;
    boolean nextFlag;
    boolean endFlag;
    boolean imagesLoaded;
    boolean gamePaused;
    boolean mouseActive;
    boolean mousePast;
    boolean registered;
    boolean skip;
    public Image rightIcon;
    public Image wrongIcon;
    Image picture1;
    Image picture2;
    Image logo;
    Image gamo;
    Image ra;
    Image bgImage;
    Image nextIm;
    Image endIm;
    Image frameImage;
    URL raUrl;
    Graphics frameBuffer;
    
    public FindIt2Frame() {
        this.psLoaded = 0;
        this.raString1 = "More Games...";
        this.raString2 = "Visit Us Now!";
        this.raString = this.raString1;
        this.pauseString1 = "Pause Game";
        this.pauseString2 = "Resume game";
        this.pauseString = this.pauseString1;
        this.title = "";
        this.useRandom = false;
        this.runThreads = true;
        this.skip = false;
    }
    
    private void addAllFields(final int n) {
        this.repaint();
        if (n != 1) {
            this.add(this.tm);
            this.add(this.info);
        }
        this.add(this.ps1);
        this.add(this.ps2);
    }
    
    public void addItemFound() {
        ++this.itemFound;
        if (this.itemFound == 5) {
            this.addToScore(this.tm.getTimeLeft());
            this.tm.timeUp = true;
            this.info.infoRun = false;
            this.info.repaint();
            this.nextFlag = true;
        }
    }
    
    public void addToScore(final int n) {
        this.score += n;
        this.info.repaint();
    }
    
    private void endGame() {
        this.lvl.resetAllData();
        this.endFlag = true;
        this.removeAllFields(1);
        this.repaint();
    }
    
    public void gameOver() {
        this.lvl.resetAllData();
        this.goFlag = true;
        this.removeAllFields(1);
        this.repaint();
    }
    
    public Image getEmptyImage(final int n, final int n2) {
        return this.createImage(n, n2);
    }
    
    public int getScore() {
        return this.score;
    }
    
    public int getTimeLeft() {
        return this.tm.getTimeLeft();
    }
    
    public void init() {
        this.registered = false;
        try {
            this.input = new URL(this.getDocumentBase(), "data.txt").openStream();
        }
        catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Corrupted data file or file is missing");
        }
        this.readFile();
        try {
            this.raUrl = new URL("Http://www.realapplets.com");
        }
        catch (Exception ex2) {}
        this.mt = new MediaTracker(this);
        this.totalFiles = 8;
        this.setBackground(this.bgColor = this.bgcolor);
        this.frameWidth = 660;
        this.frameHeight = 400;
        this.psWidth = 300;
        this.psHeight = 240;
        (this.lvl = new Level(this)).resetData();
        this.setLayout(null);
        this.startFlag = true;
        this.nextFlag = false;
        final Color white = Color.white;
        this.string2Color = white;
        this.string1Color = white;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.frameImage = this.getEmptyImage(this.frameWidth, this.frameHeight);
        this.frameBuffer = this.frameImage.getGraphics();
        this.ps1 = new Pscreen(this, this.lvl, 1);
        this.ps2 = new Pscreen(this, this.lvl, 2);
        this.info = new InfoCanvas(this);
        this.tm = new TimeLine(this);
        this.repaint();
    }
    
    public void initLoad() {
        this.nextFlag = false;
        this.ps1.psCount = 0;
        this.ps2.psCount = 0;
        this.ps1.imagesLoaded = false;
        this.ps2.imagesLoaded = false;
        this.paint(this.getGraphics());
        this.ps1.repaint();
        this.ps2.repaint();
        System.out.println("Starting..." + this.ps1.psCount + " " + this.ps2.psCount);
        while (this.ps1.psCount == 0 || this.ps2.psCount == 0) {
            if (this.ps1.psCount == 0) {
                this.ps1.paint(this.ps1.getGraphics());
            }
            if (this.ps2.psCount == 0) {
                this.ps2.paint(this.ps2.getGraphics());
            }
            System.out.println("looping..." + this.ps1.psCount + " " + this.ps2.psCount);
        }
        this.nextLevel();
    }
    
    private void loadGraphics(final int n) {
        switch (n) {
            case 1: {
                this.wrongIcon = this.loadImage("x.gif");
                break;
            }
            case 2: {
                this.logo = this.loadImage("logo.jpg");
                this.startXco = (this.frameWidth - this.picW) / 2;
                this.startYco = 30;
                break;
            }
            case 3: {
                this.gamo = this.loadImage("gamo.gif");
                this.goXco = (this.frameWidth - this.picW) / 2;
                this.goYco = 40;
                break;
            }
            case 4: {
                this.ra = this.loadImage("ra.gif");
                this.raXco = (this.frameWidth - this.picW) / 2;
                this.raYco = 20;
                break;
            }
            case 5: {
                this.bgImage = this.loadImage("bg.gif");
                break;
            }
            case 6: {
                this.rightIcon = this.loadImage("v.gif");
                break;
            }
            case 7: {
                this.nextIm = this.loadImage("welldone.gif");
                break;
            }
            case 8: {
                this.endIm = this.loadImage("end.gif");
                this.endXco = (this.frameWidth - this.picW) / 2;
                this.endYco = 40;
                break;
            }
        }
        this.repaint();
    }
    
    public Image loadImage(final String s) {
        ++this.loadCount;
        final Image image = this.getImage(this.getDocumentBase(), s);
        this.mt.addImage(image, 0);
        try {
            this.mt.waitForAll(0L);
        }
        catch (Exception ex) {
            System.out.println("Error loading " + s);
        }
        this.picW = image.getWidth(this);
        this.picH = image.getHeight(this);
        return image;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x > 30 && x < 130 && y > 360 && y < 385) {
            if (!this.gamePaused) {
                this.pauseGame();
            }
            this.getAppletContext().showDocument(this.raUrl, "_blank");
        }
        else if (x > 525 && x < 625 && y > 360 && y < 385 && !this.goFlag && !this.nextFlag && !this.startFlag && !this.endFlag) {
            this.pauseGame();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x > 30 && x < 130 && y > 360 && y < 385) {
            this.string1Color = Color.green;
            this.raString = this.raString2;
            this.mousePast = this.mouseActive;
            this.mouseActive = true;
        }
        else if (x > 525 && x < 625 && y > 360 && y < 385) {
            if (!this.goFlag && !this.nextFlag && !this.startFlag && !this.endFlag) {
                this.string2Color = Color.green;
            }
            this.mousePast = this.mouseActive;
            this.mouseActive = true;
        }
        else {
            this.string1Color = Color.white;
            this.string2Color = Color.white;
            this.raString = this.raString1;
            this.mousePast = this.mouseActive;
            this.mouseActive = false;
        }
        if (this.mouseActive != this.mousePast) {
            this.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        mouseEvent.getX();
        mouseEvent.getY();
        if (this.startFlag) {
            this.startFlag = false;
            this.repaint();
            this.removeAllFields(0);
            this.screenSetup();
            this.reload();
        }
        else if (this.goFlag) {
            this.goFlag = false;
            this.startFlag = true;
            this.removeAllFields(0);
        }
        else if (this.endFlag) {
            this.endFlag = false;
            this.addAllFields(1);
            this.reload();
        }
        else if (this.splashFlag) {
            this.splashFlag = false;
            this.repaint();
            this.addAllFields(1);
            this.skip = true;
            this.initLoad();
        }
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void nextLevel() {
        this.info.repaint();
        this.itemFound = 0;
        if (this.lvl.getLevel() % 2 == 0 && !this.skip && this.lvl.getLevel() != 0) {
            this.removeAllFields(1);
            this.splashFlag = true;
            this.repaint();
        }
        else {
            if (this.lvl.getLevel() < this.numberOfLevels) {
                this.updateScreens();
                this.lvl.next();
                this.tm.reset();
                this.ps1.resetData();
                this.ps2.resetData();
                this.updateScreens();
                this.ps1.getNewPictures(1);
                this.ps2.getNewPictures(2);
            }
            else if (this.lvl.getLevel() == this.numberOfLevels) {
                this.endGame();
            }
            this.splashFlag = false;
        }
        this.skip = false;
    }
    
    public void paint(final Graphics graphics) {
        this.frameBuffer.setColor(this.bgColor);
        this.frameBuffer.fillRect(0, 0, this.frameWidth, this.frameHeight);
        if (this.loadCount < this.totalFiles) {
            if (this.loadCount != 0) {
                this.loadPercent = this.loadCount * 100 / this.totalFiles;
            }
            this.frameBuffer.setColor(Color.gray);
            this.frameBuffer.fillRect(103, 123, 200, 40);
            this.frameBuffer.setColor(Color.white);
            this.frameBuffer.drawString("Find It 2 (Version1.0) by RealApplets.com", 60, 50);
            this.frameBuffer.drawString("Loading Files...", 60, 80);
            this.frameBuffer.fillRect(100, 120, 2 * this.loadPercent, 40);
            this.frameBuffer.drawString("Unregistered Version", 60, 200);
            this.frameBuffer.setColor(this.bgColor);
            this.frameBuffer.drawString(this.loadPercent + "%", 150, 140);
            this.loadGraphics(this.loadCount + 1);
        }
        else {
            this.frameBuffer.drawImage(this.bgImage, 0, 0, this);
            this.frameBuffer.setColor(this.string1Color);
            this.frameBuffer.drawString(this.raString, 40, 375);
            this.frameBuffer.setColor(this.string2Color);
            this.frameBuffer.drawString(this.pauseString, 540, 375);
            this.frameBuffer.setColor(this.bgColor);
            if (this.startFlag) {
                this.frameBuffer.drawImage(this.logo, this.startXco, this.startYco, this);
            }
            if (this.goFlag) {
                this.frameBuffer.drawImage(this.gamo, this.goXco, this.goYco, this);
            }
            if (this.splashFlag) {
                this.frameBuffer.drawImage(this.ra, this.raXco, this.raYco, this);
            }
            if (this.endFlag) {
                this.frameBuffer.drawImage(this.endIm, this.endXco, this.endYco, this);
            }
        }
        graphics.drawImage(this.frameImage, 0, 0, this);
    }
    
    private void pauseGame() {
        if (!this.gamePaused) {
            this.gamePaused = true;
            this.tm.timeUp = true;
            this.info.infoRun = false;
            this.pauseString = this.pauseString2;
        }
        else {
            this.gamePaused = false;
            this.tm.timeUp = false;
            this.info.infoRun = true;
            this.pauseString = this.pauseString1;
        }
        this.updateScreens();
    }
    
    public void readFile() {
        try {
            System.out.println("Reading file...");
            this.chRead = '\n';
            this.title = this.readString();
            final String string = this.readString();
            this.bgcolor = new Color(Integer.parseInt(string.substring(0, 3)), Integer.parseInt(string.substring(4, 7)), Integer.parseInt(string.substring(8, 11)));
            this.numberOfPictures = Integer.parseInt(this.readString());
            this.numberOfLevels = Integer.parseInt(this.readString());
            if (!this.registered && this.numberOfLevels > 10) {
                this.numberOfLevels = 10;
            }
            this.rightPic = new String[this.numberOfPictures];
            this.wrongPic = new String[this.numberOfPictures];
            this.xcoV = new int[this.numberOfPictures * 5];
            this.ycoV = new int[this.numberOfPictures * 5];
            this.wcoV = new int[this.numberOfPictures * 5];
            this.hcoV = new int[this.numberOfPictures * 5];
            if (this.readString().indexOf("Y") != -1) {
                this.useRandom = true;
            }
            this.useRandom = true;
            for (int i = 0; i < this.numberOfPictures; ++i) {
                this.rightPic[i] = this.readString();
                this.wrongPic[i] = this.readString();
                final String string2 = this.readString();
                this.xcoV[5 * i] = Integer.parseInt(string2.substring(0, 3));
                this.xcoV[5 * i + 1] = Integer.parseInt(string2.substring(4, 7));
                this.xcoV[5 * i + 2] = Integer.parseInt(string2.substring(8, 11));
                this.xcoV[5 * i + 3] = Integer.parseInt(string2.substring(12, 15));
                this.xcoV[5 * i + 4] = Integer.parseInt(string2.substring(16, 19));
                final String string3 = this.readString();
                this.ycoV[5 * i] = Integer.parseInt(string3.substring(0, 3));
                this.ycoV[5 * i + 1] = Integer.parseInt(string3.substring(4, 7));
                this.ycoV[5 * i + 2] = Integer.parseInt(string3.substring(8, 11));
                this.ycoV[5 * i + 3] = Integer.parseInt(string3.substring(12, 15));
                this.ycoV[5 * i + 4] = Integer.parseInt(string3.substring(16, 19));
                final String string4 = this.readString();
                this.hcoV[5 * i] = Integer.parseInt(string4.substring(0, 3));
                this.hcoV[5 * i + 1] = Integer.parseInt(string4.substring(4, 7));
                this.hcoV[5 * i + 2] = Integer.parseInt(string4.substring(8, 11));
                this.hcoV[5 * i + 3] = Integer.parseInt(string4.substring(12, 15));
                this.hcoV[5 * i + 4] = Integer.parseInt(string4.substring(16, 19));
                final String string5 = this.readString();
                this.wcoV[5 * i] = Integer.parseInt(string5.substring(0, 3));
                this.wcoV[5 * i + 1] = Integer.parseInt(string5.substring(4, 7));
                this.wcoV[5 * i + 2] = Integer.parseInt(string5.substring(8, 11));
                this.wcoV[5 * i + 3] = Integer.parseInt(string5.substring(12, 15));
                this.wcoV[5 * i + 4] = Integer.parseInt(string5.substring(16, 19));
            }
            this.input.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println("Data Available");
    }
    
    private String readString() {
        String string = "";
        try {
            while (this.chRead != '#') {
                if (this.chRead != '\n' && this.chRead != '\r') {
                    string = String.valueOf(string) + this.chRead;
                }
                this.chRead = (char)this.input.read();
            }
            this.chRead = (char)this.input.read();
        }
        catch (Exception ex) {}
        final String substring = string.substring(0, string.indexOf(42));
        System.out.println("Read: " + substring);
        return substring;
    }
    
    public void reload() {
        this.resetScore();
        this.itemFound = 0;
        this.info.infoRun = true;
        this.initLoad();
        this.tm.timeUp = false;
    }
    
    private void removeAllFields(final int n) {
        if (n != 1) {
            this.remove(this.tm);
            this.remove(this.info);
        }
        this.remove(this.ps1);
        this.remove(this.ps2);
    }
    
    public void resetScore() {
        this.score = 0;
    }
    
    public void screenSetup() {
        this.ps1.setBounds(20, 15, this.psWidth, this.psHeight);
        this.ps2.setBounds(this.psWidth + 40, 15, this.psWidth, this.psHeight);
        this.info.setBounds(375, 280, 260, 60);
        this.tm.setBounds(20, 280, 310, 60);
        this.add(this.ps1);
        this.add(this.ps2);
        this.add(this.info);
        this.add(this.tm);
        this.repaint();
    }
    
    public void stop() {
        try {
            this.runThreads = false;
            System.out.println(this.tm.tmThread);
        }
        catch (Exception ex) {
            this.tm.tmThread = null;
            System.out.println(ex);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void updateScreens() {
        this.ps1.repaint();
        this.ps2.repaint();
        this.info.repaint();
    }
}
