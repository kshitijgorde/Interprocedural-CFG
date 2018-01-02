// 
// Decompiled by Procyon v0.5.30
// 

package de.ts.b.a;

import java.awt.Font;

public class c extends a
{
    String char;
    int case;
    int else;
    
    public c() {
        this.char = null;
        this.case = 0;
        this.else = 0;
    }
    
    public c(final String char1, final int case1, final int else1) {
        this.char = null;
        this.case = 0;
        this.else = 0;
        this.char = char1;
        this.case = case1;
        this.else = else1;
    }
    
    public Font if() {
        return new Font(this.char, this.case, this.else);
    }
}
