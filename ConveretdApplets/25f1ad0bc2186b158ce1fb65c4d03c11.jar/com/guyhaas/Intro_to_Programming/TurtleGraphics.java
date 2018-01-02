// 
// Decompiled by Procyon v0.5.30
// 

package com.guyhaas.Intro_to_Programming;

import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.util.Vector;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Canvas;

public class TurtleGraphics extends Canvas
{
    private static final int TURTLE_HEIGHT = 15;
    private static final int TURTLE_WIDTH = 21;
    private static final Color[] colors;
    private static final Color initialForeground;
    private static final Color initialBackground;
    private static final int initialPenSize = 3;
    private static final int initialFontSize = 14;
    private static final int initialFontStyle = 0;
    private static final String initialFontName = "SansSerif";
    private static final Font initialFont;
    private boolean penDown;
    private boolean showTurtle;
    private Color currentPenColor;
    private Font currentFont;
    private Graphics graphicsImageGraphics;
    private Image graphicsImage;
    private Image currentTurtleImage;
    private int canvasHeight;
    private int canvasWidth;
    private int currentFontSize;
    private int currentFontStyle;
    private int currentHeading;
    private int currentPenSize;
    private int currentX;
    private int currentY;
    private int turtleSideSize;
    private int xCenter;
    private int yCenter;
    private int[] baseTurtlePixels;
    private int[] currentTurtlePixels;
    private MemoryImageSource turtleImageProducer;
    private String currentFontName;
    private Vector graphicsOps;
    private Vector refreshList;
    
    public TurtleGraphics() {
        this(640, 480);
    }
    
    public TurtleGraphics(final int canvasWidth, final int canvasHeight) {
        this.currentFontSize = 14;
        this.currentFontStyle = 0;
        this.currentFontName = "SansSerif";
        this.setSize(canvasWidth, canvasHeight);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.xCenter = canvasWidth / 2;
        this.yCenter = canvasHeight / 2;
        this.currentX = this.xCenter;
        this.currentY = this.yCenter;
        this.currentHeading = 90;
        this.penDown = true;
        this.currentPenColor = TurtleGraphics.initialForeground;
        this.currentPenSize = 3;
        this.setFont(this.currentFont = TurtleGraphics.initialFont);
        this.showTurtle = true;
        this.baseTurtlePixels = null;
        this.graphicsImage = null;
        this.graphicsOps = new Vector(100, 50);
        this.refreshList = new Vector(1000, 500);
        this.addPen();
    }
    
    private Point otherEndPoint(final Point point, final int n, final int n2) {
        return this.otherEndPoint(point, n, 0.017453292519943295 * n2);
    }
    
    private Point otherEndPoint(final Point point, final double n, final double n2) {
        final double sin = Math.sin(n2);
        final double n3 = Math.cos(n2) * n;
        final double n4 = -(sin * n);
        final Point point2 = new Point();
        point2.x = (int)Math.rint(point.x + n3);
        point2.y = (int)Math.rint(point.y + n4);
        return point2;
    }
    
    private void addGraphObj(final Object o) {
        synchronized (this.graphicsOps) {
            this.graphicsOps.addElement(o);
        }
    }
    
    private void addDrawLine(final int n, final int n2, final int n3, final int n4) {
        this.addDrawLine(new Point(n, n2), new Point(n3, n4));
    }
    
    private void addDrawLine(final Point point, final Point point2) {
        final double n = point2.x - point.x;
        final double n2 = point.y - point2.y;
        this.addGraphObj(new Line(point, point2, Math.atan(n2 / n), Math.sqrt(Math.pow(n, 2.0) + Math.pow(n2, 2.0))));
    }
    
    private void addPen() {
        this.addGraphObj(new Pen(this.currentPenSize, this.currentPenColor));
    }
    
    private void addPen(final int n, final Color color) {
        this.addGraphObj(new Pen(n, color));
    }
    
    private void addPenColor(final Color color) {
        this.addGraphObj(new Pen(this.currentPenSize, color));
    }
    
    private void addPenSize(final int n) {
        this.addGraphObj(new Pen(n, this.currentPenColor));
    }
    
