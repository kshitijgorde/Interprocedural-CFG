import java.awt.image.PixelGrabber;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.util.Calendar;
import java.awt.Button;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class pan extends Applet implements Runnable, ActionListener
{
    String dir;
    Thread kicker;
    Image[] imageIn;
    String[] imageNames;
    String[] specialImageNames;
    String Image;
    int indx;
    int currentImage;
    String imageName;
    int nSpeed;
    int originalnSpeed;
    int nHeight;
    int nWidth;
    int inImageWidth;
    int[] nImageWidth;
    int nFrameWidth;
    int nXImagePos;
    int nXPos;
    int nYPos;
    int pixelVal;
    float zoomFactor;
    boolean zoomPressed;
    float zoomVal;
    boolean showButtons;
    boolean numberButtons;
    boolean pauseButton;
    boolean slowButton;
    boolean directionButton;
    boolean fastButton;
    boolean firstButton;
    boolean secondButton;
    boolean thirdButton;
    boolean forthButton;
    boolean suspended;
    boolean priveleged;
    boolean buttonsNotAdded;
    Button buttonZoomP;
    Button buttonZoomM;
    Button buttonP1;
    Button buttonP2;
    Button buttonP3;
    Button buttonP4;
    Button buttonSuspend;
    Button buttonResume;
    Button buttonSlow;
    Button buttonFast;
    Button buttonDirection;
    boolean outOfDate;
    String at;
    boolean imageRetrieved;
    boolean invalidKey;
    boolean badImageWidth;
    String loadMessage;
    String[] imagesStr;
    String badImage;
    boolean debug;
    int maxTrialImage;
    boolean imageChanged;
    String imageParam;
    boolean loading;
    String[] months;
    int[] imagePix;
    String keyParam;
    String subStr;
    String keyValue;
    boolean error;
    boolean unregisteredImage;
    Calendar calendar;
    Calendar calnew;
    Calendar calold;
    Image offScreenImage;
    Graphics offScreenGraphic;
    Image offScreenImage2;
    Graphics offScreenGraphic2;
    MediaTracker tracker;
    String jsImageName;
    boolean javaScript;
    int directionFlag;
    
    public String[][] getParameterInfo() {
        return new String[][] { { "speed", "int", "the delay between frames" }, { "img", "String", "the directory containing the images" } };
    }
    
    public void init() {
        this.imagesStr = new String[4];
        this.at = this.getParameter("privelege");
        if (this.at != null && this.at.equals("%roast&beef*")) {
            this.priveleged = true;
        }
        if (this.debug) {
            System.out.println("priveleged :" + this.priveleged);
        }
        this.at = this.getParameter("debug");
        if (this.at != null && (this.at.equals("ON") || this.at.equals("on"))) {
            this.debug = true;
        }
        if (this.debug) {
            System.out.println("debug :" + this.debug);
        }
        this.at = this.getParameter("speed");
        this.nSpeed = ((this.at != null) ? Integer.valueOf(this.at) : 50);
        this.originalnSpeed = this.nSpeed;
        this.pixelVal = 1;
        this.at = this.getParameter("speed");
        this.nSpeed = ((this.at != null) ? Integer.valueOf(this.at) : 40);
        if (this.debug) {
            System.out.println("nSpeed  :" + this.nSpeed);
        }
        this.at = this.getParameter("backgroundcolor");
        if (this.at != null) {
            this.setBackground(this.decodeColor(this.at));
        }
        this.at = this.getParameter("showbuttons");
        if (this.at != null && (this.at.equals("NO") || this.at.equals("no"))) {
            this.showButtons = false;
        }
        if (this.debug) {
            System.out.println("showButtons :" + this.showButtons);
        }
        this.at = this.getParameter("numberbuttons");
        if (this.at != null && (this.at.equals("NO") || this.at.equals("no"))) {
            this.numberButtons = false;
        }
        if (this.debug) {
            System.out.println("numberButtons :" + this.numberButtons);
        }
        this.at = this.getParameter("pausebutton");
        if (this.at != null && (this.at.equals("NO") || this.at.equals("no"))) {
            this.pauseButton = false;
        }
        if (this.debug) {
            System.out.println("pauseButton :" + this.pauseButton);
        }
        this.at = this.getParameter("slowbutton");
        if (this.at != null && (this.at.equals("NO") || this.at.equals("no"))) {
            this.slowButton = false;
        }
        if (this.debug) {
            System.out.println("slowButton :" + this.slowButton);
        }
        this.at = this.getParameter("directionbutton");
        if (this.at != null && (this.at.equals("NO") || this.at.equals("no"))) {
            this.directionButton = false;
        }
        if (this.debug) {
            System.out.println("directionButton :" + this.directionButton);
        }
        this.at = this.getParameter("fastbutton");
        if (this.at != null && (this.at.equals("NO") || this.at.equals("no"))) {
            this.fastButton = false;
        }
        if (this.debug) {
            System.out.println("fastButton :" + this.fastButton);
        }
        this.at = this.getParameter("firstbutton");
        if (this.at != null && (this.at.equals("NO") || this.at.equals("no"))) {
            this.firstButton = false;
        }
        if (this.debug) {
            System.out.println("firstButton :" + this.firstButton);
        }
        this.at = this.getParameter("secondbutton");
        if (this.at != null && (this.at.equals("NO") || this.at.equals("no"))) {
            this.secondButton = false;
        }
        if (this.debug) {
            System.out.println("secondButton :" + this.secondButton);
        }
        this.at = this.getParameter("thirdbutton");
        if (this.at != null && (this.at.equals("NO") || this.at.equals("no"))) {
            this.thirdButton = false;
        }
        if (this.debug) {
            System.out.println("thirdButton :" + this.thirdButton);
        }
        this.at = this.getParameter("forthbutton");
        if (this.at != null && (this.at.equals("NO") || this.at.equals("no"))) {
            this.forthButton = false;
        }
        if (this.debug) {
            System.out.println("forthButton :" + this.forthButton);
        }
        this.nYPos = (this.showButtons ? 40 : 0);
        if (this.debug) {
            System.out.println("showButtons  :" + this.showButtons);
        }
        this.at = this.getParameter("height");
        this.nHeight = ((this.at != null) ? Integer.valueOf(this.at) : 240);
        this.at = this.getParameter("width");
        this.nWidth = ((this.at != null) ? Integer.valueOf(this.at) : 200);
        this.nFrameWidth = this.nWidth;
        if (this.showButtons & this.buttonsNotAdded) {
            this.buttonsNotAdded = false;
            (this.buttonZoomP = new Button("Zoom+")).addActionListener(this);
            (this.buttonZoomM = new Button("Zoom-")).addActionListener(this);
            if (this.pauseButton) {
                this.add(this.buttonSuspend = new Button("Pause"));
                this.buttonSuspend.addActionListener(this);
            }
            if (this.slowButton) {
                this.add(this.buttonSlow = new Button("Slow"));
                this.buttonSlow.addActionListener(this);
            }
            if (this.directionButton) {
                this.add(this.buttonDirection = new Button("<-|->"));
                this.buttonDirection.addActionListener(this);
            }
            if (this.fastButton) {
                this.add(this.buttonFast = new Button("Fast"));
                this.buttonFast.addActionListener(this);
            }
            if (this.numberButtons) {
                if (this.firstButton) {
                    this.add(this.buttonP1 = new Button("1"));
                    this.buttonP1.addActionListener(this);
                }
                if (this.secondButton) {
                    this.add(this.buttonP2 = new Button("2"));
                    this.buttonP2.addActionListener(this);
                }
                if (this.thirdButton) {
                    this.add(this.buttonP3 = new Button("3"));
                    this.buttonP3.addActionListener(this);
                }
                if (this.forthButton) {
                    this.add(this.buttonP4 = new Button("4"));
                    this.buttonP4.addActionListener(this);
                }
            }
        }
        this.getImage();
    }
    
    public void getImage() {
        this.printRegInfo();
        this.imageIn = new Image[4];
        int n = 0;
        this.Image = "image" + n;
        this.tracker = new MediaTracker(this);
        Label_0289: {
            break Label_0289;
            String parameter;
            do {
                this.imageIn[n] = this.getImage(this.getCodeBase(), this.imageParam);
                this.imageNames[n] = this.imageParam;
                if (this.debug) {
                    System.out.println("imageParam :" + this.imageParam);
                }
                this.tracker.addImage(this.imageIn[n], 1);
                this.showStatus("Panorama - loading image file: " + this.Image);
                while ((this.inImageWidth = this.imageIn[n].getWidth(this)) == -1) {}
                this.nImageWidth[n] = this.inImageWidth;
                if (!this.priveleged && !this.HistoGrab(n)) {
                    this.unregisteredImage = true;
                    System.out.println("nImageWidth[i] :" + this.nImageWidth[n]);
                    if (this.nImageWidth[n] > this.maxTrialImage) {
                        this.badImageWidth = true;
                        this.badImage = this.Image;
                        this.imageIn[n] = null;
                    }
                }
                this.indx = ++n;
                this.Image = "image" + this.indx;
                parameter = this.getParameter(this.Image);
                this.imageParam = parameter;
            } while (parameter != null && n < 4);
        }
    }
    
    Color decodeColor(final String s) {
        try {
            int n;
            if (s.startsWith("0x")) {
                n = Integer.parseInt(s.substring(2), 16);
            }
            else if (s.startsWith("#")) {
                n = Integer.parseInt(s.substring(1), 16);
            }
            else if (s.startsWith("0") && s.length() > 1) {
                n = Integer.parseInt(s.substring(1), 8);
            }
            else {
                n = Integer.parseInt(s, 10);
            }
            return new Color(n);
        }
        catch (NumberFormatException ex) {
            return null;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Zoom+")) {
            System.out.println("Zoom+ pressed ...");
            this.zoomUp();
        }
        if (actionCommand.equals("Zoom-")) {
            System.out.println("Zoom+ pressed ...");
            this.zoomDown();
        }
        if (actionCommand.equals("Pause")) {
            if (this.debug) {
                System.out.println("Pause pressed ...");
            }
            this.suspend();
        }
        if (actionCommand.equals("Play")) {
            this.zoomFactor = 1.1f;
            this.resume();
        }
        if (actionCommand.equals("Slow")) {
            if (this.debug) {
                System.out.println("Slow pressed ...");
            }
            this.slowTime();
        }
        if (actionCommand.equals("<-|->")) {
            if (this.debug) {
                System.out.println("Direction pressed ...");
            }
            this.direction();
        }
        if (actionCommand.equals("Fast")) {
            if (this.debug) {
                System.out.println("Fast pressed ...");
            }
            this.fastTime();
        }
        if (actionCommand.equals("1")) {
            if (this.debug) {
                System.out.println("1 pressed ...");
            }
            this.imageMessage(0);
        }
        if (actionCommand.equals("2")) {
            if (this.debug) {
                System.out.println("2 pressed ...");
            }
            this.imageMessage(1);
        }
        if (actionCommand.equals("3")) {
            if (this.debug) {
                System.out.println("3 pressed ...");
            }
            this.imageMessage(2);
        }
        if (actionCommand.equals("4")) {
            if (this.debug) {
                System.out.println("4 pressed ...");
            }
            this.imageMessage(3);
        }
    }
    
    public void javaScript(final String s) {
        final Graphics graphics = this.getGraphics();
        if (this.debug) {
            graphics.drawString("command: " + s, 10, this.nHeight / 2 + 90);
        }
        if (s.equals("Zoom+")) {
            System.out.println("Zoom+ pressed ...");
            this.zoomUp();
        }
        if (s.equals("Zoom-")) {
            System.out.println("Zoom+ pressed ...");
            this.zoomDown();
        }
        if (s.equals("Pause")) {
            if (this.debug) {
                System.out.println("Pause pressed ...");
            }
            this.suspend();
        }
        if (s.equals("Play")) {
            this.zoomFactor = 1.1f;
            this.resume();
        }
        if (s.equals("Slow")) {
            if (this.debug) {
                System.out.println("Slow pressed ...");
            }
            this.slowTime();
        }
        if (s.equals("<-|->")) {
            if (this.debug) {
                System.out.println("Direction pressed ...");
            }
            this.direction();
        }
        if (s.equals("Fast")) {
            if (this.debug) {
                System.out.println("Fast pressed ...");
            }
            this.fastTime();
        }
        if (s.equals("1")) {
            if (this.debug) {
                System.out.println("1 pressed ...");
            }
            this.imageMessage(0);
        }
        if (s.equals("2")) {
            if (this.debug) {
                System.out.println("2 pressed ...");
            }
            this.imageMessage(1);
        }
        if (s.equals("3")) {
            if (this.debug) {
                System.out.println("3 pressed ...");
            }
            this.imageMessage(2);
        }
        if (s.equals("4")) {
            if (this.debug) {
                System.out.println("4 pressed ...");
            }
            this.imageMessage(3);
        }
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        while (this.kicker != null) {
            this.clockTick();
            this.repaint();
            try {
                Thread.sleep(this.nSpeed);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void clockTick() {
        if (this.directionFlag == 1) {
            this.nXImagePos -= this.pixelVal;
            if (this.nXImagePos <= -this.nImageWidth[this.currentImage] || Math.abs(this.nXImagePos) > this.nImageWidth[this.currentImage] - this.nFrameWidth) {
                this.nXImagePos = 0;
            }
            return;
        }
        this.nXImagePos += this.pixelVal;
        if (this.nXImagePos >= 0 || Math.abs(this.nXImagePos) > this.nImageWidth[this.currentImage] - this.nFrameWidth) {
            this.nXImagePos = -this.nImageWidth[this.currentImage] + this.nFrameWidth;
        }
    }
    
    public void pleaseWait() {
        final Graphics graphics = this.getGraphics();
        final Font font = new Font("SansSerif", 1, 14);
        graphics.setFont(font);
        graphics.setColor(Color.blue);
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        graphics.drawString("Panorama Images are now loading ...", (this.nWidth - fontMetrics.stringWidth(this.loadMessage)) / 2, (this.nHeight - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent());
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        if (this.imageChanged) {
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, 0, this.nWidth, this.nHeight);
            this.imageChanged = false;
        }
        if (this.tracker.isErrorAny()) {
            graphics.drawString("Please wait while images load ...", this.nWidth / 2, this.nHeight / 2);
            graphics.setColor(Color.red);
            graphics.fillRect(0, 0, this.nWidth, this.nHeight);
            return;
        }
        this.paint(graphics, this);
    }
    
    public void paint(final Graphics graphics, final Applet applet) {
        if (this.unregisteredImage && this.badImageWidth) {
            graphics.setFont(new Font("SansSerif", 1, 12));
            graphics.setColor(Color.blue);
            graphics.drawString("Panorama: Trial version - " + this.badImage + " - invalid", 10, this.nHeight / 2);
            graphics.drawString("Accepts demo images or widths less than " + this.maxTrialImage + " pixels", 10, this.nHeight / 2 + 15);
            graphics.drawString("Please contact: ", 10, this.nHeight / 2 + 30);
            graphics.drawString("philipmorgan@freezone.co.uk", 10, this.nHeight / 2 + 45);
            graphics.drawString("www.freezone.co.uk/philipmorgan/panorama/pan.htm", 10, this.nHeight / 2 + 60);
            this.unregisteredImage = false;
            this.badImageWidth = false;
            return;
        }
        if (this.imageIn[this.currentImage] != null) {
            graphics.clipRect(this.nXPos, this.nYPos, this.nFrameWidth, this.nHeight);
            graphics.drawImage(this.imageIn[this.currentImage], this.nXImagePos, this.nYPos, this);
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void start() {
        if (this.kicker == null) {
            (this.kicker = new Thread(this)).start();
        }
    }
    
    public void restart() {
        this.stop();
        this.init();
        this.start();
    }
    
    public void suspend() {
        if (this.debug) {
            System.out.println("suspended: " + this.suspended);
        }
        if (this.suspended) {
            this.resume();
            return;
        }
        this.suspended = true;
        this.kicker.suspend();
    }
    
    public void resume() {
        if (!this.suspended) {
            return;
        }
        this.suspended = false;
        this.kicker.resume();
    }
    
    public void slowTime() {
        if (this.pixelVal > 2) {
            this.pixelVal -= 2;
            return;
        }
        this.nSpeed += 15;
    }
    
    public void direction() {
        this.directionFlag = -this.directionFlag;
        if (this.suspended) {
            this.resume();
        }
    }
    
    public void fastTime() {
        if (this.nSpeed > 5) {
            this.nSpeed -= 15;
            return;
        }
        this.pixelVal += 2;
    }
    
    public void imageMessage(final int currentImage) {
        if (this.imageIn[currentImage] == null) {
            return;
        }
        this.imageChanged = true;
        this.pixelVal = 1;
        this.nSpeed = this.originalnSpeed;
        this.currentImage = currentImage;
        this.nXImagePos = 0;
        this.nXPos = 0;
        if (this.suspended) {
            this.resume();
        }
    }
    
    public void zoomUp() {
        System.out.println("Zoom pressed - in zoomUp()  :");
        this.zoomVal *= this.zoomFactor;
        this.zoomPressed = true;
        this.repaint();
    }
    
    public void zoomDown() {
        System.out.println("Zoom pressed - in zoomDown()  :");
        if (this.zoomVal > 0.0f) {
            this.zoomVal /= this.zoomFactor;
            this.zoomPressed = true;
            this.repaint();
        }
    }
    
    public void stop() {
        this.kicker.suspend();
        this.suspended = false;
        this.kicker = null;
        this.destroy();
    }
    
    public boolean HistoGrab(final int n) {
        int i = 0;
        int j = 0;
        final int[] array = new int[256];
        int n2 = 0;
        int n3 = 0;
        if (this.debug) {
            System.out.println("Histograb entered :");
        }
        if (this.debug) {
            this.showStatus("Histograb entered : ");
        }
        while (j < this.specialImageNames.length) {
            if (this.imageNames[n].equals(this.specialImageNames[i])) {
                if (this.debug) {
                    System.out.println("FOUND:  imageNames[" + j + "]: " + this.imageNames[j] + " specialImageNames[" + j + "]: " + this.specialImageNames[j]);
                }
                return true;
            }
            ++j;
        }
        final int width = this.imageIn[n].getWidth(null);
        final int height = this.imageIn[n].getHeight(null);
        final int[] array2 = new int[width * height];
        try {
            new PixelGrabber(this.imageIn[n], 0, 0, width, height, array2, 0, width).grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int k = 0; k < width * height; ++k) {
            final int n4 = array2[k];
            final int n5 = (int)(0.33 * (0xFF & n4 >> 16) + 0.56 * (0xFF & n4 >> 8) + 0.11 * (0xFF & n4));
            final int[] array3 = array;
            final int n6 = n5;
            ++array3[n6];
        }
        for (int l = 0; l < 256; ++l) {
            if (l % 10 == 0) {
                n3 += array[l];
            }
            if (array[l] > n2) {
                n2 = array[l];
            }
        }
        if (this.debug) {
            System.out.println(this.Image + " pixSum: " + n3);
        }
        while (i < this.imagePix.length) {
            if (this.imagePix[i] == n3) {
                if (this.debug) {
                    System.out.println("Image registered - imagePix[" + i + "] : " + this.imagePix[i]);
                }
                return true;
            }
            ++i;
        }
        if (this.debug) {
            System.out.println("Panorama: " + this.Image + " unregistered.");
        }
        return false;
    }
    
    public void printRegInfo() {
        System.out.println(" ");
        System.out.print("[PANORAMA APPLET - VERSION 1.0 - ");
        if (this.priveleged) {
            System.out.println("REGISTERED]");
        }
        else {
            System.out.println("UNREGISTERED]");
        }
        System.out.println(" ");
        System.out.println("AUTHOR: Philip Morgan - COPYRIGHT 1999.");
        System.out.println(" ");
        System.out.println("philipmorgan@freezone.co.uk.");
        System.out.println(" ");
        System.out.println("http://freezone.co.uk/philipmorgan/panorama/pan.htm");
        System.out.println(" ");
        System.out.println("http://panorama.store.com");
    }
    
    public pan() {
        this.imageNames = new String[4];
        this.specialImageNames = new String[] { "ns9.jpg" };
        this.nImageWidth = new int[4];
        this.zoomFactor = 1.1f;
        this.zoomPressed = false;
        this.zoomVal = 1.0f;
        this.showButtons = true;
        this.numberButtons = true;
        this.pauseButton = true;
        this.slowButton = true;
        this.directionButton = true;
        this.fastButton = true;
        this.firstButton = true;
        this.secondButton = true;
        this.thirdButton = true;
        this.forthButton = true;
        this.suspended = false;
        this.priveleged = false;
        this.buttonsNotAdded = true;
        this.outOfDate = false;
        this.imageRetrieved = false;
        this.invalidKey = false;
        this.badImageWidth = false;
        this.loadMessage = "Images loading - please wait a moment ...";
        this.debug = false;
        this.maxTrialImage = 700;
        this.imageChanged = false;
        this.loading = false;
        this.months = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.imagePix = new int[] { 31722, 26372, 26965, 26516, 36834, 31607, 35429, 33096, 24582, 28658, 25357, 28801, 147136 };
        this.error = false;
        this.unregisteredImage = false;
        this.calendar = Calendar.getInstance();
        this.calnew = Calendar.getInstance();
        this.calold = Calendar.getInstance();
        this.directionFlag = 1;
    }
}
