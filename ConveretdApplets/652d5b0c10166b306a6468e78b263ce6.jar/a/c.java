// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.geom.NoninvertibleTransformException;

public class c
{
    public double b;
    public double void;
    public double long;
    public double else;
    public double byte;
    public double try;
    public double new;
    public double int;
    public double for;
    public double do;
    public double if;
    public double a;
    public double null;
    public double goto;
    public double char;
    public double case;
    
    public c() {
    }
    
    public c(final c c) {
        this.for(c);
    }
    
    public c(final double[] array) {
        this.a(array[0], array[4], array[8], array[12], array[1], array[5], array[9], array[13], array[2], array[6], array[10], array[14], array[3], array[7], array[11], array[15]);
    }
    
    public c(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9, final double n10, final double n11, final double n12, final double n13, final double n14, final double n15, final double n16) {
        this.a(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16);
    }
    
    public void a(final double b, final double void1, final double long1, final double else1, final double byte1, final double try1, final double new1, final double int1, final double for1, final double do1, final double if1, final double a, final double null, final double goto1, final double char1, final double case1) {
        this.b = b;
        this.void = void1;
        this.long = long1;
        this.else = else1;
        this.byte = byte1;
        this.try = try1;
        this.new = new1;
        this.int = int1;
        this.for = for1;
        this.do = do1;
        this.if = if1;
        this.a = a;
        this.null = null;
        this.goto = goto1;
        this.char = char1;
        this.case = case1;
    }
    
    public void for(final c c) {
        this.a(c.b, c.void, c.long, c.else, c.byte, c.try, c.new, c.int, c.for, c.do, c.if, c.a, c.null, c.goto, c.char, c.case);
    }
    
    public static c do() {
        return new c(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0);
    }
    
    public void a(final double n) {
        this.b *= n;
        this.void *= n;
        this.long *= n;
        this.else *= n;
        this.byte *= n;
        this.try *= n;
        this.new *= n;
        this.int *= n;
        this.for *= n;
        this.do *= n;
        this.if *= n;
        this.a *= n;
        this.null *= n;
        this.goto *= n;
        this.char *= n;
        this.case *= n;
    }
    
    public void a(final c c) {
        this.a(this.b * c.b + this.void * c.byte + this.long * c.for + this.else * c.null, this.b * c.void + this.void * c.try + this.long * c.do + this.else * c.goto, this.b * c.long + this.void * c.new + this.long * c.if + this.else * c.char, this.b * c.else + this.void * c.int + this.long * c.a + this.else * c.case, this.byte * c.b + this.try * c.byte + this.new * c.for + this.int * c.null, this.byte * c.void + this.try * c.try + this.new * c.do + this.int * c.goto, this.byte * c.long + this.try * c.new + this.new * c.if + this.int * c.char, this.byte * c.else + this.try * c.int + this.new * c.a + this.int * c.case, this.for * c.b + this.do * c.byte + this.if * c.for + this.a * c.null, this.for * c.void + this.do * c.try + this.if * c.do + this.a * c.goto, this.for * c.long + this.do * c.new + this.if * c.if + this.a * c.char, this.for * c.else + this.do * c.int + this.if * c.a + this.a * c.case, this.null * c.b + this.goto * c.byte + this.char * c.for + this.case * c.null, this.null * c.void + this.goto * c.try + this.char * c.do + this.case * c.goto, this.null * c.long + this.goto * c.new + this.char * c.if + this.case * c.char, this.null * c.else + this.goto * c.int + this.char * c.a + this.case * c.case);
    }
    
