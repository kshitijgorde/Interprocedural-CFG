// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.awt.Graphics;
import com.sygem.jazz3d3.Vertex;
import java.awt.Font;
import com.sygem.jazz3d3.Object3d;
import com.sygem.jazz3d3.primitive.Freeform3d;

public class d extends Freeform3d
{
    private int if;
    public Object3d for;
    public String[] int;
    public String[] do;
    private int new;
    private Font a;
    public int try;
    
    public d(final double n, final double n2, final double n3, final int if1) {
        super(n, n2, n3);
        this.new = 12;
        this.try = 0;
        this.for = new Object3d(n, n2, n3);
        this.if = if1;
        this.int = new String[if1];
        this.do = new String[if1];
        this.a = new Font("Courier", 0, this.new);
    }
    
    public void a(final double n, final double n2, final double n3) {
        this.for.rotateWorld(n, n2, n3);
    }
    
    public void a(final e e) {
        final b b = new b(0.0, 0.0, 0.0);
        final Vertex[] vertexArray = this.for.getVertexArray();
        for (int i = 0; i < this.if; ++i) {
            b.a(vertexArray[i].getX(), vertexArray[i].getY(), vertexArray[i].getZ());
            e.a(b);
            vertexArray[i].setX(b.a);
            vertexArray[i].setY(b.do);
            vertexArray[i].setZ(b.if);
        }
    }
    
    public void a(final float n, final float n2, final float n3) {
        this.for.scaleObject((double)n, (double)n2, (double)n3);
    }
    
    public void a(final double n) {
        this.for.scaleObject(n, n, n);
    }
    
    public void a() {
        ++this.new;
        this.a = new Font("Courier", 0, this.new);
    }
    
    public void if() {
        if (this.new > 0) {
            --this.new;
        }
        this.a = new Font("Courier", 0, this.new);
    }
    
    public void a(final int n, final int n2, final int n3, final float n4, final float n5, final float n6) {
        this.for.addVertex(this.try, n4, n5, n6);
        this.a(n, n2, n3, this.try);
        ++this.try;
    }
    
    private void a(final int n, final int n2, final int n3, final int n4) {
        final int abs = Math.abs(n);
        final int abs2 = Math.abs(n2);
        final int abs3 = Math.abs(n3);
        final String string = Integer.toString(abs);
        final String string2 = Integer.toString(abs2);
        final String string3 = Integer.toString(abs3);
        String s = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        if (abs > 9) {
            s = ".";
            s3 = " ";
        }
        if (abs2 > 9) {
            s = ".";
            s2 = ".";
            s3 = " ";
            s4 = " ";
        }
        if (abs3 > 9) {
            s2 = ".";
            s4 = " ";
        }
        String s5;
        if (n < 0) {
            s5 = "-";
            if (n < -9) {
                s5 = String.valueOf(s5) + "-";
            }
        }
        else {
            s5 = " ";
            if (n > 9) {
                s5 = String.valueOf(s5) + " ";
            }
        }
        String s6;
        if (n2 < 0) {
            s6 = "-";
            if (n2 < -9) {
                s6 = String.valueOf(s6) + "-";
            }
        }
        else {
            s6 = " ";
            if (n2 > 9) {
                s6 = String.valueOf(s6) + " ";
            }
        }
        String s7;
        if (n3 < 0) {
            s7 = "-";
            if (n3 < -9) {
                s7 = String.valueOf(s7) + "-";
            }
        }
        else {
            s7 = " ";
            if (n3 > 9) {
                s7 = String.valueOf(s7) + " ";
            }
        }
        this.int[n4] = String.valueOf(string) + s + string2 + s2 + string3;
        this.do[n4] = String.valueOf(s5) + s3 + s6 + s4 + s7;
    }
    
    public void a(final int n, final int n2, final Graphics graphics, final Object3d object3d) {
        final Vertex[] vertexArray = this.for.getVertexArray();
        for (int i = 0; i < this.if; ++i) {
            a.a.a(graphics, n2, n, object3d, this.a, Color.yellow, vertexArray[i], this.int[i], true, false, false);
            a.a.a(graphics, n2, n, object3d, this.a, Color.yellow, vertexArray[i], this.do[i], true, false, true);
        }
    }
}
