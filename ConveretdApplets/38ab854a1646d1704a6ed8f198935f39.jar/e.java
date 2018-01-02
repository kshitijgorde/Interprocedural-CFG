// 
// Decompiled by Procyon v0.5.30
// 

public class e extends u.a implements k
{
    o case;
    int else;
    int char;
    
    public e(final o case1, final int n) {
        super(n);
        this.else = 0;
        this.char = 0;
        case1.a(this);
        this.case = case1;
    }
    
    public void a(final double n) {
        ++this.else;
        this.else %= ((u.a)this).do();
        super.a[this.else] = n;
        if (this.char < ((u.a)this).do()) {
            ++this.char;
        }
        ((u.a)this).for();
    }
    
    public double a(final int n) throws s {
        if (n >= this.char) {
            throw new s();
        }
        return super.a[(((u.a)this).do() + this.else - n) % ((u.a)this).do()];
    }
    
    public String a() {
        return this.case.do();
    }
    
    public String if() {
        return this.case.for();
    }
    
    public o a(final a a, final int n, final int n2) {
        return new a(a, n, n2);
    }
    
    private class a extends o.a implements w
    {
        a i;
        int k;
        int j;
        
        public a(final a i, final int k, final int j) {
            ((u.a)e.this).a(this);
            this.i = i;
            this.k = k;
            this.j = j;
        }
        
        public double if() throws s {
            return this.i.a(e.this, this.k, this.j);
        }
        
        public void new() {
            ((o.a)this).int();
        }
        
        public String do() {
            return String.valueOf(this.i.toString()) + "(" + e.this.a() + ")";
        }
        
        public String for() {
            return e.this.if();
        }
    }
}