    public void do(final c c) {
        this.a(c.b * this.b + c.void * this.byte + c.long * this.for + c.else * this.null, c.b * this.void + c.void * this.try + c.long * this.do + c.else * this.goto, c.b * this.long + c.void * this.new + c.long * this.if + c.else * this.char, c.b * this.else + c.void * this.int + c.long * this.a + c.else * this.case, c.byte * this.b + c.try * this.byte + c.new * this.for + c.int * this.null, c.byte * this.void + c.try * this.try + c.new * this.do + c.int * this.goto, c.byte * this.long + c.try * this.new + c.new * this.if + c.int * this.char, c.byte * this.else + c.try * this.int + c.new * this.a + c.int * this.case, c.for * this.b + c.do * this.byte + c.if * this.for + c.a * this.null, c.for * this.void + c.do * this.try + c.if * this.do + c.a * this.goto, c.for * this.long + c.do * this.new + c.if * this.if + c.a * this.char, c.for * this.else + c.do * this.int + c.if * this.a + c.a * this.case, c.null * this.b + c.goto * this.byte + c.char * this.for + c.case * this.null, c.null * this.void + c.goto * this.try + c.char * this.do + c.case * this.goto, c.null * this.long + c.goto * this.new + c.char * this.if + c.case * this.char, c.null * this.else + c.goto * this.int + c.char * this.a + c.case * this.case);
    }
    
    public void if(final double n) {
        this.b /= n;
        this.void /= n;
        this.long /= n;
        this.else /= n;
        this.byte /= n;
        this.try /= n;
        this.new /= n;
        this.int /= n;
        this.for /= n;
        this.do /= n;
        this.if /= n;
        this.a /= n;
        this.null /= n;
        this.goto /= n;
        this.char /= n;
        this.case /= n;
    }
    
    public double a() {
        return (this.b * this.try - this.void * this.byte) * (this.if * this.case - this.a * this.char) - (this.b * this.new - this.long * this.byte) * (this.do * this.case - this.a * this.goto) + (this.b * this.int - this.else * this.byte) * (this.do * this.char - this.if * this.goto) + (this.void * this.new - this.long * this.try) * (this.for * this.case - this.a * this.null) - (this.void * this.int - this.else * this.try) * (this.for * this.char - this.if * this.null) + (this.long * this.int - this.else * this.new) * (this.for * this.goto - this.do * this.null);
    }
    
    public c if(c c) throws NoninvertibleTransformException {
        if (c == null) {
            c = new c();
        }
        final double a = this.a();
        if (Math.abs(a) <= Double.MIN_VALUE) {
            throw new NoninvertibleTransformException("Matrix4 cannot be inverted. Determinant: " + a + "\n" + this.toString());
        }
        c.a(this.try * (this.if * this.case - this.a * this.char) + this.new * (this.a * this.goto - this.do * this.case) + this.int * (this.do * this.char - this.if * this.goto), this.do * (this.long * this.case - this.else * this.char) + this.if * (this.else * this.goto - this.void * this.case) + this.a * (this.void * this.char - this.long * this.goto), this.goto * (this.long * this.int - this.else * this.new) + this.char * (this.else * this.try - this.void * this.int) + this.case * (this.void * this.new - this.long * this.try), this.void * (this.int * this.if - this.new * this.a) + this.long * (this.try * this.a - this.int * this.do) + this.else * (this.new * this.do - this.try * this.if), this.new * (this.for * this.case - this.a * this.null) + this.int * (this.if * this.null - this.for * this.char) + this.byte * (this.a * this.char - this.if * this.case), this.if * (this.b * this.case - this.else * this.null) + this.a * (this.long * this.null - this.b * this.char) + this.for * (this.else * this.char - this.long * this.case), this.char * (this.b * this.int - this.else * this.byte) + this.case * (this.long * this.byte - this.b * this.new) + this.null * (this.else * this.new - this.long * this.int), this.long * (this.int * this.for - this.byte * this.a) + this.else * (this.byte * this.if - this.new * this.for) + this.b * (this.new * this.a - this.int * this.if), this.int * (this.for * this.goto - this.do * this.null) + this.byte * (this.do * this.case - this.a * this.goto) + this.try * (this.a * this.null - this.for * this.case), this.a * (this.b * this.goto - this.void * this.null) + this.for * (this.void * this.case - this.else * this.goto) + this.do * (this.else * this.null - this.b * this.case), this.case * (this.b * this.try - this.void * this.byte) + this.null * (this.void * this.int - this.else * this.try) + this.goto * (this.else * this.byte - this.b * this.int), this.else * (this.try * this.for - this.byte * this.do) + this.b * (this.int * this.do - this.try * this.a) + this.void * (this.byte * this.a - this.int * this.for), this.byte * (this.if * this.goto - this.do * this.char) + this.try * (this.for * this.char - this.if * this.null) + this.new * (this.do * this.null - this.for * this.goto), this.for * (this.long * this.goto - this.void * this.char) + this.do * (this.b * this.char - this.long * this.null) + this.if * (this.void * this.null - this.b * this.goto), this.null * (this.long * this.try - this.void * this.new) + this.goto * (this.b * this.new - this.long * this.byte) + this.char * (this.void * this.byte - this.b * this.try), this.b * (this.try * this.if - this.new * this.do) + this.void * (this.new * this.for - this.byte * this.if) + this.long * (this.byte * this.do - this.try * this.for));
        c.if(a);
        return c;
    }
    
