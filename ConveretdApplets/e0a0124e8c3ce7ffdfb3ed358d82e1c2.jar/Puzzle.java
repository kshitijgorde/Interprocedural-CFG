import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.Color;
import java.awt.Rectangle;
import java.applet.AudioClip;
import java.awt.Button;
import java.awt.Image;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Puzzle extends Applet
{
    private Vector theArmies;
    private Army activeArmy;
    private Image theImage;
    private int xpieces;
    private int ypieces;
    private Button completeButton;
    private Button restartButton;
    private boolean haveCompleteButton;
    private boolean haveRestartButton;
    private AudioClip clickSound;
    private AudioClip completeSound;
    private int xmax;
    private int ymax;
    private Rectangle finish;
    private Color backColor;
    private Color foreColor;
    private Color boxColor;
    private boolean restartFlag;
    private boolean iHaveDied;
    private String stat;
    private int[] pixels;
    int ps;
    int psplus;
    int xSpan;
    int ySpan;
    private boolean allDone;
    private Image offScreenImage;
    private Image backdropImage;
    private boolean notRepainting;
    private int lastX;
    private int lastY;
    
    public void init() {
        this.getParameters();
        this.setBackground(this.backColor);
        this.setForeground(this.foreColor);
        this.restartFlag = true;
    }
    
    public void start() {
        if (this.restartFlag) {
            try {
                this.allDone = false;
                this.setStat("Cutting Pieces");
                this.offScreenImage = null;
                this.backdropImage = null;
                this.setPieces();
                if (this.haveCompleteButton) {
                    this.add(this.completeButton);
                }
                if (this.haveRestartButton) {
                    this.add(this.restartButton);
                }
                this.restartFlag = false;
                this.setStat("Running");
            }
            catch (OutOfMemoryError outOfMemoryError) {
                this.theArmies = null;
                this.iHaveDied = true;
                this.stat = "Sorry, the Java Virtual Machine has run out of memory";
            }
        }
        this.repaint();
    }
    
    private void setPieces() {
        final Shapes shapes = new Shapes(this.xpieces, this.ypieces);
        final Army[] array = new Army[this.xpieces * this.ypieces];
        this.theArmies = new Vector();
        this.ps = Global.pieceSize;
        this.psplus = this.ps + Shapes.deep * 2;
        Global.psplus = this.psplus;
        Global.deep = Shapes.deep;
        this.xmax = this.size().width - this.psplus;
        this.ymax = this.size().height - this.psplus;
        final int n = Global.pieceSize * this.xpieces;
        final int n2 = Global.pieceSize * this.ypieces;
        this.finish = new Rectangle((this.size().width - n) / 2, (this.size().height - n2) / 2, n, n2);
        this.xSpan = this.ps * this.xpieces + 2 * Shapes.deep;
        this.ySpan = this.ps * this.ypieces + 2 * Shapes.deep;
        this.pixels = new int[this.xSpan * this.ySpan];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.theImage, -Shapes.deep, -Shapes.deep, this.xSpan, this.ySpan, this.pixels, 0, this.xSpan);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < this.xSpan * this.ySpan; ++i) {
            this.pixels[i] &= 0xFFFFFF;
        }
        for (int j = 0; j < this.xpieces; ++j) {
            for (int k = 0; k < this.ypieces; ++k) {
                final int n3 = j * this.ypieces + k;
                (array[n3] = new Army(j, k, shapes.getOrMask(j, k), this)).setLocation(Global.randomInt(this.xmax), Global.randomInt(this.ymax), Global.randomInt(4), this);
                this.theArmies.addElement(array[n3]);
            }
        }
        for (int l = 0; l < this.xpieces; ++l) {
            for (int n4 = 0; n4 < this.ypieces; ++n4) {
                final int n5 = l * this.ypieces + n4;
                if (l > 0) {
                    array[n5].addCanLinkTo(array[n5 - this.ypieces]);
                }
                if (l < this.xpieces - 1) {
                    array[n5].addCanLinkTo(array[n5 + this.ypieces]);
                }
                if (n4 > 0) {
                    array[n5].addCanLinkTo(array[n5 - 1]);
                }
                if (n4 < this.ypieces - 1) {
                    array[n5].addCanLinkTo(array[n5 + 1]);
                }
            }
        }
        this.activeArmy = null;
        this.allDone = true;
    }
    
    public int[] getPixels(final int n, final int n2, final int n3, final int n4) {
        final int n5 = this.psplus + this.ps * (n3 - n);
        final int n6 = this.psplus + this.ps * (n4 - n2);
        final int n7 = n * this.ps + n2 * this.ps * this.xSpan;
        final int[] array = new int[n5 * n6];
        for (int i = 0; i < n6; ++i) {
            System.arraycopy(this.pixels, n7 + i * this.xSpan, array, i * n5, n5);
        }
        return array;
    }
    
    private void getParameters() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image image = this.getImage(this.getDocumentBase(), this.getParameter("image"));
        mediaTracker.addImage(image, 0);
        final String parameter = this.getParameter("click");
        if (parameter != null) {
            this.clickSound = this.getAudioClip(this.getDocumentBase(), parameter);
        }
        final String parameter2 = this.getParameter("complete");
        if (parameter2 != null) {
            this.completeSound = this.getAudioClip(this.getDocumentBase(), parameter2);
        }
        Global.setPieceSize(Integer.parseInt(this.getParameter("pieceSize")));
        this.xpieces = Integer.parseInt(this.getParameter("xpieces"));
        this.ypieces = Integer.parseInt(this.getParameter("ypieces"));
        final String parameter3 = this.getParameter("backColor");
        this.backColor = new Color(170, 230, 170);
        if (parameter3 != null) {
            try {
                final int int1 = Integer.parseInt(parameter3.substring(0, 3));
                final int int2 = Integer.parseInt(parameter3.substring(3, 6));
                final int int3 = Integer.parseInt(parameter3.substring(6, 9));
                if (int1 >= 0 && int1 < 256 && int2 >= 0 && int2 < 256 && int3 >= 0 && int3 < 256) {
                    this.backColor = new Color(int1, int2, int3);
                }
            }
            catch (NumberFormatException ex) {}
        }
        final String parameter4 = this.getParameter("foreColor");
        this.foreColor = Color.black;
        if (parameter4 != null) {
            try {
                final int int4 = Integer.parseInt(parameter4.substring(0, 3));
                final int int5 = Integer.parseInt(parameter4.substring(3, 6));
                final int int6 = Integer.parseInt(parameter4.substring(6, 9));
                if (int4 >= 0 && int4 < 256 && int5 >= 0 && int5 < 256 && int6 >= 0 && int6 < 256) {
                    this.foreColor = new Color(int4, int5, int6);
                }
            }
            catch (NumberFormatException ex2) {}
        }
        final String parameter5 = this.getParameter("boxColor");
        this.boxColor = Color.white;
        if (parameter5 != null) {
            try {
                final int int7 = Integer.parseInt(parameter5.substring(0, 3));
                final int int8 = Integer.parseInt(parameter5.substring(3, 6));
                final int int9 = Integer.parseInt(parameter5.substring(6, 9));
                if (int7 >= 0 && int7 < 256 && int8 >= 0 && int8 < 256 && int9 >= 0 && int9 < 256) {
                    this.boxColor = new Color(int7, int8, int9);
                }
            }
            catch (NumberFormatException ex3) {}
        }
        final String parameter6 = this.getParameter("completeButton");
        if (parameter6 != null && parameter6.equalsIgnoreCase("no")) {
            this.haveCompleteButton = false;
        }
        if (parameter6 != null) {
            this.completeButton = new Button(parameter6);
        }
        final String parameter7 = this.getParameter("restartButton");
        if (parameter7 != null && parameter7.equalsIgnoreCase("no")) {
            this.haveRestartButton = false;
        }
        if (parameter7 != null) {
            this.restartButton = new Button(parameter7);
        }
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex4) {}
        final String parameter8 = this.getParameter("rescale");
        if (parameter8 != null && parameter8.equalsIgnoreCase("yes")) {
            try {
                this.theImage = image.getScaledInstance(this.xpieces * this.ps, this.ypieces * this.ps, 4);
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.theImage = image;
            }
        }
        else {
            this.theImage = image;
        }
        mediaTracker.addImage(this.theImage, 1);
        try {
            mediaTracker.waitForID(1);
        }
        catch (InterruptedException ex5) {}
        image.flush();
    }
    
    public void paintOffscreen() {
        this.paintOffscreen(0, 0, this.size().width, this.size().height);
    }
    
    public void paintOffscreen(final int n, final int n2, final int n3, final int n4) {
        if (this.offScreenImage == null) {
            this.setOffScreenImage();
        }
        final Graphics graphics = this.backdropImage.getGraphics();
        graphics.clipRect(n, n2, n3, n4);
        graphics.setColor(this.getBackground());
        graphics.fillRect(n, n2, n3, n4);
        if (this.allDone) {
            graphics.setColor(this.boxColor);
            graphics.draw3DRect(this.finish.x - 1, this.finish.y - 1, this.finish.width + 1, this.finish.height + 1, true);
            final Rectangle rectangle = new Rectangle(n, n2, n3, n4);
            for (int i = 0; i < this.theArmies.size(); ++i) {
                final Army army = this.theArmies.elementAt(i);
                if (army != this.activeArmy && rectangle.intersects(army.getBounding())) {
                    army.drawMe(graphics, this);
                }
            }
        }
        this.repaint();
    }
    
    private void setOffScreenImage() {
        this.offScreenImage = this.createImage(this.size().width, this.size().height);
        this.backdropImage = this.createImage(this.size().width, this.size().height);
    }
    
    private void paintOffscreen(final Rectangle rectangle) {
        this.paintOffscreen(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    private void repaint(final Rectangle rectangle) {
        this.repaint(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.allDone) {
            if (this.backdropImage == null) {
                this.paintOffscreen(0, 0, this.size().width, this.size().height);
            }
            final Graphics graphics2 = this.offScreenImage.getGraphics();
            graphics2.drawImage(this.backdropImage, 0, 0, this);
            if (this.activeArmy != null) {
                this.activeArmy.drawMe(graphics2, this);
            }
            graphics.drawImage(this.offScreenImage, 0, 0, this);
            this.notRepainting = true;
            return;
        }
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.size().width, this.size().height);
        graphics.setColor(Color.black);
        graphics.drawString(this.stat, 50, 50);
    }
    
    private void setStat(final String stat) {
        this.showStatus(this.stat = stat);
        this.repaint();
    }
    
    public boolean action(final Event event, final Object o) {
        if (this.iHaveDied) {
            return super.action(event, o);
        }
        if (event.target == this.completeButton) {
            this.theArmies = new Vector();
            final int deep = Global.deep;
            final int width = this.ps * this.xpieces + deep * 2;
            final int height = this.ps * this.ypieces + deep * 2;
            final int[] array = new int[width * height];
            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    if (i >= deep && i < this.ps * this.ypieces + deep && j >= deep && j <= this.ps * this.xpieces + deep) {
                        array[i * width + j] = -16777216;
                    }
                    else {
                        array[i * width + j] = 0;
                    }
                }
            }
            final OrMask orMask = new OrMask(array, 0, 0, deep, deep, width - deep, height - deep);
            orMask.setWidth(width);
            orMask.setHeight(height);
            final Army activeArmy = new Army(0, 0, this.xpieces - 1, this.ypieces - 1, orMask, this);
            activeArmy.setLocation(this.finish.x - deep, this.finish.y - deep, 0, this);
            this.activeArmy = activeArmy;
            this.theArmies.addElement(activeArmy);
            this.offScreenImage = null;
            this.backdropImage = null;
            this.repaint();
        }
        else if (event.target == this.restartButton) {
            this.restartFlag = true;
            this.start();
        }
        return super.action(event, o);
    }
    
    public boolean mouseDown(final Event event, final int lastX, final int lastY) {
        if (this.iHaveDied) {
            return super.mouseDown(event, lastX, lastY);
        }
        this.lastX = lastX;
        this.lastY = lastY;
        for (int i = this.theArmies.size() - 1; i >= 0; --i) {
            final Army activeArmy = this.theArmies.elementAt(i);
            if (activeArmy.isThisMe(lastX, lastY)) {
                if (activeArmy != this.activeArmy) {
                    this.activeArmy = activeArmy;
                    this.theArmies.removeElement(activeArmy);
                    this.theArmies.addElement(activeArmy);
                    if (event.shiftDown() || event.controlDown() || event.metaDown()) {
                        this.paintOffscreen(activeArmy.rotateMe(this));
                    }
                    else {
                        this.paintOffscreen(activeArmy.getBounding());
                    }
                }
                else if (event.clickCount == 2 || event.shiftDown() || event.controlDown() || event.metaDown()) {
                    this.repaint(activeArmy.rotateMe(this));
                }
                i = -1;
            }
            else if (activeArmy == this.activeArmy) {
                this.activeArmy = null;
                this.paintOffscreen(activeArmy.getBounding());
            }
        }
        return super.mouseDown(event, lastX, lastY);
    }
    
    public boolean mouseDrag(final Event event, final int lastX, final int lastY) {
        if (this.iHaveDied) {
            return super.mouseDrag(event, lastX, lastY);
        }
        if (this.notRepainting) {
            final int n = lastX - this.lastX;
            final int n2 = lastY - this.lastY;
            this.lastX = lastX;
            this.lastY = lastY;
            this.notRepainting = false;
            if (this.activeArmy != null) {
                this.repaint(this.activeArmy.moveMeBy(n, n2, this.xmax, this.ymax));
            }
        }
        return super.mouseDrag(event, lastX, lastY);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.iHaveDied) {
            return super.mouseUp(event, n, n2);
        }
        if (this.activeArmy != null) {
            for (int i = this.theArmies.size() - 1; i >= 0; --i) {
                final Army army = this.theArmies.elementAt(i);
                if (this.activeArmy.doIFitWith(army)) {
                    if (this.clickSound != null) {
                        this.clickSound.play();
                    }
                    try {
                        final Army activeArmy = new Army(this.activeArmy, army, this);
                        this.theArmies.removeElement(army);
                        this.theArmies.removeElement(this.activeArmy);
                        this.theArmies.addElement(activeArmy);
                        this.activeArmy = activeArmy;
                        this.paintOffscreen(activeArmy.getBounding());
                    }
                    catch (OutOfMemoryError outOfMemoryError) {
                        this.setStat("Run out of memory...");
                    }
                    if (this.theArmies.size() == 1 && this.completeSound != null) {
                        this.completeSound.play();
                    }
                }
            }
        }
        return super.mouseUp(event, n, n2);
    }
    
    public Puzzle() {
        this.completeButton = new Button("Complete");
        this.restartButton = new Button("Restart");
        this.haveCompleteButton = true;
        this.haveRestartButton = true;
        this.restartFlag = true;
        this.iHaveDied = false;
        this.stat = "";
        this.allDone = false;
        this.notRepainting = true;
    }
}
