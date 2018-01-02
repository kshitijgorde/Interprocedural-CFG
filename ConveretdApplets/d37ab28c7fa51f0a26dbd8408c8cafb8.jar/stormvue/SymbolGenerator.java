// 
// Decompiled by Procyon v0.5.30
// 

package stormvue;

import java.awt.Graphics;
import java.applet.Applet;

public class SymbolGenerator extends Applet
{
    private int goto;
    private int else;
    private int case;
    private final int new = 1;
    private final int b = 2;
    private final int try = 3;
    private final int long = 4;
    private final int byte = 5;
    private final int c = 6;
    private final int int = 7;
    private final int void = 8;
    private final int null = 9;
    private final int for = 10;
    private final int d = 11;
    private final int char = 12;
    private final int do = 13;
    private final int if = 14;
    private final int a = 15;
    
    SymbolGenerator(final int goto1, final int else1, final int case1) {
        this.goto = goto1;
        this.else = else1;
        this.case = case1;
    }
    
    public void a(final int case1) {
        this.case = case1;
    }
    
    public void for(final Graphics graphics, final int n, final int n2) {
        graphics.drawLine(n + this.goto, n2 + this.else, n + this.goto, n2 + this.else);
        graphics.drawLine(n + this.goto + 1, n2 + this.else, n + this.goto - 1, n2 + this.else);
        graphics.drawLine(n + this.goto, n2 + this.else - 1, n + this.goto, n2 + this.else + 1);
    }
    
    public void new(final Graphics graphics, final int n, int n2) {
        n2 -= this.case / 2;
        graphics.fillOval(n + this.goto - 2, n2 + this.else - 2, this.case * 2 + 1, this.case * 2 + 1);
    }
    
    public void try(final Graphics graphics, final int n, int n2) {
        n2 -= this.case / 2;
        graphics.drawOval(n + this.goto - 2, n2 + this.else - 2, this.case * 2, this.case * 2);
    }
    
    public void do(final Graphics graphics, final int n, final int n2) {
        graphics.drawLine(n + this.goto, n2 + this.else, n + this.goto, n2 + this.else);
    }
    
    public void a(final Graphics graphics, final int n, int n2) {
        n2 -= this.case - 1;
        final int n3 = (int)(this.case * 1.5);
        graphics.drawLine(n + this.goto, n2 + this.else, n + this.goto + n3, n2 + this.else + n3);
        graphics.drawLine(n + this.goto + n3, n2 + this.else, n + this.goto, n2 + this.else + n3);
    }
    
    public void int(final Graphics graphics, final int n, final int n2) {
        graphics.drawLine(n + this.goto - this.case, n2 + this.else, n + this.goto + this.case, n2 + this.else);
        graphics.drawLine(n + this.goto, n2 + this.else - this.case, n + this.goto, n2 + this.else + this.case);
    }
    
    public void if(final Graphics graphics, final int n, final int n2) {
        graphics.drawLine(n + this.goto - this.case, n2 + this.else, n + this.goto + this.case, n2 + this.else);
    }
    
    public void if(final Graphics graphics, final int n, int n2, final boolean b) {
        final int[] array = new int[5];
        final int[] array2 = new int[5];
        n2 -= this.case;
        final int[] array3 = array;
        final int n3 = 0;
        final int[] array4 = array;
        final int n4 = 2;
        final int[] array5 = array;
        final int n5 = 4;
        final int n6 = n + this.goto;
        array5[n5] = n6;
        array3[n3] = (array4[n4] = n6);
        array[1] = n + this.goto - this.case;
        array[3] = n + this.goto + this.case;
        array2[0] = (array2[4] = n2 + this.else);
        array2[1] = (array2[3] = n2 + this.else + this.case);
        array2[2] = n2 + this.else + this.case * 2;
        if (b) {
            graphics.fillPolygon(array, array2, 5);
            return;
        }
        graphics.drawPolygon(array, array2, 5);
    }
    
    public void a(final Graphics graphics, final int n, int n2, final boolean b, final boolean b2) {
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        n2 -= this.case;
        if (b) {
            array[0] = (array[3] = n + this.goto + this.case / 2);
            array[1] = array[0] - this.case;
            array[2] = array[0] + this.case;
            array2[0] = (array2[3] = n2 + this.else);
            array2[1] = (array2[2] = n2 + this.else + this.case * 2);
            if (b2) {
                array[0] = (array[3] = array[0] - 1);
                final int[] array3 = array;
                final int n3 = 1;
                array3[n3] -= 2;
                array2[0] = (array2[3] = array2[0] - 1);
                array2[1] = (array2[2] = array2[1] + 1);
                final int[] array4 = array2;
                final int n4 = 2;
                --array4[n4];
            }
        }
        else {
            array[0] = (array[3] = n + this.goto + this.case / 2);
            array[1] = array[0] - this.case;
            array[2] = array[0] + this.case;
            array2[1] = (array2[2] = n2 + this.else);
            array2[0] = (array2[3] = n2 + this.else + this.case * 2);
            if (b2) {
                array[0] = (array[3] = array[0] - 1);
                final int[] array5 = array;
                final int n5 = 1;
                array5[n5] -= 2;
                array2[0] = (array2[3] = array2[0] + 1);
                array2[1] = (array2[2] = array2[1] - 1);
                final int[] array6 = array2;
                final int n6 = 2;
                --array6[n6];
            }
        }
        if (b2) {
            graphics.fillPolygon(array, array2, 4);
            return;
        }
        graphics.drawPolygon(array, array2, 4);
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final boolean b) {
        if (b) {
            graphics.fillRect(n + this.goto - (this.case - 1), n2 + this.else - (this.case - 1), this.case * 2 + 1, this.case * 2 + 1);
            return;
        }
        graphics.drawRect(n + this.goto - this.case, n2 + this.else - this.case, this.case * 2, this.case * 2);
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final int n3) {
        graphics.drawLine(n + this.goto, n2 + this.else, n + this.goto - n3, n2 + this.else - n3);
        graphics.drawLine(n + this.goto + 1, n2 + this.else, n + this.goto - 1 - n3, n2 + this.else - n3);
        graphics.drawLine(n + this.goto, n2 + this.else - 1, n + this.goto - n3, n2 + this.else + 1 - n3);
    }
    
    public void if(final Graphics graphics, final int n, final int n2, final int n3) {
        switch (n3) {
            case 2: {
                this.do(graphics, n, n2);
            }
            case 3: {
                this.new(graphics, n, n2);
            }
            case 4: {
                this.int(graphics, n, n2);
            }
            case 5: {
                this.a(graphics, n, n2);
            }
            case 6: {
                this.if(graphics, n, n2);
            }
            case 7: {
                this.try(graphics, n, n2);
            }
            case 8: {
                this.if(graphics, n, n2, false);
            }
            case 9: {
                this.if(graphics, n, n2, true);
            }
            case 10: {
                this.a(graphics, n, n2, true, false);
            }
            case 11: {
                this.a(graphics, n, n2, true, true);
            }
            case 12: {
                this.a(graphics, n, n2, false, false);
            }
            case 13: {
                this.a(graphics, n, n2, false, true);
            }
            case 14: {
                this.a(graphics, n, n2, false);
            }
            case 15: {
                this.a(graphics, n, n2, true);
            }
            default: {
                this.for(graphics, n, n2);
            }
        }
    }
}
