// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

import java.awt.Color;

class c
{
    int try;
    int else;
    int int;
    double char;
    double case;
    double byte;
    int new;
    int[] if;
    int[] a;
    int[] for;
    static Color[] do;
    
    public c(final double char1, final double case1, final double byte1) {
        this.char = char1;
        this.case = case1;
        this.byte = byte1;
    }
    
    public c(final double char1, final double case1, final double byte1, final int else1) {
        this.char = char1;
        this.case = case1;
        this.byte = byte1;
        this.else = else1;
        this.for();
    }
    
    public c(final c c) {
        this.char = c.char;
        this.case = c.case;
        this.byte = c.byte;
        this.else = c.else;
        this.for();
        this.new = c.new;
        this.if = new int[this.new];
        this.a = new int[this.new];
        for (int i = 0; i < this.new; ++i) {
            this.if[i] = c.if[i];
            this.a[i] = c.a[i];
        }
        this.for = null;
    }
    
    public c(final double char1, final double case1, final double byte1, final int else1, final int new1, final int[] array, final int[] array2) {
        this.char = char1;
        this.case = case1;
        this.byte = byte1;
        this.else = else1;
        this.for();
        this.new = new1;
        this.if = new int[this.new];
        this.a = new int[this.new];
        for (int i = 0; i < this.new; ++i) {
            this.if[i] = array[i];
            this.a[i] = array2[i];
        }
        this.for = null;
    }
    
    public void a(final int[] for1) {
        this.for = for1;
    }
    
    public b do() {
        return new b(this.char, this.case, this.byte);
    }
    
    public void if() {
        if (this.for != null) {
            for (int i = 0; i < this.for.length; ++i) {
                System.out.print(" " + this.for[i]);
            }
        }
    }
    
    public void int() {
        System.out.println("Magnus Chemistry");
        System.out.println("================");
        System.out.println("(c) J M Goodman, 1999-2001");
        System.out.println("Cambridge University");
        System.out.println("All rights reserved");
        System.out.println("");
    }
    
    void for() {
        if (this.else <= 14) {
            this.try = 6;
            this.int = 4;
        }
        else if (this.else <= 23) {
            this.try = 8;
            this.int = 11;
        }
        else if (this.else <= 40) {
            this.try = 7;
            this.int = 2;
        }
        else if (this.else <= 48) {
            this.try = 1;
            this.int = 5;
        }
        else if (this.else <= 52) {
            this.try = 16;
            this.int = 12;
        }
        else if (this.else <= 55) {
            this.try = 15;
            this.int = 6;
        }
        else if (this.else <= 56) {
            this.try = 9;
            this.int = 3;
        }
        else if (this.else <= 57) {
            this.try = 17;
            this.int = 6;
        }
        else if (this.else <= 58) {
            this.try = 35;
            this.int = 9;
        }
        else if (this.else <= 59) {
            this.try = 53;
            this.int = 8;
        }
        else if (this.else <= 60) {
            this.try = 14;
            this.int = 12;
        }
        else {
            this.try = -1;
            this.int = 7;
        }
    }
    
    void a() {
        if (this.try == 6) {
            this.else = 14;
            this.int = 1;
        }
        else if (this.try == 8) {
            this.else = 23;
            this.int = 11;
        }
        else if (this.try == 7) {
            this.else = 40;
            this.int = 2;
        }
        else if (this.try == 1) {
            this.else = 48;
            this.int = 5;
        }
        else if (this.try == 16) {
            this.else = 52;
            this.int = 12;
        }
        else if (this.try == 15) {
            this.else = 53;
            this.int = 6;
        }
        else if (this.try == 9) {
            this.else = 56;
            this.int = 3;
        }
        else if (this.try == 17) {
            this.else = 57;
            this.int = 6;
        }
        else if (this.try == 35) {
            this.else = 58;
            this.int = 9;
        }
        else if (this.try == 53) {
            this.else = 59;
            this.int = 8;
        }
        else if (this.try == 14) {
            this.else = 60;
            this.int = 12;
        }
        else {
            this.else = 64;
            this.int = 7;
        }
    }
    
    void a(final String s) {
        this.try = -1;
        for (int i = 0; i < magnus.a.if.length; ++i) {
            if (s.equals(magnus.a.if[i])) {
                this.try = i;
            }
            else if (s.equals(magnus.a.a[i])) {
                this.try = i;
            }
        }
    }
    
    static {
        c.do = new Color[] { Color.black, Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.yellow, Color.green, Color.green, Color.green };
    }
}
