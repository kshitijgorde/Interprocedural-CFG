// 
// Decompiled by Procyon v0.5.30
// 

package dessin;

import java.awt.Point;
import java.io.Serializable;

public class i implements Serializable
{
    int do;
    int for;
    int if;
    int a;
    
    boolean a(final int n, final int n2) {
        return n2 >= this.a && n2 <= this.do && n >= this.if && n <= this.for;
    }
    
    int if() {
        return Math.abs(this.a - this.do);
    }
    
    int a() {
        return Math.abs(this.if - this.for);
    }
    
    void a(final Point point, final Point point2) {
        this.if = ((point.x < point2.x) ? point.x : point2.x);
        this.for = ((point.x > point2.x) ? point.x : point2.x);
        this.a = ((point.y < point2.y) ? point.y : point2.y);
        this.do = ((point.y > point2.y) ? point.y : point2.y);
    }
    
    void a(final int n, final int n2, final int n3, final int n4) {
        this.if = ((n < n3) ? n : n3);
        this.for = ((n > n3) ? n : n3);
        this.a = ((n2 < n4) ? n2 : n4);
        this.do = ((n2 > n4) ? n2 : n4);
    }
}
