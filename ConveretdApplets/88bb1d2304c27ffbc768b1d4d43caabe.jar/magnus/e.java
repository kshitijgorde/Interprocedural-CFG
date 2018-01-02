// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

class e
{
    double a;
    double long;
    double goto;
    double new;
    double char;
    double case;
    double byte;
    double if;
    double int;
    double for;
    double do;
    double else;
    static final double try = 3.14159265;
    
    public void if() {
        System.out.println("Magnus Chemistry");
        System.out.println("================");
        System.out.println("(c) J M Goodman, 1999-2001");
        System.out.println("Cambridge University");
        System.out.println("All rights reserved");
        System.out.println("");
    }
    
    e() {
        this.a = 1.0;
        this.case = 1.0;
        this.do = 1.0;
    }
    
    void do(final double n) {
        this.a *= n;
        this.long *= n;
        this.goto *= n;
        this.new *= n;
        this.char *= n;
        this.case *= n;
        this.byte *= n;
        this.if *= n;
        this.int *= n;
        this.for *= n;
        this.do *= n;
        this.else *= n;
    }
    
    void a(final double n, final double n2, final double n3) {
        this.a *= n;
        this.long *= n;
        this.goto *= n;
        this.new *= n;
        this.char *= n2;
        this.case *= n2;
        this.byte *= n2;
        this.if *= n2;
        this.int *= n3;
        this.for *= n3;
        this.do *= n3;
        this.else *= n3;
    }
    
    void if(final double n, final double n2, final double n3) {
        this.new += n;
        this.if += n2;
        this.else += n3;
    }
    
    void if(double n) {
        n *= 0.017453292500000002;
        final double cos = Math.cos(n);
        final double sin = Math.sin(n);
        final double a = this.a * cos + this.int * sin;
        final double long1 = this.long * cos + this.for * sin;
        final double goto1 = this.goto * cos + this.do * sin;
        final double new1 = this.new * cos + this.else * sin;
        final double int1 = this.int * cos - this.a * sin;
        final double for1 = this.for * cos - this.long * sin;
        final double do1 = this.do * cos - this.goto * sin;
        final double else1 = this.else * cos - this.new * sin;
        this.new = new1;
        this.a = a;
        this.long = long1;
        this.goto = goto1;
        this.else = else1;
        this.int = int1;
        this.for = for1;
        this.do = do1;
    }
    
    void for(double n) {
        n *= 0.017453292500000002;
        final double cos = Math.cos(n);
        final double sin = Math.sin(n);
        final double char1 = this.char * cos + this.int * sin;
        final double case1 = this.case * cos + this.for * sin;
        final double byte1 = this.byte * cos + this.do * sin;
        final double if1 = this.if * cos + this.else * sin;
        final double int1 = this.int * cos - this.char * sin;
        final double for1 = this.for * cos - this.case * sin;
        final double do1 = this.do * cos - this.byte * sin;
        final double else1 = this.else * cos - this.if * sin;
        this.if = if1;
        this.char = char1;
        this.case = case1;
        this.byte = byte1;
        this.else = else1;
        this.int = int1;
        this.for = for1;
        this.do = do1;
    }
    
    void a(double n) {
        n *= 0.017453292500000002;
        final double cos = Math.cos(n);
        final double sin = Math.sin(n);
        final double char1 = this.char * cos + this.a * sin;
        final double case1 = this.case * cos + this.long * sin;
        final double byte1 = this.byte * cos + this.goto * sin;
        final double if1 = this.if * cos + this.new * sin;
        final double a = this.a * cos - this.char * sin;
        final double long1 = this.long * cos - this.case * sin;
        final double goto1 = this.goto * cos - this.byte * sin;
        final double new1 = this.new * cos - this.if * sin;
        this.if = if1;
        this.char = char1;
        this.case = case1;
        this.byte = byte1;
        this.new = new1;
        this.a = a;
        this.long = long1;
        this.goto = goto1;
    }
    
