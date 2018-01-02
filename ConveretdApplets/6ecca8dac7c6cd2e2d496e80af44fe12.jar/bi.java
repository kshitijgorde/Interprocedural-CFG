// 
// Decompiled by Procyon v0.5.30
// 

public class bi
{
    private int a;
    private String[] b;
    private int c;
    private int d;
    private int e;
    
    public bi(final int a) {
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.a = a;
        this.b = new String[this.a];
    }
    
    public void a(final String s) {
        this.b[this.d++] = new String(s);
        if (this.d == this.a) {
            this.d = 0;
        }
        if (this.d == this.c) {
            ++this.c;
        }
        if (this.c == this.a) {
            this.c = 0;
        }
        this.e = this.d;
        this.b[this.d] = "";
    }
    
    public String a() {
        if (this.e != this.c) {
            this.e = (this.e - 1 + this.a) % this.a;
        }
        return this.b[this.e];
    }
    
    public String b() {
        if (this.e != this.d) {
            this.e = (this.e + 1) % this.a;
        }
        return this.b[this.e];
    }
}
