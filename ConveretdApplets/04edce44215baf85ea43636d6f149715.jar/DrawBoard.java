import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.guyhaas.Intro_to_Programming.TurtleGraphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DrawBoard extends Applet implements Runnable
{
    private static final int BLACK = 0;
    private static final int WHITE = 7;
    private static final int LIGHT_GRAY = 15;
    private static final int FONT_SIZE = 24;
    private static final int JAVA_BOX_HEIGHT = 35;
    private static final int JAVA_BOX_WIDTH = 220;
    private static final int JAVA_BOX_X = -110;
    private static final int JAVA_BOX_Y = -190;
    private static final int JAVA_TEXT_X = -90;
    private static final int JAVA_TEXT_Y = -215;
    private static final int LINE_LENGTH = 300;
    private static final int PATH_PEN_SIZE = 7;
    private static final int STEP_SIZE = 10;
    private static final int TURNING_REST_TIME = 10;
    private static final int WALKING_REST_TIME = 2;
    Thread drawingThread;
    TurtleGraphics tgObj;
    
    void displayJavaStmt(final String s) {
        final int xcor = this.tgObj.xcor();
        final int ycor = this.tgObj.ycor();
        final int heading = this.tgObj.heading();
        final int getpencolor = this.tgObj.getpencolor();
        final int getpensize = this.tgObj.getpensize();
        final boolean ispendown = this.tgObj.ispendown();
        this.tgObj.hideturtle();
        this.fillBox(-110, -190, 220, 35, 15);
        this.tgObj.penup();
        this.tgObj.setxy(-90, -215);
        this.tgObj.pendown();
        this.tgObj.setpencolor(0);
        this.tgObj.label(s);
        this.tgObj.penup();
        this.tgObj.setxy(xcor, ycor);
        this.tgObj.setheading(heading);
        this.tgObj.setpencolor(getpencolor);
        this.tgObj.setpensize(getpensize);
        this.tgObj.showturtle();
        if (ispendown) {
            this.tgObj.pendown();
        }
    }
    
    void fillBox(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.tgObj.setpencolor(n5);
        this.tgObj.setheading(90);
        this.tgObj.setpensize(n4);
        this.tgObj.penup();
        this.tgObj.setxy(n, n2 - n4 / 2);
        this.tgObj.pendown();
        this.tgObj.forward(n3);
    }
    
    void rest(final int n) {
        try {
            Thread.sleep(n * 100);
        }
        catch (InterruptedException ex) {}
    }
    
    void walkForward(int i) {
        this.displayJavaStmt("forward( " + i + " )");
        while (i > 0) {
            if (i > 10) {
                this.tgObj.forward(10);
                i -= 10;
            }
            else {
                this.tgObj.forward(i);
                i = 0;
            }
            this.rest(2);
        }
    }
    
    void walkTo(final int n, final int n2) {
        this.displayJavaStmt("setxy( " + n + ", " + n2 + " )");
        final int xcor = this.tgObj.xcor();
        final int ycor = this.tgObj.ycor();
        final int n3 = n - xcor;
        final int n4 = n2 - ycor;
        final int n5 = (int)Math.floor(Math.sqrt(Math.pow(n3, 2.0) + Math.pow(n4, 2.0)) / 10.0);
        if (n5 > 1) {
            final double n6 = n3 / n5;
            final double n7 = n4 / n5;
            for (int i = 1; i < n5; ++i) {
                this.tgObj.setxy(xcor + (int)Math.floor(n6 * i), ycor + (int)Math.floor(n7 * i));
                this.rest(2);
            }
        }
        this.tgObj.setxy(n, n2);
        this.rest(2);
    }
    
    void initialize() {
        this.tgObj.setfontsize(24);
        this.tgObj.setpensize(7);
        this.tgObj.setpencolor(0);
        this.tgObj.setpencolor(15);
    }
    
    void drawBoard() {
        this.tgObj.home();
        this.tgObj.setheading(0);
        this.rest(10);
        this.tgObj.penup();
        this.walkTo(-50, -150);
        this.tgObj.pendown();
        this.walkForward(300);
        this.tgObj.penup();
        this.walkTo(50, -150);
        this.tgObj.pendown();
        this.walkForward(300);
        this.displayJavaStmt("setheading( 90 )");
        this.rest(10);
        this.tgObj.setheading(90);
        this.rest(10);
        this.tgObj.penup();
        this.walkTo(-150, -50);
        this.tgObj.pendown();
        this.walkForward(300);
        this.tgObj.penup();
        this.walkTo(-150, 50);
        this.tgObj.pendown();
        this.walkForward(300);
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        (this.tgObj = new TurtleGraphics(400, 460)).setBackground(Color.white);
        this.add("Center", this.tgObj);
        this.initialize();
    }
    
    public void run() {
        while (this.drawingThread == Thread.currentThread()) {
            this.drawBoard();
            this.rest(150);
            this.tgObj.clean();
            this.tgObj.penup();
            this.tgObj.setxy(0, 0);
            this.tgObj.pendown();
        }
    }
    
    public void start() {
        this.drawingThread = new Thread(this);
        this.tgObj.clean();
        this.tgObj.setxy(0, 0);
        this.drawingThread.start();
    }
    
    public void stop() {
        this.drawingThread = null;
    }
}
