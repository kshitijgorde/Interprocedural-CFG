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
    public static final int TURTLE_IMAGE = 0;
    public static final int ISOTRI_IMAGE = 1;
    public static final int RECTANGLE_IMAGE = 2;
    private static final int INITIAL_PEN_SIZE = 2;
    private static final int INITIAL_FONT_SIZE = 14;
    private static final int INITIAL_FONT_STYLE = 0;
    private static final String INITIAL_FONT_NAME = "Courier";
    private static final Font INITIAL_FONT;
    private static final Color INITIAL_FOREGROUND;
    private boolean penDown;
    private boolean showTurtle;
    private Color currentPenColor;
    private int currentPenSize;
    private int[] turtlePixels;
    private float currentHeading;
    private Font currentFont;
    private Image turtleImage;
    private MemoryImageSource turtleImageProducer;
    private TGCanvas tgc;
    private TGPoint currentPoint;
    private TurtlePixels currentTurtlePixels;
    
    public Turtle(final TGCanvas tgc) {
        this.currentFont = Turtle.INITIAL_FONT;
        this.tgc = tgc;
        this.currentFont = Turtle.INITIAL_FONT;
        this.currentPenColor = Turtle.INITIAL_FOREGROUND;
        this.currentPenSize = 2;
        this.currentPoint = new TGPoint(0, 0);
        this.currentHeading = 1.5707964f;
        this.currentTurtlePixels = new TurtleTurtle(this.currentHeading);
        this.penDown = true;
        tgc.addTurtle(this);
        this.showTurtle = true;
    }
    
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.showTurtle) {
            this.tgc.removeTurtle(this);
        }
        this.showTurtle = false;
    }
    
    private int rgbToPencolor(int n) {
        n &= 0xFFFFFF;
        for (int i = 0; i < Turtle.COLORS.length; ++i) {
            if ((Turtle.COLORS[i].getRGB() & 0xFFFFFF) == n) {
                return i;
            }
        }
        return n;
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
            this.currentPoint = this.currentPoint.otherEndPoint(this.currentHeading, -n);
        }
        if (this.penDown || this.showTurtle) {
            this.tgc.tgDoRepaint();
        }
    }
    
    public int colorunder() {
        return this.rgbToPencolor(this.tgc.colorunder(this.currentPoint));
    }
    
    public void fill() {
        this.tgc.fill(this.currentPoint, this.currentPenColor);
        this.tgc.tgDoRepaint();
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
            this.currentPoint = this.currentPoint.otherEndPoint(this.currentHeading, n);
        }
        if (this.penDown || this.showTurtle) {
            this.tgc.tgDoRepaint();
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
            if (this.turtlePixels == null) {
                this.turtlePixels = this.currentTurtlePixels.getPixels();
            }
            final int sideSize = this.currentTurtlePixels.getSideSize();
            if (this.turtleImageProducer == null) {
                this.turtleImageProducer = new MemoryImageSource(sideSize, sideSize, this.turtlePixels, 0, sideSize);
            }
            this.turtleImage = this.createImage(this.turtleImageProducer);
        }
        return this.turtleImage;
    }
    
    public int getImageSideSize() {
        return this.currentTurtlePixels.getSideSize();
    }
    
    public int pensize() {
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
        if (this.showTurtle) {
            this.turtlePixels = null;
            this.turtleImage = null;
            this.turtleImageProducer = null;
            this.tgc.removeTurtle(this);
            this.tgc.tgDoRepaint();
            this.showTurtle = false;
        }
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
            this.tgc.tgDoRepaint();
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
            this.currentTurtlePixels.setTurtleHeading(this.currentHeading);
            this.turtleImage = null;
            this.tgc.tgDoRepaint();
        }
    }
    
    public void pd() {
        this.penDown = true;
    }
    
    public int pencolor() {
        return this.rgbToPencolor(this.currentPenColor.getRGB());
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
            this.currentTurtlePixels.setTurtleHeading(this.currentHeading);
            this.turtleImage = null;
            this.tgc.tgDoRepaint();
        }
    }
    
    public void setlabelheight(final int n) {
        if (this.currentFont.getSize() != n) {
            this.currentFont = new Font("Courier", 0, n);
        }
    }
    
    public void seth(final int n) {
        int n2 = 360 - n % 360;
        n2 += 90;
        final float currentHeading = (float)(n2 % 360 * 0.017453292519943295);
        if (currentHeading != this.currentHeading) {
            this.currentHeading = currentHeading;
            if (this.showTurtle) {
                this.currentTurtlePixels.setTurtleHeading(this.currentHeading);
                this.turtleImage = null;
                this.tgc.tgDoRepaint();
            }
        }
    }
    
    public void setheading(final int n) {
        this.seth(n);
    }
    
    public void setpc(int n) {
        Color color;
        if (n >= 0 && n <= 15) {
            color = Turtle.COLORS[n];
        }
        else {
            n &= 0x7FFFFFF;
            color = new Color(n);
        }
        if (this.currentPenColor.getRGB() != color.getRGB()) {
            this.currentPenColor = color;
            if (this.showTurtle) {
                this.currentTurtlePixels.setTurtleColor(color);
                this.turtleImage = null;
                this.tgc.tgDoRepaint();
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
        final TGPoint currentPoint = new TGPoint(n, this.currentPoint.yFloatValue());
        if (this.penDown) {
            this.tgc.drawLine(this.currentPoint, currentPoint, this.currentPenSize, this.currentPenColor);
        }
        this.currentPoint = currentPoint;
        if (this.penDown || this.showTurtle) {
            this.tgc.tgDoRepaint();
        }
    }
    
    public void setxy(final int n, final int n2) {
        final TGPoint currentPoint = new TGPoint(n, n2);
        if (this.penDown) {
            this.tgc.drawLine(this.currentPoint, currentPoint, this.currentPenSize, this.currentPenColor);
        }
        this.currentPoint = currentPoint;
        if (this.penDown || this.showTurtle) {
            this.tgc.tgDoRepaint();
        }
    }
    
    public void sety(final int n) {
        final TGPoint currentPoint = new TGPoint(this.currentPoint.xFloatValue(), n);
        if (this.penDown) {
            this.tgc.drawLine(this.currentPoint, currentPoint, this.currentPenSize, this.currentPenColor);
        }
        this.currentPoint = currentPoint;
        if (this.penDown || this.showTurtle) {
            this.tgc.tgDoRepaint();
        }
    }
    
    public void showturtle() {
        this.st();
    }
    
    public void st() {
        if (!this.showTurtle) {
            this.currentTurtlePixels.setTurtleColor(this.currentPenColor);
            this.currentTurtlePixels.setTurtleHeading(this.currentHeading);
            this.turtleImage = null;
            this.tgc.addTurtle(this);
            this.tgc.tgDoRepaint();
            this.showTurtle = true;
        }
    }
    
    public int xcor() {
        return this.currentPoint.xIntValue();
    }
    
    public int ycor() {
        return this.currentPoint.yIntValue();
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
