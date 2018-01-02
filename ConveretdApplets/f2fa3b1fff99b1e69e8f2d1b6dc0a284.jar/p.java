import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class p extends Canvas
{
    private int if;
    private int a;
    private f else;
    private f c;
    private boolean do;
    private n null;
    private int b;
    private DCQuoteTable int;
    private Dimension e;
    private Cursor byte;
    private Cursor try;
    private Cursor char;
    private Cursor case;
    private int[] goto;
    private int new;
    private int for;
    private boolean long;
    public boolean void;
    private boolean d;
    
    public p(final boolean do1, final DCQuoteTable int1, final boolean void1) {
        this.null = null;
        this.do = do1;
        this.d = int1.Q;
        this.else = new f(this);
        if (this.d) {
            this.c = new f(this);
        }
        this.e = this.size();
        if (do1) {
            this.byte = new Cursor(11);
            this.try = new Cursor(10);
            this.char = new Cursor(0);
            this.case = new Cursor(12);
        }
        this.for = -1;
        this.int = int1;
        this.long = false;
        this.goto = new int[2];
        if (this.d) {
            this.if();
        }
        this.void = void1;
    }
    
    public void a(final n[] array) {
        this.else.a(array);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        boolean b = false;
        final Dimension size = this.size();
        if (size.width != this.e.width || size.height != this.e.height) {
            if (size.width != this.e.width) {
                this.e = size;
                this.int.recalcWidths(size.width);
                this.int.handleResize();
            }
            this.int.int();
            b = true;
        }
        this.a(graphics, b);
    }
    
    public void a(final boolean b) {
        this.a(this.getGraphics(), b);
    }
    
    private int a() {
        int if1;
        if (this.int.getColumn() == -1) {
            if1 = 0;
        }
        else {
            if1 = this.if;
        }
        return if1;
    }
    
    private void a(final Graphics graphics, final boolean b) {
        final Dimension size = this.size();
        final Dimension int1 = this.int();
        final int i = this.int.I;
        int a = 0;
        int n = 0;
        int n2 = 0;
        final int a2 = f.a();
        if (this.int.i) {
            a = this.a();
            n = this.goto[0] - a;
            n2 = this.goto[1] - a;
        }
        if (this.long) {
            graphics.setXORMode(this.int.t[1]);
            graphics.setColor(this.int.t[0]);
            final int n3 = this.a % 8;
            final int n4 = i + a2 + int1.height;
            graphics.drawLine(this.new - a, 0, this.new - a, n4);
            graphics.drawLine(this.goto[this.for] - a, 0, this.goto[this.for] - a, n4);
            this.new = this.goto[this.for];
        }
        else {
            graphics.setPaintMode();
            this.else.a(this.int.goto - this.if, this.a, this.e.width, this.e.height - this.int.I, n, n2, b);
            if (this.d) {
                this.c.a(this.int.goto - this.if, 0, this.e.width, this.e.height, n, n2, b);
            }
            this.else.a(0, i - this.a + a2, this.e);
            if (this.d) {
                this.c.a(0, 0, this.e);
            }
            final int n5 = size.height - int1.height - i - a2;
            if (n5 > 0) {
                graphics.setColor(this.int.J[2]);
                graphics.fillRect(0, int1.height + i + a2, size.width, n5);
            }
            final int n6 = size.width - int1.width;
            if (n6 > 0) {
                graphics.setColor(this.int.J[2]);
                graphics.fillRect(int1.width, 0, n6, size.height);
            }
        }
    }
    
    public void for() {
        if (this.d) {
            this.c.if();
        }
        this.else.if();
    }
    
    public void for(final int if1) {
        this.if = if1;
    }
    
    public void do(final int a) {
        this.a = a;
    }
    
    public void if() {
        this.c.a(new i[] { new i(this.int) });
    }
    
    public n a(final int n, final int n2) {
        final int i = this.int.I;
        n a = null;
        if (k.m == 2) {
            a = this.else.a(n + this.if, n2 + this.a - i - f.a());
        }
        return a;
    }
    
    public int a(final int n, final boolean b) {
        final int n2 = this.if - this.int.goto;
        int column;
        if (n < this.int.goto) {
            column = -1;
            if (b) {
                final int goto1 = this.int.goto;
            }
        }
        else {
            column = this.int.findColumn(n + n2);
            if (b) {
                final int n3 = this.int.P[column].new - n2 + this.int.P[column].case();
            }
        }
        return column;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final int i = this.int.I;
        if (n2 > i) {
            if (k.m > 0) {
                final n a = this.a(n, n2);
                if (this.null != null) {
                    if (this.null != a) {
                        this.do(this.null);
                        if (a != null && (!a.a().equals("#") || a.new != null)) {
                            this.if(a);
                            if (this.do && k.y) {
                                this.setCursor(this.case);
                            }
                            this.else.a(a, 0, i - this.a + f.a(), this.e, 0, 0);
                        }
                        else if (this.do && k.y) {
                            this.setCursor(this.char);
                        }
                        this.else.a(this.null, 0, i - this.a + f.a(), this.e, 0, 0);
                        this.null = a;
                    }
                }
                else if (a != null && (!a.a().equals("#") || a.new != null)) {
                    if (this.do && k.y) {
                        this.setCursor(this.case);
                    }
                    this.if(a);
                    this.null = a;
                    this.else.a(this.null, 0, i - this.a + f.a(), this.e, 0, 0);
                }
            }
        }
        else {
            this.do();
        }
        if (n2 < i && this.void) {
            final int a2 = this.a(n, false);
            this.if(a2);
            this.int.i = true;
            this.int.selectColumn(a2);
            this.a(true);
        }
        else {
            if (this.int.i) {
                this.a(true);
            }
            this.int.i = false;
            this.int.selectColumn(-1);
            if (this.do) {
                this.setCursor(this.char);
            }
            this.for = -1;
        }
        final int column = this.int.getColumn();
        if (column == -1) {
            if (Math.abs(n - this.goto[1]) < 4) {
                this.a(1);
            }
            else {
                this.a(-1);
            }
        }
        else {
            final int n3 = this.goto[0] - this.if;
            final int n4 = this.goto[1] - this.if;
            if (this.int.goto == 0 && column == 0) {
                this.a(-1);
            }
            else if (n3 >= this.int.goto && Math.abs(n - n3) < 4) {
                this.a(0);
            }
            else if (n4 >= this.int.goto && Math.abs(n - n4) < 4) {
                this.a(1);
            }
            else {
                this.a(-1);
            }
        }
        return true;
    }
    
    private void a(final int for1) {
        this.for = for1;
        if (this.do) {
            switch (for1) {
                case -1: {
                    this.setCursor(this.char);
                    break;
                }
                case 0: {
                    this.setCursor(this.try);
                    this.new = this.goto[0];
                    break;
                }
                case 1: {
                    this.setCursor(this.byte);
                    this.new = this.goto[1];
                    break;
                }
            }
        }
    }
    
    public void do() {
        if (this.null != null) {
            this.do(this.null);
            this.else.a(this.null, 0, this.int.I - this.a + f.a(), this.e, 0, 0);
            this.null = null;
            if (this.do && k.y) {
                this.setCursor(this.char);
            }
        }
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.do();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final int i = this.int.I;
        if (event.modifiers != 4) {
            if (this.long) {
                this.long = false;
                int column = this.int.getColumn();
                if (this.for == 0) {
                    --column;
                }
                int n3;
                if (column >= 0) {
                    n3 = this.goto[this.for] - this.int.P[column].new - this.int.goto;
                }
                else {
                    n3 = this.goto[this.for];
                }
                this.int.resizeColumn(column, n3);
                this.for = -1;
                if (this.do) {
                    this.setCursor(this.char);
                }
            }
            else if (this.int.i) {
                this.int.i = false;
                if (this.do) {
                    this.setCursor(this.char);
                }
                this.for = -1;
                this.a(true);
            }
            else if (n2 < i && this.void) {
                final int a = this.a(n, false);
                this.if(a);
                this.int.i = true;
                this.int.selectColumn(a);
                this.a(true);
            }
            else if (this.null != null) {
                final String a2 = this.null.a();
                if (a2.equals("#")) {
                    if (this.null.new != null) {
                        if (this.null.do) {
                            this.int.do().call(this.null.new, (Object[])null);
                        }
                        else {
                            try {
                                this.int.getAppletContext().showDocument(new URL(this.null.new), this.int.f);
                            }
                            catch (MalformedURLException ex) {}
                        }
                    }
                }
                else if (this.int.O) {
                    this.int.do().call(this.int.af, new Object[] { a2 });
                }
                else {
                    try {
                        this.int.getAppletContext().showDocument(new URL(this.int.af + a2), this.int.f);
                    }
                    catch (MalformedURLException ex2) {}
                }
            }
        }
        return true;
    }
    
    public void if(final int n) {
        if (n == -1) {
            this.goto[0] = 0;
            this.goto[1] = this.int.goto - 1;
        }
        else {
            final b b = this.int.P[n];
            this.goto[0] = b.new + this.int.goto;
            this.goto[1] = this.goto[0] + b.case() - 1;
        }
        if (this.int.Z) {
            final int[] goto1 = this.goto;
            final int n2 = 0;
            ++goto1[n2];
            final int[] goto2 = this.goto;
            final int n3 = 1;
            ++goto2[n3];
        }
    }
    
    public void if(final n n) {
        if (k.m == 2) {
            n.a(true);
            n.a(0, this.e.width, 0, 0);
            n.a(1, this.e.width, 0, 0);
        }
        else if (k.m == 1) {
            n.if(true);
            n.a(1, this.e.width, 0, 0);
        }
    }
    
    public void do(final n n) {
        if (k.m == 2) {
            n.a(false);
            n.a(0, this.e.width, 0, 0);
            n.a(1, this.e.width, 0, 0);
        }
        else if (k.m == 1) {
            n.if(false);
            n.a(1, this.e.width, 0, 0);
        }
    }
    
    public void a(final n n) {
        if (!this.long) {
            final int a = this.a();
            this.else.a(n, 0, this.int.I - this.a + f.a(), this.e, this.goto[0] - a, this.goto[1] - a);
        }
    }
    
    public Dimension int() {
        return this.else.do();
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.for > -1) {
            this.long = true;
            int column = this.int.getColumn();
            if (this.for == 0) {
                --column;
            }
            if (column >= 0) {
                this.goto[this.for] = Math.min(Math.max(n + this.if, this.int.P[column].new + this.int.T + this.int.goto), this.int.P[column].new + this.int.H + this.int.goto);
            }
            else {
                this.goto[this.for] = Math.min(Math.max(n, this.int.T), this.int.H);
            }
            this.a(false);
        }
        return true;
    }
}
