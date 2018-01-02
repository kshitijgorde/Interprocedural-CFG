// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import com.sygem.jazz3d3.primitive.Line3d;
import com.sygem.jazz3d3.Vertex;
import com.sygem.jazz3d3.Object3d;
import java.util.Vector;

public class a
{
    private int[][] if;
    private int a;
    
    a() {
        this.a = 0;
        this.if = new int[16][3];
        this.if[0][0] = 255;
        this.if[0][1] = 0;
        this.if[0][2] = 0;
        this.if[1][0] = 255;
        this.if[1][1] = 255;
        this.if[1][2] = 0;
        this.if[2][0] = 128;
        this.if[2][1] = 255;
        this.if[2][2] = 0;
        this.if[3][0] = 0;
        this.if[3][1] = 255;
        this.if[3][2] = 255;
        this.if[4][0] = 255;
        this.if[4][1] = 0;
        this.if[4][2] = 255;
        this.if[5][0] = 0;
        this.if[5][1] = 128;
        this.if[5][2] = 255;
        this.if[6][0] = 255;
        this.if[6][1] = 128;
        this.if[6][2] = 64;
        this.if[7][0] = 0;
        this.if[7][1] = 193;
        this.if[7][2] = 97;
        this.if[8][0] = 177;
        this.if[8][1] = 171;
        this.if[8][2] = 58;
        this.if[9][0] = 255;
        this.if[9][1] = 159;
        this.if[9][2] = 159;
        this.if[10][0] = 224;
        this.if[10][1] = 171;
        this.if[10][2] = 228;
        this.if[11][0] = 168;
        this.if[11][1] = 84;
        this.if[11][2] = 0;
        this.if[12][0] = 210;
        this.if[12][1] = 210;
        this.if[12][2] = 210;
        this.if[13][0] = 172;
        this.if[13][1] = 213;
        this.if[13][2] = 197;
        this.if[14][0] = 128;
        this.if[14][1] = 104;
        this.if[14][2] = 221;
        this.if[15][0] = 194;
        this.if[15][1] = 168;
        this.if[15][2] = 131;
    }
    
    public int[] a() {
        this.a = (this.a + 1) % 16;
        return this.if[this.a];
    }
    
    public int[] a(final int n) {
        this.a = n % 16;
        return this.if[this.a];
    }
    
    public int a(final int n, final int n2, final int n3) {
        return n2 + (n - n2 + 1) % (n3 - n2 + 1);
    }
    
    public int if(final int n, final int n2, final int n3) {
        return n3 - (n3 - n + 1) % (n3 - n2 + 1);
    }
    
    public static String[] a(String substring, final String s) {
        int n = 0;
        final int n2 = 0;
        final Vector<String> vector = new Vector<String>();
        for (int i = substring.indexOf(s, n2); i != -1; i = substring.indexOf(s)) {
            if (!substring.substring(0, i).trim().equalsIgnoreCase("")) {
                vector.addElement(substring.substring(0, i));
                ++n;
            }
            substring = substring.substring(i + 1);
        }
        if (!substring.equalsIgnoreCase("")) {
            vector.addElement(substring);
            ++n;
        }
        if (n > 0) {
            final String[] array = new String[n];
            for (int j = 0; j < n; ++j) {
                array[j] = vector.elementAt(j).toString();
            }
            return array;
        }
        return null;
    }
    
    public static boolean a(final Object3d object3d, final Vertex vertex) {
        final Vertex vertex2 = new Vertex(vertex.getX(), vertex.getY(), vertex.getZ() - 0.01);
        return object3d.pick(vertex2, new Vertex(vertex2.getX(), vertex2.getY(), -1000.0));
    }
    
    public static Vertex a(final Line3d line3d) {
        return new Vertex((((Object3d)line3d).getVertex(0).getX() + ((Object3d)line3d).getVertex(1).getX()) / 2.0, (((Object3d)line3d).getVertex(0).getY() + ((Object3d)line3d).getVertex(1).getY()) / 2.0, (((Object3d)line3d).getVertex(0).getZ() + ((Object3d)line3d).getVertex(1).getZ()) / 2.0);
    }
    
    public static void a(final Graphics graphics, final int n, final int n2, final Object3d object3d, final Font font, final Color color, final Vertex vertex, final String s, final boolean b, final boolean b2, final boolean b3) {
        graphics.setFont(font);
        graphics.setColor(color);
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        final double n3 = n / 2 + n * vertex.getX() / 1.715 - fontMetrics.stringWidth(s) / 2;
        double n4 = n2 / 2 - n2 * vertex.getY() / 1.715 + 6.93359375;
        if (b3) {
            n4 -= 0.45 * fontMetrics.getHeight();
        }
        if (!a(object3d, vertex)) {
            if (b) {
                graphics.setColor(Color.black);
                graphics.drawString(s, (int)n3 + 1, (int)n4 + 1);
            }
            if (b2) {
                graphics.setColor(Color.white);
                graphics.drawString(s, (int)n3 - 1, (int)n4 - 1);
            }
            graphics.setColor(color);
            graphics.drawString(s, (int)n3, (int)n4);
        }
    }
    
    public static void a(final Graphics graphics) {
        try {
            graphics.setColor(Color.black);
            graphics.setColor(Color.white);
        }
        catch (Exception ex) {
            System.err.println("testcolor:  " + ex.toString());
        }
    }
    
    public static b a(final Vertex vertex) {
        return new b(vertex.getX(), vertex.getY(), vertex.getZ());
    }
    
    public static void a(final Object3d object3d, final b b, final double n) {
        final Vertex[] vertexArray = object3d.getVertexArray();
        for (int i = 0; i < vertexArray.length; ++i) {
            a(vertexArray[i], b, n);
        }
    }
    
    public static void a(final Vertex vertex, final b b, final double n) {
        final e e = new e(b, n);
        final b a = a(vertex);
        e.a(a);
        vertex.set(a.a, a.do, a.if);
    }
    
    public static void a(final Vertex vertex, final b b, final b b2) {
        a(vertex);
        a(vertex, b.for(b2), b.int(b2));
    }
}
