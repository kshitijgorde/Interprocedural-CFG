// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class af
{
    public int do;
    public int case;
    public int[] new;
    public boolean for;
    public boolean int;
    public ac try;
    public boolean char;
    public int if;
    public boolean byte;
    public int a;
    
    public af() {
        this.do = 0;
        this.case = 0;
        this.new = null;
        this.for = false;
        this.int = false;
        this.try = null;
        this.char = false;
        this.if = 0;
        this.byte = false;
        this.a = 0;
    }
    
    protected void a() {
        float n = this.do;
        while (n > 1.0f) {
            n /= 2.0f;
            if (n != (int)n) {
                this.a = 0;
                return;
            }
            ++this.a;
        }
    }
    
    void a(final long n) {
        this.byte = true;
    }
}
