// 
// Decompiled by Procyon v0.5.30
// 

public class i
{
    private int char;
    int do;
    private int new;
    int if;
    int for;
    int a;
    int int;
    int else;
    boolean try;
    l byte;
    ak case;
    
    i(final l byte1, final ak case1) {
        this.char = 0;
        this.do = 0;
        this.new = -1;
        this.if = -5329991;
        this.for = 0;
        this.a = 0;
        this.int = 0;
        this.else = 0;
        this.try = false;
        this.byte = byte1;
        this.case = case1;
    }
    
    public void a() {
        this.char = 0;
        this.do = 0;
    }
    
    public boolean a(final long n) {
        if (this.do == this.char) {
            this.char = 0;
            this.do = 0;
        }
        if (this.do != this.new) {
            this.new = this.do;
            return true;
        }
        return this.try;
    }
    
    public void a(final int n) {
        this.char += n;
    }
    
    public void if() {
        if (!this.try) {
            return;
        }
        int n = 0;
        if (this.char != 0) {
            n = this.do * this.int / this.char;
        }
        d.if(this.byte.d, this.for, this.a, n, this.else, this.if);
        if (this.case.do) {
            d.if(this.byte.d, this.for + n, this.a, this.int - n, this.else, -1);
        }
    }
}
