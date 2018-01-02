import java.util.Enumeration;
import java.awt.Polygon;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class a extends Canvas
{
    public final int a = 20;
    protected static final String[] b;
    protected static final int[] c;
    protected static final String[] d;
    protected static final int[] e;
    protected final Color[] f;
    protected final String[] g;
    protected int h;
    protected int i;
    protected int j;
    protected int k;
    protected int l;
    protected int m;
    protected int n;
    protected double o;
    protected ob p;
    protected ob q;
    protected Image r;
    protected Graphics s;
    protected boolean t;
    public static int u;
    private static final String[] z;
    
    public a() {
        this.f = new Color[] { Color.red, Color.blue, new Color(0, 128, 0), new Color(255, 128, 64) };
        this.g = new String[] { a.z[9], a.z[10], a.z[11], a.z[8] };
        this.h = 5;
        this.i = this.h * 2;
        this.j = 0;
        this.k = 4;
        this.l = 2;
        this.t = false;
        this.a((int[])null, null);
    }
    
    public void a(final int[] array, final int[] array2) {
        final int u = a.u;
        Label_0035: {
            if (array == null) {
                this.p = new ob();
                if (u == 0) {
                    break Label_0035;
                }
            }
            this.p = new ob(array);
        }
        if (array2 == null) {
            this.q = new ob();
            if (u == 0) {
                return;
            }
        }
        this.q = new ob(array2);
    }
    
    public int[][] a() {
        final int[][] array = new int[2][4];
        array[0] = this.p.c();
        array[1] = this.q.c();
        return array;
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        Label_0041: {
            if (n3 == 2 * n4) {
                this.i = 2 * this.h;
                if (a.u == 0) {
                    break Label_0041;
                }
            }
            this.i = this.h;
        }
        if (this.r != null && (this.r.getWidth(this) != n3 || this.r.getHeight(this) != n4)) {
            this.r = null;
            this.s = null;
        }
        if (n3 != 0 && n4 != 0 && this.r == null) {
            this.r = this.createImage(n3, n4);
            if (this.r != null) {
                this.s = this.r.getGraphics();
                this.f();
                this.processComponentEvent(new ComponentEvent(this, 101));
            }
        }
    }
    
    public void a(final int l) {
        this.l = l;
    }
    
    public void b(final int k) {
        this.k = k;
    }
    
    public int b() {
        return this.j;
    }
    
    public double c() {
        return this.o;
    }
    
    public void d() {
        Label_0057: {
            switch (this.j) {
                case 0:
                case 1:
                case 2:
                case 3: {
                    this.e();
                    if (a.u != 0) {
                        break;
                    }
                    break Label_0057;
                }
            }
            System.err.println(a.z[3]);
        }
        this.repaint();
    }
    
    protected void e() {
        final int u = a.u;
        final int n = 0;
        final int n2 = this.getSize().width - 1;
        final int n3 = 0;
        final int n4 = this.getSize().height - 1;
        final int h = this.h;
        final int i = this.i;
        this.s.setColor(Color.black);
        int j = 1;
        while (j < i) {
            final int n5 = (int)Math.round(j * (this.getSize().width / i));
            this.s.drawLine(n5, n3, n5, n4);
            ++j;
            if (u != 0) {
                break;
            }
        }
        int k = 1;
        while (k < h) {
            final int n6 = (int)Math.round(k * (this.getSize().height / h));
            this.s.drawLine(n, n6, n2, n6);
            ++k;
            if (u != 0) {
                break;
            }
        }
    }
    
    public void f() {
        final int u = a.u;
        final String s = a.z[5];
        final Graphics graphics = this.getGraphics();
        if (this.s == null) {
            return;
        }
        Label_0057: {
            if (this.k == 4) {
                this.j = this.q.a(4);
                if (u == 0) {
                    break Label_0057;
                }
            }
            this.j = this.k;
        }
        graphics.setFont(new Font(a.z[2], 0, 20));
        final int stringWidth = this.s.getFontMetrics().stringWidth(s);
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        graphics.setColor(Color.black);
        graphics.drawString(s, (this.getSize().width - stringWidth) / 2, this.getSize().height / 2);
        Label_0207: {
            switch (this.j) {
                case 0: {
                    this.g();
                    if (u != 0) {
                        break Label_0207;
                    }
                    return;
                }
                case 1: {
                    this.h();
                    if (u != 0) {
                        break Label_0207;
                    }
                    return;
                }
                case 2: {
                    this.i();
                    if (u != 0) {
                        break Label_0207;
                    }
                    return;
                }
                case 3: {
                    this.j();
                    if (u != 0) {
                        break;
                    }
                    return;
                }
            }
        }
        System.err.println(a.z[4]);
    }
    
    protected void g() {
        final int u = a.u;
        final Dimension[] array = new Dimension[3];
        final int min = Math.min(this.getSize().width, this.getSize().height);
        array[0] = new Dimension(min / 30, min / 30);
        array[1] = new Dimension(min / 20, min / 20);
        array[2] = new Dimension(min / 10, min / 10);
        final Dimension[] a = this.a(array);
        final int width = a[0].width;
        final Dimension[] array2 = new Dimension[a.length - 1];
        int i = 0;
        while (i < array2.length) {
            array2[i] = a[i + 1];
            ++i;
            if (u != 0) {
                break;
            }
        }
        if (width == -1) {
            return;
        }
        final wb a2 = this.a(array2, width);
        this.s.setColor(Color.white);
        this.s.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.m = this.q.a(this.f.length);
        this.n = this.q.a(a.b.length);
        this.a(a2);
        this.o = a2.a();
        this.repaint();
    }
    
    protected Dimension[] a(final Dimension[] array) {
        final int u = a.u;
        int n = -1;
        Dimension[] array2 = new Dimension[0];
        Label_0250: {
            Label_0127: {
                switch (this.l) {
                    case 0:
                    case 1: {
                        array2 = new Dimension[] { array[this.q.a(2)] };
                        final int n2 = (int)Math.round(this.getSize().width * this.getSize().height / (array2[0].width * array2[0].height));
                        n = n2 / 8 + this.p.a(4 * n2 / 9 - n2 / 8);
                        if (u != 0) {
                            break Label_0127;
                        }
                        break Label_0250;
                    }
                    case 2: {
                        array2 = new Dimension[array.length];
                        int n3 = 0;
                        int i = 0;
                        while (i < array2.length) {
                            array2[i] = array[i];
                            n3 += array2[i].width * array2[i].height;
                            ++i;
                            if (u != 0) {
                                break;
                            }
                        }
                        final int n4 = (int)Math.round(this.getSize().width * this.getSize().height / (n3 / array2.length));
                        n = n4 / 3 + this.p.a(n4 / 2 - n4 / 3);
                        if (u != 0) {
                            break;
                        }
                        break Label_0250;
                    }
                }
            }
            System.err.println(a.z[6]);
        }
        final Dimension[] array3 = new Dimension[array2.length + 1];
        array3[0] = new Dimension(n, 0);
        int j = 0;
        while (j < array2.length) {
            array3[j + 1] = array2[j];
            ++j;
            if (u != 0) {
                break;
            }
        }
        return array3;
    }
    
    protected wb a(final Dimension[] array, final int n) {
        final int u = a.u;
        final wb wb = new wb();
        int i = 0;
        while (i < n) {
            Dimension dimension;
            do {
                dimension = new Dimension(array[this.p.a(array.length)]);
            } while (!wb.a(new Rectangle(new Point(this.p.a(this.getSize().width - dimension.width), this.p.a(this.getSize().height - dimension.height)), dimension)));
            ++i;
            if (u != 0) {
                break;
            }
        }
        return wb;
    }
    
    protected void a(final wb wb) {
        final int u = a.u;
        final Color color = this.f[this.m];
        if (wb == null) {
            return;
        }
        final Rectangle b = wb.b();
        Label_1838: {
            Label_1336: {
                switch (this.n) {
                    case 0: {
                        this.s.setColor(color);
                        this.s.fillRect(b.x, b.y, b.width, b.height);
                        this.s.setColor(Color.gray);
                        this.s.drawRect(b.x, b.y, b.width - 1, b.height - 1);
                        if (u != 0) {
                            break Label_1336;
                        }
                        break Label_1838;
                    }
                    case 1: {
                        this.s.setColor(color);
                        this.s.fillOval(b.x, b.y, b.width, b.height);
                        this.s.setColor(Color.gray);
                        this.s.drawOval(b.x, b.y, b.width - 1, b.height - 1);
                        if (u != 0) {
                            break Label_1336;
                        }
                        break Label_1838;
                    }
                    case 2: {
                        this.s.setColor(color);
                        this.s.fillOval(b.x, b.y, b.width, b.height);
                        final int n = b.width / 4;
                        this.s.setColor(Color.white);
                        this.s.fillOval(b.x + n, b.y + n, b.width - 2 * n, b.height - 2 * n);
                        this.s.setColor(Color.gray);
                        this.s.drawOval(b.x, b.y, b.width - 1, b.height - 1);
                        this.s.drawOval(b.x + n, b.y + n, b.width - 2 * n - 1, b.height - 2 * n - 1);
                        if (u != 0) {
                            break Label_1336;
                        }
                        break Label_1838;
                    }
                    case 3: {
                        final int[] array = new int[3];
                        final int[] array2 = new int[3];
                        array[0] = b.x;
                        array2[0] = b.y + b.height;
                        array[1] = b.x + b.width;
                        array2[1] = b.y + b.height;
                        array[2] = b.x + Math.round(b.width / 2);
                        array2[2] = b.y + (int)Math.round(b.height * (1.0 - Math.sqrt(3.0) / 2.0));
                        this.s.setColor(color);
                        this.s.fillPolygon(array, array2, array.length);
                        this.s.setColor(Color.gray);
                        this.s.drawPolygon(array, array2, array.length);
                        if (u != 0) {
                            break Label_1336;
                        }
                        break Label_1838;
                    }
                    case 4: {
                        final int[] array3 = new int[10];
                        final int[] array4 = new int[10];
                        final double tan = Math.tan(0.6283185307179586);
                        final double tan2 = Math.tan(0.3141592653589793);
                        array3[0] = b.x + Math.round(b.width / 2);
                        array4[0] = b.y;
                        array3[2] = b.x;
                        array4[2] = b.y + (int)Math.round(tan / 2.0 * b.height);
                        array3[4] = b.x + (int)Math.round((1.0 - tan / 2.0) * tan2 * b.width);
                        array4[4] = b.y + b.height;
                        array3[6] = 2 * b.x + b.width - array3[4];
                        array4[6] = b.y + b.height;
                        array3[8] = b.x + b.width;
                        array4[8] = array4[2];
                        b.x += b.width / 4;
                        final Rectangle rectangle = b;
                        rectangle.width -= 2 * (b.width / 4);
                        b.y += b.height / 4;
                        final Rectangle rectangle2 = b;
                        rectangle2.height -= 2 * (b.height / 4);
                        array3[1] = b.x + (int)Math.round((1.0 - tan / 2.0) * tan2 * b.width);
                        array4[1] = b.y;
                        array3[3] = b.x;
                        array4[3] = b.y + b.height - (int)Math.round(tan / 2.0 * b.height);
                        array3[5] = b.x + Math.round(b.width / 2);
                        array4[5] = b.y + b.height;
                        array3[7] = b.x + b.width;
                        array4[7] = array4[3];
                        array3[9] = b.x + (int)Math.round((1.0 - (1.0 - tan / 2.0) * tan2) * b.width);
                        array4[9] = b.y;
                        this.s.setColor(color);
                        this.s.fillPolygon(array3, array4, array3.length);
                        this.s.setColor(Color.gray);
                        this.s.drawPolygon(array3, array4, array3.length);
                        if (u != 0) {
                            break Label_1336;
                        }
                        break Label_1838;
                    }
                    case 5: {
                        final int[] array5 = new int[8];
                        final int[] array6 = new int[8];
                        array5[0] = b.x + Math.round(b.width / 2);
                        array6[0] = b.y;
                        array5[2] = b.x;
                        array6[2] = b.y + Math.round(b.height / 2);
                        array5[4] = 2 * b.x + b.width - array5[0];
                        array6[4] = b.y + b.height;
                        array5[6] = b.x + b.width;
                        array6[6] = array6[2];
                        b.x += b.width / 3;
                        final Rectangle rectangle3 = b;
                        rectangle3.width -= 2 * (b.width / 3);
                        b.y += b.height / 3;
                        final Rectangle rectangle4 = b;
                        rectangle4.height -= 2 * (b.height / 3);
                        array5[1] = b.x;
                        array6[1] = b.y;
                        array5[3] = array5[1];
                        array6[3] = b.y + b.height;
                        array5[5] = b.x + b.width;
                        array6[5] = array6[3];
                        array5[7] = array5[5];
                        array6[7] = array6[1];
                        this.s.setColor(color);
                        this.s.fillPolygon(array5, array6, array5.length);
                        this.s.setColor(Color.gray);
                        this.s.drawPolygon(array5, array6, array5.length);
                        if (u != 0) {
                            break Label_1336;
                        }
                        break Label_1838;
                    }
                    case 6: {
                        final int[] array7 = new int[12];
                        final int[] array8 = new int[12];
                        array7[0] = b.x + b.width / 2;
                        array8[0] = b.y;
                        array7[2] = b.x + (int)Math.round(b.width * (0.5 - Math.sqrt(3.0) / 4.0));
                        array8[2] = b.y + b.height / 4;
                        array7[4] = array7[2];
                        array8[4] = b.y + 3 * b.height / 4;
                        array7[6] = array7[0];
                        array8[6] = b.y + b.height;
                        array7[8] = b.x + (int)Math.round(b.width * (0.5 + Math.sqrt(3.0) / 4.0));
                        array8[8] = array8[4];
                        array7[10] = array7[8];
                        array8[10] = array8[2];
                        b.x += b.width / 4;
                        final Rectangle rectangle5 = b;
                        rectangle5.width -= 2 * (b.width / 4);
                        b.y += b.height / 4;
                        final Rectangle rectangle6 = b;
                        rectangle6.height -= 2 * (b.height / 4);
                        array7[1] = b.x + b.width / 4;
                        array8[1] = b.y + (int)Math.round(b.height * (0.5 - Math.sqrt(3.0) / 4.0));
                        array7[3] = b.x;
                        array8[3] = b.y + b.height / 2;
                        array7[5] = array7[1];
                        array8[5] = b.y + (int)Math.round(b.height * (0.5 + Math.sqrt(3.0) / 4.0));
                        array7[7] = b.x + 3 * b.width / 4;
                        array8[7] = array8[5];
                        array7[9] = b.x + b.width;
                        array8[9] = array8[3];
                        array7[11] = array7[7];
                        array8[11] = array8[1];
                        this.s.setColor(color);
                        this.s.fillPolygon(array7, array8, array7.length);
                        this.s.setColor(Color.gray);
                        this.s.drawPolygon(array7, array8, array7.length);
                        if (u != 0) {
                            break;
                        }
                        break Label_1838;
                    }
                }
            }
            System.err.println(a.z[7]);
        }
        this.a(wb.c());
    }
    
    protected void h() {
        final int u = a.u;
        final Dimension[] array = new Dimension[3];
        final int min = Math.min(this.getSize().width, this.getSize().height);
        array[0] = new Dimension(min / 30, min / 30);
        array[1] = new Dimension(min / 20, min / 20);
        array[2] = new Dimension(min / 10, min / 10);
        final Dimension[] a = this.a(array);
        final int width = a[0].width;
        final Dimension[] array2 = new Dimension[a.length - 1];
        int i = 0;
        while (i < array2.length) {
            array2[i] = a[i + 1];
            ++i;
            if (u != 0) {
                break;
            }
        }
        if (width == -1) {
            return;
        }
        final wb a2 = this.a(array2, width);
        this.s.setColor(Color.white);
        this.s.fillRect(0, 0, this.getSize().width, this.getSize().height);
        final int a3 = this.q.a(a.c.length);
        this.n = a.c[a3];
        this.m = this.q.a(this.f.length);
        this.a(a2);
        this.o = a2.a() * a.e[a3];
        this.repaint();
    }
    
    protected void i() {
        int u = a.u;
        final int n = this.getSize().height / 2;
        final int n2 = this.getSize().width / 2 + this.p.a(this.getSize().width / 2);
        final int n3 = (this.getSize().height - n) / 2;
        final int n4 = n3 + n;
        final int n5 = (this.getSize().width - n2) / 2;
        final int n6 = n5 + n2;
        final int n7 = (int)Math.round(this.getSize().height / this.h);
        int n8 = 0;
        int n9 = 0;
        Label_0166: {
            switch (this.l) {
                default: {
                    System.err.println(a.z[12]);
                }
                case 0: {
                    n8 = 12;
                    n9 = 20;
                    if (u != 0) {
                        break Label_0166;
                    }
                    break;
                }
                case 1: {
                    n8 = 14;
                    n9 = 20;
                    if (u != 0) {
                        break Label_0166;
                    }
                    break;
                }
                case 2: {
                    n8 = 20;
                    n9 = 10;
                    break;
                }
            }
        }
        final int n10 = 1 * n8 / 2 + this.p.a(n8 / 2);
        final int[] array = new int[(n6 - n5) / 10 + 1];
        int i = 0;
        while (i < array.length - 1) {
            array[i] = n5 + i * 10;
            ++i;
            if (u != 0) {
                break;
            }
        }
        array[array.length - 1] = n6;
        boolean b;
        int[] a3;
        do {
            final Vector vector = new Vector<Point>(n10);
            vector.addElement(new Point(n5, n3 + this.p.a(n4 - n3)));
            int j = 1;
            while (j < n10 - 1) {
                vector.addElement(new Point(n5 + this.p.a(n6 - n5), n3 + this.p.a(n4 - n3)));
                ++j;
                if (u != 0) {
                    break;
                }
            }
            vector.addElement(new Point(n6, n3 + this.p.a(n4 - n3)));
            final Point[] a = this.a(vector);
            rb.a(a, new vb());
            final Vector a2 = this.a(a);
            if (a2.size() > 1) {
                Point firstElement = a2.firstElement();
                int k = 1;
                while (k < a2.size() - 1) {
                    final Point element = a2.elementAt(k);
                    if (element.x - firstElement.x < n9) {
                        a2.removeElement(element);
                        if (u == 0) {
                            continue;
                        }
                    }
                    firstElement = element;
                    ++k;
                    if (u != 0) {
                        break;
                    }
                }
                if (a2.lastElement().x - a2.elementAt(a2.size() - 1).x < n9) {
                    a2.removeElementAt(a2.size() - 2);
                }
            }
            a3 = this.a(a2, array);
            b = true;
            int l = 0;
            while (l < a3.length) {
                if (a3[l] < n7 || a3[l] > this.getSize().height) {
                    b = false;
                }
                ++l;
                if (u != 0) {
                    break;
                }
            }
        } while (!b);
        this.o = 0.0;
        int n11 = 0;
        while (n11 < array.length - 1) {
            this.o += this.a(array[n11], a3[n11], array[n11 + 1], a3[n11 + 1]);
            ++n11;
            if (u != 0) {
                break;
            }
        }
        final double n12 = Math.round(this.getSize().height / this.h);
        final int n13 = 1 + this.q.a(4);
        this.o = this.o / n12 * n13;
        this.s.setColor(Color.white);
        this.s.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.m = this.q.a(this.f.length);
        this.s.setColor(this.f[this.m]);
        qb.a(this.s, array, a3, 3);
        final int n14 = (int)Math.round(this.getSize().width / this.i);
        final int n15 = (int)Math.round(this.getSize().height / this.h);
        final String string = a.z[1] + n13 + a.z[13] + ((n13 == 1) ? "" : "s");
        this.s.setColor(this.f[this.m]);
        int n16 = 0;
        while (n16 < 4) {
            this.s.drawLine(n14 + n16 - 2, 0, n14 + n16 - 2, n15);
            ++n16;
            if (u != 0) {
                break;
            }
        }
        this.s.setColor(Color.black);
        this.s.setFont(new Font(a.z[2], 0, 20));
        this.s.drawString(" " + string, n14 + 3, (n15 + this.s.getFontMetrics().getAscent()) / 2);
        this.repaint();
        if (q.a != 0) {
            a.u = ++u;
        }
    }
    
    protected Point[] a(final Vector vector) {
        final int u = a.u;
        final Point[] array = new Point[vector.size()];
        int i = 0;
        while (i < vector.size()) {
            array[i] = vector.elementAt(i);
            ++i;
            if (u != 0) {
                break;
            }
        }
        return array;
    }
    
    protected Vector a(final Object[] array) {
        final int u = a.u;
        final Vector<Object> vector = new Vector<Object>(array.length);
        int i = 0;
        while (i < array.length) {
            vector.addElement(array[i]);
            ++i;
            if (u != 0) {
                break;
            }
        }
        return vector;
    }
    
    protected int[] a(final Vector vector, final int[] array) {
        final int u = a.u;
        final int size = vector.size();
        final double[] array2 = new double[size];
        final double[] array3 = new double[size];
        final double[] array4 = new double[size];
        final double[] array5 = new double[size];
        int i = 0;
        while (i < vector.size()) {
            final Point point = vector.elementAt(i);
            array2[i] = point.x;
            array3[i] = point.y;
            ++i;
            if (u != 0) {
                break;
            }
        }
        array5[0] = (array4[0] = 0.0);
        int j = 1;
        while (j < size - 1) {
            final double n = (array2[j] - array2[j - 1]) / (array2[j + 1] - array2[j - 1]);
            final double n2 = n * array4[j - 1] + 2.0;
            array4[j] = (n - 1.0) / n2;
            array5[j] = (6.0 * ((array3[j + 1] - array3[j]) / (array2[j + 1] - array2[j]) - (array3[j] - array3[j - 1]) / (array2[j] - array2[j - 1])) / (array2[j + 1] - array2[j - 1]) - n * array5[j - 1]) / n2;
            ++j;
            if (u != 0) {
                break;
            }
        }
        array4[size - 1] = 0.0;
        int k = size - 2;
        while (k > -1) {
            array4[k] = array4[k] * array4[k + 1] + array5[k];
            --k;
            if (u != 0) {
                break;
            }
        }
        int n3 = 0;
        int n4 = 1;
        final int[] array6 = new int[array.length];
        int l = 0;
        while (l < array.length) {
            while (array[l] > array2[n4]) {
                ++n3;
                ++n4;
                if (u != 0) {
                    break;
                }
            }
            final double n5 = array2[n4] - array2[n3];
            final double n6 = (array2[n4] - array[l]) / n5;
            final double n7 = (array[l] - array2[n3]) / n5;
            array6[l] = (int)Math.round(n6 * array3[n3] + n7 * array3[n4] + ((n6 * n6 * n6 - n6) * array4[n3] + (n7 * n7 * n7 - n7) * array4[n4]) * n5 * n5 / 6.0);
            ++l;
            if (u != 0) {
                break;
            }
        }
        return array6;
    }
    
    protected double a(final int n, final int n2, final int n3, final int n4) {
        return Math.sqrt((n - n3) * (n - n3) + (n2 - n4) * (n2 - n4));
    }
    
    protected void j() {
        final int u = a.u;
        final int n = 6 + this.p.a(16 - 6);
        final int n2 = this.getSize().height / 8;
        final int n3 = this.getSize().width / 8;
        final Vector<Point> vector = new Vector<Point>();
        Vector b = new Vector<Point>();
        final int n4 = (int)Math.round(this.getSize().height / this.h);
        final int height = this.getSize().height;
        final int n5 = (int)Math.round(this.getSize().width / this.i);
        final int width = this.getSize().width;
        int i = 0;
        while (i < n / 2) {
            final int n6 = n5 + this.p.a((width - n5) / 2);
            final int n7 = n4 + this.p.a((height - n4) / 2);
            vector.addElement(new Point(n6, n7));
            vector.addElement(new Point(n6 + this.p.a((width - n5) / 2), n7 + this.p.a((height - n4) / 2)));
            ++i;
            if (u != 0) {
                break;
            }
        }
        final Vector b2 = this.b(vector);
        final int[] array = new int[b2.size()];
        final int[] array2 = new int[b2.size()];
        int j = 0;
        while (j < array.length) {
            final Point point = b2.elementAt(j);
            array[j] = point.x;
            array2[j] = point.y;
            ++j;
            if (u != 0) {
                break;
            }
        }
        final Polygon polygon = new Polygon(array, array2, array.length);
        Polygon polygon2;
        if (this.l == 2) {
            final Rectangle bounds = polygon.getBounds();
            final int n8 = 3 + this.p.a(4);
            int k = 0;
            while (k < n8) {
                int n9;
                int n10;
                do {
                    n9 = bounds.x + this.p.a(bounds.width);
                    n10 = bounds.y + this.p.a(bounds.height);
                } while (!polygon.contains(n9, n10));
                b.addElement(new Point(n9, n10));
                ++k;
                if (u != 0) {
                    break;
                }
            }
            b = this.b(b);
            final int[] array3 = new int[b.size()];
            final int[] array4 = new int[b.size()];
            int l = 0;
            while (l < b.size()) {
                final Point point2 = b.elementAt(l);
                array3[l] = point2.x;
                array4[l] = point2.y;
                ++l;
                if (u != 0) {
                    break;
                }
            }
            polygon2 = new Polygon(array3, array4, array3.length);
        }
        else {
            polygon2 = new Polygon();
        }
        final double n11 = this.getSize().height / this.h * (this.getSize().width / this.i);
        final int n12 = 1 + this.q.a(4);
        this.o = (this.c(b2) - this.c(b)) / n11 * n12;
        this.s.setColor(Color.white);
        this.s.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.m = this.q.a(this.f.length);
        this.s.setColor(this.f[this.m]);
        this.s.fillPolygon(polygon);
        this.s.setColor(Color.white);
        this.s.fillPolygon(polygon2);
        this.s.setColor(Color.darkGray);
        this.s.drawPolygon(polygon);
        this.s.drawPolygon(polygon2);
        final int n13 = 0;
        final int n14 = 0;
        final int n15 = (int)Math.round(this.getSize().width / this.i);
        final int n16 = (int)Math.round(this.getSize().height / this.h);
        this.s.setColor(this.f[this.m]);
        this.s.fillRect(n13, n14, n15, n16);
        this.s.setColor(Color.black);
        this.s.setFont(new Font(a.z[2], 0, 20));
        this.s.drawString(a.z[1] + n12 + a.z[0] + ((n12 == 1) ? "" : "s"), n15, (n16 + this.s.getFontMetrics().getAscent()) / 2);
        this.repaint();
    }
    
    protected Vector b(final Vector vector) {
        final int u = a.u;
        final Vector vector2 = new Vector<Point>(vector.size());
        final Vector vector3 = (Vector)vector.clone();
        final Vector vector4 = new Vector<Point>(vector.size() - 1);
        final int size = vector.size();
        if (size < 4) {
            return vector;
        }
        double n = vector3.elementAt(0).y;
        int n2 = 0;
        int i = 0;
        while (i < size) {
            final double n3 = vector3.elementAt(i).y;
            Label_0160: {
                if (n3 > n) {
                    n = n3;
                    n2 = i;
                    if (u == 0) {
                        break Label_0160;
                    }
                }
                if (n3 == n && vector3.elementAt(i).x > vector3.elementAt(n2).x) {
                    n = n3;
                    n2 = i;
                }
            }
            ++i;
            if (u != 0) {
                break;
            }
        }
        final Point point = vector3.elementAt(n2);
        vector2.addElement(point);
        vector3.removeElementAt(n2);
        final double[] array = new double[size - 1];
        int j = 0;
        while (j < vector3.size()) {
            array[j] = this.a(point, vector3.elementAt(j));
            ++j;
            if (u != 0) {
                break;
            }
        }
        int n4 = 0;
        double n5 = 6.283185307179586;
        int k = 0;
        while (k < size - 1) {
            int l = 0;
            while (l < size - 1) {
                if (array[l] < n5) {
                    n5 = array[l];
                    n4 = l;
                }
                ++l;
                if (u != 0) {
                    break;
                }
            }
            vector4.addElement(vector3.elementAt(n4));
            array[n4] = 9.42477796076938;
            n5 = 6.283185307179586;
            ++k;
            if (u != 0) {
                break;
            }
        }
        int n6 = 0;
        int n7 = 1;
        vector2.addElement(vector4.elementAt(0));
        vector4.removeElementAt(0);
        int n8 = 0;
        while (n8 < vector4.size()) {
            while (this.b(vector2.elementAt(n6), vector2.elementAt(n7), vector4.elementAt(n8)) < 3.141592653589793) {
                vector2.removeElementAt(n7);
                --n7;
                --n6;
                if (u != 0) {
                    break;
                }
            }
            vector2.addElement(vector4.elementAt(n8));
            ++n6;
            ++n7;
            ++n8;
            if (u != 0) {
                break;
            }
        }
        return vector2;
    }
    
    protected double c(final Vector vector) {
        final int u = a.u;
        double n = 0.0;
        double n2 = 0.0;
        double n3 = 0.0;
        if (vector.size() <= 2) {
            return 0.0;
        }
        final Enumeration<Point> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final Point point = elements.nextElement();
            n += point.x;
            n2 += point.y;
            if (u != 0) {
                break;
            }
        }
        final Point point2 = new Point((int)Math.round(n / vector.size()), (int)Math.round(n2 / vector.size()));
        final Enumeration<Point> elements2 = vector.elements();
        Point point3 = elements2.nextElement();
        while (elements2.hasMoreElements()) {
            final Point point4 = elements2.nextElement();
            n3 += this.a(point3, point4, point2);
            point3 = point4;
            if (u != 0) {
                break;
            }
        }
        return n3 + this.a(point3, vector.firstElement(), point2);
    }
    
    protected double a(final Point point, final Point point2, final Point point3) {
        final double sqrt = Math.sqrt((point.x - point2.x) * (point.x - point2.x) + (point.y - point2.y) * (point.y - point2.y));
        return 0.5 * sqrt * (Math.abs((point2.y - point.y) * (point3.x - point.x) - (point2.x - point.x) * (point3.y - point.y)) / sqrt);
    }
    
    public double a(final Point point, final Point point2) {
        double atan2 = Math.atan2(point2.y - point.y, point2.x - point.x);
        if (point2.y - point.y < 0) {
            atan2 += 6.283185307179586;
        }
        return atan2;
    }
    
    public double b(final Point point, final Point point2, final Point point3) {
        final double a = this.a(point2, point);
        final double a2 = this.a(point2, point3);
        return (a <= a2) ? (a2 - a) : (6.283185307179586 - a + a2);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.r, 0, 0, this);
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
    }
    
    static {
        z = new String[] { z(z("){\u001b\u0002D{mJ\u0002K`|")), z(z(")5J")), z(z("Zm\u0018\u001eC")), z(z("@f\u001c\u0016I`lJ\u0003\\ymJ\u001eK)M\u0019\u0003Ldi\u001e\u0012fhf\u001c\u0016V'o\u000f\u0003m`f\u001e_Lg|C")), z(z("@f\u001c\u0016I`lJ\u0007Wfj\u0006\u0012H)|\u0013\u0007@'")), z(z("Yd\u000f\u0016Vl(\u001d\u0016L}&DY")), z(z("@f\u001c\u0016I`lJ\u0013Lon\u0003\u0014Pe|\u0013WLg(\r\u0012QG}\u0007\u0015@{")), z(z("Ki\u000eWVai\u001a\u0012\u0005`fJ\u0013Wh\u007f9\u001fDym\u0019_\f")), z(z("fz\u000b\u0019Bl")), z(z("{m\u000e")), z(z("kd\u001f\u0012")), z(z("nz\u000f\u0012K")), z(z("@f\u001c\u0016I`lJ\u0013Lon\u0003\u0014Pe|\u0013WLg(\u0004\u0012REm\u0004\u0010QaX\u0018\u0018G!!")), z(z(")}\u0004\u001eQ")) };
        b = new String[] { z(z("zy\u001f\u0016Wl{")), z(z("ja\u0018\u0014Il{")), z(z("{a\u0004\u0010V")), z(z("}z\u0003\u0016Knd\u000f\u0004")), z(z("z|\u000b\u0005V")), z(z("z|\u000b\u0005V")), z(z("z|\u000b\u0005V")) };
        c = new int[] { 0, 3, 4, 5, 6 };
        d = new String[] { z(z("jg\u0018\u0019@{{")), z(z("jg\u0018\u0019@{{")), z(z("z|\u000b\u0005\u0005yg\u0003\u0019Qz")), z(z("z|\u000b\u0005\u0005yg\u0003\u0019Qz")), z(z("z|\u000b\u0005\u0005yg\u0003\u0019Qz")) };
        e = new int[] { 4, 3, 5, 4, 6 };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '%';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\t';
                    break;
                }
                case 1: {
                    c2 = '\b';
                    break;
                }
                case 2: {
                    c2 = 'j';
                    break;
                }
                case 3: {
                    c2 = 'w';
                    break;
                }
                default: {
                    c2 = '%';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
