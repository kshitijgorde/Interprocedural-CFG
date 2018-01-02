import java.awt.Color;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public final class DigitElement extends Canvas
{
    Dimension size;
    Container owner;
    Graphics graphic;
    public static int EMPTY;
    public static int ERROR;
    public static int MINUS;
    static Color backgroundColor;
    static Color hidingBar;
    int[] p_x;
    int[] p_y;
    int digit;
    boolean isDot;
    
    public DigitElement(final Container owner) {
        this.p_x = new int[6];
        this.p_y = new int[6];
        this.digit = DigitElement.EMPTY;
        this.isDot = false;
        this.size = new Dimension(22, 38);
        this.owner = owner;
        this.resize(this.size);
        this.owner.validate();
        this.setBackground(DigitElement.backgroundColor);
    }
    
    public Dimension getPreferredSize() {
        return this.size;
    }
    
    public Dimension getMinimumSize() {
        return this.size;
    }
    
    public void paint(final Graphics graphic) {
        this.graphic = graphic;
        this.paintDigit(this.digit);
        this.draw_dot(this.isDot ? Color.black : this.getBackground());
    }
    
    public void setDot(final boolean isDot) {
        this.isDot = isDot;
        this.repaint();
    }
    
    public boolean isDot() {
        return this.isDot;
    }
    
    public int getDigit() {
        return this.digit;
    }
    
    public void setDigit(final int digit) {
        this.digit = digit;
        this.repaint();
    }
    
    private void paintDigit(final int n) {
        switch (n) {
            case 0: {
                this.draw_0();
            }
            case 1: {
                this.draw_1();
            }
            case 2: {
                this.draw_2();
            }
            case 3: {
                this.draw_3();
            }
            case 4: {
                this.draw_4();
            }
            case 5: {
                this.draw_5();
            }
            case 6: {
                this.draw_6();
            }
            case 7: {
                this.draw_7();
            }
            case 8: {
                this.draw_8();
            }
            case 9: {
                this.draw_9();
            }
            case 12: {
                this.draw_empty();
            }
            case 13: {
                this.draw_e();
            }
            case 14: {
                this.draw_minus();
            }
            default: {
                this.draw_e();
            }
        }
    }
    
    private void draw_minus() {
        final Color hidingBar = DigitElement.hidingBar;
        final Color black = Color.black;
        this.draw_a(hidingBar);
        this.draw_b(hidingBar);
        this.draw_c(hidingBar);
        this.draw_d(hidingBar);
        this.draw_e(hidingBar);
        this.draw_f(hidingBar);
        this.draw_g(black);
    }
    
    private void draw_empty() {
        final Color hidingBar = DigitElement.hidingBar;
        this.draw_a(hidingBar);
        this.draw_b(hidingBar);
        this.draw_c(hidingBar);
        this.draw_d(hidingBar);
        this.draw_e(hidingBar);
        this.draw_f(hidingBar);
        this.draw_g(hidingBar);
    }
    
    private void draw_e() {
        final Color hidingBar = DigitElement.hidingBar;
        final Color black = Color.black;
        this.draw_a(black);
        this.draw_b(hidingBar);
        this.draw_c(hidingBar);
        this.draw_d(black);
        this.draw_e(black);
        this.draw_f(black);
        this.draw_g(black);
    }
    
    private void draw_0() {
        final Color hidingBar = DigitElement.hidingBar;
        final Color black = Color.black;
        this.draw_a(black);
        this.draw_b(black);
        this.draw_c(black);
        this.draw_d(black);
        this.draw_e(black);
        this.draw_f(black);
        this.draw_g(hidingBar);
    }
    
    private void draw_1() {
        final Color hidingBar = DigitElement.hidingBar;
        final Color black = Color.black;
        this.draw_a(hidingBar);
        this.draw_b(black);
        this.draw_c(black);
        this.draw_d(hidingBar);
        this.draw_e(hidingBar);
        this.draw_f(hidingBar);
        this.draw_g(hidingBar);
    }
    
    private void draw_2() {
        final Color hidingBar = DigitElement.hidingBar;
        final Color black = Color.black;
        this.draw_a(black);
        this.draw_b(black);
        this.draw_c(hidingBar);
        this.draw_d(black);
        this.draw_e(black);
        this.draw_f(hidingBar);
        this.draw_g(black);
    }
    
    private void draw_3() {
        final Color hidingBar = DigitElement.hidingBar;
        final Color black = Color.black;
        this.draw_a(black);
        this.draw_b(black);
        this.draw_c(black);
        this.draw_d(black);
        this.draw_e(hidingBar);
        this.draw_f(hidingBar);
        this.draw_g(black);
    }
    
    private void draw_4() {
        final Color hidingBar = DigitElement.hidingBar;
        final Color black = Color.black;
        this.draw_a(hidingBar);
        this.draw_b(black);
        this.draw_c(black);
        this.draw_d(hidingBar);
        this.draw_e(hidingBar);
        this.draw_f(black);
        this.draw_g(black);
    }
    
    private void draw_5() {
        final Color hidingBar = DigitElement.hidingBar;
        final Color black = Color.black;
        this.draw_a(black);
        this.draw_b(hidingBar);
        this.draw_c(black);
        this.draw_d(black);
        this.draw_e(hidingBar);
        this.draw_f(black);
        this.draw_g(black);
    }
    
    private void draw_6() {
        final Color hidingBar = DigitElement.hidingBar;
        final Color black = Color.black;
        this.draw_a(black);
        this.draw_b(hidingBar);
        this.draw_c(black);
        this.draw_d(black);
        this.draw_e(black);
        this.draw_f(black);
        this.draw_g(black);
    }
    
    private void draw_7() {
        final Color hidingBar = DigitElement.hidingBar;
        final Color black = Color.black;
        this.draw_a(black);
        this.draw_b(black);
        this.draw_c(black);
        this.draw_d(hidingBar);
        this.draw_e(hidingBar);
        this.draw_f(hidingBar);
        this.draw_g(hidingBar);
    }
    
    private void draw_8() {
        final Color black = Color.black;
        this.draw_a(black);
        this.draw_b(black);
        this.draw_c(black);
        this.draw_d(black);
        this.draw_e(black);
        this.draw_f(black);
        this.draw_g(black);
    }
    
    private void draw_9() {
        final Color hidingBar = DigitElement.hidingBar;
        final Color black = Color.black;
        this.draw_a(black);
        this.draw_b(black);
        this.draw_c(black);
        this.draw_d(black);
        this.draw_e(hidingBar);
        this.draw_f(black);
        this.draw_g(black);
    }
    
    private void draw_b(final Color color) {
        this.p_x[0] = 17;
        this.p_y[0] = 5;
        this.p_x[1] = 17;
        this.p_y[1] = 17;
        this.p_x[2] = 14;
        this.p_y[2] = 14;
        this.p_x[3] = 14;
        this.p_y[3] = 8;
        this.graphic.setColor(color);
        this.graphic.fillPolygon(this.p_x, this.p_y, 4);
    }
    
    private void draw_g(final Color color) {
        this.p_x[0] = 6;
        this.p_y[0] = 16;
        this.p_x[1] = 14;
        this.p_y[1] = 16;
        this.p_x[2] = 16;
        this.p_y[2] = 18;
        this.p_x[3] = 14;
        this.p_y[3] = 20;
        this.p_x[4] = 6;
        this.p_y[4] = 20;
        this.p_x[5] = 4;
        this.p_y[5] = 18;
        this.graphic.setColor(color);
        this.graphic.fillPolygon(this.p_x, this.p_y, 6);
    }
    
    private void draw_f(final Color color) {
        this.p_x[0] = 3;
        this.p_y[0] = 5;
        this.p_x[1] = 6;
        this.p_y[1] = 8;
        this.p_x[2] = 6;
        this.p_y[2] = 14;
        this.p_x[3] = 3;
        this.p_y[3] = 17;
        this.graphic.setColor(color);
        this.graphic.fillPolygon(this.p_x, this.p_y, 4);
    }
    
    private void draw_c(final Color color) {
        this.p_x[0] = 17;
        this.p_y[0] = 19;
        this.p_x[1] = 17;
        this.p_y[1] = 31;
        this.p_x[2] = 14;
        this.p_y[2] = 28;
        this.p_x[3] = 14;
        this.p_y[3] = 22;
        this.graphic.setColor(color);
        this.graphic.fillPolygon(this.p_x, this.p_y, 4);
    }
    
    private void draw_d(final Color color) {
        this.p_x[0] = 6;
        this.p_y[0] = 30;
        this.p_x[1] = 14;
        this.p_y[1] = 30;
        this.p_x[2] = 17;
        this.p_y[2] = 33;
        this.p_x[3] = 3;
        this.p_y[3] = 33;
        this.graphic.setColor(color);
        this.graphic.fillPolygon(this.p_x, this.p_y, 4);
    }
    
    private void draw_e(final Color color) {
        this.p_x[0] = 3;
        this.p_y[0] = 19;
        this.p_x[1] = 6;
        this.p_y[1] = 22;
        this.p_x[2] = 6;
        this.p_y[2] = 28;
        this.p_x[3] = 3;
        this.p_y[3] = 31;
        this.graphic.setColor(color);
        this.graphic.fillPolygon(this.p_x, this.p_y, 4);
    }
    
    private void draw_a(final Color color) {
        this.p_x[0] = 5;
        this.p_y[0] = 5;
        this.p_x[1] = 15;
        this.p_y[1] = 5;
        this.p_x[2] = 12;
        this.p_y[2] = 8;
        this.p_x[3] = 8;
        this.p_y[3] = 8;
        this.graphic.setColor(color);
        this.graphic.fillPolygon(this.p_x, this.p_y, 4);
    }
    
    private void draw_dot(final Color color) {
        this.p_x[0] = 17;
        this.p_y[0] = 32;
        this.p_x[1] = 21;
        this.p_y[1] = 32;
        this.p_x[2] = 21;
        this.p_y[2] = 37;
        this.p_x[3] = 17;
        this.p_y[3] = 37;
        this.graphic.setColor(color);
        this.graphic.fillPolygon(this.p_x, this.p_y, 4);
    }
    
    static {
        DigitElement.EMPTY = 12;
        DigitElement.ERROR = 13;
        DigitElement.MINUS = 14;
        DigitElement.backgroundColor = new Color(255, 230, 230);
        DigitElement.hidingBar = new Color(218, 218, 218);
    }
}
