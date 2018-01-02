import java.awt.event.FocusEvent;
import java.util.Hashtable;
import java.awt.image.PixelGrabber;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.Vector;
import java.awt.event.FocusListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageButton extends Applet implements Runnable, MouseListener, MouseMotionListener, FocusListener
{
    private static final int NUM_TYPES = 3;
    private static final int OFF = 0;
    private static final int ON = 1;
    private static final int PUSHED = 2;
    private Thread buttonThread;
    private Vector actionListeners;
    private Vector buttonListeners;
    private String command;
    private int x;
    private int y;
    private int width;
    private int height;
    private int mouseX;
    private int mouseY;
    private int xOffset;
    private int yOffset;
    private int prevXOffset;
    private int prevYOffset;
    private int currentType;
    private int prevType;
    private int delay;
    private int hMargin;
    private int vMargin;
    private int color;
    private int period;
    private int iter;
    private int bgWidth;
    private int bgHeight;
    private double speed;
    private double colorAmount;
    private double intensity;
    private double friction;
    private Color bgColor;
    private int[] backPixels;
    private int[] bufferPixels;
    private int[] bgPixels;
    private int[][] facePixels;
    private Rectangle faceBounds;
    private Rectangle appRect;
    private Rectangle currentRect;
    private Rectangle[] faceRects;
    private Dimension[] faceSizes;
    private Point[] edgeOffsets;
    private boolean entered;
    private boolean pressed;
    private boolean parentCoord;
    
    public void setFriction(final double n) {
        this.friction = this.makeUnity(n);
    }
    
    public synchronized void stop() {
        this.buttonThread = null;
        this.colorAmount = 0.0;
    }
    
    public void removeButtonListener(final ButtonListener buttonListener) {
        this.buttonListeners.removeElement(buttonListener);
    }
    
    public void setMargins(final int n, final int n2) {
        this.hMargin = Math.abs(n);
        this.vMargin = Math.abs(n2);
        this.setSize(this.faceBounds.width + 2 * this.hMargin, this.faceBounds.height + 2 * this.vMargin);
    }
    
    public void setActionCommand(final String s) {
        this.command = new String(s);
    }
    
    public String getActionCommand() {
        return this.command;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.pressed = false;
        this.updateButton();
        if (this.prevType == 2) {
            this.processButtonEvent(mouseEvent);
            this.processButtonEvent(new MouseEvent(this, 500, System.currentTimeMillis(), 0, mouseEvent.getX(), mouseEvent.getY(), 0, false));
            this.processActionEvent(new ActionEvent(this, 1001, this.command));
            this.start();
        }
    }
    
    public synchronized void update(final Graphics graphics) {
        final Rectangle currentRect = this.currentRect;
        if (this.faceRects[this.currentType] != null) {
            this.currentRect = new Rectangle(this.faceRects[this.currentType].x - this.edgeOffsets[this.currentType].x + this.xOffset, this.faceRects[this.currentType].y - this.edgeOffsets[this.currentType].y + this.yOffset, this.faceSizes[this.currentType].width, this.faceSizes[this.currentType].height);
            final Rectangle intersection = currentRect.union(this.currentRect).intersection(this.appRect);
            final int n = intersection.width * intersection.height;
            if (this.backPixels != null) {
                this.copyPixels(this.backPixels, this.width, this.height, intersection.x, intersection.y, intersection.width, intersection.height, this.bufferPixels, intersection.width, intersection.height, 0, 0);
            }
            else {
                final int rgb = this.bgColor.getRGB();
                for (int i = 0; i < n; ++i) {
                    this.bufferPixels[i] = rgb;
                }
            }
            this.overlayPixels(this.facePixels[this.currentType], this.currentRect.width, this.currentRect.height, 0, 0, this.faceSizes[this.currentType].width, this.faceSizes[this.currentType].height, this.bufferPixels, intersection.width, intersection.height, this.currentRect.x - intersection.x, this.currentRect.y - intersection.y, this.color, this.colorAmount);
            final Image image = this.createImage(new MemoryImageSource(intersection.width, intersection.height, this.bufferPixels, 0, intersection.width));
            graphics.drawImage(image, intersection.x, intersection.y, null);
            image.flush();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
        this.entered = true;
        this.updateButton();
        if (this.currentType == 1) {
            this.start();
            this.processButtonEvent(mouseEvent);
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.entered = false;
        this.stop();
        this.handleMotion(mouseEvent);
        this.currentType = 0;
    }
    
    private synchronized void updateBackground() {
        if (this.bgPixels != null) {
            int x;
            int y;
            if (!this.parentCoord) {
                x = 0;
                y = 0;
            }
            else {
                x = this.x;
                y = this.y;
            }
            this.backPixels = new int[this.width * this.height];
            for (int i = -(y % this.bgHeight); i < this.height; i += this.bgHeight) {
                for (int j = -(x % this.bgWidth); j < this.width; j += this.bgWidth) {
                    this.copyPixels(this.bgPixels, this.bgWidth, this.bgHeight, 0, 0, this.bgWidth, this.bgHeight, this.backPixels, this.width, this.height, j, i);
                }
            }
        }
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.paint(graphics);
            graphics.dispose();
        }
    }
    
    protected void processActionEvent(final ActionEvent actionEvent) {
        for (int size = this.actionListeners.size(), i = 0; i < size; ++i) {
            ((ActionListener)this.actionListeners.elementAt(i)).actionPerformed(actionEvent);
        }
    }
    
    private int[] getPixels(final Image image, final int n, final int n2) {
        int[] array = new int[n * n2];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n, n2, array, 0, n);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            array = null;
        }
        return array;
    }
    
    private void copyPixels(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int[] array2, final int n7, final int n8, final int n9, final int n10) {
        final Rectangle effectiveRect = this.effectiveRect(n, n2, n3, n4, n7, n8, n9, n10, n5, n6);
        final int n11 = n3 - n9;
        final int n12 = n4 - n10;
        int i = effectiveRect.y * n7;
        int n13 = (effectiveRect.y + n12) * n;
        final int n14 = effectiveRect.x + effectiveRect.width;
        while (i < (effectiveRect.y + effectiveRect.height) * n7) {
            for (int j = effectiveRect.x; j < n14; ++j) {
                array2[i + j] = array[n13 + j + n11];
            }
            i += n7;
            n13 += n;
        }
    }
    
    private void overlayPixels(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int[] array2, final int n7, final int n8, final int n9, final int n10, final int n11, final double n12) {
        final Rectangle effectiveRect = this.effectiveRect(n, n2, n3, n4, n7, n8, n9, n10, n5, n6);
        final int n13 = n9 - n3;
        final int n14 = n10 - n4;
        int i = effectiveRect.y * n7;
        int n15 = (effectiveRect.y - n14) * n;
        final int n16 = effectiveRect.x + effectiveRect.width;
        final int n17 = (effectiveRect.y + effectiveRect.height) * n7;
        final int n18 = (int)(255.0 * this.makeUnity(n12));
        final int[] array3 = new int[4];
        final int[] array4 = new int[4];
        final int[] array5 = new int[4];
        this.splitPixel(n11, array5);
        while (i < n17) {
            for (int j = effectiveRect.x; j < n16; ++j) {
                final int n19 = i + j;
                final int n20 = array2[n19];
                this.splitPixel(array[n15 + j - n13], array3);
                if (array3[0] != 0) {
                    if (n18 > 0) {
                        final int n21 = array3[1] + array3[2] + array3[3];
                        final int n22 = n18 * ((n21 < 383) ? n21 : (765 - n21)) / 383;
                        final int n23 = 255 - n22;
                        array3[1] = (n23 * array3[1] + n22 * array5[1]) / 255;
                        array3[2] = (n23 * array3[2] + n22 * array5[2]) / 255;
                        array3[3] = (n23 * array3[3] + n22 * array5[3]) / 255;
                    }
                    this.splitPixel(n20, array4);
                    final int n24 = 255 - array3[0];
                    array4[1] = (n24 * array4[1] + array3[0] * array3[1]) / 255;
                    array4[2] = (n24 * array4[2] + array3[0] * array3[2]) / 255;
                    array4[3] = (n24 * array4[3] + array3[0] * array3[3]) / 255;
                    array2[n19] = (0xFF000000 | array4[1] << 16 | array4[2] << 8 | array4[3]);
                }
            }
            i += n7;
            n15 += n;
        }
    }
    
    public void start() {
        if (this.buttonThread == null) {
            (this.buttonThread = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Applet: ImageButton\nVersion: 1.2 for Java 1.1 Platform\nAuthor: Uldarico Muico, um@mail.com\nDate: 29 July 1999";
    }
    
    public void setSize(final int width, final int height) {
        final int width2 = this.width;
        final int height2 = this.height;
        super.setSize(this.width = width, this.height = height);
        final Dimension size = this.getSize();
        final int n = (size.width - width2) / 2;
        final int n2 = (size.height - height2) / 2;
        this.appRect.setSize(size.width, size.height);
        this.bufferPixels = new int[size.width * size.height];
        int n3 = 0;
        do {
            if (this.faceRects[n3] != null) {
                this.faceRects[n3].translate(n, n2);
            }
        } while (++n3 < 3);
        this.faceBounds.translate(n, n2);
        this.currentRect.translate(n, n2);
        this.updateBackground();
    }
    
    public synchronized void setFaces(final ButtonImageSource[] array) {
        this.setFaces(array, null);
    }
    
    public synchronized void setFaces(final ButtonImageSource[] array, final Point[] array2) {
        final Hashtable<Object, Dimension> hashtable = new Hashtable<Object, Dimension>(3);
        Point point = null;
        this.faceBounds = new Rectangle();
        int n = 0;
        do {
            this.facePixels[n] = array[n].getSource(hashtable);
            this.faceSizes[n] = hashtable.get("SIZE");
            this.faceRects[n] = (Rectangle)hashtable.get("ACTIVE_BOUNDS");
            this.edgeOffsets[n] = new Point(this.faceRects[n].x, this.faceRects[n].y);
            this.faceRects[n].setLocation(-this.faceRects[n].width / 2, -this.faceRects[n].height / 2);
            final Point point2 = (Point)hashtable.get("FACE_CENTER");
            if (n == 0) {
                point = point2;
            }
            else if (point != null) {
                this.faceRects[n].translate(this.faceRects[0].x - this.edgeOffsets[0].x + point.x - (this.faceRects[n].x - this.edgeOffsets[n].x + point2.x), this.faceRects[0].y - this.edgeOffsets[0].y + point.y - (this.faceRects[n].y - this.edgeOffsets[n].y + point2.y));
            }
            if (array2 != null && array2[n] != null) {
                this.faceRects[n].translate(array2[n].x, array2[n].y);
            }
            this.faceBounds.add(new Rectangle(this.faceRects[n].x - this.edgeOffsets[n].x, this.faceRects[n].y - this.edgeOffsets[n].y, this.faceSizes[n].width, this.faceSizes[n].height));
        } while (++n < 3);
        final int n2 = this.width / 2 - this.faceBounds.width / 2 - this.faceBounds.x;
        final int n3 = this.height / 2 - this.faceBounds.height / 2 - this.faceBounds.y;
        int n4 = 0;
        do {
            this.faceRects[n4].translate(n2, n3);
        } while (++n4 < 3);
        this.faceBounds.translate(n2, n3);
        this.currentRect.setBounds(this.faceRects[0].x - this.edgeOffsets[0].x, this.faceRects[0].y - this.edgeOffsets[0].y, this.faceSizes[0].width, this.faceSizes[0].height);
    }
    
    private void restrainMove(final int n, final int n2) {
        final Rectangle rectangle = new Rectangle(this.faceBounds);
        rectangle.translate(this.xOffset + n, this.yOffset + n2);
        final Rectangle intersection = rectangle.intersection(this.appRect);
        if (this.faceBounds.width < this.width) {
            this.prevXOffset = this.xOffset;
            this.xOffset = intersection.x - this.faceBounds.x;
            if (intersection.x + this.faceBounds.width >= this.width) {
                this.xOffset -= this.faceBounds.width - intersection.width;
            }
        }
        if (this.faceBounds.height < this.height) {
            this.prevYOffset = this.yOffset;
            this.yOffset = intersection.y - this.faceBounds.y;
            if (intersection.y + this.faceBounds.height >= this.height) {
                this.yOffset -= this.faceBounds.height - intersection.height;
            }
        }
    }
    
    private void updateButton() {
        this.prevType = this.currentType;
        if (this.entered && this.faceRects[this.currentType] != null && this.faceRects[this.currentType].contains(this.mouseX - this.xOffset, this.mouseY - this.yOffset)) {
            if ((this.facePixels[this.currentType][this.mouseX - (this.faceRects[this.currentType].x - this.edgeOffsets[this.currentType].x + this.xOffset) + (this.mouseY - (this.faceRects[this.currentType].y - this.edgeOffsets[this.currentType].y + this.yOffset)) * this.faceSizes[this.currentType].width] >> 24 & 0xFF) > 112) {
                this.currentType = (this.pressed ? 2 : 1);
            }
            else {
                this.currentType = 0;
            }
        }
        else {
            this.currentType = 0;
        }
        if (this.currentType != 1) {
            this.colorAmount = 0.0;
        }
        if (this.currentType == 2 && this.prevType != 2) {
            this.render();
        }
        else if (this.buttonThread != null || this.prevType != this.currentType || (this.currentType == 2 && (this.xOffset != this.prevXOffset || this.yOffset != this.prevYOffset))) {
            this.repaint();
        }
    }
    
    private Rectangle effectiveRect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        final Rectangle intersection = new Rectangle(n3, n4, n9, n10).intersection(new Rectangle(n, n2));
        intersection.translate(n7 - n3, n8 - n4);
        return intersection.intersection(new Rectangle(n5, n6));
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.pressed = true;
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
        this.updateButton();
        if (this.currentType == 2) {
            this.stop();
            this.processButtonEvent(mouseEvent);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.currentType == 2) {
            this.restrainMove(mouseEvent.getX() - this.mouseX, mouseEvent.getY() - this.mouseY);
        }
        this.handleMotion(mouseEvent);
    }
    
    public void setBackground(final int[] bgPixels, final int bgWidth, final int bgHeight, final boolean parentCoord) {
        this.bgPixels = bgPixels;
        if (this.bgPixels != null) {
            this.bgWidth = bgWidth;
            this.bgHeight = bgHeight;
        }
        this.parentCoord = parentCoord;
        this.updateBackground();
    }
    
    public ImageButton() {
        this.actionListeners = new Vector();
        this.buttonListeners = new Vector();
        this.delay = 50;
        this.hMargin = 10;
        this.vMargin = 10;
        this.speed = 0.1;
        this.friction = 0.4;
        this.bgColor = new Color(-8421505);
        this.facePixels = new int[3][];
        this.faceBounds = new Rectangle();
        this.appRect = new Rectangle();
        this.currentRect = new Rectangle();
        this.faceRects = new Rectangle[3];
        this.faceSizes = new Dimension[3];
        this.edgeOffsets = new Point[3];
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addFocusListener(this);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.prevType == 1) {
            this.restrainMove((int)(this.friction * (mouseEvent.getX() - this.mouseX)), (int)(this.friction * (mouseEvent.getY() - this.mouseY)));
        }
        this.handleMotion(mouseEvent);
    }
    
    public ImageButton(final ButtonImageSource[] array) {
        this(array, null);
    }
    
    public ImageButton(final ButtonImageSource[] array, final Point[] array2) {
        this();
        this.setFaces(array, array2);
        this.setMargins(this.hMargin, this.vMargin);
    }
    
    public void setBackground(final Color color) {
        this.bgColor = new Color(color.getRGB());
        this.backPixels = null;
        this.bgPixels = null;
        this.updateBackground();
    }
    
    public void paint(final Graphics graphics) {
        if (this.bgPixels != null) {
            if (this.parentCoord) {
                final int x = this.x;
                final int y = this.y;
            }
            final Image image = this.createImage(new MemoryImageSource(this.width, this.height, this.backPixels, 0, this.width));
            graphics.drawImage(image, 0, 0, null);
            image.flush();
        }
        else {
            graphics.setColor(this.bgColor);
            graphics.fillRect(0, 0, this.width, this.height);
        }
        this.update(graphics);
    }
    
    public void repaint(final Rectangle currentRect) {
        if (currentRect != null) {
            this.currentRect = currentRect;
        }
        this.repaint();
    }
    
    protected void processButtonEvent(final MouseEvent mouseEvent) {
        final int size = this.buttonListeners.size();
        switch (mouseEvent.getID()) {
            case 505: {
                for (int i = 0; i < size; ++i) {
                    ((ButtonListener)this.buttonListeners.elementAt(i)).buttonOff(mouseEvent);
                }
                break;
            }
            case 504: {
                for (int j = 0; j < size; ++j) {
                    ((ButtonListener)this.buttonListeners.elementAt(j)).buttonOn(mouseEvent);
                }
                break;
            }
            case 501: {
                for (int k = 0; k < size; ++k) {
                    ((ButtonListener)this.buttonListeners.elementAt(k)).buttonPushed(mouseEvent);
                }
                break;
            }
            case 502: {
                for (int l = 0; l < size; ++l) {
                    ((ButtonListener)this.buttonListeners.elementAt(l)).buttonPopped(mouseEvent);
                }
                break;
            }
            case 500: {
                for (int n = 0; n < size; ++n) {
                    ((ButtonListener)this.buttonListeners.elementAt(n)).buttonTriggered(mouseEvent);
                }
                break;
            }
        }
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.actionListeners.removeElement(actionListener);
    }
    
    public void addButtonListener(final ButtonListener buttonListener) {
        if (!this.buttonListeners.contains(buttonListener) && buttonListener != null) {
            this.buttonListeners.addElement(buttonListener);
        }
    }
    
    private double makeUnity(double n) {
        if (n < 0.0) {
            n = 0.0;
        }
        else if (n > 1.0) {
            n = 1.0;
        }
        return n;
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.render();
    }
    
    private void splitPixel(final int n, final int[] array) {
        array[0] = (n >> 24 & 0xFF);
        array[1] = (n >> 16 & 0xFF);
        array[2] = (n >> 8 & 0xFF);
        array[3] = (n & 0xFF);
    }
    
    private void render() {
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.update(graphics);
            graphics.dispose();
        }
    }
    
    public void addActionListener(final ActionListener actionListener) {
        if (!this.actionListeners.contains(actionListener) && actionListener != null) {
            this.actionListeners.addElement(actionListener);
        }
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.faceBounds.width, this.faceBounds.height);
    }
    
    private void handleMotion(final MouseEvent mouseEvent) {
        if (mouseEvent != null) {
            this.mouseX = mouseEvent.getX();
            this.mouseY = mouseEvent.getY();
        }
        this.updateButton();
        if (this.currentType != this.prevType) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.currentType == 0) {
                if (this.prevType == 2) {
                    this.processButtonEvent(new MouseEvent(this, 502, currentTimeMillis, 0, this.mouseX, this.mouseY, 0, false));
                }
                this.processButtonEvent(new MouseEvent(this, 505, currentTimeMillis, 0, this.mouseX, this.mouseY, 0, false));
            }
            else if (this.currentType == 2) {
                this.stop();
                if (this.prevType == 0) {
                    this.processButtonEvent(new MouseEvent(this, 504, currentTimeMillis, 0, this.mouseX, this.mouseY, 0, false));
                }
                this.processButtonEvent(new MouseEvent(this, 501, currentTimeMillis, 0, this.mouseX, this.mouseY, 0, false));
            }
            else if (this.prevType != 2) {
                this.processButtonEvent(new MouseEvent(this, 504, currentTimeMillis, 0, this.mouseX, this.mouseY, 0, false));
            }
        }
        if ((this.currentType == 1 && (this.prevXOffset != this.xOffset || this.prevYOffset != this.yOffset || this.period != 0)) || (this.currentType == 0 && (this.currentType != this.prevType || (this.speed != 0.0 && (this.xOffset != 0 || this.yOffset != 0))))) {
            this.start();
        }
    }
    
    public void run() {
        final int n = (int)((1.0 - this.speed) * 255.0);
        this.iter = 0;
        this.colorAmount = 0.0;
        while (this.buttonThread != null && ((this.currentType == 1 && this.period > 0) || (this.speed != 0.0 && (this.xOffset != 0 || this.yOffset != 0)))) {
            this.prevXOffset = this.xOffset;
            this.prevYOffset = this.yOffset;
            this.xOffset = n * this.xOffset / 255;
            this.yOffset = n * this.yOffset / 255;
            if (this.currentType == 1 && this.period > 0) {
                this.colorAmount = this.intensity * (0.5 - 0.5 * Math.cos(6.283185307179586 * this.iter / this.period));
                ++this.iter;
                if (this.iter > this.period) {
                    this.iter = 0;
                }
            }
            this.handleMotion(null);
            if (this.currentType == 2) {
                this.stop();
                this.render();
                return;
            }
            try {
                Thread.sleep(this.delay);
            }
            catch (Exception ex) {}
        }
        this.stop();
        this.repaint();
    }
    
    public void focusGained(final FocusEvent focusEvent) {
    }
    
    public void setDelay(final int n) {
        this.delay = Math.abs(n);
    }
    
    public void setSpeed(final double n) {
        this.speed = this.makeUnity(n);
    }
    
    public void setBlink(final Color color, final int period, final double n) {
        if (color != null) {
            this.color = color.getRGB();
        }
        if (period >= 0) {
            this.period = period;
        }
        this.intensity = this.makeUnity(n);
        if (this.intensity == 0.0) {
            this.period = 0;
        }
    }
    
    public void setLocation(final int n, final int n2) {
        super.setLocation(n, n2);
        this.x = this.getLocation().x;
        this.y = this.getLocation().y;
        if (this.parentCoord && this.bgPixels != null) {
            this.updateBackground();
        }
    }
}
