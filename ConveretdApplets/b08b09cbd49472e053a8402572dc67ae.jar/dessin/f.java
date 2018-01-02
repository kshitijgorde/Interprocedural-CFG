// 
// Decompiled by Procyon v0.5.30
// 

package dessin;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Polygon;
import java.awt.Point;
import java.awt.Color;

public class f extends m
{
    Color goto;
    float else;
    static final int long = 3;
    Point byte;
    Point do;
    Point new;
    int char;
    boolean int;
    Polygon try;
    int[] for;
    int[] case;
    
    public f(final int[] for1, final int[] case1, final Color goto1, final float else1, final boolean int1) {
        this.goto = Color.black;
        this.else = 1.0f;
        this.int = false;
        this.char = 20;
        this.goto = goto1;
        this.else = else1;
        this.int = int1;
        this.for = for1;
        this.case = case1;
        this.do = new Point(for1[0], case1[0]);
        this.byte = new Point(for1[1], case1[1]);
        this.new = new Point(for1[2], case1[2]);
        this.try = new Polygon(for1, case1, 3);
    }
    
    public f(final Point point, final Point byte1, final Color goto1, final float else1, final boolean int1) {
        this.goto = Color.black;
        this.else = 1.0f;
        this.int = false;
        this.char = 20;
        this.goto = goto1;
        this.else = else1;
        this.int = int1;
        this.do = point;
        this.byte = byte1;
        this.new = point;
        this.int();
    }
    
    public f(final int n, final int n2, final int n3, final int n4) {
        this.goto = Color.black;
        this.else = 1.0f;
        this.int = false;
        this.char = 20;
    }
    
    public f(final int n, final int n2, final Color goto1, final float else1, final boolean int1) {
        this.goto = Color.black;
        this.else = 1.0f;
        this.int = false;
        this.char = 20;
        this.goto = goto1;
        this.else = else1;
        this.int = int1;
        this.do = new Point(n, n2);
        this.byte = new Point(n, n2);
        this.new = new Point(n, n2);
        this.int();
    }
    
    m do(final int n, final int n2) {
        if (this.try.getBounds().contains(n, n2)) {
            return this;
        }
        return null;
    }
    
    void a(final Vector vector) {
        final int intValue = vector.elementAt(0);
        final int intValue2 = vector.elementAt(1);
        final int intValue3 = vector.elementAt(2);
        final int intValue4 = vector.elementAt(3);
        if (intValue < this.do.x && intValue2 < this.do.y && intValue3 > this.byte.x && intValue4 > this.byte.y) {
            super.a = true;
        }
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        final Point do1 = this.do;
        do1.x += n3 - n;
        final Point byte1 = this.byte;
        byte1.x += n3 - n;
        final Point new1 = this.new;
        new1.x += n3 - n;
        final Point do2 = this.do;
        do2.y += n4 - n2;
        final Point new2 = this.new;
        new2.y += n4 - n2;
        final Point byte2 = this.byte;
        byte2.y += n4 - n2;
        this.int();
    }
    
    void if(final Graphics graphics, final int n, final int n2) {
    }
    
    public void a(final Graphics graphics, final boolean b) {
        if (!b) {
            graphics.setColor(this.goto);
        }
        else if (!super.a) {
            return;
        }
        if (!this.int) {
            graphics.drawPolygon(this.try);
        }
        if (this.int) {
            graphics.fillPolygon(this.try);
        }
    }
    
    public void a(final Graphics graphics) {
        graphics.setColor(Color.red);
        final Rectangle bounds = this.try.getBounds();
        graphics.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
    
    void a(final Graphics graphics, final int n, final int n2) {
    }
    
    public Rectangle for() {
        return this.try.getBounds();
    }
    
    private void int() {
        final int[] for1 = { this.do.x, this.new.x + 1, this.byte.x };
        final int[] case1 = { this.do.y, this.new.y + 1, this.byte.y };
        this.for = for1;
        this.case = case1;
        this.try = new Polygon(for1, case1, 3);
    }
    
    void a() {
    }
    
    Color do() {
        return Color.gray;
    }
    
    public String if() {
        final String string = "" + "triangle;" + this.for[0] + ";" + this.case[0] + ";" + this.for[1] + ";" + this.case[1] + ";" + this.goto.getRGB() + ";" + (int)this.else + ";" + this.for[2] + ";" + this.case[2] + ";";
        String s;
        if (this.int) {
            s = string + "0;";
        }
        else {
            s = string + "1;";
        }
        return s;
    }
    
    public void if(final int n, final int n2) {
        this.byte = new Point(n, n2);
        final Point a = this.a(this.do, this.byte);
        this.new = new Point(a.x, a.y);
        this.int();
    }
    
    void if(final Point point) {
    }
    
    void a(final Point point) {
    }
    
    void a(final int n, final int y, final int n2) {
        if (n2 == 4) {
            this.new.y = y;
        }
        if (n2 == 5) {
            this.new.x = n;
            this.new.y = y;
        }
        if (n2 == 6) {
            this.byte.x = n;
        }
        if (n2 == 7) {
            this.byte.x = n;
            this.byte.y = y;
        }
        if (n2 == 8) {
            this.byte.y = y;
            this.do.y = y;
        }
        if (n2 == 9) {
            this.do.x = n;
            this.do.y = y;
        }
        if (n2 == 10) {
            this.do.x = n;
        }
        if (n2 == 11) {
            this.new.x = n;
            this.new.y = y;
        }
        this.int();
    }
    
    private Point a(final Point point, final Point point2) {
        final Point point3 = new Point(point2.x - point.x, point2.y - point.y);
        return new Point((int)(point3.x / 2.0 - point3.y * 0.866 + point.x), (int)(point3.x * 0.866 + point3.y / 2.0 + point.y));
    }
    
    int a(final int n, final int n2) {
        final Rectangle bounds = this.try.getBounds();
        if (this.do(n, n2) == null) {
            return -1;
        }
        if (n > bounds.x + 3 && n < bounds.x + bounds.width - 3 && n2 > bounds.y - 3 && n2 < bounds.y + 3) {
            return 4;
        }
        if (n > bounds.x + bounds.width - 3 && n < bounds.x + bounds.width + 3 && n2 > bounds.y - 3 && n2 < bounds.y + 3) {
            return 5;
        }
        if (n > bounds.x + bounds.width - 3 && n < bounds.x + bounds.width + 3 && n2 > bounds.y && n2 < bounds.y + bounds.height - 3) {
            return 6;
        }
        if (n > bounds.x + bounds.width - 3 && n < bounds.x + bounds.width + 3 && n2 > bounds.y + bounds.height - 3 && n2 < bounds.y + bounds.height + 3) {
            return 7;
        }
        if (n > bounds.x + 3 && n < bounds.x + bounds.width - 3 && n2 > bounds.y + bounds.height - 3 && n2 < bounds.y + bounds.height + 3) {
            return 8;
        }
        if (n > bounds.x - 3 && n < bounds.x + 3 && n2 > bounds.y + bounds.height - 3 && n2 < bounds.y + bounds.height + 3) {
            return 9;
        }
        if (n > bounds.x - 3 && n < bounds.x + 3 && n2 > bounds.y + 3 && n2 < bounds.y + bounds.height - 3) {
            return 10;
        }
        if (n > bounds.x - 3 && n < bounds.x + 3 && n2 > bounds.y - 3 && n2 < bounds.y + 3) {
            return 11;
        }
        return 1;
    }
}
