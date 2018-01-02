import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Draw extends Applet implements TGMouseHandler
{
    private static final int CANVAS_HEIGHT = 400;
    private static final int CANVAS_WIDTH = 600;
    static final int COLOR_BOX_SIZE = 20;
    static final int COLORS_TOP_Y = 160;
    static final int COLORS_CENTER_X = 265;
    static final int COLORS_LEFT_X = 255;
    static final int[] colorChoices;
    static final int COLORS_HEIGHT;
    static final int COLORS_BOTTOM_Y;
    private static final int BUTTON_FONT_SIZE = 14;
    private static final int BUTTON_WIDTH = 65;
    private static final int BUTTON_HEIGHT = 24;
    private static final int BUTTON_TEXT_X_OFFSET = 4;
    private static final int BUTTON_TEXT_Y_OFFSET = 8;
    private static final int CLEAR_LEFT_X = -290;
    private static final int CLEAR_BOTTOM_Y = -190;
    private static final int FILL_LEFT_X = -215;
    private static final int FILL_BOTTOM_Y = -190;
    private static final int PUD_LEFT_X = -140;
    private static final int PUD_BOTTOM_Y = -190;
    private int currentColor;
    private TGCanvas canvas;
    private Turtle turtle;
    
    public void init() {
        this.setLayout(new BorderLayout());
        (this.canvas = new TGCanvas(600, 400)).setBackground(Color.white);
        this.add("Center", this.canvas);
        this.canvas.addMouseHandler(this);
        this.turtle = new Turtle(this.canvas);
    }
    
    public void start() {
        this.doClear();
    }
    
    public void stop() {
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawRect(0, 0, 599, 399);
    }
    
    public void mouseClick() {
        if (this.isMouseInRect(-290, -190, 24, 65)) {
            this.doClear();
        }
        if (this.isMouseInRect(-215, -190, 24, 65)) {
            this.doFill();
        }
        else if (this.isMouseInRect(-140, -190, 24, 65)) {
            this.flipPenState();
        }
        else if (this.isMouseInRect(255, Draw.COLORS_BOTTOM_Y, Draw.COLORS_HEIGHT, 20)) {
            this.setCurrentColor(this.mouseYtoColorNum());
        }
        else {
            this.turtle.setxy(this.canvas.mousex(), this.canvas.mousey());
        }
    }
    
    private void buttonAt(final int n, final int n2, final String s) {
        final int xcor = this.turtle.xcor();
        final int ycor = this.turtle.ycor();
        this.eraseButtonAt(n, n2);
        this.turtle.setpencolor(0);
        this.frameAt(n, n2, 24, 65, 2);
        this.turtle.penup();
        this.turtle.setxy(n + 4, n2 + 8);
        this.turtle.pendown();
        this.turtle.setlabelheight(14);
        this.turtle.label(s);
        this.turtle.penup();
        this.turtle.setxy(xcor, ycor);
    }
    
    private void doClear() {
        this.canvas.clean();
        this.drawColorChoices();
        this.buttonAt(-290, -190, "Clear");
        this.buttonAt(-215, -190, "Fill");
        this.setCurrentColor(0);
        this.turtle.pendown();
        this.flipPenState();
        this.turtle.home();
    }
    
    private void doFill() {
        this.turtle.fill();
    }
    
    private void drawColorChoices() {
        this.turtle.setpensize(20);
        this.turtle.penup();
        this.turtle.setxy(265, 160);
        this.turtle.pendown();
        this.turtle.setheading(180);
        for (int i = 0; i < Draw.colorChoices.length; ++i) {
            this.turtle.setpencolor(Draw.colorChoices[i]);
            this.turtle.forward(20);
        }
    }
    
    void drawRectangle(final int n, final int n2, final int n3, final int n4) {
        this.turtle.penup();
        this.turtle.setxy(n, n2);
        this.turtle.setheading(90);
        this.turtle.pendown();
        for (int i = 2; i > 0; --i) {
            this.turtle.forward(n3);
            this.turtle.right(90);
            this.turtle.forward(n4);
            this.turtle.right(90);
        }
    }
    
    void drawSquare(final int n, final int n2, final int n3) {
        this.turtle.penup();
        this.turtle.setxy(n, n2);
        this.turtle.setheading(90);
        this.turtle.pendown();
        for (int i = 4; i > 0; --i) {
            this.turtle.forward(n3);
            this.turtle.right(90);
        }
    }
    
    private void eraseButtonAt(final int n, final int n2) {
        final int n3 = 4;
        this.turtle.penup();
        this.turtle.setxy(n - n3, n2 + 12);
        this.turtle.pendown();
        this.turtle.setpencolor(7);
        this.turtle.setpensize(24 + 2 * n3);
        this.turtle.setheading(90);
        this.turtle.forward(65 + 2 * n3);
    }
    
    private void flipPenState() {
        if (this.turtle.ispendown()) {
            this.buttonAt(-140, -190, "PenDown");
            this.turtle.setpencolor(7);
            this.turtle.penup();
        }
        else {
            this.buttonAt(-140, -190, "PenUp");
            this.turtle.setpencolor(this.currentColor);
            this.turtle.pendown();
        }
    }
    
    private void frameAt(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.turtle.penup();
        this.turtle.setxy(n, n2);
        this.turtle.pendown();
        this.turtle.setpensize(n5);
        this.turtle.setpencolor(0);
        this.turtle.setheading(0);
        for (int i = 2; i > 0; --i) {
            this.turtle.forward(n3);
            this.turtle.right(90);
            this.turtle.forward(n4);
            this.turtle.right(90);
        }
    }
    
    private boolean isMouseInRect(final int n, final int n2, final int n3, final int n4) {
        final int mousex = this.canvas.mousex();
        final int mousey = this.canvas.mousey();
        return mousex >= n && mousey >= n2 && mousex < n + n4 && mousey < n2 + n3;
    }
    
    private int mouseYtoColorNum() {
        return (160 - this.canvas.mousey()) / 20;
    }
    
    private void setCurrentColor(final int currentColor) {
        final boolean ispendown = this.turtle.ispendown();
        final int xcor = this.turtle.xcor();
        final int ycor = this.turtle.ycor();
        this.turtle.setpensize(20);
        this.turtle.setpencolor(this.currentColor);
        this.turtle.setheading(180);
        this.turtle.penup();
        this.turtle.setxy(265, 160);
        for (int i = 0; i < Draw.colorChoices.length; ++i) {
            if (Draw.colorChoices[i] == this.currentColor) {
                this.turtle.pendown();
                this.turtle.forward(20);
                break;
            }
            this.turtle.forward(20);
        }
        final int n = 256;
        this.turtle.setpencolor(0);
        for (int j = 0; j < Draw.colorChoices.length; ++j) {
            if (Draw.colorChoices[j] == currentColor) {
                final int n2 = 160 - 20 * j - 1;
                this.turtle.setpensize(2);
                this.drawSquare(n, n2, 18);
            }
        }
        this.currentColor = currentColor;
        this.turtle.penup();
        this.turtle.setxy(xcor, ycor);
        this.turtle.setheading(0);
        if (ispendown) {
            this.turtle.pendown();
            this.turtle.setpencolor(this.currentColor);
        }
        else {
            this.turtle.setpencolor(7);
        }
    }
    
    static {
        colorChoices = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        COLORS_HEIGHT = 20 * Draw.colorChoices.length;
        COLORS_BOTTOM_Y = 160 - Draw.COLORS_HEIGHT;
    }
}
