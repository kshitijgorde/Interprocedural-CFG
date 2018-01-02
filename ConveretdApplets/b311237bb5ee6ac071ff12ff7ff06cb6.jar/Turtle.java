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
    public static final int TURTLE = 0;
    public static final int ARROW = 1;
    public static final int BALL = 2;
    public static final int BOX = 3;
    public static final int CROSS = 4;
    public static final int TRIANGLE = 5;
    private static final Color[] COLORS;
    private static final int INITIAL_PEN_SIZE = 2;
    private static final int INITIAL_FONT_SIZE = 14;
    private static final int INITIAL_FONT_STYLE = 0;
    private static final String INITIAL_FONT_NAME = "Courier";
    private static final Font INITIAL_FONT;
    private static final Color INITIAL_FOREGROUND;
    private boolean penDown;
    private boolean showTurtle;
    private Color curColor;
    private float curHeading;
    private Font curFont;
    private Image turtleImage;
    private int curPenSize;
    private MemoryImageSource turtleImageProducer;
    private TGCanvas tgc;
    private TGPoint curPoint;
    private TurtlePixels curTurtlePixels;
    
    public Turtle(final TGCanvas tgc) {
        this.curFont = Turtle.INITIAL_FONT;
        this.tgc = tgc;
        this.curFont = Turtle.INITIAL_FONT;
        this.curColor = Turtle.INITIAL_FOREGROUND;
        this.curPenSize = 2;
        this.curPoint = new TGPoint(0, 0);
        this.curHeading = 1.5707964f;
        this.curTurtlePixels = new TurtleTurtle(this.curColor, this.curHeading);
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
    
    private double getRadiansTwds(final TGPoint tgPoint, final TGPoint tgPoint2) {
        double atan = 0.0;
        final double n = tgPoint.xFloatValue();
        final double n2 = tgPoint.yFloatValue();
        final double n3 = tgPoint2.xFloatValue();
        final double n4 = tgPoint2.yFloatValue();
        if (n != n3 || n2 != n4) {
            if (n == n3) {
                atan = ((n2 < n4) ? 1.5707963267948966 : 4.71238898038469);
            }
            else {
                atan = Math.atan((n4 - n2) / (n3 - n));
                if (atan == -0.0) {
                    atan = 0.0;
                }
                if (n > n3) {
                    atan += 3.141592653589793;
                }
                if (atan < 0.0) {
                    atan += 6.283185307179586;
                }
            }
        }
        return atan;
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
            this.curPoint = this.tgc.drawLine(this.curPoint, -n, this.curHeading, this.curPenSize, this.curColor);
        }
        else {
            this.curPoint = this.curPoint.otherEndPoint(this.curHeading, -n);
        }
        if (this.penDown || this.showTurtle) {
            this.tgc.repaint();
        }
    }
    
    public int colorunder() {
        return this.rgbToPencolor(this.tgc.colorunder(this.curPoint));
    }
    
    public void fill() {
        this.tgc.fill(this.curPoint, this.curColor);
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
            this.curPoint = this.tgc.drawLine(this.curPoint, n, this.curHeading, this.curPenSize, this.curColor);
        }
        else {
            this.curPoint = this.curPoint.otherEndPoint(this.curHeading, n);
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
        while (this.turtleImage == null) {
            final int[] pixels = this.curTurtlePixels.getPixels();
            final int sideSize = this.curTurtlePixels.getSideSize();
            if (this.turtleImageProducer == null) {
                this.turtleImageProducer = new MemoryImageSource(sideSize, sideSize, pixels, 0, sideSize);
            }
            this.turtleImage = this.createImage(this.turtleImageProducer);
        }
        return this.turtleImage;
    }
    
    public int getImageSideSize() {
        return this.curTurtlePixels.getSideSize();
    }
    
    public int pensize() {
        return this.curPenSize;
    }
    
    public int heading() {
        int n = 360 - (int)Math.rint(this.curHeading / 0.017453292519943295);
        n += 90;
        return n % 360;
    }
    
    public void home() {
        this.setxy(0, 0);
    }
    
    public void ht() {
        if (this.showTurtle) {
            this.tgc.removeTurtle(this);
            this.tgc.repaint();
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
            this.tgc.label(s, this.curPoint, this.curFont, this.curColor);
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
        this.curHeading += (float)(n * 0.017453292519943295);
        if (this.curHeading > 6.283185307179586) {
            this.curHeading -= 6.283185307179586;
        }
        if (this.showTurtle && this.curTurtlePixels.setTurtleHeading(this.curHeading)) {
            this.turtleImage = null;
            this.tgc.repaint();
        }
    }
    
    public void pd() {
        this.penDown = true;
    }
    
    public int pencolor() {
        return this.rgbToPencolor(this.curColor.getRGB());
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
    
    public static Color rgbToColor(int n) {
        n &= 0xFFFFFF;
        if (n < Turtle.COLORS.length) {
            return Turtle.COLORS[n];
        }
        return new Color(n);
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
        this.curHeading -= (float)(n * 0.017453292519943295);
        if (this.curHeading < 0.0f) {
            this.curHeading += 6.283185307179586;
        }
        if (this.showTurtle && this.curTurtlePixels.setTurtleHeading(this.curHeading)) {
            this.turtleImage = null;
            this.tgc.repaint();
        }
    }
    
    public void setlabelheight(final int n) {
        if (this.curFont.getSize() != n) {
            this.curFont = new Font("Courier", 0, n);
        }
    }
    
    public void seth(final int n) {
        int n2 = 360 - n % 360;
        n2 += 90;
        this.curHeading = (float)(n2 % 360 * 0.017453292519943295);
        if (this.showTurtle && this.curTurtlePixels.setTurtleHeading(this.curHeading)) {
            this.turtleImage = null;
            this.tgc.repaint();
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
            n &= 0xFFFFFF;
            color = new Color(n);
        }
        if (this.curColor.getRGB() != color.getRGB()) {
            this.curColor = color;
            if (this.showTurtle && this.curTurtlePixels.setTurtleColor(color)) {
                this.turtleImage = null;
                this.tgc.repaint();
            }
        }
    }
    
    public void setpencolor(final int n) {
        this.setpc(n);
    }
    
    public void setpensize(final int curPenSize) {
        if (curPenSize == this.curPenSize) {
            return;
        }
        if (curPenSize < 1) {
            this.curPenSize = 1;
        }
        else {
            this.curPenSize = curPenSize;
        }
    }
    
    public void setshape(final int n, final int[] array) {
        TurtlePixels curTurtlePixels = null;
        int n2 = 0;
        if (array != null) {
            n2 = array[0];
        }
        int n3 = n2;
        if (array != null && array.length >= 2) {
            n3 = array[1];
        }
        switch (n) {
            case 0: {
                curTurtlePixels = new TurtleTurtle(this.curColor, this.curHeading);
                break;
            }
            case 1: {
                if (array != null) {
                    curTurtlePixels = new ArrowTurtle(n2, n3, this.curColor, this.curHeading);
                    break;
                }
                curTurtlePixels = new ArrowTurtle(this.curColor, this.curHeading);
                break;
            }
            case 2: {
                if (array != null) {
                    curTurtlePixels = new BallTurtle(n2, this.curColor, this.curHeading);
                    break;
                }
                curTurtlePixels = new BallTurtle(this.curColor, this.curHeading);
                break;
            }
            case 3: {
                if (array != null) {
                    curTurtlePixels = new BoxTurtle(n2, n3, this.curColor, this.curHeading);
                    break;
                }
                curTurtlePixels = new BoxTurtle(this.curColor, this.curHeading);
                break;
            }
            case 4: {
                if (array != null) {
                    curTurtlePixels = new CrossTurtle(n2, n3, this.curColor, this.curHeading);
                    break;
                }
                curTurtlePixels = new CrossTurtle(this.curColor, this.curHeading);
                break;
            }
            case 5: {
                curTurtlePixels = new TriangleTurtle(this.curColor, this.curHeading);
                break;
            }
        }
        if (curTurtlePixels != null) {
            this.curTurtlePixels = curTurtlePixels;
            this.turtleImage = null;
            this.turtleImageProducer = null;
            this.tgc.repaint();
        }
    }
    
    public void setx(final int n) {
        this.setx((float)n);
    }
    
    public void setx(final float n) {
        final TGPoint curPoint = new TGPoint(n, this.curPoint.yFloatValue());
        double n2 = 0.0;
        if (n < this.curPoint.xFloatValue()) {
            n2 += 3.141592653589793;
        }
        if (this.penDown) {
            this.tgc.drawLine(this.curPoint, curPoint, n2, this.curPenSize, this.curColor);
        }
        this.curPoint = curPoint;
        if (this.penDown || this.showTurtle) {
            this.tgc.repaint();
        }
    }
    
    public void setxy(final int n, final int n2) {
        this.setxy(new TGPoint(n, n2));
    }
    
    public void setxy(final float n, final float n2) {
        this.setxy(new TGPoint(n, n2));
    }
    
    public void setxy(final TGPoint curPoint) {
        if (this.penDown) {
            this.tgc.drawLine(this.curPoint, curPoint, this.getRadiansTwds(this.curPoint, curPoint), this.curPenSize, this.curColor);
        }
        this.curPoint = curPoint;
        if (this.penDown || this.showTurtle) {
            this.tgc.repaint();
        }
    }
    
    public void sety(final int n) {
        this.sety((float)n);
    }
    
    public void sety(final float n) {
        final TGPoint curPoint = new TGPoint(this.curPoint.xFloatValue(), n);
        double n2 = 1.5707963267948966;
        if (n < this.curPoint.yFloatValue()) {
            n2 += 3.141592653589793;
        }
        if (this.penDown) {
            this.tgc.drawLine(this.curPoint, curPoint, n2, this.curPenSize, this.curColor);
        }
        this.curPoint = curPoint;
        if (this.penDown || this.showTurtle) {
            this.tgc.repaint();
        }
    }
    
    public void showturtle() {
        this.st();
    }
    
    public void st() {
        if (!this.showTurtle) {
            if (this.curTurtlePixels.setTurtleColor(this.curColor)) {
                this.turtleImage = null;
            }
            if (this.curTurtlePixels.setTurtleHeading(this.curHeading)) {
                this.turtleImage = null;
            }
            this.tgc.addTurtle(this);
            this.tgc.repaint();
            this.showTurtle = true;
        }
    }
    
    public float xcor() {
        return this.curPoint.xFloatValue();
    }
    
    public float ycor() {
        return this.curPoint.yFloatValue();
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
