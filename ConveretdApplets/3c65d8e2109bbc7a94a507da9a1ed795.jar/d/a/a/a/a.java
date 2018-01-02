// 
// Decompiled by Procyon v0.5.30
// 

package d.a.a.a;

public class a
{
    private short[] try;
    private short int;
    private int if;
    private int a;
    private short[] char;
    private short[][] new;
    private short for;
    private short do;
    private short[] case;
    private short byte;
    
    public a() {
        final short[] array = new short[280];
        final short[] array2 = new short[8];
        final short[][] array3 = new short[2][8];
        final short[] array4 = new short[9];
        this.a(array);
        this.for((short)0);
        this.int(0);
        this.if(0);
        this.if(array2);
        this.a(array3);
        this.do((short)0);
        this.if((short)40);
        this.do(array4);
        this.a((short)0);
    }
    
    public void do() {
        System.out.println("\ndp0[]: ");
        System.out.println("\nz1: " + this.int);
        System.out.println("\nL_z2: " + this.if);
        System.out.println("\nmp: " + this.a);
        System.out.println("\nu[]: ");
        for (int i = 0; i < this.char.length; ++i) {
            System.out.print("[" + i + "] " + this.char[i]);
            if (i < this.char.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("\n");
        System.out.println("\nLARpp[]: ");
        for (int j = 0; j < 2; ++j) {
            for (int k = 0; k < 8; ++k) {
                System.out.print("[" + j + "][" + k + "] " + this.new[j][k]);
                System.out.print(", ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        System.out.println("\nj: " + this.for);
        System.out.println("\nnrp: " + this.do);
        System.out.println("\nv[]: ");
        for (int l = 0; l < this.case.length; ++l) {
            System.out.print("[" + l + "] " + this.case[l]);
            if (l < this.case.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("\n");
        System.out.println("\nmsr: " + this.byte);
    }
    
    public String toString() {
        final String s = new String("");
        new String("");
        return String.valueOf(this.do);
    }
    
    public void a(final short[] try1) {
        this.try = try1;
    }
    
    public void if(final int n, final short n2) {
        this.try[n] = n2;
    }
    
    public short[] else() {
        return this.try;
    }
    
    public short a(final int n) {
        return this.try[n];
    }
    
    public void for(final short int1) {
        this.int = int1;
    }
    
    public short for() {
        return this.int;
    }
    
    public void int(final int if1) {
        this.if = if1;
    }
    
    public int try() {
        return this.if;
    }
    
    public void if(final int a) {
        this.a = a;
    }
    
    public int char() {
        return this.a;
    }
    
    public void if(final short[] char1) {
        this.char = char1;
    }
    
    public void do(final int n, final short n2) {
        this.char[n] = n2;
    }
    
    public short[] int() {
        return this.char;
    }
    
    public short for(final int n) {
        return this.char[n];
    }
    
    public void a(final short[][] new1) {
        this.new = new1;
    }
    
    public void a(final int n, final short[] array) {
        this.new[n] = array;
    }
    
    public short[][] a() {
        return this.new;
    }
    
    public short[] do(final int n) {
        return this.new[n];
    }
    
    public void do(final short for1) {
        this.for = for1;
    }
    
    public short byte() {
        return this.for;
    }
    
    public void if(final short do1) {
        this.do = do1;
    }
    
    public short case() {
        return this.do;
    }
    
    public void do(final short[] case1) {
        this.case = case1;
    }
    
    public void a(final int n, final short n2) {
        this.case[n] = n2;
    }
    
    public short[] if() {
        return this.case;
    }
    
    public short new(final int n) {
        return this.case[n];
    }
    
    public void a(final short byte1) {
        this.byte = byte1;
    }
    
    public short new() {
        return this.byte;
    }
}
