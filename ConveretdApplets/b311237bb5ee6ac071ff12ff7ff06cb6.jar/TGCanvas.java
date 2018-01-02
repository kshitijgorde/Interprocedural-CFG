import java.awt.image.PixelGrabber;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.Font;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.FocusListener;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class TGCanvas extends Component implements FocusListener, KeyListener, MouseListener, MouseMotionListener
{
    public static final int MAX_TURTLES = 64;
    public static final int MINIMUM_HEIGHT = 40;
    public static final int MINIMUM_WIDTH = 40;
    private static final String CLASS_NAME = "TGCanvas";
    private static final Color INITIAL_BACKGROUND;
    private static final Color INITIAL_PEN_COLOR;
    private static final int GI_HEIGHT = 1201;
    private static final int GI_WIDTH = 1601;
    private static final int INITIAL_FONT_SIZE = 14;
    private static final int INITIAL_FONT_STYLE = 0;
    private static final int INITIAL_PEN_SIZE = 2;
    private static final String INITIAL_FONT_NAME = "Courier";
    private static final int PAINT_REFRESH = 0;
    private static final int PAINT_DRAW_GRAPHICS = 1;
    private static final int PAINT_ERASE_TURTLES = 2;
    private static final int PAINT_DRAW_TURTLES = 3;
    private boolean gotFocus;
    private int canvasHeight;
    private int canvasWidth;
    private int mouseX;
    private int mouseY;
    private int paintState;
    private int paintTurtleNum;
    private int xCenter;
    private int yCenter;
    private Color background;
    private Image graphicsImage;
    private Rectangle[] turtleClipRect;
    private Turtle[] turtles;
    private Vector graphicsOps;
    private Vector keyHandlers;
    private Vector mouseHandlers;
    
    public TGCanvas() {
        this(700, 400);
    }
    
    public TGCanvas(final int canvasWidth, final int canvasHeight) {
        if (canvasWidth > 40) {
            this.canvasWidth = canvasWidth;
        }
        else {
            this.canvasWidth = 40;
        }
        if (canvasHeight > 40) {
            this.canvasHeight = canvasHeight;
        }
        else {
            this.canvasHeight = 40;
        }
        super.setSize(this.canvasWidth, this.canvasHeight);
        this.addFocusListener(this);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.xCenter = this.canvasWidth / 2;
        this.yCenter = this.canvasHeight / 2;
        this.setFont(new Font("Courier", 0, 14));
        this.background = TGCanvas.INITIAL_BACKGROUND;
        this.graphicsOps = new Vector(200, 100);
        this.keyHandlers = new Vector(5, 5);
        this.mouseHandlers = new Vector(5, 5);
        this.turtleClipRect = new Rectangle[64];
        this.turtles = new Turtle[64];
        this.paintState = 0;
        this.gotFocus = false;
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.gotFocus = true;
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.gotFocus = false;
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
                ((TGMouseHandler)this.mouseHandlers.elementAt(i)).mouseClicked();
            }
            this.requestFocus();
        }
        else {
            this.fwdMouseEvent(502, mouseEvent);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.gotFocus) {
            this.mouseX = mouseEvent.getX();
            this.mouseY = mouseEvent.getY();
            for (int size = this.mouseHandlers.size(), i = 0; i < size; ++i) {
                ((TGMouseHandler)this.mouseHandlers.elementAt(i)).mouseMoved();
            }
        }
    }
    
    private void addGraphObj(final Object o) {
        synchronized (this.graphicsOps) {
            this.graphicsOps.addElement(o);
        }
    }
    
    private void clearGraphicsImage() {
        if (this.graphicsImage != null) {
            final Graphics graphics = this.graphicsImage.getGraphics();
            graphics.setClip(0, 0, 1600, 1200);
            graphics.setColor(this.background);
            graphics.fillRect(0, 0, 1600, 1200);
            graphics.setColor(TGCanvas.INITIAL_PEN_COLOR);
            graphics.dispose();
        }
    }
    
    private void initGraphicsImage() {
        this.graphicsImage = this.createImage(1601, 1201);
        this.clearGraphicsImage();
    }
    
    private Rectangle renderGraphics() {
        int x = 1601;
        int n = -1;
        int y = 1201;
        int n2 = -1;
        if (this.graphicsImage == null) {
            this.initGraphicsImage();
            x = 0;
            n = 1600;
            y = 0;
            n2 = 1200;
        }
        synchronized (this.graphicsOps) {
            for (int i = this.graphicsOps.size(); i > 0; --i) {
                final Rectangle doIt = this.graphicsOps.firstElement().doIt(this.graphicsImage);
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
                this.graphicsOps.removeElementAt(0);
            }
        }
        int canvasWidth = n + 1 - x;
        int n5 = x - (1601 - this.canvasWidth) / 2;
        if (n5 < 0) {
            canvasWidth += n5;
            n5 = 0;
        }
        if (canvasWidth > this.canvasWidth) {
            canvasWidth = this.canvasWidth;
        }
        int canvasHeight = n2 + 1 - y;
        int n6 = y - (1201 - this.canvasHeight) / 2;
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
        return new Dimension(40, 40);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.canvasWidth, this.canvasHeight);
    }
    
    public Dimension getSize() {
        return new Dimension(this.canvasWidth, this.canvasHeight);
    }
    
    public void paint(final Graphics graphics) {
        graphics.getClipBounds();
        final int n = (1201 - this.canvasHeight) / 2;
        final int n2 = (1601 - this.canvasWidth) / 2;
        switch (this.paintState) {
            case 0: {
                if (this.graphicsImage == null) {
                    graphics.setColor(this.background);
                    graphics.fillRect(0, 0, this.canvasWidth, this.canvasHeight);
                }
                else if (!graphics.drawImage(this.graphicsImage, -n2, -n, this)) {
                    return;
                }
                this.paintState = 1;
            }
            case 1: {
                final Rectangle renderGraphics = this.renderGraphics();
                if (renderGraphics != null) {
                    graphics.setClip(renderGraphics);
                    if (!graphics.drawImage(this.graphicsImage, -n2, -n, this)) {
                        return;
                    }
                }
                this.paintState = 2;
                this.paintTurtleNum = 0;
            }
            case 2: {
                while (this.paintTurtleNum < this.turtleClipRect.length) {
                    final Rectangle clip;
                    if ((clip = this.turtleClipRect[this.paintTurtleNum]) != null) {
                        graphics.setClip(clip);
                        if (!graphics.drawImage(this.graphicsImage, -n2, -n, this)) {
                            return;
                        }
                        this.turtleClipRect[this.paintTurtleNum] = null;
                    }
                    ++this.paintTurtleNum;
                }
                this.paintState = 3;
                this.paintTurtleNum = 0;
            }
            case 3: {
                while (this.paintTurtleNum < this.turtles.length) {
                    final Turtle turtle = this.turtles[this.paintTurtleNum];
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
                        this.turtleClipRect[this.paintTurtleNum] = new Rectangle(n5, n6, imageSideSize, imageSideSize);
                    }
                    ++this.paintTurtleNum;
                }
                this.paintState = 0;
                break;
            }
        }
    }
    
    public void setBounds(final int n, final int n2, final int canvasWidth, final int canvasHeight) {
        super.setBounds(n, n2, canvasWidth, canvasHeight);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.xCenter = canvasWidth / 2;
        this.yCenter = canvasHeight / 2;
        this.repaint();
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
        this.repaint();
    }
    
    public void setbg(final int n) {
        synchronized (this.graphicsOps) {
            this.graphicsOps.removeAllElements();
        }
        this.background = Turtle.rgbToColor(n);
        this.clearGraphicsImage();
        this.repaint();
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
        }
        this.clearGraphicsImage();
        this.repaint();
    }
    
    public int colorunder(final TGPoint tgPoint) {
        final String s = "TGCanvas.colorunder: ";
        final int imageX = tgPoint.imageX(1601);
        if (imageX < 0 || imageX > 1601) {
            return this.background.getRGB() & 0xFFFFFF;
        }
        final int imageY = tgPoint.imageY(1201);
        if (imageY < 0 || imageY > 1201) {
            return this.background.getRGB() & 0xFFFFFF;
        }
        final int[] array = { 0 };
        if (this.graphicsImage == null) {
            return this.background.getRGB() & 0xFFFFFF;
        }
        final PixelGrabber pixelGrabber = new PixelGrabber(this.graphicsImage, imageX, imageY, 1, 1, array, 0, 1601);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println(s + "grabPixels interrupted");
            return this.background.getRGB() & 0xFFFFFF;
        }
        pixelGrabber.getStatus();
        return array[0] & 0xFFFFFF;
    }
    
    public TGPoint drawLine(final TGPoint tgPoint, double n, double n2, final int n3, final Color color) {
        if (n < 0.0) {
            n2 -= 3.141592653589793;
            if (n2 < 0.0) {
                n2 += 6.283185307179586;
            }
            n = -n;
        }
        final TGPoint otherEndPoint = tgPoint.otherEndPoint(n2, n);
        this.addGraphObj(new TGLineOp(tgPoint, otherEndPoint, n2, color, n3));
        return otherEndPoint;
    }
    
    public void drawLine(final TGPoint tgPoint, final TGPoint tgPoint2, final double n, final int n2, final Color color) {
        this.addGraphObj(new TGLineOp(tgPoint, tgPoint2, n, color, n2));
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
    
    static {
        INITIAL_BACKGROUND = Color.white;
        INITIAL_PEN_COLOR = Color.black;
    }
}
