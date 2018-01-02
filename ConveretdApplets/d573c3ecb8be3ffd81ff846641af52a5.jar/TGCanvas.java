import java.awt.image.PixelGrabber;
import java.awt.Shape;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.event.MouseMotionListener;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class TGCanvas extends Canvas implements ImageObserver, KeyListener, MouseListener
{
    public static final int MAX_TURTLES = 12;
    private static final Color INITIAL_BACKGROUND;
    private static final Color INITIAL_FOREGROUND;
    private static final int GI_HEIGHT = 1200;
    private static final int GI_WIDTH = 1600;
    private static final int INITIAL_FONT_SIZE = 14;
    private static final int INITIAL_FONT_STYLE = 0;
    private static final int INITIAL_PEN_SIZE = 2;
    private static final int MINIMUM_HEIGHT = 20;
    private static final String INITIAL_FONT_NAME = "Courier";
    private static final int PAINT_REFRESH = 0;
    private static final int PAINT_DRAW_GRAPHICS = 1;
    private static final int PAINT_ERASE_TURTLES = 2;
    private static final int PAINT_DRAW_TURTLES = 3;
    private boolean refreshNeeded;
    private int canvasHeight;
    private int canvasWidth;
    private int mouseX;
    private int mouseY;
    private int paintState;
    private int xCenter;
    private int yCenter;
    private Graphics giGraphics;
    private Image graphicsImage;
    private Rectangle[] turtleClipRect;
    private Turtle[] turtles;
    private Vector graphicsOps;
    private Vector keyHandlers;
    private Vector mouseHandlers;
    private Vector refreshList;
    
    public TGCanvas() {
        this(700, 400);
    }
    
    public TGCanvas(final int canvasWidth, final int canvasHeight) {
        super.setSize(canvasWidth, canvasHeight);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.xCenter = this.canvasWidth / 2;
        this.yCenter = this.canvasHeight / 2;
        this.setFont(new Font("Courier", 0, 14));
        this.graphicsOps = new Vector(200, 100);
        this.keyHandlers = new Vector(5, 5);
        this.mouseHandlers = new Vector(5, 5);
        this.refreshList = null;
        this.turtleClipRect = new Rectangle[12];
        this.turtles = new Turtle[12];
        this.paintState = 1;
        this.refreshNeeded = false;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        super.repaint(0, 0, 1, 1);
        return (n & 0xA0) == 0x0;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final int size = this.keyHandlers.size();
        if (size == 0) {
            return;
        }
        final int keyCode = keyEvent.getKeyCode();
        int n = 0;
        switch (keyCode) {
            case 40: {
                n = 128;
                break;
            }
            case 10: {
                n = 10;
                break;
            }
            case 37: {
                n = 129;
                break;
            }
            case 39: {
                n = 130;
                break;
            }
            case 32: {
                n = 32;
                break;
            }
            case 38: {
                n = 131;
                break;
            }
        }
        if (n == 0) {
            return;
        }
        for (int i = 0; i < size; ++i) {
            ((TGKeyHandler)this.keyHandlers.elementAt(i)).keyPressed(n);
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final int size = this.keyHandlers.size();
        if (size == 0) {
            return;
        }
        final char keyChar = keyEvent.getKeyChar();
        for (int i = 0; i < size; ++i) {
            ((TGKeyHandler)this.keyHandlers.elementAt(i)).keyPressed(keyChar);
        }
    }
    
    private void fwdMouseEvent(final int n, final MouseEvent mouseEvent) {
        final boolean popupTrigger = mouseEvent.isPopupTrigger();
        final long when = mouseEvent.getWhen();
        final int clickCount = mouseEvent.getClickCount();
        final int modifiers = mouseEvent.getModifiers();
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final Rectangle bounds = this.getBounds();
        int n2 = x + bounds.x;
        int n3 = y + bounds.y;
        for (Container container = this.getParent(); container != null; container = container.getParent()) {
            final Class<?>[] interfaces = container.getClass().getInterfaces();
            int i = 0;
            while (i < interfaces.length) {
                if (interfaces[i].getName().equals("java.awt.event.MouseListener")) {
                    final MouseEvent mouseEvent2 = new MouseEvent(container, n, when, modifiers, n2, n3, clickCount, popupTrigger);
                    switch (n) {
                        case 501: {
                            ((MouseListener)container).mousePressed(mouseEvent2);
                            return;
                        }
                        case 502: {
                            ((MouseListener)container).mouseReleased(mouseEvent2);
                            return;
                        }
                        case 500: {
                            ((MouseListener)container).mouseClicked(mouseEvent2);
                            return;
                        }
                        case 504: {
                            ((MouseListener)container).mouseEntered(mouseEvent2);
                            return;
                        }
                        case 505: {
                            ((MouseListener)container).mouseExited(mouseEvent2);
                            return;
                        }
                        case 503: {
                            ((MouseMotionListener)container).mouseMoved(mouseEvent2);
                            return;
                        }
                        case 506: {
                            ((MouseMotionListener)container).mouseDragged(mouseEvent2);
                            return;
                        }
                        default: {
                            System.err.println("TGCanvas.fwdMouseEvent: bad id");
                            return;
                        }
                    }
                }
                else {
                    ++i;
                }
            }
            final Rectangle bounds2 = container.getBounds();
            n2 += bounds2.x;
            n3 += bounds2.y;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.fwdMouseEvent(500, mouseEvent);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.fwdMouseEvent(504, mouseEvent);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.fwdMouseEvent(505, mouseEvent);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.fwdMouseEvent(501, mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (mouseEvent.getModifiers() == 16) {
            this.mouseX = mouseEvent.getX();
            this.mouseY = mouseEvent.getY();
            for (int size = this.mouseHandlers.size(), i = 0; i < size; ++i) {
                ((TGMouseHandler)this.mouseHandlers.elementAt(i)).mouseClick();
            }
        }
        else {
            this.fwdMouseEvent(502, mouseEvent);
        }
    }
    
    private void addGraphObj(final Object o) {
        synchronized (this.graphicsOps) {
            this.graphicsOps.addElement(o);
        }
    }
    
    private void clearGraphicsImage() {
        if (this.giGraphics != null) {
            this.giGraphics.setClip(0, 0, 1599, 1199);
            this.giGraphics.setColor(TGCanvas.INITIAL_BACKGROUND);
            this.giGraphics.fillRect(0, 0, 1599, 1199);
        }
    }
    
    private void doRefresh() {
    }
    
    private void initGraphicsImage() {
        this.graphicsImage = this.createImage(1600, 1200);
        this.giGraphics = this.graphicsImage.getGraphics();
        this.clearGraphicsImage();
        this.giGraphics.setColor(TGCanvas.INITIAL_FOREGROUND);
    }
    
    private Rectangle renderGraphics() {
        int x = 1600;
        int n = -1;
        int y = 1200;
        int n2 = -1;
        if (this.graphicsImage == null) {
            this.initGraphicsImage();
            x = 0;
            n = 1599;
            y = 0;
            n2 = 1199;
        }
        synchronized (this.graphicsOps) {
            for (int i = this.graphicsOps.size(); i > 0; --i) {
                final TGGraphicsOp tgGraphicsOp = this.graphicsOps.firstElement();
                final Rectangle doIt = tgGraphicsOp.doIt(this.graphicsImage);
                if (doIt != null) {
                    if (doIt.x < x) {
                        x = doIt.x;
                    }
                    if (doIt.y < y) {
                        y = doIt.y;
                    }
                    final int n3 = doIt.x + doIt.width - 1;
                    if (n3 > n) {
                        n = n3;
                    }
                    final int n4 = doIt.y + doIt.height - 1;
                    if (n4 > n2) {
                        n2 = n4;
                    }
                }
                if (this.refreshList != null) {
                    this.refreshList.addElement(tgGraphicsOp);
                }
                this.graphicsOps.removeElementAt(0);
            }
        }
        int canvasWidth = n + 1 - x;
        int n5 = x - (1600 - this.canvasWidth) / 2;
        if (n5 < 0) {
            canvasWidth += n5;
            n5 = 0;
        }
        if (canvasWidth > this.canvasWidth) {
            canvasWidth = this.canvasWidth;
        }
        int canvasHeight = n2 + 1 - y;
        int n6 = y - (1200 - this.canvasHeight) / 2;
        if (n6 < 0) {
            canvasHeight += n6;
            n6 = 0;
        }
        if (canvasHeight > this.canvasHeight) {
            canvasHeight = this.canvasHeight;
        }
        if (canvasHeight > 0 && canvasWidth > 0) {
            return new Rectangle(n5, n6, canvasWidth, canvasHeight);
        }
        return null;
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.canvasWidth, 20);
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle clipBounds = graphics.getClipBounds();
        if (clipBounds.x != 0 || clipBounds.y != 0 || clipBounds.height != 1 || clipBounds.width != 1) {
            this.paintState = 0;
        }
        final int n = (1600 - this.canvasWidth) / 2;
        final int n2 = (1200 - this.canvasHeight) / 2;
        switch (this.paintState) {
            case 0: {
                graphics.setClip(0, 0, this.canvasWidth, this.canvasHeight);
                if (this.graphicsImage == null) {
                    graphics.setColor(TGCanvas.INITIAL_BACKGROUND);
                    graphics.fillRect(0, 0, this.canvasWidth, this.canvasHeight);
                }
                else if (!graphics.drawImage(this.graphicsImage, -n, -n2, this)) {
                    return;
                }
                this.paintState = 1;
            }
            case 1: {
                final Rectangle renderGraphics = this.renderGraphics();
                if (renderGraphics != null) {
                    graphics.setClip(renderGraphics);
                    if (!graphics.drawImage(this.graphicsImage, -n, -n2, this)) {
                        return;
                    }
                }
                this.paintState = 2;
            }
            case 2: {
                for (int i = 0; i < this.turtleClipRect.length; ++i) {
                    final Rectangle clip;
                    if ((clip = this.turtleClipRect[i]) != null) {
                        graphics.setClip(clip);
                        if (!graphics.drawImage(this.graphicsImage, -n, -n2, this)) {
                            return;
                        }
                        this.turtleClipRect[i] = null;
                    }
                }
                this.paintState = 3;
            }
            case 3: {
                for (int j = 0; j < this.turtles.length; ++j) {
                    final Turtle turtle = this.turtles[j];
                    if (turtle != null) {
                        final int n3 = (int)Math.rint(turtle.xcor() + this.xCenter);
                        final int n4 = (int)Math.rint(this.yCenter - turtle.ycor());
                        final int imageSideSize = turtle.getImageSideSize();
                        final int n5 = n3 - imageSideSize / 2;
                        final int n6 = n4 - imageSideSize / 2;
                        graphics.setClip(n5, n6, imageSideSize, imageSideSize);
                        if (!graphics.drawImage(turtle.getImage(), n5, n6, this)) {
                            return;
                        }
                        this.turtleClipRect[j] = new Rectangle(n5, n6, imageSideSize, imageSideSize);
                    }
                }
                this.paintState = 1;
                break;
            }
        }
    }
    
    public void setSize(final int canvasWidth, final int canvasHeight) {
        super.setSize(canvasWidth, canvasHeight);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.xCenter = canvasWidth / 2;
        this.yCenter = canvasHeight / 2;
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void addKeyHandler(final TGKeyHandler tgKeyHandler) {
        if (!this.keyHandlers.contains(tgKeyHandler)) {
            this.keyHandlers.addElement(tgKeyHandler);
        }
    }
    
    public void addMouseHandler(final TGMouseHandler tgMouseHandler) {
        if (!this.mouseHandlers.contains(tgMouseHandler)) {
            this.mouseHandlers.addElement(tgMouseHandler);
        }
    }
    
    public void addTurtle(final Turtle turtle) {
        int n = -1;
        for (int i = this.turtles.length - 1; i >= 0; --i) {
            if (this.turtles[i] != null) {
                if (this.turtles[i] == turtle) {
                    return;
                }
            }
            else {
                n = i;
            }
        }
        if (n < 0) {
            System.err.println("TGCanvas.addTurtle: no room!");
            return;
        }
        this.turtles[n] = turtle;
    }
    
    public int canvasHeight() {
        return this.canvasHeight;
    }
    
    public int canvasWidth() {
        return this.canvasWidth;
    }
    
    public void clean() {
        synchronized (this.graphicsOps) {
            this.graphicsOps.removeAllElements();
            if (this.refreshList != null) {
                this.refreshList.removeAllElements();
            }
        }
        this.clearGraphicsImage();
        this.repaint();
    }
    
    public void collectGraphics() {
    }
    
    public int colorunder(final TGPoint tgPoint) {
        final String s = "TGCanvas.colorunder: ";
        final int canvasX = tgPoint.canvasX(1600);
        if (canvasX < 0 || canvasX > 1600) {
            return TGCanvas.INITIAL_BACKGROUND.getRGB() & 0xFFFFFF;
        }
        final int canvasY = tgPoint.canvasY(1200);
        if (canvasY < 0 || canvasY > 1200) {
            return TGCanvas.INITIAL_BACKGROUND.getRGB() & 0xFFFFFF;
        }
        final int[] array = { 0 };
        if (this.graphicsImage == null) {
            return TGCanvas.INITIAL_BACKGROUND.getRGB() & 0xFFFFFF;
        }
        final PixelGrabber pixelGrabber = new PixelGrabber(this.graphicsImage, canvasX, canvasY, 1, 1, array, 0, 1600);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println(s + "grabPixels interrupted");
            return TGCanvas.INITIAL_BACKGROUND.getRGB() & 0xFFFFFF;
        }
        pixelGrabber.getStatus();
        return array[0] & 0xFFFFFF;
    }
    
    public TGPoint drawLine(final TGPoint tgPoint, final double n, final double n2, final int n3, final Color color) {
        final TGPoint otherEndPoint = tgPoint.otherEndPoint(n2, n);
        this.addGraphObj(new TGLineOp(tgPoint, otherEndPoint, color, n3));
        return otherEndPoint;
    }
    
    public void drawLine(final TGPoint tgPoint, final TGPoint tgPoint2, final int n, final Color color) {
        this.addGraphObj(new TGLineOp(tgPoint, tgPoint2, color, n));
    }
    
    public void fill(final TGPoint tgPoint, final Color color) {
        this.addGraphObj(new TGFillOp(tgPoint, color));
    }
    
    public void label(final String s, final TGPoint tgPoint, final Font font, final Color color) {
        this.addGraphObj(new TGLabelOp(s, tgPoint, font, color));
    }
    
    public int mousex() {
        return this.mouseX - this.xCenter;
    }
    
    public int mousey() {
        return -(this.mouseY - this.yCenter);
    }
    
    public void noCollectGraphics() {
        this.refreshList = null;
    }
    
    public void removeKeyHandler(final TGKeyHandler tgKeyHandler) {
        this.keyHandlers.removeElement(tgKeyHandler);
    }
    
    public void removeMouseHandler(final TGMouseHandler tgMouseHandler) {
        this.mouseHandlers.removeElement(tgMouseHandler);
    }
    
    public void removeTurtle(final Turtle turtle) {
        for (int i = this.turtles.length - 1; i >= 0; --i) {
            if (this.turtles[i] == turtle) {
                this.turtles[i] = null;
                return;
            }
        }
        System.err.println("TGCanvas.removeTurtle: turtle missing!");
    }
    
    public void tgDoRepaint() {
        super.repaint(0, 0, 1, 1);
    }
    
    static {
        INITIAL_BACKGROUND = Color.white;
        INITIAL_FOREGROUND = Color.black;
    }
}