    void a(final e e) {
        final double a = this.a * e.a + this.char * e.long + this.int * e.goto;
        final double long1 = this.long * e.a + this.case * e.long + this.for * e.goto;
        final double goto1 = this.goto * e.a + this.byte * e.long + this.do * e.goto;
        final double new1 = this.new * e.a + this.if * e.long + this.else * e.goto + e.new;
        final double char1 = this.a * e.char + this.char * e.case + this.int * e.byte;
        final double case1 = this.long * e.char + this.case * e.case + this.for * e.byte;
        final double byte1 = this.goto * e.char + this.byte * e.case + this.do * e.byte;
        final double if1 = this.new * e.char + this.if * e.case + this.else * e.byte + e.if;
        final double int1 = this.a * e.int + this.char * e.for + this.int * e.do;
        final double for1 = this.long * e.int + this.case * e.for + this.for * e.do;
        final double do1 = this.goto * e.int + this.byte * e.for + this.do * e.do;
        final double else1 = this.new * e.int + this.if * e.for + this.else * e.do + e.else;
        this.a = a;
        this.long = long1;
        this.goto = goto1;
        this.new = new1;
        this.char = char1;
        this.case = case1;
        this.byte = byte1;
        this.if = if1;
        this.int = int1;
        this.for = for1;
        this.do = do1;
        this.else = else1;
    }
    
    void a() {
        this.new = 0.0;
        this.a = 1.0;
        this.long = 0.0;
        this.goto = 0.0;
        this.if = 0.0;
        this.char = 0.0;
        this.case = 1.0;
        this.byte = 0.0;
        this.else = 0.0;
        this.int = 0.0;
        this.for = 0.0;
        this.do = 1.0;
    }
    
    void a(final double[] array, final int[] array2, final int n) {
        final double a = this.a;
        final double long1 = this.long;
        final double goto1 = this.goto;
        final double new1 = this.new;
        final double char1 = this.char;
        final double case1 = this.case;
        final double byte1 = this.byte;
        final double if1 = this.if;
        final double int1 = this.int;
        final double for1 = this.for;
        final double do1 = this.do;
        final double else1 = this.else;
        int n2 = n * 3;
        while (true) {
            n2 -= 3;
            if (n2 < 0) {
                break;
            }
            final double n3 = array[n2];
            final double n4 = array[n2 + 1];
            final double n5 = array[n2 + 2];
            array2[n2] = (int)(n3 * a + n4 * long1 + n5 * goto1 + new1);
            array2[n2 + 1] = (int)(n3 * char1 + n4 * case1 + n5 * byte1 + if1);
            array2[n2 + 2] = (int)(n3 * int1 + n4 * for1 + n5 * do1 + else1);
        }
    }
    
    public String toString() {
        return "[" + this.new + "," + this.a + "," + this.long + "," + this.goto + ";" + this.if + "," + this.char + "," + this.case + "," + this.byte + ";" + this.else + "," + this.int + "," + this.for + "," + this.do + "]";
    }
    
    static void a(final e e, final e e2, final double new1, final double if1, final double else1, final double n, final double n2, final double n3) {
        final e e3 = new e();
        final e e4 = new e();
        final e e5 = new e();
        e5.new = -new1;
        e5.if = -if1;
        e5.else = -else1;
        final double n4 = n - new1;
        final double n5 = n2 - if1;
        final double n6 = n3 - else1;
        final double sqrt = Math.sqrt(n6 * n6 + n5 * n5);
        final double n7 = n6 / sqrt;
        final double n8 = n5 / sqrt;
        e3.case = n7;
        e3.byte = n8;
        e3.for = -n8;
        e3.do = n7;
        final double sqrt2 = Math.sqrt(n4 * n4 + n5 * n5 + n6 * n6);
        final double n9 = sqrt / sqrt2;
        final double n10 = n4 / sqrt2;
        e4.a = n9;
        e4.goto = n10;
        e4.int = -n10;
        e4.do = n9;
        e.a(e5);
        e.a(e3);
        e.a(e4);
        e4.goto = -n10;
        e4.int = n10;
        e3.byte = -n8;
        e3.for = n8;
        e5.new = new1;
        e5.if = if1;
        e5.else = else1;
        e2.a(e4);
        e2.a(e3);
        e2.a(e5);
    }
}
