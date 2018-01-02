import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class Turtle extends Component
{
    public static final int NORTH = 0;
    public static final int EAST = 90;
    public static final int SOUTH = 180;
    public static final int WEST = 270;
    public static final int BLACK = 0;
    public static final int BLUE = 1;
    public static final int GREEN = 2;
    public static final int CYAN = 3;
    public static final int RED = 4;
    public static final int MAGENTA = 5;
    public static final int YELLOW = 6;
    public static final int WHITE = 7;
    public static final int BROWN = 8;
    public static final int TAN = 9;
    public static final int FOREST = 10;
    public static final int AQUA = 11;
    public static final int SALMON = 12;
    public static final int VIOLET = 13;
    public static final int ORANGE = 14;
    public static final int GRAY = 15;
    private static final Color[] COLORS;
    private static final int TURTLE_HEIGHT = 21;
    private static final int TURTLE_WIDTH = 15;
    private static final int BLACK_OPAQUE_PIXEL = -16777216;
    private static final int PIXEL_OPACITY_BITS = -16777216;
    private static final int WHITE_OPAQUE_PIXEL = -1;
    private static final int INITIAL_PEN_SIZE = 2;
    private static final int INITIAL_FONT_SIZE = 14;
    private static final int INITIAL_FONT_STYLE = 0;
    private static final String INITIAL_FONT_NAME = "Courier";
    private static final Font INITIAL_FONT;
    private static final Color INITIAL_FOREGROUND;
    private boolean penDown;
    private boolean showTurtle;
    private boolean[] turtleFillMask;
    private Color currentPenColor;
    private int currentPenSize;
    private int turtleSideSize;
    private int[] baseTurtlePixels;
    private int[] turtlePixels;
    private float currentHeading;
    private Font currentFont;
    private Image turtleImage;
    private MemoryImageSource turtleImageProducer;
    private TGCanvas tgc;
    private TGPoint currentPoint;
    
    public Turtle(final TGCanvas tgc) {
        this.currentFont = Turtle.INITIAL_FONT;
        this.tgc = tgc;
        this.currentFont = Turtle.INITIAL_FONT;
        this.currentHeading = 1.5707964f;
        this.currentPenColor = Turtle.INITIAL_FOREGROUND;
        this.currentPenSize = 2;
        this.currentPoint = new TGPoint(0, 0);
        this.penDown = true;
        this.showTurtle = true;
        this.initTurtlePixels();
        tgc.addTurtle(this);
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
    
    private void colorTurtle(final Color color) {
        final int n = (int)Math.rint(this.currentHeading / 0.017453292519943295);
        final int rgb = color.getRGB();
        for (int i = 0; i < this.baseTurtlePixels.length; ++i) {
            if (this.turtleFillMask[i]) {
                this.baseTurtlePixels[i] = (this.baseTurtlePixels[i] & 0xFF000000) + rgb;
            }
        }
        this.updateTurtlePixels(n);
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
    
    private void initTurtlePixels() {
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
        this.updateTurtlePixels(90);
    }
    
    private void printPixels(final String s, final int[] array, final int n, final int n2) {
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                final int n3 = i * n + j;
                System.out.println(s + "[" + n3 + "] a=" + (array[n3] >> 24 & 0xFF) + ", r=" + (array[n3] >> 16 & 0xFF) + ", g=" + (array[n3] >> 8 & 0xFF) + ", b=" + (array[n3] & 0xFF));
            }
            System.out.println("----------");
        }
    }
    
    private void updateTurtlePixels(final int n) {
        if (this.turtlePixels == null) {
            this.turtlePixels = new int[this.turtleSideSize * this.turtleSideSize];
        }
        if (n == 0) {
            for (int i = 0; i < this.turtleSideSize * this.turtleSideSize; ++i) {
                this.turtlePixels[i] = this.baseTurtlePixels[i];
            }
        }
        else {
            final int n2 = this.turtleSideSize / 2;
            final double n3 = 0.017453292519943295 * (360 - n);
            final double cos = Math.cos(n3);
            final double sin = Math.sin(n3);
            final double n4 = -n2 * cos - n2 * sin + n2;
            final double n5 = n2 * sin - n2 * cos + n2;
            for (int j = 0; j < this.turtleSideSize; ++j) {
                for (int k = 0; k < this.turtleSideSize; ++k) {
                    this.turtlePixels[j * this.turtleSideSize + k] = 0;
                }
            }
            for (int l = 0; l < this.turtleSideSize; ++l) {
                for (int n6 = 0; n6 < this.turtleSideSize; ++n6) {
                    final int n7 = (int)Math.rint(n6 * cos + l * sin + n4);
                    final int n8 = (int)Math.rint(-n6 * sin + l * cos + n5);
                    if (n7 >= 0) {
                        if (n7 < this.turtleSideSize) {
                            if (n8 >= 0) {
                                if (n8 < this.turtleSideSize) {
                                    this.turtlePixels[l * this.turtleSideSize + n6] = this.baseTurtlePixels[n8 * this.turtleSideSize + n7];
                                }
                            }
                        }
                    }
                }
            }
        }
        this.turtleImage = null;
    }
    
    public void back(final double n) {
        this.bk(n);
    }
    
    public void back(final float n) {
        this.bk((double)n);
    }
    
    public void back(final int n) {
        this.bk((double)n);
    }
    
    public void bk(final float n) {
        this.bk((double)n);
    }
    
    public void bk(final int n) {
        this.bk((double)n);
    }
    
    public void bk(final double n) {
        if (this.penDown) {
            this.currentPoint = this.tgc.drawLine(this.currentPoint, -n, this.currentHeading, this.currentPenSize, this.currentPenColor);
        }
        else {
            this.currentPoint = this.tgc.otherEndPoint(this.currentPoint, -n, this.currentHeading);
        }
        if (this.penDown || this.showTurtle) {
            this.tgc.repaint();
        }
    }
    
    public void fill() {
        this.tgc.fill(this.currentPoint, this.currentPenColor);
        this.tgc.repaint();
    }
    
    public void fd(final float n) {
        this.fd((double)n);
    }
    
    public void fd(final int n) {
        this.fd((double)n);
    }
    
    public void fd(final double n) {
        if (this.penDown) {
            this.currentPoint = this.tgc.drawLine(this.currentPoint, n, this.currentHeading, this.currentPenSize, this.currentPenColor);
        }
        else {
            this.currentPoint = this.tgc.otherEndPoint(this.currentPoint, n, this.currentHeading);
        }
        if (this.penDown || this.showTurtle) {
            this.tgc.repaint();
        }
    }
    
    public void forward(final double n) {
        this.fd(n);
    }
    
    public void forward(final float n) {
        this.fd((double)n);
    }
    
    public void forward(final int n) {
        this.fd((double)n);
    }
    
    public Image getImage() {
        if (this.turtleImage == null) {
            if (this.turtleImageProducer == null) {
                this.turtleImageProducer = new MemoryImageSource(this.turtleSideSize, this.turtleSideSize, this.turtlePixels, 0, this.turtleSideSize);
            }
            this.turtleImage = this.createImage(this.turtleImageProducer);
        }
        return this.turtleImage;
    }
    
    public int getImageSideSize() {
        return this.turtleSideSize;
    }
    
    public int getpencolor() {
        for (int i = 0; i < Turtle.COLORS.length; ++i) {
            if (this.currentPenColor.equals(Turtle.COLORS[i])) {
                return i;
            }
        }
        return this.currentPenColor.getRGB();
    }
    
    public int getpensize() {
        return this.currentPenSize;
    }
    
    public int heading() {
        int n = 360 - (int)Math.rint(this.currentHeading / 0.017453292519943295);
        n += 90;
        return n % 360;
    }
    
    public void home() {
        this.setxy(0, 0);
    }
    
    public void ht() {
        this.showTurtle = false;
        this.turtlePixels = null;
        this.turtleImage = null;
        this.turtleImageProducer = null;
        this.tgc.removeTurtle(this);
        this.tgc.repaint();
    }
    
    public void hideturtle() {
        this.ht();
    }
    
    public boolean ispendown() {
        return this.penDown;
    }
    
    public void label(final String s) {
        if (s != null) {
            this.tgc.label(s, this.currentPoint, this.currentFont, this.currentPenColor);
            this.tgc.repaint();
        }
    }
    
    public void left(final float n) {
        this.lt(n);
    }
    
    public void left(final int n) {
        this.lt((float)n);
    }
    
    public void lt(final float n) {
        this.lt((double)n);
    }
    
    public void lt(final int n) {
        this.lt((double)n);
    }
    
    public void lt(final double n) {
        this.currentHeading += (float)(n * 0.017453292519943295);
        if (this.currentHeading > 6.283185307179586) {
            this.currentHeading -= 6.283185307179586;
        }
        if (this.showTurtle) {
            this.updateTurtlePixels((int)Math.rint(this.currentHeading / 0.017453292519943295));
            this.tgc.repaint();
        }
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
    
    public void right(final double n) {
        this.rt(n);
    }
    
    public void right(final float n) {
        this.rt((double)n);
    }
    
    public void right(final int n) {
        this.rt((double)n);
    }
    
    public void rt(final float n) {
        this.rt((double)n);
    }
    
    public void rt(final int n) {
        this.rt((double)n);
    }
    
    public void rt(final double n) {
        this.currentHeading -= (float)(n * 0.017453292519943295);
        if (this.currentHeading < 0.0f) {
            this.currentHeading += 6.283185307179586;
        }
        if (this.showTurtle) {
            this.updateTurtlePixels((int)Math.rint(this.currentHeading / 0.017453292519943295));
            this.tgc.repaint();
        }
    }
    
    public void setLabelHeight(final int n) {
        if (this.currentFont.getSize() != n) {
            this.currentFont = new Font("Courier", 0, n);
        }
    }
    
    public void seth(final int n) {
        int n2 = 360 - n % 360;
        n2 += 90;
        final int n3 = n2 % 360;
        this.currentHeading = (float)(n3 * 0.017453292519943295);
        if (this.showTurtle) {
            this.updateTurtlePixels(n3);
            this.tgc.repaint();
        }
    }
    
    public void setheading(final int n) {
        this.seth(n);
    }
    
    public void setpc(int n) {
        Color currentPenColor;
        if (n >= 0 && n <= 15) {
            currentPenColor = Turtle.COLORS[n];
        }
        else {
            n &= 0x7FFFFFF;
            currentPenColor = new Color(n);
        }
        if (this.currentPenColor.getRGB() != currentPenColor.getRGB()) {
            this.currentPenColor = currentPenColor;
            if (this.showTurtle) {
                this.colorTurtle(currentPenColor);
                this.tgc.repaint();
            }
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
    }
    
    public void setx(final int n) {
        final TGPoint currentPoint = new TGPoint(n, this.currentPoint.y);
        if (this.penDown) {
            this.tgc.drawLine(this.currentPoint, currentPoint, this.currentPenSize, this.currentPenColor);
        }
        this.currentPoint = currentPoint;
        if (this.penDown || this.showTurtle) {
            this.tgc.repaint();
        }
    }
    
    public void setxy(final int n, final int n2) {
        final TGPoint currentPoint = new TGPoint(n, n2);
        if (this.penDown) {
            this.tgc.drawLine(this.currentPoint, currentPoint, this.currentPenSize, this.currentPenColor);
        }
        this.currentPoint = currentPoint;
        if (this.penDown || this.showTurtle) {
            this.tgc.repaint();
        }
    }
    
    public void sety(final int n) {
        final TGPoint currentPoint = new TGPoint(this.currentPoint.x, n);
        if (this.penDown) {
            this.tgc.drawLine(this.currentPoint, currentPoint, this.currentPenSize, this.currentPenColor);
        }
        this.currentPoint = currentPoint;
        if (this.penDown || this.showTurtle) {
            this.tgc.repaint();
        }
    }
    
    public void showturtle() {
        this.st();
    }
    
    public void st() {
        this.showTurtle = true;
        this.colorTurtle(this.currentPenColor);
        this.tgc.addTurtle(this);
        this.tgc.repaint();
    }
    
    public int xcor() {
        return (int)Math.rint(this.currentPoint.x);
    }
    
    public int ycor() {
        return (int)Math.rint(this.currentPoint.y);
    }
    
    static {
        (COLORS = new Color[16])[0] = Color.black;
        Turtle.COLORS[1] = Color.blue;
        Turtle.COLORS[2] = Color.green;
        Turtle.COLORS[3] = Color.cyan;
        Turtle.COLORS[4] = Color.red;
        Turtle.COLORS[5] = Color.magenta;
        Turtle.COLORS[6] = Color.yellow;
        Turtle.COLORS[7] = Color.white;
        Turtle.COLORS[8] = new Color(155, 96, 59);
        Turtle.COLORS[9] = new Color(197, 136, 18);
        Turtle.COLORS[10] = new Color(100, 162, 64);
        Turtle.COLORS[11] = new Color(120, 187, 187);
        Turtle.COLORS[12] = new Color(255, 149, 119);
        Turtle.COLORS[13] = new Color(144, 113, 208);
        Turtle.COLORS[14] = Color.orange;
        Turtle.COLORS[15] = Color.lightGray;
        INITIAL_FONT = new Font("Courier", 0, 14);
        INITIAL_FOREGROUND = Color.black;
    }
}
