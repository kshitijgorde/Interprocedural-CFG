import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.MouseMotionListener;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.util.Vector;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class TGCanvas extends Canvas implements KeyListener, MouseListener
{
    private static final Color INITIAL_BACKGROUND;
    private static final Color INITIAL_FOREGROUND;
    private static final int INITIAL_FONT_SIZE = 14;
    private static final int INITIAL_FONT_STYLE = 0;
    private static final String INITIAL_FONT_NAME = "Courier";
    private static final Font INITIAL_FONT;
    private static final int INITIAL_PEN_SIZE = 2;
    private static final int MINIMUM_HEIGHT = 20;
    private boolean showFrame;
    private int canvasHeight;
    private int canvasWidth;
    private int mouseX;
    private int mouseY;
    private int xCenter;
    private int yCenter;
    private int[] graphicsPixels;
    private Color currentAWTColor;
    private Font currentAWTFont;
    private Graphics giGraphics;
    private Image graphicsImage;
    private Vector graphicsOps;
    private Vector keyHandlers;
    private Vector mouseHandlers;
    private Vector refreshList;
    private Vector turtles;
    
    public TGCanvas() {
        this(700, 400);
    }
    
    public TGCanvas(final int canvasWidth, final int canvasHeight) {
        this.setSize(canvasWidth, canvasHeight);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.xCenter = this.canvasWidth / 2;
        this.yCenter = this.canvasHeight / 2;
        this.setFont(this.currentAWTFont = new Font("Courier", 0, 14));
        this.currentAWTColor = TGCanvas.INITIAL_FOREGROUND;
        this.graphicsOps = new Vector(500, 500);
        this.keyHandlers = new Vector(5, 5);
        this.mouseHandlers = new Vector(5, 5);
        this.refreshList = new Vector(1000, 500);
        this.turtles = new Vector(5, 5);
        this.showFrame = false;
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
            ((TGKeyHandler)this.keyHandlers.elementAt(i)).keyPressed((int)keyChar);
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
    
    private void drawFrame() {
        if (this.giGraphics != null) {
            this.giGraphics.setColor(Color.black);
            this.giGraphics.drawRect(0, 0, this.canvasWidth - 1, this.canvasHeight - 1);
            this.giGraphics.setColor(this.currentAWTColor);
        }
    }
    
    private void initGraphicsImage() {
        this.graphicsImage = this.createImage(this.canvasWidth, this.canvasHeight);
        (this.giGraphics = this.graphicsImage.getGraphics()).setColor(TGCanvas.INITIAL_BACKGROUND);
        this.giGraphics.fillRect(0, 0, this.canvasWidth, this.canvasHeight);
        if (this.showFrame) {
            this.giGraphics.setColor(Color.black);
            this.giGraphics.drawRect(0, 0, this.canvasWidth - 1, this.canvasHeight - 1);
        }
        this.giGraphics.setColor(TGCanvas.INITIAL_FOREGROUND);
        this.currentAWTColor = TGCanvas.INITIAL_FOREGROUND;
        this.giGraphics.setFont(this.currentAWTFont);
    }
    
    private void renderGraphics() {
        if (this.graphicsImage == null) {
            this.initGraphicsImage();
        }
        synchronized (this.graphicsOps) {
            for (int i = this.graphicsOps.size(); i > 0; --i) {
                final Fill firstElement = this.graphicsOps.firstElement();
                if (firstElement instanceof Line) {
                    ((Line)firstElement).doIt(this.giGraphics);
                }
                else if (firstElement instanceof Label) {
                    ((Label)firstElement).doIt(this.giGraphics);
                }
                else if (firstElement instanceof Fill) {
                    firstElement.doIt(this.giGraphics, this);
                }
                if (this.refreshList != null) {
                    this.refreshList.addElement(firstElement);
                }
                this.graphicsOps.removeElementAt(0);
            }
        }
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.canvasWidth, 20);
    }
    
    public void paint(final Graphics graphics) {
        this.renderGraphics();
        graphics.drawImage(this.graphicsImage, 0, 0, this);
        for (int i = 0; i < this.turtles.size(); ++i) {
            final Turtle turtle = this.turtles.elementAt(i);
            final int n = (int)Math.rint(turtle.xcor() + this.xCenter);
            final int n2 = (int)Math.rint(this.yCenter - turtle.ycor());
            final int imageSideSize = turtle.getImageSideSize();
            graphics.drawImage(turtle.getImage(), n - imageSideSize / 2, n2 - imageSideSize / 2, this);
        }
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
        if (!this.turtles.contains(turtle)) {
            this.turtles.addElement(turtle);
        }
    }
    
    public int canvasHeight() {
        return this.canvasHeight;
    }
    
    public int canvasWidth() {
        return this.canvasWidth;
    }
    
    public void clean() {
        if (this.giGraphics != null) {
            this.giGraphics.dispose();
            this.giGraphics = null;
        }
        this.graphicsImage = null;
        synchronized (this.graphicsOps) {
            this.graphicsOps.removeAllElements();
            if (this.refreshList != null) {
                this.refreshList.removeAllElements();
            }
        }
        this.repaint();
    }
    
    public void collectGraphics() {
        this.refreshList = new Vector(1000, 500);
    }
    
    public int colorunder(final TGPoint tgPoint) {
        final int n = (int)Math.rint(tgPoint.x);
        final int n2 = (int)Math.rint(tgPoint.y);
        if (Math.abs(n) >= this.xCenter || Math.abs(n2) >= this.yCenter) {
            return -1;
        }
        final int n3 = n + this.xCenter;
        final int n4 = this.yCenter - n2;
        final int[] array = { 0 };
        if (this.graphicsImage == null) {
            this.initGraphicsImage();
        }
        final PixelGrabber pixelGrabber = new PixelGrabber(this.graphicsImage, n3, n4, 1, 1, array, 0, this.canvasWidth);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println("grabPixels interrupted");
            return -1;
        }
        if ((pixelGrabber.getStatus() & 0x80) != 0x0) {
            System.err.println("grabPixels aborted or errored");
            return -1;
        }
        return array[0] & 0xFFFFFF;
    }
    
    public TGPoint drawLine(final TGPoint tgPoint, final double n, final double n2, final int n3, final Color color) {
        final TGPoint otherEndPoint = this.otherEndPoint(tgPoint, n, n2);
        this.addGraphObj(new Line(tgPoint, otherEndPoint, n3, color));
        return otherEndPoint;
    }
    
    public void drawLine(final TGPoint tgPoint, final TGPoint tgPoint2, final int n, final Color color) {
        this.addGraphObj(new Line(tgPoint, tgPoint2, n, color));
    }
    
    public void fill(final TGPoint tgPoint, final Color color) {
        this.addGraphObj(new Fill(tgPoint, color));
    }
    
    public void label(final String s, final TGPoint tgPoint, final Font font, final Color color) {
        this.addGraphObj(new Label(s, tgPoint, font, color));
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
    
    public TGPoint otherEndPoint(final TGPoint tgPoint, final double n, final double n2) {
        return new TGPoint(tgPoint.x + Math.cos(n2) * n, tgPoint.y + Math.sin(n2) * n);
    }
    
    public void removeKeyHandler(final TGKeyHandler tgKeyHandler) {
        this.keyHandlers.removeElement(tgKeyHandler);
    }
    
    public void removeMouseHandler(final TGMouseHandler tgMouseHandler) {
        this.mouseHandlers.removeElement(tgMouseHandler);
    }
    
    public void removeTurtle(final Turtle turtle) {
        this.turtles.removeElement(turtle);
    }
    
    public void resizeCanvas(final int canvasWidth, final int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.xCenter = canvasWidth / 2;
        this.yCenter = canvasHeight / 2;
        synchronized (this.graphicsOps) {
            if (this.refreshList == null) {
                this.graphicsOps.removeAllElements();
            }
            else {
                for (int i = this.graphicsOps.size(); i > 0; --i) {
                    this.refreshList.addElement(this.graphicsOps.firstElement());
                    this.graphicsOps.removeElementAt(0);
                }
                this.graphicsOps = this.refreshList;
                this.refreshList = new Vector(this.graphicsOps.size() + 500, 500);
            }
            this.graphicsImage = null;
            this.graphicsPixels = null;
        }
        this.repaint();
    }
    
    public void showFrame() {
        this.showFrame = true;
        this.drawFrame();
    }
    
    static {
        INITIAL_BACKGROUND = Color.white;
        INITIAL_FOREGROUND = Color.black;
        INITIAL_FONT = new Font("Courier", 0, 14);
    }
    
    class Fill
    {
        Color color;
        TGPoint point;
        
        Fill(final TGPoint point, final Color color) {
            this.color = color;
            this.point = point;
        }
        
        void floodFill(final int[] array, final int n, final int n2, final int n3, final int n4) {
            int n5 = n;
            int n6 = n;
            for (int i = n; i >= 0; --i) {
                final int n7 = n2 * TGCanvas.this.canvasWidth + i;
                if ((array[n7] & 0xFFFFFF) != n3) {
                    break;
                }
                array[n7] = ((array[n7] & 0xFF000000) | n4);
                n5 = i;
            }
            for (int j = n + 1; j < TGCanvas.this.canvasWidth; ++j) {
                final int n8 = n2 * TGCanvas.this.canvasWidth + j;
                if ((array[n8] & 0xFFFFFF) != n3) {
                    break;
                }
                array[n8] = ((array[n8] & 0xFF000000) | n4);
                n6 = j;
            }
            if (n2 > 0) {
                for (int k = n5; k <= n6; ++k) {
                    if ((array[(n2 - 1) * TGCanvas.this.canvasWidth + k] & 0xFFFFFF) == n3) {
                        this.floodFill(array, k, n2 - 1, n3, n4);
                    }
                }
            }
            if (n2 < TGCanvas.this.canvasHeight - 1) {
                for (int l = n5; l <= n6; ++l) {
                    if ((array[(n2 + 1) * TGCanvas.this.canvasWidth + l] & 0xFFFFFF) == n3) {
                        this.floodFill(array, l, n2 + 1, n3, n4);
                    }
                }
            }
        }
        
        void doIt(final Graphics graphics, final TGCanvas tgCanvas) {
            final int n = (int)Math.rint(this.point.x);
            final int n2 = (int)Math.rint(this.point.y);
            if (Math.abs(n) >= TGCanvas.this.xCenter || Math.abs(n2) >= TGCanvas.this.yCenter) {
                return;
            }
            final int n3 = n + TGCanvas.this.xCenter;
            final int n4 = TGCanvas.this.yCenter - n2;
            if (TGCanvas.this.graphicsImage == null) {
                TGCanvas.this.initGraphicsImage();
            }
            if (TGCanvas.this.graphicsPixels == null) {
                TGCanvas.this.graphicsPixels = new int[TGCanvas.this.canvasWidth * TGCanvas.this.canvasHeight];
            }
            final PixelGrabber pixelGrabber = new PixelGrabber(TGCanvas.this.graphicsImage, 0, 0, TGCanvas.this.canvasWidth, TGCanvas.this.canvasHeight, TGCanvas.this.graphicsPixels, 0, TGCanvas.this.canvasWidth);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex) {
                System.err.println("grabPixels interrupted");
                return;
            }
            if ((pixelGrabber.getStatus() & 0x80) != 0x0) {
                System.err.println("grabPixels aborted or errored");
                return;
            }
            final int n5 = TGCanvas.this.graphicsPixels[n4 * TGCanvas.this.canvasWidth + n3] & 0xFFFFFF;
            if (TGCanvas.this.currentAWTColor.getRGB() != this.color.getRGB()) {
                graphics.setColor(this.color);
                TGCanvas.this.currentAWTColor = this.color;
            }
            final int n6 = TGCanvas.this.currentAWTColor.getRGB() & 0xFFFFFF;
            if (n5 != n6) {
                this.floodFill(TGCanvas.this.graphicsPixels, n3, n4, n5, n6);
            }
            graphics.drawImage(TGCanvas.this.createImage(new MemoryImageSource(TGCanvas.this.canvasWidth, TGCanvas.this.canvasHeight, TGCanvas.this.graphicsPixels, 0, TGCanvas.this.canvasWidth)), 0, 0, TGCanvas.this.canvasWidth, TGCanvas.this.canvasHeight, tgCanvas);
        }
    }
    
    class Label
    {
        Color color;
        Font font;
        String text;
        TGPoint point;
        
        Label(final String text, final TGPoint point, final Font font, final Color color) {
            this.color = color;
            this.font = font;
            this.point = point;
            this.text = text;
        }
        
        void doIt(final Graphics graphics) {
            if (TGCanvas.this.currentAWTColor.getRGB() != this.color.getRGB()) {
                graphics.setColor(this.color);
                TGCanvas.this.currentAWTColor = this.color;
            }
            if (TGCanvas.this.currentAWTFont.getSize() != this.font.getSize()) {
                graphics.setFont(this.font);
                TGCanvas.this.currentAWTFont = this.font;
            }
            graphics.drawString(this.text, (int)Math.rint(this.point.x + TGCanvas.this.xCenter), (int)Math.rint(TGCanvas.this.yCenter - this.point.y));
        }
    }
    
    class Line
    {
        Color color;
        int width;
        TGPoint p1;
        TGPoint p2;
        
        public Line(final TGPoint p5, final TGPoint p6, final int width, final Color color) {
            this.p1 = p5;
            this.p2 = p6;
            this.color = color;
            this.width = width;
        }
        
        private void printPixels(final int[] array, final int[] array2) {
            int access$000 = TGCanvas.this.canvasWidth;
            int n = 0;
            int access$2 = TGCanvas.this.canvasHeight;
            int n2 = 0;
            for (int i = 0; i < array.length; ++i) {
                if (array[i] < access$000) {
                    access$000 = array[i];
                }
                if (array[i] > n) {
                    n = array[i];
                }
            }
            final int n3 = access$000 - 5;
            final int n4 = n + 5;
            for (int j = 0; j < array2.length; ++j) {
                if (array2[j] < access$2) {
                    access$2 = array2[j];
                }
                if (array2[j] > n2) {
                    n2 = array2[j];
                }
            }
            final int n5 = access$2 - 5;
            final int n6 = n2 + 5;
            if (TGCanvas.this.graphicsImage == null) {
                TGCanvas.this.initGraphicsImage();
            }
            if (TGCanvas.this.graphicsPixels == null) {
                TGCanvas.this.graphicsPixels = new int[TGCanvas.this.canvasWidth * TGCanvas.this.canvasHeight];
            }
            final PixelGrabber pixelGrabber = new PixelGrabber(TGCanvas.this.graphicsImage, 0, 0, TGCanvas.this.canvasWidth, TGCanvas.this.canvasHeight, TGCanvas.this.graphicsPixels, 0, TGCanvas.this.canvasWidth);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex) {
                System.err.println("grabPixels interrupted");
                return;
            }
            if ((pixelGrabber.getStatus() & 0x80) != 0x0) {
                System.err.println("grabPixels aborted or errored");
                return;
            }
            for (int k = n5; k <= n6; ++k) {
                for (int l = n3; l <= n4; ++l) {
                    if ((TGCanvas.this.graphicsPixels[k * TGCanvas.this.canvasWidth + l] & 0xFFFFFF) == 0x0) {
                        System.out.print("*");
                    }
                    else {
                        System.out.print("-");
                    }
                }
                System.out.println("");
            }
        }
        
        private void drawFatLine(final Graphics graphics) {
            final double atan = Math.atan((this.p2.y - this.p1.y) / (this.p2.x - this.p1.x));
            final int[] array = new int[4];
            final int[] array2 = new int[4];
            double n = atan + 1.5707963267948966;
            if (n > 6.283185307179586) {
                n -= 6.283185307179586;
            }
            final TGPoint otherEndPoint = TGCanvas.this.otherEndPoint(this.p1, this.width / 2.0, n);
            array[0] = (int)Math.rint(TGCanvas.this.xCenter + otherEndPoint.x);
            array2[0] = (int)Math.rint(TGCanvas.this.yCenter - otherEndPoint.y);
            final TGPoint otherEndPoint2 = TGCanvas.this.otherEndPoint(this.p2, this.width / 2.0, n);
            array[1] = (int)Math.rint(TGCanvas.this.xCenter + otherEndPoint2.x);
            array2[1] = (int)Math.rint(TGCanvas.this.yCenter - otherEndPoint2.y);
            double n2 = atan - 1.5707963267948966;
            if (n2 < 0.0) {
                n2 += 6.283185307179586;
            }
            final TGPoint otherEndPoint3 = TGCanvas.this.otherEndPoint(this.p2, this.width / 2.0, n2);
            array[2] = (int)Math.rint(TGCanvas.this.xCenter + otherEndPoint3.x);
            array2[2] = (int)Math.rint(TGCanvas.this.yCenter - otherEndPoint3.y);
            final TGPoint otherEndPoint4 = TGCanvas.this.otherEndPoint(this.p1, this.width / 2.0, n2);
            array[3] = (int)Math.rint(TGCanvas.this.xCenter + otherEndPoint4.x);
            array2[3] = (int)Math.rint(TGCanvas.this.yCenter - otherEndPoint4.y);
            graphics.fillPolygon(array, array2, 4);
        }
        
        void doIt(final Graphics graphics) {
            if (TGCanvas.this.currentAWTColor.getRGB() != this.color.getRGB()) {
                graphics.setColor(this.color);
                TGCanvas.this.currentAWTColor = this.color;
            }
            if (this.width == 1) {
                graphics.drawLine((int)Math.rint(this.p1.x + TGCanvas.this.xCenter), (int)Math.rint(TGCanvas.this.yCenter - this.p1.y), (int)Math.rint(this.p2.x + TGCanvas.this.xCenter), (int)Math.rint(TGCanvas.this.yCenter - this.p2.y));
            }
            else {
                this.drawFatLine(graphics);
            }
        }
        
        public String toString() {
            return "TGCanvas$Line[" + this.color + ",width=" + this.width + ",p1=[" + this.p1 + "],p2=[" + this.p2 + "]]";
        }
    }
}