    private void addLabel(final String s, final int n, final int n2) {
        this.addGraphObj(new Label(n, n2, new Font(this.currentFontName, this.currentFontStyle, this.currentFontSize), s));
    }
    
    private void drawFatLine(final Graphics graphics, final Line line) {
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        double n = line.heading + 1.5707963267948966;
        if (n > 6.283185307179586) {
            n -= 6.283185307179586;
        }
        final Point otherEndPoint = this.otherEndPoint(line.p1, line.penSize / 2.0, n);
        array[0] = otherEndPoint.x;
        array2[0] = otherEndPoint.y;
        final Point otherEndPoint2 = this.otherEndPoint(line.p2, line.penSize / 2.0, n);
        array[1] = otherEndPoint2.x;
        array2[1] = otherEndPoint2.y;
        double n2 = line.heading - 1.5707963267948966;
        if (n2 < 0.0) {
            n2 += 6.283185307179586;
        }
        final Point otherEndPoint3 = this.otherEndPoint(line.p2, line.penSize / 2.0, n2);
        array[2] = otherEndPoint3.x;
        array2[2] = otherEndPoint3.y;
        final Point otherEndPoint4 = this.otherEndPoint(line.p1, line.penSize / 2.0, n2);
        array[3] = otherEndPoint4.x;
        array2[3] = otherEndPoint4.y;
        graphics.fillPolygon(array, array2, 4);
    }
    
