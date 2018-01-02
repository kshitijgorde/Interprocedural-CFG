// 
// Decompiled by Procyon v0.5.30
// 

package a.a.b.a;

import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;

public class a implements Serializable
{
    static final long int = 5261460716622152494L;
    public int if;
    public int for;
    public int do;
    public int a;
    
    public a(final Point point) {
        this(point.x, point.y);
    }
    
    public a(final int if1, final int for1) {
        this.do = -1;
        this.a = -1;
        this.if = if1;
        this.for = for1;
    }
    
    public a(final Point point, final Dimension dimension) {
        this.do = -1;
        this.a = -1;
        this.if = point.x;
        this.for = point.y;
        if (dimension != null) {
            this.do = dimension.width;
            this.a = dimension.height;
        }
    }
    
    public a(final int if1, final int for1, final int do1, final int a) {
        this.do = -1;
        this.a = -1;
        this.if = if1;
        this.for = for1;
        this.do = do1;
        this.a = a;
    }
    
    public int do() {
        return this.if;
    }
    
    public int a() {
        return this.for;
    }
    
    public int if() {
        return this.do;
    }
    
    public int for() {
        return this.a;
    }
    
    public String toString() {
        return super.toString() + " [x=" + this.if + ", y=" + this.for + ", width=" + this.do + ", height=" + this.a + "]";
    }
}
