// 
// Decompiled by Procyon v0.5.30
// 

package com.guyhaas.Intro_to_Programming;

import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Vector;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Canvas;

public class TurtleGraphics extends Canvas
{
    private static final int TURTLE_HEIGHT = 21;
    private static final int TURTLE_WIDTH = 15;
    private static final int BLACK_OPAQUE_PIXEL = -16777216;
    private static final int PIXEL_OPACITY_BITS = -16777216;
    private static final int WHITE_OPAQUE_PIXEL = -1;
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
    private boolean[] turtleFillMask;
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
        this.initTurtle();
        this.showTurtle = true;
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
    
    private void floodFill(final int n, final int n2, final int n3) {
        final int n4 = n * this.turtleSideSize + n2;
        if (this.baseTurtlePixels[n4] == n3 && !this.turtleFillMask[n4]) {
            this.turtleFillMask[n4] = true;
            if (n - 1 >= 0) {
                this.floodFill(n - 1, n2, n3);
            }
            if (n + 1 < this.turtleSideSize) {
                this.floodFill(n + 1, n2, n3);
            }
            if (n2 - 1 >= 0) {
                this.floodFill(n, n2 - 1, n3);
            }
            if (n2 + 1 < this.turtleSideSize) {
                this.floodFill(n, n2 + 1, n3);
            }
        }
    }
    
    private void buildTurtleFillMask() {
        final int n = this.turtleSideSize / 2;
        final int n2 = this.turtleSideSize / 2;
        final int n3 = this.baseTurtlePixels[n2 * this.turtleSideSize + n];
        this.turtleFillMask = new boolean[this.turtleSideSize * this.turtleSideSize];
        this.floodFill(n2, n, n3);
    }
    
    private void clearToTurtleEdge() {
        for (int i = 0; i < this.turtleSideSize; ++i) {
            for (int j = 0; j < this.turtleSideSize; ++j) {
                final int n = i * this.turtleSideSize + j;
                if ((this.baseTurtlePixels[n] & 0xFFFFFF) != 0xFFFFFF) {
                    break;
                }
                this.baseTurtlePixels[n] = 0;
            }
        }
        for (int k = 0; k < this.turtleSideSize; ++k) {
            for (int l = this.turtleSideSize - 1; l >= 0; --l) {
                final int n2 = k * this.turtleSideSize + l;
                if ((this.baseTurtlePixels[n2] & 0xFFFFFF) != 0xFFFFFF) {
                    break;
                }
                this.baseTurtlePixels[n2] = 0;
            }
        }
        for (int n3 = 0; n3 < this.turtleSideSize; ++n3) {
            for (int n4 = 0; n4 < this.turtleSideSize; ++n4) {
                final int n5 = n4 * this.turtleSideSize + n3;
                if ((this.baseTurtlePixels[n5] & 0xFFFFFF) != 0xFFFFFF) {
                    break;
                }
                this.baseTurtlePixels[n5] = 0;
            }
        }
        for (int n6 = 0; n6 < this.turtleSideSize; ++n6) {
            for (int n7 = this.turtleSideSize - 1; n7 >= 0; --n7) {
                final int n8 = n7 * this.turtleSideSize + n6;
                if ((this.baseTurtlePixels[n8] & 0xFFFFFF) != 0xFFFFFF) {
                    break;
                }
                this.baseTurtlePixels[n8] = 0;
            }
        }
    }
    
    void colorTurtle() {
        final int rgb = this.currentPenColor.getRGB();
        for (int i = 0; i < this.baseTurtlePixels.length; ++i) {
            if (this.turtleFillMask[i]) {
                this.baseTurtlePixels[i] = (this.baseTurtlePixels[i] & 0xFF000000) + rgb;
            }
        }
        this.renderTurtle();
    }
    
    private void drawDefaultTurtle() {
        final int n = this.turtleSideSize / 2;
        int i = n - 10;
        for (int j = n - 7; j <= n + 7; ++j) {
            this.baseTurtlePixels[j * this.turtleSideSize + i] = -16777216;
        }
        final int n2 = 21;
        final int n3 = 7;
        final int n4 = 2 * n3;
        final int n5 = 2 * (n3 - n2);
        int n6 = 2 * n3 - n2;
        int n7 = n + 7;
        int n8 = n - 7;
        while (i <= n + 10) {
            if (n6 <= 0) {
                n6 += n4;
                ++i;
            }
            else {
                n6 += n5;
                ++i;
                --n7;
                ++n8;
            }
            this.baseTurtlePixels[n7 * this.turtleSideSize + i] = -16777216;
            this.baseTurtlePixels[n8 * this.turtleSideSize + i] = -16777216;
        }
    }
    
    private void initTurtle() {
        this.turtleSideSize = 26;
        if (this.turtleSideSize % 2 == 0) {
            ++this.turtleSideSize;
        }
        this.baseTurtlePixels = new int[this.turtleSideSize * this.turtleSideSize];
        for (int i = 0; i < this.baseTurtlePixels.length; ++i) {
            this.baseTurtlePixels[i] = -1;
        }
        this.drawDefaultTurtle();
        this.clearToTurtleEdge();
        this.buildTurtleFillMask();
        for (int j = 0; j < this.baseTurtlePixels.length; ++j) {
            if (this.turtleFillMask[j]) {
                this.baseTurtlePixels[j] = -16777216;
            }
        }
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
        if (this.currentTurtlePixels == null) {
            this.currentTurtlePixels = new int[this.turtleSideSize * this.turtleSideSize];
        }
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
        if (this.turtleImageProducer == null) {
            this.turtleImageProducer = new MemoryImageSource(this.turtleSideSize, this.turtleSideSize, this.currentTurtlePixels, 0, this.turtleSideSize);
        }
        this.currentTurtleImage = this.createImage(this.turtleImageProducer);
    }
    
    public void paint(final Graphics graphics) {
        this.renderGraphics();
        graphics.drawImage(this.graphicsImage, 0, 0, this);
        if (this.showTurtle) {
            if (this.currentTurtleImage == null) {
                this.renderTurtle();
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
    
    public int getpensize() {
        return this.currentPenSize;
    }
    
    public int heading() {
        int n = 360 - this.currentHeading;
        n += 90;
        return n % 360;
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
    
    public boolean ispendown() {
        return this.penDown;
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
        if (currentPenSize < 1) {
            this.currentPenSize = 1;
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
    
    public int xcor() {
        return this.currentX - this.xCenter;
    }
    
    public int ycor() {
        return -(this.currentY - this.yCenter);
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
