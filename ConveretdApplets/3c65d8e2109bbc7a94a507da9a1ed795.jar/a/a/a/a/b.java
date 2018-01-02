// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.io.Serializable;

public class b implements Cloneable, Serializable
{
    int if;
    int for;
    int do;
    int a;
    
    public b() {
        this(0, 0, 0, 0);
    }
    
    public b(final int if1, final int for1, final int do1, final int a) {
        this.if = if1;
        this.for = for1;
        this.do = do1;
        this.a = a;
    }
    
    public int do() {
        return this.if;
    }
    
    public void do(final int if1) {
        this.if = if1;
    }
    
    public int a() {
        return this.for;
    }
    
    public void a(final int for1) {
        this.for = for1;
    }
    
    public int if() {
        return this.do;
    }
    
    public void for(final int do1) {
        this.do = do1;
    }
    
    public int for() {
        return this.a;
    }
    
    public void if(final int a) {
        this.a = a;
    }
    
    public int hashCode() {
        return this.if ^ this.for * 37 ^ this.do * 43 ^ this.a * 47;
    }
    
    public boolean equals(final Object o) {
        if (o instanceof b) {
            final b b = (b)o;
            return b.if == this.if && b.for == this.for && b.do == this.do && b.a == this.a;
        }
        return false;
    }
    
    public Object clone() {
        return new b(this.if, this.for, this.do, this.a);
    }
    
    public String toString() {
        return "XYConstraints[" + this.if + "," + this.for + "," + this.do + "," + this.a + "]";
    }
}