    void printPixels(final String s, final int[] array, final int n, final int n2) {
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                final int n3 = i * n + j;
                System.out.println(s + "[" + n3 + "] a=" + (array[n3] >> 24 & 0xFF) + ", r=" + (array[n3] >> 16 & 0xFF) + ", g=" + (array[n3] >> 8 & 0xFF) + ", b=" + (array[n3] & 0xFF));
            }
            System.out.println("----------");
        }
    }
    
    void colorTurtle() {
        final int rgb = this.currentPenColor.getRGB();
        final int n = this.turtleSideSize / 2;
        for (int i = this.turtleSideSize / 2 - 10 + 1; i < this.turtleSideSize / 2 + 10; ++i) {
            int n2 = -1;
            for (int j = n; j > 0; --j) {
                if ((this.baseTurtlePixels[j * this.turtleSideSize + i] & 0xFF000000) == 0x0) {
                    n2 = (j + 1) * this.turtleSideSize + i;
                    break;
                }
            }
            if (n2 < 0) {
                n2 = i;
            }
            for (int k = n2 + this.turtleSideSize; k <= n * this.turtleSideSize; k += this.turtleSideSize) {
                this.baseTurtlePixels[k] = (this.baseTurtlePixels[k] & 0xFF000000) + rgb;
            }
            int n3 = -1;
            for (int l = n + 1; l < this.turtleSideSize; ++l) {
                if ((this.baseTurtlePixels[l * this.turtleSideSize + i] & 0xFF000000) == 0x0) {
                    n3 = (l - 1) * this.turtleSideSize + i;
                    break;
                }
            }
            if (n3 < 0) {
                n3 = (this.turtleSideSize - 1) * this.turtleSideSize + i;
            }
            for (int n4 = n3 - this.turtleSideSize; n4 > n * this.turtleSideSize; n4 -= this.turtleSideSize) {
                this.baseTurtlePixels[n4] = (this.baseTurtlePixels[n4] & 0xFF000000) + rgb;
            }
        }
        this.renderTurtle();
    }
    
    private void initTurtle() {
        final int[] array = new int[3];
        final int[] array2 = new int[3];
        this.turtleSideSize = 26;
        if (this.turtleSideSize % 2 == 0) {
            ++this.turtleSideSize;
        }
        final Image image = this.createImage(this.turtleSideSize, this.turtleSideSize);
        array[0] = this.turtleSideSize / 2 - 10;
        array2[0] = this.turtleSideSize / 2 - 7;
        array[1] = this.turtleSideSize / 2 - 10;
        array2[1] = this.turtleSideSize / 2 + 7;
        array[2] = this.turtleSideSize / 2 + 10;
        array2[2] = this.turtleSideSize / 2;
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.drawPolygon(array, array2, 3);
        graphics.fillPolygon(array, array2, 3);
        graphics.dispose();
        this.baseTurtlePixels = new int[this.turtleSideSize * this.turtleSideSize];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.turtleSideSize, this.turtleSideSize, this.baseTurtlePixels, 0, this.turtleSideSize);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println("initTurtle: interrupt during grabPixels()");
        }
        if ((pixelGrabber.getStatus() & 0x80) != 0x0) {
            System.err.println("initTurtle: grabPixels() aborted");
        }
        for (int i = 0; i < this.turtleSideSize * this.turtleSideSize; ++i) {
            if ((this.baseTurtlePixels[i] & 0xFFFFFF) == 0xFFFFFF) {
                this.baseTurtlePixels[i] = 0;
            }
        }
        this.currentTurtlePixels = new int[this.turtleSideSize * this.turtleSideSize];
        this.turtleImageProducer = new MemoryImageSource(this.turtleSideSize, this.turtleSideSize, this.currentTurtlePixels, 0, this.turtleSideSize);
        this.renderTurtle();
    }
    
    private void renderGraphics() {
        if (this.graphicsImage == null) {
            this.graphicsImage = this.createImage(this.canvasWidth, this.canvasHeight);
            (this.graphicsImageGraphics = this.graphicsImage.getGraphics()).setColor(TurtleGraphics.initialBackground);
            this.graphicsImageGraphics.fillRect(0, 0, this.canvasWidth, this.canvasHeight);
            this.graphicsImageGraphics.setColor(TurtleGraphics.initialForeground);
            this.graphicsImageGraphics.setFont(TurtleGraphics.initialFont);
        }
        synchronized (this.graphicsOps) {
            for (int i = this.graphicsOps.size(); i > 0; --i) {
                final Line firstElement = this.graphicsOps.firstElement();
                if (firstElement instanceof Pen) {
                    this.graphicsImageGraphics.setColor(((Pen)firstElement).color);
                }
                else if (firstElement instanceof Label) {
                    final Label label = (Label)firstElement;
                    this.graphicsImageGraphics.setFont(label.font);
                    this.graphicsImageGraphics.drawString(label.text, label.xPos, label.yPos);
                }
                else if (firstElement instanceof Line) {
                    final Line line = firstElement;
                    if (line.penSize == 1) {
                        this.graphicsImageGraphics.drawLine(line.p1.x, line.p1.y, line.p2.x, line.p2.y);
                    }
                    else {
                        this.drawFatLine(this.graphicsImageGraphics, line);
                    }
                }
                this.refreshList.addElement(firstElement);
                this.graphicsOps.removeElementAt(0);
            }
        }
    }
    
    private void renderTurtle() {
        if (this.currentHeading == 0) {
            for (int i = 0; i < this.turtleSideSize * this.turtleSideSize; ++i) {
                this.currentTurtlePixels[i] = this.baseTurtlePixels[i];
            }
        }
        else {
            final int n = this.turtleSideSize / 2;
            final double n2 = 0.017453292519943295 * (360 - this.currentHeading);
            final double cos = Math.cos(n2);
            final double sin = Math.sin(n2);
            final double n3 = -n * cos - n * sin + n;
            final double n4 = n * sin - n * cos + n;
            for (int j = 0; j < this.turtleSideSize; ++j) {
                for (int k = 0; k < this.turtleSideSize; ++k) {
                    this.currentTurtlePixels[j * this.turtleSideSize + k] = 0;
                }
            }
            for (int l = 0; l < this.turtleSideSize; ++l) {
                for (int n5 = 0; n5 < this.turtleSideSize; ++n5) {
                    final int n6 = (int)Math.rint(n5 * cos + l * sin + n3);
                    final int n7 = (int)Math.rint(-n5 * sin + l * cos + n4);
                    if (n6 >= 0) {
                        if (n6 < this.turtleSideSize) {
                            if (n7 >= 0) {
                                if (n7 < this.turtleSideSize) {
                                    this.currentTurtlePixels[l * this.turtleSideSize + n5] = this.baseTurtlePixels[n7 * this.turtleSideSize + n6];
                                }
                            }
                        }
                    }
                }
            }
        }
        this.currentTurtleImage = this.createImage(this.turtleImageProducer);
    }
    
    public void paint(final Graphics graphics) {
        this.renderGraphics();
        graphics.drawImage(this.graphicsImage, 0, 0, this);
        if (this.showTurtle) {
            if (this.baseTurtlePixels == null) {
                this.initTurtle();
            }
            graphics.drawImage(this.currentTurtleImage, this.currentX - this.turtleSideSize / 2, this.currentY - this.turtleSideSize / 2, this);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void bk(final int n) {
        final Point point = new Point(this.currentX, this.currentY);
        final Point otherEndPoint = this.otherEndPoint(point, -n, this.currentHeading);
        if (this.penDown) {
            this.addDrawLine(point, otherEndPoint);
        }
        this.currentX = otherEndPoint.x;
        this.currentY = otherEndPoint.y;
        if (this.penDown || this.showTurtle) {
            this.repaint();
        }
    }
    
    public void back(final int n) {
        this.bk(n);
    }
    
    public void clean() {
        if (this.graphicsImageGraphics != null) {
            this.graphicsImageGraphics.dispose();
            this.graphicsImageGraphics = null;
        }
        this.graphicsImage = null;
        synchronized (this.graphicsOps) {
            this.graphicsOps.removeAllElements();
        }
        this.addPen();
        this.repaint();
    }
    
    public void fd(final int n) {
        final Point point = new Point(this.currentX, this.currentY);
        final Point otherEndPoint = this.otherEndPoint(point, n, this.currentHeading);
        if (this.penDown) {
            this.addDrawLine(point, otherEndPoint);
        }
        this.currentX = otherEndPoint.x;
        this.currentY = otherEndPoint.y;
        if (this.penDown || this.showTurtle) {
            this.repaint();
        }
    }
    
    public void forward(final int n) {
        this.fd(n);
    }
    
    public int getpencolor() {
        for (int i = 0; i < TurtleGraphics.colors.length; ++i) {
            if (this.currentPenColor.equals(TurtleGraphics.colors[i])) {
                return i;
            }
        }
        return this.currentPenColor.getRGB();
    }
    
    public void home() {
        this.setxy(0, 0);
    }
    
    public void ht() {
        this.showTurtle = false;
        this.repaint();
    }
    
    public void hideturtle() {
        this.ht();
    }
    
    public void label(final String s) {
        this.addLabel(s, this.currentX, this.currentY);
        this.repaint();
    }
    
    public void lt(final int n) {
        this.currentHeading += n;
        this.currentHeading %= 360;
        if (this.showTurtle) {
            this.renderTurtle();
            this.repaint();
        }
    }
    
    public void left(final int n) {
        this.lt(n);
    }
    
    public void pd() {
        this.penDown = true;
    }
    
    public void pendown() {
        this.pd();
    }
    
    public void pu() {
        this.penDown = false;
    }
    
    public void penup() {
        this.pu();
    }
    
    public void setfontsize(final int currentFontSize) {
        this.currentFontSize = currentFontSize;
        this.currentFont = new Font(this.currentFontName, this.currentFontStyle, this.currentFontSize);
    }
    
    public void st() {
        this.showTurtle = true;
        this.renderTurtle();
        this.repaint();
    }
    
    public void showturtle() {
        this.st();
    }
    
    public void setx(int currentX) {
        currentX += this.xCenter;
        if (this.penDown) {
            this.addDrawLine(this.currentX, this.currentY, currentX, this.currentY);
        }
        this.currentX = currentX;
        if (this.penDown || this.showTurtle) {
            this.repaint();
        }
    }
    
    public void setxy(int currentX, int currentY) {
        currentX += this.xCenter;
        currentY = this.yCenter - currentY;
        if (this.penDown) {
            this.addDrawLine(this.currentX, this.currentY, currentX, currentY);
        }
        this.currentX = currentX;
        this.currentY = currentY;
        if (this.penDown || this.showTurtle) {
            this.repaint();
        }
    }
    
    public void sety(int currentY) {
        currentY = this.yCenter - currentY;
        if (this.penDown) {
            this.addDrawLine(this.currentX, this.currentY, this.currentX, currentY);
        }
        this.currentY = currentY;
        if (this.penDown || this.showTurtle) {
            this.repaint();
        }
    }
    
    public void rt(final int n) {
        this.currentHeading -= n;
        if (this.currentHeading < 0) {
            this.currentHeading += 360;
        }
        if (this.showTurtle) {
            this.renderTurtle();
            this.repaint();
        }
    }
    
    public void right(final int n) {
        this.rt(n);
    }
    
    public void seth(final int n) {
        this.currentHeading = 360 - n % 360;
        this.currentHeading += 90;
        this.currentHeading %= 360;
        if (this.showTurtle) {
            this.renderTurtle();
            this.repaint();
        }
    }
    
    public void setheading(final int n) {
        this.seth(n);
    }
    
    public void setpc(int n) {
        Color currentPenColor;
        if (n >= 0 && n <= 15) {
            currentPenColor = TurtleGraphics.colors[n];
        }
        else {
            n &= 0x7FFFFFF;
            currentPenColor = new Color(n);
        }
        if (!this.currentPenColor.equals(currentPenColor)) {
            this.addPenColor(this.currentPenColor = currentPenColor);
            if (this.showTurtle) {
                this.colorTurtle();
            }
            this.repaint();
        }
    }
    
    public void setpencolor(final int n) {
        this.setpc(n);
    }
    
    public void setpensize(final int currentPenSize) {
        if (currentPenSize == this.currentPenSize) {
            return;
        }
        if (currentPenSize <= 1) {
            this.currentPenSize = 1;
        }
        else if (currentPenSize >= 20) {
            this.currentPenSize = 20;
        }
        else {
            this.currentPenSize = currentPenSize;
        }
        this.addPenSize(currentPenSize);
    }
    
    public int toTurtleSpaceX(final int n) {
        return n - this.xCenter;
    }
    
    public int toTurtleSpaceY(final int n) {
        return -(n - this.yCenter);
    }
    
    static {
        (colors = new Color[16])[0] = Color.black;
        TurtleGraphics.colors[1] = Color.blue;
        TurtleGraphics.colors[2] = Color.green;
        TurtleGraphics.colors[3] = Color.cyan;
        TurtleGraphics.colors[4] = Color.red;
        TurtleGraphics.colors[5] = Color.magenta;
        TurtleGraphics.colors[6] = Color.yellow;
        TurtleGraphics.colors[7] = Color.white;
        TurtleGraphics.colors[8] = new Color(155, 96, 59);
        TurtleGraphics.colors[9] = new Color(197, 136, 18);
        TurtleGraphics.colors[10] = new Color(100, 162, 64);
        TurtleGraphics.colors[11] = new Color(120, 187, 187);
        TurtleGraphics.colors[12] = new Color(255, 149, 119);
        TurtleGraphics.colors[13] = new Color(144, 113, 208);
        TurtleGraphics.colors[14] = Color.orange;
        TurtleGraphics.colors[15] = Color.lightGray;
        initialForeground = Color.black;
        initialBackground = Color.white;
        initialFont = new Font("SansSerif", 0, 14);
    }
    
    class Point
    {
        int x;
        int y;
        
        Point() {
            this.x = 0;
            this.y = 0;
        }
        
        Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    class Line
    {
        double heading;
        double length;
        int penSize;
        Point p1;
        Point p2;
        
        Line(final Point p5, final Point p6, final double heading, final double length) {
            this.p1 = p5;
            this.p2 = p6;
            this.heading = heading;
            this.length = length;
            this.penSize = TurtleGraphics.this.currentPenSize;
        }
    }
    
    class Pen
    {
        Color color;
        int size;
        
        Pen(final int size, final Color color) {
            this.size = size;
            this.color = color;
        }
    }
    
    class Label
    {
        int xPos;
        int yPos;
        Font font;
        String text;
        
        Label(final int xPos, final int yPos, final Font font, final String text) {
            this.xPos = xPos;
            this.yPos = yPos;
            this.font = font;
            this.text = text;
        }
    }
}