    public void for() {
        this.a(this.b, this.byte, this.for, this.null, this.void, this.try, this.do, this.goto, this.long, this.new, this.if, this.char, this.else, this.int, this.a, this.case);
    }
    
    public void if() {
        this.a(-this.b, -this.void, -this.long, -this.else, -this.byte, -this.try, -this.new, -this.int, -this.for, -this.do, -this.if, -this.a, -this.null, -this.goto, -this.char, -this.case);
    }
    
    public double[][] a(double[][] array) {
        if (array == null) {
            array = new double[4][4];
        }
        array[0][0] = this.b;
        array[0][1] = this.void;
        array[0][2] = this.long;
        array[0][3] = this.else;
        array[1][0] = this.byte;
        array[1][1] = this.try;
        array[1][2] = this.new;
        array[1][3] = this.int;
        array[2][0] = this.for;
        array[2][1] = this.do;
        array[2][2] = this.if;
        array[2][3] = this.a;
        array[3][0] = this.null;
        array[3][1] = this.goto;
        array[3][2] = this.char;
        array[3][3] = this.case;
        return array;
    }
    
    public boolean equals(final Object o) {
        if (o != null && o instanceof c) {
            final c c = (c)o;
            return c.b == this.b && c.void == this.void && c.long == this.long && c.else == this.else && c.byte == this.byte && c.try == this.try && c.new == this.new && c.int == this.int && c.for == this.for && c.do == this.do && c.if == this.if && c.a == this.a && c.null == this.null && c.goto == this.goto && c.char == this.char && c.case == this.case;
        }
        return false;
    }
    
    public int hashCode() {
        final long n = 31L * (31L * (31L * (31L * (31L * (31L * (31L * (31L * (31L * (31L * (31L * (31L * (31L * (31L * (31L * (31L * 1L + Double.doubleToLongBits(this.b)) + Double.doubleToLongBits(this.void)) + Double.doubleToLongBits(this.long)) + Double.doubleToLongBits(this.else)) + Double.doubleToLongBits(this.byte)) + Double.doubleToLongBits(this.try)) + Double.doubleToLongBits(this.new)) + Double.doubleToLongBits(this.int)) + Double.doubleToLongBits(this.for)) + Double.doubleToLongBits(this.do)) + Double.doubleToLongBits(this.if)) + Double.doubleToLongBits(this.a)) + Double.doubleToLongBits(this.null)) + Double.doubleToLongBits(this.goto)) + Double.doubleToLongBits(this.char)) + Double.doubleToLongBits(this.case);
        return (int)(n ^ n >> 32);
    }
    
    public String toString() {
        return String.valueOf(this.b) + ", " + this.void + ", " + this.long + ", " + this.else + "\n" + this.byte + ", " + this.try + ", " + this.new + ", " + this.int + "\n" + this.for + ", " + this.do + ", " + this.if + ", " + this.a + "\n" + this.null + ", " + this.goto + ", " + this.char + ", " + this.case + "\n";
    }
}
