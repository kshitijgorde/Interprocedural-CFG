// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.Image;

public class ar
{
    public int do;
    public int else;
    public int[] new;
    public boolean for;
    public boolean int;
    public an byte;
    public boolean goto;
    public int if;
    public boolean case;
    public int a;
    public Image char;
    public Image try;
    
    public ar() {
        this.do = 0;
        this.else = 0;
        this.new = null;
        this.for = false;
        this.int = false;
        this.byte = null;
        this.goto = false;
        this.if = 0;
        this.case = false;
        this.a = 0;
        this.char = null;
        this.try = null;
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
        this.case = true;
    }
}
