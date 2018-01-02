import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CalcApplet extends Applet implements TGMouseHandler
{
    static final int BLACK = 0;
    static final int BLUE = 1;
    static final int RED = 4;
    static final int WHITE = 7;
    static final int NORTH = 0;
    static final int CALC_BORDER_SIZE = 5;
    static final int CALC_KEY_SIZE = 50;
    static final int CALC_KEYPAD_HEIGHT = 200;
    static final int CALC_KEYPAD_WIDTH = 200;
    static final int CALC_DISP_HEIGHT = 50;
    static final int CALC_DISP_WIDTH = 200;
    static final int CALC_HEIGHT = 250;
    static final int CALC_WIDTH = 200;
    static final int CALC_BOTTOM_Y = -125;
    static final int CALC_LEFT_X = -100;
    static final int KEY_BORDER_SIZE = 5;
    static final int KEY_LABEL_X_OFFSET = 15;
    static final int KEY_LABEL_Y_OFFSET = 15;
    static final int KEYPAD_BOTTOM_Y = -125;
    static final int KEYPAD_LEFT_X = -100;
    static final int KEYPAD_0_BOTTOM_Y = -125;
    static final int KEYPAD_0_LEFT_X = -100;
    static final int KEYPAD_1_BOTTOM_Y = -75;
    static final int KEYPAD_1_LEFT_X = -100;
    static final int KEYPAD_2_BOTTOM_Y = -75;
    static final int KEYPAD_2_LEFT_X = -50;
    static final int KEYPAD_3_BOTTOM_Y = -75;
    static final int KEYPAD_3_LEFT_X = 0;
    static final int KEYPAD_4_BOTTOM_Y = -25;
    static final int KEYPAD_4_LEFT_X = -100;
    static final int KEYPAD_5_BOTTOM_Y = -25;
    static final int KEYPAD_5_LEFT_X = -50;
    static final int KEYPAD_6_BOTTOM_Y = -25;
    static final int KEYPAD_6_LEFT_X = 0;
    static final int KEYPAD_7_BOTTOM_Y = 25;
    static final int KEYPAD_7_LEFT_X = -100;
    static final int KEYPAD_8_BOTTOM_Y = 25;
    static final int KEYPAD_8_LEFT_X = -50;
    static final int KEYPAD_9_BOTTOM_Y = 25;
    static final int KEYPAD_9_LEFT_X = 0;
    static final int KEYPAD_EQL_BOTTOM_Y = -125;
    static final int KEYPAD_EQL_LEFT_X = 0;
    static final int KEYPAD_SUM_BOTTOM_Y = -125;
    static final int KEYPAD_SUM_LEFT_X = 50;
    static final int KEYPAD_DIF_BOTTOM_Y = -75;
    static final int KEYPAD_DIF_LEFT_X = 50;
    static final int KEYPAD_MUL_BOTTOM_Y = -25;
    static final int KEYPAD_MUL_LEFT_X = 50;
    static final int KEYPAD_DIV_BOTTOM_Y = 25;
    static final int KEYPAD_DIV_LEFT_X = 50;
    static final int KEYPAD_EMPTY_BOTTOM_Y = -125;
    static final int KEYPAD_EMPTY_LEFT_X = -50;
    static final int DISP_BORDER_SIZE = 5;
    static final int DISP_BOTTOM_Y = 75;
    static final int DISP_LEFT_X = -100;
    static final int DISP_DIGIT_WIDTH = 25;
    static final int DISP_DIGIT_X_OFFSET = 15;
    static final int DISP_DIGIT_Y_OFFSET = 15;
    static final int DISP_DIGIT_BOTTOM_Y = 90;
    static final int DISP_MINUS_LEFT_X = -85;
    static final int DISP_1S_LEFT_X = 65;
    static final int DISP_10S_LEFT_X = 40;
    static final int DISP_100S_LEFT_X = 15;
    static final int DISP_1000S_LEFT_X = -10;
    static final int DISP_10000S_LEFT_X = -35;
    static final int DISP_100000S_LEFT_X = -60;
    boolean collecting;
    char opToDo;
    float curNum;
    float prevNum;
    TGCanvas canvas;
    Turtle turtle;
    
    public CalcApplet() {
        this.collecting = true;
    }
    
    void frameAt(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.turtle.setpencolor(0);
        this.turtle.setpensize(n5);
        this.turtle.penup();
        this.turtle.setxy(n, n2);
        this.turtle.pendown();
        this.turtle.setheading(0);
        for (int i = 2; i > 0; --i) {
            this.turtle.forward(n3);
            this.turtle.right(90);
            this.turtle.forward(n4);
            this.turtle.right(90);
        }
    }
    
    void solidRect(final int n, final int n2) {
        this.turtle.setpensize(1);
        for (int i = n2; i > 0; --i) {
            this.turtle.forward(n);
            this.turtle.back(n);
            this.turtle.right(90);
            this.turtle.penup();
            this.turtle.forward(1);
            this.turtle.pendown();
            this.turtle.left(90);
        }
    }
    
    void coloredRectAt(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.turtle.penup();
        this.turtle.setxy(n, n2);
        this.turtle.pendown();
        this.turtle.setpencolor(n5);
        this.solidRect(n3, n4);
    }
    
    void charsAt(final int n, final int n2, final String s) {
        this.turtle.penup();
        this.turtle.setxy(n, n2);
        this.turtle.pendown();
        this.turtle.setpencolor(1);
        this.turtle.label(s);
    }
    
    void digitAt(final int n, final int n2, final int n3) {
        this.turtle.penup();
        this.turtle.setxy(n, n2);
        this.turtle.pendown();
        this.turtle.setpencolor(1);
        this.turtle.label(Integer.toString(n3));
    }
    
    void keyAt(final int n, final int n2, final String s) {
        this.turtle.setpencolor(0);
        this.frameAt(n, n2, 50, 50, 5);
        this.turtle.penup();
        this.turtle.setxy(n + 15, n2 + 15);
        this.turtle.pendown();
        this.turtle.setpencolor(1);
        this.turtle.label(s);
    }
    
    void drawKeypad() {
        this.keyAt(-100, -125, "0");
        this.keyAt(-100, -75, "1");
        this.keyAt(-100, -25, "4");
        this.keyAt(-100, 25, "7");
        this.keyAt(-50, -125, " ");
        this.keyAt(-50, -75, "2");
        this.keyAt(-50, -25, "5");
        this.keyAt(-50, 25, "8");
        this.keyAt(0, -125, "=");
        this.keyAt(0, -75, "3");
        this.keyAt(0, -25, "6");
        this.keyAt(0, 25, "9");
        this.keyAt(50, -125, "+");
        this.keyAt(50, -75, "-");
        this.keyAt(50, -25, "X");
        this.keyAt(50, 25, "/");
    }
    
    void clearDisplay() {
        this.coloredRectAt(-95, 80, 40, 190, 7);
    }
    
    void displayError() {
        this.clearDisplay();
        this.charsAt(-35, 90, "E");
        this.charsAt(-10, 90, "R");
        this.charsAt(15, 90, "R");
        this.charsAt(40, 90, "O");
        this.charsAt(65, 90, "R");
    }
    
    void displayMinusSign() {
        this.turtle.penup();
        this.turtle.setxy(-85, 90);
        this.turtle.pendown();
        this.turtle.setpencolor(4);
        this.turtle.label("-");
    }
    
    void displayNum() {
        this.clearDisplay();
        int round = Math.round(this.curNum);
        if (round < 0) {
            this.displayMinusSign();
            round = -round;
        }
        if (round > 99999) {
            this.digitAt(-60, 90, round / 100000 % 10);
        }
        if (round > 9999) {
            this.digitAt(-35, 90, round / 10000 % 10);
        }
        if (round > 999) {
            this.digitAt(-10, 90, round / 1000 % 10);
        }
        if (round > 99) {
            this.digitAt(15, 90, round / 100 % 10);
        }
        if (round > 9) {
            this.digitAt(40, 90, round / 10 % 10);
        }
        this.digitAt(65, 90, round % 10);
    }
    
    void digit(final int n) {
        if (!this.collecting) {
            this.collecting = true;
            this.curNum = n;
        }
        else {
            this.curNum = this.curNum * 10.0f + n;
        }
        this.displayNum();
    }
    
    void doMathOp() {
        if (this.opToDo == '+') {
            this.curNum += this.prevNum;
        }
        if (this.opToDo == '-') {
            this.curNum = this.prevNum - this.curNum;
        }
        if (this.opToDo == '*') {
            this.curNum *= this.prevNum;
        }
        if (this.opToDo == '/') {
            if (this.curNum == 0.0f) {
                this.displayError();
                this.curNum = this.prevNum;
                this.collecting = false;
                this.opToDo = ' ';
                return;
            }
            this.curNum = this.prevNum / this.curNum;
        }
        this.displayNum();
    }
    
    void divideKey() {
        if (this.opToDo != ' ') {
            this.doMathOp();
        }
        this.prevNum = this.curNum;
        this.opToDo = '/';
        this.collecting = false;
    }
    
    void equalsKey() {
        this.doMathOp();
        this.collecting = false;
        this.opToDo = ' ';
    }
    
    void minusKey() {
        if (this.opToDo != ' ') {
            this.doMathOp();
        }
        this.prevNum = this.curNum;
        this.opToDo = '-';
        this.collecting = false;
    }
    
    void plusKey() {
        if (this.opToDo != ' ') {
            this.doMathOp();
        }
        this.prevNum = this.curNum;
        this.opToDo = '+';
        this.collecting = false;
    }
    
    void timesKey() {
        if (this.opToDo != ' ') {
            this.doMathOp();
        }
        this.prevNum = this.curNum;
        this.opToDo = '*';
        this.collecting = false;
    }
    
    boolean isMouseInRect(final int n, final int n2, final int n3, final int n4) {
        final int mousex = this.canvas.mousex();
        final int mousey = this.canvas.mousey();
        return mousex >= n && mousey >= n2 && mousex < n + n4 && mousey < n2 + n3;
    }
    
    boolean isMouseInKey(final int n, final int n2) {
        return this.isMouseInRect(n, n2, 50, 50);
    }
    
    public void mouseClick() {
        if (this.isMouseInKey(-100, -125)) {
            this.digit(0);
            return;
        }
        if (this.isMouseInKey(-100, -75)) {
            this.digit(1);
            return;
        }
        if (this.isMouseInKey(-50, -75)) {
            this.digit(2);
            return;
        }
        if (this.isMouseInKey(0, -75)) {
            this.digit(3);
            return;
        }
        if (this.isMouseInKey(-100, -25)) {
            this.digit(4);
            return;
        }
        if (this.isMouseInKey(-50, -25)) {
            this.digit(5);
            return;
        }
        if (this.isMouseInKey(0, -25)) {
            this.digit(6);
            return;
        }
        if (this.isMouseInKey(-100, 25)) {
            this.digit(7);
            return;
        }
        if (this.isMouseInKey(-50, 25)) {
            this.digit(8);
            return;
        }
        if (this.isMouseInKey(0, 25)) {
            this.digit(9);
            return;
        }
        if (this.isMouseInKey(50, -75)) {
            this.minusKey();
            return;
        }
        if (this.isMouseInKey(50, 25)) {
            this.divideKey();
            return;
        }
        if (this.isMouseInKey(0, -125)) {
            this.equalsKey();
            return;
        }
        if (this.isMouseInKey(50, -25)) {
            this.timesKey();
            return;
        }
        if (this.isMouseInKey(50, -125)) {
            this.plusKey();
        }
    }
    
    void initialize() {
        this.turtle.hideturtle();
        this.turtle.setlabelheight(36);
        this.drawKeypad();
        this.frameAt(-100, 75, 50, 200, 5);
        this.curNum = 0.0f;
        this.displayNum();
        this.prevNum = 0.0f;
        this.collecting = true;
        this.opToDo = ' ';
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        this.add("Center", this.canvas = new TGCanvas(400, 400));
        this.canvas.addMouseHandler(this);
        this.turtle = new Turtle(this.canvas);
        this.initialize();
    }
}
